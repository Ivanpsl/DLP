//### This file created by BYACC 1.8(/Java extension  1.14)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "sintac.y"
package sintactico;

//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short DISTINTO=257;
public final static short IGUAL=258;
public final static short MAYORIGUAL=259;
public final static short MENORIGUAL=260;
public final static short AND=261;
public final static short OR=262;
public final static short VAR=263;
public final static short IDENT=264;
public final static short STRUCT=265;
public final static short READ=266;
public final static short PRINT=267;
public final static short PRINTSP=268;
public final static short PRINTLN=269;
public final static short RETURN=270;
public final static short IF=271;
public final static short ELSE=272;
public final static short WHILE=273;
public final static short LITREAL=274;
public final static short LITENT=275;
public final static short LITCHAR=276;
public final static short CAST=277;
public final static short INT=278;
public final static short FLOAT=279;
public final static short CHAR=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    6,    6,    4,    3,
    3,    5,   11,   10,   10,   12,   13,   13,    8,    8,
    9,    9,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   16,   16,   16,    7,
    7,    7,    7,    7,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    0,    2,    5,   10,
    8,    6,    3,    0,    2,    2,    1,    3,    0,    1,
    0,    2,    3,    3,    3,    3,    3,    2,    7,   11,
    7,    4,    5,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    3,    7,    3,    4,    3,    4,    1,    3,    0,    1,
    1,    1,    1,    4,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    0,    3,    4,    5,    6,    0,
    0,    0,    0,    0,    0,    0,   20,   14,    0,   63,
   60,   61,   62,    0,    0,    0,    0,    0,    0,    9,
   13,    7,    0,   18,    0,    0,   15,    0,    0,    0,
   12,   16,   64,    8,    0,    7,   11,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   34,   35,   37,
    0,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   51,   53,    0,    0,   23,   24,
   25,   26,   27,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   55,
    0,   10,    0,    0,    0,    0,    0,    0,   54,   32,
   56,   33,    0,   21,   21,    0,    0,    0,    0,    0,
   31,   52,    0,   21,    0,   30,
};
final static short yydgoto[] = {                          1,
    2,    6,    7,   44,    9,   39,   24,   15,   45,   28,
   16,   37,   17,   62,   63,   98,
};
final static short yysindex[] = {                         0,
    0, -206, -253,  -22, -240,    0,    0,    0,    0,  -26,
 -238,  -89,  -68,  -20,   11,   16,    0,    0, -222,    0,
    0,    0,    0,   -4,  -68,  -48, -238, -117,  -31,    0,
    0,    0,  -68,    0,    7,   12,    0,  -68, -195,  -51,
    0,    0,    0,    0,  -33,    0,    0,   74,   74,   36,
   74,   74,   74,   74,   65,   38,   39,    0,    0,    0,
   20,    0,  316, -195,   41,  162,   -6,   69,  460,  468,
  493,  501,    0,  540,   74,   74,  -68,   74,   74,   74,
   74,   74,   74,   74,   74,   74,   74,   74,   74,   74,
 -182,   74,  -19,   69,    0,    0,  734,    2,    0,    0,
    0,    0,    0,  352,  384,   24,  761,  761,  -41,  -41,
  -41,  -41,   -6,   -6,  -27,  -27,  -24,  -24,  642,    0,
  576,    0,    3,   28,   74,  -35,  -34,   50,    0,    0,
    0,    0,  734,    0,    0,   74,   -5,    9,  391, -179,
    0,    0,  -29,    0,   23,    0,
};
final static short yyrindex[] = {                         0,
    0,   96,    0,    0,    0,    0,    0,    0,    0,    0,
   54,    0,    0,    0,    0,   56,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   37,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  722,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   37,   95,    0,  398,   55,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   55,    0,    0,    4,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -32,  -28,  768,  798,
  803,  812,  433,  439,  404,  411,  122,  130,    0,    0,
    0,    0,    0,  728,    0,    0,    0,    0,    0,    0,
    0,    0,   10,    0,    0,    0,    0,    0,    0,   51,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   98,    0,   57,   -8,    0,  -61,    0,
   73,    0,   77,    0,  872,   14,
};
final static int YYTABLESIZE=1072;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         49,
   88,   86,   93,   87,   91,   89,   48,   35,   49,   33,
   10,   49,   43,   49,   88,   43,   31,   11,   91,   89,
   48,   91,   19,   12,   40,   14,   49,   49,   49,   43,
   43,   13,   43,   18,   48,   88,   86,   25,   87,   91,
   89,   49,  124,  131,   57,  125,  125,   57,   48,   90,
   58,   26,   29,   58,   30,   49,    3,    4,    5,   27,
   49,   38,   48,   90,   43,   41,   90,    3,  106,   21,
   42,   46,  137,  138,   32,   68,   21,   75,   76,   77,
   94,  120,  145,   29,   90,  128,  132,  134,  135,  136,
   29,   47,  143,  144,   19,    1,   17,   49,   59,    8,
   36,   49,   64,   34,   48,  122,   49,  123,   48,   96,
    0,    0,    0,   48,    0,    0,    0,    0,    0,  140,
    0,    0,    0,   73,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  141,    0,   36,   36,   36,   36,   36,
   36,   36,    0,    0,    0,    0,   14,  146,    0,    0,
    0,    0,    0,   36,   36,   36,   36,    0,    0,    0,
    0,   21,   40,   40,   40,   40,   40,    0,   40,    0,
   41,   41,   41,   41,   41,   29,   41,    0,    0,    0,
   40,   40,   40,   40,    0,   36,    0,   36,   41,   41,
   41,   41,    0,    0,    0,   20,    0,    0,    0,    0,
    0,    0,   95,   88,   86,    0,   87,   91,   89,   21,
   22,   23,    0,    0,   40,    0,    0,    0,    0,   84,
   85,   83,   41,   82,   49,   49,    0,    0,   43,   43,
   50,    0,   51,   52,   53,   54,   55,   56,    0,   57,
   58,   59,   60,   61,   50,    0,   51,   52,   53,   54,
   55,   56,   90,   57,   58,   59,   60,   61,   50,    0,
   51,   52,   53,   54,   55,   56,    0,   57,   58,   59,
   60,   61,   50,    0,   51,   52,   53,   54,   55,   56,
    0,   57,   58,   59,   60,   61,   50,    0,   51,   52,
   53,   54,   55,   56,    0,   57,   58,   59,   60,   61,
   21,    0,   21,   21,   21,   21,   21,   21,    0,   21,
   21,   21,   21,   21,   29,    0,   29,   29,   29,   29,
   29,   29,    0,   29,   29,   29,   29,   29,   65,    0,
    0,    0,   65,    0,    0,    0,    0,   65,   58,   59,
   60,   61,   58,   59,   60,   61,    0,   58,   59,   60,
   61,   36,   36,   36,   36,   36,   36,   88,   86,    0,
   87,   91,   89,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   83,   92,   82,   40,   40,
   40,   40,   40,   40,    0,    0,   41,   41,   41,   41,
   41,   41,  126,   88,   86,    0,   87,   91,   89,    0,
    0,    0,    0,    0,    0,    0,   90,    0,    0,    0,
    0,   83,    0,   82,    0,    0,    0,    0,   78,   79,
   80,   81,   84,   85,  127,   88,   86,    0,   87,   91,
   89,  142,   88,   86,    0,   87,   91,   89,   50,    0,
    0,   50,   90,   83,   38,   82,   38,   38,   38,    0,
   83,   39,   82,   39,   39,   39,   50,   50,   50,   50,
    0,    0,   38,   38,   38,   38,    0,    0,    0,   39,
   39,   39,   39,   47,   90,    0,   47,    0,    0,   48,
    0,   90,   48,    0,    0,    0,    0,    0,    0,    0,
   50,   47,   47,   47,   47,    0,   38,   48,   48,   48,
   48,   88,   86,   39,   87,   91,   89,    0,    0,   88,
   86,    0,   87,   91,   89,    0,    0,    0,   99,   83,
    0,   82,    0,    0,    0,   47,  100,   83,    0,   82,
    0,   48,    0,    0,   88,   86,    0,   87,   91,   89,
    0,    0,   88,   86,    0,   87,   91,   89,    0,    0,
   90,  101,   83,    0,   82,    0,    0,    0,   90,  102,
   83,    0,   82,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   78,   79,   80,   81,   84,   85,    0,    0,
    0,   88,   86,   90,   87,   91,   89,    0,    0,    0,
    0,   90,    0,    0,    0,    0,    0,    0,  103,   83,
    0,   82,    0,    0,    0,    0,    0,    0,   78,   79,
   80,   81,   84,   85,    0,    0,    0,   88,   86,    0,
   87,   91,   89,    0,    0,    0,    0,    0,    0,    0,
   90,    0,    0,    0,  130,   83,    0,   82,    0,    0,
   78,   79,   80,   81,   84,   85,    0,   78,   79,   80,
   81,   84,   85,    0,   50,   50,   50,   50,   50,   50,
   38,   38,   38,   38,   38,   38,   90,   39,   39,   39,
   39,   39,   39,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   88,   86,    0,   87,   91,   89,   47,
   47,   47,   47,   47,   47,   48,   48,   48,   48,   48,
   48,   83,    0,   82,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   78,   79,   80,   81,
   84,   85,    0,    0,   78,   79,   80,   81,   84,   85,
    0,    0,   90,    0,  129,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   78,
   79,   80,   81,   84,   85,    0,    0,   78,   79,   80,
   81,   84,   85,   36,   36,    0,   36,   36,   36,   56,
   56,    0,   56,   56,   56,   88,   86,    0,   87,   91,
   89,   36,   36,   36,    0,    0,    0,   56,   56,   56,
    0,    0,    0,   83,    0,   82,   78,   79,   80,   81,
   84,   85,   88,   86,    0,   87,   91,   89,   42,    0,
    0,   42,   36,    0,    0,    0,    0,    0,   56,    0,
   83,    0,   82,    0,   90,    0,   42,   42,   42,   42,
    0,    0,   78,   79,   80,   81,   84,   85,   46,    0,
    0,   46,    0,   45,    0,    0,   45,    0,    0,    0,
    0,   90,   44,    0,    0,   44,   46,   46,   46,   46,
   42,   45,   45,   45,   45,    0,    0,    0,    0,    0,
   44,   44,   44,   44,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   46,    0,    0,    0,    0,   45,    0,    0,   78,   79,
   80,   81,   84,   85,   44,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
   67,    0,   69,   70,   71,   72,   74,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   97,
    0,    0,    0,    0,    0,    0,  104,  105,    0,  107,
  108,  109,  110,  111,  112,  113,  114,  115,  116,  117,
  118,  119,    0,  121,    0,   97,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   36,   36,
   36,   36,   36,   36,   56,   56,   56,   56,   56,   56,
   78,   79,   80,   81,   84,   85,  133,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  139,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   80,
   81,   84,   85,    0,   42,   42,   42,   42,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   46,   46,   46,   46,    0,   45,
   45,   45,   45,    0,    0,    0,    0,    0,   44,   44,
   44,   44,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   42,   43,   64,   45,   46,   47,   40,  125,   41,   58,
  264,   44,   41,   33,   42,   44,   25,   40,   46,   47,
   40,   46,   91,  264,   33,  264,   59,   33,   61,   38,
   59,   58,   61,  123,   40,   42,   43,   58,   45,   46,
   47,   33,   41,   41,   41,   44,   44,   44,   40,   91,
   41,   41,  275,   44,   59,   33,  263,  264,  265,   44,
   93,   93,   40,   91,   93,   59,   91,  263,   77,   33,
   59,  123,  134,  135,  123,   40,   40,   40,   40,   60,
   40,  264,  144,   33,   91,   62,   59,  123,  123,   40,
   40,  125,  272,  123,   41,    0,   41,   33,   44,    2,
   28,   33,   46,   27,   40,  125,   33,   94,   40,   41,
   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   -1,  125,
   -1,   -1,   -1,   59,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  125,   -1,   41,   42,   43,   44,   45,
   46,   47,   -1,   -1,   -1,   -1,  264,  125,   -1,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,  125,   41,   42,   43,   44,   45,   -1,   47,   -1,
   41,   42,   43,   44,   45,  125,   47,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   91,   -1,   93,   59,   60,
   61,   62,   -1,   -1,   -1,  264,   -1,   -1,   -1,   -1,
   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,  278,
  279,  280,   -1,   -1,   93,   -1,   -1,   -1,   -1,  261,
  262,   60,   93,   62,  257,  258,   -1,   -1,  257,  258,
  264,   -1,  266,  267,  268,  269,  270,  271,   -1,  273,
  274,  275,  276,  277,  264,   -1,  266,  267,  268,  269,
  270,  271,   91,  273,  274,  275,  276,  277,  264,   -1,
  266,  267,  268,  269,  270,  271,   -1,  273,  274,  275,
  276,  277,  264,   -1,  266,  267,  268,  269,  270,  271,
   -1,  273,  274,  275,  276,  277,  264,   -1,  266,  267,
  268,  269,  270,  271,   -1,  273,  274,  275,  276,  277,
  264,   -1,  266,  267,  268,  269,  270,  271,   -1,  273,
  274,  275,  276,  277,  264,   -1,  266,  267,  268,  269,
  270,  271,   -1,  273,  274,  275,  276,  277,  264,   -1,
   -1,   -1,  264,   -1,   -1,   -1,   -1,  264,  274,  275,
  276,  277,  274,  275,  276,  277,   -1,  274,  275,  276,
  277,  257,  258,  259,  260,  261,  262,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   60,   61,   62,  257,  258,
  259,  260,  261,  262,   -1,   -1,  257,  258,  259,  260,
  261,  262,   41,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   41,   42,   43,   -1,   45,   46,
   47,   41,   42,   43,   -1,   45,   46,   47,   41,   -1,
   -1,   44,   91,   60,   41,   62,   43,   44,   45,   -1,
   60,   41,   62,   43,   44,   45,   59,   60,   61,   62,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   59,
   60,   61,   62,   41,   91,   -1,   44,   -1,   -1,   41,
   -1,   91,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   59,   60,   61,   62,   -1,   93,   59,   60,   61,
   62,   42,   43,   93,   45,   46,   47,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,   93,   59,   60,   -1,   62,
   -1,   93,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   91,   59,   60,   -1,   62,   -1,   -1,   -1,   91,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
   -1,   42,   43,   91,   45,   46,   47,   -1,   -1,   -1,
   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,  257,  258,  259,
  260,  261,  262,   -1,  257,  258,  259,  260,  261,  262,
  257,  258,  259,  260,  261,  262,   91,  257,  258,  259,
  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,  257,
  258,  259,  260,  261,  262,  257,  258,  259,  260,  261,
  262,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,  257,  258,  259,
  260,  261,  262,   42,   43,   -1,   45,   46,   47,   42,
   43,   -1,   45,   46,   47,   42,   43,   -1,   45,   46,
   47,   60,   61,   62,   -1,   -1,   -1,   60,   61,   62,
   -1,   -1,   -1,   60,   -1,   62,  257,  258,  259,  260,
  261,  262,   42,   43,   -1,   45,   46,   47,   41,   -1,
   -1,   44,   91,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   60,   -1,   62,   -1,   91,   -1,   59,   60,   61,   62,
   -1,   -1,  257,  258,  259,  260,  261,  262,   41,   -1,
   -1,   44,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   91,   41,   -1,   -1,   44,   59,   60,   61,   62,
   93,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   93,   -1,   -1,  257,  258,
  259,  260,  261,  262,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   48,
   49,   -1,   51,   52,   53,   54,   55,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   68,
   -1,   -1,   -1,   -1,   -1,   -1,   75,   76,   -1,   78,
   79,   80,   81,   82,   83,   84,   85,   86,   87,   88,
   89,   90,   -1,   92,   -1,   94,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,  257,  258,  259,  260,  261,  262,
  257,  258,  259,  260,  261,  262,  125,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  136,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,
  260,  261,  262,   -1,  257,  258,  259,  260,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,   -1,  257,
  258,  259,  260,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"\"DISTINTO\"","\"IGUAL\"",
