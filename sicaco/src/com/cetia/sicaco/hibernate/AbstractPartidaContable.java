package com.cetia.sicaco.hibernate;

/**
 * AbstractPartidaContable entity provides the base persistence definition of
 * the PartidaContable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractPartidaContable implements java.io.Serializable {

	// Fields

	private Integer cuentaId;
	private String codigoCuenta;
	private String nombreCuenta;
	private double parcial;
	private double debe;
	private double haber;
	private long pcoId;

	// Constructors

	/** default constructor */
	public AbstractPartidaContable() {
	}

	/** full constructor */
	public AbstractPartidaContable(Integer cuentaId, String codigoCuenta,
			String nombreCuenta, double parcial, double debe, double haber,
			long pcoId) {
		this.cuentaId = cuentaId;
		this.codigoCuenta = codigoCuenta;
		this.nombreCuenta = nombreCuenta;
		this.parcial = parcial;
		this.debe = debe;
		this.haber = haber;
		this.pcoId = pcoId;
	}

	// Property accessors

	public Integer getCuentaId() {
		return this.cuentaId;
	}

	public void setCuentaId(Integer cuentaId) {
		this.cuentaId = cuentaId;
	}

	public String getCodigoCuenta() {
		return this.codigoCuenta;
	}

	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public String getNombreCuenta() {
		return this.nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public double getParcial() {
		return this.parcial;
	}

	public void setParcial(double parcial) {
		this.parcial = parcial;
	}

	public double getDebe() {
		return this.debe;
	}

	public void setDebe(double debe) {
		this.debe = debe;
	}

	public double getHaber() {
		return this.haber;
	}

	public void setHaber(double haber) {
		this.haber = haber;
	}

	public long getPcoId() {
		return this.pcoId;
	}

	public void setPcoId(long pcoId) {
		this.pcoId = pcoId;
	}

}