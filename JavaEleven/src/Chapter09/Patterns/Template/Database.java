package CHAPTER09.Patterns.Template;

import java.util.HashMap;

public class Database {
    private static HashMap<Integer, Customer> database = new HashMap<>();

    public static void putCustomerWithId(int id, Customer c){
        database.put(id, c);
    }

    public static Customer getCustomerWithId(int id) {
        return database.get(id);
    }
}
