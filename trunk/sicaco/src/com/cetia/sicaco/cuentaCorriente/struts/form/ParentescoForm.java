package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.SecParParentesco;
import com.cetia.sicaco.struts.BasicForm;

public class ParentescoForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3524696889450424356L;
	SecParParentesco secParParentescoH = new SecParParentesco();
	private boolean mdf;
	
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set getCtaBenBeneficiarioses() {
		return secParParentescoH.getCtaBenBeneficiarioses();
	}

	public String getParDescripcion() {
		return secParParentescoH.getParDescripcion();
	}

	public Integer getParId() {
		return secParParentescoH.getParId();
	}

	public Set getSecPemPersonaEmergencias() {
		return secParParentescoH.getSecPemPersonaEmergencias();
	}

	public void setCtaBenBeneficiarioses(Set ctaBenBeneficiarioses) {
		secParParentescoH.setCtaBenBeneficiarioses(ctaBenBeneficiarioses);
	}

	public void setParDescripcion(String parDescripcion) {
		secParParentescoH.setParDescripcion(parDescripcion);
	}

	public void setParId(Integer parId) {
		secParParentescoH.setParId(parId);
	}

	public void setSecPemPersonaEmergencias(Set secPemPersonaEmergencias) {
		secParParentescoH.setSecPemPersonaEmergencias(secPemPersonaEmergencias);
	}

	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	public SecParParentesco getSecParParentescoH() {
		return secParParentescoH;
	}

	public void setSecParParentescoH(SecParParentesco secParParentescoH) {
		this.secParParentescoH = secParParentescoH;
	}
	
	
}
