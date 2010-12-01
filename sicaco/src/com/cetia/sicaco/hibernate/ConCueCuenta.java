package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * ConCueCuenta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ConCueCuenta extends AbstractConCueCuenta implements
		java.io.Serializable {

	public ConCueCuenta(ConTicTipoCuenta conTicTipoCuenta,
			ConCueCuenta conCueCuenta, String cueCodigoCuenta,
			byte cueTipoCuenta, String cueNombre, double cueSaldoActual,
			byte cuePosteable, String cueDescripcion, Integer cueEstado,
			float cuePorcentaje, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, Set conCueCuentas,
			Set conDpaDetallePartidas, Set conSacSaldosAnterioresCuentas,
			Set conMxcModuloxCuentacontables) {
		super(conTicTipoCuenta, conCueCuenta, cueCodigoCuenta, cueTipoCuenta,
				cueNombre, cueSaldoActual, cuePosteable, cueDescripcion, cueEstado,
				cuePorcentaje, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, conCueCuentas,
				conDpaDetallePartidas, conSacSaldosAnterioresCuentas,
				conMxcModuloxCuentacontables);
		// TODO Auto-generated constructor stub
	}

	public ConCueCuenta(ConTicTipoCuenta conTicTipoCuenta,
			String cueCodigoCuenta, byte cueTipoCuenta, String cueNombre,
			double cueSaldoActual, Integer cueEstado, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion) {
		super(conTicTipoCuenta, cueCodigoCuenta, cueTipoCuenta, cueNombre,
				cueSaldoActual, cueEstado, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
		// TODO Auto-generated constructor stub
	}

	public ConCueCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	// Constructors
}
