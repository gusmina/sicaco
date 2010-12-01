/**
 * 
 */
package com.cetia.sicaco.struts;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.hibernate.Session;

import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.mad.utilidades.filtros.FiltroOpenSession;

/**
 * @author Creativa Consultores.
 *
 */
public abstract class DMLAction extends LookupDispatchAction {
	//private String methodName = null;
	
	public boolean isObjectNull(Object obj) {
		boolean ret = false;
		ret = (obj ==null);
		if(!ret) {
			if (obj instanceof String) {
				String stringObj = (String) obj;
				ret = (stringObj.trim().equals(""));
			}
		}
		
		return ret;
	}
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		String methodKey = request.getParameter("accion");
		String methodValue = getLookupMapName(request, methodKey, mapping);
	
	
		if (form instanceof BasicForm) {
			BasicForm basicForm = (BasicForm) form;
			if(methodValue.equals("editar") || methodValue.equals("salvar") || methodValue.equals("salvar2")){
				basicForm.fillAuditoriaUpdate();
				
			}if(methodValue.startsWith("guardar") || methodValue.startsWith("Guardar")){
				basicForm.fillAuditoriaSave();
			}
		}
		ActionForward forward =super.execute(mapping, form, request, response);
		
		return forward;
	}

	private Parametros parametros;

	public Parametros getParametros() {
		return parametros;
	}

	public void setParametros(Parametros parametros) {
		this.parametros = parametros;
	}
	
	public SecIseInicioSesion getSesionUsuarioConectado(HttpServletRequest request) {
		UsuarioConectado usuarioCon = (UsuarioConectado) request.getSession().getAttribute(Constantes.USUARIO_KEY);
		if(usuarioCon==null)return null;
		//FIXME Mauricio Jovel: Descomentarear la obtencion de la sesion de hibernate.
		SecIseInicioSesionDAO dao = new SecIseInicioSesionDAO(getSessionHibernate(request));
		return dao.findById(usuarioCon.getNombreUsuario());
	}
	
	public Session getSessionHibernate(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}
	
	public static Session getSessionHibernate2(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}
}
