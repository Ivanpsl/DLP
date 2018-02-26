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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.GestorErrores;
import ast.*;
//#line 24 "Parser.java"




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
   15,   15,   15,   15,   15,   16,   16,   16,    7,    7,
    7,    7,    7,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    0,    2,    5,   10,
    8,    6,    3,    0,    2,    2,    1,    3,    0,    1,
    0,    2,    3,    3,    3,    3,    3,    2,    7,   11,
    7,    4,    5,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    3,    7,    4,    3,    4,    1,    3,    0,    1,    1,
    1,    1,    4,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    0,    3,    4,    5,    6,    0,
    0,    0,    0,    0,    0,   17,    0,   14,    0,   62,
   59,   60,   61,    0,    0,    0,    0,    0,    0,    9,
   13,    7,    0,   18,    0,    0,   15,    0,    0,    0,
   12,   16,   63,    8,    0,    7,   11,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   34,   35,   37,
    0,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   51,    0,    0,   23,   24,   25,
   26,   27,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   54,    0,
   10,    0,    0,    0,    0,    0,    0,   53,   32,   55,
   33,    0,   21,   21,    0,    0,    0,    0,    0,   31,
   52,    0,   21,    0,   30,
};
final static short yydgoto[] = {                          1,
    2,    6,    7,   44,    9,   39,   24,   15,   45,   28,
   16,   37,   17,   62,   63,   97,
};
final static short yysindex[] = {                         0,
    0, -206, -253,  -22, -240,    0,    0,    0,    0,  -26,
 -238,  -89,  -68,  -20,   12,    0,   16,    0, -213,    0,
    0,    0,    0,    7,  -68,  -50, -238, -122,  -25,    0,
    0,    0,  -68,    0,   15,   17,    0,  -68, -188,  -45,
    0,    0,    0,    0,  -33,    0,    0,   69,   69,   41,
   69,   69,   69,   69,   65,   42,   43,    0,    0,    0,
   26,    0,  453, -188,   47,  162,   -6,   69,  461,  488,
  494,  524,    0,  545,   69,   69,  -68,   69,   69,   69,
   69,   69,   69,   69,   69,   69,   69,   69,   69,   69,
 -176,   69,  -19,   69,    0,  717,    2,    0,    0,    0,
    0,    0,  316,  352,   28,  729,  729,  -41,  -41,  -41,
  -41,   -6,   -6,  -27,  -27,  -24,  -24,  577,    0,  682,
    0,    3,   34,   69,  -29,  -23,   55,    0,    0,    0,
    0,  717,    0,    0,   69,   -5,    9,  384, -175,    0,
    0,  -16,    0,   23,    0,
};
final static short yyrindex[] = {                         0,
    0,   96,    0,    0,    0,    0,    0,    0,    0,    0,
   58,    0,    0,    0,    0,    0,   60,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   37,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  556,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   37,   94,    0,  404,    4,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    4,    0,   10,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -32,  -28,  768,  781,  805,
  809,  426,  432,  391,  397,  122,  130,    0,    0,    0,
    0,    0,  635,    0,    0,    0,    0,    0,    0,    0,
    0,   11,    0,    0,    0,    0,    0,    0,   51,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  101,    0,   62,   -8,    0,  -54,    0,
   44,    0,    0,    0,  831,   18,
};
final static int YYTABLESIZE=1069;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         49,
   88,   86,   35,   87,   91,   89,   48,   33,   49,   93,
   10,   49,   43,   49,   88,   43,   31,   11,   91,   89,
   48,   91,   19,   12,   40,   14,   49,   49,   49,   43,
   43,   13,   43,   18,   48,   88,   86,   25,   87,   91,
   89,   49,  123,  130,   58,  124,  124,   58,   48,   90,
   56,   57,   26,   56,   57,   49,    3,    4,    5,   27,
   49,   29,   48,   90,   43,   30,   90,   38,  105,   21,
   34,   36,   32,   41,    3,   42,   21,   46,  136,  137,
   68,   75,   76,   29,   90,   77,   94,  119,  144,  127,
   29,   47,  131,  133,  135,    1,  142,   49,   19,  134,
   20,   49,    8,    0,   48,  121,  143,   64,   48,    0,
    0,  122,    0,    0,    0,    0,    0,    0,    0,  139,
    0,    0,    0,   73,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  140,   36,   36,   36,   36,   36,   36,
   36,   14,    0,    0,    0,    0,    0,  145,    0,    0,
    0,    0,   36,   36,   36,   36,    0,    0,    0,    0,
    0,   21,   40,   40,   40,   40,   40,    0,   40,    0,
   41,   41,   41,   41,   41,   29,   41,    0,    0,    0,
   40,   40,   40,   40,   36,    0,   36,    0,   41,   41,
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
    0,    0,   65,    0,    0,    0,    0,    0,   58,   59,
   60,   61,   58,   59,   60,   61,    0,    0,    0,    0,
   36,   36,   36,   36,   36,   36,  125,   88,   86,    0,
   87,   91,   89,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   83,    0,   82,   40,   40,
   40,   40,   40,   40,    0,    0,   41,   41,   41,   41,
   41,   41,  126,   88,   86,    0,   87,   91,   89,    0,
    0,    0,    0,    0,    0,    0,   90,    0,    0,    0,
    0,   83,    0,   82,    0,    0,    0,    0,   78,   79,
   80,   81,   84,   85,  141,   88,   86,    0,   87,   91,
   89,   38,    0,   38,   38,   38,    0,   39,    0,   39,
   39,   39,   90,   83,   50,   82,    0,   50,    0,   38,
   38,   38,   38,    0,    0,   39,   39,   39,   39,    0,
    0,    0,   50,   50,   50,   50,   47,    0,    0,   47,
    0,    0,   48,    0,   90,   48,    0,    0,    0,    0,
    0,    0,    0,   38,   47,   47,   47,   47,    0,   39,
   48,   48,   48,   48,   88,   86,   50,   87,   91,   89,
    0,    0,   88,   86,    0,   87,   91,   89,    0,    0,
    0,    0,   83,   92,   82,    0,    0,    0,   47,   98,
   83,    0,   82,    0,   48,    0,    0,    0,    0,   88,
   86,    0,   87,   91,   89,   88,   86,    0,   87,   91,
   89,    0,    0,   90,    0,    0,   99,   83,    0,   82,
    0,   90,  100,   83,    0,   82,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   88,   86,    0,   87,   91,
   89,    0,   78,   79,   80,   81,   84,   85,   90,    0,
    0,    0,  101,   83,   90,   82,   88,   86,    0,   87,
   91,   89,    0,    0,    0,    0,    0,   36,   36,    0,
   36,   36,   36,  102,   83,    0,   82,    0,   78,   79,
   80,   81,   84,   85,   90,   36,   36,   36,   88,   86,
    0,   87,   91,   89,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   90,   83,    0,   82,    0,
   78,   79,   80,   81,   84,   85,   36,   38,   38,   38,
   38,   38,   38,   39,   39,   39,   39,   39,   39,    0,
   50,   50,   50,   50,   50,   50,    0,   90,    0,  128,
    0,    0,    0,    0,    0,    0,   55,   55,    0,   55,
   55,   55,   47,   47,   47,   47,   47,   47,   48,   48,
   48,   48,   48,   48,   55,   55,   55,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   78,
   79,   80,   81,   84,   85,    0,    0,   78,   79,   80,
   81,   84,   85,   88,   86,   55,   87,   91,   89,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  129,   83,    0,   82,   78,   79,   80,   81,   84,   85,
   78,   79,   80,   81,   84,   85,    0,    0,   88,   86,
    0,   87,   91,   89,    0,    0,    0,    0,    0,    0,
   88,   86,   90,   87,   91,   89,   83,    0,   82,    0,
   78,   79,   80,   81,   84,   85,    0,    0,   83,    0,
   82,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   78,   79,   80,   81,   84,   85,   90,   42,    0,
    0,   42,   36,   36,   36,   36,   36,   36,    0,   90,
    0,   46,    0,    0,   46,    0,   42,   42,   42,   42,
    0,    0,    0,   78,   79,   80,   81,   84,   85,   46,
   46,   46,   46,    0,    0,   45,    0,    0,   45,   44,
    0,    0,   44,    0,    0,    0,    0,    0,    0,    0,
   42,    0,    0,   45,   45,   45,   45,   44,   44,   44,
   44,    0,    0,   46,    0,    0,    0,    0,   66,   67,
    0,   69,   70,   71,   72,   74,    0,    0,    0,    0,
    0,   55,   55,   55,   55,   55,   55,   45,   96,    0,
    0,   44,    0,    0,    0,  103,  104,    0,  106,  107,
  108,  109,  110,  111,  112,  113,  114,  115,  116,  117,
  118,    0,  120,    0,   96,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   78,   79,
   80,   81,   84,   85,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  132,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  138,    0,    0,    0,    0,
    0,    0,    0,   78,   79,   80,   81,   84,   85,    0,
    0,    0,    0,    0,    0,    0,    0,   80,   81,   84,
   85,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   42,   42,   42,   42,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   46,   46,   46,
   46,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   45,   45,   45,   45,   44,   44,   44,   44,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   42,   43,  125,   45,   46,   47,   40,   58,   41,   64,
  264,   44,   41,   33,   42,   44,   25,   40,   46,   47,
   40,   46,   91,  264,   33,  264,   59,   33,   61,   38,
   59,   58,   61,  123,   40,   42,   43,   58,   45,   46,
   47,   33,   41,   41,   41,   44,   44,   44,   40,   91,
   41,   41,   41,   44,   44,   33,  263,  264,  265,   44,
   93,  275,   40,   91,   93,   59,   91,   93,   77,   33,
   27,   28,  123,   59,  263,   59,   40,  123,  133,  134,
   40,   40,   40,   33,   91,   60,   40,  264,  143,   62,
   40,  125,   59,  123,   40,    0,  272,   33,   41,  123,
   41,   33,    2,   -1,   40,  125,  123,   46,   40,   -1,
   -1,   94,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,
   -1,   -1,   -1,   59,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  125,   41,   42,   43,   44,   45,   46,
   47,  264,   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,  125,   41,   42,   43,   44,   45,   -1,   47,   -1,
   41,   42,   43,   44,   45,  125,   47,   -1,   -1,   -1,
   59,   60,   61,   62,   91,   -1,   93,   -1,   59,   60,
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
   -1,   -1,  264,   -1,   -1,   -1,   -1,   -1,  274,  275,
  276,  277,  274,  275,  276,  277,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   41,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,  257,  258,
  259,  260,  261,  262,   -1,   -1,  257,  258,  259,  260,
  261,  262,   41,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   41,   42,   43,   -1,   45,   46,
   47,   41,   -1,   43,   44,   45,   -1,   41,   -1,   43,
   44,   45,   91,   60,   41,   62,   -1,   44,   -1,   59,
   60,   61,   62,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   -1,   59,   60,   61,   62,   41,   -1,   -1,   44,
   -1,   -1,   41,   -1,   91,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   59,   60,   61,   62,   -1,   93,
   59,   60,   61,   62,   42,   43,   93,   45,   46,   47,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   60,   61,   62,   -1,   -1,   -1,   93,   59,
   60,   -1,   62,   -1,   93,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   91,   -1,   -1,   59,   60,   -1,   62,
   -1,   91,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,  257,  258,  259,  260,  261,  262,   91,   -1,
   -1,   -1,   59,   60,   91,   62,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   59,   60,   -1,   62,   -1,  257,  258,
  259,  260,  261,  262,   91,   60,   61,   62,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   60,   -1,   62,   -1,
  257,  258,  259,  260,  261,  262,   91,  257,  258,  259,
  260,  261,  262,  257,  258,  259,  260,  261,  262,   -1,
  257,  258,  259,  260,  261,  262,   -1,   91,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,  257,  258,  259,  260,  261,  262,  257,  258,
  259,  260,  261,  262,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,  257,  258,  259,
  260,  261,  262,   42,   43,   91,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   -1,   62,  257,  258,  259,  260,  261,  262,
  257,  258,  259,  260,  261,  262,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   42,   43,   91,   45,   46,   47,   60,   -1,   62,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,   60,   -1,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   91,   41,   -1,
   -1,   44,  257,  258,  259,  260,  261,  262,   -1,   91,
   -1,   41,   -1,   -1,   44,   -1,   59,   60,   61,   62,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   59,
   60,   61,   62,   -1,   -1,   41,   -1,   -1,   44,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   59,   60,   61,   62,   59,   60,   61,
   62,   -1,   -1,   93,   -1,   -1,   -1,   -1,   48,   49,
   -1,   51,   52,   53,   54,   55,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   93,   68,   -1,
   -1,   93,   -1,   -1,   -1,   75,   76,   -1,   78,   79,
   80,   81,   82,   83,   84,   85,   86,   87,   88,   89,
   90,   -1,   92,   -1,   94,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  124,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  135,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,  260,  261,
  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  257,  258,  259,  260,
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
"camposStruct : camposStruct campoStruct",
"campoStruct : param ';'",
"funParams : param",
"funParams : funParams ',' param",
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

