package fixtgen.generators;

import fixtgen.main.FixtureType;
import fixtgen.main.IDataProvider;
import fixtgen.preferences.IPreferenceManager;

/**
 * ColumnFixture generator
 * @author dpalyuga
 * @version 1.0
 */
public class ColumnFixtureGenerator extends AbstractGenerator {
        
    final static String ATTR_MODIFIER = "public";
    
    final static String ATTR_TYPE = "String";
    
    final static String METHOD_MODIFIER = "public";
    
    final static String METHOD_TYPE = "String";
    
    final static String TYPE_PREFIX = FixtureType.COLUMN.getName();
    
    private ColumnFixtureGenerator(final IPreferenceManager preferenceManager) {
        super(preferenceManager);
    }
    
    public static ColumnFixtureGenerator createNew(final IPreferenceManager preferenceManager) {
    	return new ColumnFixtureGenerator(preferenceManager);
    }
    
    /* (non-Javadoc)
     * @see com.luxoft.eid.odt.fixturegenerator.IGenerator#generate()
     */
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
                String[] tableItems = splitTableCells(currentLine);
                for (String item : tableItems) {
                    if (isMethodName(item)) {
                        result.append(getMethodDeclaration(item));
                    } else {
                        result.append(getAttrDeclaration(item));
                    }
                }
                break; // To read only the second line with attributes and methods names
            }
        }
        result.append("}");
        return result.toString();
    }
    
    public boolean isMethodName(final String name) {
        final String clearName = getClearName(name);
        final String lastSymbol = clearName.substring(clearName.length() - 1);
        final String lastTwoSymblos = clearName.substring(clearName.length() - 2);
        return ("?".equals(lastSymbol) || "()".equals(lastTwoSymblos));        
    }
    
    private String getAttrDeclaration(final String attrName) {
        return generateAttrJavadoc(attrName) + 
               ATTR_MODIFIER + " " + ATTR_TYPE + " " + convertToCamelCase(attrName) + ";";
    }

    private String getMethodDeclaration(final String methodName) {
        
        return  generateMethodJavadoc(methodName)
                + METHOD_MODIFIER + " " + METHOD_TYPE + " " + convertToCamelCase(methodName) + "() {"
                + "final String result = \"\";" 
                + "return result;" 
                + "} ";
    }
    
    private String generateMethodJavadoc(final String methodName) {
        return ""; //TODO: Add javadoc generation
    }
    
    private String generateAttrJavadoc(final String attrName) {
        return "/** " + attrName.trim() + " */ ";
    }

    @Override
    protected String getParentClassKey() {
        return FixtureType.COLUMN.getParentClassPrefKey();
    }

    @Override
    protected String getImportClassesKey() {
        return FixtureType.COLUMN.getImportClassesPrefKey();
    }

}
