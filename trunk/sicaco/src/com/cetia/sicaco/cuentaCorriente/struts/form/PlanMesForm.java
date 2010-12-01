package com.cetia.sicaco.cuentaCorriente.struts.form;

import com.cetia.sicaco.struts.BasicForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import com.cetia.sicaco.hibernate.CtaPlmPlanMeses;

public class PlanMesForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1201118238634252675L;
	private CtaPlmPlanMeses ctaPlmPlanMesesH = new CtaPlmPlanMeses();
	private boolean mdf;
	
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
	
	
	/*public Set getCtaTahTipoAhorros_1() {
		return ctaPlmPlanMesesH.getCtaTahTipoAhorros_1();
	}*/

	public Set getCtaTahTipoAhorros() {
		return ctaPlmPlanMesesH.getCtaTahTipoAhorros();
	}

	public Set getCtaTisTipoSeguros() {
		return ctaPlmPlanMesesH.getCtaTisTipoSeguros();
	}

	public Set getCtaTprTipoPrestamos() {
		return ctaPlmPlanMesesH.getCtaTprTipoPrestamos();
	}

	public String getPlmDescripcion() {
		return ctaPlmPlanMesesH.getPlmDescripcion();
	}

	public Integer getPlmDuracion() {
		return ctaPlmPlanMesesH.getPlmDuracion();
	}

	public Integer getPlmId() {
		return ctaPlmPlanMesesH.getPlmId();
	}

	public String getPlmNombre() {
		return ctaPlmPlanMesesH.getPlmNombre();
	}

/*	public void setCtaTahTipoAhorros_1(Set ctaTahTipoAhorros_1) {
		ctaPlmPlanMesesH.setCtaTahTipoAhorros_1(ctaTahTipoAhorros_1);
	}
*/
	public void setCtaTahTipoAhorros(Set ctaTahTipoAhorros) {
		ctaPlmPlanMesesH.setCtaTahTipoAhorros(ctaTahTipoAhorros);
	}

	public void setCtaTisTipoSeguros(Set ctaTisTipoSeguros) {
		ctaPlmPlanMesesH.setCtaTisTipoSeguros(ctaTisTipoSeguros);
	}

	public void setCtaTprTipoPrestamos(Set ctaTprTipoPrestamos) {
		ctaPlmPlanMesesH.setCtaTprTipoPrestamos(ctaTprTipoPrestamos);
	}

	public void setPlmDescripcion(String plmDescripcion) {
		ctaPlmPlanMesesH.setPlmDescripcion(plmDescripcion);
	}

	public void setPlmDuracion(Integer plmDuracion) {
		ctaPlmPlanMesesH.setPlmDuracion(plmDuracion);
	}

	public void setPlmId(Integer plmId) {
		ctaPlmPlanMesesH.setPlmId(plmId);
	}

	public void setPlmNombre(String plmNombre) {
		ctaPlmPlanMesesH.setPlmNombre(plmNombre);
	}
	public CtaPlmPlanMeses getCtaPlmPlanMesesH() {
		return ctaPlmPlanMesesH;
	}
	public void setCtaPlmPlanMesesH(CtaPlmPlanMeses ctaPlmPlanMesesH) {
		this.ctaPlmPlanMesesH = ctaPlmPlanMesesH;
	}

}
