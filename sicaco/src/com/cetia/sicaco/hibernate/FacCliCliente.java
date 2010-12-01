package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * FacCliCliente entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class FacCliCliente extends AbstractFacCliCliente implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public FacCliCliente() {
	}

	/** minimal constructor */
	public FacCliCliente(String cliCodigo, String cliNombre,
			String cliDireccion, String cliMunicipio, String cliDepartamento) {
		super(cliCodigo, cliNombre, cliDireccion, cliMunicipio, cliDepartamento);
	}

	public FacCliCliente(String cliCodigo, String cliNombre,
			String cliDireccion, String cliNumRegistro, String cliMunicipio,
			String cliDepartamento, String cliGiro, String cliContribuyente,
			Integer cliDeclaraIva, Set facFenFacturaEncabezados) {
		super(cliCodigo, cliNombre, cliDireccion, cliNumRegistro, cliMunicipio,
				cliDepartamento, cliGiro, cliContribuyente, cliDeclaraIva,
				facFenFacturaEncabezados);
		// TODO Auto-generated constructor stub
	}

	/** full constructor */
	
}
