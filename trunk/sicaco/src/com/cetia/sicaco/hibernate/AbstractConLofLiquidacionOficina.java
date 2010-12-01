package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractConLofLiquidacionOficina entity provides the base persistence
 * definition of the ConLofLiquidacionOficina entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConLofLiquidacionOficina implements
		java.io.Serializable {

	// Fields

	private Long lofId;
	private Integer cueId;
	private String cueNombre;
	private Double valorDepositado;
	private Date fecha;
	private Integer sucId;

	// Constructors

	/** default constructor */
	public AbstractConLofLiquidacionOficina() {
	}

	/** minimal constructor */
	public AbstractConLofLiquidacionOficina(Integer cueId, String cueNombre,
			Double valorDepositado, Date fecha) {
		this.cueId = cueId;
		this.cueNombre = cueNombre;
		this.valorDepositado = valorDepositado;
		this.fecha = fecha;
	}

	/** full constructor */
	public AbstractConLofLiquidacionOficina(Integer cueId, String cueNombre,
			Double valorDepositado, Date fecha, Integer sucId) {
		this.cueId = cueId;
		this.cueNombre = cueNombre;
		this.valorDepositado = valorDepositado;
		this.fecha = fecha;
		this.sucId = sucId;
	}

	// Property accessors

	public Long getLofId() {
		return this.lofId;
	}

	public void setLofId(Long lofId) {
		this.lofId = lofId;
	}

	public Integer getCueId() {
		return this.cueId;
	}

	public void setCueId(Integer cueId) {
		this.cueId = cueId;
	}

	public String getCueNombre() {
		return this.cueNombre;
	}

	public void setCueNombre(String cueNombre) {
		this.cueNombre = cueNombre;
	}

	public Double getValorDepositado() {
		return this.valorDepositado;
	}

	public void setValorDepositado(Double valorDepositado) {
		this.valorDepositado = valorDepositado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getSucId() {
		return this.sucId;
	}

	public void setSucId(Integer sucId) {
		this.sucId = sucId;
	}

}