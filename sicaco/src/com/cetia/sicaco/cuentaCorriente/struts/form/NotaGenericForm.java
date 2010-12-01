package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaTntTipoNota;
import com.cetia.sicaco.struts.BasicForm;

public class NotaGenericForm extends BasicForm {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7665562104939766232L;
	private CtaNotNotas notaH = new CtaNotNotas();
	private String preId;
	private long[] posicionNotas;
	private boolean mdf;
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



	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}



	public Long getCasCuenta() {
		return notaH.getCasCuenta();
	}



	public void setCasCuenta(Long casCuenta) {
		notaH.setCasCuenta(casCuenta);
	}



	public String getPreId() {
		return this.preId;
	}



	public void setPreId(String preId) {
		 this.preId = preId;
	}
	
	public long[] getPosicionNotas() {
		return posicionNotas;
	}



	public void setPosicionNotas(long[] posicionNotas) {
		this.posicionNotas = posicionNotas;
	}



	public Set getCtaAscAsociados() {
		return notaH.getCtaAscAsociados();
	}



	public Set getCtaInaIngresosxasociados() {
		return notaH.getCtaInaIngresosxasociados();
	}



	public CtaTntTipoNota getCtaTntTipoNota() {
		return notaH.getCtaTntTipoNota();
	}



	public Set getCtaTxaTransaccionxcuentaAsociados() {
		return notaH.getCtaTxaTransaccionxcuentaAsociados();
	}



	public String getNotCampo() {
		return notaH.getNotCampo();
	}



	public Date getNotFecha() {
		return notaH.getNotFecha();
	}



	public Integer getNotId() {
		return notaH.getNotId();
	}



	public String getNotNota() {
		return notaH.getNotNota();
	}



	public void setCtaAscAsociados(Set ctaAscAsociados) {
		notaH.setCtaAscAsociados(ctaAscAsociados);
	}



	public void setCtaInaIngresosxasociados(Set ctaInaIngresosxasociados) {
		notaH.setCtaInaIngresosxasociados(ctaInaIngresosxasociados);
	}



	public void setCtaTntTipoNota(CtaTntTipoNota ctaTntTipoNota) {
		notaH.setCtaTntTipoNota(ctaTntTipoNota);
	}



	public void setCtaTxaTransaccionxcuentaAsociados(
			Set ctaTxaTransaccionxcuentaAsociados) {
		notaH
				.setCtaTxaTransaccionxcuentaAsociados(ctaTxaTransaccionxcuentaAsociados);
	}



	public void setNotCampo(String notCampo) {
		notaH.setNotCampo(notCampo);
	}



	public void setNotFecha(Date notFecha) {
		notaH.setNotFecha(notFecha);
	}



	public void setNotId(Integer notId) {
		notaH.setNotId(notId);
	}



	public void setNotNota(String notNota) {
		notaH.setNotNota(notNota);
	}



	public CtaNotNotas getNotaH() {
		return notaH;
	}



	public void setNotaH(CtaNotNotas notaH) {
		this.notaH = notaH;
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
