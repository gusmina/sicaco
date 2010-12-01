package com.cetia.sicaco.procesosEspeciales.struts.action;

public class ObjetoQuery {

	//ASC_CODIGO","NOMBRE","TOTAL_APORTACION",
	//"TOTAL_AHORRO", "TOTAL_PRESTAMO","TOTAL_SEGURO","ASC_ID
	private String ascId;
	private String ascCodigo;
	private String nombre;
	private Double totalAportacion;
	private Double totalAhorro;
	private Double totalPrestamo;
	private Double totalSeguro;
	
	public ObjetoQuery() {
	}

	public ObjetoQuery(String ascId, String ascCodigo, String nombre, Double totalAportacion,
			Double totalAhorro, Double totalPrestamo, Double totalSeguro) {
		super();
		this.ascId = ascId;
		this.ascCodigo = ascCodigo;
		this.nombre = nombre;
		this.totalAportacion = totalAportacion;
		this.totalAhorro = totalAhorro;
		this.totalPrestamo = totalPrestamo;
		this.totalSeguro = totalSeguro;
	}

	public String getAscId() {
		return ascId;
	}

	public void setAscId(String ascId) {
		this.ascId = ascId;
	}

	public String getAscCodigo() {
		return ascCodigo;
	}

	public void setAscCodigo(String ascCodigo) {
		this.ascCodigo = ascCodigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getTotalAportacion() {
		return totalAportacion;
	}

	public void setTotalAportacion(Double totalAportacion) {
		this.totalAportacion = totalAportacion;
	}

	public Double getTotalAhorro() {
		return totalAhorro;
	}

	public void setTotalAhorro(Double totalAhorro) {
		this.totalAhorro = totalAhorro;
	}

	public Double getTotalPrestamo() {
		return totalPrestamo;
	}

	public void setTotalPrestamo(Double totalPrestamo) {
		this.totalPrestamo = totalPrestamo;
	}

	public Double getTotalSeguro() {
		return totalSeguro;
	}

	public void setTotalSeguro(Double totalSeguro) {
		this.totalSeguro = totalSeguro;
	}

	public double totalTotal(ObjetoQuery oq){
		Double total = 0.0;
		total += oq.getTotalAhorro();
		total += oq.getTotalAportacion();
		total += oq.getTotalPrestamo();
		total += oq.getTotalSeguro();
		return total;
	}
	
	public double totalAhyAp(ObjetoQuery oq){
		Double total = 0.0;
		total += oq.getTotalAhorro();
		total += oq.getTotalAportacion();
		return total;
	}
	
	public double totalPrySe(ObjetoQuery oq){
		Double total = 0.0;
		total += oq.getTotalPrestamo();
		total += oq.getTotalSeguro();
		return total;
	}
	
	public double totalMenosAh(ObjetoQuery oq){
		Double total = 0.0;
		total += oq.getTotalAportacion();
		total += oq.getTotalPrestamo();
		total += oq.getTotalSeguro();
		return total;
	}
	
	public double totalMenosAp(ObjetoQuery oq){
		Double total = 0.0;
		total += oq.getTotalAhorro();
		total += oq.getTotalPrestamo();
		total += oq.getTotalSeguro();
		return total;
	}
	
	public double totalMenosPr(ObjetoQuery oq){
		Double total = 0.0;
		total += oq.getTotalAhorro();
		total += oq.getTotalAportacion();
		total += oq.getTotalSeguro();
		return total;
	}
	
	public double totalMenosSe(ObjetoQuery oq){
		Double total = 0.0;
		total += oq.getTotalAhorro();
		total += oq.getTotalAportacion();
		total += oq.getTotalPrestamo();
		return total;
	}
}
