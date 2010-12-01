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
 * IucTutTarTpr entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.IucTutTarTpr
 * @author MyEclipse Persistence Tools
 */

public class IucTutTarTprDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(IucTutTarTprDAO.class);

	// property constants

	public IucTutTarTprDAO(Session session) {
		super(session);
	}

	public void save(IucTutTarTpr transientInstance) {
		log.debug("saving IucTutTarTpr instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IucTutTarTpr persistentInstance) {
		log.debug("deleting IucTutTarTpr instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IucTutTarTpr findById(java.lang.Integer id) {
		log.debug("getting IucTutTarTpr instance with id: " + id);
		try {
			IucTutTarTpr instance = (IucTutTarTpr) getSession().get(
					"com.cetia.sicaco.hibernate.IucTutTarTpr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IucTutTarTpr instance) {
		log.debug("finding IucTutTarTpr instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.IucTutTarTpr").add(
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
		log.debug("finding IucTutTarTpr instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IucTutTarTpr as model where model."
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
		log.debug("finding all IucTutTarTpr instances");
		try {
			String queryString = "from IucTutTarTpr";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IucTutTarTpr merge(IucTutTarTpr detachedInstance) {
		log.debug("merging IucTutTarTpr instance");
		try {
			IucTutTarTpr result = (IucTutTarTpr) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IucTutTarTpr instance) {
		log.debug("attaching dirty IucTutTarTpr instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IucTutTarTpr instance) {
		log.debug("attaching clean IucTutTarTpr instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}