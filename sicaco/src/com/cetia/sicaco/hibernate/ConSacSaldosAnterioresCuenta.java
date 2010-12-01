package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * ConSacSaldosAnterioresCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConSacSaldosAnterioresCuenta extends
		AbstractConSacSaldosAnterioresCuenta implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConSacSaldosAnterioresCuenta() {
	}

	/** full constructor */
	public ConSacSaldosAnterioresCuenta(Long sacId, ConCueCuenta conCueCuenta,
			Date sacFecha, double sacSaldoALaFecha , Double sacTotalDebe, Double sacTotalHaber) {
		super(conCueCuenta, sacFecha, sacSaldoALaFecha, sacTotalDebe, sacTotalHaber);
	}

}
