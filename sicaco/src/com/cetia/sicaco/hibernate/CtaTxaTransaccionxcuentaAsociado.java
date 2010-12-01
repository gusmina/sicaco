package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * CtaTxaTransaccionxcuentaAsociado entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class CtaTxaTransaccionxcuentaAsociado extends
		AbstractCtaTxaTransaccionxcuentaAsociado implements
		java.io.Serializable {

	public CtaTxaTransaccionxcuentaAsociado() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CtaTxaTransaccionxcuentaAsociado(Long txaId,
			CtaCasCuentaAsociado ctaCasCuentaAsociado,
			CtaTtrTipoTransaccion ctaTtrTipoTransaccion,
			CtaNotNotas ctaNotNotas, Double txaMonto, Date txaFecha,
			Long txaComprobante, String txaNota, String txaCheque,
			String txaEstado, Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set ctaMxpMovimientoPrestamos, Set ctaMxaMovimientoAhorros,
			Set ctaMxsMovimientoSeguroses) {
		super(txaId, ctaCasCuentaAsociado, ctaTtrTipoTransaccion, ctaNotNotas,
				txaMonto, txaFecha, txaComprobante, txaNota, txaCheque, txaEstado,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, ctaMxpMovimientoPrestamos,
				ctaMxaMovimientoAhorros, ctaMxsMovimientoSeguroses);
		// TODO Auto-generated constructor stub
	}


	public CtaTxaTransaccionxcuentaAsociado(Long txaId, Double txaMonto,
			Date txaFecha, Long txaComprobante) {
		super(txaId, txaMonto, txaFecha, txaComprobante);
		// TODO Auto-generated constructor stub
	}
	
}
