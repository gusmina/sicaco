/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.contabilidad.struts.action;

import java.io.IOException;
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
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.contabilidad.struts.form.RelacionModuloContaForm;
import com.cetia.sicaco.hibernate.ConCpaConceptoPartida;
import com.cetia.sicaco.hibernate.ConCpaConceptoPartidaDAO;
import com.cetia.sicaco.hibernate.ConCueCuentaDAO;
import com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontable;
import com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontableDAO;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.CtrEstEstadoDAO;
import com.cetia.sicaco.hibernate.FacFusFacturaUso;
import com.cetia.sicaco.hibernate.FacFusFacturaUsoDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 11-30-2008
 * 
 * XDoclet definition:
 * @struts.action path="/relacionInventario" name="relacionModuloContaForm" parameter="accion" scope="request"
 * @struts.action-forward name="lista" path="pagina-lista.contabilidad.unionInventario"
 */


/**
 * LOS PARAMETROS PARA UNION DE INVENTARIO SE RIGEN POR EL SIGUIENTE ORDEN
 * PARAMETRO[0]=MODULO AL QUE PERTENECE (CUENTA CORRIENTE,FACTURACION , ETC.)
 * PARAMETRO[1]=TIPO DE FACTURA(CONSUMIDOR FINAL,VENTA A CONTRIBUYENTES)
 * PARAMETRO[2]=USO DE LA FACTURA (FUS_ID)
 * PARAMETRO[3]=ESTADO DE LA FACTURA (EST_ID)
 * PARAMETRO[4]=NO RELACIONA NINGUN VALOR(0),EL VALOR TOTAL DE LA FACTURA(1),EL VALOR DE LA FACTURA SIN IVA(2), EL VALOR DEL IVA DE LA FACTURA(3)
 * PARAMETRO[5]=TIPO DE PAGO EFECTIVO (E) O CREDITO(C)
 * PARAMETRO[6]=RELACIONA EL COSTO(1) O NO RELACIONA EL COSTO(0) CUANDO DEBE DESCARGARSE DEL INVENTARIO 
 * 
 * PARAMETRO[7]=ES CLIENTE EXCENTO (1) O NO (0)
 * PARAMETRO[8]=CLIENTE 
 *  
 * */

public class RelacionInventarioAction extends DMLAction {
	
