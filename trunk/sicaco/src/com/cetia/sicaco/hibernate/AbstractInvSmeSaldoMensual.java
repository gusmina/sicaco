package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvArtArticulo entity provides the base persistence definition of the
 * InvArtArticulo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvSmeSaldoMensual implements java.io.Serializable {

	// Fields

	private Integer smeId;
	private InvArtArticulo invArtArticulo = new InvArtArticulo();
	private Double smeCantidad;
	private Date smeFecha;
	private Double smeSaldo;
	private Double smeCostoArt;

	// Constructors

	/** default constructor */
	public AbstractInvSmeSaldoMensual() {
	}

	/** minimal constructor */
	public AbstractInvSmeSaldoMensual(Integer smeId,
			InvArtArticulo invArtArticulo, Double smeCantidad, Date smeFecha,
			Double smeSaldo, Double smeCostoArt) {
		super();
		this.smeId = smeId;
		this.invArtArticulo = invArtArticulo;
		this.smeCantidad = smeCantidad;
		this.smeFecha = smeFecha;
		this.smeSaldo = smeSaldo;
		this.smeCostoArt = smeCostoArt;
	}


	// Property accessors
	
	public Integer getSmeId() {
		return smeId;
	}

	public void setSmeId(Integer smeId) {
		this.smeId = smeId;
	}

	public InvArtArticulo getInvArtArticulo() {
		return invArtArticulo;
	}

	public void setInvArtArticulo(InvArtArticulo invArtArticulo) {
		this.invArtArticulo = invArtArticulo;
	}

	public Double getSmeCantidad() {
		return smeCantidad;
	}

	public void setSmeCantidad(Double smeCantidad) {
		this.smeCantidad = smeCantidad;
	}

	public Date getSmeFecha() {
		return smeFecha;
	}

	public void setSmeFecha(Date smeFecha) {
		this.smeFecha = smeFecha;
	}

	public Double getSmeSaldo() {
		return smeSaldo;
	}

	public void setSmeSaldo(Double smeSaldo) {
		this.smeSaldo = smeSaldo;
	}

	public Double getSmeCostoArt() {
		return smeCostoArt;
	}

	public void setSmeCostoArt(Double smeCostoArt) {
		this.smeCostoArt = smeCostoArt;
	}

}