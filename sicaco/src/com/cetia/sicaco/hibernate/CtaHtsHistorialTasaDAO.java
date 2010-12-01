package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaHtsHistorialTasa entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaHtsHistorialTasa
 * @author MyEclipse Persistence Tools
 */

public class CtaHtsHistorialTasaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaHtsHistorialTasaDAO.class);
	// property constants
	public static final String HTS_TASA = "htsTasa";

	public CtaHtsHistorialTasaDAO(Session session) {
		super(session);
	}

	public void save(CtaHtsHistorialTasa transientInstance) {
		log.debug("saving CtaHtsHistorialTasa instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaHtsHistorialTasa persistentInstance) {
		log.debug("deleting CtaHtsHistorialTasa instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaHtsHistorialTasa findById(java.lang.Integer id) {
		log.debug("getting CtaHtsHistorialTasa instance with id: " + id);
		try {
			CtaHtsHistorialTasa instance = (CtaHtsHistorialTasa) getSession()
					.get("com.cetia.sicaco.hibernate.CtaHtsHistorialTasa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaHtsHistorialTasa instance) {
		log.debug("finding CtaHtsHistorialTasa instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaHtsHistorialTasa").add(
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
		log.debug("finding CtaHtsHistorialTasa instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaHtsHistorialTasa as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHtsTasa(Object htsTasa) {
		return findByProperty(HTS_TASA, htsTasa);
	}

	public List findAll() {
		log.debug("finding all CtaHtsHistorialTasa instances");
		try {
			String queryString = "from CtaHtsHistorialTasa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaHtsHistorialTasa merge(CtaHtsHistorialTasa detachedInstance) {
		log.debug("merging CtaHtsHistorialTasa instance");
		try {
			CtaHtsHistorialTasa result = (CtaHtsHistorialTasa) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaHtsHistorialTasa instance) {
		log.debug("attaching dirty CtaHtsHistorialTasa instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaHtsHistorialTasa instance) {
		log.debug("attaching clean CtaHtsHistorialTasa instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public CtaHtsHistorialTasa findLatest(Integer tinId) {
		log.debug("finding all CtaHtsHistorialTasa instances");
		try {
			String queryString = "from CtaHtsHistorialTasa htsn where htsn.htsId = (" +
					"select max(hts.htsId) from CtaHtsHistorialTasa hts " +
					"where hts.ctaTinTasaInteres.tinId = " +tinId + ")";
			Query queryObject = getSession().createQuery(queryString);
			return (CtaHtsHistorialTasa) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}