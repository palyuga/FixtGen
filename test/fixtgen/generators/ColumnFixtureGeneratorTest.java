package fixtgen.generators;

import fixtgen.main.IDataProvider;
import junit.framework.TestCase;

public class ColumnFixtureGeneratorTest extends TestCase {
    
    public void testIsMethodName() {
    
        ColumnFixtureGenerator generator = new ColumnFixtureGenerator();
        final String method1 = " method? ";
        final String method2 = " method() ";
        final String notMethod = " this is not a method ";
        
        assertTrue(generator.isMethodName(method1));
        assertTrue(generator.isMethodName(method2));
        assertFalse(generator.isMethodName(notMethod));
    }

    public void testGenerate() {
        ColumnFixtureGenerator generator = new ColumnFixtureGenerator();
        final String expectedOutput = 
            "/** " + ColumnFixtureGenerator.COPYRIGHT + " */"
            + "package some.package;import com.boeing.eid.odt.fitnesse.common.ColumnSymbolFixture;"
            + "public class ClassName extends ColumnSymbolFixture "
            + "{/** attr one */ public String attrOne;/** attr two */ public String attrTwo;" 
            + "public String methodOne() {final String result = \"\";return result;}" 
            + " public String methodTwo() {final String result = \"\";return result;} }";
        assertEquals(generator.generate(new FakeDataProvider()), expectedOutput);
    }
    
    public void testY() {
        ColumnFixtureGenerator generator = new ColumnFixtureGenerator();
        System.out.println(generator.generate(new FakeDataProvider1()));
    }
    
    static class FakeDataProvider implements IDataProvider {

        private static int lineNumber = 1;
        
        @Override
        public String readLine() {
            final String result;
            final String line1 = " |!-  some.package.ClassName  -!|";
            final String line2 = " | attr one |attr two|   method one?  | method two() |";
            
            if (lineNumber == 1) {
                result = line1;
            } else if (lineNumber == 2) {
                result = line2;
            } else {
                result = null;
                lineNumber = 0;
            }
            lineNumber++;
            return result;
        }

        @Override
        public void reset() {
            lineNumber = 1;            
        }
    }
    
    static class FakeDataProvider1 implements IDataProvider {

        private static int lineNumber = 1;
        
        @Override
        public String readLine() {
            final String result;
            final String line1 = "|!-com.boeing.eid.odt.fitnesse.selenium.page.listorders.FillFieldsAndComboboxes-!|";
            final String line2 = "|creation date from             |creation date to             |fill?             |";
            
            if (lineNumber == 1) {
                result = line1;
            } else if (lineNumber == 2) {
                result = line2;
            } else {
                result = null;
                lineNumber = 0;
            }
            lineNumber++;
            return result;
        }

        @Override
        public void reset() {
            lineNumber = 1;           
        }
    }
    
}
