package solution;

public class FoodDataParseStrategy implements ParserStrategy<FoodData>{

    // CSV 파일의 한 줄(line)을 받아서 ,를 기준으로 구분하고 구분된 값들을 FoodData의 정보 갱신
    @Override
    public FoodData parse(String line) {
        String[] values = line.split(",");
        String name = values[0];
        double carbs = Double.parseDouble(values[1]);
        double calories = Double.parseDouble(values[2]);
        
        // FoodData의 정보를 갱신
        return new FoodData(name,carbs,calories);
    }

}
