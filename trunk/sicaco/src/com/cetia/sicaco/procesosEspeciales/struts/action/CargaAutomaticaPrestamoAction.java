/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.procesosEspeciales.struts.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.hibernate.Transaction;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeImpl;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamo;
import com.cetia.sicaco.hibernate.CtaLprLineaPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo;
import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaNotNotasDAO;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTinTasaInteres;
import com.cetia.sicaco.hibernate.CtaTinTasaInteresDAO;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamo;
import com.cetia.sicaco.hibernate.CtaTprTipoPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccionDAO;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.CtrEstEstadoDAO;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.procesosEspeciales.struts.form.CargaAutomaticaPrestamoForm;
import com.cetia.sicaco.procesosEspeciales.struts.form.FallidosPreExcel;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.cetia.sicaco.struts.PartidaAutomatica;

/** 
 * MyEclipse Struts
 * Creation date: 11-27-2008
 * 
 * XDoclet definition:
 * @struts.action path="/cargaAutomaticaPrestamo" name="cargaAutomaticaPrestamoForm" input="redirectInvalidData" parameter="accion" scope="request" validate="true"
 * @struts.action-forward name="lista" path="pagina-lista.procesosEspeciales.cargaAutomaticaPrestamo"
 */
public class CargaAutomaticaPrestamoAction extends DMLAction {
	
	public String TABLA_ID = "CtaAscAsociado";
	public String WICH_ONE = "";

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CargaAutomaticaPrestamoForm cAPreForm = (CargaAutomaticaPrestamoForm)form;
		
		CtaLprLineaPrestamoDAO lprDao = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		List lineas = lprDao.findAll();
		request.setAttribute("lineas", lineas);
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		CtaLprLineaPrestamo lpr = (CtaLprLineaPrestamo)lineas.get(0);
		List tprList = tipoPrestamoDAO.findByLinea(lpr.getLprId());
		request.setAttribute("lstTpr", tprList);
		
		//Si hay fallidos mostrar
		if(cAPreForm.getFallidos() != null && cAPreForm.getFallidos().size()>0){
			TableFacade tableFacade = new TableFacadeImpl(TABLA_ID, request);
			tableFacade.setItems(cAPreForm.getFallidos());
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
		}
		
		//----- Variables de configuracion
		request.setAttribute("form", cAPreForm);
		request.setAttribute("edit", 0);
		request.setAttribute(Constantes.ACCION_KEY, "/cargaAutomaticaPrestamo");
		return mapping.findForward("lista");
	}
	
	//---- Funcion que genera el html de la tabla del jmesa
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("codigo","error");
		Table table = tableFacade.getTable();
		//---- Titulo de la tabla
		table.setCaptionKey("tbl.capre.caption");
		
		Row row = table.getRow();
		
		Column nombreColumna = row.getColumn("codigo");
		nombreColumna.setTitleKey("tbl.capre.codigo");
		
		nombreColumna = row.getColumn("error");
		nombreColumna.setTitleKey("tbl.capre.error");
		
		return tableFacade.render();
	}
	
	//---- Funcion que genera los exports, el formato que tendran
	 private void export(final TableFacade tableFacade) {
		 tableFacade.setColumnProperties("codigo","error");
			Table table = tableFacade.getTable();
			//---- Titulo de la tabla
			table.setCaptionKey("tbl.capre.caption");
			
			Row row = table.getRow();
			
			Column nombreColumna = row.getColumn("codigo");
			nombreColumna.setTitleKey("tbl.capre.codigo");
			
			nombreColumna = row.getColumn("error");
			nombreColumna.setTitleKey("tbl.capre.error");
			
		tableFacade.render();
	}

	public ActionForward redirectInvalidData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = (ActionErrors)request.getSession().getAttribute(Constantes.ERRORS);
		saveMessages(request, errors);
		return lista(mapping, form, request, response);
	}

	public void mensajes(String msg, HttpServletRequest request){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
		saveMessages(request, errors);
	}

	public ActionForward cargar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		CargaAutomaticaPrestamoForm cAPreForm = (CargaAutomaticaPrestamoForm)form;
		//CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO();
