package CHAPTER05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamExercise {
    public void _test(){


        System.out.println("========= 스트림 : 기본예제 =========");
        {
            List<Integer> numbers = Arrays.asList(1,2,3,4,5);
            List<Integer> squares = numbers.stream()
                    .map(x->x*x)
                    .collect(toList());
            System.out.println(squares.toString());


            List<Integer> numbers1 = Arrays.asList(1,2,3);
            List<Integer> numbers2 = Arrays.asList(3,4);
            List<int[]> pairs = numbers1.stream()
                    .flatMap(i->numbers2.stream()
                            .map(j->new int[]{i,j}))
                    .collect(toList());
            System.out.println(pairs.toString());
        }

        System.out.println("\n========= 스트림 : 활용예제 p.177 =========");
        {
            Trader t1 = new Trader("Raoul", "Cambridge");
            Trader t2 = new Trader("Mario", "Milan");
            Trader t3 = new Trader("Alan", "Cambridge");
            Trader t4 = new Trader("Brian", "Cambridge");

            List<Transaction> transactions = Arrays.asList(
                    new Transaction(t4, 2011, 300),
                    new Transaction(t1, 2012, 1000),
                    new Transaction(t1, 2011, 400),
                    new Transaction(t2, 2012, 710),
                    new Transaction(t2, 2012, 700),
                    new Transaction(t3, 2012, 950)
            );

            //1. 2011에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리
            List<Transaction> e1 = transactions.stream()
                    .filter(a->a.getYear()==2011)
                    .sorted(comparing(Transaction::getValue))
                    .collect(Collectors.toList());
            System.out.println(e1.toString());

            //2. 거래자가 근무하는 모든 도시를 중복없이 나열
            List<String> e2 = transactions.stream()
                    .map(a->a.getTrader().getCity())
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println(e2.toString());

            //3. 케임브릿지에서 근무하는 모든 거래자를 찾아 이름순으로 정렬
            List<Trader> e3 = transactions.stream()
                    .map(Transaction::getTrader)
                    .filter(a->a.getCity().equals("Cambridge"))
                    .distinct()
                    .sorted((o1, o2)->o1.getName().compareTo(o2.getName()))
                    .collect(Collectors.toList());
            //.collect(toSet());
            System.out.println(e3.toString());

            //4. 모든 거래자 이름을 알파벳순으로 정렬하고 하나의 문자열로 변환하여 반환
            String e4 = transactions.stream()
                    .map(a->a.getTrader().getName())
                    .distinct()
                    .sorted()
                    .reduce("", (s1, s2)->s1+s2);
            //.collect(joining());
            System.out.println(e4.toString());

            //5. 밀라노에 거래자가 있는가
            Boolean e5 = transactions.stream()
                    .map(a->a.getTrader().getCity())
                    .anyMatch(a->a.equals("Millano"));
            Boolean e51 = transactions.stream()
                    .map(a->a.getTrader().getCity())
                    .anyMatch(a->a.equals("Cambridge"));
            System.out.println(e5.toString());
            System.out.println(e51.toString());

            //6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력
            transactions.stream()
                    .filter(a->a.getTrader().getCity().equals("Cambridge"))
                    .forEach(a->System.out.print(a.getValue()+" "));
            System.out.println();

            //7. 전체 트랜잭션중 최댓값은 얼마인가
            Optional<Integer> e7 = transactions.stream()
                    .map(Transaction::getValue)
                    .reduce(Integer::max);
            System.out.println(e7.toString());

            //8. 전체 트랜잭션중 최솟값은 얼마인가
            Optional<Integer> e8 = transactions.stream()
                    .map(Transaction::getValue)
                    .reduce(Integer::min);
            System.out.println(e8.toString());

            /*System.out.println("========= 스트림 : 기본형 특화 스트림 p.183=========");
            IntStream intStream = transactions.stream().mapToInt(Transaction::getValue);
            Stream<Integer> justStream = intStream.boxed();
            //System.out.println(intStream.toString());
            //System.out.println(justStream.toString());*/
        }


        System.out.println("\n========= 스트림 : 피타고라스의 수 p.185=========");
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b->Math.sqrt(a*a + b*b)%1==0)
                                .mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)}))
                .limit(5).forEach(t->System.out.print("("+t[0]+","+t[1]+","+t[2]+") "));
        System.out.println();

        IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a->IntStream.rangeClosed(a,100)
                                .mapToObj(b->new double[]{a,b,Math.sqrt(a*a+b*b)})
                                        .filter(t->t[2]%1==0))
                                .limit(5).forEach(t->System.out.print("("+t[0]+","+t[1]+","+t[2]+") "));
        System.out.println();

        System.out.println("\n========= 스트림 : NULL SAFETY p.189=========");
        Stream<String> notNullStream = Stream.of("M","A","R","I","O");
        Stream<String> nullableStream = Stream.ofNullable(null);
        String v = null;
        Stream<String> oldNullableStream = v==null? Stream.empty():Stream.of(v);

        System.out.println("\n========= 스트림 : 파일 읽기 예제 p.190=========");
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("D:\\DailyLogs\\README.md"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(uniqueWords);

        System.out.println("\n========= 스트림 : 무한스트림 피보나치 p.191=========");
        Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(20)
                .forEach(x-> System.out.print(x[1]+" "));
        System.out.println();
        Stream.iterate(new int[]{0, 1}, x-> x[1]<100, x -> new int[]{x[1], x[0] + x[1]})
                .limit(20)
                .forEach(x-> System.out.print(x[1]+" "));
        System.out.println();

        System.out.println("\n========= 스트림 : generate 메서드 p.193=========");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        System.out.println();

        IntStream ones = IntStream.generate(()->1);
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };


    }




}
