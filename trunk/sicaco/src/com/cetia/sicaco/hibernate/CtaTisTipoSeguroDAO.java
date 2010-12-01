package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMessages;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaTisTipoSeguro entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTisTipoSeguro
 * @author MyEclipse Persistence Tools
 */

public class CtaTisTipoSeguroDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaTisTipoSeguroDAO.class);
	// property constants
	public static final String TIS_NOMBRE = "tisNombre";
	public static final String TIS_DESCRIPCION = "tisDescripcion";
	public static final String TIS_POLIZA = "tisPoliza";
	public static final String TIS_INICIO_POLIZA = "tisInicioPoliza";
	public static final String TIS_FIN_POLIZA	= "tisFinPoliza";

	public CtaTisTipoSeguroDAO(Session session) {
		super(session);
	}

	public void save(CtaTisTipoSeguro transientInstance) {
		log.debug("saving CtaTisTipoSeguro instance");
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

	public void delete(CtaTisTipoSeguro persistentInstance) {
		log.debug("deleting CtaTisTipoSeguro instance");
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

	public CtaTisTipoSeguro findById(java.lang.Integer id) {
		log.debug("getting CtaTisTipoSeguro instance with id: " + id);
		try {
			CtaTisTipoSeguro instance = (CtaTisTipoSeguro) getSession().get(
					"com.cetia.sicaco.hibernate.CtaTisTipoSeguro", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTisTipoSeguro instance) {
		log.debug("finding CtaTisTipoSeguro instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTisTipoSeguro").add(
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
		log.debug("finding CtaTisTipoSeguro instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTisTipoSeguro as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTisNombre(Object tisNombre) {
		return findByProperty(TIS_NOMBRE, tisNombre);
	}

	public List findByTisDescripcion(Object tisDescripcion) {
		return findByProperty(TIS_DESCRIPCION, tisDescripcion);
	}

	public List findByTisPoliza(Object tisPoliza) {
		return findByProperty(TIS_POLIZA, tisPoliza);
	}
	
	public List findByTisInicioPoliza(Object tisInicioPoliza) {
		return findByProperty(TIS_INICIO_POLIZA, tisInicioPoliza);
	}
	
	public List findByTisFinPoliza(Object tisFinPoliza) {
		return findByProperty(TIS_FIN_POLIZA, tisFinPoliza);
	}

	public List findAll() {
		log.debug("finding all CtaTisTipoSeguro instances");
		try {
			String queryString = "from CtaTisTipoSeguro";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTisTipoSeguro merge(CtaTisTipoSeguro detachedInstance) {
		log.debug("merging CtaTisTipoSeguro instance");
		try {
			CtaTisTipoSeguro result = (CtaTisTipoSeguro) getSession().merge(
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

	public void attachDirty(CtaTisTipoSeguro instance) {
		log.debug("attaching dirty CtaTisTipoSeguro instance");
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

	public void attachClean(CtaTisTipoSeguro instance) {
		log.debug("attaching clean CtaTisTipoSeguro instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUpdatedName(Integer tisId, String tisNombre) {
		try{
			String sql = "from CtaTisTipoSeguro tis where tis.tisId != ? and tis.tisNombre = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, tisId);
			queryObject.setParameter(1, tisNombre);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}