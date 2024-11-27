import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<ArthropodClassifier> classifiers;
        try {
            classifiers = ArthropodImporter.loadCSV("arthropods.csv");
        } catch (IOException e) {
            System.err.println("Error loading classifiers from CSV: " + e.getMessage());
            return; // 오류 발생 시 프로그램 종료
        }
        
        // ArthropodClassification 객체 생성
        ArthropodClassification classification = new ArthropodClassification(classifiers);

        // Beetle 객체 생성 using Builder
        Arthropod beetle = new Arthropod.ArthropodBuilder()
                .setBodyRegions(3)
                .setPairsOfAntennae(1)
                .setRespirationType(RespirationType.TRACHEAL) // 예시 값
                .setMetamorphosisType(MetamorphosisType.COMPLETE) // 예시 값
                .setPairsOfWing(2)
                .setNumberOfLegs(6)
                .setDistinction("Beetle")
                .build();

        // Beetle 정보 출력 및 분류 결과 출력
        System.out.println(beetle);
        System.out.println(" => Arthropod Type: " + classification.classify(beetle));

        // Crab 객체 생성 using Factory
        Arthropod crab = ArthropodFactory.create(ArthropodType.CRUSTACEA);
        System.out.println(crab);
        System.out.println(" => Arthropod Type: " + classification.classify(crab));

        // 어떤 Arthropod 객체 생성
        Arthropod arthropodToClassify = new Arthropod.ArthropodBuilder()
                .setBodyRegions(2)
                .setPairsOfAntennae(0)
                .setRespirationType(RespirationType.GILLS) // 예시 값
                .setMetamorphosisType(MetamorphosisType.INCOMPLETE) // 예시 값
                .setPairsOfWing(0)
                .setNumberOfLegs(10)
                .setDistinction("Unknown")
                .build();
        System.out.println(arthropodToClassify);
        System.out.println(" => Arthropod Type: " + classification.classify(arthropodToClassify));

        // Lambda를 사용하여 Arthropod 분류
        Optional<ArthropodClassifier> matchingClassifier = classifiers.stream()
                .filter(classifier -> classifier.matches(arthropodToClassify))
                .findFirst();

        matchingClassifier.ifPresent(classifier -> 
            System.out.println(arthropodToClassify + " => Arthropod Type: " + classifier.getArthropodType())
        );

        // classifiers에서 Arthropod 리스트 가져오기
        List<Arthropod> arthropods = classifiers.stream()
                .map(ArthropodClassifier::getArthropod)
                .collect(Collectors.toList());

        // 각 Arthropod를 분류하여 결과 출력
        for (Arthropod arthropod : arthropods) {
            System.out.print(arthropod);
            System.out.println(" => Arthropod Type: " + classification.classify(arthropod));
        }
    }
}
