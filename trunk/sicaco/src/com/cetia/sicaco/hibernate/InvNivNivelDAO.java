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
 * InvNivNivel entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvNivNivel
 * @author MyEclipse Persistence Tools
 */

public class InvNivNivelDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvNivNivelDAO.class);
	// property constants
	public static final String NIV_ESTADO = "nivEstado";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvNivNivelDAO(Session session) {
		super(session);
	}

	public void save(InvNivNivel transientInstance) {
		log.debug("saving InvNivNivel instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			getSession().clear();
			log.debug("save successf" +
					"ul");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvNivNivel persistentInstance) {
		log.debug("deleting InvNivNivel instance");
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

	public InvNivNivel findById(com.cetia.sicaco.hibernate.InvNivNivelId id) {
		log.debug("getting InvNivNivel instance with id: " + id);
		try {
			InvNivNivel instance = (InvNivNivel) getSession().get(
					"com.cetia.sicaco.hibernate.InvNivNivel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvNivNivel instance) {
		log.debug("finding InvNivNivel instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvNivNivel").add(
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
		log.debug("finding InvNivNivel instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from InvNivNivel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNivEstado(Object nivEstado) {
		return findByProperty(NIV_ESTADO, nivEstado);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvNivNivel instances");
		try {
			String queryString = "from InvNivNivel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvNivNivel merge(InvNivNivel detachedInstance) {
		log.debug("merging InvNivNivel instance");
		try {
			InvNivNivel result = (InvNivNivel) getSession().merge(
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

	public void attachDirty(InvNivNivel instance) {
		log.debug("attaching dirty InvNivNivel instance");
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

	public void attachClean(InvNivNivel instance) {
		log.debug("attaching clean InvNivNivel instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByEstante(int estanteId) {
		log.debug("finding all InvNivNivel instances by estanteId");
		try {
			String queryString = "from InvNivNivel niv where niv.id.invStnEstante.stnId =" + estanteId;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}