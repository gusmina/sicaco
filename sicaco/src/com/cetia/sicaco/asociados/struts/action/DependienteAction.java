/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.asociados.struts.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oro.text.perl.Perl5Util;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Transaction;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.asociados.struts.form.AsociadoForm;
import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorroDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCntCodigosAnterioresDAO;
import com.cetia.sicaco.hibernate.CtaDomDomicilioDAO;
import com.cetia.sicaco.hibernate.CtaInaIngresosxasociado;
import com.cetia.sicaco.hibernate.CtaInaIngresosxasociadoDAO;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaNotNotasDAO;
import com.cetia.sicaco.hibernate.CtaTahTipoAhorroDAO;
import com.cetia.sicaco.hibernate.CtaTasTipoAsociado;
import com.cetia.sicaco.hibernate.CtaTasTipoAsociadoDAO;
import com.cetia.sicaco.hibernate.CtrEstEstadoDAO;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.cetia.sicaco.hibernate.HibernateSessionFactory;
import com.cetia.sicaco.hibernate.SecCelCorreoElectronico;
import com.cetia.sicaco.hibernate.SecCelCorreoElectronicoDAO;
import com.cetia.sicaco.hibernate.SecCelCorreoElectronicoId;
import com.cetia.sicaco.hibernate.SecDppDepartamentoPaisDAO;
import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecPerPersonaDAO;
import com.cetia.sicaco.hibernate.SecRolRoles;
import com.cetia.sicaco.hibernate.SecRolRolesDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.cetia.sicaco.struts.PartidaAutomatica;
import com.cetia.sicaco.struts.UsuarioConectado;
import com.mad.utilidades.ExportReport;
import com.mad.utilidades.ReportFile;
import com.mad.utilidades.ThreadEmail;
import com.mad.utilidades.seguridad.Hasher;
import com.mad.utilidades.seguridad.PwdAleatorio;

/** 
 * MyEclipse Struts
 * Creation date: 08-13-2008
 * 
 * XDoclet definition:
 * @struts.action path="/dependiente" name="asociadoForm" parameter="accion" scope="request"
 */
