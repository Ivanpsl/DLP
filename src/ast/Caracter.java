/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	caracter:expresion -> string:String

public class Caracter extends AbstractExpresion {

	public Caracter(String string) {
		this.string = string;
	}

	public Caracter(Object string) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;

		searchForPositions(string);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
}

