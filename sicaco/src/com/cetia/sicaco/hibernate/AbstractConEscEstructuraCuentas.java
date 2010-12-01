package com.cetia.sicaco.hibernate;

/**
 * AbstractConEscEstructuraCuentas entity provides the base persistence
 * definition of the ConEscEstructuraCuentas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConEscEstructuraCuentas implements
		java.io.Serializable {

	// Fields

	private Integer escNivel;
	private Integer escTamanho;

	// Constructors

	/** default constructor */
	public AbstractConEscEstructuraCuentas() {
	}

	/** full constructor */
	public AbstractConEscEstructuraCuentas(Integer escNivel, Integer escTamanho) {
		this.escNivel = escNivel;
		this.escTamanho = escTamanho;
	}

	// Property accessors

	public Integer getEscNivel() {
		return this.escNivel;
	}

	public void setEscNivel(Integer escNivel) {
		this.escNivel = escNivel;
	}

	public Integer getEscTamanho() {
		return this.escTamanho;
	}

	public void setEscTamanho(Integer escTamanho) {
		this.escTamanho = escTamanho;
	}

}