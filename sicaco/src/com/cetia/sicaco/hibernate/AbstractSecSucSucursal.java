package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecSucSucursal entity provides the base persistence definition of the
 * SecSucSucursal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecSucSucursal implements java.io.Serializable {

	// Fields

	private Integer sucId;
	private String sucNombre;
	private String sucDireccion;
	private String sucEstado;
	private Set secPerPersonas = new HashSet(0);
	private Set invBodBodegases = new HashSet(0);
	private Set facFenFacturaEncabezados = new HashSet(0);
	private Set ctrRckRepositorioChequeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecSucSucursal() {
	}

	/** minimal constructor */
	public AbstractSecSucSucursal(Integer sucId, String sucNombre,
			String sucDireccion, String sucEstado) {
		this.sucId = sucId;
		this.sucNombre = sucNombre;
		this.sucDireccion = sucDireccion;
		this.sucEstado = sucEstado;
	}

	/** full constructor */
	public AbstractSecSucSucursal(Integer sucId, String sucNombre,
			String sucDireccion, String sucEstado, Set secPerPersonas,
			Set invBodBodegases, Set facFenFacturaEncabezados,
			Set ctrRckRepositorioChequeses) {
		this.sucId = sucId;
		this.sucNombre = sucNombre;
		this.sucDireccion = sucDireccion;
		this.sucEstado = sucEstado;
		this.secPerPersonas = secPerPersonas;
		this.invBodBodegases = invBodBodegases;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
		this.ctrRckRepositorioChequeses = ctrRckRepositorioChequeses;
	}

	// Property accessors

	public Integer getSucId() {
		return this.sucId;
	}

	public void setSucId(Integer sucId) {
		this.sucId = sucId;
	}

	public String getSucNombre() {
		return this.sucNombre;
	}

	public void setSucNombre(String sucNombre) {
		this.sucNombre = sucNombre;
	}

	public String getSucDireccion() {
		return this.sucDireccion;
	}

	public void setSucDireccion(String sucDireccion) {
		this.sucDireccion = sucDireccion;
	}

	public String getSucEstado() {
		return this.sucEstado;
	}

	public void setSucEstado(String sucEstado) {
		this.sucEstado = sucEstado;
	}

	public Set getSecPerPersonas() {
		return this.secPerPersonas;
	}

	public void setSecPerPersonas(Set secPerPersonas) {
		this.secPerPersonas = secPerPersonas;
	}

	public Set getInvBodBodegases() {
		return invBodBodegases;
	}

	public void setInvBodBodegases(Set invBodBodegases) {
		this.invBodBodegases = invBodBodegases;
	}

	public Set getFacFenFacturaEncabezados() {
		return facFenFacturaEncabezados;
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}
	
	public Set getCtrRckRepositorioChequeses() {
		return this.ctrRckRepositorioChequeses;
	}

	public void setCtrRckRepositorioChequeses(Set ctrRckRepositorioChequeses) {
		this.ctrRckRepositorioChequeses = ctrRckRepositorioChequeses;
	}

}