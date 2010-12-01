package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtrUreUsuarioRepositorio entity provides the base persistence
 * definition of the CtrUreUsuarioRepositorio entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrUreUsuarioRepositorio implements
		java.io.Serializable {

	// Fields

	private Integer ureId;
	private CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas = new CtrRfcRepositorioFacturas();
	private SecPerPersona secPerPersona = new SecPerPersona();
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractCtrUreUsuarioRepositorio() {
	}

	/** minimal constructor */
	public AbstractCtrUreUsuarioRepositorio(Integer ureId,
			String audUsuarioCreacion, Date audFechaCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.ureId = ureId;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractCtrUreUsuarioRepositorio(Integer ureId,
			CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas,
			SecPerPersona secPerPersona, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.ureId = ureId;
		this.ctrRfcRepositorioFacturas = ctrRfcRepositorioFacturas;
		this.secPerPersona = secPerPersona;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public Integer getUreId() {
		return this.ureId;
	}

	public void setUreId(Integer ureId) {
		this.ureId = ureId;
	}

	public CtrRfcRepositorioFacturas getCtrRfcRepositorioFacturas() {
		return this.ctrRfcRepositorioFacturas;
	}

	public void setCtrRfcRepositorioFacturas(
			CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas) {
		this.ctrRfcRepositorioFacturas = ctrRfcRepositorioFacturas;
	}

	public SecPerPersona getSecPerPersona() {
		return this.secPerPersona;
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		this.secPerPersona = secPerPersona;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public Date getAudFechaModificacion() {
		return this.audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return this.audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

}