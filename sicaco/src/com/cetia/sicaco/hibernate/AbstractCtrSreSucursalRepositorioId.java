package com.cetia.sicaco.hibernate;

/**
 * AbstractCtrSreSucursalRepositorioId entity provides the base persistence
 * definition of the CtrSreSucursalRepositorioId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrSreSucursalRepositorioId implements
		java.io.Serializable {

	// Fields

	private CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas;
	private SecSucSucursal secSucSucursal;

	// Constructors

	/** default constructor */
	public AbstractCtrSreSucursalRepositorioId() {
	}

	/** full constructor */
	public AbstractCtrSreSucursalRepositorioId(
			CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas,
			SecSucSucursal secSucSucursal) {
		this.ctrRfcRepositorioFacturas = ctrRfcRepositorioFacturas;
		this.secSucSucursal = secSucSucursal;
	}

	// Property accessors

	public CtrRfcRepositorioFacturas getCtrRfcRepositorioFacturas() {
		return this.ctrRfcRepositorioFacturas;
	}

	public void setCtrRfcRepositorioFacturas(
			CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas) {
		this.ctrRfcRepositorioFacturas = ctrRfcRepositorioFacturas;
	}

	public SecSucSucursal getSecSucSucursal() {
		return this.secSucSucursal;
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		this.secSucSucursal = secSucSucursal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractCtrSreSucursalRepositorioId))
			return false;
		AbstractCtrSreSucursalRepositorioId castOther = (AbstractCtrSreSucursalRepositorioId) other;

		return ((this.getCtrRfcRepositorioFacturas() == castOther
				.getCtrRfcRepositorioFacturas()) || (this
				.getCtrRfcRepositorioFacturas() != null
				&& castOther.getCtrRfcRepositorioFacturas() != null && this
				.getCtrRfcRepositorioFacturas().equals(
						castOther.getCtrRfcRepositorioFacturas())))
				&& ((this.getSecSucSucursal() == castOther.getSecSucSucursal()) || (this
						.getSecSucSucursal() != null
						&& castOther.getSecSucSucursal() != null && this
						.getSecSucSucursal().equals(
								castOther.getSecSucSucursal())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCtrRfcRepositorioFacturas() == null ? 0 : this
						.getCtrRfcRepositorioFacturas().hashCode());
		result = 37
				* result
				+ (getSecSucSucursal() == null ? 0 : this.getSecSucSucursal()
						.hashCode());
		return result;
	}

}