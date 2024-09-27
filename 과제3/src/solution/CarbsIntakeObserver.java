package solution;

public class CarbsIntakeObserver implements Observer{
    private double carbsIntakeThreshold;

    public CarbsIntakeObserver(double carbsIntakeThreshold){
        this.carbsIntakeThreshold = carbsIntakeThreshold;
    }
    @Override
    public void update(DailyHealthData d) {
        if (d.getCarbsIntake() > carbsIntakeThreshold){
            System.out.println("high carbs intake level"); // 탄수화물 섭취량이 많음
        }
    }
    
}
