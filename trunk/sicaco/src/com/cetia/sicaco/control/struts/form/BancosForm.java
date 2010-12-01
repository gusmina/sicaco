/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.control.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.CtrBanBanco;
import com.cetia.sicaco.hibernate.CtrPaiPais;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-18-2008
 * 
 * XDoclet definition:
 * @struts.form name="bancosForm"
 */
public class BancosForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	CtrBanBanco bancoH = new CtrBanBanco();
	
	private Integer banId2;
	
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (bancoH.getAudFechaCreacion()!=null)?(sdf.format(bancoH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (bancoH.getAudUsuarioModificacion()!=null)?(sdf.format(bancoH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return bancoH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return bancoH.getAudUsuarioModificacion();
	}

	public Integer getBanId() {
		return bancoH.getBanId();
	}

	public String getBanNombre() {
		return bancoH.getBanNombre();
	}

	public CtrPaiPais getCtrPaiPais() {
		return bancoH.getCtrPaiPais();
	}

	public Set getInvPcbProveedorCuentaBancarias() {
		return bancoH.getInvPcbProveedorCuentaBancarias();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		bancoH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		bancoH.setAudFechaModificacion(audFechaModificacion);
	}
	
	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			bancoH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			bancoH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		bancoH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		bancoH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setBanId(Integer banId) {
		bancoH.setBanId(banId);
	}

	public void setBanNombre(String banNombre) {
		bancoH.setBanNombre(banNombre);
	}

	public void setCtrPaiPais(CtrPaiPais ctrPaiPais) {
		bancoH.setCtrPaiPais(ctrPaiPais);
	}

	public void setInvPcbProveedorCuentaBancarias(
			Set invPcbProveedorCuentaBancarias) {
		bancoH.setInvPcbProveedorCuentaBancarias(invPcbProveedorCuentaBancarias);
	}

	public CtrBanBanco getBancoH() {
		return bancoH;
	}

	public void setBancoH(CtrBanBanco bancoH) {
		this.bancoH = bancoH;
	}

	public Integer getBanId2() {
		return banId2;
	}

	public void setBanId2(Integer banId2) {
		this.banId2 = banId2;
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}
	
}