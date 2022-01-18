package CHAPTER06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collectors.*;

public class CollectorExercise {

    public enum Type {FISH, MEAT, OTHER};

    public void _test(){

        //빈 스트림에 연산해서 반환된 Optional 객체 접근하기
        /*int result = Arrays.asList(new Dish[]{}).stream()
                .map(Dish::getCalories)
                .collect(reducing(Integer::sum))
                .get();
        System.out.println(result);*/

        //??

        //빈 스트림에 연산해서 반환된 Optional 객체에 접근하기
        Dish[] dishes = {new Dish(Type.FISH, 100), new Dish(Type.MEAT, 200), new Dish(Type.MEAT, 300)};
        Map<Type, Dish> mostCaloricByType = Arrays.asList(dishes).stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                        Optional::get)));
        System.out.println(mostCaloricByType.toString());

        //2~100 정수를 소수와 소수가 아닌 것으로 분할하기
        Map<Boolean, List<Integer>> primeList = IntStream.range(2, 100).boxed()
                .collect(partitioningBy(x -> isPrime(x)));
        System.out.println(primeList);

        //커스텀 구현한 콜렉터 사용하기
        List<Dish> collectedDishes = Arrays.asList(dishes).stream().collect(new ToListCollector<Dish>());
        List<Dish> collectedDishes2 = Arrays.asList(dishes).stream().collect(toList());

        //2~100 정수를 소수와 소수가 아닌 것으로 분할하는데
        //소수를 판별할 때는, 이전까지 확인된 소수로만 나머지 연산해서 확인하기


    }

    public boolean isPrime(int candidate){
        return IntStream.range(2, (int)Math.sqrt(candidate)).noneMatch(i->candidate%i==0);
    }

    public boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return primes.stream()
                .takeWhile(i->i<=candidateRoot)
                .noneMatch(i->candidate%i==0);
    }

    //자바8을 위한 takeWhile호환메서드
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for(A item : list){
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    class Dish{
        private Type type;
        private int calories;
        Dish(Type t, int c){
            this.type = t;
            this.calories = c;
        }
        public Type getType(){
            return this.type;
        }

        public int getCalories(){
            return this.calories;
        }
    }

    class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new; //수집연산의 시작점
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add; //탐색한 항목을 어떻게 누적할 것인지
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (list1, list2) ->{ //분할/병렬처리한 항목을 어떻게 합칠 것인지
                list1.addAll(list2);
                return list1;
            };
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            return Function.identity(); //항등함수, 연산 끝나면 자기자신을 반환
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
        }
    }
}
