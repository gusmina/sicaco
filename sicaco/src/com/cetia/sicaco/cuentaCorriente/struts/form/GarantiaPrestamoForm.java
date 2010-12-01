package com.cetia.sicaco.cuentaCorriente.struts.form;

import com.cetia.sicaco.hibernate.CtaGarGarantia;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaTgaTipoGarantia;
import com.cetia.sicaco.struts.BasicForm;

public class GarantiaPrestamoForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 289033808050711137L;
	private CtaGarGarantia garantiaH = new CtaGarGarantia();
	private boolean mdf;
	private String ascId;
	private long[] posicionGxp;
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



	public long[] getPosicionGxp() {
		return posicionGxp;
	}



	public void setPosicionGxp(long[] posicionGxp) {
		this.posicionGxp = posicionGxp;
	}



	public String getPreId() {
		return this.garantiaH.getCtaPrePrestamo().getPreId();
	}



	public void setPreId(String preId) {
		this.garantiaH.getCtaPrePrestamo().setPreId(preId);
	}
	

	public boolean isMdf() {
		return mdf;
	}



	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}



	public String getAscId() {
		return ascId;
	}



	public void setAscId(String ascId) {
		this.ascId = ascId;
	}



	public CtaPrePrestamo getCtaPrePrestamo() {
		return garantiaH.getCtaPrePrestamo();
	}



	public CtaTgaTipoGarantia getCtaTgaTipoGarantia() {
		return garantiaH.getCtaTgaTipoGarantia();
	}



	public String getGarDescripcionInmueble() {
		return garantiaH.getGarDescripcionInmueble();
	}



	public Integer getGarId() {
		return garantiaH.getGarId();
	}



	public String getGarInspeccion() {
		return garantiaH.getGarInspeccion();
	}



	public String getGarNombreInmueble() {
		return garantiaH.getGarNombreInmueble();
	}



	public String getGarUbicacion() {
		return garantiaH.getGarUbicacion();
	}



	public Float getGarValor() {
		return garantiaH.getGarValor();
	}



	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		garantiaH.setCtaPrePrestamo(ctaPrePrestamo);
	}



	public void setCtaTgaTipoGarantia(CtaTgaTipoGarantia ctaTgaTipoGarantia) {
		garantiaH.setCtaTgaTipoGarantia(ctaTgaTipoGarantia);
	}



	public void setGarDescripcionInmueble(String garDescripcionInmueble) {
		garantiaH.setGarDescripcionInmueble(garDescripcionInmueble);
	}



	public void setGarId(Integer garId) {
		garantiaH.setGarId(garId);
	}



	public void setGarInspeccion(String garInspeccion) {
		garantiaH.setGarInspeccion(garInspeccion);
	}



	public void setGarNombreInmueble(String garNombreInmueble) {
		garantiaH.setGarNombreInmueble(garNombreInmueble);
	}



	public void setGarUbicacion(String garUbicacion) {
		garantiaH.setGarUbicacion(garUbicacion);
	}



	public void setGarValor(Float garValor) {
		garantiaH.setGarValor(garValor);
	}


	public CtaGarGarantia getGarantiaH() {
		return garantiaH;
	}



	public void setGarantiaH(CtaGarGarantia garantiaH) {
		this.garantiaH = garantiaH;
	}


	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
