package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtrCckControlCheques entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrCckControlCheques extends AbstractCtrCckControlCheques
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrCckControlCheques() {
	}

	/** minimal constructor */
	public CtrCckControlCheques(Integer cckId, String cckSerie,
			Integer cckCorrIni, Integer cckCorrFin, String audUsuarioCreacion,
			Date audFechaCreacion) {
		super(cckId, cckSerie, cckCorrIni, cckCorrFin, audUsuarioCreacion,
				audFechaCreacion);
	}

	/** full constructor */
	public CtrCckControlCheques(Integer cckId, CtrBanBanco ctrBanBanco,
			String cckSerie, Integer cckCorrIni, Integer cckCorrFin,
			Integer cckDigitos,
			String audUsuarioCreacion, Date audFechaCreacion,
			String audUsuarioModificacion, Date audFechaModificacion,
			Set ctrRckRepositorioChequeses) {
		super(cckId, ctrBanBanco, cckSerie, cckCorrIni, cckCorrFin,
				cckDigitos,
				audUsuarioCreacion, audFechaCreacion, audUsuarioModificacion,
				audFechaModificacion, ctrRckRepositorioChequeses);
	}

}
