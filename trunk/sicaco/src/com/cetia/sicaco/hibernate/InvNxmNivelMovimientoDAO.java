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
 * InvNxmNivelMovimiento entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvNxmNivelMovimiento
 * @author MyEclipse Persistence Tools
 */

public class InvNxmNivelMovimientoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvNxmNivelMovimientoDAO.class);
	// property constants
	public static final String NXM_UNIDADES = "nxmUnidades";
	public static final String NXM_VALOR = "nxmValor";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvNxmNivelMovimientoDAO(Session session) {
		super(session);
	}

	public void save(InvNxmNivelMovimiento transientInstance) {
		log.debug("saving InvNxmNivelMovimiento instance");
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

	public void delete(InvNxmNivelMovimiento persistentInstance) {
		log.debug("deleting InvNxmNivelMovimiento instance");
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

	public InvNxmNivelMovimiento findById(java.lang.Integer id) {
		log.debug("getting InvNxmNivelMovimiento instance with id: " + id);
		try {
			InvNxmNivelMovimiento instance = (InvNxmNivelMovimiento) getSession()
					.get("com.cetia.sicaco.hibernate.InvNxmNivelMovimiento", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvNxmNivelMovimiento instance) {
		log.debug("finding InvNxmNivelMovimiento instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvNxmNivelMovimiento").add(
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
		log.debug("finding InvNxmNivelMovimiento instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvNxmNivelMovimiento as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNxmUnidades(Object nxmUnidades) {
		return findByProperty(NXM_UNIDADES, nxmUnidades);
	}

	public List findByNxmValor(Object nxmValor) {
		return findByProperty(NXM_VALOR, nxmValor);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvNxmNivelMovimiento instances");
		try {
			String queryString = "from InvNxmNivelMovimiento";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvNxmNivelMovimiento merge(InvNxmNivelMovimiento detachedInstance) {
		log.debug("merging InvNxmNivelMovimiento instance");
		try {
			InvNxmNivelMovimiento result = (InvNxmNivelMovimiento) getSession()
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

	public void attachDirty(InvNxmNivelMovimiento instance) {
		log.debug("attaching dirty InvNxmNivelMovimiento instance");
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

	public void attachClean(InvNxmNivelMovimiento instance) {
		log.debug("attaching clean InvNxmNivelMovimiento instance");
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