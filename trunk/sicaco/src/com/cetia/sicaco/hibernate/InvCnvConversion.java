package com.cetia.sicaco.hibernate;

/**
 * InvCnvConversion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvCnvConversion extends AbstractInvCnvConversion implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvCnvConversion() {
	}

	/** minimal constructor */
	public InvCnvConversion(Integer cnvId, String cnvNuevaMedida,
			Double cnvFactor, String cnvNombreMedida, String cnvComentario) {
		super(cnvId, cnvNuevaMedida, cnvFactor, cnvNombreMedida, cnvComentario);
	}

	/** full constructor */
	public InvCnvConversion(Integer cnvId, InvMedMedida invMedMedida,
			String cnvNuevaMedida, Double cnvFactor, String cnvNombreMedida,
			String cnvComentario) {
		super(cnvId, invMedMedida, cnvNuevaMedida, cnvFactor, cnvNombreMedida,
				cnvComentario);
	}

}
