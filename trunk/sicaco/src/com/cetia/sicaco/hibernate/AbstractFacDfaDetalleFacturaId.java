package com.cetia.sicaco.hibernate;

/**
 * AbstractFacDfaDetalleFacturaId entity provides the base persistence
 * definition of the FacDfaDetalleFacturaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFacDfaDetalleFacturaId implements
		java.io.Serializable {

	// Fields

	private Integer dfaId;
	private InvArtArticulo invArtArticulo = new InvArtArticulo();

	// Constructors

	/** default constructor */
	public AbstractFacDfaDetalleFacturaId() {
	}

	/** full constructor */
	public AbstractFacDfaDetalleFacturaId(Integer dfaId,
			InvArtArticulo invArtArticulo) {
		this.dfaId = dfaId;
		this.invArtArticulo = invArtArticulo;
	}

	// Property accessors

	public Integer getDfaId() {
		return this.dfaId;
	}

	public void setDfaId(Integer dfaId) {
		this.dfaId = dfaId;
	}

	public InvArtArticulo getInvArtArticulo() {
		return this.invArtArticulo;
	}

	public void setInvArtArticulo(InvArtArticulo invArtArticulo) {
		this.invArtArticulo = invArtArticulo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractFacDfaDetalleFacturaId))
			return false;
		AbstractFacDfaDetalleFacturaId castOther = (AbstractFacDfaDetalleFacturaId) other;

		return ((this.getDfaId() == castOther.getDfaId()) || (this.getDfaId() != null
				&& castOther.getDfaId() != null && this.getDfaId().equals(
				castOther.getDfaId())))
				&& ((this.getInvArtArticulo() == castOther.getInvArtArticulo()) || (this
						.getInvArtArticulo() != null
						&& castOther.getInvArtArticulo() != null && this
						.getInvArtArticulo().equals(
								castOther.getInvArtArticulo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDfaId() == null ? 0 : this.getDfaId().hashCode());
		result = 37
				* result
				+ (getInvArtArticulo() == null ? 0 : this.getInvArtArticulo()
						.hashCode());
		return result;
	}

}