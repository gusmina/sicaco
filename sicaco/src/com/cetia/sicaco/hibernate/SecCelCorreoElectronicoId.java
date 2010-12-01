package com.cetia.sicaco.hibernate;

/**
 * SecCelCorreoElectronicoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecCelCorreoElectronicoId extends
		AbstractSecCelCorreoElectronicoId implements java.io.Serializable {

	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5121700061560176236L;

	/** default constructor */
	public SecCelCorreoElectronicoId() {
	}

	/** full constructor */
	public SecCelCorreoElectronicoId(String celCorreoElectronico,
			SecPerPersona secPerPersona) {
		super(celCorreoElectronico, secPerPersona);
	}

}
