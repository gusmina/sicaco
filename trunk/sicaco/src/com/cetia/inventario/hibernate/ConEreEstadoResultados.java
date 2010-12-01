package com.cetia.inventario.hibernate;

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

	/** full constructor */
	public ConEreEstadoResultados(String cueNombre, Double banda1, Double banda2) {
		super(cueNombre, banda1, banda2);
	}

}
