package solution;

import java.util.ArrayList;
import java.util.List;

// 식단 짜주는 사람
// 구현 완료
public class DiabetesManager implements Subject{
    private List<Observer> observers;
    private DailyHealthData d;


    public DiabetesManager(){
        this.observers = new ArrayList<>(); 
    }

    public void addDailyHealthData(DailyHealthData d){
        this.d = d;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // notifyObservers() updates DailyHealthData to all subscribed observers.
    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            // 모든 구독자들이 매일마다 바뀐 data를 확인하고 업데이트함.
            o.update(d);
        }
    }

    
    
}
