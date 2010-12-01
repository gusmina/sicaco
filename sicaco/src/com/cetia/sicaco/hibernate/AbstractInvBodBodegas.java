package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractInvBodBodegas entity provides the base persistence definition of the
 * InvBodBodegas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvBodBodegas implements java.io.Serializable {

	// Fields

	private Integer bodId;
	private CtrPaiPais ctrPaiPais = new CtrPaiPais();
	private SecSucSucursal secSucSucursal = new SecSucSucursal();
	private String bodNombre;
	private String bodDireccion;
	private String bodComentario;
	private Date audFechaCreacion;
	private String audUsuarioCreacion;
	private Date audFechaModificacion;
	private String audUsuarioModificacion;
	private String bodEstado;
	private Set invCprCapacidadProductos = new HashSet(0);
	private Set facFenFacturaEncabezados = new HashSet(0);
	private Set invMovMovimientoses = new HashSet(0);
	private Set invEboExistenciaBodegas = new HashSet(0);
	private Set invStnEstantes = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractInvBodBodegas() {
	}

	/** minimal constructor */
	public AbstractInvBodBodegas(Integer bodId, CtrPaiPais ctrPaiPais,
			SecSucSucursal secSucSucursal,
			String bodNombre, String bodDireccion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String bodEstado) {
		this.bodId = bodId;
		this.ctrPaiPais = ctrPaiPais;
		this.secSucSucursal = secSucSucursal;
		this.bodNombre = bodNombre;
		this.bodDireccion = bodDireccion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.bodEstado = bodEstado;
	}

	/** full constructor */
	public AbstractInvBodBodegas(Integer bodId, CtrPaiPais ctrPaiPais,
			SecSucSucursal secSucSucursal,
			String bodNombre, String bodDireccion, String bodComentario,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String bodEstado, Set invCprCapacidadProductos,
			Set facFenFacturaEncabezados, Set invMovMovimientoses,
			Set invEboExistenciaBodegas, Set invStnEstantes) {
		this.bodId = bodId;
		this.ctrPaiPais = ctrPaiPais;
		this.secSucSucursal = secSucSucursal;
		this.bodNombre = bodNombre;
		this.bodDireccion = bodDireccion;
		this.bodComentario = bodComentario;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.bodEstado = bodEstado;
		this.invCprCapacidadProductos = invCprCapacidadProductos;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
		this.invMovMovimientoses = invMovMovimientoses;
		this.invEboExistenciaBodegas = invEboExistenciaBodegas;
		this.invStnEstantes = invStnEstantes;
	}

	// Property accessors

	public Integer getBodId() {
		return this.bodId;
	}

	public void setBodId(Integer bodId) {
		this.bodId = bodId;
	}

	public CtrPaiPais getCtrPaiPais() {
		return this.ctrPaiPais;
	}

	public void setCtrPaiPais(CtrPaiPais ctrPaiPais) {
		this.ctrPaiPais = ctrPaiPais;
	}

	public SecSucSucursal getSecSucSucursal() {
		return secSucSucursal;
	}

	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		this.secSucSucursal = secSucSucursal;
	}

	public String getBodNombre() {
		return this.bodNombre;
	}

	public void setBodNombre(String bodNombre) {
		this.bodNombre = bodNombre;
	}

	public String getBodDireccion() {
		return this.bodDireccion;
	}

	public void setBodDireccion(String bodDireccion) {
		this.bodDireccion = bodDireccion;
	}

	public String getBodComentario() {
		return this.bodComentario;
	}

	public void setBodComentario(String bodComentario) {
		this.bodComentario = bodComentario;
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

	public String getBodEstado() {
		return this.bodEstado;
	}

	public void setBodEstado(String bodEstado) {
		this.bodEstado = bodEstado;
	}

	public Set getInvCprCapacidadProductos() {
		return this.invCprCapacidadProductos;
	}

	public void setInvCprCapacidadProductos(Set invCprCapacidadProductos) {
		this.invCprCapacidadProductos = invCprCapacidadProductos;
	}

	public Set getFacFenFacturaEncabezados() {
		return this.facFenFacturaEncabezados;
	}

	public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
	}

	public Set getInvMovMovimientoses() {
		return this.invMovMovimientoses;
	}

	public void setInvMovMovimientoses(Set invMovMovimientoses) {
		this.invMovMovimientoses = invMovMovimientoses;
	}

	public Set getInvEboExistenciaBodegas() {
		return this.invEboExistenciaBodegas;
	}

	public void setInvEboExistenciaBodegas(Set invEboExistenciaBodegas) {
		this.invEboExistenciaBodegas = invEboExistenciaBodegas;
	}

	public Set getInvStnEstantes() {
		return this.invStnEstantes;
	}

	public void setInvStnEstantes(Set invStnEstantes) {
		this.invStnEstantes = invStnEstantes;
	}

}