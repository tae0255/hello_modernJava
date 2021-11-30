package Chapter02;

public class RunnableExercise {

    public void _test(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world");
            }
        });
        t.run();

        Thread t2 = new Thread(()->System.out.println("Hello world"));
        t2.run();
    }


}
