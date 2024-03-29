/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.procesosEspeciales.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Transaction;

import com.cetia.sicaco.cuentaCorriente.struts.action.CuentaAhorroAction;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorroDAO;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorro;
import com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorroDAO;
import com.cetia.sicaco.hibernate.CtaTahTipoAhorroDAO;
import com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado;
import com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociadoDAO;
import com.cetia.sicaco.procesosEspeciales.struts.form.CapitalizacionAhorroForm;
import com.cetia.sicaco.struts.DMLAction;
import com.cetia.sicaco.struts.PartidaAutomatica;

/** 
 * MyEclipse Struts
 * Creation date: 12-20-2008
 * 
 * XDoclet definition:
 * @struts.action path="/capitalizacionAhorro" name="capitalizacionAhorroForm" input="/form/capitalizacionAhorro.jsp" parameter="accion" scope="request" validate="true"
 */
public class CapitalizacionAhorroAction extends DMLAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	private static final String TYPE_MENSAJE_EXITO = "exito";
	private static final String TYPE_MENSAJE_ERROR = "error";
	
	
	public ActionForward ejecutarCapitalizacionAhorro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CapitalizacionAhorroForm capitalizacionAhorroForm = (CapitalizacionAhorroForm) form;// TODO Auto-generated method stub
		//proceso capitalizacion
		//CuentaAhorroForm ahorroForm = new CuentaAhorroForm();
		CuentaAhorroAction ahorroAction = new CuentaAhorroAction();
		String usuario = capitalizacionAhorroForm.getUsuarioConectado().getNombreUsuario();
		
		Date fechaMovs = capitalizacionAhorroForm.getFechaCapitalizacionD();
		Date fechaUsuarioHaceMov = new Date();
		CtaTtrTipoTransaccion tipoTransac = new CtaTtrTipoTransaccion();
		tipoTransac.setTtrId(16);
		String parametrosPart;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = shortSdf.format(fechaMovs);
		fecha = fecha+" 23:00:00";
		System.out.println(fecha);
		try {
			fechaMovs = sdf.parse(fecha);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CtaTahTipoAhorroDAO tipoAhorroDAO = new CtaTahTipoAhorroDAO(getSessionHibernate(request));
		CtaCasCuentaAsociadoDAO cuentascDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		
		CtaCahCuentaAhorroDAO ahorroDAO = new CtaCahCuentaAhorroDAO(getSessionHibernate(request));
		CtaMxaMovimientoAhorroDAO movAhorroDAO = new CtaMxaMovimientoAhorroDAO(getSessionHibernate(request));
		CtaTxaTransaccionxcuentaAsociadoDAO transaccionDAO = new CtaTxaTransaccionxcuentaAsociadoDAO(getSessionHibernate(request));
		ArrayList<?> listaAhorrosACapitalizar = (ArrayList<?>) ahorroDAO.findAllCuAhorroCapitalizacion();
		/*	1. actualizar saldos e intereses; 
			2. Crear movimiento en transaccion y en tabla movimientos ahorro 
			3. crear mov en tabla saldos por mes activo
		*/
		Transaction tx = ahorroDAO.getSession().beginTransaction();
		try{
			Iterator it = listaAhorrosACapitalizar.iterator();
			Double inteTransaccion = new Double(0.0);
			Double interesACapitalizar = new Double(0.0);
			while(it.hasNext()){
				CtaCahCuentaAhorro cAhorro = new CtaCahCuentaAhorro();
				Object[] item = (Object[])it.next();
				cAhorro.setCahId(item[0].toString());
				cAhorro.setCahCuota(new Double(item[3].toString()));
				cAhorro.setCtaTahTipoAhorro(tipoAhorroDAO.findById(new Integer(item[4].toString())));
				cAhorro.setCahSaldoActual(new Double(item[1].toString()));
				cAhorro.setCahInteresAcumulado(new Double(item[2].toString()));
				//sumamos el interes de la transaccion
				//ahorroForm.setCtaCahCuentaAhorroH(cAhorro);
				//FIXME se debera poner la fecha a todos los calculos de transaccion.
				
				inteTransaccion = ahorroAction.calculoInteresTransaccion(cAhorro.getCahId(),fechaMovs,request);
				interesACapitalizar = inteTransaccion + cAhorro.getCahInteresAcumulado();
				cAhorro.setCahInteresAcumulado(0.0);
				double saldoActual = cAhorro.getCahSaldoActual();
				
				
						cAhorro.setCahSaldoActual(interesACapitalizar + cAhorro.getCahSaldoActual());
						
						ahorroDAO.merge(cAhorro);
				if(saldoActual> 0){
						//definimos movimientos
						CtaMxaMovimientoAhorro movAhorro = new CtaMxaMovimientoAhorro();
						CtaTxaTransaccionxcuentaAsociado transaccionAh = new CtaTxaTransaccionxcuentaAsociado();
						
						//transaccion sets
						CtaCasCuentaAsociado cuAsociado = cuentascDAO.findById(new Long(item[5].toString()));
						//cuAsociado.setCasCuenta(new Long(item[5].toString()));
						transaccionAh.setTxaFecha(fechaMovs);
						transaccionAh.setCtaCasCuentaAsociado(cuAsociado);
						transaccionAh.setTxaEstado("E");
						transaccionAh.setTxaMonto(interesACapitalizar);
						transaccionAh.setCtaTtrTipoTransaccion(tipoTransac);
						
						
						//auditoria en transaccion
						transaccionAh.setAudFechaCreacion(fechaUsuarioHaceMov);
						transaccionAh.setAudFechaModificacion(fechaUsuarioHaceMov);
						transaccionAh.setAudUsuarioCreacion(capitalizacionAhorroForm.getUsuarioConectado().getNombreUsuario());
						transaccionAh.setAudUsuarioModificacion(capitalizacionAhorroForm.getUsuarioConectado().getNombreUsuario());
						
						transaccionDAO.save(transaccionAh);
						
						//movimiento en ahorro
						movAhorro.setCtaCahCuentaAhorro(cAhorro);
						movAhorro.setMxaFecha(fechaMovs);
						movAhorro.setCtaTxaTransaccionxcuentaAsociado(transaccionAh);
						movAhorro.setMxaMonto(interesACapitalizar);
						movAhorro.setMxaSaldo(cAhorro.getCahSaldoActual());
						movAhorro.setMxaInteresTran(0.0);
						
						movAhorro.setAudFechaCreacion(fechaUsuarioHaceMov);
						movAhorro.setAudFechaModificacion(fechaUsuarioHaceMov);
						movAhorro.setAudUsuarioCreacion(capitalizacionAhorroForm.getUsuarioConectado().getNombreUsuario());
						movAhorro.setAudUsuarioModificacion(capitalizacionAhorroForm.getUsuarioConectado().getNombreUsuario());
						System.out.println("Interes a capitalizar=------------------------->"+interesACapitalizar);
	//					System.out.println("Interes inteTr=-------------------------------->"+inteTransaccion);
						System.out.println("Cuenta: " +cuAsociado.getCtaCahCuentaAhorro().getCahId());
						movAhorroDAO.save(movAhorro);
						
						//relacion monto a ahorro
						parametrosPart = "1;2;"+cAhorro.getCtaTahTipoAhorro().getTahId()+";"+tipoTransac.getTtrId()+";";
						PartidaAutomatica partidaAutomatica = new PartidaAutomatica();
						partidaAutomatica.crearPartidaAutomatica(parametrosPart + "1"+";-1", interesACapitalizar, 
								usuario, 1, null, null, null,request);
					}
					tx.commit();
				}
				
			
		}catch(Exception e){
			
			tx.rollback();
			e.printStackTrace();
		}finally{
			 ahorroDAO.getSession().flush();
			ahorroDAO.getSession().clear();
		}
		String mensaje = getResources(request).getMessage("errors.capitalizacionHechaConExito");
		
		enviarMensajeXML(response, mensaje, TYPE_MENSAJE_EXITO);
				
		return null;
		//return mapping.findForward("lista");
	}
	public void enviarMensajeXML(HttpServletResponse response, String mensaje,
			String typeMensaje) {
		response.setContentType("txt/xml");
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<mensaje>");
			out.print("\t<class>");
			out.print(typeMensaje);
			out.println("</class>");
			out.print("\t<texto>");
			out.print(mensaje);
			out.println("</texto>");
			out.println("</mensaje>");
			out.flush();
			out.println();
		} catch (IOException e) {
			log.error("",e);
		}
	}
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cmd.capitalizacionAhorro.capitalizar", "ejecutarCapitalizacionAhorro");
		return map;
	}
	
	
}