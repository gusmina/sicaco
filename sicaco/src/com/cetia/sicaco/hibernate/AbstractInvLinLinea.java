package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvLinLinea entity provides the base persistence definition of the
 * InvLinLinea entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvLinLinea implements java.io.Serializable {

	// Fields

	private Integer linId;
	private String linCodigo;
	private String linNombre;
	private String linDescripcion;
	private Double linUtilidad;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invArtArticulos = new HashSet(0);

	// Constructors


	/** default constructor */
	public AbstractInvLinLinea() {
	}

	/** minimal constructor */
	public AbstractInvLinLinea(Integer linId, String linCodigo,
			String linNombre, String linDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super();
		this.linId = linId;
		this.linCodigo = linCodigo;
		this.linNombre = linNombre;
		this.linDescripcion = linDescripcion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */

	public AbstractInvLinLinea(Integer linId, String linCodigo,
			String linNombre, String linDescripcion, Double linUtilidad,
			Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invArtArticulos) {
		super();
		this.linId = linId;
		this.linCodigo = linCodigo;
		this.linNombre = linNombre;
		this.linDescripcion = linDescripcion;
		this.linUtilidad = linUtilidad;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invArtArticulos = invArtArticulos;
	}

	public Integer getLinId() {
		return linId;
	}

	public void setLinId(Integer linId) {
		this.linId = linId;
	}

	public String getLinCodigo() {
		return linCodigo;
	}

	public void setLinCodigo(String linCodigo) {
		this.linCodigo = linCodigo;
	}

	public String getLinNombre() {
		return linNombre;
	}

	public void setLinNombre(String linNombre) {
		this.linNombre = linNombre;
	}

	public String getLinDescripcion() {
		return linDescripcion;
	}

	public void setLinDescripcion(String linDescripcion) {
		this.linDescripcion = linDescripcion;
	}

	public Date getAudFechaCreacion() {
		return audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public Set getInvArtArticulos() {
		return invArtArticulos;
	}

	public void setInvArtArticulos(Set invArtArticulos) {
		this.invArtArticulos = invArtArticulos;
	}

	public Double getLinUtilidad() {
		return linUtilidad;
	}

	public void setLinUtilidad(Double linUtilidad) {
		this.linUtilidad = linUtilidad;
	}

	// Property accessors


}