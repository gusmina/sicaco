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

import com.cetia.sicaco.cuentaCorriente.struts.form.TipoGarantiaForm;
import com.cetia.sicaco.hibernate.CtaTgaTipoGarantia;
import com.cetia.sicaco.hibernate.CtaTgaTipoGarantiaDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

public class TipoGarantiaAction extends DMLAction {
	
	public static final String TABLA_ID = "ctaTgaTipoGarantia";

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		TipoGarantiaForm tipoGarantiaForm = (TipoGarantiaForm) form;
		CtaTgaTipoGarantiaDAO tipoGarantiaDAO = new CtaTgaTipoGarantiaDAO(getSessionHibernate(request));
		List lst = tipoGarantiaDAO.findAll();
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
		request.setAttribute("form", tipoGarantiaForm);
		request.setAttribute(Constantes.ACCION_KEY, "/tipoGarantiaAction");
		return mapping.findForward("lista");
	}
	
	//---- metodo que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("tgaNombre", "tgaDescripcion", "tgaId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tga.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("tgaNombre");
		nombreColumna.setTitleKey("tbl.tga.nombre");
		
		nombreColumna = row.getColumn("tgaDescripcion");
		nombreColumna.setTitleKey("tbl.tga.descripcion");
		
		nombreColumna = row.getColumn("tgaId");
		nombreColumna.setTitleKey("tbl.tga.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaTgaTipoGarantia tipoGarantia= (CtaTgaTipoGarantia)item;
				HtmlBuilder html = new HtmlBuilder();
				value = "Modificar";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/tipoGarantiaAction.do?tgaId="+tipoGarantia.getTgaId()+"&tgaNombre="
				+tipoGarantia.getTgaNombre()+"&tgaDescripcion="+ tipoGarantia.getTgaDescripcion()+
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
		 tableFacade.setColumnProperties("tgaNombre", "tgaDescripcion");
			Table table = tableFacade.getTable();
			
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.tga.caption");
			
			Row row = table.getRow();
			Column nombreColumna = row.getColumn("tgaNombre");
			nombreColumna.setTitleKey("tbl.tga.nombre");
			
			nombreColumna = row.getColumn("tgaDescripcion");
			nombreColumna.setTitleKey("tbl.tga.descripcion");
			
		tableFacade.render();
	}
	
	 public ActionForward guardar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			TipoGarantiaForm tipoGarantiaForm = (TipoGarantiaForm)form;
			CtaTgaTipoGarantiaDAO tipoGarantiaDAO= new CtaTgaTipoGarantiaDAO(getSessionHibernate(request));
			Transaction tx = tipoGarantiaDAO.getSession().beginTransaction();
			try{
				if(!tipoGarantiaForm.isMdf()){//ingresando un nuevo registro
					if(tipoGarantiaDAO.findByTgaNombre(tipoGarantiaForm.getCtaTgaTipoGarantiaH().getTgaNombre()).isEmpty()){
						tipoGarantiaDAO.save(tipoGarantiaForm.getCtaTgaTipoGarantiaH());
					}else{
						mensajes("errors.tgaNombreRepetido",request);
						return lista(mapping, form, request, response);
					}
				}else{//modificando un registro
					if(tipoGarantiaDAO.findByUpdatedName(tipoGarantiaForm.getTgaId(),tipoGarantiaForm.getTgaNombre()).isEmpty()){
						tipoGarantiaDAO.merge(tipoGarantiaForm.getCtaTgaTipoGarantiaH());
					}else{
						mensajes("errors.tgaNombreRepetido",request);
						return lista(mapping, form, request, response);
					}
				}
				tx.commit();
			}catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				tipoGarantiaDAO.getSession().flush();
				tipoGarantiaDAO.getSession().clear();
				
			}
			return lista(mapping,new TipoGarantiaForm(),request,response);
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
		map.put("cmd.tga.lista", "lista");
		map.put("cmd.tga.guardar", "guardar");
		map.put("cmd.tga.cargarDatos", "cargarDatos");
		map.put("cmd.tga.redirectInvalidData", "redirectInvalidData");
		return map;
	}

}
