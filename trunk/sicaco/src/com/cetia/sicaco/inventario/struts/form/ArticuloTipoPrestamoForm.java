package com.cetia.sicaco.inventario.struts.form;

import com.cetia.sicaco.hibernate.CtaTprTipoPrestamo;
import com.cetia.sicaco.hibernate.InvTarTipoArticulo;
import com.cetia.sicaco.hibernate.IucTutTarTpr;
import com.cetia.sicaco.struts.BasicForm;

public class ArticuloTipoPrestamoForm extends BasicForm {

		private IucTutTarTpr iucTutTarTprH = new IucTutTarTpr();
		private String tarId2;
		private Integer lprId;
		
		public String getTarId2() {
			return tarId2;
		}

		public void setTarId2(String tarId) {
			this.tarId2 = tarId;
		}

		public CtaTprTipoPrestamo getCtaTprTipoPrestamo() {
			return iucTutTarTprH.getCtaTprTipoPrestamo();
		}

		public InvTarTipoArticulo getInvTarTipoArticulo() {
			return iucTutTarTprH.getInvTarTipoArticulo();
		}

		public Integer getTutId() {
			return iucTutTarTprH.getTutId();
		}

		public void setCtaTprTipoPrestamo(CtaTprTipoPrestamo ctaTprTipoPrestamo) {
			iucTutTarTprH.setCtaTprTipoPrestamo(ctaTprTipoPrestamo);
		}

		public void setInvTarTipoArticulo(InvTarTipoArticulo invTarTipoArticulo) {
			iucTutTarTprH.setInvTarTipoArticulo(invTarTipoArticulo);
		}

		public void setTutId(Integer tutId) {
			iucTutTarTprH.setTutId(tutId);
		}

		public IucTutTarTpr getIucTutTarTprH() {
			return iucTutTarTprH;
		}

		public void setIucTutTarTprH(IucTutTarTpr iucTutTarTprH) {
			this.iucTutTarTprH = iucTutTarTprH;
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
