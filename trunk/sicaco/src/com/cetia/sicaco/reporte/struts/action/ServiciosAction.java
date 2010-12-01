package com.cetia.sicaco.reporte.struts.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConCueCuentaDAO;
import com.cetia.sicaco.hibernate.ConTicTipoCuenta;
import com.cetia.sicaco.hibernate.ConTicTipoCuentaDAO;
import com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajo;
import com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajoDAO;
import com.cetia.sicaco.hibernate.CtaLahLineaAhorro;
import com.cetia.sicaco.hibernate.CtaLahLineaAhorroDAO;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamo;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTahTipoAhorro;
import com.cetia.sicaco.hibernate.CtaTahTipoAhorroDAO;
import com.cetia.sicaco.hibernate.CtaTgaTipoGarantia;
import com.cetia.sicaco.hibernate.CtaTgaTipoGarantiaDAO;
import com.cetia.sicaco.hibernate.CtaTisTipoSeguro;
import com.cetia.sicaco.hibernate.CtaTisTipoSeguroDAO;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamo;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccionDAO;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.CtrEstEstadoDAO;
import com.cetia.sicaco.reporte.struts.form.ServiciosForm;
import com.cetia.sicaco.struts.DMLAction;

public class ServiciosAction extends DMLAction {

	
	
