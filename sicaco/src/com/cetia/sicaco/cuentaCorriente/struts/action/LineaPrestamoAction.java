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
import org.jmesa.view.editor.DateCellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.cuentaCorriente.struts.form.LineaPrestamoForm;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamo;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamoDAO;
import com.cetia.sicaco.hibernate.CtrParParametros;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 08-17-2008
 * 
 * XDoclet definition:
 * @struts.action path="/lineaPrestamo" name="lineaPrestamoForm" input="verLista" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="verLista" path="pagina-lista.cuentaCorriente.lineaPrestamo"
 */
public class LineaPrestamoAction extends DMLAction {
	
	private static final String TABLA_ID = "lineaPrestamoTabla";
	/*
	 * Generated Methods
	 */
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		LineaPrestamoForm lineaPrestamoForm = (LineaPrestamoForm) form;
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		CtrParParametros parametros = parametrosDAO.findById("PORCENTAJE_GENERAL_COMISION");
		lineaPrestamoForm.setLprComision(parametros.getParValorNumber());
		List<CtaLprLineaPrestamo> lineas = lineaPrestamoDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lineas);
		
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
        	//---- exporta la tabla
          //  export(tableFacade,cuentaAsociadoForm.getTipoCuentaMadre());
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		request.setAttribute("form", lineaPrestamoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/lineaPrestamo");
		return mapping.findForward("verLista");
	}
	
	
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("lprNombre",
				"lprDescripcion", "lprDesde", "lprHasta"/*, "lprOrdenAprov"*/);
		Table table = tableFacade.getTable();
		// ---- Titulo de la tabla
		table.setCaptionKey("tbl.cuentaCorriente.lineaPrestamo.caption");

		Row row = table.getRow();
		Column nombreColumna = row.getColumn("lprNombre");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprNombre");

		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaLprLineaPrestamo linea = (CtaLprLineaPrestamo) item;
				HtmlBuilder html = new HtmlBuilder();
				
				// ---- Generar link para entrar a edicion
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/lineaPrestamo.do?lprId="
						+ linea.getLprId()
						+ "&accion=Ver&mdf=true";
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();
				return html.toString();
			}
		});

		nombreColumna = row.getColumn("lprDescripcion");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprDescripcion");

		nombreColumna = row.getColumn("lprDesde");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprDesde");
		nombreColumna.getCellRenderer().setCellEditor(new DateCellEditor(Constantes.FORMATE_DATE));

		nombreColumna = row.getColumn("lprHasta");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprHasta");
		nombreColumna.getCellRenderer().setCellEditor(new DateCellEditor(Constantes.FORMATE_DATE));
		
	/*	nombreColumna = row.getColumn("lprOrdenAprov");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprOrdenAprov");*/
		
		return tableFacade.render();
	}

	private void export(TableFacade tableFacade) {
		tableFacade.setColumnProperties("lprNombre",
				"lprDescripcion", "lprDesde", "lprHasta"/*, "lprOrdenAprov"*/);
		Table table = tableFacade.getTable();
		// ---- Titulo de la tabla
		table.setCaptionKey("tbl.cuentaCorriente.lineaPrestamo.caption");

		Row row = table.getRow();
		Column nombreColumna = row.getColumn("lprNombre");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprNombre");

		nombreColumna = row.getColumn("lprDescripcion");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lprDescripcion");

		nombreColumna = row.getColumn("lprDesde");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprDesde");
		nombreColumna.getCellRenderer().setCellEditor(new DateCellEditor(Constantes.FORMATE_DATE));

		nombreColumna = row.getColumn("lprHasta");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprHasta");
		nombreColumna.getCellRenderer().setCellEditor(new DateCellEditor(Constantes.FORMATE_DATE));
		
		/*nombreColumna = row.getColumn("lprOrdenAprov");
		nombreColumna.setTitleKey("tbl.cuentaCorriente.lineaPrestamo.lprOrdenAprov");*/
		
		tableFacade.render();
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LineaPrestamoForm lineaPrestamoForm = (LineaPrestamoForm) form;
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		Transaction trx = lineaPrestamoDAO.getSession().beginTransaction();
		try{
			if(lineaPrestamoDAO.findByLprNombre(lineaPrestamoForm.getLineaPrestamo().getLprNombre()).isEmpty()){
				lineaPrestamoDAO.save(lineaPrestamoForm.getLineaPrestamo());
			}else{
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage("msg.lineaPrestamo.exitoA"));
				saveMessages(request, messages);
				return lista(mapping, form, request, response);
			}
		}catch(Exception e){
			
		}finally{
			trx.commit();
			lineaPrestamoDAO.getSession().flush();
			lineaPrestamoDAO.getSession().clear();	
			lineaPrestamoForm.setLprId(new Integer(0));
		}	
		return lista(mapping, new LineaPrestamoForm(), request, response);
	}
	
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		LineaPrestamoForm lineaPrestamoForm = (LineaPrestamoForm) form;
		if(lineaPrestamoForm.getLprId()!= null){
			if(lineaPrestamoDAO.findByUpdatedName(lineaPrestamoForm.getLprId(), lineaPrestamoForm.getLprNombre()).isEmpty()){
				Transaction trx = lineaPrestamoDAO.getSession().beginTransaction();
				try{
					lineaPrestamoDAO.merge(lineaPrestamoForm.getLineaPrestamo());
					trx.commit();
					lineaPrestamoForm =  new LineaPrestamoForm();
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					lineaPrestamoDAO.getSession().flush();
					lineaPrestamoDAO.getSession().clear();
					
				}
			}else{
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage("msg.lineaPrestamo.lineaRepetida"));
				saveMessages(request, messages);
			}
		}
		return lista(mapping, lineaPrestamoForm, request, response);
	}

	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		LineaPrestamoForm lineaPrestamoForm = (LineaPrestamoForm) form;
		Transaction trx = lineaPrestamoDAO.getSession().beginTransaction();
		lineaPrestamoDAO.delete(lineaPrestamoForm.getLineaPrestamo());
		trx.commit();
		lineaPrestamoDAO.getSession().flush();
		lineaPrestamoDAO.getSession().clear();
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage("msg.lineaPrestamo.exitoE"));
		saveMessages(request, messages);
		return lista(mapping, form, request, response);
	}
	
	public ActionForward ver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		LineaPrestamoForm lineaPrestamoForm = (LineaPrestamoForm) form;
	    CtaLprLineaPrestamo lineaPrestamo = lineaPrestamoDAO.findById(lineaPrestamoForm.getLprId());
		lineaPrestamoForm.setLineaPrestamo(lineaPrestamo);
		List<CtaLprLineaPrestamo> lineas = lineaPrestamoDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lineas);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
        	//---- exporta la tabla
          //  export(tableFacade,cuentaAsociadoForm.getTipoCuentaMadre());
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		lineaPrestamoForm.setMdf(true);
		request.setAttribute("form", lineaPrestamoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/lineaPrestamo");
		return mapping.findForward("verLista");
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LineaPrestamoForm lineaPrestamoForm =new LineaPrestamoForm();
		lineaPrestamoForm.setLprId(null);
		lineaPrestamoForm.setMdf(false);
		return lista(mapping, lineaPrestamoForm , request, response);
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		saveMessages(request, errors);
		CtaLprLineaPrestamoDAO lineaPrestamoDAO = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		LineaPrestamoForm lineaPrestamoForm = (LineaPrestamoForm) form;
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		CtrParParametros parametros = parametrosDAO.findById("PORCENTAJE_GENERAL_COMISION");
		lineaPrestamoForm.setLprComision(parametros.getParValorNumber());
		List<CtaLprLineaPrestamo> lineas = lineaPrestamoDAO.findAll();
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lineas);
		
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
        	//---- exporta la tabla
          //  export(tableFacade,cuentaAsociadoForm.getTipoCuentaMadre());
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		request.setAttribute("form", lineaPrestamoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/lineaPrestamo");
		return mapping.findForward("verLista");
	}

	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.lineaPrestamo.buscar","buscar");
		map.put("cmd.lineaPrestamo.guardar","guardar");
		map.put("cmd.lineaPrestamo.modificar","modificar");
		map.put("cmd.lineaPrestamo.eliminar","eliminar");
		map.put("cmd.lineaPrestamo.cargar", "ver");
		map.put("cmd.lineaPrestamo.lista", "lista");
		map.put("cmd.lineaPrestamo.cancelar", "cancelar");
		map.put("cmd.lineaPrestamo.redirectInvalidData", "redirectInvalidData");
		return map;
	}
}