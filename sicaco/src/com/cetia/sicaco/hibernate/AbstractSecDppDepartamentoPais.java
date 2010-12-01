package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecDppDepartamentoPais entity provides the base persistence
 * definition of the SecDppDepartamentoPais entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecDppDepartamentoPais implements
		java.io.Serializable {

	// Fields

	private Integer dppId;
	private String dppNombre;
	private String dppDescripcion;
	private Set secPerPersonas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecDppDepartamentoPais() {
	}

	/** minimal constructor */
	public AbstractSecDppDepartamentoPais(Integer dppId, String dppNombre) {
		this.dppId = dppId;
		this.dppNombre = dppNombre;
	}

	/** full constructor */
	public AbstractSecDppDepartamentoPais(Integer dppId, String dppNombre,
			String dppDescripcion, Set secPerPersonas) {
		this.dppId = dppId;
		this.dppNombre = dppNombre;
		this.dppDescripcion = dppDescripcion;
		this.secPerPersonas = secPerPersonas;
	}

	// Property accessors

	public Integer getDppId() {
		return this.dppId;
	}

	public void setDppId(Integer dppId) {
		this.dppId = dppId;
	}

	public String getDppNombre() {
		return this.dppNombre;
	}

	public void setDppNombre(String dppNombre) {
		this.dppNombre = dppNombre;
	}

	public String getDppDescripcion() {
		return this.dppDescripcion;
	}

	public void setDppDescripcion(String dppDescripcion) {
		this.dppDescripcion = dppDescripcion;
	}

	public Set getSecPerPersonas() {
		return this.secPerPersonas;
	}

	public void setSecPerPersonas(Set secPerPersonas) {
		this.secPerPersonas = secPerPersonas;
	}

}