//#line 133 "sintac.y"
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
//#line 546 "Parser.java"
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
case 1:
//#line 22 "sintac.y"
{ raiz = new Programa((List<Definicion>)val_peek(0));}
break;
case 2:
//#line 27 "sintac.y"
{ yyval = new ArrayList<Definicion>();}
break;
case 3:
//#line 28 "sintac.y"
{ yyval = val_peek(1); ((ArrayList<Definicion>)yyval).add((Definicion)val_peek(0));}
break;
case 4:
//#line 31 "sintac.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 32 "sintac.y"
{yyval = val_peek(0);}
break;
case 6:
//#line 33 "sintac.y"
{yyval = val_peek(0);}
break;
case 7:
//#line 36 "sintac.y"
{ yyval = new ArrayList<DefVariable>();}
break;
case 8:
//#line 37 "sintac.y"
{yyval = val_peek(1); ((ArrayList<DefVariable>)yyval).add((DefVariable)val_peek(0));}
break;
case 9:
//#line 40 "sintac.y"
{yyval = new DefVariable(val_peek(3), val_peek(1)); }
break;
case 10:
//#line 43 "sintac.y"
{yyval = new DefFuncion(val_peek(9), val_peek(7), val_peek(4), val_peek(2), val_peek(1));}
break;
case 11:
//#line 44 "sintac.y"
{yyval = new DefFuncion(val_peek(7), val_peek(5), new TipoVoid(), val_peek(2),val_peek(1));}
break;
case 12:
//#line 47 "sintac.y"
{yyval = new DefStruct(val_peek(4),val_peek(2));}
break;
case 13:
//#line 54 "sintac.y"
{yyval = new Parametro(val_peek(2), val_peek(0));}
break;
case 14:
//#line 57 "sintac.y"
{yyval = new ArrayList<Parametro>();}
break;
case 15:
//#line 58 "sintac.y"
{yyval = val_peek(1); ((ArrayList<Parametro>)yyval).add((Parametro)val_peek(0));}
break;
case 16:
//#line 60 "sintac.y"
{yyval = val_peek(1);}
break;
case 17:
//#line 64 "sintac.y"
{yyval=new ArrayList<Parametro>(); ((ArrayList<Parametro>)yyval).add((Parametro)val_peek(0));}
break;
case 18:
//#line 65 "sintac.y"
{yyval=val_peek(2); ((ArrayList<Parametro>)val_peek(2)).add((Parametro)val_peek(0)); }
break;
case 19:
//#line 67 "sintac.y"
{yyval = new ArrayList<Parametro>();}
break;
case 20:
//#line 68 "sintac.y"
{yyval = val_peek(0);}
break;
case 21:
//#line 75 "sintac.y"
{yyval = new ArrayList<Sentencia>();}
break;
case 22:
//#line 76 "sintac.y"
{yyval = val_peek(1); ((ArrayList<Sentencia>)yyval).add((Sentencia)val_peek(0));}
break;
case 23:
//#line 79 "sintac.y"
{yyval = new Read(val_peek(1)) ;}
break;
case 24:
//#line 80 "sintac.y"
{yyval = new Print(val_peek(1));}
break;
case 25:
//#line 81 "sintac.y"
{yyval = new Printsp(val_peek(1));}
break;
case 26:
//#line 82 "sintac.y"
{yyval = new Println(val_peek(1));}
break;
case 27:
//#line 83 "sintac.y"
{yyval = new Return(val_peek(1));}
break;
case 28:
//#line 84 "sintac.y"
{yyval = new Return(null);}
break;
case 29:
//#line 85 "sintac.y"
{yyval = new If(val_peek(4),val_peek(1),null);}
break;
case 30:
//#line 86 "sintac.y"
{yyval = new If(val_peek(8),val_peek(5),val_peek(1));}
break;
case 31:
//#line 87 "sintac.y"
{yyval = new While(val_peek(4),val_peek(1));}
break;
case 32:
//#line 88 "sintac.y"
{yyval = new Asignacion(val_peek(3),val_peek(1));}
break;
case 33:
//#line 89 "sintac.y"
{yyval = new LlamadaFuncion(val_peek(4),val_peek(2));}
break;
case 34:
//#line 93 "sintac.y"
{yyval = new LiteralReal(val_peek(0));}
break;
case 35:
//#line 94 "sintac.y"
{yyval = new LiteralInt(val_peek(0));}
break;
case 36:
//#line 95 "sintac.y"
{yyval = new Variable(val_peek(0));}
break;
case 37:
//#line 96 "sintac.y"
{yyval = new Caracter(val_peek(0));}
break;
case 38:
//#line 97 "sintac.y"
{yyval = new ExprAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 39:
//#line 98 "sintac.y"
{yyval = new ExprAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 40:
//#line 99 "sintac.y"
{yyval = new ExprAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 41:
//#line 100 "sintac.y"
{yyval = new ExprAritmetica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 42:
//#line 101 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 43:
//#line 102 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 44:
//#line 103 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 45:
//#line 104 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 46:
//#line 105 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 47:
//#line 106 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 48:
//#line 107 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 49:
//#line 108 "sintac.y"
{yyval = new ExprLogica(val_peek(2),val_peek(1),val_peek(0));}
break;
case 50:
//#line 109 "sintac.y"
{yyval = new Negacion(val_peek(0));}
break;
case 51:
//#line 110 "sintac.y"
{yyval = val_peek(1);}
break;
case 52:
//#line 111 "sintac.y"
{yyval = new Cast(val_peek(4),val_peek(1));}
break;
case 53:
//#line 112 "sintac.y"
{yyval = val_peek(3);}
break;
case 54:
//#line 113 "sintac.y"
{yyval = val_peek(2);}
break;
case 55:
//#line 114 "sintac.y"
{yyval = new LlamadaFuncion(val_peek(3),val_peek(1));}
break;
case 56:
//#line 117 "sintac.y"
{yyval = new ArrayList<Expresion>(); ((ArrayList<Expresion>)yyval).add((Expresion)val_peek(0));}
break;
case 57:
//#line 118 "sintac.y"
{yyval = val_peek(2); ((ArrayList<Expresion>)yyval).add((Expresion)val_peek(0));}
break;
case 59:
//#line 122 "sintac.y"
{yyval = new TipoInt();}
break;
case 60:
//#line 123 "sintac.y"
{yyval = new TipoReal();}
break;
case 61:
//#line 124 "sintac.y"
{yyval = new TipoChar();}
break;
case 62:
//#line 125 "sintac.y"
{yyval = new TipoStruct(val_peek(0));}
break;
case 63:
//#line 126 "sintac.y"
{yyval = new TipoArray(val_peek(2), val_peek(0));}
break;
//#line 942 "Parser.java"
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
