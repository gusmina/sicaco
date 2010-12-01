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

import com.cetia.sicaco.hibernate.InvMedMedida;
import com.cetia.sicaco.hibernate.InvMedMedidaDAO;
import com.cetia.sicaco.hibernate.InvTmeTipoMedida;
import com.cetia.sicaco.hibernate.InvTmeTipoMedidaDAO;
import com.cetia.sicaco.inventario.struts.form.MedidaForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

public class MedidaAction extends DMLAction {

	private static final String TABLE_ID="MedidaTableId";
	private static final String MED_ID="MedId";
	private static final String LIST_TIPO_MED="listaTipoMed";

	@SuppressWarnings("unchecked")
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MedidaForm medidaForm = (MedidaForm)form;
		List<InvMedMedida> lst = null;
		List<InvTmeTipoMedida> lstTipoMedida = null;
		InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
		lst =   (List<InvMedMedida>)invMedidaDAO.findAll();
		InvTmeTipoMedidaDAO invTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		lstTipoMedida = invTipoMedidaDAO.findAll();
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
        request.setAttribute(LIST_TIPO_MED, lstTipoMedida);
        request.setAttribute(Constantes.ACCION_KEY,"/medida");
        request.setAttribute("filtro", "0");
        request.setAttribute("form", medidaForm);
		return mapping.findForward("lista");
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("medId","medNombreMedida","invTmeTipoMedida.tmeNombre","medBogus");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.medida.caption");
		Row row = table.getRow();
		
		Column nombreColumna2 = row.getColumn("medId");
		nombreColumna2.setTitleKey("tbl.medida.codigomedida");
		
		Column nombreColumna = row.getColumn("medBogus");
		nombreColumna.setTitleKey("tbl.medida.tipo.eliminar");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvMedMedida medida = (InvMedMedida)item;
				
				HtmlBuilder html = new HtmlBuilder();
				value = "Eliminar";
				//html.a().onclick("handlerDeleteButton1('mediId="+ medida.getMedId() + "');").id("deleteButtonId").close();
				html.a().style("cursor: pointer;").onclick("handlerDeleteButton('"+ medida.getMedId()+ "');").append("class=\"linkEditar\"").title(value.toString()).id("deleteButtonId").close();
				//html.append(value);
				html.aEnd();
				
				HtmlBuilder htmlC = new HtmlBuilder();
				String conv = "Conversi&oacute;n";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/medida.do?medId="+medida.getMedId().toString()+"&accion=convert";
				htmlC.a().href().quote().append(link).quote().append("class=\"linkCalculadora\"").title(conv).close();
			//	htmlC.a().href().quote().append(link).quote().close();
				//htmlC.append(conv);
				htmlC.aEnd();
								
