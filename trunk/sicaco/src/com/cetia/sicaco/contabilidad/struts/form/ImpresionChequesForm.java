/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.contabilidad.struts.form;

import org.apache.struts.action.ActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-27-2008
 * 
 * XDoclet definition:
 * @struts.form name="impresionChequesForm"
 */
public class ImpresionChequesForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1216467244973579857L;

	private Integer[] indicesSeleccionados;
	private Long[] pcoIds;

	/**
	 * @return the pcoIds
	 */
	public Long[] getPcoIds() {
		return pcoIds;
	}

	/**
	 * @param pcoIds the pcoIds to set
	 */
	public void setPcoIds(Long[] pcoIds) {
		this.pcoIds = pcoIds;
	}

	/**
	 * @return the indicesSeleccionados
	 */
	public Integer[] getIndicesSeleccionados() {
		return indicesSeleccionados;
	}

	/**
	 * @param indicesSeleccionados the indicesSeleccionados to set
	 */
	public void setIndicesSeleccionados(Integer[] indicesSeleccionados) {
		this.indicesSeleccionados = indicesSeleccionados;
	}
}