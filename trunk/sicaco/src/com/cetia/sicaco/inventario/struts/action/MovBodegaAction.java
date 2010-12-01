/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.inventario.struts.action;

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
import org.jmesa.view.editor.DateCellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.InvArtArticulo;
import com.cetia.sicaco.hibernate.InvArtArticuloDAO;
import com.cetia.sicaco.hibernate.InvBodBodegas;
import com.cetia.sicaco.hibernate.InvBodBodegasDAO;
import com.cetia.sicaco.hibernate.InvCprCapacidadProducto;
import com.cetia.sicaco.hibernate.InvCprCapacidadProductoDAO;
import com.cetia.sicaco.hibernate.InvCprCapacidadProductoId;
import com.cetia.sicaco.hibernate.InvEboExistenciaBodega;
import com.cetia.sicaco.hibernate.InvEboExistenciaBodegaDAO;
import com.cetia.sicaco.hibernate.InvLinLineaDAO;
import com.cetia.sicaco.hibernate.InvMovMovimientos;
import com.cetia.sicaco.hibernate.InvMovMovimientosDAO;
import com.cetia.sicaco.hibernate.InvNivNivel;
import com.cetia.sicaco.hibernate.InvNivNivelDAO;
import com.cetia.sicaco.hibernate.InvNivNivelId;
import com.cetia.sicaco.hibernate.InvNxmNivelMovimiento;
import com.cetia.sicaco.hibernate.InvNxmNivelMovimientoDAO;
import com.cetia.sicaco.hibernate.InvStnEstante;
import com.cetia.sicaco.hibernate.InvStnEstanteDAO;
import com.cetia.sicaco.hibernate.InvTmoTipoMovimiento;
import com.cetia.sicaco.hibernate.InvTmoTipoMovimientoDAO;
import com.cetia.sicaco.hibernate.OrdOcoOrdenDeCompra;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecPerPersonaDAO;
import com.cetia.sicaco.inventario.struts.form.ArtBodegaForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 06-02-2008
 * 
 * XDoclet definition:
 * @struts.action path="/movBodega" name="artBodegaForm" input="redirectInvalidData" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.inventario.movBodega"
 */
public class MovBodegaAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	public static final String TABLA_ID = "invEboExistenciaBodega";
	
	int pos;
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
		//pos = 0;
		ArtBodegaForm artBodegaForm = (ArtBodegaForm)form;
		
		InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
		List lstBod = bodegasDAO.findAll();
		request.setAttribute("listaBodegas", lstBod);
		
		InvArtArticulo articulo = new InvArtArticulo();
		articulo.setArtCodigo(artBodegaForm.getArtCod());
		InvEboExistenciaBodegaDAO existenciaBodegaDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
		InvEboExistenciaBodega existenciaBodega = (InvEboExistenciaBodega)existenciaBodegaDAO.findByArticulo(artBodegaForm.getBodega(), articulo).get(0);
		artBodegaForm.setEboCantidadProducto(existenciaBodega.getEboCantidadProducto());
		artBodegaForm.setEboSaldo(existenciaBodega.getEboSaldo());
		
		/*TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		//tableFacade.setEditable(true);
		
		

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
        //----- Variables de configuracion*/
		//request.getSession().setAttribute("ordDet", lst);
		artBodegaForm.setDis("true");
		request.setAttribute("form", artBodegaForm);
		request.setAttribute(Constantes.ACCION_KEY, "/movBodega");
		return mapping.findForward("lista");
		 
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("ocoCodigo","ocoEmision","secAscAsociado.id.ascCodigo","ocoMonto",
				"ocoPagado","ocoVencimiento");//,"chkbox");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.ordenPago.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("ocoCodigo");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoCodigo");
