package com.cetia.sicaco.hibernate;

/**
 * ConEreEstadoResultados entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConEreEstadoResultados extends AbstractConEreEstadoResultados
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ConEreEstadoResultados() {
	}

	/** minimal constructor */
	public ConEreEstadoResultados(String cueNombre) {
		super(cueNombre);
	}

	/** full constructor */
	public ConEreEstadoResultados(String cueNombre, Double banda1,
			Double banda2, Integer subrayado) {
		super(cueNombre, banda1, banda2, subrayado);
	}

}
