package com.cetia.sicaco.hibernate;

/**
 * CtaGarGarantia entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaGarGarantia extends AbstractCtaGarGarantia implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CtaGarGarantia() {
	}

	/** minimal constructor */
	public CtaGarGarantia(Integer garId, String garInspeccion,
			String garDescripcionInmueble, String garNombreInmueble,
			Float garValor) {
		super(garId, garInspeccion, garDescripcionInmueble, garNombreInmueble,
				garValor);
	}

	/** full constructor */
	public CtaGarGarantia(Integer garId, CtaPrePrestamo ctaPrePrestamo,
			CtaTgaTipoGarantia ctaTgaTipoGarantia, String garInspeccion,
			String garUbicacion, String garDescripcionInmueble,
			String garNombreInmueble, Float garValor) {
		super(garId, ctaPrePrestamo, ctaTgaTipoGarantia, garInspeccion,
				garUbicacion, garDescripcionInmueble, garNombreInmueble,
				garValor);
	}

}
