/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cuentaCorriente.struts.action;

import java.util.HashMap;
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
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.cuentaCorriente.struts.form.PreCuentaBancariaForm;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancariaDAO;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTcuTipoCuentaDAO;
import com.cetia.sicaco.hibernate.CtrBanBancoDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 08-14-2008
 * 
 * XDoclet definition:
 * @struts.action path="/cuentaBancaria" name="cuentaBancariaForm" parameter="accion" scope="request"
 * @struts.action-forward name="lista" path="pagina-lista.cuentaCorriente.cuentasBancarias"
 */
public class PreCuentaBancariaAction extends DMLAction {
	
	public static final String TABLA_ID = "ctaCbaCuentaBancaria";

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaCbaCuentaBancariaDAO cuentaBancariaDAO = new CtaCbaCuentaBancariaDAO(getSessionHibernate(request));
		CtrBanBancoDAO bancoDAO = new CtrBanBancoDAO(getSessionHibernate(request));
		CtaTcuTipoCuentaDAO tipoCuentaDAO = new  CtaTcuTipoCuentaDAO(getSessionHibernate(request));
		PreCuentaBancariaForm cuentaBancariaForm = (PreCuentaBancariaForm) form;
		List lst = cuentaBancariaDAO.findByAsociado(cuentaBancariaForm.getAscId());
		List lBacs = bancoDAO.findAll();
		request.setAttribute("lBacs", lBacs);
		List lTipos = tipoCuentaDAO.findAll();
		request.setAttribute("lTipos", lTipos);
		request.setAttribute(Constantes.ACCION_KEY, "/preCuentaBancaria");
		//paginacion roberto
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID ,request );
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = cuentaBancariaDAO.getTotalRowCountbyAsociado(cuentaBancariaForm.getAscId());
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		lst = cuentaBancariaDAO.findByAsociado(cuentaBancariaForm.getAscId(), rowStart, rowEnd);
		tableFacade.setItems(lst);
		/*TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();*/
		if (limit.isExported()) {
        	//---- exporta la tabla
            export(tableFacade,request);
            return null; 
        } else {
        	//---- genera el html de la tabla para ser mostrada
            String html = html(tableFacade, request,cuentaBancariaForm.getPreId(),cuentaBancariaForm.getEstado());
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
		if(cuentaBancariaForm.isMdf()){
			cuentaBancariaForm.setCuentaBancariaH(cuentaBancariaDAO.findById(cuentaBancariaForm.getCuentaBancariaH().getCbaId()));
		}
		//----- Variables de configuracion
		request.setAttribute("form", cuentaBancariaForm);
		request.setAttribute("asociado", asociadoDAO.findById(cuentaBancariaForm.getAscId()));
		return mapping.findForward("lista");
	}
	
	//---- metodo que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request,final String preId,final int estado) {
		tableFacade.setColumnProperties("cbaCuenta", "ctrBanBanco.banId", "tcuTipoCuenta.tcuId","cbaId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.cba.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("cbaCuenta");
		nombreColumna.setTitleKey("tbl.cba.numeroCuenta");
		
		nombreColumna = row.getColumn("ctrBanBanco.banId");
		nombreColumna.setTitleKey("tbl.cba.banco");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				CtaCbaCuentaBancaria cuentaBancaria = (CtaCbaCuentaBancaria)item;
				return cuentaBancaria.getCtrBanBanco().getBanNombre(); 
			}
		});
		
		nombreColumna = row.getColumn("tcuTipoCuenta.tcuId");
		nombreColumna.setTitleKey("tbl.cba.tipoCuenta");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				CtaCbaCuentaBancaria cuentaBancaria = (CtaCbaCuentaBancaria)item;
				if(cuentaBancaria.getCtaTcuTipoCuenta() == null){
					return "Tipo de cuenta no ingresado";
				}else{
					return cuentaBancaria.getCtaTcuTipoCuenta().getTcuNombre();
				}
			}
		});

		nombreColumna = row.getColumn("cbaId");
		nombreColumna.setTitleKey("tbl.cba.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				CtaCbaCuentaBancaria cuentaBancaria = (CtaCbaCuentaBancaria)item;
				String toReturn="";
				Object value ="";
				HtmlBuilder html = new HtmlBuilder();
				value =getResources(request).getMessage("cmd.cas.linkSeleccionar");
				String link = tableFacade.getWebContext().getContextPath();
				link += "/cuentaCorriente/preCuentaBancaria.do?accion=seleccionar&cbaId="+cuentaBancaria.getCbaId()+"&preId="+preId+"&estado="+estado;
				html.a().href().quote().append(link).quote().append("class=\"linkActivar\"").title(value.toString()).close();
				//html.append(value);
				html.aEnd();
				toReturn = html.toString();
				return toReturn;
			}
		});
		
		return tableFacade.render();
	}
	
	//---- metodo que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade,final HttpServletRequest request) {
		 tableFacade.setColumnProperties("cbaCuenta", "banId", "tcuTipoCuenta.tcuId");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.cba.caption");
			
			Row row = table.getRow();
			Column nombreColumna = row.getColumn("cbaCuenta");
			nombreColumna.setTitleKey("tbl.cba.numeroCuenta");
			
			nombreColumna = row.getColumn("banId");
			nombreColumna.setTitleKey("tbl.cba.banco");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property, rowcount);
					CtaCbaCuentaBancaria cuentaBancaria = (CtaCbaCuentaBancaria)item;
					CtrBanBancoDAO bancoDAO = new CtrBanBancoDAO(getSessionHibernate(request));
					value = bancoDAO.findById(cuentaBancaria.getCtrBanBanco().getBanId()).getBanNombre();
					return value;
				}
			});
			
			nombreColumna = row.getColumn("tcuTipoCuenta.tcuId");
			nombreColumna.setTitleKey("tbl.cba.tipoCuenta");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property, rowcount);
					CtaCbaCuentaBancaria cuentaBancaria = (CtaCbaCuentaBancaria)item;
					CtaTcuTipoCuentaDAO cuentaDAO = new CtaTcuTipoCuentaDAO(getSessionHibernate(request));
					value = cuentaDAO.findById(cuentaBancaria.getCtaTcuTipoCuenta().getTcuId()).getTcuNombre();
					return value;
				}
			});
			
		 tableFacade.render();
	}
	
	public ActionForward regresar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toPrestamosAprobados");
	}
	
	public void mensajes(String msg, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
	}

	public ActionForward seleccionar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PreCuentaBancariaForm cuentaBancariaForm = (PreCuentaBancariaForm) form;
		CtaPrePrestamoDAO ctaPrePrestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
		
		Transaction tx= ctaPrePrestamoDAO.getSession().beginTransaction();
		
		CtaCbaCuentaBancariaDAO ctaCbaCuentaBancariaDAO = new CtaCbaCuentaBancariaDAO(getSessionHibernate(request));
		CtaPrePrestamo ctaPrePrestamo = ctaPrePrestamoDAO.findById(cuentaBancariaForm.getPreId());
		ctaPrePrestamo.setCtaCbaCuentaBancaria(ctaCbaCuentaBancariaDAO.findById(cuentaBancariaForm.getCbaId()));
		
		ctaPrePrestamoDAO.merge(ctaPrePrestamo);
		tx.commit();
		return mapping.findForward("toPrestamosAprobados");
	}
		
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.cba.lista","lista");
		map.put("cmd.cba.regresar","regresar");
		map.put("cmd.cba.seleccionar","seleccionar");
		return map;
	}
}