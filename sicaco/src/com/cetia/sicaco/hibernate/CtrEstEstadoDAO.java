package com.cetia.sicaco.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtrEstEstado entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrEstEstado
 * @author MyEclipse Persistence Tools
 */

public class CtrEstEstadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtrEstEstadoDAO.class);
	// property constants
	public static final String EST_NOMBRE = "estNombre";

	public CtrEstEstadoDAO(Session session) {
		super(session);
	}

	public void save(CtrEstEstado transientInstance) {
		log.debug("saving CtrEstEstado instance");
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

	public void delete(CtrEstEstado persistentInstance) {
		log.debug("deleting CtrEstEstado instance");
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

	public CtrEstEstado findById(java.lang.Integer id) {
		log.debug("getting CtrEstEstado instance with id: " + id);
		try {
			CtrEstEstado instance = (CtrEstEstado) getSession().get(
					"com.cetia.sicaco.hibernate.CtrEstEstado", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrEstEstado instance) {
		log.debug("finding CtrEstEstado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrEstEstado").add(
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
		log.debug("finding CtrEstEstado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrEstEstado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEstNombre(Object estNombre) {
		return findByProperty(EST_NOMBRE, estNombre);
	}

	public List findAll() {
		log.debug("finding all CtrEstEstado instances");
		try {
			String queryString = "from CtrEstEstado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrEstEstado merge(CtrEstEstado detachedInstance) {
		log.debug("merging CtrEstEstado instance");
		try {
			CtrEstEstado result = (CtrEstEstado) getSession().merge(
					detachedInstance);
			getSession().flush();
			getSession().clear();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtrEstEstado instance) {
		log.debug("attaching dirty CtrEstEstado instance");
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

	public void attachClean(CtrEstEstado instance) {
		log.debug("attaching clean CtrEstEstado instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByEstadoAsociado(String propertyName, Object value) {
		log.debug("finding CtrEstEstado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrEstEstado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByTus(String tusCodigo) {
		log.debug("finding all CtrEstEstado instances");
		try {
			String queryString = "from CtrEstEstado est where est.ctrTusTipoUso.tusCodigo = '" + tusCodigo + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}