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
 * ConLofLiquidacionOficina entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.ConLofLiquidacionOficina
 * @author MyEclipse Persistence Tools
 */

public class ConLofLiquidacionOficinaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConLofLiquidacionOficinaDAO.class);
	// property constants
	public static final String CUE_ID = "cueId";
	public static final String CUE_NOMBRE = "cueNombre";
	public static final String VALOR_DEPOSITADO = "valorDepositado";
	public static final String SUC_ID = "sucId";

	public ConLofLiquidacionOficinaDAO(Session session) {
		super(session);
	}

	public void save(ConLofLiquidacionOficina transientInstance) {
		log.debug("saving ConLofLiquidacionOficina instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConLofLiquidacionOficina persistentInstance) {
		log.debug("deleting ConLofLiquidacionOficina instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConLofLiquidacionOficina findById(java.lang.Long id) {
		log.debug("getting ConLofLiquidacionOficina instance with id: " + id);
		try {
			ConLofLiquidacionOficina instance = (ConLofLiquidacionOficina) getSession()
					.get("com.cetia.sicaco.hibernate.ConLofLiquidacionOficina",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConLofLiquidacionOficina instance) {
		log.debug("finding ConLofLiquidacionOficina instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConLofLiquidacionOficina").add(
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
		log.debug("finding ConLofLiquidacionOficina instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConLofLiquidacionOficina as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCueId(Object cueId) {
		return findByProperty(CUE_ID, cueId);
	}

	public List findByCueNombre(Object cueNombre) {
		return findByProperty(CUE_NOMBRE, cueNombre);
	}

	public List findByValorDepositado(Object valorDepositado) {
		return findByProperty(VALOR_DEPOSITADO, valorDepositado);
	}

	public List findBySucId(Object sucId) {
		return findByProperty(SUC_ID, sucId);
	}

	public List findAll() {
		log.debug("finding all ConLofLiquidacionOficina instances");
		try {
			String queryString = "from ConLofLiquidacionOficina";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConLofLiquidacionOficina merge(
			ConLofLiquidacionOficina detachedInstance) {
		log.debug("merging ConLofLiquidacionOficina instance");
		try {
			ConLofLiquidacionOficina result = (ConLofLiquidacionOficina) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConLofLiquidacionOficina instance) {
		log.debug("attaching dirty ConLofLiquidacionOficina instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConLofLiquidacionOficina instance) {
		log.debug("attaching clean ConLofLiquidacionOficina instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}