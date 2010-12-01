package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * SecTelTelefono entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecTelTelefono extends AbstractSecTelTelefono implements
		java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -5633139228398379546L;

	/** default constructor */
	public SecTelTelefono() {
	}

	public SecTelTelefono(SecTelTelefonoId id,
			SecTteTipoTelefono secTteTipoTelefono, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String telExt) {
		super(id, secTteTipoTelefono, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, telExt);
		// TODO Auto-generated constructor stub
	}

	/** full constructor */

}
