package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * CtrParParametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrParParametros extends AbstractCtrParParametros implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrParParametros() {
	}

	/** minimal constructor */
	public CtrParParametros(String parNombre, String parDescripcion,
			Date parValorDate, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(parNombre, parDescripcion, parValorDate, audUsuarioCreacion,
				audFechaCreacion, audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public CtrParParametros(String parNombre, String parDescripcion,
			Date parValorDate, String parValorString, Double parValorNumber,
			Byte parValorBoolean, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(parNombre, parDescripcion, parValorDate, parValorString,
				parValorNumber, parValorBoolean, audUsuarioCreacion,
				audFechaCreacion, audFechaModificacion, audUsuarioModificacion);
	}

}
