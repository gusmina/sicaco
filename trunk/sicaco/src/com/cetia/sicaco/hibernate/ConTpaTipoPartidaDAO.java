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
 * ConTpaTipoPartida entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConTpaTipoPartida
 * @author MyEclipse Persistence Tools
 */

public class ConTpaTipoPartidaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConTpaTipoPartidaDAO.class);
	// property constants
	public static final String TPA_NOMBRE = "tpaNombre";
	public static final String TPA_DESCRIPCION = "tpaDescripcion";

	public ConTpaTipoPartidaDAO(Session session) {
		super(session);
	}

	public void save(ConTpaTipoPartida transientInstance) {
		log.debug("saving ConTpaTipoPartida instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConTpaTipoPartida persistentInstance) {
		log.debug("deleting ConTpaTipoPartida instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConTpaTipoPartida findById(java.lang.Integer id) {
		log.debug("getting ConTpaTipoPartida instance with id: " + id);
		try {
			ConTpaTipoPartida instance = (ConTpaTipoPartida) getSession().get(
					"com.cetia.sicaco.hibernate.ConTpaTipoPartida", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConTpaTipoPartida instance) {
		log.debug("finding ConTpaTipoPartida instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConTpaTipoPartida").add(
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
		log.debug("finding ConTpaTipoPartida instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConTpaTipoPartida as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTpaNombre(Object tpaNombre) {
		return findByProperty(TPA_NOMBRE, tpaNombre);
	}

	public List findByTpaDescripcion(Object tpaDescripcion) {
		return findByProperty(TPA_DESCRIPCION, tpaDescripcion);
	}

	public List findAll() {
		log.debug("finding all ConTpaTipoPartida instances");
		try {
			String queryString = "from ConTpaTipoPartida";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConTpaTipoPartida merge(ConTpaTipoPartida detachedInstance) {
		log.debug("merging ConTpaTipoPartida instance");
		try {
			ConTpaTipoPartida result = (ConTpaTipoPartida) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConTpaTipoPartida instance) {
		log.debug("attaching dirty ConTpaTipoPartida instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConTpaTipoPartida instance) {
		log.debug("attaching clean ConTpaTipoPartida instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}