package HW3.src.solution;
// 권장 섭취량
public class DailyHealthData {
    private String date;
    private double bloodSugarLevel;
    private double insulinDose;
    private double carbsIntake;

    public DailyHealthData(String date, double bloodSugarLevel, double insulinDose, double carbsIntake) {
        this.date = date;
        this.bloodSugarLevel = bloodSugarLevel;
        this.insulinDose = insulinDose;
        this.carbsIntake = carbsIntake;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBloodSugarLevel() {
        return this.bloodSugarLevel;
    }

    public void setBloodSugarLevel(double bloodSugarLevel) {
        this.bloodSugarLevel = bloodSugarLevel;
    }

    public double getInsulinDose() {
        return this.insulinDose;
    }

    public void setInsulinDose(double insulinDose) {
        this.insulinDose = insulinDose;
    }

    public double getCarbsIntake() {
        return this.carbsIntake;
    }

    public void setCarbsIntake(double carbsIntake) {
        this.carbsIntake = carbsIntake;
    }

    @Override
    public String toString() {
        return "{" +
                " date='" + date + "'" +
                ", bloodSugarLevel='" + bloodSugarLevel + "'" +
                ", insulinDose='" + insulinDose + "'" +
                ", carbsIntake='" + carbsIntake + "'" +
                "}";
    }
}
