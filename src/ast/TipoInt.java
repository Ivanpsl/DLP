/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	tipoInt:tipo -> 

public class TipoInt extends AbstractTipo {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

