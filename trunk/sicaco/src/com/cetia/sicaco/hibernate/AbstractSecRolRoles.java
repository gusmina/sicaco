package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecRolRoles entity provides the base persistence definition of the
 * SecRolRoles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecRolRoles implements java.io.Serializable {

	// Fields

	private String rolNombre;
	private String rolDescripcion;
	private String rolTipoSesion;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set secIseInicioSesions = new HashSet(0);
	private Set secRopRolMenus = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecRolRoles() {
	}

	/** minimal constructor */
	public AbstractSecRolRoles(String rolNombre, String rolDescripcion,
			String rolTipoSesion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.rolNombre = rolNombre;
		this.rolDescripcion = rolDescripcion;
		this.rolTipoSesion = rolTipoSesion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractSecRolRoles(String rolNombre, String rolDescripcion,
			String rolTipoSesion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set secIseInicioSesions,
			Set secRopRolMenus) {
		this.rolNombre = rolNombre;
		this.rolDescripcion = rolDescripcion;
		this.rolTipoSesion = rolTipoSesion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.secIseInicioSesions = secIseInicioSesions;
		this.secRopRolMenus = secRopRolMenus;
	}

	// Property accessors

	public String getRolNombre() {
		return this.rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public String getRolDescripcion() {
		return this.rolDescripcion;
	}

	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}

	public String getRolTipoSesion() {
		return this.rolTipoSesion;
	}

	public void setRolTipoSesion(String rolTipoSesion) {
		this.rolTipoSesion = rolTipoSesion;
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

	public Set getSecIseInicioSesions() {
		return this.secIseInicioSesions;
	}

	public void setSecIseInicioSesions(Set secIseInicioSesions) {
		this.secIseInicioSesions = secIseInicioSesions;
	}

	public Set getSecRopRolMenus() {
		return this.secRopRolMenus;
	}

	public void setSecRopRolMenus(Set secRopRolMenus) {
		this.secRopRolMenus = secRopRolMenus;
	}

}