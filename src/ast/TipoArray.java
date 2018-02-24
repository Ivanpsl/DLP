/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	tipoArray:tipo -> numero:String  tipo:tipo

public class TipoArray extends AbstractTipo {

	public TipoArray(String numero, Tipo tipo) {
		this.numero = numero;
		this.tipo = tipo;

		searchForPositions(tipo);	// Obtener linea/columna a partir de los hijos
	}

	public TipoArray(Object numero, Object tipo) {
		this.numero = (numero instanceof Token) ? ((Token)numero).getLexeme() : (String) numero;
		this.tipo = (Tipo) tipo;

		searchForPositions(numero, tipo);	// Obtener linea/columna a partir de los hijos
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String numero;
	private Tipo tipo;
}

