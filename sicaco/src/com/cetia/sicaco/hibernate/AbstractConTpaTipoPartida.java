package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractConTpaTipoPartida entity provides the base persistence definition of
 * the ConTpaTipoPartida entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConTpaTipoPartida implements java.io.Serializable {

	// Fields

	private Integer tpaId;
	private String tpaNombre;
	private String tpaDescripcion;
	private String tpaCorrelativoInicio;
	private Set conPcoPartidaContables = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractConTpaTipoPartida() {
	}

	/** minimal constructor */
	public AbstractConTpaTipoPartida(Integer tpaId, String tpaNombre) {
		this.tpaId = tpaId;
		this.tpaNombre = tpaNombre;
	}

	/** full constructor */
	public AbstractConTpaTipoPartida(Integer tpaId, String tpaNombre,
			String tpaDescripcion, String tpaCorrelativoInicio, Set conPcoPartidaContables) {
		this.tpaId = tpaId;
		this.tpaNombre = tpaNombre;
		this.tpaDescripcion = tpaDescripcion;
		this.tpaCorrelativoInicio = tpaCorrelativoInicio;
		this.conPcoPartidaContables = conPcoPartidaContables;
	}

	// Property accessors

	public Integer getTpaId() {
		return this.tpaId;
	}

	public void setTpaId(Integer tpaId) {
		this.tpaId = tpaId;
	}

	public String getTpaNombre() {
		return this.tpaNombre;
	}

	public void setTpaNombre(String tpaNombre) {
		this.tpaNombre = tpaNombre;
	}

	public String getTpaDescripcion() {
		return this.tpaDescripcion;
	}

	public void setTpaDescripcion(String tpaDescripcion) {
		this.tpaDescripcion = tpaDescripcion;
	}

	public Set getConPcoPartidaContables() {
		return this.conPcoPartidaContables;
	}

	public void setConPcoPartidaContables(Set conPcoPartidaContables) {
		this.conPcoPartidaContables = conPcoPartidaContables;
	}

	public String getTpaCorrelativoInicio() {
		return tpaCorrelativoInicio;
	}

	public void setTpaCorrelativoInicio(String tpaCorrelativoInicio) {
		this.tpaCorrelativoInicio = tpaCorrelativoInicio;
	}

}