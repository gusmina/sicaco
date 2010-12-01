package com.cetia.sicaco.hibernate;

/**
 * AbstractConCteConfiguracionEstadoFinanciero entity provides the base
 * persistence definition of the ConCteConfiguracionEstadoFinanciero entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConCteConfiguracionEstadoFinanciero implements
		java.io.Serializable {

	// Fields

	private Integer cetId;
	private ConCueCuenta conCueCuenta = new ConCueCuenta();
	private String cetInf;
	private Integer cetPosc;
	private Integer cetBanda;

	// Constructors

	/** default constructor */
	public AbstractConCteConfiguracionEstadoFinanciero() {
	}

	/** full constructor */
	public AbstractConCteConfiguracionEstadoFinanciero(
			ConCueCuenta conCueCuenta, String cetInf,Integer cetPosc,Integer cetBanda) {
		this.conCueCuenta = conCueCuenta;
		this.cetInf = cetInf;
		this.cetPosc = cetPosc;
		this.cetBanda = cetBanda;
	}

	// Property accessors

	public Integer getCetId() {
		return this.cetId;
	}

	public void setCetId(Integer cetId) {
		this.cetId = cetId;
	}

	public ConCueCuenta getConCueCuenta() {
		return this.conCueCuenta;
	}

	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		this.conCueCuenta = conCueCuenta;
	}

	public String getCetInf() {
		return this.cetInf;
	}

	public void setCetInf(String cetInf) {
		this.cetInf = cetInf;
	}

	public Integer getCetPosc() {
		return cetPosc;
	}

	public void setCetPosc(Integer cetPosc) {
		this.cetPosc = cetPosc;
	}

	public Integer getCetBanda() {
		return cetBanda;
	}

	public void setCetBanda(Integer cetBanda) {
		this.cetBanda = cetBanda;
	}

	
}