package com.cetia.sicaco.hibernate;

/**
 * SecTelTelefonoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecTelTelefonoId extends AbstractSecTelTelefonoId implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -8710714066730713571L;

	/** default constructor */
	public SecTelTelefonoId() {
	}

	/** full constructor */
	public SecTelTelefonoId(String telTelefono, SecPerPersona secPerPersona) {
		super(telTelefono, secPerPersona);
	}

}
