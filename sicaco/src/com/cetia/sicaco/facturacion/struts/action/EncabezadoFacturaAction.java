package com.cetia.sicaco.facturacion.struts.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
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

import com.cetia.sicaco.facturacion.struts.form.EncabezadoFacturaForm;
import com.cetia.sicaco.hibernate.ConRimRetencionImpuestoDAO;
import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo;
import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamo;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccionDAO;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.CtrEstEstadoDAO;
import com.cetia.sicaco.hibernate.CtrParParametros;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.cetia.sicaco.hibernate.CtrRfcRepositorioFacturas;
import com.cetia.sicaco.hibernate.CtrRfcRepositorioFacturasDAO;
import com.cetia.sicaco.hibernate.FacCliCliente;
import com.cetia.sicaco.hibernate.FacCliClienteDAO;
import com.cetia.sicaco.hibernate.FacDfaDetalleFactura;
import com.cetia.sicaco.hibernate.FacDfaDetalleFacturaDAO;
import com.cetia.sicaco.hibernate.FacDfaDetalleFacturaId;
import com.cetia.sicaco.hibernate.FacFenFacturaEncabezado;
import com.cetia.sicaco.hibernate.FacFenFacturaEncabezadoDAO;
import com.cetia.sicaco.hibernate.FacFusFacturaUso;
import com.cetia.sicaco.hibernate.FacFusFacturaUsoDAO;
import com.cetia.sicaco.hibernate.HibernateSessionFactory;
import com.cetia.sicaco.hibernate.InvArtArticulo;
import com.cetia.sicaco.hibernate.InvArtArticuloDAO;
import com.cetia.sicaco.hibernate.InvBodBodegasDAO;
import com.cetia.sicaco.hibernate.InvCnvConversion;
import com.cetia.sicaco.hibernate.InvCnvConversionDAO;
import com.cetia.sicaco.hibernate.InvCprCapacidadProducto;
import com.cetia.sicaco.hibernate.InvCprCapacidadProductoDAO;
import com.cetia.sicaco.hibernate.InvCprCapacidadProductoId;
import com.cetia.sicaco.hibernate.InvEboExistenciaBodega;
import com.cetia.sicaco.hibernate.InvEboExistenciaBodegaDAO;
import com.cetia.sicaco.hibernate.InvEboExistenciaBodegaId;
import com.cetia.sicaco.hibernate.InvMedMedida;
import com.cetia.sicaco.hibernate.InvMedMedidaDAO;
import com.cetia.sicaco.hibernate.InvMovMovimientos;
import com.cetia.sicaco.hibernate.InvMovMovimientosDAO;
import com.cetia.sicaco.hibernate.InvPexProductosExistencia;
import com.cetia.sicaco.hibernate.InvPexProductosExistenciaDAO;
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.InvProProveedorDAO;
import com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticulo;
import com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticuloDAO;
import com.cetia.sicaco.hibernate.InvSmeSaldoMensual;
import com.cetia.sicaco.hibernate.InvSmeSaldoMensualDAO;
import com.cetia.sicaco.hibernate.InvTarTipoArticuloDAO;
import com.cetia.sicaco.hibernate.InvTmoTipoMovimientoDAO;
import com.cetia.sicaco.hibernate.SecIseInicioSesion;
import com.cetia.sicaco.hibernate.SecIseInicioSesionDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecPerPersonaDAO;
import com.cetia.sicaco.hibernate.SecSucSucursal;
import com.cetia.sicaco.hibernate.SecSucSucursalDAO;
import com.cetia.sicaco.orden.struts.action.OrdenCompraAction;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.cetia.sicaco.struts.PartidaAutomatica;
import com.mad.utilidades.ExportReport;
import com.mad.utilidades.Format;
import com.mad.utilidades.Redondeo;
import com.mad.utilidades.ReportFile;

/**
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/encabezadoFactura" name="encabezadoFacturaForm"
 *                input="redirectInvalidData" parameter="accion" scope="request"
 *                validate="true"
 * @struts.action-forward name="redirectInvalidData" path="encabezadoFactura.do"
 * @struts.action-forward name="dml"
 *                        path="pagina-dml.facturacion.encabezadoFactura"
 */
public class EncabezadoFacturaAction extends DMLAction {
	public static final String TABLA_ID = "facDfaDetalleFactura";
	public static final String LST_BOD = "bod";
	public static final String LST_PRO = "pro";
	public static final String FACTURA = "factura";
	public static final String TIPO_FACT = "tipoFact";
	public static final String ASK_BODEGA = "askBod";
	int REPOS;
	int pos;

	/*** NOTA ***/
	
