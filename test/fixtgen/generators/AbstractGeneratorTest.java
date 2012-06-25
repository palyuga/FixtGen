package fixtgen.generators;

import junit.framework.TestCase;

public class AbstractGeneratorTest extends TestCase {
    
    public void testConvertToCamelCase() {
        AbstractGenerator generator = new ColumnFixtureGenerator();
        String humanString = "   kill   all humans ";
        String expectedResult = "killAllHumans";
        String actualResult = generator.convertToCamelCase(humanString);
        assertEquals(actualResult, expectedResult);        
    }
    
    public void testTrimTableSymbols() {
        AbstractGenerator generator = new ColumnFixtureGenerator();
        String inputString = " |  some.text       | ";
        String expectedResult = "some.text";
        String actualResult = generator.stripTableSymbols(inputString);
        assertEquals(actualResult, expectedResult);  
    }
    
    public void testGetClassNameFromPackagePath() {
        AbstractGenerator generator = new ColumnFixtureGenerator();
        String inputString = "some.package.name.ClassName";
        String expectedResult = "ClassName";
        String actualResult = generator.getClassNameFromPackagePath(inputString);
        assertEquals(actualResult, expectedResult);  
    }
    
    public void testSplitTableCells() {
        AbstractGenerator generator = new ColumnFixtureGenerator();
        String inputString = "  |one|two|three| ";
        String[] expectedResult = {"one", "two", "three"};
        String[] actualResult = generator.splitTableCells(inputString);
        if (expectedResult.length == actualResult.length) {
            for (int i = 0; i < expectedResult.length; i++) {
                if (!expectedResult[i].equals(actualResult[i])) {
                    fail(
                       "The elements at index: " + i + "aren't equals. "
                       + "Expected element was: " + expectedResult[i]
                       + ", but actual was: " + actualResult[i]
                    );
                }
            }
        } else {
            fail("Lengths of expected and actual arrays aren't equals ");
        }
    }
    
    
}
