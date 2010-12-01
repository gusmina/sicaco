package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractCtrRckRepositorioCheques entity provides the base persistence
 * definition of the CtrRckRepositorioCheques entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrRckRepositorioCheques implements
		java.io.Serializable {

	// Fields

	private Integer rckId;
	private CtrCckControlCheques ctrCckControlCheques = new CtrCckControlCheques();
	private SecSucSucursal secSucSucursal = new SecSucSucursal();
	private String rckNombre;
	private Integer rckCorrIni;
	private Integer rckCorrFin;
	private Integer rckCorrActual;
	private Date rckFechaIni;
	private Date rckFechaFin;
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private String audUsuarioModificacion;
	private Date audFechaModificacion;
	private String rckEstado;

	// Constructors

	/** default constructor */
	public AbstractCtrRckRepositorioCheques() {
	}

	/** minimal constructor */
	public AbstractCtrRckRepositorioCheques(Integer rckId, String rckNombre,
			Integer rckCorrIni, Integer rckCorrFin, Integer rckCorrActual,
			String audUsuarioCreacion, Date audFechaCreacion,
			String rckEstado) {
		this.rckId = rckId;
		this.rckNombre = rckNombre;
		this.rckCorrIni = rckCorrIni;
		this.rckCorrFin = rckCorrFin;
		this.rckCorrActual = rckCorrActual;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.rckEstado = rckEstado;
	}

	/** full constructor */
	public AbstractCtrRckRepositorioCheques(Integer rckId,
			CtrCckControlCheques ctrCckControlCheques,
			SecSucSucursal secSucSucursal, String rckNombre,
			Integer rckCorrIni, Integer rckCorrFin, Integer rckCorrActual,
			Date rckFechaIni, Date rckFechaFin, String audUsuarioCreacion,
			Date audFechaCreacion, String audUsuarioModificacion,
			Date audFechaModificacion, String rckEstado) {
		this.rckId = rckId;
		this.ctrCckControlCheques = ctrCckControlCheques;
		this.secSucSucursal = secSucSucursal;
		this.rckNombre = rckNombre;
		this.rckCorrIni = rckCorrIni;
		this.rckCorrFin = rckCorrFin;
		this.rckCorrActual = rckCorrActual;
		this.rckFechaIni = rckFechaIni;
		this.rckFechaFin = rckFechaFin;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.audFechaModificacion = audFechaModificacion;
		this.rckEstado = rckEstado;
	}

	// Property accessors

	public Integer getRckId() {
		return this.rckId;
	}

	public void setRckId(Integer rckId) {
		this.rckId = rckId;
	}

	public CtrCckControlCheques getCtrCckControlCheques() {
		return this.ctrCckControlCheques;
	}

	public void setCtrCckControlCheques(
			CtrCckControlCheques ctrCckControlCheques) {
		this.ctrCckControlCheques = ctrCckControlCheques;
	}

	public SecSucSucursal getSecSucSucursal() {
		return this.secSucSucursal;
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		this.secSucSucursal = secSucSucursal;
	}

	public String getRckNombre() {
		return this.rckNombre;
	}

	public void setRckNombre(String rckNombre) {
		this.rckNombre = rckNombre;
	}

	public Integer getRckCorrIni() {
		return this.rckCorrIni;
	}

	public void setRckCorrIni(Integer rckCorrIni) {
		this.rckCorrIni = rckCorrIni;
	}

	public Integer getRckCorrFin() {
		return this.rckCorrFin;
	}

	public void setRckCorrFin(Integer rckCorrFin) {
		this.rckCorrFin = rckCorrFin;
	}

	public Integer getRckCorrActual() {
		return this.rckCorrActual;
	}

	public void setRckCorrActual(Integer rckCorrActual) {
		this.rckCorrActual = rckCorrActual;
	}

	public Date getRckFechaIni() {
		return this.rckFechaIni;
	}

	public void setRckFechaIni(Date rckFechaIni) {
		this.rckFechaIni = rckFechaIni;
	}

	public Date getRckFechaFin() {
		return this.rckFechaFin;
	}

	public void setRckFechaFin(Date rckFechaFin) {
		this.rckFechaFin = rckFechaFin;
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

	public String getAudUsuarioModificacion() {
		return this.audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public Date getAudFechaModificacion() {
		return this.audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getRckEstado() {
		return rckEstado;
	}

	public void setRckEstado(String rckEstado) {
		this.rckEstado = rckEstado;
	}

}