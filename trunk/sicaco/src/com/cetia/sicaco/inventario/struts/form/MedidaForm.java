package com.cetia.sicaco.inventario.struts.form;

import java.util.Date;

import com.cetia.sicaco.hibernate.InvLinLinea;
import com.cetia.sicaco.hibernate.InvMedMedida;
import com.cetia.sicaco.hibernate.InvTmeTipoMedida;
import com.cetia.sicaco.struts.BasicForm;

public class MedidaForm extends BasicForm{
	
	InvMedMedida invMedida  = new InvMedMedida();
	InvTmeTipoMedida invTmeTipoMedida = new InvTmeTipoMedida();
	private String mediId;
	
	
	public String getTmeNombre() {
		return invTmeTipoMedida.getTmeNombre();
	}

	public void setTmeDescripcion(String tmeDescripcion) {
		invTmeTipoMedida.setTmeDescripcion(tmeDescripcion);
	}

	public InvMedMedida getInvMedida() {
		return invMedida;
	}

	public void setInvMedida(InvMedMedida invMedida) {
		this.invMedida = invMedida;
	}

	public Integer getTmeId() {
		return invTmeTipoMedida.getTmeId();
	}

	public void setTmeId(Integer tmeId) {
		invTmeTipoMedida.setTmeId(tmeId);
	}
	
	public InvTmeTipoMedida getInvTmeTipoMedida() {
		return invMedida.getInvTmeTipoMedida();
	}


	
	
	
	public String getMedComentario() {
		return invMedida.getMedComentario();
	}



	public String getMedId() {
		return invMedida.getMedId();
	}



	public String getMedNombreMedida() {
		return invMedida.getMedNombreMedida();
	}



	public void setInvTmeTipoMedida(InvTmeTipoMedida invTmeTipoMedida) {
		invMedida.setInvTmeTipoMedida(invTmeTipoMedida);
	}



	public void setMedComentario(String medComentario) {
		invMedida.setMedComentario(medComentario);
	}



	public void setMedId(String medId) {
		invMedida.setMedId(medId);
	}



	public void setMedNombreMedida(String medNombreMedida) {
		invMedida.setMedNombreMedida(medNombreMedida);
	}

	public String getMediId() {
		return mediId;
	}

	public void setMediId(String mediId) {
		this.mediId = mediId;
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
