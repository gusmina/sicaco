package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaLahLineaAhorro entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaLahLineaAhorro extends AbstractCtaLahLineaAhorro implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaLahLineaAhorro() {
	}

	/** minimal constructor */
	public CtaLahLineaAhorro(Integer lahId, String lahNombre, Date lahDesde) {
		super(lahId, lahNombre, lahDesde);
	}

	/** full constructor */
	public CtaLahLineaAhorro(Integer lahId, String lahNombre,
			String lahDescripcion, Date lahDesde, Date lahHasta,
			Set ctaTahTipoAhorros) {
		super(lahId, lahNombre, lahDescripcion, lahDesde, lahHasta,
				ctaTahTipoAhorros);
	}

}
