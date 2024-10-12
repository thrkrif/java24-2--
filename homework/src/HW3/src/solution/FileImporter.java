package HW3.src.solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileImporter<T> {
    // T = List<FoodData>, List<DailyFoodData>, List<DailyHealthData>
    private ParserStrategy<T> strategy;


    public FileImporter(ParserStrategy<T> strategy){
        this.strategy = strategy;
    }


    // CSV 파일을 읽고, 읽은 데이터를 ParserStrategy에 넘겨서 각 줄을 파싱한 후, 결과를 리스트에 담아 반환한다.
    // 파일을 한 줄씩 읽는다.
    // 각 줄을 ParserStrategy<T>를 이용하여 파싱한다.
    // 파싱된 데이터를 resultList에 저장한다.
    // 파싱이 완료되면 resultList를 반환한다.

    // BufferedReader로 파일을 한 줄씩 읽는다.
    // 첫 줄이 헤더인 경우 br.readLine()으로 이를 건너뛴다.
    // 각 줄을 strategy.parse() 메서드로 파싱하여 리스트에 추가한다.
    // 파일의 모든 줄을 읽은 후 리스트를 반환한다.
    public List<T> loadCSV(String fileName) {
        System.out.println(fileName + " 로딩중.."); // 어떤 파일을 읽어들이는지 확인한다.
        List<T> resultList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // 첫 줄에 헤더가 있을 수 있으므로 이를 건너뛴다.
            br.readLine(); // 헤더를 건너뛴다.

            while ((line = br.readLine()) != null) {
                // 각 줄을 파싱하여 리스트에 추가
                T parsedData = strategy.parse(line);
                resultList.add(parsedData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    
}
