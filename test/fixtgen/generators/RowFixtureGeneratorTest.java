package fixtgen.generators;

import fixtgen.main.IDataProvider;
import junit.framework.TestCase;

public class RowFixtureGeneratorTest extends TestCase {
    public void testGenerate() {
        RowFixtureGenerator generator = new RowFixtureGenerator();
        System.out.println(generator.generate(new FakeRowFixtureDataProvider()));
    }
    
    public void testGenerateModel() {
        RowFixtureGenerator generator = new RowFixtureGenerator();
        System.out.println(generator.generateModel(new FakeRowFixtureDataProvider()));
    }
    
    static class FakeRowFixtureDataProvider implements IDataProvider {

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
