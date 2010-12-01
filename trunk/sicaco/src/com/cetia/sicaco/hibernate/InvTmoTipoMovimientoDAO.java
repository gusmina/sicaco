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
 * InvTmoTipoMovimiento entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvTmoTipoMovimiento
 * @author MyEclipse Persistence Tools
 */

public class InvTmoTipoMovimientoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvTmoTipoMovimientoDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";

	public InvTmoTipoMovimientoDAO(Session session) {
		super(session);
	}

	public void save(InvTmoTipoMovimiento transientInstance) {
		log.debug("saving InvTmoTipoMovimiento instance");
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

	public void delete(InvTmoTipoMovimiento persistentInstance) {
		log.debug("deleting InvTmoTipoMovimiento instance");
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

	public InvTmoTipoMovimiento findById(java.lang.Integer id) {
		log.debug("getting InvTmoTipoMovimiento instance with id: " + id);
		try {
			InvTmoTipoMovimiento instance = (InvTmoTipoMovimiento) getSession()
					.get("com.cetia.sicaco.hibernate.InvTmoTipoMovimiento", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvTmoTipoMovimiento instance) {
		log.debug("finding InvTmoTipoMovimiento instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvTmoTipoMovimiento").add(
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
		log.debug("finding InvTmoTipoMovimiento instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvTmoTipoMovimiento as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List findAll() {
		log.debug("finding all InvTmoTipoMovimiento instances");
		try {
			String queryString = "from InvTmoTipoMovimiento";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvTmoTipoMovimiento merge(InvTmoTipoMovimiento detachedInstance) {
		log.debug("merging InvTmoTipoMovimiento instance");
		try {
			InvTmoTipoMovimiento result = (InvTmoTipoMovimiento) getSession()
					.merge(detachedInstance);
			getSession().flush();
			getSession().clear();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvTmoTipoMovimiento instance) {
		log.debug("attaching dirty InvTmoTipoMovimiento instance");
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

	public void attachClean(InvTmoTipoMovimiento instance) {
		log.debug("attaching clean InvTmoTipoMovimiento instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}