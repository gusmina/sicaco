package com.cetia.sicaco.hibernate;

/**
 * AbstractSecCelCorreoElectronicoId entity provides the base persistence
 * definition of the SecCelCorreoElectronicoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecCelCorreoElectronicoId implements
		java.io.Serializable {

	// Fields

	private String celCorreoElectronico = new String();
	private SecPerPersona secPerPersona = new SecPerPersona();

	// Constructors

	/** default constructor */
	public AbstractSecCelCorreoElectronicoId() {
	}

	/** full constructor */
	public AbstractSecCelCorreoElectronicoId(String celCorreoElectronico,
			SecPerPersona secPerPersona) {
		this.celCorreoElectronico = celCorreoElectronico;
		this.secPerPersona = secPerPersona;
	}

	// Property accessors

	public String getCelCorreoElectronico() {
		return this.celCorreoElectronico;
	}

	public void setCelCorreoElectronico(String celCorreoElectronico) {
		this.celCorreoElectronico = celCorreoElectronico;
	}

	public SecPerPersona getSecPerPersona() {
		return this.secPerPersona;
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		this.secPerPersona = secPerPersona;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractSecCelCorreoElectronicoId))
			return false;
		AbstractSecCelCorreoElectronicoId castOther = (AbstractSecCelCorreoElectronicoId) other;

		return ((this.getCelCorreoElectronico() == castOther
				.getCelCorreoElectronico()) || (this.getCelCorreoElectronico() != null
				&& castOther.getCelCorreoElectronico() != null && this
				.getCelCorreoElectronico().equals(
						castOther.getCelCorreoElectronico())))
				&& ((this.getSecPerPersona() == castOther.getSecPerPersona()) || (this
						.getSecPerPersona() != null
						&& castOther.getSecPerPersona() != null && this
						.getSecPerPersona()
						.equals(castOther.getSecPerPersona())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCelCorreoElectronico() == null ? 0 : this
						.getCelCorreoElectronico().hashCode());
		result = 37
				* result
				+ (getSecPerPersona() == null ? 0 : this.getSecPerPersona()
						.hashCode());
		return result;
	}

}