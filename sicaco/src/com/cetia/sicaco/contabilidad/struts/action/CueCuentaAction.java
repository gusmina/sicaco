/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.contabilidad.struts.action;

import java.io.IOException;
import java.text.DecimalFormat;
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

import com.cetia.sicaco.contabilidad.struts.form.CueCuentaForm;
import com.cetia.sicaco.cuentaCorriente.struts.form.TipoSeguroForm;
import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConCueCuentaDAO;
import com.cetia.sicaco.hibernate.ConDpaDetallePartidaDAO;
import com.cetia.sicaco.hibernate.ConEscEstructuraCuentas;
import com.cetia.sicaco.hibernate.ConEscEstructuraCuentasDAO;
import com.cetia.sicaco.hibernate.ConTicTipoCuenta;
import com.cetia.sicaco.hibernate.ConTicTipoCuentaDAO;
import com.cetia.sicaco.hibernate.CtaTisTipoSeguro;
import com.cetia.sicaco.hibernate.CtaTisTipoSeguroDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.Format;

/** 
 * MyEclipse Struts
 * Creation date: 11-18-2008
 * 
 * XDoclet definition:
 * @struts.action path="//cueCuenta" name="cueCuentaForm" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="lista"
 * @struts.action-forward name="redirectInvalidData" path="redirectInvalidData" redirect="true"
 */
public class CueCuentaAction extends DMLAction {

	public String TABLA_ID = "conCueCuenta";
	private String LIST_PLAN_MES="lstPlmPlanMes";

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		List lst = cuentaDAO.findAll();
		
		ConTicTipoCuentaDAO tipoCuentaDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		List lstTic = tipoCuentaDAO.findAll();
		request.setAttribute("lstTic", lstTic);
		
		List lstCues = cuentaDAO.findAllParent();
		request.setAttribute("lstCues", lstCues);
		
