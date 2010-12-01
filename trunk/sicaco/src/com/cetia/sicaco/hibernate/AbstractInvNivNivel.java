package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvNivNivel entity provides the base persistence definition of the
 * InvNivNivel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvNivNivel implements java.io.Serializable {

	// Fields

	private InvNivNivelId id;
	private String nivEstado;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set invNxmNivelMovimientos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvNivNivel() {
	}

	/** minimal constructor */
	public AbstractInvNivNivel(InvNivNivelId id, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.id = id;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractInvNivNivel(InvNivNivelId id, String nivEstado,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set invNxmNivelMovimientos) {
		this.id = id;
		this.nivEstado = nivEstado;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.invNxmNivelMovimientos = invNxmNivelMovimientos;
	}

	// Property accessors

	public InvNivNivelId getId() {
		return this.id;
	}

	public void setId(InvNivNivelId id) {
		this.id = id;
	}

	public String getNivEstado() {
		return this.nivEstado;
	}

	public void setNivEstado(String nivEstado) {
		this.nivEstado = nivEstado;
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

	public Set getInvNxmNivelMovimientos() {
		return this.invNxmNivelMovimientos;
	}

	public void setInvNxmNivelMovimientos(Set invNxmNivelMovimientos) {
		this.invNxmNivelMovimientos = invNxmNivelMovimientos;
	}

}