package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtrPaiPais entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrPaiPais extends AbstractCtrPaiPais implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrPaiPais() {
	}

	/** minimal constructor */
	public CtrPaiPais(Integer paiId, CtrEstEstado ctrEstEstado,
			String paiNombre, String audUsuarioCreacion, Date audFechaCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(paiId, ctrEstEstado, paiNombre, audUsuarioCreacion,
				audFechaCreacion, audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public CtrPaiPais(Integer paiId, CtrEstEstado ctrEstEstado,
			String paiNombre, String audUsuarioCreacion, Date audFechaCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invProProveedors, Set invBodBodegases, Set ctrBanBancos) {
		super(paiId, ctrEstEstado, paiNombre, audUsuarioCreacion,
				audFechaCreacion, audFechaModificacion, audUsuarioModificacion,
				invProProveedors, invBodBodegases, ctrBanBancos);
	}

}
