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
 * InvTclTipoClasificacion entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvTclTipoClasificacion
 * @author MyEclipse Persistence Tools
 */

public class InvTclTipoClasificacionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvTclTipoClasificacionDAO.class);
	// property constants
	public static final String TCL_DESCRIPCION = "tclDescripcion";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvTclTipoClasificacionDAO(Session session) {
		super(session);
	}

	public void save(InvTclTipoClasificacion transientInstance) {
		log.debug("saving InvTclTipoClasificacion instance");
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

	public void delete(InvTclTipoClasificacion persistentInstance) {
		log.debug("deleting InvTclTipoClasificacion instance");
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

	public InvTclTipoClasificacion findById(java.lang.String id) {
		log.debug("getting InvTclTipoClasificacion instance with id: " + id);
		try {
			InvTclTipoClasificacion instance = (InvTclTipoClasificacion) getSession()
					.get("com.cetia.sicaco.hibernate.InvTclTipoClasificacion",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvTclTipoClasificacion instance) {
		log.debug("finding InvTclTipoClasificacion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvTclTipoClasificacion").add(
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
		log.debug("finding InvTclTipoClasificacion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvTclTipoClasificacion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTclDescripcion(Object tclDescripcion) {
		return findByProperty(TCL_DESCRIPCION, tclDescripcion);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvTclTipoClasificacion instances");
		try {
			String queryString = "from InvTclTipoClasificacion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvTclTipoClasificacion merge(
			InvTclTipoClasificacion detachedInstance) {
		log.debug("merging InvTclTipoClasificacion instance");
		try {
			InvTclTipoClasificacion result = (InvTclTipoClasificacion) getSession()
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

	public void attachDirty(InvTclTipoClasificacion instance) {
		log.debug("attaching dirty InvTclTipoClasificacion instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			getSession().clear();
			log.debug("attach success" +
					"ful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvTclTipoClasificacion instance) {
		log.debug("attaching clean InvTclTipoClasificacion instance");
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