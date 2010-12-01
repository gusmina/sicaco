package com.cetia.sicaco.hibernate;

/**
 * PartidaContable entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class PartidaContable extends AbstractPartidaContable implements
		java.io.Serializable {

	public PartidaContable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartidaContable(Integer cuentaId, String codigoCuenta,
			String nombreCuenta, double parcial, double debe, double haber,
			long pcoId) {
		super(cuentaId, codigoCuenta, nombreCuenta, parcial, debe, haber, pcoId);
		// TODO Auto-generated constructor stub
	}

	
}
