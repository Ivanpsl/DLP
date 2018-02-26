/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;

public interface Visitor {
	public Object visit(Programa node, Object param);
	public Object visit(DefVariable node, Object param);
	public Object visit(DefFuncion node, Object param);
	public Object visit(DefStruct node, Object param);
	public Object visit(TipoInt node, Object param);
	public Object visit(TipoReal node, Object param);
	public Object visit(TipoArray node, Object param);
	public Object visit(TipoChar node, Object param);
	public Object visit(TipoStruct node, Object param);
	public Object visit(TipoVoid node, Object param);
	public Object visit(Parametro node, Object param);
	public Object visit(Read node, Object param);
	public Object visit(Print node, Object param);
	public Object visit(Printsp node, Object param);
	public Object visit(Println node, Object param);
	public Object visit(Return node, Object param);
	public Object visit(If node, Object param);
	public Object visit(While node, Object param);
	public Object visit(Asignacion node, Object param);
	public Object visit(ExprAritmetica node, Object param);
	public Object visit(ExprLogica node, Object param);
	public Object visit(Negacion node, Object param);
	public Object visit(Cast node, Object param);
	public Object visit(LiteralInt node, Object param);
	public Object visit(LiteralReal node, Object param);
	public Object visit(Caracter node, Object param);
	public Object visit(Variable node, Object param);
	public Object visit(LlamadaFuncion node, Object param);
}
