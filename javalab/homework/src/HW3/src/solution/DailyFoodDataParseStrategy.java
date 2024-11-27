package HW3.src.solution;

import java.util.ArrayList;
import java.util.List;

public class DailyFoodDataParseStrategy implements ParserStrategy<DailyFoodData>{    

    private List<FoodData> foodDataList;

    // Constructor that takes the existing list of food data
    public DailyFoodDataParseStrategy(List<FoodData> foodDataList) {
        this.foodDataList = foodDataList;
    }


    @Override
    
    public DailyFoodData parse(String line) {
       // line을 쉼표로 구분하여 데이터 추출
        String[] parts = line.split(",", 2); // 첫 번째 쉼표를 기준으로 날짜와 나머지 분리
        String date = parts[0]; // 첫 번째는 날짜
        String foodString = parts[1].replace("\"", ""); // 두 번째는 음식 목록. 따옴표 제거

        // 음식 목록을 쉼표로 나누어 각 음식을 리스트로 저장
        String[] foodNames = foodString.split(",");
        // 먹은 음식들을 저장
        List<FoodData> foods = new ArrayList<>();

        // foodNames를 순회하며, 해당 음식의 데이터를 foodDataList에서 찾아 foods에 저장한다.
        for (String foodName : foodNames) {
            FoodData matchingFood = foodDataList.stream().filter(f -> f.getName().equalsIgnoreCase(foodName.trim())).findFirst().orElse(new FoodData(foodName.trim(), 0.0, 0.0)); // 찾지 못하면 기본 값으로 추가
            foods.add(matchingFood);
        }

        // DailyFoodData 정보를 갱신
        return new DailyFoodData(date, foods);
    }

}
