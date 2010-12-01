package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecParParentesco entity provides the base persistence definition of
 * the SecParParentesco entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecParParentesco implements java.io.Serializable {

	// Fields

	private Integer parId;
	private String parDescripcion;
	private Set ctaBenBeneficiarioses = new HashSet(0);
	private Set secPemPersonaEmergencias = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecParParentesco() {
	}

	/** minimal constructor */
	public AbstractSecParParentesco(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	/** full constructor */
	public AbstractSecParParentesco(String parDescripcion,
			Set ctaBenBeneficiarioses, Set secPemPersonaEmergencias) {
		this.parDescripcion = parDescripcion;
		this.ctaBenBeneficiarioses = ctaBenBeneficiarioses;
		this.secPemPersonaEmergencias = secPemPersonaEmergencias;
	}

	// Property accessors

	public Integer getParId() {
		return this.parId;
	}

	public void setParId(Integer parId) {
		this.parId = parId;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public Set getCtaBenBeneficiarioses() {
		return this.ctaBenBeneficiarioses;
	}

	public void setCtaBenBeneficiarioses(Set ctaBenBeneficiarioses) {
		this.ctaBenBeneficiarioses = ctaBenBeneficiarioses;
	}

	public Set getSecPemPersonaEmergencias() {
		return this.secPemPersonaEmergencias;
	}

	public void setSecPemPersonaEmergencias(Set secPemPersonaEmergencias) {
		this.secPemPersonaEmergencias = secPemPersonaEmergencias;
	}

}