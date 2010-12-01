package com.cetia.sicaco.hibernate;

import java.util.Date;

/**
 * SecHseHistorialSesion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecHseHistorialSesion extends AbstractSecHseHistorialSesion
		implements java.io.Serializable {
	
	private Date hseFechaAccesoIni;
	private Date hseFechaAccesoFin;
	private Date hseFechaSalidaIni;
	private Date hseFechaSalidaFin;
	
	// Constructors

	public Date getHseFechaAccesoIni() {
		return hseFechaAccesoIni;
	}

	public void setHseFechaAccesoIni(Date hseFechaAccesoIni) {
		this.hseFechaAccesoIni = hseFechaAccesoIni;
	}

	public Date getHseFechaAccesoFin() {
		return hseFechaAccesoFin;
	}

	public void setHseFechaAccesoFin(Date hseFechaAccesoFin) {
		this.hseFechaAccesoFin = hseFechaAccesoFin;
	}

	public Date getHseFechaSalidaIni() {
		return hseFechaSalidaIni;
	}

	public void setHseFechaSalidaIni(Date hseFechaSalidaIni) {
		this.hseFechaSalidaIni = hseFechaSalidaIni;
	}

	public Date getHseFechaSalidaFin() {
		return hseFechaSalidaFin;
	}

	public void setHseFechaSalidaFin(Date hseFechaSalidaFin) {
		this.hseFechaSalidaFin = hseFechaSalidaFin;
	}

	/** default constructor */
	public SecHseHistorialSesion() {
	}

	/** minimal constructor */
	public SecHseHistorialSesion(Integer hseId, Date hseFechaAcceso,
			String hseIp, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(hseId, hseFechaAcceso, hseIp, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

	/** full constructor */
	public SecHseHistorialSesion(Integer hseId,
			SecIseInicioSesion secIseInicioSesion, Date hseFechaAcceso,
			String hseIp, Date hseFechaSalida, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(hseId, secIseInicioSesion, hseFechaAcceso, hseIp, hseFechaSalida,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion);
	}

}
