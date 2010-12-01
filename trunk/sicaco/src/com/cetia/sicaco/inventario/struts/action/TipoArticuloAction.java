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

import com.cetia.sicaco.hibernate.InvTarTipoArticulo;
import com.cetia.sicaco.hibernate.InvTarTipoArticuloDAO;
import com.cetia.sicaco.inventario.struts.form.TipoArticuloForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

public class TipoArticuloAction extends DMLAction {
	
	private static final String TABLE_ID="tipoArticuloTableId";
	private static final String TAR_ID="tarId";
	private final static String METHOD_NAME = "methodName";

	@SuppressWarnings("unchecked")
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoArticuloForm tipoArticuloForm = (TipoArticuloForm)form;
		
		List<InvTarTipoArticulo> lst = null;
		InvTarTipoArticuloDAO invArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
		
		tipoArticuloForm.setIdTipo(invArticuloDAO.nextId());
		
		lst =   (List<InvTarTipoArticulo>)invArticuloDAO.findAll();
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
        request.setAttribute(Constantes.ACCION_KEY,"/tipoArticulo");
        request.setAttribute("filtro", "0");
        request.getSession().setAttribute(METHOD_NAME, "invalidSave");
        request.setAttribute("form", tipoArticuloForm);
		return mapping.findForward("lista");
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("tarId","tarNombre","tarDescripcion","audUsuarioModificacion");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tipoarticulo.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("tarNombre");
		nombreColumna.setTitleKey("tbl.tipoarticulo.tarNombre");
		
		nombreColumna = row.getColumn("tarDescripcion");
		nombreColumna.setTitleKey("tbl.tipoarticulo.tarDescripcion");
		
		nombreColumna = row.getColumn("tarId");
		nombreColumna.setTitleKey("tbl.tipoarticulo.tarId");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvTarTipoArticulo tipoArticulo = (InvTarTipoArticulo)item;
				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/tipoArticulo.do?accion=forwardToEdicion"+"&"+"tarId="+tipoArticulo.getTarId();
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("audUsuarioModificacion");
		nombreColumna.setTitleKey("tbl.tipoarticulo.eliminar");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				InvTarTipoArticulo tipoArticulo = (InvTarTipoArticulo)item;
				HtmlBuilder html = new HtmlBuilder();
				String javaScript = "handlerDeleteButton(':')";
				html.a().onclick(javaScript.replaceFirst(":",tipoArticulo.getTarId())).append("class=\"linkEliminar\"").title("Eliminar").close();
			//	html.append("eliminar");
				html.aEnd();
				
				HtmlBuilder htmlCB2 = new HtmlBuilder();
				String msj  = "Union Tipo Prestamo";
				String linkCB2 = tableFacade.getWebContext().getContextPath();
				linkCB2 += "/inventario/articuloTipoPrestamo.do?tarId2="+tipoArticulo.getTarId()+"&accion=lista";
				htmlCB2.a().href().quote().append(linkCB2).quote().title(msj).close();
			//	htmlCR.a().href().quote().append(linkCR).quote().close();
				htmlCB2.append(msj);
				htmlCB2.aEnd();
				
				return html.toString() + " | " + htmlCB2.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("tarId","tarNombre","tarDescripcion");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.tipoarticulo.caption.x");
			
			Row row = table.getRow();
			
			Column nombreColumna = row.getColumn("tarNombre");
			nombreColumna.setTitleKey("tbl.tipoarticulo.tarNombre.x");
			
			nombreColumna = row.getColumn("tarDescripcion");
			nombreColumna.setTitleKey("tbl.tipoarticulo.tarDescripcion.x");
			

			nombreColumna = row.getColumn("tarId");
			nombreColumna.setTitleKey("tbl.tipoarticulo.tarId.x");
			
