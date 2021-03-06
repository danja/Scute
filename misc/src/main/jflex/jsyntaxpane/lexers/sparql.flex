package jsyntaxpane.lexers;


import jsyntaxpane.Token;
import jsyntaxpane.TokenType;

%%

%public
%class CLexer
%extends DefaultJFlexLexer
%final
%unicode
%char
%type Token

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

// strings
STRING_LITERAL1 = "'" ( ([^\x27\x5C\x0A\x0D]) | {ECHAR} )* "'"
STRING_LITERAL2 = "\"" ( ([^\x22\x5C\x0A\x0D]) | {ECHAR} )* "\""
STRING_LITERAL_LONG1 = "'''" ( ( "'" | "''" )? ( [^'\\] | {ECHAR} ) )* "'''"
STRING_LITERAL_LONG2 = "\"\"\"" ( ( "\"" | "\"\"" )? ( [^\"\\] | {ECHAR} ) )* "\"\"\""
ECHAR = [\\][tbnrf\\\"\']

// character sets etc
NIL = "("{WS}*")"
WS = [\x20\x09\x0D\x0A]
ANON = "["{WS}*"]"

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

%%

<YYINITIAL> {
  [bB][aA][sS][eE] { return KW_BASE; }
  [pP][rR][eE][fF][iI][xX] { return KW_PREFIX; }
  [sS][eE][lL][eE][cC][tT] { return KW_SELECT; }
  [cC][oO][nN][sS][tT][rR][uU][cC][tT] { return KW_CONSTRUCT; }
  [dD][eE][sS][cC][rR][iI][bB][eE] { return KW_DESCRIBE; }
  [aA][sS][kK] { return KW_ASK; }
  [oO][rR][dD][eE][rR] { return KW_ORDER; }
  [bB][yY] { return KW_BY; }
  [aA][sS][kK] { return KW_ASK; }
  [dD][eE][sS][cC] { return KW_DESC; }
  [lL][iI][mM][iI][tT] { return KW_LIMIT; }
  [oO][fF][fF][sS][eE][tT] { return KW_OFFSET; }
  [dD][iI][sS][tT][iI][nN][cC][tT] { return KW_DISTINCT; }
  [rR][eE][dD][uU][cC][eE][dD] { return KW_REDUCED; }
  [fF][rR][oO][mM] { return KW_FROM; }
  [nN][aA][mM][eE][dD] { return KW_NAMED; }
  [wW][hH][eE][rR][eE] { return KW_WHERE; }
  [gG][rR][aA][pP][hH] { return KW_GRAPH; }
  [oO][pP][tT][iI][oO][nN][aA][lL] { return KW_OPTIONAL; }
  [uU][nN][iI][oO][nN] { return KW_UNION; }
  [fF][iI][lL][tT][eE][rR] { return KW_FILTER; }
  "a" { return KW_A; }
  [sS][tT][rR] { return KW_STR; }
  [lL][aA][nN][gG] { return KW_LANG; }
  [lL][aA][nN][gG][mM][sA][tT][cC][hH][eE][sS] { return KW_LANGMATCHES; }
  [dD][aA][tT][aA][tT][yY][pP][eE] { return KW_DATATYPE; }
  [bB][oO][uU][nN][dD] { return KW_BOUND; }
  [sS][aA][mM][eE][tT][eE][rR][mM] { return KW_SAME_TERM; }
  [iI][sS][uU][rR][iI] { return KW_IS_URI; }
  [iI][sS][iI][rR][iI] { return KW_IS_IRI; }
  [iI][sS][bB][lL][aA][nN][kK] { return KW_IS_BLANK; }
  [iI][sS][lL][iI][tT][eE][rR][aA][lL] { return KW_IS_LITERAL; }
  [rR][eE][gG][eE][xX] { return KW_REGEX; }

  [tT][rR][uU][eE] { return LIT_TRUE; }
  [fF][aA][lL][sS][eE] { return LIT_FALSE; }

  {INTEGER} { return LIT_INTEGER; }
  {DECIMAL} { return LIT_DECIMAL; }
  {DOUBLE} { return LIT_DOUBLE; }
  {INTEGER_POSITIVE} { return LIT_INTEGER_POS; }
  {INTEGER_NEGATIVE} { return LIT_INTEGER_NEG; }
  {DECIMAL_POSITIVE} { return LIT_DECIMAL_POS; }
  {DECIMAL_NEGATIVE} { return LIT_DECIMAL_NEG; }
  {DOUBLE_POSITIVE} { return LIT_DOUBLE_POS; }
  {DOUBLE_NEGATIVE} { return LIT_DOUBLE_NEG; }

  "(" { return OP_LROUND; }
  ")" { return OP_RROUND; }
  "{" { return OP_LCURLY; }
  "}" { return OP_RCURLY; }
  "[" { return OP_LSQUARE; }
  "]" { return OP_RSQUARE; }
  
  "." { return OP_DOT; }
  ";" { return OP_SEMI; }
  "," { return OP_COMMA; }
  "||" { return OP_PIPEPIPE; }
  "&&" { return OP_ANDAND; }
  "=" { return OP_EQ; }
  "!=" { return OP_NE; }
  "<" { return OP_LT; }
  ">" { return OP_GT; }
  "<=" { return OP_LE; }
  ">=" { return OP_GE; }
  "+" { return OP_PLUS; }
  "-" { return OP_MINUS; }
  "*" { return OP_MULT; }
  "/" { return OP_DIV; }
  "!" { return OP_NOT; }
  "^^" { return OP_HATHAT; }

  "<"/{IRI_REF_BODY}">" { yybegin(IRI_REF_BODY); return LIT_IRI_START; }
  {PNAME_LN} { yypushback(yylength()); yybegin(PNAME_LN); }
  {PNAME_NS} { yypushback(yylength()); yybegin(PNAME_NS); }
  {BLANK_NODE_LABEL} { return LIT_BLANK_NODE; }
  {ANON} { return LIT_ANON; }
  {NIL} { return LIT_NIL; }
  {LANGTAG} { return LANGTAG; }

  {VAR1}|{VAR2} { return VAR; }
  {STRING_LITERAL1}|{STRING_LITERAL2}|{STRING_LITERAL_LONG1}|{STRING_LITERAL_LONG2} { return LIT_STRING; }

  "#".* { return COMMENT; }
}

// here we process PNAME_NS as separate tokens instead of one. We can assume that it looks exactly as it should
<PNAME_NS> {
	":" { yybegin(YYINITIAL); return NAME_COLON; }
	{PN_PREFIX}/":" { return NAME_NS; }
	[^] { yybegin(YYINITIAL); return BAD_CHARACTER; }
}
<PNAME_LN> {
	":" { yybegin(PNAME_LN2); return NAME_COLON; }
	{PN_PREFIX}/":" { return NAME_NS; }
	[^] { yybegin(YYINITIAL); return BAD_CHARACTER; }
}
<PNAME_LN2> {
	{PN_LOCAL} { yybegin(YYINITIAL); return NAME_LN; }
	[^] { yybegin(YYINITIAL); return BAD_CHARACTER; }
}

<IRI_REF_BODY> {
  {IRI_REF_BODY} { yybegin(IRI_REF_END); return LIT_IRI_BODY; }
	">" { yybegin(YYINITIAL); return LIT_IRI_END; }
	[^] { yybegin(YYINITIAL); return BAD_CHARACTER; }
}
<IRI_REF_END> {
	">" { yybegin(YYINITIAL); return LIT_IRI_END; }
	[^] { yybegin(YYINITIAL); return BAD_CHARACTER; }
}

{WS} { return WHITE_SPACE; }
[:letter:]+ { return UNKNOWN; }
[^] { return BAD_CHARACTER; }
