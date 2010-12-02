/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.reporte.struts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Session;

import com.cetia.sicaco.hibernate.ConTicTipoCuenta;
import com.cetia.sicaco.hibernate.ConTicTipoCuentaDAO;
import com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajoDAO;
import com.cetia.sicaco.hibernate.CtaLahLineaAhorro;
import com.cetia.sicaco.hibernate.CtaLahLineaAhorroDAO;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamo;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTisTipoSeguro;
import com.cetia.sicaco.hibernate.CtaTisTipoSeguroDAO;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccionDAO;
import com.cetia.sicaco.hibernate.SecSucSucursalDAO;
import com.cetia.sicaco.reporte.struts.form.ReportesForm;
import com.mad.utilidades.filtros.FiltroOpenSession;

/** 
 * MyEclipse Struts
 * Creation date: 09-23-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class InicializarReporteAction extends DispatchAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward movimientosPrestamosDia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		form = new ReportesForm();
		request.setAttribute("form", form);
		
		CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
		List<CtaTtrTipoTransaccion> tipoTran = tipoTransaccionDAO.findAll();

		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		List<CtaLprLineaPrestamo> lineaPre = lineaPrestamoDAO.findAll();
		
		request.setAttribute("tipoTran", tipoTran);
		request.setAttribute("lineaPre", lineaPre);

		return mapping.findForward("reporte");
	}
	
	public ActionForward saldosDePrestamos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		List<CtaLprLineaPrestamo> lineaPre = lineaPrestamoDAO.findAll();
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		
		request.setAttribute("lineaPre", lineaPre);
		request.setAttribute("listaEmpresa",listaEmpresa);
		
		return mapping.findForward("reporte");
	}
	
	public ActionForward otorgamientoPrestamos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		List<CtaLprLineaPrestamo> lineaPre = lineaPrestamoDAO.findAll();
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		
		request.setAttribute("lineaPre", lineaPre);
		request.setAttribute("listaEmpresa",listaEmpresa);
		
		return mapping.findForward("reporte");
	}		
	
	public ActionForward saldosDeAhorros(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		CtaLahLineaAhorroDAO lineaAhorroDAO = new CtaLahLineaAhorroDAO(getSessionHibernate(request));
		CtaTtrTipoTransaccionDAO ttrDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		List listaLineasAhorro = lineaAhorroDAO.findAll();
		List listaTipoTran = ttrDAO.findAll();
		request.setAttribute("listaLineasAhorro",listaLineasAhorro);
		request.setAttribute("listaEmpresa",listaEmpresa);
		request.setAttribute("listaTipoTran", listaTipoTran);
		
		return mapping.findForward("reporte");
	}
	
	public ActionForward interesCapitalizadoAhorrantes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaLahLineaAhorroDAO lineaAhorroDAO = new CtaLahLineaAhorroDAO(getSessionHibernate(request));
		List listaLineasAhorro = lineaAhorroDAO.findAll();
		request.setAttribute("listaLineasAhorro",listaLineasAhorro);
		
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		request.setAttribute("listaEmpresa",listaEmpresa);
		
		return mapping.findForward("reporte");
	}
	
	public ActionForward catalogoDeCuentas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		form = new ReportesForm();
		request.setAttribute("form", form);
		ConTicTipoCuentaDAO tipoCuenta = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		List<ConTicTipoCuenta> tipoCue = tipoCuenta.findAll();
		
		request.setAttribute("tipoCue", tipoCue);

		return mapping.findForward("reporte");
	}
	
	public ActionForward prestamosPorRangoDeCuentas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		request.setAttribute("listaEmpresa",listaEmpresa);
		return mapping.findForward("reporte");
	}
	
	public ActionForward planillasPorEmpresa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		request.setAttribute("listaEmpresa",listaEmpresa);
		return mapping.findForward("reporte");
	}
	
	public ActionForward estadoDeCuentas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		return mapping.findForward("reporte");
	}
	
	public ActionForward abonosPorCajas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		SecSucSucursalDAO sucDAO = new SecSucSucursalDAO(getSessionHibernate(request));
		List listaSucursales = sucDAO.findAll();
		request.setAttribute("listaSuc", listaSucursales);
		return mapping.findForward("reporte");
	}
	
	public ActionForward saldosDeSeguros(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		request.setAttribute("listaEmpresa",listaEmpresa);
		
		CtaTisTipoSeguroDAO lineaSeguroDAO = new  CtaTisTipoSeguroDAO(getSessionHibernate(request));
		List<CtaTisTipoSeguro> lineaSeg = lineaSeguroDAO.findAll();
		request.setAttribute("lineaSeg", lineaSeg);
		
		form = new ReportesForm();
		request.setAttribute("form", form);

		
		
		return mapping.findForward("reporte");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward qen1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaTisTipoSeguroDAO lineaSegDAO = new  CtaTisTipoSeguroDAO(getSessionHibernate(request));
		CtaLahLineaAhorroDAO lineaAhDAO = new CtaLahLineaAhorroDAO(getSessionHibernate(request));
		CtaLprLineaPrestamoDAO lineaPreDAO = new  CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		
		CtaTtrTipoTransaccionDAO ttrDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
		List listaTipoTran = ttrDAO.findAll();
		request.setAttribute("listaTipoTran", listaTipoTran);
		
		List<CtaTisTipoSeguro> lineaSeg = lineaSegDAO.findAll();
		List<CtaLahLineaAhorro> lineaAh = lineaAhDAO.findAll();
		List<CtaLprLineaPrestamo> lineaPre = lineaPreDAO.findAll();
		
		request.setAttribute("lineaSeg", lineaSeg);
		request.setAttribute("listaLineasAhorro", lineaAh);
		request.setAttribute("lineaPre", lineaPre);
		
		return mapping.findForward("reporte");
	} 
	
	
	public ActionForward libroDiarioGeneral(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		return mapping.findForward("reporte");
	} 
	
	@SuppressWarnings("unchecked")
	public ActionForward saldosAl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaTisTipoSeguroDAO lineaSegDAO = new  CtaTisTipoSeguroDAO(getSessionHibernate(request));
		CtaLahLineaAhorroDAO lineaAhDAO = new CtaLahLineaAhorroDAO(getSessionHibernate(request));
		CtaLprLineaPrestamoDAO lineaPreDAO = new  CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		
		CtaTtrTipoTransaccionDAO ttrDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
		List listaTipoTran = ttrDAO.findAll();
		request.setAttribute("listaTipoTran", listaTipoTran);
		
		List<CtaTisTipoSeguro> lineaSeg = lineaSegDAO.findAll();
		List<CtaLahLineaAhorro> lineaAh = lineaAhDAO.findAll();
		List<CtaLprLineaPrestamo> lineaPre = lineaPreDAO.findAll();
		
		request.setAttribute("lineaSeg", lineaSeg);
		request.setAttribute("listaLineasAhorro", lineaAh);
		request.setAttribute("lineaPre", lineaPre);
		
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		request.setAttribute("listaEmpresa",listaEmpresa);
		return mapping.findForward("reporte");
	}  	
	
	public ActionForward insafocoop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		form = new ReportesForm();
		request.setAttribute("form", form);
		CtaEtrEmpresaTrabajoDAO empresaDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List listaEmpresa = empresaDAO.findAllActivasConDepartamentos();
		request.setAttribute("listaEmpresa",listaEmpresa);
		return mapping.findForward("reporte");
	}
	
	public Session getSessionHibernate(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}
}