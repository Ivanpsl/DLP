/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	literalInt:expresion -> lexema:String

public class LiteralInt extends AbstractExpresion {

	public LiteralInt(String lexema) {
		this.lexema = lexema;
	}

	public LiteralInt(Object lexema) {
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

