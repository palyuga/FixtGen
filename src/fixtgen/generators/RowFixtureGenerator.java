package fixtgen.generators;

import fixtgen.main.FixtureType;
import fixtgen.main.IDataProvider;
import fixtgen.preferences.IPreferenceManager;

/**
 * RowFixture generator
 * @author dpalyuga
 * @version 1.0
 */
public class RowFixtureGenerator extends AbstractGenerator implements IModelGenerator {
    
    private static final String TYPE_PREFIX = FixtureType.ROW.getName();

    public static String MODEL_CLASSNAME = "ModelClass";
    
    private String attrModifier = "public";
    
    private String attrType = "String";
    
    private String methodModifier = "public";
    
    private RowFixtureGenerator(final IPreferenceManager preferenceManager) {
        super(preferenceManager);
    }
    
    public static RowFixtureGenerator createNew(final IPreferenceManager preferenceManager) {
    	return new RowFixtureGenerator(preferenceManager);
    }    
    
    @Override
    public String generate(IDataProvider dataProvider) {
        StringBuffer result = new StringBuffer();
        dataProvider.reset();
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
        return TYPE_PREFIX + PARENT_CLASS_PREF_POSTFIX;
    }

    @Override
    protected String getImportClassesKey() {
        return TYPE_PREFIX + IMPORT_CLASSES_PREF_POSTFIX;
    }
}
