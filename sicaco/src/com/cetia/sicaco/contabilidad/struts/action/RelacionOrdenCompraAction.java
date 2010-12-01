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
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.InvProProveedorDAO;
import com.cetia.sicaco.hibernate.InvTprTipoProveedor;
import com.cetia.sicaco.hibernate.InvTprTipoProveedorDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 12-01-2008
 * 
 * XDoclet definition:
 * @struts.action path="/relacionOrdenCompra" name="relacionModuloContaForm" parameter="accion" scope="request"
 * @struts.action-forward name="lista" path="pagina-lista.contabilidad.unionOrdenPago"
 */

/**
 * LOS PARAMETROS PARA UNION DE ORDEN DE COMPRA SE RIGEN POR EL SIGUIENTE ORDEN
 * PARAMETRO[0] = MODULO AL QUE PERTENECE (CUENTA CORRIENTE,FACTURACION , ETC.)
 * PARAMETRO[1]= RELACIONA LAS ORDENES DE DONACION(1) O NO(0)
 * PARAMETRO[2]= ESTADO DE LA ORDEN DE COMPRA: PAGADA(P) O ANULADA(A)
 * PARAMETRO[3]=TIPO DE PROVEEDOR(TPR_ID)
 * PARAMETRO[4]=PROVEEDOR (ID_PROV)
 *  
 * */

public class RelacionOrdenCompraAction extends DMLAction {
	
private static final String TABLA_ID="conMxcModuloxCuentaContable";
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RelacionModuloContaForm relacionForm = (RelacionModuloContaForm) form;
		ConMxcModuloxCuentacontableDAO moduloxCuentacontableDAO  = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
 		ConCpaConceptoPartidaDAO conceptoPartidaDAO = new ConCpaConceptoPartidaDAO(getSessionHibernate(request));
		List cpaList = moduloxCuentacontableDAO.findByModulo(3);
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
		//reseteamos el form
		relacionForm.setParametro(2,"-1");
		relacionForm.setParametro(3,"-1");
		relacionForm.getConCueCuenta().setCueId(-1);
		relacionForm.setConCpaConceptoPartida(new ConCpaConceptoPartida());
		relacionForm.getConCpaConceptoPartida().setCpaId(-2);
		relacionForm.setCxcCargoAbono((byte)-1);
        //----- Variables de configuracion
		request.setAttribute("tiposProv", tipoProveedorDAO.findAll());
		request.setAttribute("conceptos", conceptoPartidaDAO.findByCpaDescripcionConcepto(1));
		request.setAttribute("cuentasContables", cuentaDAO.findByCuePosteable(0));
		request.setAttribute("form", form);
		request.setAttribute(Constantes.ACCION_KEY, "/relacionOrdenCompra");
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
		nombreColumna.setTitleKey("tbl.mxPag.proveedor");

		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				InvProProveedorDAO proProveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
				InvTprTipoProveedorDAO tipoProveedorDAO = new  InvTprTipoProveedorDAO(getSessionHibernate(request));
				ConMxcModuloxCuentacontable modulo = (ConMxcModuloxCuentacontable) item;
				String[] args = modulo.getCxcParametrosUnion().split(";");
				InvProProveedor proveedor = proProveedorDAO.findById(Integer.parseInt(args[4]));
				InvTprTipoProveedor tipoProveedor = tipoProveedorDAO.findById(Integer.parseInt(args[3]));
				return proveedor.getProNombre() + " / " + tipoProveedor.getTprNombre();
			}
		});
		
		nombreColumna = row.getColumn("cxcId");
		nombreColumna.setTitleKey("tbl.mxPag.misc");

		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				ConMxcModuloxCuentacontable modulo = (ConMxcModuloxCuentacontable) item;
				String[] args = modulo.getCxcParametrosUnion().split(";");
				String value = (args[2].equals("C")?"Cargada / ":"Anulada / ") + (args[1].equals("1")?"Si":"No");
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
				link += "/contabilidad/relacionOrdenCompra.do?cxcId="+modulo.getCxcId()+"&accion=eliminar";
				html.a().href().quote().append(link).quote().close();
				html.append("Eliminar");
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
		String parametros = "";
		ConMxcModuloxCuentacontableDAO mxcModuloxCuentacontableDAO = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		RelacionModuloContaForm relacionForm = (RelacionModuloContaForm) form;
		errors = validarGuardado(relacionForm);
		Transaction tx = mxcModuloxCuentacontableDAO.getSession().beginTransaction();
		parametros = "3;"+relacionForm.getParametro(1)+";"+relacionForm.getParametro(2)+";"+relacionForm.getParametro(3)+";"+relacionForm.getParametro(4);
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
		if(relacionForm.getParametro(3).equals("-1")){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.tipoProveedorNoSeleccionado"));
		}else{
			if(relacionForm.getParametro(4)== null || relacionForm.getParametro(4).length()==0 ){
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.proveedorNoSeleccionado"));
			}
		}
		if(relacionForm.getParametro(2).equals("-1")){
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.mxcc.estadoOrdenRequired"));
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
		InvProProveedorDAO proProveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		String html = "<label>No se encontraron proveedores del tipo seleccionado</label>";
		RelacionModuloContaForm moduloContaForm = (RelacionModuloContaForm) form;
		List proveedores = proProveedorDAO.findByProperty("invTprTipoProveedor.tprId", moduloContaForm.getParametro(3));
		if(!proveedores.isEmpty()){
			html = "<select class=\"obligatorio\" name=\"parametro[4]\" id=\"prov\">";
			Iterator<InvProProveedor> it = proveedores.iterator();
			while(it.hasNext()){
				InvProProveedor proveedor = it.next();
				html += "<option value=\""+proveedor.getProId()+"\">"+proveedor.getProNombre()+"</option>";
			}
			html+="</select>";
		}
		try{
				response.getWriter().write(html);
				response.getWriter().flush();
				response.getWriter().close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	protected Map getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cmd.mxComp.lista","lista");
		map.put("cmd.mxComp.guardar","guardar");
		map.put("cmd.mxComp.eliminar","eliminar");
		map.put("cmd.mxComp.cancelar","lista");
		map.put("cmd.mxComp.findData","findData");
		return map;
	}
}