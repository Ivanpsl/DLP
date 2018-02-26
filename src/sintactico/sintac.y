%{
package sintactico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.GestorErrores;
import ast.*;
%}
%left '{' '}'
%left '(' ')'
%left  'DISTINTO' 'IGUAL'
%left 'MAYORIGUAL' 'MENORIGUAL' '>' '<' 
%left 'AND' '!' 'OR'
%left '+' '-'
%left '*' '/'
%left '[' ']'  
%left '.' 
%left ','

%%

programa: definiciones	     	{ raiz = new Programa((List<Definicion>)$1);}
	;

//------------ Definiciones

definiciones:				 	{ $$ = new ArrayList<Definicion>();}
	 | definiciones definicion	{ $$ = $1; ((ArrayList<Definicion>)$$).add((Definicion)$2);}
	;
	
definicion: defFuncion			{$$ = $1;}
	| defVar					{$$ = $1;}
	| defStruct					{$$ = $1;}
	;
 
defsVariablesLocales:  					{ $$ = new ArrayList<DefVariable>();}
	| defsVariablesLocales defVar		{$$ = $1; ((ArrayList<DefVariable>)$$).add((DefVariable)$2);}
	;
	
defVar: 'VAR' 'IDENT' ':' tipo ';'  			{$$ = new DefVariable($2, $4); }
	; 
	
defFuncion: 'IDENT' '(' funOpParams ')'':' tipo '{' defsVariablesLocales sentencias '}'				{$$ = new DefFuncion($1, $3, $6, $8, $9);}
	| 'IDENT' '(' funOpParams ')' '{' defsVariablesLocales sentencias '}'							{$$ = new DefFuncion($1, $3, new TipoVoid(), $6,$7);}
	;	

defStruct: 'STRUCT' 'IDENT' '{' camposStruct '}' ';'												{$$ = new DefStruct($2,$4);}
	;	



//--------- Parametros 	
		
param: 'IDENT' ':' tipo				{$$ = new Parametro($1, $3);}
	;

camposStruct:						{$$ = new ArrayList<Parametro>();}
	| camposStruct campoStruct		{$$ = $1; ((ArrayList<Parametro>)$$).add((Parametro)$2);}
	;
campoStruct: param ';'				{$$ = $1;}
	;
	
	
funParams: param					{$$=new ArrayList<Parametro>(); ((ArrayList<Parametro>)$$).add((Parametro)$1);}
	| funParams ',' param			{$$=$1; ((ArrayList<Parametro>)$1).add((Parametro)$3); }
	;
funOpParams: 						{$$ = new ArrayList<Parametro>();}
	| funParams						{$$ = $1;}
	;
	
	

//------------ Sentencias y expresiones 
	
sentencias: 					{$$ = new ArrayList<Sentencia>();}
	| sentencias sentencia		{$$ = $1; ((ArrayList<Sentencia>)$$).add((Sentencia)$2);}
	;

sentencia: 'READ' expr';'											{$$ = new Read($2) ;}
	| 'PRINT' expr ';'												{$$ = new Print($2);}		
	| 'PRINTSP' expr ';'											{$$ = new Printsp($2);}	
	| 'PRINTLN' expr ';'											{$$ = new Println($2);}	
	| 'RETURN' expr ';'												{$$ = new Return($2);}	
	| 'RETURN' ';'													{$$ = new Return(null);}
	| 'IF' '(' expr ')' '{' sentencias '}'							{$$ = new If($3,$6,null);}
	| 'IF' '(' expr ')' '{' sentencias '}' 'ELSE' '{' sentencias '}'{$$ = new If($3,$6,$10);}
	| 'WHILE' '(' expr ')' '{' sentencias '}'						{$$ = new While($3,$6);}
	| expr '=' expr ';'												{$$ = new Asignacion($1,$3);}
	| 'IDENT' '(' expresiones ')' ';' 								{$$ = new LlamadaFuncion($1,$3);}
	;

	
expr: 'LITREAL'						{$$ = new LiteralReal($1);}
	| 'LITENT'						{$$ = new LiteralInt($1);}
	| 'IDENT'						{$$ = new Variable($1);}
	| 'LITCHAR'						{$$ = new Caracter($1);}
	| expr '+' expr	  				{$$ = new ExprAritmetica($1,$2,$3);}
	| expr '-' expr		  			{$$ = new ExprAritmetica($1,$2,$3);}  
	| expr '*' expr	  				{$$ = new ExprAritmetica($1,$2,$3);}	 
	| expr '/' expr	  				{$$ = new ExprAritmetica($1,$2,$3);}	 
	| expr 'MAYORIGUAL' expr 		{$$ = new ExprLogica($1,$2,$3);}
	| expr 'IGUAL' expr	  			{$$ = new ExprLogica($1,$2,$3);}
	| expr '<' expr	  				{$$ = new ExprLogica($1,$2,$3);}
	| expr '>' expr	  				{$$ = new ExprLogica($1,$2,$3);} 
	| expr 'MENORIGUAL' expr		{$$ = new ExprLogica($1,$2,$3);}
	| expr 'AND' expr	  			{$$ = new ExprLogica($1,$2,$3);}
	| expr 'OR' expr	  			{$$ = new ExprLogica($1,$2,$3);}
	| expr 'DISTINTO' expr	  		{$$ = new ExprLogica($1,$2,$3);}
	| '!' expr	  					{$$ = new Negacion($2);}	
	| '(' expr ')'	  				{$$ = $2;}
	| 'CAST''<' tipo '>''('expr')'	{$$ = new Cast($3,$6);}
	| expr '['expr']' 	  			{$$ = $1;}
	| expr '.' 'IDENT'	  			{$$ = $1;}
	|'IDENT' '(' expresiones ')'  	{$$ = new LlamadaFuncion($1,$3);}
	;

expresiones: expr 					{$$ = new ArrayList<Expresion>(); ((ArrayList<Expresion>)$$).add((Expresion)$1);}
	| expresiones ',' expr			{$$ = $1; ((ArrayList<Expresion>)$$).add((Expresion)$3);}
	|
	;

tipo: 'INT'						{$$ = new TipoInt();}
	| 'FLOAT'					{$$ = new TipoReal();}
	| 'CHAR'					{$$ = new TipoChar();}
	| 'IDENT'					{$$ = new TipoStruct($1);}	
	| '[' 'LITENT' ']' tipo		{$$ = new TipoArray($2, $4);}	
	;




%%
/* No es necesario modificar esta sección ------------------ */

public Parser(Yylex lex, GestorErrores gestor, boolean debug) {
	this(debug);
	this.lex = lex;
	this.gestor = gestor;
}

// Métodos de acceso para el main -------------
public int parse() { return yyparse(); }
public AST getAST() { return raiz; }

// Funciones requeridas por Yacc --------------
void yyerror(String msg) {
	Token lastToken = (Token) yylval;
	gestor.error("Sintáctico", "Token = " + lastToken.getToken() + ", lexema = \"" + lastToken.getLexeme() + "\". " + msg, lastToken.getStart());
}

int yylex() {
	try {
		int token = lex.yylex();
		yylval = new Token(token, lex.lexeme(), lex.line(), lex.column());
		return token;
	} catch (IOException e) {
		return -1;
	}
}

private Yylex lex;
private GestorErrores gestor;
private AST raiz;
