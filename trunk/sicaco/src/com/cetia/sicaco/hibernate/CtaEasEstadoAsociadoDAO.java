package com.cetia.sicaco.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaEasEstadoAsociado entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaEasEstadoAsociado
 * @author MyEclipse Persistence Tools
 */

public class CtaEasEstadoAsociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaEasEstadoAsociadoDAO.class);
	// property constants
	public static final String EAS_NOMBRE = "easNombre";
	public static final String EAS_DESCRIPCION = "easDescripcion";

	public CtaEasEstadoAsociadoDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void save(CtaEasEstadoAsociado transientInstance) {
		log.debug("saving CtaEasEstadoAsociado instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaEasEstadoAsociado persistentInstance) {
		log.debug("deleting CtaEasEstadoAsociado instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaEasEstadoAsociado findById(java.lang.Integer id) {
		log.debug("getting CtaEasEstadoAsociado instance with id: " + id);
		try {
			CtaEasEstadoAsociado instance = (CtaEasEstadoAsociado) getSession()
					.get("com.cetia.sicaco.hibernate.CtaEasEstadoAsociado", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaEasEstadoAsociado instance) {
		log.debug("finding CtaEasEstadoAsociado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaEasEstadoAsociado").add(
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
		log.debug("finding CtaEasEstadoAsociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaEasEstadoAsociado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEasNombre(Object easNombre) {
		return findByProperty(EAS_NOMBRE, easNombre);
	}

	public List findByEasDescripcion(Object easDescripcion) {
		return findByProperty(EAS_DESCRIPCION, easDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaEasEstadoAsociado instances");
		try {
			String queryString = "from CtaEasEstadoAsociado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaEasEstadoAsociado merge(CtaEasEstadoAsociado detachedInstance) {
		log.debug("merging CtaEasEstadoAsociado instance");
		try {
			CtaEasEstadoAsociado result = (CtaEasEstadoAsociado) getSession()
					.merge(detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaEasEstadoAsociado instance) {
		log.debug("attaching dirty CtaEasEstadoAsociado instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaEasEstadoAsociado instance) {
		log.debug("attaching clean CtaEasEstadoAsociado instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}