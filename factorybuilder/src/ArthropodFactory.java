public class ArthropodFactory {
    // Factory : 객체를 생성하는 부분, Builder를 이용하여 객체 생성

    // ArthropodType으로 Arthropod 생성
    public static Arthropod create(ArthropodType type) {
        if (type == ArthropodType.ARACHNIDA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(2)
                    .setPairsOfAntennae(0)
                    .setRespirationType(RespirationType.BOOK_LUNGS)
                    .setMetamorphosisType(MetamorphosisType.NONE)
                    .setPairsOfWing(0)
                    .setNumberOfLegs(8)
                    .setDistinction("")
                    .build();
        } else if (type == ArthropodType.CHILOPODA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(2)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.NONE)
                    .setPairsOfWing(0)
                    .setNumberOfLegs(30)
                    .setDistinction("1 pair of legs per segment")
                    .build();
        } else if (type == ArthropodType.DIPLOPODA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(2)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.NONE)
                    .setPairsOfWing(0)
                    .setNumberOfLegs(100)
                    .setDistinction("2 pairs of legs per segment")
                    .build();
        } else if (type == ArthropodType.CRUSTACEA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(2)
                    .setPairsOfAntennae(2)
                    .setRespirationType(RespirationType.GILLS)
                    .setMetamorphosisType(MetamorphosisType.VARIABLE)
                    .setPairsOfWing(0)
                    .setNumberOfLegs(10)
                    .setDistinction("")
                    .build();
        } else if (type == ArthropodType.ODONATA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(3)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.INCOMPLETE)
                    .setPairsOfWing(2)
                    .setNumberOfLegs(6)
                    .setDistinction("wings membranous")
                    .build();
        } else if (type == ArthropodType.ORTHOPTERA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(3)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.INCOMPLETE)
                    .setPairsOfWing(2)
                    .setNumberOfLegs(6)
                    .setDistinction("hind legs enlarged")
                    .build();
        } else if (type == ArthropodType.DIPTERA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(3)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.COMPLETE)
                    .setPairsOfWing(1)
                    .setNumberOfLegs(6)
                    .setDistinction("")
                    .build();
        } else if (type == ArthropodType.COLEOPTERA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(3)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.COMPLETE)
                    .setPairsOfWing(2)
                    .setNumberOfLegs(6)
                    .setDistinction("hard exoskeleton and elytra (wing covers)")
                    .build();
        } else if (type == ArthropodType.LEPIDOPTERA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(3)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.COMPLETE)
                    .setPairsOfWing(2)
                    .setNumberOfLegs(6)
                    .setDistinction("wings scaly")
                    .build();
        } else if (type == ArthropodType.HYMENOPTERA) {
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(3)
                    .setPairsOfAntennae(1)
                    .setRespirationType(RespirationType.TRACHEAL)
                    .setMetamorphosisType(MetamorphosisType.COMPLETE)
                    .setPairsOfWing(2)
                    .setNumberOfLegs(6)
                    .setDistinction("wings membranous")
                    .build();
        } else {
            // UNKNOWN or any other case
            return new Arthropod.ArthropodBuilder()
                    .setBodyRegions(0)
                    .setPairsOfAntennae(0)
                    .setRespirationType(RespirationType.UNKNOWN)
                    .setMetamorphosisType(MetamorphosisType.UNKNOWN)
                    .setPairsOfWing(0)
                    .setNumberOfLegs(0)
                    .setDistinction("Unknown")
                    .build();
        }
    }

    // 세부 속성으로 Arthropod 생성
    public static Arthropod create(int bodyRegions, int pairsOfAntennae, RespirationType respiration,
                                   MetamorphosisType metamorphosis, int pairsOfWing, int numberOfLegs,
                                   String distinction) {
        return new Arthropod.ArthropodBuilder()
                .setBodyRegions(bodyRegions)
                .setPairsOfAntennae(pairsOfAntennae)
                .setRespirationType(respiration)
                .setMetamorphosisType(metamorphosis)
                .setPairsOfWing(pairsOfWing)
                .setNumberOfLegs(numberOfLegs)
                .setDistinction(distinction)
                .build();
    }
}

