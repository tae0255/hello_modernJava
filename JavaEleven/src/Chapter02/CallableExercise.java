package Chapter02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExercise {

    public void _test(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> threadName = executorService.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });
        System.out.println(threadName);

        Future<String> threadName2 = executorService.submit(()->Thread.currentThread().getName());
        System.out.println(threadName2);
    }
}
