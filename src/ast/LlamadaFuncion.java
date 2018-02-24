/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	llamadaFuncion -> nombre:String  parametros:expresion*

public class LlamadaFuncion extends AbstractTraceable implements AST {

	public LlamadaFuncion(String nombre, List<Expresion> parametros) {
		this.nombre = nombre;
		this.parametros = parametros;

		searchForPositions(parametros);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public LlamadaFuncion(Object nombre, Object parametros) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getLexeme() : (String) nombre;
		this.parametros = (List<Expresion>) parametros;

		searchForPositions(nombre, parametros);	// Obtener linea/columna a partir de los hijos
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getParametros() {
		return parametros;
	}
	public void setParametros(List<Expresion> parametros) {
		this.parametros = parametros;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
	private List<Expresion> parametros;
}

