/**
 * 
 */
package com.cetia.sicaco.struts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.hibernate.Session;

import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.cetia.sicaco.hibernate.SecMopMenuOpcion;
import com.cetia.sicaco.hibernate.SecRopRolMenu;
import com.cetia.sicaco.hibernate.SecRopRolMenuDAO;
import com.mad.utilidades.filtros.FiltroOpenSession;



/**
 * @author mauricio
 *
 */
public class SecurityProcessor extends TilesRequestProcessor {
	

	/* (non-Javadoc)
	 * @see org.apache.struts.action.RequestProcessor#processActionCreate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.struts.action.ActionMapping)
	 */
	
	protected Action processActionCreate(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException {
		Action action = super.processActionCreate(request, response, mapping);
		if (action instanceof DMLAction) {
			DMLAction dmlAction = (DMLAction) action;
			dmlAction.setParametros((Parametros)request.getSession().getAttribute(Constantes.PARAMETROS_KEY));
		}
		return action;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.RequestProcessor#processActionForm(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.struts.action.ActionMapping)
	 */
	
	protected ActionForm processActionForm(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping) {
		ActionForm formulario = super.processActionForm(request, response, mapping);
		if (formulario instanceof BasicForm) {
			BasicForm basic = (BasicForm) formulario;
			HttpSession session = request.getSession();
			basic.setUsuarioConectado((UsuarioConectado)session.getAttribute(Constantes.USUARIO_KEY));
			
		}
		return formulario;
	}


	/* (non-Javadoc)
	 * @see org.apache.struts.action.RequestProcessor#processPreprocess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	
	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {
		boolean sesionValida=false;
		HttpSession session = request.getSession();
		String direccion = request.getRequestURI();
		String parametros= request.getQueryString();
		//System.out.println("------------"+direccion+"?"+parametros);
		String completeURL = direccion+"?"+parametros;
		
		if(request.isRequestedSessionIdValid()){
			Object usuario = session.getAttribute(Constantes.USUARIO_KEY);
			
			if (direccion.indexOf("autenticacion.do")<0) {
				if (usuario != null && validateRol(usuario, completeURL, request)){
					sesionValida = true;
				}
			}else{
				sesionValida=true;
			}
		}
		
		if(sesionValida==false){
			try {
				response.sendRedirect(request.getContextPath()+"/seguridad/autenticacion.do?accion=cargar");
			} catch (IOException e) {

			}
		}
		
		return sesionValida;
	}
	
	protected boolean validateRol(Object uObj, String path, HttpServletRequest request){
		boolean result = false;
		UsuarioConectado u = (UsuarioConectado) uObj;
		//FIXME Mauricio Jovel: Descomenterar la sesion de hibernate
		SecIseInicioSesionDAO sesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		SecIseInicioSesion usuario = sesionDAO.findById(u.getNombreUsuario());
		
		
		List<String> direccionesUsu = new ArrayList<String>();
		List<String> direccionesAdmin = new ArrayList<String>();
		direccionesUsu = obtenerOpcionesPorRol(usuario.getSecRolRoles().getRolNombre(), request);
		direccionesAdmin = obtenerOpcionesPorRol("ADMINISTRADOR", request);
		
/*		for(int i=0;i<direccionesAdmin.size();i++){
			//System.out.println(direccionesAdmin.get(i));
		}*/
		//Obtener el modulo y el action de la url que trae el request
		
		path = obtenerDireccion(path);		
		path = path.substring(6,path.length());

		if(contieneDireccion(direccionesUsu, path)){
			result = true;
		}else{
			if(contieneDireccion(direccionesAdmin, path)){
				result = false;
			}else result = true;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected List<String> obtenerOpcionesPorRol(String rolNombre, HttpServletRequest request){
		List<String> direccionesUsu = new ArrayList<String>();
		//FIXME Mauricio Jovel: Descomentarear como se obtiene la sesion para que de soporte.
		SecRopRolMenuDAO menuDAO = new SecRopRolMenuDAO(getSessionHibernate(request));		
		List<SecRopRolMenu> lista = menuDAO.findByRol(rolNombre);
		if(!lista.isEmpty()){
			for (Iterator<SecRopRolMenu> menus = lista.iterator(); menus.hasNext();) {
				SecRopRolMenu menu2 = (SecRopRolMenu) menus.next();
				SecMopMenuOpcion menuOpcion = menu2.getId().getSecMopMenuOpcion();
				
				if(menuOpcion.getMopAction()!=null) {
					String direccion = menuOpcion.getMopModule()+menuOpcion.getMopAction();//direccion modulo concatenado con action
					direccionesUsu.add(obtenerDireccion(direccion));
				}							
				direccionesUsu = cargarHijos(direccionesUsu,menuOpcion.getSecMopMenuOpcions(), rolNombre);	
			}
		}

		return direccionesUsu;
		
	}

	@SuppressWarnings("unchecked")
	protected List<String> cargarHijos(List<String> lista, Set<SecMopMenuOpcion> menuOpcion, String nombreRol){

		for (Iterator<SecMopMenuOpcion> opciones = menuOpcion.iterator(); opciones.hasNext();) {
			SecMopMenuOpcion opcion = (SecMopMenuOpcion) opciones.next();
			if (isContentInRole(opcion, nombreRol)) {
				if(opcion.getMopAction()!=null){
					String direccion = opcion.getMopModule()+opcion.getMopAction();//direccion modulo concatenado con action
					lista.add(obtenerDireccion(direccion));
				}
				lista = cargarHijos(lista,opcion.getSecMopMenuOpcions(),nombreRol);
			}
		}
		
		return lista;
	}
	
	protected String obtenerDireccion(String direccion){
		int indexOfWps = direccion.indexOf("?");
		
		String o = direccion;//o cadena hasta antes del ? si lo encontro
		if(indexOfWps!=-1){//encontro el ?
			o = direccion.substring(0, indexOfWps);
		}
		String d = o.substring(o.length()-3, o.length());//d tres ultimas letras de o para verificar si son iguales a .do
		String aa = o;// aa variable final para almacenar sin el .do si lo encontro
		if(d.equals(".do")) {
			aa = o.substring(0,o.length()-3);
		}
		int indexOfDir = direccion.indexOf("accion=");
		String accion="";
		if(indexOfDir!=-1){
			accion=direccion.substring(indexOfDir,direccion.length());
			int indexOfAmp = accion.indexOf("&");
			if(indexOfAmp!=-1){
				accion = accion.substring(0,indexOfAmp);
			}
		}
		if(!accion.equals("")) aa = aa + "?" + accion;
		return aa;
	}
	
	protected boolean contieneDireccion(List<String> direcciones,String path){
		boolean contiene = false;
		for(int i=0;i<direcciones.size();i++){
			String direccion = direcciones.get(i);
			/*int indexOfDir = direccion.indexOf(path);
			if(indexOfDir!=-1) {
				contiene=true;
				break;
			}*/
			if(direccion.equals(path)){
				contiene=true;
				break;
			}
		}		
		return contiene;
	}
	
	@SuppressWarnings("unchecked")
	protected boolean isContentInRole(SecMopMenuOpcion opcion, String nombreRol) {
		boolean estaEnRoles = false;
		if(!opcion.getSecRopRolMenus().isEmpty()) {
			for (Iterator<SecRopRolMenu> roles = opcion.getSecRopRolMenus().iterator(); roles.hasNext();) {
				SecRopRolMenu rol = (SecRopRolMenu) roles.next();
				if(nombreRol.equals(rol.getId().getSecRolRoles().getRolNombre())) {
					estaEnRoles = true;
					break;
				}
			}
		}
		return estaEnRoles;
	}	
	
	public Session getSessionHibernate(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}

}
