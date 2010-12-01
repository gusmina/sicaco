package com.cetia.sicaco.hibernate;

/**
 * AbstractCtrSreSucursalRepositorio entity provides the base persistence
 * definition of the CtrSreSucursalRepositorio entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrSreSucursalRepositorio implements
		java.io.Serializable {

	// Fields

	private CtrSreSucursalRepositorioId id;

	// Constructors

	/** default constructor */
	public AbstractCtrSreSucursalRepositorio() {
	}

	/** full constructor */
	public AbstractCtrSreSucursalRepositorio(CtrSreSucursalRepositorioId id) {
		this.id = id;
	}

	// Property accessors

	public CtrSreSucursalRepositorioId getId() {
		return this.id;
	}

	public void setId(CtrSreSucursalRepositorioId id) {
		this.id = id;
	}

}