/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	while:sentencia -> condicion:expresion  contenido:sentencia*

public class While extends AbstractSentencia {

	public While(Expresion condicion, List<Sentencia> contenido) {
		this.condicion = condicion;
		this.contenido = contenido;

		searchForPositions(condicion, contenido);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public While(Object condicion, Object contenido) {
		this.condicion = (Expresion) condicion;
		this.contenido = (List<Sentencia>) contenido;

		searchForPositions(condicion, contenido);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getCondicion() {
		return condicion;
	}
	public void setCondicion(Expresion condicion) {
		this.condicion = condicion;
	}

	public List<Sentencia> getContenido() {
		return contenido;
	}
	public void setContenido(List<Sentencia> contenido) {
		this.contenido = contenido;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion condicion;
	private List<Sentencia> contenido;
}

