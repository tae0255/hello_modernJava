package CHAPTER09.Patterns.Template;

import java.util.function.Consumer;

public class OnlineBankingLamda {
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
}
