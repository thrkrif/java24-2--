package observer;
import java.util.List;
import java.util.ArrayList;

public class DiabetesManager implements Subject{
    private DailyHelathData d;
    private List<Observer> observers;

    public DiabetesManager(){
        this.observers = new ArrayList<>();
        // 추가, 삭제, notify를 위한 공간 만들기
    }

    public void addDailyHealthData(DailyHelathData d){
        this.d = d;
        notifyObserver();
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
    public void notifyObserver(){
        for (Observer o : observers) {
            o.update(d);
        }
    }

    

}
