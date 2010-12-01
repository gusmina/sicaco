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
 * CtaDexDescuentosExternos entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaDexDescuentosExternos
 * @author MyEclipse Persistence Tools
 */

public class CtaDexDescuentosExternosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaDexDescuentosExternosDAO.class);
	// property constants
	public static final String DEX_MONTO = "dexMonto";
	public static final String DEX_NOMBRE_DESCUENTO = "dexNombreDescuento";

	public CtaDexDescuentosExternosDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void save(CtaDexDescuentosExternos transientInstance) {
		log.debug("saving CtaDexDescuentosExternos instance");
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

	public void delete(CtaDexDescuentosExternos persistentInstance) {
		log.debug("deleting CtaDexDescuentosExternos instance");
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

	public CtaDexDescuentosExternos findById(java.lang.Integer id) {
		log.debug("getting CtaDexDescuentosExternos instance with id: " + id);
		try {
			CtaDexDescuentosExternos instance = (CtaDexDescuentosExternos) getSession()
					.get("com.cetia.sicaco.hibernate.CtaDexDescuentosExternos",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaDexDescuentosExternos instance) {
		log.debug("finding CtaDexDescuentosExternos instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaDexDescuentosExternos").add(
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
		log.debug("finding CtaDexDescuentosExternos instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaDexDescuentosExternos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDexMonto(Object dexMonto) {
		return findByProperty(DEX_MONTO, dexMonto);
	}

	public List findByDexNombreDescuento(Object dexNombreDescuento) {
		return findByProperty(DEX_NOMBRE_DESCUENTO, dexNombreDescuento);
	}

	public List findAll() {
		log.debug("finding all CtaDexDescuentosExternos instances");
		try {
			String queryString = "from CtaDexDescuentosExternos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	public CtaDexDescuentosExternos merge(
			CtaDexDescuentosExternos detachedInstance) {
		log.debug("merging CtaDexDescuentosExternos instance");
		try {
			CtaDexDescuentosExternos result = (CtaDexDescuentosExternos) getSession()
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

	public void attachDirty(CtaDexDescuentosExternos instance) {
		log.debug("attaching dirty CtaDexDescuentosExternos instance");
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

	public void attachClean(CtaDexDescuentosExternos instance) {
		log.debug("attaching clean CtaDexDescuentosExternos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Double findDescuentosByAsociado(String ascId) {
		try {
			Double descuentoTotal = new Double(0);
			String queryString = "select sum(c.ctaPrePrestamo.preCuota) from CtaCasCuentaAsociado as c where c.ctaAscAsociado.ascId=? group by c.ctaAscAsociado.ascId";//extrayendo el total de descuentos de los prestamos
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			descuentoTotal += (Double) queryObject.uniqueResult();
			queryString = "select sum(c.ctaSegSeguros.segCuota) from CtaCasCuentaAsociado as c where c.ctaAscAsociado.ascId=? group by c.ctaAscAsociado.ascId";//extrayendo el total de descuentos de los seguros.
			queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			descuentoTotal += (Double) queryObject.uniqueResult();
			queryString = "select sum(c.ctaCahCuentaAhorro.cahCuota) from CtaCasCuentaAsociado as c where c.ctaAscAsociado.ascId=? and substring(c.ctaCahCuentaAhorro.cahId,1,1) = 'A' group by c.ctaAscAsociado.ascId";//estrayendo el total de los descuentos de aportaciones
			queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			descuentoTotal += (Double) queryObject.uniqueResult();
			return descuentoTotal;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}