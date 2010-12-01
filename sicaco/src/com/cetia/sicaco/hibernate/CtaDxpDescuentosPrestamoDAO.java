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
 * CtaDxpDescuentosPrestamo entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaDxpDescuentosPrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaDxpDescuentosPrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaDxpDescuentosPrestamoDAO.class);

	public CtaDxpDescuentosPrestamoDAO(Session session) {
		super(session);
	}

	public void save(CtaDxpDescuentosPrestamo transientInstance) {
		log.debug("saving CtaDxpDescuentosPrestamo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaDxpDescuentosPrestamo persistentInstance) {
		log.debug("deleting CtaDxpDescuentosPrestamo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaDxpDescuentosPrestamo findById(java.lang.Integer id) {
		log.debug("getting CtaDxpDescuentosPrestamo instance with id: " + id);
		try {
			CtaDxpDescuentosPrestamo instance = (CtaDxpDescuentosPrestamo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaDxpDescuentosPrestamo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaDxpDescuentosPrestamo instance) {
		log.debug("finding CtaDxpDescuentosPrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaDxpDescuentosPrestamo").add(
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
		log.debug("finding CtaDxpDescuentosPrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaDxpDescuentosPrestamo as model where model."
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
		log.debug("finding all CtaDxpDescuentosPrestamo instances");
		try {
			String queryString = "from CtaDxpDescuentosPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByPreIdAndTipoCuenta(String preId, String tipoCuenta) {
		log.debug("finding CtaDxpDescuentosPrestamo instance by preIdAndTipoCuenta");
		try {
			String queryString = "from CtaDxpDescuentosPrestamo as model "+
								 "where model.ctaPrePrestamoByPreId.preId= ?";
			
			if(tipoCuenta.equals("B")){
				queryString += " and model.ctaCahCuentaAhorro.cahId is not null "+
				" and substring(model.ctaCahCuentaAhorro.cahId,1,1)='B'";
			}
			if(tipoCuenta.equals("C")){
				queryString += " and model.ctaPrePrestamoByPreId2 is not null";
			}
			if(tipoCuenta.equals("D")){
				queryString += " and model.ctaSegSeguros is not null";
			}								 
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, preId);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by preIdAndTipoCuenta failed", re);
			throw re;
		}
	}
	
	public CtaDxpDescuentosPrestamo merge(
			CtaDxpDescuentosPrestamo detachedInstance) {
		log.debug("merging CtaDxpDescuentosPrestamo instance");
		try {
			CtaDxpDescuentosPrestamo result = (CtaDxpDescuentosPrestamo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaDxpDescuentosPrestamo instance) {
		log.debug("attaching dirty CtaDxpDescuentosPrestamo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaDxpDescuentosPrestamo instance) {
		log.debug("attaching clean CtaDxpDescuentosPrestamo instance");
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
			String sql = "select max(dxp.dxpId) + 1  as id from CtaDxpDescuentosPrestamo dxp";
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