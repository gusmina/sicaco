package com.cetia.sicaco.facturacion.struts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.cetia.sicaco.hibernate.ConRimRetencionImpuesto;
import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtrEstEstado;
import com.cetia.sicaco.hibernate.FacCliCliente;
import com.cetia.sicaco.hibernate.FacDfaDetalleFactura;
import com.cetia.sicaco.hibernate.FacDfaDetalleFacturaId;
import com.cetia.sicaco.hibernate.FacFenFacturaEncabezado;
import com.cetia.sicaco.hibernate.FacFusFacturaUso;
import com.cetia.sicaco.hibernate.InvBodBodegas;
import com.cetia.sicaco.hibernate.InvProProveedor;
import com.cetia.sicaco.hibernate.SecSucSucursal;
import com.cetia.sicaco.struts.BasicForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-08-2008
 * 
 * XDoclet definition:
 * @struts.form name="encabezadoFacturaForm"
 */
public class EncabezadoFacturaForm extends BasicForm {
	
	private static final long serialVersionUID = -5898953685634363548L;
	private FacFenFacturaEncabezado facturaEncabezadoH = new FacFenFacturaEncabezado();
	private FacDfaDetalleFactura detalleFacturaH = new FacDfaDetalleFactura();
	
	private boolean nva ;// se utiliza para saber si es una nueva factura.
	private boolean vfa; //determina el uso de la factura, si es true, es de venta,si es false,es de compra
	private double totalVentasA = 0.00;//total de ventas afectas
	private double totalVentasE = 0.00;
	private double totalFactura = 0.00;
	private double ivaRetenido = 0.00;
	private String proCodigo;
	private String proNombre;
	private String proRegistro;

	private String proNit;
	private String artNombre ;
	private String artCodigo;
	private String factorConversion;
	private String codCli;//para la busqueda cliente
	private String nombreCli;
	private int ascEmp;		
	private String ack;
	private byte exento;
	private Integer idProveedor;
	
	
	private String moa;
	private int askBod;
	private Boolean soloLectura = false;
	
	private int voc; // 2 venta, 1 compra
	private String view;
	private Date fenFechaFactura2;
	private String medida;
	private int pu;
	private String aCod;
	private String arti;
	private String codigo;
	private int fuso;
	private int impresa;
	
	private int[] posiciones;
	private int[] valores;
	private String fenSerieFacturaO;
	private String fenNumeroFacturaO;
	
	private String registroContribuyente;
	private String numeroContribuyente;

	private String esDeProv;
	
	private String aoc;
	
	private String tipoPagoId;
	private int bod;
	
	public String getAck() {
		return ack;
	}


	public void setAck(String ack) {
		this.ack = ack;
	}

	public String getProCodigo() {
		return proCodigo;
	}

	public void setProCodigo(String proCodigo) {
		this.proCodigo = proCodigo;
	}

