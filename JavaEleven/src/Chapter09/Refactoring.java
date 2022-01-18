package CHAPTER09;

import CHAPTER09.Patterns.DutyChain.HeaderTextProcessing;
import CHAPTER09.Patterns.DutyChain.ProcessingObject;
import CHAPTER09.Patterns.DutyChain.SpellCheckerProcessing;
import CHAPTER09.Patterns.Factory.*;
import CHAPTER09.Patterns.Observer.Feed;
import CHAPTER09.Patterns.Observer.Guardian;
import CHAPTER09.Patterns.Observer.LeMonde;
import CHAPTER09.Patterns.Observer.NYTimes;
import CHAPTER09.Patterns.Strategy.IsAllLowerCase;
import CHAPTER09.Patterns.Strategy.IsNumeric;
import CHAPTER09.Patterns.Strategy.Validator;
import CHAPTER09.Patterns.Template.*;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Refactoring {
    public void _test(){
        //lamdaStrategy();
        //lamdaTemplate();
        //lamdaObserver();
        //lamdaDutyChain();
        //lamdaFactory();
    }

    private void lamdaFactory(){
        Product p = ProductFactory.createProduct("loan");
        Product p2 = ProductFactoryLamda.createProduct("loan");
    }

    private void lamdaDutyChain(){
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Arn't labdas really cool?!");
        System.out.println(result);

        UnaryOperator<String> headerTextProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lamda");
        Function<String, String> pipeline = headerTextProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Arn't labdas really cool?!");
        System.out.println(result2);

    }

    private void lamdaObserver(){
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Modern Java in Action!");

        f.registerObserver((String tweet)->{
            if(tweet!=null && tweet.contains("Kimchi")){
                System.out.println("Hottes news from Republic of Korea! " + tweet);
            }
        });
        f.notifyObservers("I love Kimchi, and Saranghaeyo YeunEGaJungGye!");

    }

    private void lamdaTemplate(){
        Database.putCustomerWithId(1337, new Customer());
        new OnlineBankingSubclass().processCustomer(1337);
        new OnlineBankingLamda().processCustomer(1337, (Customer c) -> System.out.println("Hello "+c));
    }

    private void lamdaStrategy(){
        Validator numericValidator = new Validator(new IsNumeric());
        System.out.println(numericValidator.validate("1111"));
        Validator numericValidator2 = new Validator((String s)->s.matches("\\d+"));
        System.out.println(numericValidator2.validate("1111"));

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        System.out.println(lowerCaseValidator.validate("bbbb"));
        Validator lowerCaseValidator2 = new Validator((String s)->s.matches("[a-z]+"));
        System.out.println(lowerCaseValidator2.validate("bbbb"));
    }

    private void conditionalDeferredExecution(){
        Logger logger = Logger.getGlobal();

        //객체 상태를 외부에서 조회, 조건 부합하면 연산수행
        if(logger.isLoggable(Level.FINER)){
            logger.finer("Problem: " + generateDiagnostic());
        }

        //객체 상태를 내부에서 조회, 조건 관계없이 연산수행
        logger.log(Level.FINER, "Problem: " + generateDiagnostic());

        //객체 상태를 내부에서 조회, 조건 부합하면 연산수행
        logger.log(Level.FINER, ()->"Problem: " + generateDiagnostic());

    }

    private String generateDiagnostic() {
        return "UNKNOWN";
    }


}
