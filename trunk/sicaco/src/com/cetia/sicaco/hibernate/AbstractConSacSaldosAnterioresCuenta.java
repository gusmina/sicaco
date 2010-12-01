package com.cetia.sicaco.hibernate;

import java.util.Date;


/**
 * AbstractConSacSaldosAnterioresCuenta entity provides the base persistence definition of the ConSacSaldosAnterioresCuenta entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractConSacSaldosAnterioresCuenta  implements java.io.Serializable {


    // Fields    

     private Long sacId;
     private ConCueCuenta conCueCuenta;
     private Date sacFecha;
     private Double sacSaldoALaFecha;
     private Double sacTotalDebe;
     private Double sacTotalHaber;


    // Constructors

    /** default constructor */
    public AbstractConSacSaldosAnterioresCuenta() {
    }

	/** minimal constructor */
    public AbstractConSacSaldosAnterioresCuenta(ConCueCuenta conCueCuenta, Date sacFecha, Double sacSaldoALaFecha) {
        this.conCueCuenta = conCueCuenta;
        this.sacFecha = sacFecha;
        this.sacSaldoALaFecha = sacSaldoALaFecha;
    }
    
    /** full constructor */
    public AbstractConSacSaldosAnterioresCuenta(ConCueCuenta conCueCuenta, Date sacFecha, Double sacSaldoALaFecha, Double sacTotalDebe, Double sacTotalHaber) {
        this.conCueCuenta = conCueCuenta;
        this.sacFecha = sacFecha;
        this.sacSaldoALaFecha = sacSaldoALaFecha;
        this.sacTotalDebe = sacTotalDebe;
        this.sacTotalHaber = sacTotalHaber;
    }

   
    // Property accessors

    public Long getSacId() {
        return this.sacId;
    }
    
    public void setSacId(Long sacId) {
        this.sacId = sacId;
    }

    public ConCueCuenta getConCueCuenta() {
        return this.conCueCuenta;
    }
    
    public void setConCueCuenta(ConCueCuenta conCueCuenta) {
        this.conCueCuenta = conCueCuenta;
    }

    public Date getSacFecha() {
        return this.sacFecha;
    }
    
    public void setSacFecha(Date sacFecha) {
        this.sacFecha = sacFecha;
    }

    public Double getSacSaldoALaFecha() {
        return this.sacSaldoALaFecha;
    }
    
    public void setSacSaldoALaFecha(Double sacSaldoALaFecha) {
        this.sacSaldoALaFecha = sacSaldoALaFecha;
    }

    public Double getSacTotalDebe() {
        return this.sacTotalDebe;
    }
    
    public void setSacTotalDebe(Double sacTotalDebe) {
        this.sacTotalDebe = sacTotalDebe;
    }

    public Double getSacTotalHaber() {
        return this.sacTotalHaber;
    }
    
    public void setSacTotalHaber(Double sacTotalHaber) {
        this.sacTotalHaber = sacTotalHaber;
    }
   








}