package fixtgen.main;

/** Represents all used fixture types */
public enum FixtureType {
    
    DO("DoFixture"),
    
    ROW("RowFixture"),
    
    COLUMN("ColumnFixture"),
    
    SEQUENCE("SequenceFixture");
    
    private static final String PARENT_CLASS_PREF_POSTFIX = "ParentClass";
    
    private static final String IMPORT_CLASSES_PREF_POSTFIX = "ImportClasses";
    
    private final String name;
    
    private FixtureType(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getParentClassPrefKey() {
        return getName() + PARENT_CLASS_PREF_POSTFIX;
    }
    
    public String getImportClassesPrefKey() {
        return getName() + IMPORT_CLASSES_PREF_POSTFIX;
    }
}