	private static final String TABLA_ID = "conMxcModuloxCuentaContable";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RelacionModuloContaForm relacionForm = (RelacionModuloContaForm) form;
		ConMxcModuloxCuentacontableDAO moduloxCuentacontableDAO  = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
 		ConCpaConceptoPartidaDAO conceptoPartidaDAO = new ConCpaConceptoPartidaDAO(getSessionHibernate(request));
		List cpaList = moduloxCuentacontableDAO.findByModulo(2);
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(cpaList);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
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
		//reseteamos el form a los valores originales
		relacionForm.setParametro(1,"-1");
		relacionForm.setParametro(3,"-1");
		relacionForm.setParametro(5,"-1");
		relacionForm.setParametro(4,"0");
		relacionForm.getConCueCuenta().setCueId(-1);
		relacionForm.setConCpaConceptoPartida(new ConCpaConceptoPartida());
		relacionForm.getConCpaConceptoPartida().setCpaId(-2);
		relacionForm.setCxcCargoAbono((byte)-1);
        //----- Variables de configuracion
		request.setAttribute("conceptos", conceptoPartidaDAO.findByCpaDescripcionConcepto(1));
		request.setAttribute("cuentasContables", cuentaDAO.findByCuePosteable(0));
		request.setAttribute("facUsoList", facturaUsoDAO.findAll());
		request.setAttribute("form", form);
		request.setAttribute(Constantes.ACCION_KEY, "/relacionInventario");
		return mapping.findForward("lista");
	}
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("cueId","cxcParametrosUnion","cxcId","cxcCargoAbono","cxaConceptoExtra");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.cxc.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("cueId");
		nombreColumna.setTitleKey("tbl.cxc.cuentaContable");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				ConMxcModuloxCuentacontable cmx = (ConMxcModuloxCuentacontable) item;
				return cmx.getConCueCuenta().getCueCodigoCuenta() + " / " + (cmx.getConCpaConceptoPartida()!=null?cmx.getConCpaConceptoPartida().getCpaConcepto():cmx.getCxaConceptoExtra());
			}
		});
		
		nombreColumna = row.getColumn("cxcParametrosUnion");
		nombreColumna.setTitleKey("tbl.cxi.tipoFactura");

		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
				ConMxcModuloxCuentacontable modulo = (ConMxcModuloxCuentacontable) item;
				String[] args = modulo.getCxcParametrosUnion().split(";");
				String value = null;
				String var = args[1];
				/** a.	 CO  � consumidor final
					b.	 CR  � cr�dito fiscal
					c.	 VC  � ventas a contribuyentes
					d.	 ND  � nota de debito
					e.	 NC  � nota de cr�dito
				*/
				if(var.equals("CO")) value = getResources(request).getMessage("lbl.mxinv.consumidorFinal");
				if(var.equals("CR")) value = getResources(request).getMessage("lbl.mxinv.creditoFiscal");
				if(var.equals("VC")) value = getResources(request).getMessage("lbl.mxinv.ventaContribuyentes");
				if(var.equals("ND")) value = getResources(request).getMessage("lbl.mxinv.notaDebito");
				if(var.equals("NC")) value = getResources(request).getMessage("lbl.mxinv.notaCredito");
				FacFusFacturaUso uso = facturaUsoDAO.findById(Integer
						.parseInt(args[2]));
				value += " / " + uso.getFusNombre();
				return value;
			}
		});
		
		
		
		nombreColumna = row.getColumn("cxcId");
		nombreColumna.setTitleKey("tbl.cxi.misc");

		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
				ConMxcModuloxCuentacontable modulo = (ConMxcModuloxCuentacontable) item;
				String[] args = modulo.getCxcParametrosUnion().split(";");
				CtrEstEstado estado = estadoDAO.findById(new Integer(args[3]));
				String value = estado.getEstNombre();
				value += (args[5].equals("E")?" / Contado / ":" / Credito / ");
				if(args[4].equals("1")){
					value += "Valor Total Factura / ";
				}
				if(args[4].equals("2")){
					value += "Valor Sin IVA de Factura / ";
				}
				if(args[4].equals("3")){
					value += "Valor IVA de Factura / ";
				}
				value += (args[6].equals("1")?"Si":"No");
				return value;
			}
		});	
		
		nombreColumna = row.getColumn("cxcCargoAbono");
		nombreColumna.setTitleKey("tbl.cxc.cargoAbono");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
			String value = null;
			ConMxcModuloxCuentacontable modulo = (ConMxcModuloxCuentacontable) item;
			if(modulo.getCxcCargoAbono() == 1) value = "Cargo";
			else value = "Abono";
			return value;
			}
		});
		
		nombreColumna = row.getColumn("cxaConceptoExtra");
		nombreColumna.setTitleKey("tbl.cxc.opcion");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				ConMxcModuloxCuentacontable modulo = (ConMxcModuloxCuentacontable) item;
				//---- Generar link para entrar a edicion
				/*String link = tableFacade.getWebContext().getContextPath();
				link += "/contabilidad/relacionInventario.do?cxcId="+modulo.getCxcId()+"&accion=eliminar";
				html.a().href().quote().append(link).quote().append("class=\"linkEliminar\"").title("Eliminar").close();
				//html.append("Eliminar");
				html.aEnd();				
				return html.toString();	*/
				String eliminar = "Eliminar";
				html.a().onclick("handlerDeleteButton('"+ modulo.getCxcId() +"');").append("class=\"linkEliminar\"").title(eliminar).id("deleteButtonId").close();
				html.aEnd();
				return html.toString();
			}
		});
		return tableFacade.render();
	}

	
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		ConMxcModuloxCuentacontableDAO mxcModuloxCuentacontableDAO = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		RelacionModuloContaForm relacionForm = (RelacionModuloContaForm) form;
		Transaction tx = mxcModuloxCuentacontableDAO.getSession().beginTransaction();
		errors = validarGuardado(relacionForm);
		
		String parametros = "2;"+relacionForm.getParametro(1)+";"+relacionForm.getParametro(2)+";"+relacionForm.getParametro(3)+";"+relacionForm.getParametro(4)+";"+relacionForm.getParametro(5)+";"+relacionForm.getParametro(6);
		if (relacionForm.getParametro(1).equals("CO")|| relacionForm.getParametro(1).equals("VC")){
			//parametros+=";"+relacionForm.getParametro(7);
		}
		if(!mxcModuloxCuentacontableDAO.findByCuentaParametros(relacionForm.getConCueCuenta().getCueId(), parametros).isEmpty()){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.relacionRepetida"));
		}
			if(errors.isEmpty()){
				try{
				if(relacionForm.getConCpaConceptoPartida().getCpaId() == -1) relacionForm.setConCpaConceptoPartida(null);
				relacionForm.getModuloxCuentacontable().setCxcParametrosUnion(parametros);
				relacionForm.setCxcFechaRelacion(new Date());
				mxcModuloxCuentacontableDAO.save(relacionForm.getModuloxCuentacontable());
				tx.commit();
				}catch (Exception e) {
					tx.rollback();
					e.printStackTrace();
				}finally{
					mxcModuloxCuentacontableDAO.getSession().flush();
					mxcModuloxCuentacontableDAO.getSession().clear();
					
				}
			}
		saveMessages(request, errors);
		return lista(mapping, form, request, response);
	}
	
	private ActionErrors validarGuardado(RelacionModuloContaForm relacionForm){
		ActionErrors errors = new ActionErrors();
		if(relacionForm.getParametro(1).equals("-1") || relacionForm.getParametro(2).equals("-1")){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.tipoFacturaRequired"));
		}
		if(relacionForm.getParametro(4).equals("-1")){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.valorFacturaRequired"));
		}
		if(relacionForm.getParametro(3).equals("-1")){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.estadoFacturaRequired"));
		}
		if(relacionForm.getParametro(5).equals("-1")){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.tipoPagoRequired"));
		}
		if(relacionForm.getConCpaConceptoPartida().getCpaId() == -2){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.conceptoRequired"));
		}else{
			if(relacionForm.getConCpaConceptoPartida().getCpaId() == -1 && relacionForm.getCxaConceptoExtra().trim().equals("")){//validamos que el otro concepto no sea vacio
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.conceptoRequired"));
			}
		}
		if(relacionForm.getConCueCuenta().getCueId() == -1){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.cuentaContableRequired"));
		}
		if(relacionForm.getCxcCargoAbono() == -1){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.accionRequired"));
		}
		return errors;
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ConMxcModuloxCuentacontableDAO mxcModuloxCuentacontableDAO = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		RelacionModuloContaForm relacionForm = (RelacionModuloContaForm) form;
		Transaction tx = mxcModuloxCuentacontableDAO.getSession().beginTransaction();
		try{
			mxcModuloxCuentacontableDAO.delete(mxcModuloxCuentacontableDAO.findById(relacionForm.getCxcId()));
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally{
			mxcModuloxCuentacontableDAO.getSession().flush();
			mxcModuloxCuentacontableDAO.getSession().clear();
			
		}
		return lista(mapping, form, request, response);
	}
	
	public ActionForward findData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		RelacionModuloContaForm moduloContaForm = (RelacionModuloContaForm) form;
		String listaResponse = "";
		try {
			 	listaResponse= construirLista(facturaUsoDAO.findByDosId(moduloContaForm.getId1(), moduloContaForm.getId2()));
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
	
	private String construirLista(List lista){
		String html = "<select class=\"obligatorio\"  name=\"parametro[2]\">";
		if(lista != null && !lista.isEmpty()){
								Iterator<FacFusFacturaUso> it2 = lista.iterator();
								while(it2.hasNext()){
										FacFusFacturaUso fuso = (FacFusFacturaUso) it2.next();
										html += "<option value=\""+ fuso.getFusId()+"\">"+fuso.getFusNombre()+"</option>";
								}
		}else{
			html+="<option value=\"0\">- No hay uso asociado -</option>";
		}
		html+= "</select>";
		return html;
	}
	
	protected Map getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cmd.mxinv.lista","lista");
		map.put("cmd.mxinv.guardar","guardar");
		map.put("cmd.mxinv.eliminar","eliminar");
		map.put("cmd.mxinv.cancelar","lista");
		map.put("cmd.mxinv.findData","findData");
		return map;
	}
}