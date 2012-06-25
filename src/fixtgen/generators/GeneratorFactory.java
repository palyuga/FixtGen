package fixtgen.generators;

import fixtgen.main.FixtureType;

/**
 * Factory produces fixture generators
 * @author dpalyuga
 * @version 1.0
 */
public class GeneratorFactory {

    /**
     * Returns an appropriate generator for specified type of the fixture
     * @param fixtureType
     * @return an appropriate generator for specified type of the fixture
     */
    public static AbstractGenerator getGenerator(FixtureType fixtureType) {
        
        AbstractGenerator result = null;
        
        if (fixtureType == FixtureType.COLUMN) {
            result = new ColumnFixtureGenerator();
        } else if (fixtureType == FixtureType.DO) {
            result = new DoFixtureGenerator();
        } else if (fixtureType == FixtureType.ROW) {
            result = new RowFixtureGenerator();
        }
        
        return result;
    }
}