				return html.toString() + " | " + htmlC.toString();
			}
		});
		
		nombreColumna = row.getColumn("medNombreMedida");
		nombreColumna.setTitleKey("tbl.medida.medNombremedida");

		nombreColumna = row.getColumn("invTmeTipoMedida.tmeNombre");
		nombreColumna.setTitleKey("tbl.medida.tipo.medNombremedida");
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("medId","medNombreMedida","invTmeTipoMedida.tmeNombre");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.medida.caption");
			Row row = table.getRow();
			
			Column nombreColumna = row.getColumn("medId");
			nombreColumna.setTitleKey("tbl.medida.codigomedida.x");
			
			nombreColumna = row.getColumn("medNombreMedida");
			nombreColumna.setTitleKey("tbl.medida.medNombremedida");
			

			nombreColumna = row.getColumn("invTmeTipoMedida.tmeNombre");
			nombreColumna.setTitleKey("tbl.medida.tipo.medNombremedida");
		tableFacade.render();
	}
	 
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			MedidaForm mform = (MedidaForm)form;
			InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
			Transaction tx = invMedidaDAO.getSession().beginTransaction();
			InvMedMedida modelMedida = mform.getInvMedida();
			try {
				invMedidaDAO.getSession().save(modelMedida);
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}finally{
				invMedidaDAO.getSession().flush();
				invMedidaDAO.getSession().clear();
				
			}
			return lista(mapping, form, request, response);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			MedidaForm mform = (MedidaForm)form;
			InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
			Transaction tx = invMedidaDAO.getSession().beginTransaction();
			try {
				invMedidaDAO.getSession().delete(invMedidaDAO.findById(mform.getMediId()));
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}finally{
				invMedidaDAO.getSession().flush();
				invMedidaDAO.getSession().clear();
				
			}
		return lista(mapping, form, request, response);
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			MedidaForm mform = (MedidaForm)form;
			InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
			Transaction tx = invMedidaDAO.getSession().beginTransaction();
			try {
				invMedidaDAO.getSession().merge(mform.getInvMedida());
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}finally{
				invMedidaDAO.getSession().flush();
				invMedidaDAO.getSession().clear();
				
			}
			request.setAttribute("form", mform);
		return mapping.findForward("lista");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward findByExample(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MedidaForm mform = (MedidaForm)form;
		InvTmeTipoMedidaDAO invTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		List lstTipoMedida = invTipoMedidaDAO.findAll();
		InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
		Transaction tx = invMedidaDAO.getSession().beginTransaction();
		List<InvMedMedida> lst = null;
		lst =  (List<InvMedMedida>)invMedidaDAO.findByCriteria(mform.getInvMedida());
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
        request.setAttribute(LIST_TIPO_MED, lstTipoMedida);
        request.setAttribute(Constantes.ACCION_KEY,"/medida");
        request.setAttribute("filtro", "0");
        request.setAttribute("form", mform);
		return mapping.findForward("lista");
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		MedidaForm medidaForm = (MedidaForm)form;
		List<InvMedMedida> lst = null;
		List<InvTmeTipoMedida> lstTipoMedida = null;
		InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
		lst =   (List<InvMedMedida>)invMedidaDAO.findAll();
		InvTmeTipoMedidaDAO invTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
		lstTipoMedida = invTipoMedidaDAO.findAll();
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
        request.setAttribute(LIST_TIPO_MED, lstTipoMedida);
        request.setAttribute(Constantes.ACCION_KEY,"/medida");
        request.setAttribute("filtro", "0");
        request.setAttribute("form", medidaForm);
        saveMessages(request, errors);
		return mapping.findForward("lista");
	}
		
		/*ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
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
		}
		saveMessages(request, errors);
		return mapping.findForward(forwardName);
	}*/
	
	@SuppressWarnings("unchecked")
	private String invalidSave(HttpServletRequest request,HttpServletResponse response,ActionForm form){
			List<InvMedMedida> lst = null;
			List<InvTmeTipoMedida> lstTipoMeList = null;
			InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
			lst =   (List<InvMedMedida>)invMedidaDAO.findAll();
			InvTmeTipoMedidaDAO invTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			lstTipoMeList = (List<InvTmeTipoMedida>)invTipoMedidaDAO.findAll();
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
	        request.setAttribute(LIST_TIPO_MED, lstTipoMeList);
	        request.setAttribute(Constantes.ACCION_KEY,"/medida");
	        request.setAttribute("filtro", "0");
		return "lista";
	}
	
	
	@SuppressWarnings("unchecked")
	private String invalidEdit(HttpServletRequest request,HttpServletResponse response,ActionForm form){
			MedidaForm mform = (MedidaForm)form;
			List<InvTmeTipoMedida> lstTipoMeList = null;
			InvMedMedida modelMedida = null;
			InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
			InvTmeTipoMedidaDAO invTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			lstTipoMeList = (List<InvTmeTipoMedida>)invTipoMedidaDAO.findAll();
			modelMedida = invMedidaDAO.findById((String)request.getSession().getAttribute(MED_ID));
			mform.setInvMedida(modelMedida);
			request.setAttribute(LIST_TIPO_MED, lstTipoMeList);
			request.setAttribute("mode","readonly");
			request.setAttribute("form",mform);
			request.setAttribute("filtro","1");
			request.setAttribute(Constantes.ACCION_KEY, "/medida");
		return "dml";
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward forwardToEdicion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			MedidaForm mform = (MedidaForm)form;
			List<InvTmeTipoMedida> lstTipoMeList = null;
			request.getSession().setAttribute(MED_ID,mform.getMedId());
			InvMedMedida modelMedida = null;
			InvMedMedidaDAO invMedidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
			InvTmeTipoMedidaDAO invTipoMedidaDAO = new InvTmeTipoMedidaDAO(getSessionHibernate(request));
			lstTipoMeList = (List<InvTmeTipoMedida>)invTipoMedidaDAO.findAll();
			modelMedida = invMedidaDAO.findById(mform.getMedId());
			mform.setInvMedida(modelMedida);
			request.setAttribute(LIST_TIPO_MED, lstTipoMeList);
			request.setAttribute("mode","readonly");
			request.setAttribute("form",mform);
			request.setAttribute("filtro","1");
			request.setAttribute(Constantes.ACCION_KEY, "/medida");
		return mapping.findForward("dml");
	}
	
	public ActionForward convert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("convert");
	}

	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.medida.lista", "lista");
		map.put("cmd.medida.eliminar", "eliminar");
		map.put("cmd.medida.findByExample", "findByExample");
		map.put("cmd.medida.guardar", "guardar");
		map.put("cmd.medida.editar", "editar");
		map.put("cmd.medida.forwardToEdicion", "forwardToEdicion");
		map.put("cmd.conversion.lista", "convert");
		map.put("cmd.medida.redirectInvalidData", "redirectInvalidData");
		return map;
	}
}
