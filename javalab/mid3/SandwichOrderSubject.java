package mid3;
import java.util.ArrayList;
import java.util.List;


public class SandwichOrderSubject implements Subject{
    private List<Observer> observers; 

    public SandwichOrderSubject(){
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o){
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String orderDetails){
        for (Observer o : observers){
            o.update(orderDetails);
        }
    }
}
