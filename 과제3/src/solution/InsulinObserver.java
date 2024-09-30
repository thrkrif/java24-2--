package solution;

public class InsulinObserver implements Observer{
    private String name;
    private double insulinThreshold;
    
    public InsulinObserver(String name, double insulinThreshold){
        this.name = name;
        this.insulinThreshold = insulinThreshold;
    }

    @Override
    public void update(DailyHealthData d) {
        if (d.getInsulinDose() > insulinThreshold){
            System.out.println(name + " : high insulin dose level"); // 인슐린 복용량이 높음
        }
    }


}
