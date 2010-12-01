package com.cetia.sicaco.struts;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cetia.sicaco.hibernate.ConCpaConceptoPartidaDAO;
import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConCueCuentaDAO;
import com.cetia.sicaco.hibernate.ConDpaDetallePartida;
import com.cetia.sicaco.hibernate.ConDpaDetallePartidaDAO;
import com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontable;
import com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontableDAO;
import com.cetia.sicaco.hibernate.ConPcoPartidaContable;
import com.cetia.sicaco.hibernate.ConPcoPartidaContableDAO;
import com.cetia.sicaco.hibernate.CtaChkChequePrestamo;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccionDAO;
import com.mad.utilidades.filtros.FiltroOpenSession;

public class PartidaAutomatica {
		
	//Metodo encargado de crear las partidas automaticas para cada modulo
	public void crearPartidaAutomatica(String pars,Double monto,String usuario,int tpaId,Integer chequePendiente
			,CtaChkChequePrestamo cheque,Integer chequeNegociable,HttpServletRequest request){
		ConDpaDetallePartida detalle;
		String nombre = "";
		String[] args = pars.split(";");
//		System.out.println("Parámetros: " + pars);
		DecimalFormat df = new DecimalFormat("#.00");
		try {
//			System.out.println(df.format(monto));
			monto = Double.valueOf(df.format(monto));
		} catch (Exception e1) {
			System.out.println("no se pudo redondear el numero");
		}
		Integer modulo = Integer.parseInt(args[0]);
		ConDpaDetallePartidaDAO detallePartidaDAO = new ConDpaDetallePartidaDAO(getSessionHibernate(request));
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		ConMxcModuloxCuentacontableDAO moduloxCuentacontableDAO = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		List<ConMxcModuloxCuentacontable> listaParametros = moduloxCuentacontableDAO.findByCxcParametrosUnion(pars);
		if(!listaParametros.isEmpty()){ // si hay al menos una configuracion se creara la partida, sino no.
			List<ConPcoPartidaContable> lista = partidaContableDAO.findByModuloFecha(new Date(),modulo);//buscamos si existe alguna partida de este modulo, en este dia
			Transaction tx = partidaContableDAO.getSession().beginTransaction();
			try{
				switch (modulo) {
				case 1:nombre = "Partida de movimientos contables del modulo Cuenta Corriente";
							break;
				case 2:nombre = "Partida de movimientos contables del modulo Inventario";
							break;
				case 3:nombre = "Partida de movimientos contables del modulo Orden de Compra";
							break;
				case 4:nombre = "Partida de movimientos contables del modulo Orden de Pago";
							break;
				case 5:nombre = "Partida de movimientos contables por cuota de afiliacion en el modulo Asociados";
							break;
				case 6:nombre = "Partida de movimientos contables por Distribucion de dividendos";
							break;
				case 7:nombre = "Partida de movimientos contables por Liquidacion de Asociados(Saldo a Favor)";
							break;
				case 8:nombre = "Partida de movimientos contables por Descuentos en Planilla (Retenciones de Mas)";
							break;
				case 9:nombre = "Partida de movimientos contables por Multa de Ahorros";
							break;
				case 10:nombre = "Partida de movimientos contables por Descuentos a socios retirados";
				break;
	
			}
				if(lista.isEmpty() && tpaId==1){//si no hay entonces se crea la partida
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(partidaContable);
				}
				if(tpaId==2){// esto es por si la partida es de cheque
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(0,partidaContable);
				}
				if(lista.isEmpty() && tpaId==3){// Partida de descuento en planilla, si no existe debemos crearla.
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(0,partidaContable);
				}
				ConPcoPartidaContable partidaCon = (ConPcoPartidaContable) lista.get(0);
				Iterator<ConMxcModuloxCuentacontable> iterador = listaParametros.iterator();
				List<ConDpaDetallePartida> listaDetalles;
				while(iterador.hasNext()){
					ConMxcModuloxCuentacontable moduloParametros = iterador.next();
					//preguntamos si ya existe un detalle de esta partida, y con este concepto, para solo actualizar los valores
					//si la partida no existe entonces se debe crear
					listaDetalles = detallePartidaDAO.findByPartidaModulo((moduloParametros.getConCpaConceptoPartida() != null?moduloParametros.getConCpaConceptoPartida().getCpaId():null), partidaCon.getPcoId(),moduloParametros.getCxaConceptoExtra(),moduloParametros.getConCueCuenta().getCueId());
					if(listaDetalles.isEmpty()){
						detalle = new ConDpaDetallePartida();
						detalle.setConPcoPartidaContable(partidaCon);
						detalle.setConCueCuenta(moduloParametros.getConCueCuenta());
						if(modulo == 1){
							detalle.setConCpaConceptoPartida(null);
							CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
							detalle.setDpaOtroConcepto(tipoTransaccionDAO.findById(Integer.parseInt(args[3])).getTtrNombre());
						}else{
							if(moduloParametros.getConCpaConceptoPartida() == null){
									detalle.setConCpaConceptoPartida(null);
									detalle.setDpaOtroConcepto(moduloParametros.getCxaConceptoExtra());
							}else{
									detalle.setConCpaConceptoPartida(moduloParametros.getConCpaConceptoPartida());
							}
						}
						if(moduloParametros.getCxcCargoAbono()==1){// el movimiento es un cargo
//							System.out.println("Cargando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorDebe(monto);
							detalle.setDpaValorHaber(0.00);
							actualizarSaldos(moduloParametros.getConCueCuenta(), monto,0,request);
						}else{//el movimiento es un abono
//							System.out.println("Abonando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorDebe(0.00);
							detalle.setDpaValorHaber(monto);
							actualizarSaldos(moduloParametros.getConCueCuenta(), 0, monto,request);
						}
						detallePartidaDAO.save(detalle);
					}else{//actualizacion por si ya existe
						detalle = listaDetalles.get(0);
						if(moduloParametros.getCxcCargoAbono()==1){// el movimiento es un cargo
//							System.out.println("Cargando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
								detalle.setDpaValorDebe(detalle.getDpaValorDebe()+monto);
								actualizarSaldos(moduloParametros.getConCueCuenta(), monto,0,request);
						}else{//el movimiento es un abono
//							System.out.println("Abonando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorHaber(detalle.getDpaValorHaber()+monto);
								actualizarSaldos(moduloParametros.getConCueCuenta(), 0, monto,request);
						}
						detallePartidaDAO.merge(detalle);
					}
					tx.commit();
					//System.out.println("Exito al almacenar la partida");
				}
				partidaContableDAO.getSession().flush();
				detallePartidaDAO.getSession().flush();
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				detallePartidaDAO.getSession().clear();
				partidaContableDAO.getSession().clear();
				//partidaContableDAO.getSession().close();
			}
		}
	}
	
