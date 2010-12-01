package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaRxpRetencionPrestamo entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaRxpRetencionPrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaRxpRetencionPrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaRxpRetencionPrestamoDAO.class);

	public CtaRxpRetencionPrestamoDAO(Session session) {
		super(session);
	}

	public void save(CtaRxpRetencionPrestamo transientInstance) {
		log.debug("saving CtaRxpRetencionPrestamo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaRxpRetencionPrestamo persistentInstance) {
		log.debug("deleting CtaRxpRetencionPrestamo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaRxpRetencionPrestamo findById(java.lang.Integer id) {
		log.debug("getting CtaRxpRetencionPrestamo instance with id: " + id);
		try {
			CtaRxpRetencionPrestamo instance = (CtaRxpRetencionPrestamo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaRxpRetencionPrestamo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaRxpRetencionPrestamo instance) {
		log.debug("finding CtaRxpRetencionPrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaRxpRetencionPrestamo").add(
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
		log.debug("finding CtaRxpRetencionPrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaRxpRetencionPrestamo as model where model."
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
		log.debug("finding all CtaRxpRetencionPrestamo instances");
		try {
			String queryString = "from CtaRxpRetencionPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaRxpRetencionPrestamo merge(
			CtaRxpRetencionPrestamo detachedInstance) {
		log.debug("merging CtaRxpRetencionPrestamo instance");
		try {
			CtaRxpRetencionPrestamo result = (CtaRxpRetencionPrestamo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaRxpRetencionPrestamo instance) {
		log.debug("attaching dirty CtaRxpRetencionPrestamo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaRxpRetencionPrestamo instance) {
		log.debug("attaching clean CtaRxpRetencionPrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(rxp.rxpId) + 1  as id from CtaRxpRetencionPrestamo rxp";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	}
}