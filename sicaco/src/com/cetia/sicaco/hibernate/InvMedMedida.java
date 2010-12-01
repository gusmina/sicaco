package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * InvMedMedida entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvMedMedida extends AbstractInvMedMedida implements
		java.io.Serializable {

	public InvMedMedida() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvMedMedida(String medId, InvTmeTipoMedida invTmeTipoMedida,
			String medNombreMedida, String medComentario, String medBogus,
			Set invArtArticulos, Set invCnvConversions) {
		super(medId, invTmeTipoMedida, medNombreMedida, medComentario, medBogus,
				invArtArticulos, invCnvConversions);
		// TODO Auto-generated constructor stub
	}

	public InvMedMedida(String medId) {
		super(medId);
		// TODO Auto-generated constructor stub
	}

	// Constructors

	

}
