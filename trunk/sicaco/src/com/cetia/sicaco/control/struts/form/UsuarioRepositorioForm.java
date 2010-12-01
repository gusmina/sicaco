package com.cetia.sicaco.control.struts.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cetia.sicaco.hibernate.CtrRfcRepositorioFacturas;
import com.cetia.sicaco.hibernate.CtrUreUsuarioRepositorio;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.hibernate.SecSucSucursal;
import com.cetia.sicaco.struts.BasicForm;

public class UsuarioRepositorioForm extends BasicForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6812074214456493320L;
	
	
	private CtrUreUsuarioRepositorio usuarioRepositorioH = new CtrUreUsuarioRepositorio();
	
	private SecSucSucursal sucursalH = new SecSucSucursal();
	
	public CtrUreUsuarioRepositorio getUsuarioRepositorioH() {
		return usuarioRepositorioH;
	}


	public void setUsuarioRepositorioH(CtrUreUsuarioRepositorio usuarioRepositorioH) {
		this.usuarioRepositorioH = usuarioRepositorioH;
	}


	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (usuarioRepositorioH.getAudFechaCreacion()!=null)?(sdf.format(usuarioRepositorioH.getAudFechaCreacion())):null;
	}


	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (usuarioRepositorioH.getAudFechaCreacion()!=null)?(sdf.format(usuarioRepositorioH.getAudFechaCreacion())):null;
	}



	public String getAudUsuarioCreacion() {
		return usuarioRepositorioH.getAudUsuarioCreacion();
	}



	public String getAudUsuarioModificacion() {
		return usuarioRepositorioH.getAudUsuarioModificacion();
	}



	public CtrRfcRepositorioFacturas getCtrRfcRepositorioFacturas() {
		return usuarioRepositorioH.getCtrRfcRepositorioFacturas();
	}



	public SecPerPersona getSecPerPersona() {
		return usuarioRepositorioH.getSecPerPersona();
	}



	public Integer getUreId() {
		return usuarioRepositorioH.getUreId();
	}



	public void setAudFechaCreacion(Date audFechaCreacion) {
		usuarioRepositorioH.setAudFechaCreacion(audFechaCreacion);
	}



	public void setAudFechaModificacion(Date audFechaModificacion) {
		usuarioRepositorioH.setAudFechaModificacion(audFechaModificacion);
	}



	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		usuarioRepositorioH.setAudUsuarioCreacion(audUsuarioCreacion);
	}



	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		usuarioRepositorioH.setAudUsuarioModificacion(audUsuarioModificacion);
	}



	public void setCtrRfcRepositorioFacturas(
			CtrRfcRepositorioFacturas ctrRfcRepositorioFacturas) {
		usuarioRepositorioH
				.setCtrRfcRepositorioFacturas(ctrRfcRepositorioFacturas);
	}



	public void setSecPerPersona(SecPerPersona secPerPersona) {
		usuarioRepositorioH.setSecPerPersona(secPerPersona);
	}



	public void setUreId(Integer ureId) {
		usuarioRepositorioH.setUreId(ureId);
	}



	public Integer getSucId() {
		return sucursalH.getSucId();
	}


	public String getSucNombre() {
		return sucursalH.getSucNombre();
	}


	public void setSucId(Integer sucId) {
		sucursalH.setSucId(sucId);
	}


	public void setSucNombre(String sucNombre) {
		sucursalH.setSucNombre(sucNombre);
	}


	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}

}
