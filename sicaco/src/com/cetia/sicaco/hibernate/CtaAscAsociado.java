package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaAscAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaAscAsociado extends AbstractCtaAscAsociado implements
		java.io.Serializable {

	public CtaAscAsociado(String ascId, CtaDomDomicilio ctaDomDomicilio,
			CtrEstEstado ctrEstEstado, CtaNotNotas ctaNotNotas,
			CtaTcoTipoContrato ctaTcoTipoContrato,
			CtaDptDepartamentoTrabajo ctaDptDepartamentoTrabajo,
			SecPerPersona secPerPersona, CtaTasTipoAsociado ctaTasTipoAsociado,
			String ascCodigo, Integer estId, Date ascIngresoCoope,
			Date ascRetiroCoope, String ascProfesion, Float ascSalario,
			String ascAsociadoPadre, String ascNacionalidad,
			String ascJefeInmediato, Date ascIngresoCia,
			Float ascRentaDomicilio, String ascIsss, Date ascDuiFechaExp,
			String ascDuiLugarExp, Float ascDividendoAportaciones,
			String ascNombreNit, String ascDirTrabajo,
			String ascCodigoAsociado, String ascPagoIngreso,
			String ascPagaraPadre, String ascEstadoDistribucion,
			Double ascDividendoPrestamo, Double ascSaldoAFavor, Set ctaInaIngresosxasociados,
			Set ctaFxpFiadorPrestamos, Set ctaCasCuentaAsociados,
			Set ctaCntCodigosAnterioreses, Set ctaBenBeneficiarioses,
			Set facFenFacturaEncabezados) {
		super(ascId, ctaDomDomicilio, ctrEstEstado, ctaNotNotas, ctaTcoTipoContrato,
				ctaDptDepartamentoTrabajo, secPerPersona, ctaTasTipoAsociado,
				ascCodigo, estId, ascIngresoCoope, ascRetiroCoope, ascProfesion,
				ascSalario, ascAsociadoPadre, ascNacionalidad, ascJefeInmediato,
				ascIngresoCia, ascRentaDomicilio, ascIsss, ascDuiFechaExp,
				ascDuiLugarExp, ascDividendoAportaciones, ascNombreNit, ascDirTrabajo,
				ascCodigoAsociado, ascPagoIngreso, ascPagaraPadre,
				ascEstadoDistribucion, ascDividendoPrestamo, ascSaldoAFavor, ctaInaIngresosxasociados,
				ctaFxpFiadorPrestamos, ctaCasCuentaAsociados,
				ctaCntCodigosAnterioreses, ctaBenBeneficiarioses,
				facFenFacturaEncabezados);
		// TODO Auto-generated constructor stub
	}



	public CtaAscAsociado() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CtaAscAsociado(String ascId, String ascCodigo, Date ascIngresoCoope,
			Float ascSalario, String ascIsss, Date ascDuiFechaExp,
			String ascDuiLugarExp, String ascNombreNit) {
		super(ascId, ascCodigo, ascIngresoCoope, ascSalario, ascIsss, ascDuiFechaExp,
				ascDuiLugarExp, ascNombreNit);
		// TODO Auto-generated constructor stub
	}

	

}
