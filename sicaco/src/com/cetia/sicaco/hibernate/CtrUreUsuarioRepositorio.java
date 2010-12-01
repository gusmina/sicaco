package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtrUreUsuarioRepositorio entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrUreUsuarioRepositorio extends AbstractCtrUreUsuarioRepositorio
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrUreUsuarioRepositorio() {
	}

	/** minimal constructor */
	public CtrUreUsuarioRepositorio(Integer ureId, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(ureId, audUsuarioCreacion, audFechaCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public CtrUreUsuarioRepositorio(Integer ureId,
			CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas,
			SecPerPersona secPerPersona, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(ureId, ctrRfcRepositorioFacturas, secPerPersona,
				audUsuarioCreacion, audFechaCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

}
