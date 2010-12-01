package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * SecDppDepartamentoPais entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecDppDepartamentoPais extends AbstractSecDppDepartamentoPais
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecDppDepartamentoPais() {
	}

	/** minimal constructor */
	public SecDppDepartamentoPais(Integer dppId, String dppNombre) {
		super(dppId, dppNombre);
	}

	/** full constructor */
	public SecDppDepartamentoPais(Integer dppId, String dppNombre,
			String dppDescripcion, Set secPerPersonas) {
		super(dppId, dppNombre, dppDescripcion, secPerPersonas);
	}

}
