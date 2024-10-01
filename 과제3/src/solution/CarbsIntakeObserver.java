package solution;

public class CarbsIntakeObserver implements Observer{
    private String name;
    private double carbsIntakeThreshold;
    

    public CarbsIntakeObserver(String name, double carbsIntakeThreshold){
        this.name = name;
        this.carbsIntakeThreshold = carbsIntakeThreshold;
    }
    @Override
    public void update(DailyHealthData d) {
        // FileImporter에 의해 DailyHealthData가 갱신, 멤버인 carbs를 carbsIntakeThreshold와 비교
        if (d.getCarbsIntake() > carbsIntakeThreshold){
            System.out.println(name + " : high carbs intake level"); // 탄수화물 섭취량이 많음
        }
    }
    
}
