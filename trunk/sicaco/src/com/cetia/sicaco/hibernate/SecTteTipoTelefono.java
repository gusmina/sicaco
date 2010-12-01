package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * SecTteTipoTelefono entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecTteTipoTelefono extends AbstractSecTteTipoTelefono implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = 6839566975030592142L;

	/** default constructor */
	public SecTteTipoTelefono() {
	}

	/** minimal constructor */
	public SecTteTipoTelefono(Integer tteId) {
		super(tteId);
	}

	/** full constructor */
	public SecTteTipoTelefono(Integer tteId, String tteDescripcion,
			Set secTelTelefonos) {
		super(tteId, tteDescripcion, secTelTelefonos);
	}

}
