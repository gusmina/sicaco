package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractFacCliCliente entity provides the base persistence definition of the
 * FacCliCliente entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFacCliCliente implements java.io.Serializable {

	// Fields

	private String cliCodigo;
	private String cliNombre;
	private String cliDireccion;
	private String cliNumRegistro;
	private String cliMunicipio;
	private String cliDepartamento;
	private String cliGiro;
	private String cliContribuyente;
	private Integer cliDeclaraIva;
	private Set facFenFacturaEncabezados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractFacCliCliente() {
	}

	/** minimal constructor */
	public AbstractFacCliCliente(String cliCodigo, String cliNombre,
			String cliDireccion, String cliMunicipio, String cliDepartamento) {
		this.cliCodigo = cliCodigo;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliMunicipio = cliMunicipio;
		this.cliDepartamento = cliDepartamento;
	}

	/** full constructor */
	public AbstractFacCliCliente(String cliCodigo, String cliNombre,
			String cliDireccion, String cliNumRegistro, String cliMunicipio,
			String cliDepartamento, String cliGiro, String cliContribuyente,
			Integer cliDeclaraIva, Set facFenFacturaEncabezados) {
		super();
		this.cliCodigo = cliCodigo;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliNumRegistro = cliNumRegistro;
		this.cliMunicipio = cliMunicipio;
		this.cliDepartamento = cliDepartamento;
		this.cliGiro = cliGiro;
		this.cliContribuyente = cliContribuyente;
		this.cliDeclaraIva = cliDeclaraIva;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}
	
	// Property accessors

	public String getCliCodigo() {
		return this.cliCodigo;
	}

	public void setCliCodigo(String cliCodigo) {
		this.cliCodigo = cliCodigo;
	}

	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliNumRegistro() {
		return this.cliNumRegistro;
	}

	public void setCliNumRegistro(String cliNumRegistro) {
		this.cliNumRegistro = cliNumRegistro;
	}

	public String getCliMunicipio() {
		return this.cliMunicipio;
	}

	public void setCliMunicipio(String cliMunicipio) {
		this.cliMunicipio = cliMunicipio;
	}

	public String getCliDepartamento() {
		return this.cliDepartamento;
	}

	public void setCliDepartamento(String cliDepartamento) {
		this.cliDepartamento = cliDepartamento;
	}

	public String getCliGiro() {
		return this.cliGiro;
	}

	public void setCliGiro(String cliGiro) {
		this.cliGiro = cliGiro;
	}

	public String getCliContribuyente() {
		return cliContribuyente;
	}

	public void setCliContribuyente(String cliContribuyente) {
		this.cliContribuyente = cliContribuyente;
	}

	public Integer getCliDeclaraIva() {
		return cliDeclaraIva;
	}

	public void setCliDeclaraIva(Integer cliDeclaraIva) {
		this.cliDeclaraIva = cliDeclaraIva;
	}

	public Set getFacFenFacturaEncabezados() {
		return facFenFacturaEncabezados;
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}

}