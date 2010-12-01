package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaPxtPersonaExterna entity provides the base persistence definition
 * of the CtaPxtPersonaExterna entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaPxtPersonaExterna implements
		java.io.Serializable {

	// Fields

	private String pxtId;
	private String pxtPrimerApellido;
	private String pxtSegundoApellido;
	private String pxtNombres;
	private String pxtDireccion;
	private Float pxtSalario;
	private String pxtDui;
	private String pxtTrabajo;
	private String pxtJefeInmediato;
	private String pxtTelefonoCasa;
	private String pxtTelefonoOficina;
	private String pxtCodigoEmpleado;
	private String pxtEmpresa;
	private String pxtEmail;
	private Set ctaFxpFiadorPrestamos = new HashSet(0);
	private Set ctaCasCuentaAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCtaPxtPersonaExterna() {
	}

	/** minimal constructor */
	public AbstractCtaPxtPersonaExterna(String pxtId, String pxtPrimerApellido,
			String pxtNombres, String pxtDireccion, Float pxtSalario,
			String pxtDui, String pxtTrabajo, String pxtTelefonoCasa) {
		this.pxtId = pxtId;
		this.pxtPrimerApellido = pxtPrimerApellido;
		this.pxtNombres = pxtNombres;
		this.pxtDireccion = pxtDireccion;
		this.pxtSalario = pxtSalario;
		this.pxtDui = pxtDui;
		this.pxtTrabajo = pxtTrabajo;
		this.pxtTelefonoCasa = pxtTelefonoCasa;
	}

	/** full constructor */
	public AbstractCtaPxtPersonaExterna(String pxtId, String pxtPrimerApellido,
			String pxtSegundoApellido, String pxtNombres, String pxtDireccion,
			Float pxtSalario, String pxtDui, String pxtTrabajo,
			String pxtJefeInmediato, String pxtTelefonoCasa,
			String pxtTelefonoOficina, String pxtCodigoEmpleado,
			String pxtEmpresa, String pxtEmail, Set ctaFxpFiadorPrestamos,
			Set ctaCasCuentaAsociados) {
		this.pxtId = pxtId;
		this.pxtPrimerApellido = pxtPrimerApellido;
		this.pxtSegundoApellido = pxtSegundoApellido;
		this.pxtNombres = pxtNombres;
		this.pxtDireccion = pxtDireccion;
		this.pxtSalario = pxtSalario;
		this.pxtDui = pxtDui;
		this.pxtTrabajo = pxtTrabajo;
		this.pxtJefeInmediato = pxtJefeInmediato;
		this.pxtTelefonoCasa = pxtTelefonoCasa;
		this.pxtTelefonoOficina = pxtTelefonoOficina;
		this.pxtCodigoEmpleado = pxtCodigoEmpleado;
		this.pxtEmpresa = pxtEmpresa;
		this.pxtEmail = pxtEmail;
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

	// Property accessors

	public String getPxtId() {
		return this.pxtId;
	}

	public void setPxtId(String pxtId) {
		this.pxtId = pxtId;
	}

	public String getPxtPrimerApellido() {
		return this.pxtPrimerApellido;
	}

	public void setPxtPrimerApellido(String pxtPrimerApellido) {
		this.pxtPrimerApellido = pxtPrimerApellido;
	}

	public String getPxtSegundoApellido() {
		return this.pxtSegundoApellido;
	}

	public void setPxtSegundoApellido(String pxtSegundoApellido) {
		this.pxtSegundoApellido = pxtSegundoApellido;
	}

	public String getPxtNombres() {
		return this.pxtNombres;
	}

	public void setPxtNombres(String pxtNombres) {
		this.pxtNombres = pxtNombres;
	}

	public String getPxtDireccion() {
		return this.pxtDireccion;
	}

	public void setPxtDireccion(String pxtDireccion) {
		this.pxtDireccion = pxtDireccion;
	}

	public Float getPxtSalario() {
		return this.pxtSalario;
	}

	public void setPxtSalario(Float pxtSalario) {
		this.pxtSalario = pxtSalario;
	}

	public String getPxtDui() {
		return this.pxtDui;
	}

	public void setPxtDui(String pxtDui) {
		this.pxtDui = pxtDui;
	}

	public String getPxtTrabajo() {
		return this.pxtTrabajo;
	}

	public void setPxtTrabajo(String pxtTrabajo) {
		this.pxtTrabajo = pxtTrabajo;
	}

	public String getPxtJefeInmediato() {
		return this.pxtJefeInmediato;
	}

	public void setPxtJefeInmediato(String pxtJefeInmediato) {
		this.pxtJefeInmediato = pxtJefeInmediato;
	}

	public String getPxtTelefonoCasa() {
		return this.pxtTelefonoCasa;
	}

	public void setPxtTelefonoCasa(String pxtTelefonoCasa) {
		this.pxtTelefonoCasa = pxtTelefonoCasa;
	}

	public String getPxtTelefonoOficina() {
		return this.pxtTelefonoOficina;
	}

	public void setPxtTelefonoOficina(String pxtTelefonoOficina) {
		this.pxtTelefonoOficina = pxtTelefonoOficina;
	}

	public Set getCtaFxpFiadorPrestamos() {
		return this.ctaFxpFiadorPrestamos;
	}

	public void setCtaFxpFiadorPrestamos(Set ctaFxpFiadorPrestamos) {
		this.ctaFxpFiadorPrestamos = ctaFxpFiadorPrestamos;
	}

	public String getPxtCodigoEmpleado() {
		return pxtCodigoEmpleado;
	}

	public void setPxtCodigoEmpleado(String pxtCodigoEmpleado) {
		this.pxtCodigoEmpleado = pxtCodigoEmpleado;
	}

	public String getPxtEmpresa() {
		return pxtEmpresa;
	}

	public void setPxtEmpresa(String pxtEmpresa) {
		this.pxtEmpresa = pxtEmpresa;
	}

	public String getPxtEmail() {
		return pxtEmail;
	}

	public void setPxtEmail(String pxtEmail) {
		this.pxtEmail = pxtEmail;
	}

	public Set getCtaCasCuentaAsociados() {
		return ctaCasCuentaAsociados;
	}

	public void setCtaCasCuentaAsociados(Set ctaCasCuentaAsociados) {
		this.ctaCasCuentaAsociados = ctaCasCuentaAsociados;
	}

}