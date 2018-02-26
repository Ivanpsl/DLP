package sintactico;
import java.io.*;
import main.*;
import ast.Position;
%%
%byaccj
%unicode
%line
%column
%public

%{
	public Yylex(Reader in, GestorErrores gestor) {
		this(in);
		this.gestor = gestor;
	}

	public int line() { return yyline + 1; }
	public int column() { return yycolumn + 1; }
	public String lexeme() { return yytext(); }

	// Traza para probar el léxico de manera independiente al sintáctico
	public static void main(String[] args) throws Exception {
		Yylex lex = new Yylex(new FileReader(Main.programa), new GestorErrores());
		int token;
		while ((token = lex.yylex()) != 0)
			System.out.println("\t[" + lex.line() + ":" + lex.column() + "] Token: " + token + ". Lexema: " + lex.lexeme());
	}

	private GestorErrores gestor;
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
[a-zA-ZñÑ][a-zA-Z0-9_ñÑ\\n]*	{ return Parser.IDENT; }
'.'		{ return Parser.LITCHAR; }


"/*"~"*/"	{ }
"//".*							{ }		// Comentario de una linea 
[ \n\r\t]     { }

.	  { System.out.println("Error léxico en " + line() + ":" + column() + ". Lexema = \"" + yytext() + "\""); }
