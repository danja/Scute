/**
 * 
 */
package org.hyperdata.scute.validate;

/*
 * see com.hp.hpl.jena.n3.turtle.Turtle2NTriples
 */

import java.io.PrintStream;
import java.io.PrintWriter;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.n3.turtle.TurtleEventHandler;
import com.hp.hpl.jena.n3.turtle.TurtleParseException;

/** Emit N-triples */
public class TurtleHandler implements TurtleEventHandler
{
    PrintStream out = System.out ;
    int count = 0;
    
    public TurtleHandler(PrintStream out) { this.out = out ; }
    
    public void triple(int line, int col, Triple triple)
    {
        //Check it's valid triple.
        Node s = triple.getSubject() ;
        Node p = triple.getPredicate() ;
        Node o = triple.getObject() ;
        
        if ( ! ( s.isURI() || s.isBlank() ) )
            throw new TurtleParseException("["+line+", "+col+"] : Error: Subject is not a URI or blank node") ;
        if ( ! p.isURI() )
            throw new TurtleParseException("["+line+", "+col+"] : Error: Predicate is not a URI") ;
        if ( ! ( o.isURI() || o.isBlank() || o.isLiteral() ) ) 
            throw new TurtleParseException("["+line+", "+col+"] : Error: Object is not a URI, blank node or literal") ;
      
        outputNode(s) ;
        out.print(" ") ;
        outputNode(p) ;
        out.print(" ") ;
        outputNode(o) ;
        out.print(" .") ;
        out.println() ;
        out.flush() ;
        count++;
    }
    
    public int getCount(){
    	return count;
    }

    private void outputNode(Node node)
    {
        if ( node.isURI() ) 
        { 
            out.print("<") ;
            out.print(node.getURI()) ;
            out.print(">") ;
            return ; 
        }
        if ( node.isBlank() )
        {
            out.print("_:") ;
            out.print(node.getBlankNodeLabel()) ;
            return ;
        }
        if ( node.isLiteral() )
        {
            out.print('"') ;
            outputEsc(node.getLiteralLexicalForm()) ;
            out.print('"') ;

            if ( node.getLiteralLanguage() != null && node.getLiteralLanguage().length()>0)
            {
                out.print('@') ;
                out.print(node.getLiteralLanguage()) ;
            }

            if ( node.getLiteralDatatypeURI() != null )
            {
                out.print("^^<") ;
                out.print(node.getLiteralDatatypeURI()) ;
                out.print(">") ;
            }
            return ; 
        }
        System.err.println("Illegal node: "+node) ;
    }

    public void startFormula(int line, int col)
    { throw new TurtleParseException("["+line+", "+col+"] : Error: Formula found") ; }

    public void endFormula(int line, int col)
    { throw new TurtleParseException("["+line+", "+col+"] : Error: Formula found") ; }

    public void prefix(int line, int col, String prefix, String iri)
    {  }
    
    static boolean applyUnicodeEscapes = true ;
    
    private static void writeString(String s, PrintWriter writer) {

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' || c == '"') {
                writer.print('\\');
                writer.print(c);
            } else if (c == '\n') {
                writer.print("\\n");
            } else if (c == '\r') {
                writer.print("\\r");
            } else if (c == '\t') {
                writer.print("\\t");
            } else if (c >= 32 && c < 127) {
                writer.print(c);
            } else {
                String hexstr = Integer.toHexString(c).toUpperCase();
                int pad = 4 - hexstr.length();
                writer.print("\\u");
                for (; pad > 0; pad--)
                    writer.print("0");
                writer.print(hexstr);
            }
        }
    }
    
    
    public  void outputEsc(String s)
    {
        int len = s.length() ;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            
            // Escape escapes and quotes
            if (c == '\\' || c == '"' ) 
            {
                out.print('\\') ;
                out.print(c) ;
            }
            else if (c == '\n') out.print("\\n");
            else if (c == '\t') out.print("\\t");
            else if (c == '\r') out.print("\\r");
            else if (c == '\f') out.print("\\f");
            else if ( c >= 32 && c < 127 )
                out.print(c);
            else
            {
                // Unsubtle.  Does not cover beyond 16 bits codepoints 
                // which Java keeps as surrogate pairs and wil print as two \ u escapes. 
                String hexstr = Integer.toHexString(c).toUpperCase();
                int pad = 4 - hexstr.length();
                out.print("\\u");
                for (; pad > 0; pad--)
                    out.print("0");
                out.print(hexstr);
            }
        }
    }
}

