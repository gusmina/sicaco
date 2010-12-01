package com.cetia.sicaco.cuentaCorriente.struts.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Transaction;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.cuentaCorriente.struts.form.AplicarDescuentosPrestamoForm;
import com.cetia.sicaco.hibernate.CtaCahCuentaAhorro;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociado;
import com.cetia.sicaco.hibernate.CtaCasCuentaAsociadoDAO;
import com.cetia.sicaco.hibernate.CtaDxpDescuentosPrestamo;
import com.cetia.sicaco.hibernate.CtaDxpDescuentosPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo;
import com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamoDAO;
import com.cetia.sicaco.hibernate.CtaRxpRetencionPrestamo;
import com.cetia.sicaco.hibernate.CtaRxpRetencionPrestamoDAO;
import com.cetia.sicaco.hibernate.CtaSegSeguros;
import com.cetia.sicaco.hibernate.CtrParParametrosDAO;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;
import com.mad.utilidades.IntereseYMora;

public class AplicarDescuentosPrestamoAction extends DMLAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AplicarDescuentosPrestamoForm descuentosForm = (AplicarDescuentosPrestamoForm) form;
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaCasCuentaAsociado cuentaAsociado = cuentaAsociadoDAO.findById(descuentosForm.getCasCuenta());
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		Double iva = parametrosDAO.findById("IVA").getParValorNumber(); 
			
		request.setAttribute("nombreCompleto", 
				cuentaAsociado.getCtaAscAsociado().getSecPerPersona().getPerPrimerNombre()+" "+
				cuentaAsociado.getCtaAscAsociado().getSecPerPersona().getPerSegundoNombre()+" "+
				cuentaAsociado.getCtaAscAsociado().getSecPerPersona().getPerTercerNombre()+" "+
				cuentaAsociado.getCtaAscAsociado().getSecPerPersona().getPerPrimerApellido()+" "+
				cuentaAsociado.getCtaAscAsociado().getSecPerPersona().getPerSegundoApellido()+" "+
				cuentaAsociado.getCtaAscAsociado().getSecPerPersona().getPerApellidoCasada()
		);
		request.setAttribute("cuentaAsociado", cuentaAsociado);
		request.setAttribute("iva", iva);
		request.getSession().setAttribute("listaRetenciones",new HashMap<Long, CtaRxpRetencionPrestamo>() );

		if(cuentaAsociado.getCasRefinanciado() != null){
			CtaCasCuentaAsociado cuentaRef = cuentaAsociadoDAO.findById(cuentaAsociado.getCasRefinanciado());
			CtaPrePrestamo prestamo= cuentaRef.getCtaPrePrestamo();
			
			//Calcular saldo con los intereses pendientes
			IntereseYMora iYm = new IntereseYMora();
			CtaMxpMovimientoPrestamoDAO mxpDao = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
			CtaMxpMovimientoPrestamo mxpAnt = mxpDao.findUltimoMovimiento(cuentaRef.getCtaPrePrestamo().getPreId());
			if(mxpAnt == null || mxpAnt.getMxpId() == null){
				mxpAnt = new CtaMxpMovimientoPrestamo();
			}
			iYm = iYm.actualizaInteres(mxpAnt, cuentaRef.getCtaPrePrestamo(), cuentaRef, new Date(),request);
			DecimalFormat df = new DecimalFormat("0.00");
			Double saldoConInteres = cuentaRef.getCtaPrePrestamo().getPreSaldoActualT() + iYm.getAcumulado() + iYm.getPendiente();
			Double saldoFormateado = new Double(df.format(saldoConInteres));
			
			cuentaRef.setCtaPrePrestamo(prestamo);
			request.setAttribute("refinanciando", saldoFormateado);
			request.setAttribute("ref", 1);
			request.setAttribute("cuentaRef",cuentaRef);
		}else request.setAttribute("refinanciando","0.00"); 
		
		request.setAttribute(Constantes.ACCION_KEY, "/aplicarDescuentos");
		return mapping.findForward("lista");
	}

	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("cancelar");
	}
	
	public ActionForward aplicarDescuentos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AplicarDescuentosPrestamoForm apliPrestamoForm = (AplicarDescuentosPrestamoForm) form;
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSessionHibernate(request));
		Transaction tx = cuentaAsociadoDAO.getSession().beginTransaction();
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSessionHibernate(request));
		
		CtaCasCuentaAsociado cuentaAsociado = cuentaAsociadoDAO.findById(apliPrestamoForm.getCasCuenta());
		CtaPrePrestamo prestamo = cuentaAsociado.getCtaPrePrestamo();
		Double liquido=prestamo.getPreMontoSolicitado();
		//Restando las otras deducciones y el iva
		liquido = liquido - prestamo.getPreOtrasDeducciones() - prestamo.getPreIvaDeducciones();
		
		//Verificando que el prestamo este refinanciando otro
		if(cuentaAsociado.getCasRefinanciado() != null){
			CtaCasCuentaAsociado cuentaRef = cuentaAsociadoDAO.findById(cuentaAsociado.getCasRefinanciado());
			
			//Restando al liquido el préstamo a refinanciar
			liquido = liquido - cuentaRef.getCtaPrePrestamo().getPreSaldoActualT() - cuentaRef.getCtaPrePrestamo().getPreInteresAcumulado() - cuentaRef.getCtaPrePrestamo().getPrePendMov();
		}
		
		//----------------------------Almacenando las retenciones--------------------------------
		CtaRxpRetencionPrestamoDAO retencionDAO = new CtaRxpRetencionPrestamoDAO(getSessionHibernate(request));
		//Obtengo las retenciones antiguas almacenadas en la base de datos y las elimino
		
		List<CtaRxpRetencionPrestamo> retenciones = retencionDAO.findByProperty("ctaPrePrestamo.preId",prestamo.getPreId());
		Iterator iOldRet = retenciones.iterator();
		
		while(iOldRet.hasNext()){
			CtaRxpRetencionPrestamo retencion = (CtaRxpRetencionPrestamo) iOldRet.next();
			retencionDAO.delete(retencion);
		}
		//Obtengo las retenciones nuevas, las almaceno y actualizo el líquido a recibir
		HashMap<Long, CtaRxpRetencionPrestamo> mapa = (HashMap<Long, CtaRxpRetencionPrestamo>)request.getSession().getAttribute("listaRetenciones");
		Iterator<Long> iNewRet = mapa.keySet().iterator();
		long i;
		while (iNewRet.hasNext()) {
			i = iNewRet.next().longValue();
			CtaRxpRetencionPrestamo retencion = (CtaRxpRetencionPrestamo) mapa.get(i);
			retencion.setRxpId(retencionDAO.nextId());
			retencion.setCtaPrePrestamo(prestamo);
			retencionDAO.save(retencion);
			liquido = liquido - retencion.getRxpMonto();
		}
		
		//--------------------------Actualizando las aportaciones----------------------------------
		liquido = liquido - apliPrestamoForm.getAportaciones();
		prestamo.setPreAportaciones(apliPrestamoForm.getAportaciones());
		
		
		//-----------------------Eliminando los descuentos anteriores------------------------------
		CtaDxpDescuentosPrestamoDAO descPrestDAO = new CtaDxpDescuentosPrestamoDAO(getSessionHibernate(request));
		List listaDescPrest = (List) descPrestDAO.findByProperty("ctaPrePrestamoByPreId.preId", cuentaAsociado.getCtaPrePrestamo().getPreId());
		Iterator iDescPrest = listaDescPrest.iterator();
		while(iDescPrest.hasNext()){
			CtaDxpDescuentosPrestamo descuento = (CtaDxpDescuentosPrestamo) iDescPrest.next();
			descPrestDAO.delete(descuento);
		}
		//------------------------Actualizo los abonos a prestamos---------------------------------		

		if (apliPrestamoForm.getPosicionPre() != null) {
			int size = apliPrestamoForm.getPosicionPre().length;
			int idp = 0;
			while (idp < size) {
				CtaDxpDescuentosPrestamo descuento = new CtaDxpDescuentosPrestamo();
				descuento.setCasCuenta(apliPrestamoForm.getPosicionPre()[idp]);
				CtaCasCuentaAsociado cuentaAsocPrest = cuentaAsociadoDAO.findById(apliPrestamoForm.getPosicionPre()[idp]);
				
				descuento.setCtaCahCuentaAhorro(null);
				descuento.setCtaSegSeguros(null);
				descuento.setCtaPrePrestamoByPreId2(cuentaAsocPrest.getCtaPrePrestamo());
				descuento.setCtaPrePrestamoByPreId(cuentaAsociado.getCtaPrePrestamo());
				descuento.setDxpInteresPagado(0.00);
				if(apliPrestamoForm.getDigitadoPre()[idp] == 0) descuento.setDxpMonto(null);
				else descuento.setDxpMonto(apliPrestamoForm.getPrestamos()[idp]);
				
				descuento.setDxpId(descPrestDAO.nextId());
				
				descPrestDAO.save(descuento);
				
				//Calcular saldo con los intereses pendientes
				IntereseYMora iYm = new IntereseYMora();
				CtaMxpMovimientoPrestamoDAO mxpDao = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
				CtaMxpMovimientoPrestamo mxpAnt = mxpDao.findUltimoMovimiento(cuentaAsocPrest.getCtaPrePrestamo().getPreId());
				if(mxpAnt == null || mxpAnt.getMxpId() == null){
					mxpAnt = new CtaMxpMovimientoPrestamo();
				}
				iYm = iYm.actualizaInteres(mxpAnt, cuentaAsocPrest.getCtaPrePrestamo(), 
						cuentaAsocPrest, new Date(),request);
				Double saldoConInteres = cuentaAsocPrest.getCtaPrePrestamo().getPreSaldoActualT() + iYm.getAcumulado() + iYm.getPendiente();
				
				if(descuento.getDxpMonto()==null) liquido = liquido - saldoConInteres;
				else liquido = liquido - descuento.getDxpMonto();
				idp++;
			}
		}
		//------------------------Actualizo los abonos de ahorros---------------------------------

		if (apliPrestamoForm.getPosicionAho() != null) {
			int size = apliPrestamoForm.getPosicionAho().length;
			int idp = 0;
			while (idp < size) {
				CtaDxpDescuentosPrestamo descuento = new CtaDxpDescuentosPrestamo();
				descuento.setCasCuenta(apliPrestamoForm.getPosicionAho()[idp]);
				CtaCasCuentaAsociado cuentaAsocAho = cuentaAsociadoDAO.findById(apliPrestamoForm.getPosicionAho()[idp]);
				
				descuento.setCtaCahCuentaAhorro(cuentaAsocAho.getCtaCahCuentaAhorro());
				descuento.setCtaSegSeguros(null);
				descuento.setCtaPrePrestamoByPreId2(null);
				descuento.setCtaPrePrestamoByPreId(cuentaAsociado.getCtaPrePrestamo());
				descuento.setDxpMonto(apliPrestamoForm.getAhorros()[idp]);
				descuento.setDxpId(descPrestDAO.nextId());
				descuento.setDxpInteresPagado(0.00);
				
				descPrestDAO.save(descuento);
				liquido = liquido - descuento.getDxpMonto();
				idp++;
			}
		}
		//------------------------Actualizo los abonos de seguros---------------------------------

		if (apliPrestamoForm.getPosicionSeg() != null) {
			int size = apliPrestamoForm.getPosicionSeg().length;
			int idp = 0;
			while (idp < size) {
				CtaDxpDescuentosPrestamo descuento = new CtaDxpDescuentosPrestamo();
				descuento.setCasCuenta(apliPrestamoForm.getPosicionSeg()[idp]);
				CtaCasCuentaAsociado cuentaAsocSeg = cuentaAsociadoDAO.findById(apliPrestamoForm.getPosicionSeg()[idp]);
				
				descuento.setCtaCahCuentaAhorro(null);
				descuento.setCtaSegSeguros(cuentaAsocSeg.getCtaSegSeguros());
				descuento.setCtaPrePrestamoByPreId2(null);
				descuento.setCtaPrePrestamoByPreId(cuentaAsociado.getCtaPrePrestamo());
				descuento.setDxpInteresPagado(0.00);
				
				if(apliPrestamoForm.getDigitadoSeg()[idp]==0) descuento.setDxpMonto(null); 
				else descuento.setDxpMonto(apliPrestamoForm.getSeguros()[idp]);
				
				descuento.setDxpId(descPrestDAO.nextId());
				
				descPrestDAO.save(descuento);
				if(descuento.getDxpMonto()==null) liquido = liquido - cuentaAsocSeg.getCtaSegSeguros().getSegSaldoActual(); 
				else liquido = liquido - descuento.getDxpMonto();
				idp++;
			}
		}		
		
		//Actualizo el préstamo con el nuevo líquido a recibir resultante de restar las otras retenciones,
		//las aportaciones y ...
		DecimalFormat df = new DecimalFormat("0.00");
		prestamo.setPreLiquidoARecibir(new Double(df.format(liquido)));
		
		prestamoDAO.merge(prestamo);
		
		tx.commit();
		cuentaAsociadoDAO.getSession().flush();
		cuentaAsociadoDAO.getSession().clear();
		
		return mapping.findForward("cancelar");
	}	
	
	public ActionForward cargarLineasDeAhorro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AplicarDescuentosPrestamoForm apliPrestamoForm = (AplicarDescuentosPrestamoForm) form;
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));		
		CtaCasCuentaAsociado cuentaAsocPre = cuentaAsociadoDAO.findById(apliPrestamoForm.getCasCuenta());
		CtaDxpDescuentosPrestamoDAO descPrestDAO = new CtaDxpDescuentosPrestamoDAO(getSessionHibernate(request));
		
		String texto = "";
		DecimalFormat df = new DecimalFormat("0.00");
		HtmlBuilder htmlBuilder = new HtmlBuilder();

		//Obteniendo los abonos asignados a los ahorros del asociado
		List listaDescAho = (List) descPrestDAO.findByPreIdAndTipoCuenta(cuentaAsocPre.getCtaPrePrestamo().getPreId(), "B");		
		
		List listaAhorros = (List) cuentaAsociadoDAO.findByAscAndTipoCuenta2(cuentaAsocPre.getCtaAscAsociado().getAscId(),"B");//Para obtener las cuentas de ahorro activas del asociado
		Iterator iAho = listaAhorros.iterator();		
		int size = listaAhorros.size();
		int i=1;

		if(size>0){
			htmlBuilder.table(0).id("hor-zebra").align("left").border("0").close();
			htmlBuilder.thead(0);
			htmlBuilder.th(0).close().nbsp().thEnd();
			htmlBuilder.th(0).close().append("N&uacute;mero de Cuenta").thEnd();
			htmlBuilder.th(0).close().append("L&iacute;nea de Ahorro").thEnd();
			htmlBuilder.th(0).close().append("Tipo de Ahorro").thEnd();
			htmlBuilder.th(0).close().append("Saldo Actual").thEnd();
			htmlBuilder.th(0).close().append("Abono").thEnd();
			htmlBuilder.theadEnd(0);
			htmlBuilder.append("<tfoot style=\"border-top: 1px dashed #69c;\"><tr><td colspan=\"6\" align=\"right\">"+
					//"<img src=\"../imagenes/opcionesTabla/reload.png\" style=\"cursor: pointer;\" alt=\"Actualizar L&iacute;quido\" id=\"actualizarLAho\"/>"+
					"</td></tr></tfoot>");
			while(iAho.hasNext()){
				CtaCasCuentaAsociado cuentaAsocAho = (CtaCasCuentaAsociado) iAho.next();
				CtaCahCuentaAhorro cuentaAho = cuentaAsocAho.getCtaCahCuentaAhorro();
				if(cuentaAho.getCahId().substring(0, 1).equals("B")){
					
					if(i%2 == 0) htmlBuilder.tr(0).close();
					else htmlBuilder.tr(0).append(" class=\"odd\"").close();
					
					//Verifica si el ahorro tiene abono
					Iterator<CtaDxpDescuentosPrestamo> iDesc = listaDescAho.iterator();
					int j=0,encontrado=0;
					while(iDesc.hasNext()){
						CtaDxpDescuentosPrestamo desc = (CtaDxpDescuentosPrestamo) iDesc.next();
						if(desc.getCtaCahCuentaAhorro().getCahId() == cuentaAho.getCahId()){					
							htmlBuilder.td(0).close().input().type("checkbox").checked().name("posicionAho").append("class=\"posicionAho\"").onclick("calculaLiquidoARecibir();").value(cuentaAsocAho.getCasCuenta().toString()).tdEnd();					
							htmlBuilder.td(0).close().append(cuentaAho.getCahId()).tdEnd();
							htmlBuilder.td(0).close().append(cuentaAho.getCtaTahTipoAhorro().getCtaLahLineaAhorro().getLahNombre()).tdEnd();
							htmlBuilder.td(0).close().append(cuentaAho.getCtaTahTipoAhorro().getTahDescripcion()).tdEnd();
							htmlBuilder.td(0).close().append(df.format(cuentaAho.getCahSaldoActual())).tdEnd();
							htmlBuilder.td(0).close().input().type("text").value(desc.getDxpMonto().toString()).id(cuentaAsocAho.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocAho.getCasCuenta()+"').val(),'"+cuentaAsocAho.getCasCuenta()+"');soloNumerosAho($('#"+cuentaAsocAho.getCasCuenta()+"').val(),'"+cuentaAsocAho.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);
							encontrado=1;
							break;
						}
						j++;
					}
					if(encontrado!=1){
						htmlBuilder.td(0).close().input().type("checkbox").name("posicionAho").append("class=\"posicionAho\"").onclick("calculaLiquidoARecibir();").value(cuentaAsocAho.getCasCuenta().toString()).tdEnd();					
						htmlBuilder.td(0).close().append(cuentaAho.getCahId()).tdEnd();
						htmlBuilder.td(0).close().append(cuentaAho.getCtaTahTipoAhorro().getCtaLahLineaAhorro().getLahNombre()).tdEnd();
						htmlBuilder.td(0).close().append(cuentaAho.getCtaTahTipoAhorro().getTahDescripcion()).tdEnd();
						htmlBuilder.td(0).close().append(df.format(cuentaAho.getCahSaldoActual())).tdEnd();
						htmlBuilder.td(0).close().input().type("text").id(cuentaAsocAho.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocAho.getCasCuenta()+"').val(),'"+cuentaAsocAho.getCasCuenta()+"');soloNumerosAho($('#"+cuentaAsocAho.getCasCuenta()+"').val(),'"+cuentaAsocAho.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);						
					}							
					i++;
				}			
			}
			htmlBuilder.tableEnd(1);
		}else{
			htmlBuilder.append("<label style=\"color: red;text-transform: none\">"+getResources(request,"aux").getMessage("cmd.aho.inactivas")+"</label>");
		}

		texto = htmlBuilder.toString();
		try{
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
	
	public ActionForward cargarTiposDeSeguro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AplicarDescuentosPrestamoForm apliPrestamoForm = (AplicarDescuentosPrestamoForm) form;
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaDxpDescuentosPrestamoDAO descPrestDAO = new CtaDxpDescuentosPrestamoDAO(getSessionHibernate(request));
		//Obteniendo la cuenta del prestamo aprobado
		CtaCasCuentaAsociado cuentaAsocPre = cuentaAsociadoDAO.findById(apliPrestamoForm.getCasCuenta());
		DecimalFormat df = new DecimalFormat("0.00"); 
		
		boolean errorDesc = false;
		String texto = "";
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		
		//Obteniendo los descuentos asignados a los seguros del asociado
		List listaDescSeg = (List) descPrestDAO.findByPreIdAndTipoCuenta(cuentaAsocPre.getCtaPrePrestamo().getPreId(), "D");
		
		List listaSeguros = (List) cuentaAsociadoDAO.findByAscAndTipoCuenta2(cuentaAsocPre.getCtaAscAsociado().getAscId(),"D");//Para obtener las cuentas de seguro activas del asociado
		Iterator iSeg = listaSeguros.iterator();		
		int size = listaSeguros.size();
		int i=1;
		
		if(size>0){
			htmlBuilder.table(0).id("hor-zebra").align("left").border("0").close();
			htmlBuilder.thead(0).close();
			htmlBuilder.th(0).close().thEnd();
			htmlBuilder.th(0).close().append("N&uacute;mero de Cuenta").thEnd();
			htmlBuilder.th(0).close().append("Tipo de Seguro").thEnd();
			htmlBuilder.th(0).close().append("Saldo Actual").thEnd();
			htmlBuilder.th(0).close().append("Abono").thEnd();
			htmlBuilder.theadEnd(0);			
			while(iSeg.hasNext()){
				CtaCasCuentaAsociado cuentaAsocSeg = (CtaCasCuentaAsociado) iSeg.next();
				CtaSegSeguros cuentaSeg = cuentaAsocSeg.getCtaSegSeguros();
				if(cuentaSeg.getSegId().substring(0, 1).equals("D")){
					if(i%2 == 0) htmlBuilder.tr(0).close();
					else htmlBuilder.tr(0).append(" class=\"odd\"").close();
					
					//Verifica si el prestamo tiene descuento
					Iterator<CtaDxpDescuentosPrestamo> iDesc = listaDescSeg.iterator();
					int j=0,encontrado=0;
					while(iDesc.hasNext()){
						CtaDxpDescuentosPrestamo desc = (CtaDxpDescuentosPrestamo) iDesc.next();
						if(desc.getCtaSegSeguros().getSegId() == cuentaSeg.getSegId()){
							htmlBuilder.td(0).close().input().type("checkbox").checked().name("posicionSeg").append("class=\"posicionSeg\"").onclick("calculaLiquidoARecibir();").value(cuentaAsocSeg.getCasCuenta().toString()).close().tdEnd();					
							htmlBuilder.td(0).close().append(cuentaSeg.getSegId()).tdEnd();
							htmlBuilder.td(0).close().append(cuentaSeg.getCtaTisTipoSeguro().getTisNombre()).tdEnd();
							if(i%2 == 0) htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocSeg.getCasCuenta().toString()).value(df.format(cuentaSeg.getSegSaldoActual())).style("border: none;background-color: ffffff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd(); 
							else htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocSeg.getCasCuenta().toString()).value(df.format(cuentaSeg.getSegSaldoActual())).style("border: none;background-color: #e8edff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd();
							
							if(desc.getDxpMonto()==null) htmlBuilder.td(0).close().input().type("text").id(cuentaAsocSeg.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocSeg.getCasCuenta()+"').val(),'"+cuentaAsocSeg.getCasCuenta()+"');soloNumerosSeg($('#"+cuentaAsocSeg.getCasCuenta()+"').val(),'"+cuentaAsocSeg.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);
							else{
								htmlBuilder.td(0).close().input().type("text").value(desc.getDxpMonto().toString()).id(cuentaAsocSeg.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocSeg.getCasCuenta()+"').val(),'"+cuentaAsocSeg.getCasCuenta()+"');soloNumerosSeg($('#"+cuentaAsocSeg.getCasCuenta()+"').val(),'"+cuentaAsocSeg.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);
								//Error si el abono es mayor que el saldo deudor del seguro
								if(Math.round(cuentaAsocSeg.getCtaSegSeguros().getSegSaldoActual())<desc.getDxpMonto()) errorDesc=true;								
							}
							encontrado=1;														
							break;
						}
						j++;
					}
					if(encontrado!=1){
						htmlBuilder.td(0).close().input().type("checkbox").name("posicionSeg").append("class=\"posicionSeg\"").onclick("calculaLiquidoARecibir();").value(cuentaAsocSeg.getCasCuenta().toString()).close().tdEnd();					
						htmlBuilder.td(0).close().append(cuentaSeg.getSegId()).tdEnd();
						htmlBuilder.td(0).close().append(cuentaSeg.getCtaTisTipoSeguro().getTisNombre()).tdEnd();
						if(i%2 == 0) htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocSeg.getCasCuenta().toString()).value(df.format(cuentaSeg.getSegSaldoActual())).style("border: none;background-color: ffffff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd(); 
						else htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocSeg.getCasCuenta().toString()).value(df.format(cuentaSeg.getSegSaldoActual())).style("border: none;background-color: #e8edff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd();
						
						htmlBuilder.td(0).close().input().type("text").id(cuentaAsocSeg.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocSeg.getCasCuenta()+"').val(),'"+cuentaAsocSeg.getCasCuenta()+"');soloNumerosSeg($('#"+cuentaAsocSeg.getCasCuenta()+"').val(),'"+cuentaAsocSeg.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);						
					}
					i++;
				}			
			}
			if(errorDesc){				
				htmlBuilder.append("<tfoot style=\"border-top: 1px dashed #69c;\"><tr><td colspan=\"6\" align=\"right\">"+
						"<div id=\"divTablaSeguros\" style=\"color:#FF6666\">Errores encontrados.Verifique los datos!</div>"+
						//"<img src=\"../imagenes/opcionesTabla/reload.png\" style=\"cursor: pointer;\" alt=\"Actualizar L&iacute;quido\" id=\"actualizarLSeg\"/>"+
						"<input type=\"hidden\" id=\"errorSeg\" value=\"true\">"+
						"</td></tr></tfoot>");			
			}else{
				htmlBuilder.append("<tfoot style=\"border-top: 1px dashed #69c;\"><tr><td colspan=\"6\" align=\"right\">"+
						"<div id=\"divTablaSeguros\"></div>"+
						//"<img src=\"../imagenes/opcionesTabla/reload.png\" style=\"cursor: pointer;\" alt=\"Actualizar L&iacute;quido\" id=\"actualizarPre\" onclick=\"verificarPrestamos();calculaLiquidoARecibir();\" />"+
						"<input type=\"hidden\" id=\"errorSeg\" value=\"false\">"+
						"</td></tr></tfoot>");				
			}			
			htmlBuilder.tableEnd(1);
		}else{
			htmlBuilder.append("<label style=\"color: red;text-transform: none\">"+getResources(request,"aux").getMessage("cmd.seg.inactivas")+"</label>");
		}

		texto = htmlBuilder.toString();
		try{
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
	
	public ActionForward cargarLineasDePrestamo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AplicarDescuentosPrestamoForm apliPrestamoForm = (AplicarDescuentosPrestamoForm) form;
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));
		CtaDxpDescuentosPrestamoDAO descPrestDAO = new CtaDxpDescuentosPrestamoDAO(getSessionHibernate(request));
		//Obteniendo la cuenta del prestamo aprobado
		CtaCasCuentaAsociado cuentaAsociado = cuentaAsociadoDAO.findById(apliPrestamoForm.getCasCuenta());
		DecimalFormat df = new DecimalFormat("0.00"); 
		
		boolean errorDesc = false;
		ArrayList<Integer> descEncontrados = new ArrayList<Integer>();
		String texto = "";
		HtmlBuilder htmlBuilder = new HtmlBuilder();

		//Obteniendo los descuentos asignados a los prestamos del asociado
		List listaDescPrest = (List) descPrestDAO.findByPreIdAndTipoCuenta(cuentaAsociado.getCtaPrePrestamo().getPreId(), "C");
		
		//Obteniendo los prestamos activos que posee el asociado
		ArrayList<CtaCasCuentaAsociado> listaPrestamos = new ArrayList<CtaCasCuentaAsociado>();
		
		//Si es refinanciamiento elimino el prestamo refinanciado de la lista para aplicar descuentos
		if(cuentaAsociado.getCasRefinanciado()!=null){
			listaPrestamos = (ArrayList<CtaCasCuentaAsociado>) cuentaAsociadoDAO.findByAscAndTipoCuentaSinRef(cuentaAsociado.getCtaAscAsociado().getAscId(),"C",cuentaAsociado.getCasRefinanciado());//Para obtener las cuentas de préstamo activas del asociado
		}else {
			listaPrestamos = (ArrayList<CtaCasCuentaAsociado>) cuentaAsociadoDAO.findByAscAndTipoCuenta2(cuentaAsociado.getCtaAscAsociado().getAscId(),"C");//Para obtener las cuentas de préstamo activas del asociado
		}
		
		int size = listaPrestamos.size();
		Iterator iPre = listaPrestamos.iterator();	
		int i=1;
		
		if(size>0){
			htmlBuilder.table(0).id("hor-zebra").align("left").border("0").close();
			htmlBuilder.thead(0).close();
			htmlBuilder.th(0).close().thEnd();
			htmlBuilder.th(0).close().append("Referencia").thEnd();
			htmlBuilder.th(0).close().append("Tipo de Pr&eacute;stamo").thEnd();
			htmlBuilder.th(0).close().append("Saldo Actual + Intereses").thEnd();
			htmlBuilder.th(0).close().append("Abono").thEnd();
			htmlBuilder.theadEnd(0);
			
			while(iPre.hasNext()){
				CtaCasCuentaAsociado cuentaAsocPre = (CtaCasCuentaAsociado) iPre.next();
				CtaPrePrestamo cuentaPre = cuentaAsocPre.getCtaPrePrestamo();
				if(cuentaPre.getPreId().substring(0, 1).equals("C")){

					if(i%2 == 0) htmlBuilder.tr(0).close();
					else htmlBuilder.tr(0).append(" class=\"odd\"").close();

					//Calcular saldo con los intereses pendientes
					IntereseYMora iYm = new IntereseYMora();
					CtaMxpMovimientoPrestamoDAO mxpDao = new CtaMxpMovimientoPrestamoDAO(getSessionHibernate(request));
					CtaMxpMovimientoPrestamo mxpAnt = mxpDao.findUltimoMovimiento(cuentaAsocPre.getCtaPrePrestamo().getPreId());
					if(mxpAnt == null || mxpAnt.getMxpId() == null){
						mxpAnt = new CtaMxpMovimientoPrestamo();
					}
					iYm = iYm.actualizaInteres(mxpAnt, cuentaAsocPre.getCtaPrePrestamo(), 
							cuentaAsocPre, new Date(),request);
					Double saldoConInteres = cuentaAsocPre.getCtaPrePrestamo().getPreSaldoActualT() + iYm.getAcumulado() + iYm.getPendiente();					
					
					//Verifica si el prestamo tiene descuento
					Iterator<CtaDxpDescuentosPrestamo> iDesc = listaDescPrest.iterator();
					int j=0,encontrado=0;
					while(iDesc.hasNext()){
						CtaDxpDescuentosPrestamo desc = (CtaDxpDescuentosPrestamo) iDesc.next();
						if(desc.getCtaPrePrestamoByPreId2().getPreId() == cuentaPre.getPreId()){
							htmlBuilder.td(0).close().input().type("checkbox").checked().name("posicionPre").append("class=\"posicionPre\"").onclick("calculaLiquidoARecibir();").value(cuentaAsocPre.getCasCuenta().toString()).close().tdEnd();					
							htmlBuilder.td(0).close().append(cuentaPre.getPreId()).tdEnd();
							htmlBuilder.td(0).close().append(cuentaPre.getCtaTprTipoPrestamo().getTprNombre()).tdEnd();
							
							//cuentaPre.getPreSaldoActualT()+cuentaPre.getPreInteresAcumulado()+cuentaPre.getPrePendMov()
							
							if(i%2 == 0) htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocPre.getCasCuenta().toString()).value(df.format(saldoConInteres)).style("border: none;background-color: ffffff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd();
							else htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocPre.getCasCuenta().toString()).value(df.format(saldoConInteres)).style("border: none;background-color: #e8edff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd();
							
							if(desc.getDxpMonto() == null) htmlBuilder.td(0).close().input().type("text").name("montoAbonoPrest").id(cuentaAsocPre.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocPre.getCasCuenta()+"').val(),'"+cuentaAsocPre.getCasCuenta()+"');soloNumeros($('#"+cuentaAsocPre.getCasCuenta()+"').val(),'"+cuentaAsocPre.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);
							else {
								htmlBuilder.td(0).close().input().type("text").name("montoAbonoPrest").value(desc.getDxpMonto().toString()).id(cuentaAsocPre.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocPre.getCasCuenta()+"').val(),'"+cuentaAsocPre.getCasCuenta()+"');soloNumeros($('#"+cuentaAsocPre.getCasCuenta()+"').val(),'"+cuentaAsocPre.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);
								//Error si el abono es mayor que el saldo actual mas intereses
								if(saldoConInteres<desc.getDxpMonto()) errorDesc=true;								
							}
							descEncontrados.add(j);
							encontrado=1;
							break;
						}
						j++;
					}
					if(encontrado!=1){
						htmlBuilder.td(0).close().input().type("checkbox").name("posicionPre").append("class=\"posicionPre\"").onclick("calculaLiquidoARecibir();").value(cuentaAsocPre.getCasCuenta().toString()).close().tdEnd();					
						htmlBuilder.td(0).close().append(cuentaPre.getPreId()).tdEnd();
						htmlBuilder.td(0).close().append(cuentaPre.getCtaTprTipoPrestamo().getTprNombre()).tdEnd();
						
						if(i%2 == 0) htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocPre.getCasCuenta().toString()).value(df.format(saldoConInteres)).style("border: none;background-color: ffffff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd();
						else htmlBuilder.td(0).close().input().type("text").readonly().name(cuentaAsocPre.getCasCuenta().toString()).value(df.format(saldoConInteres)).style("border: none;background-color: #e8edff;font-size: 11px; color: rgb(102, 102, 153);").close().tdEnd();
						
						htmlBuilder.td(0).close().input().type("text").name("montoAbonoPrest").id(cuentaAsocPre.getCasCuenta().toString()).size("10").onkeyup("dosDecimales($('#"+cuentaAsocPre.getCasCuenta()+"').val(),'"+cuentaAsocPre.getCasCuenta()+"');soloNumeros($('#"+cuentaAsocPre.getCasCuenta()+"').val(),'"+cuentaAsocPre.getCasCuenta()+"');calculaLiquidoARecibir();").close().tdEnd().trEnd(0);
					}
					i++;
				}			
			}
			if(errorDesc){
				htmlBuilder.append("<tfoot style=\"border-top: 1px dashed #69c;\"><tr><td colspan=\"6\" align=\"right\">"+
						"<div id=\"divTablaPrestamos\" style=\"color:#FF6666\">Errores encontrados.Verifique los datos!</div>"+
						//"<img src=\"../imagenes/opcionesTabla/reload.png\" style=\"cursor: pointer;\" alt=\"Actualizar L&iacute;quido\" id=\"actualizarPre\" onclick=\"verificarPrestamos();calculaLiquidoARecibir();\" />"+
						"<input type=\"hidden\" id=\"errorPre\" value=\"true\">"+
						"</td></tr></tfoot>");				
			}else{
				htmlBuilder.append("<tfoot style=\"border-top: 1px dashed #69c;\"><tr><td colspan=\"6\" align=\"right\">"+
						"<div id=\"divTablaPrestamos\"></div>"+
						//"<img src=\"../imagenes/opcionesTabla/reload.png\" style=\"cursor: pointer;\" alt=\"Actualizar L&iacute;quido\" id=\"actualizarPre\" onclick=\"verificarPrestamos();calculaLiquidoARecibir();\" />"+
						"<input type=\"hidden\" id=\"errorPre\" value=\"false\">"+
						"</td></tr></tfoot>");				
			}
			
			htmlBuilder.tableEnd(1);
		}else{
			htmlBuilder.append("<label style=\"color: red;text-transform: none\">"+getResources(request,"aux").getMessage("cmd.pre.inactivas")+"</label>");
		}

		texto = htmlBuilder.toString();
		try{
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

	public ActionForward validarLineasDePrestamo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AplicarDescuentosPrestamoForm apliPrestamoForm = (AplicarDescuentosPrestamoForm) form;	
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSessionHibernate(request));		
		CtaCasCuentaAsociado cuentaAsociado = cuentaAsociadoDAO.findById(apliPrestamoForm.getCasCuenta());
		
		String texto = "";
		boolean error=false;
		HtmlBuilder htmlBuilder = new HtmlBuilder();
		
		List listaPrestamos = (List) request.getSession().getAttribute("listaPrestamos");
		Iterator iPre = listaPrestamos.iterator();
		
		if (apliPrestamoForm.getPosicionPre() != null) {
			int size = apliPrestamoForm.getPosicionPre().length;
			int i = 0;
			while (i < size) {
				while(iPre.hasNext()){
					CtaCasCuentaAsociado cuentaAsocPre = (CtaCasCuentaAsociado) iPre.next();
					if(apliPrestamoForm.getPosicionPre()[i]== cuentaAsocPre.getCasCuenta()){
						CtaPrePrestamo cuentaPre = cuentaAsocPre.getCtaPrePrestamo();
						if(apliPrestamoForm.getPrestamos()[i]>(cuentaPre.getPreSaldoActualT()+cuentaPre.getPreInteresAcumulado()+cuentaPre.getPrePendMov())){
							error=true;
						}else{
							
						}
						break;
					}					
				}
				iPre = listaPrestamos.iterator();
				i++;
			}
		}
		if(error) htmlBuilder.append("Errores encontrados.Verifique los datos!");
		else htmlBuilder.append("El l&iacute;quido a recibir se ha actualizado correctamente!");
		texto = htmlBuilder.toString();
		try{
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
	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.desc.lista","lista");
		map.put("cmd.desc.aceptar","aplicarDescuentos");
		map.put("cmd.desc.cancelar","cancelar");
		map.put("cmd.desc.cargarLineasDeAhorro","cargarLineasDeAhorro");
		map.put("cmd.desc.cargarTiposDeSeguro","cargarTiposDeSeguro");
		map.put("cmd.desc.cargarLineasDePrestamo","cargarLineasDePrestamo");
		map.put("cmd.desc.validarLineasDePrestamo","validarLineasDePrestamo");
		return map;
	}
}