package solution;

public class InsulinObserver implements Observer{
    private double insulinThreshold;

    public InsulinObserver(double insulinThreshold){
        this.insulinThreshold = insulinThreshold;
    }

    @Override
    public void update(DailyHealthData d) {
        if (d.getInsulinDose() > insulinThreshold){
            System.out.println("high insulin dose level"); // 인슐린 복용량이 높음
        }
    }


}
