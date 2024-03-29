/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.asociados.struts.action;

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

import com.cetia.sicaco.asociados.struts.form.EmpresaTrabajoForm;
import com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajo;
import com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajoDAO;
import com.cetia.sicaco.hibernate.InvCnvConversionDAO;
import com.cetia.sicaco.hibernate.InvMedMedida;
import com.cetia.sicaco.hibernate.InvMedMedidaDAO;
import com.cetia.sicaco.inventario.struts.form.ConversionForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 07-23-2008
 * 
 * XDoclet definition:
 * @struts.action path="/empresaTrabajo" name="empresaTrabajoForm" input="redirectInvalidData" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.asociados.empresaTrabajo"
 */
public class EmpresaTrabajoAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "ctaEtrEmpresaTrabajo";
	public static final String CONV_MED_ID = "convMedId";
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
		EmpresaTrabajoForm empresaTrabajoForm = (EmpresaTrabajoForm)form;
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List lst = empresaTrabajoDAO.findAll();
		//paginacion roberto
		
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID ,request );
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = empresaTrabajoDAO.getTotalRowCount();
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		lst = empresaTrabajoDAO.findAll(rowStart, rowEnd);
		tableFacade.setItems(lst);
      /*
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
	*/
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
		request.setAttribute("form", empresaTrabajoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/empresaTrabajo");
        request.setAttribute("filtro", "1");
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("etrNombre", "etrDescripcion", "etrEstado", "etrId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.etr.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("etrNombre");
		nombreColumna.setTitleKey("tbl.etr.etrNombre");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaEtrEmpresaTrabajo empresaTrabajo = (CtaEtrEmpresaTrabajo)item;
				
				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/asociados/empresaTrabajo.do?accion=cargarDatos&etrId=" + empresaTrabajo.getEtrId();
				html.a().href(link).close();
				html.append(value);
				html.aEnd();				
				return html.toString();		
			}
		});
		
		nombreColumna = row.getColumn("etrDescripcion");
		nombreColumna.setTitleKey("tbl.etr.etrDescripcion");
		
		nombreColumna = row.getColumn("etrEstado");
		nombreColumna.setTitleKey("tbl.etr.etrEstado");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaEtrEmpresaTrabajo empresaTrabajo = (CtaEtrEmpresaTrabajo)item;

				HtmlBuilder html = new HtmlBuilder();
				if(empresaTrabajo.getEtrEstado().equals("A")){
					value = "Activo";
				}else{
					value = "Inactivo";
				}
								
				return value;
			}
		});
		
		nombreColumna = row.getColumn("etrId");
		nombreColumna.setTitleKey("tbl.etr.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaEtrEmpresaTrabajo empresaTrabajo = (CtaEtrEmpresaTrabajo)item;
				String clase = "class=\"linkInActivar\"";
				HtmlBuilder html = new HtmlBuilder();
				if(empresaTrabajo.getEtrEstado().equals("A")){
					value = "<u>inactivar</u>";
				}else{
					value = "<u>activar</u>";
					clase = "class=\"linkActivar\"";
				}
				html.a().onclick("handlerDeleteButton("+ empresaTrabajo.getEtrId()+ ");").append(clase).title(value.toString()).id("deleteButtonId").close();
				html.aEnd();
				
				HtmlBuilder html2 = new HtmlBuilder();
				String link2 = tableFacade.getWebContext().getContextPath();
				link2 += "/asociados/departamentoTrabajo.do?accion=lista&etrId=" + empresaTrabajo.getEtrId();
				html2.a().href(link2).close();
				html2.append("departamentos");
				html2.aEnd();
				
				return html.toString() + " | " + html2.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("cnvNuevaMedida","cnvFactor","cnvNombreMedida",
					"cnvComentario");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.conversion.caption");
			
			Row row = table.getRow();
			Column nombreColumna = row.getColumn("cnvNuevaMedida");
			nombreColumna.setTitleKey("tbl.conversion.cnvNuevaMedida");
			
			nombreColumna = row.getColumn("cnvFactor");
			nombreColumna.setTitleKey("tbl.conversion.cnvFactor");
			
			nombreColumna = row.getColumn("cnvNombreMedida");
			nombreColumna.setTitleKey("tbl.conversion.cnvNombreMedida");
			
			nombreColumna = row.getColumn("cnvComentario");
			nombreColumna.setTitleKey("tbl.conversion.cnvComentario");
		
		tableFacade.render();
	}
	 
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EmpresaTrabajoForm empresaTrabajoForm = (EmpresaTrabajoForm)form;
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		
		Transaction tx = empresaTrabajoDAO.getSession().beginTransaction();
		
		empresaTrabajoForm.getEmpresaTrabajoH().setEtrEstado("A");
		empresaTrabajoDAO.save(empresaTrabajoForm.getEmpresaTrabajoH());
		tx.commit();
		
		empresaTrabajoDAO.getSession().flush();
		empresaTrabajoDAO.getSession().clear();
		
		
		EmpresaTrabajoForm empresaTrabajoForm2 = new EmpresaTrabajoForm();
		return lista(mapping,empresaTrabajoForm2,request,response);
	}

	public ActionForward inactivar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EmpresaTrabajoForm empresaTrabajoForm = (EmpresaTrabajoForm)form;
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		
		Transaction tx = empresaTrabajoDAO.getSession().beginTransaction();
		
		empresaTrabajoForm.setEmpresaTrabajoH(empresaTrabajoDAO.findById(empresaTrabajoForm.getEtrId2()));
		if(empresaTrabajoForm.getEtrEstado().equals("A")){
			empresaTrabajoForm.getEmpresaTrabajoH().setEtrEstado("I");
		}else{
			empresaTrabajoForm.getEmpresaTrabajoH().setEtrEstado("A");
		}
		empresaTrabajoDAO.merge(empresaTrabajoForm.getEmpresaTrabajoH());
		tx.commit();

		EmpresaTrabajoForm empresaTrabajoForm2 = new EmpresaTrabajoForm();
		return lista(mapping,empresaTrabajoForm2,request,response);
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		EmpresaTrabajoForm empresaTrabajoForm = (EmpresaTrabajoForm)form;
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List lst = empresaTrabajoDAO.findAll();
/*		
		if(lst.size() == 0){
			lst = new ArrayList();
		}
	*/	
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
		request.setAttribute("form", empresaTrabajoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/empresaTrabajo");
        request.setAttribute("filtro", "1");
		saveMessages(request, errors);
		return mapping.findForward("lista");
	}
	
	public void mensajes(String msg,ConversionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = new ActionErrors();
		ConversionForm conversionForm = (ConversionForm)form;
		conversionForm.setMedId((String) request.getSession().getAttribute(CONV_MED_ID));
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		conversionForm.getConversionH().setCnvComentario("");
		InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
		
		InvMedMedida medida = medidaDAO.findById(conversionForm.getMedId());
		request.setAttribute("medida", medida);
		
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(medida.getInvCnvConversions());
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
		request.setAttribute(Constantes.ACCION_KEY, "/conversion");
		request.setAttribute("form", conversionForm);
        request.setAttribute("filtro", "1");
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
		//return mapping.findForward("lista");
	}
	
	public ActionForward cargarDatos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EmpresaTrabajoForm empresaTrabajoForm = (EmpresaTrabajoForm)form;
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List lst = empresaTrabajoDAO.findAll();

		empresaTrabajoForm.setEmpresaTrabajoH(empresaTrabajoDAO.findById(empresaTrabajoForm.getEtrId()));
		
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
		request.setAttribute("form", empresaTrabajoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/empresaTrabajo");
        request.setAttribute("filtro", "2");
		return mapping.findForward("lista");
		 
	}
	
	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EmpresaTrabajoForm empresaTrabajoForm = (EmpresaTrabajoForm)form;
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		
		Transaction tx = empresaTrabajoDAO.getSession().beginTransaction();
		
		//empresaTrabajoForm.getEmpresaTrabajoH().setEtrEstado("A");
		empresaTrabajoDAO.merge(empresaTrabajoForm.getEmpresaTrabajoH());
		tx.commit();

		EmpresaTrabajoForm empresaTrabajoForm2 = new EmpresaTrabajoForm();
		return lista(mapping,empresaTrabajoForm2,request,response);
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//EmpresaTrabajoForm empresaTrabajoForm = (EmpresaTrabajoForm)form;
		EmpresaTrabajoForm empresaTrabajoForm2 = new EmpresaTrabajoForm();
		return lista(mapping,empresaTrabajoForm2,request,response);
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.etr.lista", "lista");
		map.put("cmd.etr.guardar", "guardar");
		map.put("cmd.etr.inactivar", "inactivar");
		map.put("cmd.etr.redirectInvalidData", "redirectInvalidData");
		map.put("cmd.etr.cargarDatos", "cargarDatos");
		map.put("cmd.etr.salvar", "salvar");
		map.put("cmd.etr.cancelar", "cancelar");
		
		return map;
	}
}