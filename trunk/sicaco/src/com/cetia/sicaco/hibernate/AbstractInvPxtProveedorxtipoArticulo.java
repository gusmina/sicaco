package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractInvClaClasificado entity provides the base persistence definition of
 * the InvClaClasificado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvPxtProveedorxtipoArticulo implements java.io.Serializable {

	// Fields

	private Integer pxtId;
	private InvProProveedor invProProveedor = new InvProProveedor();
	private InvTarTipoArticulo invTarTipoArticulo = new InvTarTipoArticulo();

	// Constructors

	/** default constructor */
	public AbstractInvPxtProveedorxtipoArticulo() {
	}

	/** full constructor */
	public AbstractInvPxtProveedorxtipoArticulo(Integer pxtId,
			InvProProveedor invProProveedor,
			InvTarTipoArticulo invTarTipoArticulo) {
		super();
		this.pxtId = pxtId;
		this.invProProveedor = invProProveedor;
		this.invTarTipoArticulo = invTarTipoArticulo;
	}
	
	// Property accessors

	public Integer getPxtId() {
		return pxtId;
	}

	public void setPxtId(Integer pxtId) {
		this.pxtId = pxtId;
	}

	public InvProProveedor getInvProProveedor() {
		return invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public InvTarTipoArticulo getInvTarTipoArticulo() {
		return invTarTipoArticulo;
	}

	public void setInvTarTipoArticulo(InvTarTipoArticulo invTarTipoArticulo) {
		this.invTarTipoArticulo = invTarTipoArticulo;
	}

}