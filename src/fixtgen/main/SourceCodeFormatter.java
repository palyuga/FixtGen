package fixtgen.main;

import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

/**
 * Class for source code format using eclipse formatter
 * @author dpalyuga
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class SourceCodeFormatter {
    
    /** 
     * Formats given source code and returns it 
     * @return formatted source code
     */
    public static String format(final String source) {
        if (source == null) {
            return "";
        }
        CodeFormatter codeFormatter = new DefaultCodeFormatter();
        TextEdit textEdit = codeFormatter.format(
            CodeFormatter.F_INCLUDE_COMMENTS,
            source, 
            0,
            source.length(),
            0,
            null
        );
        
        IDocument doc = new Document(source);
        
        try {
            textEdit.apply(doc);
        } catch (MalformedTreeException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return doc.get();
    }
}
