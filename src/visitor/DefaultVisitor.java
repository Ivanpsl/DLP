/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;
import java.util.*;

/*
DefaultVisitor. Implementación base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementación por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Programa { List<Definicion> definicion; }
	public Object visit(Programa node, Object param) {
		visitChildren(node.getDefinicion(), param);
		return null;
	}

	//	class DefVariable { Tipo tipo;  String nombre; }
	public Object visit(DefVariable node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class DefFuncion { String nombre;  Tipo retorno;  List<Sentencia> sentencia; }
	public Object visit(DefFuncion node, Object param) {
		if (node.getRetorno() != null)
			node.getRetorno().accept(this, param);
		visitChildren(node.getSentencia(), param);
		return null;
	}

	//	class DefStruct { String nombre;  List<DefVariable> campos; }
	public Object visit(DefStruct node, Object param) {
		visitChildren(node.getCampos(), param);
		return null;
	}

	//	class TipoInt {  }
	public Object visit(TipoInt node, Object param) {
		return null;
	}

	//	class TipoReal {  }
	public Object visit(TipoReal node, Object param) {
		return null;
	}

	//	class TipoArray { String numero;  Tipo tipo; }
	public Object visit(TipoArray node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class TipoChar {  }
	public Object visit(TipoChar node, Object param) {
		return null;
	}

	//	class TipoStruct { String nombre; }
	public Object visit(TipoStruct node, Object param) {
		return null;
	}

	//	class Read { Expresion expresion; }
	public Object visit(Read node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Print { Expresion expresion; }
	public Object visit(Print node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Printsp { Expresion expresion; }
	public Object visit(Printsp node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Println { Expresion expresion; }
	public Object visit(Println node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Return { Expresion expresion; }
	public Object visit(Return node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class If { Expresion condicion;  List<Sentencia> cierto;  List<Sentencia> falso; }
	public Object visit(If node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getCierto(), param);
		visitChildren(node.getFalso(), param);
		return null;
	}

	//	class While { Expresion condicion;  List<Sentencia> contenido; }
	public Object visit(While node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getContenido(), param);
		return null;
	}

	//	class Asignacion { Expresion left;  Expresion right; }
	public Object visit(Asignacion node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class ExprAritmetica { Expresion left;  String operador;  Expresion right; }
	public Object visit(ExprAritmetica node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class ExprLogica { Expresion left;  String operador;  Expresion right; }
	public Object visit(ExprLogica node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class Negacion { Expresion expresion; }
	public Object visit(Negacion node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Cast { Tipo tipo;  Expresion expresion; }
	public Object visit(Cast node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class LiteralInt { String lexema; }
	public Object visit(LiteralInt node, Object param) {
		return null;
	}

	//	class LiteralReal { String lexema; }
	public Object visit(LiteralReal node, Object param) {
		return null;
	}

	//	class Caracter { String string; }
	public Object visit(Caracter node, Object param) {
		return null;
	}

	//	class Variable { String lexema; }
	public Object visit(Variable node, Object param) {
		return null;
	}

	//	class LlamadaFuncion { String nombre;  List<Expresion> parametros; }
	public Object visit(LlamadaFuncion node, Object param) {
		visitChildren(node.getParametros(), param);
		return null;
	}
	
	// Método auxiliar -----------------------------
	protected void visitChildren(List<? extends AST> children, Object param) {
		if (children != null)
			for (AST child : children)
				child.accept(this, param);
	}
}
