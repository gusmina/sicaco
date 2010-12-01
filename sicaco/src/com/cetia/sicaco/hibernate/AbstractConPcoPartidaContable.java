package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractConPcoPartidaContable entity provides the base persistence definition
 * of the ConPcoPartidaContable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConPcoPartidaContable implements
		java.io.Serializable {

	// Fields

	private long pcoId;
	private ConTpaTipoPartida conTpaTipoPartida = new ConTpaTipoPartida();
	private ConCpaConceptoPartida conCpaConceptoPartida=new ConCpaConceptoPartida();
	private CtaChkChequePrestamo ctaChkChequePrestamo = new CtaChkChequePrestamo();
	private Integer pcoComprobantePartida;
	private String pcoEstado;
	private String pcoOtroConcepto;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Integer pcoChequePendiente;
	private Date pcoFechaIngresoPartida;
	private Integer pcoChequeNegociable;
	private Integer pcoModulo;
	private Set conDpaDetallePartidas = new HashSet(0);

	// Constructors

	public AbstractConPcoPartidaContable(long pcoId,
			ConTpaTipoPartida conTpaTipoPartida,
			ConCpaConceptoPartida conCpaConceptoPartida,
			CtaChkChequePrestamo ctaChkChequePrestamo,
			Integer pcoComprobantePartida, String pcoEstado,
			String pcoOtroConcepto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Integer pcoChequePendiente,
			Date pcoFechaIngresoPartida, Integer pcoChequeNegociable,
			Integer pcoModulo, Set conDpaDetallePartidas) {
		super();
		this.pcoId = pcoId;
		this.conTpaTipoPartida = conTpaTipoPartida;
		this.conCpaConceptoPartida = conCpaConceptoPartida;
		this.ctaChkChequePrestamo = ctaChkChequePrestamo;
		this.pcoComprobantePartida = pcoComprobantePartida;
		this.pcoEstado = pcoEstado;
		this.pcoOtroConcepto = pcoOtroConcepto;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.pcoChequePendiente = pcoChequePendiente;
		this.pcoFechaIngresoPartida = pcoFechaIngresoPartida;
		this.pcoChequeNegociable = pcoChequeNegociable;
		this.pcoModulo = pcoModulo;
		this.conDpaDetallePartidas = conDpaDetallePartidas;
	}

	/** default constructor */
	public AbstractConPcoPartidaContable() {
	}

	/** minimal constructor */
	public AbstractConPcoPartidaContable(long pcoId,
			Integer pcoComprobantePartida, String pcoEstado,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Date pcoFechaIngresoPartida, Integer pcoChequeNegociable,Integer pcoModulo) {
		this.pcoId = pcoId;
		this.pcoComprobantePartida = pcoComprobantePartida;
		this.pcoEstado = pcoEstado;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.pcoFechaIngresoPartida = pcoFechaIngresoPartida;
		this.pcoChequeNegociable = pcoChequeNegociable;
		this.pcoModulo = pcoModulo;
	}

	/** full constructor */

	// Property accessors

	
	
	public long getPcoId() {
		return this.pcoId;
	}

	public void setPcoId(long pcoId) {
		this.pcoId = pcoId;
	}

	public ConTpaTipoPartida getConTpaTipoPartida() {
		return this.conTpaTipoPartida;
	}

	public void setConTpaTipoPartida(ConTpaTipoPartida conTpaTipoPartida) {
		this.conTpaTipoPartida = conTpaTipoPartida;
	}

	public ConCpaConceptoPartida getConCpaConceptoPartida() {
		return this.conCpaConceptoPartida;
	}

	public void setConCpaConceptoPartida(
			ConCpaConceptoPartida conCpaConceptoPartida) {
		this.conCpaConceptoPartida = conCpaConceptoPartida;
	}

	public Integer getPcoComprobantePartida() {
		return this.pcoComprobantePartida;
	}

	public void setPcoComprobantePartida(Integer pcoComprobantePartida) {
		this.pcoComprobantePartida = pcoComprobantePartida;
	}

	public String getPcoEstado() {
		return this.pcoEstado;
	}

	public void setPcoEstado(String pcoEstado) {
		this.pcoEstado = pcoEstado;
	}

	public String getPcoOtroConcepto() {
		return this.pcoOtroConcepto;
	}

	public void setPcoOtroConcepto(String pcoOtroConcepto) {
		this.pcoOtroConcepto = pcoOtroConcepto;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
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

	public Integer getPcoChequePendiente() {
		return this.pcoChequePendiente;
	}

	public void setPcoChequePendiente(Integer pcoChequePendiente) {
		this.pcoChequePendiente = pcoChequePendiente;
	}

	public Set getConDpaDetallePartidas() {
		return this.conDpaDetallePartidas;
	}

	public void setConDpaDetallePartidas(Set conDpaDetallePartidas) {
		this.conDpaDetallePartidas = conDpaDetallePartidas;
	}

	public Date getPcoFechaIngresoPartida() {
		return pcoFechaIngresoPartida;
	}

	public void setPcoFechaIngresoPartida(Date pcoFechaIngresoPartida) {
		this.pcoFechaIngresoPartida = pcoFechaIngresoPartida;
	}

	public Integer getPcoChequeNegociable() {
		return pcoChequeNegociable;
	}

	public void setPcoChequeNegociable(Integer pcoChequeNegociable) {
		this.pcoChequeNegociable = pcoChequeNegociable;
	}

	public Integer getPcoModulo() {
		return pcoModulo;
	}

	public void setPcoModulo(Integer pcoModulo) {
		this.pcoModulo = pcoModulo;
	}

	public Date getAudFechaCreacion() {
		return audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public CtaChkChequePrestamo getCtaChkChequePrestamo() {
		return ctaChkChequePrestamo;
	}

	public void setCtaChkChequePrestamo(CtaChkChequePrestamo ctaChkChequePrestamo) {
		this.ctaChkChequePrestamo = ctaChkChequePrestamo;
	}

}