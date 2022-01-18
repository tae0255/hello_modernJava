package CHAPTER09.Patterns.Observer;

public class NYTimes implements Observer{
    @Override
    public void notify(String tweet) {
        if(tweet!=null && tweet.contains("money")){
            System.out.println("Breaking news in NY! "+tweet);
        }
    }
}
