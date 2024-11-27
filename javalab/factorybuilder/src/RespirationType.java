public enum RespirationType {
    TRACHEAL("TRACHEAL"),
    BOOK_LUNGS("BOOK_LUNGS"),
    GILLS("GILLS"),
    UNKNOWN("Unknown");

    private final String name;

    RespirationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
