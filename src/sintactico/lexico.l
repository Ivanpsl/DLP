package sintactico;

%%
%byaccj
%unicode
%line
%column
%public

%{
  public String lexeme() {
   return yytext();
  }

  public int line() {
    return yyline + 1;
  }

  public int column() {
    return yycolumn + 1;
  }
%}

%%
[+/\[\]<>;(){}=,:\.\*\-!&\|]  { return yytext().charAt(0); }


int		{ return Parser.INT; }
float	{ return Parser.FLOAT; }
char	{ return Parser.CHAR; }
var		{ return Parser.VAR; }
if		{ return Parser.IF; }
else	{ return Parser.ELSE; }
while	{ return Parser.WHILE; }
return 	{ return Parser.RETURN; }
read	{ return Parser.READ; }
cast	{ return Parser.CAST; }
struct	{ return Parser.STRUCT; }
print	{ return Parser.PRINT; }
printsp	{ return Parser.PRINTSP; }
println	{ return Parser.PRINTLN; }

"&&"	{ return Parser.AND; }
"||"	{ return Parser.OR; }
"<="	{ return Parser.MENORIGUAL; }
">=" 	{ return Parser.MAYORIGUAL; }
"!="	{ return Parser.DISTINTO; }
"=="	{ return Parser.IGUAL; }

[0-9]+     				{ return Parser.LITENT; }
[0-9]+\.[0-9]*			{ return Parser.LITREAL; }
[a-zA-ZÒ—][a-zA-Z0-9_Ò—\\n]*	{ return Parser.IDENT; }
'.'		{ return Parser.LITCHAR; }


"/*"~"*/"	{ }
"//".*							{ }		// Comentario de una linea 
[ \n\r\t]     { }

.	  { System.out.println("Error lÈxico en " + line() + ":" + column() + ". Lexema = \"" + yytext() + "\""); }
