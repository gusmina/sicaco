package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrCckControlCheques entity provides the base persistence definition
 * of the CtrCckControlCheques entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrCckControlCheques implements
		java.io.Serializable {

	// Fields

	private Integer cckId;
	private CtrBanBanco ctrBanBanco = new CtrBanBanco();
	private String cckSerie;
	private Integer cckCorrIni;
	private Integer cckCorrFin;
	private Integer cckDigitos;
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private String audUsuarioModificacion;
	private Date audFechaModificacion;
	private Set ctrRckRepositorioChequeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtrCckControlCheques() {
	}

	/** minimal constructor */
	public AbstractCtrCckControlCheques(Integer cckId, String cckSerie,
			Integer cckCorrIni, Integer cckCorrFin, String audUsuarioCreacion,
			Date audFechaCreacion) {
		this.cckId = cckId;
		this.cckSerie = cckSerie;
		this.cckCorrIni = cckCorrIni;
		this.cckCorrFin = cckCorrFin;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
	}

	/** full constructor */
	public AbstractCtrCckControlCheques(Integer cckId, CtrBanBanco ctrBanBanco,
			String cckSerie, Integer cckCorrIni, Integer cckCorrFin,
			Integer cckDigitos,
			String audUsuarioCreacion, Date audFechaCreacion,
			String audUsuarioModificacion, Date audFechaModificacion,
			Set ctrRckRepositorioChequeses) {
		this.cckId = cckId;
		this.ctrBanBanco = ctrBanBanco;
		this.cckSerie = cckSerie;
		this.cckCorrIni = cckCorrIni;
		this.cckCorrFin = cckCorrFin;
		this.cckDigitos = cckDigitos;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.audFechaModificacion = audFechaModificacion;
		this.ctrRckRepositorioChequeses = ctrRckRepositorioChequeses;
	}

	// Property accessors

	public Integer getCckId() {
		return this.cckId;
	}

	public void setCckId(Integer cckId) {
		this.cckId = cckId;
	}

	public CtrBanBanco getCtrBanBanco() {
		return this.ctrBanBanco;
	}

	public void setCtrBanBanco(CtrBanBanco ctrBanBanco) {
		this.ctrBanBanco = ctrBanBanco;
	}

	public String getCckSerie() {
		return this.cckSerie;
	}

	public void setCckSerie(String cckSerie) {
		this.cckSerie = cckSerie;
	}

	public Integer getCckCorrIni() {
		return this.cckCorrIni;
	}

	public void setCckCorrIni(Integer cckCorrIni) {
		this.cckCorrIni = cckCorrIni;
	}

	public Integer getCckCorrFin() {
		return this.cckCorrFin;
	}

	public void setCckCorrFin(Integer cckCorrFin) {
		this.cckCorrFin = cckCorrFin;
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

	public Set getCtrRckRepositorioChequeses() {
		return this.ctrRckRepositorioChequeses;
	}

	public void setCtrRckRepositorioChequeses(Set ctrRckRepositorioChequeses) {
		this.ctrRckRepositorioChequeses = ctrRckRepositorioChequeses;
	}

	public Integer getCckDigitos() {
		return cckDigitos;
	}

	public void setCckDigitos(Integer cckDigitos) {
		this.cckDigitos = cckDigitos;
	}

}