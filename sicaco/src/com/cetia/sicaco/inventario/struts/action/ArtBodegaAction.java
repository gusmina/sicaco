/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.inventario.struts.action;

import java.util.HashMap;
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

import com.cetia.sicaco.hibernate.InvArtArticulo;
import com.cetia.sicaco.hibernate.InvEboExistenciaBodega;
import com.cetia.sicaco.hibernate.InvEboExistenciaBodegaDAO;
import com.cetia.sicaco.hibernate.InvLinLineaDAO;
import com.cetia.sicaco.inventario.struts.form.ArtBodegaForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.Format;

/** 
 * MyEclipse Struts
 * Creation date: 05-15-2008
 * 
 * XDoclet definition:
 * @struts.action path="/artBodega" name="artBodegaForm" input="redirectInvalidData" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.inventario.artBodega"
 */
public class ArtBodegaAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "invEboExistenciaBodega";
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
		ArtBodegaForm artBodegaForm = (ArtBodegaForm)form;
		InvEboExistenciaBodegaDAO existenciaBodegaDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
		
		/*MODIFICACIONES PARA PAGINACIÓN*/
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID, request);
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = existenciaBodegaDAO.getTotalRowCount(artBodegaForm.getBodega());
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		List lst  = existenciaBodegaDAO.findByBodega(artBodegaForm.getBodega(), rowStart, rowEnd);
		tableFacade.setItems(lst);
		/*FIN MODIFICACIONES*/
		
		/*List lst  = existenciaBodegaDAO.findByBodega(artBodegaForm.getBodega());

		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
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
		request.getSession().setAttribute("formS", artBodegaForm);
		request.setAttribute("form", artBodegaForm);
		request.setAttribute(Constantes.ACCION_KEY, "/artBodega");
        request.setAttribute("filtro", "0");
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("id.invPexProductosExistencia.invArtArticulo.artCodigo",
				"id.invPexProductosExistencia.invArtArticulo.artNombre",
				"eboCantidadProducto","eboSaldo","audFechaCreacion");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.abo.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("id.invPexProductosExistencia.invArtArticulo.artCodigo");
		nombreColumna.setTitleKey("tbl.abo.id.invArtArticulo.artCodigo");

		nombreColumna = row.getColumn("id.invPexProductosExistencia.invArtArticulo.artNombre");
		nombreColumna.setTitleKey("tbl.abo.id.invArtArticulo.artNombre");
		
		nombreColumna = row.getColumn("eboCantidadProducto");
		nombreColumna.setTitleKey("tbl.abo.eboCantidadProducto");
		
		nombreColumna = row.getColumn("eboSaldo");
		nombreColumna.setTitleKey("tbl.abo.eboSaldo");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				InvEboExistenciaBodega existenciaBodega = (InvEboExistenciaBodega) item;
				
				value = "<div align=\"right\">"+Format.formatDinero(existenciaBodega.getEboSaldo())+"</div>";
				return value;
			}
		});
		
		nombreColumna = row.getColumn("audFechaCreacion");
		nombreColumna.setTitleKey("tbl.abo.acciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				InvEboExistenciaBodega bodega = (InvEboExistenciaBodega)item;
				
				HtmlBuilder html = new HtmlBuilder();
				value = "Movimientos";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/inventario/movimiento.do?accion=lista&bodega=" + bodega.getId().getInvBodBodegas().getBodId() + "&artCod=" + bodega.getId().getInvPexProductosExistencia().getArtCodigo();
				html.a().href().quote().append(link).quote().append("class=\"linkMovimientoBod\"").title(value.toString()).close();
				//html.a().href(link).close();
				//html.append(value);
				html.aEnd();
								
				return html.toString();
			}
		});
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("id.invPexProductosExistencia.invArtArticulo.artCodigo",
				"id.invPexProductosExistencia.invArtArticulo.artNombre",
				"eboCantidadProducto","eboSaldo");
		Table table = tableFacade.getTable();
		
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.abo.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("id.invPexProductosExistencia.invArtArticulo.artCodigo");
		nombreColumna.setTitleKey("tbl.abo.id.invArtArticulo.artCodigo.x");

		nombreColumna = row.getColumn("id.invPexProductosExistencia.invArtArticulo.artNombre");
		nombreColumna.setTitleKey("tbl.abo.id.invArtArticulo.artNombre.x");
		
		nombreColumna = row.getColumn("eboCantidadProducto");
		nombreColumna.setTitleKey("tbl.abo.eboCantidadProducto");
		
		nombreColumna = row.getColumn("eboSaldo");
		nombreColumna.setTitleKey("tbl.abo.eboSaldo");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				InvEboExistenciaBodega existenciaBodega = (InvEboExistenciaBodega) item;
				
				value = Format.formatDinero(existenciaBodega.getEboSaldo());
				return value;
			}
		});
		
		tableFacade.render();
	}
	 
	public ActionForward buscar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ArtBodegaForm artBodegaForm = (ArtBodegaForm)form;
		InvEboExistenciaBodegaDAO existenciaBodegaDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
		InvArtArticulo articulo = new InvArtArticulo();
		articulo.setArtCodigo(artBodegaForm.getArtCod());
		articulo.setArtNombre(artBodegaForm.getArtName());
		List lst  = null;
		if((artBodegaForm.getArtCod().equals("")||artBodegaForm.getArtCod() == null) &&
				(artBodegaForm.getArtName().equals("")||artBodegaForm.getArtName() == null)){
			lst = existenciaBodegaDAO.findByBodega(artBodegaForm.getBodega());
		}else{
			lst = existenciaBodegaDAO.findByArticulo(artBodegaForm.getBodega(),articulo);
		}

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
		request.setAttribute("form", artBodegaForm);
		request.setAttribute(Constantes.ACCION_KEY, "/artBodega");
        request.setAttribute("filtro", "0");
        return mapping.findForward("lista");
	}

	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		InvLinLineaDAO lineaDAO = new InvLinLineaDAO(getSessionHibernate(request));
		List lst = null;
		Transaction tx = lineaDAO.getSession().beginTransaction();
		lst  = lineaDAO.findAll();
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
		request.setAttribute(Constantes.ACCION_KEY, "/linea");
        request.setAttribute("filtro", "1");
		saveMessages(request, errors);
		return mapping.findForward("lista");
	}
	
	public ActionForward regresar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("redirectBodega");
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.abo.list", "lista");
		map.put("cmd.abo.lista", "lista");
		map.put("cmd.abo.buscar", "buscar");
		map.put("cmd.asc.redirectInvalidData", "redirectInvalidData");
		map.put("cmd.abo.return","regresar");
		return map;
	}
}