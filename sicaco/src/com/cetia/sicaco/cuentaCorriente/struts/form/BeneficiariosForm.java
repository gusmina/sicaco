package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaBenBeneficiarios;
import com.cetia.sicaco.hibernate.CtaDomDomicilio;
import com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajo;
import com.cetia.sicaco.hibernate.CtaNotNotas;
import com.cetia.sicaco.hibernate.CtaTasTipoAsociado;
import com.cetia.sicaco.hibernate.CtaTcoTipoContrato;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.SecPerPersona;

import com.cetia.sicaco.hibernate.SecParParentesco;
import com.cetia.sicaco.struts.BasicForm;

public class BeneficiariosForm extends BasicForm{

	private CtaBenBeneficiarios ctaBenBeneficiariosH = new CtaBenBeneficiarios();
	private boolean mdf = false;
	private float porcentaje=0;
	private long cuentaX=-1;
	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isMdf() {
		return mdf;
	}

	public void setMdf(boolean mdf) {
		this.mdf = mdf;
	}

	public boolean equals(Object obj) {
		return ctaBenBeneficiariosH.equals(obj);
	}

	public String getBenEstado() {
		return ctaBenBeneficiariosH.getBenEstado();
	}

	public String getBenFechaNacimiento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (ctaBenBeneficiariosH.getBenFechaNacimiento()!=null)?(sdf.format(ctaBenBeneficiariosH.getBenFechaNacimiento())):null;
	}

	public String getBenHijo() {
		return ctaBenBeneficiariosH.getBenHijo();
	}

	public Integer getBenId() {
		return ctaBenBeneficiariosH.getBenId();
	}

	public String getBenPrimerApellido() {
		return ctaBenBeneficiariosH.getBenPrimerApellido();
	}

	public String getBenPrimerNombre() {
		return ctaBenBeneficiariosH.getBenPrimerNombre();
	}

	public String getBenSegundoApellido() {
		return ctaBenBeneficiariosH.getBenSegundoApellido();
	}

	public String getBenSegundoNombre() {
		return ctaBenBeneficiariosH.getBenSegundoNombre();
	}

	public String getBenSexo() {
		return ctaBenBeneficiariosH.getBenSexo();
	}

	public String getBenTelefono() {
		return ctaBenBeneficiariosH.getBenTelefono();
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return ctaBenBeneficiariosH.getCtaAscAsociado();
	}

	public Set getCtaBxcBeneficiariosCuentas() {
		return ctaBenBeneficiariosH.getCtaBxcBeneficiariosCuentas();
	}

	public SecParParentesco getSecParParentesco() {
		return ctaBenBeneficiariosH.getSecParParentesco();
	}

	public int hashCode() {
		return ctaBenBeneficiariosH.hashCode();
	}

	public void setBenEstado(String benEstado) {
		ctaBenBeneficiariosH.setBenEstado(benEstado);
	}

	public void setBenFechaNacimiento(String benFechaNacimiento) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			ctaBenBeneficiariosH.setBenFechaNacimiento(sdf.parse(benFechaNacimiento));
		} catch (ParseException e) {
		}
	}

	public void setBenHijo(String benHijo) {
		ctaBenBeneficiariosH.setBenHijo(benHijo);
	}

	public void setBenId(Integer benId) {
		ctaBenBeneficiariosH.setBenId(benId);
	}

	public void setBenPrimerApellido(String benPrimerApellido) {
		ctaBenBeneficiariosH.setBenPrimerApellido(benPrimerApellido);
	}

	public void setBenPrimerNombre(String benPrimerNombre) {
		ctaBenBeneficiariosH.setBenPrimerNombre(benPrimerNombre);
	}

	public void setBenSegundoApellido(String benSegundoApellido) {
		ctaBenBeneficiariosH.setBenSegundoApellido(benSegundoApellido);
	}

	public void setBenSegundoNombre(String benSegundoNombre) {
		ctaBenBeneficiariosH.setBenSegundoNombre(benSegundoNombre);
	}

	public void setBenSexo(String benSexo) {
		ctaBenBeneficiariosH.setBenSexo(benSexo);
	}

	public void setBenTelefono(String benTelefono) {
		ctaBenBeneficiariosH.setBenTelefono(benTelefono);
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		ctaBenBeneficiariosH.setCtaAscAsociado(ctaAscAsociado);
	}

	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		ctaBenBeneficiariosH
				.setCtaBxcBeneficiariosCuentas(ctaBxcBeneficiariosCuentas);
	}

	public void setSecParParentesco(SecParParentesco secParParentesco) {
		ctaBenBeneficiariosH.setSecParParentesco(secParParentesco);
	}

	public String toString() {
		return ctaBenBeneficiariosH.toString();
	}

	public String getBenApellidoCasada() {
		return ctaBenBeneficiariosH.getBenApellidoCasada();
	}


	public void setBenApellidoCasada(String benApellidoCasada) {
		ctaBenBeneficiariosH.setBenApellidoCasada(benApellidoCasada);
	}


	public CtaBenBeneficiarios getCtaBenBeneficiariosH() {
		return ctaBenBeneficiariosH;
	}

	public void setCtaBenBeneficiariosH(CtaBenBeneficiarios ctaBenBeneficiariosH) {
		this.ctaBenBeneficiariosH = ctaBenBeneficiariosH;
	}

	public String getAscId() {
		return this.ctaBenBeneficiariosH.getCtaAscAsociado().getAscId();
	}

	public void setAscId(String ascId) {
		this.ctaBenBeneficiariosH.getCtaAscAsociado().setAscId(ascId);
	}


	public float getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}


	public long getCuentaX() {
		return cuentaX;
	}


	public void setCuentaX(long cuentaX) {
		this.cuentaX = cuentaX;
	}


}
