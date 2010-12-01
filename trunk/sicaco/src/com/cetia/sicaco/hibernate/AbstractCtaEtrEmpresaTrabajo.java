package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaEtrEmpresaTrabajo entity provides the base persistence definition
 * of the CtaEtrEmpresaTrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaEtrEmpresaTrabajo implements
		java.io.Serializable {

	// Fields

	private Integer etrId;
	private String etrNombre;
	private String etrDescripcion;
	private String etrEstado;
	private Set ctaDptDepartamentoTrabajos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaEtrEmpresaTrabajo() {
	}

	/** minimal constructor */
	public AbstractCtaEtrEmpresaTrabajo(Integer etrId, String etrNombre,
			String etrEstado) {
		this.etrId = etrId;
		this.etrNombre = etrNombre;
		this.etrEstado = etrEstado;
	}

	/** full constructor */
	public AbstractCtaEtrEmpresaTrabajo(Integer etrId, String etrNombre,
			String etrDescripcion, String etrEstado,
			Set ctaDptDepartamentoTrabajos) {
		this.etrId = etrId;
		this.etrNombre = etrNombre;
		this.etrDescripcion = etrDescripcion;
		this.etrEstado = etrEstado;
		this.ctaDptDepartamentoTrabajos = ctaDptDepartamentoTrabajos;
	}

	// Property accessors

	public Integer getEtrId() {
		return this.etrId;
	}

	public void setEtrId(Integer etrId) {
		this.etrId = etrId;
	}

	public String getEtrNombre() {
		return this.etrNombre;
	}

	public void setEtrNombre(String etrNombre) {
		this.etrNombre = etrNombre;
	}

	public String getEtrDescripcion() {
		return this.etrDescripcion;
	}

	public void setEtrDescripcion(String etrDescripcion) {
		this.etrDescripcion = etrDescripcion;
	}

	public String getEtrEstado() {
		return this.etrEstado;
	}

	public void setEtrEstado(String etrEstado) {
		this.etrEstado = etrEstado;
	}

	public Set getCtaDptDepartamentoTrabajos() {
		return this.ctaDptDepartamentoTrabajos;
	}

	public void setCtaDptDepartamentoTrabajos(Set ctaDptDepartamentoTrabajos) {
		this.ctaDptDepartamentoTrabajos = ctaDptDepartamentoTrabajos;
	}

}