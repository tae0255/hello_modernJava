package CHAPTER09.Patterns.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
