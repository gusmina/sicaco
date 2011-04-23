/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.orden.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeImpl;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.DateCellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.OrdOcoOrdenDeCompra;
import com.cetia.sicaco.hibernate.OrdOcoOrdenDeCompraDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecPerPersonaDAO;
import com.cetia.sicaco.orden.struts.form.OrdenCompraForm;
import com.cetia.sicaco.orden.struts.form.OrdenPagoForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 05-09-2008
 * 
 * XDoclet definition:
 * @struts.action path="/detalleOrden" name="ordenCompraForm" input="redirectInvalidData" parameter="accion" scope="request"
 * @struts.action-forward name="lista" path="pagina-lista.orden.detalleOrden"
 */
public class DetalleOrdenAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "ordOcoOrdenCompra";
	public int pos = 0;
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		pos = 0;
		OrdenCompraForm compraForm = (OrdenCompraForm)form;
		OrdOcoOrdenDeCompraDAO compraDAO = new OrdOcoOrdenDeCompraDAO(getSessionHibernate(request));
		OrdenPagoForm pagoForm = (OrdenPagoForm) request.getSession().getAttribute("pagoForm");
		String proCodigo = pagoForm.getProCodigo();
		Integer don = pagoForm.getDon();
		List lst = compraDAO.findByProCodigo2(proCodigo);
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);

		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
        	//---- exporta la tabla
            export(tableFacade);
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		request.getSession().setAttribute("ordDet", lst);
		request.setAttribute("form", compraForm);
		request.setAttribute(Constantes.ACCION_KEY, "/detalleOrden");
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("ocoCodigo","ocoEmision","secAscAsociado.id.ascCodigo","ocoMonto",
				"ocoPagado","ocoVencimiento");//,"chkbox");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.ordenPago.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("ocoCodigo");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoCodigo");

		nombreColumna = row.getColumn("secAscAsociado.id.ascCodigo");
		nombreColumna.setTitleKey("tbl.ordenPago.secAscAsociado.id.ascCodigo");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra) item;
				
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
				CtaAscAsociado asociado = asociadoDAO.findById(compra.getAscCodigo());
				
				SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
				SecPerPersona persona = personaDAO.findById(asociado.getSecPerPersona().getPerId());
				
				value = asociado.getAscCodigo() + " - " + persona.getPerPrimerApellido()
				+ ", " + persona.getPerPrimerNombre();
				
				HtmlBuilder html = new HtmlBuilder();
				html.append(value);
								
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("ocoPagado");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoPagado");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)item;
				HtmlBuilder html = new HtmlBuilder();
				if(compra.getOcoPagado() == null){
					html.input().type("text").name("valores").value(""+compra.getOcoMonto()+"").size("10").id("montoId").close();
				}else{
					html.input().type("text").name("valores").value(""+compra.getOcoPagado()+"").size("10").id("montoId").close();
				}
								
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("ocoEmision");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoEmision");
		nombreColumna.getCellRenderer().setCellEditor(new DateCellEditor("dd-MMM-yyyy"));
		
		nombreColumna = row.getColumn("ocoVencimiento");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoVencimiento");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)item;
				HtmlBuilder html = new HtmlBuilder();
				String del="check";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/orden/detalleOrden.do?accion=check&ocoCodigo=" + compra.getOcoCodigo();
				if(compra.getOcoPagado() == null){
					html.input().type("checkbox").name("posiciones").value(""+pos+"").close();
				}else{
					html.input().type("checkbox").name("posiciones").value(""+pos+"").checked().close();
				}
				pos=pos+1;
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("ocoMonto");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoMonto");
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("linNombre","linDescripcion");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.linea.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("linNombre");
		nombreColumna.setTitleKey("tbl.linea.linNombre");
		
		nombreColumna = row.getColumn("linDescripcion");
		nombreColumna.setTitleKey("tbl.linea.linDescripcion");

		tableFacade.render();
	}

	public ActionForward regresar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		OrdenCompraForm compraForm = (OrdenCompraForm)form;
		List lst = (List) request.getSession().getAttribute("ordDet");
		int[] pos = compraForm.getPosiciones();
		double[] val = compraForm.getValores();
		int[] pos2 = new int[lst.size()];
		
		
		if((pos!=null) && (pos.length > 0)) {
			
			int k = 0;
			for(int i = 0; i < pos2.length; i++){
				if(k+1 > pos.length){
					pos2[i] = -1;
				}else{
					if(i < pos[k]){
						pos2[i] = -1;
					}else{
						pos2[i] = i;
						k++;
					}
				}
				
			}
			
	        for(int i = 0; i < pos.length; i++) {
	            double unValor = val[pos[i]];
	            if(unValor <= 0){
	            	request.getSession().setAttribute("ordDet", lst);
	            	mensajes("errors.ordDet.zero", compraForm, request, response);
					return mapping.findForward("lista");
	            }
	            int j=0;
	            Iterator iterator = lst.iterator();
	        	OrdOcoOrdenDeCompra compra = new OrdOcoOrdenDeCompra();
	            while(j < pos[i]){
	            	if(iterator.hasNext()){
	            		compra = (OrdOcoOrdenDeCompra) iterator.next();
	            	}
	            	j++;
	            }
	            compra = (OrdOcoOrdenDeCompra)iterator.next();
	            if(compra.getOcoMonto() >= unValor){
	            	compra.setOcoPagado(unValor);
	            }else{
	            	request.getSession().setAttribute("ordDet", lst);
	            	mensajes("errors.ordDet.cantMax", compraForm, request, response);
					return mapping.findForward("lista");
	            }
	            
	        }
	   
			Iterator iterator = lst.iterator();
			int[] del = new int[lst.size()];
	        for(int i = 0; i < lst.size(); i++) {
	        	if(iterator.hasNext()){
	        		OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)iterator.next();
	        		if(pos2[i] == -1){
	        			del[i]=i;
	        		}else{
	        			del[i]=-1;
	        		}
	        	}
			}
	        //int l = 0;
	        for(int i = 0; i < lst.size(); i++){
	        	if(del[i] > -1){
	        		lst.remove(i);
	        		int l=i;
	        		while(l < del.length-1){
	        			del[l] = del[l+1];
	        			l++;
	        		}
	        		i--;
	        		
	        	}
	        }
			
			request.getSession().setAttribute("ordDet", lst);
		}else{
			request.getSession().setAttribute("ordDet", new ArrayList());
		}
		return mapping.findForward("regresar");
	}
	
	public ActionForward check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		OrdenCompraForm compraForm = (OrdenCompraForm)form;
		List lst = (List) request.getSession().getAttribute("ordDet");
		for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
			OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra) iterator.next();
			if(compraForm.getOcoCodigo().equals(compra.getOcoCodigo())){
				System.out.println(compraForm.getPagado());
				System.out.println(compraForm.getOcoPagado());
				System.out.println(compra.getOcoPagado());
				System.out.println("checkeado");
			}
			
		}
		
		request.getSession().setAttribute("compraForm", compraForm);
		return lista(mapping, form, request, response);
	}
	
	public void mensajes(String msg,OrdenCompraForm compraForm,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = new ActionErrors();
		pos = 0;
		OrdOcoOrdenDeCompraDAO compraDAO = new OrdOcoOrdenDeCompraDAO(getSessionHibernate(request));
		OrdenPagoForm pagoForm = (OrdenPagoForm) request.getSession().getAttribute("pagoForm");
		String proCodigo = pagoForm.getInvProProveedor().getProCodigo();
		List lst = compraDAO.findByProCodigo2(proCodigo);
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
        	//---- exporta la tabla
            export(tableFacade);
//            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		request.getSession().setAttribute("ordDet", lst);
		request.setAttribute("form", compraForm);
		request.setAttribute(Constantes.ACCION_KEY, "/detalleOrden");
		request.setAttribute("detalleOrden", 1);
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
		//return mapping.findForward("lista");
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.ordDet.lista", "lista");
		map.put("cmd.ordDet.retornar", "regresar");
		map.put("cmd.ordDet.selecciona", "check");
		map.put("cmd.ordDet.update", "update");
		//map.put("cmd.ordDet.redirectInvalidData", "redirectInvalidData");
		return map;
	}
}