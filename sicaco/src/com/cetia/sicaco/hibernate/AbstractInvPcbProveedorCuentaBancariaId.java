package com.cetia.sicaco.hibernate;

/**
 * AbstractInvPcbProveedorCuentaBancariaId entity provides the base persistence
 * definition of the InvPcbProveedorCuentaBancariaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvPcbProveedorCuentaBancariaId implements
		java.io.Serializable {

	// Fields

	private InvProProveedor invProProveedor = new InvProProveedor();
	private CtrBanBanco ctrBanBanco = new CtrBanBanco();
	private String pcbNumeroCuenta;

	// Constructors

	/** default constructor */
	public AbstractInvPcbProveedorCuentaBancariaId() {
	}

	/** full constructor */
	public AbstractInvPcbProveedorCuentaBancariaId(
			InvProProveedor invProProveedor, CtrBanBanco ctrBanBanco,
			String pcbNumeroCuenta) {
		this.invProProveedor = invProProveedor;
		this.ctrBanBanco = ctrBanBanco;
		this.pcbNumeroCuenta = pcbNumeroCuenta;
	}

	// Property accessors

	public InvProProveedor getInvProProveedor() {
		return this.invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public CtrBanBanco getCtrBanBanco() {
		return this.ctrBanBanco;
	}

	public void setCtrBanBanco(CtrBanBanco ctrBanBanco) {
		this.ctrBanBanco = ctrBanBanco;
	}

	public String getPcbNumeroCuenta() {
		return this.pcbNumeroCuenta;
	}

	public void setPcbNumeroCuenta(String pcbNumeroCuenta) {
		this.pcbNumeroCuenta = pcbNumeroCuenta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractInvPcbProveedorCuentaBancariaId))
			return false;
		AbstractInvPcbProveedorCuentaBancariaId castOther = (AbstractInvPcbProveedorCuentaBancariaId) other;

		return ((this.getInvProProveedor() == castOther.getInvProProveedor()) || (this
				.getInvProProveedor() != null
				&& castOther.getInvProProveedor() != null && this
				.getInvProProveedor().equals(castOther.getInvProProveedor())))
				&& ((this.getCtrBanBanco() == castOther.getCtrBanBanco()) || (this
						.getCtrBanBanco() != null
						&& castOther.getCtrBanBanco() != null && this
						.getCtrBanBanco().equals(castOther.getCtrBanBanco())))
				&& ((this.getPcbNumeroCuenta() == castOther
						.getPcbNumeroCuenta()) || (this.getPcbNumeroCuenta() != null
						&& castOther.getPcbNumeroCuenta() != null && this
						.getPcbNumeroCuenta().equals(
								castOther.getPcbNumeroCuenta())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInvProProveedor() == null ? 0 : this.getInvProProveedor()
						.hashCode());
		result = 37
				* result
				+ (getCtrBanBanco() == null ? 0 : this.getCtrBanBanco()
						.hashCode());
		result = 37
				* result
				+ (getPcbNumeroCuenta() == null ? 0 : this.getPcbNumeroCuenta()
						.hashCode());
		return result;
	}

}