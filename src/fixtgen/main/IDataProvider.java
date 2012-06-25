/**
 * @{#} IDataProvider.java
 *
 * This file contains Boeing intellectual property.  It may
 * contain information about Boeing processes that are part
 * of the Company's competitive advantage. Release of this
 * file requires prior approval from Boeing Management.
 *
 * Copyright (c) Feb 15, 2012, The Boeing Company
 * Unpublished Work - All Rights Reserved
 */
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
