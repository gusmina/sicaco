package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecIseInicioSesion entity provides the base persistence definition of
 * the SecIseInicioSesion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecIseInicioSesion implements
		java.io.Serializable {

	// Fields

	private String iseNombreUsuario;
	private SecRolRoles secRolRoles;
	private SecPerPersona secPerPersona;
	private String iseContrasenia;
	private Date iseFechaActivacion;
	private Date iseUltimaSesion;
	private String iseUltimaIp;
	private Integer iseVecesUtilizado;
	private Date iseFechaInactivacion;
	private String isePorqueInactivacion;
	private String iseTipoSesion;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set secHseHistorialSesions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecIseInicioSesion() {
	}

	/** minimal constructor */
	public AbstractSecIseInicioSesion(String iseNombreUsuario,
			String iseContrasenia, Date iseFechaActivacion,
			Integer iseVecesUtilizado, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.iseNombreUsuario = iseNombreUsuario;
		this.iseContrasenia = iseContrasenia;
		this.iseFechaActivacion = iseFechaActivacion;
		this.iseVecesUtilizado = iseVecesUtilizado;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractSecIseInicioSesion(String iseNombreUsuario,
			SecRolRoles secRolRoles, SecPerPersona secPerPersona,
			String iseContrasenia, Date iseFechaActivacion,
			Date iseUltimaSesion, String iseUltimaIp,
			Integer iseVecesUtilizado, Date iseFechaInactivacion,
			String isePorqueInactivacion, String iseTipoSesion,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set secHseHistorialSesions) {
		this.iseNombreUsuario = iseNombreUsuario;
		this.secRolRoles = secRolRoles;
		this.secPerPersona = secPerPersona;
		this.iseContrasenia = iseContrasenia;
		this.iseFechaActivacion = iseFechaActivacion;
		this.iseUltimaSesion = iseUltimaSesion;
		this.iseUltimaIp = iseUltimaIp;
		this.iseVecesUtilizado = iseVecesUtilizado;
		this.iseFechaInactivacion = iseFechaInactivacion;
		this.isePorqueInactivacion = isePorqueInactivacion;
		this.iseTipoSesion = iseTipoSesion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.secHseHistorialSesions = secHseHistorialSesions;
	}

	// Property accessors

	public String getIseNombreUsuario() {
		return this.iseNombreUsuario;
	}

	public void setIseNombreUsuario(String iseNombreUsuario) {
		this.iseNombreUsuario = iseNombreUsuario;
	}

	public SecRolRoles getSecRolRoles() {
		return this.secRolRoles;
	}

	public void setSecRolRoles(SecRolRoles secRolRoles) {
		this.secRolRoles = secRolRoles;
	}

	public SecPerPersona getSecPerPersona() {
		return this.secPerPersona;
	}

	public void setSecPerPersona(SecPerPersona secPerPersona) {
		this.secPerPersona = secPerPersona;
	}

	public String getIseContrasenia() {
		return this.iseContrasenia;
	}

	public void setIseContrasenia(String iseContrasenia) {
		this.iseContrasenia = iseContrasenia;
	}

	public Date getIseFechaActivacion() {
		return this.iseFechaActivacion;
	}

	public void setIseFechaActivacion(Date iseFechaActivacion) {
		this.iseFechaActivacion = iseFechaActivacion;
	}

	public Date getIseUltimaSesion() {
		return this.iseUltimaSesion;
	}

	public void setIseUltimaSesion(Date iseUltimaSesion) {
		this.iseUltimaSesion = iseUltimaSesion;
	}

	public String getIseUltimaIp() {
		return this.iseUltimaIp;
	}

	public void setIseUltimaIp(String iseUltimaIp) {
		this.iseUltimaIp = iseUltimaIp;
	}

	public Integer getIseVecesUtilizado() {
		return this.iseVecesUtilizado;
	}

	public void setIseVecesUtilizado(Integer iseVecesUtilizado) {
		this.iseVecesUtilizado = iseVecesUtilizado;
	}

	public Date getIseFechaInactivacion() {
		return this.iseFechaInactivacion;
	}

	public void setIseFechaInactivacion(Date iseFechaInactivacion) {
		this.iseFechaInactivacion = iseFechaInactivacion;
	}

	public String getIsePorqueInactivacion() {
		return this.isePorqueInactivacion;
	}

	public void setIsePorqueInactivacion(String isePorqueInactivacion) {
		this.isePorqueInactivacion = isePorqueInactivacion;
	}

	public String getIseTipoSesion() {
		return this.iseTipoSesion;
	}

	public void setIseTipoSesion(String iseTipoSesion) {
		this.iseTipoSesion = iseTipoSesion;
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

	public Set getSecHseHistorialSesions() {
		return this.secHseHistorialSesions;
	}

	public void setSecHseHistorialSesions(Set secHseHistorialSesions) {
		this.secHseHistorialSesions = secHseHistorialSesions;
	}

}