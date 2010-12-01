package com.cetia.sicaco.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecMopMenuOpcion entity provides the base persistence definition of
 * the SecMopMenuOpcion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecMopMenuOpcion implements java.io.Serializable {

	// Fields

	private String mopName;
	private SecMopMenuOpcion secMopMenuOpcion;
	private String mopTitle;
	private String mopDescription;
	private String mopLocation;
	private String mopTarget;
	private String mopOnclick;
	private String mopOnmouseover;
	private String mopOnmouseout;
	private String mopImage;
	private String mopAltimge;
	private String mopTooltip;
	private String mopWidth;
	private String mopHeight;
	private String mopForward;
	private String mopAction;
	private String mopModule;
	private Integer mopOrden;
	private String mopTipoSesion;
	private Set secRopRolMenus = new HashSet(0);
	private Set secMopMenuOpcions = new HashSet(0);

	// Constructors

	
	/** default constructor */
	public AbstractSecMopMenuOpcion() {
	}

	/** minimal constructor */
	public AbstractSecMopMenuOpcion(String mopName, String mopTitle) {
		this.mopName = mopName;
		this.mopTitle = mopTitle;
	}

	/** full constructor */
	public AbstractSecMopMenuOpcion(String mopName,
			SecMopMenuOpcion secMopMenuOpcion, String mopTitle,
			String mopDescription, String mopLocation, String mopTarget,
			String mopOnclick, String mopOnmouseover, String mopOnmouseout,
			String mopImage, String mopAltimge, String mopTooltip,
			String mopWidth, String mopHeight, String mopForward,
			String mopAction, String mopModule, Integer mopOrden,
			String mopTipoSesion,	Set secRopRolMenus, Set secMopMenuOpcions) {
		super();
		this.mopName = mopName;
		this.secMopMenuOpcion = secMopMenuOpcion;
		this.mopTitle = mopTitle;
		this.mopDescription = mopDescription;
		this.mopLocation = mopLocation;
		this.mopTarget = mopTarget;
		this.mopOnclick = mopOnclick;
		this.mopOnmouseover = mopOnmouseover;
		this.mopOnmouseout = mopOnmouseout;
		this.mopImage = mopImage;
		this.mopAltimge = mopAltimge;
		this.mopTooltip = mopTooltip;
		this.mopWidth = mopWidth;
		this.mopHeight = mopHeight;
		this.mopForward = mopForward;
		this.mopAction = mopAction;
		this.mopModule = mopModule;
		this.mopOrden = mopOrden;
		this.mopTipoSesion = mopTipoSesion;
		this.secRopRolMenus = secRopRolMenus;
		this.secMopMenuOpcions = secMopMenuOpcions;
	}


	// Property accessors

	public String getMopName() {
		return this.mopName;
	}

	public void setMopName(String mopName) {
		this.mopName = mopName;
	}

	public SecMopMenuOpcion getSecMopMenuOpcion() {
		return this.secMopMenuOpcion;
	}

	public void setSecMopMenuOpcion(SecMopMenuOpcion secMopMenuOpcion) {
		this.secMopMenuOpcion = secMopMenuOpcion;
	}

	public String getMopTitle() {
		return this.mopTitle;
	}

	public void setMopTitle(String mopTitle) {
		this.mopTitle = mopTitle;
	}

	public String getMopDescription() {
		return this.mopDescription;
	}

	public void setMopDescription(String mopDescription) {
		this.mopDescription = mopDescription;
	}

	public String getMopLocation() {
		return this.mopLocation;
	}

	public void setMopLocation(String mopLocation) {
		this.mopLocation = mopLocation;
	}

	public String getMopTarget() {
		return this.mopTarget;
	}

	public void setMopTarget(String mopTarget) {
		this.mopTarget = mopTarget;
	}

	public String getMopOnclick() {
		return this.mopOnclick;
	}

	public void setMopOnclick(String mopOnclick) {
		this.mopOnclick = mopOnclick;
	}

	public String getMopOnmouseover() {
		return this.mopOnmouseover;
	}

	public void setMopOnmouseover(String mopOnmouseover) {
		this.mopOnmouseover = mopOnmouseover;
	}

	public String getMopOnmouseout() {
		return this.mopOnmouseout;
	}

	public void setMopOnmouseout(String mopOnmouseout) {
		this.mopOnmouseout = mopOnmouseout;
	}

	public String getMopImage() {
		return this.mopImage;
	}

	public void setMopImage(String mopImage) {
		this.mopImage = mopImage;
	}

	public String getMopAltimge() {
		return this.mopAltimge;
	}

	public void setMopAltimge(String mopAltimge) {
		this.mopAltimge = mopAltimge;
	}

	public String getMopTooltip() {
		return this.mopTooltip;
	}

	public void setMopTooltip(String mopTooltip) {
		this.mopTooltip = mopTooltip;
	}

	public String getMopWidth() {
		return this.mopWidth;
	}

	public void setMopWidth(String mopWidth) {
		this.mopWidth = mopWidth;
	}

	public String getMopHeight() {
		return this.mopHeight;
	}

	public void setMopHeight(String mopHeight) {
		this.mopHeight = mopHeight;
	}

	public String getMopForward() {
		return this.mopForward;
	}

	public void setMopForward(String mopForward) {
		this.mopForward = mopForward;
	}

	public String getMopAction() {
		return this.mopAction;
	}

	public void setMopAction(String mopAction) {
		this.mopAction = mopAction;
	}

	public String getMopModule() {
		return this.mopModule;
	}

	public void setMopModule(String mopModule) {
		this.mopModule = mopModule;
	}

	public Set getSecRopRolMenus() {
		return this.secRopRolMenus;
	}

	public void setSecRopRolMenus(Set secRopRolMenus) {
		this.secRopRolMenus = secRopRolMenus;
	}

	public Set getSecMopMenuOpcions() {
		return this.secMopMenuOpcions;
	}

	public void setSecMopMenuOpcions(Set secMopMenuOpcions) {
		this.secMopMenuOpcions = secMopMenuOpcions;
	}

	public Integer getMopOrden() {
		return mopOrden;
	}

	public void setMopOrden(Integer mopOrden) {
		this.mopOrden = mopOrden;
	}

	public String getMopTipoSesion() {
		return mopTipoSesion;
	}

	public void setMopTipoSesion(String mopTipoSesion) {
		this.mopTipoSesion = mopTipoSesion;
	}

}