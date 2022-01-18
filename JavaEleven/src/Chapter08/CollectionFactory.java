package CHAPTER08;

import java.util.*;

import static java.util.Map.entry;
import static java.util.stream.Collectors.toSet;

public class CollectionFactory {
    public static void _test() {
        //oldJavaFactory();
        //listFactory();
        //setFactory();
        //mapFactory();

        final Person p = new Person("Bob", 30);
        //p = new Person("John", 25);
        p.name = "John";
        p.age = 25;
    }

    private static class Person{
        public String name;
        public int age;

        Person(String name, int age){
            this.name = name;
            this.age = age;
        }

    }


    public static void oldJavaFactory(){
        List<String> friends = new ArrayList<>();
        friends.add("Rael");
        friends.add("Oli");
        friends.add("Thiba");

        try{
            List<String> friends2 = Arrays.asList("Rael", "Oli");
            friends2.set(0, "Rich");
            friends2.add("Thiba");
            //AbstractList 추상클래스는 add, set 모두 UnsupportedException을 뱉는다.
            //Arrays.asList는 주어진형식을 받아 ArrayList를 반환하는데
            //Arrays는 private ArrayList에서 set은 가능하도록 재정의했다.

            ArrayList<String> friends3 = new ArrayList<>(Arrays.asList("Rael", "oli"));
            friends3.set(0, "Rich");
            friends3.add("Thiba");
            //ArrayList는 add, set모두 가능하도록 재정의했다.

        }catch (UnsupportedOperationException e){
            e.printStackTrace();
        }
    }

    public static void listFactory(){
        List<String> friends = List.of("Rael", "Oli", "Thiba");
        System.out.println(friends);

        try{
            friends.add("Taey");
            friends.set(0, "Taey");
        }catch(UnsupportedOperationException e){
            e.printStackTrace();
        }


    }

    private static void setFactory() {
        Set<String> friends = Set.of("Rael", "Oli", "Thiba");
        //Set<String> friends2 = Set.of("Rael", "Oli", "Thiba", "Rael"); //예외발생
        Set<String> friends3 = List.of("Rael", "Oli", "Thiba", "Rael").stream().distinct().collect(toSet());
    }

    private static void mapFactory() {
        Map<String, Integer> ageOfFriends = Map.of("Rael", 30, "Oli", 25, "Thiba", 26);
        Map<String, Integer> ageOfFriends2 = Map.ofEntries(
                entry("Rael", 30),
                entry("Oli", 25),
                entry("Thiba", 26)
        );


    }




}
