package Chapter02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorExercise {

    public void _test(){
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(Color.GREEN, 500, 1));
        inventory.add(new Apple(Color.RED, 750, 2));
        inventory.add(new Apple(Color.RED, 4000, 3));
        inventory.add(new Apple(Color.GREEN, 600, 4));
        inventory.add(new Apple(Color.RED, 100, 5));
        inventory.add(new Apple(Color.GREEN, 1000, 6));


        List<Apple> t1 = new ArrayList<>(inventory);
        t1.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        for(Apple a : t1) System.out.print(a.getWeight()+" ");
        System.out.println();

        List<Apple> t2 = new ArrayList<>(inventory);
        t2.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));
        for(Apple a : t2) System.out.print(a.getWeight()+" ");
        System.out.println();

    }

    enum Color {RED, GREEN}

    public class Apple {
        Color color;
        Integer weight;
        Integer no;

        Apple(Color color, int weight, int no){
            this.color = color;
            this.weight = Integer.valueOf(weight);
            this.no = Integer.valueOf(no);
        }

        public Color getColor(){return this.color;}
        public Integer getWeight(){return this.weight;}
        public Integer getNo(){return this.no;}

    }

}
