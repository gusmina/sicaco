package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtaBenBeneficiarios entity provides the base persistence definition
 * of the CtaBenBeneficiarios entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaBenBeneficiarios implements
		java.io.Serializable {

	// Fields

	private Integer benId;
	private SecParParentesco secParParentesco = new SecParParentesco();
	private CtaAscAsociado ctaAscAsociado = new CtaAscAsociado();
	private String benPrimerNombre;
	private String benPrimerApellido;
	private String benSegundoNombre;
	private String benSegundoApellido;
	private String benApellidoCasada;
	private Date benFechaNacimiento;
	private String benSexo;
	private String benTelefono;
	private String benEstado;
	private String benHijo;
	private String benNombreCompleto;
	private Set ctaBxcBeneficiariosCuentas = new HashSet(0);
	
	// Constructors

	/** default constructor */
	public AbstractCtaBenBeneficiarios() {
	}

	/** minimal constructor */
	public AbstractCtaBenBeneficiarios(String benPrimerNombre,
			CtaAscAsociado ctaAscAsociado,String benPrimerApellido, String benTelefono, String benEstado,
			String benHijo) {
		this.benPrimerNombre = benPrimerNombre;
		this.benPrimerApellido = benPrimerApellido;
		this.benTelefono = benTelefono;
		this.benEstado = benEstado;
		this.benHijo = benHijo;
		this.ctaAscAsociado = ctaAscAsociado;
	}

	/** full constructor */
	public AbstractCtaBenBeneficiarios(SecParParentesco secParParentesco, CtaAscAsociado ctaAscAsociado,
			String benPrimerNombre, String benPrimerApellido,
			String benSegundoNombre, String benSegundoApellido,
			String benApellidoCasada, 
			Date benFechaNacimiento, String benSexo, String benTelefono,
			String benEstado, String benHijo, String benNombreCompleto,
			Set ctaBxcBeneficiariosCuentas) {
		this.secParParentesco = secParParentesco;
		this.ctaAscAsociado = ctaAscAsociado;
		this.benPrimerNombre = benPrimerNombre;
		this.benPrimerApellido = benPrimerApellido;
		this.benSegundoNombre = benSegundoNombre;
		this.benSegundoApellido = benSegundoApellido;
		this.benApellidoCasada = benApellidoCasada;
		this.benFechaNacimiento = benFechaNacimiento;
		this.benSexo = benSexo;
		this.benTelefono = benTelefono;
		this.benEstado = benEstado;
		this.benHijo = benHijo;
		this.benNombreCompleto = benNombreCompleto;
		this.ctaBxcBeneficiariosCuentas = ctaBxcBeneficiariosCuentas;
	}

	// Property accessors

	public Integer getBenId() {
		return this.benId;
	}

	public void setBenId(Integer benId) {
		this.benId = benId;
	}

	public SecParParentesco getSecParParentesco() {
		return this.secParParentesco;
	}

	public void setSecParParentesco(SecParParentesco secParParentesco) {
		this.secParParentesco = secParParentesco;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return this.ctaAscAsociado;
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		this.ctaAscAsociado = ctaAscAsociado;
	}
	
	public String getBenPrimerNombre() {
		return this.benPrimerNombre;
	}

	public void setBenPrimerNombre(String benPrimerNombre) {
		this.benPrimerNombre = benPrimerNombre;
	}

	public String getBenPrimerApellido() {
		return this.benPrimerApellido;
	}

	public void setBenPrimerApellido(String benPrimerApellido) {
		this.benPrimerApellido = benPrimerApellido;
	}

	public String getBenSegundoNombre() {
		return this.benSegundoNombre;
	}

	public void setBenSegundoNombre(String benSegundoNombre) {
		this.benSegundoNombre = benSegundoNombre;
	}

	public String getBenSegundoApellido() {
		return this.benSegundoApellido;
	}

	public void setBenSegundoApellido(String benSegundoApellido) {
		this.benSegundoApellido = benSegundoApellido;
	}

	public String getBenApellidoCasada() {
		return benApellidoCasada;
	}

	public void setBenApellidoCasada(String benApellidoCasada) {
		this.benApellidoCasada = benApellidoCasada;
	}

	public Date getBenFechaNacimiento() {
		return this.benFechaNacimiento;
	}

	public void setBenFechaNacimiento(Date benFechaNacimiento) {
		this.benFechaNacimiento = benFechaNacimiento;
	}

	public String getBenSexo() {
		return this.benSexo;
	}

	public void setBenSexo(String benSexo) {
		this.benSexo = benSexo;
	}

	public String getBenTelefono() {
		return this.benTelefono;
	}

	public void setBenTelefono(String benTelefono) {
		this.benTelefono = benTelefono;
	}

	public String getBenEstado() {
		return this.benEstado;
	}

	public void setBenEstado(String benEstado) {
		this.benEstado = benEstado;
	}

	public String getBenHijo() {
		return this.benHijo;
	}

	public void setBenHijo(String benHijo) {
		this.benHijo = benHijo;
	}

	public String getBenNombreCompleto() {
		return benNombreCompleto;
	}

	public void setBenNombreCompleto(String benNombreCompleto) {
		this.benNombreCompleto = benNombreCompleto;
	}

	public Set getCtaBxcBeneficiariosCuentas() {
		return this.ctaBxcBeneficiariosCuentas;
	}

	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		this.ctaBxcBeneficiariosCuentas = ctaBxcBeneficiariosCuentas;
	}

}