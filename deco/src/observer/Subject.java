package observer;

public interface Subject {
    // add, remove, notify

    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
