/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.inventario.struts.action;

import java.util.Date;
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
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.facade.TableFacadeImpl;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.hibernate.InvBodBodegasDAO;
import com.cetia.sicaco.hibernate.InvNivNivel;
import com.cetia.sicaco.hibernate.InvNivNivelDAO;
import com.cetia.sicaco.hibernate.InvNivNivelId;
import com.cetia.sicaco.hibernate.InvStnEstante;
import com.cetia.sicaco.hibernate.InvStnEstanteDAO;
import com.cetia.sicaco.inventario.struts.form.EstantesForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 04-14-2008
 * 
 * XDoclet definition:
 * @struts.action path="/estantes" name="estantesForm" input="redirectInvalidData" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.inventario.estantes"
 */
public class EstantesAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "invStnEstante";
	private final static String METHOD_NAME = "methodName";
	private final static String BDG_ID = "bdgId";
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
		EstantesForm estantesForm = (EstantesForm)form;
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		
		
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID, request);
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = estanteDAO.getTotalRowCount();
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		List lst = estanteDAO.findAll(rowStart, rowEnd);
		tableFacade.setItems(lst);
		
		//java.util.List lst = null;
		//lst  = estanteDAO.findAll();
		InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
		java.util.List lstB = bodegasDAO.findAll();
		request.setAttribute("bodega", lstB);
		/*TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();*/
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
		request.getSession().removeAttribute(BDG_ID);
		request.getSession().setAttribute(METHOD_NAME, "invalidSave");
		request.setAttribute("form", estantesForm);
		request.setAttribute(Constantes.ACCION_KEY, "/estantes");
        request.setAttribute("filtro", "0");
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("invBodBodegas.bodNombre","stnCodigo","stnCantFilas","stnCantColumnas",
				"stnEstado","stnId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.estantes.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("invBodBodegas.bodNombre");
		nombreColumna.setTitleKey("tbl.estantes.invBodBodegas.bodNombre");
	
		nombreColumna = row.getColumn("stnCodigo");
		nombreColumna.setTitleKey("tbl.estantes.stnCodigo");
				
		nombreColumna = row.getColumn("stnCantFilas");
		nombreColumna.setTitleKey("tbl.estantes.stnCantFilas");

		nombreColumna = row.getColumn("stnCantColumnas");
		nombreColumna.setTitleKey("tbl.estantes.stnCantColumnas");
		
		nombreColumna = row.getColumn("stnEstado");
		nombreColumna.setTitleKey("tbl.estantes.stnEstado");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				if(value.toString().trim().equals("A")){
					value = "Activo";
				}else{
					value = "Inactivo";
				}
				return value;
			}
		});
		
		nombreColumna = row.getColumn("stnId");
		nombreColumna.setTitleKey("tbl.estantes.niveles");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvStnEstante estante = (InvStnEstante)item;
				value = "Editar";
				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/estantes.do?stnId="+estante.getStnId().toString()+"&accion=edit";
				html.a().href().quote().append(link).quote().append("class=\"linkEditar\"").title(value.toString()).close();
				//html.a().href().quote().append(link).quote().close();
				//html.append(value);
				html.aEnd();
				
				HtmlBuilder htmlNv = new HtmlBuilder();
				String niveles = getResources(request).getMessage("cmd.estantes.niveles");
				String linkTe = tableFacade.getWebContext().getContextPath();
				linkTe += "/inventario/nivel.do?stnId="+estante.getStnId().toString()+"&accion=level";
				htmlNv.a().href().quote().append(linkTe).quote().append("class=\"linkNivel\"").title(niveles).close();
				//htmlNv.append(niveles);
				htmlNv.aEnd();
				
				return html.toString() + " |  " + htmlNv.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("invBodBodegas.bodNombre","stnCodigo","stnCantFilas","stnCantColumnas",
					"stnEstado");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.estantes.caption");
			
			Row row = table.getRow();
			
			Column nombreColumna = row.getColumn("invBodBodegas.bodNombre");
			nombreColumna.setTitleKey("tbl.estantes.invBodBodegas.bodNombre");
		
			nombreColumna = row.getColumn("stnCodigo");
			nombreColumna.setTitleKey("tbl.estantes.stnCodigo.x");
					
			nombreColumna = row.getColumn("stnCantFilas");
			nombreColumna.setTitleKey("tbl.estantes.stnCantFilas");

			nombreColumna = row.getColumn("stnCantColumnas");
			nombreColumna.setTitleKey("tbl.estantes.stnCantColumnas");
			
			nombreColumna = row.getColumn("stnEstado");
			nombreColumna.setTitleKey("tbl.estantes.stnEstado");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property, rowcount);
					if(value.toString().trim().equals("A")){
						value = "Activo";
					}else{
						value = "Inactivo";
					}
					return value;
				}
			});
			
			tableFacade.render();
	}
	 
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EstantesForm estantesForm = (EstantesForm)form;
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		Transaction tx = estanteDAO.getSession().beginTransaction();
		if(estantesForm.getStnCantFilas() < 1 || estantesForm.getStnCantColumnas() < 1){
			String forward = mensajes("error.estantes.cantNiveles.zero", estantesForm, request, response);
			return mapping.findForward(forward);
		}
		try {
			estanteDAO.save(estantesForm.getEstanteH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			int rows = estantesForm.getStnCantFilas();
			while(rows > 0){
				int cols = estantesForm.getStnCantColumnas();
				while(cols > 0){
					InvNivNivelDAO nivelDAO = new InvNivNivelDAO(getSessionHibernate(request));
					InvNivNivelId nivelId = new InvNivNivelId();
					nivelId.setInvStnEstante(estantesForm.getEstanteH());
					nivelId.setNivColId(cols);
					nivelId.setNivFilaId(rows);
					InvNivNivel nivel = new InvNivNivel();
					nivel.setId(nivelId);
					nivel.setNivEstado("A");
					nivel.setAudFechaCreacion(new Date());
					nivel.setAudFechaModificacion(new Date());
					nivel.setAudUsuarioCreacion(estantesForm.getUsuarioConectado().getNombreUsuario());
					nivel.setAudUsuarioModificacion(estantesForm.getUsuarioConectado().getNombreUsuario());
					Transaction txn = nivelDAO.getSession().beginTransaction();
					nivelDAO.save(nivel);
					txn.commit();
					cols = cols - 1;
				}
				rows = rows - 1;
			}
			
			estanteDAO.getSession().flush();
			estanteDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}

	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EstantesForm estantesForm = (EstantesForm)form;
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		Transaction tx = estanteDAO.getSession().beginTransaction();
		try {
			estanteDAO.merge(estantesForm.getEstanteH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			estanteDAO.getSession().flush();
			estanteDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EstantesForm estantesForm = (EstantesForm)form;
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		Transaction tx = estanteDAO.getSession().beginTransaction();
		try {
			estantesForm.setEstanteH(estanteDAO.findById(estantesForm.getStnId()));
			estanteDAO.delete(estantesForm.getEstanteH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			estanteDAO.getSession().flush();
			estanteDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EstantesForm estantesForm = (EstantesForm)form;
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
		estantesForm.setEstanteH(estanteDAO.findById(estantesForm.getStnId()));
		java.util.List lstB = bodegasDAO.findAll();
		request.getSession().setAttribute(METHOD_NAME, "invalidUpdate");
		//request.getSession().setAttribute(BDG_ID, bodegasForm.getBodId());
		request.setAttribute("form", estantesForm);
		request.setAttribute("bodega", lstB);
		request.setAttribute(Constantes.ACCION_KEY, "/estantes");
        request.setAttribute("filtro", "1");
		return mapping.findForward("dml");
	}
	
		
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return lista(mapping, form, request, response);
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		EstantesForm estantesForm = (EstantesForm)form;
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		
		//request.getSession().removeAttribute("id");
		String methodName = (String)request.getSession().getAttribute(METHOD_NAME);
		if(methodName!= null){
			if(methodName.equals("invalidSave")){
				java.util.List lst = null;
				lst  = estanteDAO.findAll();
				InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
				java.util.List lstB = bodegasDAO.findAll();
				request.setAttribute("bodega", lstB);
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
				request.getSession().removeAttribute(BDG_ID);
				request.getSession().setAttribute(METHOD_NAME, "invalidSave");
				request.setAttribute("form", estantesForm);
				request.setAttribute(Constantes.ACCION_KEY, "/estantes");
		        request.setAttribute("filtro", "0");
		        saveMessages(request, errors);
				return mapping.findForward("lista");
			}
			if(methodName.equals("invalidUpdate")){
				InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
				estantesForm.setEstanteH(estanteDAO.findById(estantesForm.getStnId()));
				java.util.List lstB = bodegasDAO.findAll();
				request.getSession().setAttribute(METHOD_NAME, "invalidUpdate");
				//request.getSession().setAttribute(BDG_ID, bodegasForm.getBodId());
				request.setAttribute("form", estantesForm);
				request.setAttribute("bodega", lstB);
				request.setAttribute(Constantes.ACCION_KEY, "/estantes");
		        request.setAttribute("filtro", "1");
				saveMessages(request, errors);
				return mapping.findForward("dml");
			}
		}
		return lista(mapping, form, request, response);
	}
	
	public String mensajes(String msg,EstantesForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = new ActionErrors();
		EstantesForm estantesForm = (EstantesForm)form;
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		
		//request.getSession().removeAttribute("id");
		String methodName = (String)request.getSession().getAttribute(METHOD_NAME);
		if(methodName!= null){
			if(methodName.equals("invalidSave")){
				java.util.List lst = null;
				lst  = estanteDAO.findAll();
				InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
				java.util.List lstB = bodegasDAO.findAll();
				request.setAttribute("bodega", lstB);
				TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
				tableFacade.setItems(lst);
				//---- Genera los tipos de formas con que se podran exportar los datos
				tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
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
				request.getSession().removeAttribute(BDG_ID);
				request.getSession().setAttribute(METHOD_NAME, "invalidSave");
				request.setAttribute("form", estantesForm);
				request.setAttribute(Constantes.ACCION_KEY, "/estantes");
		        request.setAttribute("filtro", "0");
		        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
		        saveMessages(request, errors);
				return "lista";
			}
			if(methodName.equals("invalidUpdate")){
				InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
				estantesForm.setEstanteH(estanteDAO.findById(estantesForm.getStnId()));
				java.util.List lstB = bodegasDAO.findAll();
				request.getSession().setAttribute(METHOD_NAME, "invalidUpdate");
				//request.getSession().setAttribute(BDG_ID, bodegasForm.getBodId());
				request.setAttribute("form", estantesForm);
				request.setAttribute("bodega", lstB);
				request.setAttribute(Constantes.ACCION_KEY, "/estantes");
		        request.setAttribute("filtro", "1");
		        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
		        saveMessages(request, errors);
				return "dml";
			}
		}
		return "lista";
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.estantes.lista", "lista");
		map.put("cmd.estantes.guardar", "guardar");
		map.put("cmd.estantes.eliminar", "eliminar");
		map.put("cmd.estantes.salvar", "salvar");
		map.put("cmd.estantes.editar", "editar");
		map.put("cmd.estantes.cancelar", "cancelar");
		map.put("cmd.estantes.redirectInvalidData","redirectInvalidData");
		return map;
	}
}