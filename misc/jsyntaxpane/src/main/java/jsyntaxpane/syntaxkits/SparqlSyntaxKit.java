
package jsyntaxpane.syntaxkits;

import jsyntaxpane.DefaultSyntaxKit;
import jsyntaxpane.lexers.SparqlLexer;

/**
 *
 */
public class SparqlSyntaxKit extends DefaultSyntaxKit {

    public SparqlSyntaxKit() {
        super(new SparqlLexer());
    }
}
