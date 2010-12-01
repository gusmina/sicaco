package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCtrEmpEmpresa entity provides the base persistence definition of the
 * CtrEmpEmpresa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtrEmpEmpresa implements java.io.Serializable {

	// Fields

	private Integer empId;
	private String empNombre;
	private String empNit;
	private String empDireccion;
	private String empTel;
	private Date empIniOp;
	private String empRegistro;
	private String empGiro;
	private String audUsuarioCreacion;
	private Date audFechaCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private Set ctrCfcControlFacturacions = new HashSet(0);

	// Constructors

	public AbstractCtrEmpEmpresa(Integer empId, String empNombre,
			String empNit, String empDireccion, String empTel, Date empIniOp,
			String empRegistro, String empGiro, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		this.empId = empId;
		this.empNombre = empNombre;
		this.empNit = empNit;
		this.empDireccion = empDireccion;
		this.empTel = empTel;
		this.empIniOp = empIniOp;
		this.empRegistro = empRegistro;
		this.empGiro = empGiro;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	/** default constructor */
	public AbstractCtrEmpEmpresa() {
	}

	public AbstractCtrEmpEmpresa(Integer empId, String empNombre,
			String empNit, String empDireccion, String empTel, Date empIniOp,
			String empRegistro, String empGiro, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set ctrCfcControlFacturacions) {
		this.empId = empId;
		this.empNombre = empNombre;
		this.empNit = empNit;
		this.empDireccion = empDireccion;
		this.empTel = empTel;
		this.empIniOp = empIniOp;
		this.empRegistro = empRegistro;
		this.empGiro = empGiro;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaCreacion = audFechaCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.ctrCfcControlFacturacions = ctrCfcControlFacturacions;
	}


	// Property accessors

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpNombre() {
		return this.empNombre;
	}

	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}

	public String getEmpNit() {
		return this.empNit;
	}

	public void setEmpNit(String empNit) {
		this.empNit = empNit;
	}

	public String getEmpDireccion() {
		return this.empDireccion;
	}

	public void setEmpDireccion(String empDireccion) {
		this.empDireccion = empDireccion;
	}

	public String getEmpTel() {
		return this.empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public Date getEmpIniOp() {
		return this.empIniOp;
	}

	public void setEmpIniOp(Date empIniOp) {
		this.empIniOp = empIniOp;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
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

	public Set getCtrCfcControlFacturacions() {
		return this.ctrCfcControlFacturacions;
	}

	public void setCtrCfcControlFacturacions(Set ctrCfcControlFacturacions) {
		this.ctrCfcControlFacturacions = ctrCfcControlFacturacions;
	}

	public String getEmpRegistro() {
		return empRegistro;
	}

	public void setEmpRegistro(String empRegistro) {
		this.empRegistro = empRegistro;
	}

	public String getEmpGiro() {
		return empGiro;
	}

	public void setEmpGiro(String empGiro) {
		this.empGiro = empGiro;
	}
	

}