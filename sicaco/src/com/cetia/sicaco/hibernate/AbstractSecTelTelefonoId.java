package com.cetia.sicaco.hibernate;

/**
 * AbstractSecTelTelefonoId entity provides the base persistence definition of
 * the SecTelTelefonoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTelTelefonoId implements java.io.Serializable {

	// Fields

	private String telTelefono;
	private SecPerPersona secPerPersona = new SecPerPersona();

	// Constructors

	/** default constructor */
	public AbstractSecTelTelefonoId() {
	}

	/** full constructor */
	public AbstractSecTelTelefonoId(String telTelefono,
			SecPerPersona secPerPersona) {
		this.telTelefono = telTelefono;
		this.secPerPersona = secPerPersona;
	}

	// Property accessors

	public String getTelTelefono() {
		return this.telTelefono;
	}

	public void setTelTelefono(String telTelefono) {
		this.telTelefono = telTelefono;
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
		if (!(other instanceof AbstractSecTelTelefonoId))
			return false;
		AbstractSecTelTelefonoId castOther = (AbstractSecTelTelefonoId) other;

		return ((this.getTelTelefono() == castOther.getTelTelefono()) || (this
				.getTelTelefono() != null
				&& castOther.getTelTelefono() != null && this.getTelTelefono()
				.equals(castOther.getTelTelefono())))
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
				+ (getTelTelefono() == null ? 0 : this.getTelTelefono()
						.hashCode());
		result = 37
				* result
				+ (getSecPerPersona() == null ? 0 : this.getSecPerPersona()
						.hashCode());
		return result;
	}

}