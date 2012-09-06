package fixtgen.generators;

import fixtgen.main.FixtureType;
import fixtgen.main.IDataProvider;
import fixtgen.preferences.IPreferenceManager;

public class SequenceFixtureGenerator extends AbstractGenerator {
    
    /** Static factory method. */
    public static SequenceFixtureGenerator createNew(final IPreferenceManager preferenceManager) {
        return new SequenceFixtureGenerator(preferenceManager);
    }
    
    /** The private constructor */
    private SequenceFixtureGenerator(final IPreferenceManager preferenceManager) {
        super(preferenceManager);
    }

    @Override
    public String generate(IDataProvider dataProvider) {
        return "";
    }

    @Override
    protected String getParentClassKey() {
        return FixtureType.SEQUENCE.getParentClassPrefKey();
    }

    @Override
    protected String getImportClassesKey() {
        return FixtureType.SEQUENCE.getImportClassesPrefKey();
    }
}
