package com.cetia.sicaco.cliente.struts.action;

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
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.cliente.struts.form.SolicitudesForm;
import com.cetia.sicaco.cuentaCorriente.struts.action.CuentaAhorroAction;
import com.cetia.sicaco.cuentaCorriente.struts.action.CuentaAsociadoAction;
import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorroDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria;
import com.cetia.sicaco.hibernate.CtaCbaCuentaBancariaDAO;
import com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorro;
import com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorroDAO;
import com.cetia.sicaco.hibernate.CtaPrePrestamoDAO;
import com.cetia.sicaco.hibernate.CtaStbSolTransBanc;
import com.cetia.sicaco.hibernate.CtaStbSolTransBancDAO;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccionDAO;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtrBanBanco;
import com.cetia.sicaco.hibernate.CtrBanBancoDAO;
import com.cetia.sicaco.hibernate.CtrParParametros;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.InvProProveedorDAO;
import com.cetia.sicaco.hibernate.OrdOcoOrdenDeCompraDAO;
import com.cetia.sicaco.hibernate.OrdPecPeticionCompra;
import com.cetia.sicaco.hibernate.OrdPecPeticionCompraDAO;
import com.cetia.sicaco.hibernate.SecSucSucursal;
import com.cetia.sicaco.hibernate.SecSucSucursalDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.Format;

public class SolicitudesAction extends DMLAction {
	
	
	
	
	public ActionForward dml(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.setAttribute(Constantes.ACCION_KEY, "/solicitudes");
		SolicitudesForm solicitudForm = (SolicitudesForm) form;
		CtaAscAsociadoDAO cuentaDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado cuenta = (CtaAscAsociado)cuentaDAO.findByAscCodigoAsociado(solicitudForm.getUsuarioConectado().getNombreUsuario()).get(0);
		String codigoAsc = cuenta.getAscId();
//		System.out.println(codigoAsc);
		return mapping.findForward("dml");
	}
	
	public ActionForward cargarSolicitudes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SolicitudesForm solicitudForm = (SolicitudesForm) form;
		String texto = "";
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		//htmlBuilder.table(0).align("center").border("0").close().tr(0).close();
		htmlBuilder.append("<table\n"+
				"		style=\"font-family: 'Lucida Sans Unicode', 'Lucida Grande', Sans-Serif;\n"+
				"		font-size: 13px;"+
				"		margin-bottom: 20px;"+
				"		margin-top:20px;"+
				"		margin-left: auto;"+
				"		margin-right: auto;"+
				"		text-align: left;"+
				"		width: 100%;"+
				"		border-collapse: collapse;"+
				"		padding: 10px 10px 10px 10px;\" id=\"hor-zebra\">");
		
		CtaAscAsociadoDAO cuentaDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado cuenta = (CtaAscAsociado)cuentaDAO.findByAscCodigoAsociado(solicitudForm.getUsuarioConectado().getNombreUsuario()).get(0);
		String codigoAsc = cuenta.getAscId();
		Double disponible = 0.0;
		
