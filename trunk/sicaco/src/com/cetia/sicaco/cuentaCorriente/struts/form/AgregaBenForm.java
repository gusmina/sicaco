/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.cuentaCorriente.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaBenBeneficiarios;
import com.cetia.sicaco.hibernate.SecParParentesco;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-02-2009
 * 
 * XDoclet definition:
 * @struts.form name="agregaBenForm"
 */
public class AgregaBenForm extends BasicForm {
	/*
	 * Generated Methods
	 */

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8141177970159291552L;
	private Long cuentaX;
	private CtaBenBeneficiarios beneficiariosH = new CtaBenBeneficiarios();
	
	private int[] posiciones;
	private Double[] valores;
	
	private String ascId;

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public Long getCuentaX() {
		return cuentaX;
	}

	public void setCuentaX(Long cuentaX) {
		this.cuentaX = cuentaX;
	}

	public int[] getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(int[] posiciones) {
		this.posiciones = posiciones;
	}

	public Double[] getValores() {
		return valores;
	}

	public void setValores(Double[] valores) {
		this.valores = valores;
	}

	public String getAscId() {
		return ascId;
	}

	public void setAscId(String ascId) {
		this.ascId = ascId;
	}

	/*
	 * Beneficiarios
	 */
	public CtaBenBeneficiarios getBeneficiariosH() {
		return beneficiariosH;
	}

	public void setBeneficiariosH(CtaBenBeneficiarios beneficiariosH) {
		this.beneficiariosH = beneficiariosH;
	}
	
	public String getBenApellidoCasada() {
		return beneficiariosH.getBenApellidoCasada();
	}

	public String getBenEstado() {
		return beneficiariosH.getBenEstado();
	}

	public String getBenFechaNacimiento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (beneficiariosH.getBenFechaNacimiento()!=null)?(sdf.format(beneficiariosH.getBenFechaNacimiento())):null;
	}

	public String getBenHijo() {
		return beneficiariosH.getBenHijo();
	}

	public Integer getBenId() {
		return beneficiariosH.getBenId();
	}

	public String getBenNombreCompleto() {
		return beneficiariosH.getBenNombreCompleto();
	}

	public String getBenPrimerApellido() {
		return beneficiariosH.getBenPrimerApellido();
	}

	public String getBenPrimerNombre() {
		return beneficiariosH.getBenPrimerNombre();
	}

	public String getBenSegundoApellido() {
		return beneficiariosH.getBenSegundoApellido();
	}

	public String getBenSegundoNombre() {
		return beneficiariosH.getBenSegundoNombre();
	}

	public String getBenSexo() {
		return beneficiariosH.getBenSexo();
	}

	public String getBenTelefono() {
		return beneficiariosH.getBenTelefono();
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return beneficiariosH.getCtaAscAsociado();
	}

	public Set getCtaBxcBeneficiariosCuentas() {
		return beneficiariosH.getCtaBxcBeneficiariosCuentas();
	}

	public SecParParentesco getSecParParentesco() {
		return beneficiariosH.getSecParParentesco();
	}

	public void setBenApellidoCasada(String benApellidoCasada) {
		beneficiariosH.setBenApellidoCasada(benApellidoCasada);
	}

	public void setBenEstado(String benEstado) {
		beneficiariosH.setBenEstado(benEstado);
	}

	public void setBenFechaNacimiento(Date benFechaNacimiento) {
		beneficiariosH.setBenFechaNacimiento(benFechaNacimiento);
	}

	public void setBenFechaNacimiento(String benFechaNacimiento) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			beneficiariosH.setBenFechaNacimiento(sdf.parse(benFechaNacimiento));
		} catch (ParseException e) {
		}
	}

	public void setBenHijo(String benHijo) {
		beneficiariosH.setBenHijo(benHijo);
	}

	public void setBenId(Integer benId) {
		beneficiariosH.setBenId(benId);
	}

	public void setBenNombreCompleto(String benNombreCompleto) {
		beneficiariosH.setBenNombreCompleto(benNombreCompleto);
	}

	public void setBenPrimerApellido(String benPrimerApellido) {
		beneficiariosH.setBenPrimerApellido(benPrimerApellido);
	}

	public void setBenPrimerNombre(String benPrimerNombre) {
		beneficiariosH.setBenPrimerNombre(benPrimerNombre);
	}

	public void setBenSegundoApellido(String benSegundoApellido) {
		beneficiariosH.setBenSegundoApellido(benSegundoApellido);
	}

	public void setBenSegundoNombre(String benSegundoNombre) {
		beneficiariosH.setBenSegundoNombre(benSegundoNombre);
	}

	public void setBenSexo(String benSexo) {
		beneficiariosH.setBenSexo(benSexo);
	}

	public void setBenTelefono(String benTelefono) {
		beneficiariosH.setBenTelefono(benTelefono);
	}

	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		beneficiariosH.setCtaAscAsociado(ctaAscAsociado);
	}

	public void setCtaBxcBeneficiariosCuentas(Set ctaBxcBeneficiariosCuentas) {
		beneficiariosH
				.setCtaBxcBeneficiariosCuentas(ctaBxcBeneficiariosCuentas);
	}

	public void setSecParParentesco(SecParParentesco secParParentesco) {
		beneficiariosH.setSecParParentesco(secParParentesco);
	}

	@Override
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}
}