package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * SecPerPersona entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecPerPersona extends AbstractSecPerPersona implements
		java.io.Serializable {

	private String  codigoUsuario;
	
	// Constructors

	public SecPerPersona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SecPerPersona(String perId,
			SecDppDepartamentoPais secDppDepartamentoPais,
			SecSucSucursal secSucSucursal, String perPrimerNombre,
			String perSegundoNombre, String perTercerNombre,
			String perPrimerApellido, String perSegundoApellido,
			String perApellidoCasada, String perGenero,
			Date perFechaNacimiento, String perLugarNacimiento, String perNit,
			String perDui, String perMunicipio, String perCalle,
			String perColoniaBarrio, String perCodigoPostal,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String perEstado, Integer zonId, String perDireccionCompleta,
			Set secPemPersonaEmergencias, 
			Set ctrUreUsuarioRepositorios, Set secCelCorreoElectronicos,
			Set secIseInicioSesions, Set secTelTelefonos, Set ctaAscAsociados) {
		super(perId, secDppDepartamentoPais, secSucSucursal, perPrimerNombre,
				perSegundoNombre, perTercerNombre, perPrimerApellido,
				perSegundoApellido, perApellidoCasada, perGenero, perFechaNacimiento,
				perLugarNacimiento, perNit, perDui, perMunicipio, perCalle,
				perColoniaBarrio, perCodigoPostal, audFechaCreacion,
				audUsuarioCreacion, audFechaModificacion, audUsuarioModificacion,
				perEstado, zonId, perDireccionCompleta, secPemPersonaEmergencias,
				ctrUreUsuarioRepositorios, secCelCorreoElectronicos,
				secIseInicioSesions, secTelTelefonos, ctaAscAsociados);
		// TODO Auto-generated constructor stub
	}

	public SecPerPersona(String perId, String perPrimerNombre,
			String perPrimerApellido, String perGenero,
			String perLugarNacimiento, String perMunicipio, String perCalle,
			String perColoniaBarrio, String perCodigoPostal,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String perEstado) {
		super(perId, perPrimerNombre, perPrimerApellido, perGenero, perLugarNacimiento,
				perMunicipio, perCalle, perColoniaBarrio, perCodigoPostal,
				audFechaCreacion, audUsuarioCreacion, audFechaModificacion,
				audUsuarioModificacion, perEstado);
		// TODO Auto-generated constructor stub
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public String toString() {
		return getPerPrimerApellido() +" " +getPerSegundoApellido() + ", " +getPerPrimerNombre() + " " + getPerSegundoNombre();
	}
}