		if(solicitudForm.getOpSolicitud()==1){
			htmlBuilder.append("<tr> <th colspan=\"2\"> <b>Solicitid de retiro de ahorros </b></th> </tr> ");
			htmlBuilder.append("<tr class =\"odd\">");
			htmlBuilder.append("<td>");
			htmlBuilder.append("<label>Cuenta Fuente:</label>").tdEnd();
			htmlBuilder.td(0).close();
			htmlBuilder.select().name("cuenta").id("cuenta").onchange("cargarDisponible();").close();
			
			CtaCasCuentaAsociadoDAO casDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
			List lah = (List) casDAO.findByAscAndTipoCuenta2(codigoAsc, "B");
			int c = 0;
			for(Iterator iterador = lah.iterator(); iterador.hasNext();){
				CtaCasCuentaAsociado casCuenta = (CtaCasCuentaAsociado) iterador.next();
				if(casCuenta.getCtaCahCuentaAhorro().getCahId().substring(0, 1).equals("B")){
					if(c==0){
						disponible = casCuenta.getCtaCahCuentaAhorro().getCahSaldoActual();
					}
					if(casCuenta.getCtrEstEstado().getEstId()==9){
						htmlBuilder.option().value(casCuenta.getCasCuenta().toString()).close();
						htmlBuilder.append(casCuenta.getCtaCahCuentaAhorro().getCahId()+" - "+casCuenta.getCtaCahCuentaAhorro().getCtaTahTipoAhorro().getTahNombre());
						htmlBuilder.optionEnd();
					}
					c++;
				}
			}
			htmlBuilder.selectEnd().tdEnd().trEnd(0);
			htmlBuilder.tr(0).close();
			htmlBuilder.td(0).close().append("<label>Disponible:</label>").tdEnd();
			htmlBuilder.td(0).close().div().id("disp").close();
			htmlBuilder.input().type("text").readonly().id("disponible").name("disponible").size("10").value(Format.formatDinero(disponible)).close();
			htmlBuilder.divEnd().tdEnd().trEnd(0);
			htmlBuilder.tr(0).append(" class =\"odd\"").close().td(0).close().append("<label>Cantidad:</label>").tdEnd();
			htmlBuilder.td(0).close().input().type("text").size("8")
				.onkeyup("dosDecimales($('#txaMonto').val(),'txaMonto');")
				.styleClass("obligatorio").name("txaMonto").id("txaMonto").close().tdEnd().trEnd(0);
			
			/*para cargar los bancos*/
			CtrBanBancoDAO bancoDAO = new CtrBanBancoDAO(getSessionHibernate(request));
			List lban = bancoDAO.findAll();
			htmlBuilder.td(0).close().append("<label>Banco:</label>").tdEnd();
			htmlBuilder.td(0).close().select().name("banIdD").id("banIdD");
			htmlBuilder.onchange("cargarCuentas();").close();
			for (Iterator iterator = lban.iterator(); iterator.hasNext();) {
				CtrBanBanco banco = (CtrBanBanco) iterator.next();
				htmlBuilder.option().value(banco.getBanId().toString()).close().append(banco.getBanNombre()).optionEnd();
			}
			htmlBuilder.selectEnd().tdEnd();
			htmlBuilder.trEnd(0).tr(0).append(" class =\"odd\"").close();
			htmlBuilder.td(0).close().append("<label>Cuenta Destino:</label>").tdEnd();
			htmlBuilder.td(0).close();
			htmlBuilder.div().id("bancarias").close();
			if(lban.size() > 0){
				htmlBuilder.select().name("cuentaBan").id("cuentaBanId").close();
				CtaCbaCuentaBancariaDAO cuentaBancariaDAO = new CtaCbaCuentaBancariaDAO(getSessionHibernate(request));
				CtrBanBanco banco = (CtrBanBanco) lban.get(0);
				List cuentasBancarias = cuentaBancariaDAO.findByAscAndBanco(codigoAsc,banco.getBanId());
				for (Iterator iterator = cuentasBancarias.iterator(); iterator
						.hasNext();) {
					CtaCbaCuentaBancaria cba = (CtaCbaCuentaBancaria) iterator.next();
					htmlBuilder.option().value(cba.getCbaId().toString()).close().append(cba.getCbaCuenta()).optionEnd();
					String codigoBanco = cba.getCbaId().toString();
				}
				htmlBuilder.selectEnd();
			}else{
				htmlBuilder.append("No hay cuentas que mostrar");
			}
			htmlBuilder.divEnd().tdEnd();
			/******************************************************************************************************************/
			
		}
		if(solicitudForm.getOpSolicitud()==2){
			htmlBuilder.append("<tr > <th colspan=\"2\"> <b>Solicitud de orden de compra </b></th> </tr> ");
			htmlBuilder.append("<tr class=\"odd\" > ");
			htmlBuilder.td(0).close().append("<label>Credito Disponible: $</label>").tdEnd();
			htmlBuilder.td(0).close();
			
			CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
			Double creditoUsado = prestamoDAO.sumCreditoUtilizado(codigoAsc, "O");
			
			OrdOcoOrdenDeCompraDAO ordenDAO = new OrdOcoOrdenDeCompraDAO(getSessionHibernate(request));
			creditoUsado += ordenDAO.usadoEmitidasAsociado(codigoAsc);
			CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
			CtrParParametros parametros = parametrosDAO.findById("CREDITO_ORDEN");
			
			if(creditoUsado==null){
				creditoUsado = 0.0;
			}
			OrdPecPeticionCompraDAO petCompDAO = new OrdPecPeticionCompraDAO(getSessionHibernate(request));
			creditoUsado += petCompDAO.creditoUtilizado(codigoAsc);
			
			if(creditoUsado<=parametros.getParValorNumber()){
				disponible = parametros.getParValorNumber() - creditoUsado;
			}
						
			htmlBuilder.input().type("text").id("disponible").name("disponible").readonly().size("10").value(disponible.toString()).close();
			htmlBuilder.tr(0).close().td(0).close().append("<label>Cargar Proveedor:</label>").tdEnd();
			htmlBuilder.td(0).close().select().name("provId").id("provId").append("style = \"width: 300px;\"").close();
			InvProProveedorDAO provDAO = new InvProProveedorDAO(getSessionHibernate(request));
			List listaProveedores = provDAO.findAll();
			Iterator iProv = listaProveedores.iterator();
			while(iProv.hasNext()){
				InvProProveedor prov = (InvProProveedor) iProv.next();
				htmlBuilder.option().value(prov.getProId().toString()).close().append(prov.getProNombre()).optionEnd();
			}
			htmlBuilder.selectEnd();
			htmlBuilder.tdEnd().trEnd(0);
			htmlBuilder.tr(0).append(" class=\"odd\" ").close().td(0).close().append("<label>Monto:</label>").tdEnd();
			htmlBuilder.td(0).close().input().type("text").id("ocoMonto").name("ocoMonto").size("10").onkeyup("dosDecimales($('#ocoMonto').val(),'ocoMonto');").close();
			htmlBuilder.tr(0).close().td(0).close().append("<label>Lugar de Entrega:</label>").tdEnd();
			htmlBuilder.td(0).close().select().name("lugar").id("lugar").close();
			SecSucSucursalDAO sucursalDAO = new SecSucSucursalDAO(getSessionHibernate(request));
			List listaSucursales = sucursalDAO.findAll();
			Iterator iSuc = listaSucursales.iterator();
			while(iSuc.hasNext()){
				SecSucSucursal suc = (SecSucSucursal) iSuc.next();
				htmlBuilder.option().value(suc.getSucId().toString()).close().append(suc.getSucNombre()).optionEnd();
			}
			htmlBuilder.selectEnd();			
		}
		
