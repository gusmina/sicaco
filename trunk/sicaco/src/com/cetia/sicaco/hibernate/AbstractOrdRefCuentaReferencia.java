package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractOrdRefCuentaReferencia entity provides the base persistence definition of the OrdRefCuentaReferencia entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrdRefCuentaReferencia  implements java.io.Serializable {


    // Fields    

     private Integer refId;
     private InvProProveedor invProProveedor = new InvProProveedor();
     private String refCuenta;
     private String refDescripcion;
     private Date audFechaCreacion;
     private String audUsuarioCreacion;
     private Date audFechaModificacion;
     private String audUsuarioModificacion;
     private String refEstado;
     private Set ordOcoOrdenDeCompras = new HashSet(0);


    // Constructors


	/** default constructor */
    public AbstractOrdRefCuentaReferencia() {
    }

	/** minimal constructor */
    public AbstractOrdRefCuentaReferencia(Integer refId, String refCuenta, Date audFechaCreacion, String audUsuarioCreacion, Date audFechaModificacion, String audUsuarioModificacion) {
        this.refId = refId;
        this.refCuenta = refCuenta;
        this.audFechaCreacion = audFechaCreacion;
        this.audUsuarioCreacion = audUsuarioCreacion;
        this.audFechaModificacion = audFechaModificacion;
        this.audUsuarioModificacion = audUsuarioModificacion;
    }
    
    /** full constructor */

    public AbstractOrdRefCuentaReferencia(Integer refId,
			InvProProveedor invProProveedor, String refCuenta,
			String refDescripcion, Date audFechaCreacion,
			String audUsuarioCreacion, Date audFechaModificacion,
			String audUsuarioModificacion, String refEstado,
			Set ordOcoOrdenDeCompras) {
		super();
		this.refId = refId;
		this.invProProveedor = invProProveedor;
		this.refCuenta = refCuenta;
		this.refDescripcion = refDescripcion;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.refEstado = refEstado;
		this.ordOcoOrdenDeCompras = ordOcoOrdenDeCompras;
	}
   
    // Property accessors

    public Integer getRefId() {
        return this.refId;
    }
    
    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public InvProProveedor getInvProProveedor() {
        return this.invProProveedor;
    }
    
    public void setInvProProveedor(InvProProveedor invProProveedor) {
        this.invProProveedor = invProProveedor;
    }

    public String getRefCuenta() {
        return this.refCuenta;
    }
    
    public void setRefCuenta(String refCuenta) {
        this.refCuenta = refCuenta;
    }

    public String getRefDescripcion() {
        return this.refDescripcion;
    }
    
    public void setRefDescripcion(String refDescripcion) {
        this.refDescripcion = refDescripcion;
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

    public Set getOrdOcoOrdenDeCompras() {
        return this.ordOcoOrdenDeCompras;
    }
    
    public void setOrdOcoOrdenDeCompras(Set ordOcoOrdenDeCompras) {
        this.ordOcoOrdenDeCompras = ordOcoOrdenDeCompras;
    }

	public String getRefEstado() {
		return refEstado;
	}

	public void setRefEstado(String refEstado) {
		this.refEstado = refEstado;
	}
   

}