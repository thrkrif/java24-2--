public enum ArthropodType {
    ARACHNIDA("Arachnida"),
    CHILOPODA("Chilopoda"),
    DIPLOPODA("Diplopoda"),
    CRUSTACEA("Crustacea"),
    ODONATA("Odonata"),
    ORTHOPTERA("Orthoptera"),
    DIPTERA("Diptera"),
    COLEOPTERA("Coleoptera"),
    LEPIDOPTERA("Lepidoptera"),
    HYMENOPTERA("Hymenoptera"),
    UNKNOWN("Unknown");

    private final String name;

    ArthropodType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
