package Parallel;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
    private static final long N = 10_000_000L;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ParallelStreams.class.getSimpleName())
                .threads(Runtime.getRuntime().availableProcessors())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public static Long sequentialSum(){
        return Stream.iterate(1L, i->i+1).limit(N)
                .reduce(0L, Long::sum);
    }

    @Benchmark
    public static Long parallelRangedSum(){
        return LongStream.rangeClosed(1, N)
                .parallel()
                .reduce(0L, Long::sum);
    }

    @Benchmark
    public static Long iterativeSum(){
        long result = 0;
        for(long i = 1L; i<=N; i++){
            result+=i;
        }
        return result;
    }

    @Benchmark
    public static Long rangedSum(){
        return LongStream.rangeClosed(1, N)
                .reduce(0L, Long::sum);
    }

    @Benchmark
    public static Long parallelSum(){
        return LongStream.iterate(1L, i->i+1)
                .parallel()
                .reduce(0L, Long::sum);
    }

}
