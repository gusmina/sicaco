package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * SecIseInicioSesion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecIseInicioSesion extends AbstractSecIseInicioSesion implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecIseInicioSesion() {
	}

	/** minimal constructor */
	public SecIseInicioSesion(String iseNombreUsuario, String iseContrasenia,
			Date iseFechaActivacion, Integer iseVecesUtilizado,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion) {
		super(iseNombreUsuario, iseContrasenia, iseFechaActivacion,
				iseVecesUtilizado, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion);
	}

	/** full constructor */
	public SecIseInicioSesion(String iseNombreUsuario, SecRolRoles secRolRoles,
			SecPerPersona secPerPersona, String iseContrasenia,
			Date iseFechaActivacion, Date iseUltimaSesion, String iseUltimaIp,
			Integer iseVecesUtilizado, Date iseFechaInactivacion,
			String isePorqueInactivacion, String iseTipoSesion,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			Set secHseHistorialSesions) {
		super(iseNombreUsuario, secRolRoles, secPerPersona, iseContrasenia,
				iseFechaActivacion, iseUltimaSesion, iseUltimaIp,
				iseVecesUtilizado, iseFechaInactivacion, isePorqueInactivacion,
				iseTipoSesion, audFechaCreacion, audUsuarioCreacion,
				audFechaModificacion, audUsuarioModificacion,
				secHseHistorialSesions);
	}

}
