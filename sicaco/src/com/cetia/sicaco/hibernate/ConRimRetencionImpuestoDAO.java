package com.cetia.sicaco.hibernate;

import java.util.Date;
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
 * ConRimRetencionImpuesto entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConRimRetencionImpuesto
 * @author MyEclipse Persistence Tools
 */

public class ConRimRetencionImpuestoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConRimRetencionImpuestoDAO.class);
	// property constants
	public static final String RIM_IMPUESTO = "rimImpuesto";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public ConRimRetencionImpuestoDAO(Session session) {
		super(session);
	}

	public void save(ConRimRetencionImpuesto transientInstance) {
		log.debug("saving ConRimRetencionImpuesto instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConRimRetencionImpuesto persistentInstance) {
		log.debug("deleting ConRimRetencionImpuesto instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConRimRetencionImpuesto findById(java.lang.Integer id) {
		log.debug("getting ConRimRetencionImpuesto instance with id: " + id);
		try {
			ConRimRetencionImpuesto instance = (ConRimRetencionImpuesto) getSession()
					.get("com.cetia.sicaco.hibernate.ConRimRetencionImpuesto",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConRimRetencionImpuesto instance) {
		log.debug("finding ConRimRetencionImpuesto instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConRimRetencionImpuesto").add(
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
		log.debug("finding ConRimRetencionImpuesto instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConRimRetencionImpuesto as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRimImpuesto(Object rimImpuesto) {
		return findByProperty(RIM_IMPUESTO, rimImpuesto);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all ConRimRetencionImpuesto instances");
		try {
			String queryString = "from ConRimRetencionImpuesto";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConRimRetencionImpuesto merge(
			ConRimRetencionImpuesto detachedInstance) {
		log.debug("merging ConRimRetencionImpuesto instance");
		try {
			ConRimRetencionImpuesto result = (ConRimRetencionImpuesto) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConRimRetencionImpuesto instance) {
		log.debug("attaching dirty ConRimRetencionImpuesto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConRimRetencionImpuesto instance) {
		log.debug("attaching clean ConRimRetencionImpuesto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}