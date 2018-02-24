%{
package sintactico;

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

programa: definiciones
	;

//------------ Definiciones

definiciones:
	 | definiciones definicion
	;
	
definicion: defFuncion
	| defVar
	| defStruct
	;
 
defsVariablesLocales:  
	| defsVariablesLocales defVar
	;
	
defVar: 'VAR' 'IDENT' ':' tipo ';'
	; 
	
defFuncion: 'IDENT' '(' funOpParams ')'':' tipo '{' defsVariablesLocales sentencias '}'
	| 'IDENT' '(' funOpParams ')' '{' defsVariablesLocales sentencias '}'
	;	

defStruct: 'STRUCT' 'IDENT' '{' camposStruct '}' ';'
	;	



//--------- Parametros 	
		
param: 'IDENT' ':' tipo
	;

camposStruct:
	| camposStruct paramStruct
	;
paramStruct: param ';'
	;
	
	
funParams: param
	| param ',' funParams
	;
funOpParams: 
	| funParams
	;
	
	

//------------ Sentencias y expresiones 
	
sentencias: 
	| sentencias sentencia
	;

sentencia: 'READ' expr';'
	| 'PRINT' expr ';'
	| 'PRINTSP' expr ';'
	| 'PRINTLN' expr ';'
	| 'RETURN' expr ';'
	| 'RETURN' ';'
	| 'IF' '(' expr ')' '{' sentencias '}'
	| 'IF' '(' expr ')' '{' sentencias '}' 'ELSE' '{' sentencias '}'
	| 'WHILE' '(' expr ')' '{' sentencias '}'
	| expr '=' expr ';'
	| 'IDENT' '(' expresiones ')' ';' 
	;

expr: 'LITREAL'
	| 'LITENT'
	| 'IDENT'
	| 'LITCHAR'
	| expr '+' expr	  
	| expr '-' expr	  
	| expr '*' expr	 
	| expr '/' expr	 
	| expr 'MAYORIGUAL' expr
	| expr 'IGUAL' expr
	| expr '<' expr
	| expr '>' expr
	| expr 'MENORIGUAL' expr
	| expr 'AND' expr
	| expr 'OR' expr
	| expr 'DISTINTO' expr
	| '!' expr	
	| '(' expr ')'
	| 'CAST''<' tipo '>''('expr')'
	| 'IDENT' '('')'
	| expr '['expr']' 
	| expr '.' 'IDENT'
	|'IDENT' '(' expresiones ')'
	;

expresiones: expr 
	| expresiones ',' expr
	|
	;

tipo: 'INT'
	| 'FLOAT'
	| 'CHAR'
	| 'IDENT'
	| '[' 'LITENT' ']' tipo
	;



%%

private Yylex lex;
private int token;

public Parser(Yylex lex, boolean debug) {
  this(debug);
  this.lex = lex;
}


public int parse() { return yyparse(); }


// Funciones requeridas por el parser

void yyerror(String s)
{
 System.out.println("Error sintáctico en " + lex.line() + ":" + lex.column() + " Token = " + token + " lexema = \"" + lex.lexeme()+"\"");
}

int yylex() {
  try {
	token = lex.yylex();
	yylval = lex.lexeme();
	return token;
  } catch (Exception e) {
    return -1;
  }
}

