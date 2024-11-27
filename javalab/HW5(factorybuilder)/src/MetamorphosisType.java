// 자바프로그래밍2 2분반 32207522 양상훈

public enum MetamorphosisType {
    COMPLETE("COMPLETE"),
    INCOMPLETE("INCOMPLETE"),
    NONE("NONE"),
    VARIABLE("VARIABLE"),
    UNKNOWN("unknown");

    private final String name;

    MetamorphosisType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
