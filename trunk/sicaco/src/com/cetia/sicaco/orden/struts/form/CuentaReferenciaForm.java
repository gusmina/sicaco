/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.orden.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.InvMedMedida;
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.OrdRefCuentaReferencia;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-30-2008
 * 
 * XDoclet definition:
 * @struts.form name="cuentaReferenciaForm"
 */
public class CuentaReferenciaForm extends BasicForm {
	/*
	 * Generated Methods
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6663131381634933575L;
	
	private OrdRefCuentaReferencia referenciaH = new OrdRefCuentaReferencia();
	
	private int provId;
	private int reffId;

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
		return (referenciaH.getAudFechaCreacion()!=null)?(sdf.format(referenciaH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (referenciaH.getAudUsuarioModificacion()!=null)?(sdf.format(referenciaH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return referenciaH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return referenciaH.getAudUsuarioModificacion();
	}

	public InvProProveedor getInvProProveedor() {
		return referenciaH.getInvProProveedor();
	}

	public Set getOrdOcoOrdenDeCompras() {
		return referenciaH.getOrdOcoOrdenDeCompras();
	}

	public String getRefCuenta() {
		return referenciaH.getRefCuenta();
	}

	public String getRefDescripcion() {
		return referenciaH.getRefDescripcion();
	}

	public Integer getRefId() {
		return referenciaH.getRefId();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		referenciaH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		referenciaH.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			referenciaH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			referenciaH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		referenciaH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		referenciaH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		referenciaH.setInvProProveedor(invProProveedor);
	}

	public void setOrdOcoOrdenDeCompras(Set ordOcoOrdenDeCompras) {
		referenciaH.setOrdOcoOrdenDeCompras(ordOcoOrdenDeCompras);
	}

	public void setRefCuenta(String refCuenta) {
		referenciaH.setRefCuenta(refCuenta);
	}

	public void setRefDescripcion(String refDescripcion) {
		referenciaH.setRefDescripcion(refDescripcion);
	}

	public void setRefId(Integer refId) {
		referenciaH.setRefId(refId);
	}
	
	public Integer getProId(){
		if(referenciaH.getInvProProveedor() == null){
			referenciaH.setInvProProveedor(new InvProProveedor());
		}
		return referenciaH.getInvProProveedor().getProId();
	}
	
	public void setProId(Integer proId){
		if(referenciaH.getInvProProveedor() == null){
			referenciaH.setInvProProveedor(new InvProProveedor());
		}
		referenciaH.getInvProProveedor().setProId(proId);
	}

	public OrdRefCuentaReferencia getReferenciaH() {
		return referenciaH;
	}

	public void setReferenciaH(OrdRefCuentaReferencia referenciaH) {
		this.referenciaH = referenciaH;
	}

	public String getRefEstado() {
		return referenciaH.getRefEstado();
	}

	public void setRefEstado(String refEstado) {
		referenciaH.setRefEstado(refEstado);
	}

	public int getProvId() {
		return provId;
	}

	public void setProvId(int provId) {
		this.provId = provId;
	}
	
	public int getReffId() {
		return reffId;
	}

	public void setReffId(int reffId) {
		this.reffId = reffId;
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}
}