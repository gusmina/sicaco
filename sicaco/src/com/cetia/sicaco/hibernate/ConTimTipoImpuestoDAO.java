package com.cetia.sicaco.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConTimTipoImpuesto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConTimTipoImpuesto
 * @author MyEclipse Persistence Tools
 */

public class ConTimTipoImpuestoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConTimTipoImpuestoDAO.class);
	// property constants
	public static final String TIM_NOMBRE = "timNombre";
	public static final String TIM_DESCRIPCION = "timDescripcion";

	public ConTimTipoImpuestoDAO(Session session) {
		super(session);
	}

	public void save(ConTimTipoImpuesto transientInstance) {
		log.debug("saving ConTimTipoImpuesto instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConTimTipoImpuesto persistentInstance) {
		log.debug("deleting ConTimTipoImpuesto instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConTimTipoImpuesto findById(java.lang.String id) {
		log.debug("getting ConTimTipoImpuesto instance with id: " + id);
		try {
			ConTimTipoImpuesto instance = (ConTimTipoImpuesto) getSession()
					.get("com.cetia.sicaco.hibernate.ConTimTipoImpuesto", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConTimTipoImpuesto instance) {
		log.debug("finding ConTimTipoImpuesto instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConTimTipoImpuesto").add(
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
		log.debug("finding ConTimTipoImpuesto instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConTimTipoImpuesto as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTimNombre(Object timNombre) {
		return findByProperty(TIM_NOMBRE, timNombre);
	}

	public List findByTimDescripcion(Object timDescripcion) {
		return findByProperty(TIM_DESCRIPCION, timDescripcion);
	}

	public List findAll() {
		log.debug("finding all ConTimTipoImpuesto instances");
		try {
			String queryString = "from ConTimTipoImpuesto";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConTimTipoImpuesto merge(ConTimTipoImpuesto detachedInstance) {
		log.debug("merging ConTimTipoImpuesto instance");
		try {
			ConTimTipoImpuesto result = (ConTimTipoImpuesto) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConTimTipoImpuesto instance) {
		log.debug("attaching dirty ConTimTipoImpuesto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConTimTipoImpuesto instance) {
		log.debug("attaching clean ConTimTipoImpuesto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}