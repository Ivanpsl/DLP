/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	variable:expresion -> lexema:String

public class Variable extends AbstractExpresion {

	public Variable(String lexema) {
		this.lexema = lexema;
	}

	public Variable(Object lexema) {
		this.lexema = (lexema instanceof Token) ? ((Token)lexema).getLexeme() : (String) lexema;

		searchForPositions(lexema);	// Obtener linea/columna a partir de los hijos
	}

	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String lexema;
}

