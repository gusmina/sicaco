package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * CtrTusTipoUso entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrTusTipoUso extends AbstractCtrTusTipoUso implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrTusTipoUso() {
	}

	/** minimal constructor */
	public CtrTusTipoUso(String tusCodigo, String tusNombre) {
		super(tusCodigo, tusNombre);
	}

	/** full constructor */
	public CtrTusTipoUso(String tusCodigo, String tusNombre,
			String tusComentario, Set ctrEstEstados) {
		super(tusCodigo, tusNombre, tusComentario, ctrEstEstados);
	}

}
