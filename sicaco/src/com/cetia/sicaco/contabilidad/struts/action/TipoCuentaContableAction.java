/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.contabilidad.struts.action;

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

import com.cetia.sicaco.contabilidad.struts.form.TipoCuentaContableForm;
import com.cetia.sicaco.hibernate.ConTicTipoCuenta;
import com.cetia.sicaco.hibernate.ConTicTipoCuentaDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 11-22-2008
 * 
 * XDoclet definition:
 * @struts.action path="/tipoCuentaContable" name="tipoCuentaContableForm" input="redirectInvalidData" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.contabilidad.tipoCuentaContable"
 */
public class TipoCuentaContableAction extends DMLAction {
	
	private static final String TABLA_ID="conTicTipoCuenta";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
 		ConTicTipoCuentaDAO tipoCuentaDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		List lst = tipoCuentaDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
        	//---- exporta la tabla
        //    export(tableFacade);
            return null; 
        } else {
        	String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		request.setAttribute("form", form);
		request.setAttribute(Constantes.ACCION_KEY, "/tipoCuentaContable");
		return mapping.findForward("lista");
	}
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("ticNombre","ticAcreeDeudo");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tic.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("ticNombre");
		nombreColumna.setTitleKey("tbl.tic.ticNombre");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				ConTicTipoCuenta tipoCuenta = (ConTicTipoCuenta) item;
				HtmlBuilder html = new HtmlBuilder();
				//---- Generar link para entrar a edicion
				String link = tableFacade.getWebContext().getContextPath();
				link += "/contabilidad/tipoCuentaContable.do?&ticId="+tipoCuenta.getTicId()+"&accion=cargarDatos&mdf=true";
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();				
				return html.toString();	
			}
		});
		
		nombreColumna = row.getColumn("ticAcreeDeudo");
		nombreColumna.setTitleKey("tbl.tic.ticAcreeDeudo");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
			String value = null;
			ConTicTipoCuenta tipoCuenta = (ConTicTipoCuenta) item;
			if(tipoCuenta.getTicAcreeDeudo() != null){
				if(tipoCuenta.getTicAcreeDeudo().equals(0)) value = "Si";
				else  value = "No";
			}else{
				value = "--";
			}
			return value;
			}
		});	
		
		return tableFacade.render();
	}
	
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		ConTicTipoCuentaDAO ticDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		TipoCuentaContableForm ticForm = (TipoCuentaContableForm) form;
		if(ticDAO.findByTicNombre(ticForm.getTicNombre()).isEmpty()){
			Transaction tx = ticDAO.getSession().beginTransaction();
			try{
				ticDAO.save(ticForm.getTipoCuentaH());
				tx.commit();
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				ticDAO.getSession().flush();
				ticDAO.getSession().clear();
				
			}
		}else{
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.tic.nombreRepetido"));
			saveMessages(request, errors);
		}
		return lista(mapping, ticForm, request, response);
	}

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		ConTicTipoCuentaDAO ticDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		TipoCuentaContableForm ticForm = (TipoCuentaContableForm) form;
		if(ticDAO.findByUpdatedName(ticForm.getTicId(), ticForm.getTicNombre()).isEmpty()){
			Transaction tx = ticDAO.getSession().beginTransaction();
			try{
				ticDAO.merge(ticForm.getTipoCuentaH());
				tx.commit();
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				ticDAO.getSession().flush();
				ticDAO.getSession().clear();
				
			}
		}else{
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.tic.nombreRepetido"));
			saveMessages(request, errors);
		}
		ticForm.setMdf(false);
		return lista(mapping,ticForm, request, response);
	}
	
	public ActionForward cargarDatos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ConTicTipoCuentaDAO ticDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		TipoCuentaContableForm ticForm = (TipoCuentaContableForm) form;
		ticForm.setTipoCuentaH(ticDAO.findById(ticForm.getTicId()));
		return lista(mapping, form, request, response);
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		saveMessages(request, errors);
		return lista(mapping, form, request, response);
	}
	
	
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cmd.tic.lista","lista");
		map.put("cmd.tic.guardar","guardar");
		map.put("cmd.tic.modificar","modificar");
		map.put("cmd.tic.cargarDatos","cargarDatos");
		map.put("cmd.tic.redirectInvalidData","redirectInvalidData");
		return map;
	}
}