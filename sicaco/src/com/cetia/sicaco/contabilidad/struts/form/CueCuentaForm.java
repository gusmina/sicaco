package com.cetia.sicaco.contabilidad.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cetia.sicaco.struts.BasicForm;
import com.cetia.sicaco.hibernate.ConCueCuenta;
import com.cetia.sicaco.hibernate.ConTicTipoCuenta;

public class CueCuentaForm extends BasicForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6602866573413047371L;
	private ConCueCuenta conCueCuentaH = new ConCueCuenta(); 
	
	private int cuentaPadre;
	private String unionString;
	private Integer tipoId;
	private int padreId;
	private int nivel;
	private int cp;
	private String cueCodCue;
	private int cueId2;
	
	private int tipoId2;
	private String cueDescripcionP;
	private Double cuePorcentajeP;
	private String cueNombreP;
	private Integer cueEstadoP;
	
	private Integer retroactiva;
	private Integer retroactivaP;
	
	public int getCuentaPadre() {
		return cuentaPadre;
	}

	public void setCuentaPadre(int cuentaPadre) {
		this.cuentaPadre = cuentaPadre;
	}

	
	public boolean isFillAuditoria() {
		// TODO Auto-generated method stub
		return true;
	}

	public ConCueCuenta getConCueCuentaH() {
		return conCueCuentaH;
	}

	public void setConCueCuentaH(ConCueCuenta conCueCuentaH) {
		this.conCueCuentaH = conCueCuentaH;
	}

	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (conCueCuentaH.getAudFechaCreacion()!=null)?(sdf.format(conCueCuentaH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (conCueCuentaH.getAudFechaModificacion()!=null)?(sdf.format(conCueCuentaH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return conCueCuentaH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return conCueCuentaH.getAudUsuarioModificacion();
	}

	public ConCueCuenta getConCueCuenta() {
		return conCueCuentaH.getConCueCuenta();
	}

	public Set getConCueCuentas() {
		return conCueCuentaH.getConCueCuentas();
	}

	public Set getConDpaDetallePartidas() {
		return conCueCuentaH.getConDpaDetallePartidas();
	}

	public Set getConMxcModuloxCuentacontables() {
		return conCueCuentaH.getConMxcModuloxCuentacontables();
	}

	public Set getConSacSaldosAnterioresCuentas() {
		return conCueCuentaH.getConSacSaldosAnterioresCuentas();
	}

	public ConTicTipoCuenta getConTicTipoCuenta() {
		return conCueCuentaH.getConTicTipoCuenta();
	}

	public String getCueCodigoCuenta() {
		return conCueCuentaH.getCueCodigoCuenta();
	}

	public String getCueDescripcion() {
		return conCueCuentaH.getCueDescripcion();
	}

	public Integer getCueEstado() {
		return conCueCuentaH.getCueEstado();
	}

	public Integer getCueId() {
		return conCueCuentaH.getCueId();
	}

	public String getCueNombre() {
		return conCueCuentaH.getCueNombre();
	}

	public float getCuePorcentaje() {
		return conCueCuentaH.getCuePorcentaje();
	}

	public byte getCuePosteable() {
		return conCueCuentaH.getCuePosteable();
	}

	public double getCueSaldoActual() {
		return conCueCuentaH.getCueSaldoActual();
	}

	public byte getCueTipoCuenta() {
		return conCueCuentaH.getCueTipoCuenta();
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		conCueCuentaH.setAudFechaCreacion(audFechaCreacion);
	}
	
	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			conCueCuentaH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			conCueCuentaH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		conCueCuentaH.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		conCueCuentaH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		conCueCuentaH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setConCueCuenta(ConCueCuenta conCueCuenta) {
		conCueCuentaH.setConCueCuenta(conCueCuenta);
	}

	public void setConCueCuentas(Set conCueCuentas) {
		conCueCuentaH.setConCueCuentas(conCueCuentas);
	}

	public void setConDpaDetallePartidas(Set conDpaDetallePartidas) {
		conCueCuentaH.setConDpaDetallePartidas(conDpaDetallePartidas);
	}

	public void setConMxcModuloxCuentacontables(Set conMxcModuloxCuentacontables) {
		conCueCuentaH
				.setConMxcModuloxCuentacontables(conMxcModuloxCuentacontables);
	}

	public void setConSacSaldosAnterioresCuentas(
			Set conSacSaldosAnterioresCuentas) {
		conCueCuentaH
				.setConSacSaldosAnterioresCuentas(conSacSaldosAnterioresCuentas);
	}

	public void setConTicTipoCuenta(ConTicTipoCuenta conTicTipoCuenta) {
		conCueCuentaH.setConTicTipoCuenta(conTicTipoCuenta);
	}

	public void setCueCodigoCuenta(String cueCodigoCuenta) {
		conCueCuentaH.setCueCodigoCuenta(cueCodigoCuenta);
	}

	public void setCueDescripcion(String cueDescripcion) {
		conCueCuentaH.setCueDescripcion(cueDescripcion);
	}

	public void setCueEstado(Integer cueEstado) {
		conCueCuentaH.setCueEstado(cueEstado);
	}

	public void setCueId(Integer cueId) {
		conCueCuentaH.setCueId(cueId);
	}

	public void setCueNombre(String cueNombre) {
		conCueCuentaH.setCueNombre(cueNombre);
	}

	public void setCuePorcentaje(float cuePorcentaje) {
		conCueCuentaH.setCuePorcentaje(cuePorcentaje);
	}

	public void setCuePosteable(byte cuePosteable) {
		conCueCuentaH.setCuePosteable(cuePosteable);
	}

	public void setCueSaldoActual(double cueSaldoActual) {
		conCueCuentaH.setCueSaldoActual(cueSaldoActual);
	}

	public void setCueTipoCuenta(byte cueTipoCuenta) {
		conCueCuentaH.setCueTipoCuenta(cueTipoCuenta);
	}

	public String getUnionString() {
		return unionString;
	}

	public void setUnionString(String unionString) {
		this.unionString = unionString;
	}

	public Integer getTipoId() {
		return tipoId;
	}

	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}

	public int getPadreId() {
		return padreId;
	}

	public void setPadreId(int padreId) {
		this.padreId = padreId;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getCueCodCue() {
		return cueCodCue;
	}

	public void setCueCodCue(String cueCodCue) {
		this.cueCodCue = cueCodCue;
	}

	public int getCueId2() {
		return cueId2;
	}

	public void setCueId2(int cueId2) {
		this.cueId2 = cueId2;
	}

	public int getTipoId2() {
		return tipoId2;
	}

	public void setTipoId2(int tipoId2) {
		this.tipoId2 = tipoId2;
	}

	public String getCueDescripcionP() {
		return cueDescripcionP;
	}

	public void setCueDescripcionP(String cueDescripcionP) {
		this.cueDescripcionP = cueDescripcionP;
	}

	public Double getCuePorcentajeP() {
		return cuePorcentajeP;
	}

	public void setCuePorcentajeP(Double cuePorcentajeP) {
		this.cuePorcentajeP = cuePorcentajeP;
	}
		
	public String getCueNombreP() {
		return cueNombreP;
	}

	public void setCueNombreP(String cueNombreP) {
		this.cueNombreP = cueNombreP;
	}

	

	public Integer getCueEstadoP() {
		return cueEstadoP;
	}

	public void setCueEstadoP(Integer cueEstadoP) {
		this.cueEstadoP = cueEstadoP;
	}

	public Integer getRetroactiva() {
		return retroactiva;
	}

	public void setRetroactiva(Integer retroactiva) {
		this.retroactiva = retroactiva;
	}

	public Integer getRetroactivaP() {
		return retroactivaP;
	}

	public void setRetroactivaP(Integer retroactivaP) {
		this.retroactivaP = retroactivaP;
	}
	
}
