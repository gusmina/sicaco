package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrRfcRepositorioFacturas entity provides the base persistence
 * definition of the CtrRfcRepositorioFacturas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrRfcRepositorioFacturas implements
		java.io.Serializable {

	// Fields

	private Integer rfcId;
	private CtrCfcControlFacturacion ctrCfcControlFacturacion = new CtrCfcControlFacturacion();
	private String rfcNombre;
	private Integer rfcCorrIni;
	private Integer rfcCorrFin;
	private Integer rfcCorrActual;
	private Date rfcFechaIni;
	private Date rfcFechaFin;
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private String tipoFactCont;
	private Integer sucID;
	private String rfcEstado;
	private Set ctrUreUsuarioRepositorios = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtrRfcRepositorioFacturas() {
	}

	/** minimal constructor */
	public AbstractCtrRfcRepositorioFacturas(Integer rfcId, String rfcNombre,
			Integer rfcCorrIni, Integer rfcCorrFin, Integer rfcCorrActual,
			String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion,String tipoFactCont,Integer sucID,
			String rfcEstado) {
		this.rfcId = rfcId;
		this.rfcNombre = rfcNombre;
		this.rfcCorrIni = rfcCorrIni;
		this.rfcCorrFin = rfcCorrFin;
		this.rfcCorrActual = rfcCorrActual;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.tipoFactCont = tipoFactCont;
		this.sucID = sucID;
		this.rfcEstado = rfcEstado;
	}

	/** full constructor */
	public AbstractCtrRfcRepositorioFacturas(Integer rfcId,
			CtrCfcControlFacturacion ctrCfcControlFacturacion,
			String rfcNombre, Integer rfcCorrIni, Integer rfcCorrFin,
			Integer rfcCorrActual, 
			String audUsuarioCreacion, Date audFechaCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String tipoFactCont,Integer sucID, String rfcEstado,
			Set ctrUreUsuarioRepositorios) {
		this.rfcId = rfcId;
		this.ctrCfcControlFacturacion = ctrCfcControlFacturacion;
		this.rfcNombre = rfcNombre;
		this.rfcCorrIni = rfcCorrIni;
		this.rfcCorrFin = rfcCorrFin;
		this.rfcCorrActual = rfcCorrActual;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.ctrUreUsuarioRepositorios = ctrUreUsuarioRepositorios;
		this.tipoFactCont = tipoFactCont;
		this.sucID = sucID;
		this.rfcEstado = rfcEstado;
	}

	// Property accessors

	public Integer getSucID() {
		return sucID;
	}

	public void setSucID(Integer sucID) {
		this.sucID = sucID;
	}
	
	public String getTipoFactCont() {
		return tipoFactCont;
	}

	public void setTipoFactCont(String tipoFactCont) {
		this.tipoFactCont = tipoFactCont;
	}
	
	public Integer getRfcId() {
		return this.rfcId;
	}

	public void setRfcId(Integer rfcId) {
		this.rfcId = rfcId;
	}

	public CtrCfcControlFacturacion getCtrCfcControlFacturacion() {
		return this.ctrCfcControlFacturacion;
	}

	public void setCtrCfcControlFacturacion(
			CtrCfcControlFacturacion ctrCfcControlFacturacion) {
		this.ctrCfcControlFacturacion = ctrCfcControlFacturacion;
	}

	public String getRfcNombre() {
		return this.rfcNombre;
	}

	public void setRfcNombre(String rfcNombre) {
		this.rfcNombre = rfcNombre;
	}

	public Integer getRfcCorrIni() {
		return this.rfcCorrIni;
	}

	public void setRfcCorrIni(Integer rfcCorrIni) {
		this.rfcCorrIni = rfcCorrIni;
	}

	public Integer getRfcCorrFin() {
		return this.rfcCorrFin;
	}

	public void setRfcCorrFin(Integer rfcCorrFin) {
		this.rfcCorrFin = rfcCorrFin;
	}

	public Integer getRfcCorrActual() {
		return this.rfcCorrActual;
	}

	public void setRfcCorrActual(Integer rfcCorrActual) {
		this.rfcCorrActual = rfcCorrActual;
	}

	public Date getRfcFechaIni() {
		return this.rfcFechaIni;
	}

	public void setRfcFechaIni(Date rfcFechaIni) {
		this.rfcFechaIni = rfcFechaIni;
	}

	public Date getRfcFechaFin() {
		return this.rfcFechaFin;
	}

	public void setRfcFechaFin(Date rfcFechaFin) {
		this.rfcFechaFin = rfcFechaFin;
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

	public Set getCtrUreUsuarioRepositorios() {
		return this.ctrUreUsuarioRepositorios;
	}

	public void setCtrUreUsuarioRepositorios(Set ctrUreUsuarioRepositorios) {
		this.ctrUreUsuarioRepositorios = ctrUreUsuarioRepositorios;
	}

	public String getRfcEstado() {
		return rfcEstado;
	}

	public void setRfcEstado(String rfcEstado) {
		this.rfcEstado = rfcEstado;
	}

}