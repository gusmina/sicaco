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
 * IucPutProveedorTipoPrestamo entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.IucPutProveedorTipoPrestamo
 * @author MyEclipse Persistence Tools
 */

public class IucPutProveedorTipoPrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(IucPutProveedorTipoPrestamoDAO.class);

	// property constants

	public IucPutProveedorTipoPrestamoDAO(Session session) {
		super(session);
	}

	public void save(IucPutProveedorTipoPrestamo transientInstance) {
		log.debug("saving IucPutProveedorTipoPrestamo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IucPutProveedorTipoPrestamo persistentInstance) {
		log.debug("deleting IucPutProveedorTipoPrestamo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IucPutProveedorTipoPrestamo findById(java.lang.Integer id) {
		log
				.debug("getting IucPutProveedorTipoPrestamo instance with id: "
						+ id);
		try {
			IucPutProveedorTipoPrestamo instance = (IucPutProveedorTipoPrestamo) getSession()
					.get(
							"com.cetia.sicaco.hibernate.IucPutProveedorTipoPrestamo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IucPutProveedorTipoPrestamo instance) {
		log.debug("finding IucPutProveedorTipoPrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.IucPutProveedorTipoPrestamo")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log
				.debug("finding IucPutProveedorTipoPrestamo instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from IucPutProveedorTipoPrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IucPutProveedorTipoPrestamo instances");
		try {
			String queryString = "from IucPutProveedorTipoPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IucPutProveedorTipoPrestamo merge(
			IucPutProveedorTipoPrestamo detachedInstance) {
		log.debug("merging IucPutProveedorTipoPrestamo instance");
		try {
			IucPutProveedorTipoPrestamo result = (IucPutProveedorTipoPrestamo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IucPutProveedorTipoPrestamo instance) {
		log.debug("attaching dirty IucPutProveedorTipoPrestamo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IucPutProveedorTipoPrestamo instance) {
		log.debug("attaching clean IucPutProveedorTipoPrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}