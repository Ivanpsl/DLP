/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	defFuncion:definicion -> nombre:String  retorno:tipo  sentencia:sentencia*

public class DefFuncion extends AbstractDefinicion {

	public DefFuncion(String nombre, Tipo retorno, List<Sentencia> sentencia) {
		this.nombre = nombre;
		this.retorno = retorno;
		this.sentencia = sentencia;

		searchForPositions(retorno, sentencia);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefFuncion(Object nombre, Object retorno, Object sentencia) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getLexeme() : (String) nombre;
		this.retorno = (Tipo) retorno;
		this.sentencia = (List<Sentencia>) sentencia;

		searchForPositions(nombre, retorno, sentencia);	// Obtener linea/columna a partir de los hijos
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getRetorno() {
		return retorno;
	}
	public void setRetorno(Tipo retorno) {
		this.retorno = retorno;
	}

	public List<Sentencia> getSentencia() {
		return sentencia;
	}
	public void setSentencia(List<Sentencia> sentencia) {
		this.sentencia = sentencia;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String nombre;
	private Tipo retorno;
	private List<Sentencia> sentencia;
}