	/** LA VARIABLE VFA SE UTILIZA PARA NO TENER QUE ESTAR LLAMANDO A CADA RATO LOS METODOS
	 * PARA SACAR EL TIPO DE OPERACION DEL USO DE LA FACTURA, SOLO SE DEBE REALIZAR UNA VEZ.
	 * 
	 *CUIDADO CON LOS "BEAN:MESSAGE" EN LA LLAMADA A LOS METODOS EN LA JSP. 
	 *
	 */
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm) form;
		CtrRfcRepositorioFacturasDAO repositorioDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		pos = 0;
		SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		SecIseInicioSesion inicioSesion = inicioSesionDAO.findById(encabezadoFacturaForm.getUsuarioConectado().getNombreUsuario());
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
	 	SecPerPersona persona = personaDAO.findById(inicioSesion.getSecPerPersona().getPerId());
	 	int x = 0;
	 	if(repositorioDAO.findSerie(encabezadoFacturaForm.getFenTipoFactura()/*getAck()*/, persona.getSecSucSucursal().getSucId()).size() < 1){
	 		x = -1;
	 	}else{
	 		x = repositorioDAO.findSerie(encabezadoFacturaForm.getFenTipoFactura()/*getAck()*/, persona.getSecSucSucursal().getSucId()).size();
	 	}
	 	/*
	 	if(encabezadoFacturaForm.getFenTipoFactura().equals("NC")){
	 		encabezadoFacturaForm.setFenTipoFactura("CR");
	 	}
	 	*/
	 	if(x<1	&& !encabezadoFacturaForm.getAck().equals("prov")){
			// logica de validacion si se da el caso que no tengan facturas en
			// el repositorio de facturas.
			//mensajes("errors.encabezado.serieNula", encabezadoFacturaForm, request, response);
			if(encabezadoFacturaForm.getFenTipoFactura().equals("VC")){
				encabezadoFacturaForm.setFenTipoFactura("CO");
			}
			return cancelarXfact(mapping, form, request, response,"errors.encabezado.serieNula");
		} else {
			encabezadoFacturaForm.setEsDeProv("prov");
			InvCnvConversionDAO cnvConversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
			List<?> lConv = cnvConversionDAO.findAll();
			InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
			List<?> lBod = bodegasDAO.findActivas();/****************/
			List<?> lusosFac = facturaUsoDAO.findAllV("v",7);
			List lusosFacC = facturaUsoDAO.findAllV("c",8);
			
			if (encabezadoFacturaForm.isNva()) {// si es true la factura es
												// nueva
				encabezadoFacturaForm = inicializarFactura(encabezadoFacturaForm,request);
//		Validacion en el caso que no exista repositorio para entrar en el
				
				if(encabezadoFacturaForm == null){
					return lista(mapping, form, request, response);
				}
				request.getSession().setAttribute("encabezadoFacturaForm",
						encabezadoFacturaForm);
			} else {
				if(!encabezadoFacturaForm.getAck().equals("NC") && !encabezadoFacturaForm.getAck().equals("ND")){
					encabezadoFacturaForm = (EncabezadoFacturaForm) request
					.getSession().getAttribute("encabezadoFacturaForm");
				}else{
					FacFenFacturaEncabezadoDAO encabezadoDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
					//Aplicar uno por uno el encabezado al encabezado h
					FacFenFacturaEncabezado original = encabezadoDAO.findById(encabezadoFacturaForm.getFenId());
					encabezadoFacturaForm.setInvBodBodegas(original.getInvBodBodegas());
					encabezadoFacturaForm.setCtaAscAsociado(original.getCtaAscAsociado());
					encabezadoFacturaForm.setFacCliCliente(original.getFacCliCliente());
					encabezadoFacturaForm.setFacDfaDetalleFacturas(original.getFacDfaDetalleFacturas());
					//encabezadoFacturaForm.setFacturaEncabezadoH(encabezadoDAO.findById(encabezadoFacturaForm.getFenId()));
					
					if(repositorioDAO.findSerie(encabezadoFacturaForm.getAck(),inicioSesionDAO.findById(encabezadoFacturaForm.getUsuarioConectado().getNombreUsuario()).getSecPerPersona().getSecSucSucursal().getSucId()).size() > 0){
						Transaction tx = repositorioDAO.getSession().beginTransaction();
						CtrRfcRepositorioFacturas repositorio = (CtrRfcRepositorioFacturas) repositorioDAO.findSerie(encabezadoFacturaForm.getAck(),inicioSesionDAO.findById(encabezadoFacturaForm.getUsuarioConectado().getNombreUsuario()).getSecPerPersona().getSecSucSucursal().getSucId() ).get(0);
						tx.commit();
						repositorioDAO.getSession().flush();
						repositorioDAO.getSession().clear();
						encabezadoFacturaForm.setFenOriginal(original.getFenId());
						encabezadoFacturaForm.setFenSerieFacturaO(original.getFenSerieFactura());
						encabezadoFacturaForm.setFenNumeroFacturaO(original.getFenNumeroFactura());
						encabezadoFacturaForm.setFenSerieFactura(repositorio.getCtrCfcControlFacturacion().getCfcSerie());
						encabezadoFacturaForm.setFenNumeroFactura(Format.formatNumeroFactura(repositorio.getRfcCorrActual(), repositorio.getCtrCfcControlFacturacion().getCfcDigitos()));
						encabezadoFacturaForm = calcularTotalForm(encabezadoFacturaForm,request);
						request.getSession().setAttribute("encabezadoFacturaForm", encabezadoFacturaForm);
						request.setAttribute("detalleOrden", 4);
					}else{
						//mensajes("errors.encabezado.serieNula", encabezadoFacturaForm, request, response);
						//request.setAttribute("detalleOrden", 4);
						return cancelarXfact(mapping, form, request, response,"errors.encabezado.serieNula");
					}
				}
			}
			// Construyendo Jmesa
			TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
			tableFacade.setItems(encabezadoFacturaForm.getFacDfaDetalleFacturas());
			// ---- Genera los tipos de formas con que se podran exportar los
			// datos
			tableFacade.setExportTypes(response, ExportType.CSV,
					ExportType.JEXCEL);// , ExportType.PDF);
			tableFacade.setStateAttr("restore");
			Limit limit = tableFacade.getLimit();
			if (limit.isExported()) {
				// ---- exporta la tabla
				if(encabezadoFacturaForm.getAck().equals("ND") || encabezadoFacturaForm.getAck().equals("NC")){
					exportNotas(tableFacade,request);
				}else{
					export(tableFacade,request);
				}
				// export(tableFacade);
				return null;
			} else {
				// ---- genera el html de la tabla para ser mostrada
				if(encabezadoFacturaForm.getAck().equals("ND") || encabezadoFacturaForm.getAck().equals("NC")){
					request.setAttribute(Constantes.LISTA_KEY, htmlNotas(tableFacade,
							request, encabezadoFacturaForm, ""));
				}else{
					request.setAttribute(Constantes.LISTA_KEY, html(tableFacade,
							request, encabezadoFacturaForm, ""));
				}
			}
			request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
			request.setAttribute("modo", "aut");
			request.setAttribute("listaConversiones", lConv);
			request.setAttribute("listaBodegas", lBod);
			request.setAttribute("listaFacturaUso", lusosFac);
			request.setAttribute("listaFacturaUsoC", lusosFacC);
			request.setAttribute("ack", encabezadoFacturaForm.getAck());
			if(encabezadoFacturaForm.getFacFusFacturaUso().getFusId()!= null){
				encabezadoFacturaForm.setFuso(encabezadoFacturaForm.getFacFusFacturaUso().getFusId());
			}
			encabezadoFacturaForm.setDfaCantidad(null);
			encabezadoFacturaForm.setDfaPrecioUnitario(null);
			request.setAttribute("form", encabezadoFacturaForm);
		}
		return mapping.findForward("lista");
	}

	public ActionForward recalcularPrecio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm) form;
			InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
			InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
			InvArtArticulo articulo = new InvArtArticulo();
			articulo = articuloDAO.findById(encabezadoFacturaForm.getArtCodigo());
			articulo.setArtPrecioSugerido(articulo.getArtPrecioSugerido()*conversionDAO.findById(encabezadoFacturaForm.getCnvId()).getCnvFactor());
			String tag = "<html:text property=\"dfaPrecioUnitario\" styleClass=\"obligatorio\" size=\"15\" maxlength=\"15\" styleId=\"precioUnitario\" value=\""+articulo.getArtPrecioMinimo()+"\"/>";
			try {
				response.getWriter().write(tag);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	private EncabezadoFacturaForm inicializarFactura(EncabezadoFacturaForm encabezadoFacturaForm,HttpServletRequest request) {
		if (encabezadoFacturaForm.getAck().equals("prov")) {
			encabezadoFacturaForm.setFenSerieFactura("0000000000");
			encabezadoFacturaForm.setFenNumeroFactura(null);
		} else {
			SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
			CtrRfcRepositorioFacturasDAO repositorioDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
			if(repositorioDAO.findSerie(encabezadoFacturaForm.getFenTipoFactura(),inicioSesionDAO.findById(encabezadoFacturaForm.getUsuarioConectado().getNombreUsuario()).getSecPerPersona().getSecSucSucursal().getSucId()).size() < 1){
				return null;
			}
			CtrRfcRepositorioFacturas repositorio = (CtrRfcRepositorioFacturas) repositorioDAO.findSerie(encabezadoFacturaForm.getFenTipoFactura(),inicioSesionDAO.findById(encabezadoFacturaForm.getUsuarioConectado().getNombreUsuario()).getSecPerPersona().getSecSucSucursal().getSucId() ).get(0);
			encabezadoFacturaForm.setFenSerieFactura(repositorio.getCtrCfcControlFacturacion().getCfcSerie());
			encabezadoFacturaForm.setFenNumeroFactura(Format.formatNumeroFactura(repositorio.getRfcCorrActual(), repositorio.getCtrCfcControlFacturacion().getCfcDigitos()));
		}
		encabezadoFacturaForm.setFenFechaFactura(new Date());
		encabezadoFacturaForm.setNva(false);
		encabezadoFacturaForm.setFenTotalVentasExentas(0.00);
		encabezadoFacturaForm.setFenTotalVenta(0.00);// sumatoria de todo el
														// total
		encabezadoFacturaForm.setFenIvaRetenido(0.00);
		return encabezadoFacturaForm;
	}

	// ---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade,
			final HttpServletRequest request,EncabezadoFacturaForm form, final String view) {
		tableFacade.setColumnProperties("id.invArtArticulo.artCodigo",
				"dfaCantidad", "cnvId", "dfaDescripcion", "dfaPrecioUnitario",
				"dfaExento", "dfaPrecioTotal", "audUsuarioCreacion");
		Table table = tableFacade.getTable();
		// ---- Titulo de la tabla

		Row row = table.getRow();

		Column nombreColumna = row.getColumn("id.invArtArticulo.artCodigo");
		nombreColumna.setTitleKey("tbl.detalle.id.invArtArticulo.artCodigo");

		nombreColumna = row.getColumn("dfaDescripcion");
		nombreColumna.setTitleKey("tbl.detalle.dfaDescripcion");

		nombreColumna = row.getColumn("dfaCantidad");
		nombreColumna.setTitleKey("tbl.detalle.dfaCantidad");

		nombreColumna = row.getColumn("cnvId");
		nombreColumna.setTitleKey("tbl.detalle.cnvNombreMedida");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				if(detalle.getCnvId() == -2){
					InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
					InvArtArticulo articulo = articuloDAO.findById(detalle.getId().getInvArtArticulo().getArtCodigo());
					
					InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
					InvMedMedida medida = medidaDAO.findById(articulo.getInvMedMedida().getMedId());
					value = medida.getMedNombreMedida();
				}else{
					value = conversionDAO.findById(detalle.getCnvId())
					.getCnvNombreMedida();
				}
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaPrecioUnitario");
		nombreColumna.setTitleKey("tbl.detalle.dfaPrecioUnitario");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				
				//value = Redondeo.dRound(detalle.getDfaPrecioUnitario(), 2);
				value = "<div align=\"right\">"+Format.formatDinero(detalle.getDfaPrecioUnitario())+"</div>";
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaExento");
		nombreColumna.setTitleKey("tbl.detalle.dfaExento");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				if (detalle.getDfaExento() == 1) {
					value = "Si";
				} else {
					value = "No";
				}
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaPrecioTotal");
		nombreColumna.setTitleKey("tbl.detalle.dfaPrecioTotal");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				
				//value = Redondeo.dRound(detalle.getDfaPrecioTotal(), 2);
				value = "<div align=\"right\">"+Format.formatDinero(detalle.getDfaPrecioTotal())+"</div>";
				return value;
			}
		});

		nombreColumna = row.getColumn("audUsuarioCreacion");
		nombreColumna.setTitleKey("tbl.detalle.eliminar");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				HtmlBuilder html = new HtmlBuilder();
				/*if(view !=null || !view.trim().equals("")){
					return "";
				}*/
				String del = "Eliminar";
				String link = tableFacade.getWebContext().getContextPath();
				link += "/facturacion/encabezadoFactura.do?artCodigo="
						+ detalle.getId().getInvArtArticulo().getArtCodigo()
						+ "&accion=delete";
				html.a().href().quote().append(link).quote().append("class=\"linkEliminar\"").title(del).close();
				//html.append(del);
				html.aEnd();
				return html.toString();
			}
		});
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade,final HttpServletRequest request) {
		 tableFacade.setColumnProperties("id.invArtArticulo.artCodigo",
					"dfaCantidad", "cnvId", "dfaDescripcion", "dfaPrecioUnitario",
					"dfaExento", "dfaPrecioTotal");
			Table table = tableFacade.getTable();
			// ---- Titulo de la tabla
			table.setCaptionKey("tbl.detalle.caption");
			
			Row row = table.getRow();

			Column nombreColumna = row.getColumn("id.invArtArticulo.artCodigo");
			nombreColumna.setTitleKey("tbl.detalle.id.invArtArticulo.artCodigo.x");

			nombreColumna = row.getColumn("dfaDescripcion");
			nombreColumna.setTitleKey("tbl.detalle.dfaDescripcion.x");

			nombreColumna = row.getColumn("dfaCantidad");
			nombreColumna.setTitleKey("tbl.detalle.dfaCantidad");

			nombreColumna = row.getColumn("cnvId");
			nombreColumna.setTitleKey("tbl.detalle.cnvNombreMedida");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					if(detalle.getCnvId() == -2){
						InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
						InvArtArticulo articulo = articuloDAO.findById(detalle.getId().getInvArtArticulo().getArtCodigo());
						
						InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
						InvMedMedida medida = medidaDAO.findById(articulo.getInvMedMedida().getMedId());
						value = medida.getMedNombreMedida();
					}else{
						value = conversionDAO.findById(detalle.getCnvId())
						.getCnvNombreMedida();
					}
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaPrecioUnitario");
			nombreColumna.setTitleKey("tbl.detalle.dfaPrecioUnitario");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					
					value = Format.formatDinero(detalle.getDfaPrecioUnitario());
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaExento");
			nombreColumna.setTitleKey("tbl.detalle.dfaExento");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					if (detalle.getDfaExento() == 1) {
						value = "Si";
					} else {
						value = "No";
					}
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaPrecioTotal");
			nombreColumna.setTitleKey("tbl.detalle.dfaPrecioTotal");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					
					value = Format.formatDinero(detalle.getDfaPrecioTotal());
					return value;
				}
			});

		tableFacade.render();
	}

	public ActionForward cargarListaProveedor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) { // se utiliza para realizar la
											// busqueda de los proveedores
		InvProProveedorDAO proProveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		InvProProveedor proveedor = new InvProProveedor();
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm) form;
		List<InvProProveedor> listaProveedores = null;
		proveedor.setProCodigo(encabezadoFacturaForm.getProCodigo());
		proveedor.setProNombre(encabezadoFacturaForm.getProNombre());
		try {
			listaProveedores = proProveedorDAO.findByCriteria(proveedor);
			String listaResponse;
			if(proProveedorDAO.findAll().size()<1){
				listaResponse = "<table id=\"resultadoArt\">";
				listaResponse += "<tr>";
				listaResponse += "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
						+ "No existe ning&uacute;n proveedor en el sistema</span></td>";
				listaResponse += "</tr></table>";
			}else{
				if(listaProveedores.size() < 1){
					listaResponse = "<table id=\"resultadoArt\">";
					listaResponse += "<tr>";
					listaResponse += "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
							+ "La b&uacute;squeda no gener&oacute; ning&uacute;n resultado</span></td>";
					listaResponse += "</tr></table>";
				}else{
					// Construimos una lista para el response
					listaResponse = contruirListaProveedores(listaProveedores);
				}
			}
			response.setCharacterEncoding("UTF-8");			
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

	private String contruirListaProveedores(
			List<InvProProveedor> listaProveedores) {
		String lista = "<table id=\"resultadoProv\">";
		int max = 0;
		for (Iterator iterator = listaProveedores.iterator(); iterator
				.hasNext();) {
			InvProProveedor proProveedor = (InvProProveedor) iterator.next();
			if(max < 10){
				lista += "<tr>";
				lista += "<td><input onclick=\"JavaScript:saveSeleccionP(this.value);\" type=\"radio\" name=\"_miseleccion\" value=\""
						+ proProveedor.getProCodigo()
						+ ";"
						+ proProveedor.getProNombre()
						+ ";"
						+ proProveedor.getProRegistro()
						+ ";"
						+ proProveedor.getProId()
						+ ";"
						+ proProveedor.getProNit() + "\"/></td>";
				lista += "<td><span style=\"font-size: 10px;color: #6E6E6E;font-style: italic;\">"
						+ proProveedor.getProNombre() + "</span></td>";
				lista += "</tr>";
			}
			max++;
		}
		lista += "</table>";
		return lista;
	}

	public ActionForward cargarListaArticulos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) { // se utiliza para realizar la
											// busqueda de los articulos
		InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		InvArtArticulo articulo = new InvArtArticulo();
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm) form;
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) request.getSession().getAttribute("encabezadoFacturaForm");
		List<InvArtArticulo> listaArticulos = null;
		articulo.setArtCodigo(encabezadoFacturaForm.getArtCodigo());
		articulo.setArtNombre(encabezadoFacturaForm.getArtNombre());
		
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		List cnv = conversionDAO.findByMedId(articulo.getInvMedMedida().getMedId());
		request.setAttribute("listaConversiones", cnv);
		if (existenciaFactura(articulo.getArtCodigo(), facturaForm
				.getFacDfaDetalleFacturas())) {
			String listaResponse = "<table id=\"resultadoArt\">";
			listaResponse += "<tr>";
			listaResponse += "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">El art&iacute;culo "
					+ articulo.getArtNombre()
					+ " ya esta presente en la factura</span></td>";
			listaResponse += "</tr></table>";
			try {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(listaResponse);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				String listaResponse = "";
				int noEncuentraProv = -1;
				if(encabezadoFacturaForm.getIdProveedor() != null && encabezadoFacturaForm.getIdProveedor() > 0){
					InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
					InvProProveedor proveedor = proveedorDAO.findById(encabezadoFacturaForm.getIdProveedor());
					listaArticulos = articuloDAO.findByCriteria(articulo);
					ArrayList listaNuevaArt = new ArrayList();
					InvPxtProveedorxtipoArticuloDAO pxtDAO = new InvPxtProveedorxtipoArticuloDAO(getSessionHibernate(request));
					InvTarTipoArticuloDAO tipoArticuloDAO = new InvTarTipoArticuloDAO(getSessionHibernate(request));
					for (Iterator iterator = listaArticulos.iterator(); iterator.hasNext();) {
						InvArtArticulo articulo2 = (InvArtArticulo) iterator.next();
						InvPxtProveedorxtipoArticulo pxt = new InvPxtProveedorxtipoArticulo();
						pxt.setInvProProveedor(proveedor);
						pxt.setInvTarTipoArticulo(tipoArticuloDAO.findById(articulo2.getInvTarTipoArticulo().getTarId()));
						if(pxtDAO.findByProAndTar(pxt).size()>0){
							listaNuevaArt.add(articulo2);
						}
					}
					listaArticulos = listaNuevaArt;
				}else{
					if(encabezadoFacturaForm.getIdProveedor() != null){
						noEncuentraProv = 1;
					}
					listaResponse = "<table id=\"resultadoArt\">";
					listaResponse += "<tr>";
					listaResponse += "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
							+ "Ningun proveedor ha sido seleccionado</span></td>";
					listaResponse += "</tr></table>";
				}
				
				if(noEncuentraProv == -1){
					if(articuloDAO.findAll().size()<1){
						listaResponse = "<table id=\"resultadoArt\">";
						listaResponse += "<tr>";
						listaResponse += "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
								+ "No existe ning&uacute;n art&iacute;culo en inventario</span></td>";
						listaResponse += "</tr></table>";
					}else{
						// Construimos una lista para el response
						listaArticulos = articuloDAO.findByCriteria(articulo);
						if(listaArticulos.size() < 1){
							listaResponse = "<table id=\"resultadoArt\">";
							listaResponse += "<tr>";
							listaResponse += "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
									+ "La b&uacute;squeda no gener&oacute; ning&uacute;n resultado</span></td>";
							listaResponse += "</tr></table>";
						}else{
							listaResponse = contruirListaArticulos(listaArticulos,
									encabezadoFacturaForm,request);
						}
					}
				}
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(listaResponse);
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

	private boolean existenciaFactura(String codigoArticulo, Set listArticulos) {// verifica si ya se ha agrego al
	// menos una vez/ un articulo a la factura
		for (Iterator iterator = listArticulos.iterator(); iterator.hasNext();) {
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator
					.next();
			if (detalleFactura.getId().getInvArtArticulo().getArtCodigo()
					.equals(codigoArticulo))
				return true;
		}
		return false;
	}

	private String contruirListaArticulos(List<InvArtArticulo> listaArticulos,
			EncabezadoFacturaForm facForm, HttpServletRequest request) {
		String lista = "<table id=\"resultadoArt\">";
		String color = "#6E6E6E";
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		int max =0;
/*		double factor = conversionDAO.findById(facForm.getCnvId())
				.getCnvFactor();*/
		for (Iterator iterator = listaArticulos.iterator(); iterator.hasNext();) {
			InvArtArticulo articulo = (InvArtArticulo) iterator.next();
			InvEboExistenciaBodegaDAO eboDao = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
			Integer cantidad = eboDao.existenciaPorBodegaYArticulo(facForm.getBod(), articulo.getArtCodigo());
			if(max<10){
			lista += "<tr>";
			lista += "<td><input onclick=\"JavaScript:saveSeleccionA(this.value, '" + articulo.getInvMedMedida().getMedId() + "','"+articulo.getArtCodigo()+"');\" type=\"radio\" name=\"_miseleccion\" value=\""
					+ articulo.getArtCodigo()
					+ ";"
					+ articulo.getArtNombre()
					//+ ";"
					//+ Double.parseDouble(Redondeo.dRound(articulo.getArtPrecioSugerido(),2))* factor
					+ "\"/></td>";
			if(tipoPrestamoDAO.findByTar(articulo.getInvTarTipoArticulo().getTarId()) == null){
				color = "red";
			}
			lista += "<td><span style=\"font-size: 10px;color:"+ color +" ;font-style: italic;\">"
					+ articulo.getArtNombre()
					+ " - Disponible: "
					+ cantidad
					+ "</span></td>";
			lista += "</tr>";
			}
			max++;
		}
		lista += "</table>";
		return lista;
	}

	public ActionForward cargarListaClientes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoForm = (EncabezadoFacturaForm) form;
		List<FacCliCliente> listaClientes = null;
		List listaAsociados = null;
		FacCliClienteDAO cliClienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
		
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		FacCliCliente cliente = new FacCliCliente();
		CtaAscAsociado asociado = new CtaAscAsociado();
		cliente.setCliCodigo(encabezadoForm.getCodCli());
		cliente.setCliNombre(encabezadoForm.getNombreCli());

		if(encabezadoForm.getAscEmp() == 2){
			asociado.setAscCodigo(encabezadoForm.getCodCli());
		}else{
			asociado.setAscCodigoAsociado(encabezadoForm.getCodCli());
		}		
		//asociado.setAscCodigo(encabezadoForm.getCodCli());
		
		int opcion = 0; //si es uno quiere decir que probo con el nombre solamente y no encontro nada
		if(encabezadoForm.getNombreCli() != null && !encabezadoForm.getNombreCli().trim().equals("")){
			if(encabezadoForm.getNombreCli().trim().indexOf(",") == -1){
				if(encabezadoForm.getNombreCli().trim().indexOf(" ") == -1){
					asociado.getSecPerPersona().setPerPrimerNombre(encabezadoForm.getNombreCli().trim());
					opcion = 1;
				}else{
					StringTokenizer tokenizer = new StringTokenizer(encabezadoForm.getNombreCli().trim());
					asociado.getSecPerPersona().setPerPrimerNombre(tokenizer.nextToken().trim());
					asociado.getSecPerPersona().setPerPrimerApellido(tokenizer.nextToken().trim());
				}
			}else{
				if(encabezadoForm.getNombreCli().trim().startsWith(",")){
					asociado.getSecPerPersona().setPerPrimerApellido("");
					asociado.getSecPerPersona().setPerPrimerNombre(encabezadoForm.getNombreCli().substring(1).trim());
				}else{
					StringTokenizer tokenizer = new StringTokenizer(encabezadoForm.getNombreCli().trim(),",");
					asociado.getSecPerPersona().setPerPrimerApellido(tokenizer.nextToken().trim());
					asociado.getSecPerPersona().setPerPrimerNombre(tokenizer.nextToken().trim());
				}
			}
		}else{
			asociado.getSecPerPersona().setPerPrimerNombre(encabezadoForm.getNombreCli().trim());
		}
		
		SecPerPersona persona = new SecPerPersona();
		persona.setPerPrimerNombre(encabezadoForm.getNombreCli().trim());
		
		try {
			listaClientes = cliClienteDAO.findByCriteria(cliente,10,0);
			listaAsociados = asociadoDAO.findByNameUser(asociado,10);
			Boolean nulo = false;
			if(listaClientes.size() < 1 && listaAsociados.size() < 1){
				if(opcion == 1){
					asociado.getSecPerPersona().setPerPrimerApellido(encabezadoForm.getNombreCli().trim());
					asociado.getSecPerPersona().setPerPrimerNombre(null);
					listaAsociados = asociadoDAO.findByNameUser(asociado,10);
					if(listaAsociados.size()<1){
						nulo = true;
					}
				}else{
					nulo = true;
				}
			}
			String listaResponse = construirListaClientes(listaClientes,
					listaAsociados, nulo,request);
			response.setCharacterEncoding("UTF-8");
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
	
	public ActionForward cargarListaContribuyentes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoForm = (EncabezadoFacturaForm) form;
		List<FacCliCliente> listaClientes = null;
		FacCliClienteDAO cliClienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
		
		FacCliCliente cliente = new FacCliCliente();
		cliente.setCliCodigo(encabezadoForm.getCodCli());
		cliente.setCliNombre(encabezadoForm.getNombreCli().trim());
		
		try {
			listaClientes = cliClienteDAO.findByCriteriaAndContribuyente(cliente);
			Boolean nulo = false;
			if(listaClientes.size() < 1 ){
				nulo = true;
			}
			String listaResponse = construirListaClientes(listaClientes,
					null, nulo,request);
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

	private String construirListaClientes(List<FacCliCliente> listaClientes,
			List<CtaAscAsociado> listaAsociados, Boolean nulo, HttpServletRequest request) {
		String lista = "<table id=\"resultadoCli\">";
		if(nulo == true){
			lista+= "<td><span style=\"font-size: 10px;color: red;font-style: italic;\">"
			+ "No se encontro ningun cliente o asociado en el sistema para esta b&uacute;squeda</span></td>";
		}else{
			int max1 = 0;
			int max2 = 0;
			int x = 5;
			if(listaAsociados == null){
				x = 10;
			}
			for (Iterator iterator = listaClientes.iterator(); iterator.hasNext();) {
				FacCliCliente cliente = (FacCliCliente) iterator.next();
				if(max1<x){
					lista += "<tr>";
					lista += "<td><input onclick=\"JavaScript:saveSeleccionC(this.value);\" type=\"radio\" name=\"_miseleccion\" value=\""
							+ cliente.getCliCodigo()
							+ ";"
							+ cliente.getCliNombre()
							+";"
							+ cliente.getCliCodigo()
							+";"
							+ cliente.getCliNumRegistro()
							+";"
							+ cliente.getCliContribuyente()
							+";"
							+ "C"
							+ "\"/></td>";
					lista += "<td><span style=\"font-size: 10px;color: #6E6E6E;font-style: italic;\">"
							+ cliente.getCliNombre() + "</span></td>";
					lista += "</tr>";
				}
				max1++;
			}
			if(listaAsociados != null){
				for (Iterator iterator = listaAsociados.iterator(); iterator.hasNext();) {
					//SecAscAsociado asociado = (SecAscAsociado) iterator.next();
					CtaAscAsociado asociado = (CtaAscAsociado)iterator.next();
					CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
					
					//Obtener disponible
					CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
					Double creditoUsado = prestamoDAO.sumCreditoUtilizado(asociado.getAscId(),"F");
					if(creditoUsado == null){
						creditoUsado = 0.0;
					}
					CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
					CtrParParametros creditoOrden = parametrosDAO.findById("CREDITO_FACT");
					Double disponible = creditoOrden.getParValorNumber() - creditoUsado;
					
					CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
					//CtaPrePrestamo prestamo = cuentaAsociadoDAO.findCredito(asociado.getAscId());
					if(max2<5){
						lista += "<tr>";
						lista += "<td><input onclick=\"JavaScript:saveSeleccionC(this.value);\" type=\"radio\" name=\"_miseleccion\" value=\""
								+ asociado.getAscCodigo()
								+ ";"
								+ asociado.getSecPerPersona().getPerPrimerNombre()
								+ " "
								+ asociado.getSecPerPersona().getPerPrimerApellido()
								+ ";"
								+ asociado.getAscId()
								+";"
								+" "
								+";"
								+" "
								+";"
								+ "A"
								+ "\"/></td>";
						if(asociado.getEstId()==21){
							lista += "<td><span style=\"font-size: 10px;color: #CC3333;font-style: italic;\">"
								+ "Restringido - " 
								+ asociado.getSecPerPersona().getPerPrimerNombre()
								+ " "
								+ asociado.getSecPerPersona().getPerSegundoNombre()
								+ " "
								+ asociado.getSecPerPersona().getPerPrimerApellido()
								+ " "
								+ asociado.getSecPerPersona().getPerSegundoApellido()
								+ "</span></td>";
							lista += "</tr>";
						}else{
							lista += "<td><span style=\"font-size: 10px;color: #6E6E6E;font-style: italic;\">"
									+ asociado.getSecPerPersona().getPerPrimerNombre()
									+ " "
									+ asociado.getSecPerPersona().getPerSegundoNombre()
									+ " "
									+ asociado.getSecPerPersona().getPerPrimerApellido()
									+ " "
									+ asociado.getSecPerPersona().getPerSegundoApellido()
									+ " - "
									+ estadoDAO.findById(asociado.getEstId()).getEstNombre()
									+ " - Credito disponible: </span><span style=\"font-size: 10px;color: #02BB68;font-style: italic;\">"
									+ Format.formatDinero(disponible)
									+ "</td>";
							lista += "</tr>";
							/*
							 * + " - Credito disponible: </span><span style=\"font-size: 10px;color: #02BB68;font-style: italic;\">"
									+ Format.formatDinero(prestamo.getPreSaldoActualT())
							 */
						}
					}
					max2++;
				}
			}
		}
		lista += "</table>";
		return lista;
	}

	public ActionForward agregarDetalle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {// agrega detalles a la factura
		ActionForward target = null;
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) form;
		// Formulario que viene en el request
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm) request
				.getSession().getAttribute("encabezadoFacturaForm");
		if(existenciaFactura(facturaForm.getArtCodigo(), encabezadoFacturaForm.getFacDfaDetalleFacturas()) == true){
			mensajes("errors.encabezado.detalleRepetido", encabezadoFacturaForm, request, response);
			return lista(mapping, form, request, response);
		}
		Boolean validado = true;
		validado = validarAgregar(facturaForm, request, response, encabezadoFacturaForm.getFacDfaDetalleFacturas().size());
		if(validado == false){
			encabezadoFacturaForm.setArtCodigo(facturaForm.getArtCodigo());
			encabezadoFacturaForm.setArtNombre(facturaForm.getArtNombre());
			encabezadoFacturaForm.setCnvId(facturaForm.getCnvId());
			return lista(mapping, form, request, response);
		}
		
		encabezadoFacturaForm.setCodCli(facturaForm.getCodCli());
		encabezadoFacturaForm.setNombreCli(facturaForm.getNombreCli());
		encabezadoFacturaForm.setIdProveedor(facturaForm.getIdProveedor());
		encabezadoFacturaForm.setFenFechaFactura(facturaForm.getFenFechaFactura());
		//encabezadoFacturaForm.setFenTipoPago(facturaForm.getFenTipoPago());
		
		//disponible
		Double disponible = 0.0;
		Integer declaraIva = new Integer(-1);
		//Union con cliente
		if(facturaForm.getAoc() != null && !facturaForm.getAoc().equals("")){
			if(facturaForm.getAoc().equals("C")){
				/*if(facturaForm.getTipoPagoId().equals("C")){
					mensajes("error.encabezado.clienteNoCredito", facturaForm, request, response);
					return lista(mapping, encabezadoFacturaForm, request, response);
				}*/
				FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
				FacCliCliente cliente = clienteDAO.findById(facturaForm.getCodigo());
				declaraIva = cliente.getCliDeclaraIva();
				encabezadoFacturaForm.setFacCliCliente(cliente);
				encabezadoFacturaForm.setNumeroContribuyente(cliente.getCliContribuyente());
				encabezadoFacturaForm.setRegistroContribuyente(cliente.getCliNumRegistro());
				encabezadoFacturaForm.setCtaAscAsociado(null);
				encabezadoFacturaForm.setAoc("C");
			}
			if(facturaForm.getAoc().equals("A")){
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
				CtaAscAsociado asociado = asociadoDAO.findById(facturaForm.getCodigo());
				if(asociado == null || asociado.getAscId() == null){
					mensajes("error.encabezado.cargueAsociado", facturaForm, request, response);
					return lista(mapping, encabezadoFacturaForm, request, response);
				}
				if(asociado.getEstId() == 21){
					mensajes("error.encabezado.asociadoRestringido", facturaForm, request, response);
					return lista(mapping, encabezadoFacturaForm, request, response);
				}
				
				if(facturaForm.getTipoPagoId().equals("C")){
					//Obtener disponible
					CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
					Double creditoUsado = prestamoDAO.sumCreditoUtilizado(asociado.getAscId(),"F");
					if(creditoUsado == null){
						creditoUsado = 0.0;
					}
					CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
					CtrParParametros creditoOrden = parametrosDAO.findById("CREDITO_FACT");
					disponible = creditoOrden.getParValorNumber() - creditoUsado;
					
					if(disponible == 0){
						mensajes("error.encabezado.masDeLoQuePuede", facturaForm, request, response);
						return lista(mapping, encabezadoFacturaForm, request, response);
					}
				}
				encabezadoFacturaForm.setCtaAscAsociado(asociado);
				encabezadoFacturaForm.setFacCliCliente(null);
				encabezadoFacturaForm.setAoc("A");
			}
		}
		
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		FacDfaDetalleFacturaId facturaId = new FacDfaDetalleFacturaId();
		facturaForm.getDetalleFacturaH().setDfaPrecioTotal(0.00);
		// Construyendo el detalle
		InvArtArticulo artArticulo = articuloDAO.findById(facturaForm
				.getArtCodigo());
		if(facturaForm.getTipoPagoId() != null && facturaForm.getTipoPagoId().equals("C") && !facturaForm.getFenTipoFactura().equals("CR")){
			CtaTprTipoPrestamoDAO tprDao = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
			CtaTprTipoPrestamo tpr = tprDao.findByTar(artArticulo.getInvTarTipoArticulo().getTarId());
			if(tpr==null || tpr.getTprId() == null){
				mensajes("error.encabezado.noTieneUnionTPR", facturaForm, request, response);
				return lista(mapping, encabezadoFacturaForm, request, response);
			}
		}
		FacDfaDetalleFactura detalleFactura = facturaForm.getDetalleFacturaH();
		
		facturaId.setInvArtArticulo(artArticulo);
		detalleFactura.setId(facturaId);
		detalleFactura.setDfaDescripcion(artArticulo.getArtNombre());
		detalleFactura.setFacFenFacturaEncabezado(encabezadoFacturaForm.getFacturaEncabezadoH());
		detalleFactura.setAudFechaCreacion(new Date());
		detalleFactura.setAudFechaModificacion(new Date());
		detalleFactura.setAudUsuarioCreacion(encabezadoFacturaForm.getUsuarioConectado().getNombreUsuario());
		detalleFactura.setAudUsuarioModificacion(encabezadoFacturaForm.getUsuarioConectado().getNombreUsuario());
		
		Boolean venOComp = ventaOCompra(encabezadoFacturaForm.getAck());
		
		Boolean verSum=false;
		if(detalleFactura.getCnvId()!=-1){
			
			if(detalleFactura.getCnvId()==-2){
				verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
						facturaForm.getInvBodBodegas().getBodId(),
						detalleFactura.getDfaCantidad(),
						venOComp,request);
			}else{
				verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
						facturaForm.getInvBodBodegas().getBodId(),
						(detalleFactura.getDfaCantidad() * conversionDAO.findById(detalleFactura.getCnvId()).getCnvFactor()),
						venOComp,request);
			}
		
			if (verSum) {
				// validacion para avisar que la cantidad maxima de existencia esta
				// siendo sobrepasada
				if(encabezadoFacturaForm.getCtaAscAsociado() != null 
						&& encabezadoFacturaForm.getCtaAscAsociado().getAscId()!=null 
						&& facturaForm.getTipoPagoId().equals("C")){
					Double totalDetalle = 0.0;
					/*if(detalleFactura.getCnvId() != -2){
						InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
						totalDetalle = (detalleFactura.getDfaCantidad()*conversion.getCnvFactor())*detalleFactura.getDfaPrecioUnitario();
					}else{*/
						totalDetalle = detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad();
					/*}*/
					if(detalleFactura.getDfaExento() != 1){
						ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO(getSessionHibernate(request));
						double impuesto = impuestoDAO.findById(1).getRimImpuesto() / 100;
						totalDetalle = totalDetalle * (1 + impuesto);
					}
					if((encabezadoFacturaForm.getTotalFactura() + totalDetalle)>disponible){
						mensajes("error.encabezado.masDeLoQuePuede", encabezadoFacturaForm, request, response);
						return lista(mapping, encabezadoFacturaForm, request, response);
					}
				}
				
				/*Codigo nuevo para calcular el iva*/
				ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO(getSessionHibernate(request));
				double impuesto = impuestoDAO.findById(1).getRimImpuesto()/100;
				double precioUnitarioExento =0;
				if(facturaForm.getFenTipoFactura().equals("CO")){
					//double precioUnitarioExento = detalleFactura.getDfaPrecioUnitario()/(impuesto+1);
					if (detalleFactura.getDfaExento().intValue() == 1){
						precioUnitarioExento = 
							Double.parseDouble(Redondeo.dRound(detalleFactura.getDfaPrecioUnitario(), 5));	
					}else{
					precioUnitarioExento = 
						Double.parseDouble(Redondeo.dRound(detalleFactura.getDfaPrecioUnitario()/(impuesto+1), 5));
					}
					double precioTotalAfecto = precioUnitarioExento*detalleFactura.getDfaCantidad();
					detalleFactura.setDfaPrecioUnitario(Double.parseDouble(Redondeo.dRound(precioUnitarioExento, 5)));
					detalleFactura.setDfaPrecioTotal(Double.parseDouble(Redondeo.dRound(precioTotalAfecto, 5)));
					//detalleFactura.setDfaPrecioTotal(detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad());
				}
				
				if(facturaForm.getFenTipoFactura().equals("VC")){
					FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
					FacCliCliente cliente = clienteDAO.findById(encabezadoFacturaForm.getCodCli());
					
					if(cliente.getCliContribuyente() != null){
						
						if(cliente.getCliDeclaraIva() == 0){
							if (detalleFactura.getDfaExento().intValue() == 1){
								precioUnitarioExento = 
									Double.parseDouble(Redondeo.dRound(detalleFactura.getDfaPrecioUnitario(), 5));	
							}else{
							precioUnitarioExento = 
								Double.parseDouble(Redondeo.dRound(detalleFactura.getDfaPrecioUnitario()/(impuesto+1), 5));
							}
							
							double precioTotalExento = precioUnitarioExento*detalleFactura.getDfaCantidad();
							detalleFactura.setDfaPrecioUnitario(Double.parseDouble(Redondeo.dRound(precioUnitarioExento, 5)));
							detalleFactura.setDfaPrecioTotal(Double.parseDouble(Redondeo.dRound(precioTotalExento, 5)));
						}
						else{
							if (detalleFactura.getDfaExento().intValue() == 1){
								precioUnitarioExento = 
									Double.parseDouble(Redondeo.dRound(detalleFactura.getDfaPrecioUnitario(), 5));	
							}else{
							precioUnitarioExento = 
								Double.parseDouble(Redondeo.dRound(detalleFactura.getDfaPrecioUnitario()/(impuesto+1), 5));
							}
							//precioUnitarioExento = Double.parseDouble(Redondeo.dRound(detalleFactura.getDfaPrecioUnitario()/(impuesto+1), 2));
							double precioTotalExento = precioUnitarioExento*detalleFactura.getDfaCantidad();
							//double precioTotalAfecto = detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad();
							detalleFactura.setDfaPrecioUnitario(Double.parseDouble(Redondeo.dRound(precioUnitarioExento, 5)));
							detalleFactura.setDfaPrecioTotal(Double.parseDouble(Redondeo.dRound(precioTotalExento, 5)));
						}
					}
					/*else{
						double precioTotalAfecto = detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad();
						detalleFactura.setDfaPrecioTotal(precioTotalAfecto);
					}*/
				}
				if(facturaForm.getFenTipoFactura().equals("CR")){
					InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
					InvProProveedor proveedor = proveedorDAO.findById(encabezadoFacturaForm.getIdProveedor());
					
					/*ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO();
					double impuesto = impuestoDAO.findById(1).getRimImpuesto()/100;*/
					
					if(proveedor.getProRegistro() != null){
						precioUnitarioExento = detalleFactura.getDfaPrecioUnitario();
						double precioTotalExento = precioUnitarioExento*detalleFactura.getDfaCantidad();
						detalleFactura.setDfaPrecioUnitario(Double.parseDouble(Redondeo.dRound(precioUnitarioExento, 3)));
						detalleFactura.setDfaPrecioTotal(precioTotalExento);
					}
					else{
						double precioTotalAfecto = detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad();
						detalleFactura.setDfaPrecioTotal(precioTotalAfecto);
					}
				}
				/*termina bloque que calcula el iva*/
				if(declaraIva.intValue() ==0){
					detalleFactura.setDfaExento(new Byte((byte) 1));
				}
				encabezadoFacturaForm.getFacDfaDetalleFacturas().add(detalleFactura);
				encabezadoFacturaForm = calcularTotalForm(encabezadoFacturaForm,request);
				request.getSession().setAttribute("encabezadoFacturaForm",
						encabezadoFacturaForm);
			} else {
				// si se retorna false entonces no puede ser admitida la cantidad
				if(venOComp){
					mensajes("errors.encabezado.maxArticuloVenta", encabezadoFacturaForm, request, response);
				}else{
					mensajes("errors.encabezado.maxArticuloCompra", encabezadoFacturaForm, request, response);
				}
				
				request.getSession().setAttribute("encabezadoFacturaForm",
						encabezadoFacturaForm);
			}
		}else {
			// si se retorna false entonces no puede ser admitida la cantidad
			request.getSession().setAttribute("encabezadoFacturaForm",
					encabezadoFacturaForm);
		}
		
		//encabezadoFacturaForm.setFenCodigoCliente(facturaForm.getCodigo());
		encabezadoFacturaForm.setCodigo(facturaForm.getCodigo());
		
		encabezadoFacturaForm.setProCodigo(facturaForm.getProCodigo());
		encabezadoFacturaForm.setProNit(facturaForm.getProNit());
		encabezadoFacturaForm.setProNombre(facturaForm.getProNombre());
		encabezadoFacturaForm.setProRegistro(facturaForm.getProRegistro());
		encabezadoFacturaForm.setInvProProveedor(facturaForm.getInvProProveedor());
		
		encabezadoFacturaForm.getInvBodBodegas().setBodId(facturaForm.getInvBodBodegas().getBodId());
		encabezadoFacturaForm.setFenTipoPago(facturaForm.getTipoPagoId());
		encabezadoFacturaForm.setTipoPagoId(facturaForm.getTipoPagoId());
		
		encabezadoFacturaForm.getFacFusFacturaUso().setFusId(facturaForm.getFuso());
		
		encabezadoFacturaForm.setFenNumeroFactura(facturaForm.getFenNumeroFactura());
		
		request.getSession().setAttribute("encabezadoFacturaForm",
				encabezadoFacturaForm);
		
		request.setAttribute("form", encabezadoFacturaForm);
		if(ventaOCompra(encabezadoFacturaForm.getAck())){
			if(encabezadoFacturaForm.getFacDfaDetalleFacturas().size() == 14){
				mensajes("error.encabezado.unoMas", encabezadoFacturaForm, request, response);
			}
			if(encabezadoFacturaForm.getFacDfaDetalleFacturas().size() == 15){
				mensajes("error.encabezado.maximo", encabezadoFacturaForm, request, response);
			}
		}
		target = lista(mapping,form, request, response);
		return target;
	}

	private Boolean validarAgregar(EncabezadoFacturaForm facturaForm,
			HttpServletRequest request, HttpServletResponse response, int detalles) {
		ActionErrors errors = new ActionErrors();
		Boolean valid = true;
		if(facturaForm.getArtCodigo().equals("") || facturaForm.getArtCodigo() == null){
			mensajes2("errors.encabezado.agregar.artCodigo", facturaForm, request, response, errors);
			valid = false;
		}
		if(facturaForm.getArtNombre().equals("") || facturaForm.getArtNombre() == null){
			mensajes2("errors.encabezado.agregar.nombreArt", facturaForm, request, response, errors);
			valid = false;
		}
		if(facturaForm.getCnvId() == -5){
			mensajes2("errors.encabezado.agregar.cnvId", facturaForm, request, response, errors);
			valid = false;
		}
		if(facturaForm.getDfaPrecioUnitario() == null){
			mensajes2("errors.encabezado.agregar.dfaPrecioUnitario1", facturaForm, request, response, errors);
			valid = false;
		}else{
			if(facturaForm.getDfaPrecioUnitario() <= 0){
				mensajes2("errors.encabezado.agregar.dfaPrecioUnitario2", facturaForm, request, response, errors);
				valid = false;
			}
		}
		if(facturaForm.getDfaCantidad() <= 0){
			mensajes2("errors.encabezado.agregar.dfaCantidad", facturaForm, request, response, errors);
			valid = false;
		}
		if(valid == false){
			mensajes2("errors.encabezado.agregar.reload", facturaForm, request, response, errors);
		}
		if(ventaOCompra(facturaForm.getAck()) == true && detalles >= 15){
			mensajes2("errors.encabezado.noMasArticulos", facturaForm, request, response, errors);
			valid = false;
		}
		return valid;
	}

	private Boolean ventaOCompra(String ack) { // si es true es que es de venta
		if(ack != null){
			if(ack.equals("prov")){
				return false;
			}
			if(ack.equals("c")){
				return true;
			}
			if(ack.equals("f")){
				return true;
			}
		}
		return null;
	}

	private boolean verificarSuministros(String codigoArticulo, int bodId,
			double cantidad,boolean vfa,HttpServletRequest request) {
		boolean decision = false;// si se retorna true quiere decir que si puede
		// admitirse la cantidad en bodega. si se retorna false entonces no se puede admitir
		// la cantidad en bodega
		InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
		InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
		InvCprCapacidadProductoDAO cprDAO = new InvCprCapacidadProductoDAO(getSessionHibernate(request));
		InvCprCapacidadProductoId cprId = new InvCprCapacidadProductoId();
		cprId.getInvArtArticulo().setArtCodigo(codigoArticulo);
		cprId.getInvBodBodegas().setBodId(bodId);
		InvCprCapacidadProducto cpr = cprDAO.findById(cprId);
		eboId.getInvBodBodegas().setBodId(bodId);
		eboId.getInvPexProductosExistencia().setArtCodigo(codigoArticulo);
		InvEboExistenciaBodega ebo = eboDAO.findById(eboId);
		if(vfa){
			//si la factura es de venta
			if (cantidad <= ebo.getEboCantidadProducto()) {
				decision = true; 
			} 
		}else{
			//si la factura es de compra
			if ((cantidad+ebo.getEboCantidadProducto()) <= cpr.getCprCantidadMaxima()) {
				decision = true; 
			} 
		}
		return decision;
	}

	private EncabezadoFacturaForm calcularTotalForm(EncabezadoFacturaForm encabezadoFacturaForm,HttpServletRequest request) {// Calcula los totales de la factura
		double afect = 0.00, exen = 0.00, ivaR= 0.00,totalFactura=0.00;
		DecimalFormat df = new DecimalFormat("0.00");
		/*Iterator iterator = encabezadoFacturaForm.getFacDfaDetalleFacturas().iterator();
		while(iterator.hasNext()) {
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator
					.next();
			double precioExentoArticulo = detalleFactura.getDfaPrecioUnitario()* detalleFactura.getDfaCantidad();//cantidadReal;
			precioExentoArticulo = Math.ceil(precioExentoArticulo*Math.pow(10, 2))/Math.pow(10, 2);
			double precioUnitario = detalleFactura.getDfaPrecioUnitario();
			if (detalleFactura.getDfaExento() == 1) {// calculamos el precio si es exento
				exen = exen+ precioExentoArticulo;
				exen = Math.ceil(exen*Math.pow(10, 2))/Math.pow(10, 2);//FIXME modificado 16/11/2009 mucho ojo con la siguiente instruccion
				detalleFactura.setDfaPrecioTotal(precioExentoArticulo);
			} else {// calculamos el precio si no es exento
				ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO();
				double impuesto = impuestoDAO.findById(1).getRimImpuesto() / 100;
				double precioAfectoArticuloT = 0.0;
				double precioAfectoArticulo = precioUnitario
						* (1 + impuesto);// extraemos el impuesto almacenado
											// en la base
				precioAfectoArticulo = Math.ceil(precioAfectoArticulo*Math.pow(10, 2))/Math.pow(10, 2);//new
				precioAfectoArticuloT = precioAfectoArticulo*detalleFactura.getDfaCantidad();
				ivaR = ivaR + (precioAfectoArticuloT - precioExentoArticulo);
				ivaR = Math.ceil(ivaR*Math.pow(10, 2))/Math.pow(10, 2);
				afect = afect+ precioExentoArticulo;
				detalleFactura.setDfaPrecioTotal(precioAfectoArticuloT);
			}
		}//se redondean los datos que apareceran en pantalla
		encabezadoFacturaForm.setFenIvaRetenido(ivaR);
		encabezadoFacturaForm.setIvaRetenido(Double.parseDouble(Redondeo.dRound(ivaR, 2)));
		encabezadoFacturaForm.setFenTotalVentasExentas(exen);
		encabezadoFacturaForm.setTotalVentasE(Double.parseDouble(Redondeo.dRound(exen, 2)));// este es el que se utiliza para visualizar en el form
		encabezadoFacturaForm.setTotalVentasA(Double.parseDouble(Redondeo.dRound(afect, 2)));
		totalFactura = encabezadoFacturaForm.getTotalVentasA()+ encabezadoFacturaForm.getTotalVentasE() + encabezadoFacturaForm.getFenIvaRetenido();
		encabezadoFacturaForm.setFenTotalVenta(totalFactura);
		encabezadoFacturaForm.setTotalFactura(Double.parseDouble(Redondeo.dRound(totalFactura, 2)));*/
		
		/*Implementando una nueva solucion general para todas las facturas
		 * 1.Consumidores Finales
		 * 2.Contribuyentes Exentos de iva
		 * 3.Contribuyentes no exentos*/
		
		Iterator iterador2 = encabezadoFacturaForm.getFacDfaDetalleFacturas().iterator();
		FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
		FacCliCliente cliente = new FacCliCliente();
		InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
		InvProProveedor proveedor = new InvProProveedor();
		if(encabezadoFacturaForm.getFenTipoFactura().equals("VC")){
			cliente = clienteDAO.findById(encabezadoFacturaForm.getCodCli());
			
		}
		//FIXME se deben de redondear los decimales, no se como hacerlo,  se debe de tener mucho cuidado con esto
		if(encabezadoFacturaForm.getFenTipoFactura().equals("CR")){
			if(encabezadoFacturaForm.getIdProveedor()!=null)proveedor = proveedorDAO.findById(encabezadoFacturaForm.getIdProveedor());
			else proveedor = (InvProProveedor)proveedorDAO.findByProCodigo(encabezadoFacturaForm.getProCodigo()).get(0);
		}
		
		ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO(getSessionHibernate(request));
		double impuesto = impuestoDAO.findById(1).getRimImpuesto()/100;

		/*while(iterador2.hasNext()){
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura)iterador2.next();
			if(contador == i){
				if(clie != null){
					if(cliente.getCliDeclaraIva() == 0){
						double precioUnitarioExento = detalleFactura.getDfaPrecioUnitario()/(impuesto+1);
						double precioTotalExento = precioUnitarioExento*detalleFactura.getDfaCantidad();
						exen = exen + precioTotalExento;
						detalleFactura.setDfaPrecioUnitario(Double.parseDouble(Redondeo.dRound(precioUnitarioExento, 2)));
						detalleFactura.setDfaPrecioTotal(precioTotalExento);
					}
					else{
						double precioUnitarioExento = detalleFactura.getDfaPrecioUnitario()/(impuesto+1);
						double precioTotalExento = precioUnitarioExento*detalleFactura.getDfaCantidad();
						afect = afect + precioTotalExento;
						detalleFactura.setDfaPrecioUnitario(Double.parseDouble(Redondeo.dRound(precioUnitarioExento, 2)));
						detalleFactura.setDfaPrecioTotal(precioTotalExento);
					}
				}
				else{
					double precioTotalAfecto = detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad();
					afect = afect + precioTotalAfecto;
					detalleFactura.setDfaPrecioTotal(precioTotalAfecto);
				}
			}
			else{
				if(cliente.getCliDeclaraIva() == 0){
					exen = exen + detalleFactura.getDfaPrecioTotal();
				}
				else{
					afect = afect + detalleFactura.getDfaPrecioTotal();
				}
			}
			contador++;
			System.out.println("el contador es:"+contador);
		}*/
		
		while(iterador2.hasNext()){
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura)iterador2.next();
			
			if(encabezadoFacturaForm.getFenTipoFactura().equals("VC")){ // venta Contribuyente
				if(cliente.getCliDeclaraIva() == 0){
					exen = exen + detalleFactura.getDfaPrecioTotal();
					Integer i = 1;
					Byte b = i.byteValue();
					detalleFactura.setDfaExento(b);
					
				}
				else{
					if(detalleFactura.getDfaExento().intValue() == 1)
						exen = exen + detalleFactura.getDfaPrecioTotal();
					else
					afect = afect + detalleFactura.getDfaPrecioTotal();
				}
			}
			if(encabezadoFacturaForm.getFenTipoFactura().equals("CR")){ // compra proveedor
				double precioExentoArticulo = detalleFactura.getDfaPrecioUnitario()* detalleFactura.getDfaCantidad();//cantidadReal;
				if (detalleFactura.getDfaExento() == 1) {// calculamos el precio si es exento
					exen = exen+ precioExentoArticulo;
					detalleFactura.setDfaPrecioTotal(precioExentoArticulo);
				} else {// calculamos el precio si no es exento
					
					double precioAfectoArticulo = precioExentoArticulo
							* (1 + impuesto);// extraemos el impuesto almacenado
												// en la base
					ivaR = ivaR + (precioAfectoArticulo - precioExentoArticulo);
					afect = afect+ precioExentoArticulo;
					detalleFactura.setDfaPrecioTotal(precioAfectoArticulo);
				}
				/*if(proveedor.getProRegistro() != null){
					afect = afect + detalleFactura.getDfaPrecioTotal();
				}
				else{
					exen = exen + detalleFactura.getDfaPrecioTotal();
				}*/
			}
			if(encabezadoFacturaForm.getFenTipoFactura().equals("CO")){ // consumidor final?
				if(detalleFactura.getDfaExento().intValue() == 1)
					exen = exen + detalleFactura.getDfaPrecioTotal();
				else
				afect = afect + detalleFactura.getDfaPrecioTotal();
/*
				detalleFactura.setDfaPrecioTotal(Double.parseDouble(Redondeo.dRound((detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad()), 2))/(impuesto+1));
				afect = afect + detalleFactura.getDfaPrecioTotal();
				detalleFactura.setDfaPrecioTotal(detalleFactura.getDfaPrecioUnitario()*detalleFactura.getDfaCantidad());*/
			}
		}
		
		
		encabezadoFacturaForm.setFenTotalVentasExentas(exen);
		
		
		encabezadoFacturaForm.setTotalVentasE(Double.parseDouble(Redondeo.dRound(exen, 3)));// este es el que se utiliza para visualizar en el form
		System.out.println("Exen="+exen);
		encabezadoFacturaForm.setTotalVentasA(Double.parseDouble(Redondeo.dRound(afect, 3)));
		System.out.println("Afect="+afect);
		totalFactura = /*encabezadoFacturaForm.getTotalVentasA()*/new Double(df.format(afect+ encabezadoFacturaForm.getTotalVentasE())).doubleValue();// + encabezadoFacturaForm.getFenIvaRetenido();
		if(encabezadoFacturaForm.getFenTipoFactura().equals("VC")){
			if(cliente.getCliContribuyente()!=null && cliente.getCliDeclaraIva()==1) 
				ivaR = ivaR + (afect*impuesto);//era totalFactura*impuesto
		}
		if(encabezadoFacturaForm.getFenTipoFactura().equals("CR")){
		//	if(proveedor.getProRegistro()!=null) ivaR = ivaR + (totalFactura*impuesto);
		}
		if(encabezadoFacturaForm.getFenTipoFactura().equals("CO")){
			ivaR = ivaR + (afect*impuesto);//era totalFactura*impuesto
		}
		encabezadoFacturaForm.setIvaRetenido(Double.parseDouble(Redondeo.dRound(ivaR, 2)));
		totalFactura = totalFactura + ivaR;
		totalFactura = new Double(df.format(totalFactura));
		encabezadoFacturaForm.setFenIvaRetenido(new Double (df.format(ivaR)).doubleValue());
		//System.out.println("totalFactura: "+totalFactura);
		encabezadoFacturaForm.setFenTotalVenta(totalFactura);
		encabezadoFacturaForm.setTotalFactura(Double.parseDouble(Redondeo.dRound(totalFactura, 2)));
		return encabezadoFacturaForm;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {// Metodo que se utiliza para eliminar articulso
//del detalle de factura
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm) form;
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) request
				.getSession().getAttribute("encabezadoFacturaForm");
		
		for (Iterator iterator = facturaForm.getFacDfaDetalleFacturas().iterator(); iterator.hasNext();) {
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator.next();
			if (detalleFactura.getId().getInvArtArticulo().getArtCodigo()
					.equals(encabezadoFacturaForm.getArtCodigo())) {
				facturaForm.getFacDfaDetalleFacturas().remove(detalleFactura);
				facturaForm = calcularTotalForm(facturaForm,request);
				request.getSession().setAttribute("encabezadoFacturaForm", facturaForm);
				return lista(mapping,(ActionForm) facturaForm, request, response);
			}
		}
		facturaForm = calcularTotalForm(facturaForm,request);
		request.getSession().setAttribute("encabezadoFacturaForm", facturaForm);
		return lista(mapping,(ActionForm) facturaForm, request, response);
	}

	public ActionForward cancelarXfact(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, String mensaje) {
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) form;
		ActionForward target = null;
		String tget = "lista";
		facturaForm.setNva(true);
		request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
		if(facturaForm.getFenTipoFactura() == null){
			facturaForm = (EncabezadoFacturaForm) request.getSession().getAttribute("encabezadoFacturaForm");
			if(facturaForm.getFenTipoFactura().equals("ND")){
				facturaForm.setFenTipoFactura("CR");
			}else{
				facturaForm.setFenTipoFactura("CO");
			}
		}
		if(facturaForm.getFenTipoFactura().equals("CR")){
			tget = "redirectListaFactC";
		}
		if(facturaForm.getFenTipoFactura().equals("CO")){
			tget= "redirectListaFactV";
		}
		if(facturaForm.getFenTipoFactura().equals("VC")){
			tget= "redirectListaFactV";
		}
		if(facturaForm.getFenTipoFactura().equals("ND") || facturaForm.getAck().equals("ND")){
			tget= "redirectListaFactND";
		}
		if(facturaForm.getFenTipoFactura().equals("NC") || facturaForm.getAck().equals("NC")){
			tget= "redirectListaFactNC";
		}
		if(facturaForm.getFenTipoFactura().equals("") || facturaForm.getFenTipoFactura() == null){
			tget= "";
		}
		if(!mensaje.equals("") && mensaje != null){
			mensajes(mensaje, facturaForm, request, response);
		}
		return mapping.findForward(tget);
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) form;
		ActionForward target = null;
		String tget = "lista";
		facturaForm.setNva(true);
		request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
		if(facturaForm.getFenTipoFactura() == null){
			facturaForm = (EncabezadoFacturaForm) request.getSession().getAttribute("encabezadoFacturaForm");
			if(facturaForm.getFenTipoFactura().equals("ND")){
				facturaForm.setFenTipoFactura("CR");
			}else{
				facturaForm.setFenTipoFactura("CO");
			}
		}
		if(facturaForm.getFenTipoFactura().equals("CR")){
			tget = "redirectListaFactC";
		}
		if(facturaForm.getFenTipoFactura().equals("CO")){//consumidor final
			tget= "redirectListaFactV";
		}
		if(facturaForm.getFenTipoFactura().equals("VC")){
			tget= "redirectListaFactV";
		}
		if(facturaForm.getFenTipoFactura().equals("ND") || facturaForm.getAck().equals("ND")){
			tget= "redirectListaFactND";
		}
		if(facturaForm.getFenTipoFactura().equals("NC") || facturaForm.getAck().equals("NC")){
			tget= "redirectListaFactNC";
		}
		if(facturaForm.getFenTipoFactura().equals("") || facturaForm.getFenTipoFactura() == null){
			tget= "";
		}
		return mapping.findForward(tget);
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		FacFenFacturaEncabezadoDAO encabezadoDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
		FacDfaDetalleFacturaDAO dfaDAO =  new FacDfaDetalleFacturaDAO(getSessionHibernate(request));
		ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO(getSessionHibernate(request));
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		EncabezadoFacturaForm encabezadoForm = (EncabezadoFacturaForm)request.getSession().getAttribute("encabezadoFacturaForm");
		EncabezadoFacturaForm encabezadoFormReq = (EncabezadoFacturaForm) form;
		//System.out.println(encabezadoFormReq.getFenTipoFactura());
		
		// Agregar la sucursal a la factura
		SecSucSucursalDAO sucursalDAO = new SecSucSucursalDAO(getSessionHibernate(request));
		SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		SecIseInicioSesion inicioSesion = inicioSesionDAO.findById(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona persona = personaDAO.findById(inicioSesion.getSecPerPersona().getPerId());
		SecSucSucursal sucursal = sucursalDAO.findById(persona.getSecSucSucursal().getSucId());
		encabezadoForm.setSecSucSucursal(sucursal);
		//Terminamos de agregarla
		
		encabezadoForm.setCodCli(encabezadoFormReq.getCodCli());
		encabezadoForm.setNombreCli(encabezadoFormReq.getNombreCli());
		
		if(encabezadoFormReq.getAoc() != null && !encabezadoFormReq.getAoc().equals("")){
			if(encabezadoFormReq.getAoc().equals("C")){//es cliente
				/*if(encabezadoFormReq.getTipoPagoId().equals("C")){
					mensajes("error.encabezado.clienteNoCredito", encabezadoFormReq, request, response);
					return lista(mapping, encabezadoFormReq, request, response);
				}*/
				FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
				FacCliCliente cliente = clienteDAO.findById(encabezadoFormReq.getCodigo());
				encabezadoForm.setFacCliCliente(cliente);
				encabezadoForm.setCtaAscAsociado(null);
			}
			if(encabezadoFormReq.getAoc().equals("A")){//es asociado
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
				CtaAscAsociado asociado = asociadoDAO.findById(encabezadoFormReq.getCodigo());
				encabezadoForm.setCtaAscAsociado(asociado);
				encabezadoForm.setFacCliCliente(null);
			}
		}else{
			encabezadoForm.setCtaAscAsociado(null);
			encabezadoForm.setFacCliCliente(null);
		}
		
		//encabezadoForm.setFenCodigoCliente(encabezadoFormReq.getCodigo());
		encabezadoForm.setCodigo(encabezadoFormReq.getCodigo());
		
		encabezadoForm.setProCodigo(encabezadoFormReq.getProCodigo());
		encabezadoForm.setProNit(encabezadoFormReq.getProNit());
		encabezadoForm.setProNombre(encabezadoFormReq.getProNombre());
		encabezadoForm.setProRegistro(encabezadoFormReq.getProRegistro());
		encabezadoForm.setInvProProveedor(encabezadoFormReq.getInvProProveedor());
		
		encabezadoForm.getInvBodBodegas().setBodId(encabezadoFormReq.getInvBodBodegas().getBodId());
		encabezadoForm.setFenTipoPago(encabezadoFormReq.getFenTipoPago());
		
		encabezadoForm.setFacFusFacturaUso(encabezadoFormReq.getFacFusFacturaUso());
		
		encabezadoForm.setFenNumeroFactura(encabezadoFormReq.getFenNumeroFactura());
		encabezadoForm.setTipoPagoId(encabezadoFormReq.getTipoPagoId());
		String errores = encuetraNulos(encabezadoForm, request,response);
		if(!errores.equals("") && errores != null){
			return lista(mapping, form, request, response);
		}
		
		if(encabezadoForm.getAck().equals("prov") || encabezadoForm.getAck().equals("cr")){//si es nota de credito
			// o compra a proveedor se asigna el codigo al form de encabezado
			//encabezadoForm.setFenNumeroFactura(Format.formatNumeroFactura(Integer.parseInt(encabezadoFormReq.getFenNumeroFactura()),15));
			encabezadoForm.getInvProProveedor().setProId(encabezadoFormReq.getInvProProveedor().getProId());
		}
		else{//sino, se asigna el cliente al form
			encabezadoForm.setInvProProveedor(null);
			//encabezadoForm.setFenNumeroFactura(Format.formatNumeroFactura(Integer.parseInt(encabezadoForm.getFacturaEncabezadoH().getFenNumeroFactura()),15));
		}
		encabezadoForm.setInvBodBodegas(encabezadoFormReq.getInvBodBodegas());
		encabezadoForm.setFacFusFacturaUso(encabezadoFormReq.getFacFusFacturaUso());
		//encabezadoForm.setFenTipoPago(encabezadoFormReq.getFenTipoPago());
		
		if(encabezadoForm.getTipoPagoId().equals("C")){
			encabezadoForm.setFenTipoPago(encabezadoForm.getTipoPagoId());
			/*encabezadoForm.setFenCancelada("C");*/
			encabezadoForm.setFenCancelada("C");
		}else{
			encabezadoForm.setFenTipoPago(encabezadoForm.getTipoPagoId());
			encabezadoForm.setFenCancelada("E");/*************************/
		}
		encabezadoForm.setConRimRetencionImpuesto(impuestoDAO.findById(1));//hayamos el primer registro de la tabla impuestos - IVA
		encabezadoForm.setAudFechaCreacion(new Date());
		encabezadoForm.setAudFechaModificacion(new Date());
		encabezadoForm.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		encabezadoForm.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		
		FacFusFacturaUsoDAO fusoDao = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		encabezadoForm.setFacFusFacturaUso(fusoDao.findById(encabezadoForm.getFacFusFacturaUso().getFusId()));
		
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		for (Iterator iterator = encabezadoForm.getFacDfaDetalleFacturas().iterator(); iterator.hasNext();) {
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator.next();
			//Se verifica si hay disponibilidad nuevamente para la bodega escogida
			//System.out.println(detalleFactura.getDfaPrecioTotal());
			boolean verSum;
			boolean venOComp;
			if(encabezadoForm.getFacFusFacturaUso().getFusToperacion().equals("v")){
				venOComp = true;//venta
			}else{
				venOComp = false;//compra
			}
			if(encabezadoForm.getTipoPagoId().equals("C")  && encabezadoForm.getAck().equals("c")){//credito y cliente
				if(tipoPrestamoDAO.findByTar(detalleFactura.getId().getInvArtArticulo().getInvTarTipoArticulo().getTarId()) == null){
					mensajes("error.encabezado.noUnionPrestamo", encabezadoFormReq, request, response);
					return lista(mapping, encabezadoFormReq, request, response);
				}
			}
			if(detalleFactura.getCnvId()==-2){
				verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
						encabezadoForm.getInvBodBodegas().getBodId(),
						detalleFactura.getDfaCantidad(),
						venOComp,request);
			}else{
				verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
						encabezadoForm.getInvBodBodegas().getBodId(),
						(detalleFactura.getDfaCantidad() * conversionDAO.findById(detalleFactura.getCnvId()).getCnvFactor()),
						venOComp,request);
			}
			if(verSum == false){
				if(venOComp == true){
					mensajes("errors.encabezado.vendiendoMax", encabezadoForm, request, response);
					return lista(mapping, encabezadoForm, request, response);
				}else{
					mensajes("errors.encabezado.comprandoMax", encabezadoForm, request, response);
					return lista(mapping, encabezadoForm, request, response);
				}
			}
			//Termina verificacion
		};
		Transaction tx = encabezadoDAO.getSession().beginTransaction();
		try{
			encabezadoForm.setFenId(encabezadoDAO.nextId());
			encabezadoForm.setCtrEstEstado(estadoDAO.findById(3));//seteamos el estado de guardado
			encabezadoDAO.save(encabezadoForm.getFacturaEncabezadoH());
			Transaction transaction  = dfaDAO.getSession().beginTransaction();
			for (Iterator iterator = encabezadoForm.getFacDfaDetalleFacturas().iterator(); iterator.hasNext();) {
				FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator.next();
				System.out.println("saving dfa...");
				dfaDAO.save(detalleFactura);
			}
			transaction.commit();
			tx.commit();//solo se realiza un 'save' ya que en el mapeo, se coloco ' cascade="save-update" '
			
			
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			encabezadoDAO.getSession().flush();
			encabezadoDAO.getSession().clear();
			
			dfaDAO.getSession().flush();
			dfaDAO.getSession().clear();
			
		}
		request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
		return cancelar(mapping, form, request, response);
	}
	
	private String encuetraNulos(EncabezadoFacturaForm encabezadoForm,
		HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		String nulo = "";
		if(encabezadoForm.getFacturaEncabezadoH().getFacDfaDetalleFacturas().size() < 1){
			mensajes2("errors.encabezado.detalles", encabezadoForm, request, response, errors);
			nulo = "no";
		}
		if(encabezadoForm.getFacturaEncabezadoH().getFenFechaFactura().equals("")
				|| encabezadoForm.getFacturaEncabezadoH().getFenFechaFactura() == null){
			mensajes2("errors.encabezado.fechaFactura", encabezadoForm, request, response, errors);
			nulo = "no";
		}
		if(encabezadoForm.getFacturaEncabezadoH().getFenNumeroFactura().equals("")
				|| encabezadoForm.getFacturaEncabezadoH().getFenNumeroFactura() == null){
			mensajes2("errors.encabezado.numeroFactura", encabezadoForm, request, response, errors);
			nulo = "no";
		}
		if(encabezadoForm.getFacturaEncabezadoH().getFenSerieFactura().equals("")
				|| encabezadoForm.getFacturaEncabezadoH().getFenSerieFactura() == null){
			mensajes2("errors.encabezado.serieFactura", encabezadoForm, request, response, errors);
			nulo = "no";
		}
		if(encabezadoForm.getFacturaEncabezadoH().getInvBodBodegas().getBodId() == 0
				|| encabezadoForm.getFacturaEncabezadoH().getInvBodBodegas().getBodId() == null){
			mensajes2("errors.encabezado.InvBodBodegas.bodId", encabezadoForm, request, response, errors);
			nulo = "no";
		}
		if(encabezadoForm.getAck().equals("prov")){
			if(encabezadoForm.getFacturaEncabezadoH().getInvProProveedor().getProId() == 0
					|| encabezadoForm.getFacturaEncabezadoH().getInvProProveedor().getProId() == null){
				mensajes2("errors.encabezado.invProProveedor.proId", encabezadoForm, request, response, errors);
				nulo = "no";
			}else{
				if(encabezadoForm.getProCodigo().equals("")
						|| encabezadoForm.getProCodigo() == null){
					mensajes2("errors.encabezado.proCodigo", encabezadoForm, request, response, errors);
					nulo = "no";
				}
				if(encabezadoForm.getProNombre().equals("")
						|| encabezadoForm.getProNombre() == null){
					mensajes2("errors.encabezado.proNombre", encabezadoForm, request, response, errors);
					nulo = "no";
				}
			}
		}
		if(encabezadoForm.getAck().equals("c")){
			if(encabezadoForm.getFacturaEncabezadoH().getCtaAscAsociado() == null
					&& encabezadoForm.getFacturaEncabezadoH().getFacCliCliente() == null){
				mensajes2("errors.encabezado.codigoCliente", encabezadoForm, request, response, errors);
				nulo = "no";
			}else{
				if(encabezadoForm.getCodCli().equals("")
						|| encabezadoForm.getCodCli() == null){
					mensajes2("errors.encabezado.codCli", encabezadoForm, request, response, errors);
					nulo = "no";
				}
				if(encabezadoForm.getNombreCli().equals("")
						|| encabezadoForm.getNombreCli() == null){
					mensajes2("errors.encabezado.nombreCli", encabezadoForm, request, response, errors);
					nulo = "no";
				}
			}
		}
		if(encabezadoForm.getAck().equals("f")){
			if(encabezadoForm.getFacturaEncabezadoH().getFacCliCliente() == null){
				mensajes2("errors.encabezado.codigoCliente", encabezadoForm, request, response, errors);
				nulo = "no";
			}else{
				if(encabezadoForm.getCodCli().equals("")
						|| encabezadoForm.getCodCli() == null){
					mensajes2("errors.encabezado.codCli", encabezadoForm, request, response, errors);
					nulo = "no";
				}
				if(encabezadoForm.getNombreCli().equals("")
						|| encabezadoForm.getNombreCli() == null){
					mensajes2("errors.encabezado.nombreCli", encabezadoForm, request, response, errors);
					nulo = "no";
				}
			}
		}
		return nulo;
	}

	//Cuando se corre este action se afectan los inventarios
	public ActionForward guardarAndImprimir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		FacFenFacturaEncabezadoDAO encabezadoDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
		ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO(getSessionHibernate(request));
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		EncabezadoFacturaForm encabezadoForm = (EncabezadoFacturaForm)request.getSession().getAttribute("encabezadoFacturaForm");
		EncabezadoFacturaForm encabezadoFormReq = (EncabezadoFacturaForm) form;
		

		// Agregar la sucursal a la factura
		SecSucSucursalDAO sucursalDAO = new SecSucSucursalDAO(getSessionHibernate(request));
		SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		SecIseInicioSesion inicioSesion = inicioSesionDAO.findById(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona persona = personaDAO.findById(inicioSesion.getSecPerPersona().getPerId());
		SecSucSucursal sucursal = sucursalDAO.findById(persona.getSecSucSucursal().getSucId());
		encabezadoForm.setSecSucSucursal(sucursal);
		//Terminamos de agregarla
		
		encabezadoForm.setCodCli(encabezadoFormReq.getCodCli());
		encabezadoForm.setNombreCli(encabezadoFormReq.getNombreCli());
		
		//Inicia divisor
		Double divisor = 0.0;
		Double multiplicador = 0.0;
		
		//Union con cliente
		if(encabezadoFormReq.getAoc() != null && !encabezadoFormReq.getAoc().equals("")){
			if(encabezadoFormReq.getAoc().equals("C")){
				/*if(encabezadoFormReq.getTipoPagoId().equals("C")){
					mensajes("error.encabezado.clienteNoCredito", encabezadoFormReq, request, response);
					return lista(mapping, encabezadoFormReq, request, response);
				}*/
				FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
				FacCliCliente cliente = clienteDAO.findById(encabezadoFormReq.getCodigo());
				encabezadoForm.setFacCliCliente(cliente);
				encabezadoForm.setCtaAscAsociado(null);
			}
			if(encabezadoFormReq.getAoc().equals("A")){
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
				CtaAscAsociado asociado = asociadoDAO.findById(encabezadoFormReq.getCodigo());
				if(asociado.getEstId() == 21){
					mensajes("errors.encabezado.detalleRepetido", encabezadoForm, request, response);
					return lista(mapping, encabezadoForm, request, response);
				}
				encabezadoForm.setCtaAscAsociado(asociado);
				encabezadoForm.setFacCliCliente(null);
				
				CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
				divisor = parametrosDAO.findById("NUM_CUOTAS_ORDEN_COMPRA").getParValorNumber();
				multiplicador = parametrosDAO.findById("DIVISOR_CUOTAS_MES").getParValorNumber();
			}
		}else{
			encabezadoForm.setCtaAscAsociado(null);
			encabezadoForm.setFacCliCliente(null);
		}
		
		//encabezadoForm.setFenCodigoCliente(encabezadoFormReq.getCodigo());
		encabezadoForm.setCodigo(encabezadoFormReq.getCodigo());
		
		encabezadoForm.setProCodigo(encabezadoFormReq.getProCodigo());
		encabezadoForm.setProNit(encabezadoFormReq.getProNit());
		encabezadoForm.setProNombre(encabezadoFormReq.getProNombre());
		encabezadoForm.setProRegistro(encabezadoFormReq.getProRegistro());
		encabezadoForm.setInvProProveedor(encabezadoFormReq.getInvProProveedor());
		
		encabezadoForm.getInvBodBodegas().setBodId(encabezadoFormReq.getInvBodBodegas().getBodId());
		//encabezadoForm.setFenTipoPago(encabezadoFormReq.getFenTipoPago());
		
		encabezadoForm.setFacFusFacturaUso(encabezadoFormReq.getFacFusFacturaUso());
		encabezadoForm.setTipoPagoId(encabezadoFormReq.getTipoPagoId());
		encabezadoForm.setFenTipoPago(encabezadoFormReq.getTipoPagoId());
		encabezadoForm.setFenNumeroFactura(encabezadoFormReq.getFenNumeroFactura()); //actualizar este numero
		
		String errores = encuetraNulos(encabezadoForm, request,response);
		if(!errores.equals("") && errores != null){
			return lista(mapping, form, request, response);
		}
		
		if(encabezadoForm.getAck().equals("prov") || encabezadoForm.getAck().equals("cr")){//si es nota de credito
			// o compra a proveedor se asigna el codigo al form de encabezado
			//encabezadoForm.setFenNumeroFactura(Format.formatNumeroFactura(Integer.parseInt(encabezadoFormReq.getFenNumeroFactura()),15));
			encabezadoForm.getInvProProveedor().setProId(encabezadoFormReq.getInvProProveedor().getProId());
		}
		else{//sino, se asigna el cliente al form
			encabezadoForm.setInvProProveedor(null);
			CtrRfcRepositorioFacturasDAO repositorioDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
			//SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO();
			CtrRfcRepositorioFacturas repositorio = (CtrRfcRepositorioFacturas) repositorioDAO.findSerie(encabezadoFormReq.getFenTipoFactura(),inicioSesionDAO.findById(encabezadoFormReq.getUsuarioConectado().getNombreUsuario()).getSecPerPersona().getSecSucSucursal().getSucId() ).get(0);
			
			encabezadoForm.setFenSerieFactura(repositorio.getCtrCfcControlFacturacion().getCfcSerie());
			encabezadoForm.setFenNumeroFactura(Format.formatNumeroFactura(repositorio.getRfcCorrActual(), repositorio.getCtrCfcControlFacturacion().getCfcDigitos()));
		}
		encabezadoForm.setInvBodBodegas(encabezadoFormReq.getInvBodBodegas());
		encabezadoForm.setFacFusFacturaUso(encabezadoFormReq.getFacFusFacturaUso());
		if(encabezadoForm.getFenTipoPago()==null){
			encabezadoForm.setFenTipoPago(encabezadoFormReq.getFenTipoPago());
		}
		if(encabezadoForm.getFenTipoPago()==null ||encabezadoForm.getFenTipoPago().equals("E")){
			encabezadoForm.setFenCancelada("E");
		}else{
			encabezadoForm.setFenCancelada("C");
			
			//cargar disponible para comparacion
			CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
			//validar que no entre aqui cuando sea de venta a contribuyentes
			Double creditoUsado = 0.0;
			if(encabezadoForm.getCtaAscAsociado()!=null 
					&& encabezadoForm.getCtaAscAsociado().getAscId() != null 
					&& !encabezadoForm.getFenTipoFactura().equals("CR")){
				creditoUsado = prestamoDAO.sumCreditoUtilizado(encabezadoForm.getCtaAscAsociado().getAscId(),"F");
				
				Double disponible = 0.0;
				CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
				CtrParParametros creditoOrden = parametrosDAO.findById("CREDITO_FACT");
				disponible = creditoOrden.getParValorNumber() - creditoUsado - encabezadoForm.getFenTotalVenta();
				
				if(disponible == 0){
					mensajes("error.encabezado.masDeLoQuePuede", encabezadoForm, request, response);
					return lista(mapping, encabezadoForm, request, response);
				}
			}
			
		}
		encabezadoForm.setConRimRetencionImpuesto(impuestoDAO.findById(1));
		encabezadoForm.setAudFechaCreacion(new Date());
		encabezadoForm.setAudFechaModificacion(new Date());
		encabezadoForm.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		encabezadoForm.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		Transaction tx = encabezadoDAO.getSession().beginTransaction();
		FacDfaDetalleFacturaDAO dfaDAO =  new FacDfaDetalleFacturaDAO(getSessionHibernate(request));
		Transaction txx = dfaDAO.getSession().beginTransaction();
		try{
			encabezadoForm.setFenId(encabezadoDAO.nextId());
			encabezadoForm.setCtrEstEstado(estadoDAO.findById(1));//seteamos el estado de impresa
			encabezadoDAO.save(encabezadoForm.getFacturaEncabezadoH());
			for (Iterator iterator = encabezadoForm.getFacDfaDetalleFacturas().iterator(); iterator.hasNext();) {
				FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator.next();
				dfaDAO.save(detalleFactura);
			}
			txx.commit();
			tx.commit();//solo se realiza un 'save' ya que en el mapeo, se coloco ' cascade="save-update" '
			
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			FacFenFacturaEncabezado encabezado = encabezadoForm.getFacturaEncabezadoH();
			enviaAContabilidad(encabezado, 
					encabezadoForm.getUsuarioConectado().getNombreUsuario(),request);
			
			encabezadoDAO.getSession().flush();
			encabezadoDAO.getSession().clear();
			
		}
		InvPexProductosExistenciaDAO existenciaDAO = new InvPexProductosExistenciaDAO(getSessionHibernate(request)); 
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		FacFenFacturaEncabezado fenFacturaEncabezado = encabezadoDAO.findById(encabezadoForm.getFenId());
		InvArtArticuloDAO artArticuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		Iterator it = fenFacturaEncabezado.getFacDfaDetalleFacturas().iterator();
		Transaction tx2 = existenciaDAO.getSession().beginTransaction();
		if(facturaUsoDAO.findById(fenFacturaEncabezado.getFacFusFacturaUso().getFusId()).getFusToperacion().equals("c")){
			encabezadoForm.setView("prov");
			request.setAttribute("compra", 1);
			while(it.hasNext()){
				FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) it.next();
				InvArtArticulo articulo = new InvArtArticulo();
				articulo = artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
				
				//Se verifica si hay disponibilidad nuevamente para la bodega escogida
				boolean verSum;
				boolean venOComp;
				if(fenFacturaEncabezado.getFacFusFacturaUso().getFusToperacion().equals("v")){
					venOComp = true;
				}else{
					venOComp = false;
				}
				
				if(detalleFactura.getCnvId()==-2){
					verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
							fenFacturaEncabezado.getInvBodBodegas().getBodId(),
							detalleFactura.getDfaCantidad(),
							venOComp,request);
				}else{
					verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
							fenFacturaEncabezado.getInvBodBodegas().getBodId(),
							(detalleFactura.getDfaCantidad() * conversionDAO.findById(detalleFactura.getCnvId()).getCnvFactor()),
							venOComp,request);
				}
				if(verSum == false){
					mensajes("errors.encabezado.comprandoMax", encabezadoForm, request, response);
					return lista(mapping, encabezadoForm, request, response);
				}
				
				//Termina verificacion
				
				Double precioMinimo = articulo.getArtPrecioMinimo();
				double costoIng = detalleFactura.getDfaPrecioUnitario();
				
				int existenciaActual = existenciaDAO.findById(articulo.getArtCodigo()).getPexCantidadProducto();
				int cantIngreso;
				if(detalleFactura.getCnvId() == -2){
					cantIngreso = detalleFactura.getDfaCantidad();
				}else{
					InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
					cantIngreso = detalleFactura.getDfaCantidad()*conversion.getCnvFactor().intValue();
				}
				int existenciaNva = existenciaActual + cantIngreso;
				double saldoActual = existenciaDAO.findById(articulo.getArtCodigo()).getPexSaldo();
				double saldoIng = costoIng * cantIngreso;
				double nuevoSaldo = saldoActual + saldoIng;
				double nuevoCosto = nuevoSaldo/(existenciaNva);
				
				//Nuevo costo implica modificaciones al articulo
				articulo.setArtPrecioMinimo(nuevoCosto);
				articulo.setArtPrecioSugerido(articulo.getArtPrecioMinimo()*(1+(articulo.getArtPorcentajeUtilidad()/100)));
				articulo.setAudFechaModificacion(new Date());
				articulo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
				artArticuloDAO.merge(articulo);
				tx2.commit();
				
				try {
					InvPexProductosExistencia existencia = existenciaDAO.findById(articulo.getArtCodigo());
					existencia.setAudFechaModificacion(new Date());
					existencia.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					existencia.setPexCantidadProducto(existenciaNva);
					existencia.setPexCostoProducto(nuevoCosto);
					existencia.setPexSaldo(nuevoSaldo); 
					existenciaDAO.merge(existencia);
					tx2.commit();

					InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request)); 
					InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
					eboId.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
					eboId.getInvPexProductosExistencia().setArtCodigo(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
					InvEboExistenciaBodega ebo = eboDAO.findById(eboId); 
					int eboNuevaCant = ebo.getEboCantidadProducto() + cantIngreso; 
					InvCprCapacidadProductoDAO capacidadProductoDAO = new InvCprCapacidadProductoDAO(getSessionHibernate(request));
					InvCprCapacidadProductoId id2 = new InvCprCapacidadProductoId();
					id2.setInvArtArticulo(articulo); 
					id2.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
					InvCprCapacidadProducto capacidadProducto = capacidadProductoDAO.findById(id2);
					if(capacidadProducto.getCprCantidadMaxima() < eboNuevaCant){//esta verificacion se usa
						//para que (por pedido del cliente) si la cantidad maxima se supera con la nueva 
						//compra, se aumente automaticamente la cantidad maxima
							capacidadProducto.setCprCantidadMaxima(eboNuevaCant);
							capacidadProductoDAO.merge(capacidadProducto); 
							tx2.commit(); 
						}
					ebo.setEboCantidadProducto(eboNuevaCant); 
					ebo.setEboSaldo(nuevoCosto*eboNuevaCant); 
					ebo.setAudFechaModificacion(new Date());
					ebo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					eboDAO.merge(ebo); 
					tx2.commit();
					InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));
					InvTmoTipoMovimientoDAO tipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
					InvMovMovimientos movimientos = new InvMovMovimientos();
					movimientos.setMovId(movimientosDAO.nextId());
					movimientos.setInvArtArticulo(articulo);
					movimientos.setInvBodBodegas(ebo.getId().getInvBodBodegas());
					movimientos.setMovUnidades(cantIngreso);
					movimientos.setMovValor(saldoIng);
					movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(1));//indica que el movimiento es de entrada
					movimientos.setAudFechaCreacion(new Date());
					movimientos.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientos.setAudFechaModificacion(new Date());
					movimientos.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientosDAO.save(movimientos);
					tx2.commit();
				} catch (Exception e) {
					tx2.rollback(); 
					e.printStackTrace(); 
				}finally{ 
	 				existenciaDAO.getSession().flush();
	 				existenciaDAO.getSession().clear();
	 				
	 			}
			}
		}else{//si es salida se utiliza toda esta porcion de codigo
			encabezadoForm.setView("c");
			request.setAttribute("compra", 0);
		 	CtrRfcRepositorioFacturasDAO rfDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
		 	Transaction transaction = rfDAO.getSession().beginTransaction();
		 	
		 	CtrRfcRepositorioFacturas rfcRepositorioFacturas = (CtrRfcRepositorioFacturas) rfDAO.findSerie(encabezadoForm.getFenTipoFactura(), persona.getSecSucSucursal().getSucId()).get(0);
		 	rfcRepositorioFacturas.setRfcCorrActual(rfcRepositorioFacturas.getRfcCorrActual() + 1); 
		 	rfDAO.merge(rfcRepositorioFacturas); 
		 	transaction.commit(); 
		 	while(it.hasNext()){
		 		FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) it.next();
				if(artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo())!=null){ 
					InvArtArticulo articulo = artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
					
					//Se verifica si hay disponibilidad nuevamente para la bodega escogida
					boolean verSum;
					boolean venOComp;
					if(fenFacturaEncabezado.getFacFusFacturaUso().getFusToperacion().equals("v")){
						venOComp = true;
					}else{
						venOComp = false;
					}
					
					if(detalleFactura.getCnvId()==-2){
						verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
								fenFacturaEncabezado.getInvBodBodegas().getBodId(),
								detalleFactura.getDfaCantidad(),
								venOComp,request);
					}else{
						verSum = verificarSuministros(detalleFactura.getId().getInvArtArticulo().getArtCodigo(),
								fenFacturaEncabezado.getInvBodBodegas().getBodId(),
								(detalleFactura.getDfaCantidad() * conversionDAO.findById(detalleFactura.getCnvId()).getCnvFactor()),
								venOComp,request);
					}
					if(verSum == false){
						mensajes("errors.encabezado.vendiendoMax", encabezadoForm, request, response);
						return lista(mapping, encabezadoForm, request, response);
					}
					//Termina verificacion
					
		 			double costo = articulo.getArtPrecioMinimo();
		 			int cant1 = existenciaDAO.findById(articulo.getArtCodigo()).getPexCantidadProducto();
		 			int cant2;
		 			if(detalleFactura.getCnvId() == -2){
		 				cant2 = detalleFactura.getDfaCantidad();
		 			}else{
			 			InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
						cant2 = detalleFactura.getDfaCantidad()*conversion.getCnvFactor().intValue();
		 			}
					double nuevoSaldo = costo * (cant1 - cant2);//FIXME VALIDAR NUEVO SALDO Y CANTIDAD EN INVENTARIO 
					if(nuevoSaldo<0){
						mensajes("errors.encabezado.vendiendoMax", encabezadoForm, request, response);
						return lista(mapping, encabezadoForm, request, response);
					}
		  			try {
		  				InvPexProductosExistencia existencia = existenciaDAO.findById(articulo.getArtCodigo());
						existencia.setAudFechaModificacion(new Date());
		  				existencia.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		 				existencia.setPexCantidadProducto(cant1 - cant2);
		  				existencia.setPexSaldo(nuevoSaldo);
		  				existenciaDAO.merge(existencia); 
		  				InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
	 					InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
	 					eboId.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
	  					eboId.getInvPexProductosExistencia().setArtCodigo(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
	  					InvEboExistenciaBodega ebo = eboDAO.findById(eboId); 
	  					int eboNuevaCant = ebo.getEboCantidadProducto() - cant2;
	  					ebo.setEboCantidadProducto(eboNuevaCant); 
	  					ebo.setEboSaldo(costo * eboNuevaCant); 
	  					ebo.setAudFechaModificacion(new Date());
	  					ebo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
	  					eboDAO.merge(ebo); 
		  				tx2.commit();
		  				InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));
						InvTmoTipoMovimientoDAO tipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
						InvMovMovimientos movimientos = new InvMovMovimientos();
						movimientos.setMovId(movimientosDAO.nextId());
						movimientos.setInvArtArticulo(articulo);
						movimientos.setInvBodBodegas(ebo.getId().getInvBodBodegas());
						movimientos.setMovUnidades(cant2);
						movimientos.setMovValor(cant2*costo);
						movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(2));//indica que el movimiento es de salida
						movimientos.setAudFechaCreacion(new Date());
						movimientos.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
						movimientos.setAudFechaModificacion(new Date());
						movimientos.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
						movimientosDAO.save(movimientos);
						tx2.commit();
		  			} catch (Exception e) {
		  				tx2.rollback(); 
		 				e.printStackTrace(); 
		 			}finally{ 
		 				existenciaDAO.getSession().flush();
		 				existenciaDAO.getSession().clear();
		 				//existenciaDAO.getSession().close();
		 			}
				}
		 	}
		 	//Afecta credito, si tuviese
			if(encabezadoForm.getFenTipoPago().equals("C") 
					&& encabezadoForm.getCtaAscAsociado() != null
					&& encabezadoForm.getCtaAscAsociado().getAscId() != null){
				
				//Obtener asociado
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
				CtaAscAsociado asociado = asociadoDAO.findById(encabezadoForm.getCtaAscAsociado().getAscId());
				
				CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
				
				CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
				
				//Se sacan todos los detalles de la factura
				FacDfaDetalleFacturaDAO dfaDao = new FacDfaDetalleFacturaDAO(getSessionHibernate(request));
				List detalles = dfaDao.findByPropertyOrderByTarId("facFenFacturaEncabezado.fenId", fenFacturaEncabezado.getFenId());
				List detalles2 = detalles;
				int size = detalles.size();
				int i =0;
				
				Double totalGeneral = 0.0;
				for (int j=0;j<size;j++) {
					i=j+1;
					//FacDfaDetalleFactura dfa = (FacDfaDetalleFactura) iterator.next();
					FacDfaDetalleFactura dfa = (FacDfaDetalleFactura) detalles.get(j);

					InvArtArticulo artDfa = artArticuloDAO.findById(dfa.getId().getInvArtArticulo().getArtCodigo());
					System.out.println("J="+j);
					System.out.println("I="+i);					
					System.out.println("artDfa en j = "+ j + " " + artDfa.getArtNombre());
					CtaTprTipoPrestamoDAO tprDao = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
					CtaTprTipoPrestamo tpr = tprDao.findByTar(artDfa.getInvTarTipoArticulo().getTarId());
					if(tpr!=null){
						CtaCasCuentaAsociado cas = new CtaCasCuentaAsociado();
						CtaPrePrestamo prestamo = new CtaPrePrestamo();
						List lCas =cuentaAsociadoDAO.findByTprAndAsc(tpr.getTprId(), asociado.getAscId(), "F");
						
						CtrParParametrosDAO par = new CtrParParametrosDAO(getSessionHibernate(request));
						DecimalFormat df = new DecimalFormat("0.00");
						
						Double iva = 0.00, total;
						iva = new Double(df.format((par.findById("IVA").getParValorNumber()/100)));
						
						if(dfa.getDfaExento() == 0) total = new Double(df.format(dfa.getDfaPrecioTotal() + (dfa.getDfaPrecioTotal() * iva)));  
						else total = new Double(df.format(dfa.getDfaPrecioTotal()));
						
						if(lCas.size()>0){
							cas = (CtaCasCuentaAsociado) lCas.get(0);
							prestamo = prestamoDAO.findById(cas.getCtaPrePrestamo().getPreId());
							prestamo.setPreSaldoActualT(prestamo.getPreSaldoActualT() + total/*dfa.getDfaPrecioTotal()*/);
							prestamo.setPreMontoSolicitado(prestamo.getPreSaldoActualT());
							prestamo.setPrePendMov(prestamo.getPrePendMov() + (dfa.getDfaPrecioTotal()*((tpr.getCtaTinTasaInteres().getTinTasa()/12)/100)));
							prestamo.setPreCuota(((prestamo.getPreSaldoActualT() + prestamo.getPrePendMov())/divisor) * multiplicador);
							prestamoDAO.merge(prestamo);
							Transaction txPre = prestamoDAO.getSession().beginTransaction();
							txPre.commit();
						}else{
							prestamo.setCtaTprTipoPrestamo(tpr);
							prestamo.setPreAcumMov(0.0);
							prestamo.setPreCredito("F");
							prestamo.setPrePendMov(dfa.getDfaPrecioTotal()*((tpr.getCtaTinTasaInteres().getTinTasa()/12)/100));
							prestamo.setPreLiquidoARecibir(dfa.getDfaPrecioTotal());
							prestamo.setPreFechaSolicitud(new Date());
							prestamo.setPreMontoSolicitado(dfa.getDfaPrecioTotal());
							prestamo.setPreSaldoActualT(total/*dfa.getDfaPrecioTotal()*/);
							prestamo.setPreMoraMov(0.0);
							prestamo.setPreCuota(((prestamo.getPreSaldoActualT() + prestamo.getPrePendMov())/divisor) * multiplicador);
							prestamo.setPreReferencia(prestamoDAO.generarId("C"));
							prestamo.setPreId(prestamo.getPreReferencia());
							prestamo.setPreInteresAcumulado(0.0);
							prestamo.setCtaTinTasaInteres(null);
							prestamo.setCtaSegSeguros(null);
							prestamo.setCtaCbaCuentaBancaria(null);
							prestamoDAO.save(prestamo);
							Transaction txC1 = prestamoDAO.getSession().beginTransaction();
							txC1.commit();
							
							cas.setCasFechaApertura(new Date());
							cas.setCasPrincipal("N");
							cas.setCtaAscAsociado(asociado);
							cas.setCtaCahCuentaAhorro(null);
							cas.setCtaCbaCuentaBancaria(null);
							cas.setCtaPrePrestamo(prestamo);
							cas.setCtaPxtPersonaExterna(null);
							cas.setCtaSegSeguros(null);
							
							//Estado activo para prestamo 13
							//CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO();
							CtrEstEstado estActivo = estadoDAO.findById(13);
							
							cas.setCtrEstEstado(estActivo);
							cuentaAsociadoDAO.save(cas);
							Transaction txC2 = cuentaAsociadoDAO.getSession().beginTransaction();
							txC2.commit();
						}						
						
						//System.out.println("prestamo.getPreSaldoActualT() = "+prestamo.getPreSaldoActualT());
						CtaTxaTransaccionxcuentaAsociado txa = new CtaTxaTransaccionxcuentaAsociado();
						CtaTxaTransaccionxcuentaAsociadoDAO txaDAO = new CtaTxaTransaccionxcuentaAsociadoDAO(getSessionHibernate(request));
						
						txa.setCtaCasCuentaAsociado(cas);
						CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
						//Cargo por orden, el ttrId es 1
						txa.setCtaTtrTipoTransaccion(tipoTransaccionDAO.findById(1));
						txa.setTxaComprobante(null);
						//CtaTcmTipoComprobanteDAO tipoComprobanteDAO = new CtaTcmTipoComprobanteDAO();
						txa.setTxaFecha(new Date());												
						
						//System.out.println("total = "+total);
						txa.setAudFechaCreacion(new Date());
						txa.setAudFechaModificacion(new Date());
						txa.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
						txa.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());

						
						CtaMxpMovimientoPrestamoDAO movimientoPrestamoDAO = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
						Transaction txmxp = movimientoPrestamoDAO.getSession().beginTransaction();
						CtaMxpMovimientoPrestamo mxpAnt = movimientoPrestamoDAO.findUltimoMovimiento(prestamo.getPreId());
						CtaMxpMovimientoPrestamo movimientoPrestamo = new CtaMxpMovimientoPrestamo();
						if(mxpAnt == null){
							movimientoPrestamo.setCtaPrePrestamo(prestamo);
							movimientoPrestamo.setMxpFecha(new Date());
							movimientoPrestamo.setMxpMora(0.0);
							movimientoPrestamo.setMxpInteresPendiente(0.0);
							movimientoPrestamo.setMxpInteresAcumulado(0.0);
							movimientoPrestamo.setMxpSaldoActual(0.0);
							movimientoPrestamo.setMxpSaldo(prestamo.getPreSaldoActualT());
							movimientoPrestamo.setAudFechaCreacion(new Date());
							movimientoPrestamo.setAudFechaModificacion(new Date());
							movimientoPrestamo.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
							movimientoPrestamo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
							movimientoPrestamo.setCtaTxaTransaccionxcuentaAsociado(txa);
							

						}else{
							movimientoPrestamo.setCtaPrePrestamo(prestamo);
							movimientoPrestamo.setMxpFecha(new Date());
							movimientoPrestamo.setMxpMora(0.0);
							movimientoPrestamo.setMxpSaldoActual(mxpAnt.getMxpSaldoActual());
							movimientoPrestamo.setMxpSaldo(prestamo.getPreSaldoActualT());
							movimientoPrestamo.setMxpInteresPendiente(0.0);
							movimientoPrestamo.setMxpInteresAcumulado(0.0);
							movimientoPrestamo.setAudFechaCreacion(new Date());
							movimientoPrestamo.setAudFechaModificacion(new Date());
							movimientoPrestamo.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
							movimientoPrestamo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
							movimientoPrestamo.setCtaTxaTransaccionxcuentaAsociado(txa);
							

						}
						
						//Esta transaccion y los movimientos solo tiene que hacerlos cuando el siguiente producto despues del actual ya no pertenezca a este prestamo
						if(j<size-1){
							FacDfaDetalleFactura dfaSig = (FacDfaDetalleFactura) detalles2.get(i);
							InvArtArticulo artDfaSig = artArticuloDAO.findById(dfaSig.getId().getInvArtArticulo().getArtCodigo());
							System.out.println("artDfaSig en i = "+ i + " " + artDfaSig.getArtNombre());
							System.out.println("artDfa.getInvTarTipoArticulo().getTarId()="+artDfa.getInvTarTipoArticulo().getTarId());
							System.out.println("artDfaSig.getInvTarTipoArticulo().getTarId()="+artDfaSig.getInvTarTipoArticulo().getTarId());
							if(!artDfa.getInvTarTipoArticulo().getTarId().equals(artDfaSig.getInvTarTipoArticulo().getTarId())){
								System.out.println("Guarde Movimiento");
								
								txa.setTxaMonto(totalGeneral + total);
								txaDAO.save(txa);
								Transaction txpr = txaDAO.getSession().beginTransaction();
								txpr.commit();
								txaDAO.getSession().flush();
								txaDAO.getSession().clear();	
								
								movimientoPrestamoDAO.save(movimientoPrestamo);
								txmxp.commit();
								movimientoPrestamoDAO.getSession().flush();
								movimientoPrestamoDAO.getSession().clear();
								
								totalGeneral = 0.00;
							}
							else{
								System.out.println("Sume");
								totalGeneral = totalGeneral + total;
							}
						}else{
							System.out.println("Guarde Movimiento");
							
							txa.setTxaMonto(totalGeneral + total);
							txaDAO.save(txa);
							Transaction txpr = txaDAO.getSession().beginTransaction();
							txpr.commit();
							txaDAO.getSession().flush();
							txaDAO.getSession().clear();
							
							movimientoPrestamoDAO.save(movimientoPrestamo);
							txmxp.commit();
							movimientoPrestamoDAO.getSession().flush();
							movimientoPrestamoDAO.getSession().clear();	
							
							totalGeneral = 0.00;							
						}
						
						//Llamar a dividir todos los prestamos de orden entre el divisor para sus nuevas cuotas
						OrdenCompraAction ordenCompraAction = new OrdenCompraAction();
						
//						ordenCompraAction.divisorACreditos(asociado.getAscId(), "F", tpr.getTprId(),
//								divisor, encabezadoForm.getUsuarioConectado().getNombreUsuario(),request);
					}else{
						mensajes("error.encabezado.noUnionPrestamo", encabezadoForm, request, response);
						return lista(mapping, encabezadoForm, request, response);
					}
				}
			}
		}
		request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
		encabezadoForm.setImpresa(1);
		encabezadoForm.setNva(true);
		encabezadoForm.setFenId(fenFacturaEncabezado.getFenId());
		return vista(mapping, encabezadoForm, request, response);
		//return cancelar(mapping, form, request, response);
	}
	
	private void enviaAContabilidad(FacFenFacturaEncabezado encabezado, String usuario, HttpServletRequest request) {
		
		//Total con iva
		
		PartidaAutomatica partidaAutomatica =  new PartidaAutomatica();
		
		partidaAutomatica.crearPartidaAutomatica("2;"
				+ encabezado.getFenTipoFactura()+";"
				+ encabezado.getFacFusFacturaUso().getFusId() + ";"
				+ encabezado.getCtrEstEstado().getEstId() + ";"
				+ "1;"
				+ encabezado.getFenCancelada() + ";0", 
				encabezado.getFenTotalVenta(),
				usuario,1,null,null,null,request);
		
		//Total de la factura sin iva

		
		partidaAutomatica.crearPartidaAutomatica("2;"
				+ encabezado.getFenTipoFactura()+";"
				+ encabezado.getFacFusFacturaUso().getFusId() + ";"
				+ encabezado.getCtrEstEstado().getEstId() + ";"
				+ "2;"
				+ encabezado.getFenCancelada() + ";0", 
				(encabezado.getFenTotalVenta() - encabezado.getFenIvaRetenido()),
				usuario,1,null,null,null,request);
		
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		FacFusFacturaUso fuso = facturaUsoDAO.findById(encabezado.getFacFusFacturaUso().getFusId());
		if(fuso.getFusToperacion().equals("v")){
			InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
			FacDfaDetalleFacturaDAO dfaDao = new FacDfaDetalleFacturaDAO(getSessionHibernate(request));
			List dfas = dfaDao.findByProperty("facFenFacturaEncabezado.fenId", encabezado.getFenId());
			Double totalCostos = 0.0;
			for (Iterator iterator = dfas.iterator(); iterator.hasNext();) {
				FacDfaDetalleFactura dfa = (FacDfaDetalleFactura) iterator.next();
				InvArtArticulo articulo = articuloDAO.findById(dfa.getId().getInvArtArticulo().getArtCodigo());
				totalCostos += (articulo.getArtPrecioMinimo()*dfa.getDfaCantidad());
			}
			partidaAutomatica.crearPartidaAutomatica("2;"
					+ encabezado.getFenTipoFactura()+";"
					+ encabezado.getFacFusFacturaUso().getFusId() + ";"
					+ encabezado.getCtrEstEstado().getEstId() + ";"
					+ "0;"
					+ encabezado.getFenCancelada() + ";1", 
					totalCostos,
					usuario,1,null,null,null,request);
			System.out.println("2;"
					+ encabezado.getFenTipoFactura()+";"
					+ encabezado.getFacFusFacturaUso().getFusId() + ";"
					+ encabezado.getCtrEstEstado().getEstId() + ";"
					+ "0;"
					+ encabezado.getFenCancelada() + ";1");
		}
		
		//Total de la Iva Retenido
		if(encabezado.getFenIvaRetenido()>0){
			partidaAutomatica.crearPartidaAutomatica("2;"
					+ encabezado.getFenTipoFactura()+";"
					+ encabezado.getFacFusFacturaUso().getFusId() + ";"
					+ encabezado.getCtrEstEstado().getEstId() + ";"
					+ "3;"
					+ encabezado.getFenCancelada() + ";0", 
					encabezado.getFenIvaRetenido(),
					usuario,1,null,null,null,request);
		}
		System.out.println("2;"
					+ encabezado.getFenTipoFactura()+";"
					+ encabezado.getFacFusFacturaUso().getFusId() + ";"
					+ encabezado.getCtrEstEstado().getEstId() + ";"
					+ "3;"
					+ encabezado.getFenCancelada() + ";0");
	}

	public ActionForward vista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm) form;
		if(encabezadoFacturaForm.getImpresa() == 1){
			request.setAttribute("impresa", 1);
		}
		
		CtrRfcRepositorioFacturasDAO repositorioDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		InvCnvConversionDAO cnvConversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		
		FacFenFacturaEncabezadoDAO encabezadoDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
		encabezadoFacturaForm.setFacturaEncabezadoH(encabezadoDAO.findById(encabezadoFacturaForm.getFenId()));
		
		//Llena info de la original
		if(encabezadoFacturaForm.getView().equals("ND") || encabezadoFacturaForm.getView().equals("NC")){
			FacFenFacturaEncabezado original = encabezadoDAO.findById(encabezadoFacturaForm.getFenOriginal());
			//encabezadoFacturaForm = new EncabezadoFacturaForm();
			encabezadoFacturaForm.setFenSerieFacturaO(original.getFenSerieFactura());
			encabezadoFacturaForm.setFenNumeroFacturaO(original.getFenNumeroFactura());
			/*encabezadoFacturaForm.setCtaAscAsociado(original.getCtaAscAsociado());
			encabezadoFacturaForm.setFacCliCliente(original.getFacCliCliente());
			encabezadoFacturaForm.setFacDfaDetalleFacturas(original.getFacDfaDetalleFacturas());*/
		}
		if(encabezadoFacturaForm.getCtrEstEstado().getEstId() != 2){
			request.setAttribute("anular", 1);
		}
		encabezadoFacturaForm.setTipoPagoId(encabezadoFacturaForm.getFenCancelada());
		
		if(encabezadoFacturaForm.getView().equals("prov")){
			InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
			InvProProveedor proveedor = proveedorDAO.findById(encabezadoFacturaForm.getInvProProveedor().getProId());
			
			encabezadoFacturaForm.setProCodigo(proveedor.getProCodigo());
			encabezadoFacturaForm.setProNit(proveedor.getProNit());
			encabezadoFacturaForm.setProNombre(proveedor.getProNombre());
			encabezadoFacturaForm.setProRegistro(proveedor.getProRegistro());
		}
		
		if(encabezadoFacturaForm.getView().equals("c")){
			if(encabezadoFacturaForm.getFacCliCliente() != null &&
					!encabezadoFacturaForm.getFacCliCliente().getCliCodigo().equals("")){
				encabezadoFacturaForm.setCodCli(encabezadoFacturaForm.getFacCliCliente().getCliCodigo());
			}
			if(encabezadoFacturaForm.getCtaAscAsociado() != null &&
					!encabezadoFacturaForm.getCtaAscAsociado().getAscId().equals("")){
				encabezadoFacturaForm.setCodCli(encabezadoFacturaForm.getCtaAscAsociado().getAscId());
			}	
			
			CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
			CtaAscAsociado asociado = asociadoDAO.findById(encabezadoFacturaForm.getCodCli());
			if(asociado==null){
				FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
				FacCliCliente cliente = clienteDAO.findById(encabezadoFacturaForm.getCodCli());
				encabezadoFacturaForm.setNombreCli(cliente.getCliNombre());
			}else{
				SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
				SecPerPersona persona = personaDAO.findById(asociado.getSecPerPersona().getPerId());
				encabezadoFacturaForm.setCodCli(asociado.getAscCodigo());
				encabezadoFacturaForm.setNombreCli(persona.getPerPrimerApellido() + ", " + persona.getPerPrimerNombre());
			}
		}
		/*if(encabezadoFacturaForm.getFenTipoFactura().equals("NC")){
			encabezadoFacturaForm.setFenTipoFactura("CR");
		}
		if(encabezadoFacturaForm.getFenTipoFactura().equals("ND")){
			encabezadoFacturaForm.setFenTipoFactura("VC");
		}*/
		encabezadoFacturaForm = calcularTotalForm(encabezadoFacturaForm,request);
		
		List<?> lConv = cnvConversionDAO.findAll();
		InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
		List<?> lBod = bodegasDAO.findAll();
		List<?> lusosFac = facturaUsoDAO.findAllV("v",7);//7 es salida ajuste
		List<?> lusosFacC = facturaUsoDAO.findAllV("c",9);//9 es entrada manual

		// Construyendo Jmesa
		TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
		tableFacade.setItems(encabezadoFacturaForm.getFacDfaDetalleFacturas());
		// ---- Genera los tipos de formas con que se podran exportar los
		// datos
		tableFacade.setExportTypes(response, ExportType.CSV,
				ExportType.JEXCEL);// , ExportType.PDF);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		if (limit.isExported()) {
			// ---- exporta la tabla
			exportView(tableFacade,request);
			return null;
		} else {
			// ---- genera el html de la tabla para ser mostrada
			// String html = html(tableFacade, request)
			request.setAttribute(Constantes.LISTA_KEY, htmlView(tableFacade,
					request, encabezadoFacturaForm, encabezadoFacturaForm.getView()));
		}
		request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
		request.setAttribute("modo", "aut");
		request.setAttribute("listaConversiones", lConv);
		request.setAttribute("listaBodegas", lBod);
		request.setAttribute("listaFacturaUso", lusosFac);
		request.setAttribute("listaFacturaUsoC", lusosFacC);
		request.setAttribute("view", encabezadoFacturaForm.getView());
		request.setAttribute("form", encabezadoFacturaForm);
		request.getSession().setAttribute("encabezadoFacturaForm", encabezadoFacturaForm);
		return mapping.findForward("lista");
	}
	
	private String htmlView(final TableFacade tableFacade,
			final HttpServletRequest request,EncabezadoFacturaForm form, final String view) {
		tableFacade.setColumnProperties("id.invArtArticulo.artCodigo",
				"dfaCantidad", "cnvId", "dfaDescripcion", "dfaPrecioUnitario",
				"dfaExento", "dfaPrecioTotal");
		Table table = tableFacade.getTable();
		// ---- Titulo de la tabla

		Row row = table.getRow();

		Column nombreColumna = row.getColumn("id.invArtArticulo.artCodigo");
		nombreColumna.setTitleKey("tbl.detalle.id.invArtArticulo.artCodigo");

		nombreColumna = row.getColumn("dfaDescripcion");
		nombreColumna.setTitleKey("tbl.detalle.dfaDescripcion");

		nombreColumna = row.getColumn("dfaCantidad");
		nombreColumna.setTitleKey("tbl.detalle.dfaCantidad");

		nombreColumna = row.getColumn("cnvId");
		nombreColumna.setTitleKey("tbl.detalle.cnvNombreMedida");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				if(detalle.getCnvId() == -2){
					InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
					InvArtArticulo articulo = articuloDAO.findById(detalle.getId().getInvArtArticulo().getArtCodigo());
					
					InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
					InvMedMedida medida = medidaDAO.findById(articulo.getInvMedMedida().getMedId());
					value = medida.getMedNombreMedida();
				}else{
					value = conversionDAO.findById(detalle.getCnvId())
					.getCnvNombreMedida();
				}
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaPrecioUnitario");
		nombreColumna.setTitleKey("tbl.detalle.dfaPrecioUnitario");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				//value = Redondeo.dRound(detalle.getDfaPrecioUnitario(), 2);
				value = "<div align=\"right\">"+Format.formatDinero(detalle.getDfaPrecioUnitario())+"</div>";
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaExento");
		nombreColumna.setTitleKey("tbl.detalle.dfaExento");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				if (detalle.getDfaExento() == 1) {
					value = "Si";
				} else {
					value = "No";
				}
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaPrecioTotal");
		nombreColumna.setTitleKey("tbl.detalle.dfaPrecioTotal");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				//value = Redondeo.dRound(detalle.getDfaPrecioTotal(), 2);
				value = "<div align=\"right\">"+Format.formatDinero(detalle.getDfaPrecioTotal())+"</div>";
				return value;
			}
		});

		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void exportView(final TableFacade tableFacade,final HttpServletRequest request) {
		 tableFacade.setColumnProperties("id.invArtArticulo.artCodigo",
					"dfaCantidad", "cnvId", "dfaDescripcion", "dfaPrecioUnitario",
					"dfaExento", "dfaPrecioTotal");
			Table table = tableFacade.getTable();
			// ---- Titulo de la tabla
			table.setCaptionKey("tbl.detalle.caption");

			Row row = table.getRow();

			Column nombreColumna = row.getColumn("id.invArtArticulo.artCodigo");
			nombreColumna.setTitleKey("tbl.detalle.id.invArtArticulo.artCodigo.x");

			nombreColumna = row.getColumn("dfaDescripcion");
			nombreColumna.setTitleKey("tbl.detalle.dfaDescripcion.x");

			nombreColumna = row.getColumn("dfaCantidad");
			nombreColumna.setTitleKey("tbl.detalle.dfaCantidad");

			nombreColumna = row.getColumn("cnvId");
			nombreColumna.setTitleKey("tbl.detalle.cnvNombreMedida");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					if(detalle.getCnvId() == -2){
						InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
						InvArtArticulo articulo = articuloDAO.findById(detalle.getId().getInvArtArticulo().getArtCodigo());
						
						InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
						InvMedMedida medida = medidaDAO.findById(articulo.getInvMedMedida().getMedId());
						value = medida.getMedNombreMedida();
					}else{
						value = conversionDAO.findById(detalle.getCnvId())
						.getCnvNombreMedida();
					}
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaPrecioUnitario");
			nombreColumna.setTitleKey("tbl.detalle.dfaPrecioUnitario");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					value = Format.formatDinero(detalle.getDfaPrecioUnitario());
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaExento");
			nombreColumna.setTitleKey("tbl.detalle.dfaExento");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					if (detalle.getDfaExento() == 1) {
						value = "Si";
					} else {
						value = "No";
					}
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaPrecioTotal");
			nombreColumna.setTitleKey("tbl.detalle.dfaPrecioTotal");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					value = Format.formatDinero(detalle.getDfaPrecioTotal());
					return value;
				}
			});

		tableFacade.render();
	}
	
	public ActionForward cargarListaCnv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm)form;
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		try{
			List listaConversiones = conversionDAO.findByMedId(encabezadoFacturaForm.getMedida());
			// Construimos una lista para el response
			String listaResponse = contruirListaConversiones(listaConversiones, request, encabezadoFacturaForm.getArti());

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
	
	private String contruirListaConversiones(List listaConversiones, HttpServletRequest request, String artCod){
		String lista ="<select name=\"cnvId\" class=\"obligatorio\" styleId=\"convert2\" id=\"convert2\""
			+ " onchange=\"ajaxCallSincrono('"+ request.getContextPath() + "/facturacion/encabezadoFactura.do','accion=cargarPrecioUnitario&pu='+ $('#convert2').val() + '&arti=" + artCod + "','tbPU','precioU');\">";
		Iterator it = listaConversiones.iterator();
		lista+="<option value=\"-5\">---</option>";
		InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		InvArtArticulo articulo = articuloDAO.findById(artCod);
		InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
		InvMedMedida medida = medidaDAO.findById(articulo.getInvMedMedida().getMedId());
		lista+="<option value=\"-2\">"+ medida.getMedNombreMedida() +"</option>";
		while(it.hasNext()){
			InvCnvConversion conv = (InvCnvConversion) it.next();
			lista+="<option value=\""+conv.getCnvId()+"\">"+conv.getCnvNombreMedida()+"</option>";
		}
		lista+="</select>";
		return lista;
	}
	
	public ActionForward cargarPrecioUnitario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm)form;
		
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		InvCnvConversion conversion = conversionDAO.findById(encabezadoFacturaForm.getPu());
		
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) request.getSession().getAttribute("encabezadoFacturaForm");
		
		InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		InvArtArticulo articulo = articuloDAO.findById(encabezadoFacturaForm.getArti());
		Double precio;
		String readonly="";
		if(facturaForm.getAck().equals("prov")){
			if(encabezadoFacturaForm.getPu()!= -1 && encabezadoFacturaForm.getPu()==-2){
				precio = articulo.getArtPrecioMinimo();
			}else{
				precio = articulo.getArtPrecioMinimo()*conversion.getCnvFactor();
			}
		}else{
			if(encabezadoFacturaForm.getPu()!= -1 && encabezadoFacturaForm.getPu()==-2){
				precio = articulo.getArtPrecioSugerido();
			}else{
				if(encabezadoFacturaForm.getPu()==-5){
					precio = 0.0;
				}else{
					precio = articulo.getArtPrecioSugerido()*conversion.getCnvFactor();
				}
			}
			readonly="readonly=\"true\"";
		}
		try{
			System.out.println("Precio: "+precio);
			String texto = "<script type=\"text/javascript\">" +
					"$(document).ready(function(){" +
					"$(\"#precioUId\").numeric({allow:\".\"});" +
					"});</script>";
			texto += "<input type=\"text\" name=\"dfaPrecioUnitario\" size=\"15\" maxlength=\"15\" styleClass=\"obligatorio\" value=\"" + Format.formatDineroSinSimbolo(precio) + "\" id=\"precioUId\" " + readonly + " />";
			response.getWriter().write(texto);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (RuntimeException e) {
			log.error("Error runtime", e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	public void mensajes(String msg,EncabezadoFacturaForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
	}
	
	public void mensajes2(String msg,EncabezadoFacturaForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionErrors errors){
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
	}
	
	public ActionForward imprimirGuardado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoForm = (EncabezadoFacturaForm)form;
		
		//Iniciar divisor
		Double divisor = 0.0;
		
		InvPexProductosExistenciaDAO existenciaDAO = new InvPexProductosExistenciaDAO(getSessionHibernate(request)); 
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		
		Transaction tx2 = existenciaDAO.getSession().beginTransaction();
		
		FacFenFacturaEncabezadoDAO   encabezadoDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
		FacFenFacturaEncabezado fenFacturaEncabezado = encabezadoDAO.findById(encabezadoForm.getFenId());
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		fenFacturaEncabezado.setCtrEstEstado(estadoDAO.findById(1));
		
		// Agregar la sucursal a la factura
		SecSucSucursalDAO sucursalDAO = new SecSucSucursalDAO(getSessionHibernate(request));
		SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		SecIseInicioSesion inicioSesion = inicioSesionDAO.findById(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona persona = personaDAO.findById(inicioSesion.getSecPerPersona().getPerId());
		SecSucSucursal sucursal = sucursalDAO.findById(persona.getSecSucSucursal().getSucId());
		fenFacturaEncabezado.setSecSucSucursal(sucursal);
		//Terminamos de agregarla
		
		fenFacturaEncabezado.setFenFechaFactura(new Date());
		fenFacturaEncabezado.setAudFechaModificacion(new Date());
		fenFacturaEncabezado.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		if(facturaUsoDAO.findById(fenFacturaEncabezado.getFacFusFacturaUso().getFusId()).getFusToperacion().equals("v")){
//			SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO();
			CtrRfcRepositorioFacturasDAO repositorioDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
			CtrRfcRepositorioFacturas repositorio = (CtrRfcRepositorioFacturas) repositorioDAO.findSerie(
					fenFacturaEncabezado.getFenTipoFactura(),inicioSesionDAO.findById(
					encabezadoForm.getUsuarioConectado().getNombreUsuario())
					.getSecPerPersona().getSecSucSucursal().getSucId() ).get(0);
			fenFacturaEncabezado.setFenSerieFactura(repositorio.getCtrCfcControlFacturacion().getCfcSerie());
			fenFacturaEncabezado.setFenNumeroFactura(Format.formatNumeroFactura(repositorio.getRfcCorrActual(), repositorio.getCtrCfcControlFacturacion().getCfcDigitos()));
		}
		encabezadoDAO.merge(fenFacturaEncabezado);
		tx2.commit();
		
		//Envia a contabilidad
		enviaAContabilidad(fenFacturaEncabezado,encabezadoForm.getUsuarioConectado().getNombreUsuario(),request);
		
		InvArtArticuloDAO artArticuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
//		Iterator it = fenFacturaEncabezado.getFacDfaDetalleFacturas().iterator();
//		fenFacturaEncabezado.getFenId();
		FacDfaDetalleFacturaDAO dfaDAO = new FacDfaDetalleFacturaDAO(getSessionHibernate(request));
		Iterator it = dfaDAO.findByProperty("facFenFacturaEncabezado", fenFacturaEncabezado).iterator();
		
		if(facturaUsoDAO.findById(fenFacturaEncabezado.getFacFusFacturaUso().getFusId()).getFusToperacion().equals("c")){//compra
			while(it.hasNext()){
				FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) it.next();
				InvArtArticulo articulo = new InvArtArticulo();
				articulo = artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
				double costoIng;
				int existenciaActual = existenciaDAO.findById(articulo.getArtCodigo()).getPexCantidadProducto();
				int cantIngreso;
				if(detalleFactura.getCnvId() == -2){
					cantIngreso = detalleFactura.getDfaCantidad();
					costoIng = detalleFactura.getDfaPrecioUnitario();
				}else{
					InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
					cantIngreso = detalleFactura.getDfaCantidad()*conversion.getCnvFactor().intValue();
					costoIng = detalleFactura.getDfaPrecioUnitario()/conversion.getCnvFactor().intValue();
				}
				int existenciaNva = existenciaActual + cantIngreso;
				double saldoActual = existenciaDAO.findById(articulo.getArtCodigo()).getPexSaldo();
				double saldoIng = costoIng * cantIngreso;
				double nuevoSaldo = saldoActual + saldoIng;
				double nuevoCosto = nuevoSaldo/(existenciaNva);
				 
				
				//Nuevo costo implica modificaciones al articulo
				articulo.setArtPrecioMinimo(nuevoCosto);
				articulo.setArtPrecioSugerido(articulo.getArtPrecioMinimo()*(1+(articulo.getArtPorcentajeUtilidad()/100)));
				articulo.setAudFechaModificacion(new Date());
				articulo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
				artArticuloDAO.merge(articulo);
				InvSmeSaldoMensualDAO smeDAO = new InvSmeSaldoMensualDAO(getSessionHibernate(request));
				InvSmeSaldoMensual sme = smeDAO.findByCodArtAndDate(articulo.getArtCodigo(), new Date());
				if (sme != null) {
					sme.setSmeCostoArt(nuevoCosto);
					sme.setSmeSaldo(nuevoCosto*sme.getSmeCantidad());
					Transaction txsme = smeDAO.getSession().beginTransaction();
					txsme.commit();
				}
				Transaction txArt = artArticuloDAO.getSession().beginTransaction();
				txArt.commit();
				try {
					InvPexProductosExistencia existencia = existenciaDAO.findById(articulo.getArtCodigo());
					existencia.setAudFechaModificacion(new Date());
					existencia.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					existencia.setPexCantidadProducto(existenciaNva);
					existencia.setPexCostoProducto(nuevoCosto);
					existencia.setPexSaldo(nuevoSaldo); 
					existenciaDAO.merge(existencia);
					Transaction tx3 = existenciaDAO.getSession().beginTransaction();
					tx3.commit();
					articulo.setArtPrecioMinimo(nuevoCosto);
					articulo.setArtPrecioSugerido(nuevoCosto *(1+(articulo.getArtPorcentajeUtilidad()/100)));
					tx3.commit();
					InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request)); 
					InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
					eboId.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
					eboId.getInvPexProductosExistencia().setArtCodigo(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
					InvEboExistenciaBodega ebo = eboDAO.findById(eboId); 
					int eboNuevaCant = ebo.getEboCantidadProducto() + cantIngreso; 
					InvCprCapacidadProductoDAO capacidadProductoDAO = new InvCprCapacidadProductoDAO(getSessionHibernate(request));
					InvCprCapacidadProductoId id2 = new InvCprCapacidadProductoId();
					id2.setInvArtArticulo(articulo); 
					id2.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
					InvCprCapacidadProducto capacidadProducto = capacidadProductoDAO.findById(id2);
					if(capacidadProducto.getCprCantidadMaxima() < eboNuevaCant){//esta verificacion se usa
						//para que (por pedido del cliente) si la cantidad maxima se supera con la nueva 
						//compra, se aumente automaticamente la cantidad maxima
							capacidadProducto.setCprCantidadMaxima(eboNuevaCant);
							capacidadProductoDAO.merge(capacidadProducto); 
							tx3.commit(); 
						}
					ebo.setEboCantidadProducto(eboNuevaCant); 
					ebo.setEboSaldo(nuevoCosto*eboNuevaCant); 
					ebo.setAudFechaModificacion(new Date());
					ebo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					eboDAO.merge(ebo); 
					tx3.commit();
					InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));
					InvTmoTipoMovimientoDAO tipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
					InvMovMovimientos movimientos = new InvMovMovimientos();
					movimientos.setMovId(movimientosDAO.nextId());
					movimientos.setInvArtArticulo(articulo);
					movimientos.setInvBodBodegas(ebo.getId().getInvBodBodegas());
					movimientos.setMovUnidades(cantIngreso);
					movimientos.setMovValor(saldoIng);
					movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(1));//indica que el movimiento es de entrada
					movimientos.setAudFechaCreacion(new Date());
					movimientos.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientos.setAudFechaModificacion(new Date());
					movimientos.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientosDAO.save(movimientos);
					tx3.commit();
				} catch (Exception e) {
					Transaction tx3 = existenciaDAO.getSession().beginTransaction();
					tx3.rollback(); 
					e.printStackTrace(); 
				}finally{ 
	 				existenciaDAO.getSession().flush();
	 				existenciaDAO.getSession().clear();
	 				
	 			}
			}
		}else{//si es salida se utiliza toda esta porcion de codigo
			
			CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
			divisor = parametrosDAO.findById("NUM_CUOTAS_ORDEN_COMPRA").getParValorNumber();
			Double multiplicador = parametrosDAO.findById("DIVISOR_CUOTAS_MES").getParValorNumber();
			
			if(fenFacturaEncabezado.getFenCancelada().equals("C")/*fenFacturaEncabezado.getFenCancelada().equals("P")*/){
				//cargar disponible para comparacion
				CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
				Double creditoUsado = prestamoDAO.sumCreditoUtilizado(fenFacturaEncabezado.getCtaAscAsociado().getAscId(),"F");
				if(creditoUsado == null){
					creditoUsado = 0.0;
				}
				Double disponible = 0.0;
				CtrParParametros creditoOrden = parametrosDAO.findById("CREDITO_FACT");
				disponible = creditoOrden.getParValorNumber() - creditoUsado - fenFacturaEncabezado.getFenTotalVenta();
				
				if(disponible == 0){
					mensajes("error.encabezado.masDeLoQuePuede", encabezadoForm, request, response);
					return lista(mapping, encabezadoForm, request, response);
				}
			}
			
		 	CtrRfcRepositorioFacturasDAO rfDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
		 	Transaction transaction = rfDAO.getSession().beginTransaction();
		 	
		 	CtrRfcRepositorioFacturas rfcRepositorioFacturas = (CtrRfcRepositorioFacturas) rfDAO.findSerie(encabezadoForm.getFenTipoFactura(), persona.getSecSucSucursal().getSucId()).get(0);
		 	rfcRepositorioFacturas.setRfcCorrActual(rfcRepositorioFacturas.getRfcCorrActual() + 1); 
		 	rfDAO.merge(rfcRepositorioFacturas); 
		 	transaction.commit(); 
		 	while(it.hasNext()){
		 		FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) it.next();
				if(artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo())!=null){ 
					InvArtArticulo articulo = artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
		 			double costo = detalleFactura.getDfaPrecioUnitario();
		 			int cant1 = existenciaDAO.findById(articulo.getArtCodigo()).getPexCantidadProducto();
		 			int cant2;
		 			if(detalleFactura.getCnvId() == -2){
		 				cant2 = detalleFactura.getDfaCantidad();
		 			}else{
			 			InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
						cant2 = detalleFactura.getDfaCantidad()*conversion.getCnvFactor().intValue();
		 			}
					double nuevoSaldo = costo * (cant1 - cant2); 
					if(nuevoSaldo<0){
						mensajes("errors.encabezado.vendiendoMax", encabezadoForm, request, response);
						return lista(mapping, encabezadoForm, request, response);
					}
		  			try {
		  				InvPexProductosExistencia existencia = existenciaDAO.findById(articulo.getArtCodigo());
						existencia.setAudFechaModificacion(new Date());
		  				existencia.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		 				existencia.setPexCantidadProducto(cant1 - cant2);
		  				existencia.setPexSaldo(nuevoSaldo);
		  				existenciaDAO.merge(existencia); 
		  				InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
	 					InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
	 					eboId.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
	  					eboId.getInvPexProductosExistencia().setArtCodigo(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
	  					InvEboExistenciaBodega ebo = eboDAO.findById(eboId); int eboNuevaCant =
	  					ebo.getEboCantidadProducto() - cant2;
	  					ebo.setEboCantidadProducto(eboNuevaCant); ebo.setEboSaldo(costo * eboNuevaCant); 
	  					ebo.setAudFechaModificacion(new Date());
	  					ebo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
	  					eboDAO.merge(ebo); 
	  					Transaction tx3 = eboDAO.getSession().beginTransaction();
	  					tx3.commit();
		  				InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));
						InvTmoTipoMovimientoDAO tipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
						InvMovMovimientos movimientos = new InvMovMovimientos();
						movimientos.setMovId(movimientosDAO.nextId());
						movimientos.setInvArtArticulo(articulo);
						movimientos.setInvBodBodegas(ebo.getId().getInvBodBodegas());
						movimientos.setMovUnidades(cant2);
						movimientos.setMovValor(cant2*costo);
						movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(2));//indica que el movimiento es de salida
						movimientos.setAudFechaCreacion(new Date());
						movimientos.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
						movimientos.setAudFechaModificacion(new Date());
						movimientos.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
						movimientosDAO.save(movimientos);
						tx3.commit();
						
						
						
		  			} catch (Exception e) {
		  				Transaction tx3 = existenciaDAO.getSession().beginTransaction();
		  				tx3.rollback(); 
		 				e.printStackTrace(); 
		 			}finally{ 
		 				existenciaDAO.getSession().flush();
		 				existenciaDAO.getSession().clear();
		 				
		 				/*if(fenFacturaEncabezado.getCtaAscAsociado() != null){
		 					if(fenFacturaEncabezado.getCtaAscAsociado().getAscId() != null && fenFacturaEncabezado.getFenTipoPago()!=null && fenFacturaEncabezado.getFenTipoPago().equals("C")){
				 				
			 				}
		 				}*/
		 				
		 			}
		 			if(fenFacturaEncabezado.getFenTipoPago()!=null
		 				&& fenFacturaEncabezado.getFenTipoPago().equals("C") 
		 				&& fenFacturaEncabezado.getCtaAscAsociado() != null
						&& fenFacturaEncabezado.getCtaAscAsociado().getAscId() != null){
		 				afectaCredito(detalleFactura, fenFacturaEncabezado.getCtaAscAsociado().getAscId(), 
		 						encabezadoForm.getUsuarioConectado().getNombreUsuario(),divisor, multiplicador,request);
		 			}
				}
		 	}
		}
		return cancelar(mapping, form, request, response);
	}
	
	/*Nota de credito
	 * y Nota de debito
	 */
	
	private void afectaCredito(FacDfaDetalleFactura detalleFactura, 
			String ascId, String usuario, Double divisor, Double multiplicador,HttpServletRequest request) {
		//Se sacan todos los detalles de la factura
		InvArtArticuloDAO artArticuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado asociado = asociadoDAO.findById(ascId);
		
		FacDfaDetalleFactura dfa = detalleFactura;
		InvArtArticulo artDfa = artArticuloDAO.findById(dfa.getId().getInvArtArticulo().getArtCodigo());
		
		CtaTprTipoPrestamoDAO tprDao = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		CtaTprTipoPrestamo tpr = tprDao.findByTar(artDfa.getInvTarTipoArticulo().getTarId());
		
		CtaCasCuentaAsociado cas = new CtaCasCuentaAsociado();
		CtaPrePrestamo prestamo = new CtaPrePrestamo();
		List lCas =cuentaAsociadoDAO.findByTprAndAsc(tpr.getTprId(), asociado.getAscId(), "F");
		if(lCas.size()>0){
			cas = (CtaCasCuentaAsociado) lCas.get(0);
			prestamo = prestamoDAO.findById(cas.getCtaPrePrestamo().getPreId());
			prestamo.setPreSaldoActualT(prestamo.getPreSaldoActualT() + dfa.getDfaPrecioTotal());
			prestamo.setPreMontoSolicitado(prestamo.getPreSaldoActualT());
			prestamo.setPrePendMov(prestamo.getPrePendMov() + (dfa.getDfaPrecioTotal()*((tpr.getCtaTinTasaInteres().getTinTasa()/12)/100)));
			prestamo.setPreCuota(((prestamo.getPreSaldoActualT() + prestamo.getPrePendMov())/divisor) * multiplicador);
			prestamoDAO.merge(prestamo);
			Transaction txPre = prestamoDAO.getSession().beginTransaction();
			txPre.commit();
		}else{
			prestamo.setCtaTprTipoPrestamo(tpr);
			prestamo.setPreAcumMov(0.0);
			prestamo.setPreCredito("F");
			prestamo.setPrePendMov(dfa.getDfaPrecioTotal()*((tpr.getCtaTinTasaInteres().getTinTasa()/12)/100));
			prestamo.setPreLiquidoARecibir(dfa.getDfaPrecioTotal());
			prestamo.setPreFechaSolicitud(new Date());
			prestamo.setPreMontoSolicitado(dfa.getDfaPrecioTotal());
			prestamo.setPreSaldoActualT(dfa.getDfaPrecioTotal());
			prestamo.setPreMoraMov(0.0);
			prestamo.setPreCuota(((prestamo.getPreSaldoActualT() + prestamo.getPrePendMov())/divisor) * multiplicador);
			prestamo.setPreReferencia(prestamoDAO.generarId("C"));
			prestamo.setPreId(prestamo.getPreReferencia());
			prestamo.setPreInteresAcumulado(0.0);
			prestamo.setCtaTinTasaInteres(null);
			prestamo.setCtaSegSeguros(null);
			prestamoDAO.save(prestamo);
			Transaction txC1 = prestamoDAO.getSession().beginTransaction();
			txC1.commit();
			
			cas.setCasFechaApertura(new Date());
			cas.setCasPrincipal("N");
			cas.setCtaAscAsociado(asociado);
			cas.setCtaCahCuentaAhorro(null);
			cas.setCtaCbaCuentaBancaria(null);
			cas.setCtaPrePrestamo(prestamo);
			cas.setCtaPxtPersonaExterna(null);
			cas.setCtaSegSeguros(null);
			
			//Estado activo para prestamo 13
			//CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO();
			CtrEstEstado estActivo = estadoDAO.findById(13);
			
			cas.setCtrEstEstado(estActivo);
			cuentaAsociadoDAO.save(cas);
			Transaction txC2 = cuentaAsociadoDAO.getSession().beginTransaction();
			txC2.commit();
		}
		
		CtaTxaTransaccionxcuentaAsociado txa = new CtaTxaTransaccionxcuentaAsociado();
		CtaTxaTransaccionxcuentaAsociadoDAO txaDAO = new CtaTxaTransaccionxcuentaAsociadoDAO(getSessionHibernate(request));
		
		txa.setCtaCasCuentaAsociado(cas);
		CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
		//Cargo por orden, el ttrId es 1
		txa.setCtaTtrTipoTransaccion(tipoTransaccionDAO.findById(1));
		txa.setTxaComprobante(txaDAO.nextComprobante());
		//CtaTcmTipoComprobanteDAO tipoComprobanteDAO = new CtaTcmTipoComprobanteDAO();
		txa.setTxaFecha(new Date());
		txa.setTxaMonto(dfa.getDfaPrecioTotal());
		txa.setAudFechaCreacion(new Date());
		txa.setAudFechaModificacion(new Date());
		txa.setAudUsuarioCreacion(usuario);
		txa.setAudUsuarioModificacion(usuario);
		txaDAO.save(txa);
		Transaction txpr = txaDAO.getSession().beginTransaction();
		txpr.commit();
		txaDAO.getSession().flush();
		txaDAO.getSession().clear();
		
		CtaMxpMovimientoPrestamoDAO movimientoPrestamoDAO = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
		Transaction txmxp = movimientoPrestamoDAO.getSession().beginTransaction();
		CtaMxpMovimientoPrestamo mxpAnt = movimientoPrestamoDAO.findUltimoMovimiento(prestamo.getPreId());
		CtaMxpMovimientoPrestamo movimientoPrestamo = new CtaMxpMovimientoPrestamo();
		if(mxpAnt == null){
			movimientoPrestamo.setCtaPrePrestamo(prestamo);
			movimientoPrestamo.setMxpFecha(new Date());
			movimientoPrestamo.setMxpMora(0.0);
			movimientoPrestamo.setMxpInteresPendiente(0.0);
			movimientoPrestamo.setMxpInteresAcumulado(0.0);
			movimientoPrestamo.setMxpSaldoActual(0.0);
			movimientoPrestamo.setAudFechaCreacion(new Date());
			movimientoPrestamo.setAudFechaModificacion(new Date());
			movimientoPrestamo.setAudUsuarioCreacion(usuario);
			movimientoPrestamo.setAudUsuarioModificacion(usuario);
			movimientoPrestamo.setCtaTxaTransaccionxcuentaAsociado(txa);
			
			movimientoPrestamoDAO.save(movimientoPrestamo);
			txmxp.commit();
			movimientoPrestamoDAO.getSession().flush();
			movimientoPrestamoDAO.getSession().clear();
		}else{
			movimientoPrestamo.setCtaPrePrestamo(prestamo);
			movimientoPrestamo.setMxpFecha(new Date());
			movimientoPrestamo.setMxpMora(0.0);
			movimientoPrestamo.setMxpSaldoActual(0.0);
			movimientoPrestamo.setMxpInteresPendiente(0.0);
			movimientoPrestamo.setMxpInteresAcumulado(0.0);
			movimientoPrestamo.setAudFechaCreacion(new Date());
			movimientoPrestamo.setAudFechaModificacion(new Date());
			movimientoPrestamo.setAudUsuarioCreacion(usuario);
			movimientoPrestamo.setAudUsuarioModificacion(usuario);
			movimientoPrestamo.setCtaTxaTransaccionxcuentaAsociado(txa);
			
			movimientoPrestamoDAO.save(movimientoPrestamo);
			txmxp.commit();
			movimientoPrestamoDAO.getSession().flush();
			movimientoPrestamoDAO.getSession().clear();
		}
		
		//Llamar a dividir todos los prestamos de orden entre el divisor para sus nuevas cuotas
		OrdenCompraAction ordenCompraAction = new OrdenCompraAction();
		
//		ordenCompraAction.divisorACreditos(asociado.getAscId(), "F", tpr.getTprId(),
//				divisor,usuario,request);
	}

	// ---- Funcion que genera el html de la tabla del jmesa
	private String htmlNotas(final TableFacade tableFacade,
			final HttpServletRequest request,EncabezadoFacturaForm form, final String view) {
		tableFacade.setColumnProperties("id.invArtArticulo.artCodigo",
				"dfaCantidad", "cnvId", "dfaDescripcion", "dfaPrecioUnitario",
				"dfaExento", "dfaPrecioTotal", "audUsuarioCreacion");
		Table table = tableFacade.getTable();
		// ---- Titulo de la tabla

		Row row = table.getRow();

		Column nombreColumna = row.getColumn("id.invArtArticulo.artCodigo");
		nombreColumna.setTitleKey("tbl.detalle.id.invArtArticulo.artCodigo");

		nombreColumna = row.getColumn("dfaDescripcion");
		nombreColumna.setTitleKey("tbl.detalle.dfaDescripcion");

		nombreColumna = row.getColumn("dfaCantidad");
		nombreColumna.setTitleKey("tbl.detalle.dfaCantidad");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura)item;
				HtmlBuilder html = new HtmlBuilder();
				html.input().type("text").name("valores").value(""+detalleFactura.getDfaCantidad()+"").size("10").close();
				return html.toString();
			}
		});

		nombreColumna = row.getColumn("cnvId");
		nombreColumna.setTitleKey("tbl.detalle.cnvNombreMedida");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				if(detalle.getCnvId() == -2){
					InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
					InvArtArticulo articulo = articuloDAO.findById(detalle.getId().getInvArtArticulo().getArtCodigo());
					
					InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
					InvMedMedida medida = medidaDAO.findById(articulo.getInvMedMedida().getMedId());
					value = medida.getMedNombreMedida();
				}else{
					value = conversionDAO.findById(detalle.getCnvId())
					.getCnvNombreMedida();
				}
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaPrecioUnitario");
		nombreColumna.setTitleKey("tbl.detalle.dfaPrecioUnitario");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				//value = Redondeo.dRound(detalle.getDfaPrecioUnitario(), 2);
				value = "<div align=\"right\">"+Format.formatDinero(detalle.getDfaPrecioUnitario())+"</div>";
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaExento");
		nombreColumna.setTitleKey("tbl.detalle.dfaExento");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				if (detalle.getDfaExento() == 1) {
					value = "Si";
				} else {
					value = "No";
				}
				return value;
			}
		});

		nombreColumna = row.getColumn("dfaPrecioTotal");
		nombreColumna.setTitleKey("tbl.detalle.dfaPrecioTotal");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				//value = Redondeo.dRound(detalle.getDfaPrecioTotal(), 2);
				value = "<div align=\"right\">"+Format.formatDinero(detalle.getDfaPrecioTotal())+"</div>";
				return value;
			}
		});

		nombreColumna = row.getColumn("audUsuarioCreacion");
		nombreColumna.setTitleKey("tbl.detalle.devolver");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property,
						rowcount);
				FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
				HtmlBuilder html = new HtmlBuilder();
				html.input().type("checkbox").name("posiciones").value(""+pos+"").close();//onchange("submitear();").
				pos++;
				return html.toString();
			}
		});
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void exportNotas(final TableFacade tableFacade,final HttpServletRequest request) {
		 tableFacade.setColumnProperties("id.invArtArticulo.artCodigo",
					"dfaCantidad", "cnvId", "dfaDescripcion", "dfaPrecioUnitario",
					"dfaExento", "dfaPrecioTotal");
			Table table = tableFacade.getTable();
			// ---- Titulo de la tabla
			table.setCaptionKey("tbl.detalle.caption");

			Row row = table.getRow();

			Column nombreColumna = row.getColumn("id.invArtArticulo.artCodigo");
			nombreColumna.setTitleKey("tbl.detalle.id.invArtArticulo.artCodigo.x");

			nombreColumna = row.getColumn("dfaDescripcion");
			nombreColumna.setTitleKey("tbl.detalle.dfaDescripcion.x");

			nombreColumna = row.getColumn("dfaCantidad");
			nombreColumna.setTitleKey("tbl.detalle.dfaCantidad");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property, rowcount);
					FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura)item;
					HtmlBuilder html = new HtmlBuilder();
					value = detalleFactura.getDfaCantidad();
					return value;
				}
			});

			nombreColumna = row.getColumn("cnvId");
			nombreColumna.setTitleKey("tbl.detalle.cnvNombreMedida");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					if(detalle.getCnvId() == -2){
						InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
						InvArtArticulo articulo = articuloDAO.findById(detalle.getId().getInvArtArticulo().getArtCodigo());
						
						InvMedMedidaDAO medidaDAO = new InvMedMedidaDAO(getSessionHibernate(request));
						InvMedMedida medida = medidaDAO.findById(articulo.getInvMedMedida().getMedId());
						value = medida.getMedNombreMedida();
					}else{
						value = conversionDAO.findById(detalle.getCnvId())
						.getCnvNombreMedida();
					}
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaPrecioUnitario");
			nombreColumna.setTitleKey("tbl.detalle.dfaPrecioUnitario");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					value = Format.formatDinero(detalle.getDfaPrecioUnitario());
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaExento");
			nombreColumna.setTitleKey("tbl.detalle.dfaExento");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {

				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					if (detalle.getDfaExento() == 1) {
						value = "Si";
					} else {
						value = "No";
					}
					return value;
				}
			});

			nombreColumna = row.getColumn("dfaPrecioTotal");
			nombreColumna.setTitleKey("tbl.detalle.dfaPrecioTotal");
			nombreColumna.getCellRenderer().setCellEditor(new CellEditor() {
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new BasicCellEditor().getValue(item, property,
							rowcount);
					FacDfaDetalleFactura detalle = (FacDfaDetalleFactura) item;
					//value = Redondeo.dRound(detalle.getDfaPrecioTotal(), 2);
					value = Format.formatDinero(detalle.getDfaPrecioTotal());
					return value;
				}
			});

		tableFacade.render();
	}
	
	public ActionForward notaCredito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) form;
		facturaForm.setAck("NC");
		//facturaForm.setFenTipoFactura("NC");
		request.setAttribute("form", facturaForm);
		return lista(mapping, form, request, response);
	}
	
	public ActionForward notaDebito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm) form;
		/*EncabezadoFacturaForm newForm = new EncabezadoFacturaForm();
		FacFenFacturaEncabezadoDAO encabezadoDAO = new FacFenFacturaEncabezadoDAO();
		FacFenFacturaEncabezado fen = encabezadoDAO.findById(facturaForm.getFenId());
		newForm.setFacturaEncabezadoH(fen);
		newForm.setAck("ND");
		request.setAttribute("form", newForm);
		request.getSession().setAttribute("encabezadoFacturaForm", newForm);*/
		facturaForm.setAck("ND");
		//facturaForm.setFenTipoFactura("ND");
		request.setAttribute("form", facturaForm);
		return lista(mapping, form, request, response);
	}
	
	public ActionForward imprimirNota(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		FacFenFacturaEncabezadoDAO encabezadoDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
		ConRimRetencionImpuestoDAO impuestoDAO = new ConRimRetencionImpuestoDAO(getSessionHibernate(request));
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		EncabezadoFacturaForm encabezadoForm = (EncabezadoFacturaForm)request.getSession().getAttribute("encabezadoFacturaForm");
		EncabezadoFacturaForm encabezadoFormReq = (EncabezadoFacturaForm) form;
		
		FacFenFacturaEncabezado encabezado = new FacFenFacturaEncabezado();
		
		
		//Verificamos si hay notas por devolucion hechas sobre esta misma factura
		//De ser asi, debemos validar el disponible a devolver
		FacFenFacturaEncabezado facturaOriginal = encabezadoDAO.findById(encabezadoForm.getFenOriginal());
		
		// Agregar la sucursal a la factura
		SecSucSucursalDAO sucursalDAO = new SecSucSucursalDAO(getSessionHibernate(request));
		SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO(getSessionHibernate(request));
		SecIseInicioSesion inicioSesion = inicioSesionDAO.findById(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		SecPerPersonaDAO personaDAO = new SecPerPersonaDAO(getSessionHibernate(request));
		SecPerPersona persona = personaDAO.findById(inicioSesion.getSecPerPersona().getPerId());
		SecSucSucursal sucursal = sucursalDAO.findById(persona.getSecSucSucursal().getSucId());
		encabezado.setSecSucSucursal(sucursal);
		//Terminamos de agregarla
		
		encabezado.setFenId(encabezadoDAO.nextId());
		encabezado.setCtrEstEstado(estadoDAO.findById(1));//seteamos el estado de impresa

		//Union con cliente
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
		if(encabezadoForm.getCtaAscAsociado() == null || encabezadoForm.getCtaAscAsociado().getAscId() == null){
			if(encabezadoForm.getFacCliCliente()!= null){
				encabezado.setFacCliCliente(clienteDAO.findById(encabezadoForm.getFacCliCliente().getCliCodigo()));
				encabezado.setCtaAscAsociado(null);
			}else{
				encabezado.setFacCliCliente(null);
				encabezado.setCtaAscAsociado(null);
			}
		}else{
			encabezado.setCtaAscAsociado(asociadoDAO.findById(encabezadoForm.getCtaAscAsociado().getAscId()));
			encabezado.setFacCliCliente(null);
		}
		
		encabezado.setFenFechaFactura(new Date());
		encabezado.setFenOriginal(encabezadoForm.getFenOriginal());
		encabezado.setFenTipoFactura(encabezadoForm.getAck());
		encabezado.setFenTotalVenta(encabezadoForm.getFenTotalVenta());
		encabezado.setFenTotalVentasExentas(encabezado.getFenTotalVentasExentas());
		
		InvBodBodegasDAO bodegasDAO = new InvBodBodegasDAO(getSessionHibernate(request));
		encabezado.setInvBodBodegas(bodegasDAO.findById(encabezadoForm.getInvBodBodegas().getBodId()));
		
		encabezadoForm.setValores(encabezadoFormReq.getValores());
		encabezadoForm.setPosiciones(encabezadoFormReq.getPosiciones());

		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		Integer repoId = -1;
		if(encabezadoForm.getAck().equals("NC")){//si es nota de credito
			// o compra a proveedor se asigna el codigo al form de encabezado
			encabezado.setFenTipoFactura("NC");
			//encabezadoForm.setFenTipoFactura("NC");
			CtrRfcRepositorioFacturasDAO repositorioDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
//			SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO();
			CtrRfcRepositorioFacturas repositorio = (CtrRfcRepositorioFacturas) repositorioDAO.findSerie(encabezadoForm.getAck()/*getFenTipoFactura()*/,inicioSesionDAO.findById(encabezadoForm.getUsuarioConectado().getNombreUsuario()).getSecPerPersona().getSecSucSucursal().getSucId() ).get(0);
			repoId = repositorio.getRfcId();
			encabezado.setFenSerieFactura(repositorio.getCtrCfcControlFacturacion().getCfcSerie());
			encabezado.setFenNumeroFactura(Format.formatNumeroFactura(repositorio.getRfcCorrActual(), repositorio.getCtrCfcControlFacturacion().getCfcDigitos()));
			//entrada por nota de credito, id 7
			encabezado.setFacFusFacturaUso(facturaUsoDAO.findById(7));
			InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
			encabezado.setInvProProveedor(proveedorDAO.findById(encabezadoForm.getInvProProveedor().getProId()));
			/**/
			//encabezadoForm.setFenTipoFactura("CR");
			/**/
		}
		else{//sino, se asigna el cliente al form
			encabezado.setFenTipoFactura("ND");
			encabezado.setInvProProveedor(null);
			CtrRfcRepositorioFacturasDAO repositorioDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
//			SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO();
			CtrRfcRepositorioFacturas repositorio = (CtrRfcRepositorioFacturas) repositorioDAO.findSerie(encabezado.getFenTipoFactura(),inicioSesionDAO.findById(encabezadoForm.getUsuarioConectado().getNombreUsuario()).getSecPerPersona().getSecSucSucursal().getSucId() ).get(0);
			repoId = repositorio.getRfcId();
			encabezado.setFenSerieFactura(repositorio.getCtrCfcControlFacturacion().getCfcSerie());
			encabezado.setFenNumeroFactura(Format.formatNumeroFactura(repositorio.getRfcCorrActual(), repositorio.getCtrCfcControlFacturacion().getCfcDigitos()));
			//salida por nota de credito, id = 8
			encabezado.setFacFusFacturaUso(facturaUsoDAO.findById(8));
		}
		
		encabezado.setConRimRetencionImpuesto(impuestoDAO.findById(1));
		encabezado.setAudFechaCreacion(new Date());
		encabezado.setAudFechaModificacion(new Date());
		encabezado.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		encabezado.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		//encabezado.setFacDfaDetalleFacturas(encabezadoForm.getFacDfaDetalleFacturas());
		///////////////////////////////////////
		
		//FacFenFacturaEncabezado encabezado = encabezadoDAO.findById(encabezadoForm.getFenOriginal());
		/*FacDfaDetalleFacturaDAO detalleFacturaDAO = new FacDfaDetalleFacturaDAO();
		List lst = detalleFacturaDAO.findByFenOriginal(encabezadoForm.getFenOriginal());*/
		int nxt=0;
		for (Iterator iterator = encabezadoForm.getFacDfaDetalleFacturas().iterator(); iterator.hasNext();) {
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator.next();
			FacDfaDetalleFactura detalleFactura2 = new FacDfaDetalleFactura();
			detalleFactura2.setAudFechaCreacion(new Date());
			detalleFactura2.setAudFechaModificacion(new Date());
			detalleFactura2.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
			detalleFactura2.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
			detalleFactura2.setCnvId(detalleFactura.getCnvId());
			detalleFactura2.setDfaCantidad(detalleFactura.getDfaCantidad());
			detalleFactura2.setDfaDescripcion(detalleFactura.getDfaDescripcion());
			detalleFactura2.setDfaExento(detalleFactura.getDfaExento());
			detalleFactura2.setDfaPrecioTotal(detalleFactura.getDfaPrecioTotal());
			detalleFactura2.setDfaPrecioUnitario(detalleFactura.getDfaPrecioUnitario());
			detalleFactura2.setFacFenFacturaEncabezado(encabezado);
			
			FacDfaDetalleFacturaId detalleFacturaId = new FacDfaDetalleFacturaId();
			FacDfaDetalleFacturaDAO detalleFacturaDAO = new FacDfaDetalleFacturaDAO(getSessionHibernate(request));
			detalleFacturaId.setDfaId(detalleFacturaDAO.nextId() + nxt);
			InvArtArticuloDAO articuloDAO= new InvArtArticuloDAO(getSessionHibernate(request));
			detalleFacturaId.setInvArtArticulo(articuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo()));
			
			detalleFactura2.setId(detalleFacturaId);
			encabezado.getFacDfaDetalleFacturas().add(detalleFactura2);
			nxt ++;
		}
		
		Iterator it2 = encabezado.getFacDfaDetalleFacturas().iterator();
		int[] pos = encabezadoForm.getPosiciones();
		int[] val = encabezadoForm.getValores();
		int[] pos2 = new int[encabezado.getFacDfaDetalleFacturas().size()];
		
		
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
			
	        for(int i = 0; i < pos.length; i++) {
	            int unValor = val[pos[i]];
	            if(unValor < 0){
	            	//request.getSession().setAttribute("ordDet", lst);
	            	mensajes("errors.encabezado.zero", encabezadoForm, request, response);
					return lista(mapping, encabezadoForm, request, response);
	            }
	            int j=0;
	            //Iterator iterator = lst.iterator();
	            //cambiar
	        	FacDfaDetalleFactura detalleFactura = new FacDfaDetalleFactura();
	            while(j < pos[i]){
	            	if(it2.hasNext()){
	            		detalleFactura = (FacDfaDetalleFactura) it2.next();
	            	}
	            	j++;
	            }
	            detalleFactura = (FacDfaDetalleFactura)it2.next();
	            if(unValor > detalleFactura.getDfaCantidad() ){
	            	mensajes("errors.encabezado.cantMax", encabezadoForm, request, response);
					return lista(mapping, form, request, response);
	            }else{
	            	detalleFactura.setDfaCantidad(unValor);
	            }
	            
	        }
	   
			//Iterator iterator = lst.iterator();
	        Iterator iterator = encabezado.getFacDfaDetalleFacturas().iterator();
			int[] del = new int[encabezadoForm.getFacDfaDetalleFacturas().size()];
	        for(int i = 0; i < encabezado.getFacDfaDetalleFacturas().size(); i++) {
	        	if(iterator.hasNext()){
	        		FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura)iterator.next();
	        		if(pos2[i] == -1){
	        			del[i]=i;
	        		}else{
	        			del[i]=-1;
	        		}
	        	}
			}
	        //int l = 0;
	        for(int i = 0; i < encabezado.getFacDfaDetalleFacturas().size(); i++){
	        	if(del[i] > -1){
	        		encabezado.getFacDfaDetalleFacturas().remove(i);
	        		int l=i;
	        		while(l < del.length-1){
	        			del[l] = del[l+1];
	        			l++;
	        		}
	        		//i--;
	        	}
	        }
		}
		
		//////////////////////////////////////
		for (Iterator iterator = encabezado.getFacDfaDetalleFacturas().iterator(); iterator.hasNext();) {
			FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) iterator.next();
			int cant = detalleFactura.getDfaCantidad();
		}
		Transaction tx = encabezadoDAO.getSession().beginTransaction();
		try{
			encabezadoDAO.save(encabezado);
			tx.commit();//solo se realiza un 'save' ya que en el mapeo, se coloco ' cascade="save-update" '
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			encabezadoDAO.getSession().flush();
			encabezadoDAO.getSession().clear();
			//
		}
		
		InvPexProductosExistenciaDAO existenciaDAO = new InvPexProductosExistenciaDAO(getSessionHibernate(request)); 
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		FacFenFacturaEncabezado fenFacturaEncabezado = encabezadoDAO.findById(encabezado.getFenId());
		InvArtArticuloDAO artArticuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		Iterator it = fenFacturaEncabezado.getFacDfaDetalleFacturas().iterator();
		Transaction tx2 = existenciaDAO.getSession().beginTransaction();
		if(facturaUsoDAO.findById(fenFacturaEncabezado.getFacFusFacturaUso().getFusId()).getFusToperacion().equals("c")){
			while(it.hasNext()){
				FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) it.next();
				InvArtArticulo articulo = new InvArtArticulo();
				articulo = artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
				double costoIng = detalleFactura.getDfaPrecioUnitario();
				int existenciaActual = existenciaDAO.findById(articulo.getArtCodigo()).getPexCantidadProducto();
				int cantIngreso;
				if(detalleFactura.getCnvId() == -2){
					cantIngreso = detalleFactura.getDfaCantidad();
				}else{
					InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
					cantIngreso = detalleFactura.getDfaCantidad()*conversion.getCnvFactor().intValue();
				}
				int existenciaNva = existenciaActual + cantIngreso;
				double saldoActual = existenciaDAO.findById(articulo.getArtCodigo()).getPexSaldo();
				double saldoIng = costoIng * cantIngreso;
				double nuevoSaldo = saldoActual + saldoIng;
				double nuevoCosto = nuevoSaldo/(existenciaNva);
				try {
					InvPexProductosExistencia existencia = existenciaDAO.findById(articulo.getArtCodigo());
					existencia.setAudFechaModificacion(new Date());
					existencia.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					existencia.setPexCantidadProducto(existenciaNva);
					existencia.setPexCostoProducto(nuevoCosto);
					existencia.setPexSaldo(nuevoSaldo); 
					existenciaDAO.merge(existencia);
					tx2.commit();
					articulo.setArtPrecioMinimo(nuevoCosto);
					articulo.setArtPrecioSugerido(nuevoCosto *(1+(articulo.getArtPorcentajeUtilidad()/100)));
					tx2.commit();
					InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request)); 
					InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
					eboId.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
					eboId.getInvPexProductosExistencia().setArtCodigo(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
					InvEboExistenciaBodega ebo = eboDAO.findById(eboId); 
					int eboNuevaCant = ebo.getEboCantidadProducto() + cantIngreso; 
					InvCprCapacidadProductoDAO capacidadProductoDAO = new InvCprCapacidadProductoDAO(getSessionHibernate(request));
					InvCprCapacidadProductoId id2 = new InvCprCapacidadProductoId();
					id2.setInvArtArticulo(articulo); 
					id2.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
					InvCprCapacidadProducto capacidadProducto = capacidadProductoDAO.findById(id2);
					if(capacidadProducto.getCprCantidadMaxima() < eboNuevaCant){//esta verificacion se usa
						//para que (por pedido del cliente) si la cantidad maxima se supera con la nueva 
						//compra, se aumente automaticamente la cantidad maxima
							capacidadProducto.setCprCantidadMaxima(eboNuevaCant);
							capacidadProductoDAO.merge(capacidadProducto); 
							tx2.commit(); 
						}
					ebo.setEboCantidadProducto(eboNuevaCant); 
					ebo.setEboSaldo(nuevoCosto*eboNuevaCant); 
					ebo.setAudFechaModificacion(new Date());
					ebo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					eboDAO.merge(ebo); 
					tx2.commit();
					InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));
					InvTmoTipoMovimientoDAO tipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
					InvMovMovimientos movimientos = new InvMovMovimientos();
					movimientos.setMovId(movimientosDAO.nextId());
					movimientos.setInvArtArticulo(articulo);
					movimientos.setInvBodBodegas(ebo.getId().getInvBodBodegas());
					movimientos.setMovUnidades(cantIngreso);
					movimientos.setMovValor(saldoIng);
					movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(1));//indica que el movimiento es de entrada
					movimientos.setAudFechaCreacion(new Date());
					movimientos.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientos.setAudFechaModificacion(new Date());
					movimientos.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientosDAO.save(movimientos);
					tx2.commit();
				} catch (Exception e) {
					tx2.rollback(); 
					e.printStackTrace(); 
				}finally{ 
	 				existenciaDAO.getSession().flush();
	 				existenciaDAO.getSession().clear();
	 				
	 			}
			}
		}else{//si es salida se utiliza toda esta porcion de codigo
		 	CtrRfcRepositorioFacturasDAO rfDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
		 	Transaction transaction = rfDAO.getSession().beginTransaction();
		 	CtrRfcRepositorioFacturas rfcRepositorioFacturas = (CtrRfcRepositorioFacturas) rfDAO.findSerie(encabezadoForm.getAck()/*getFenTipoFactura()*/, persona.getSecSucSucursal().getSucId()).get(0);
		 	rfcRepositorioFacturas.setRfcCorrActual(rfcRepositorioFacturas.getRfcCorrActual() + 1); 
		 	rfDAO.merge(rfcRepositorioFacturas); 
		 	transaction.commit(); 
		 	while(it.hasNext()){
		 		FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) it.next();
				if(artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo())!=null){ 
					InvArtArticulo articulo = artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
		 			double costo = detalleFactura.getDfaPrecioUnitario();
		 			int cant1 = existenciaDAO.findById(articulo.getArtCodigo()).getPexCantidadProducto();
		 			int cant2;
		 			if(detalleFactura.getCnvId() == -2){
		 				cant2 = detalleFactura.getDfaCantidad();
		 			}else{
			 			InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
						cant2 = detalleFactura.getDfaCantidad()*conversion.getCnvFactor().intValue();
		 			}
					double nuevoSaldo = costo * (cant1 - cant2); 
		  			try {
		  				InvPexProductosExistencia existencia = existenciaDAO.findById(articulo.getArtCodigo());
						existencia.setAudFechaModificacion(new Date());
		  				existencia.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		 				existencia.setPexCantidadProducto(cant1 - cant2);
		  				existencia.setPexSaldo(nuevoSaldo);
		  				existenciaDAO.merge(existencia); 
		  				InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
	 					InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
	 					eboId.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
	  					eboId.getInvPexProductosExistencia().setArtCodigo(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
	  					InvEboExistenciaBodega ebo = eboDAO.findById(eboId); int eboNuevaCant =
	  					ebo.getEboCantidadProducto() - cant2;
	  					ebo.setEboCantidadProducto(eboNuevaCant); ebo.setEboSaldo(costo * eboNuevaCant); 
	  					ebo.setAudFechaModificacion(new Date());
	  					ebo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
	  					eboDAO.merge(ebo); 
		  				tx2.commit();
		  				InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));
						InvTmoTipoMovimientoDAO tipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
						InvMovMovimientos movimientos = new InvMovMovimientos();
						movimientos.setMovId(movimientosDAO.nextId());
						movimientos.setInvArtArticulo(articulo);
						movimientos.setInvBodBodegas(ebo.getId().getInvBodBodegas());
						movimientos.setMovUnidades(cant2);
						movimientos.setMovValor(cant2*costo);
						movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(2));//indica que el movimiento es de salida
						movimientos.setAudFechaCreacion(new Date());
						movimientos.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
						movimientos.setAudFechaModificacion(new Date());
						movimientos.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
						movimientosDAO.save(movimientos);
						tx2.commit();
		  			} catch (Exception e) {
		  				tx2.rollback(); 
		 				e.printStackTrace(); 
		 			}finally{ 
		 				existenciaDAO.getSession().flush();
		 				existenciaDAO.getSession().clear();
		 				//
		 			}
				}
		 	}
		}
		//Aumentar el correlativo del repositorio
		if(!repoId.equals(-1)){
			CtrRfcRepositorioFacturasDAO repoDao = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
			CtrRfcRepositorioFacturas repo = repoDao.findById(repoId);
			repo.setRfcCorrActual(repo.getRfcCorrActual() + 1);
			repoDao.merge(repo);
			Transaction txr = repoDao.getSession().beginTransaction();
			txr.commit();
			repoDao.getSession().flush();
			repoDao.getSession().clear();
			
		}
		
		request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
		return cancelar(mapping, form, request, response);
	}
	
	public ActionForward cancelarFact(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		EncabezadoFacturaForm encabezadoForm = (EncabezadoFacturaForm)form;
		FacFenFacturaEncabezadoDAO encabezadoDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
		InvPexProductosExistenciaDAO existenciaDAO = new InvPexProductosExistenciaDAO(getSessionHibernate(request)); 
		InvCnvConversionDAO conversionDAO = new InvCnvConversionDAO(getSessionHibernate(request));
		FacFusFacturaUsoDAO facturaUsoDAO = new FacFusFacturaUsoDAO(getSessionHibernate(request));
		FacFenFacturaEncabezado fenFacturaEncabezado = encabezadoDAO.findById(encabezadoForm.getFenId());
		InvArtArticuloDAO artArticuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));
		Iterator it = fenFacturaEncabezado.getFacDfaDetalleFacturas().iterator();
	 	CtrRfcRepositorioFacturasDAO rfDAO = new CtrRfcRepositorioFacturasDAO(getSessionHibernate(request));
	 	Integer compra = -1;
	 	if(fenFacturaEncabezado.getFacFusFacturaUso().getFusToperacion().equals("c")){
	 		compra = 1;
	 	}else{
	 		compra = 0;
	 	}
	 	//SecIseInicioSesionDAO inicioSesionDAO = new SecIseInicioSesionDAO();
		//SecIseInicioSesion inicioSesion = inicioSesionDAO.findById(encabezadoForm.getUsuarioConectado().getNombreUsuario());
		//SecPerPersonaDAO personaDAO = new SecPerPersonaDAO();
	 	//SecPerPersona persona = personaDAO.findById(inicioSesion.getSecPerPersona().getPerId());
	 	//Transaction tx = personaDAO.getSession().beginTransaction();
	 	//tx.commit(); 
	 	while(it.hasNext()){
	 		FacDfaDetalleFactura detalleFactura = (FacDfaDetalleFactura) it.next();
			if(artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo())!=null){ 
				InvArtArticulo articulo = artArticuloDAO.findById(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
	 			double costo = detalleFactura.getDfaPrecioUnitario();
	 			int cant1 = existenciaDAO.findById(articulo.getArtCodigo()).getPexCantidadProducto();
	 			int cant2;
	 			if(detalleFactura.getCnvId() == -2){
	 				cant2 = detalleFactura.getDfaCantidad();
	 			}else{
		 			InvCnvConversion conversion = conversionDAO.findById(detalleFactura.getCnvId());
					cant2 = detalleFactura.getDfaCantidad()*conversion.getCnvFactor().intValue();
	 			}
				Double nuevoSaldo = 0.0;
				Integer cant = 0;
				//Si era factura de compra, se resta la existencia, si es venta se suma
				if(compra == 1){
					cant = cant1 - cant2;
					nuevoSaldo = costo * cant; 
				}else{
					cant = cant1 + cant2;
					nuevoSaldo = costo * cant;
				}
	  			try {
	  				InvPexProductosExistencia existencia = existenciaDAO.findById(articulo.getArtCodigo());
					existencia.setAudFechaModificacion(new Date());
	  				existencia.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
	 				existencia.setPexCantidadProducto(cant);
	  				existencia.setPexSaldo(nuevoSaldo);
	  				existenciaDAO.merge(existencia); 
	  				InvEboExistenciaBodegaDAO eboDAO = new InvEboExistenciaBodegaDAO(getSessionHibernate(request));
 					InvEboExistenciaBodegaId eboId = new InvEboExistenciaBodegaId();
 					eboId.getInvBodBodegas().setBodId(encabezadoForm.getInvBodBodegas().getBodId());
  					eboId.getInvPexProductosExistencia().setArtCodigo(detalleFactura.getId().getInvArtArticulo().getArtCodigo());
  					InvEboExistenciaBodega ebo = eboDAO.findById(eboId);
  					int eboNuevaCant = 0;
  					if(compra == 1){
  						eboNuevaCant = ebo.getEboCantidadProducto() - cant2;
  					}else{
  						eboNuevaCant = ebo.getEboCantidadProducto() + cant2;
  					}
  					ebo.setEboCantidadProducto(eboNuevaCant); ebo.setEboSaldo(costo * eboNuevaCant); 
  					ebo.setAudFechaModificacion(new Date());
  					ebo.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
  					eboDAO.merge(ebo); 
  					//Transaction txEbo = eboDAO.getSession().beginTransaction();
  					//txEbo.commit();
	  				InvMovMovimientosDAO movimientosDAO = new InvMovMovimientosDAO(getSessionHibernate(request));
					InvTmoTipoMovimientoDAO tipoMovimientoDAO = new InvTmoTipoMovimientoDAO(getSessionHibernate(request));
					InvMovMovimientos movimientos = new InvMovMovimientos();
					movimientos.setMovId(movimientosDAO.nextId());
					movimientos.setInvArtArticulo(articulo);
					movimientos.setInvBodBodegas(ebo.getId().getInvBodBodegas());
					movimientos.setMovUnidades(cant2);
					movimientos.setMovValor(cant2*costo);
					if(compra == 1){
						movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(2));//indica que el movimiento es de salida
					}else{
						movimientos.setInvTmoTipoMovimiento(tipoMovimientoDAO.findById(1));//indica que el movimiento es de entrada
					}
					movimientos.setAudFechaCreacion(new Date());
					movimientos.setAudUsuarioCreacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientos.setAudFechaModificacion(new Date());
					movimientos.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
					movimientosDAO.save(movimientos);
					Transaction txMov = movimientosDAO.getSession().beginTransaction();
					txMov.commit();
	  			} catch (Exception e) {
	 				e.printStackTrace(); 
	 			}finally{ 
	 				existenciaDAO.getSession().flush();
	 				existenciaDAO.getSession().clear();
	 			}
			}
	  }
	 	CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
	 	//EstId 2 es para anulada
	 	fenFacturaEncabezado.setCtrEstEstado(estadoDAO.findById(2));
	 	fenFacturaEncabezado.setAudFechaModificacion(new Date());
	 	fenFacturaEncabezado.setAudUsuarioModificacion(encabezadoForm.getUsuarioConectado().getNombreUsuario());
	 	encabezadoDAO.merge(fenFacturaEncabezado);
	 	Transaction tx = encabezadoDAO.getSession().beginTransaction();
	 	tx.commit();
	 	encabezadoDAO.getSession().flush();
	 	encabezadoDAO.getSession().clear();
	 	enviaAContabilidad(fenFacturaEncabezado, encabezadoForm.getUsuarioConectado().getNombreUsuario(),request);
		request.setAttribute(Constantes.ACCION_KEY, "/encabezadoFactura");
		return cancelar(mapping, encabezadoForm, request, response);
	}
	
	public ActionForward cargarExento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm encabezadoFacturaForm = (EncabezadoFacturaForm)form;
		InvArtArticuloDAO articuloDAO = new InvArtArticuloDAO(getSessionHibernate(request));

		try{
			InvArtArticulo articulo = articuloDAO.findById(encabezadoFacturaForm.getArti());
			// Construimos una lista para el response
			HtmlBuilder htmlBuilder = new HtmlBuilder();
			htmlBuilder.select().name("dfaExento").id("exento").append(" onfocus=\"blur();\"").close();
			if(articulo.getArtExcentoIva()==0){
				htmlBuilder.option().value("0").close().append("No").optionEnd();
				htmlBuilder.option().value("1").close().append("Si").optionEnd();
			}else{
				htmlBuilder.option().value("1").close().append("Si").optionEnd();
				htmlBuilder.option().value("0").close().append("No").optionEnd();
			}
			htmlBuilder.selectEnd();
			String comboExento = htmlBuilder.toString();

			response.getWriter().write(comboExento);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (RuntimeException e) {
			log.error("Error runtime", e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	public ActionForward imprimeFactura(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EncabezadoFacturaForm facturaForm = (EncabezadoFacturaForm)form;
		//imprimirReporte
		ActionForward forward = null;
		try{
			Date fechaActual = new Date();
			//ReportesOrdenForm rOrdenForm = (ReportesOrdenForm) form;
			if(facturaForm.getFenTipoFactura().equals("CO")){//Este if contiene la llamada a la factura original
				response.setHeader("Cache-Control","private");
				response.setHeader("Pragma", "Cache");
				String pathReporte = "";//ruta reporte
				String nombreReporte = "";
				ExportReport exportar = null;
				ReportFile reporte = new ReportFile();
				ServletContext servletContext = getServlet().getServletContext();
				
				nombreReporte = "factura";
				pathReporte = servletContext
				.getRealPath("/reportesOtros/facturacion/factura/factura.jasper");
				String pathSubreporte = servletContext
				.getRealPath("/reportesOtros/facturacion/factura/");
				reporte.addParameter("SUBREPORT_DIR",pathSubreporte + "/");
				reporte.addParameter("fenId",facturaForm.getFenId());
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
				//response.setHeader("content-Disposition", "attachment;filename="+nombreReporte+"-"+rForm.getFechaReporte()+".pdf");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(repCompilado, 0, repCompilado.length);
				outputStream.flush();
				outputStream.close();
			}
			else{//Este es para llamar a la nueva factura de venta a contribuyente
				FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSessionHibernate(request));
				FacCliCliente cliente = clienteDAO.findById(facturaForm.getCodCli());
				
				FacFenFacturaEncabezadoDAO facturaDAO = new FacFenFacturaEncabezadoDAO(getSessionHibernate(request));
				FacFenFacturaEncabezado factura = facturaDAO.findById(facturaForm.getFenId());

				response.setHeader("Cache.Control", "private");
				response.setHeader("Pragma", "Cache");
				String pathReporte = "";
				String nombreReporte = "";
				ExportReport exportar = null;
				ReportFile reporte = new ReportFile();
				ServletContext servletContext = getServlet().getServletContext();
				
				nombreReporte = "facturaC";
				pathReporte = servletContext.getRealPath("/reportesOtros/facturacion/factura/facturaC.jasper");
				reporte.addParameter("fenId", facturaForm.getFenId());
				reporte.addParameter("formaPago", factura.getFenTipoPago());
				reporte.addParameter("totalE", factura.getFenTotalVentasExentas());
				reporte.addParameter("totalV", factura.getFenTotalVenta());
				reporte.addParameter("ivaR", factura.getFenIvaRetenido());
				reporte.addParameter("codCli", cliente.getCliCodigo());
				reporte.addParameter("nombreCli", cliente.getCliNombre());
				reporte.addParameter("municipCli", cliente.getCliMunicipio());
				reporte.addParameter("departCli", cliente.getCliDepartamento());
				reporte.addParameter("direcCli", cliente.getCliDireccion());
				reporte.addParameter("regisCli", cliente.getCliNumRegistro());
				reporte.addParameter("giroCli", cliente.getCliGiro());
				reporte.addParameter("totalFact", factura.getFenTotalVenta());
				reporte.setPathJasper(pathReporte);
				exportar = new ExportReport(reporte);
				
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
				//response.setHeader("content-Disposition", "attachment;filename="+nombreReporte+"-"+rForm.getFechaReporte()+".pdf");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(repCompilado, 0, repCompilado.length);
				outputStream.flush();
				outputStream.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("Se produjo un error al tratar de generar el reporte...", e);
			System.out.println("Se produjo un error al tratar de generar el reporte...\n");
		}
		return null;
	}
	
	
	protected Map getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.encabezado.lista", "lista");
		map.put("cmd.encabezado.guardar", "guardar");
		map.put("cmd.proveedor.salvar", "salvar");
		map.put("cmd.proveedor.editar", "editar");
		map.put("cmd.encabezado.cancelarXfact", "cancelarXfact");
		map.put("cmd.encabezado.cancelar", "cancelar");
		map.put("cmd.proveedor.agregar", "agregar");
		map.put("cmd.encabezado.buscarArt", "cargarListaArticulos");
		map.put("cmd.encabezado.buscarProv", "cargarListaProveedor");
		map.put("cmd.encabezado.buscarClientes", "cargarListaClientes");
		map.put("cmd.detalle.eliminar", "delete");
		map.put("cmd.detalle.agregar", "agregarDetalle");
		map.put("cmd.detalle.forwardToAgregar", "forwardToAgregarDetalle");
		map.put("cmd.encabezado.imprimir", "guardarAndImprimir");
		map.put("cmd.encabezado.vista", "vista");
		map.put("cmd.encabezado.cargarListaCnv","cargarListaCnv");
		map.put("cmd.encabezado.cargarPrecioUnitario", "cargarPrecioUnitario");
		map.put("cmd.encabezado.imprimirGuardado","imprimirGuardado");
		map.put("cmd.encabezado.notaCredito", "notaCredito");
		map.put("cmd.encabezado.notaDebito", "notaDebito");
		map.put("cmd.encabezado.imprimirNota", "imprimirNota");
		map.put("cmd.encabezado.cancelarFact", "cancelarFact");
		map.put("cmd.encabezado.cargarListaContribuyentes", "cargarListaContribuyentes");
		map.put("cmd.encabezado.cargarExento", "cargarExento");
		map.put("cmd.encabezado.imprimeFactura", "imprimeFactura");
		return map;
	}
}