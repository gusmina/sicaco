/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.asociados.struts.action;

import java.util.HashMap;
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
import org.hibernate.Transaction;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeImpl;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.asociados.struts.form.TipoAsociadoForm;
import com.cetia.sicaco.hibernate.CtaTasTipoAsociado;
import com.cetia.sicaco.hibernate.CtaTasTipoAsociadoDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 07-29-2008
 * 
 * XDoclet definition:
 * @struts.action path="/tipoAsociado" name="tipoAsociadoForm" input="redirectInvalidData" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.asociados.tipoAsociado"
 * @struts.action-forward name="redirectInvalidData" path="tipoAsociado.do?accion=redirectInvalidData"
 */
public class TipoAsociadoAction extends DMLAction {
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
	
	public static final String TABLA_ID = "ctaTasTipoAsociado";

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	    	TipoAsociadoForm tipoAsociadoForm = (TipoAsociadoForm) form;
		    CtaTasTipoAsociadoDAO tipoAsociadoDAO = new CtaTasTipoAsociadoDAO(getSessionHibernate(request));
		    List<CtaTasTipoAsociado> ls = null;
		
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
	    tableFacade.setExportTypes(response,ExportType.CSV, ExportType.JEXCEL);
	    tableFacade.setStateAttr("restore");
	    Limit limit = tableFacade.getLimit();
	    int totalRows = tipoAsociadoDAO.getTotalRowCount();
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		ls = tipoAsociadoDAO.findAll(rowStart, rowEnd);
		tableFacade.setItems(ls);
	 /* List lst = tipoAsociadoDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		*/
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
		request.setAttribute("form", tipoAsociadoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/tipoAsociado");
		return mapping.findForward("lista");
	}
	
	//---- metodo que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("tasNombre", "tasDescripcion", "tasId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tas.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("tasNombre");
		nombreColumna.setTitleKey("tbl.tas.tasNombre");
		
		nombreColumna = row.getColumn("tasDescripcion");
		nombreColumna.setTitleKey("tbl.tas.tasDescripcion");
		
		nombreColumna = row.getColumn("tasId");
		nombreColumna.setTitleKey("tbl.tas.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaTasTipoAsociado tipoAsociado= (CtaTasTipoAsociado)item;
				HtmlBuilder html = new HtmlBuilder();
				value = "Editar";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/asociados/tipoAsociado.do?tasId="+tipoAsociado.getTasId()+"&tasNombre="
				+tipoAsociado.getTasNombre()+"&tasDescripcion="+ tipoAsociado.getTasDescripcion()+
				"&accion=cargarDatos&mdf=true";
				html.a().href().quote().append(link).quote().append("class=\"linkEditar\"").title(value.toString()).close();
				//html.append(value);
				html.aEnd();				
				return html.toString();		
			}
		});
		
		return tableFacade.render();
	}
	
	//---- metodo que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("tasNombre", "tasDescripcion");
			Table table = tableFacade.getTable();;
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.conversion.caption");
		
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.tas.caption");
			
			Row row = table.getRow();
			Column nombreColumna = row.getColumn("tasNombre");
			nombreColumna.setTitleKey("tbl.tas.tasNombre");
			
			nombreColumna = row.getColumn("tasDescripcion");
			nombreColumna.setTitleKey("tbl.tas.tasDescripcion");
			
		tableFacade.render();
	}
	
	 public ActionForward guardar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			TipoAsociadoForm tipoAsociadoForm = (TipoAsociadoForm)form;
			CtaTasTipoAsociadoDAO tipoAsociadoDAO= new CtaTasTipoAsociadoDAO(getSessionHibernate(request));
			Transaction tx = tipoAsociadoDAO.getSession().beginTransaction();
			try{
				if(!tipoAsociadoForm.isMdf()){//ingresando un nuevo registro
					if(tipoAsociadoDAO.findByTasNombre(tipoAsociadoForm.getTipoAsociadoH().getTasNombre()).isEmpty()){
						tipoAsociadoForm.getTipoAsociadoH().setTasId(tipoAsociadoDAO.nextId());
						tipoAsociadoDAO.save(tipoAsociadoForm.getTipoAsociadoH());
					}else{
						mensajes("errors.tasNombreRepetido",request);
						return lista(mapping, form, request, response);
					}
				}else{//modificando un registro
					if(tipoAsociadoDAO.findByUpdatedName(tipoAsociadoForm.getTasId(),tipoAsociadoForm.getTasNombre()).isEmpty()){
						tipoAsociadoDAO.merge(tipoAsociadoForm.getTipoAsociadoH());
					}else{
						mensajes("errors.tasNombreRepetido",request);
						return lista(mapping, form, request, response);
					}
				}
				tx.commit();
			}catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				tipoAsociadoDAO.getSession().flush();
				tipoAsociadoDAO.getSession().clear();
				
			}
			return lista(mapping,new TipoAsociadoForm(),request,response);
		} 
	 
	 public ActionForward cargarDatos(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		 return lista(mapping, form, request, response);
	 }
	 
		public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
			ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
			saveMessages(request, errors);
			return lista(mapping, form, request, response);
		}
		
		public void mensajes(String msg, HttpServletRequest request){
			ActionErrors errors = new ActionErrors();
	        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
	        saveMessages(request, errors);
		}
		
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.tas.lista", "lista");
		map.put("cmd.tas.guardar", "guardar");
		map.put("cmd.tas.cargarDatos", "cargarDatos");
		map.put("cmd.tas.redirectInvalidData", "redirectInvalidData");
		return map;
	}
}