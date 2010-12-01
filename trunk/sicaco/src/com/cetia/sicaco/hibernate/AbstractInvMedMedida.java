package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvMedMedida entity provides the base persistence definition of the
 * InvMedMedida entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvMedMedida implements java.io.Serializable {

	// Fields

	private String medId;
	private InvTmeTipoMedida invTmeTipoMedida = new InvTmeTipoMedida();
	private String medNombreMedida;
	private String medComentario;
	private String medBogus;
	private Set invArtArticulos = new HashSet(0);
	private Set invCnvConversions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvMedMedida() {
	}

	/** minimal constructor */
	public AbstractInvMedMedida(String medId) {
		this.medId = medId;
	}

	/** full constructor */
	public AbstractInvMedMedida(String medId,
			InvTmeTipoMedida invTmeTipoMedida, String medNombreMedida,
			String medComentario, String medBogus, Set invArtArticulos,
			Set invCnvConversions) {
		super();
		this.medId = medId;
		this.invTmeTipoMedida = invTmeTipoMedida;
		this.medNombreMedida = medNombreMedida;
		this.medComentario = medComentario;
		this.medBogus = medBogus;
		this.invArtArticulos = invArtArticulos;
		this.invCnvConversions = invCnvConversions;
	}

	// Property accessors

	public String getMedId() {
		return this.medId;
	}

	public void setMedId(String medId) {
		this.medId = medId;
	}

	public InvTmeTipoMedida getInvTmeTipoMedida() {
		return this.invTmeTipoMedida;
	}

	public void setInvTmeTipoMedida(InvTmeTipoMedida invTmeTipoMedida) {
		this.invTmeTipoMedida = invTmeTipoMedida;
	}

	public String getMedNombreMedida() {
		return this.medNombreMedida;
	}

	public void setMedNombreMedida(String medNombreMedida) {
		this.medNombreMedida = medNombreMedida;
	}

	public String getMedComentario() {
		return this.medComentario;
	}

	public void setMedComentario(String medComentario) {
		this.medComentario = medComentario;
	}

	public Set getInvArtArticulos() {
		return this.invArtArticulos;
	}

	public void setInvArtArticulos(Set invArtArticulos) {
		this.invArtArticulos = invArtArticulos;
	}

	public Set getInvCnvConversions() {
		return this.invCnvConversions;
	}

	public void setInvCnvConversions(Set invCnvConversions) {
		this.invCnvConversions = invCnvConversions;
	}

	public String getMedBogus() {
		return medBogus;
	}

	public void setMedBogus(String medBogus) {
		this.medBogus = medBogus;
	}

}