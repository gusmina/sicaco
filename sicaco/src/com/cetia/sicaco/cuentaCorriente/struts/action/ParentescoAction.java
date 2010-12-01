/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
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

import com.cetia.sicaco.cuentaCorriente.struts.form.ParentescoForm;
import com.cetia.sicaco.hibernate.SecParParentesco;
import com.cetia.sicaco.hibernate.SecParParentescoDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 09-27-2008
 * 
 * XDoclet definition:
 * @struts.action path="/parentesco" name="parentezcoForm" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.cuentaCorriente.parentesco"
 * @struts.action-forward name="redirectInvalidData" path="/parentesco.do?accion=redirectInvalidData" redirect="true"
*/
public class ParentescoAction extends DMLAction {
	public String TABLA_ID = "secParParentesco";
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ParentescoForm parentescoForm = (ParentescoForm) form;
		SecParParentescoDAO secParParentezcoDAO = new SecParParentescoDAO(getSessionHibernate(request));
		List lst = secParParentezcoDAO.findAll();
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
		request.setAttribute("form", parentescoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/parentesco");
		return mapping.findForward("lista");
	}
	
	//---- metodo que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("parDescripcion", "parId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.par.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("parDescripcion");
		nombreColumna.setTitleKey("tbl.par.parDescripcion");
		
		nombreColumna = row.getColumn("parId");
		nombreColumna.setTitleKey("tbl.par.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecParParentesco parentesco = (SecParParentesco)item;
				HtmlBuilder html = new HtmlBuilder();
				value = "Modificar";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/parentesco.do?parId="+parentesco.getParId()+"&parDescripcion="
				+parentesco.getParDescripcion()+"&accion=cargarDatos&mdf=true";
				html.a().href().quote().append(link).quote().append("class=\"linkEditar\"").title(value.toString()).close();
				/*html.a().href().quote().append(link).quote().close();
				html.append(value);*/
				html.aEnd();				
				return html.toString();		
			}
		});
		
		return tableFacade.render();
	}
	
	//---- metodo que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("parDescripcion", "parId");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.par.caption");
			
			Row row = table.getRow();
			Column nombreColumna = row.getColumn("parDescripcion");
			nombreColumna.setTitleKey("tbl.par.parDescripcion");
		tableFacade.render();
	}
	
	 public ActionForward guardar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			ParentescoForm parentescoForm = (ParentescoForm)form;
			SecParParentescoDAO parentescoDAO = new SecParParentescoDAO(getSessionHibernate(request));
			Transaction tx = parentescoDAO.getSession().beginTransaction();
			try{
				if(parentescoForm.getSecParParentescoH().getParDescripcion().trim()!=""){
					if(!parentescoForm.isMdf()){//ingresando un nuevo registro
						
							if(parentescoDAO.findByParDescripcion(parentescoForm.getSecParParentescoH().getParDescripcion()).isEmpty()){
								parentescoForm.getSecParParentescoH().setParId(parentescoDAO.nextId());
								parentescoDAO.save(parentescoForm.getSecParParentescoH());
							}else{
								mensajes("errors.parDescripcionRepetida",request);
								return lista(mapping, form, request, response);
							}
						
						
					}else{//modificando un registro
						if(parentescoDAO.findByUpdatedName(parentescoForm.getParId(),parentescoForm.getParDescripcion()).isEmpty()){
							parentescoDAO.merge(parentescoForm.getSecParParentescoH());
						}else{
							mensajes("errors.parDescripcionRepetida",request);
							return lista(mapping, form, request, response);
						}
					}
				}else{
					mensajes("errors.parDescripcionVacia",request);
					return lista(mapping, form, request, response);
				}
			}catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				tx.commit();
				parentescoDAO.getSession().flush();
				parentescoDAO.getSession().clear();
				
			}
			return lista(mapping,new ParentescoForm(),request,response);
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
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.par.lista", "lista");
		map.put("cmd.par.guardar", "guardar");
		map.put("cmd.par.cargarDatos", "cargarDatos");
		map.put("cmd.par.redirectInvalidData", "redirectInvalidData");
		return map;
	}
}