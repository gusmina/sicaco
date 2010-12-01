package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Set;

/**
 * InvSmeSaldoMensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class InvSmeSaldoMensual extends AbstractInvSmeSaldoMensual implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InvSmeSaldoMensual() {
	}

	/** minimal constructor */
	public InvSmeSaldoMensual(Integer smeId, InvArtArticulo invArtArticulo,
			Double smeCantidad, Date smeFecha, Double smeSaldo, Double smeCostoArt) {
		super(smeId, invArtArticulo, smeCantidad, smeFecha, smeSaldo, smeCostoArt);
		// TODO Auto-generated constructor stub
	}

}
