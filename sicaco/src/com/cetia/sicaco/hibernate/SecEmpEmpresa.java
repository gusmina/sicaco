package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * SecEmpEmpresa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecEmpEmpresa extends AbstractSecEmpEmpresa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecEmpEmpresa() {
	}

	/** minimal constructor */
	public SecEmpEmpresa(Integer empId, String empNombre, String empTelefono) {
		super(empId, empNombre, empTelefono);
	}

	/** full constructor */
	public SecEmpEmpresa(Integer empId, String empNombre, String empDireccion,
			String empTelefono, Set secAscAsociados) {
		super(empId, empNombre, empDireccion, empTelefono, secAscAsociados);
	}

}
