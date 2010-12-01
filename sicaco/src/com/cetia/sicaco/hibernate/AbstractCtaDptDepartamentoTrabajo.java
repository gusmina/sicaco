package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaDptDepartamentoTrabajo entity provides the base persistence
 * definition of the CtaDptDepartamentoTrabajo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaDptDepartamentoTrabajo implements
		java.io.Serializable {

	// Fields

	private Integer dptId;
	private CtaEtrEmpresaTrabajo ctaEtrEmpresaTrabajo = new CtaEtrEmpresaTrabajo();
	private String dptNombre;
	private String dptUbicacion;
	private String dptDescripcion;
	private String dptEstado;
	private String dptCentroCosto;
	private Set ctaAscAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaDptDepartamentoTrabajo() {
	}

	/** minimal constructor */
	public AbstractCtaDptDepartamentoTrabajo(Integer dptId, String dptNombre,
			String dptUbicacion, String dptEstado) {
		this.dptId = dptId;
		this.dptNombre = dptNombre;
		this.dptUbicacion = dptUbicacion;
		this.dptEstado = dptEstado;
	}

	/** full constructor */
	public AbstractCtaDptDepartamentoTrabajo(Integer dptId,
			CtaEtrEmpresaTrabajo ctaEtrEmpresaTrabajo, String dptNombre,
			String dptUbicacion, String dptDescripcion, String dptEstado,
			String dptCentroCosto, Set ctaAscAsociados) {
		this.dptId = dptId;
		this.ctaEtrEmpresaTrabajo = ctaEtrEmpresaTrabajo;
		this.dptNombre = dptNombre;
		this.dptUbicacion = dptUbicacion;
		this.dptDescripcion = dptDescripcion;
		this.dptEstado = dptEstado;
		this.dptCentroCosto = dptCentroCosto;
		this.ctaAscAsociados = ctaAscAsociados;
	}

	// Property accessors

	public Integer getDptId() {
		return this.dptId;
	}

	public void setDptId(Integer dptId) {
		this.dptId = dptId;
	}

	public CtaEtrEmpresaTrabajo getCtaEtrEmpresaTrabajo() {
		return this.ctaEtrEmpresaTrabajo;
	}

	public void setCtaEtrEmpresaTrabajo(
			CtaEtrEmpresaTrabajo ctaEtrEmpresaTrabajo) {
		this.ctaEtrEmpresaTrabajo = ctaEtrEmpresaTrabajo;
	}

	public String getDptNombre() {
		return this.dptNombre;
	}

	public void setDptNombre(String dptNombre) {
		this.dptNombre = dptNombre;
	}

	public String getDptUbicacion() {
		return this.dptUbicacion;
	}

	public void setDptUbicacion(String dptUbicacion) {
		this.dptUbicacion = dptUbicacion;
	}

	public String getDptDescripcion() {
		return this.dptDescripcion;
	}

	public void setDptDescripcion(String dptDescripcion) {
		this.dptDescripcion = dptDescripcion;
	}

	public String getDptEstado() {
		return this.dptEstado;
	}

	public void setDptEstado(String dptEstado) {
		this.dptEstado = dptEstado;
	}

	public String getDptCentroCosto() {
		return dptCentroCosto;
	}

	public void setDptCentroCosto(String dptCentroCosto) {
		this.dptCentroCosto = dptCentroCosto;
	}

	public Set getCtaAscAsociados() {
		return this.ctaAscAsociados;
	}

	public void setCtaAscAsociados(Set ctaAscAsociados) {
		this.ctaAscAsociados = ctaAscAsociados;
	}

}