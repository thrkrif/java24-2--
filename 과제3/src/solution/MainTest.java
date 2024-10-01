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
        BloodSugarObserver o1 = new BloodSugarObserver("James",150);
        CarbsIntakeObserver o2 = new CarbsIntakeObserver("Tomas" ,100);
        InsulinObserver o3 = new InsulinObserver("Alice",10);
        CaloriesObserver o4 = new CaloriesObserver("Choi", 300, dailyFoodDataList);
        manager.addObserver(o1);
        manager.addObserver(o2);
        manager.addObserver(o3);
        manager.addObserver(o4);
            
        new Thread(() -> {
            for (DailyHealthData h : dailyHealthDataList) {
                // 해당 날짜의 DailyFoodData 찾기
                DailyFoodData f = dailyFoodDataList.stream().filter(e -> e.getDate().equals(h.getDate())).findAny().orElse(null);

                // 음식 데이터를 통해 탄수화물 총합 구하기
                // 당일 섭취한 음식들의 fooddata에 있는 Carbohydrates + dailyhealthdata의 CarbsIntake
                double totalCarbs = f.getFoods().stream().mapToDouble(e -> e.getCarbs()).sum();
                totalCarbs += h.getCarbsIntake(); // 기존 탄수화물 섭취량과 음식 섭취량 더하기
                h.setCarbsIntake(totalCarbs); // 갱신된 탄수화물 섭취량 설정

                // 인슐린 용량 계산
                double insulinDose = (h.getInsulinDose() + totalCarbs) / 10.0;
                h.setInsulinDose(insulinDose);

                // 칼로리 총합 구하기
                double totalCalories = f.getFoods().stream().mapToDouble(e -> e.getCalories()).sum();

                // 갱신된 건강 데이터 출력
                System.out.println("날짜: " + h.getDate());
                System.out.println("섭취 음식: " + f.getFoods());
                System.out.println("총 탄수화물 섭취량: " + h.getCarbsIntake());
                System.out.println("인슐린 용량: " + h.getInsulinDose());
                System.out.println("총 섭취 칼로리: " + totalCalories);

                // DiabetesManager에 갱신된 DailyHealthData 추가 -> 옵저버들에게 알림
                manager.addDailyHealthData(h);
                
                // 옵저버 추가 삭제 루틴을 포함
                // 5일차에 옵저버를 추가/삭제하는 예시
                if (h.getDate().equals("2024-09-05")) {
                    System.out.println("== 2024-09-05 마무리 : InsulinObserver 삭제 및 CarbsIntakeObserver 옵저버 추가 ==");
                    manager.removeObserver(o3);  // 인슐린 옵저버 삭제
                    CarbsIntakeObserver o5 = new CarbsIntakeObserver("Sarah", 80);
                    manager.addObserver(o5);    // 탄수화물 옵저버 추가
                }

                try {
                    Thread.sleep(1000); // 1초 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\n"); // 줄 바꿈으로 구분
            }
        }).start();

     
    }
}
