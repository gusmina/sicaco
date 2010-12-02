/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.seguridad.struts.action;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.directory.InvalidAttributesException;
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

import com.cetia.sicaco.hibernate.SecCelCorreoElectronico;
import com.cetia.sicaco.hibernate.SecCelCorreoElectronicoDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecPerPersonaDAO;
import com.cetia.sicaco.seguridad.struts.form.CorreoForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 02-12-2008
 * 
 * XDoclet definition:
 * @struts.action path="/correo" name="correoForm" input="pagina-lista.seguridad.correo" parameter="accion" scope="request" validate="true"
 */
public class CorreoAction extends DMLAction {
	/*
	 * Generated Methods
	 */
	public static final String TABLA_ID = "secCelCorreoElectronico";
	private final static String PER_ID = "perId";
	private final static String ERRORS = "errors";
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	//---- Redirige y crea la tabla de datos a mostrar ya sea en el browser o genera un export
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorreoForm correoForm = (CorreoForm) form;
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona secPerPersona = secPerPersonaDAO.findById(correoForm.getPerId());
		request.getSession().setAttribute(PER_ID,correoForm.getPerId());
		//Aqui empieza el codigo para generar Tabla
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(secPerPersona.getSecCelCorreoElectronicos());
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		
		Limit limit = tableFacade.getLimit();
        if (limit.isExported()) {
        	//---- exporta la tabla
            export(tableFacade,request);
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
        request.setAttribute(Constantes.PERSONA,secPerPersona);
        request.setAttribute(Constantes.ACCION_KEY, "/correo");
        if(request.getSession().getAttribute("asoc") == null){
        	 request.getSession().setAttribute("asoc", correoForm.isAsoc());
        }
        request.setAttribute("filtro", "1");
        return mapping.findForward("lista");
		 
	}
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorreoForm correoForm = (CorreoForm) form;
		String perId = correoForm.getPerId(); 
		ActionErrors	errors =	(ActionErrors)request.getSession().getAttribute(ERRORS);
		request.getSession().removeAttribute(ERRORS);
		//String perId =  (String)request.getSession().getAttribute(PER_ID);
		correoForm.setPerId(perId);
		//request.getSession().removeAttribute(PER_ID);
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona secPerPersona = secPerPersonaDAO.findById(perId);
		//Aqui empieza el codigo para generar Tabla
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(secPerPersona.getSecCelCorreoElectronicos());
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
        if (limit.isExported()) {
        	//---- exporta la tabla
            export(tableFacade,request);
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
        saveMessages(request, errors);
        request.setAttribute(Constantes.PERSONA,secPerPersona);
        request.setAttribute("form",correoForm);
        request.setAttribute(Constantes.ACCION_KEY, "/correo");
        request.setAttribute("filtro", "1");
        request.getSession().setAttribute("asoc", correoForm.isAsoc());
		return mapping.findForward("lista");
	}
	
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("id.celCorreoElectronico","celPrincipal","eliminar");
		Table table = tableFacade.getTable();
				
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.correo.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("id.celCorreoElectronico");
		nombreColumna.setTitleKey("tbl.correo.id.celCorreoElectronico");
		
		nombreColumna = row.getColumn("eliminar");
		nombreColumna.setTitleKey("tbl.correo.eliminar");
		
