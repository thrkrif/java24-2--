import java.util.Objects;

public class Arthropod {
    
    private int bodyRegions;
    private int pairsOfAntennae;
    private RespirationType respiration;
    private MetamorphosisType metamorphosis;
    private int pairsOfWing;
    private int numberOfLegs;
    private String distinction;

    public Arthropod(ArthropodBuilder builder) {
        this.bodyRegions = builder.bodyRegions;
        this.pairsOfAntennae = builder.pairsOfAntennae;
        this.respiration = builder.respiration;
        this.metamorphosis = builder.metamorphosis;
        this.pairsOfWing = builder.pairsOfWing;
        this.numberOfLegs = builder.numberOfLegs;
        this.distinction = builder.distinction;
    }


    public int getBodyRegions() {
        return this.bodyRegions;
    }


    public int getPairsOfAntennae() {
        return this.pairsOfAntennae;
    }

    public RespirationType getRespiration() {
        return this.respiration;
    }

    public MetamorphosisType getMetamorphosis() {
        return this.metamorphosis;
    }

    public int getPairsOfWing() {
        return this.pairsOfWing;
    }

    public int getNumberOfLegs() {
        return this.numberOfLegs;
    }

    public String getDistinction() {
        return this.distinction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 참조 비교: 같은 객체이면 true
        if (o == null || getClass() != o.getClass()) return false; // 타입 비교: null이거나 다른 클래스 타입이면 false
        Arthropod arthropod = (Arthropod) o; // 형변환
        return bodyRegions == arthropod.bodyRegions &&
               pairsOfAntennae == arthropod.pairsOfAntennae &&
               pairsOfWing == arthropod.pairsOfWing &&
               numberOfLegs == arthropod.numberOfLegs &&
               Objects.equals(respiration, arthropod.respiration) &&
               Objects.equals(metamorphosis, arthropod.metamorphosis) &&
               Objects.equals(distinction, arthropod.distinction); // 객체 비교
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyRegions, pairsOfAntennae, respiration, metamorphosis, pairsOfWing, numberOfLegs, distinction);
    }

    @Override
    public String toString() {
        return "{" +
            " bodyRegions='" + getBodyRegions() + "'" +
            ", pairsOfAntennae='" + getPairsOfAntennae() + "'" +
            ", respiration='" + getRespiration() + "'" +
            ", metamorphosis='" + getMetamorphosis() + "'" +
            ", pairsOfWing='" + getPairsOfWing() + "'" +
            ", numberOfLegs='" + getNumberOfLegs() + "'" +
            ", distinction='" + getDistinction() + "'" +
            "}";
    }


    public static class ArthropodBuilder{
        private int bodyRegions;
        private int pairsOfAntennae;
        private RespirationType respiration;
        private MetamorphosisType metamorphosis;
        private int pairsOfWing;
        private int numberOfLegs;
        private String distinction;

        public ArthropodBuilder setBodyRegions(int bodyRegions){
            this.bodyRegions = bodyRegions;
            return this;
        }

        public ArthropodBuilder setPairsOfAntennae(int pairsOfAntennae){
            this.pairsOfAntennae = pairsOfAntennae;
            return this;
        }

        public ArthropodBuilder setRespirationType(RespirationType respiration){
            this.respiration = respiration;
            return this;
        }

        public ArthropodBuilder setMetamorphosisType(MetamorphosisType metamorphosis){
            this.metamorphosis = metamorphosis;
            return this;
        }

        public ArthropodBuilder setPairsOfWing(int pairsOfWing){
            this.pairsOfWing = pairsOfWing;
            return this;
        }

        public ArthropodBuilder setNumberOfLegs(int numberOfLegs){
            this.numberOfLegs = numberOfLegs;
            return this;
        }

        public ArthropodBuilder setDistinction(String distinction){
            this.distinction = distinction;
            return this;
        }

        public Arthropod build(){
            return new Arthropod(this);
        }
    }
}
