package com.cetia.sicaco.cuentaCorriente.struts.action;

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

import com.cetia.sicaco.cuentaCorriente.struts.form.TipoFiadorForm;
import com.cetia.sicaco.hibernate.CtaTfiTipoFiador;
import com.cetia.sicaco.hibernate.CtaTfiTipoFiadorDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

public class TipoFiadorAction extends DMLAction {
	public static final String TABLA_ID = "ctaTfiTipoFiador";

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		TipoFiadorForm tipoFiadorForm = (TipoFiadorForm) form;
		CtaTfiTipoFiadorDAO tipoFiadorDAO = new CtaTfiTipoFiadorDAO(getSessionHibernate(request));
		List lst = tipoFiadorDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
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
		request.setAttribute("form", tipoFiadorForm);
		request.setAttribute(Constantes.ACCION_KEY, "/tipoFiador");
		return mapping.findForward("lista");
	}
	
	//---- metodo que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("tfiNombre", "tfiDescripcion", "tfiId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tfi.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("tfiNombre");
		nombreColumna.setTitleKey("tbl.tfi.Nombre");
		
		nombreColumna = row.getColumn("tfiDescripcion");
		nombreColumna.setTitleKey("tbl.tfi.desc");
		
		nombreColumna = row.getColumn("tfiId");
		nombreColumna.setTitleKey("tbl.tfi.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaTfiTipoFiador tipoFiador= (CtaTfiTipoFiador)item;
				HtmlBuilder html = new HtmlBuilder();
				value = "Modificar";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/tipoFiador.do?tfiId="+tipoFiador.getTfiId()+"&tfiNombre="
				+tipoFiador.getTfiNombre()+"&tfiDescripcion="+ tipoFiador.getTfiDescripcion()+
				"&accion=cargarDatos&mdf=true";
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();				
				return html.toString();		
			}
		});
		
		return tableFacade.render();
	}
	
	//---- metodo que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("tfiNombre", "tfiDescripcion");
			Table table = tableFacade.getTable();
			
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.tfi.caption");
			
			Row row = table.getRow();
			Column nombreColumna = row.getColumn("tfiNombre");
			nombreColumna.setTitleKey("tbl.tfi.tfiNombre");
			
			nombreColumna = row.getColumn("tfiDescripcion");
			nombreColumna.setTitleKey("tbl.tfi.tfiDescripcion");
			
		tableFacade.render();
	}
	
	 public ActionForward guardar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			TipoFiadorForm tipoFiadorForm = (TipoFiadorForm)form;
			CtaTfiTipoFiadorDAO tipoFiadorDAO= new CtaTfiTipoFiadorDAO(getSessionHibernate(request));
			Transaction tx = tipoFiadorDAO.getSession().beginTransaction();
			try{
				if(!tipoFiadorForm.isMdf()){//ingresando un nuevo registro
					if(tipoFiadorDAO.findByTfiNombre(tipoFiadorForm.getCtatFiadorH().getTfiNombre()).isEmpty()){
						tipoFiadorDAO.save(tipoFiadorForm.getCtatFiadorH());
					}else{
						mensajes("errors.tfiNombreRepetido",request);
						return lista(mapping, form, request, response);
					}
				}else{//modificando un registro
					if(tipoFiadorDAO.findByUpdatedName(tipoFiadorForm.getTfiId(),tipoFiadorForm.getTfiNombre()).isEmpty()){
						tipoFiadorDAO.merge(tipoFiadorForm.getCtatFiadorH());
					}else{
						mensajes("errors.tfiNombreRepetido",request);
						return lista(mapping, form, request, response);
					}
				}
				
			}catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				tx.commit();
				tipoFiadorDAO.getSession().flush();
				tipoFiadorDAO.getSession().clear();
				
			}
			return lista(mapping,new TipoFiadorForm(),request,response);
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
		map.put("cmd.tfi.lista", "lista");
		map.put("cmd.tfi.guardar", "guardar");
		map.put("cmd.tfi.cargarDatos", "cargarDatos");
		map.put("cmd.tfi.redirectInvalidData", "redirectInvalidData");
		return map;
	}
}
