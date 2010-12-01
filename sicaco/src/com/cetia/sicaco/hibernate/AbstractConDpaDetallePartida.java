package com.cetia.sicaco.hibernate;

/**
 * AbstractConDpaDetallePartida entity provides the base persistence definition
 * of the ConDpaDetallePartida entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConDpaDetallePartida implements
		java.io.Serializable {

	// Fields

	private long dpaId;
	private ConPcoPartidaContable conPcoPartidaContable = new ConPcoPartidaContable();
	private ConCpaConceptoPartida conCpaConceptoPartida = new ConCpaConceptoPartida();
	private ConCueCuenta conCueCuenta = new ConCueCuenta();
	private double dpaValorDebe;
	private double dpaValorHaber;
	private String dpaOtroConcepto;

	// Constructors

	/** default constructor */
	public AbstractConDpaDetallePartida() {
	}

	/** minimal constructor */
	public AbstractConDpaDetallePartida(
			ConPcoPartidaContable conPcoPartidaContable) {
		this.conPcoPartidaContable = conPcoPartidaContable;
	}

	/** full constructor */
	public AbstractConDpaDetallePartida(
			ConPcoPartidaContable conPcoPartidaContable,
			ConCpaConceptoPartida conCpaConceptoPartida,
			ConCueCuenta conCueCuenta, double dpaValorDebe,
			double dpaValorHaber, String dpaOtroConcepto) {
		this.conPcoPartidaContable = conPcoPartidaContable;
		this.conCpaConceptoPartida = conCpaConceptoPartida;
		this.conCueCuenta = conCueCuenta;
		this.dpaValorDebe = dpaValorDebe;
		this.dpaValorHaber = dpaValorHaber;
		this.dpaOtroConcepto = dpaOtroConcepto;
	}

	// Property accessors

	public long getDpaId() {
		return this.dpaId;
	}

	public void setDpaId(long dpaId) {
		this.dpaId = dpaId;
	}

	public ConPcoPartidaContable getConPcoPartidaContable() {
		return this.conPcoPartidaContable;
	}

	public void setConPcoPartidaContable(
			ConPcoPartidaContable conPcoPartidaContable) {
		this.conPcoPartidaContable = conPcoPartidaContable;
	}

	public ConCpaConceptoPartida getConCpaConceptoPartida() {
		return this.conCpaConceptoPartida;
	}

	public void setConCpaConceptoPartida(
			ConCpaConceptoPartida conCpaConceptoPartida) {
		this.conCpaConceptoPartida = conCpaConceptoPartida;
	}

	public ConCueCuenta getConCueCuenta() {
		return this.conCueCuenta;
	}

	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		this.conCueCuenta = conCueCuenta;
	}

	public double getDpaValorDebe() {
		return this.dpaValorDebe;
	}

	public void setDpaValorDebe(double dpaValorDebe) {
		this.dpaValorDebe = dpaValorDebe;
	}

	public double getDpaValorHaber() {
		return this.dpaValorHaber;
	}

	public void setDpaValorHaber(double dpaValorHaber) {
		this.dpaValorHaber = dpaValorHaber;
	}

	public String getDpaOtroConcepto() {
		return this.dpaOtroConcepto;
	}

	public void setDpaOtroConcepto(String dpaOtroConcepto) {
		this.dpaOtroConcepto = dpaOtroConcepto;
	}

}