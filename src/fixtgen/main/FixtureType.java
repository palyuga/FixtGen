package fixtgen.main;

/** Represents all used fixture types */
public enum FixtureType {
    
    DO("DoFixture"),
    
    ROW("RowFixture"),
    
    COLUMN("ColumnFixture");
    
    private String name;
    
    private FixtureType(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
