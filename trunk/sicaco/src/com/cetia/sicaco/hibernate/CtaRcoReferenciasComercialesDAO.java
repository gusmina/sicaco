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
 * CtaRcoReferenciasComerciales entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaRcoReferenciasComerciales
 * @author MyEclipse Persistence Tools
 */

public class CtaRcoReferenciasComercialesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaRcoReferenciasComercialesDAO.class);
	// property constants
	public static final String RCO_REFERENCIA = "rcoReferencia";
	public static final String RCO_SUCURSAL = "rcoSucursal";
	public static final String RCO_MONTO = "rcoMonto";
	public static final String RCO_ESTADO = "rcoEstado";

	public CtaRcoReferenciasComercialesDAO(Session session) {
		super(session);
	}

	public void save(CtaRcoReferenciasComerciales transientInstance) {
		log.debug("saving CtaRcoReferenciasComerciales instance");
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

	public void delete(CtaRcoReferenciasComerciales persistentInstance) {
		log.debug("deleting CtaRcoReferenciasComerciales instance");
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

	public CtaRcoReferenciasComerciales findById(java.lang.Integer id) {
		log.debug("getting CtaRcoReferenciasComerciales instance with id: "
				+ id);
		try {
			CtaRcoReferenciasComerciales instance = (CtaRcoReferenciasComerciales) getSession()
					.get(
							"com.cetia.sicaco.hibernate.CtaRcoReferenciasComerciales",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaRcoReferenciasComerciales instance) {
		log.debug("finding CtaRcoReferenciasComerciales instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaRcoReferenciasComerciales")
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
				.debug("finding CtaRcoReferenciasComerciales instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaRcoReferenciasComerciales as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRcoReferencia(Object rcoReferencia) {
		return findByProperty(RCO_REFERENCIA, rcoReferencia);
	}

	public List findByRcoSucursal(Object rcoSucursal) {
		return findByProperty(RCO_SUCURSAL, rcoSucursal);
	}

	public List findByRcoMonto(Object rcoMonto) {
		return findByProperty(RCO_MONTO, rcoMonto);
	}

	public List findByRcoEstado(Object rcoEstado) {
		return findByProperty(RCO_ESTADO, rcoEstado);
	}

	public List findAll() {
		log.debug("finding all CtaRcoReferenciasComerciales instances");
		try {
			String queryString = "from CtaRcoReferenciasComerciales";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaRcoReferenciasComerciales merge(
			CtaRcoReferenciasComerciales detachedInstance) {
		log.debug("merging CtaRcoReferenciasComerciales instance");
		try {
			CtaRcoReferenciasComerciales result = (CtaRcoReferenciasComerciales) getSession()
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

	public void attachDirty(CtaRcoReferenciasComerciales instance) {
		log.debug("attaching dirty CtaRcoReferenciasComerciales instance");
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

	public void attachClean(CtaRcoReferenciasComerciales instance) {
		log.debug("attaching clean CtaRcoReferenciasComerciales instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}