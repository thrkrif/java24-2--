package HW3.src.solution;

public class BloodSugarObserver implements Observer{
    private String name;
    private double bloodSugarThreshold;
    
    public BloodSugarObserver(String name, double bloodSugarThreshold) {
        this.name = name;
        this.bloodSugarThreshold = bloodSugarThreshold;
    }

    @Override
    public void update(DailyHealthData d){
    
    // 음식의 당수치가 제한 당수치보다 높으면 대안 또는 혈중 당 수치 높다고 출력하라
        if (d.getBloodSugarLevel() > bloodSugarThreshold){
            System.out.println(name + " : high blood sugar level");
        }
    }
}