public class DependienteAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	public static final String TABLA_ID = "ctaAscAsociado";
	public static final String TUS_CODIGO = "ctrTusTipoUso.tusCodigo";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AsociadoForm  asociadoForm = (AsociadoForm) form;
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
 		List lst = asociadoDAO.findByAscAsociadoPadre(asociadoForm.getAscAsociadoH().getAscAsociadoPadre().toString());
		
 		CtaAscAsociado asociado = asociadoDAO.findById(asociadoForm.getAscAsociadoH().getAscAsociadoPadre());
 		request.setAttribute("ctaAsociado", asociado);
 		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
 		SecPerPersona persona = personaDAO.findById(asociado.getSecPerPersona().getPerId());
 		request.setAttribute("secPersona", persona);
 		//paginacion Roberto
 		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID ,request );
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = personaDAO.getTotalRowCountbyAscAsociadoPadre(asociadoForm.getAscAsociadoH().getAscAsociadoPadre().toString());
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		lst = personaDAO.findAllbyAscAsociadoPadre(asociadoForm.getAscAsociadoH().getAscAsociadoPadre().toString(), rowStart, rowEnd);
		tableFacade.setItems(lst);
 		/*
 		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		*/
		if (limit.isExported()) {
        	//---- exporta la tabla
        //    export(tableFacade);
            return null; 
        } else {
        	String html = html(tableFacade, request);
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        //----- Variables de configuracion
		request.setAttribute(Constantes.ACCION_KEY, "/dependiente");
		request.setAttribute("form", asociadoForm);
		return mapping.findForward("lista");
	}
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("ascCodigoAsociado","secPerPersona.perPrimerNombre","estId");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.ascAsociado.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("ascCodigoAsociado");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascCodigo");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaAscAsociado asociado = (CtaAscAsociado)item;
				HtmlBuilder html = new HtmlBuilder();
				value = asociado.getAscCodigoAsociado();
				//---- Generar link para entrar a edicion
				String link = tableFacade.getWebContext().getContextPath();
				//link += "/asociados/dependiente.do?&ascId="+asociado.getAscId()+"&ascAsociadoPadre="+asociado.getAscAsociadoPadre()+"&accion=forwardToViewAsociadoDpte";
				link += "/asociados/asociado.do?accion=cargarDatos&mdf=true&ascId="+asociado.getAscId();
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();				
				return html.toString();	
			}
		});
		
		nombreColumna = row.getColumn("estId");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascEstado");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaAscAsociado asociado = (CtaAscAsociado)item;
				CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
				value = estadoDAO.findById(asociado.getEstId()).getEstNombre().toString();
				return value;
			}
		});
		
		nombreColumna = row.getColumn("secPerPersona.perPrimerNombre");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascNombre");
		//----- Implementamos la edicion de asociado
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaAscAsociado asociado = (CtaAscAsociado)item;
				SecPerPersona persona = asociado.getSecPerPersona();
				value = persona.getPerPrimerApellido();
				value = value + (isObjectNull(persona.getPerSegundoApellido())?"":(" "+persona.getPerSegundoApellido()));
				value = value  + ", " + persona.getPerPrimerNombre();
				value = value + (isObjectNull(persona.getPerSegundoNombre())?"":(" "+persona.getPerSegundoNombre()));		
				return value.toString();	
			}
		});
		return tableFacade.render();
	}
	
	public ActionForward forwardToViewAsociadoDpte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {//metodo que redirije a la vista
		//de la informacion de un dependient
			SecDppDepartamentoPaisDAO departamentoPaisDAO = new SecDppDepartamentoPaisDAO(getSessionHibernate(request));
			CtaTasTipoAsociadoDAO tipoAsociadoDAO = new CtaTasTipoAsociadoDAO(getSessionHibernate(request));
			CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
			CtaDomDomicilioDAO domicilioDAO = new CtaDomDomicilioDAO(getSessionHibernate(request));
			CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
			AsociadoForm asociadoForm = (AsociadoForm) form;
			
			List tDomList = domicilioDAO.findAll();
			request.setAttribute("tDomList", tDomList);
			
			List dppList = departamentoPaisDAO.findAll();
			request.setAttribute("dppList", dppList);
			
			List tasList = tipoAsociadoDAO.findAll();
			request.setAttribute("tasList", tasList);
			
			List estList = estadoDAO.findByProperty(TUS_CODIGO,"ACTIV");
			request.setAttribute("estList", estList);
		
			request.setAttribute(Constantes.ACCION_KEY, "/dependiente");
			CtaAscAsociado asociado = asociadoDAO.findById(asociadoForm.getAscId());
			CtaAscAsociado padre = asociadoDAO.findById(asociadoForm.getAscAsociadoPadre());
			request.setAttribute("asociadoPadre",padre.getSecPerPersona());
			asociadoForm.setAscAsociadoH(asociado);


				request.setAttribute("form", asociadoForm);
		return mapping.findForward("vistaDep");
	}
	
	public ActionForward forwardToNewDependiente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {//metodo que redirije a la
		//creacion de un dependiente
		SecDppDepartamentoPaisDAO departamentoPaisDAO = new SecDppDepartamentoPaisDAO(getSessionHibernate(request));
		CtaTasTipoAsociadoDAO tipoAsociadoDAO = new CtaTasTipoAsociadoDAO(getSessionHibernate(request));
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		CtaDomDomicilioDAO domicilioDAO = new CtaDomDomicilioDAO(getSessionHibernate(request));
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		AsociadoForm asociadoForm = (AsociadoForm) form;
		
		List tDomList = domicilioDAO.findAll();
		request.setAttribute("tDomList", tDomList);
		
		List dppList = departamentoPaisDAO.findAll();
		request.setAttribute("dppList", dppList);
		
		List<CtaTasTipoAsociado> tasList = new ArrayList<CtaTasTipoAsociado>();
		tasList.add(tipoAsociadoDAO.findById(5));
		tasList.add(tipoAsociadoDAO.findById(1));
		request.setAttribute("tasList", tasList);
		
		List estList = estadoDAO.findByProperty(TUS_CODIGO,"ACTIV");
		request.setAttribute("estList", estList);
	
		request.setAttribute(Constantes.ACCION_KEY, "/dependiente");
		CtaAscAsociado padre = asociadoDAO.findById(asociadoForm.getAscAsociadoPadre());
		//TRANSFIRIENDO DATOS PARA QUE AL CARGAR EL FORMULARIO APAREZCAN LOS DATOS 
		//DOMICILIARES DEL ASOCIADO PADRE
		SecPerPersona persona = padre.getSecPerPersona();
		asociadoForm.getSecPerPersona().setPerCalle(persona.getPerCalle());
		asociadoForm.getSecPerPersona().setPerColoniaBarrio(persona.getPerColoniaBarrio());
		asociadoForm.getSecPerPersona().setPerMunicipio(persona.getPerMunicipio());
		asociadoForm.getSecPerPersona().setPerCodigoPostal(persona.getPerCodigoPostal());
		asociadoForm.getSecPerPersona().setSecDppDepartamentoPais(persona.getSecDppDepartamentoPais());
		asociadoForm.setAscRentaDomicilio(padre.getAscRentaDomicilio());
		asociadoForm.setCtaDomDomicilio(padre.getCtaDomDomicilio());
		asociadoForm.setAscCodigoAsociado(asociadoDAO.generarCodigo());
		request.getSession().setAttribute("cuotaAfiliacion", parametrosDAO.findById("VALOR_INSCRIPCION").getParValorNumber());
		request.setAttribute("asociadoPadre",padre); //arreglar esto AHORA!!,DEBE CARGAR EL ASOCIADO NADA MAS!!
		//asociadoForm.setAscAsociadoPadre(asociadoForm.getAscId());
		request.setAttribute("form", asociadoForm);
		return mapping.findForward("dml");
	}
	
	 public ActionForward guardar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		 	ActionForward target = null;
		 	ActionErrors errors = new ActionErrors();
			AsociadoForm asociadoForm = (AsociadoForm)form;
			CtaCahCuentaAhorroDAO cuentaAhorroDAO = new CtaCahCuentaAhorroDAO(getSessionHibernate(request));
			CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
			SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
			CtaNotNotasDAO notasDAO = new CtaNotNotasDAO(getSessionHibernate(request));
			CtaAscAsociado asociado = asociadoForm.getAscAsociadoH();
			SecPerPersona persona = asociado.getSecPerPersona();
			CtaNotNotas nota = asociado.getCtaNotNotas();
			CtaInaIngresosxasociadoDAO ingresosxasociadoDAO = new CtaInaIngresosxasociadoDAO(getSessionHibernate(request));
			SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
			SecIseInicioSesion iseInicioSesion = new SecIseInicioSesion();
			SecRolRolesDAO rolesDAO = new SecRolRolesDAO(getSessionHibernate(request));
			PwdAleatorio pwdAleatorio = new PwdAleatorio();
			CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
			SecCelCorreoElectronicoDAO correoElectronicoDAO = new SecCelCorreoElectronicoDAO(getSessionHibernate(request));
			SecCelCorreoElectronicoId correoElectronicoId = new SecCelCorreoElectronicoId();
			SecCelCorreoElectronico correoElectronicoH = new SecCelCorreoElectronico();
			Transaction tx = asociadoDAO.getSession().beginTransaction();
			CtaCasCuentaAsociado cuentaAsociado  = new CtaCasCuentaAsociado();
			CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
			CtaNotNotas nota2 = new CtaNotNotas();
			CtaCntCodigosAnterioresDAO codigosAnterioresDAO = new CtaCntCodigosAnterioresDAO(getSessionHibernate(request));
			CtaTahTipoAhorroDAO tipoAhorroDAO = new CtaTahTipoAhorroDAO(getSessionHibernate(request));
			try{
				if(!asociadoForm.isMdf()){//logica para almacenamiento de nuevos asociados	
					List<SecPerPersona>  lstPersonas = personaDAO.findByDuiOrNit(persona);
					List<SecCelCorreoElectronico> lstCorreo = null;
					lstCorreo = correoElectronicoDAO.findByProperty("id.celCorreoElectronico",asociadoForm.getCorreoElectronico());
					if(!lstPersonas.isEmpty()){
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.asc.personaRepetida"));
					}
					if(persona.getPerNit().trim().length() != 14){
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.persona.nitSize"));
					}
					if(lstCorreo.size() >0){
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.asc.correoRepetido"));
					}else{
						if(validateEmailPattern(asociadoForm.getCorreoElectronico())){
							errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.asc.correoFormatoInvalido"));
						}
					}
					if(asociado.getAscDirTrabajo().trim().equals("") || asociado.getAscDirTrabajo() == null){
						errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.asc.direccionTrabajoInvalidaDep"));
					}
					if(!errors.isEmpty()){
						saveMessages(request, errors);
						request.setAttribute(Constantes.ACCION_KEY, "/asociado");
						target = forwardToNewDependiente(mapping, asociadoForm, request, response);
					}else{
						
						persona.setPerId(personaDAO.generarId());
						persona.setPerEstado("A");
						persona.setSecSucSucursal(null);
						persona.setAudFechaCreacion(new Date());
						persona.setAudUsuarioCreacion(asociadoForm.getUsuarioConectado().getNombreUsuario());
						persona.setAudFechaModificacion(new Date());
						persona.setAudUsuarioModificacion(asociadoForm.getUsuarioConectado().getNombreUsuario());
						correoElectronicoId.setCelCorreoElectronico(asociadoForm.getCorreoElectronico());
						correoElectronicoId.setSecPerPersona(persona);
						correoElectronicoH.setId(correoElectronicoId);
						correoElectronicoH.setCelPrincipal("S");
						correoElectronicoH.setAudFechaCreacion(new Date());
						correoElectronicoH.setAudUsuarioCreacion(asociadoForm.getUsuarioConectado().getNombreUsuario());
						correoElectronicoH.setAudFechaModificacion(new Date());
						correoElectronicoH.setAudUsuarioModificacion(asociadoForm.getUsuarioConectado().getNombreUsuario());
						persona.getSecCelCorreoElectronicos().add(correoElectronicoH);
						personaDAO.save(persona);
						asociado.setSecPerPersona(persona);
						nota.setNotNota("Ingreso a cooperativa");
						nota.setNotId(notasDAO.nextId());
						nota.setNotFecha(new Date());
						notasDAO.save(nota);
						asociado.setCtaNotNotas(nota);
						asociado.setAscId(asociadoDAO.generarId());
						asociado.setSecPerPersona(persona);
						asociado.setAscIngresoCoope(new Date());
						asociado.getCtrEstEstado().setEstId(0);//setea estado de activo al asociado
						asociado.setEstId(0);
						asociado.setCtaDptDepartamentoTrabajo(null);
						asociado.setCtaTcoTipoContrato(null);
						if(asociadoForm.getAscAsociadoH().getAscPagaraPadre()==null 
								|| asociadoForm.getAscAsociadoH().getAscPagaraPadre().trim().equals("")){
							asociado.setAscPagaraPadre("N");
						}
						asociado.setAscCodigoAsociado(asociadoDAO.generarCodigo());
						asociado.setAscId(asociadoDAO.generarId());
						asociadoDAO.save(asociado);
						if(asociado.getAscPagaraPadre().equals("S")){
							setearYAPadre(asociado.getAscAsociadoPadre(),request);
						}
						CtaInaIngresosxasociado ingresosxasociado = new CtaInaIngresosxasociado();
						nota2.setNotId(notasDAO.nextId());
						nota2.setNotFecha(new Date());
						nota2.setNotNota("Ingreso a cooperativa");
						ingresosxasociado.setInaId(ingresosxasociadoDAO.nextId());
						ingresosxasociado.setInaFechaIngreso(new Date());
						ingresosxasociado.setCtaAscAsociado(asociado);
						ingresosxasociado.setCtaNotNotas(nota2);
						ingresosxasociadoDAO.save(ingresosxasociado);
						String password = pwdAleatorio.getPasswordAleatorio(parametrosDAO.findById("PASS_SIZE").getParValorNumber().intValue());
						iseInicioSesion.setSecPerPersona(persona);
						iseInicioSesion.setIseContrasenia(Hasher.getHash(password.toUpperCase()));
						iseInicioSesion.setIseTipoSesion(Constantes.TIPO_SESSION_CLIENTE);
						iseInicioSesion.setIseNombreUsuario(asociado.getAscCodigoAsociado());
						iseInicioSesion.setSecRolRoles((SecRolRoles)(rolesDAO.findByRolTipoSesion("C").get(0)));
						iseInicioSesion.setAudFechaCreacion(new Date());
						iseInicioSesion.setAudFechaModificacion(new Date());
						iseInicioSesion.setAudUsuarioCreacion(asociadoForm.getUsuarioConectado().getNombreUsuario());
						iseInicioSesion.setAudUsuarioModificacion(asociadoForm.getUsuarioConectado().getNombreUsuario());
						iseInicioSesion.setIseFechaActivacion(new Date());
						iseInicioSesion.setIseVecesUtilizado(0);
						inicioSesionDAO.save(iseInicioSesion);
						//Creamos la cuenta principal
						CtaCahCuentaAhorro principal = new CtaCahCuentaAhorro();
						principal.setCahId(cuentaAhorroDAO.generarId("B"));
						principal.setCahSaldoActual(new Double(0.00));
						principal.setCahInteresAcumulado(new Double(0.00));
						principal.setCahCuota(0.00);
						principal.getCtaTahTipoAhorro().setTahId(5);//para que siempre se vaya a ahorro descuentos
						cuentaAhorroDAO.save(principal);
						cuentaAsociado = new CtaCasCuentaAsociado();
						cuentaAsociado.setCtaAscAsociado(asociado);
						cuentaAsociado.setCtaCahCuentaAhorro(principal);
						cuentaAsociado.setCasFechaApertura(new Date());
						cuentaAsociado.setCasValorApertura(0.00);
						cuentaAsociado.setCtaPrePrestamo(null);
						cuentaAsociado.setCtaCbaCuentaBancaria(null);
						cuentaAsociado.setCtaSegSeguros(null);
						cuentaAsociado.setCasPrincipal("S");
						cuentaAsociado.getCtrEstEstado().setEstId(9);
						cuentaAsociado.setCtaPxtPersonaExterna(null);
						cuentaAsociadoDAO.save(cuentaAsociado);
						tx.commit();
						if(asociado.getAscPagoIngreso().equals("S") && !asociado.getAscPagaraPadre().equals("S")){
							PartidaAutomatica partidaAutomatica =  new PartidaAutomatica();
							partidaAutomatica.crearPartidaAutomatica("5", parametrosDAO.findById("VALOR_INSCRIPCION").getParValorNumber(), asociadoForm.getUsuarioConectado().getNombreUsuario(), 1, null, null, null,request);
							asociadoForm.setEntrada(true);
							target = forwardToNewDependiente(mapping, form, request, response);
						}else{
							request.setAttribute("ASC_ID", asociado.getAscId());
							target= mapping.findForward("aperturaAportacion");
						}
						createNewThreadEmail(password, persona, asociadoForm.getUsuarioConectado(), errors, iseInicioSesion.getIseNombreUsuario(),request);
					}
				}else{//logica de modificacion de asociados
					persona.setSecSucSucursal(null);
					persona.setAudFechaModificacion(new Date());
					persona.setAudUsuarioModificacion(asociadoForm.getUsuarioConectado().getNombreUsuario());
					personaDAO.merge(persona);
					if(!nota.getNotNota().equals(asociadoForm.getNotaTemp())){
						nota.setNotFecha(new Date());
						nota.setNotNota(asociadoForm.getNotaTemp());
						notasDAO.merge(nota);
					}
					asociado.setSecPerPersona(persona);
					asociadoDAO.merge(asociado);
					if(asociado.getAscPagaraPadre().equals("S")){
						setearYAPadre(asociado.getAscAsociadoPadre(),request);
					}
					/*if(!asociado.getAscCodigo().equals(asociadoForm.getViejoCodigo())){
						CtaCntCodigosAnteriores codigoViejo = codigosAnterioresDAO.findLastCode(asociado.getAscId());
						codigoViejo.setCntFechaFin(new Date());
						codigosAnterioresDAO.merge(codigoViejo);
						CtaCntCodigosAnteriores codigosAnterioresNuevo = new CtaCntCodigosAnteriores();
						codigosAnterioresNuevo.setCntCod(asociado.getAscCodigo());
						codigosAnterioresNuevo.setCntFechaIni(new Date());
						codigosAnterioresNuevo.setCtaAscAsociado(asociado);
						codigosAnterioresDAO.save(codigosAnterioresNuevo);
					}*/
					tx.commit();
					target = lista(mapping, new AsociadoForm(), request, response);
				}
			}catch(Exception e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				asociadoDAO.getSession().flush();
				asociadoDAO.getSession().clear();
				
			}
			return target;
		}
	 
	 private void setearYAPadre(String ascAsociadoPadre,HttpServletRequest request) {
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado padre = asociadoDAO.findById(ascAsociadoPadre);
		padre.setAscPagaraPadre("Y");
		
		Transaction tx = asociadoDAO.getSession().beginTransaction();
		asociadoDAO.merge(padre);
		tx.commit();
		asociadoDAO.getSession().flush();
		asociadoDAO.getSession().clear();
	}

	private boolean validateEmailPattern(String email){
		 String EMAIL_REGEXP = "/^[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\\.-][a-z0-9]+)*)+\\.[a-z]{2,}$/i";
		 Perl5Util perl5Util = new Perl5Util();
		 boolean respuesta = false;
		 if(!perl5Util.match(EMAIL_REGEXP, email)) {
			 respuesta  =  true;
		 }
		 return respuesta;
	}

 
 private void createNewThreadEmail(String password,
			SecPerPersona modelPerPersona, UsuarioConectado attribute,ActionErrors errors,String login,HttpServletRequest request) {
		String msg = null;
		String subject = null;
		int puerto = 0;
		String ipServidor = null;
		String correoAdmin = null;
		String correoCliente = null;
		
		if(password != null && modelPerPersona!=null && attribute != null &&  errors != null){
			CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
			SecCelCorreoElectronicoDAO secCelCorreoElectronicoDAO = new SecCelCorreoElectronicoDAO(getSessionHibernate(request));
			Transaction tx = parametrosDAO.getSession().beginTransaction();
			try {
				msg = parametrosDAO.findById("MAIL_MENSAJE").getParValorString();
				subject = parametrosDAO.findById("MAIL_SUBJECT").getParValorString();
				puerto =  parametrosDAO.findById("MAIL_PUERTO").getParValorNumber().intValue();
				ipServidor =  parametrosDAO.findById("MAIL_SERVIDOR").getParValorString();
				correoAdmin = secCelCorreoElectronicoDAO.findCorreoForConectedUser(attribute.getNombreUsuario());
				correoCliente = secCelCorreoElectronicoDAO.findCorreoByPerId(modelPerPersona.getPerId());
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.session.email"));
			}finally{
				 parametrosDAO.getSession().flush();
				 parametrosDAO.getSession().clear();
				 
			}
			msg = msg + "\nUsuario: " + login + "\nPassword: " + password;
			if(msg!=null && subject != null && puerto != 0 && ipServidor != null && correoAdmin!= null
					&& correoCliente!=null){
					 new ThreadEmail(
							 ipServidor,puerto,correoAdmin
							 ,new String[]{correoAdmin,correoCliente},msg,
							 subject,new StringBuilder().append(modelPerPersona.getPerPrimerNombre())
							 .append("-").append(modelPerPersona.getPerPrimerApellido()).toString()
							 ,"Administrador"
					).start();
				
			}else{
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.session.email"));
			}
		}else{
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.session.email"));
		}
	}
 
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		saveMessages(request, errors);
		request.setAttribute("form", (AsociadoForm) form);
		return forwardToNewDependiente(mapping, form, request, response);
	}
	
	 public ActionForward regresar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {// se utiliza para regresar 
		 //de la vista de la informacion del dependiente a la del asociado
		 AsociadoForm  asociadoForm = (AsociadoForm) form;
		 return lista(mapping, asociadoForm, request, response);
	 }
	 
	 public ActionForward regresarToAsociados(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		 //se utiliza para regresar a la lista de asociados
		 request.setAttribute(Constantes.ACCION_KEY, "/dependiente");
		 return mapping.findForward("toAsociados");
	 }
	 
		public ActionForward forwardToAport(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			AsociadoForm asociadoForm = (AsociadoForm) form;
			//request.setAttribute("ASC_ID", asociadoForm.getAscId());
			request.getSession().setAttribute("asociadoId", asociadoForm.getAscId());
			return  mapping.findForward("aperturaAportacion");
		}
		
		public ActionForward generarComprobante(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
			AsociadoForm asociadoForm = (AsociadoForm) form;
			try{
				Date fechaActual = new Date();
				response.setHeader("Cache-Control","private");
				response.setHeader("Pragma", "Cache");
				String pathReporte = "";//ruta reporte
				String nombreReporte = "";
				ExportReport exportar = null;
				ReportFile reporte = new ReportFile();
				ServletContext servletContext = getServlet().getServletContext();
				
				nombreReporte = "comprobante_asociado";
				pathReporte = servletContext
				.getRealPath("/reportesOtros/facturacion/comprobante/"+nombreReporte+".jasper");
				reporte.addParameter("ascId",asociadoForm.getAscId());
				reporte.addParameter("valorIngreso",parametrosDAO.findById("VALOR_INSCRIPCION").getParValorNumber());
				SecIseInicioSesionDAO sesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
				SecIseInicioSesion sesion = sesionDAO.findById(asociadoForm.getUsuarioConectado().getNombreUsuario());
				reporte.addParameter("sucNombre",sesion.getSecPerPersona().getSecSucSucursal().getSucNombre());
				
				reporte.setPathJasper(pathReporte);
				exportar = new ExportReport(reporte);//reporte a exportar
				
				//Conexion jdbc normal
				String jdbcDriver = "com.mysql.jdbc.Driver";
				Class.forName(jdbcDriver);
				String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
				String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
				String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
				Connection con = DriverManager.getConnection(url, user, pass);
				byte[] repCompilado = exportar.exportReportPDF(con);				
				response.setContentType("application/pdf");
				response.setContentLength(repCompilado.length);
				response.setHeader("content-Disposition", "attachment;filename="+nombreReporte+"-"+fechaActual.getTime()+".pdf");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(repCompilado, 0, repCompilado.length);
				outputStream.flush();
				outputStream.close();
				
			}catch(Exception e){
				e.printStackTrace();
				log.error("Se produjo un error al tratar de generar el reporte...", e);
				System.out.println("Se produjo un error al tratar de generar el reporte...\n");
			}
			return null;
		}
	 
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.ascDependiente.lista","lista");
		map.put("cmd.asc.regresarListaDep","regresar");
		map.put("cmd.asc.forwardToViewAsociadoDpte","forwardToViewAsociadoDpte");
		map.put("cmd.asc.regresarToAsociados","regresarToAsociados");
		map.put("cmd.asc.nuevoDept","forwardToNewDependiente");
		map.put("cmd.ascDependiente.guardar","guardar");
		map.put("cmd.asc.redirectInvalidData","redirectInvalidData");
		map.put("cmd.asc.forwardToAport", "forwardToAport");
		map.put("cmd.asc.generarComprobante", "generarComprobante");
		return map;
	}
}