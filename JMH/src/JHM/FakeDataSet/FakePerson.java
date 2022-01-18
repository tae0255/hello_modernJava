package FakeDataSet;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class FakePerson {

    public void _test() {
        HashMap<String, String> documents = new HashMap<>();
        Faker faker = new Faker();
        for(int i=0; i<100000; i++){
            documents.put(faker.name().fullName(), faker.address().fullAddress());
        }

        //documents.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(System.out::println);
        //documents.forEach((name, address)->System.out.println(name+" : "+address));
        //System.out.println(documents.getOrDefault("Chani", "NOT_FOUND"));
        //documents.entrySet().stream().parallel().sorted(Map.Entry.comparingByKey()).limit(10).forEachOrdered(System.out::println);

        System.out.println("# filter startWith A, limit 10");
        documents.entrySet().stream().filter(e -> e.getKey().startsWith("A")).limit(10).forEach(System.out::println);

        System.out.println("\n# distinct, limit 10");
        documents.entrySet().stream().distinct().limit(10).forEach(System.out::println);

        System.out.println("\n# map address -> length, limit 10");
        documents.entrySet().stream().map(e -> e.getValue().length()).limit(10).forEach(System.out::println);

        System.out.println("\n# collect, joining string");
        System.out.println(documents.keySet().stream().limit(10).collect(joining(" ")));

        System.out.println("\n# collect, reducing for joining string");
        System.out.println(documents.keySet().stream().limit(10).collect(reducing("", (o, v)->o+","+v)));

        System.out.println("\n# reduce for joining string");
        System.out.println(documents.keySet().stream().limit(10).reduce("", (o, v)->o+","+v));

        System.out.println("\n# replaceAll name toUpperCase, limit 10");
        documents.replaceAll((k,v)->k.toUpperCase());
        documents.entrySet().stream().limit(10).forEach(System.out::println);


    }

}
