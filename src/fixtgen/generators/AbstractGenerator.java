/**
 * @{#} IGenerator.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import fixtgen.main.Activator;
import fixtgen.main.IDataProvider;

/**
 * Generator interface
 *
 * @author dpalyuga
 * @version 1.0
 */
public abstract class AbstractGenerator {
    
    protected static final String COPYRIGHT = "Copyright"; //TODO: Add copyright

    protected static final String IMPORT_CLASSES_DELIMITER = ",";

    protected static final String CLASS_MODIFIER = "public";

    protected static final String EXTENDS = "extends";
    
    private String parentClass;
    
    private String importClasses;    
    
    protected AbstractGenerator() {
        
        final String parentClass = Activator.getDefault().getPreferenceStore()
                .getString(getParentClassKey());        
        
        final String importClasses = Activator.getDefault().getPreferenceStore()
                .getString(getImportClassesKey());
        
        setParentClass(parentClass);
        setImportClasses(importClasses);
    }

    public abstract String generate(final IDataProvider dataProvider);

    public final String getFullPackageName(final IDataProvider dataProvider) {
        dataProvider.reset();
        return this.getPackageName(dataProvider.readLine());
    }

    public String getClassName(final IDataProvider dataProvider) {
        dataProvider.reset();
        return this.getClassNameFromPackagePath(dataProvider.readLine());
    }

    protected final String convertToCamelCase(final String inputString) {

        final StringBuffer result = new StringBuffer();

        final String lowerCaseString = this.getClearName(inputString.toLowerCase())
                .replace("?", "").replace("()", "");

        boolean shouldCapitalizeNextChar = false;

        for (int i = 0; i < lowerCaseString.length(); i++) {
            char ch = lowerCaseString.charAt(i);

            if (ch == ' ') {
                shouldCapitalizeNextChar = true;
            } else {

                if (shouldCapitalizeNextChar) {
                    result.append(
                        lowerCaseString.substring(i, i + 1).toUpperCase()
                    );
                } else {
                    result.append(ch);
                }

                shouldCapitalizeNextChar = false;
            }
        }

        return result.toString();
    }

    protected String stripTableSymbols(final String inputString) {
        return inputString.replace("|", "")
                          .replace(" ", "")
                          .replace("'", "")
                          .replace("!-", "")
                          .replace("-!", "");
    }

    protected String getClearName(final String inputString) {
        return inputString.replace("|", "")
                          .replace("'", "")
                          .replace("=", "")
                          .trim();
    }


    protected String getPackageName(final String inputString) {
        
        /* We need to take only first column value for row fixtures 
         * which has more than one columns in the header */
        String firstColumn = this.splitTableCells(inputString)[0];

        String[] stringArray = this.stripTableSymbols(firstColumn).split("\\.");
        String[] tempArray = new String[stringArray.length - 1];
        for (int i = 0; i < stringArray.length - 1; i++) {
            tempArray[i] = stringArray[i];
        }
        return this.implode(tempArray, ".");

    }

    protected String getClassNameFromPackagePath(final String inputString) {
        
        /* We need to take only first column value for row fixtures 
         * which has more than one columns in the header */
        String firstColumn = this.splitTableCells(inputString)[0];

        String[] stringArray = this.stripTableSymbols(firstColumn).split("\\.");
        return stringArray[stringArray.length - 1];
    }

    protected String[] splitTableCells(final String tableRow) {
        return tableRow
                .trim() // Trim spaces
                .replaceAll("\\|$|^\\|", "") // Trim '|' symbols
                .split("\\|"); // Split by '|' symbols
    }

    protected String getClassHeader(
        final String[] importClasses,
        final String fitnesseTableHeader,
        final String parentClass
    ) {
        StringBuilder result = new StringBuilder();
        result.append(
                "/** " + this.getCopyright() + " */"
                 + "package " + this.getPackageName(fitnesseTableHeader) + ";"
        );

        for (String importClass : importClasses) {
            result.append("import " + importClass + ";");
        }
        result.append(
           CLASS_MODIFIER + " class "
           + this.getClassNameFromPackagePath(fitnesseTableHeader)
           + " extends " + parentClass
           + " {"
        );
        return result.toString();
    }

    protected String getCopyright() {
        return COPYRIGHT;
    }

    protected String capitalizeFirstChar(final String inputString) {
        return Character.toUpperCase(inputString.charAt(0)) + inputString.substring(1);
    }

    protected String implode(final String[] strArray, final String glue) {
        String result;
        if (strArray == null || strArray.length == 0) {
            result = "";
        } else {
            StringBuilder out = new StringBuilder();
            out.append(strArray[0]);
            for (int i = 1 ;i < strArray.length; i++) {
                out.append(glue).append(strArray[i]);
            }
            result = out.toString();
        }
        return result;
    }

    /**
     * Load a Properties File
     * @param propsFile
     * @return Properties
     * @throws IOException
     */
    public static Properties loadProperties(File propsFile) throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(propsFile);
        props.load(fis);
        fis.close();
        return props;
    }
    

    /**
     * @param parentClass the parentClass to set
     */
    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    /**
     * @param importClasses the importClasses to set
     */
    public void setImportClasses(String importClasses) {
        this.importClasses = importClasses;
    }

    
    protected String getParentClass() {
        return this.parentClass;
    }
    
    protected String[] getImportClasses() {
        return this.importClasses.split(IMPORT_CLASSES_DELIMITER);
    }
    
    protected abstract String getParentClassKey();
    
    protected abstract String getImportClassesKey();
}
