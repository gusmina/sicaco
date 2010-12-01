package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtaTntTipoNota entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTntTipoNota extends AbstractCtaTntTipoNota implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaTntTipoNota() {
	}

	/** minimal constructor */
	public CtaTntTipoNota(Integer tntId, String tntNombre) {
		super(tntId, tntNombre);
	}

	/** full constructor */
	public CtaTntTipoNota(Integer tntId, String tntNombre,
			String tntDescripcion, Set ctaNotNotases, Set ctaTisTipoSeguros) {
		super(tntId, tntNombre, tntDescripcion, ctaNotNotases, ctaTisTipoSeguros);
		// TODO Auto-generated constructor stub
	}
	
}
