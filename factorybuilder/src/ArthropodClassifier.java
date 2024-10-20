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
        if (type == ArthropodType.UNKNOWN) {
            return otherArthropod.getBodyRegions() == 0 &&
                   otherArthropod.getPairsOfAntennae() == 0 &&
                   otherArthropod.getPairsOfWing() == 0 &&
                   otherArthropod.getNumberOfLegs() == 0 &&
                   otherArthropod.getRespiration() == null &&
                   otherArthropod.getMetamorphosis() == null &&
                   otherArthropod.getDistinction() == null; // 모든 필드가 'Unknown'이면 true
        }
        return this.arthropod.equals(otherArthropod); // 일반적인 비교
    }

    // 주어진 속성과 일치 여부를 판단하는 메서드
    public boolean matches(int bodyRegions, int pairsOfAntennae, RespirationType respiration,
                           MetamorphosisType metamorphosis, int pairsOfWing,
                           int numberOfLegs, String distinction) {
        // Arthropod와 비교
        return this.arthropod.getBodyRegions() == bodyRegions &&
               this.arthropod.getPairsOfAntennae() == pairsOfAntennae &&
               Objects.equals(this.arthropod.getRespiration(), respiration) &&
               Objects.equals(this.arthropod.getMetamorphosis(), metamorphosis) &&
               this.arthropod.getPairsOfWing() == pairsOfWing &&
               this.arthropod.getNumberOfLegs() == numberOfLegs &&
               Objects.equals(this.arthropod.getDistinction(), distinction);
    }
}
