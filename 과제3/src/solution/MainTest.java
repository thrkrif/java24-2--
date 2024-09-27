package solution;

import java.util.List;

public class MainTest {

    public MainTest() {
        // Load fooddata
		List<FoodData> foodDataList = new FileImporter<>(new FoodDataParseStrategy()).loadCSV("fooddata.csv");

        // Load dailyfooddata
		List<DailyFoodData> dailyFoodDataList = new FileImporter<>(new DailyFoodDataParseStrategy(foodDataList)).loadCSV("dailyfooddata.csv");

        // Load dailyhealthdata
		List<DailyHealthData> dailyHealthDataList = new FileImporter<>(new DailyHealthDataParseStrategy()).loadCSV("dailyhealthdata.csv");

        // Subject
        DiabetesManager manager = new DiabetesManager();
        // Observer
        
        // Add observers
        BloodSugarObserver o1 = new BloodSugarObserver(150);
        CarbsIntakeObserver o2 = new CarbsIntakeObserver(100);
        InsulinObserver o3 = new InsulinObserver(10);
        manager.addObserver(o1);
        manager.addObserver(o2);
        manager.addObserver(o3);

        // Simulate health data updates with food integration
        new Thread(() -> {// Simulate food affecting carbs & insulin
            for (DailyHealthData h : dailyHealthDataList) {
            DailyFoodData f = dailyFoodDataList.stream().filter(e ->
            e.getDate().equals(h.getDate())).findAny().orElse(null);
            double totalCarbs = f.getFoods().stream().mapToDouble(e ->
            e.getCarbs()).sum();
            totalCarbs += h.getCarbsIntake();
            h.setCarbsIntake(totalCarbs);
            double insulinDose = (h.getInsulinDose() + totalCarbs) / 10.0;
            h.setInsulinDose(insulinDose);
            manager.addDailyHealthData(h);
            try {
            Thread.sleep(1000); // Wait for 1 second before next update
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            System.out.println("\n\n\n");
            }
            }).start();
            

    }
}
