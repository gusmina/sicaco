package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractInvProProveedor entity provides the base persistence definition of the InvProProveedor entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractInvProProveedor  implements java.io.Serializable {


    // Fields    

     private Integer proId;
     private InvTprTipoProveedor invTprTipoProveedor = new InvTprTipoProveedor();
     private CtrPaiPais ctrPaiPais = new CtrPaiPais();
     private String proNombre;
     private String proDireccion;
     private String proNumeroTelefono;
     private String proHorarioOficina;
     private String proNit;
     private Double proLimiteCredito;
     private Date audFechaCreacion;
     private String audUsuarioCreacion;
     private Date audFechaModificacion;
     private String audUsuarioModificacion;
     private String proCodigo;
     private String proRegistro;
     private String proEstado;
     private String proGiro;
     private Integer proDiasPago;
     private Set ordOpaOrdenDePagos = new HashSet(0);
     private Set ordOcoOrdenDeCompras = new HashSet(0);
     private Set ordRefCuentaReferencias = new HashSet(0);
     private Set invClaClasificados = new HashSet(0);
     private Set invCprContactoProveedors = new HashSet(0);
     private Set invPcbProveedorCuentaBancarias = new HashSet(0);
     private Set facFenFacturaEncabezados = new HashSet(0);
     private Set iucPutProveedorTipoPrestamos = new HashSet(0);


    // Constructors

	/** default constructor */
    public AbstractInvProProveedor() {
    }

	/** minimal constructor */
    public AbstractInvProProveedor(Integer proId, InvTprTipoProveedor invTprTipoProveedor, CtrPaiPais ctrPaiPais, String proNombre, String proDireccion, String proNumeroTelefono, String proHorarioOficina, String proNit, Date audFechaCreacion, String audUsuarioCreacion, Date audFechaModificacion, String audUsuarioModificacion, String proCodigo, String proRegistro) {
        this.proId = proId;
        this.invTprTipoProveedor = invTprTipoProveedor;
        this.ctrPaiPais = ctrPaiPais;
        this.proNombre = proNombre;
        this.proDireccion = proDireccion;
        this.proNumeroTelefono = proNumeroTelefono;
        this.proHorarioOficina = proHorarioOficina;
        this.proNit = proNit;
        this.audFechaCreacion = audFechaCreacion;
        this.audUsuarioCreacion = audUsuarioCreacion;
        this.audFechaModificacion = audFechaModificacion;
        this.audUsuarioModificacion = audUsuarioModificacion;
        this.proCodigo = proCodigo;
        this.proRegistro = proRegistro;
    }
    
    /** full constructor */
    public AbstractInvProProveedor(Integer proId,
			InvTprTipoProveedor invTprTipoProveedor, CtrPaiPais ctrPaiPais,
			String proNombre, String proDireccion, String proNumeroTelefono,
			String proHorarioOficina, String proNit, Double proLimiteCredito,
			Date audFechaCreacion, String audUsuarioCreacion,
			Date audFechaModificacion, String audUsuarioModificacion,
			String proCodigo, String proRegistro, String proEstado,
			String proGiro, Integer proDiasPago, Set ordOpaOrdenDePagos,
			Set ordOcoOrdenDeCompras, Set ordRefCuentaReferencias,
			Set invClaClasificados, Set invCprContactoProveedors,
			Set invPcbProveedorCuentaBancarias, Set facFenFacturaEncabezados,
			Set iucPutProveedorTipoPrestamos) {
		super();
		this.proId = proId;
		this.invTprTipoProveedor = invTprTipoProveedor;
		this.ctrPaiPais = ctrPaiPais;
		this.proNombre = proNombre;
		this.proDireccion = proDireccion;
		this.proNumeroTelefono = proNumeroTelefono;
		this.proHorarioOficina = proHorarioOficina;
		this.proNit = proNit;
		this.proLimiteCredito = proLimiteCredito;
		this.audFechaCreacion = audFechaCreacion;
		this.audUsuarioCreacion = audUsuarioCreacion;
		this.audFechaModificacion = audFechaModificacion;
		this.audUsuarioModificacion = audUsuarioModificacion;
		this.proCodigo = proCodigo;
		this.proRegistro = proRegistro;
		this.proEstado = proEstado;
		this.proGiro = proGiro;
		this.proDiasPago = proDiasPago;
		this.ordOpaOrdenDePagos = ordOpaOrdenDePagos;
		this.ordOcoOrdenDeCompras = ordOcoOrdenDeCompras;
		this.ordRefCuentaReferencias = ordRefCuentaReferencias;
		this.invClaClasificados = invClaClasificados;
		this.invCprContactoProveedors = invCprContactoProveedors;
		this.invPcbProveedorCuentaBancarias = invPcbProveedorCuentaBancarias;
		this.facFenFacturaEncabezados = facFenFacturaEncabezados;
		this.iucPutProveedorTipoPrestamos = iucPutProveedorTipoPrestamos;
	}

   
    // Property accessors

    public Integer getProId() {
        return this.proId;
    }
    
    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public InvTprTipoProveedor getInvTprTipoProveedor() {
        return this.invTprTipoProveedor;
    }
    
    public void setInvTprTipoProveedor(InvTprTipoProveedor invTprTipoProveedor) {
        this.invTprTipoProveedor = invTprTipoProveedor;
    }

    public CtrPaiPais getCtrPaiPais() {
        return this.ctrPaiPais;
    }
    
    public void setCtrPaiPais(CtrPaiPais ctrPaiPais) {
        this.ctrPaiPais = ctrPaiPais;
    }

    public String getProNombre() {
        return this.proNombre;
    }
    
    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProDireccion() {
        return this.proDireccion;
    }
    
    public void setProDireccion(String proDireccion) {
        this.proDireccion = proDireccion;
    }

    public String getProNumeroTelefono() {
        return this.proNumeroTelefono;
    }
    
    public void setProNumeroTelefono(String proNumeroTelefono) {
        this.proNumeroTelefono = proNumeroTelefono;
    }

    public String getProHorarioOficina() {
        return this.proHorarioOficina;
    }
    
    public void setProHorarioOficina(String proHorarioOficina) {
        this.proHorarioOficina = proHorarioOficina;
    }

    public String getProNit() {
        return this.proNit;
    }
    
    public void setProNit(String proNit) {
        this.proNit = proNit;
    }

    public Double getProLimiteCredito() {
        return this.proLimiteCredito;
    }
    
    public void setProLimiteCredito(Double proLimiteCredito) {
        this.proLimiteCredito = proLimiteCredito;
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

    public String getProCodigo() {
        return this.proCodigo;
    }
    
    public void setProCodigo(String proCodigo) {
        this.proCodigo = proCodigo;
    }

    public String getProRegistro() {
        return this.proRegistro;
    }
    
    public void setProRegistro(String proRegistro) {
        this.proRegistro = proRegistro;
    }

    public Set getOrdOpaOrdenDePagos() {
        return this.ordOpaOrdenDePagos;
    }
    
    public void setOrdOpaOrdenDePagos(Set ordOpaOrdenDePagos) {
        this.ordOpaOrdenDePagos = ordOpaOrdenDePagos;
    }

    public Set getOrdOcoOrdenDeCompras() {
        return this.ordOcoOrdenDeCompras;
    }
    
    public void setOrdOcoOrdenDeCompras(Set ordOcoOrdenDeCompras) {
        this.ordOcoOrdenDeCompras = ordOcoOrdenDeCompras;
    }

    public Set getOrdRefCuentaReferencias() {
        return this.ordRefCuentaReferencias;
    }
    
    public void setOrdRefCuentaReferencias(Set ordRefCuentaReferencias) {
        this.ordRefCuentaReferencias = ordRefCuentaReferencias;
    }

    public Set getInvClaClasificados() {
        return this.invClaClasificados;
    }
    
    public void setInvClaClasificados(Set invClaClasificados) {
        this.invClaClasificados = invClaClasificados;
    }

    public Set getInvCprContactoProveedors() {
        return this.invCprContactoProveedors;
    }
    
    public void setInvCprContactoProveedors(Set invCprContactoProveedors) {
        this.invCprContactoProveedors = invCprContactoProveedors;
    }

    public Set getInvPcbProveedorCuentaBancarias() {
        return this.invPcbProveedorCuentaBancarias;
    }
    
    public void setInvPcbProveedorCuentaBancarias(Set invPcbProveedorCuentaBancarias) {
        this.invPcbProveedorCuentaBancarias = invPcbProveedorCuentaBancarias;
    }

    public Set getFacFenFacturaEncabezados() {
        return this.facFenFacturaEncabezados;
    }
    
    public void setFacFenFacturaEncabezados(Set facFenFacturaEncabezados) {
        this.facFenFacturaEncabezados = facFenFacturaEncabezados;
    }

	public String getProEstado() {
		return proEstado;
	}

	public void setProEstado(String proEstado) {
		this.proEstado = proEstado;
	}

	public String getProGiro() {
		return proGiro;
	}

	public void setProGiro(String proGiro) {
		this.proGiro = proGiro;
	}

	public Integer getProDiasPago() {
		return proDiasPago;
	}

	public void setProDiasPago(Integer proDiasPago) {
		this.proDiasPago = proDiasPago;
	}

	public Set getIucPutProveedorTipoPrestamos() {
		return iucPutProveedorTipoPrestamos;
	}

	public void setIucPutProveedorTipoPrestamos(Set iucPutProveedorTipoPrestamos) {
		this.iucPutProveedorTipoPrestamos = iucPutProveedorTipoPrestamos;
	}
   
}