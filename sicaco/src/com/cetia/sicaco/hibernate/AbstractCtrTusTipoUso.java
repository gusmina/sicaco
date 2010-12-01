package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrTusTipoUso entity provides the base persistence definition of the
 * CtrTusTipoUso entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrTusTipoUso implements java.io.Serializable {

	// Fields

	private String tusCodigo;
	private String tusNombre;
	private String tusComentario;
	private Set ctrEstEstados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtrTusTipoUso() {
	}

	/** minimal constructor */
	public AbstractCtrTusTipoUso(String tusCodigo, String tusNombre) {
		this.tusCodigo = tusCodigo;
		this.tusNombre = tusNombre;
	}

	/** full constructor */
	public AbstractCtrTusTipoUso(String tusCodigo, String tusNombre,
			String tusComentario, Set ctrEstEstados) {
		this.tusCodigo = tusCodigo;
		this.tusNombre = tusNombre;
		this.tusComentario = tusComentario;
		this.ctrEstEstados = ctrEstEstados;
	}

	// Property accessors

	public String getTusCodigo() {
		return this.tusCodigo;
	}

	public void setTusCodigo(String tusCodigo) {
		this.tusCodigo = tusCodigo;
	}

	public String getTusNombre() {
		return this.tusNombre;
	}

	public void setTusNombre(String tusNombre) {
		this.tusNombre = tusNombre;
	}

	public String getTusComentario() {
		return this.tusComentario;
	}

	public void setTusComentario(String tusComentario) {
		this.tusComentario = tusComentario;
	}

	public Set getCtrEstEstados() {
		return this.ctrEstEstados;
	}

	public void setCtrEstEstados(Set ctrEstEstados) {
		this.ctrEstEstados = ctrEstEstados;
	}

}