	public String getProNombre() {
		return proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public String getProRegistro() {
		return proRegistro;
	}

	public void setProRegistro(String proRegistro) {
		this.proRegistro = proRegistro;
	}

	public String getProNit() {
		return proNit;
	}

	public void setProNit(String proNit) {
		this.proNit = proNit;
	}

	public CtaAscAsociado getCtaAscAsociado() {
		return facturaEncabezadoH.getCtaAscAsociado();
	}


	public FacCliCliente getFacCliCliente() {
		return facturaEncabezadoH.getFacCliCliente();
	}


	public void setCtaAscAsociado(CtaAscAsociado ctaAscAsociado) {
		facturaEncabezadoH.setCtaAscAsociado(ctaAscAsociado);
	}


	public void setFacCliCliente(FacCliCliente facCliCliente) {
		facturaEncabezadoH.setFacCliCliente(facCliCliente);
	}


	public String getCodCli() {
		return codCli;
	}

	public void setCodCli(String codCli) {
		this.codCli = codCli;
	}

	public String getNombreCli() {
		return nombreCli;
	}

	public void setNombreCli(String nombreCli) {
		this.nombreCli = nombreCli;
	}

	public int getAscEmp() {
		return ascEmp;
	}


	public void setAscEmp(int ascEmp) {
		this.ascEmp = ascEmp;
	}


	public int getCnvId() {
		return detalleFacturaH.getCnvId();
	}

	public void setCnvId(int cnvId) {
		detalleFacturaH.setCnvId(cnvId);
	}

	public double getTotalVentasE() {
		return totalVentasE;
	}

	public void setTotalVentasE(double totalVentasE) {
		this.totalVentasE = totalVentasE;
	}

	public double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public String getArtNombre() {
		return artNombre;
	}

	public void setArtNombre(String artNombre) {
		this.artNombre = artNombre;
	}

	public String getArtCodigo() {
		return artCodigo;
	}

	public void setArtCodigo(String artCodigo) {
		this.artCodigo = artCodigo;
	}

	public FacDfaDetalleFactura getDetalleFacturaH() {
		return detalleFacturaH;
	}

	public void setDetalleFacturaH(FacDfaDetalleFactura detalleFacturaH) {
		this.detalleFacturaH = detalleFacturaH;
	}

	public Integer getDfaCantidad() {
		return detalleFacturaH.getDfaCantidad();
	}

	public String getDfaDescripcion() {
		return detalleFacturaH.getDfaDescripcion();
	}

	public Byte getDfaExento() {
		return detalleFacturaH.getDfaExento();
	}

	public Double getDfaPrecioTotal() {
		return detalleFacturaH.getDfaPrecioTotal();
	}

	public Double getDfaPrecioUnitario() {
		return detalleFacturaH.getDfaPrecioUnitario();
	}

	public FacFenFacturaEncabezado getFacFenFacturaEncabezado() {
		return detalleFacturaH.getFacFenFacturaEncabezado();
	}

	public FacDfaDetalleFacturaId getId() {
		return detalleFacturaH.getId();
	}

	public Set getInvMovMovimientoses() {
		return detalleFacturaH.getInvMovMovimientoses();
	}

	public void setDfaCantidad(Integer dfaCantidad) {
		detalleFacturaH.setDfaCantidad(dfaCantidad);
	}

	public void setDfaDescripcion(String dfaDescripcion) {
		detalleFacturaH.setDfaDescripcion(dfaDescripcion);
	}

	public void setDfaExento(Byte dfaExento) {
		detalleFacturaH.setDfaExento(dfaExento);
	}

	public void setDfaPrecioTotal(Double dfaPrecioTotal) {
		detalleFacturaH.setDfaPrecioTotal(dfaPrecioTotal);
	}

	public void setDfaPrecioUnitario(Double dfaPrecioUnitario) {
		detalleFacturaH.setDfaPrecioUnitario(dfaPrecioUnitario);
	}

	public void setFacFenFacturaEncabezado(
			FacFenFacturaEncabezado facFenFacturaEncabezado) {
		detalleFacturaH.setFacFenFacturaEncabezado(facFenFacturaEncabezado);
	}

	public void setId(FacDfaDetalleFacturaId id) {
		detalleFacturaH.setId(id);
	}

	public void setInvMovMovimientoses(Set invMovMovimientoses) {
		detalleFacturaH.setInvMovMovimientoses(invMovMovimientoses);
	}

	public byte getExento() {
		return exento;
	}

	public void setExento(byte exento) {
		this.exento = exento;
	}

	public String getFactorConversion() {
		return factorConversion;
	}

	public void setFactorConversion(String factorConversion) {
		this.factorConversion = factorConversion;
	}

	public boolean isNva() {
		return nva;
	}

	public void setNva(boolean nva) {
		this.nva = nva;
	}

	public boolean getVfa() {
		return vfa;
	}

	public void setVfa(boolean vfa){
		this.vfa = vfa;
	}

	public double getTotalVentasA() {
		return totalVentasA;
	}

	public void setTotalVentasA(double totalVentas) {
		this.totalVentasA = totalVentas;
	}

	public double getIvaRetenido() {
		return ivaRetenido;
	}

	public void setIvaRetenido(double ivaRetenido) {
		this.ivaRetenido = ivaRetenido;
	}

	public Boolean getSoloLectura() {
		return this.soloLectura;
	}

	public void setSoloLectura(Boolean soloLectura) {
		this.soloLectura = soloLectura;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getAudFechaCreacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (facturaEncabezadoH.getAudFechaCreacion()!=null)?(sdf.format(facturaEncabezadoH.getAudFechaCreacion())):null;
	}

	public String getAudFechaModificacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (facturaEncabezadoH.getAudUsuarioModificacion()!=null)?(sdf.format(facturaEncabezadoH.getAudFechaModificacion())):null;
	}

