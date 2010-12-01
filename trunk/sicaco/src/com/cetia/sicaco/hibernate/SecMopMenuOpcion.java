package com.cetia.sicaco.hibernate;

import java.util.Set;

/**
 * SecMopMenuOpcion entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class SecMopMenuOpcion extends AbstractSecMopMenuOpcion implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecMopMenuOpcion() {
	}

	/** minimal constructor */
	public SecMopMenuOpcion(String mopName, String mopTitle) {
		super(mopName, mopTitle);
	}

	public SecMopMenuOpcion(String mopName, SecMopMenuOpcion secMopMenuOpcion,
			String mopTitle, String mopDescription, String mopLocation,
			String mopTarget, String mopOnclick, String mopOnmouseover,
			String mopOnmouseout, String mopImage, String mopAltimge,
			String mopTooltip, String mopWidth, String mopHeight,
			String mopForward, String mopAction, String mopModule,
			Integer mopOrden, String mopTipoSesion, Set secRopRolMenus,
			Set secMopMenuOpcions) {
		super(mopName, secMopMenuOpcion, mopTitle, mopDescription, mopLocation,
				mopTarget, mopOnclick, mopOnmouseover, mopOnmouseout, mopImage,
				mopAltimge, mopTooltip, mopWidth, mopHeight, mopForward, mopAction,
				mopModule, mopOrden, mopTipoSesion, secRopRolMenus, secMopMenuOpcions);
		// TODO Auto-generated constructor stub
	}

	/** full constructor */

}
