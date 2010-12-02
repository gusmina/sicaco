/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.inventario.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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

import com.cetia.sicaco.hibernate.InvTprTipoProveedor;
import com.cetia.sicaco.hibernate.InvTprTipoProveedorDAO;
import com.cetia.sicaco.inventario.struts.form.TipoProveedorForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 03-24-2008
 * 
 * XDoclet definition:
 * @struts.action path="/tipoProveedor" name="tipoProveedorForm" input="algo" parameter="accion" scope="request" validate="true"
 */
public class TipoProveedorAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "invTprTipoProveedor";
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
		TipoProveedorForm tipoProveedorForm = (TipoProveedorForm) form;
		tipoProveedorForm.getTipoProveedorH().setTprNombre("");
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		java.util.List lst = null;
		Transaction tx = tipoProveedorDAO.getSession().beginTransaction();
		try {

			lst  = tipoProveedorDAO.findAll();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			tipoProveedorDAO.getSession().flush();
			tipoProveedorDAO.getSession().clear();
			
		}
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
		request.setAttribute(Constantes.ACCION_KEY, "/tipoProveedor");
        request.setAttribute("filtro", "1");
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("tprNombre","tprId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tipoProveedor.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("tprNombre");
		nombreColumna.setTitleKey("tbl.tipoProveedor.tprNombre");
		
		nombreColumna = row.getColumn("tprId");
		nombreColumna.setTitleKey("tbl.tipoProveedor.tprId");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvTprTipoProveedor tipoProveedor = (InvTprTipoProveedor)item;
				
				HtmlBuilder html = new HtmlBuilder();
				value = "eliminar";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/tipoProveedor.do?tprId="+tipoProveedor.getTprId().toString()+"&accion=eliminar";
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();
				
				return html.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		tableFacade.setColumnProperties("tprNombre","tprId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tipoProveedor.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("tprNombre");
		nombreColumna.setTitleKey("tbl.tipoProveedor.tprNombre");
		
		tableFacade.render();
	}
	 
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoProveedorForm tipoProveedorForm = (TipoProveedorForm) form;
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		Transaction tx = tipoProveedorDAO.getSession().beginTransaction();
		try {
			tipoProveedorForm.getTipoProveedorH().setTprId(tipoProveedorDAO.nextId());
			tipoProveedorDAO.save(tipoProveedorForm.getTipoProveedorH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			tipoProveedorDAO.getSession().flush();
			tipoProveedorDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoProveedorForm tipoProveedorForm = (TipoProveedorForm) form;
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		Transaction tx = tipoProveedorDAO.getSession().beginTransaction();
		try {
			tipoProveedorForm.setTipoProveedorH(tipoProveedorDAO.findById(tipoProveedorForm.getTprId()));
			tipoProveedorDAO.delete(tipoProveedorForm.getTipoProveedorH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			tipoProveedorDAO.getSession().flush();
			tipoProveedorDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		TipoProveedorForm tipoProveedorForm = (TipoProveedorForm) form;
		tipoProveedorForm.getTipoProveedorH().setTprNombre("");
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		java.util.List lst = null;
		Transaction tx = tipoProveedorDAO.getSession().beginTransaction();
		try {

			lst  = tipoProveedorDAO.findAll();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			tipoProveedorDAO.getSession().flush();
			tipoProveedorDAO.getSession().clear();
			
		}
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
		request.setAttribute(Constantes.ACCION_KEY, "/tipoProveedor");
        request.setAttribute("filtro", "1");
		saveMessages(request, errors);
		return mapping.findForward("lista");
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.tipoProveedor.lista", "lista");
		map.put("cmd.tipoProveedor.guardar", "guardar");
		map.put("cmd.tipoProveedor.eliminar", "eliminar");
		map.put("cmd.tipoProveedor.redirectInvalidData", "redirectInvalidData");
		return map;
	}
}