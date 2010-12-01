package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecPerPersona entity provides the base persistence definition of the
 * SecPerPersona entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecPerPersona implements java.io.Serializable {

	// Fields

	private String perId;
	private SecDppDepartamentoPais secDppDepartamentoPais = new SecDppDepartamentoPais();
	private SecSucSucursal secSucSucursal = new SecSucSucursal();
	private String perPrimerNombre;
	private String perSegundoNombre;
	private String perTercerNombre;
	private String perPrimerApellido;
	private String perSegundoApellido;
	private String perApellidoCasada;
	private String perGenero;
	private Date perFechaNacimiento;
	private String perLugarNacimiento;
	private String perNit;
	private String perDui;
	private String perMunicipio;
	private String perCalle;
	private String perColoniaBarrio;
	private String perCodigoPostal;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private String perEstado;
	private Integer zonId;
	private String perDireccionCompleta;
	private Set secPemPersonaEmergencias = new HashSet(0);
	private Set ctrUreUsuarioRepositorios = new HashSet(0);
	private Set secCelCorreoElectronicos = new HashSet(0);
	private Set secIseInicioSesions = new HashSet(0);
	private Set secTelTelefonos = new HashSet(0);
	private Set ctaAscAsociados = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecPerPersona() {
	}

	/** minimal constructor */
	public AbstractSecPerPersona(String perId, String perPrimerNombre,
			String perPrimerApellido, String perGenero,
			String perLugarNacimiento, String perMunicipio, String perCalle,
			String perColoniaBarrio, String perCodigoPostal,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String perEstado) {
		this.perId = perId;
		this.perPrimerNombre = perPrimerNombre;
		this.perPrimerApellido = perPrimerApellido;
		this.perGenero = perGenero;
		this.perLugarNacimiento = perLugarNacimiento;
		this.perMunicipio = perMunicipio;
		this.perCalle = perCalle;
		this.perColoniaBarrio = perColoniaBarrio;
		this.perCodigoPostal = perCodigoPostal;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.perEstado = perEstado;
	}

	/** full constructor */
	public AbstractSecPerPersona(String perId,
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
			Set ctrUreUsuarioRepositorios,
			Set secCelCorreoElectronicos, Set secIseInicioSesions,
			Set secTelTelefonos, Set ctaAscAsociados) {
		this.perId = perId;
		this.secDppDepartamentoPais = secDppDepartamentoPais;
		this.secSucSucursal = secSucSucursal;
		this.perPrimerNombre = perPrimerNombre;
		this.perSegundoNombre = perSegundoNombre;
		this.perTercerNombre = perTercerNombre;
		this.perPrimerApellido = perPrimerApellido;
		this.perSegundoApellido = perSegundoApellido;
		this.perApellidoCasada = perApellidoCasada;
		this.perGenero = perGenero;
		this.perFechaNacimiento = perFechaNacimiento;
		this.perLugarNacimiento = perLugarNacimiento;
		this.perNit = perNit;
		this.perDui = perDui;
		this.perMunicipio = perMunicipio;
		this.perCalle = perCalle;
		this.perColoniaBarrio = perColoniaBarrio;
		this.perCodigoPostal = perCodigoPostal;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.perEstado = perEstado;
		this.zonId = zonId;
		this.perDireccionCompleta = perDireccionCompleta;
		this.secPemPersonaEmergencias = secPemPersonaEmergencias;
		this.ctrUreUsuarioRepositorios = ctrUreUsuarioRepositorios;
		this.secCelCorreoElectronicos = secCelCorreoElectronicos;
		this.secIseInicioSesions = secIseInicioSesions;
		this.secTelTelefonos = secTelTelefonos;
		this.ctaAscAsociados = ctaAscAsociados;
	}

	// Property accessors

	public String getPerId() {
		return this.perId;
	}

	public void setPerId(String perId) {
		this.perId = perId;
	}

	public SecDppDepartamentoPais getSecDppDepartamentoPais() {
		return this.secDppDepartamentoPais;
	}

	public void setSecDppDepartamentoPais(
			SecDppDepartamentoPais secDppDepartamentoPais) {
		this.secDppDepartamentoPais = secDppDepartamentoPais;
	}

	public SecSucSucursal getSecSucSucursal() {
		return this.secSucSucursal;
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		this.secSucSucursal = secSucSucursal;
	}

	public String getPerPrimerNombre() {
		return this.perPrimerNombre;
	}

	public void setPerPrimerNombre(String perPrimerNombre) {
		this.perPrimerNombre = perPrimerNombre;
	}

	public String getPerSegundoNombre() {
		return this.perSegundoNombre;
	}

	public void setPerSegundoNombre(String perSegundoNombre) {
		this.perSegundoNombre = perSegundoNombre;
	}

	public String getPerTercerNombre() {
		return this.perTercerNombre;
	}

	public void setPerTercerNombre(String perTercerNombre) {
		this.perTercerNombre = perTercerNombre;
	}

	public String getPerPrimerApellido() {
		return this.perPrimerApellido;
	}

	public void setPerPrimerApellido(String perPrimerApellido) {
		this.perPrimerApellido = perPrimerApellido;
	}

	public String getPerSegundoApellido() {
		return this.perSegundoApellido;
	}

	public void setPerSegundoApellido(String perSegundoApellido) {
		this.perSegundoApellido = perSegundoApellido;
	}

	public String getPerApellidoCasada() {
		return this.perApellidoCasada;
	}

	public void setPerApellidoCasada(String perApellidoCasada) {
		this.perApellidoCasada = perApellidoCasada;
	}

	public String getPerGenero() {
		return this.perGenero;
	}

	public void setPerGenero(String perGenero) {
		this.perGenero = perGenero;
	}

	public Date getPerFechaNacimiento() {
		return this.perFechaNacimiento;
	}

	public void setPerFechaNacimiento(Date perFechaNacimiento) {
		this.perFechaNacimiento = perFechaNacimiento;
	}

	public String getPerLugarNacimiento() {
		return this.perLugarNacimiento;
	}

	public void setPerLugarNacimiento(String perLugarNacimiento) {
		this.perLugarNacimiento = perLugarNacimiento;
	}

	public String getPerNit() {
		return this.perNit;
	}

	public void setPerNit(String perNit) {
		this.perNit = perNit;
	}

	public String getPerDui() {
		return this.perDui;
	}

	public void setPerDui(String perDui) {
		this.perDui = perDui;
	}

	public String getPerMunicipio() {
		return this.perMunicipio;
	}

	public void setPerMunicipio(String perMunicipio) {
		this.perMunicipio = perMunicipio;
	}

	public String getPerCalle() {
		return this.perCalle;
	}

	public void setPerCalle(String perCalle) {
		this.perCalle = perCalle;
	}

	public String getPerColoniaBarrio() {
		return this.perColoniaBarrio;
	}

	public void setPerColoniaBarrio(String perColoniaBarrio) {
		this.perColoniaBarrio = perColoniaBarrio;
	}

	public String getPerCodigoPostal() {
		return this.perCodigoPostal;
	}

	public void setPerCodigoPostal(String perCodigoPostal) {
		this.perCodigoPostal = perCodigoPostal;
	}

	public Date getAudFechaCreacion() {
		return this.audFechaCreacion;
	}

	public void setAudFechaCreacion(Date audFechaCreacion) {
		this.audFechaCreacion = audFechaCreacion;
	}

	public String getAudUsuarioCreacion() {
		return this.audUsuarioCreacion;
	}

	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		this.audUsuarioCreacion = audUsuarioCreacion;
	}

	public Date getAudFechaModificacion() {
		return this.audFechaModificacion;
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		this.audFechaModificacion = audFechaModificacion;
	}

	public String getAudUsuarioModificacion() {
		return this.audUsuarioModificacion;
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		this.audUsuarioModificacion = audUsuarioModificacion;
	}

	public String getPerEstado() {
		return this.perEstado;
	}

	public void setPerEstado(String perEstado) {
		this.perEstado = perEstado;
	}

	public Integer getZonId() {
		return this.zonId;
	}

	public void setZonId(Integer zonId) {
		this.zonId = zonId;
	}

	public String getPerDireccionCompleta() {
		return perDireccionCompleta;
	}

	public void setPerDireccionCompleta(String perDireccionCompleta) {
		this.perDireccionCompleta = perDireccionCompleta;
	}

	public Set getSecPemPersonaEmergencias() {
		return this.secPemPersonaEmergencias;
	}

	public void setSecPemPersonaEmergencias(Set secPemPersonaEmergencias) {
		this.secPemPersonaEmergencias = secPemPersonaEmergencias;
	}

	public Set getCtrUreUsuarioRepositorios() {
		return this.ctrUreUsuarioRepositorios;
	}

	public void setCtrUreUsuarioRepositorios(Set ctrUreUsuarioRepositorios) {
		this.ctrUreUsuarioRepositorios = ctrUreUsuarioRepositorios;
	}

	public Set getSecCelCorreoElectronicos() {
		return this.secCelCorreoElectronicos;
	}

	public void setSecCelCorreoElectronicos(Set secCelCorreoElectronicos) {
		this.secCelCorreoElectronicos = secCelCorreoElectronicos;
	}

	public Set getSecIseInicioSesions() {
		return this.secIseInicioSesions;
	}

	public void setSecIseInicioSesions(Set secIseInicioSesions) {
		this.secIseInicioSesions = secIseInicioSesions;
	}

	public Set getSecTelTelefonos() {
		return this.secTelTelefonos;
	}

	public void setSecTelTelefonos(Set secTelTelefonos) {
		this.secTelTelefonos = secTelTelefonos;
	}

	public Set getCtaAscAsociados() {
		return ctaAscAsociados;
	}

	public void setCtaAscAsociados(Set ctaAscAsociados) {
		this.ctaAscAsociados = ctaAscAsociados;
	}

}