package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaBenBeneficiarios entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaBenBeneficiarios extends AbstractCtaBenBeneficiarios implements
		java.io.Serializable {

	public CtaBenBeneficiarios() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CtaBenBeneficiarios(SecParParentesco secParParentesco,
			CtaAscAsociado ctaAscAsociado, String benPrimerNombre,
			String benPrimerApellido, String benSegundoNombre,
			String benApellidoCasada,
			String benSegundoApellido, Date benFechaNacimiento, String benSexo,
			String benTelefono, String benEstado, String benHijo,
			String benNombreCompleto, Set ctaBxcBeneficiariosCuentas) {
		super(secParParentesco, ctaAscAsociado, benPrimerNombre, benPrimerApellido,
				benSegundoNombre, benSegundoApellido, benApellidoCasada,
				benFechaNacimiento, benSexo,
				benTelefono, benEstado, benHijo, benNombreCompleto,
				ctaBxcBeneficiariosCuentas);
		// TODO Auto-generated constructor stub
	}


	public CtaBenBeneficiarios(String benPrimerNombre,
			CtaAscAsociado ctaAscAsociado, String benPrimerApellido,
			String benTelefono, String benEstado, String benHijo) {
		super(benPrimerNombre, ctaAscAsociado, benPrimerApellido, benTelefono,
				benEstado, benHijo);
		// TODO Auto-generated constructor stub
	}

	

	
}
