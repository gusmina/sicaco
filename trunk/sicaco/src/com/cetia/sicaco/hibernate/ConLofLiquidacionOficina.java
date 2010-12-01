package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * ConLofLiquidacionOficina entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConLofLiquidacionOficina extends AbstractConLofLiquidacionOficina
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConLofLiquidacionOficina() {
	}

	/** full constructor */
	public ConLofLiquidacionOficina(Integer cueId, String cueNombre,
			Double valorDepositado, Date fecha) {
		super(cueId, cueNombre, valorDepositado, fecha);
	}

}
