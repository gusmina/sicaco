package com.cetia.sicaco.cuentaCorriente.struts.action;

import java.io.IOException;
import java.util.ArrayList;
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
import org.jmesa.facade.TableFacadeFactory;
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

import com.cetia.sicaco.cuentaCorriente.struts.form.FiadorPrestamoForm;
import com.cetia.sicaco.cuentaCorriente.struts.form.PrestamoForm;
import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaFxpFiadorPrestamo;
import com.cetia.sicaco.hibernate.CtaFxpFiadorPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaPxtPersonaExterna;
import com.cetia.sicaco.hibernate.CtrEstEstadoDAO;
import com.cetia.sicaco.hibernate.SecCelCorreoElectronicoDAO;
import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecPerPersonaDAO;
import com.cetia.sicaco.hibernate.SecTelTelefono;
import com.cetia.sicaco.hibernate.SecTelTelefonoDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.Format;

/** 
 * 
 * XDoclet definition:
 * @struts.action path="/fiadorPrestamo" name="fiadorForm" parameter="accion" scope="request"
 */
public class FiadorAction extends DMLAction {
	
	public static final String TABLA_ID = "ctaFxpFiadorPrestamo";
	private static final String PRE_ID = "ctaPrePrestamo.preId";

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FiadorPrestamoForm fiadorPrestamoForm = (FiadorPrestamoForm) form;
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaFxpFiadorPrestamoDAO fiadorPrestamoDAO = new CtaFxpFiadorPrestamoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociado casCuenta = cuentaAsociadoDAO.findByPreId(fiadorPrestamoForm.getPreId());
		CtaAscAsociadoDAO asoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado asc = new CtaAscAsociado();
		SecPerPersonaDAO perDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona per = new SecPerPersona();
		asc = casCuenta.getCtaAscAsociado();
		String asc_id = asc.getAscId();
		asc = asoDAO.findById(asc_id);
		per = asc.getSecPerPersona();
		String per_id = per.getPerId();
		
