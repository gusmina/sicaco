package com.cetia.sicaco.cuentaCorriente.struts.form;

import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaRcoReferenciasComerciales;
import com.cetia.sicaco.struts.BasicForm;

public class ReferenciaComercialForm extends BasicForm {

	private CtaRcoReferenciasComerciales referenciasComercialesH = new CtaRcoReferenciasComerciales();
	private long posicionRco[];		
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



	public String getRcoConcepto() {
	return referenciasComercialesH.getRcoConcepto();
}



public void setRcoConcepto(String rcoConcepto) {
	referenciasComercialesH.setRcoConcepto(rcoConcepto);
}



	public String getPreId() {
		return this.referenciasComercialesH.getCtaPrePrestamo().getPreId();
	}



	public void setPreId(String preId) {
		this.referenciasComercialesH.getCtaPrePrestamo().setPreId(preId);
	}
	
	
	public long[] getPosicionRco() {
		return posicionRco;
	}



	public void setPosicionRco(long[] posicionRco) {
		this.posicionRco = posicionRco;
	}



	public CtaRcoReferenciasComerciales getReferenciasComercialesH() {
		return referenciasComercialesH;
	}



	public void setReferenciasComercialesH(
			CtaRcoReferenciasComerciales referenciasComercialesH) {
		this.referenciasComercialesH = referenciasComercialesH;
	}



	public CtaPrePrestamo getCtaPrePrestamo() {
		return referenciasComercialesH.getCtaPrePrestamo();
	}



	public String getRcoEstado() {
		return referenciasComercialesH.getRcoEstado();
	}



	public Integer getRcoId() {
		return referenciasComercialesH.getRcoId();
	}



	public Float getRcoMonto() {
		return referenciasComercialesH.getRcoMonto();
	}



	public String getRcoReferencia() {
		return referenciasComercialesH.getRcoReferencia();
	}



	public String getRcoSucursal() {
		return referenciasComercialesH.getRcoSucursal();
	}



	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		referenciasComercialesH.setCtaPrePrestamo(ctaPrePrestamo);
	}



	public void setRcoEstado(String rcoEstado) {
		referenciasComercialesH.setRcoEstado(rcoEstado);
	}



	public void setRcoId(Integer rcoId) {
		referenciasComercialesH.setRcoId(rcoId);
	}



	public void setRcoMonto(Float rcoMonto) {
		referenciasComercialesH.setRcoMonto(rcoMonto);
	}



	public void setRcoReferencia(String rcoReferencia) {
		referenciasComercialesH.setRcoReferencia(rcoReferencia);
	}



	public void setRcoSucursal(String rcoSucursal) {
		referenciasComercialesH.setRcoSucursal(rcoSucursal);
	}



	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
