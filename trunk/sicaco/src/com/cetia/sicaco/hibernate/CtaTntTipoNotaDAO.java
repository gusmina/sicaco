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
 * CtaTntTipoNota entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTntTipoNota
 * @author MyEclipse Persistence Tools
 */

public class CtaTntTipoNotaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaTntTipoNotaDAO.class);
	// property constants
	public static final String TNT_NOMBRE = "tntNombre";
	public static final String TNT_DESCRIPCION = "tntDescripcion";

	public CtaTntTipoNotaDAO(Session session) {
		super(session);
	}

	public void save(CtaTntTipoNota transientInstance) {
		log.debug("saving CtaTntTipoNota instance");
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

	public void delete(CtaTntTipoNota persistentInstance) {
		log.debug("deleting CtaTntTipoNota instance");
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

	public CtaTntTipoNota findById(java.lang.Integer id) {
		log.debug("getting CtaTntTipoNota instance with id: " + id);
		try {
			CtaTntTipoNota instance = (CtaTntTipoNota) getSession().get(
					"com.cetia.sicaco.hibernate.CtaTntTipoNota", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTntTipoNota instance) {
		log.debug("finding CtaTntTipoNota instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTntTipoNota").add(
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
		log.debug("finding CtaTntTipoNota instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTntTipoNota as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTntNombre(Object tntNombre) {
		return findByProperty(TNT_NOMBRE, tntNombre);
	}

	public List findByTntDescripcion(Object tntDescripcion) {
		return findByProperty(TNT_DESCRIPCION, tntDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTntTipoNota instances");
		try {
			String queryString = "from CtaTntTipoNota";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTntTipoNota merge(CtaTntTipoNota detachedInstance) {
		log.debug("merging CtaTntTipoNota instance");
		try {
			CtaTntTipoNota result = (CtaTntTipoNota) getSession().merge(
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

	public void attachDirty(CtaTntTipoNota instance) {
		log.debug("attaching dirty CtaTntTipoNota instance");
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

	public void attachClean(CtaTntTipoNota instance) {
		log.debug("attaching clean CtaTntTipoNota instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaTntTipoNota tnt where tnt.tntId != ? and tnt.tntNombre = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, id);
			queryObject.setParameter(1, nombre);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}