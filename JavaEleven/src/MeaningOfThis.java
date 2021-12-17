public class MeaningOfThis {
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
