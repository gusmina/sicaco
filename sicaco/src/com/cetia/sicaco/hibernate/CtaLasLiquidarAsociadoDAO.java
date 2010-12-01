package com.cetia.sicaco.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaLasLiquidarAsociado entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaLasLiquidarAsociado
 * @author MyEclipse Persistence Tools
 */

public class CtaLasLiquidarAsociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaLasLiquidarAsociadoDAO.class);

	// property constants

	public CtaLasLiquidarAsociadoDAO(Session session) {
		super(session);
	}

	public void save(CtaLasLiquidarAsociado transientInstance) {
		log.debug("saving CtaLasLiquidarAsociado instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaLasLiquidarAsociado persistentInstance) {
		log.debug("deleting CtaLasLiquidarAsociado instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaLasLiquidarAsociado findById(
			com.cetia.sicaco.hibernate.CtaLasLiquidarAsociadoId id) {
		log.debug("getting CtaLasLiquidarAsociado instance with id: " + id);
		try {
			CtaLasLiquidarAsociado instance = (CtaLasLiquidarAsociado) getSession()
					.get("com.cetia.sicaco.hibernate.CtaLasLiquidarAsociado",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaLasLiquidarAsociado instance) {
		log.debug("finding CtaLasLiquidarAsociado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaLasLiquidarAsociado").add(
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
		log.debug("finding CtaLasLiquidarAsociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaLasLiquidarAsociado as model where model."
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
		log.debug("finding all CtaLasLiquidarAsociado instances");
		try {
			String queryString = "from CtaLasLiquidarAsociado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaLasLiquidarAsociado merge(CtaLasLiquidarAsociado detachedInstance) {
		log.debug("merging CtaLasLiquidarAsociado instance");
		try {
			CtaLasLiquidarAsociado result = (CtaLasLiquidarAsociado) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaLasLiquidarAsociado instance) {
		log.debug("attaching dirty CtaLasLiquidarAsociado instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaLasLiquidarAsociado instance) {
		log.debug("attaching clean CtaLasLiquidarAsociado instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public void deleteAll() {
		log.debug("deleting all instances of CtaLasLiquidarAsociado ");
		try {
			Transaction tx = this.getSession().beginTransaction();
			String queryString = "delete from CtaLasLiquidarAsociado";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.executeUpdate();
			tx.commit();
			this.getSession().flush();
			this.getSession().clear();
		} catch (RuntimeException re) {
			log.error("deleting all instances of CtaLasLiquidarAsociado failed", re);
			throw re;
		}
	}
}