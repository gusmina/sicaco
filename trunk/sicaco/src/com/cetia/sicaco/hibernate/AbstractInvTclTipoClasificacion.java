package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvTclTipoClasificacion entity provides the base persistence
 * definition of the InvTclTipoClasificacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvTclTipoClasificacion implements
		java.io.Serializable {

	// Fields

	private String tclClasificacion;
	private String tclDescripcion;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invClaClasificados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvTclTipoClasificacion() {
	}

	/** minimal constructor */
	public AbstractInvTclTipoClasificacion(String tclClasificacion,
			String tclDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.tclClasificacion = tclClasificacion;
		this.tclDescripcion = tclDescripcion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractInvTclTipoClasificacion(String tclClasificacion,
			String tclDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set invClaClasificados) {
		this.tclClasificacion = tclClasificacion;
		this.tclDescripcion = tclDescripcion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invClaClasificados = invClaClasificados;
	}

	// Property accessors

	public String getTclClasificacion() {
		return this.tclClasificacion;
	}

	public void setTclClasificacion(String tclClasificacion) {
		this.tclClasificacion = tclClasificacion;
	}

	public String getTclDescripcion() {
		return this.tclDescripcion;
	}

	public void setTclDescripcion(String tclDescripcion) {
		this.tclDescripcion = tclDescripcion;
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

	public Set getInvClaClasificados() {
		return this.invClaClasificados;
	}

	public void setInvClaClasificados(Set invClaClasificados) {
		this.invClaClasificados = invClaClasificados;
	}

}