/*		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)item;
				
				HtmlBuilder html = new HtmlBuilder();
				String val = "- " + value + " -";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/orden/ordenCompra.do?accion=edit&ocoId="+ compra.getId().getOcoId();
				html.a().href().quote().append(link).quote().close();
				html.append(val);
				html.aEnd();
								
				return html.toString();
			}
		});
*/		
		nombreColumna = row.getColumn("secAscAsociado.id.ascCodigo");
		nombreColumna.setTitleKey("tbl.ordenPago.secAscAsociado.id.ascCodigo");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra) item;
				
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
				CtaAscAsociado asociado = asociadoDAO.findById(compra.getAscCodigo());
				
				SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
				SecPerPersona persona = personaDAO.findById(asociado.getSecPerPersona().getPerId());
				
				value = value + " - " + persona.getPerPrimerApellido()
				+ ", " + persona.getPerPrimerNombre();
				
				HtmlBuilder html = new HtmlBuilder();
				html.append(value);
								
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("ocoPagado");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoPagado");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)item;
				HtmlBuilder html = new HtmlBuilder();
				if(compra.getOcoPagado() == null){
					html.input().type("text").name("valores").value(""+compra.getOcoMonto()+"").size("10").close();
				}else{
					html.input().type("text").name("valores").value(""+compra.getOcoPagado()+"").size("10").close();
				}
				//html.append(value);
								
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("ocoEmision");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoEmision");
		nombreColumna.getCellRenderer().setCellEditor(new DateCellEditor("dd-MMM-yyyy"));
		
		nombreColumna = row.getColumn("ocoVencimiento");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoVencimiento");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				OrdOcoOrdenDeCompra compra = (OrdOcoOrdenDeCompra)item;
				HtmlBuilder html = new HtmlBuilder();
				String del="check";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/orden/detalleOrden.do?accion=check&ocoCodigo=" + compra.getOcoCodigo();
				if(compra.getOcoPagado() == null){
					html.input().type("checkbox").name("posiciones").value(""+pos+"").close();
				}else{
					html.input().type("checkbox").name("posiciones").value(""+pos+"").checked().close();
				}
				pos=pos+1;
				return html.toString();
			}
		});
		
		nombreColumna = row.getColumn("ocoMonto");
		nombreColumna.setTitleKey("tbl.ordenPago.ocoMonto");
