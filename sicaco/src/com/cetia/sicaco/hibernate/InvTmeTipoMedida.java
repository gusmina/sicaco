package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * InvTmeTipoMedida entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvTmeTipoMedida extends AbstractInvTmeTipoMedida implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvTmeTipoMedida() {
	}

	/** minimal constructor */
	public InvTmeTipoMedida(Integer tmeId, String tmeNombre) {
		super(tmeId, tmeNombre);
	}

	/** full constructor */
	public InvTmeTipoMedida(Integer tmeId, String tmeNombre,
			String tmeDescripcion, Set invMedMedidas) {
		super(tmeId, tmeNombre, tmeDescripcion, invMedMedidas);
	}

}
