package fixtgen.generators;

import fixtgen.generators.DoFixtureGenerator;
import fixtgen.main.IDataProvider;
import fixtgen.testservice.MockPreferenceManager;
import junit.framework.TestCase;

public class DoFixtureGeneratorTest extends TestCase {

	private static final DoFixtureGenerator generator =
			DoFixtureGenerator.createNew(new MockPreferenceManager());
	
     public void testGetMethodNameFromTableRow() {
         final String inputString = " | is text  | someText | displayed on page | pageName  |  ";
         final String expectedResult = "isTextDisplayedOnPage";
         assertEquals(expectedResult, generator.getMethodNameFromTableRow(inputString));
     }
     
     public void testGetParentClass() {
         System.out.println(generator.getParentClass());
     }
     
     public void testGetImportClasses() {
         String[] result = generator.getImportClasses();
         for (String item : result) {
             System.out.println(item);
         }
     }
     
     public void testGenerate() {
         final String expectedResult = "/** Copyright */package com.boeing.eid.odt.fitnesse.selenium.page.submitrequest;" 
             + "import com.boeing.eid.odt.fitnesse.common.DoSymbolFixture;" 
             + "public class CheckElementsExistence extends DoSymbolFixture "
             + "{public boolean isLinkDisplayed(final String arg0) { return true; } " 
             + "public String getDataItemTableRowCount() { return \"\"; } " 
             + "public boolean isPackageTypeComboboxEnabled() { return true; } "
             + "public boolean isThisNumberGreaterThanAndNotSeven(final String arg0, final String arg1) { return true; } "
             + "}";
         final String actualResult = generator.generate(new DoFixtureDataMockProvider());
         assertEquals(expectedResult, actualResult);
     }
     
     static class DoFixtureDataMockProvider implements IDataProvider {

         private static int lineNumber = 0;
         
         @Override
         public String readLine() {
             
             final String[] lines = {
                     "|!-com.boeing.eid.odt.fitnesse.selenium.page.submitrequest.CheckElementsExistence-!               |",
                     "|'''check'''|''is link ''                     |Upload from File                |''displayed''|true|",
                     "|'''check'''|''is link ''                     |Some other link               |''displayed''|true|",
                     "|'''check'''|''get data item table row count''|5                                                  |",
                     "|'''reject'''                |''is package type combobox enabled''                |",
                     "|is this number| 6 | greater than | 5 | and not seven|"
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