		tableFacade.render();
	}
	 
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			ActionMessages errors = new ActionMessages();
			TipoArticuloForm tform = (TipoArticuloForm)form;
			InvTarTipoArticulo modelTipoArticulo = null; 
			InvTarTipoArticuloDAO invArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
			modelTipoArticulo =  invArticuloDAO.findById(tform.getIdTipo());
			if(modelTipoArticulo!= null){
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.tipoarticulo.tarId"));
				
			}else{
				Transaction tx = invArticuloDAO.getSession().beginTransaction();
				modelTipoArticulo = tform.getInvTarTipoArticulo();
				//modelTipoArticulo.setTarId(tform.getIdTipo());
				modelTipoArticulo.setTarId(invArticuloDAO.nextId());
				try {
					invArticuloDAO.getSession().save(modelTipoArticulo);
				} catch (Exception e) {
					// TODO: handle exception
					tx.rollback();
					e.printStackTrace();
				}finally{
					invArticuloDAO.getSession().flush();
					invArticuloDAO.getSession().clear();
					
				}
			}
			saveMessages(request, errors);
			return lista(mapping, new TipoArticuloForm(), request, response);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			TipoArticuloForm tform = (TipoArticuloForm)form;
			InvTarTipoArticuloDAO invArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
			Transaction tx = invArticuloDAO.getSession().beginTransaction();
			InvTarTipoArticulo modelTipoArticulo;
			try {
				modelTipoArticulo = invArticuloDAO.findById(tform.getTarIdHidden());
				if(modelTipoArticulo.getInvArtArticulos().size()<1){
					invArticuloDAO.getSession().delete(modelTipoArticulo);
					tx.commit();
				}else{
					mensajes("error.tar.hijos", request);
				}
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}finally{
				invArticuloDAO.getSession().flush();
				invArticuloDAO.getSession().clear();
				
			}
		return lista(mapping, new TipoArticuloForm(), request, response);
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			TipoArticuloForm tform = (TipoArticuloForm)form;
			tform.getInvTarTipoArticulo().setTarId(tform.getIdTipo());
			InvTarTipoArticuloDAO invArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
			Transaction tx = invArticuloDAO.getSession().beginTransaction();
			try {
				invArticuloDAO.getSession().merge(tform.getInvTarTipoArticulo());
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}finally{
				invArticuloDAO.getSession().flush();
				invArticuloDAO.getSession().clear();
				
			}
		return lista(mapping, new TipoArticuloForm(), request, response);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward findByExample(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoArticuloForm tform = (TipoArticuloForm)form;
		InvTarTipoArticuloDAO invArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
		Transaction tx = invArticuloDAO.getSession().beginTransaction();
		List<InvTarTipoArticulo> lst = null;
		tform.getInvTarTipoArticulo().setTarId(tform.getIdTipo());
		try {
			lst =  (List<InvTarTipoArticulo>)invArticuloDAO.findByCriteria(tform.getInvTarTipoArticulo());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			invArticuloDAO.getSession().flush();
			invArticuloDAO.getSession().clear();
			
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
        //----- Variables de configuracion
        request.setAttribute(Constantes.ACCION_KEY,"/tipoArticulo");
        request.setAttribute("filtro", "0");
        request.getSession().setAttribute(METHOD_NAME, "invalidSave");
		return mapping.findForward("lista");
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		TipoArticuloForm tform = (TipoArticuloForm)form;
		InvTarTipoArticuloDAO invArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
		
		String methodName = (String)request.getSession().getAttribute(METHOD_NAME);
		if(methodName!= null){
			if(methodName.equals("invalidSave")){
				java.util.List lst = null;
				lst =   (List<InvTarTipoArticulo>)invArticuloDAO.findAll();
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
		        request.setAttribute(Constantes.ACCION_KEY,"/tipoArticulo");
		        request.setAttribute("filtro", "0");
		        saveMessages(request, errors);
				return mapping.findForward("lista");
			}
			if(methodName.equals("invalidUpdate")){
				String tid = (String)request.getSession().getAttribute(TAR_ID);
				InvTarTipoArticulo modelArticulo = null;
				modelArticulo = invArticuloDAO.findById(tid);
				tform.setInvTarTipoArticulo(modelArticulo);
				tform.getInvTarTipoArticulo().setTarId(tid);
				tform.setIdTipo(tid);
				tform.setMode("true");
				request.setAttribute("form",tform);
				request.setAttribute("filtro","1");
				request.setAttribute(Constantes.ACCION_KEY, "/tipoArticulo");
				saveMessages(request, errors);
				return mapping.findForward("dml");
			}
		}
		return lista(mapping, form, request, response);
	}
	
	private String invalidSave(HttpServletRequest request,HttpServletResponse response,ActionForm form){

			List<InvTarTipoArticulo> lst = null;
			InvTarTipoArticuloDAO invArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
			lst =   (List<InvTarTipoArticulo>)invArticuloDAO.findAll();
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
	        request.setAttribute(Constantes.ACCION_KEY,"/tipoArticulo");
	        request.setAttribute("filtro", "0");
		return "lista";
	}
	
	
	private String invalidEdit(HttpServletRequest request,HttpServletResponse response,ActionForm form){
			TipoArticuloForm tform = (TipoArticuloForm)form;
			InvTarTipoArticuloDAO invTarTipoArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
			InvTarTipoArticulo modelArticulo = null;
			modelArticulo = invTarTipoArticuloDAO.findById((String)request.getSession().getAttribute(TAR_ID));
			tform.setInvTarTipoArticulo(modelArticulo);
			request.setAttribute("mode","readonly");
			request.setAttribute("form",tform);
			request.setAttribute("filtro","1");
			request.setAttribute(Constantes.ACCION_KEY, "/tipoArticulo");
		return "dml";
	}
	
	public ActionForward forwardToEdicion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		TipoArticuloForm tform = (TipoArticuloForm)form;
		request.getSession().setAttribute(TAR_ID,tform.getTarId());
		InvTarTipoArticuloDAO invTarTipoArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
		InvTarTipoArticulo modelArticulo = null;
		modelArticulo = invTarTipoArticuloDAO.findById(tform.getTarId());
		tform.setIdTipo(tform.getTarId());
		tform.setInvTarTipoArticulo(modelArticulo);
		tform.setMode("true");
		request.setAttribute("form",tform);
		request.setAttribute("filtro","1");
		request.setAttribute(Constantes.ACCION_KEY, "/tipoArticulo");
		request.getSession().setAttribute(METHOD_NAME, "invalidUpdate");
		return mapping.findForward("dml");
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return lista(mapping, form, request, response);
	}
	
	public void mensajes(String msg, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
		saveMessages(request, errors);
	}
	
	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.tipoarticulo.lista", "lista");
		map.put("cmd.tipoarticulo.eliminar", "eliminar");
		map.put("cmd.tipoarticulo.buscar", "findByExample");
		map.put("cmd.tipoarticulo.guardar", "guardar");
		map.put("cmd.tipoarticulo.editar", "editar");
		map.put("cmd.tipoarticulo.redirectInvalidData","redirectInvalidData");
		map.put("cmd.tipoarticulo.forwardToEdicion", "forwardToEdicion");
		map.put("cmd.tipoarticulo.cancelar", "cancelar");
		return map;
	}
}
