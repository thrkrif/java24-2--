package HW3.src.template;

public class MainTest {

    public MainTest() {
        // Load fooddata
		//List<FoodData> foodDataList = new FileImporter<>(new FoodDataParseStrategy()).loadCSV("fooddata.csv");

        // Load dailyfooddata
		//List<DailyFoodData> dailyFoodDataList = new FileImporter<>(new DailyFoodDataParseStrategy(foodDataList)).loadCSV("dailyfooddata.csv");

        // Load dailyhealthdata
		//List<DailyHealthData> dailyHealthDataList = new FileImporter<>(new DailyHealthDataParseStrategy()).loadCSV("dailyhealthdata.csv");

        // Subject
        
        // Observer
        
        // Add observers
        
        // Simulate health data updates with food integration
        new Thread(() -> {
            // for (DailyHealthData dailyHealthData : dailyHealthDataList) {
                try {
                    Thread.sleep(1000); // Wait for 1 second before the next update
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\n\n\n");
            // }
        }).start();

    }
}
