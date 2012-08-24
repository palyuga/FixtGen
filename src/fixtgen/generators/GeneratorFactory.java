package fixtgen.generators;

import fixtgen.main.FixtureType;
import fixtgen.preferences.EclipsePreferenceManager;

/**
 * Factory produces fixture generators
 * @author dpalyuga
 * @version 1.0
 */
public class GeneratorFactory {

    /**
     * Returns an appropriate generator for specified fixture type.
     * @param fixtureType type of the fixture
     * @return an appropriate generator for specified fixture type
     */
    public static AbstractGenerator getGenerator(FixtureType fixtureType) {
        
        AbstractGenerator result = null;
        
        switch (fixtureType) {
            
            case COLUMN:
                result = ColumnFixtureGenerator.createNew(EclipsePreferenceManager.getInstance());
                break;
            
            case DO:
                result = DoFixtureGenerator.createNew(EclipsePreferenceManager.getInstance());
                break;
            
            case ROW:
                result = RowFixtureGenerator.createNew(EclipsePreferenceManager.getInstance());
                break;
            
            case SEQUENCE:
                result = SequenceFixtureGenerator.createNew(EclipsePreferenceManager.getInstance());
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported fixture type");
        }
        
        return result;
    }
}
