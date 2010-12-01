package com.cetia.sicaco.cuentaCorriente.struts.form;

import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaRpeReferenciasPersonales;
import com.cetia.sicaco.struts.BasicForm;

public class ReferenciaPersonalForm extends BasicForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6427418340581029777L;
	private CtaRpeReferenciasPersonales referenciasPersonalesH = new CtaRpeReferenciasPersonales();
	private long[] posicionRpe;
	private boolean mdf;
	private String ascId;
	private int estadoPrestamo;//se utiliza para conservar el estado del prestamo al cual pertenece esta jsp
	private String perId;//id de la persona que solicita el prestamo, se utiliza para verificar que una persona no se
	
	
	
	public String getPerId() {
		return perId;
	}



	public void setPerId(String perId) {
		this.perId = perId;
	}



	public int getEstadoPrestamo() {
		return estadoPrestamo;
	}



	public void setEstadoPrestamo(int estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}



	public String getAscId() {
		return ascId;
	}



	public void setAscId(String ascId) {
		this.ascId = ascId;
	}



	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}



	public String getPreId() {
		return this.referenciasPersonalesH.getCtaPrePrestamo().getPreId();
	}



	public void setPreId(String preId) {
		this.referenciasPersonalesH.getCtaPrePrestamo().setPreId(preId);
	}



	public long[] getPosicionRpe() {
		return posicionRpe;
	}



	public void setPosicionRpe(long[] posicionRpe) {
		this.posicionRpe = posicionRpe;
	}



	public CtaPrePrestamo getCtaPrePrestamo() {
		return referenciasPersonalesH.getCtaPrePrestamo();
	}



	public Integer getParId() {
		return referenciasPersonalesH.getParId();
	}



	public String getRpeApellidos() {
		return referenciasPersonalesH.getRpeApellidos();
	}



	public String getRpeDireccion() {
		return referenciasPersonalesH.getRpeDireccion();
	}



	public String getRpeEstadoValidez() {
		return referenciasPersonalesH.getRpeEstadoValidez();
	}



	public Integer getRpeId() {
		return referenciasPersonalesH.getRpeId();
	}



	public String getRpeNombres() {
		return referenciasPersonalesH.getRpeNombres();
	}



	public String getRpeTelefono() {
		return referenciasPersonalesH.getRpeTelefono();
	}



	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		referenciasPersonalesH.setCtaPrePrestamo(ctaPrePrestamo);
	}



	public void setParId(Integer parId) {
		referenciasPersonalesH.setParId(parId);
	}



	public void setRpeApellidos(String rpeApellidos) {
		referenciasPersonalesH.setRpeApellidos(rpeApellidos);
	}



	public void setRpeDireccion(String rpeDireccion) {
		referenciasPersonalesH.setRpeDireccion(rpeDireccion);
	}



	public void setRpeEstadoValidez(String rpeEstadoValidez) {
		referenciasPersonalesH.setRpeEstadoValidez(rpeEstadoValidez);
	}



	public void setRpeId(Integer rpeId) {
		referenciasPersonalesH.setRpeId(rpeId);
	}



	public void setRpeNombres(String rpeNombres) {
		referenciasPersonalesH.setRpeNombres(rpeNombres);
	}



	public void setRpeTelefono(String rpeTelefono) {
		referenciasPersonalesH.setRpeTelefono(rpeTelefono);
	}



	public CtaRpeReferenciasPersonales getReferenciasPersonalesH() {
		return referenciasPersonalesH;
	}



	public void setReferenciasPersonalesH(
			CtaRpeReferenciasPersonales referenciasPersonalesH) {
		this.referenciasPersonalesH = referenciasPersonalesH;
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