		fiadorPrestamoForm.setPerId(per_id);
		List lst = fiadorPrestamoDAO.findByProperty(PRE_ID, fiadorPrestamoForm.getPreId());
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		//tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
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
		request.setAttribute("prestamo", casCuenta.getCtaPrePrestamo());
		request.setAttribute("asociado", casCuenta.getCtaAscAsociado());
		request.setAttribute("fiadorForm", form);
		request.setAttribute(Constantes.ACCION_KEY, "/fiadorPrestamo");
		return mapping.findForward("lista");
	}
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("fxpId","preId","ascId","fxpEstado");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.fxp.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("fxpId");
		nombreColumna.setTitleKey("tbl.fxp.Nombre");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				CtaFxpFiadorPrestamo fiador = (CtaFxpFiadorPrestamo) item;
				FiadorPrestamoForm fiadorForm = (FiadorPrestamoForm) request.getAttribute("fiadorForm");
				HtmlBuilder html = new HtmlBuilder();
				// ---- Generar link para entrar a edicion
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/fiadorPrestamo.do?fxpId="+ fiador.getFxpId() + "&preId="+ fiador.getCtaPrePrestamo().getPreId()+ "&accion=cargarDatos&mdf=true&estadoPrestamo="+fiadorForm.getEstadoPrestamo();
				if(fiador.getCtaAscAsociado() == null){
					CtaPxtPersonaExterna personaExterna = fiador	.getCtaPxtPersonaExterna();
					value = personaExterna.getPxtPrimerApellido() + ", "+ personaExterna.getPxtNombres();
				}else{
					value = fiador.getCtaAscAsociado().getSecPerPersona().getPerPrimerApellido()+". "+fiador.getCtaAscAsociado().getSecPerPersona().getPerPrimerNombre()+" " +fiador.getCtaAscAsociado().getSecPerPersona().getPerSegundoNombre();
				}
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();
				value = html;

				return value.toString();
			}
		});
		
		nombreColumna = row.getColumn("preId");
		nombreColumna.setTitleKey("tbl.fxp.salario");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaFxpFiadorPrestamo fiador = (CtaFxpFiadorPrestamo)item;
				//HtmlBuilder html = new HtmlBuilder();
				//String link = tableFacade.getWebContext().getContextPath();
				if(fiador.getCtaPxtPersonaExterna() == null){//si el id viene nulo quiere decir que el fiador es externo, caso contrario es externo.
					CtaAscAsociado ascAsociado = fiador.getCtaAscAsociado();
					value= ascAsociado.getAscSalario();
				}else{
					CtaPxtPersonaExterna personaExterna = fiador.getCtaPxtPersonaExterna();
					value = personaExterna.getPxtSalario();
				}			
				return Format.formatDinero(value);
			}
		});
		
		nombreColumna = row.getColumn("ascId");
		nombreColumna.setTitleKey("tbl.fxp.tipoFiador");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaFxpFiadorPrestamo fiador = (CtaFxpFiadorPrestamo)item;
				if(fiador.getCtaPxtPersonaExterna() == null){//si el id viene nulo quiere decir que el fiador es externo, caso contrario es externo.
					value = getResources(request).getMessage("lbl.fxp.asociado");
				}else{
					value = getResources(request).getMessage("lbl.fxp.personaExterna");
				}			
				return value.toString();
			}
		});
		
		nombreColumna = row.getColumn("fxpEstado");
		nombreColumna.setTitleKey("tbl.fxp.estadoFiador");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaFxpFiadorPrestamo fiador = (CtaFxpFiadorPrestamo)item;
				if(fiador.getFxpEstado().equals("D")) value="Denegado";
				if(fiador.getFxpEstado().equals("C")) value="Cancelando";
				if(fiador.getFxpEstado().equals("V"))	value="En Verificacion";	
				if(fiador.getFxpEstado().equals("S")) value="Solvente";
				if(fiador.getFxpEstado().equals("A"))value="Aprobado";
				return value.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	public ActionForward cargarListaAsociados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		FiadorPrestamoForm fiadorPrestamoForm = (FiadorPrestamoForm) form;
		try {
		String listaResponse = construirListaAsociados(asociadoDAO.findByNameUserWithoutMe(fiadorPrestamoForm.getCtaAscAsociado(),fiadorPrestamoForm.getAscId()));
			response.getWriter().write(listaResponse);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (RuntimeException e) {
			log.error("Error runtime", e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	private String construirListaAsociados(List<CtaAscAsociado> listaAsociados){
		String lista = "<table id=\"resultadoCli2\">";
		int max = 0;
		if(listaAsociados.size() == 0)
			lista+= "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
			+ "No se encontro asociado en el sistema para esta b&uacute;squeda</span></td>";
		else{
			Iterator<CtaAscAsociado> it = listaAsociados.iterator();
			while(it.hasNext() &&  max < 5){
				CtaAscAsociado asociado = it.next();
				lista += "<tr>";
				lista += "<td><input onclick=\"JavaScript:saveSeleccion2(this.value);\" type=\"radio\" name=\"_miseleccion\" value=\""
					+ asociado.getAscCodigoAsociado()
					+ ";"
					+ asociado.getSecPerPersona().getPerNit()
					+ ";"
					+ asociado.getSecPerPersona().getPerDui()
					+ ";"
					+ asociado.getSecPerPersona().getPerPrimerNombre()
					+ ";"
					+ asociado.getSecPerPersona().getPerSegundoNombre()
					+ ";"
					+ asociado.getSecPerPersona().getPerPrimerApellido()
					+ ";"
					+ asociado.getSecPerPersona().getPerSegundoApellido()
					+ ";"
					+ asociado.getAscId()
					+ ";"
					+ asociado.getAscCodigo()
					+ "\"/></td>";
			lista += "<td><span style=\"font-size: 10px;color: #6E6E6E;font-style: italic;\">"
					+ asociado.getAscCodigoAsociado()
					+ " "
					+ asociado.getSecPerPersona().getPerPrimerNombre()
					+ " "
					+ asociado.getSecPerPersona().getPerPrimerApellido();
					if(asociado.getEstId() == 21){
						lista += "</span> "
							+ "<span style=\"font-size: 10px;color: #FF1414;font-style: italic;\">"
							+ "Restringido</span>";
					}
					lista += "</span></td></tr>";
			max++;
			}
		}
		lista += "</table>";
		return lista;
	}
	
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		String listaResponse = "";
		String listaErrores ="";
		ArrayList<String> errors = new ArrayList<String>();
		FiadorPrestamoForm fiadorForm = (FiadorPrestamoForm) form;
		HashMap<Long, CtaFxpFiadorPrestamo> mapa = (HashMap<Long, CtaFxpFiadorPrestamo>)request.getSession().getAttribute("listaFiadores");
		errors = validarSolicitudFiador(fiadorForm, errors,request);
		if(!errors.isEmpty()){
			listaErrores=construirListaErrores(errors);
		}else{
			fiadorForm.setFxpEstado("V");//setea el estado "En verificacion", para dar a entender que el fiador aun no ha sido certificado.
			if(fiadorForm.getFiador().equals("asociado") ){
				fiadorForm.setCtaPxtPersonaExterna(null);
				fiadorForm.setCtaAscAsociado(asociadoDAO.findById(fiadorForm.getCtaAscAsociado().getAscId()));
			}else{//si el fiador es externo se aplicara otra logica
				fiadorForm.setCtaAscAsociado(null);
				fiadorForm.getCtaPxtPersonaExterna().setPxtEmpresa(fiadorForm.getCtaPxtPersonaExterna().getPxtTrabajo());
				//fiadorForm.set)))
				/***fiadorForm.getCtaPxtPersonaExterna().setPxtId(personaExternaDAO.generarId());
				personaExternaDAO.save(fiadorForm.getCtaPxtPersonaExterna());**/
			}
			fiadorForm.setCtaTfiTipoFiador(null);
			mapa.put(System.currentTimeMillis(),fiadorForm.getFiadorPrestamoH());
			request.getSession().setAttribute("listaFiadores", mapa);
		}
		listaResponse =construirListaFiadores(mapa,request);
		try {
			response.getWriter().write(listaErrores+listaResponse);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String construirListaFiadores(HashMap<Long, CtaFxpFiadorPrestamo> mapa,HttpServletRequest request){
		String html = "<table class=\"tableone\" summary=\"\"><caption>"
				+ getResources(request).getMessage("lbl.fxp.caption")
				+ "</caption>";
		html = html
				+ "<thead><tr><th class=\"th1\" scope=\"col\">&nbsp;</th><th class=\"th1\" scope=\"col\">"
				+ getResources(request).getMessage("lbl.fxp.Nombre")
				+ "</th><th class=\"th1\" scope=\"col\">"
				+ getResources(request).getMessage("lbl.fxp.salario")
				+ "</th><th class=\"th1\" scope=\"col\">"
				+ getResources(request).getMessage("lbl.fxp.tipoFiador")
				+ "</th></tr></thead><tbody><tr><td colspan=\"5\"><div class=\"innerb\"><table class=\"tabletwo\">";
		long i;
		Iterator<Long> iterator = mapa.keySet().iterator();

		while (iterator.hasNext()) {
			i = iterator.next().longValue();
			CtaFxpFiadorPrestamo fxp = mapa.get(i);
			html = html
					+ "<tr><th class=\"td1\" scope=\"row\"><input type=\"checkbox\" name=\"posicionFxp\" class=\"posicionFxp\" value=\""
					+ i + "\"/></th>" + "<td class=\"td1\">";
					if(fxp.getCtaAscAsociado() == null){//es externo
						html = html +  fxp.getCtaPxtPersonaExterna().getPxtNombres()+ ", " +fxp.getCtaPxtPersonaExterna().getPxtPrimerApellido() + "</td><td class=\"td1\">"
						+ fxp.getCtaPxtPersonaExterna().getPxtSalario() + "</td><td class=\"td1\">"
						+ getResources(request).getMessage("lbl.fxp.personaExterna")+ "</td></tr>";
					}else{//es asociado
						html = html +  fxp.getCtaAscAsociado().getSecPerPersona().getPerPrimerNombre()+ ", " +fxp.getCtaAscAsociado().getSecPerPersona().getPerPrimerApellido() + "</td><td class=\"td1\">"
						+ fxp.getCtaAscAsociado().getAscSalario() + "</td><td class=\"td1\">"
						+ getResources(request).getMessage("lbl.fxp.asociado")+ "</td></tr>";
					}
		}
		html = html + "</table></div></td></tr></tbody></table>";
		return html;
	}
	
	private ArrayList<String> validarSolicitudFiador(FiadorPrestamoForm fiadorForm,ArrayList<String> errors , HttpServletRequest request){
		String ascId = fiadorForm.getFiadorPrestamoH().getCtaAscAsociado().getAscId();
		HashMap<Long, CtaFxpFiadorPrestamo> mapa = (HashMap<Long, CtaFxpFiadorPrestamo>)request.getSession().getAttribute("listaFiadores");
		if(fiadorForm.getFiador().equals("asociado")){
			if(fiadorForm.getFiadorPrestamoH().getCtaAscAsociado().getAscId() == null || fiadorForm.getCtaAscAsociado().getAscId().trim().equals("")){
				errors.add(getResources(request).getMessage("errors.fxp.asociadoRequiered"));
			}
			Iterator<CtaFxpFiadorPrestamo> it =mapa.values().iterator();
			while(it.hasNext()){//para verificar que no se agregue el mismo asociado dos veces
				CtaFxpFiadorPrestamo fxp = it.next();
				if(fxp.getCtaAscAsociado() != null && fxp.getCtaAscAsociado().getAscId().equals(ascId)){
					errors.add(getResources(request).getMessage("errors.fxp.asociadoRepetido"));
					break;
				}
			}
		}
		/**	
		 *  ESTA VERIFICACION DEBE UTILIZARSE PARA ESCRIBIR UNA NOTA CUANDO SE HAGA SUBMIT DE LA SOLICITUD, ES VERIFICACION DE RUTINA NO DE ERROR
		 * 
		 if(fiadorPrestamoDAO.findByProperty(ASC_ID, fiadorForm.getCtaAscAsociado().getAscId()).size() > parametrosDAO.findById("MAXIMO_ASC_FIADOR").getParValorNumber()){
				errors.add(getResources(request).getMessage("errors.fxp.fiadorNumAscSuperado"));
			}*/
		if(fiadorForm.getFiador().equals("externo")){
			CtaPxtPersonaExterna externa = fiadorForm.getCtaPxtPersonaExterna();
			if(externa.getPxtDui() == null || externa.getPxtDui().trim().equals("")){
				errors.add(getResources(request).getMessage("errors.fxp.pxtDui.required"));
			}
			if(externa.getPxtNombres() == null || externa.getPxtNombres().trim().equals("")){
				errors.add(getResources(request).getMessage("errors.fxp.pxtNombres.required"));
			}
			if(externa.getPxtPrimerApellido() == null || externa.getPxtPrimerApellido().trim().equals("")){
				errors.add(getResources(request).getMessage("errors.fxp.pxtPrimerApellido.required"));
			}
			if(externa.getPxtDireccion() == null || externa.getPxtDireccion().trim().equals("")){
				errors.add(getResources(request).getMessage("errors.fxp.pxtDireccion.required"));
			}
			if(externa.getPxtTelefonoCasa() == null || externa.getPxtTelefonoCasa().trim().equals("")){
				errors.add(getResources(request).getMessage("errors.fxp.pxtDireccion.required"));
			}
			if(externa.getPxtTrabajo() == null || externa.getPxtTrabajo().trim().equals("")){
				errors.add(getResources(request).getMessage("errors.fxp.pxtLugarTrabajo.required"));
			}
			if(externa.getPxtSalario() == null || externa.getPxtSalario() < 0){
				errors.add(getResources(request).getMessage("errors.fxp.pxtSalario.required"));
			}
			Iterator<CtaFxpFiadorPrestamo> it =mapa.values().iterator();
			while(it.hasNext()){//para verificar que no se agreguen dos o mas personas con el mismo dui
				CtaFxpFiadorPrestamo fxp = it.next();
				if(fxp.getCtaPxtPersonaExterna() != null && fxp.getCtaPxtPersonaExterna().getPxtDui().equals(externa.getPxtDui())){
					errors.add(getResources(request).getMessage("errors.fxp.pxtRepetido"));
					break;
				}
			}
		}
		if(fiadorForm.getFiador().trim().equals("")){
			errors.add(getResources(request).getMessage("errors.fxp.fxpTipoNoSel"));
		}

		return errors;
	}
	
	private String construirListaErrores(ArrayList<String> errors){
		String errores = "<table align=\"center\"><tr><td colspan=\"2\"><span style=\"font-size: 14px;color: red;font-style: italic;\">Se han encontrado los siguientes errores:</span></td></tr>";
		Iterator<String> it = errors.iterator();
		while(it.hasNext()){
			errores=errores+"<tr><td><span style=\"font-size: 12px;color: red;font-style: italic;\">"+ 
			it.next()
			+"</span></td></tr>";
		}
		errores=errores+"</table>";
		return errores;
	}
	
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		FiadorPrestamoForm fiadorForm = (FiadorPrestamoForm) form;
		CtaFxpFiadorPrestamoDAO fiadorPrestamoDAO = new CtaFxpFiadorPrestamoDAO(getSessionHibernate(request));
		Transaction tx =fiadorPrestamoDAO.getSession().beginTransaction();
		if(!fiadorForm.getFxpId().equals("")){
			try{
				CtaFxpFiadorPrestamo fiador = fiadorPrestamoDAO.findById(fiadorForm.getFxpId());
				fiador.setFxpEstado(fiadorForm.getFxpEstado());
				tx.commit();
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("lbl.pre.modificacionExitoSol"));
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				fiadorPrestamoDAO.getSession().flush();
				fiadorPrestamoDAO.getSession().clear();
				
				fiadorForm.setMdf(false);
			}
		}else{
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.fxo.fiador.required"));
		}
		saveMessages(request, errors);
		return lista(mapping, fiadorForm, request, response);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FiadorPrestamoForm fiadorForm = (FiadorPrestamoForm) form;
		HashMap<Long, CtaFxpFiadorPrestamo> mapa = (HashMap<Long, CtaFxpFiadorPrestamo>)request.getSession().getAttribute("listaFiadores");
		if (fiadorForm.getPosicionFxp() != null) {
			int size = fiadorForm.getPosicionFxp().length;
			int i = 0;
			while (i < size) {
				mapa.remove(fiadorForm.getPosicionFxp()[i]);
				i++;
			}
		}
		try {
			request.getSession().setAttribute("listaFiadores", mapa);
			response.getWriter().write(construirListaFiadores(mapa, request));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward cargarDatos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FiadorPrestamoForm fiadorForm = (FiadorPrestamoForm) form;
		SecTelTelefonoDAO telefonoDAO = new SecTelTelefonoDAO(getSessionHibernate(request));
		SecCelCorreoElectronicoDAO celCorreoElectronicoDAO = new SecCelCorreoElectronicoDAO(getSessionHibernate(request));
		CtaFxpFiadorPrestamoDAO fiadorPrestamoDAO = new CtaFxpFiadorPrestamoDAO(getSessionHibernate(request));
		CtaFxpFiadorPrestamo fxp = (CtaFxpFiadorPrestamo)fiadorPrestamoDAO.findById(fiadorForm.getFxpId());
		if(fxp.getCtaAscAsociado() != null){//el fiador es una asociado
			fiadorForm.setNombreFiador(fxp.getCtaAscAsociado().getSecPerPersona().getPerPrimerNombre() + " " + fxp.getCtaAscAsociado().getSecPerPersona().getPerSegundoNombre());
			fiadorForm.setPrimerApellido(fxp.getCtaAscAsociado().getSecPerPersona().getPerPrimerApellido());
			fiadorForm.setSegundoApellido(fxp.getCtaAscAsociado().getSecPerPersona().getPerSegundoApellido());
			fiadorForm.setDireccion(fxp.getCtaAscAsociado().getSecPerPersona().getPerCalle() + " " + fxp.getCtaAscAsociado().getSecPerPersona().getPerColoniaBarrio() + " " + fxp.getCtaAscAsociado().getSecPerPersona().getPerCodigoPostal()+ " "+fxp.getCtaAscAsociado().getSecPerPersona().getPerMunicipio());
			fiadorForm.setCodigo(fxp.getCtaAscAsociado().getAscCodigo());
			List lista = telefonoDAO.findByProperty("id.secPerPersona.perId", fxp.getCtaAscAsociado().getSecPerPersona().getPerId());
			if(!lista.isEmpty()){//por si no tiene telefonos
				SecTelTelefono tel = (SecTelTelefono)lista.get(0);
				fiadorForm.setTelefono(tel.getId().getTelTelefono());
			}
			String correo = celCorreoElectronicoDAO.findCorreoByPerId(fxp.getCtaAscAsociado().getSecPerPersona().getPerId());
			fiadorForm.setPxtEmail((correo != null? correo:""));
			try {
				String relajo= fxp.getCtaAscAsociado().getCtaDptDepartamentoTrabajo().getCtaEtrEmpresaTrabajo().getEtrNombre() +"-"+fxp.getCtaAscAsociado().getCtaDptDepartamentoTrabajo().getDptNombre();
				fiadorForm.setLugarTrabajo(relajo);
			} catch (Exception e) {
				fiadorForm.setLugarTrabajo(" ");
			}
			
			fiadorForm.setJefeInmediato(fxp.getCtaAscAsociado().getAscJefeInmediato());
			fiadorForm.setSalario(fxp.getCtaAscAsociado().getAscSalario());
			
		}else{//el fiador es una persona externa
			fiadorForm.setNombreFiador(fxp.getCtaPxtPersonaExterna().getPxtNombres());
			fiadorForm.setPrimerApellido(fxp.getCtaPxtPersonaExterna().getPxtPrimerApellido());
			fiadorForm.setSegundoApellido(fxp.getCtaPxtPersonaExterna().getPxtSegundoApellido());
			fiadorForm.setDireccion(fxp.getCtaPxtPersonaExterna().getPxtDireccion());
			fiadorForm.setLugarTrabajo(fxp.getCtaPxtPersonaExterna().getPxtTrabajo());
			fiadorForm.setSalario(fxp.getCtaPxtPersonaExterna().getPxtSalario());
			fiadorForm.setTelefono(fxp.getCtaPxtPersonaExterna().getPxtTelefonoCasa());
			fiadorForm.setTelTrabajo(fxp.getCtaPxtPersonaExterna().getPxtTelefonoOficina());
			fiadorForm.setJefeInmediato(fxp.getCtaPxtPersonaExterna().getPxtJefeInmediato());
			fiadorForm.setPxtEmail(fxp.getCtaPxtPersonaExterna().getPxtEmail());
			fiadorForm.setCodigo(fxp.getCtaPxtPersonaExterna().getPxtCodigoEmpleado());
		
		}
		fiadorForm.setFiadorPrestamoH(fxp);
		return lista(mapping,fiadorForm, request, response);
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		saveMessages(request, errors);
		return lista(mapping, form, request, response);
	}
	
	public ActionForward regresarToPrestamo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SecIseInicioSesionDAO sesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		PrestamoForm prestamoForm = new PrestamoForm();
		FiadorPrestamoForm fxpForm = (FiadorPrestamoForm) form;
		prestamoForm.setPreId(fxpForm.getPreId());
		request.setAttribute("prestamoForm", prestamoForm);
		SecIseInicioSesion sesion = sesionDAO.findById(fxpForm.getUsuarioConectado().getNombreUsuario());
		if((sesion.getSecRolRoles().getRolNombre().equals("COMITECRED") || sesion.getSecRolRoles().getRolNombre().equals("CONSADMIN")) && !sesion.getSecPerPersona().getPerId().equals(fxpForm.getPerId())){
			request.setAttribute("rol", "iddkfq");
		}
		return mapping.findForward("forwardToPrestamo");
	}
	
	public ActionForward cargarFiadores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		try {
//			Thread.currentThread().sleep(10);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		String listaResponse = "";
		FiadorPrestamoForm fiadorForm = (FiadorPrestamoForm) form;
		HashMap<Long, CtaFxpFiadorPrestamo> mapa = (HashMap<Long, CtaFxpFiadorPrestamo>)request.getSession().getAttribute("listaFiadores");
		CtaFxpFiadorPrestamoDAO fiadorPrestamoDAO = new CtaFxpFiadorPrestamoDAO(getSessionHibernate(request));
		List<CtaFxpFiadorPrestamo> lista = fiadorPrestamoDAO.findByProperty("ctaPrePrestamo.preId", fiadorForm.getPreId());
		Iterator iterator = lista.iterator();
		int add = 0;
		while(iterator.hasNext()) {
			CtaFxpFiadorPrestamo fiadorPrestamo = (CtaFxpFiadorPrestamo) iterator.next();
			mapa.put(System.currentTimeMillis() + add, fiadorPrestamo);
			add++;
		}
		listaResponse =construirListaFiadores(mapa, request);
		try {
			String var = listaResponse;
			response.getWriter().write(var);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward regresarToPrestamoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toPrestamosList");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FiadorPrestamoForm fiadorForm = (FiadorPrestamoForm) form;
		CtaFxpFiadorPrestamoDAO fiadorPrestamoDAO = new CtaFxpFiadorPrestamoDAO(getSessionHibernate(request));
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID, request);
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = fiadorPrestamoDAO.getTotalRowCountFiadorByCriteria(fiadorForm.getFiadorPrestamoH());
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		List<CtaFxpFiadorPrestamo> lista = fiadorPrestamoDAO.findFiadorByCriteria(fiadorForm.getFiadorPrestamoH(), rowStart, rowEnd);
		tableFacade.setItems(lista);
		/*List<CtaFxpFiadorPrestamo> lista = fiadorPrestamoDAO.findFiadorByCriteria(fiadorForm.getFiadorPrestamoH());
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lista);
		//---- Genera los tipos de formas con que se podran exportar los datos
	//	tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();*/
		if (limit.isExported()) {
        	//---- exporta la tabla
        //    export(tableFacade);
            return null; 
        } else {
        	String html = htmlFiador(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		request.setAttribute(Constantes.ACCION_KEY, "/fiadorPrestamo");
		return mapping.findForward("forwardToBusqueda");
	}
	
	
	private String htmlFiador(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("ascCodigoAsociado","secPerPersona.perPrimerNombre");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.ascAsociado.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("ascCodigoAsociado");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascCodigo");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				CtaFxpFiadorPrestamo fiadorPrestamo = (CtaFxpFiadorPrestamo)item;
				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/fiadorPrestamo.do?&ascId="+fiadorPrestamo.getCtaAscAsociado().getAscId()+"&accion=forwardToListaDeudores";
				html.a().href().quote().append(link).quote().close();
				html.append(fiadorPrestamo.getCtaAscAsociado().getAscCodigoAsociado());
				html.aEnd();				
				return html.toString();	
			}
		});
		
		nombreColumna = row.getColumn("secPerPersona.perPrimerNombre");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascNombre");
		//----- Implementamos la edicion de asociado
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaFxpFiadorPrestamo fiadorPrestamo = (CtaFxpFiadorPrestamo) item;
				SecPerPersona persona = fiadorPrestamo.getCtaAscAsociado().getSecPerPersona();
				value = persona.getPerPrimerApellido();
				value = value + (isObjectNull(persona.getPerSegundoApellido())?"":(" "+persona.getPerSegundoApellido()));
				value = value  + ", " +persona.getPerPrimerNombre();
				value = value  + (isObjectNull(persona.getPerSegundoNombre())?"":(" "+persona.getPerSegundoNombre()));		
				return value.toString();	
			}
		});	
		
		return tableFacade.render();
	}
	
	public ActionForward forwardToListaDeudores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaFxpFiadorPrestamoDAO fiadorDAO = new CtaFxpFiadorPrestamoDAO(getSessionHibernate(request));
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		FiadorPrestamoForm fiadorPrestamoForm = (FiadorPrestamoForm) form;
	//	fiadorPrestamoForm.setFiadorPrestamoH(fiadorDAO.findById(fiadorPrestamoForm.getFxpId()));
		List lst = fiadorDAO.findDeudores(fiadorPrestamoForm.getAscId());
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
        	//---- exporta la tabla
        //    export(tableFacade);
            return null; 
        } else {
        	String html = htmlDeudores(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
		request.setAttribute("asociado", asociadoDAO.findById(fiadorPrestamoForm.getAscId()));
		request.setAttribute(Constantes.ACCION_KEY, "/fiadorPrestamo");
		return mapping.findForward("dml");
	}
	
	private String htmlDeudores(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("ctaPrePrestamo.preId", "ctrEstEstado.estNombre","casFechaApertura","ctaPrePrestamo.preMontoSolicitado"
				,"ctaPrePrestamo.preSaldoActualT","ctaAscAsociado.ascCodigoAsociado","ctaAscAsociado.secPerPersona.perPrimerNombre","ctaAscAsociado.estId");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.fxp.caption.deudores");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("ctaPrePrestamo.preId");
		nombreColumna.setTitleKey("tbl.cas.casReferencia");
		
		nombreColumna = row.getColumn("ctrEstEstado.estNombre");
		nombreColumna.setTitleKey("tbl.cas.estadoPrestamo");
		
		nombreColumna = row.getColumn("casFechaApertura");
		nombreColumna.setTitleKey("tbl.cas.fechaApertura");
		nombreColumna.getCellRenderer().setCellEditor(new DateCellEditor("dd-MM-yyyy"));
		
		nombreColumna = row.getColumn("ctaPrePrestamo.preMontoSolicitado");
		nombreColumna.setTitleKey("tbl.cas.montoSolicitado");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){
			
			public Object getValue(Object item, String property, int rowcount) {
				CtaCasCuentaAsociado cuentaAsoc = (CtaCasCuentaAsociado)item;
				CtaPrePrestamo prestamo = cuentaAsoc.getCtaPrePrestamo();
					return Format.formatDinero(prestamo.getPreMontoSolicitado());
			}
			
		});
		
		nombreColumna = row.getColumn("ctaPrePrestamo.preSaldoActualT");
		nombreColumna.setTitleKey("tbl.cas.saldoActual");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){
			
			public Object getValue(Object item, String property, int rowcount) {
				CtaCasCuentaAsociado cuentaAsoc = (CtaCasCuentaAsociado)item;
				CtaPrePrestamo prestamo = cuentaAsoc.getCtaPrePrestamo();
					return Format.formatDinero(prestamo.getPreLiquidoARecibir());
			}
			
		});
		
		nombreColumna = row.getColumn("ctaAscAsociado.ascCodigoAsociado");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascCodigo");
		
		nombreColumna = row.getColumn("ctaAscAsociado.secPerPersona.perPrimerNombre");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascNombre");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaCasCuentaAsociado cuentaAsociado = (CtaCasCuentaAsociado) item;
				SecPerPersona persona = cuentaAsociado.getCtaAscAsociado().getSecPerPersona();
				value = persona.getPerPrimerApellido();
				value = value + (isObjectNull(persona.getPerSegundoApellido())?"":(" "+persona.getPerSegundoApellido()));
				value = value  + ", " +persona.getPerPrimerNombre();
				value = value  + (isObjectNull(persona.getPerSegundoNombre())?"":(" "+persona.getPerSegundoNombre()));		
				return value.toString();	
			}
		});
		
		nombreColumna = row.getColumn("ctaAscAsociado.estId");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascEstado");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaCasCuentaAsociado cuentaAsociado = (CtaCasCuentaAsociado) item;
				CtaAscAsociado asociado = cuentaAsociado.getCtaAscAsociado();
				CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
				value = estadoDAO.findById(asociado.getEstId()).getEstNombre().toString();
				return value;
			}
		});
		
		return tableFacade.render();
	}
	
	public ActionForward regresarToDeudores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return buscar(mapping, form, request, response);
	}
	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.fxp.lista","lista");
		map.put("cmd.fxp.cargarListaAsociados","cargarListaAsociados");
		map.put("cmd.fxp.agregar","guardar");
		map.put("cmd.fxp.actualizar","modificar");
		map.put("cmd.fxp.eliminar","eliminar");
		map.put("cmd.fxp.cargarDatos","cargarDatos");
		map.put("cmd.fxp.redirectInvalidData","redirectInvalidData");
		map.put("cmd.fxp.regresar","regresarToPrestamo");
		map.put("cmd.fxp.cargarFiadores","cargarFiadores");
		map.put("cmd.fxp.cancelar","regresarToPrestamoList");
		map.put("cmd.fxp.buscar","buscar");
		map.put("cmd.fxp.forwardToListaDeudores","forwardToListaDeudores");
		map.put("cmd.fxp.regresarToPrestamoList", "regresarToPrestamoList");	
		map.put("cmd.fxp.regresarToDeudores", "regresarToDeudores");	
		return map;
	}
	
}