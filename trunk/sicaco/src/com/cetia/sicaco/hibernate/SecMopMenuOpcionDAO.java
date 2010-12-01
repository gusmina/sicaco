package com.cetia.sicaco.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.sun.org.apache.xpath.internal.Expression;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecMopMenuOpcion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecMopMenuOpcion
 * @author MyEclipse Persistence Tools
 */

public class SecMopMenuOpcionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecMopMenuOpcionDAO.class);
	// property constants
	public static final String MOP_TITLE = "mopTitle";
	public static final String MOP_DESCRIPTION = "mopDescription";
	public static final String MOP_LOCATION = "mopLocation";
	public static final String MOP_TARGET = "mopTarget";
	public static final String MOP_ONCLICK = "mopOnclick";
	public static final String MOP_ONMOUSEOVER = "mopOnmouseover";
	public static final String MOP_ONMOUSEOUT = "mopOnmouseout";
	public static final String MOP_IMAGE = "mopImage";
	public static final String MOP_ALTIMGE = "mopAltimge";
	public static final String MOP_TOOLTIP = "mopTooltip";
	public static final String MOP_WIDTH = "mopWidth";
	public static final String MOP_HEIGHT = "mopHeight";
	public static final String MOP_FORWARD = "mopForward";
	public static final String MOP_ACTION = "mopAction";
	public static final String MOP_MODULE = "mopModule";
	public static final String MOP_ORDEN = "mopOrden";
	public static final String MOP_TIPO_SESION = "mopTipoSesion";

	public SecMopMenuOpcionDAO(Session session) {
		super(session);
	}

	public void save(SecMopMenuOpcion transientInstance) {
		log.debug("saving SecMopMenuOpcion instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			getSession().clear();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecMopMenuOpcion persistentInstance) {
		log.debug("deleting SecMopMenuOpcion instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			getSession().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecMopMenuOpcion findById(java.lang.String id) {
		log.debug("getting SecMopMenuOpcion instance with id: " + id);
		try {
			SecMopMenuOpcion instance = (SecMopMenuOpcion) getSession().get(
					"com.cetia.sicaco.hibernate.SecMopMenuOpcion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecMopMenuOpcion instance) {
		log.debug("finding SecMopMenuOpcion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecMopMenuOpcion").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SecMopMenuOpcion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecMopMenuOpcion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMopTitle(Object mopTitle) {
		return findByProperty(MOP_TITLE, mopTitle);
	}

	public List findByMopDescription(Object mopDescription) {
		return findByProperty(MOP_DESCRIPTION, mopDescription);
	}

	public List findByMopLocation(Object mopLocation) {
		return findByProperty(MOP_LOCATION, mopLocation);
	}

	public List findByMopTarget(Object mopTarget) {
		return findByProperty(MOP_TARGET, mopTarget);
	}

	public List findByMopOnclick(Object mopOnclick) {
		return findByProperty(MOP_ONCLICK, mopOnclick);
	}

	public List findByMopOnmouseover(Object mopOnmouseover) {
		return findByProperty(MOP_ONMOUSEOVER, mopOnmouseover);
	}

	public List findByMopOnmouseout(Object mopOnmouseout) {
		return findByProperty(MOP_ONMOUSEOUT, mopOnmouseout);
	}

	public List findByMopImage(Object mopImage) {
		return findByProperty(MOP_IMAGE, mopImage);
	}

	public List findByMopAltimge(Object mopAltimge) {
		return findByProperty(MOP_ALTIMGE, mopAltimge);
	}

	public List findByMopTooltip(Object mopTooltip) {
		return findByProperty(MOP_TOOLTIP, mopTooltip);
	}

	public List findByMopWidth(Object mopWidth) {
		return findByProperty(MOP_WIDTH, mopWidth);
	}

	public List findByMopHeight(Object mopHeight) {
		return findByProperty(MOP_HEIGHT, mopHeight);
	}

	public List findByMopForward(Object mopForward) {
		return findByProperty(MOP_FORWARD, mopForward);
	}

	public List findByMopAction(Object mopAction) {
		return findByProperty(MOP_ACTION, mopAction);
	}

	public List findByMopModule(Object mopModule) {
		return findByProperty(MOP_MODULE, mopModule);
	}
	
	public List findByMopOrden(Object mopOrden) {
		return findByProperty(MOP_ORDEN, mopOrden);
	}
	
	public List findByMopTipoSesion(Object mopTipoSesion) {
		return findByProperty(MOP_TIPO_SESION, mopTipoSesion);
	}

	public List findAll() {
		log.debug("finding all SecMopMenuOpcion instances");
		try {
			String queryString = "from SecMopMenuOpcion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecMopMenuOpcion merge(SecMopMenuOpcion detachedInstance) {
		log.debug("merging SecMopMenuOpcion instance");
		try {
			SecMopMenuOpcion result = (SecMopMenuOpcion) getSession().merge(
					detachedInstance);
			getSession().flush();
			getSession().clear();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecMopMenuOpcion instance) {
		log.debug("attaching dirty SecMopMenuOpcion instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			getSession().clear();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecMopMenuOpcion instance) {
		log.debug("attaching clean SecMopMenuOpcion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//**** Funcion creada para obtener todos los parent menus para ser usada en rolMenuDML.jsp
	public List findAllParent() {
		log.debug("finding all SecMopMenuOpcion instances");
		try {
			String queryString = "from SecMopMenuOpcion ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllParentNull() {
		log.debug("finding all SecMopMenuOpcion instances");
		try {
			String queryString = "from SecMopMenuOpcion mop where mop.secMopMenuOpcion.mopName is null order by mop.mopOrden";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllSons(String parent) {
		log.debug("finding all SecMopMenuOpcion instances");
		try {
			String queryString = "from SecMopMenuOpcion mop where mop.secMopMenuOpcion.mopName ='" + parent +"' order by mop.mopOrden";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}