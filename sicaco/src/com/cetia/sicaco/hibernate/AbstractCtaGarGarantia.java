package com.cetia.sicaco.hibernate;

/**
 * AbstractCtaGarGarantia entity provides the base persistence definition of the
 * CtaGarGarantia entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCtaGarGarantia implements java.io.Serializable {

	// Fields

	private Integer garId;
	private CtaPrePrestamo ctaPrePrestamo = new CtaPrePrestamo();
	private CtaTgaTipoGarantia ctaTgaTipoGarantia = new CtaTgaTipoGarantia();
	private String garInspeccion;
	private String garUbicacion;
	private String garDescripcionInmueble;
	private String garNombreInmueble;
	private Float garValor;

	// Constructors

	/** default constructor */
	public AbstractCtaGarGarantia() {
	}

	/** minimal constructor */
	public AbstractCtaGarGarantia(Integer garId, String garInspeccion,
			String garDescripcionInmueble, String garNombreInmueble,
			Float garValor) {
		this.garId = garId;
		this.garInspeccion = garInspeccion;
		this.garDescripcionInmueble = garDescripcionInmueble;
		this.garNombreInmueble = garNombreInmueble;
		this.garValor = garValor;
	}

	/** full constructor */
	public AbstractCtaGarGarantia(Integer garId, CtaPrePrestamo ctaPrePrestamo,
			CtaTgaTipoGarantia ctaTgaTipoGarantia, String garInspeccion,
			String garUbicacion, String garDescripcionInmueble,
			String garNombreInmueble, Float garValor) {
		this.garId = garId;
		this.ctaPrePrestamo = ctaPrePrestamo;
		this.ctaTgaTipoGarantia = ctaTgaTipoGarantia;
		this.garInspeccion = garInspeccion;
		this.garUbicacion = garUbicacion;
		this.garDescripcionInmueble = garDescripcionInmueble;
		this.garNombreInmueble = garNombreInmueble;
		this.garValor = garValor;
	}

	// Property accessors

	public Integer getGarId() {
		return this.garId;
	}

	public void setGarId(Integer garId) {
		this.garId = garId;
	}

	public CtaPrePrestamo getCtaPrePrestamo() {
		return this.ctaPrePrestamo;
	}

	public void setCtaPrePrestamo(CtaPrePrestamo ctaPrePrestamo) {
		this.ctaPrePrestamo = ctaPrePrestamo;
	}

	public CtaTgaTipoGarantia getCtaTgaTipoGarantia() {
		return this.ctaTgaTipoGarantia;
	}

	public void setCtaTgaTipoGarantia(CtaTgaTipoGarantia ctaTgaTipoGarantia) {
		this.ctaTgaTipoGarantia = ctaTgaTipoGarantia;
	}

	public String getGarInspeccion() {
		return this.garInspeccion;
	}

	public void setGarInspeccion(String garInspeccion) {
		this.garInspeccion = garInspeccion;
	}

	public String getGarUbicacion() {
		return this.garUbicacion;
	}

	public void setGarUbicacion(String garUbicacion) {
		this.garUbicacion = garUbicacion;
	}

	public String getGarDescripcionInmueble() {
		return this.garDescripcionInmueble;
	}

	public void setGarDescripcionInmueble(String garDescripcionInmueble) {
		this.garDescripcionInmueble = garDescripcionInmueble;
	}

	public String getGarNombreInmueble() {
		return this.garNombreInmueble;
	}

	public void setGarNombreInmueble(String garNombreInmueble) {
		this.garNombreInmueble = garNombreInmueble;
	}

	public Float getGarValor() {
		return this.garValor;
	}

	public void setGarValor(Float garValor) {
		this.garValor = garValor;
	}

}