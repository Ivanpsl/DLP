/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	defFuncion:definicion -> nombre:String  param:parametro*  retorno:tipo  vars:defVariable*  sentencia:sentencia*

public class DefFuncion extends AbstractDefinicion {

	public DefFuncion(String nombre, List<Parametro> param, Tipo retorno, List<DefVariable> vars, List<Sentencia> sentencia) {
		this.nombre = nombre;
		this.param = param;
		this.retorno = retorno;
		this.vars = vars;
		this.sentencia = sentencia;

		searchForPositions(param, retorno, vars, sentencia);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefFuncion(Object nombre, Object param, Object retorno, Object vars, Object sentencia) {
		this.nombre = (nombre instanceof Token) ? ((Token)nombre).getLexeme() : (String) nombre;
		this.param = (List<Parametro>) param;
		this.retorno = (Tipo) retorno;
		this.vars = (List<DefVariable>) vars;
		this.sentencia = (List<Sentencia>) sentencia;

		searchForPositions(nombre, param, retorno, vars, sentencia);	// Obtener linea/columna a partir de los hijos
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Parametro> getParam() {
		return param;
	}
	public void setParam(List<Parametro> param) {
		this.param = param;
	}

	public Tipo getRetorno() {
		return retorno;
	}
	public void setRetorno(Tipo retorno) {
		this.retorno = retorno;
	}

	public List<DefVariable> getVars() {
		return vars;
	}
	public void setVars(List<DefVariable> vars) {
		this.vars = vars;
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
	private List<Parametro> param;
	private Tipo retorno;
	private List<DefVariable> vars;
	private List<Sentencia> sentencia;
}