	//metodo que crea las partidas para cada modulo si no existen
	private ConPcoPartidaContable crearPartida(String concepto,int modulo,String usuario,int tpaId,Integer chequePendiente
			,CtaChkChequePrestamo cheque,Integer chequeNegociable, HttpServletRequest request){
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		ConPcoPartidaContable partidaContable = new ConPcoPartidaContable();
		//ConPcoPartidaContable ultimaPartidaContable = partidaContableDAO.getLastPartidaPorEstado("P");
		//partidaContable.setPcoId(ultimaPartidaContable.getPcoId()+1);
//		partidaContable.setPcoId(partidaContableDAO.nextId());
		partidaContable.setPcoModulo(modulo);
		partidaContable.setPcoFechaIngresoPartida(new Date());
		partidaContable.setAudFechaCreacion(new Date());
		partidaContable.setAudFechaModificacion(new Date());
		partidaContable.setPcoFechaIngresoPartida(new Date());
		partidaContable.setAudUsuarioCreacion(usuario);
		partidaContable.setAudUsuarioModificacion(usuario);
		partidaContable.setConCpaConceptoPartida(null);
		partidaContable.setPcoOtroConcepto(concepto);
		partidaContable.getConTpaTipoPartida().setTpaId(tpaId);
		partidaContable.setPcoEstado("P");
		partidaContable.setCtaChkChequePrestamo(cheque);
		partidaContable.setPcoChequePendiente(chequePendiente);
		partidaContable.setPcoChequeNegociable(chequeNegociable);
		String[] fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date()).split("-");
		partidaContable.setPcoComprobantePartida(partidaContableDAO.getLastComprobante(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]))+1);
		return partidaContable;
	}

	//Metodo que se utiliza para el manejo de partidas de descuento en planilla
	public void crearPartidaAutomaticaPlanilla(String pars,Double monto,String usuario,int tpaId, Date fechaP,HttpServletRequest request){
		ConDpaDetallePartida detalle;
		String nombre = "";
		String[] args = pars.split(";");
		//System.out.println(pars);
		Integer modulo = Integer.parseInt(args[0]);
		if (modulo == 1){
			nombre = "Partida de movimientos contables del modulo Cuenta Corriente";
		}else{
			nombre = "Partida de movimientos contables por Descuentos en Planilla (Retenciones de Mas)";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			System.out.println(df.format(monto));
			monto = Double.valueOf(df.format(monto));
		} catch (Exception e1) {
			System.out.println("no se pudo redondear el numero");
		}
		ConDpaDetallePartidaDAO detallePartidaDAO = new ConDpaDetallePartidaDAO(getSessionHibernate(request));
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		ConMxcModuloxCuentacontableDAO moduloxCuentacontableDAO = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		//Obtiene las relaciones contables
		List<ConMxcModuloxCuentacontable> listaParametros = moduloxCuentacontableDAO.findByCxcParametrosUnion(pars);
		if(!listaParametros.isEmpty()){ // si hay al menos una relaciï¿½n contable se creara la partida.
			//buscamos si existe alguna partida de actualizaciï¿½n de planilla, en este dia
			List<ConPcoPartidaContable> lista = partidaContableDAO.findByTipoPartidaFecha(fechaP,tpaId);
			Transaction tx = partidaContableDAO.getSession().beginTransaction();
			try{
				if(tpaId != 3)System.out.println("Error. El tipo de partida no es 3 (Descuento en planilla)");
				if(lista.isEmpty() && tpaId==3){// Partida de descuento en planilla, si no existe debemos crearla.
					ConPcoPartidaContable partidaContable = crearPartidaPlanilla(nombre, modulo, usuario, tpaId, fechaP,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(0,partidaContable);
				}
				ConPcoPartidaContable partidaCon = (ConPcoPartidaContable) lista.get(0);
				Iterator<ConMxcModuloxCuentacontable> iterador = listaParametros.iterator();
				List<ConDpaDetallePartida> listaDetalles;
				while(iterador.hasNext()){
					ConMxcModuloxCuentacontable moduloParametros = iterador.next();
					//preguntamos si ya existe un detalle de esta partida, y con este concepto, para solo actualizar los valores
					//si la partida no existe entonces se debe crear
					listaDetalles = detallePartidaDAO.findByPartidaModulo((moduloParametros.getConCpaConceptoPartida() != null?moduloParametros.getConCpaConceptoPartida().getCpaId():null), partidaCon.getPcoId(),moduloParametros.getCxaConceptoExtra(),moduloParametros.getConCueCuenta().getCueId());
					if(listaDetalles.isEmpty()){
						detalle = new ConDpaDetallePartida();
						detalle.setConPcoPartidaContable(partidaCon);
						detalle.setConCueCuenta(moduloParametros.getConCueCuenta());
						detalle.setConCpaConceptoPartida(null);
						CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
						if(modulo == 8){
							detalle.setDpaOtroConcepto("Retenciones de mas");
						}else{
							detalle.setDpaOtroConcepto(tipoTransaccionDAO.findById(Integer.parseInt(args[3])).getTtrNombre());	
						}
						
						if(moduloParametros.getCxcCargoAbono()==1){// el movimiento es un cargo
//							System.out.println("(Planilla)Cargando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorDebe(monto);
							detalle.setDpaValorHaber(0.00);
							actualizarSaldos(moduloParametros.getConCueCuenta(), monto,0,request);
						}else{//el movimiento es un abono
//							System.out.println("(Planilla)Abonando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorDebe(0.00);
							detalle.setDpaValorHaber(monto);
							actualizarSaldos(moduloParametros.getConCueCuenta(), 0, monto,request);
						}
						detallePartidaDAO.save(detalle);
					}else{//actualizacion por si ya existe
						detalle = listaDetalles.get(0);
						if(moduloParametros.getCxcCargoAbono()==1){// el movimiento es un cargo
//							System.out.println("(Planilla)Cargando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
								detalle.setDpaValorDebe(detalle.getDpaValorDebe()+monto);
								actualizarSaldos(moduloParametros.getConCueCuenta(), monto,0,request);
						}else{//el movimiento es un abono
//							System.out.println("(Planilla)Abonando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorHaber(detalle.getDpaValorHaber()+monto);
								actualizarSaldos(moduloParametros.getConCueCuenta(), 0, monto,request);
						}
						detallePartidaDAO.merge(detalle);
					}
					tx.commit();
				}
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				
				detallePartidaDAO.getSession().flush();
				detallePartidaDAO.getSession().clear();
				partidaContableDAO.getSession().flush();
				partidaContableDAO.getSession().clear();
				//partidaContableDAO.getSession().close();
			}
		}
	}
	
	//Metodo creado para crear una partida de descuento en planilla en una fecha especï¿½fica.
	private ConPcoPartidaContable crearPartidaPlanilla(String concepto,int modulo,String usuario,int tpaId,Date fechaP,HttpServletRequest request){
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		ConPcoPartidaContable partidaContable = new ConPcoPartidaContable();
//		partidaContable.setPcoId(partidaContableDAO.nextId());
		partidaContable.setPcoModulo(modulo);
		partidaContable.setPcoFechaIngresoPartida(fechaP);
		partidaContable.setAudFechaCreacion(new Date());
		partidaContable.setAudFechaModificacion(new Date());
		partidaContable.setAudUsuarioCreacion(usuario);
		partidaContable.setAudUsuarioModificacion(usuario);
		partidaContable.setConCpaConceptoPartida(null);
		partidaContable.setPcoOtroConcepto(concepto);
		partidaContable.getConTpaTipoPartida().setTpaId(tpaId);
		partidaContable.setPcoEstado("P");
		partidaContable.setCtaChkChequePrestamo(null);
		partidaContable.setPcoChequePendiente(null);
		partidaContable.setPcoChequeNegociable(null);
		String[] fecha = new SimpleDateFormat("dd-MM-yyyy").format(fechaP).split("-");
		partidaContable.setPcoComprobantePartida(partidaContableDAO.getLastComprobante(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]))+1);
		return partidaContable;
	}
	
	private int actualizarSaldos(ConCueCuenta conCueCuenta,double valorDebe, double valorHaber, HttpServletRequest request){
		 //FIXME no olvidar setear en la llamada a esta funcion la cuenta padre que tenga la cuenta posteable
		 ConCueCuentaDAO conCueCuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		 try{
			 if(conCueCuenta.getConCueCuenta()==null){
				Double saldoActual = conCueCuenta.getCueSaldoActual();
				//preguntamos si es acreedora
				if(conCueCuenta.getCueTipoCuenta()!=1){// si no es retroactiva
					saldoActual= (conCueCuenta.getConTicTipoCuenta().getTicAcreeDeudo()==1)? saldoActual + valorDebe - valorHaber: saldoActual + valorHaber - valorDebe;
				}else{
					saldoActual= (conCueCuenta.getConTicTipoCuenta().getTicAcreeDeudo()==1)? saldoActual + valorHaber - valorDebe: saldoActual + valorDebe - valorHaber;
				}
				conCueCuenta.setCueSaldoActual(saldoActual);
				return 1;
			 }else{
			 	Double saldoActual = conCueCuenta.getCueSaldoActual();
			 	if(conCueCuenta.getCueTipoCuenta()!=1){// si no es retroactiva
					saldoActual= (conCueCuenta.getConTicTipoCuenta().getTicAcreeDeudo()==1)? saldoActual + valorDebe - valorHaber: saldoActual + valorHaber - valorDebe;
				}else{
					saldoActual= (conCueCuenta.getConTicTipoCuenta().getTicAcreeDeudo()==1)? saldoActual + valorHaber - valorDebe: saldoActual + valorDebe - valorHaber;
				}
			 	conCueCuenta.setCueSaldoActual(saldoActual);
				conCueCuentaDAO.merge(conCueCuenta);
				conCueCuentaDAO.getSession().flush();
				conCueCuentaDAO.getSession().clear();
				return 1 *actualizarSaldos(
						conCueCuentaDAO.findById(conCueCuenta.getConCueCuenta().getCueId()), valorDebe, valorHaber,request); 
			}
		 }catch(Exception e){
			 e.printStackTrace(); 
		 }
		 
		return 0;//si algo fallo
	 }
	
	//Metodo encargado de crear las partidas automaticas para cada modulo
	//y concatena lo que se envia a la ultima variable al concepto del detalle
	public void crearPartidaAutomatica(String pars,Double monto,String usuario,int tpaId,
						Integer chequePendiente,CtaChkChequePrestamo cheque,Integer chequeNegociable,
						String aConcatenar,HttpServletRequest request){
		
		
		ConDpaDetallePartida detalle;
		String nombre = "";
		String[] args = pars.split(";");
		
		System.out.println("Pars: "+args);
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			System.out.println(df.format(monto));
			monto = Double.valueOf(df.format(monto));
		} catch (Exception e1) {
			System.out.println("no se pudo redondear el numero");
		}
		
		Integer modulo = Integer.parseInt(args[0]);
		if(modulo==7) modulo=1;
		ConDpaDetallePartidaDAO detallePartidaDAO = new ConDpaDetallePartidaDAO(getSessionHibernate(request));
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		ConMxcModuloxCuentacontableDAO moduloxCuentacontableDAO = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		List<ConMxcModuloxCuentacontable> listaParametros = moduloxCuentacontableDAO.findByCxcParametrosUnion(pars);
		if(!listaParametros.isEmpty()){ // si hay al menos una configuracion se creara la partida, sino no.
			List<ConPcoPartidaContable> lista = partidaContableDAO.findByModuloFecha(new Date(),modulo);//buscamos si existe alguna partida de este modulo, en este dia
			Transaction tx = partidaContableDAO.getSession().beginTransaction();
			try{
				switch (modulo) {
				case 1:nombre = "Partida de movimientos contables del modulo Cuenta Corriente";
							break;
				case 2:nombre = "Partida de movimientos contables del modulo Inventario";
							break;
				case 3:nombre = "Partida de movimientos contables del modulo Orden de Compra";
							break;
				case 4:nombre = "Partida de movimientos contables del modulo Orden de Pago";
							break;
				case 5:nombre = "Partida de movimientos contables por cuota de afiliacion en el modulo Asociados";
							break;
				case 6:nombre = "Partida de movimientos contables por Distribucion de dividendos";
							break;
				case 7:nombre = "Partida de movimientos contables por Liquidacion de Asociados(Saldo a Favor)";
							break;
				case 8:nombre = "Partida de movimientos contables por Descuentos en Planilla (Retenciones de Mas)";
							break;
			}
				if(lista.isEmpty() && tpaId==1){//si no hay entonces se crea la partida
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(partidaContable);
				}
				if(tpaId!=1){// esto es por si la partida es de cheque
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(0,partidaContable);
				}
				ConPcoPartidaContable partidaCon = (ConPcoPartidaContable) lista.get(0);
				Iterator<ConMxcModuloxCuentacontable> iterador = listaParametros.iterator();
				while(iterador.hasNext()){
					ConMxcModuloxCuentacontable moduloParametros = iterador.next();
					//preguntamos si ya existe un detalle de esta partida, y con este concepto, para solo actualizar los valores
					//si la partida no existe entonces se debe crear
						detalle = new ConDpaDetallePartida();
						detalle.setConPcoPartidaContable(partidaCon);
						detalle.setConCueCuenta(moduloParametros.getConCueCuenta());
						detalle.setDpaOtroConcepto(moduloParametros.getCxaConceptoExtra() + aConcatenar);
						detalle.setConCpaConceptoPartida(null);
						if(moduloParametros.getCxcCargoAbono()==1){// el movimiento es un cargo
							System.out.println("Cargando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorDebe(monto);
							detalle.setDpaValorHaber(0.00);
							actualizarSaldos(moduloParametros.getConCueCuenta(), monto,0,request);
						}else{//el movimiento es un abono
							System.out.println("Abonando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorDebe(0.00);
							detalle.setDpaValorHaber(monto);
							actualizarSaldos(moduloParametros.getConCueCuenta(), 0, monto,request);
						}
						detallePartidaDAO.save(detalle);
					tx.commit();
				}
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				detallePartidaDAO.getSession().flush();
				detallePartidaDAO.getSession().clear();
				partidaContableDAO.getSession().flush();
				partidaContableDAO.getSession().clear();
				//partidaContableDAO.getSession().close();
			}
		}
	}
	
	//metodo para las partidas de las cuentas contables
	public void crearPartidaAutomaticaCC(String cuenta,Double monto,String tipo, String usuario, String concepto,HttpServletRequest request){
		ConDpaDetallePartida detalle;
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			System.out.println(df.format(monto));
			monto = Double.valueOf(df.format(monto));
		} catch (Exception e1) {
			System.out.println("no se pudo redondear el numero");
		}
		String nombre = "Partida de movimientos contables de Procesos Especiales";
		ConCueCuentaDAO conCuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		ConCueCuenta cuentaCon = new ConCueCuenta();
		//original//cuentaCon = (ConCueCuenta)conCuentaDAO.findByCueCodigoCuenta(cuenta).get(0);
		cuentaCon = (ConCueCuenta)conCuentaDAO.findById(Integer.parseInt(cuenta));
		ConDpaDetallePartidaDAO detallePartidaDAO = new ConDpaDetallePartidaDAO(getSessionHibernate(request));
		System.out.println("Cuenta contable: "+cuentaCon.getCueNombre());
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
			List<ConPcoPartidaContable> lista = partidaContableDAO.findByModuloFecha(new Date(),1);//buscamos si existe alguna partida de este modulo, en este dia
			Transaction tx = partidaContableDAO.getSession().beginTransaction();
			try{
				if(lista.isEmpty()){//si no hay entonces se crea la partida
					ConPcoPartidaContable partidaContable = crearPartida(nombre,1,usuario,1,null,null,null,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(partidaContable);
				}
				ConPcoPartidaContable partidaCon = (ConPcoPartidaContable) lista.get(0);
				List<ConDpaDetallePartida> listaDetalles;
					//preguntamos si ya existe un detalle de esta partida, y con este concepto, para solo actualizar los valores
					//si la partida no existe entonces se debe crear
				//listaDetalles = detallePartidaDAO.findByPartidaModulo((moduloParametros.getConCpaConceptoPartida() != null?moduloParametros.getConCpaConceptoPartida().getCpaId():null), partidaCon.getPcoId(),moduloParametros.getCxaConceptoExtra(),moduloParametros.getConCueCuenta().getCueId());
				listaDetalles = detallePartidaDAO.findByPartidaModulo(null, partidaCon.getPcoId(), concepto, cuentaCon.getCueId());
					if(listaDetalles.isEmpty()){
						detalle = new ConDpaDetallePartida();
						detalle.setConPcoPartidaContable(partidaCon);
						detalle.setConCueCuenta(cuentaCon);
						detalle.setConCpaConceptoPartida(null);
						CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
						if(tipo.equals("C")){//el movimiento es un cargo
							detalle.setDpaOtroConcepto(concepto);
							System.out.println("Cargando la cuenta "+cuentaCon.getCueId()+" con "+monto);
							detalle.setDpaValorDebe(monto);
							detalle.setDpaValorHaber(0.00);
							actualizarSaldos(cuentaCon, monto,0,request);
						}
						else{//el movimiento es un abono
							detalle.setDpaOtroConcepto(concepto);
							System.out.println("Abonando la cuenta "+cuentaCon.getCueId()+" con "+monto);
							detalle.setDpaValorDebe(0.00);
							detalle.setDpaValorHaber(monto);
							actualizarSaldos(cuentaCon, 0, monto,request);
						}
						detallePartidaDAO.save(detalle);
					}else{//actualizacion por si ya existe
						detalle = listaDetalles.get(0);
						if(listaDetalles.size() > 1 )System.out.println("Warn: mas detales que los sumados!");
						if(tipo.equals("C")){// el movimiento es un cargo
							System.out.println("Cargando la cuenta "+cuentaCon.getCueId()+" con "+monto);
							detalle.setDpaValorDebe(detalle.getDpaValorDebe()+monto);
							actualizarSaldos(cuentaCon, monto,0,request);
						}else{//el movimiento es un abono
							System.out.println("Abonando la cuenta "+cuentaCon.getCueId()+" con "+monto);
							detalle.setDpaValorHaber(detalle.getDpaValorHaber()+monto);
							actualizarSaldos(cuentaCon, 0, monto,request);
						}
						detallePartidaDAO.merge(detalle);
						tx.commit();
					}
					tx.commit();
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				detallePartidaDAO.getSession().flush();
				detallePartidaDAO.getSession().clear();
				partidaContableDAO.getSession().flush();
				partidaContableDAO.getSession().clear();
				//partidaContableDAO.getSession().close();
			}
	}
	
	//metodo para las partidas de las cuentas contables, no actualiza detalles, crea nuevos.
	public void crearPartidaAutomaticaCC2(String cuenta,Double monto,String tipo, String usuario, String concepto,HttpServletRequest request){

		ConDpaDetallePartida detalle;
		String nombre = "Partida de movimientos contables de Procesos Especiales";
		ConCueCuentaDAO conCuentaDAO = new ConCueCuentaDAO(getSessionHibernate(request));
		ConCueCuenta cuentaCon = new ConCueCuenta();
		//original//cuentaCon = (ConCueCuenta)conCuentaDAO.findByCueCodigoCuenta(cuenta).get(0);
		cuentaCon = (ConCueCuenta)conCuentaDAO.findById(Integer.parseInt(cuenta));
		ConDpaDetallePartidaDAO detallePartidaDAO = new ConDpaDetallePartidaDAO(getSessionHibernate(request));
		System.out.println("Cuenta contable: "+cuentaCon.getCueNombre());
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			System.out.println(df.format(monto));
			monto = Double.valueOf(df.format(monto));
		} catch (Exception e1) {
			System.out.println("no se pudo redondear el numero");
		}
			List<ConPcoPartidaContable> lista = partidaContableDAO.findByModuloFecha(new Date(),1);//buscamos si existe alguna partida de este modulo, en este dia
			Transaction tx = partidaContableDAO.getSession().beginTransaction();
			try{
				if(lista.isEmpty()){//si no hay entonces se crea la partida
					ConPcoPartidaContable partidaContable = crearPartida(nombre,1,usuario,1,null,null,null,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(partidaContable);
				}
				ConPcoPartidaContable partidaCon = (ConPcoPartidaContable) lista.get(0);
				List<ConDpaDetallePartida> listaDetalles;
					//preguntamos si ya existe un detalle de esta partida, y con este concepto, para solo actualizar los valores
					//si la partida no existe entonces se debe crear
				//listaDetalles = detallePartidaDAO.findByPartidaModulo((moduloParametros.getConCpaConceptoPartida() != null?moduloParametros.getConCpaConceptoPartida().getCpaId():null), partidaCon.getPcoId(),moduloParametros.getCxaConceptoExtra(),moduloParametros.getConCueCuenta().getCueId());
				listaDetalles = detallePartidaDAO.findByPartidaModulo(null, partidaCon.getPcoId(), concepto, cuentaCon.getCueId());
					if(true){
						detalle = new ConDpaDetallePartida();
						detalle.setConPcoPartidaContable(partidaCon);
						detalle.setConCueCuenta(cuentaCon);
						detalle.setConCpaConceptoPartida(null);
						CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
						if(tipo.equals("C")){//el movimiento es un cargo
							detalle.setDpaOtroConcepto(concepto);
							System.out.println("Cargando la cuenta "+cuentaCon.getCueId()+" con "+monto);
							detalle.setDpaValorDebe(monto);
							detalle.setDpaValorHaber(0.00);
							actualizarSaldos(cuentaCon, monto,0,request);
						}
						else{//el movimiento es un abono
							detalle.setDpaOtroConcepto(concepto);
							System.out.println("Abonando la cuenta "+cuentaCon.getCueId()+" con "+monto);
							detalle.setDpaValorDebe(0.00);
							detalle.setDpaValorHaber(monto);
							actualizarSaldos(cuentaCon, 0, monto,request);
						}
						detallePartidaDAO.save(detalle);
					}
					tx.commit();
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				detallePartidaDAO.getSession().flush();
				detallePartidaDAO.getSession().clear();
				partidaContableDAO.getSession().flush();
				partidaContableDAO.getSession().clear();
				//partidaContableDAO.getSession().close();
			}
	
	}
	
	//Metodo encargado de crear las partidas automaticas para las ordenes de pago
	public void crearPartidaAutomaticaOrdenPago(String pars,Double monto,Double montoDesc, String usuario,int tpaId,
			Integer chequePendiente,CtaChkChequePrestamo cheque,Integer chequeNegociable,HttpServletRequest request){
		ConDpaDetallePartida detalle;
		String nombre = "";
		String[] args = pars.split(";");
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			System.out.println(df.format(monto));
			monto = Double.valueOf(df.format(monto));
			montoDesc = Double.valueOf(df.format(montoDesc));
		} catch (Exception e1) {
			System.out.println("no se pudo redondear el numero");
		}
		Integer modulo = Integer.parseInt(args[0]);
		ConDpaDetallePartidaDAO detallePartidaDAO = new ConDpaDetallePartidaDAO(getSessionHibernate(request));
		ConPcoPartidaContableDAO partidaContableDAO = new ConPcoPartidaContableDAO(getSessionHibernate(request));
		ConMxcModuloxCuentacontableDAO moduloxCuentacontableDAO = new ConMxcModuloxCuentacontableDAO(getSessionHibernate(request));
		List<ConMxcModuloxCuentacontable> listaParametros = moduloxCuentacontableDAO.findByCxcParametrosUnion(pars);
		if(!listaParametros.isEmpty()){ // si hay al menos una configuracion se creara la partida, sino no.
			List<ConPcoPartidaContable> lista = partidaContableDAO.findByModuloFechaTipo(new Date(),modulo,tpaId);//buscamos si existe alguna partida de este modulo, en este dia
			
			Transaction tx = partidaContableDAO.getSession().beginTransaction();
			try{
				switch (modulo) {
				case 1:nombre = "Partida de movimientos contables del modulo Cuenta Corriente";
							break;
				case 2:nombre = "Partida de movimientos contables del modulo Inventario";
							break;
				case 3:nombre = "Partida de movimientos contables del modulo Orden de Compra";
							break;
				case 4:nombre = "Partida de movimientos contables del modulo Orden de Pago";
							break;
				case 5:nombre = "Partida de movimientos contables por cuota de afiliacion en el modulo Asociados";
							break;
				case 6:nombre = "Partida de movimientos contables por Distribucion de dividendos";
							break;
				case 7:nombre = "Partida de movimientos contables por Liquidacion de Asociados(Saldo a Favor)";
							break;
				case 8:nombre = "Partida de movimientos contables por Descuentos en Planilla (Retenciones de Mas)";
							break;
				case 9:nombre = "Partida de movimientos contables por Multa de Ahorros";
							break;
				case 10:nombre = "Partida de movimientos contables por Descuentos a socios retirados";
				break;
	
			}
				
				if(lista.isEmpty() && tpaId==1){//si no hay entonces se crea la partida
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(partidaContable);
				}				
				if(tpaId==2 && args[2].equals("0")){// esto es por si la partida es de cheque
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(0,partidaContable);
				}
				if(lista.isEmpty() && tpaId==3){// Partida de descuento en planilla, si no existe debemos crearla.
					ConPcoPartidaContable partidaContable = crearPartida(nombre,modulo,usuario,tpaId,chequePendiente,cheque,chequeNegociable,request);
					partidaContableDAO.save(partidaContable);
					tx.commit();
					lista.add(0,partidaContable);
				}

				ConPcoPartidaContable partidaCon = (ConPcoPartidaContable) lista.get(0);
				Iterator<ConMxcModuloxCuentacontable> iterador = listaParametros.iterator();
				List<ConDpaDetallePartida> listaDetalles;
				while(iterador.hasNext()){
					ConMxcModuloxCuentacontable moduloParametros = iterador.next();
					//preguntamos si ya existe un detalle de esta partida, y con este concepto, para solo actualizar los valores
					//si la partida no existe entonces se debe crear
					listaDetalles = detallePartidaDAO.findByPartidaModulo((moduloParametros.getConCpaConceptoPartida() != null?moduloParametros.getConCpaConceptoPartida().getCpaId():null), partidaCon.getPcoId(),moduloParametros.getCxaConceptoExtra(),moduloParametros.getConCueCuenta().getCueId());
					if(listaDetalles.isEmpty()){
						detalle = new ConDpaDetallePartida();
						detalle.setConPcoPartidaContable(partidaCon);
						detalle.setConCueCuenta(moduloParametros.getConCueCuenta());
						if(modulo == 1){
							detalle.setConCpaConceptoPartida(null);
							CtaTtrTipoTransaccionDAO tipoTransaccionDAO = new CtaTtrTipoTransaccionDAO(getSessionHibernate(request));
							detalle.setDpaOtroConcepto(tipoTransaccionDAO.findById(Integer.parseInt(args[3])).getTtrNombre());
						}else{
							if(moduloParametros.getConCpaConceptoPartida() == null){
									detalle.setConCpaConceptoPartida(null);
									detalle.setDpaOtroConcepto(moduloParametros.getCxaConceptoExtra());
							}else{
									detalle.setConCpaConceptoPartida(moduloParametros.getConCpaConceptoPartida());
							}
						}
						if(moduloParametros.getCxcCargoAbono()==1){// el movimiento es un cargo
							System.out.println("Cargando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
							detalle.setDpaValorDebe(monto);
							detalle.setDpaValorHaber(0.00);
							actualizarSaldos(moduloParametros.getConCueCuenta(), monto,0,request);
						}else{//el movimiento es un abono
							System.out.println("Abonando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+montoDesc);
							detalle.setDpaValorDebe(0.00);
							detalle.setDpaValorHaber(montoDesc);
							actualizarSaldos(moduloParametros.getConCueCuenta(), 0, montoDesc,request);
						}
						detallePartidaDAO.save(detalle);
					}else{//actualizacion por si ya existe
						detalle = listaDetalles.get(0);
						if(moduloParametros.getCxcCargoAbono()==1){// el movimiento es un cargo
							System.out.println("Cargando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+monto);
								detalle.setDpaValorDebe(detalle.getDpaValorDebe()+monto);
								actualizarSaldos(moduloParametros.getConCueCuenta(), monto,0,request);
						}else{//el movimiento es un abono
							System.out.println("Abonando la cuenta "+moduloParametros.getConCueCuenta().getCueId()+" con "+montoDesc);
							detalle.setDpaValorHaber(detalle.getDpaValorHaber()+montoDesc);
								actualizarSaldos(moduloParametros.getConCueCuenta(), 0, montoDesc,request);
						}
						detallePartidaDAO.merge(detalle);
					}
					tx.commit();
				}
			}catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}finally{
				detallePartidaDAO.getSession().flush();
				detallePartidaDAO.getSession().clear();
				partidaContableDAO.getSession().flush();
				partidaContableDAO.getSession().clear();
				//partidaContableDAO.getSession().close();
			}
		}
	}	
	
	public Session getSessionHibernate(HttpServletRequest request) {
		return (Session) request.getAttribute(FiltroOpenSession.KEY_SESSION);
	}
}