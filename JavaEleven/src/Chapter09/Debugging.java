package CHAPTER09;

import CHAPTER09.Test.Point;

import java.util.Arrays;
import java.util.List;

public class Debugging {
    public void _test() {
        //lamdaTrace();
        loggingStream();
    }

    public void lamdaTrace(){
        List<Point> points = Arrays.asList(new Point(12,2), null);
        points.stream().map(p->p.getX()).forEach(System.out::println);
    }

    public void loggingStream(){
        List<Integer> numbers = Arrays.asList(2,3,4,5);
        numbers.stream()
                .peek(x->System.out.println("from stream: "+x))
                .map(x->x+17)
                .peek(x->System.out.println("after map: "+x))
                .filter(x->x%2==0)
                .peek(x->System.out.println("after filter: "+x))
                .limit(3)
                .peek(x->System.out.println("after limit: "+x))
                .forEach(System.out::println);
    }
}
