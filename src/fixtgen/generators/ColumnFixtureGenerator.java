/**
 * @{#} ColumnFixtureGenerator.java
 *
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage. Release of this
 * file requires prior approval from Boeing Management.
 *
 * Copyright (c) Feb 15, 2012, The Boeing Company
 * Unpublished Work - All Rights Reserved
 */
package fixtgen.generators;

import fixtgen.main.IDataProvider;

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
    
    final static String TYPE_PREFIX = "ColumnFixture";
    
    public ColumnFixtureGenerator() {
        super();
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
                break; // Only reading second line with attributes and methods names
            }
        }
        result.append("}");
        return result.toString();
    }
    
    protected boolean isMethodName(final String name) {
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
        return TYPE_PREFIX + "ParentClass";
    }

    @Override
    protected String getImportClassesKey() {
        return TYPE_PREFIX + "ImportClasses";
    }

}