		htmlBuilder.tableEnd(1);
		texto = htmlBuilder.toString();
		try{
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(texto);
			response.getWriter().flush();
			response.getWriter().close();
		} catch(RuntimeException e){
			log.error("Runtime Error", e);
		} catch(IOException e){
			log.error(e);
		}
		return null;
	}
	
	public ActionForward disponibilidad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SolicitudesForm solicitudForm = (SolicitudesForm) form;
		String texto= "";
		double disponible = 0.0;
		CtaCasCuentaAsociadoDAO casDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociado cas = casDAO.findById(Long.valueOf(solicitudForm.getCuenta()));
		disponible = cas.getCtaCahCuentaAhorro().getCahSaldoActual();
		
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		
		htmlBuilder.td(0).close();
		htmlBuilder.input().type("text").readonly().id("disponibilidad").size("10").value(Format.formatDinero(disponible)).end();
		htmlBuilder.tdEnd();
		texto = htmlBuilder.toString();
		
		try{
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(texto);
			response.getWriter().flush();
			response.getWriter().close();
		}catch (RuntimeException e){
			log.error("Error en tiempo de ejecucion", e);
		}catch (IOException e){
			log.error(e);
		}
		
		return null;
	}
	
	public ActionForward htmlBancarias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SolicitudesForm solicitudForm = (SolicitudesForm)form;
		
		CtaAscAsociadoDAO cuentaDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado cuenta = (CtaAscAsociado)cuentaDAO.findByAscCodigoAsociado(solicitudForm.getUsuarioConectado().getNombreUsuario()).get(0);
		String codigoAsc = cuenta.getAscId();
		
		
		String texto = "";
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		CtaCbaCuentaBancariaDAO cuentaBancariaDAO = new CtaCbaCuentaBancariaDAO(getSessionHibernate(request));
		List cuentasBancarias = cuentaBancariaDAO.findByAscAndBanco(codigoAsc,solicitudForm.getBanIdD());
		if(cuentasBancarias.size()>0){
			htmlBuilder.select().name("cuentaBan").id("cuentaBanId").close();
			for (Iterator iterator = cuentasBancarias.iterator(); iterator
					.hasNext();) {
				CtaCbaCuentaBancaria cba = (CtaCbaCuentaBancaria) iterator.next();
				htmlBuilder.option().value(cba.getCbaId().toString()).close().append(cba.getCbaCuenta()).optionEnd();
				String codigoBanco = cba.getCbaId().toString();
			}
			htmlBuilder.selectEnd();
		}else{
			htmlBuilder.append("No hay cuentas que mostrar");
		}
		texto = htmlBuilder.toString();
		try{
			//log.error(texto);
			response.setCharacterEncoding("UTF-8");
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
	
	public ActionForward realizarSolicitud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SolicitudesForm solicitudForm = (SolicitudesForm) form;
		
		String codigoBanco = solicitudForm.getCuentaBan();
//		System.out.println("CodigoBanco: "+ codigoBanco);
		
		int error=0;
		
		if(solicitudForm.getOpSolicitud()==1){//Peticion de retiro de ahorros
			if(solicitudForm.getCuenta()==null){
				mensajes("error.solicitudes.cuentaAho", solicitudForm, request, response);
				error=1;
			}
			if(solicitudForm.getCuentaBan()==null){
				mensajes("error.solicitudes.cba", solicitudForm, request, response);
				error=1;
			}
			if(solicitudForm.getTxaMonto()==null || solicitudForm.getTxaMonto()<=0){
				mensajes("error.solicitudes.monto", solicitudForm, request, response);
				error=1;
			}
			if(error!=1){
				CtaCasCuentaAsociadoDAO casDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
				CtaCasCuentaAsociado cas = casDAO.findById(Long.valueOf(solicitudForm.getCuenta()));
				CuentaAhorroAction cahAction = new CuentaAhorroAction();
				double monto = solicitudForm.getTxaMonto();
				
				double penalidad = 0.00;
				penalidad = cahAction.calculoPenalidad(cas.getCtaCahCuentaAhorro(), monto,request);
				if(monto>cas.getCtaCahCuentaAhorro().getCahSaldoActual()){//
					mensajes("error.solicitudes.montoMayor", solicitudForm, request, response);					
				}else{//Realizamos la transaccion		
					CtaTxaTransaccionxcuentaAsociadoDAO txaDAO = new CtaTxaTransaccionxcuentaAsociadoDAO(getSessionHibernate(request));
					CuentaAsociadoAction cuentaAsociadoAction = new CuentaAsociadoAction();
					CuentaAhorroAction cuentaAhorroAction = new CuentaAhorroAction();
					CtaTxaTransaccionxcuentaAsociado txa = new CtaTxaTransaccionxcuentaAsociado();
					CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
					
					solicitudForm.setTxaMonto(monto-penalidad);
					txa.setCtaCasCuentaAsociado(cas);
					txa.setTxaFecha(new Date());
					txa.setTxaComprobante(null);
					txa.setTxaMonto(solicitudForm.getTxaMonto());
					txa.setCtaNotNotas(null);
					txa.setTxaCheque(null);
					txa.setCtaTtrTipoTransaccion(tipoTransaccionDAO.findById(26));//Peticion de Transaccion Electronica
					txa.setAudFechaCreacion(new Date());
					txa.setAudFechaModificacion(new Date());
					txa.setAudUsuarioCreacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
					txa.setAudUsuarioModificacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
					txa.setTxaEstado("P");
					txaDAO.save(txa);
					Transaction tx = txaDAO.getSession().beginTransaction();
					tx.commit();
					txaDAO.getSession().flush();
					txaDAO.getSession().clear();
					
					CtaCahCuentaAhorroDAO ahorroDAO = new CtaCahCuentaAhorroDAO(getSessionHibernate(request));
					CtaMxaMovimientoAhorroDAO movimientoAhorroDAO = new CtaMxaMovimientoAhorroDAO(getSessionHibernate(request));
					
					CtaCahCuentaAhorro ahorro = ahorroDAO.findById(cas.getCtaCahCuentaAhorro().getCahId());
					Double nuevoInteres = cuentaAsociadoAction.getTotalInteresesAhorro(ahorro.getCahId(),request);
					
					ahorro.setCahInteresAcumulado(nuevoInteres);
					ahorro.setCahSaldoActual(ahorro.getCahSaldoActual() - solicitudForm.getTxaMonto());
					ahorroDAO.merge(ahorro);
					Transaction tx1 = ahorroDAO.getSession().beginTransaction();
					tx1.commit();
					
					//Movimiento por el cargo/abono
					CtaMxaMovimientoAhorro movimientoAhorro = new CtaMxaMovimientoAhorro();
					movimientoAhorro.setCtaCahCuentaAhorro(ahorro);
					movimientoAhorro.setCtaTxaTransaccionxcuentaAsociado(txa);
					movimientoAhorro.setMxaFecha(new Date());
					movimientoAhorro.setMxaInteresTran(cuentaAhorroAction.calculoInteresTransaccion(ahorro.getCahId(), new Date(),request));
					movimientoAhorro.setMxaMonto(solicitudForm.getTxaMonto());
					movimientoAhorro.setMxaSaldo(ahorro.getCahSaldoActual());
					movimientoAhorro.setAudFechaCreacion(new Date());
					movimientoAhorro.setAudFechaModificacion(new Date());
					movimientoAhorro.setAudUsuarioCreacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
					movimientoAhorro.setAudUsuarioModificacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
					movimientoAhorroDAO.save(movimientoAhorro);
					Transaction tx2 = movimientoAhorroDAO.getSession().beginTransaction();
					tx2.commit();
					
					enviarAsolTransBanc(txa, movimientoAhorro, codigoBanco, penalidad,request);
					
					//Enviamos la penalidad a peticion de transaccion electronica
					if(penalidad>0){
						CtaTxaTransaccionxcuentaAsociado txa2 = new CtaTxaTransaccionxcuentaAsociado();
						
						txa2.setCtaCasCuentaAsociado(cas);
						txa2.setTxaFecha(new Date());
						txa2.setTxaComprobante(null);
						txa2.setTxaMonto(penalidad);
						txa2.setCtaNotNotas(null);
						txa2.setTxaCheque(null);
						txa2.setCtaTtrTipoTransaccion(tipoTransaccionDAO.findById(38));//Cargo por penalidad en ahorros
						txa2.setAudFechaCreacion(new Date());
						txa2.setAudFechaModificacion(new Date());
						txa2.setAudUsuarioCreacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
						txa2.setAudUsuarioModificacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
						txa2.setTxaEstado("P");
						txaDAO.save(txa2);
						Transaction tx3 = txaDAO.getSession().beginTransaction();
						tx3.commit();
						txaDAO.getSession().flush();
						txaDAO.getSession().clear();

						nuevoInteres = cuentaAsociadoAction.getTotalInteresesAhorro(ahorro.getCahId(),request);
						
						ahorro.setCahInteresAcumulado(nuevoInteres);
						ahorro.setCahSaldoActual(ahorro.getCahSaldoActual() - penalidad);
						ahorroDAO.merge(ahorro);
						Transaction tx4 = ahorroDAO.getSession().beginTransaction();
						tx4.commit();
						
						//Movimiento por el cargo
						CtaMxaMovimientoAhorro movimientoAhorro2 = new CtaMxaMovimientoAhorro();
						movimientoAhorro2.setCtaCahCuentaAhorro(ahorro);
						movimientoAhorro2.setCtaTxaTransaccionxcuentaAsociado(txa2);
						movimientoAhorro2.setMxaFecha(new Date());
						movimientoAhorro2.setMxaInteresTran(cuentaAhorroAction.calculoInteresTransaccion(ahorro.getCahId(), new Date(),request));
						movimientoAhorro2.setMxaMonto(penalidad);
						movimientoAhorro2.setMxaSaldo(ahorro.getCahSaldoActual());
						movimientoAhorro2.setAudFechaCreacion(new Date());
						movimientoAhorro2.setAudFechaModificacion(new Date());
						movimientoAhorro2.setAudUsuarioCreacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
						movimientoAhorro2.setAudUsuarioModificacion(solicitudForm.getUsuarioConectado().getNombreUsuario());
						movimientoAhorroDAO.save(movimientoAhorro2);
						Transaction tx5 = movimientoAhorroDAO.getSession().beginTransaction();
						tx5.commit();						
						
						//enviarAsolTransBanc(txa2, movimientoAhorro2, codigoBanco, 1);
					}					
					
					mensajes("lbl.solicitudes.exito", solicitudForm, request, response);					
				}			
			}
		}else{
			if(solicitudForm.getOpSolicitud()==2){//Peticion de Orden de Compra
				if(solicitudForm.getOcoMonto()==null || solicitudForm.getOcoMonto()<=0){
					mensajes("error.solicitudes.monto", solicitudForm, request, response);
					error=1;
				}
				if(solicitudForm.getOcoMonto()>solicitudForm.getDisponible()){
					mensajes("error.solicitudes.montoMayor", solicitudForm, request, response);
					error=1;
				}
				if(solicitudForm.getProvId()==null){
					mensajes("error.solicitudes.provId", solicitudForm, request, response);
					error=1;
				}
				if(error!=1){
					OrdPecPeticionCompraDAO petCompraDAO = new OrdPecPeticionCompraDAO(getSessionHibernate(request));
					Transaction tx = petCompraDAO.getSession().beginTransaction();
						
					CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
					InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSessionHibernate(request));
					
					CtaAscAsociado asociado = (CtaAscAsociado)asociadoDAO.findByAscCodigoAsociado(solicitudForm.getUsuarioConectado().getNombreUsuario()).get(0);
					InvProProveedor invProProveedor = proveedorDAO.findById(solicitudForm.getProvId());
					
					OrdPecPeticionCompra petCompra = new OrdPecPeticionCompra();
					petCompra.setPecId(petCompraDAO.nextId());
					petCompra.setCtaAscAsociado(asociado);
					
					SecSucSucursalDAO sucursalDAO = new SecSucSucursalDAO(getSessionHibernate(request));
					SecSucSucursal sucursal = sucursalDAO.findById(solicitudForm.getLugar());
					petCompra.setSecSucSucursal(sucursal);
					petCompra.setPecMonto(solicitudForm.getOcoMonto());
					petCompra.setInvProProveedor(invProProveedor);						
					petCompra.setPecFecha(new Date());
					
					petCompraDAO.save(petCompra);
					tx.commit();
					mensajes("lbl.solicitudes.exito", solicitudForm, request, response);
				
				}				
				
			}else{
				mensajes("error.solicitudes.tipo", solicitudForm, request, response);
			}
		}
		return dml(mapping, solicitudForm, request, response);
	}
	
	public void mensajes(String msg,SolicitudesForm form,
			HttpServletRequest request, HttpServletResponse response){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(msg));
        saveMessages(request, errors);
	}
	
	private void enviarAsolTransBanc(CtaTxaTransaccionxcuentaAsociado txa,
			CtaMxaMovimientoAhorro movimientoAhorro, String cuentaBan, Double penalidad,HttpServletRequest request) {
		CtaStbSolTransBancDAO solTransBancDAO = new CtaStbSolTransBancDAO(getSessionHibernate(request));
		CtaStbSolTransBanc solTransBanc = new CtaStbSolTransBanc();
		CtaCahCuentaAhorroDAO cuentaAhorroDAO = new CtaCahCuentaAhorroDAO(getSessionHibernate(request));
		CtaCahCuentaAhorro cuentaAhorro = cuentaAhorroDAO.findById(movimientoAhorro.getCtaCahCuentaAhorro().getCahId());
		solTransBanc.setCtaCahCuentaAhorro(cuentaAhorro);
		solTransBanc.setStbEstado("N");
		solTransBanc.setStbFechaSol(new Date());
		solTransBanc.setStbMonto(movimientoAhorro.getMxaMonto());
		CtaCasCuentaAsociadoDAO casDao = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociado cas = casDao.findbyCahId(cuentaAhorro.getCahId());
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		CtaAscAsociado asociado = asociadoDAO.findById(cas.getCtaAscAsociado().getAscId());
		String nombreAsociado = asociado.getSecPerPersona().getPerPrimerNombre() + " " +
			asociado.getSecPerPersona().getPerSegundoNombre() + " " +
			asociado.getSecPerPersona().getPerPrimerApellido() + " " +
			asociado.getSecPerPersona().getPerSegundoApellido();
		solTransBanc.setStbNombreAsociado(nombreAsociado);
		solTransBanc.setStbRazon("Deposito pago ahorros electronicos");
		CtaCbaCuentaBancariaDAO bancariaDAO = new CtaCbaCuentaBancariaDAO(getSessionHibernate(request));
		CtaCbaCuentaBancaria bancaria = bancariaDAO.findById(cuentaBan);
		solTransBanc.setCtaCbaCuentaBancaria(bancaria);
		solTransBanc.setStbTipoAhorro(bancaria.getCtaTcuTipoCuenta().getTcuNombre());
		solTransBanc.setInvPcbProveedorCuentaBancaria(null);
		solTransBanc.setStbPenalidad(penalidad);
		solTransBancDAO.save(solTransBanc);
		
		Transaction tx = solTransBancDAO.getSession().beginTransaction();
		tx.commit();
		solTransBancDAO.getSession().flush();
		solTransBancDAO.getSession().clear();
	}
	
	@Override
	protected Map<String,String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.solicitudes.dml", "dml");
		map.put("cmd.solicitudes.cargarSolicitudes", "cargarSolicitudes");
		map.put("cmd.solicitudes.disponibilidad", "disponibilidad");
		map.put("cmd.solicitudes.htmlBancarias", "htmlBancarias");
		map.put("cmd.solicitudes.realizar", "realizarSolicitud");
		// TODO Auto-generated method stub
		return map;
	}

}
