package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvTarTipoArticulo entity provides the base persistence definition of
 * the InvTarTipoArticulo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvTarTipoArticulo implements
		java.io.Serializable {

	// Fields

	private String tarId;
	private String tarNombre;
	private String tarDescripcion;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invArtArticulos = new HashSet(0);
	private Set iucTutTarTprs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvTarTipoArticulo() {
	}

	/** minimal constructor */
	public AbstractInvTarTipoArticulo(String tarId, String tarNombre,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.tarId = tarId;
		this.tarNombre = tarNombre;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractInvTarTipoArticulo(String tarId, String tarNombre,
			String tarDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invArtArticulos,
			Set iucTutTarTprs) {
		super();
		this.tarId = tarId;
		this.tarNombre = tarNombre;
		this.tarDescripcion = tarDescripcion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invArtArticulos = invArtArticulos;
		this.iucTutTarTprs = iucTutTarTprs;
	}

	// Property accessors

	public String getTarId() {
		return this.tarId;
	}

	public void setTarId(String tarId) {
		this.tarId = tarId;
	}

	public String getTarNombre() {
		return this.tarNombre;
	}

	public void setTarNombre(String tarNombre) {
		this.tarNombre = tarNombre;
	}

	public String getTarDescripcion() {
		return this.tarDescripcion;
	}

	public void setTarDescripcion(String tarDescripcion) {
		this.tarDescripcion = tarDescripcion;
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

	public Set getInvArtArticulos() {
		return this.invArtArticulos;
	}

	public void setInvArtArticulos(Set invArtArticulos) {
		this.invArtArticulos = invArtArticulos;
	}

	public Set getIucTutTarTprs() {
		return iucTutTarTprs;
	}

	public void setIucTutTarTprs(Set iucTutTarTprs) {
		this.iucTutTarTprs = iucTutTarTprs;
	}

}