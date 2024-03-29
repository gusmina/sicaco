/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.inventario.struts.action;

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

import com.cetia.sicaco.hibernate.CtrPaiPais;
import com.cetia.sicaco.hibernate.CtrPaiPaisDAO;
import com.cetia.sicaco.hibernate.CtrParParametros;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.InvProProveedorDAO;
import com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticulo;
import com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticuloDAO;
import com.cetia.sicaco.hibernate.InvTarTipoArticulo;
import com.cetia.sicaco.hibernate.InvTarTipoArticuloDAO;
import com.cetia.sicaco.hibernate.InvTprTipoProveedor;
import com.cetia.sicaco.hibernate.InvTprTipoProveedorDAO;
import com.cetia.sicaco.inventario.struts.form.ProveedorForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 03-24-2008
 * 
 * XDoclet definition:
 * @struts.action path="/proveedor" name="proveedorForm" input="pagina-lista.inventario.proveedor" parameter="accion" scope="request"
 * @struts.action-forward name="lista" path="pagina-lista.inventario.proveedor"
 * @struts.action-forward name="dml" path="pagina-dml.inventario.proveedor"
 */
public class ProveedorAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "invProProveedor";
	
	public static final String TABLA_ID2 = "invTarTipoArticulo";
	
	public int pos = 0;
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		
		//java.util.List lst = null;
		Transaction tx = proveedorDAO.getSession().beginTransaction();
		//lst  = proveedorDAO.findAll();
		CtrPaiPaisDAO paisDAO = new CtrPaiPaisDAO(getSessionHibernate(request));
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		java.util.List lstP = paisDAO.findAll();
		java.util.List lstTP = tipoProveedorDAO.findAll();
		request.setAttribute("pais", lstP);
		request.setAttribute("tipoProveedor", lstTP);
		
		/* MODIFICACION PARA PAGINACION */
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID, request);
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = proveedorDAO.getTotalRowCount();
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		List lst = proveedorDAO.findAll(rowStart, rowEnd);
		tableFacade.setItems(lst);
		/* FIN DE MODIFICACION PARA PAGINACION*/
		
		/*TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
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
		request.setAttribute(Constantes.ACCION_KEY, "/proveedor");
        request.setAttribute("filtro", "2");
        request.setAttribute("form", proveedorForm);
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("proCodigo","proNombre","invTprTipoProveedor.tprNombre",
				"ctrPaiPais.paiNombre","proNumeroTelefono","proId");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.proveedor.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("proNombre");
		nombreColumna.setTitleKey("tbl.proveedor.proNombre");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvProProveedor proveedor = (InvProProveedor)item;
				
				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/proveedor.do?proId="+proveedor.getProId().toString()+"&accion=edit";
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();
				
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("invTprTipoProveedor.tprNombre");
		nombreColumna.setTitleKey("tbl.proveedor.invTprTipoProveedor.tprNombre");
		
		nombreColumna = row.getColumn("proCodigo");
		nombreColumna.setTitleKey("tbl.proveedor.proCodigo");
		
		nombreColumna = row.getColumn("ctrPaiPais.paiNombre");
		nombreColumna.setTitleKey("tbl.proveedor.ctrPaiPais.paiNombre");
		
		nombreColumna = row.getColumn("proNumeroTelefono");
		nombreColumna.setTitleKey("tbl.proveedor.proNumeroTelefono");
		
		nombreColumna = row.getColumn("proId");
		nombreColumna.setTitleKey("tbl.proveedor.accion");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvProProveedor proveedor = (InvProProveedor)item;
				value = "Contactos";
				HtmlBuilder htmlCt = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/contactoProveedor.do?proId="+proveedor.getProId().toString()+"&accion=contacto";
				htmlCt.a().href().quote().append(link).quote().append("class=\"linkBeneficiarios\"").title(value.toString()).close();
				/*htmlCt.a().href().quote().append(link).quote().close();
				htmlCt.append(value);*/
				htmlCt.aEnd();
				
				HtmlBuilder htmlCB = new HtmlBuilder();
				String account = "Cuentas";
				String linkCB = tableFacade.getWebContext().getContextPath();
				linkCB += "/inventario/prCuentaBancaria.do?proId="+proveedor.getProId().toString()+"&accion=lista";
				htmlCB.a().href().quote().append(linkCB).quote().append("class=\"linkCuentaBancaria\"").title(account).close();
				/*htmlCB.a().href().quote().append(linkCB).quote().close();
				htmlCB.append(account);*/
				htmlCB.aEnd();
				
				HtmlBuilder htmlCR = new HtmlBuilder();
				String ref = "Referencias";
				String linkCR = tableFacade.getWebContext().getContextPath();
				linkCR += "/orden/cuentaReferencia.do?proId="+proveedor.getProId().toString()+"&accion=lista";
				htmlCR.a().href().quote().append(linkCR).quote().append("class=\"linkSesion\"").title(ref).close();
			//	htmlCR.a().href().quote().append(linkCR).quote().close();
				//htmlCR.append(ref);
				htmlCR.aEnd();
				
				HtmlBuilder htmlCB2 = new HtmlBuilder();
				String msj  = "Union Tipo Prestamo";
				String linkCB2 = tableFacade.getWebContext().getContextPath();
				linkCB2 += "/inventario/proveedorOrden.do?proId2="+proveedor.getProId()+"&accion=lista";
				htmlCB2.a().href().quote().append(linkCB2).quote().append("class=\"linkUnion\"").title(msj).close();
			//	htmlCR.a().href().quote().append(linkCR).quote().close();
				//htmlCB2.append(msj);
				htmlCB2.aEnd();
				
				return htmlCt.toString() + " | " + htmlCB.toString() + " | " + htmlCR.toString()+" | " + htmlCB2.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("proCodigo","proNombre","invTprTipoProveedor.tprNombre",
					"ctrPaiPais.paiNombre","proNumeroTelefono");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.proveedor.caption");
			
			Row row = table.getRow();
			
			Column nombreColumna = row.getColumn("proNombre");
			nombreColumna.setTitleKey("tbl.proveedor.proNombre");
			
			nombreColumna = row.getColumn("invTprTipoProveedor.tprNombre");
			nombreColumna.setTitleKey("tbl.proveedor.invTprTipoProveedor.tprNombre");
			
			nombreColumna = row.getColumn("proCodigo");
			nombreColumna.setTitleKey("tbl.proveedor.proCodigo");
			
			nombreColumna = row.getColumn("ctrPaiPais.paiNombre");
			nombreColumna.setTitleKey("tbl.proveedor.ctrPaiPais.paiNombre.x");
			
			nombreColumna = row.getColumn("proNumeroTelefono");
			nombreColumna.setTitleKey("tbl.proveedor.proNumeroTelefono.x");
			
		tableFacade.render();
	}
	 
	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		Transaction tx = proveedorDAO.getSession().beginTransaction();
		try {
			proveedorForm.getProveedorH().setProId(proveedorDAO.nextId());
			proveedorForm.setProCodigo(proveedorDAO.nextCod());
			proveedorForm.setProEstado("A");
			proveedorDAO.save(proveedorForm.getProveedorH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			proveedorDAO.getSession().flush();
			proveedorDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}

	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		Transaction tx = proveedorDAO.getSession().beginTransaction();
		try {
			proveedorDAO.merge(proveedorForm.getProveedorH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			proveedorDAO.getSession().flush();
			proveedorDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		Transaction tx = proveedorDAO.getSession().beginTransaction();
		try {
			proveedorForm.setProveedorH(proveedorDAO.findById(proveedorForm.getProId()));
			proveedorForm.setProEstado("I");
			proveedorDAO.merge(proveedorForm.getProveedorH());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			proveedorDAO.getSession().flush();
			proveedorDAO.getSession().clear();
			
		}
		return lista(mapping,form,request,response);
	}
/*	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		CtrPaiPaisDAO paisDAO = new CtrPaiPaisDAO(getSessionHibernate(request));
		proveedorForm.setProveedorH(proveedorDAO.findById(proveedorForm.getProId()));
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		java.util.List lstP = paisDAO.findAll();
		java.util.List lstTP = tipoProveedorDAO.findAll();
		request.setAttribute("form", proveedorForm);
		request.setAttribute("pais", lstP);
		request.setAttribute("tipoProveedor", lstTP);
		request.setAttribute(Constantes.ACCION_KEY, "/proveedor");
        request.setAttribute("filtro", "1");
        request.setAttribute("boton", "1");
		return mapping.findForward("dml");
	}
	*/
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm)form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		proveedorForm.setProCodigo(proveedorDAO.nextCod());
		
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		CtrParParametros parametros = parametrosDAO.findById("DIAS_FECHA_PAGO_PROVEEDOR");
		proveedorForm.setProDiasPago(parametros.getParValorNumber().intValue());
		
		CtrPaiPaisDAO paisDAO = new CtrPaiPaisDAO(getSessionHibernate(request));
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		java.util.List lstP = paisDAO.findAll();
		java.util.List lstTP = tipoProveedorDAO.findAll();
		request.setAttribute("form", proveedorForm);
		request.setAttribute("pais", lstP);
		request.setAttribute("tipoProveedor", lstTP);
		request.setAttribute(Constantes.ACCION_KEY, "/proveedor");
        request.setAttribute("filtro", "1");
        request.setAttribute("boton", "0");
		return mapping.findForward("dml");
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return lista(mapping, form, request, response);
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		java.util.List lst = null;
		Transaction tx = proveedorDAO.getSession().beginTransaction();
		lst  = proveedorDAO.findByCriteria(proveedorForm.getProveedorH());
		CtrPaiPais pais = new CtrPaiPais();
		CtrPaiPaisDAO paisDAO = new CtrPaiPaisDAO(getSessionHibernate(request));
		InvTprTipoProveedor tipoProveedor = new InvTprTipoProveedor();
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		java.util.List lstP = paisDAO.findAll();
		java.util.List lstTP = tipoProveedorDAO.findAll();
		request.setAttribute("pais", lstP);
		request.setAttribute("tipoProveedor", lstTP);
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
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
		request.setAttribute(Constantes.ACCION_KEY, "/proveedor");
        request.setAttribute("filtro", "2");
		return mapping.findForward("lista");
	}
	
	public ActionForward contactos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constantes.ACCION_KEY,"/contactoProveedor");
		return mapping.findForward("contactos");
	}
	
	public ActionForward account(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constantes.ACCION_KEY,"/prCuentaBancaria");
		return mapping.findForward("account");
	}
	
	public ActionForward mover(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constantes.ACCION_KEY,"/cuentaReferencia");
		return mapping.findForward("ref");
	}
	
	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		java.util.List lst = null;
		Transaction tx = proveedorDAO.getSession().beginTransaction();
		lst  = proveedorDAO.findAll();
		CtrPaiPaisDAO paisDAO = new CtrPaiPaisDAO(getSessionHibernate(request));
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		java.util.List lstP = paisDAO.findAll();
		java.util.List lstTP = tipoProveedorDAO.findAll();
		request.setAttribute("pais", lstP);
		request.setAttribute("tipoProveedor", lstTP);
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(lst);
		//---- Genera los tipos de formas con que se podran exportar los datos
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);//, ExportType.PDF);
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
		request.setAttribute(Constantes.ACCION_KEY, "/proveedor");
        request.setAttribute("filtro", "2");
        saveMessages(request, errors);
		return mapping.findForward("lista");
		 
	}
	
	/* ///////////////////////////
	 * 
	 * Inicia parte de rol-menu
	 * 
	 * ///////////////////////////
	 */
