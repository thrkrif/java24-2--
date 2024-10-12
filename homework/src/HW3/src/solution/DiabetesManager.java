package HW3.src.solution;

import java.util.ArrayList;
import java.util.List;

// 식단 확인

public class DiabetesManager implements Subject{
    private List<Observer> observers;
    private DailyHealthData d;


    public DiabetesManager(){
        this.observers = new ArrayList<>(); 
    }
    // DailyHealthData 업데이트
    public void addDailyHealthData(DailyHealthData d){
        this.d = d; // 새로 업데이트 된 데이터를 저장한다.
        notifyObservers();  // 옵저버들에게 알린다.
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
            // 모든 구독자들이 그 날의 DailyHealthData를 확인.
            o.update(d);
        }
    }

    
    
}
