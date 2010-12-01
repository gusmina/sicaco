package com.cetia.sicaco.inventario.struts.action;

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

import com.cetia.sicaco.hibernate.InvTmeTipoMedida;
import com.cetia.sicaco.hibernate.InvTmeTipoMedidaDAO;
import com.cetia.sicaco.inventario.struts.form.MedidaTipoForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

public class MedidaTipoAction extends DMLAction {

	private static final String TABLE_ID="tipoMedidaTableId";

	@SuppressWarnings("unchecked")
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MedidaTipoForm tipoForm = (MedidaTipoForm)form;
		List<InvTmeTipoMedida> lst = null;
		InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		lst =   (List<InvTmeTipoMedida>)invTmeTipoMedidaDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLE_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
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
        request.setAttribute("form", tipoForm);
        request.setAttribute(Constantes.ACCION_KEY,"/medidaTipo");
        request.setAttribute("filtro", "0");
		return mapping.findForward("lista");
	
		
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("tmeNombre","tmeDescripcion","tmeId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tme.medida.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("tmeNombre");
		nombreColumna.setTitleKey("tbl.tme.medida.tmeNombre");
		
		nombreColumna = row.getColumn("tmeDescripcion");
		nombreColumna.setTitleKey("tbl.tme.medida.tmeDescripcion");
		
		nombreColumna = row.getColumn("tmeId");
		nombreColumna.setTitleKey("tbl.tme.medida.eliminar");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvTmeTipoMedida tipoMedida = (InvTmeTipoMedida)item;
				
				HtmlBuilder html = new HtmlBuilder();
				value = "Eliminar";
				String link = tableFacade.getWebContext().getContextPath();
				//html.a().onclick("handlerDeleteButton1('tmeId="+ tipoMedida.getTmeId() + "');").id("deleteButtonId").close();
				html.a().style("cursor: pointer;").onclick("handlerDeleteButton("+ tipoMedida.getTmeId()+ ");").append("class=\"linkEliminar\"").title(value.toString()).id("deleteButtonId").close();
				//html.append(value);
				html.aEnd();
								
				return html.toString();
			}
		});
				
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("tmeNombre","tmeDescripcion");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tme.medida.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("tmeNombre");
		nombreColumna.setTitleKey("tbl.tme.medida.tmeNombre");
		
		nombreColumna = row.getColumn("tmeDescripcion");
		nombreColumna.setTitleKey("tbl.tme.medida.tmeDescripcion.x");			
		tableFacade.render();
	}
	 
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			MedidaTipoForm mtform = (MedidaTipoForm)form;
			List<InvTmeTipoMedida> lst = null;
			InvTmeTipoMedida modelTiMedida = null;
			InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			lst =  invTmeTipoMedidaDAO.findByProperty("tmeNombre",mtform.getTmeNombre());
			if(lst.size() == 0){
				Transaction tx = invTmeTipoMedidaDAO.getSession().beginTransaction();
				invTmeTipoMedidaDAO.save(mtform.getInvTmeTipoMedida()); 
				tx.commit();
			}else{
				mensajes("error.tme.mismoNombre", mtform, request, response);
				return mapping.findForward("lista");
			}
			return lista(mapping, form, request, response);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			MedidaTipoForm mtform = (MedidaTipoForm)form;
			InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			Transaction tx = invTmeTipoMedidaDAO.getSession().beginTransaction();
			InvTmeTipoMedida modelTiMedida = null; 
			try {
				modelTiMedida = invTmeTipoMedidaDAO.findById(mtform.getTmeId());
				invTmeTipoMedidaDAO.getSession().delete(modelTiMedida);
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}finally{
				invTmeTipoMedidaDAO.getSession().flush();
				invTmeTipoMedidaDAO.getSession().clear();
				
			}
		return lista(mapping, form, request, response);
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			MedidaTipoForm mtform = (MedidaTipoForm)form;
			InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			Transaction tx = invTmeTipoMedidaDAO.getSession().beginTransaction();
			try {
				invTmeTipoMedidaDAO.getSession().merge(mtform.getInvTmeTipoMedida());
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}finally{
				invTmeTipoMedidaDAO.getSession().flush();
				invTmeTipoMedidaDAO.getSession().clear();
				
			}
		return lista(mapping, form, request, response);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward findByExample(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MedidaTipoForm mtform = (MedidaTipoForm)form;
		InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		Transaction tx = invTmeTipoMedidaDAO.getSession().beginTransaction();
		List<InvTmeTipoMedida> lst = null;
		try {
			lst =  (List<InvTmeTipoMedida>)invTmeTipoMedidaDAO.findByCriteria(mtform.getInvTmeTipoMedida());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			invTmeTipoMedidaDAO.getSession().flush();
			invTmeTipoMedidaDAO.getSession().clear();
			
		}
			TableFacade tableFacade = new TableFacadeImpl(TABLE_ID, request);
			tableFacade.setItems(lst);
			//---- Genera los tipos de formas con que se podran exportar los datos
			tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
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
	        request.setAttribute("form", mtform);
	        request.setAttribute(Constantes.ACCION_KEY,"/medidaTipo");
	        request.setAttribute("filtro", "0");
		return mapping.findForward("lista");
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		MedidaTipoForm tipoForm = (MedidaTipoForm)form;
		List<InvTmeTipoMedida> lst = null;
		InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		lst =   (List<InvTmeTipoMedida>)invTmeTipoMedidaDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLE_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
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
        request.setAttribute("form", tipoForm);
        request.setAttribute(Constantes.ACCION_KEY,"/medidaTipo");
        request.setAttribute("filtro", "0");
		/*
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		//----- Variables de configuracion
		String methodName = (String)request.getSession().getAttribute(Constantes.METHOD_NAME);
		request.removeAttribute(Constantes.METHOD_NAME);
		String forwardName = null;
		try {
			if(methodName!= null){
				Method method = this.getClass().getDeclaredMethod(methodName, 
						new Class[]{HttpServletRequest.class,HttpServletResponse.class,ActionForm.class
						});
				method.setAccessible(true);		
				forwardName = (String)method.invoke(this,new Object[]{request,response,form});
			}
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		saveMessages(request, errors);
		return mapping.findForward("lista");
	}
	
	private String invalidSave(HttpServletRequest request,HttpServletResponse response,ActionForm form){
			List<InvTmeTipoMedida> lst = null;
			MedidaTipoForm tipoForm = (MedidaTipoForm)form;
			InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			lst =   (List<InvTmeTipoMedida>)invTmeTipoMedidaDAO.findAll();
			TableFacade tableFacade = new TableFacadeImpl(TABLE_ID, request);
			tableFacade.setItems(lst);
			//---- Genera los tipos de formas con que se podran exportar los datos
			tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
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
		    request.setAttribute("form", tipoForm);
		    request.setAttribute(Constantes.ACCION_KEY,"/tipoArticulo");
		    request.setAttribute("filtro", "0");
		return "lista";
	}
	
	
	private String invalidEdit(HttpServletRequest request,HttpServletResponse response,ActionForm form){
			MedidaTipoForm mtform = (MedidaTipoForm)form;
			InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			InvTmeTipoMedida modelTipoMedida = null;
			modelTipoMedida = invTmeTipoMedidaDAO.findById(mtform.getTmeId());//(Integer)request.getSession().getAttribute(TME_ID));
			mtform.setInvTmeTipoMedida(modelTipoMedida);
			mtform.setMode("true");
			request.setAttribute("form",mtform);
			request.setAttribute("filtro","1");
			request.setAttribute(Constantes.ACCION_KEY, "/medidaTipo");
		return "dml";
	}
	
	public ActionForward forwardToEdicion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MedidaTipoForm mtform = (MedidaTipoForm)form;
		//request.getSession().setAttribute(TME_ID,mtform.getTmeId());
		InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		InvTmeTipoMedida modelTipoMedida = null;
		modelTipoMedida = invTmeTipoMedidaDAO.findById(mtform.getTmeId());
		mtform.setInvTmeTipoMedida(modelTipoMedida);
		mtform.setMode("true");
		request.setAttribute("form",mtform);
		request.setAttribute("filtro","1");
		request.setAttribute(Constantes.ACCION_KEY, "/medidaTipo");
		return mapping.findForward("dml");
	}
	
	public void mensajes(String msg,MedidaTipoForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = new ActionErrors();
		MedidaTipoForm tipoForm = (MedidaTipoForm)form;
		List<InvTmeTipoMedida> lst = null;
		InvTmeTipoMedidaDAO invTmeTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		lst =   (List<InvTmeTipoMedida>)invTmeTipoMedidaDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLE_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
        if (limit.isExported()) {
        	//---- exporta la tabla
            export(tableFacade);
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
        request.setAttribute("form", tipoForm);
        request.setAttribute(Constantes.ACCION_KEY,"/medidaTipo");
        request.setAttribute("filtro", "0");
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
		//return mapping.findForward("lista");
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.tme.lista", "lista");
		map.put("cmd.tme.guardar", "guardar");
		map.put("cmd.tme.eliminar", "eliminar");
		map.put("cmd.tme.redirectInvalidData", "redirectInvalidData");
		return map;
	}
	
}