/*		
		HtmlColumn chkbox = (HtmlColumn) row.getColumn("chkbox");
		chkbox.getCellRenderer().setWorksheetEditor(new CheckboxWorksheetEditor());
		chkbox.setTitle("&nbsp;");
		chkbox.setFilterable(false);
		chkbox.setSortable(false);
*/
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("linNombre","linDescripcion");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.linea.caption");
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("linNombre");
		nombreColumna.setTitleKey("tbl.linea.linNombre");
		
		nombreColumna = row.getColumn("linDescripcion");
		nombreColumna.setTitleKey("tbl.linea.linDescripcion");

		tableFacade.render();
	}
	 
	 public ActionForward cargarListaEstantes(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) { // se utiliza para realizar la busqueda de los proveedores
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		InvStnEstante estante = new InvStnEstante();
		ArtBodegaForm artBodegaForm = (ArtBodegaForm) form;
		List<InvStnEstante> listaEstantes = null;
		try {
			/*InvArtArticulo articulo = new InvArtArticulo();
			articulo.setArtCodigo(artBodegaForm.getArtCod());
			InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
			InvEboExistenciaBodega ebo = eboDAO.findByArticulo(artBodegaForm.getBodega2(), articulo).get(0);*/
			listaEstantes = estanteDAO.findByBodega(artBodegaForm.getBodega2());
			//Construimos una lista para el response
			String listaResponse1 = contruirSelectEstantes(listaEstantes, request);//,ebo);
			response.getWriter().write(listaResponse1);
			response.getWriter().flush();
			response.getWriter().close();
		}catch(RuntimeException e) {
			log.error("Error runtime",e);
		} catch (IOException e) {
			log.error(e);
		}
		artBodegaForm.setDis("false");
		request.setAttribute("form", artBodegaForm);
		return null;
	}
	 /*styleId="bodega2"
					styleClass="obligatorio" value="${form.bodega2}"
					onchange="ajaxCallNormal('${pageContext.request.contextPath}/inventario${_accion}.do','accion=cargarListaEstantes&bodega2='+$('#bodega2').val(),'estantes')" >
					*/
		
	private String contruirSelectEstantes(List<InvStnEstante> listaEstantes, HttpServletRequest request){//, InvEboExistenciaBodega ebo) {
		//String exist = "<label>Existencia en bodega " + ebo.getId().getInvBodBodegas().getBodNombre() + ": " + ebo.getEboCantidadProducto() +"</label>";
		//exist += "<br><label>Saldo actual en bodega " + ebo.getId().getInvBodBodegas().getBodNombre() + ": " + ebo.getEboSaldo() +"</label><br>";
		String label = "<label>Seleccione el estante:</label>	";
		String select = "<select id=\"estanteId\" name=\"estanteId\" ";
		select += "styleId=\"estanteId\" styleClass=\"obligatorio\"";//value=\"${form.estanteId}\"
		select += "onchange=\"ajaxCallNormal('"+ request.getContextPath() +"/inventario/movBodega.do','accion=cargarListaCeldas&estanteId='+$('#estanteId').val(),'celdas')\" >";
		for (Iterator iterator = listaEstantes.iterator(); iterator.hasNext();) {
			InvStnEstante estante = (InvStnEstante) iterator.next();
			if(estante.getStnEstado().equals("A")){
				select +="<option value=\""+ estante.getStnId() + "\">";
				select += estante.getStnCodigo();
				select += "</option>";
			}
		}
		select += "</select>";
		return label + select;
	}
	
	public ActionForward cargarListaCeldas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) { // se utiliza para realizar la busqueda de los proveedores
		InvNivNivelDAO nivelDAO = new InvNivNivelDAO(getSessionHibernate(request));
		InvNivNivel nivel = new InvNivNivel();
		ArtBodegaForm artBodegaForm = (ArtBodegaForm) form;
		List<InvNivNivel> listaCeldas = null;
		try {
			listaCeldas = nivelDAO.findByEstante(artBodegaForm.getEstanteId());
			//Construimos una lista para el response
			String listaResponse2 = contruirSelectCeldas(listaCeldas);
			response.getWriter().write(listaResponse2);
			response.getWriter().flush();
			response.getWriter().close();
		}catch(RuntimeException e) {
			log.error("Error runtime",e);
		} catch (IOException e) {
			log.error(e);
		}
		request.setAttribute("form", artBodegaForm);
		return null;
	}
	
	private String contruirSelectCeldas(List<InvNivNivel> listaCeldas) {
		String label = "<label>Seleccione el compartimento del estante:</label>	";
		String select = "<select id=\"celdaId\" name=\"celdaId\" ";
		select += "styleId=\"celdaId\" styleClass=\"obligatorio\"";// value=\"${form.celdaId}\" ";
		for (Iterator iterator = listaCeldas.iterator(); iterator.hasNext();) {
			InvNivNivel celda = (InvNivNivel) iterator.next();
			if(celda.getNivEstado().equals("A")){
				select +="<option value=\""+ celda.getId().getNivFilaId().toString() + "C"+ celda.getId().getNivColId().toString() + "\">";
				select += "F"+celda.getId().getNivFilaId().toString() + " C"+celda.getId().getNivColId().toString();
				select += "</option>";
			}
		}
		select += "</select>";
		return label + select;
	}
	 
	public ActionForward movimiento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ArtBodegaForm artBodegaForm = (ArtBodegaForm)form;
		
		int bodega1 = artBodegaForm.getBodega();
		int bodega2 = artBodegaForm.getBodega2();
		int aMover = artBodegaForm.getCantMover();
		String artCod = artBodegaForm.getArtCod();
		
		
		InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
		Transaction tx = eboDAO.getSession().beginTransaction();
		
		InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		InvArtArticulo articulo = articuloDAO.findById(artCod);
		
		InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));

		InvTmoTipoMovimientoDAO invTmoTipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
		InvTmoTipoMovimiento entrada = invTmoTipoMovimientoDAO.findById(1);
		InvTmoTipoMovimiento salida = invTmoTipoMovimientoDAO.findById(2);
		InvTmoTipoMovimiento interno = invTmoTipoMovimientoDAO.findById(3);
		
		String usuario = artBodegaForm.getUsuarioConectado().getNombreUsuario();
		
		InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
		InvBodBodegas bodegas1 = bodegasDAO.findById(bodega1);
		if(bodega1 == bodega2){
			
			InvMovMovimientos movimientos = new InvMovMovimientos();
			movimientos.setInvTmoTipoMovimiento(interno);
			movimientos.setInvBodBodegas(bodegas1);
			movimientos.setInvArtArticulo(articulo);
			movimientos.setMovUnidades(aMover);
			movimientos.setMovValor(aMover*articulo.getArtPrecioMinimo());
			movimientos.setAudFechaCreacion(new Date());
			movimientos.setAudFechaModificacion(new Date());
			movimientos.setAudUsuarioCreacion(usuario);
			movimientos.setAudUsuarioModificacion(usuario);
			movimientosDAO.save(movimientos);
			tx.commit();
			
			if(artBodegaForm.getCeldaId() != null && !artBodegaForm.getCeldaId().equals("")){
				insertaNM(artBodegaForm,movimientos,usuario,tx,request);
			}
			
		}else{
			InvBodBodegas bodegas2 = bodegasDAO.findById(bodega2);
			
			InvEboExistenciaBodega ebo1 = eboDAO.findByArticulo(bodega1, articulo).get(0);
			InvEboExistenciaBodega ebo2 = eboDAO.findByArticulo(bodega2, articulo).get(0);
			
			InvCprCapacidadProductoDAO cprDAO = new InvCprCapacidadProductoDAO(getSessionHibernate(request));
			InvCprCapacidadProductoId cprId = new InvCprCapacidadProductoId();
			cprId.setInvArtArticulo(articulo);
			cprId.setInvBodBodegas(bodegas2);
			InvCprCapacidadProducto cpr2 = cprDAO.findById(cprId);
			
			int newExistBod = ebo2.getEboCantidadProducto() + aMover;
			
			if( aMover > ebo1.getEboCantidadProducto()){
				System.out.println("bodega 1 no posee tanto inventario");
				return lista(mapping, form, request, response);
			}
			
			if(newExistBod > cpr2.getCprCantidadMaxima()){
				System.out.println("no cabe");
				return lista(mapping, form, request, response);
			}
			
			ebo2.setEboCantidadProducto(newExistBod);
			ebo2.setEboSaldo(articulo.getArtPrecioMinimo() * ebo2.getEboCantidadProducto());
			ebo2.setAudFechaModificacion(new Date());
			ebo2.setAudUsuarioModificacion(usuario);
			eboDAO.merge(ebo2);
			tx.commit();
			
			ebo1.setEboCantidadProducto(ebo1.getEboCantidadProducto() - aMover);
			ebo1.setEboSaldo(articulo.getArtPrecioMinimo() * ebo1.getEboCantidadProducto());
			ebo1.setAudFechaModificacion(new Date());
			ebo1.setAudUsuarioModificacion(usuario);
			eboDAO.merge(ebo1);
			tx.commit();
			
			InvMovMovimientos movimientos = new InvMovMovimientos();
			
			//guardando movimiento de salida
			movimientos.setInvTmoTipoMovimiento(salida);
			movimientos.setInvBodBodegas(bodegas1);
			movimientos.setInvArtArticulo(articulo);
			movimientos.setMovUnidades(aMover);
			movimientos.setMovValor(aMover*articulo.getArtPrecioMinimo());
			movimientos.setAudFechaCreacion(new Date());
			movimientos.setAudFechaModificacion(new Date());
			movimientos.setAudUsuarioCreacion(usuario);
			movimientos.setAudUsuarioModificacion(usuario);
			movimientos.setMovId(movimientosDAO.nextId());
			
			movimientosDAO.save(movimientos);
			tx.commit();
			
			//guardando movimiento de entrada
			InvMovMovimientos movimientos2 = new InvMovMovimientos();
			movimientos2.setMovId(movimientosDAO.nextId());
			movimientos2.setInvTmoTipoMovimiento(entrada);
			movimientos2.setInvBodBodegas(bodegas2);
			movimientos2.setInvArtArticulo(articulo);
			movimientos2.setMovUnidades(aMover);
			movimientos2.setMovValor(aMover*articulo.getArtPrecioMinimo());
			movimientos2.setAudFechaCreacion(new Date());
			movimientos2.setAudFechaModificacion(new Date());
			movimientos2.setAudUsuarioCreacion(usuario);
			movimientos2.setAudUsuarioModificacion(usuario);
			movimientosDAO.save(movimientos2);
			tx.commit();
			
			if(artBodegaForm.getCeldaId() != null && !artBodegaForm.getCeldaId().equals("")){
				insertaNM(artBodegaForm,movimientos2,usuario,tx,request);
			}
			
		}
		
		return lista(mapping, form, request, response);
	}

	private void insertaNM(ActionForm form,InvMovMovimientos movimientos, 
			String usuario, Transaction tx,HttpServletRequest request){
		ArtBodegaForm artBodegaForm = (ArtBodegaForm) form;
		int fila = Integer.valueOf(artBodegaForm.getCeldaId().substring(0,artBodegaForm.getCeldaId().indexOf("C")));
		int columna = Integer.valueOf(artBodegaForm.getCeldaId().substring(artBodegaForm.getCeldaId().indexOf("C")+1,artBodegaForm.getCeldaId().length()));
		InvNxmNivelMovimientoDAO nxmDAO = new InvNxmNivelMovimientoDAO(getSessionHibernate(request));
		InvNxmNivelMovimiento nxm = new InvNxmNivelMovimiento();
		nxm.setInvMovMovimientos(movimientos);
		
		InvNivNivelDAO nivelDAO = new InvNivNivelDAO(getSessionHibernate(request));
		InvNivNivelId nId = new InvNivNivelId();
		InvStnEstanteDAO estanteDAO = new InvStnEstanteDAO(getSessionHibernate(request));
		nId.setInvStnEstante(estanteDAO.findById(artBodegaForm.getEstanteId()));
		nId.setNivFilaId(fila);
		nId.setNivColId(columna);
		nxm.setInvNivNivel(nivelDAO.findById(nId));
		nxm.setNxmFecha(new Date());
		nxm.setNxmUnidades(movimientos.getMovUnidades());
		nxm.setNxmValor(movimientos.getMovValor());
		nxm.setAudFechaCreacion(new Date());
		nxm.setAudFechaModificacion(new Date());
		nxm.setAudUsuarioCreacion(usuario);
		nxm.setAudUsuarioModificacion(usuario);
		nxmDAO.save(nxm);
		tx.commit();
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
		return mapping.findForward("redirectPersona");
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.mover.lista", "lista");
		map.put("cmd.mover.move", "movimiento");
		map.put("cmd.abo.buscar", "buscar");
		map.put("cmd.asc.anular", "anular");
		map.put("cmd.asc.redirectInvalidData", "redirectInvalidData");
		map.put("cmd.asc.return","regresar");
		map.put("cmd.mover.cargarListaEstantes", "cargarListaEstantes");
		map.put("cmd.mover.cargarListaCeldas", "cargarListaCeldas");
		return map;
	}
}