package com.cetia.sicaco.cuentaCorriente.struts.form;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaFxpFiadorPrestamo;
import com.cetia.sicaco.hibernate.CtaPrePrestamo;
import com.cetia.sicaco.hibernate.CtaPxtPersonaExterna;
import com.cetia.sicaco.hibernate.CtaTfiTipoFiador;
import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.struts.FiadorVista;

public class FiadorPrestamoForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3270536522426325801L;
	private String ascId;//se utiliza para determinar los datos del asociado padre o comparaciones en los prestamos
	private String fiador;//se utiliza para saber si es asociado o no, por razones de seguridad
	private CtaFxpFiadorPrestamo fiadorPrestamoH = new CtaFxpFiadorPrestamo();
	private FiadorVista fiadorVista = new FiadorVista();
	private boolean mdf;
	private long[] posicionFxp;
	private int estadoPrestamo;//se utiliza para conservar el estado del prestamo al cual pertenece esta jsp
	private String pxtCodigoEmpleado;
	private String perId;//id de la persona que solicita el prestamo, se utiliza para verificar que una persona no se		
	
	
	
	public String getCodigo() {
		return fiadorVista.getCodigo();
	}



	public void setCodigo(String codigo) {
		fiadorVista.setCodigo(codigo);
	}



	public String getPerId() {
		return perId;
	}



	public void setPerId(String perId) {
		this.perId = perId;
	}



	public int getEstadoPrestamo() {
		return estadoPrestamo;
	}



	public void setEstadoPrestamo(int estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}



	public String getPrimerApellido() {
		return fiadorVista.getPrimerApellido();
	}



	public String getSegundoApellido() {
		return fiadorVista.getSegundoApellido();
	}



	public void setPrimerApellido(String primerApellido) {
		fiadorVista.setPrimerApellido(primerApellido);
	}



	public void setSegundoApellido(String segundoApellido) {
		fiadorVista.setSegundoApellido(segundoApellido);
	}



	public String getDireccion() {
		return fiadorVista.getDireccion();
	}



	public String getJefeInmediato() {
		return fiadorVista.getJefeInmediato();
	}



	public String getLugarTrabajo() {
		return fiadorVista.getLugarTrabajo();
	}



	public String getTelefono() {
		return fiadorVista.getTelefono();
	}



	public String getTelTrabajo() {
		return fiadorVista.getTelTrabajo();
	}



	public void setDireccion(String direccion) {
		fiadorVista.setDireccion(direccion);
	}



	public void setJefeInmediato(String jefeInmediato) {
		fiadorVista.setJefeInmediato(jefeInmediato);
	}



	public void setLugarTrabajo(String lugarTrabajo) {
		fiadorVista.setLugarTrabajo(lugarTrabajo);
	}



	public void setTelefono(String telefono) {
		fiadorVista.setTelefono(telefono);
	}



	public void setTelTrabajo(String telTrabajo) {
		fiadorVista.setTelTrabajo(telTrabajo);
	}



	public String getId() {
		return fiadorVista.getId();
	}



	public String getNombreFiador() {
		return fiadorVista.getNombreFiador();
	}



	public Float getSalario() {
		return fiadorVista.getSalario();
	}



	public String getTipoFiador() {
		return fiadorVista.getTipoFiador();
	}



	public void setId(String id) {
		fiadorVista.setId(id);
	}



	public void setNombreFiador(String nombreFiador) {
		fiadorVista.setNombreFiador(nombreFiador);
	}



	public void setSalario(Float salario) {
		fiadorVista.setSalario(salario);
	}



	public void setTipoFiador(String tipoFiador) {
		fiadorVista.setTipoFiador(tipoFiador);
	}



	public FiadorVista getFiadorVista() {
		return fiadorVista;
	}

	public void setFiadorVista(FiadorVista fiadorVista) {
		this.fiadorVista = fiadorVista;
	}

	public String getAscId() {
		return(fiadorPrestamoH.getCtaAscAsociado() != null?fiadorPrestamoH.getCtaAscAsociado().getAscId():""); 
	}

	public void setAscId(String ascId) {
		fiadorPrestamoH.getCtaAscAsociado().setAscId(ascId);
	}

	public long[] getPosicionFxp() {
		return posicionFxp;
	}

	public void setPosicionFxp(long[] posicionFxp) {
		this.posicionFxp = posicionFxp;
	}

	public String getPxtId() {
		return fiadorPrestamoH.getCtaPxtPersonaExterna().getPxtId();
	}

	public void setPxtId(String pxtId) {
		this.fiadorPrestamoH.getCtaPxtPersonaExterna().setPxtId(pxtId);
	}

	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	public String getPreId() {
		return fiadorPrestamoH.getCtaPrePrestamo().getPreId();
	}

	public void setPreId(String preId) {
		this.fiadorPrestamoH.getCtaPrePrestamo().setPreId(preId);
	}

	public String getFiador() {
		return fiador;
	}

	public void setFiador(String fiador) {
		this.fiador = fiador;
	}

	public CtaFxpFiadorPrestamo getFiadorPrestamoH() {
		return fiadorPrestamoH;
	}

	public void setFiadorPrestamoH(CtaFxpFiadorPrestamo fiadorPrestamoH) {
		this.fiadorPrestamoH = fiadorPrestamoH;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return fiadorPrestamoH.getCtaAscAsociado();
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return fiadorPrestamoH.getCtaPrePrestamo();
	}

	public CtaPxtPersonaExterna getCtaPxtPersonaExterna() {
		return fiadorPrestamoH.getCtaPxtPersonaExterna();
	}

	public CtaTfiTipoFiador getCtaTfiTipoFiador() {
		return fiadorPrestamoH.getCtaTfiTipoFiador();
	}

	public String getFxpEstado() {
		return fiadorPrestamoH.getFxpEstado();
	}

	public Integer getFxpId() {
		return fiadorPrestamoH.getFxpId();
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		fiadorPrestamoH.setCtaAscAsociado(ctaAscAsociado);
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		fiadorPrestamoH.setCtaPrePrestamo(ctaPrePrestamo);
	}

	public void setCtaPxtPersonaExterna(
			CtaPxtPersonaExterna ctaPxtPersonaExterna) {
		fiadorPrestamoH.setCtaPxtPersonaExterna(ctaPxtPersonaExterna);
	}
	
	
	public void setCtaTfiTipoFiador(CtaTfiTipoFiador ctaTfiTipoFiador) {
		fiadorPrestamoH.setCtaTfiTipoFiador(ctaTfiTipoFiador);
	}

	public void setFxpEstado(String fxpEstado) {
		fiadorPrestamoH.setFxpEstado(fxpEstado);
	}

	public void setFxpId(Integer fxpId) {
		fiadorPrestamoH.setFxpId(fxpId);
	}

	public String getPxtCodigoEmpleado() {
		return pxtCodigoEmpleado;
	}

	public void setPxtCodigoEmpleado(String pxtCodigoEmpleado) {
		this.pxtCodigoEmpleado = pxtCodigoEmpleado;
	}
	
	
	public String getPxtEmail() {
		return fiadorVista.getPxtEmail();
	}



	public void setPxtEmail(String pxtEmail) {
		fiadorVista.setPxtEmail(pxtEmail);
	}

	public String getAscCodigoAsociado() {
		return fiadorPrestamoH.getCtaAscAsociado().getAscCodigoAsociado();
	}
	
	public void setAscCodigoAsociado(String ascCodigoAsociado) {
		fiadorPrestamoH.getCtaAscAsociado().setAscCodigoAsociado(ascCodigoAsociado);
	}

	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
}
