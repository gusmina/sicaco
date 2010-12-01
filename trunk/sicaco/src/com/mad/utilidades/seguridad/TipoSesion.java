package com.mad.utilidades.seguridad;

import javax.servlet.http.HttpServletRequest;

import com.cetia.sicaco.hibernate.SecIseInicioSesion;

/**
 * 
 * Verifica si el login utilizado es de tipo Administrativo o de tipo Cliente
 */
public class TipoSesion {
	public static boolean isAdministrativo(SecIseInicioSesion usuarioConectado,
			HttpServletRequest request) {
		return "A".equalsIgnoreCase(usuarioConectado.getIseTipoSesion());
	}

	public static boolean isCliente(SecIseInicioSesion usuarioConectado,
			HttpServletRequest request) {
		//Por el momento solo se necesita verificar si el tipo de usuario es cliente
		return "C".equalsIgnoreCase(usuarioConectado.getIseTipoSesion());
	}
}
