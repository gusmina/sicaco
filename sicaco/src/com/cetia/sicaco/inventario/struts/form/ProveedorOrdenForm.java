package com.cetia.sicaco.inventario.struts.form;

import com.cetia.sicaco.hibernate.CtaTprTipoPrestamo;
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.IucPutProveedorTipoPrestamo;
import com.cetia.sicaco.struts.BasicForm;

public class ProveedorOrdenForm extends BasicForm {

	private IucPutProveedorTipoPrestamo iucPutProveedorTipoPrestamoH = new IucPutProveedorTipoPrestamo();
	private int proId2;
	private Integer lprId;
	
	public Integer getPutId() {
		return iucPutProveedorTipoPrestamoH.getPutId();
	}

	public int getProId2() {
		return proId2;
	}

	public void setProId2(int proId2) {
		this.proId2 = proId2;
	}

	public CtaTprTipoPrestamo getCtaTprTipoPrestamo() {
		return iucPutProveedorTipoPrestamoH.getCtaTprTipoPrestamo();
	}

	public InvProProveedor getInvProProveedor() {
		return iucPutProveedorTipoPrestamoH.getInvProProveedor();
	}

	public int getProId() {
		return iucPutProveedorTipoPrestamoH.getInvProProveedor().getProId();
	}

	public void setCtaTprTipoPrestamo(CtaTprTipoPrestamo ctaTprTipoPrestamo) {
		iucPutProveedorTipoPrestamoH.setCtaTprTipoPrestamo(ctaTprTipoPrestamo);
	}

	public void setInvProProveedor(InvProProveedor invProProveedor) {
		iucPutProveedorTipoPrestamoH.setInvProProveedor(invProProveedor);
	}

	public void setInvProId(int proId) {
		iucPutProveedorTipoPrestamoH.getInvProProveedor().setProId(proId);
	}

	public void setPutId(Integer putId) {
		iucPutProveedorTipoPrestamoH.setPutId(putId);
	}

	public IucPutProveedorTipoPrestamo getIucPutProveedorTipoPrestamoH() {
		return iucPutProveedorTipoPrestamoH;
	}

	public void setIucPutProveedorTipoPrestamoH(
			IucPutProveedorTipoPrestamo iucPutProveedorTipoPrestamoH) {
		this.iucPutProveedorTipoPrestamoH = iucPutProveedorTipoPrestamoH;
	}

	public Integer getLprId() {
		return lprId;
	}

	public void setLprId(Integer lprId) {
		this.lprId = lprId;
	}

	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
