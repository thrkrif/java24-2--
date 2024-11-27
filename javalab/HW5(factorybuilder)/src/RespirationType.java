// 자바프로그래밍2 2분반 32207522 양상훈

public enum RespirationType {
    TRACHEAL("TRACHEAL"),
    BOOK_LUNGS("BOOK_LUNGS"),
    GILLS("GILLS"),
    UNKNOWN("unknown");

    private final String name;

    RespirationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
