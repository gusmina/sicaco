package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * ConTicTipoCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConTicTipoCuenta extends AbstractConTicTipoCuenta implements
		java.io.Serializable {

	public ConTicTipoCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConTicTipoCuenta(String ticNombre, Integer ticAcreeDeudo,
			Set conCueCuentas) {
		super(ticNombre, ticAcreeDeudo, conCueCuentas);
		// TODO Auto-generated constructor stub
	}

	public ConTicTipoCuenta(String ticNombre, Integer ticAcreeDeudo) {
		super(ticNombre, ticAcreeDeudo);
		// TODO Auto-generated constructor stub
	}

	

}
