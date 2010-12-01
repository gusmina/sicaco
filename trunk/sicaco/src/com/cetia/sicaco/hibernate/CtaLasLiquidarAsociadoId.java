package com.cetia.sicaco.hibernate;

/**
 * CtaLasLiquidarAsociadoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaLasLiquidarAsociadoId extends AbstractCtaLasLiquidarAsociadoId
		implements java.io.Serializable {

	/**
	 * 
	 */
	public CtaLasLiquidarAsociadoId() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param lasId
	 * @param lasNombreCuenta
	 * @param lasSaldo
	 * @param lasInteres
	 * @param lasOtrasDeducciones
	 * @param lasFavorContra
	 * @param lasAscId
	 */
	public CtaLasLiquidarAsociadoId(Integer lasId, String lasNombreCuenta,
			double lasSaldo, double lasInteres, double lasOtrasDeducciones,
			Integer lasFavorContra, String lasAscId) {
		super(lasId, lasNombreCuenta, lasSaldo, lasInteres, lasOtrasDeducciones,
				lasFavorContra, lasAscId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param lasId
	 * @param lasNombreCuenta
	 * @param lasSaldo
	 * @param lasFavorContra
	 * @param lasAscId
	 */
	public CtaLasLiquidarAsociadoId(Integer lasId, String lasNombreCuenta,
			double lasSaldo, Integer lasFavorContra, String lasAscId) {
		super(lasId, lasNombreCuenta, lasSaldo, lasFavorContra, lasAscId);
		// TODO Auto-generated constructor stub
	}

	// Constructors
}
