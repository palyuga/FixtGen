package fixtgen.generators;

import fixtgen.main.FixtureType;

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
                result = new ColumnFixtureGenerator();
                break;
            
            case DO:
                result = new DoFixtureGenerator();
                break;
            
            case ROW:
                result = new RowFixtureGenerator();
                break;
            
            default:
                throw new IllegalArgumentException("Unsupported fixture type");
        }
        
        return result;
    }
}
