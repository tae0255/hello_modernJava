package CHAPTER09.Patterns.Observer;

public class LeMonde implements Observer{
    @Override
    public void notify(String tweet) {
        if(tweet!=null && tweet.contains("wine")){
            System.out.println("Today chees, wine and news! "+tweet);
        }
    }
}
