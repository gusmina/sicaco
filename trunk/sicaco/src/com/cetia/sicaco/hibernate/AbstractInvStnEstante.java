package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvStnEstante entity provides the base persistence definition of the
 * InvStnEstante entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvStnEstante implements java.io.Serializable {

	// Fields

	private Integer stnId;
	private InvBodBodegas invBodBodegas = new InvBodBodegas();
	private Integer stnCantFilas;
	private Integer stnCantColumnas;
	private String stnEstado;
	private String stnPosicion;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private String stnCodigo;
	private Set invNivNivels = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvStnEstante() {
	}

	/** minimal constructor */
	public AbstractInvStnEstante(Integer stnId, InvBodBodegas invBodBodegas,
			Integer stnCantFilas, Integer stnCantColumnas, String stnEstado,
			String stnPosicion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String stnCodigo) {
		this.stnId = stnId;
		this.invBodBodegas = invBodBodegas;
		this.stnCantFilas = stnCantFilas;
		this.stnCantColumnas = stnCantColumnas;
		this.stnEstado = stnEstado;
		this.stnPosicion = stnPosicion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.stnCodigo = stnCodigo;
	}

	/** full constructor */
	public AbstractInvStnEstante(Integer stnId, InvBodBodegas invBodBodegas,
			Integer stnCantFilas, Integer stnCantColumnas, String stnEstado,
			String stnPosicion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String stnCodigo, Set invNivNivels) {
		this.stnId = stnId;
		this.invBodBodegas = invBodBodegas;
		this.stnCantFilas = stnCantFilas;
		this.stnCantColumnas = stnCantColumnas;
		this.stnEstado = stnEstado;
		this.stnPosicion = stnPosicion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.stnCodigo = stnCodigo;
		this.invNivNivels = invNivNivels;
	}

	// Property accessors

	public Integer getStnId() {
		return this.stnId;
	}

	public void setStnId(Integer stnId) {
		this.stnId = stnId;
	}

	public InvBodBodegas getInvBodBodegas() {
		return this.invBodBodegas;
	}

	public void setInvBodBodegas(InvBodBodegas invBodBodegas) {
		this.invBodBodegas = invBodBodegas;
	}

	public Integer getStnCantFilas() {
		return this.stnCantFilas;
	}

	public void setStnCantFilas(Integer stnCantFilas) {
		this.stnCantFilas = stnCantFilas;
	}

	public Integer getStnCantColumnas() {
		return this.stnCantColumnas;
	}

	public void setStnCantColumnas(Integer stnCantColumnas) {
		this.stnCantColumnas = stnCantColumnas;
	}

	public String getStnEstado() {
		return this.stnEstado;
	}

	public void setStnEstado(String stnEstado) {
		this.stnEstado = stnEstado;
	}

	public String getStnPosicion() {
		return this.stnPosicion;
	}

	public void setStnPosicion(String stnPosicion) {
		this.stnPosicion = stnPosicion;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return this.audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return this.audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public String getStnCodigo() {
		return this.stnCodigo;
	}

	public void setStnCodigo(String stnCodigo) {
		this.stnCodigo = stnCodigo;
	}

	public Set getInvNivNivels() {
		return this.invNivNivels;
	}

	public void setInvNivNivels(Set invNivNivels) {
		this.invNivNivels = invNivNivels;
	}

}