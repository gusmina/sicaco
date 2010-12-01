package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvMovMovimientos entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvMovMovimientos extends AbstractInvMovMovimientos implements
		java.io.Serializable {

	// Constructors

	private Date fechaIni;
	private Date fechaFin;
	
	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/** default constructor */
	public InvMovMovimientos() {
	}

	/** full constructor */
	public InvMovMovimientos(Integer movId, InvArtArticulo invArtArticulo,
			InvBodBodegas invBodBodegas,
			InvTmoTipoMovimiento invTmoTipoMovimiento, Integer movUnidades,
			Double movValor, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invNxmNivelMovimientos) {
		super(movId, invArtArticulo, invBodBodegas, invTmoTipoMovimiento, movUnidades,
				movValor, audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, invNxmNivelMovimientos);
		// TODO Auto-generated constructor stub
	}

}
