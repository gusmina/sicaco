package com.cetia.sicaco.facturacion.struts.form;

import java.util.Set;

import com.cetia.sicaco.hibernate.FacCliCliente;
import com.cetia.sicaco.struts.BasicForm;

public class ClienteForm extends BasicForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4197693117519172268L;
	private FacCliCliente facCliClienteH = new FacCliCliente();
	private boolean modi=false; // se utiliza para saber si esta modificando o no
	private boolean emp=false; // para saber si el codigo de registro esta vacio o no e inhabilitarle
	private boolean contribuyente = false;
	
	private int chequeado;
	
	public boolean isEmp() {
		return emp;
	}

	public void setEmp(boolean emp) {
		this.emp = emp;
	}

	public boolean isModi() {
		return modi;
	}

	public void setModi(boolean modi) {
		this.modi = modi;
	}

	public String getCliCodigo() {
		return facCliClienteH.getCliCodigo();
	}



	public String getCliDepartamento() {
		return facCliClienteH.getCliDepartamento();
	}



	public String getCliDireccion() {
		return facCliClienteH.getCliDireccion();
	}



	public String getCliGiro() {
		return facCliClienteH.getCliGiro();
	}



	public String getCliMunicipio() {
		return facCliClienteH.getCliMunicipio();
	}



	public String getCliNombre() {
		return facCliClienteH.getCliNombre();
	}



	public String getCliNumRegistro() {
		return facCliClienteH.getCliNumRegistro();
	}



	public void setCliCodigo(String cliCodigo) {
		facCliClienteH.setCliCodigo(cliCodigo);
	}



	public void setCliDepartamento(String cliDepartamento) {
		facCliClienteH.setCliDepartamento(cliDepartamento);
	}



	public void setCliDireccion(String cliDireccion) {
		facCliClienteH.setCliDireccion(cliDireccion);
	}



	public void setCliGiro(String cliGiro) {
		facCliClienteH.setCliGiro(cliGiro);
	}



	public void setCliMunicipio(String cliMunicipio) {
		facCliClienteH.setCliMunicipio(cliMunicipio);
	}



	public void setCliNombre(String cliNombre) {
		facCliClienteH.setCliNombre(cliNombre);
	}



	public void setCliNumRegistro(String cliNumRegistro) {
		facCliClienteH.setCliNumRegistro(cliNumRegistro);
	}



	public String getCliContribuyente() {
		return facCliClienteH.getCliContribuyente();
	}

	public Integer getCliDeclaraIva() {
		return facCliClienteH.getCliDeclaraIva();
	}

	public void setCliContribuyente(String cliContribuyente) {
		facCliClienteH.setCliContribuyente(cliContribuyente.replace("-",""));
	}

	public void setCliDeclaraIva(Integer cliDeclaraIva) {
		facCliClienteH.setCliDeclaraIva(cliDeclaraIva);
	}

	public FacCliCliente getFacCliClienteH() {
		return facCliClienteH;
	}



	public void setFacCliClienteH(FacCliCliente facCliClienteH) {
		this.facCliClienteH = facCliClienteH;
	}

	public boolean isContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(boolean contribuyente) {
		this.contribuyente = contribuyente;
	}

	public int getChequeado() {
		return chequeado;
	}

	public void setChequeado(int chequeado) {
		this.chequeado = chequeado;
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return false;
	}

}
