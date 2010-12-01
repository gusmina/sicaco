package com.cetia.sicaco.hibernate;

/**
 * AbstractConEreEstadoResultados entity provides the base persistence
 * definition of the ConEreEstadoResultados entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConEreEstadoResultados implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String cueNombre;
	private Double banda1;
	private Double banda2;
	private Integer subrayado;

	// Constructors

	/** default constructor */
	public AbstractConEreEstadoResultados() {
	}

	/** minimal constructor */
	public AbstractConEreEstadoResultados(String cueNombre) {
		this.cueNombre = cueNombre;
	}

	/** full constructor */
	public AbstractConEreEstadoResultados(String cueNombre, Double banda1,
			Double banda2, Integer subrayado) {
		this.cueNombre = cueNombre;
		this.banda1 = banda1;
		this.banda2 = banda2;
		this.subrayado = subrayado;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCueNombre() {
		return this.cueNombre;
	}

	public void setCueNombre(String cueNombre) {
		this.cueNombre = cueNombre;
	}

	public Double getBanda1() {
		return this.banda1;
	}

	public void setBanda1(Double banda1) {
		this.banda1 = banda1;
	}

	public Double getBanda2() {
		return this.banda2;
	}

	public void setBanda2(Double banda2) {
		this.banda2 = banda2;
	}

	public Integer getSubrayado() {
		return this.subrayado;
	}

	public void setSubrayado(Integer subrayado) {
		this.subrayado = subrayado;
	}

}