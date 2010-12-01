/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.seguridad.struts.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.cetia.sicaco.hibernate.SecMopMenuOpcion;
import com.cetia.sicaco.hibernate.SecMopMenuOpcionDAO;
import com.cetia.sicaco.hibernate.SecRolRoles;
import com.cetia.sicaco.hibernate.SecRolRolesDAO;
import com.cetia.sicaco.hibernate.SecRopRolMenu;
import com.cetia.sicaco.hibernate.SecRopRolMenuDAO;
import com.cetia.sicaco.hibernate.SecRopRolMenuId;
import com.cetia.sicaco.seguridad.struts.form.RolMenuForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.AlreadyExistRolMenuException;

/** 
 * MyEclipse Struts
 * Creation date: 02-24-2008
 * 
 * XDoclet definition:
 * @struts.action path="/rolMenu" name="rolMenuForm" input="pagina-lista.seguridad.rolMenu" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.seguridad.rolMenu"
 * @struts.action-forward name="dml" path="pagina-dml.seguridad.rolMenu"
 * @struts.action-forward name="error" path="pagina-lista.seguridad.rolMenu"
 */
public class RolMenuAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "secRopRolMenu";
	
	public static final String TABLA_ID2 = "secMopMenuOpcion";
	
	public int pos = 0;
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
		RolMenuForm rolMenuForm = (RolMenuForm) form;
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		List list = rolMenuDAO.findAll();
			
		request.setAttribute("listaRolMenu", list);
		
		List lr = rolMenuDAO.findAllRolNombre();
		request.setAttribute("listRol", lr);
		
		List lm = rolMenuDAO.findAllMopName();
		request.setAttribute("listMenu", lm);
	    
		//Aqui empieza el c�digo para generar Tabla
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
        request.setAttribute(Constantes.ACCION_KEY, "/rolMenu");
        request.setAttribute("filtro", "1");
		return mapping.findForward("lista");
		 
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		RolMenuForm rolMenuForm = (RolMenuForm)form;
		//---- lista donde se guardaran los datos a mostrar

		java.util.List list = rolMenuDAO.findByCriteria(rolMenuForm.getRolMenuH());
				
		request.setAttribute("listaRolMenu", list);

		List lr = rolMenuDAO.findAllRolNombre();
		request.setAttribute("listRol", lr);
		
		List lm = rolMenuDAO.findAllMopName();
		request.setAttribute("listMenu", lm);
		
		//Aqui empieza el c�digo para generar Tabla
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
        request.setAttribute(Constantes.ACCION_KEY, "/rolMenu");
        request.setAttribute("filtro", "1");
		return mapping.findForward("lista");
		 
	}
		
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("id.secRolRoles.rolNombre","id.secMopMenuOpcion.mopName","audUsuarioCreacion");
		Table table = tableFacade.getTable();
				
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.rolMenu.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("id.secRolRoles.rolNombre");
		nombreColumna.setTitleKey("tbl.rolMenu.id.rolNombre");
		
		Column column = row.getColumn("id.secMopMenuOpcion.mopName");
		column.setTitleKey("tbl.rolMenu.id.mopName");
		
		column = row.getColumn("audUsuarioCreacion");
		column.setTitle("Eliminar");
		
		column.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecRopRolMenu rolMenu = (SecRopRolMenu)item;
				HtmlBuilder html = new HtmlBuilder();
				String del = "eliminar";
				//---- Generar link para entrar a edicion
				String link = tableFacade.getWebContext().getContextPath();
				link += "/seguridad/rolMenu.do?id.secRolRoles.rolNombre="+rolMenu.getId().getSecRolRoles().getRolNombre()+"&id.secMopMenuOpcion.mopName="+rolMenu.getId().getSecMopMenuOpcion().getMopName()+"&accion=agrega&boton=1";
				html.a().href().quote().append(link).quote().close();
				//---- Agregamos el link con el nombre = value
				html.append(del);
				html.aEnd();
				
				return html.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	private String htmlSeleccion(final TableFacade tableFacade, final HttpServletRequest request, final List list, final String rol) {
		tableFacade.setColumnProperties("mopTitle","mopDescription","mopName");
		Table table = tableFacade.getTable();
				
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.mop.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("mopTitle");
		nombreColumna.setTitleKey("tbl.mop.mopTitle");
		
		Column column = row.getColumn("mopDescription");
		column.setTitleKey("tbl.mop.mopDescripcion");
		
		column = row.getColumn("mopName");
		column.setTitle("Asignar");
		column.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				SecMopMenuOpcion menuOpcion = (SecMopMenuOpcion)item;
				HtmlBuilder html = new HtmlBuilder();
				
				if(!asignado(menuOpcion.getMopName(), rol,request)){
					html.input().type("checkbox").name("posiciones").value(""+pos+"").close();
				}else{
					html.input().type("checkbox").name("posiciones").value(""+pos+"").checked().close();
				}
				pos=pos+1;
				System.out.println(html.toString());
				return html.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("id.secRolRoles.rolNombre","id.secMopMenuOpcion.mopName","audUsuarioCreacion");
			Table table = tableFacade.getTable();
					
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.rolMenu.caption.export");
			
			Row row = table.getRow();
			
			Column nombreColumna = row.getColumn("id.secRolRoles.rolNombre");
			nombreColumna.setTitleKey("tbl.rolMenu.id.rolNombre");
			
			Column column = row.getColumn("id.secMopMenuOpcion.mopName");
			column.setTitleKey("tbl.rolMenu.id.mopName.export");
			
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
		pos = 0;
		RolMenuForm rolMenuForm = (RolMenuForm)form;
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		List list = rolMenuDAO.findAll();
		request.setAttribute("listaRolMenu", list);
		
		SecRolRolesDAO rolesDAO = new SecRolRolesDAO(getSessionHibernate(request));
		List lr = rolesDAO.findAll();
		request.setAttribute("listRol", lr);
		
		SecMopMenuOpcionDAO menuOpcionDAO = new SecMopMenuOpcionDAO(getSessionHibernate(request));
		List lm = menuOpcionDAO.findAllParent();
		request.setAttribute("listMenu", lm);
		
		//Aqui empieza el c�digo para generar Tabla
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID2, request);
		tableFacade.setItems(lm);
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
            String html = htmlSeleccion(tableFacade, request,list,rolMenuForm.getRolNombre());
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        
		//----- Variables de configuracion
		request.setAttribute(Constantes.ACCION_KEY, "/rolMenu");
		request.setAttribute("filtro", "0");
		request.setAttribute("boton", "0");
		request.setAttribute("detalleOrden", 2);
		return mapping.findForward("dml");
	}
	
	//---- Accion asociada al link de edicion de la tabla, entra a pantalla de edicion
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RolMenuForm rolMenuForm = (RolMenuForm) form;
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		List list = rolMenuDAO.findAll();
		request.setAttribute("listaRolMenu", list);
		
		//---- Set de los datos de la persona seleccionada para la edicion
		rolMenuForm.setRolMenuH(rolMenuDAO.findById(rolMenuForm.getId()));
	
		//---- Variables de configuracion
		request.setAttribute(Constantes.ACCION_KEY, "/rolMenuAccion");
		request.setAttribute("filtro", "0");
		request.setAttribute("boton", "1");
		return mapping.findForward("dml");
	}
	
	//---- Accion de guardar nueva persona, regresa a lista principal
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RolMenuForm rolMenuForm = (RolMenuForm) form;
		SecRopRolMenu rolMenuModel = null;
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		
		//---- Indica inicio de las transacciones
		Transaction trx = rolMenuDAO.getSession().beginTransaction();
		try {
			rolMenuModel = rolMenuDAO.findById(rolMenuForm.getId());
			validateSesionLogic(rolMenuModel);
			rolMenuDAO.save(rolMenuForm.getRolMenuH());
			trx.commit();
		}catch(AlreadyExistRolMenuException rolEx){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.rolMenu.rolMenuRelacion"));
			saveMessages(request, errors);
		} 
		catch (Exception e) {
			// TODO: handle exception
			trx.rollback();
			e.printStackTrace();
		}finally{
			rolMenuDAO.getSession().flush();
			rolMenuDAO.getSession().clear();
			
		}
		
		
		//correoForm.setPerId(correoElectronicoDAO.nextId());
		
		//---- Save realiza un insert en la tabla
		
		return lista(mapping, form, request, response);
	}
	private void validateSesionLogic(SecRopRolMenu flagModel) throws Exception{
		if(flagModel != null)throw new AlreadyExistRolMenuException();
	}
	
	//---- Accion de guardar los cambios hecho de una edicion
	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RolMenuForm rolMenuForm = (RolMenuForm) form;
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		
		//---- Inicia transaccion
		Transaction trx = rolMenuDAO.getSession().beginTransaction();
		
		//---- Merge indica un update en la tabla
		rolMenuDAO.merge(rolMenuForm.getRolMenuH());
		trx.commit();
		rolMenuDAO.getSession().flush();
		rolMenuDAO.getSession().clear();
		
		return lista(mapping, form, request, response);
	}
	
	//---- Elimina los datos seleccionados de la tabla
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RolMenuForm rolMenuForm = (RolMenuForm) form;
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		
		//---- Inicia transaccion
		Transaction trx = rolMenuDAO.getSession().beginTransaction();
		
		//---- Delete indica un delete de la tabla
		rolMenuDAO.delete(rolMenuDAO.findById(rolMenuForm.getRolMenuH().getId()));
		trx.commit();
		rolMenuDAO.getSession().flush();
		rolMenuDAO.getSession().clear();
		
		return lista(mapping, form, request, response);
	}
	
	public boolean asignado(String menuName, String rol,HttpServletRequest request){
		SecMopMenuOpcionDAO menuOpcionDAO = new SecMopMenuOpcionDAO(getSessionHibernate(request));
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		
		SecMopMenuOpcion menuOpcion = menuOpcionDAO.findById(menuName);
		if(rol == null || rol.equals("")){
			return false;
		}
		
		List list = rolMenuDAO.findByMopNameByRol(rol);
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			SecRopRolMenu rolMenu = (SecRopRolMenu) iterator.next();
			if(rolMenu.getId().getSecMopMenuOpcion() == menuOpcion){
				return true;
			}
		}
				
		return false;
	}
	
	public ActionForward asignar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RolMenuForm rolMenuForm = (RolMenuForm)form;
		SecRolRolesDAO rolesDAO = new SecRolRolesDAO(getSessionHibernate(request));
		SecRolRoles secRolRoles = rolesDAO.findById(rolMenuForm.getRolName());
		SecMopMenuOpcionDAO menuOpcionDAO = new SecMopMenuOpcionDAO(getSessionHibernate(request));
		List lmenu = menuOpcionDAO.findAllParent();
		
		List lst = (List) request.getSession().getAttribute("ordDet");
		int[] pos = rolMenuForm.getPosiciones();
		int[] pos2 = new int[lmenu.size()];
		
		
		if((pos!=null) && (pos.length > 0)) {
			
			int k = 0;
			for(int i = 0; i < pos2.length; i++){
				if(k+1 > pos.length){
					pos2[i] = -1;
				}else{
					if(i < pos[k]){
						pos2[i] = -1;
					}else{
						pos2[i] = i;
						k++;
					}
				}
				
			}
			
			SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
			Transaction tx = rolMenuDAO.getSession().beginTransaction();
			
			Iterator iterator = lmenu.iterator();
			int[] del = new int[lmenu.size()];
	        for(int i = 0; i < lmenu.size(); i++) {
	        	if(iterator.hasNext()){
	        		//OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)iterator.next();
	        		SecMopMenuOpcion menuOpcion = (SecMopMenuOpcion)iterator.next();
	        		if(pos2[i] == -1){
	        			SecRopRolMenuId rolMenuId = new SecRopRolMenuId();
	        			SecRopRolMenu rolMenu = new SecRopRolMenu();
	        			
	        			rolMenuId.setSecMopMenuOpcion(menuOpcion);
	        			rolMenuId.setSecRolRoles(secRolRoles);
	        			rolMenu.setId(rolMenuId);
	        			
	        			if(rolMenuDAO.findById(rolMenuId) != null ){
	        				rolMenu = rolMenuDAO.findById(rolMenuId);
	        				rolMenu.setAudFechaModificacion(new Date());
	        				rolMenu.setAudUsuarioModificacion(rolMenuForm.getUsuarioConectado().getNombreUsuario());
	        				rolMenuDAO.delete(rolMenu);
	        			}
	        		}else{
	        			SecRopRolMenuId rolMenuId = new SecRopRolMenuId();
	        			SecRopRolMenu rolMenu = new SecRopRolMenu();
	        			
	        			rolMenuId.setSecMopMenuOpcion(menuOpcion);
	        			rolMenuId.setSecRolRoles(secRolRoles);
	        			rolMenu.setId(rolMenuId);
	        			
	        			if(rolMenuDAO.findById(rolMenuId) == null ){
		        			rolMenu.setAudFechaCreacion(new Date());
		        			rolMenu.setAudFechaModificacion(new Date());
		        			rolMenu.setAudUsuarioCreacion(rolMenuForm.getUsuarioConectado().getNombreUsuario());
		        			rolMenu.setAudUsuarioModificacion(rolMenuForm.getUsuarioConectado().getNombreUsuario());
		        			rolMenuDAO.save(rolMenu);
		        			tx.commit();
	        			}
	        		}
	        	}
			}
			//guardamos
			return lista(mapping, form, request, response);
		}
		//solo regresamos
		return lista(mapping, form, request, response);
	}
	
	public ActionForward seleccionaRol(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RolMenuForm rolMenuForm = (RolMenuForm)form;
		SecRolRolesDAO rolesDAO = new SecRolRolesDAO(getSessionHibernate(request));
		SecRolRoles secRolRoles = rolesDAO.findById(rolMenuForm.getRolNombre());
		rolMenuForm.setRolName(rolMenuForm.getRolNombre());
		pos = 0;
		
		SecRopRolMenuDAO rolMenuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));
		List list = rolMenuDAO.findAll();
		request.setAttribute("listaRolMenu", list);
		
		List lr = rolesDAO.findAll();
		request.setAttribute("listRol", lr);
		
		SecMopMenuOpcionDAO menuOpcionDAO = new SecMopMenuOpcionDAO(getSessionHibernate(request));
		List lm = menuOpcionDAO.findAllParent();
		request.setAttribute("listMenu", lm);
		
		//Aqui empieza el c�digo para generar Tabla
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID2, request);
		tableFacade.setItems(lm);
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
            String html = htmlSeleccion(tableFacade, request,list,rolMenuForm.getRolNombre());
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        
		//----- Variables de configuracion
		request.setAttribute(Constantes.ACCION_KEY, "/rolMenu");
		request.setAttribute("filtro", "0");
		request.setAttribute("boton", "1");
		request.setAttribute("detalleOrden", 2);
		request.setAttribute("form", rolMenuForm);
		
		return mapping.findForward("dml");
	}
	
	//---- Mapas usados para conectar las acciones
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.rolMenu.agregar", "guardar");
		map.put("cmd.rolMenu.mostrar","agregar");
		map.put("cmd.rolMenu.editar","editar");
		map.put("cmd.rolMenu.modificar","salvar");
		map.put("cmd.rolMenu.eliminar","eliminar");
		map.put("cmd.rolMenu.cancelar","cancelar");
		map.put("cmd.rolMenu.lista", "lista");
		map.put("cmd.rolMenu.axn2","editar");
		map.put("cmd.rolMenu.dml", "agregar");
		map.put("cmd.rolMenu.buscar", "buscar");
		map.put("cmd.rolMenu.busqueda", "busqueda");
		map.put("cmd.rolMenu.eliminarNo", "cancelar");
		map.put("cmd.rolMenu.eliminarSi", "eliminar");
		map.put("cmd.rolMenu.nueva", "agregar");
		map.put("cmd.rolMenu.asignar", "asignar");
		map.put("cmd.rolMenu.seleccionaRol", "seleccionaRol");
		return map;
	}
}