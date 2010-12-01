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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionErrors;
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

import com.cetia.sicaco.cuentaCorriente.struts.form.TipoNotaForm;
import com.cetia.sicaco.hibernate.CtaTntTipoNota;
import com.cetia.sicaco.hibernate.CtaTntTipoNotaDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 08-17-2008
 * 
 * XDoclet definition:
 * @struts.action path="/tipoNota" name="tipoNotaForm" input="verLista" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="verLista" path="pagina-lista.cuentaCorriente.tipoNota"
 */
public class TipoNotaAction extends DMLAction {
	
	private static final String TABLA_ID = null;
	
	public TipoNotaAction() {
		super();
	}
	
	/*
	 * Generated Methods
	 */

	private boolean salvarLista(List<CtaTntTipoNota> notas,
			HttpServletRequest request, HttpServletResponse response) {
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(notas);

		// ---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
			// ---- exporta la tabla
			export(tableFacade);
			return false;
		} else {
			// ---- genera el html de la tabla para ser mostrada
			String html = html(tableFacade, request);
			request.setAttribute(Constantes.LISTA_KEY, html);
			return true;
		}
	}
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("tntNombre", "tntDescripcion");
		Table table = tableFacade.getTable();
		// ---- Titulo de la tabla
		table.setCaptionKey("tbl.cuentaCorriente.tipoNota.caption");

		Row row = table.getRow();
		Column nombreColumna = row.getColumn("tntNombre");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.tipoNota.tntNombre");

		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaTntTipoNota nota = (CtaTntTipoNota) item;
				HtmlBuilder html = new HtmlBuilder();
				
				// ---- Generar link para entrar a edicion
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/tipoNota.do?&tntId="
						+ nota.getTntId()
						+ "&accion=Ver";
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();
				return html.toString();
			}
		});

		nombreColumna = row.getColumn("tntDescripcion");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.tipoNota.tntDescripcion");

		return tableFacade.render();
	}

	private void export(final TableFacade tableFacade) {
		tableFacade.setColumnProperties("tntNombre", "tntDescripcion");
		Table table = tableFacade.getTable();
		// ---- Titulo de la tabla
		table.setCaptionKey("tbl.cuentaCorriente.tipoNota.caption");

		Row row = table.getRow();
		Column nombreColumna = row.getColumn("tntNombre");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.tipoNota.tntNombre");

		nombreColumna = row.getColumn("tntDescripcion");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.tipoNota.tntDescripcion");
	}

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward buscar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//TipoNotaForm tipoNotaForm = (TipoNotaForm) form;
		List<CtaTntTipoNota> notas = null;
		CtaTntTipoNotaDAO tipoNotaDAO = new CtaTntTipoNotaDAO(getSessionHibernate(request));
		notas = tipoNotaDAO.findAll();
		
		return (salvarLista(notas,request,response))?mapping.findForward("verLista"):null;
	}
	
	

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoNotaForm tipoNotaForm = (TipoNotaForm) form;
		CtaTntTipoNotaDAO tipoNotaDAO = new CtaTntTipoNotaDAO(getSessionHibernate(request));
		Transaction trx = tipoNotaDAO.getSession().beginTransaction();
		try{
			if(tipoNotaDAO.findByTntNombre(tipoNotaForm.getTipoNota().getTntNombre()).isEmpty()){
				tipoNotaDAO.save(tipoNotaForm.getTipoNota());
				tipoNotaForm.setTipoNota(new CtaTntTipoNota());
				ActionMessages messages = new ActionMessages();
				messages.add("mensaje", new ActionMessage("msg.tipoNota.exitoA"));
				saveMessages(request, messages);
			}else{
				mensajes("errors.tntNombreRepetido",request);
				return buscar(mapping, form, request, response);
			}
		}catch(Exception e){
			trx.rollback();
			e.printStackTrace();
		}finally{
			trx.commit();
			tipoNotaDAO.getSession().flush();
			tipoNotaDAO.getSession().clear();
			
		}
		return buscar(mapping, form, request, response);
	}
	
	public void mensajes(String msg, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoNotaForm tipoNotaForm = (TipoNotaForm) form;
		CtaTntTipoNotaDAO tipoNotaDAO = new CtaTntTipoNotaDAO(getSessionHibernate(request));
		Transaction trx = tipoNotaDAO.getSession().beginTransaction();
		try{
			if(tipoNotaDAO.findByUpdatedName(tipoNotaForm.getTntId(),tipoNotaForm.getTntNombre()).isEmpty()){
				tipoNotaDAO.merge(tipoNotaForm.getTipoNota());
				ActionMessages messages = new ActionMessages();
				messages.add("mensaje", new ActionMessage("msg.tipoNota.exitoG"));
				saveMessages(request, messages);
			}else{
				mensajes("errors.tntNombreRepetido",request);
				return buscar(mapping, form, request, response);
			}
		}catch(Exception e){
			trx.rollback();
			e.printStackTrace();
		}finally{
			trx.commit();	
			tipoNotaDAO.getSession().flush();
			tipoNotaDAO.getSession().clear();
			
		}
		return buscar(mapping, form, request, response);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoNotaForm tipoNotaForm = (TipoNotaForm) form;
		CtaTntTipoNotaDAO tipoNotaDAO = new CtaTntTipoNotaDAO(getSessionHibernate(request));
		Transaction trx = tipoNotaDAO.getSession().beginTransaction();
		tipoNotaDAO.delete(tipoNotaDAO.findById(tipoNotaForm.getTntId()));
		trx.commit();
		tipoNotaDAO.getSession().flush();
		tipoNotaDAO.getSession().clear();
		ActionMessages messages = new ActionMessages();
		messages.add("mensaje", new ActionMessage("msg.tipoNota.exitoE"));
		saveMessages(request, messages);
		return buscar(mapping, form, request, response);
	}
	
	public ActionForward ver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoNotaForm tipoNotaForm = (TipoNotaForm) form;
		CtaTntTipoNotaDAO tipoNotaDAO = new CtaTntTipoNotaDAO(getSessionHibernate(request));
		tipoNotaForm.setTipoNota(tipoNotaDAO.findById(tipoNotaForm.getTntId()));
		return buscar(mapping, form, request, response);
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoNotaForm tipoNotaForm = (TipoNotaForm) form;
		tipoNotaForm.setTipoNota(new CtaTntTipoNota());
		return buscar(mapping, form, request, response);
	}
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		saveMessages(request, errors);
		return buscar(mapping, form, request, response);
	}
	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("cmd.tipoNota.buscar", "buscar");
		map.put("cmd.tipoNota.insertar", "agregar");
		map.put("cmd.tipoNota.actualizar", "guardar");
		map.put("cmd.tipoNota.eliminar", "eliminar");
		map.put("cmd.tipoNota.cargar", "ver");
		map.put("cmd.tipoNota.cancelar", "cancelar");
		map.put("cmd.tipoNota.lista", "buscar");
		
		return map;
	}
}