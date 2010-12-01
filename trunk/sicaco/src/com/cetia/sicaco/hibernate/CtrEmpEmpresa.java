package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtrEmpEmpresa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtrEmpEmpresa extends AbstractCtrEmpEmpresa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtrEmpEmpresa() {
	}

	public CtrEmpEmpresa(Integer empId, String empNombre, String empNit,
			String empDireccion, String empTel, Date empIniOp,
			String empRegistro, String empGiro, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set ctrCfcControlFacturacions) {
		super(empId, empNombre, empNit, empDireccion, empTel, empIniOp, empRegistro,
				empGiro, audUsuarioCreacion, audFechaCreacion, audFechaModificacion,
				audUsuarioModificacion, ctrCfcControlFacturacions);
	}

	public CtrEmpEmpresa(Integer empId, String empNombre, String empNit,
			String empDireccion, String empTel, Date empIniOp,
			String empRegistro, String empGiro, String audUsuarioCreacion,
			Date audFechaCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(empId, empNombre, empNit, empDireccion, empTel, empIniOp, empRegistro,
				empGiro, audUsuarioCreacion, audFechaCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	

}
