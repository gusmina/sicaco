/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.inventario.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.CtrPaiPais;
import com.cetia.sicaco.hibernate.InvBodBodegas;
import com.cetia.sicaco.hibernate.SecSucSucursal;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-01-2008
 * 
 * XDoclet definition:
 * @struts.form name="bodegasForm"
 */
public class BodegasForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -9088321172001627446L;
	
	private InvBodBodegas bodegasH = new InvBodBodegas();

	
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
		return (bodegasH.getAudFechaCreacion()!=null)?(sdf.format(bodegasH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (bodegasH.getAudUsuarioModificacion()!=null)?(sdf.format(bodegasH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return bodegasH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return bodegasH.getAudUsuarioModificacion();
	}

	public String getBodComentario() {
		return bodegasH.getBodComentario();
	}

	public String getBodDireccion() {
		return bodegasH.getBodDireccion();
	}

	public String getBodEstado() {
		return bodegasH.getBodEstado();
	}

	public Integer getBodId() {
		return bodegasH.getBodId();
	}

	public String getBodNombre() {
		return bodegasH.getBodNombre();
	}

	public CtrPaiPais getCtrPaiPais() {
		return bodegasH.getCtrPaiPais();
	}

	public Set getFacFenFacturaEncabezados() {
		return bodegasH.getFacFenFacturaEncabezados();
	}

	public Set getInvCprCapacidadProductos() {
		return bodegasH.getInvCprCapacidadProductos();
	}

	public Set getInvEboExistenciaBodegas() {
		return bodegasH.getInvEboExistenciaBodegas();
	}

	public Set getInvMovMovimientoses() {
		return bodegasH.getInvMovMovimientoses();
	}

	public Set getInvStnEstantes() {
		return bodegasH.getInvStnEstantes();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		bodegasH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		bodegasH.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			bodegasH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			bodegasH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		bodegasH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		bodegasH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setBodComentario(String bodComentario) {
		bodegasH.setBodComentario(bodComentario);
	}

	public void setBodDireccion(String bodDireccion) {
		bodegasH.setBodDireccion(bodDireccion);
	}

	public void setBodEstado(String bodEstado) {
		bodegasH.setBodEstado(bodEstado);
	}

	public void setBodId(Integer bodId) {
		bodegasH.setBodId(bodId);
	}

	public void setBodNombre(String bodNombre) {
		bodegasH.setBodNombre(bodNombre);
	}

	public void setCtrPaiPais(CtrPaiPais ctrPaiPais) {
		bodegasH.setCtrPaiPais(ctrPaiPais);
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		bodegasH.setFacFenFacturaEncabezados(facFenFacturaEncabezados);
	}

	public void setInvCprCapacidadProductos(Set invCprCapacidadProductos) {
		bodegasH.setInvCprCapacidadProductos(invCprCapacidadProductos);
	}

	public void setInvEboExistenciaBodegas(Set invEboExistenciaBodegas) {
		bodegasH.setInvEboExistenciaBodegas(invEboExistenciaBodegas);
	}

	public void setInvMovMovimientoses(Set invMovMovimientoses) {
		bodegasH.setInvMovMovimientoses(invMovMovimientoses);
	}

	public void setInvStnEstantes(Set invStnEstantes) {
		bodegasH.setInvStnEstantes(invStnEstantes);
	}

	public InvBodBodegas getBodegasH() {
		return bodegasH;
	}

	public SecSucSucursal getSecSucSucursal() {
		return bodegasH.getSecSucSucursal();
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		bodegasH.setSecSucSucursal(secSucSucursal);
	}

	public void setBodegasH(InvBodBodegas bodegasH) {
		this.bodegasH = bodegasH;
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}
}