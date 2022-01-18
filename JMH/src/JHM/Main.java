import FakeDataSet.FakePerson;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main{

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();
    public static void main(String[] args){
        new FakePerson()._test();


        /*
        //ParallelStreams는 입력없이 결과를 출력하므로, ()->T 시그니처를 가지는 Supplier 인터페이스
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");
        //System.out.println("Parallel forkJoinSum done in: " + measurePerf(ParallelStreams::parallelSum, 10_000_000L) + " msecs" );
        System.out.println("Range forkJoinSum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs");
        System.out.println("Parallel range forkJoinSum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs" );
        
        //ForkJoinSumCalculator는 입력/결과 모두 있으므로, (T)->R 시그니처를 가지는 Function 인터페이스
        System.out.println("ForkJoin sum done in: " + measurePerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " msecs" );*/

    }

    private static <T, R> Long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    public static <T> long measurePerf(Supplier<T> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            T result = f.get();
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}