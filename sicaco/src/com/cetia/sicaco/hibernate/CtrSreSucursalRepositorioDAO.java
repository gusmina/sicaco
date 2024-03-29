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
 * CtrSreSucursalRepositorio entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrSreSucursalRepositorio
 * @author MyEclipse Persistence Tools
 */

public class CtrSreSucursalRepositorioDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtrSreSucursalRepositorioDAO.class);

	// property constants

	public CtrSreSucursalRepositorioDAO(Session session) {
		super(session);
	}

	public void save(CtrSreSucursalRepositorio transientInstance) {
		log.debug("saving CtrSreSucursalRepositorio instance");
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

	public void delete(CtrSreSucursalRepositorio persistentInstance) {
		log.debug("deleting CtrSreSucursalRepositorio instance");
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

	public CtrSreSucursalRepositorio findById(
			com.cetia.sicaco.hibernate.CtrSreSucursalRepositorioId id) {
		log.debug("getting CtrSreSucursalRepositorio instance with id: " + id);
		try {
			CtrSreSucursalRepositorio instance = (CtrSreSucursalRepositorio) getSession()
					.get(
							"com.cetia.sicaco.hibernate.CtrSreSucursalRepositorio",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrSreSucursalRepositorio instance) {
		log.debug("finding CtrSreSucursalRepositorio instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrSreSucursalRepositorio")
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
		log.debug("finding CtrSreSucursalRepositorio instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrSreSucursalRepositorio as model where model."
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
		log.debug("finding all CtrSreSucursalRepositorio instances");
		try {
			String queryString = "from CtrSreSucursalRepositorio";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrSreSucursalRepositorio merge(
			CtrSreSucursalRepositorio detachedInstance) {
		log.debug("merging CtrSreSucursalRepositorio instance");
		try {
			CtrSreSucursalRepositorio result = (CtrSreSucursalRepositorio) getSession()
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

	public void attachDirty(CtrSreSucursalRepositorio instance) {
		log.debug("attaching dirty CtrSreSucursalRepositorio instance");
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

	public void attachClean(CtrSreSucursalRepositorio instance) {
		log.debug("attaching clean CtrSreSucursalRepositorio instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}