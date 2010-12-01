package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.CtaLprLineaPrestamo;
import com.cetia.sicaco.hibernate.CtaPlmPlanMeses;
import com.cetia.sicaco.hibernate.CtaTinTasaInteres;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamo;
import com.cetia.sicaco.struts.BasicForm;


public class TipoPrestamoForm extends BasicForm{
	
	CtaTprTipoPrestamo ctatPrestamoH = new CtaTprTipoPrestamo();
	
	private boolean mdf;
	private Integer plmId;
	private Integer tinPlazoId;
	
	//----------------------------
	
	public Integer getPlmId() {
		return plmId;
	}

	public void setPlmId(Integer plmId) {
		this.plmId = plmId;
	}

	public Integer getTinPlazoId() {
		return tinPlazoId;
	}

	public void setTinPlazoId(Integer tinPlazoId) {
		this.tinPlazoId = tinPlazoId;
	}

	//---------------------------
	public int getLprId() {
		return ctatPrestamoH.getCtaLprLineaPrestamo().getLprId();
	}
	
	public void setLprId(int lprId) {
		ctatPrestamoH.getCtaLprLineaPrestamo().setLprId(lprId);
	}
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}
	public CtaTprTipoPrestamo getCtatPrestamoH() {
		return ctatPrestamoH;
	}

	public void setCtatPrestamoH(CtaTprTipoPrestamo ctatPrestamoH) {
		this.ctatPrestamoH = ctatPrestamoH;
	}

	public boolean equals(Object arg0) {
		return ctatPrestamoH.equals(arg0);
	}

	public CtaLprLineaPrestamo getCtaLprLineaPrestamo() {
		return ctatPrestamoH.getCtaLprLineaPrestamo();
	}

	public CtaPlmPlanMeses getCtaPlmPlanMeses() {
		return ctatPrestamoH.getCtaPlmPlanMeses();
	}

	public Set getCtaPrePrestamos() {
		return ctatPrestamoH.getCtaPrePrestamos();
	}

	public CtaTinTasaInteres getCtaTinTasaInteres() {
		return ctatPrestamoH.getCtaTinTasaInteres();
	}

	public String getTprDescripcion() {
		return ctatPrestamoH.getTprDescripcion();
	}

	public Integer getTprId() {
		return ctatPrestamoH.getTprId();
	}

	public String getTprNombre() {
		return ctatPrestamoH.getTprNombre();
	}

	public int hashCode() {
		return ctatPrestamoH.hashCode();
	}

	public void setCtaLprLineaPrestamo(CtaLprLineaPrestamo ctaLprLineaPrestamo) {
		ctatPrestamoH.setCtaLprLineaPrestamo(ctaLprLineaPrestamo);
	}

	public void setCtaPlmPlanMeses(CtaPlmPlanMeses ctaPlmPlanMeses) {
		ctatPrestamoH.setCtaPlmPlanMeses(ctaPlmPlanMeses);
	}

	public void setCtaPrePrestamos(Set ctaPrePrestamos) {
		ctatPrestamoH.setCtaPrePrestamos(ctaPrePrestamos);
	}

	public void setCtaTinTasaInteres(CtaTinTasaInteres ctaTinTasaInteres) {
		ctatPrestamoH.setCtaTinTasaInteres(ctaTinTasaInteres);
	}

	public void setTprDescripcion(String tprDescripcion) {
		ctatPrestamoH.setTprDescripcion(tprDescripcion);
	}

	public void setTprId(Integer tprId) {
		ctatPrestamoH.setTprId(tprId);
	}

	public void setTprNombre(String tprNombre) {
		ctatPrestamoH.setTprNombre(tprNombre);
	}

	public String toString() {
		return ctatPrestamoH.toString();
	}

	/**
	 * 
	 */
	

}
