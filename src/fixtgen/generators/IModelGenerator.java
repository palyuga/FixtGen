package fixtgen.generators;

import fixtgen.main.IDataProvider;

/**
 * Interface for generators which can generate a model class.
 * @author dpalyuga
 * @version 1.0
 */
public interface IModelGenerator {
    
    /**
     * Method should return source of the model class
     * @param dataProvider provides content of fitnesse-table
     * @return source of the model class
     */
    public String generateModel(final IDataProvider dataProvider);
}
