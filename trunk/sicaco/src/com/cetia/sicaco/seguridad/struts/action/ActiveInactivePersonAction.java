package com.cetia.sicaco.seguridad.struts.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecPerPersonaDAO;
import com.cetia.sicaco.seguridad.struts.form.ActiveInactivePersonForm;
import com.cetia.sicaco.seguridad.struts.form.PersonaForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.Format;
import com.mad.utilidades.MysqlProcedureException;

public class ActiveInactivePersonAction extends DMLAction {

	private static final String TABLA_ID = "secActiveInactivePersona";
	
	
	public ActionForward  lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return findByExample(mapping, form, request, response);
		/*SecPerPersonaDAO personaDAO = new SecPerPersonaDAO();
		PersonaForm personaForm = (PersonaForm)form;
		List<SecPerPersona> list = null;
		//Transaction tx = personaDAO.getSession().beginTransaction();
		
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID ,request );
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = personaDAO.getTotalRowCount();
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		list = personaDAO.findAll(rowStart,rowEnd);
		tableFacade.setItems(list);
		//Aqui empieza el codigo para generar Tabla
   /*  TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(loadLista(personaForm));
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		
     */
      /*  if (limit.isExported()) {
        	//---- exporta la tabla
            export(tableFacade);
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
        request.setAttribute(Constantes.ACCION_KEY, "/activeInactivePersonAction");
		return mapping.findForward("lista");*/
	}
	
	@SuppressWarnings("unchecked")
	private List<SecPerPersona> loadLista(PersonaForm personaForm, HttpServletRequest request){
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		return (List<SecPerPersona>)personaDAO.findAll(personaForm.getUsuarioConectado().getMax());
	}
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("perId","perDui","perPrimerNombre","perEstado"
		);
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.aipersona.caption");
		
		Row row = table.getRow();
		Column nombreColumna =  row.getColumn("perId");
		nombreColumna.setTitle("-");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecPerPersona persona = (SecPerPersona)item;
				String jsonParam = "{page : '0',accion : 'findByKey',perId : "+"'"+persona.getPerId()+"'"+"}";
				String url = "'"+tableFacade.getWebContext().getContextPath();tableFacade.getWebContext().getContextPath();
				url+="/seguridad/activeInactivePersonAction.do'";
				String javaScriptFunction = "ajaxCallNormalInvalidateFields("+url+","+jsonParam+","+"'idPerForm'"+")";
				HtmlBuilder html = new HtmlBuilder();
				html.input().type("radio").name("idRadio").onclick(javaScriptFunction).close();
				return html.toString();
			}
		});

		
		nombreColumna = row.getColumn("perPrimerNombre");
		nombreColumna.setTitleKey("tbl.persona.perPrimerNombre");
		
		//----- Implementamos la edicion de usuario
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecPerPersona persona = (SecPerPersona)item;
				value = persona.getPerPrimerApellido();
				value = value + (isObjectNull(persona.getPerSegundoApellido())?"":(" "+persona.getPerSegundoApellido()));
				value = value + (isObjectNull(persona.getPerApellidoCasada())?"":(" de "+persona.getPerApellidoCasada()));
				value = value + ", " + persona.getPerPrimerNombre();
				return value;
			}
		});


		nombreColumna = row.getColumn("perDui");
		nombreColumna.setTitleKey("tbl.persona.perDui");
		
		nombreColumna = row.getColumn("perEstado");
		nombreColumna.setTitleKey("tbl.aipersona.estado");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecPerPersona persona = (SecPerPersona)item;
				
				HtmlBuilder html = new HtmlBuilder();
				if(persona.getPerEstado()!= null && !persona.getPerEstado().trim().equals("") && persona.getPerEstado().equals("A")){
					value = "Activo";
				}
				if(persona.getPerEstado()!= null && !persona.getPerEstado().trim().equals("") && persona.getPerEstado().equals("I")){
					value = "Inactivo";
				}
				return value;
			}
		});
		return tableFacade.render();
	}
	private void export(final TableFacade tableFacade) {
		tableFacade.setColumnProperties("perDui","perPrimerNombre","perEstado"
		);
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.aipersona.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("perPrimerNombre");
		nombreColumna.setTitleKey("tbl.persona.perPrimerNombre");
		
		//----- Implementamos la edicion de usuario
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecPerPersona persona = (SecPerPersona)item;
				
				
//				value = value + (isObjectNull(persona.getPerSegundoNombre())?"":(" "+persona.getPerSegundoNombre()));
//				value = value + (isObjectNull(persona.getPerTercerNombre())?"":(" "+persona.getPerTercerNombre()));
				value = persona.getPerPrimerApellido();
				value = value + (isObjectNull(persona.getPerSegundoApellido())?"":(" "+persona.getPerSegundoApellido()));
				value = value + (isObjectNull(persona.getPerApellidoCasada())?"":(" de "+persona.getPerApellidoCasada()));
				value = value + ", " + persona.getPerPrimerNombre();
				return value;
			}
		});


		nombreColumna = row.getColumn("perDui");
		nombreColumna.setTitleKey("tbl.persona.perDui");
		
		nombreColumna = row.getColumn("perEstado");
		nombreColumna.setTitleKey("tbl.aipersona.estado");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecPerPersona persona = (SecPerPersona)item;
				
				HtmlBuilder html = new HtmlBuilder();
				if(persona.getPerEstado()!= null && !persona.getPerEstado().trim().equals("") && persona.getPerEstado().equals("A")){
					value = "Activo";
				}
				if(persona.getPerEstado()!= null && !persona.getPerEstado().trim().equals("") && persona.getPerEstado().equals("I")){
					value = "Inactivo";
				}
				return value;
			}
		});
		tableFacade.render();
	}
	
	public ActionForward  activePerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActiveInactivePersonForm aform = (ActiveInactivePersonForm)form;
		String perId = aform.getPerId();
		String	user = aform.getUsuarioConectado().getNombreUsuario();
		SecPerPersona secPerPersona = null;
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		Transaction tx = secPerPersonaDAO.getSession().beginTransaction();
		try {
			secPerPersona = secPerPersonaDAO.findById(aform.getPerId());
			secPerPersona.setPerEstado("A");
			secPerPersona.setAudFechaModificacion(new Date());
			secPerPersona.setAudUsuarioModificacion(user);
			
			
			Set<SecIseInicioSesion> listaSesion = secPerPersona.getSecIseInicioSesions();
			for (Iterator iterator = listaSesion.iterator(); iterator.hasNext();) {
				SecIseInicioSesion inicioSesion = (SecIseInicioSesion) iterator.next(); 
				inicioSesion.setIseFechaInactivacion(null);
			}
			tx.commit();
		}catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			secPerPersonaDAO.getSession().flush();
			secPerPersonaDAO.getSession().clear();
			
		}
		return lista(mapping, form, request, response);
	}
	
	public  ActionForward inactivePerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActiveInactivePersonForm aform = (ActiveInactivePersonForm)form;
		String perId = aform.getPerId();
		String	user = aform.getUsuarioConectado().getNombreUsuario();
		SecPerPersona secPerPersona = null;
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecIseInicioSesionDAO secIseInicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		Transaction tx = secIseInicioSesionDAO.getSession().beginTransaction();
		try {
			secPerPersona = secPerPersonaDAO.findById(perId);
			List sesionList = secIseInicioSesionDAO.findActiveSesionByPersonaList(secPerPersona);
			secPerPersona.setPerEstado("I");
			secPerPersona.setAudFechaModificacion(new Date());
			secPerPersona.setAudUsuarioModificacion(user);
			secPerPersonaDAO.merge(secPerPersona);
			tx.commit();
			secPerPersonaDAO.getSession().flush();
			secPerPersonaDAO.getSession().clear();
			/*se coloca != null porque pueda ser que exista una persona pero que no tenga sesion*/
			if(sesionList.size()>0){
				for (Iterator iterator = sesionList.iterator(); iterator
						.hasNext();) {
					SecIseInicioSesion inicioSesion = (SecIseInicioSesion) iterator.next();
					inicioSesion.setIseFechaInactivacion(new Date());
					secIseInicioSesionDAO.merge(inicioSesion);
					tx.commit();
					secIseInicioSesionDAO.getSession().flush();
					secIseInicioSesionDAO.getSession().clear();
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			 secIseInicioSesionDAO.getSession().flush();
			 secIseInicioSesionDAO.getSession().clear();
			
		}
		request.setAttribute(Constantes.ACCION_KEY, "/activeInactivePersonAction");
		return lista(mapping, form, request, response);
	}
	
	public  ActionForward deletePerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		/*se tiene que llamar un procedimiento almacenado el cual elimina
		 * a la persona junto con sus sesiones historial emergencias y todo lo demas relacionado
		 * a la persona*/
		ActionErrors errors = new ActionErrors();
		ActiveInactivePersonForm aform = (ActiveInactivePersonForm)form;
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		Transaction tx = secPerPersonaDAO.getSession().beginTransaction();
		try {
			secPerPersonaDAO.callSP_DELETE_PERSONA(aform.getPerId());
			tx.commit();
		}catch(MysqlProcedureException pex){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(pex.getMessage()));
			pex.printStackTrace();
			tx.rollback();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			 secPerPersonaDAO.getSession().flush();
			 secPerPersonaDAO.getSession().clear();
			 
		}
		saveMessages(request, errors);
		request.setAttribute(Constantes.ACCION_KEY, "/activeInactivePersonAction");
		return lista(mapping, form, request, response);
	}
	
	public  ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		PersonaForm personaForm = (PersonaForm)form;
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		//---- lista donde se guardaran los datos a mostrar
		java.util.List list = personaDAO.findAll(personaForm.getUsuarioConectado().getMax());
		//Aqui empieza el codigo para generar Tabla
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(list);
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
        request.setAttribute(Constantes.ACCION_KEY, "/activeInactivePersonAction");
		saveMessages(request, errors);
		return mapping.findForward("lista");
	}
	
	
	public ActionForward findByExample(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		PersonaForm personaForm = (PersonaForm)form;
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID ,request );
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = personaDAO.getTotalRowCountPersonaByCriteria(personaForm.getPersonaH());
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		List<SecPerPersona> list = personaDAO.findByCriteria(personaForm.getPersonaH(), rowStart, rowEnd);
		tableFacade.setItems(list);
		//---- lista donde se guardaran los datos a mostrar
		//SecPerPersonaDAO personaDAO = new SecPerPersonaDAO();
	//	java.util.List list = personaDAO.findByCriteria(personaForm.getPersonaH());
		
		//Aqui empieza el c�digo para generar Tabla
		//TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		//tableFacade.setItems(list);
		//---- Genera los tipos de formas con que se podran exportar los datos
		/*tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL, ExportType.PDF);
		tableFacade.setStateAttr("restore");
		
		Limit limit = tableFacade.getLimit();*/
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
        request.setAttribute(Constantes.ACCION_KEY, "/activeInactivePersonAction");
        request.setAttribute("form", personaForm);
		return mapping.findForward("lista");
	}
	
	public ActionForward findByKey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActiveInactivePersonForm aform = (ActiveInactivePersonForm)form;
		SecPerPersona modelPerPersona = null;
		SecPerPersonaDAO secPerPersonaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		Transaction tx = secPerPersonaDAO.getSession().beginTransaction();
		try {
			modelPerPersona = secPerPersonaDAO.findById(aform.getPerId());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			 secPerPersonaDAO.getSession().flush();
			 secPerPersonaDAO.getSession().clear();
			 
		}
		modelPerPersona.setPerNit(Format.formatNit(modelPerPersona.getPerNit()));
		aform.setPersonaH(modelPerPersona);
		
		request.setAttribute("form", aform);
		request.setAttribute(Constantes.ACCION_KEY, "/activeInactivePersonAction");
		return mapping.findForward("form");
	}
			
	//---- Mapas usados para conectar las acciones
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.ai.persona.activePerson","activePerson");
		map.put("cmd.ai.persona.inactivePerson","inactivePerson");
		map.put("cmd.ai.persona.deletePerson","deletePerson");
		map.put("cmd.ai.persona.redirectInvalidData","redirectInvalidData");
		map.put("cmd.ai.persona.lista", "lista");
		map.put("cmd.ai.persona.findByExample","findByExample");
		map.put("cmd.ai.persona.findByKey","findByKey");
		return map;
	}

}
