package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecEmpEmpresa entity provides the base persistence definition of the
 * SecEmpEmpresa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecEmpEmpresa implements java.io.Serializable {

	// Fields

	private Integer empId;
	private String empNombre;
	private String empDireccion;
	private String empTelefono;
	private Set secAscAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecEmpEmpresa() {
	}

	/** minimal constructor */
	public AbstractSecEmpEmpresa(Integer empId, String empNombre,
			String empTelefono) {
		this.empId = empId;
		this.empNombre = empNombre;
		this.empTelefono = empTelefono;
	}

	/** full constructor */
	public AbstractSecEmpEmpresa(Integer empId, String empNombre,
			String empDireccion, String empTelefono, Set secAscAsociados) {
		this.empId = empId;
		this.empNombre = empNombre;
		this.empDireccion = empDireccion;
		this.empTelefono = empTelefono;
		this.secAscAsociados = secAscAsociados;
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

	public String getEmpDireccion() {
		return this.empDireccion;
	}

	public void setEmpDireccion(String empDireccion) {
		this.empDireccion = empDireccion;
	}

	public String getEmpTelefono() {
		return this.empTelefono;
	}

	public void setEmpTelefono(String empTelefono) {
		this.empTelefono = empTelefono;
	}

	public Set getSecAscAsociados() {
		return this.secAscAsociados;
	}

	public void setSecAscAsociados(Set secAscAsociados) {
		this.secAscAsociados = secAscAsociados;
	}

}