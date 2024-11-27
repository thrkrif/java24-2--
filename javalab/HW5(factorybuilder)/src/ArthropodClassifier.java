// 자바프로그래밍2 2분반 32207522 양상훈

import java.util.Objects;

public class ArthropodClassifier {
    private final ArthropodType type;
    private final Arthropod arthropod;

    public ArthropodClassifier(ArthropodType type, Arthropod arthropod) {
        this.type = type;
        this.arthropod = arthropod;
    }

    public ArthropodType getArthropodType() {
        return type;
    }

    public Arthropod getArthropod() {
        return arthropod;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Arthropod: %s", type, arthropod);
    }

    // Arthropod 객체와 일치 여부를 판단하는 메서드
    public boolean matches(Arthropod otherArthropod) {
        // 모든 필드를 개별적으로 비교
        return this.arthropod.getBodyRegions() == otherArthropod.getBodyRegions() &&
               this.arthropod.getPairsOfAntennae() == otherArthropod.getPairsOfAntennae() &&
               this.arthropod.getPairsOfWing() == otherArthropod.getPairsOfWing() &&
               this.arthropod.getNumberOfLegs() == otherArthropod.getNumberOfLegs() &&
               Objects.equals(this.arthropod.getRespiration(), otherArthropod.getRespiration()) &&
               Objects.equals(this.arthropod.getMetamorphosis(), otherArthropod.getMetamorphosis()) &&
               Objects.equals(this.arthropod.getDistinction(), otherArthropod.getDistinction());
    }

    // 주어진 속성과 일치 여부를 판단하는 메서드
    public boolean matches(int bodyRegions, int pairsOfAntennae, RespirationType respiration,
                           MetamorphosisType metamorphosis, int pairsOfWing,
                           int numberOfLegs, String distinction) {
        // Arthropod의 필드들과 비교
        return this.arthropod.getBodyRegions() == bodyRegions &&
               this.arthropod.getPairsOfAntennae() == pairsOfAntennae &&
               Objects.equals(this.arthropod.getRespiration(), respiration) &&
               Objects.equals(this.arthropod.getMetamorphosis(), metamorphosis) &&
               this.arthropod.getPairsOfWing() == pairsOfWing &&
               this.arthropod.getNumberOfLegs() == numberOfLegs &&
               Objects.equals(this.arthropod.getDistinction(), distinction);
    }
}
