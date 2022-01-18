package STREAM_EXERCISE;

//https://receptive-tempo-0e5.notion.site/2676eec0707749aa9e6bdb6f9bfd6151

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class EXERCISE01 {
    public void exec(){


        List<String> strList = Arrays.asList("jiwoong", "", "hyungjoon", "", "minseung", "hyunjoon", "yoochul", "Tae0");

        System.out.println("1. 빈 문자열의 개수를 출력");
        System.out.println(strList.stream().filter(o->o.isEmpty()).count());

        System.out.println("2. h로 시작하는 문자열의 개수를 출력");
        System.out.println(strList.stream().filter(o->o.startsWith("h")).count());

        System.out.println("3. 길이가 2 이상인 문자열만 따로 리스트에 저장");
        System.out.println(strList.stream().filter(o->o.length()>=2).collect(Collectors.toList()));





        List<String> words = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

        System.out.println("4. 접두사가 되는 문자열 개수 세기");
        Map<String, Integer> map = new HashMap<>();
        words.forEach(s->{
            int cnt = (int)words.stream().filter(s2->s2.startsWith(s)).count();
            map.put(s, cnt);
        });
        System.out.println(map);

        System.out.println("5. 길이가 2이상인 문자를 대문자로 바꾸어 공백으로 구분하여 한 문자열로 합치기");
        String result = words.stream().filter(w->w.length()>1).map(String::toUpperCase).collect(Collectors.joining(" "));
        System.out.println(result);




        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        System.out.println("6. 두 숫자 리스트의 모든 조합");
        numbers1.stream()
                .flatMap(i->numbers2.stream()
                        .map(j->new int[]{i,j}))
                .collect(Collectors.toList())
                .forEach(o->System.out.println(o[0]+" "+o[1]));

        System.out.println("6. 두 숫자 리스트간 곱의 최대값");
        System.out.println(numbers1.stream().flatMap(i->numbers2.stream().map(j->i*j)).reduce(Integer::max).get());




        init();
        System.out.println("7. 2020에 일어난 모든 거래내역을 찾아 거래값을 기준으로 오름차순 정렬");
        transactions.stream().filter(t->t.year==2020).sorted(Comparator.comparingInt(t -> t.value)).forEach(System.out::println);

        System.out.println("8. 거래내역 있는 거래자가 근무하는 모든 도시를 중복없이 나열");
        transactions.stream().map(t->t.trader.city).distinct().forEach(System.out::println);

        System.out.println("9. 서울에서 근무하는 모든 거래자를 찾아서 이름순서대로 정렬");
        transactions.stream().map(t->t.trader).filter(t->t.city.equals("Seoul")).sorted(Comparator.comparing(t -> t.name)).forEach(t->System.out.println(t.name));

        System.out.println("10. 부산에 거래자가 있는지 확인");
        System.out.println(transactions.stream().filter(t->t.trader.city.equals("Busan")).count());

        System.out.println("11. 서울에 거주하는 거래자의 모든 거래내역");
        transactions.stream().filter(t->t.trader.city.equals("Seoul")).forEach(System.out::println);

        System.out.println("12. 모든 거래내역중 최댓값과 최솟값, 단 최댓값은 reduce 최솟값은 min");
        System.out.println(transactions.stream().map(t->t.value).reduce(Integer::max).orElse(-1));
        System.out.println(transactions.stream().collect(reducing(0, Transaction::getValue, Integer::min)));
        System.out.println(transactions.stream().min(Comparator.comparingInt(Transaction::getValue)).orElseThrow(RuntimeException::new).getValue());






        String[] strArr = {"aaa", "bb", "c", "dddd"};

        System.out.println("13. 문자열배열의 모든 문자열 길이를 더한 값");
        Arrays.stream(strArr).collect(reducing(0, o->o.length(), Integer::sum));
        System.out.println(Arrays.stream(strArr).map(String::length).reduce(0, Integer::sum));

        System.out.println("14. 문자열배열에서 가장 긴 문자열의 길이");
        System.out.println(Arrays.stream(strArr).map(String::length).reduce(Integer::max));

        System.out.println("15. 임의 로또번호를 정렬해서 출력");
        new Random().ints(1,46).distinct().limit(6).sorted().forEach(System.out::println);

        System.out.println("16. 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력");
        IntStream.rangeClosed(1,6).boxed()
                .flatMap(i->IntStream.rangeClosed(1,6).boxed().map(j->new Integer[]{i,j}))
                .filter(arr->arr[0]+arr[1]==6)
                .forEach(o->System.out.println(o[0]+" "+o[1]));




        Student[] stuArr = new Student[]{
                new Student("나자바", true, 1, 1, 300),
                new Student("김지미", false, 1, 1, 250),
                new Student("김자바", true, 1, 1, 200),
                new Student("이지미", false, 1, 2, 150),
                new Student("남자바", true, 1, 2, 100),
                new Student("안지미", false, 1, 2, 50),
                new Student("황지미", false, 1, 3, 100),
                new Student("강지미", false, 1, 3, 150),
                new Student("이자바", true, 1, 3, 200),
                new Student("나자바", true, 2, 1, 300),
                new Student("김지미", false, 2, 1, 250),
                new Student("김자바", true, 2, 1, 200),
                new Student("이지미", false, 2, 2, 150),
                new Student("남자바", true, 2, 2, 100),
                new Student("안지미", false, 2, 2, 50),
                new Student("황지미", false, 2, 3, 100),
                new Student("강지미", false, 2, 3, 150),
                new Student("이자바", true, 2, 3, 200)
        };


        System.out.println("17. 불합격(150점 미만)한 학생 수를 남자와 여자로 구별해 출력");
        System.out.println(Arrays.stream(stuArr).collect(partitioningBy(Student::isMale, partitioningBy(s->s.getScore()<150, counting()))));

        System.out.println("18. 각 반별 총점을 학년 별로 나누어 구하기");
        System.out.println(Arrays.stream(stuArr).collect(groupingBy(Student::getHak, groupingBy(Student::getBan, summingInt(Student::getScore)))));



    }

    private static class Student {

        private String name;
        private boolean isMale; // 성별
        private int hak; // 학년
        private int ban; // 반
        private int score;

        Student(String name, boolean isMale, int hak, int ban, int score){
            this.name = name;
            this.isMale = isMale;
            this.hak = hak;
            this.ban = ban;
            this.score = score;
        }

        public boolean isMale(){
            return isMale;
        }

        public int getScore(){
            return score;
        }

        public int getBan(){
            return ban;
        }

        public int getHak(){
            return hak;
        }

        public String toString() {
            return String.format("[%s, %s, %d학년 %d반, %3d점 ]", name, isMale ? "남" : "여", hak, ban, score);
        }

        // groupingBy()에서 사용 성적을 상,중,하 세 단계로 분류
        enum Level {
            HIGH, MID, LOW
        }

    }

    private List<Transaction> transactions;

    private void init() {
        Trader kyu = new Trader("Kyu", "Seoul");
        Trader ming = new Trader("Ming", "Gyeonggi");
        Trader hyuk = new Trader("Hyuk", "Incheon");
        Trader hwan = new Trader("Hyuk", "Seoul");
        transactions = Arrays.asList(
                new Transaction(kyu, 2019, 30000),
                new Transaction(kyu, 2020, 12000),
                new Transaction(ming, 2020, 40000),
                new Transaction(ming, 2020, 7100),
                new Transaction(hyuk, 2019, 5900),
                new Transaction(hwan, 2020, 4900)
        );
    }


    private class Trader {
        private String name;
        private String city;
        Trader(String name, String city){
            this.name = name;
            this.city = city;
        }
    }

    private class Transaction {
        private Trader trader;
        private int year;
        private int value;
        Transaction(Trader trader, int year, int value){
            this.trader = trader;
            this.year = year;
            this.value = value;
        }
        int getValue(){
            return this.value;
        }
        @Override
        public String toString(){
            return trader + ", " + year + ", " + value;
        }
    }

}
