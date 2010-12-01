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
 * SecTelTelefono entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecTelTelefono
 * @author MyEclipse Persistence Tools
 */

public class SecTelTelefonoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTelTelefonoDAO.class);
	// property constants
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String TEL_EXT = "telExt";

	public SecTelTelefonoDAO(Session session) {
		super(session);
	}

	public void save(SecTelTelefono transientInstance) {
		log.debug("saving SecTelTelefono instance");
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

	public void delete(SecTelTelefono persistentInstance) {
		log.debug("deleting SecTelTelefono instance");
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

	public SecTelTelefono findById(
			com.cetia.sicaco.hibernate.SecTelTelefonoId id) {
		log.debug("getting SecTelTelefono instance with id: " + id);
		try {
			SecTelTelefono instance = (SecTelTelefono) getSession().get(
					"com.cetia.sicaco.hibernate.SecTelTelefono", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTelTelefono instance) {
		log.debug("finding SecTelTelefono instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecTelTelefono").add(
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
		log.debug("finding SecTelTelefono instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTelTelefono as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all SecTelTelefono instances");
		try {
			String queryString = "from SecTelTelefono";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTelTelefono merge(SecTelTelefono detachedInstance) {
		log.debug("merging SecTelTelefono instance");
		try {
			SecTelTelefono result = (SecTelTelefono) getSession().merge(
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

	public void attachDirty(SecTelTelefono instance) {
		log.debug("attaching dirty SecTelTelefono instance");
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

	public void attachClean(SecTelTelefono instance) {
		log.debug("attaching clean SecTelTelefono instance");
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