/*	
	private String htmlSeleccion(final TableFacade tableFacade, final HttpServletRequest request, final List list, final Integer proId) {
		tableFacade.setColumnProperties("tclClasificacion","tclDescripcion","audUsuarioCreacion");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tipoClasificacion.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("tclClasificacion");
		nombreColumna.setTitleKey("tbl.tipoClasificacion.tclClasificacion");
		
		nombreColumna = row.getColumn("tclDescripcion");
		nombreColumna.setTitleKey("tbl.tipoClasificacion.tclDescripcion");
		
		nombreColumna = row.getColumn("audUsuarioCreacion");
		nombreColumna.setTitle("Asignar");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvTclTipoClasificacion tipoClasificacion = (InvTclTipoClasificacion)item;
				HtmlBuilder html = new HtmlBuilder();
				
				if(!asignado(tipoClasificacion.getTclClasificacion(), proId)){
					html.input().type("checkbox").name("posiciones").value(""+pos+"").close();
				}else{
					html.input().type("checkbox").name("posiciones").value(""+pos+"").checked().close();
				}
				pos=pos+1;
				return html.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	public boolean asignado(String tipoClasificacion, Integer provId){
		InvTclTipoClasificacionDAO tipoClasificacionDAO = new InvTclTipoClasificacionDAO(getSessionHibernate(request));
		InvClaClasificadoDAO clasificadoDAO = new InvClaClasificadoDAO(getSessionHibernate(request));
		
		InvTclTipoClasificacion tipoClasificacion2 = tipoClasificacionDAO.findById(tipoClasificacion);
		if(provId == null ){
			return false;
		}
		
		List list = clasificadoDAO.findByProId(provId);
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			InvClaClasificado clasificado = (InvClaClasificado)iterator.next();
			if(clasificado.getId().getInvTclTipoClasificacion() == tipoClasificacion2){
				return true;
			}
		}
				
		return false;
	}
*/	
	private String htmlSeleccion(final TableFacade tableFacade, final HttpServletRequest request, final List list, final Integer proId) {
		tableFacade.setColumnProperties("tarId","tarNombre","tarDescripcion","audUsuarioModificacion");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.tipoarticulo.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("tarNombre");
		nombreColumna.setTitleKey("tbl.tipoarticulo.tarNombre");
		
		nombreColumna = row.getColumn("tarDescripcion");
		nombreColumna.setTitleKey("tbl.tipoarticulo.tarDescripcion");
		
		nombreColumna = row.getColumn("tarId");
		nombreColumna.setTitleKey("tbl.tipoarticulo.tarId");
		/*nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvTarTipoArticulo tipoArticulo = (InvTarTipoArticulo)item;
				HtmlBuilder html = new HtmlBuilder();
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/tipoArticulo.do?accion=forwardToEdicion"+"&"+"tarId="+tipoArticulo.getTarId();
				html.a().href().quote().append(link).quote().close();
				html.append(value);
				html.aEnd();
				return html.toString();
			}
		});*/
		
		nombreColumna = row.getColumn("audUsuarioModificacion");
		nombreColumna.setTitleKey("tbl.proveedor.check");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				InvTarTipoArticulo tipoArticulo = (InvTarTipoArticulo)item;
				HtmlBuilder html = new HtmlBuilder();
				String javaScript = "handlerDeleteButton(':')";
				html.a().onclick(javaScript.replaceFirst(":",tipoArticulo.getTarId())).close();
				html.append("eliminar");
				html.aEnd();
				return html.toString();
			}
		});
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvTarTipoArticulo tipoArticulo = (InvTarTipoArticulo)item;
				HtmlBuilder html = new HtmlBuilder();
				
				if(!asignado(tipoArticulo.getTarId(), proId,request)){
					html.input().type("checkbox").name("posiciones").value(""+pos+"").close();
				}else{
					html.input().type("checkbox").name("posiciones").value(""+pos+"").checked().close();
				}
				pos=pos+1;
				return html.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	public boolean asignado(String tipoArticulo, Integer provId,HttpServletRequest request){
		InvTarTipoArticuloDAO tipoArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
		InvPxtProveedorxtipoArticuloDAO pxtDAO = new InvPxtProveedorxtipoArticuloDAO(getSessionHibernate(request));
		
		InvTarTipoArticulo tipoArticulo2 = tipoArticuloDAO.findById(tipoArticulo);
		if(provId == null ){
			return false;
		}
		
		List list = pxtDAO.findByProId(provId);
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			InvPxtProveedorxtipoArticulo pxt = (InvPxtProveedorxtipoArticulo)iterator.next();
			if(pxt.getInvTarTipoArticulo() == tipoArticulo2){
				return true;
			}
		}
				
		return false;
	}
	
	//---- Accion asociada al link de edicion de la tabla, entra a pantalla de edicion
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProveedorForm proveedorForm = (ProveedorForm) form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		CtrPaiPaisDAO paisDAO = new CtrPaiPaisDAO(getSessionHibernate(request));
		
		proveedorForm.setProveedorH(proveedorDAO.findById(proveedorForm.getProId()));
		InvTprTipoProveedorDAO tipoProveedorDAO = new InvTprTipoProveedorDAO(getSessionHibernate(request));
		
		java.util.List lstP = paisDAO.findAll();
		java.util.List lstTP = tipoProveedorDAO.findAll();
		
		proveedorForm.setProveId(proveedorForm.getProId());
		request.setAttribute("form", proveedorForm);
		request.setAttribute("pais", lstP);
		request.setAttribute("tipoProveedor", lstTP);
		request.setAttribute(Constantes.ACCION_KEY, "/proveedor");
        request.setAttribute("filtro", "1");
        request.setAttribute("boton", "1");
        
		pos = 0;
