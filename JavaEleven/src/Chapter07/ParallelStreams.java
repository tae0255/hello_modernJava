package Chapter07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
    private static final long N = 10_000_000L;

    public static Long sequentialSum(){
        return Stream.iterate(1L, i->i+1).limit(N)
                .reduce(0L, Long::sum);
    }

    public static Long parallelRangedSum(){
        return LongStream.rangeClosed(1, N)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static Long iterativeSum(){
        long result = 0;
        for(long i = 1L; i<=N; i++){
            result+=i;
        }
        return result;
    }

    public static Long rangedSum(){
        return LongStream.rangeClosed(1, N)
                .reduce(0L, Long::sum);
    }

    public static Long parallelSum(){
        return LongStream.iterate(1L, i->i+1)
                .parallel()
                .reduce(0L, Long::sum);
    }

}