//		List lst = asociadoDAO.findAll(cAPreForm.getUsuarioConectado().getMax());
		FormFile file = cAPreForm.getArchivo();
		int noEncontrados=0;
		cAPreForm.setFallidos(leerExcel(file,request,noEncontrados, cAPreForm));
		log.info("Numero de Codigos no encontrados:" + noEncontrados);
		//----- Variables de configuracion
		cAPreForm.setArchivo(null);
		request.setAttribute("form", cAPreForm);
		request.setAttribute(Constantes.ACCION_KEY, "/cargaAutomaticaPrestamo");
		request.setAttribute("edit", 1);
		return lista(mapping, form, request, response);
	}
	
	public ArrayList<FallidosPreExcel> leerExcel (FormFile file,HttpServletRequest request, int noEncontrados, CargaAutomaticaPrestamoForm cAPreForm)
	{
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociadoDAO casDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		Transaction tx = asociadoDAO.getSession().beginTransaction();
		CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		CtaTprTipoPrestamo tpr = tipoPrestamoDAO.findById(cAPreForm.getTprId());
		CtaTinTasaInteresDAO tasaInteresDAO = new CtaTinTasaInteresDAO(getSessionHibernate(request));
		CtaTinTasaInteres tasaCero = tasaInteresDAO.findById(1);
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		CtrEstEstado estado = estadoDAO.findById(13);
		CtaNotNotasDAO notasDAO = new CtaNotNotasDAO(getSessionHibernate(request));
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		Double pagosMes = parametrosDAO.findById("DIVISOR_CUOTAS_MES").getParValorNumber();
		InputStream inputStream = null;
		
		ArrayList<FallidosPreExcel> fallidos = new ArrayList<FallidosPreExcel>();
		try
		{
			inputStream = file.getInputStream();
		}
		catch (IOException e)
		{
			mensajes("error.planilla.fileNotFound", request);
			log.error("archivo no fue encontrado en la direccion proporcionada");
			e.printStackTrace ();
		}

		POIFSFileSystem fileSystem = null;

		try
		{
			fileSystem = new POIFSFileSystem (inputStream);

			HSSFWorkbook      workBook = new HSSFWorkbook (fileSystem);
			HSSFSheet         sheet    = workBook.getSheetAt (0);
			Iterator<HSSFRow> rows     = sheet.rowIterator();
			
			while (rows.hasNext ())
			{
				HSSFRow row = rows.next ();

				// once get a row its time to iterate through cells.
				Iterator<HSSFCell> cells = row.cellIterator();
				while (cells.hasNext ())
				{
					HSSFCell cell = cells.next ();
					if(cell.getCellNum() < 2){
						if(cell.getCellType () == HSSFCell.CELL_TYPE_STRING){
							HSSFRichTextString richTextString = cell.getRichStringCellValue ();
							if(richTextString != null && !richTextString.toString().trim().equals("")){
								CtaAscAsociado asociado = obtenerAsociado(cAPreForm.getEmpOAsoc(),richTextString.getString(),request);
								if(asociado != null && asociado.getAscId()!=null){
									//CtaAscAsociado asociado = (CtaAscAsociado) asociadoDAO.findByAscCodigoAsociado(richTextString.getString()).get(0);
									if(cells.hasNext()){
										HSSFCell cellMontoSolicitado = cells.next();
										if(cellMontoSolicitado.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
											Double montoS = cellMontoSolicitado.getNumericCellValue();
											if(cells.hasNext()){
												HSSFCell cellCuotas = cells.next();
												if(cellCuotas.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
													Double cuotas = cellCuotas.getNumericCellValue();
													Double multiplicador = 1.0;
													Double numIniTarjeta = -1.0;
													if(cells.hasNext()){
														HSSFCell cellMultiplicador = cells.next();
														if(cellMultiplicador.getCellType () == HSSFCell.CELL_TYPE_NUMERIC){
															//multiplicador = cellMultiplicador.getNumericCellValue();
															if(cellMultiplicador.getNumericCellValue() >= 1.0){
																multiplicador = cellMultiplicador.getNumericCellValue();
																if(cells.hasNext()){
																	HSSFCell cellIniTarjeta = cells.next();
																	if(cellIniTarjeta.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
																		numIniTarjeta = cellIniTarjeta.getNumericCellValue();
																	}
																}
															}
														}
													}
													Double montoSolicitado = (montoS*multiplicador.intValue());
													CtaPrePrestamo prestamo = new CtaPrePrestamo();
													prestamo.setCtaTinTasaInteres(null);
													prestamo.setCtaTprTipoPrestamo(tpr);
													/*prestamo.setPreCuota(
															Calculadora.calcularCuotaDouble(
																	montoSolicitado,
																	(double) tpr.getCtaTinTasaInteres().getTinTasa(), 
																	tpr.getCtaPlmPlanMeses().getPlmDuracion()
															)
													);*/
													prestamo.setPrePendMov(montoSolicitado*(prestamo.getCtaTprTipoPrestamo().getCtaTinTasaInteres().getTinTasa()/100));
													prestamo.setPreCuota(/*((montoSolicitado + prestamo.getPrePendMov())/cAPreForm.getPagos())*pagosMes*/cuotas);
													prestamo.setPreFechaSolicitud(new Date());
													prestamo.setPreId(prestamoDAO.generarId("C"));
													prestamo.setPreInteresAcumulado(0.0);
													prestamo.setPreLiquidoARecibir(0.0);
													prestamo.setPreMontoSolicitado(montoSolicitado);
													prestamo.setPreReferencia(prestamo.getPreId());
													prestamo.setPreSaldoActualT(prestamo.getPreMontoSolicitado());
													prestamo.setPreMoraMov(0.0);
													prestamo.setCtaSegSeguros(null);
													prestamo.setPreCredito("A");
													prestamo.setCtaCbaCuentaBancaria(null);
													prestamoDAO.save(prestamo);
													/*tx.commit();
													prestamoDAO.getSession().flush();
													prestamoDAO.getSession().clear();
													*/
													CtaCasCuentaAsociado cas = new CtaCasCuentaAsociado();
													cas.setCasFechaApertura(new Date());
													cas.setCasPrincipal("N");
													cas.setCasValorApertura(0.0);
													cas.setCtaAscAsociado(asociado);
													cas.setCtaPrePrestamo(prestamo);
													cas.setCtaCahCuentaAhorro(null);
													cas.setCtaCbaCuentaBancaria(null);
													cas.setCtaSegSeguros(null);
													cas.setCtrEstEstado(estado);
													cas.setCtaPxtPersonaExterna(null);
													casDAO.save(cas);
													
													//crear la transaccion 46, Cargo al asociado por prestamo automatico
													CtaTxaTransaccionxcuentaAsociadoDAO txaDao = new CtaTxaTransaccionxcuentaAsociadoDAO(getSessionHibernate(request));
													CtaTxaTransaccionxcuentaAsociado txa = new CtaTxaTransaccionxcuentaAsociado();
													txa.setAudFechaCreacion(new Date());
													txa.setAudFechaModificacion(new Date());
													txa.setAudUsuarioCreacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
													txa.setAudUsuarioModificacion(txa.getAudUsuarioCreacion());
													txa.setCtaCasCuentaAsociado(cas);
													txa.setCtaNotNotas(null);
													
													CtaTtrTipoTransaccionDAO ttrDao = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
													CtaTtrTipoTransaccion ttr = ttrDao.findById(46);
													txa.setCtaTtrTipoTransaccion(ttr);
													txa.setTxaFecha(new Date());
													txa.setTxaMonto(prestamo.getPreMontoSolicitado());
													txa.setTxaNota(ttr.getTtrNombre());
													txaDao.save(txa);
													
													tx.commit();
													txaDao.getSession().flush();
													txaDao.getSession().clear();
													
													//Creamos el movimiento de apertura de prestamo
													CtaMxpMovimientoPrestamoDAO mxpDAO = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
													CtaMxpMovimientoPrestamo mxp = new CtaMxpMovimientoPrestamo();
													
													mxp.setCtaPrePrestamo(prestamo);
													mxp.setCtaTxaTransaccionxcuentaAsociado(txa);
													mxp.setMxpFecha(new Date());
													mxp.setMxpInteresAcumulado(0.00);
													mxp.setMxpInteresPendiente(0.00);
													mxp.setMxpMora(0.00);
													mxp.setMxpSaldo(prestamo.getPreMontoSolicitado());
													mxp.setMxpSaldoActual(0.00);
													mxp.setAudFechaCreacion(new Date());
													mxp.setAudFechaModificacion(new Date());
													mxp.setAudUsuarioCreacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
													mxp.setAudUsuarioModificacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
													Transaction txMxp = mxpDAO.getSession().beginTransaction();
													mxpDAO.save(mxp);
													txMxp.commit();
													
													//notas
													CtaNotNotas nota1 = new CtaNotNotas();
													CtaNotNotas nota2 = new CtaNotNotas();
													seteaNota(nota1, cas, cAPreForm, 1, 0.0,request);
													seteaNota(nota2, cas, cAPreForm, 2, multiplicador,request);
													if(numIniTarjeta > 0){
														CtaNotNotas nota3 = new CtaNotNotas();
														seteaNota(nota3, cas, cAPreForm, 3, numIniTarjeta,request);
													}
													enviarAContabilidad(txa,request);
												}else{
													fallidos.add(new FallidosPreExcel(richTextString.getString(),"(columna 3)la cuota ingresada no es un numero"));
													//log.error("Asociado " + asociado.getAscCodigoAsociado() + ", la cuota ingresada no es un numero");
													break;
												}
											}else{
												fallidos.add(new FallidosPreExcel(richTextString.getString(),"(columna 3) no se encontro la celda de las cuotas"));
												//log.error("Asociado " + asociado.getAscCodigoAsociado() + ", no existe la celda de las cuotas");
												break;
											}
										}else{
											fallidos.add(new FallidosPreExcel(richTextString.getString(),"(columna 2) el monto ingresado no es un numero"));
											//log.error("Asociado " + asociado.getAscCodigoAsociado() + ", el monto ingresado no es un numero");
											break;
										}
									}else{
										fallidos.add(new FallidosPreExcel(richTextString.getString(),"(columna 2) no se encontro la celda del monto solicitado"));
										//log.error("Asociado " + asociado.getAscCodigoAsociado() + ", no existe la celda del monto solicitado");
										break;
									}
								}else{
									fallidos.add(new FallidosPreExcel(richTextString.getString(),"(columna 1) no se encontro el codigo ingresado"));
									//noEncontrados++;
									//log.error("El codigo de la celda " + row.getRowNum() + ", " + cell.getCellNum() + " no fue encontrado (vacio)");
									//mensajes("error.cCodEmpleado.asociadoNoEncontrado", request);
									break;
								}
							}else{
								fallidos.add(new FallidosPreExcel(richTextString.getString(),"(columna 1) campo vacio"));
								//noEncontrados++;
								//log.error("El codigo de la celda " + row.getRowNum() + ", " + cell.getCellNum() + " no fue encontrado (vacio)");
							}
						}else{
							fallidos.add(new FallidosPreExcel("fallo","no se encontro la columna 1"));
							//noEncontrados++;
							//log.error("El codigo de la celda " + row.getRowNum() + ", " + cell.getCellNum() + " no es de tipo String");
						}
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
		return fallidos;
	}

	private void enviarAContabilidad(CtaTxaTransaccionxcuentaAsociado txa,HttpServletRequest request) {
		Integer tc = 0;
		Integer clasificacion = -1;
		Integer relacionInteres = 0;
		tc = 3;
		
		CtaCasCuentaAsociadoDAO casDao = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociado cas = casDao.findById(txa.getCtaCasCuentaAsociado().getCasCuenta());
		
		clasificacion = cas.getCtaPrePrestamo().getCtaTprTipoPrestamo().getCtaLprLineaPrestamo().getLprId();
		String parametros ="1;";
		parametros += tc.toString() + ";";
		parametros += clasificacion.toString() + ";";
		parametros += txa.getCtaTtrTipoTransaccion().getTtrId().toString() + ";";
		parametros += relacionInteres.toString();
		
		Double monto = txa.getTxaMonto();
		if(monto > 0){
			PartidaAutomatica partidaAutomatica =  new PartidaAutomatica();
			partidaAutomatica.crearPartidaAutomatica(
					parametros+";-1",
					txa.getTxaMonto(),
					txa.getAudUsuarioCreacion(),1,null,null,null,request);
		}
	}

	private CtaAscAsociado obtenerAsociado(String empOAsoc, String codigo,HttpServletRequest request) {
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		if(empOAsoc.equals("e")){
			List asociados = asociadoDAO.findByAscCodigo(codigo);
			if(asociados.size() > 0){
				return (CtaAscAsociado) asociados.get(0);
			}
		}else{
			List asociados = asociadoDAO.findByAscCodigoAsociado(codigo);
			if(asociados.size() > 0){
				return (CtaAscAsociado) asociados.get(0);
			}
		}
		return null;
	}

	private void seteaNota(CtaNotNotas notas, CtaCasCuentaAsociado cas,
			CargaAutomaticaPrestamoForm cAPreForm,int campo, Double cant,HttpServletRequest request) {
		CtaNotNotasDAO notasDAO = new CtaNotNotasDAO(getSessionHibernate(request));
		Transaction tx = notasDAO.getSession().beginTransaction();
		notas.setCasCuenta(cas.getCasCuenta());
		
		if(campo == 1){
			notas.setNotCampo("Razon del Prestamo");
			notas.setNotNota(cAPreForm.getRazon());
		}
		if(campo == 2){
			notas.setNotCampo("Cantidad de prestamos");
			notas.setNotNota( Integer.toString(cant.intValue()));
		}
		if(campo == 3){
			notas.setNotCampo("Correlativo inicial");
			notas.setNotNota(Integer.toString(cant.intValue()));
		}
		
		notas.setNotFecha(new Date());
		notas.setNotId(notasDAO.nextId());
		notasDAO.save(notas);
		tx.commit();
		notasDAO.getSession().flush();
		notasDAO.getSession().clear();
	}

	public ActionForward sinxls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CargaAutomaticaPrestamoForm cAPreForm = (CargaAutomaticaPrestamoForm)form;
		
		CtaLprLineaPrestamoDAO lprDao = new CtaLprLineaPrestamoDAO(getSessionHibernate(request));
		List lineas = lprDao.findAll();
		request.setAttribute("lineas", lineas);
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		CtaLprLineaPrestamo lpr = (CtaLprLineaPrestamo)lineas.get(0);
		List tprList = null;
		if(cAPreForm.getLprId()!=null && cAPreForm.getLprId()!=lpr.getLprId()){
			tprList = tipoPrestamoDAO.findByLinea(cAPreForm.getLprId());
		}else{
			tprList = tipoPrestamoDAO.findByLinea(lpr.getLprId());
		}
		 
		request.setAttribute("lstTpr", tprList);
		
		//----- Variables de configuracion
		request.setAttribute("form", cAPreForm);
		request.setAttribute("edit", 0);
		request.setAttribute(Constantes.ACCION_KEY, "/cargaAutomaticaPrestamo");
		return mapping.findForward("sinxls");
	}
	
	public ActionForward cargar2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		CargaAutomaticaPrestamoForm cAPreForm = (CargaAutomaticaPrestamoForm)form;
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		if(!validado(request,cAPreForm,2)){
			return sinxls(mapping, form, request, response);
		}
		
		if(cAPreForm.getCantidad() < 1){
			cAPreForm.setCantidad(1);
		}
		
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		CtaTprTipoPrestamo tpr = tipoPrestamoDAO.findById(cAPreForm.getTprId());
		CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
		Transaction tx = prestamoDAO.getSession().beginTransaction();
		
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		CtrEstEstado estado = estadoDAO.findById(13);
		
		CtaAscAsociado asociado = asociadoDAO.findById(cAPreForm.getCodigo());
		
		Double montoSolicitado = (cAPreForm.getMonto()*cAPreForm.getCantidad());
		CtaPrePrestamo prestamo = new CtaPrePrestamo();
		prestamo.setCtaTinTasaInteres(null);
		prestamo.setCtaTprTipoPrestamo(tpr);
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		Double pagosMes = parametrosDAO.findById("DIVISOR_CUOTAS_MES").getParValorNumber();
		
		DecimalFormat df = new DecimalFormat("0.00");
		prestamo.setPrePendMov(new Double(df.format(montoSolicitado*(prestamo.getCtaTprTipoPrestamo().getCtaTinTasaInteres().getTinTasa()/100))));
		prestamo.setPreCuota(((montoSolicitado + prestamo.getPrePendMov())/cAPreForm.getPagos())*pagosMes);
		
		prestamo.setPreFechaSolicitud(new Date());
		prestamo.setPreId(prestamoDAO.generarId("C"));
		prestamo.setPreInteresAcumulado(0.0);
		prestamo.setPreLiquidoARecibir(0.0);
		prestamo.setPreMontoSolicitado(montoSolicitado);
		prestamo.setPreReferencia(prestamo.getPreId());
		prestamo.setPreSaldoActualT(prestamo.getPreMontoSolicitado());
		prestamo.setPreMoraMov(0.0);
		prestamo.setCtaSegSeguros(null);
		prestamo.setPreCredito("A");
		prestamo.setCtaCbaCuentaBancaria(null);
		prestamoDAO.save(prestamo);

		CtaCasCuentaAsociadoDAO casDao = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociado cas = new CtaCasCuentaAsociado();
		cas.setCasFechaApertura(new Date());
		cas.setCasPrincipal("N");
		cas.setCasValorApertura(0.0);
		cas.setCtaAscAsociado(asociado);
		cas.setCtaPrePrestamo(prestamo);
		cas.setCtaCahCuentaAhorro(null);
		cas.setCtaCbaCuentaBancaria(null);
		cas.setCtaSegSeguros(null);
		cas.setCtrEstEstado(estado);
		cas.setCtaPxtPersonaExterna(null);
		casDao.save(cas);
		
		//crear la transaccion 46, Cargo al asociado por prestamo automatico
		CtaTxaTransaccionxcuentaAsociadoDAO txaDao = new CtaTxaTransaccionxcuentaAsociadoDAO(getSessionHibernate(request));
		CtaTxaTransaccionxcuentaAsociado txa = new CtaTxaTransaccionxcuentaAsociado();
		txa.setAudFechaCreacion(new Date());
		txa.setAudFechaModificacion(new Date());
		txa.setAudUsuarioCreacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
		txa.setAudUsuarioModificacion(txa.getAudUsuarioCreacion());
		txa.setCtaCasCuentaAsociado(cas);
		txa.setCtaNotNotas(null);
		
		CtaTtrTipoTransaccionDAO ttrDao = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
		CtaTtrTipoTransaccion ttr = ttrDao.findById(46);
		txa.setCtaTtrTipoTransaccion(ttr);
		txa.setTxaFecha(new Date());
		txa.setTxaMonto(prestamo.getPreMontoSolicitado());
		txa.setTxaNota(ttr.getTtrNombre());
		txaDao.save(txa);
		
		tx.commit();
		txaDao.getSession().flush();
		txaDao.getSession().clear();
		
		//Creamos el movimiento de apertura de prestamo
		CtaMxpMovimientoPrestamoDAO mxpDAO = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
		CtaMxpMovimientoPrestamo mxp = new CtaMxpMovimientoPrestamo();
		
		mxp.setCtaPrePrestamo(prestamo);
		mxp.setCtaTxaTransaccionxcuentaAsociado(txa);
		mxp.setMxpFecha(new Date());
		mxp.setMxpInteresAcumulado(0.00);
		mxp.setMxpInteresPendiente(0.00);
		mxp.setMxpMora(0.00);
		mxp.setMxpSaldo(prestamo.getPreMontoSolicitado());
		mxp.setMxpSaldoActual(0.00);
		mxp.setAudFechaCreacion(new Date());
		mxp.setAudFechaModificacion(new Date());
		mxp.setAudUsuarioCreacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
		mxp.setAudUsuarioModificacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
		Transaction txMxp = mxpDAO.getSession().beginTransaction();
		mxpDAO.save(mxp);
		txMxp.commit();
		
		//notas
		CtaNotNotas nota1 = new CtaNotNotas();
		CtaNotNotas nota2 = new CtaNotNotas();
		seteaNota(nota1, cas, cAPreForm, 1, 0.0,request);
		seteaNota(nota2, cas, cAPreForm, 2, (double) cAPreForm.getCantidad(),request);
		
		if(cAPreForm.getCorrelativo() > 0){
			CtaNotNotas nota3 = new CtaNotNotas();
			seteaNota(nota3, cas, cAPreForm, 3, (double) cAPreForm.getCorrelativo(),request);
		}
		enviarAContabilidad(txa,request);
		
//		List lst = asociadoDAO.findAll(cAPreForm.getUsuarioConectado().getMax());
		//----- Variables de configuracion
		cAPreForm.setArchivo(null);
		request.setAttribute("form", cAPreForm);
		request.setAttribute(Constantes.ACCION_KEY, "/cargaAutomaticaPrestamo");
		request.setAttribute("edit", 1);
		mensajes("msg.cAPre.creacionExitosa", request);
		CargaAutomaticaPrestamoForm form2 = new CargaAutomaticaPrestamoForm();
		return sinxls(mapping, form2, request, response);
	}
	
	private boolean validado(HttpServletRequest request,
			CargaAutomaticaPrestamoForm cAPreForm, int carga) {
		ActionErrors errors = new ActionErrors();
		boolean x = true;
		if(cAPreForm.getMonto() <= 0){
			mensajes("error.cAPre.montoCero", request, errors);
			x = false;
		}
		/*if(cAPreForm.getCantidad()<1){
			mensajes("error.cAPre.cantidadCero", request, errors);
			x = false;
		}*/
		if(carga == 2){
			if(cAPreForm.getCodigo() == null || cAPreForm.getCodigo().trim().equals("")){
				mensajes("error.cAPre.codigo", request, errors);
			}
		}
		if(cAPreForm.getRazon() == null || cAPreForm.getRazon().trim().equals("")){
			mensajes("error.cAPre.razon", request, errors);
		}
		return x;
	}

	public ActionForward cargarListaAsociados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CargaAutomaticaPrestamoForm cAPreForm = (CargaAutomaticaPrestamoForm)form;
		List<CtaAscAsociado> listaAsociados = null;
		
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado asociado = new CtaAscAsociado();
		if(cAPreForm.getAscEmp() == 2){
			asociado.setAscCodigo(cAPreForm.getAscCodigoAsociado());
		}else{
			asociado.setAscCodigoAsociado(cAPreForm.getAscCodigoAsociado());
		}
		//asociado.setAscCodigoAsociado(ordenCompraForm.getAscCodigo3());
		if(cAPreForm.getAscNombre() != null && !cAPreForm.getAscNombre().trim().equals("")){
			if(cAPreForm.getAscNombre().trim().indexOf(",") == -1){
				if(cAPreForm.getAscNombre().trim().indexOf(" ") == -1){
					asociado.getSecPerPersona().setPerPrimerNombre(cAPreForm.getAscNombre().trim());
				}else{
					StringTokenizer tokenizer = new StringTokenizer(cAPreForm.getAscNombre().trim());
					asociado.getSecPerPersona().setPerPrimerNombre(tokenizer.nextToken().trim());
					asociado.getSecPerPersona().setPerPrimerApellido(tokenizer.nextToken().trim());
				}
			}else{
				if(cAPreForm.getAscNombre().trim().startsWith(",")){
					asociado.getSecPerPersona().setPerPrimerApellido("");
					asociado.getSecPerPersona().setPerPrimerNombre(cAPreForm.getAscNombre().substring(1).trim());
				}else{
					StringTokenizer tokenizer = new StringTokenizer(cAPreForm.getAscNombre().trim(),",");
					asociado.getSecPerPersona().setPerPrimerApellido(tokenizer.nextToken().trim());
					asociado.getSecPerPersona().setPerPrimerNombre(tokenizer.nextToken().trim());
				}
			}
		}
		
		SecPerPersona persona = new SecPerPersona();
		persona.setPerPrimerNombre(cAPreForm.getAscNombre());
		
		try {
			listaAsociados = asociadoDAO.findByNameUser(asociado,10);
			
			Boolean nulo = false;
			if(listaAsociados.size() < 1){
				nulo = true;
			}
			String listaResponse = "";
			listaResponse = construirListaAsociados(listaAsociados, nulo,request);
			
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
	
	private String construirListaAsociados(List<CtaAscAsociado> listaAsociados, Boolean nulo,HttpServletRequest request) {
		String lista = "<table id=\"resultadoAsc\">";
		lista+="<tr><td colspan=\"2\"><span style=\"font-size: 10px;font-style: italic;\">Asociados:</span></td></tr>";
		if(nulo == true){
			lista+= "<tr><td colspan=\"2\"><span style=\"font-size: 10px;color: red;font-style: italic;\">"
			+ "No se encontro ningun asociado en el sistema para esta b&uacute;squeda</span></td></tr>";
		}else{
			int max2 = 0;
			for (Iterator iterator = listaAsociados.iterator(); iterator.hasNext();) {
				//SecAscAsociado asociado = (SecAscAsociado) iterator.next();
				CtaAscAsociado asociado = (CtaAscAsociado)iterator.next();
				CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
				lista += "<tr>";
				lista += "<td><input onclick=\"JavaScript:saveSeleccionA(this.value);\" type=\"radio\" name=\"_miseleccion\" value=\""
						+ asociado.getAscCodigoAsociado()
						+ ";"
						+ asociado.getSecPerPersona().getPerPrimerNombre()
						+ " "
						+ asociado.getSecPerPersona().getPerPrimerApellido()
						+ ";"
						+ asociado.getAscId()
						+ "\"/></td>";
					lista += "<td><span style=\"font-size: 10px;color: #6E6E6E;font-style: italic;\">"
						+ asociado.getSecPerPersona().getPerPrimerNombre()
						+ " "
						+ asociado.getSecPerPersona().getPerPrimerApellido()
						+ "</span></td>";
					lista += "</tr>";
			}
		}
		lista += "</table>";
		return lista;
	}
	
	public void mensajes(String msg, HttpServletRequest request, ActionErrors errors){
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
		saveMessages(request, errors);
	}
	
	public ActionForward cargarTodos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		CargaAutomaticaPrestamoForm cAPreForm = (CargaAutomaticaPrestamoForm)form;
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		if(!validado(request,cAPreForm,0)){
			return sinxls(mapping, form, request, response);
		}
		CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		CtaTprTipoPrestamo tpr = tipoPrestamoDAO.findById(cAPreForm.getTprId());
		CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
		
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
		CtrEstEstado estado = estadoDAO.findById(13);
		
		cAPreForm.setCantidad(1);
		
		List asociados = asociadoDAO.findActivosConAportacion2();
		
		Double montoSolicitado = (cAPreForm.getMonto()*cAPreForm.getCantidad());
		for (Iterator iterator = asociados.iterator(); iterator.hasNext();) {
			String ascId = (String) iterator.next();
			CtaAscAsociado asociado = asociadoDAO.findById(ascId);
			
			Transaction tx = prestamoDAO.getSession().beginTransaction();
			
			CtaPrePrestamo prestamo = new CtaPrePrestamo();
			prestamo.setCtaTinTasaInteres(null);
			prestamo.setCtaTprTipoPrestamo(tpr);
			CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
			Double pagosMes = parametrosDAO.findById("DIVISOR_CUOTAS_MES").getParValorNumber();
			prestamo.setPrePendMov(montoSolicitado*(prestamo.getCtaTprTipoPrestamo().getCtaTinTasaInteres().getTinTasa()/100));
			prestamo.setPreCuota(((montoSolicitado + prestamo.getPrePendMov())/cAPreForm.getPagos())*pagosMes);
			prestamo.setPreFechaSolicitud(new Date());
			prestamo.setPreId(prestamoDAO.generarId("C"));
			prestamo.setPreInteresAcumulado(0.0);
			prestamo.setPreLiquidoARecibir(0.0);
			prestamo.setPreMontoSolicitado(montoSolicitado);
			prestamo.setPreReferencia(prestamo.getPreId());
			prestamo.setPreSaldoActualT(prestamo.getPreMontoSolicitado());
			prestamo.setPreMoraMov(0.0);
			prestamo.setCtaSegSeguros(null);
			prestamo.setPreCredito("A");
			prestamo.setCtaCbaCuentaBancaria(null);
			prestamoDAO.save(prestamo);

			CtaCasCuentaAsociadoDAO casDao = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
			CtaCasCuentaAsociado cas = new CtaCasCuentaAsociado();
			cas.setCasFechaApertura(new Date());
			cas.setCasPrincipal("N");
			cas.setCasValorApertura(0.0);
			cas.setCtaAscAsociado(asociado);
			cas.setCtaPrePrestamo(prestamo);
			cas.setCtaCahCuentaAhorro(null);
			cas.setCtaCbaCuentaBancaria(null);
			cas.setCtaSegSeguros(null);
			cas.setCtrEstEstado(estado);
			cas.setCtaPxtPersonaExterna(null);
			casDao.save(cas);
			
			//crear la transaccion 46, cargo al asociado por prestamo automatico
			CtaTxaTransaccionxcuentaAsociadoDAO txaDao = new CtaTxaTransaccionxcuentaAsociadoDAO(getSessionHibernate(request));
			CtaTxaTransaccionxcuentaAsociado txa = new CtaTxaTransaccionxcuentaAsociado();
			txa.setAudFechaCreacion(new Date());
			txa.setAudFechaModificacion(new Date());
			txa.setAudUsuarioCreacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
			txa.setAudUsuarioModificacion(txa.getAudUsuarioCreacion());
			txa.setCtaCasCuentaAsociado(cas);
			txa.setCtaNotNotas(null);
			
			CtaTtrTipoTransaccionDAO ttrDao = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
			CtaTtrTipoTransaccion ttr = ttrDao.findById(46);
			txa.setCtaTtrTipoTransaccion(ttr);
			txa.setTxaFecha(new Date());
			txa.setTxaMonto(prestamo.getPreMontoSolicitado());
			txa.setTxaNota(ttr.getTtrNombre());
			txaDao.save(txa);
			
			tx.commit();
			txaDao.getSession().flush();
			txaDao.getSession().clear();
			
			
			//Creamos el movimiento de apertura de prestamo
			CtaMxpMovimientoPrestamoDAO mxpDAO = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
			CtaMxpMovimientoPrestamo mxp = new CtaMxpMovimientoPrestamo();
			
			mxp.setCtaPrePrestamo(prestamo);
			mxp.setCtaTxaTransaccionxcuentaAsociado(txa);
			mxp.setMxpFecha(new Date());
			mxp.setMxpInteresAcumulado(0.00);
			mxp.setMxpInteresPendiente(0.00);
			mxp.setMxpMora(0.00);
			mxp.setMxpSaldo(prestamo.getPreMontoSolicitado());
			mxp.setMxpSaldoActual(0.00);
			mxp.setAudFechaCreacion(new Date());
			mxp.setAudFechaModificacion(new Date());
			mxp.setAudUsuarioCreacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
			mxp.setAudUsuarioModificacion(cAPreForm.getUsuarioConectado().getNombreUsuario());
			Transaction txMxp = mxpDAO.getSession().beginTransaction();
			mxpDAO.save(mxp);
			txMxp.commit();
			
			//notas
			CtaNotNotas nota1 = new CtaNotNotas();
			CtaNotNotas nota2 = new CtaNotNotas();
			seteaNota(nota1, cas, cAPreForm, 1, 0.0,request);
			seteaNota(nota2, cas, cAPreForm, 2, (double) cAPreForm.getCantidad(),request);
			
			enviarAContabilidad(txa,request);
		}

		//----- Variables de configuracion
		cAPreForm.setArchivo(null);
		request.setAttribute("form", cAPreForm);
		request.setAttribute(Constantes.ACCION_KEY, "/cargaAutomaticaPrestamo");
		request.setAttribute("edit", 1);
		mensajes("msg.cAPre.creacionTotalExitosa", request);
		CargaAutomaticaPrestamoForm form2 = new CargaAutomaticaPrestamoForm();
		return sinxls(mapping, form2, request, response);
	}
	
	public ActionForward cargarTipos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CargaAutomaticaPrestamoForm cargaForm = (CargaAutomaticaPrestamoForm)form;
		CtaTprTipoPrestamoDAO tprDao = new CtaTprTipoPrestamoDAO(getSessionHibernate(request));
		String listaResponse = "";
		try{
			if(cargaForm.getLprId().equals(-1)){
				listaResponse = "No hay elementos que mostrar";
			}else{
				List tpr = tprDao.findByLinea(cargaForm.getLprId());
				listaResponse = contruirListaTipos(tpr,request);
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
	
	private String contruirListaTipos(List tpr, HttpServletRequest request) {
		HtmlBuilder html = new HtmlBuilder();
		html.select().name("tprId");
		html.id("tprId").styleClass("obligatorio").close();
		for (Iterator iterator = tpr.iterator(); iterator.hasNext();) {
			CtaTprTipoPrestamo tipo = (CtaTprTipoPrestamo) iterator.next();
			html.option().value(tipo.getTprId().toString()).close().append(tipo.getTprNombre()).optionEnd();
		}
		html.selectEnd();
		return html.toString();
	}
	
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.cAPre.lista", "lista");
		map.put("cmd.cAPre.sinxls", "sinxls");
		map.put("cmd.cAPre.cargar", "cargar");
		map.put("cmd.cAPre.cargar2", "cargar2");
		map.put("cmd.cAPre.cargarListaAsociados", "cargarListaAsociados");
		map.put("cmd.cAPre.cargarTodos", "cargarTodos");
		map.put("cmd.cAPre.cargarTipos", "cargarTipos");
		return map;
	}
}