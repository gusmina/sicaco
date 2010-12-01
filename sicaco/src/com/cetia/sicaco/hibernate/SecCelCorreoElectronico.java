package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * SecCelCorreoElectronico entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecCelCorreoElectronico extends AbstractSecCelCorreoElectronico
		implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -6672083830526747093L;

	/** default constructor */
	public SecCelCorreoElectronico() {
	}
	
	public SecCelCorreoElectronico(SecCelCorreoElectronicoId secCelCorreoElectronicoId) {
			setId(secCelCorreoElectronicoId);
	}
	

	/** minimal constructor */
	public SecCelCorreoElectronico(SecCelCorreoElectronicoId id,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(id, audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public SecCelCorreoElectronico(SecCelCorreoElectronicoId id,
			String celPrincipal, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(id, celPrincipal, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

}
