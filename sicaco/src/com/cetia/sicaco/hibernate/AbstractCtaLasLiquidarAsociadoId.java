package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaLasLiquidarAsociadoId entity provides the base persistence
 * definition of the CtaLasLiquidarAsociadoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaLasLiquidarAsociadoId implements
		java.io.Serializable {

	// Fields

	private Integer lasId;
	private String lasNombreCuenta;
	private double lasSaldo;
	private double lasInteres;
	private double lasOtrasDeducciones;
	private Integer lasFavorContra;
	private String lasAscId;

	// Constructors

	/** default constructor */
	public AbstractCtaLasLiquidarAsociadoId() {
	}

	/** minimal constructor */
	public AbstractCtaLasLiquidarAsociadoId(Integer lasId,
			String lasNombreCuenta, double lasSaldo, Integer lasFavorContra, String lasAscId) {
		this.lasId = lasId;
		this.lasNombreCuenta = lasNombreCuenta;
		this.lasSaldo = lasSaldo;
		this.lasFavorContra = lasFavorContra;
		this.lasAscId = lasAscId;
	}

	/** full constructor */
	public AbstractCtaLasLiquidarAsociadoId(Integer lasId,
			String lasNombreCuenta, double lasSaldo, double lasInteres,
			double lasOtrasDeducciones, Integer lasFavorContra, String lasAscId) {
		this.lasId = lasId;
		this.lasNombreCuenta = lasNombreCuenta;
		this.lasSaldo = lasSaldo;
		this.lasInteres = lasInteres;
		this.lasOtrasDeducciones = lasOtrasDeducciones;
		this.lasFavorContra = lasFavorContra;
		this.lasAscId = lasAscId;
	}

	// Property accessors

	public Integer getLasId() {
		return this.lasId;
	}

	public void setLasId(Integer lasId) {
		this.lasId = lasId;
	}

	public String getLasNombreCuenta() {
		return this.lasNombreCuenta;
	}

	public void setLasNombreCuenta(String lasNombreCuenta) {
		this.lasNombreCuenta = lasNombreCuenta;
	}

	public double getLasSaldo() {
		return this.lasSaldo;
	}

	public void setLasSaldo(double lasSaldo) {
		this.lasSaldo = lasSaldo;
	}

	public double getLasInteres() {
		return this.lasInteres;
	}

	public void setLasInteres(double lasInteres) {
		this.lasInteres = lasInteres;
	}

	public double getLasOtrasDeducciones() {
		return this.lasOtrasDeducciones;
	}

	public void setLasOtrasDeducciones(double lasOtrasDeducciones) {
		this.lasOtrasDeducciones = lasOtrasDeducciones;
	}

	public Integer getLasFavorContra() {
		return this.lasFavorContra;
	}

	public void setLasFavorContra(Integer lasFavorContra) {
		this.lasFavorContra = lasFavorContra;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractCtaLasLiquidarAsociadoId))
			return false;
		AbstractCtaLasLiquidarAsociadoId castOther = (AbstractCtaLasLiquidarAsociadoId) other;

		return ((this.getLasId() == castOther.getLasId()) || (this.getLasId() != null
				&& castOther.getLasId() != null && this.getLasId().equals(
				castOther.getLasId())))
				&& ((this.getLasNombreCuenta() == castOther
						.getLasNombreCuenta()) || (this.getLasNombreCuenta() != null
						&& castOther.getLasNombreCuenta() != null && this
						.getLasNombreCuenta().equals(
								castOther.getLasNombreCuenta())))
				&& (this.getLasSaldo() == castOther.getLasSaldo())
				&& (this.getLasInteres() == castOther.getLasInteres())
				&& (this.getLasOtrasDeducciones() == castOther
						.getLasOtrasDeducciones())
				&& ((this.getLasFavorContra() == castOther.getLasFavorContra()) || (this
						.getLasFavorContra() != null
						&& castOther.getLasFavorContra() != null && this
						.getLasFavorContra().equals(
								castOther.getLasFavorContra())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getLasId() == null ? 0 : this.getLasId().hashCode());
		result = 37
				* result
				+ (getLasNombreCuenta() == null ? 0 : this.getLasNombreCuenta()
						.hashCode());
		result = 37 * result + (int) this.getLasSaldo();
		result = 37 * result + (int) this.getLasInteres();
		result = 37 * result + (int) this.getLasOtrasDeducciones();
		result = 37
				* result
				+ (getLasFavorContra() == null ? 0 : this.getLasFavorContra()
						.hashCode());
		return result;
	}

	public String getLasAscId() {
		return lasAscId;
	}

	public void setLasAscId(String lasAscId) {
		this.lasAscId = lasAscId;
	}

}