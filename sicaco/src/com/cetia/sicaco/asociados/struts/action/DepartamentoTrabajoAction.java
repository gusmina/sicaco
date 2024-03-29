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

import com.cetia.sicaco.asociados.struts.form.DepartamentoTrabajoForm;
import com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajo;
import com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajoDAO;
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
 * Creation date: 07-25-2008
 * 
 * XDoclet definition:
 * @struts.action path="/departamentoTrabajo" name="departamentoTrabajoForm" input="/form/departamentoTrabajo.jsp" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.asociados.departamentoTrabajo"
 */
public class DepartamentoTrabajoAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "ctaDptDepartamentoTrabajo";
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
		DepartamentoTrabajoForm departamentoTrabajoForm = (DepartamentoTrabajoForm)form;
		CtaDptDepartamentoTrabajoDAO departamentoTrabajoDAO = new CtaDptDepartamentoTrabajoDAO(getSessionHibernate(request));
		
		List lst = departamentoTrabajoDAO.findAllByEtrId(departamentoTrabajoForm.getEtrId());

		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		CtaEtrEmpresaTrabajo empresaTrabajo = empresaTrabajoDAO.findById(departamentoTrabajoForm.getEtrId());
		request.setAttribute("ctaEmpresa", empresaTrabajo);
		//paginacion roberto
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID ,request );
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = departamentoTrabajoDAO.getTotalRowCountByEtrId(departamentoTrabajoForm.getEtrId());
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		lst = departamentoTrabajoDAO.findAllByEtrId(departamentoTrabajoForm.getEtrId(), rowStart, rowEnd);
		tableFacade.setItems(lst);
		/*TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
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
		request.setAttribute("form", departamentoTrabajoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/departamentoTrabajo");
        request.setAttribute("filtro", "1");
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("dptNombre", "dptCentroCosto",
			"dptUbicacion", "dptEstado", "dptId");

		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.dpt.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("dptNombre");
		nombreColumna.setTitleKey("tbl.dpt.dptNombre");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaDptDepartamentoTrabajo departamentoTrabajo = (CtaDptDepartamentoTrabajo)item;
				
				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/asociados/departamentoTrabajo.do?accion=cargarDatos&dptId=" + departamentoTrabajo.getDptId() + "&etrId="+ departamentoTrabajo.getCtaEtrEmpresaTrabajo().getEtrId();
				html.a().href(link).close();
				html.append(value);
				html.aEnd();				
				return html.toString();		
			}
		});
		
		nombreColumna = row.getColumn("dptCentroCosto");
		nombreColumna.setTitleKey("tbl.dpt.dptCentroCosto");
		
		nombreColumna = row.getColumn("dptUbicacion");
		nombreColumna.setTitleKey("tbl.dpt.dptUbicacion");
		
		nombreColumna = row.getColumn("dptEstado");
		nombreColumna.setTitleKey("tbl.dpt.dptEstado");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaDptDepartamentoTrabajo departamentoTrabajo = (CtaDptDepartamentoTrabajo)item;

				HtmlBuilder html = new HtmlBuilder();
				if(departamentoTrabajo.getDptEstado().equals("A")){
					value = "Activo";
				}else{
					value = "Inactivo";
				}
								
				return value;
			}
		});
		
		nombreColumna = row.getColumn("dptId");
		nombreColumna.setTitleKey("tbl.dpt.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaDptDepartamentoTrabajo departamentoTrabajo = (CtaDptDepartamentoTrabajo)item;
				String clase = "class=\"linkInActivar\"";
				HtmlBuilder html = new HtmlBuilder();
				if(departamentoTrabajo.getDptEstado().equals("A")){
					value = "Inactivar";
				}else{
					value = "Activar";
					clase = "class=\"linkActivar\"";
				}
				//String link = tableFacade.getWebContext().getContextPath();
				html.a().onclick("handlerDeleteButton("+ departamentoTrabajo.getDptId()+ ");").append(clase).title(value.toString()).id("deleteButtonId").close();
			//	html.append(value);
				html.aEnd();
								
				return html.toString();
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
		DepartamentoTrabajoForm departamentoTrabajoForm = (DepartamentoTrabajoForm)form;
		CtaDptDepartamentoTrabajoDAO departamentoTrabajoDAO = new CtaDptDepartamentoTrabajoDAO(getSessionHibernate(request));
		
		if(vacios(departamentoTrabajoForm, request, response)){
			return lista(mapping, form, request, response);
		}
		Transaction tx = departamentoTrabajoDAO.getSession().beginTransaction();
		
		departamentoTrabajoForm.getDepartamentoTrabajoH().setDptEstado("A");
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		departamentoTrabajoForm.setCtaEtrEmpresaTrabajo(empresaTrabajoDAO.findById(departamentoTrabajoForm.getEtrId()));
		departamentoTrabajoDAO.save(departamentoTrabajoForm.getDepartamentoTrabajoH());
		tx.commit();

		DepartamentoTrabajoForm form2 = new DepartamentoTrabajoForm();
		form2.setEtrId(departamentoTrabajoForm.getEtrId());
		return lista(mapping,form2,request,response);
	}

	private boolean vacios(DepartamentoTrabajoForm departamentoTrabajoForm,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		Boolean valid = false;
		if(departamentoTrabajoForm.getDptNombre().equals("") || departamentoTrabajoForm.getDptNombre() == null){
			mensajes2("errors.dpt.dptNombre.vacio", departamentoTrabajoForm, request, response, errors);
			valid = true;
		}
		if(departamentoTrabajoForm.getDptCentroCosto().equals("") || departamentoTrabajoForm.getDptCentroCosto() == null){
			mensajes2("errors.dpt.dptCentroCosto.vacio", departamentoTrabajoForm, request, response, errors);
			valid = true;
		}
		if(departamentoTrabajoForm.getDptUbicacion().equals("") || departamentoTrabajoForm.getDptUbicacion() == null){
			mensajes2("errors.dpt.dptUbicacion.vacio", departamentoTrabajoForm, request, response, errors);
			valid = true;
		}
		return valid;
	}
	
	public void mensajes2(String msg,DepartamentoTrabajoForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors){
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
	}

	public ActionForward inactivar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DepartamentoTrabajoForm departamentoTrabajoForm = (DepartamentoTrabajoForm)form;
		CtaDptDepartamentoTrabajoDAO departamentoTrabajoDAO = new CtaDptDepartamentoTrabajoDAO(getSessionHibernate(request));
		
		Transaction tx = departamentoTrabajoDAO.getSession().beginTransaction();
		
		departamentoTrabajoForm.setDepartamentoTrabajoH(departamentoTrabajoDAO.findById(departamentoTrabajoForm.getDptId2()));
		if(departamentoTrabajoForm.getDptEstado().equals("A")){
			departamentoTrabajoForm.getDepartamentoTrabajoH().setDptEstado("I");
		}else{
			departamentoTrabajoForm.getDepartamentoTrabajoH().setDptEstado("A");
		}
		departamentoTrabajoDAO.merge(departamentoTrabajoForm.getDepartamentoTrabajoH());
		tx.commit();

		DepartamentoTrabajoForm form2 = new DepartamentoTrabajoForm();
		form2.setEtrId(departamentoTrabajoForm.getEtrId());
		return lista(mapping,form2,request,response);
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		DepartamentoTrabajoForm departamentoTrabajoForm = (DepartamentoTrabajoForm)form;
		CtaDptDepartamentoTrabajoDAO departamentoTrabajoDAO = new CtaDptDepartamentoTrabajoDAO(getSessionHibernate(request));
		
		List lst = departamentoTrabajoDAO.findAll();

		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List letr = empresaTrabajoDAO.findAll();
		request.setAttribute("letr", letr);
		
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
		request.setAttribute("form", departamentoTrabajoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/departamentoTrabajo");
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
	
	public ActionForward regresar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("redirectEmpresa");
	}
	
	public ActionForward cargarDatos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DepartamentoTrabajoForm departamentoTrabajoForm = (DepartamentoTrabajoForm)form;
		CtaDptDepartamentoTrabajoDAO departamentoTrabajoDAO = new CtaDptDepartamentoTrabajoDAO(getSessionHibernate(request));
		
		List lst = departamentoTrabajoDAO.findAllByEtrId(departamentoTrabajoForm.getEtrId());

		departamentoTrabajoForm.setDepartamentoTrabajoH(departamentoTrabajoDAO.findById(departamentoTrabajoForm.getDptId()));
		
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		List letr = empresaTrabajoDAO.findAll();
		request.setAttribute("letr", letr);
		
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
		request.setAttribute("form", departamentoTrabajoForm);
		request.setAttribute(Constantes.ACCION_KEY, "/departamentoTrabajo");
        request.setAttribute("filtro", "2");
		return mapping.findForward("lista");
		 
	}
	
	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DepartamentoTrabajoForm departamentoTrabajoForm = (DepartamentoTrabajoForm)form;
		CtaDptDepartamentoTrabajoDAO departamentoTrabajoDAO = new CtaDptDepartamentoTrabajoDAO(getSessionHibernate(request));
		
		if(vacios(departamentoTrabajoForm, request, response)){
			return lista(mapping, form, request, response);
		}
		Transaction tx = departamentoTrabajoDAO.getSession().beginTransaction();
		
		//departamentoTrabajoForm.getDepartamentoTrabajoH().setDptEstado("A");
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSessionHibernate(request));
		departamentoTrabajoForm.setCtaEtrEmpresaTrabajo(empresaTrabajoDAO.findById(departamentoTrabajoForm.getEtrId()));
		departamentoTrabajoDAO.merge(departamentoTrabajoForm.getDepartamentoTrabajoH());
		tx.commit();

		DepartamentoTrabajoForm form2 = new DepartamentoTrabajoForm();
		form2.setEtrId(departamentoTrabajoForm.getEtrId());
		return lista(mapping,form2,request,response);
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DepartamentoTrabajoForm departamentoTrabajoForm = (DepartamentoTrabajoForm)form;
		DepartamentoTrabajoForm form2 = new DepartamentoTrabajoForm();
		form2.setEtrId(departamentoTrabajoForm.getEtrId());
		return lista(mapping,form2,request,response);
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.dpt.lista", "lista");
		map.put("cmd.dpt.guardar", "guardar");
		map.put("cmd.dpt.inactivar", "inactivar");
		map.put("cmd.dpt.redirectInvalidData", "redirectInvalidData");
		map.put("cmd.dpt.regresar", "regresar");
		map.put("cmd.dpt.salvar", "salvar");
		map.put("cmd.dpt.cancelar", "cancelar");
		map.put("cmd.dpt.cargarDatos", "cargarDatos");
		return map;
	}
}