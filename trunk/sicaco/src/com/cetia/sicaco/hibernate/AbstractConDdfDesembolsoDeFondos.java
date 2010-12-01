package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractConDdfDesembolsoDeFondos entity provides the base persistence
 * definition of the ConDdfDesembolsoDeFondos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConDdfDesembolsoDeFondos implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer banId;
	private Double prestamos;
	private Double ahorros;
	private Double proveedores;
	private String codigoCuenta;
	private Date fecha;

	// Constructors

	/** default constructor */
	public AbstractConDdfDesembolsoDeFondos() {
	}

	/** full constructor */
	public AbstractConDdfDesembolsoDeFondos(Integer banId, Double prestamos,
			Double ahorros, Double proveedores, String codigoCuenta, Date fecha) {
		this.banId = banId;
		this.prestamos = prestamos;
		this.ahorros = ahorros;
		this.proveedores = proveedores;
		this.codigoCuenta = codigoCuenta;
		this.fecha = fecha;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBanId() {
		return this.banId;
	}

	public void setBanId(Integer banId) {
		this.banId = banId;
	}

	public Double getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(Double prestamos) {
		this.prestamos = prestamos;
	}

	public Double getAhorros() {
		return this.ahorros;
	}

	public void setAhorros(Double ahorros) {
		this.ahorros = ahorros;
	}

	public Double getProveedores() {
		return this.proveedores;
	}

	public void setProveedores(Double proveedores) {
		this.proveedores = proveedores;
	}

	public String getCodigoCuenta() {
		return this.codigoCuenta;
	}

	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}