package com.cetia.sicaco.hibernate;

/**
 * AbstractInvCnvConversion entity provides the base persistence definition of
 * the InvCnvConversion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvCnvConversion implements java.io.Serializable {

	// Fields

	private Integer cnvId;
	private InvMedMedida invMedMedida;
	private String cnvNuevaMedida;
	private Double cnvFactor;
	private String cnvNombreMedida;
	private String cnvComentario;

	// Constructors

	/** default constructor */
	public AbstractInvCnvConversion() {
	}

	/** minimal constructor */
	public AbstractInvCnvConversion(Integer cnvId, String cnvNuevaMedida,
			Double cnvFactor, String cnvNombreMedida, String cnvComentario) {
		this.cnvId = cnvId;
		this.cnvNuevaMedida = cnvNuevaMedida;
		this.cnvFactor = cnvFactor;
		this.cnvNombreMedida = cnvNombreMedida;
		this.cnvComentario = cnvComentario;
	}

	/** full constructor */
	public AbstractInvCnvConversion(Integer cnvId, InvMedMedida invMedMedida,
			String cnvNuevaMedida, Double cnvFactor, String cnvNombreMedida,
			String cnvComentario) {
		this.cnvId = cnvId;
		this.invMedMedida = invMedMedida;
		this.cnvNuevaMedida = cnvNuevaMedida;
		this.cnvFactor = cnvFactor;
		this.cnvNombreMedida = cnvNombreMedida;
		this.cnvComentario = cnvComentario;
	}

	// Property accessors

	public Integer getCnvId() {
		return this.cnvId;
	}

	public void setCnvId(Integer cnvId) {
		this.cnvId = cnvId;
	}

	public InvMedMedida getInvMedMedida() {
		return this.invMedMedida;
	}

	public void setInvMedMedida(InvMedMedida invMedMedida) {
		this.invMedMedida = invMedMedida;
	}

	public String getCnvNuevaMedida() {
		return this.cnvNuevaMedida;
	}

	public void setCnvNuevaMedida(String cnvNuevaMedida) {
		this.cnvNuevaMedida = cnvNuevaMedida;
	}

	public Double getCnvFactor() {
		return this.cnvFactor;
	}

	public void setCnvFactor(Double cnvFactor) {
		this.cnvFactor = cnvFactor;
	}

	public String getCnvNombreMedida() {
		return this.cnvNombreMedida;
	}

	public void setCnvNombreMedida(String cnvNombreMedida) {
		this.cnvNombreMedida = cnvNombreMedida;
	}

	public String getCnvComentario() {
		return this.cnvComentario;
	}

	public void setCnvComentario(String cnvComentario) {
		this.cnvComentario = cnvComentario;
	}

}