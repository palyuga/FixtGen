/**
 * @{#} RowFixtureGenerator.java
 *
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage. Release of this
 * file requires prior approval from Boeing Management.
 *
 * Copyright (c) Feb 20, 2012, The Boeing Company
 * Unpublished Work - All Rights Reserved
 */
package fixtgen.generators;

import fixtgen.main.IDataProvider;

/**
 * RowFixture generator
 * @author dpalyuga
 * @version 1.0
 */
public class RowFixtureGenerator extends AbstractGenerator {
    
    private static final String TYPE_PREFIX = "RowFixture";

    public static String MODEL_CLASSNAME = "ModelClass";
    
    private String attrModifier = "public";
    
    private String attrType = "String";
    
    private String methodModifier = "public";
    
    @Override
    public String generate(IDataProvider dataProvider) {
        StringBuffer result = new StringBuffer();
        String currentLine;
        int lineNumber = 0;
        if ( (currentLine = dataProvider.readLine()) != null ) {
            if (lineNumber++ == 0) {
                result.append(
                    getClassHeader(getImportClasses(), currentLine, getParentClass())
                );
            }
        }
        result.append(getRowFixtureBody());
        return result.toString();
    } 
    
    @Override
    public String generateModel(IDataProvider dataProvider) {
        StringBuffer result = new StringBuffer();
        dataProvider.reset();
        String currentLine;
        int lineNumber = 0;
        while ( (currentLine = dataProvider.readLine()) != null ) {
            if (lineNumber++ == 0) {
                result.append(
                    "package " + getPackageName(currentLine) + ";"
                );
            } else {
                result.append(getStubClass(currentLine));
                break;
            }
        }
        result.append("}");
        return result.toString();
    }
    
    private String getRowFixtureBody() {
        final String result = 
            "public Class<" + MODEL_CLASSNAME + "> getTargetClass() {"
            + "return " + MODEL_CLASSNAME + ".class; }"
            + "public Object[] query() throws Exception {"
            + "return new " + MODEL_CLASSNAME + "[1]; } }";
        return result;
    }
    
    private String getStubClass(final String row) {
        StringBuilder result = new StringBuilder();
        StringBuilder methods = new StringBuilder();
        StringBuilder attributes = new StringBuilder();
        String classHeader = "public class " + MODEL_CLASSNAME + " {";
        String[] args = splitTableCells(row);
        StringBuilder constructor = getConstructor(args);
        for (String arg : args) {
            String camelCaseName = convertToCamelCase(getClearName(arg));
            attributes.append(attrModifier + " " + attrType + " " + camelCaseName + ";");
            methods.append(
               methodModifier + " " + "void" + " " 
               + "set" + capitalizeFirstChar(camelCaseName) 
               + "(final " + attrType + " " + camelCaseName + ") {"
               + " this." + camelCaseName + " = " + camelCaseName + "; }"
            );
        }
        
        return result
            .append(classHeader)
            .append(attributes)
            .append(constructor)
            .append(methods)
            .toString();
    }
    
    private StringBuilder getConstructor(final String[] args) {
        final StringBuilder result = new StringBuilder();
        result.append(methodModifier).append(' ').append(MODEL_CLASSNAME).append('(');
        
        for (String argumentName : args) {
            result.append("final ")
                .append(attrType)
                .append(' ')
                .append(convertToCamelCase(argumentName))
                .append(", ");
        }
        
        // Now we need to remove ", " at the end of string
        result.delete(result.length() - 2, result.length());
        result.append(") {");
        for (String argumentName : args) {
            result.append("this.")
                .append(convertToCamelCase(argumentName))
                .append(" = ")
                .append(convertToCamelCase(argumentName))
                .append(';');
        }
        
        result.append('}');
        return result;
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
