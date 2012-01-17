/* --------------------------Usercode Section------------------------ */

package jsyntaxpane.lexers;


import jsyntaxpane.Token;
import jsyntaxpane.TokenType;


%%

/* -----------------Options and Declarations Section----------------- */

%public
%class SparqlLexer
%extends DefaultJFlexLexer
%final
%unicode
%char
%type Token


/*
  Declarations

  Code between %{ and %}, both of which must be at the beginning of a
  line, will be copied letter to letter into the lexer class source.
  Here you declare member variables and functions that are used inside
  scanner actions.
*/
%{
    /**
     * Default constructor is needed as we will always call the yyreset
     */
    public SparqlLexer() {
        super();
    }

    @Override
    public int yychar() {
        return yychar;
    }
%}


/*
  Macro Declarations

  These declarations are regular expressions that will be used later
  in the Lexical Rules Section.
*/

IRI_REF_BODY = ([^\<\>\"\{\}\|\^\`\\\x00-\x20])*
IRI_REF = "<"{IRI_REF_BODY}">"
PNAME_NS = {PN_PREFIX}? ":"
PNAME_LN = {PNAME_NS} {PN_LOCAL}
BLANK_NODE_LABEL = "_:" {PN_LOCAL}

VAR1 = "?" {VARNAME}
VAR2 = "$" {VARNAME}
LANGTAG = "@" [a-zA-Z]+ ("-" [a-zA-Z0-9]+)*

// numbers
INTEGER = [0-9]+
DECIMAL = ([0-9]+ "." [0-9]*) | ("." [0-9]+)
DOUBLE = ([0-9]+ "." [0-9]* {EXPONENT}) | ("." ([0-9])+ {EXPONENT}) | (([0-9])+ {EXPONENT})

INTEGER_POSITIVE = "+"{INTEGER}
DECIMAL_POSITIVE = "+"{DECIMAL}
DOUBLE_POSITIVE = "+"{DOUBLE}
INTEGER_NEGATIVE = "-"{INTEGER}
DECIMAL_NEGATIVE = "-"{DECIMAL}
DOUBLE_NEGATIVE = "-"{DOUBLE}

EXPONENT = [eE] [+-]? [0-9]+

Number = INTEGER | DECIMAL | DOUBLE | INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE | INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE

// strings
STRING_LITERAL1 = "'" ( ([^\x27\x5C\x0A\x0D]) | {ECHAR} )* "'"
STRING_LITERAL2 = "\"" ( ([^\x22\x5C\x0A\x0D]) | {ECHAR} )* "\""
STRING_LITERAL_LONG1 = "'''" ( ( "'" | "''" )? ( [^'\\] | {ECHAR} ) )* "'''"
STRING_LITERAL_LONG2 = "\"\"\"" ( ( "\"" | "\"\"" )? ( [^\"\\] | {ECHAR} ) )* "\"\"\""
ECHAR = [\\][tbnrf\\\"\']

String = STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | ECHAR

// character sets etc
NIL = "("{WS}*")"
WS = [\x20\x09\x0D\x0A]
ANON = "["{WS}*"]"

Bnode = BLANK_NODE_LABEL | ANON

PN_CHARS_BASE = [A-Za-z\u00C0-\u00D6\u00DB-\u00F6\u00f8-\u02FF\u0370-\u037D\u037F-\u1FFF\u200C-\u200D\u2070-\u218F\u2C00-\u2FEF\u3001-\uD7FF\uF900-\uFDCF\uFDF0-\uFFFD]
PN_CHARS_U = {PN_CHARS_BASE}|"_"
VARNAME = ( {PN_CHARS_U} | [0-9] ) ( {PN_CHARS_U} | [0-9\u00B7\u0300-\u036F\u203F-\u2040] )*
PN_CHARS = {PN_CHARS_U} | [\-0-9\u00B7\u0300-\u036F\u203F-\u2040]
PN_PREFIX = {PN_CHARS_BASE} (({PN_CHARS}|".")* {PN_CHARS})?
PN_LOCAL = ( {PN_CHARS_U} | [0-9] ) (({PN_CHARS}|".")* {PN_CHARS})?

%x PNAME_NS
%x PNAME_LN
%x PNAME_LN2

%x IRI_REF_BODY
%x IRI_REF_END

Reserved =
  [bB][aA][sS][eE] |
  [pP][rR][eE][fF][iI][xX] |
  [sS][eE][lL][eE][cC][tT] |
  [cC][oO][nN][sS][tT][rR][uU][cC][tT] |
  [dD][eE][sS][cC][rR][iI][bB][eE] |
  [aA][sS][kK] |
  [oO][rR][dD][eE][rR] |
  [bB][yY] |
  [aA][sS][kK] |
  [dD][eE][sS][cC] |
  [lL][iI][mM][iI][tT] |
  [oO][fF][fF][sS][eE][tT] |
  [dD][iI][sS][tT][iI][nN][cC][tT] |
  [rR][eE][dD][uU][cC][eE][dD] |
  [fF][rR][oO][mM] |
  [nN][aA][mM][eE][dD] |
  [wW][hH][eE][rR][eE] |
  [gG][rR][aA][pP][hH] |
  [oO][pP][tT][iI][oO][nN][aA][lL] |
  [uU][nN][iI][oO][nN] |
  [fF][iI][lL][tT][eE][rR] |
  "a" |
  [sS][tT][rR] |
  [lL][aA][nN][gG] |
  [lL][aA][nN][gG][mM][sA][tT][cC][hH][eE][sS] |
  [dD][aA][tT][aA][tT][yY][pP][eE] |
  [bB][oO][uU][nN][dD] |
  [sS][aA][mM][eE][tT][eE][rR][mM] |
  [iI][sS][uU][rR][iI] |
  [iI][sS][iI][rR][iI] |
  [iI][sS][bB][lL][aA][nN][kK] |
  [iI][sS][lL][iI][tT][eE][rR][aA][lL] |
  [rR][eE][gG][eE][xX] |

  [tT][rR][uU][eE] |
  [fF][aA][lL][sS][eE] 
%%

/* ------------------------Lexical Rules Section---------------------- */

/*
   This section contains regular expressions and actions, i.e. Java
   code, that will be executed when the scanner matches the associated
   regular expression. */

   /* YYINITIAL is the state at which the lexer begins scanning.  So
   these regular expressions will only be matched if the scanner is in
   the start state YYINITIAL. */

<YYINITIAL> {


  /* keywords */
  {Reserved}                     { return token(TokenType.KEYWORD); }

  /* operators OK */

  "(" 		|
  ")" 		|
  "{" 		|
  "}" 		|
  "[" 		|
  "]" 		|
  
  "." 		|
  ";" 		|
  "," 		|
  "||" 		|
  "&&" 		|
  "=" 		|
  "!=" 		|
  "<" 		|
  ">" 		|
  "<=" 		|
  ">=" 		|
  "+" 		|
  "-" 		|
  "*" 		|
  "/" 		|
  "!" 		|
  "^^"                          { return token(TokenType.OPERATOR); }

  /* string literal */
  {String}                 	{ return token(TokenType.STRING); }

  /* numeric literals */

  {Number}                 	{ return token(TokenType.NUMBER); }

  /* comments */
   "#".*                  	{ return token(TokenType.COMMENT); }

  /* whitespace */
  {WS}+                  { /* skip */ }

  /* identifiers */
  {IRI_REF}                   { return token(TokenType.IDENTIFIER); }

  {Bnode}                   { return token(TokenType.TYPE); }

 {PNAME_LN}	 { return token(TokenType.TYPE2); }

{PNAME_NS}	 { return token(TokenType.TYPE3); }
}


/* error fallback */
.|\n                             {  }
<<EOF>>                          { return null; }

