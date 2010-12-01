package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * InvCprContactoProveedor entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvCprContactoProveedor extends AbstractInvCprContactoProveedor
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvCprContactoProveedor() {
	}

	/** minimal constructor */
	public InvCprContactoProveedor(Integer cprId, String cprNombreCompleto,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(cprId, cprNombreCompleto, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public InvCprContactoProveedor(Integer cprId,
			InvProProveedor invProProveedor, String cprNombreCompleto,
			String cprNumeroTelOficina, String cprNumeroTelCelular,
			String cprEmpresaCelular, String cprNumeroFax, String cprEmail,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(cprId, invProProveedor, cprNombreCompleto, cprNumeroTelOficina,
				cprNumeroTelCelular, cprEmpresaCelular, cprNumeroFax, cprEmail,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}
	
}
