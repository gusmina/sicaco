package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * AbstractInvCprContactoProveedor entity provides the base persistence
 * definition of the InvCprContactoProveedor entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvCprContactoProveedor implements
		java.io.Serializable {

	// Fields

	private Integer cprId;
	private InvProProveedor invProProveedor;
	private String cprNombreCompleto;
	private String cprNumeroTelOficina;
	private String cprNumeroTelCelular;
	private String cprNumeroFax;
	private String cprEmpresaCelular;
	private String cprEmail;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;

	// Constructors

	/** default constructor */
	public AbstractInvCprContactoProveedor() {
	}

	/** minimal constructor */
	public AbstractInvCprContactoProveedor(Integer cprId,
			String cprNombreCompleto, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.cprId = cprId;
		this.cprNombreCompleto = cprNombreCompleto;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** full constructor */
	public AbstractInvCprContactoProveedor(Integer cprId,
			InvProProveedor invProProveedor, String cprNombreCompleto,
			String cprNumeroTelOficina, String cprNumeroTelCelular,
			String cprEmpresaCelular, String cprNumeroFax,
			String cprEmail, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.cprId = cprId;
		this.invProProveedor = invProProveedor;
		this.cprNombreCompleto = cprNombreCompleto;
		this.cprNumeroTelOficina = cprNumeroTelOficina;
		this.cprNumeroTelCelular = cprNumeroTelCelular;
		this.cprNumeroFax = cprNumeroFax;
		this.cprEmpresaCelular = cprEmpresaCelular;
		this.cprEmail = cprEmail;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	// Property accessors

	public Integer getCprId() {
		return this.cprId;
	}

	public void setCprId(Integer cprId) {
		this.cprId = cprId;
	}

	public InvProProveedor getInvProProveedor() {
		return this.invProProveedor;
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		this.invProProveedor = invProProveedor;
	}

	public String getCprNombreCompleto() {
		return this.cprNombreCompleto;
	}

	public void setCprNombreCompleto(String cprNombreCompleto) {
		this.cprNombreCompleto = cprNombreCompleto;
	}

	public String getCprNumeroTelOficina() {
		return this.cprNumeroTelOficina;
	}

	public void setCprNumeroTelOficina(String cprNumeroTelOficina) {
		this.cprNumeroTelOficina = cprNumeroTelOficina;
	}

	public String getCprNumeroTelCelular() {
		return this.cprNumeroTelCelular;
	}

	public void setCprNumeroTelCelular(String cprNumeroTelCelular) {
		this.cprNumeroTelCelular = cprNumeroTelCelular;
	}

	public String getCprEmpresaCelular() {
		return this.cprEmpresaCelular;
	}

	public void setCprEmpresaCelular(String cprEmpresaCelular) {
		this.cprEmpresaCelular = cprEmpresaCelular;
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

	public String getCprNumeroFax() {
		return cprNumeroFax;
	}

	public void setCprNumeroFax(String cprNumeroFax) {
		this.cprNumeroFax = cprNumeroFax;
	}

	public String getCprEmail() {
		return cprEmail;
	}

	public void setCprEmail(String cprEmail) {
		this.cprEmail = cprEmail;
	}

}