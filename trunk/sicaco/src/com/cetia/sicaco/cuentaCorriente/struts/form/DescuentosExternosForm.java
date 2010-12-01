/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cuentaCorriente.struts.form;

import com.cetia.sicaco.hibernate.CtaDexDescuentosExternos;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-16-2008
 * 
 * XDoclet definition:
 * @struts.form name="descuentosExternosHForm"
 */
public class DescuentosExternosForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6017718564330704358L;
	
	private CtaDexDescuentosExternos descuentosExternosH = new CtaDexDescuentosExternos();;
	private String ascId;
	private String perId;//id de la persona que solicita el prestamo, se utiliza para verificar que una persona no se
	//auto apruebe el prestamo
	private boolean mdf;
	private int estadoPrestamo;//se utiliza para conservar el estado del prestamo al cual pertenece esta jsp
	private long[] posicionDex;
	
	
	
	
	public int getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(int estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public String getPerId() {
		return perId;
	}

	public void setPerId(String perId) {
		this.perId = perId;
	}

	public long[] getPosicionDex() {
		return posicionDex;
	}

	public void setPosicionDex(long[] posicionDex) {
		this.posicionDex = posicionDex;
	}

	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	public String getPreId() {
		return this.descuentosExternosH.getCtaPrePrestamo().getPreId();
	}

	public void setPreId(String preId) {
		this.descuentosExternosH.getCtaPrePrestamo().setPreId(preId);
	}

	public CtaDexDescuentosExternos getDescuentosExternosH() {
		return descuentosExternosH;
	}

	public void setDescuentosExternosH(CtaDexDescuentosExternos descuentosExternosH) {
		this.descuentosExternosH = descuentosExternosH;
	}
	
	public String getAscId() {
		return ascId;
	}

	public void setAscId(String ascId) {
		this.ascId = ascId;
	}

	public CtaLprLineaPrestamo getCtaLprLineaPrestamo() {
		if(descuentosExternosH.getCtaLprLineaPrestamo()==null)
			descuentosExternosH.setCtaLprLineaPrestamo(new CtaLprLineaPrestamo());
		return descuentosExternosH.getCtaLprLineaPrestamo();
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		if(descuentosExternosH.getCtaPrePrestamo()==null)
			descuentosExternosH.setCtaPrePrestamo(new CtaPrePrestamo());
		return descuentosExternosH.getCtaPrePrestamo();
	}

	public Integer getDexId() {
		return descuentosExternosH.getDexId();
	}

	public double getDexMonto() {
		return descuentosExternosH.getDexMonto();
	}

	public String getDexNombreDescuento() {
		return descuentosExternosH.getDexNombreDescuento();
	}

	public void setCtaLprLineaPrestamo(CtaLprLineaPrestamo ctaLprLineaPrestamo) {
		descuentosExternosH.setCtaLprLineaPrestamo(ctaLprLineaPrestamo);
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		descuentosExternosH.setCtaPrePrestamo(ctaPrePrestamo);
	}

	public void setDexId(Integer dexId) {
		descuentosExternosH.setDexId(dexId);
	}

	public void setDexMonto(double dexMonto) {
		descuentosExternosH.setDexMonto(dexMonto);
	}

	public void setDexNombreDescuento(String dexNombreDescuento) {
		descuentosExternosH.setDexNombreDescuento(dexNombreDescuento);
	}
	
	
	public boolean isFillAuditoria() {
		return false;
	}
}