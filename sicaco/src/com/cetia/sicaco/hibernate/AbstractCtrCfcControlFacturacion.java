package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrCfcControlFacturacion entity provides the base persistence
 * definition of the CtrCfcControlFacturacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrCfcControlFacturacion implements
		java.io.Serializable {

	// Fields

	private String cfcSerie;
	private CtrEmpEmpresa ctrEmpEmpresa = new  CtrEmpEmpresa();
	private Integer cfcCorrIni;
	private Integer cfcCorrFin;
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Integer cfcDigitos;
	private Set ctrRfcRepositorioFacturases = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtrCfcControlFacturacion() {
	}

	/** minimal constructor */
	public AbstractCtrCfcControlFacturacion(String cfcSerie,
			Integer cfcCorrIni, Integer cfcCorrFin, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.cfcSerie = cfcSerie;
		this.cfcCorrIni = cfcCorrIni;
		this.cfcCorrFin = cfcCorrFin;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractCtrCfcControlFacturacion(String cfcSerie,
			CtrEmpEmpresa ctrEmpEmpresa, Integer cfcCorrIni,
			Integer cfcCorrFin, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Integer cfcDigitos,
			Set ctrRfcRepositorioFacturases) {
		this.cfcSerie = cfcSerie;
		this.ctrEmpEmpresa = ctrEmpEmpresa;
		this.cfcCorrIni = cfcCorrIni;
		this.cfcCorrFin = cfcCorrFin;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.cfcDigitos = cfcDigitos;
		this.ctrRfcRepositorioFacturases = ctrRfcRepositorioFacturases;
	}

	// Property accessors

	public String getCfcSerie() {
		return this.cfcSerie;
	}

	public void setCfcSerie(String cfcSerie) {
		this.cfcSerie = cfcSerie;
	}

	public CtrEmpEmpresa getCtrEmpEmpresa() {
		return this.ctrEmpEmpresa;
	}

	public void setCtrEmpEmpresa(CtrEmpEmpresa ctrEmpEmpresa) {
		this.ctrEmpEmpresa = ctrEmpEmpresa;
	}

	public Integer getCfcCorrIni() {
		return this.cfcCorrIni;
	}

	public void setCfcCorrIni(Integer cfcCorrIni) {
		this.cfcCorrIni = cfcCorrIni;
	}

	public Integer getCfcCorrFin() {
		return this.cfcCorrFin;
	}

	public void setCfcCorrFin(Integer cfcCorrFin) {
		this.cfcCorrFin = cfcCorrFin;
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

	public Integer getCfcDigitos() {
		return cfcDigitos;
	}

	public void setCfcDigitos(Integer cfcDigitos) {
		this.cfcDigitos = cfcDigitos;
	}

	public Set getCtrRfcRepositorioFacturases() {
		return this.ctrRfcRepositorioFacturases;
	}

	public void setCtrRfcRepositorioFacturases(Set ctrRfcRepositorioFacturases) {
		this.ctrRfcRepositorioFacturases = ctrRfcRepositorioFacturases;
	}

}