/*		
		InvClaClasificadoDAO clasificadoDAO = new InvClaClasificadoDAO(getSessionHibernate(request));
		List list = clasificadoDAO.findAll();
		request.setAttribute("listCla", list);
		
		InvTclTipoClasificacionDAO tipoClasificacionDAO = new InvTclTipoClasificacionDAO(getSessionHibernate(request));
		List lm = tipoClasificacionDAO.findAll();
		request.setAttribute("listTcl", lm);
*/	
		InvPxtProveedorxtipoArticuloDAO pxt = new InvPxtProveedorxtipoArticuloDAO(getSessionHibernate(request));
		List list = pxt.findAll();
		request.setAttribute("listPxt", list);
		
		InvTarTipoArticuloDAO tipoArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
		List lm = tipoArticuloDAO.findAll();
		request.setAttribute("listTcl", lm);
		
		//Aqui empieza el codigo para generar Tabla
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
            String html = htmlSeleccion(tableFacade, request,list,proveedorForm.getProId());
            request.setAttribute(Constantes.LISTA_KEY, html);
        }
        
		//----- Variables de configuracion
		request.setAttribute("detalleOrden", 3);
		
		return mapping.findForward("dml");
	}
/*	
	public ActionForward asignar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//RolMenuForm rolMenuForm = (RolMenuForm)form;
		ProveedorForm proveedorForm = (ProveedorForm)form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		InvProProveedor proveedor = proveedorDAO.findById(proveedorForm.getProveId());
		
		InvTclTipoClasificacionDAO tipoClasificacionDAO = new InvTclTipoClasificacionDAO(getSessionHibernate(request));
		List tlist = tipoClasificacionDAO.findAll();
		
		List lst = (List) request.getSession().getAttribute("ordDet");
		int[] pos = proveedorForm.getPosiciones();
		int[] pos2 = new int[tlist.size()];
		
		
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
			
			InvClaClasificadoDAO clasificadoDAO = new InvClaClasificadoDAO(getSessionHibernate(request));
			Transaction tx = clasificadoDAO.getSession().beginTransaction();
			
			Iterator iterator = tlist.iterator();
			int[] del = new int[tlist.size()];
	        for(int i = 0; i < tlist.size(); i++) {
	        	if(iterator.hasNext()){
	        		//OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)iterator.next();
	        		InvTclTipoClasificacion tipoClasificacion = (InvTclTipoClasificacion)iterator.next();
	        		if(pos2[i] == -1){
	        			InvClaClasificadoId clasificadoId = new InvClaClasificadoId();
	        			InvClaClasificado clasificado = new InvClaClasificado();
	        			
	        			clasificadoId.setInvTclTipoClasificacion(tipoClasificacion);
	        			clasificadoId.setInvProProveedor(proveedor);
	        			clasificado.setId(clasificadoId);
	        			
	        			if(clasificadoDAO.findById(clasificadoId) != null){
	        				clasificado = clasificadoDAO.findById(clasificadoId);
	        				clasificado.setAudFechaModificacion(new Date());
	        				clasificado.setAudUsuarioModificacion(proveedorForm.getUsuarioConectado().getNombreUsuario());
	        				clasificadoDAO.delete(clasificado);
	        			}
	        			
	        		}else{
	        			InvClaClasificadoId clasificadoId = new InvClaClasificadoId();
	        			InvClaClasificado clasificado = new InvClaClasificado();
	        			
	        			clasificadoId.setInvTclTipoClasificacion(tipoClasificacion);
	        			clasificadoId.setInvProProveedor(proveedor);
	        			clasificado.setId(clasificadoId);
	        			
	        			if(clasificadoDAO.findById(clasificadoId) == null){
	        				clasificado.setAudFechaCreacion(new Date());
	        				clasificado.setAudFechaModificacion(new Date());
	        				clasificado.setAudUsuarioCreacion(proveedorForm.getUsuarioConectado().getNombreUsuario());
	        				clasificado.setAudUsuarioModificacion(proveedorForm.getUsuarioConectado().getNombreUsuario());
	        				clasificadoDAO.save(clasificado);
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
*/	
	
	public ActionForward asignar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ProveedorForm proveedorForm = (ProveedorForm)form;
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		InvProProveedor proveedor = proveedorDAO.findById(proveedorForm.getProveId());
		
		InvTarTipoArticuloDAO tipoArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
		//InvTclTipoClasificacionDAO tipoClasificacionDAO = new InvTclTipoClasificacionDAO(getSessionHibernate(request));
		List tlist = tipoArticuloDAO.findAll();
		
		List lst = (List) request.getSession().getAttribute("ordDet");
		int[] pos = proveedorForm.getPosiciones();
		int[] pos2 = new int[tlist.size()];
		
		
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
			
			InvPxtProveedorxtipoArticuloDAO pxtDAO = new InvPxtProveedorxtipoArticuloDAO(getSessionHibernate(request));
			//InvClaClasificadoDAO clasificadoDAO = new InvClaClasificadoDAO(getSessionHibernate(request));
			Transaction tx = pxtDAO.getSession().beginTransaction();
			
			Iterator iterator = tlist.iterator();
			int[] del = new int[tlist.size()];
	        for(int i = 0; i < tlist.size(); i++) {
	        	if(iterator.hasNext()){
	        		InvTarTipoArticulo tipoArticulo = (InvTarTipoArticulo)iterator.next();
	        		//InvTclTipoClasificacion tipoClasificacion = (InvTclTipoClasificacion)iterator.next();
	        		if(pos2[i] == -1){
	        			//InvClaClasificadoId clasificadoId = new InvClaClasificadoId();
	        			InvPxtProveedorxtipoArticulo pxt = new InvPxtProveedorxtipoArticulo();
	        			//InvClaClasificado clasificado = new InvClaClasificado();
	        			
	        			pxt.setInvTarTipoArticulo(tipoArticulo);
	        			pxt.setInvProProveedor(proveedor);
	        			//clasificado.setId(clasificadoId);
	        			
	        			if(pxtDAO.findByProAndTar(pxt).size()>0){
	        				pxt = (InvPxtProveedorxtipoArticulo)pxtDAO.findByProAndTar(pxt).get(0);
	        				pxtDAO.delete(pxt);
	        				tx.commit();
	        			}
	        			
	        		}else{
	        			InvPxtProveedorxtipoArticulo pxt = new InvPxtProveedorxtipoArticulo();
	        			
	        			pxt.setInvTarTipoArticulo(tipoArticulo);
	        			pxt.setInvProProveedor(proveedor);
	        			
	        			if(pxtDAO.findByProAndTar(pxt).size()<1){
	        				pxtDAO.save(pxt);
	        				tx.commit();
	        			}
	        		}
	        	}
			}
	        pxtDAO.getSession().flush();
	        pxtDAO.getSession().clear();
	        
			//guardamos
			return lista(mapping, form, request, response);
		}
		else{
			InvPxtProveedorxtipoArticuloDAO pxtDAO = new InvPxtProveedorxtipoArticuloDAO(getSessionHibernate(request));
			Transaction tx = pxtDAO.getSession().beginTransaction();
			List lstP = pxtDAO.findByProId(proveedor.getProId());
			for (Iterator iterator = lstP.iterator(); iterator.hasNext();) {
				InvPxtProveedorxtipoArticulo pxt = (InvPxtProveedorxtipoArticulo) iterator.next();
				pxtDAO.delete(pxt);
				tx.commit();
			}
			pxtDAO.getSession().flush();
	        pxtDAO.getSession().clear();
	        
		}
		//solo regresamos
		return lista(mapping, form, request, response);
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.proveedor.lista", "lista");
		map.put("cmd.proveedor.guardar", "guardar");
		map.put("cmd.proveedor.eliminar", "eliminar");
		map.put("cmd.proveedor.salvar", "salvar");
		map.put("cmd.proveedor.editar", "editar");
		map.put("cmd.proveedor.cancelar", "cancelar");
		map.put("cmd.proveedor.agregar", "agregar");
		map.put("cmd.proveedor.buscar", "buscar");
		map.put("cmd.contactoProveedor.lista", "contactos");
		map.put("cmd.pCuentaBancaria.lista", "account");
		map.put("cmd.cref.mover", "mover");
		map.put("cmd.proveedor.nuevo", "agregar");
		map.put("cmd.proveedor.redirectInvalidData", "redirectInvalidData");
		map.put("cmd.proveedor.asignar", "asignar");
		return map;
	}
}