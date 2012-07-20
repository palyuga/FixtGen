package fixtgen.main;

/**
 * Data Provider interface
 * @author D.Palyuga
 * @version 1.0
 */
public interface IDataProvider {
    
    /** Returns next line of text */
    public String readLine();
    
    /** Reset reader to first line */
    public void reset();
}