		//----- Implementamos la edicion de usuario
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				SecCelCorreoElectronico correoElectronico = (SecCelCorreoElectronico)item;
				HtmlBuilder html = new HtmlBuilder();
				String eliminar = "Eliminar";
				html.a().onclick("handlerDeleteButton('"+ correoElectronico.getId().getCelCorreoElectronico()+ "','"+correoElectronico.getId().getSecPerPersona().getPerId()+"');").append("class=\"linkEliminar\"").title(eliminar).id("deleteButtonId").close();
				html.aEnd();
				return html.toString();
			}
		});
		
		Column column = row.getColumn("celPrincipal");
		column.setTitleKey("tbl.correo.celPrincipal");
		
		column.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				String value = (String) new BasicCellEditor().getValue(item, property, rowcount);
				value = (value.equalsIgnoreCase("S"))?getResources(request).getMessage("lbl.correo.celPrincipalS"):getResources(request).getMessage("lbl.correo.celPrincipalN");
				return value;
			}
			
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade, final HttpServletRequest request) {
		 tableFacade.setColumnProperties("id.celCorreoElectronico","celPrincipal");
			Table table = tableFacade.getTable();
					
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.correo.caption.export");
			
			Row row = table.getRow();
			
			Column nombreColumna = row.getColumn("id.celCorreoElectronico");
			nombreColumna.setTitleKey("tbl.correo.id");
			
			Column column = row.getColumn("celPrincipal");
			column.setTitleKey("tbl.correo.celPrincipal");
			
			column.getCellRenderer().setCellEditor(new CellEditor(){

				public Object getValue(Object item, String property, int rowcount) {
					String value = (String) new BasicCellEditor().getValue(item, property, rowcount);
					value = (value.equalsIgnoreCase("S"))?getResources(request).getMessage("lbl.correo.celPrincipalS.export"):getResources(request).getMessage("lbl.correo.celPrincipalN");
					return value;
				}
				
			});
			
			tableFacade.render();
	 }
	
	//---- Accion de cancelar, que regresa a la pantalla basica de la lista 
 	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return lista(mapping, form, request, response);
	}
	
 	//---- Accion que inicia la pantalla para agregar persona
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		//----- Variables de configuracion
		request.setAttribute(Constantes.ACCION_KEY, "/correoAccion");
		request.setAttribute("filtro", "0");
		request.setAttribute("boton", "0");
		return mapping.findForward("dml");
	}
	
	//---- Accion asociada al link de edicion de la tabla, entra a pantalla de edicion
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorreoForm correoForm = (CorreoForm) form;
		SecCelCorreoElectronicoDAO correoElectronicoDAO = new SecCelCorreoElectronicoDAO(getSessionHibernate(request));
		
		//---- Set de los datos de la persona seleccionada para la edicion
		correoForm.setCorreoElectronicoH(correoElectronicoDAO.findById(correoForm.getId()));
		request.setAttribute("anterior",correoForm.getCorreoElectronicoH());
		//---- Variables de configuracion
		request.setAttribute(Constantes.ACCION_KEY, "/correoAccion");
		request.setAttribute("filtro", "0");
		request.setAttribute("boton", "1");
		return mapping.findForward("dml");
	}
	
	//---- Accion de guardar nueva persona, regresa a lista principal
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorreoForm correoForm = (CorreoForm) form;
		List<SecCelCorreoElectronico> lstCorreo = null;
		Set<SecCelCorreoElectronico>  setCorreo = null;
		SecPerPersona  modelPersona = null;
		SecCelCorreoElectronicoDAO correoElectronicoDAO = new SecCelCorreoElectronicoDAO(getSessionHibernate(request));
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		
		//---- Indica inicio de las transacciones
		Transaction trx = correoElectronicoDAO.getSession().beginTransaction();
		try {
			
			lstCorreo = correoElectronicoDAO.findByCorreo(correoForm.getEmail());
			/*if(lstCorreo.size()>0){
				ActionErrors errors = new ActionErrors();
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.email.repetido"));
				saveMessages(request, errors);
			}*/
			modelPersona = secPerPersonaDAO.findById(correoForm.getPerId());
			setCorreo = modelPersona.getSecCelCorreoElectronicos();
			validateCorreoLogic(correoForm,lstCorreo,setCorreo);
			correoForm.getId().setCelCorreoElectronico(correoForm.getEmail());
			correoElectronicoDAO.save(correoForm.getCorreoElectronicoH());
			trx.commit();
			
		}catch(InvalidAttributesException invalidex){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.email.repetido"));
			saveMessages(request, errors);
		}
		catch(InputMismatchException inpex){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.email.principal"));
			saveMessages(request, errors);
		}
		catch (Exception e) {
			// TODO: handle exception
			trx.rollback();
			e.printStackTrace();
		}
		finally{
			correoElectronicoDAO.getSession().flush();
			correoElectronicoDAO.getSession().clear();
			
		}
		return lista(mapping, form, request, response);
	}
	
	private void validateCorreoLogic(CorreoForm correoForm,List<SecCelCorreoElectronico>
		lstCorreo,Set<SecCelCorreoElectronico>  setCorreo) throws Exception{
		if(lstCorreo.size() > 0) throw new InvalidAttributesException();
		for(SecCelCorreoElectronico model :setCorreo){
			if(model.getCelPrincipal().equals("S") && correoForm.getCelPrincipal().equals("S")){
				 	throw new InputMismatchException();
			}
		}
	}
	
	
	
	//---- Accion de guardar los cambios hecho de una edicion
	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorreoForm correoForm = (CorreoForm) form;
		SecCelCorreoElectronicoDAO correoElectronicoDAO = new SecCelCorreoElectronicoDAO(getSessionHibernate(request));
		CorreoForm id = (CorreoForm) request.getAttribute("anterior");
		//---- Inicia transaccion
		Transaction trx = correoElectronicoDAO.getSession().beginTransaction();
		correoElectronicoDAO.delete(correoElectronicoDAO.findById(id.getCorreoElectronicoH().getId()));
		//correoForm.getId().setCelCorreoElectronico(correoForm.getId().getCelCorreoElectronico().toUpperCase());
		
		//---- Merge indica un update en la tabla
		correoElectronicoDAO.save(correoForm.getCorreoElectronicoH());
		trx.commit();
		correoElectronicoDAO.getSession().flush();
		correoElectronicoDAO.getSession().clear();
		
		return lista(mapping, form, request, response);
	}
	
	//---- Elimina los datos seleccionados de la tabla
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorreoForm correoForm = (CorreoForm) form;
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona modelPersona = null;
		SecCelCorreoElectronico modelCorreo = null;
		SecCelCorreoElectronicoDAO correoElectronicoDAO = new SecCelCorreoElectronicoDAO(getSessionHibernate(request));
		
		//---- Inicia transaccion
		Transaction trx = correoElectronicoDAO.getSession().beginTransaction();
		
		//---- Delete indica un delete de la tabla
		modelPersona = secPerPersonaDAO.findById(correoForm.getPerId());
		correoForm.getId().setSecPerPersona(modelPersona);
		correoForm.getId().setCelCorreoElectronico(correoForm.getEmail());
		modelCorreo = correoElectronicoDAO.findById(correoForm.getCorreoElectronicoH().getId());
		modelPersona.getSecCelCorreoElectronicos().remove(modelCorreo);
		correoElectronicoDAO.delete(modelCorreo);//correoElectronicoDAO.findById(correoForm.getCorreoElectronicoH().getId()));
		trx.commit();
		correoElectronicoDAO.getSession().flush();
		correoElectronicoDAO.getSession().clear();
		
		return lista(mapping, form, request, response);
	}
	
	public ActionForward redirectLista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("redirectLista");
	}
	
	public ActionForward redirectListaAsociados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			request.getSession().removeAttribute("asoc");
		return mapping.findForward("redirectListaAsociados");
	}
	
	
	//---- Mapas usados para conectar las acciones
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.correo.agregar", "guardar");
		map.put("cmd.correo.mostrar","agregar");
		map.put("cmd.correo.editar","editar");
		map.put("cmd.correo.modificar","salvar");
		map.put("cmd.correo.eliminar","eliminar");
		map.put("cmd.correo.cancelar","cancelar");
		map.put("cmd.correo.lista", "lista");
		map.put("cmd.correo.axn2","editar");
		map.put("cmd.correo.dml", "dml");
		map.put("cmd.correo.redirectInvalidData","redirectInvalidData");
		map.put("cmd.correo.redirectLista","redirectLista");
		map.put("cmd.correo.redirectListaAsc","redirectListaAsociados");
		return map;
	}
}