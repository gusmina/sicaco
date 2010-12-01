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
 * SecTteTipoTelefono entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecTteTipoTelefono
 * @author MyEclipse Persistence Tools
 */

public class SecTteTipoTelefonoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecTteTipoTelefonoDAO.class);
	// property constants
	public static final String TTE_DESCRIPCION = "tteDescripcion";

	public SecTteTipoTelefonoDAO(Session session) {
		super(session);
	}

	public void save(SecTteTipoTelefono transientInstance) {
		log.debug("saving SecTteTipoTelefono instance");
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

	public void delete(SecTteTipoTelefono persistentInstance) {
		log.debug("deleting SecTteTipoTelefono instance");
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

	public SecTteTipoTelefono findById(java.lang.Integer id) {
		log.debug("getting SecTteTipoTelefono instance with id: " + id);
		try {
			SecTteTipoTelefono instance = (SecTteTipoTelefono) getSession()
					.get("com.cetia.sicaco.hibernate.SecTteTipoTelefono",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTteTipoTelefono instance) {
		log.debug("finding SecTteTipoTelefono instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecTteTipoTelefono").add(
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
		log.debug("finding SecTteTipoTelefono instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTteTipoTelefono as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTteDescripcion(Object tteDescripcion) {
		return findByProperty(TTE_DESCRIPCION, tteDescripcion);
	}

	public List findAll() {
		log.debug("finding all SecTteTipoTelefono instances");
		try {
			String queryString = "from SecTteTipoTelefono";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTteTipoTelefono merge(SecTteTipoTelefono detachedInstance) {
		log.debug("merging SecTteTipoTelefono instance");
		try {
			SecTteTipoTelefono result = (SecTteTipoTelefono) getSession()
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

	public void attachDirty(SecTteTipoTelefono instance) {
		log.debug("attaching dirty SecTteTipoTelefono instance");
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

	public void attachClean(SecTteTipoTelefono instance) {
		log.debug("attaching clean SecTteTipoTelefono instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}