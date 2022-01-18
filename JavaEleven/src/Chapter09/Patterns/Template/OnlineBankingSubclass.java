package CHAPTER09.Patterns.Template;

public class OnlineBankingSubclass extends OnlineBanking{
    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println("Hello "+c);
    }
}
