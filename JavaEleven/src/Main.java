import Chapter02.CallableExercise;
import Chapter02.ComparatorExercise;
import Chapter02.RunnableExercise;

public class Main {
    public static void main(String[] args){
        //new ComparatorExercise()._test();
        //new RunnableExercise()._test();
        //new CallableExercise()._test();

        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }

    public static class MeaningOfThis{
        public final int value = 4;
        public void doIt(){
            int value = 6;
            Runnable r = new Runnable() {
                public final int value = 5;
                @Override
                public void run() {
                    int value = 10;
                    System.out.println(value);
                    System.out.println(this.value);
                }
            };
            r.run();
        }

    }

}
