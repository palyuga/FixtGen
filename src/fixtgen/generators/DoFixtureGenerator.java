package fixtgen.generators;

import java.util.HashMap;
import java.util.Map;

import fixtgen.main.IDataProvider;

/**
 * DoFixture generator
 * @author dpalyuga
 * @version 1.0
 */
public class DoFixtureGenerator extends AbstractGenerator {
   
    final static String ARG_TYPE = "String";
    
    final static String METHOD_MODIFIER = "public";
    
    final static String METHOD_TYPE = "String";

    private static final String TYPE_PREFIX = "DoFixture";
    
    private Map<String, Boolean> methodsAdded = new HashMap<String, Boolean>();
    
    public DoFixtureGenerator() {
        super();
    }
    
    @Override
    public String generate(IDataProvider dataProvider) {
        StringBuffer result = new StringBuffer();
        String currentLine;
        int lineNumber = 0;
        while ( (currentLine = dataProvider.readLine()) != null ) {
            if (lineNumber++ == 0) {
                result.append(
                    getClassHeader(getImportClasses(), currentLine, getParentClass())
                );
            } else {
                final String methodName = getMethodNameFromTableRow(currentLine);
                final int numberOfArguments = getNumberOfArguments(currentLine);
                
                final String mapKey = methodName + "___" + numberOfArguments;
                
                if (!this.methodsAdded.containsKey(mapKey)) {
                    
                    this.methodsAdded.put(mapKey, true);
                    
                    result.append(METHOD_MODIFIER + " ");
                    result.append(
                        (isBooleanMethod(methodName) || isRejectMethod(currentLine)) ?
                        "boolean" : "String"
                    );
                    
                    result.append(" " + methodName + "(");
                    
                    
                    for (int i = 0; i < numberOfArguments; i++) {
                        if (i != 0) {
                            result.append(", ");
                        }
                        result.append("final " + ARG_TYPE + " arg" + i);
                    }
                    result.append(") { return ");
                    result.append(
                        (isBooleanMethod(methodName) || isRejectMethod(currentLine)) ?
                        "true" : "\"\""
                    );
                    result.append("; } ");
                }
            }
        }
        result.append("}");
        return result.toString();
    }
    
    protected String getMethodNameFromTableRow(final String tableRow) {
        StringBuilder result = new StringBuilder();
        String[] items = splitTableCells(tableRow);
        int startIndex = 0;
        int finishIndex = items.length;
        int divisionBy2Modulo = 0;
        final String firstWord = getClearName(items[0]);
        
        if ("check".equals(firstWord)) {
            startIndex = 1;
            finishIndex--;
            divisionBy2Modulo = 1;            
        } else if ("reject".equals(firstWord)) {
            startIndex = 1;
            divisionBy2Modulo = 1;
        }
        
        for (int i = startIndex; i < finishIndex; i++) {
            if ( i % 2 == divisionBy2Modulo) { 
                String currentWord = getClearName(items[i]);
                if (i == startIndex) {
                    result.append(
                       convertToCamelCase(currentWord)
                    );
                } else {
                    result.append(
                        capitalizeFirstChar(
                            convertToCamelCase(currentWord)
                        )
                   );
                }
            }
        }
        return result.toString();
    }
    
    protected int getNumberOfArguments(final String tableRow) {

        String[] items = splitTableCells(tableRow);
        final String firstWord = getClearName(items[0]);
        int countLength;
        if ("check".equals(firstWord)) {
            countLength = items.length - 2;
        } else if ("reject".equals(firstWord)){
            countLength = items.length - 1;
        } else {
            countLength = items.length;
        }
        
        return getNumberOfEvenCells(countLength);        
    }

    private boolean isRejectMethod(final String tableRow) {
        String[] items = splitTableCells(tableRow);
        final String firstWord = getClearName(items[0]);
        return "reject".equals(firstWord);
    }
    
    private boolean isBooleanMethod(final String methodName) {
        return "is".equals(methodName.substring(0, 2));
    }
    
    private int getNumberOfEvenCells(int cellsAmount) {
        return cellsAmount / 2;
    }
    
    @Override
    protected String getParentClassKey() {
        return TYPE_PREFIX + "ParentClass";
    }

    @Override
    protected String getImportClassesKey() {
        return TYPE_PREFIX + "ImportClasses";
    }

}
