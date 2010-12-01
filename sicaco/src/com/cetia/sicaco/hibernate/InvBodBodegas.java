package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvBodBodegas entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvBodBodegas extends AbstractInvBodBodegas implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvBodBodegas() {
	}

	public InvBodBodegas(Integer bodId, CtrPaiPais ctrPaiPais,
			SecSucSucursal secSucSucursal, String bodNombre,
			String bodDireccion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String bodEstado) {
		super(bodId, ctrPaiPais, secSucSucursal, bodNombre, bodDireccion,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, bodEstado);
		// TODO Auto-generated constructor stub
	}

	public InvBodBodegas(Integer bodId, CtrPaiPais ctrPaiPais,
			SecSucSucursal secSucSucursal, String bodNombre,
			String bodDireccion, String bodComentario, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String bodEstado,
			Set invCprCapacidadProductos, Set facFenFacturaEncabezados,
			Set invMovMovimientoses, Set invEboExistenciaBodegas,
			Set invStnEstantes) {
		super(bodId, ctrPaiPais, secSucSucursal, bodNombre, bodDireccion,
				bodComentario, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion, bodEstado,
				invCprCapacidadProductos, facFenFacturaEncabezados,
				invMovMovimientoses, invEboExistenciaBodegas, invStnEstantes);
		// TODO Auto-generated constructor stub
	}

}
