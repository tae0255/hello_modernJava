import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args){
        //File[] hiddenFiles = new File("C:\\").listFiles(File::isHidden);
        //System.out.println(Arrays.stream(hiddenFiles).toArray().toString());


        //new CollectorExercise()._test();
        List<Integer> list = IntStream.range(0, 10)
                .boxed()
                .collect(toList());

        //https://hamait.tistory.com/547
        try{
            System.out.println("list.stream().peek(list::remove).forEach(System.out::println);");
            list.stream().peek(list::remove).forEach(System.out::println);
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        try{
            System.out.println("\n\nlist.stream().sorted().peek(list::remove).forEach(System.out::println);");
            list.stream().sorted().peek(list::remove).forEach(System.out::println);
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        try{
            System.out.println("\n\nlist.stream().sorted().parallel().peek(list::remove).forEach(System.out::println);");
            list.stream().sorted().parallel().peek(list::remove).forEach(System.out::println);
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

    }
}
