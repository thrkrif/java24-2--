package HW3.src.solution;
import java.util.List;

public class CaloriesObserver implements Observer{
    private String name;
    private double calorieThreshold;
    private List<DailyFoodData> dailyFoodDataList;
    
    public CaloriesObserver(String name, double calorieThreshold, List<DailyFoodData> dailyFoodDataList){
        this.name = name;
        this.calorieThreshold = calorieThreshold;
        this.dailyFoodDataList = dailyFoodDataList;
    }

    @Override
    public void update(DailyHealthData d) {
        // DailyHealthData의 날짜에 해당하는 DailyFoodData를 찾음
        DailyFoodData foodData = dailyFoodDataList.stream().filter(f -> f.getDate().equals(d.getDate())).findFirst().orElse(null);

        if (foodData != null) {
        // DailyFoodData를 통해 총 칼로리 계산
        double totalCalories = foodData.getFoods().stream().mapToDouble(e -> e.getCalories()).sum();

            // 총 칼로리가 임계값을 초과하면 알림
            if (totalCalories > calorieThreshold) {
                System.out.println(name + " : high calorie level");
            }

        } 
    }
}