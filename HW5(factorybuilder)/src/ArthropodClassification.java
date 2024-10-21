// 자바프로그래밍2 2분반 32207522 양상훈

import java.util.List;

public class ArthropodClassification {
    private List<ArthropodClassifier> classifiers;

    public ArthropodClassification(List<ArthropodClassifier> classifiers) {
        this.classifiers = classifiers; // 모든 곤충 분류 방법 리스트를 추가
    }

    // 첫 번째 버전: Arthropod 객체를 매개변수로 받는 메서드
    public ArthropodType classify(Arthropod arthropod) {
        // 리스트에 있는 classifier와 match하는 것이 있으면 classifier.getArthropodType() 반환
        for (ArthropodClassifier classifier : classifiers) {
            if (classifier.matches(arthropod)) {
                return classifier.getArthropodType();
            }
        }
        // 없으면, ArthropodType.UNKNOWN 반환
        return ArthropodType.UNKNOWN;
    }

    // 두 번째 버전: 개별적인 속성 값들을 매개변수로 받는 메서드
    public ArthropodType classify(int bodyRegions, int pairsOfAntennae, RespirationType respirationType,
                                  MetamorphosisType metamorphosisType, int pairsOfWing,
                                  int numberOfLegs, String distinction) {
        // 새로운 Arthropod 객체를 생성
        Arthropod arthropod = new Arthropod.ArthropodBuilder()
                .setBodyRegions(bodyRegions)
                .setPairsOfAntennae(pairsOfAntennae)
                .setRespirationType(respirationType)
                .setMetamorphosisType(metamorphosisType)
                .setPairsOfWing(pairsOfWing)
                .setNumberOfLegs(numberOfLegs)
                .setDistinction(distinction)
                .build();

        // 리스트에 있는 classifier와 match하는 것이 있으면 classifier.getArthropodType() 반환
        for (ArthropodClassifier classifier : classifiers) {
            if (classifier.matches(arthropod)) {
                return classifier.getArthropodType();
            }
        }
        // 없으면, ArthropodType.UNKNOWN 반환
        return ArthropodType.UNKNOWN;
    }
}
