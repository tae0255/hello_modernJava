package CHAPTER08;

import java.util.*;

public class ListSetProcessor {

    public static void _test(){
        //oldJavaRemove();
        //exerciseRemoveIf();
        //exerciseReplaceAll();
        //exerciseSort();
    }

    private static void oldJavaRemove() {
        TestData td = new TestData();
        for(Person p : td.people){
            //if(p.age > 50) td.people.remove(p);
        }
        System.out.println(td.people);
    }

    public static void exerciseRemoveIf(){
        TestData td = new TestData();
        td.people.removeIf(p -> p.age>50);
        System.out.println(td.people);

        /*td.people.stream()
                .map(p -> p.name.toLowerCase())
                .collect(Collectors.toList())
                .forEach(System.out::println);*/

        for(ListIterator<Person> itr = td.people.listIterator(); itr.hasNext(); ){
            Person p = itr.next();
            itr.set(changeName(p));
        }
        System.out.println(td.people);

    }

    private static void exerciseReplaceAll() {
        TestData td = new TestData();
        td.people.replaceAll(ListSetProcessor::changeName);
        System.out.println(td.people);

    }

    private static Person changeName(Person p) {
        return new Person(p.name.toLowerCase(), p.age);
    }

    private static void exerciseSort() {
        TestData td = new TestData();
        td.people.sort((o1, o2) -> Integer.compare(o1.age, o2.age));
        System.out.println(td.people);
    }


    private static class TestData{
        List<Person> people;
        TestData(){
            people = new ArrayList<Person>();
            people.add(new Person("김이름",30));
            people.add(new Person("이익명",40));
            people.add(new Person("최백수",50));
            people.add(new Person("박진수",60));
            people.add(new Person("공금례",70));
            people.add(new Person("오막례",90));
            people.add(new Person("John",30));
            people.add(new Person("Henry",40));
            people.add(new Person("Rachel",50));
            people.add(new Person("Michael",60));
            people.add(new Person("Shell",70));
            people.add(new Person("Python",90));

        }
    }

    private static class Person{
        String name;
        int age;
        Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString(){return name + " : " + age;}
    }
}
