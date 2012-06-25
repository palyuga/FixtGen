package fixtgen.main;

/**
 * Provides data from strings separated by specified symbol
 * @author dpalyuga
 * @version 1.0
 */
public class SimpleStringDataProvider implements IDataProvider {

    private String[] data;
    
    private int index = 0;
    
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    /**
     * The constructor
     * @param data String data to provide
     */
    public SimpleStringDataProvider(final String data) {
        this.data = data.split(LINE_SEPARATOR);
        this.index = 0;
    }
    
    @SuppressWarnings("unused")
    private SimpleStringDataProvider() {}
    
    /** Reset string counter to 0 */
    public void reset() {
        this.index = 0;
    }
    
    @Override
    public String readLine() {
        String result = null;
        if (index < data.length) {
            result = data[index++];
        }
        return result;
    }

}
