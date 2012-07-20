package fixtgen.generators;

import fixtgen.main.IDataProvider;
import fixtgen.testservice.MockPreferenceManager;
import junit.framework.TestCase;

public class RowFixtureGeneratorTest extends TestCase {
    
	private static final RowFixtureGenerator generator =
			RowFixtureGenerator.createNew(new MockPreferenceManager());
	
	public void testGenerate() {
        System.out.println(generator.generate(new RowFixtureMockDataProvider()));
    }
    
    public void testGenerateModel() {
        System.out.println(generator.generateModel(new RowFixtureMockDataProvider()));
    }
    
    static class RowFixtureMockDataProvider implements IDataProvider {

        private static int lineNumber = 0;
        
        @Override
        public String readLine() {
            
            final String[] lines = {
                    
                    "|!-com.boeing.eid.odt.fitnesse.db.SelectDtOdtRequestTable-!|requestId1   |",
                    "|part id      |state    |type            |",
                    "|${PART_ID1}  |SUBMITTED|${REQUEST_TYPE1}|" 
            };
            
            if (lineNumber < lines.length) {
                return lines[lineNumber++];
            } else {
                return null;
            }
        }

        @Override
        public void reset() {
            lineNumber = 0;            
        }
    }
}