	public void iniciarComboXML(HttpServletResponse response, PrintWriter writer) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		response.setHeader("Cache-Control", "private");
		response.setHeader("Pragma", "Cache");
		response.setHeader("Content-Disposition", "filename="+format.format(new Date())+".xml");
		response.setContentType("text/xml");
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.println("<combo>");
	}
	
	public void finalizarComboXML(HttpServletResponse response, PrintWriter writer) {
		response.setCharacterEncoding("UTF-8");
		writer.println("</combo>");
		writer.flush();
		writer.close();
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward lineaXML(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CtaLahLineaAhorroDAO ahorroDAO = new CtaLahLineaAhorroDAO(getSessionHibernate(request));
		PrintWriter out = response.getWriter();
		iniciarComboXML(response, out);
		List<CtaLahLineaAhorro> lineas = ahorroDAO.findAll();
		out.println("<identificador valor=\"linea\" />");
		for (Iterator<CtaLahLineaAhorro> lineasI = lineas.iterator(); lineasI.hasNext();) {
			CtaLahLineaAhorro ctaLahLineaAhorro = (CtaLahLineaAhorro) lineasI
					.next();
			out.print("\t<opcion valor=\"");
			out.print(ctaLahLineaAhorro.getLahId());
			out.print("\"><![CDATA[");
			out.print(ctaLahLineaAhorro.getLahNombre());
			out.println("]]></opcion>");
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	public ActionForward tipoAhorroXML(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CtaTahTipoAhorroDAO tipoAhorroDAO = new CtaTahTipoAhorroDAO(getSessionHibernate(request));
		PrintWriter out = response.getWriter();
		ServiciosForm servicioForm = (ServiciosForm) form;
		List<CtaTahTipoAhorro> lista = tipoAhorroDAO.findByLinea(servicioForm.getIdPadre());
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"tipoAhorro\" />");
		for (Iterator<CtaTahTipoAhorro> listas = lista.iterator(); listas.hasNext();) {
			CtaTahTipoAhorro ctaTahTipoAhorro = (CtaTahTipoAhorro) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(ctaTahTipoAhorro.getTahId());
			out.print("\"><![CDATA[");
			out.print(ctaTahTipoAhorro.getTahNombre());
			out.println("]]></opcion>");
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	
	// Obtener las empresas de trabajo
	
	public ActionForward empresaTrabajo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		PrintWriter out = response.getWriter();
		List<CtaEtrEmpresaTrabajo> lista = empresaTrabajoDAO.findAllActivasConDepartamentos();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"empresaTrabajo\" />");
		/*opcion todos*/
		out.print("\t<opcion valor=\"");
		out.print(-1);
		out.print("\"><![CDATA[");
		out.print("Acumulado");
		out.println("]]></opcion>");		
		for (Iterator<CtaEtrEmpresaTrabajo> listas = lista.iterator(); listas.hasNext();) {
			CtaEtrEmpresaTrabajo empresaTrabajo = (CtaEtrEmpresaTrabajo) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(empresaTrabajo.getEtrId());
			out.print("\"><![CDATA[");
			out.print(empresaTrabajo.getEtrNombre());
			out.println("]]></opcion>");
		}
		finalizarComboXML(response, out);
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public ActionForward tipoCuenta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ConTicTipoCuentaDAO tipoCuenta = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		PrintWriter out = response.getWriter();
		List<ConTicTipoCuenta> lista = tipoCuenta.findAll();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"ticId\" />");
		/*opcion todos*/
		out.print("\t<opcion valor=\"");
		out.print(-1);
		out.print("\"><![CDATA[");
		out.print("Acumulado");
		out.println("]]></opcion>");
		for (Iterator<ConTicTipoCuenta> listas = lista.iterator(); listas.hasNext();) {
			ConTicTipoCuenta tipoCuentai = (ConTicTipoCuenta) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(tipoCuentai.getTicId());
			out.print("\"><![CDATA[");
			out.print(tipoCuentai.getTicNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward cuentaContable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		List<ConCueCuenta> lista1 = cuentaDAO.findAllCodNameByEstadoForEstConf(1);
		PrintWriter out = response.getWriter();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"idCuenta\" />");
		for (Iterator<ConCueCuenta> listas = lista1.iterator(); listas.hasNext();) {
			ConCueCuenta cuentas = (ConCueCuenta) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(cuentas.getCueCodigoCuenta());
			out.print("\"><![CDATA[");
			out.print(cuentas.getCueNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward tipoSeguro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CtaTisTipoSeguroDAO tipoSeguroDAO = new CtaTisTipoSeguroDAO(getSessionHibernate(request));
		List<CtaTisTipoSeguro> lista1 = tipoSeguroDAO.findAll();
		PrintWriter out = response.getWriter();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"tipoSeguro\" />");
		for (Iterator<CtaTisTipoSeguro> listas = lista1.iterator(); listas.hasNext();) {
			CtaTisTipoSeguro seguro = (CtaTisTipoSeguro) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(seguro.getTisId());
			out.print("\"><![CDATA[");
			out.print(seguro.getTisNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward estadoAsociado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		List<CtrEstEstado> lista1 = estadoDAO.findAll();
		PrintWriter out = response.getWriter();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"estadoAsociado\" />");
		for (Iterator<CtrEstEstado> listas = lista1.iterator(); listas.hasNext();) {
			CtrEstEstado estado = (CtrEstEstado) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(estado.getEstId());
			out.print("\"><![CDATA[");
			out.print(estado.getEstNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward tipoTransaccion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
		List<CtaTtrTipoTransaccion> lista1 = tipoTransaccionDAO.findAll();
		PrintWriter out = response.getWriter();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"tipoTransaccion\" />");
		for (Iterator<CtaTtrTipoTransaccion> listas = lista1.iterator(); listas.hasNext();) {
			CtaTtrTipoTransaccion tipoTransaccion = (CtaTtrTipoTransaccion) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(tipoTransaccion.getTtrId());
			out.print("\"><![CDATA[");
			out.print(tipoTransaccion.getTtrNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward tipoPrestamo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ServiciosForm servForm = (ServiciosForm) form;
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		List<CtaTprTipoPrestamo> lista1 = tipoPrestamoDAO.findByLinea(servForm.getIdPadre());
		PrintWriter out = response.getWriter();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"tipoPrestamo\" />");
		for (Iterator<CtaTprTipoPrestamo> listas = lista1.iterator(); listas.hasNext();) {
			CtaTprTipoPrestamo tipoPrestamo = (CtaTprTipoPrestamo) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(tipoPrestamo.getTprId());
			out.print("\"><![CDATA[");
			out.print(tipoPrestamo.getTprNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward lineaPrestamo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		List<CtaLprLineaPrestamo> lista1 = lineaPrestamoDAO.findAll();
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"lineaPrestamo\" />");
		/*opcion todos*/
		out.print("\t<opcion valor=\"");
		out.print(-1);
		out.print("\"><![CDATA[");
		out.print("Acumulado");
		out.println("]]></opcion>");
		for (Iterator<CtaLprLineaPrestamo> listas = lista1.iterator(); listas.hasNext();) {
			CtaLprLineaPrestamo lineaPrestamo = (CtaLprLineaPrestamo) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(lineaPrestamo.getLprId());
			out.print("\"><![CDATA[");
			out.print(lineaPrestamo.getLprNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward tipoGarantia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CtaTgaTipoGarantiaDAO tipoGarantiaDAO = new CtaTgaTipoGarantiaDAO(getSessionHibernate(request));
		List<CtaTgaTipoGarantia> lista1 = tipoGarantiaDAO.findAll();
		PrintWriter out = response.getWriter();
		iniciarComboXML(response, out);
		out.println("<identificador valor=\"tipoGarantia\" />");
		/*opcion todos*/
		out.print("\t<opcion valor=\"");
		out.print(-2);
		out.print("\"><![CDATA[");
		out.print("------");
		out.println("]]></opcion>");		
		/*opcion fiador solidario*/
		out.print("\t<opcion valor=\"");
		out.print(-1);
		out.print("\"><![CDATA[");
		out.print("Fiador Solidario");
		out.println("]]></opcion>");
		
		for (Iterator<CtaTgaTipoGarantia> listas = lista1.iterator(); listas.hasNext();) {
			CtaTgaTipoGarantia tipoGarantia = (CtaTgaTipoGarantia) listas
					.next();
			out.print("\t<opcion valor=\"");
			out.print(tipoGarantia.getTgaId());
			out.print("\"><![CDATA[");
			out.print(tipoGarantia.getTgaNombre());
			out.println("]]></opcion>");
			
		}
		finalizarComboXML(response, out);
		return null;
	}
	
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cmd.repGen.lin", "lineaXML");
		map.put("cmd.repGen.tipoAhorro", "tipoAhorroXML");
		map.put("cmd.repGen.empresaTrabajo", "empresaTrabajo");
		map.put("cmd.repGen.tipoCuenta", "tipoCuenta");
		map.put("cmd.repGen.cuentaContable", "cuentaContable");
		map.put("cmd.repGen.tipoSeguro", "tipoSeguro");
		map.put("cmd.repGen.estadoAsociado", "estadoAsociado");
		map.put("cmd.repGen.tipoTransaccion", "tipoTransaccion");
		map.put("cmd.repGen.tipoPrestamo", "tipoPrestamo");
		map.put("cmd.repGen.tipoGarantia", "tipoGarantia");
		map.put("cmd.repGen.lineaPrestamo", "lineaPrestamo");
		return map;
	}

}