	public String getAudUsuarioCreacion() {
		return facturaEncabezadoH.getAudUsuarioCreacion();
	}

	public String getAudUsuarioModificacion() {
		return facturaEncabezadoH.getAudUsuarioModificacion();
	}

	public ConRimRetencionImpuesto getConRimRetencionImpuesto() {
		return facturaEncabezadoH.getConRimRetencionImpuesto();
	}

	public CtrEstEstado getCtrEstEstado() {
		return facturaEncabezadoH.getCtrEstEstado();
	}

	public Set getFacDfaDetalleFacturas() {
		return facturaEncabezadoH.getFacDfaDetalleFacturas();
	}

	public FacFusFacturaUso getFacFusFacturaUso() {
		return facturaEncabezadoH.getFacFusFacturaUso();
	}

	public String getFenCancelada() {
		return facturaEncabezadoH.getFenCancelada();
	}

	public String getFenFechaFactura() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (facturaEncabezadoH.getFenFechaFactura()!=null)?(sdf.format(facturaEncabezadoH.getFenFechaFactura())):null;
	}

	public Integer getFenId() {
		return facturaEncabezadoH.getFenId();
	}

	public Double getFenIvaRetenido() {
		return facturaEncabezadoH.getFenIvaRetenido();
	}

	public String getFenNumeroFactura() {
		return facturaEncabezadoH.getFenNumeroFactura();
	}

	public String getFenRegistro() {
		return facturaEncabezadoH.getFenRegistro();
	}

	public String getFenSerieFactura() {
		return facturaEncabezadoH.getFenSerieFactura();
	}

	public String getFenTipoFactura() {
		return facturaEncabezadoH.getFenTipoFactura();
	}

	public Double getFenTotalVenta() {
		return facturaEncabezadoH.getFenTotalVenta();
	}

	public Double getFenTotalVentasExentas() {
		return facturaEncabezadoH.getFenTotalVentasExentas();
	}

	public InvBodBodegas getInvBodBodegas() {
		return facturaEncabezadoH.getInvBodBodegas();
	}

	/*public InvProProveedor getInvProProveedor() {
		return facturaEncabezadoH.getInvProProveedor();
	}*/

	public void setAudFechaCreacion(Date audFechaCreacion) {
		facturaEncabezadoH.setAudFechaCreacion(audFechaCreacion);
	}

	public void setAudFechaModificacion(Date audFechaModificacion) {
		facturaEncabezadoH.setAudFechaModificacion(audFechaModificacion);
	}

	public void setAudFechaCreacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			facturaEncabezadoH.setAudFechaCreacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudFechaModificacion(String adufecString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			facturaEncabezadoH.setAudFechaModificacion(sdf.parse(adufecString));
		} catch (ParseException e) {
		}
	}
	
	public void setAudUsuarioCreacion(String audUsuarioCreacion) {
		facturaEncabezadoH.setAudUsuarioCreacion(audUsuarioCreacion);
	}

	public void setAudUsuarioModificacion(String audUsuarioModificacion) {
		facturaEncabezadoH.setAudUsuarioModificacion(audUsuarioModificacion);
	}

	public void setConRimRetencionImpuesto(
			ConRimRetencionImpuesto conRimRetencionImpuesto) {
		facturaEncabezadoH.setConRimRetencionImpuesto(conRimRetencionImpuesto);
	}

	public void setCtrEstEstado(CtrEstEstado ctrEstEstado) {
		facturaEncabezadoH.setCtrEstEstado(ctrEstEstado);
	}

	public void setFacDfaDetalleFacturas(Set facDfaDetalleFacturas) {
		facturaEncabezadoH.setFacDfaDetalleFacturas(facDfaDetalleFacturas);
	}

	public void setFacFusFacturaUso(FacFusFacturaUso facFusFacturaUso) {
		facturaEncabezadoH.setFacFusFacturaUso(facFusFacturaUso);
	}

	public void setFenCancelada(String fenCancelada) {
		facturaEncabezadoH.setFenCancelada(fenCancelada);
	}

	public void setFenFechaFactura(Date fenFechaFactura) {
		facturaEncabezadoH.setFenFechaFactura(fenFechaFactura);
	}
	
	public void setFenFechaFactura(String fenFechaFactura) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			facturaEncabezadoH.setFenFechaFactura(sdf.parse(fenFechaFactura));
		} catch (ParseException e) {
		}
	}

	public void setFenId(Integer fenId) {
		facturaEncabezadoH.setFenId(fenId);
	}

	public void setFenIvaRetenido(Double fenIvaRetenido) {
		facturaEncabezadoH.setFenIvaRetenido(fenIvaRetenido);
	}

	public void setFenNumeroFactura(String fenNumeroFactura) {
		facturaEncabezadoH.setFenNumeroFactura(fenNumeroFactura);
	}

	public void setFenRegistro(String fenRegistro) {
		facturaEncabezadoH.setFenRegistro(fenRegistro);
	}

	public void setFenSerieFactura(String fenSerieFactura) {
		facturaEncabezadoH.setFenSerieFactura(fenSerieFactura);
	}

	public void setFenTipoFactura(String fenTipoFactura) {
		facturaEncabezadoH.setFenTipoFactura(fenTipoFactura);
	}

	public void setFenTotalVenta(Double fenTotalVenta) {
		facturaEncabezadoH.setFenTotalVenta(fenTotalVenta);
	}

	public void setFenTotalVentasExentas(Double fenTotalVentasExentas) {
		facturaEncabezadoH.setFenTotalVentasExentas(fenTotalVentasExentas);
	}

	public void setInvBodBodegas(InvBodBodegas invBodBodegas) {
		facturaEncabezadoH.setInvBodBodegas(invBodBodegas);
	}

	/*public void setInvProProveedor(InvProProveedor invProProveedor) {
		facturaEncabezadoH.setInvProProveedor(invProProveedor);
	}*/
	
	public String getFenTipoPago() {
		return facturaEncabezadoH.getFenTipoPago();
	}

	public void setFenTipoPago(String fenTipoPago) {
		facturaEncabezadoH.setFenTipoPago(fenTipoPago);
	}

	public InvProProveedor getInvProProveedor() {
		return facturaEncabezadoH.getInvProProveedor();
	}


	public void setInvProProveedor(InvProProveedor invProProveedor) {
		facturaEncabezadoH.setInvProProveedor(invProProveedor);
	}

	public Integer getFenOriginal() {
		return facturaEncabezadoH.getFenOriginal();
	}


	public void setFenOriginal(Integer fenOriginal) {
		facturaEncabezadoH.setFenOriginal(fenOriginal);
	}


	public FacFenFacturaEncabezado getFacturaEncabezadoH() {
		return facturaEncabezadoH;
	}

	public void setFacturaEncabezadoH(FacFenFacturaEncabezado facturaEncabezadoH) {
		this.facturaEncabezadoH = facturaEncabezadoH;
	}
	
	public String getMoa() {
		return moa;
	}

	public void setMoa(String moa) {
		this.moa = moa;
	}

	public int getAskBod() {
		return askBod;
	}

	public void setAskBod(int askBod) {
		this.askBod = askBod;
	}
	
	
	public boolean isFillAuditoria() {
		return true;
	}

	public int getVoc() {
		return voc;
	}

	public void setVoc(int voc) {
		this.voc = voc;
	}

	public String getHseFechaFactFin() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (facturaEncabezadoH.getHseFechaFactFin()!=null)?(sdf.format(facturaEncabezadoH.getHseFechaFactFin())):null;
	}

	public String getHseFechaFactIni() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return (facturaEncabezadoH.getHseFechaFactIni()!=null)?(sdf.format(facturaEncabezadoH.getHseFechaFactIni())):null;
	}

	public void setHseFechaFactFin(String hseFechaFactFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			facturaEncabezadoH.setHseFechaFactFin(sdf.parse(hseFechaFactFin));
		} catch (ParseException e) {
		}
	}

	public void setHseFechaFactIni(String hseFechaFactIni) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			facturaEncabezadoH.setHseFechaFactIni(sdf.parse(hseFechaFactIni));
		} catch (ParseException e) {
		}
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public int getPu() {
		return pu;
	}

	public void setPu(int pu) {
		this.pu = pu;
	}

	public String getACod() {
		return aCod;
	}

	public void setACod(String aCod) {
		this.aCod = aCod;
	}

	public String getArti() {
		return arti;
	}

	public void setArti(String arti) {
		this.arti = arti;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getFuso() {
		return fuso;
	}

	public void setFuso(int fuso) {
		this.fuso = fuso;
	}

	public int getImpresa() {
		return impresa;
	}

	public void setImpresa(int impresa) {
		this.impresa = impresa;
	}

	public int[] getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(int[] posiciones) {
		this.posiciones = posiciones;
	}

	public int[] getValores() {
		return valores;
	}

	public void setValores(int[] valores) {
		this.valores = valores;
	}

	public String getFenSerieFacturaO() {
		return fenSerieFacturaO;
	}

	public void setFenSerieFacturaO(String fenSerieFacturaO) {
		this.fenSerieFacturaO = fenSerieFacturaO;
	}

	public String getFenNumeroFacturaO() {
		return fenNumeroFacturaO;
	}

	public void setFenNumeroFacturaO(String fenNumeroFacturaO) {
		this.fenNumeroFacturaO = fenNumeroFacturaO;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getRegistroContribuyente() {
		return registroContribuyente;
	}

	public void setRegistroContribuyente(String registroContribuyente) {
		this.registroContribuyente = registroContribuyente;
	}

	public String getNumeroContribuyente() {
		return numeroContribuyente;
	}

	public void setNumeroContribuyente(String numeroContribuyente) {
		this.numeroContribuyente = numeroContribuyente;
	}

	public String getEsDeProv() {
		return esDeProv;
	}

	public void setEsDeProv(String esDeProv) {
		this.esDeProv = esDeProv;
	}

	public String getAoc() {
		return aoc;
	}

	public void setAoc(String aoc) {
		this.aoc = aoc;
	}

	public String getTipoPagoId() {
		return tipoPagoId;
	}

	public void setTipoPagoId(String tipoPagoId) {
		this.tipoPagoId = tipoPagoId;
	}


	public SecSucSucursal getSecSucSucursal() {
		return facturaEncabezadoH.getSecSucSucursal();
	}


	public void setSecSucSucursal(SecSucSucursal secSucSucursal) {
		facturaEncabezadoH.setSecSucSucursal(secSucSucursal);
	}

	public int getBod() {
		return bod;
	}

	public void setBod(int bod) {
		this.bod = bod;
	}
	
}

