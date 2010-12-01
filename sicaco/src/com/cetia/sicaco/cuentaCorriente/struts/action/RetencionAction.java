/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cuentaCorriente.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.cuentaCorriente.struts.form.RetencionForm;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaRxpRetencionPrestamo;
import com.cetia.sicaco.hibernate.CtaRxpRetencionPrestamoDAO;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 10-19-2009
 * 
 * XDoclet definition:
 * @struts.action path="/retenciones" name="retencionesForm" parameter="accion" scope="request"
 */
public class RetencionAction extends DMLAction {
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
	public ActionForward guardarRetencion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RetencionForm retencionForm = (RetencionForm) form;// TODO Auto-generated method stub
		String listaResponse = "";
		String listaErrores ="";
		CtaRxpRetencionPrestamo retencion = new CtaRxpRetencionPrestamo();
		retencion.setRxpNombre(retencionForm.getNombre());
		retencion.setRxpMonto(retencionForm.getMonto());
		ArrayList<String> errors = new ArrayList<String>();
		HashMap<Long, CtaRxpRetencionPrestamo> mapa = (HashMap<Long, CtaRxpRetencionPrestamo>)request.getSession().getAttribute("listaRetenciones");
		if(retencionForm .getNombre().trim().equals("") || retencionForm.getNombre()== null){
			errors.add(getResources(request,"aux").getMessage("errors.desc.nombreRet"));
		}
		if(retencionForm.getMonto()<=0 || retencionForm.getMonto()== null){
			errors.add(getResources(request,"aux").getMessage("errors.desc.montoRet"));
		}else{
			if(retencionForm.getMonto() > retencionForm.getLiquido()){
				errors.add(getResources(request,"aux").getMessage("errors.desc.montoRetLiquido"));
			}
		}
		if(!errors.isEmpty()){
			listaErrores=construirListaErrores(errors);
		}else{
			mapa.put(System.currentTimeMillis(),retencion);
			request.getSession().setAttribute("listaRetenciones", mapa);
		}
		listaResponse =construirListaRetenciones(mapa,request);
		try {
			response.getWriter().write(listaErrores+listaResponse);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	private String construirListaErrores(ArrayList<String> errors){
		String errores = "<label style=\"color: red;text-transform: none\">Se han encontrado los siguientes errores:</label><br>";
		Iterator<String> it = errors.iterator();
		while(it.hasNext()){
			errores=errores+"<label style=\"color: red;text-transform: none\">- "+ 
			it.next()
			+"</label><br>";
		}
		return errores;
	}	
	
	private String construirListaRetenciones(HashMap<Long, CtaRxpRetencionPrestamo> mapa,HttpServletRequest request){
		String html = "<table id=\"hor-zebra\">";
		html = html
				+ "<thead><tr><th scope=\"col\" class=\"th1\" style=\"text-align: left;\">&nbsp;</th>"
				+ "<th scope=\"col\" class=\"th1\" style=\"text-align: left;\" >"+ getResources(request,"aux").getMessage("lbl.desc.nombreRetencion")+"</th>"
				+ "<th scope=\"col\" class=\"th1\" style=\"text-align: left;\" >"+ getResources(request,"aux").getMessage("lbl.desc.montoRetencion")+"</th>"
				+ "</tr></thead><tbody>";
		long i;
		int j=1,size;
		size=mapa.size();
		if(size>0){
			Iterator<Long> iterator = mapa.keySet().iterator();
			
			while (iterator.hasNext()) {
				i = iterator.next().longValue();
				CtaRxpRetencionPrestamo retencion = (CtaRxpRetencionPrestamo) mapa.get(i);
				
				if(j%2==0){
					html = html + "<tr scope=\"row\">";
					html = html
					+ "<td><input type=\"checkbox\" name=\"posicionRetenciones\" class=\"posicionRetenciones\" value=\""
					+ i + "\"/></td>" + "<td>"+ retencion.getRxpNombre() + "</td><td><input type=\"text\" name=\"montoRetencion\" value=\""+retencion.getRxpMonto()+"\" id=\""+i+"\" style=\"border: none;background-color: ffffff;\" readonly=\"readonly\" class=\"input\"/></td></tr>";
				}
				else{
					html= html + "<tr class=\"odd\" scope=\"row\">";
					html = html
					+ "<td><input type=\"checkbox\" name=\"posicionRetenciones\" class=\"posicionRetenciones\" value=\""
					+ i + "\"/></td>" + "<td>"+ retencion.getRxpNombre() + "</td><td><input type=\"text\" name=\"montoRetencion\" value=\""+retencion.getRxpMonto()+"\" id=\""+i+"\" style=\"border: none;background-color: #e8edff;\" readonly=\"readonly\" class=\"input\"/></td></tr>";				
				}
				j++;
			}			
		}else{
			html= html + "<tr class=\"odd\"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>"+
						"<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
		html = html + "</tbody></table>";
		return html;
	}

	public ActionForward eliminarRetencion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RetencionForm retencionForm = (RetencionForm) form;
		HashMap<Long, CtaRxpRetencionPrestamo> mapa = (HashMap<Long,CtaRxpRetencionPrestamo>)request.getSession().getAttribute("listaRetenciones");
		double retencion=0;
		if (retencionForm.getPosicionRetenciones() != null) {
			int size = retencionForm.getPosicionRetenciones().length;
			int i = 0;
			while (i < size) {
				CtaRxpRetencionPrestamo retencionPre = mapa.get(retencionForm.getPosicionRetenciones()[i]);
				retencion=retencion+retencionPre.getRxpMonto();
				mapa.remove(retencionForm.getPosicionRetenciones()[i]);
				i++;
			}
		}
		try {
			request.setAttribute("retencion", retencion);
			request.getSession().setAttribute("listaRetenciones",mapa);
			response.getWriter().write(construirListaRetenciones(mapa, request));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	

	public ActionForward cargarRetenciones(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RetencionForm retencionForm = (RetencionForm) form;// TODO Auto-generated method stub
		String listaResponse = "";

		HashMap<Long, CtaRxpRetencionPrestamo> mapa = (HashMap<Long, CtaRxpRetencionPrestamo>)request.getSession().getAttribute("listaRetenciones");
		if(mapa == null){
			mapa = new HashMap<Long, CtaRxpRetencionPrestamo>();
		}
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociado cuentaAsociado = cuentaAsociadoDAO.findById(retencionForm.getCasCuenta());
		if(cuentaAsociado!=null){
			CtaRxpRetencionPrestamoDAO	retencionesDAO = new CtaRxpRetencionPrestamoDAO(getSessionHibernate(request));
			List<CtaRxpRetencionPrestamo> retenciones = retencionesDAO.findByProperty("ctaPrePrestamo.preId", cuentaAsociado.getCtaPrePrestamo().getPreId());
			int add = 0;
			Iterator<CtaRxpRetencionPrestamo> iRet= retenciones.iterator();
			while(iRet.hasNext()){
				CtaRxpRetencionPrestamo retencion = iRet.next();
				mapa.put(System.currentTimeMillis() + add, retencion);
				add++;
			}
		}
		listaResponse = construirListaRetenciones(mapa, request);
		try {
			String var = listaResponse;
			response.getWriter().write(var);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.ret.guardarRetencion","guardarRetencion");	
		map.put("cmd.ret.eliminarRetencion","eliminarRetencion");
		map.put("cmd.ret.cargarRetenciones","cargarRetenciones");
		return map;
	}

}