		cuentaForm.setCueCodCue(cuentaDAO.getNextByNivel(1,""));

		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);

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
		request.setAttribute("form", cuentaForm);
		request.setAttribute("edit", 0);
		request.setAttribute(Constantes.ACCION_KEY, "/cueCuenta");
		return mapping.findForward("lista");
	}

	//---- metodo que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("cueCodigoCuenta", "cueNombre","cueSaldoActual",
				"conTicTipoCuenta.ticNombre", "cuePosteable", "cueEstado");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.cue.caption");

		Row row = table.getRow();
		Column nombreColumna = row.getColumn("cueCodigoCuenta");
		nombreColumna.setTitleKey("tbl.cue.cueCodigoCuenta");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				ConCueCuenta cuenta = (ConCueCuenta)item;

				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/contabilidad/cueCuenta.do?accion=cargarDatos&cueId=" + cuenta.getCueId();
				html.a().href(link).close();
				html.append(value);
				html.aEnd();				
				return html.toString();		
			}
		});

		nombreColumna = row.getColumn("cueNombre");
		nombreColumna.setTitleKey("tbl.cue.cueNombre");
		
		nombreColumna = row.getColumn("cueSaldoActual");
		nombreColumna.setTitleKey("tbl.cue.cueSaldoActual");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				return Format.formatDinero(value);
			}
		});
		
		nombreColumna = row.getColumn("conTicTipoCuenta.ticNombre");
		nombreColumna.setTitleKey("tbl.cue.ctaTicTipoCuenta.ticNombre");
		
		nombreColumna = row.getColumn("cuePosteable");
		nombreColumna.setTitleKey("tbl.cue.cuePosteable");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				ConCueCuenta cuenta = (ConCueCuenta)item;
				if(cuenta.getCuePosteable()==0){
					HtmlBuilder html = new HtmlBuilder();
					html.append("S&iacute;");
					value = html.toString();
				}else{
					value = "No";
				}
				return value;
			}

		});
		
		nombreColumna = row.getColumn("cueEstado");
		nombreColumna.setTitleKey("tbl.cue.cueEstado");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				ConCueCuenta cuenta = (ConCueCuenta)item;
				if(cuenta.getCueEstado()==1){
					value = "Activa";
				}else{
					value = "Inactiva";
				}
				return value;
			}

		});

		return tableFacade.render();
	}

	//---- metodo que genera los exports, el formato que tendran
	private void export(final TableFacade tableFacade) {
		tableFacade.setColumnProperties("cueCodigoCuenta", "cueNombre",
				"conTicTipoCuenta.ticNombre","cuePosteable");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.cue.caption");

		Row row = table.getRow();
		Column nombreColumna = row.getColumn("cueCodigoCuenta");
		nombreColumna.setTitleKey("tbl.cue.cueCodigoCuenta");;

		nombreColumna = row.getColumn("cueNombre");
		nombreColumna.setTitleKey("tbl.cue.cueNombre");

		nombreColumna = row.getColumn("conTicTipoCuenta.ticNombre");
		nombreColumna.setTitleKey("tbl.cue.ctaTicTipoCuenta.ticNombre");
		
		nombreColumna = row.getColumn("cuePosteable");
		nombreColumna.setTitleKey("tbl.cue.cuePosteable");
		
				
		tableFacade.render();
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		if(cuentaForm.getCueNombre() == null | cuentaForm.getCueNombre().trim().equals("")){
			mensajes("error.cue.nombreVacio", request);
			return lista(mapping, cuentaForm, request, response);
		}
		if(cuentaForm.getCueTipoCuenta() == 2){/*son las de reserva*/
			Double porcentajeActual = cuentaDAO.findPorcentajeActual().doubleValue();
			if(porcentajeActual + cuentaForm.getCuePorcentaje() > 100){
				mensajes("error.cue.porcentajeSobrepasado", request);
				return lista(mapping, cuentaForm, request, response);
			}
		}
		cuentaForm.setUnionString("");
		Transaction tx = cuentaDAO.getSession().beginTransaction();
		try{
			if(cuentaForm.getCueId() != null && cuentaForm.getCp() == -1 && cuentaForm.getCuentaPadre() != -1){
				cuentaForm.setCp(Integer.valueOf(cuentaForm.getCueId()));
			}
			if(verificarCodCuenta(cuentaForm.getCueCodigoCuenta(), cuentaForm.getCueCodCue(),cuentaForm.getCp(),request)){
				ConCueCuenta cuenta = new ConCueCuenta();
				cuenta = cuentaForm.getConCueCuentaH();
				if(cuenta.getCueCodigoCuenta() == null){
					cuenta.setCueCodigoCuenta(cuentaForm.getCueCodCue());
				}else{
					cuenta.setCueCodigoCuenta(cuenta.getCueCodigoCuenta() + cuentaForm.getCueCodCue());
				}
				if(cuentaDAO.findByCueCodCue(cuenta.getCueCodigoCuenta(),1).size()>0){
					mensajes("error.cue.codigoRepetido", request);
					return lista(mapping, cuentaForm, request, response);
				}
				ConTicTipoCuentaDAO tipoCuentaDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
				cuenta.setConTicTipoCuenta(tipoCuentaDAO.findById(cuentaForm.getTipoId()));
				if(cuentaForm.getCp() != -1){
					cuenta.setConCueCuenta(cuentaDAO.findById(cuentaForm.getCp()));
				}
				cuenta.setCueEstado(1);
				cuenta.setCuePosteable(Byte.valueOf("0"));
				cuenta.setCueSaldoActual(0.0);
				cuenta.setCueTipoCuenta(cuentaForm.getRetroactiva().byteValue());
				cuentaDAO.save(cuenta);
				
				if(cuentaForm.getCp() != -1){
					ConCueCuenta cuentaPadre = cuentaDAO.findById(cuentaForm.getCp());
					if(cuentaPadre.getCuePosteable() == Byte.valueOf("0")){
						//Se agrega el saldo actual del padre en el hijo
						cuenta.setCueSaldoActual(cuentaPadre.getCueSaldoActual());
						cuentaDAO.merge(cuenta);
						tx.commit();
						
						//Aquí se agregarán los movimientos del padre al hijo
						ConDpaDetallePartidaDAO partidaDAO = new ConDpaDetallePartidaDAO(getSessionHibernate(request));
						partidaDAO.actualizaMovimientos(cuenta.getCueId(), cuentaPadre.getCueId());
					}
					cuentaPadre.setCuePosteable(Byte.valueOf("1"));
					cuentaDAO.merge(cuentaPadre);
				}
			}else{
				mensajes("error.cue.codigoNoConcuerda", request);
				return lista(mapping, cuentaForm, request, response);
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			tx.commit();
			cuentaDAO.getSession().flush();
			cuentaDAO.getSession().clear();
			
		}
		CueCuentaForm cuentaForm2 = new CueCuentaForm();
		return lista(mapping,cuentaForm2,request,response);
	} 

	private boolean verificarCodCuenta(String cueCodigoCuenta,
			String cueCodCue, int cp,HttpServletRequest request) {
		ConEscEstructuraCuentasDAO escDao = new ConEscEstructuraCuentasDAO(getSessionHibernate(request));
		if(cueCodCue == null || cueCodCue.trim().equals("")){
			return false;
		}
		int tamFinCod = cueCodCue.length();
		if(cueCodigoCuenta != null && !cueCodigoCuenta.trim().equals("")){
			int nivel = obtenerNivel(Integer.valueOf(cp),request);
			ConEscEstructuraCuentas esc = escDao.findById(nivel);
			if(tamFinCod > esc.getEscTamanho()){
				return false;
			}
			if(tamFinCod <= esc.getEscTamanho()){
				return true;
			}
		}else{
			ConEscEstructuraCuentas esc = escDao.findById(1);
			if(tamFinCod > esc.getEscTamanho()){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

	public ActionForward cargarDatos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		ConCueCuenta cuenta = cuentaDAO.findById(cuentaForm.getCueId());
		if(cuenta.getCueTipoCuenta()==2){
			request.setAttribute("reserva", 1);
		}
		cuentaForm.setCueNombreP(cuenta.getCueNombre());
		cuentaForm.setCueDescripcionP(cuenta.getCueDescripcion());
		cuentaForm.setCuePorcentajeP((double)cuenta.getCuePorcentaje());
		cuentaForm.setCueEstadoP(cuenta.getCueEstado());
		cuentaForm.setRetroactivaP(Integer.valueOf(cuenta.getCueTipoCuenta()));
		DecimalFormat df = new DecimalFormat("0.00");
		Double saldo = new Double(df.format(cuenta.getCueSaldoActual()));
		cuentaForm.setCueSaldoActual(saldo);
		cuentaForm.setTipoId(cuenta.getConTicTipoCuenta().getTicId());
		//cuentaForm.setTipoId();
		
		request.setAttribute("cueCuenta", cuenta);
		
		List lst = cuentaDAO.findAll();
		
		ConTicTipoCuentaDAO tipoCuentaDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		List lstTic = tipoCuentaDAO.findAll();
		request.setAttribute("lstTic", lstTic);
		
		List lstCues = cuentaDAO.findByProperty("conCueCuenta.cueId", cuentaForm.getCueId());
		request.setAttribute("lstCues", lstCues);
		
		int nivel = obtenerNivel(cuentaForm.getCueId(),request);
		
		cuentaForm.setCueCodCue(cuentaDAO.getNextByNivel(nivel,cuentaForm.getCueId().toString()));
		cuentaForm.setCueCodigoCuenta(cuenta.getCueCodigoCuenta());
		
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);

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
		request.setAttribute("form", cuentaForm);
		request.setAttribute("edit", 1);
		request.setAttribute(Constantes.ACCION_KEY, "/cueCuenta");
		return mapping.findForward("lista");
	}

	public void mensajes(String msg, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
		saveMessages(request, errors);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TipoSeguroForm tipoSeguroForm = (TipoSeguroForm)form;
		CtaTisTipoSeguroDAO tipoSeguroDAO = new CtaTisTipoSeguroDAO(getSessionHibernate(request));

		Transaction tx = tipoSeguroDAO.getSession().beginTransaction();
		try{
			CtaTisTipoSeguro tipoSeguro = tipoSeguroDAO.findById(tipoSeguroForm.getTisId2());
			if(tipoSeguro.getCtaSegSeguroses().size() > 0){
				mensajes("errors.tis.tieneHijos",request);
				return lista(mapping, form, request, response);
			}else{
				tipoSeguroDAO.delete(tipoSeguro);
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			tx.commit();
			tipoSeguroDAO.getSession().flush();
			tipoSeguroDAO.getSession().clear();
			tipoSeguroDAO.getSession().close();
		}
		mensajes("msg.tis.eliminacionExitosa", request);
		TipoSeguroForm tipoSeguroForm2 = new TipoSeguroForm();
		return lista(mapping,tipoSeguroForm2,request,response);
	} 
	
	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		ConCueCuenta cuenta = cuentaDAO.findById(cuentaForm.getCueId());
		if(cuenta.getCueNombre()==null | cuenta.getCueNombre().trim().equals("")){
			mensajes("error.cue.nombreVacio", request);
			return lista(mapping, cuentaForm, request, response);
		}
		if(cuenta.getCueTipoCuenta() == 2){
			Double porcentajeActual = cuentaDAO.findPorcentajeActual().doubleValue();
			if(porcentajeActual - cuenta.getCuePorcentaje() + cuentaForm.getCuePorcentajeP() > 100){
				mensajes("error.cue.porcentajeSobrepasado", request);
				return lista(mapping, cuentaForm, request, response);
			}
		}
		cuenta.setCueNombre(cuentaForm.getCueNombreP());
		cuenta.setCueEstado(cuentaForm.getCueEstadoP());
		cuenta.setCueDescripcion(cuentaForm.getCueDescripcionP());
		cuenta.setCueTipoCuenta(cuentaForm.getRetroactivaP().byteValue());
		
		ConTicTipoCuenta cttc = new ConTicTipoCuenta();
		ConTicTipoCuentaDAO cttcDAO = new ConTicTipoCuentaDAO(getSessionHibernate(request));
		cttc = cttcDAO.findById(cuentaForm.getTipoId());
		cuenta.setConTicTipoCuenta(cttc);
		if(cuenta.getCueTipoCuenta() == 2){
			cuenta.setCuePorcentaje((cuentaForm.getCuePorcentajeP()==null)?0:cuentaForm.getCuePorcentajeP().floatValue());
		}else{
			cuenta.setCuePorcentaje(new Float(0.0));
		}
		
		Transaction tx = cuentaDAO.getSession().beginTransaction();
		cuentaDAO.merge(cuenta);
		tx.commit();
		cuentaDAO.getSession().flush();
		cuentaDAO.getSession().clear();
		
		return lista(mapping, new CueCuentaForm(), request, response);
	} 
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		CueCuentaForm cuentaForm = new CueCuentaForm();
		return lista(mapping, cuentaForm, request, response);
	}
	
	public ActionForward cargarHijos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		List listaHijos = null;
		try {
			listaHijos = cuentaDAO.findByProperty("conCueCuenta.cueId", Math.abs(cuentaForm.getPadreId()));
			Boolean nulo = false;
			String listaResponse = "";
			if(listaHijos.size() < 1 || cuentaForm.getPadreId() < 0 ){
				HtmlBuilder builder = new HtmlBuilder();
				builder.table(0);
				builder.tr(0).close().td(0).colspan("4").close();
				builder.div().id("hijos" + cuentaForm.getNivel()).close().divEnd();
				builder.tdEnd().trEnd(0).tableEnd(0);
				listaResponse = builder.toString();
			}else{
				listaResponse = construirListaHijos(listaHijos, cuentaForm.getNivel() + 1,request, cuentaForm.getPadreId());
			}
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

	private String construirListaHijos(List listaHijos, int i, HttpServletRequest request, int padre) {
		HtmlBuilder builder = new HtmlBuilder();
		builder.table(0).close().tr(0).close().td(0).close();
		builder.append("<label>"+Format.rellenarIzquierdaV2("Hijo", "sub-", i-2) + "</label>");
		builder.tdEnd().td(0).close();
		builder.select().id("cuentaP" + i).styleClass("obligatorio");
		builder.onchange("guardaCP($('#cuentaP"+i+"').val());ajaxCallNormal('" + request.getContextPath() + "/contabilidad/cueCuenta.do','accion=cargarHijos&nivel=" + i + "&padreId='+$('#cuentaP"+ i +"').val(),'hijos"+i+"')");
		builder.close();
		builder.option().value("-" + padre ).close().append("---").optionEnd();
		for (Iterator iterator = listaHijos.iterator(); iterator.hasNext();) {
			ConCueCuenta cuenta = (ConCueCuenta) iterator.next();
			builder.option().value(cuenta.getCueId().toString()).close();
			builder.append(cuenta.getCueNombre()).optionEnd();
		}
		builder.selectEnd();
		builder.tdEnd().trEnd(0);
		builder.tr(0).close().td(0).colspan("4").close();
		builder.div().id("hijos" + i).close().divEnd();
		builder.tdEnd().trEnd(0).tableEnd(0);
		return builder.toString();
	}
	
	public ActionForward cargaCodigo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		List listaHijos = null;
		try {
			if(cuentaForm.getPadreId()>0){
				ConCueCuenta cuenta = cuentaDAO.findById(cuentaForm.getPadreId());
			
				HtmlBuilder html = new HtmlBuilder();
				html.input().type("text").styleClass("obligatorio").size("13").name("cueCodigoCuenta");
				html.value(cuenta.getCueCodigoCuenta()).id("cueCodigoCuentaId").readonly().end();
				response.getWriter().write(html.toString());
				response.getWriter().flush();
				response.getWriter().close();
			}
		} catch (RuntimeException e) {
			log.error("Error runtime", e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	public ActionForward cargaCodigoCue(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		List listaHijos = null;
		HtmlBuilder html = new HtmlBuilder();
		try {
			if(cuentaForm.getPadreId() < 0){
				html.input().type("text").styleClass("obligatorio").size("1").name("cueCodCue");
				int nivel = 1;
				ConEscEstructuraCuentasDAO escDao = new ConEscEstructuraCuentasDAO(getSessionHibernate(request));
				html.maxlength(escDao.findById(nivel).getEscTamanho().toString());
				html.value(cuentaDAO.getNextByNivel(nivel,""+cuentaForm.getPadreId())).id("cueCodCueId").end();
			}else{
				ConCueCuenta cuenta = cuentaDAO.findById(cuentaForm.getPadreId());
				html.input().type("text").styleClass("obligatorio").size("1").name("cueCodCue");
				int nivel = obtenerNivel(cuentaForm.getPadreId(),request);
				ConEscEstructuraCuentasDAO escDao = new ConEscEstructuraCuentasDAO(getSessionHibernate(request));
				html.maxlength(escDao.findById(nivel).getEscTamanho().toString());
				html.value(cuentaDAO.getNextByNivel(nivel,""+cuentaForm.getPadreId())).id("cueCodCueId").end();
			}
			response.getWriter().write(html.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (RuntimeException e) {
			log.error("Error runtime", e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	private int obtenerNivel(int padreId,HttpServletRequest request) {
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		ConCueCuenta cuenta = cuentaDAO.findById(padreId);
		int tamPadre = cuenta.getCueCodigoCuenta().length();
		ConEscEstructuraCuentasDAO escDao = new ConEscEstructuraCuentasDAO(getSessionHibernate(request));
		List estructura = escDao.findAll();
		int sum = 0;
		int nivel = 1;
		for (Iterator iterator = estructura.iterator(); iterator.hasNext();) {
			ConEscEstructuraCuentas esc = (ConEscEstructuraCuentas) iterator.next();
			sum += esc.getEscTamanho();
			if(tamPadre > sum){
				nivel ++;
			}
		}
		return nivel + 1;
	}
	
	public ActionForward muestraPorcentaje(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CueCuentaForm cuentaForm = (CueCuentaForm)form;
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		HtmlBuilder builder = new HtmlBuilder();
		if(cuentaForm.getTipoId2() == 2){
			try {
				builder.append("<label>Porcentaje</label>:");
				builder.input().type("text").id("cuePorcentajeId").styleClass("obligatorio").size("15");
				builder.maxlength("15").value("0.00").name("cuePorcentaje").close();
				builder.span().close().append("<bean:message key=\"msg.obligatorio\"/>").spanEnd();
				response.getWriter().write(builder.toString());
				response.getWriter().flush();
				response.getWriter().close();
			} catch (RuntimeException e) {
				log.error("Error runtime", e);
			} catch (IOException e) {
				log.error(e);
			}
		}
		return null;
	}

	
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.cue.lista", "lista");
		map.put("cmd.cue.guardar", "guardar");
		map.put("cmd.cue.cargarHijos", "cargarHijos");
		map.put("cmd.cue.cargaCodigo", "cargaCodigo");
		map.put("cmd.cue.cargaCodigoCue", "cargaCodigoCue");
		map.put("cmd.cue.cargarDatos", "cargarDatos");
		map.put("cmd.cue.salvar", "salvar");
		map.put("cmd.cue.cancelar", "cancelar");
		map.put("cmd.cue.muestraPorcentaje", "muestraPorcentaje");
		return map;
	}
}