// 자바프로그래밍2 2분반 32207522 양상훈

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
    // 사용자가 필드의 값을 설정하지 않을 때, 기본값으로 한다.
        private int bodyRegions = -1;
        private int pairsOfAntennae = -1;
        private RespirationType respiration = RespirationType.UNKNOWN;
        private MetamorphosisType metamorphosis = MetamorphosisType.UNKNOWN;
        private int pairsOfWing = -1;
        private int numberOfLegs = -1;
        private String distinction = "unknown";

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