"\"MAYORIGUAL\"","\"MENORIGUAL\"","\"AND\"","\"OR\"","\"VAR\"","\"IDENT\"",
"\"STRUCT\"","\"READ\"","\"PRINT\"","\"PRINTSP\"","\"PRINTLN\"","\"RETURN\"",
"\"IF\"","\"ELSE\"","\"WHILE\"","\"LITREAL\"","\"LITENT\"","\"LITCHAR\"",
"\"CAST\"","\"INT\"","\"FLOAT\"","\"CHAR\"",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones :",
"definiciones : definiciones definicion",
"definicion : defFuncion",
"definicion : defVar",
"definicion : defStruct",
"defsVariablesLocales :",
"defsVariablesLocales : defsVariablesLocales defVar",
"defVar : \"VAR\" \"IDENT\" ':' tipo ';'",
"defFuncion : \"IDENT\" '(' funOpParams ')' ':' tipo '{' defsVariablesLocales sentencias '}'",
"defFuncion : \"IDENT\" '(' funOpParams ')' '{' defsVariablesLocales sentencias '}'",
"defStruct : \"STRUCT\" \"IDENT\" '{' camposStruct '}' ';'",
"param : \"IDENT\" ':' tipo",
"camposStruct :",
"camposStruct : camposStruct paramStruct",
"paramStruct : param ';'",
"funParams : param",
"funParams : param ',' funParams",
"funOpParams :",
"funOpParams : funParams",
"sentencias :",
"sentencias : sentencias sentencia",
"sentencia : \"READ\" expr ';'",
"sentencia : \"PRINT\" expr ';'",
"sentencia : \"PRINTSP\" expr ';'",
"sentencia : \"PRINTLN\" expr ';'",
"sentencia : \"RETURN\" expr ';'",
"sentencia : \"RETURN\" ';'",
"sentencia : \"IF\" '(' expr ')' '{' sentencias '}'",
"sentencia : \"IF\" '(' expr ')' '{' sentencias '}' \"ELSE\" '{' sentencias '}'",
"sentencia : \"WHILE\" '(' expr ')' '{' sentencias '}'",
"sentencia : expr '=' expr ';'",
"sentencia : \"IDENT\" '(' expresiones ')' ';'",
"expr : \"LITREAL\"",
"expr : \"LITENT\"",
"expr : \"IDENT\"",
"expr : \"LITCHAR\"",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr \"MAYORIGUAL\" expr",
"expr : expr \"IGUAL\" expr",
"expr : expr '<' expr",
"expr : expr '>' expr",
"expr : expr \"MENORIGUAL\" expr",
"expr : expr \"AND\" expr",
"expr : expr \"OR\" expr",
"expr : expr \"DISTINTO\" expr",
"expr : '!' expr",
"expr : '(' expr ')'",
"expr : \"CAST\" '<' tipo '>' '(' expr ')'",
"expr : \"IDENT\" '(' ')'",
"expr : expr '[' expr ']'",
"expr : expr '.' \"IDENT\"",
"expr : \"IDENT\" '(' expresiones ')'",
"expresiones : expr",
"expresiones : expresiones ',' expr",
"expresiones :",
"tipo : \"INT\"",
"tipo : \"FLOAT\"",
"tipo : \"CHAR\"",
"tipo : \"IDENT\"",
"tipo : '[' \"LITENT\" ']' tipo",
};

//#line 127 "sintac.y"

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

//#line 544 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
