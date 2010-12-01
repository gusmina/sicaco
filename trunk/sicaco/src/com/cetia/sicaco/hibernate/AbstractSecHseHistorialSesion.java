package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractSecHseHistorialSesion entity provides the base persistence definition
 * of the SecHseHistorialSesion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecHseHistorialSesion implements
		java.io.Serializable {

	// Fields

	private Integer hseId;
	private SecIseInicioSesion secIseInicioSesion;
	private Date hseFechaAcceso;
	private String hseIp;
	private Date hseFechaSalida;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractSecHseHistorialSesion() {
	}

	/** minimal constructor */
	public AbstractSecHseHistorialSesion(Integer hseId, Date hseFechaAcceso,
			String hseIp, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		this.hseId = hseId;
		this.hseFechaAcceso = hseFechaAcceso;
		this.hseIp = hseIp;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractSecHseHistorialSesion(Integer hseId,
			SecIseInicioSesion secIseInicioSesion, Date hseFechaAcceso,
			String hseIp, Date hseFechaSalida, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.hseId = hseId;
		this.secIseInicioSesion = secIseInicioSesion;
		this.hseFechaAcceso = hseFechaAcceso;
		this.hseIp = hseIp;
		this.hseFechaSalida = hseFechaSalida;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public Integer getHseId() {
		return this.hseId;
	}

	public void setHseId(Integer hseId) {
		this.hseId = hseId;
	}

	public SecIseInicioSesion getSecIseInicioSesion() {
		return this.secIseInicioSesion;
	}

	public void setSecIseInicioSesion(SecIseInicioSesion secIseInicioSesion) {
		this.secIseInicioSesion = secIseInicioSesion;
	}

	public Date getHseFechaAcceso() {
		return this.hseFechaAcceso;
	}

	public void setHseFechaAcceso(Date hseFechaAcceso) {
		this.hseFechaAcceso = hseFechaAcceso;
	}

	public String getHseIp() {
		return this.hseIp;
	}

	public void setHseIp(String hseIp) {
		this.hseIp = hseIp;
	}

	public Date getHseFechaSalida() {
		return this.hseFechaSalida;
	}

	public void setHseFechaSalida(Date hseFechaSalida) {
		this.hseFechaSalida = hseFechaSalida;
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

}