/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	tipoStruct:tipo -> nombre:String

public class TipoStruct extends AbstractTipo {

	public TipoStruct(String nombre) {
		this.nombre = nombre;
	}

	public TipoStruct(Object nombre) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getLexeme() : (String) nombre;

		searchForPositions(nombre);	// Obtener linea/columna a partir de los hijos
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
}

