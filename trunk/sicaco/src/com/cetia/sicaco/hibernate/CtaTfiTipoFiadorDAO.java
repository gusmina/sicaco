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
 * CtaTfiTipoFiador entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTfiTipoFiador
 * @author MyEclipse Persistence Tools
 */

public class CtaTfiTipoFiadorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaTfiTipoFiadorDAO.class);
	// property constants
	public static final String TFI_NOMBRE = "tfiNombre";
	public static final String TFI_DESCRIPCION = "tfiDescripcion";

	public CtaTfiTipoFiadorDAO(Session session) {
		super(session);
	}

	public void save(CtaTfiTipoFiador transientInstance) {
		log.debug("saving CtaTfiTipoFiador instance");
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

	public void delete(CtaTfiTipoFiador persistentInstance) {
		log.debug("deleting CtaTfiTipoFiador instance");
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

	public CtaTfiTipoFiador findById(java.lang.Integer id) {
		log.debug("getting CtaTfiTipoFiador instance with id: " + id);
		try {
			CtaTfiTipoFiador instance = (CtaTfiTipoFiador) getSession().get(
					"com.cetia.sicaco.hibernate.CtaTfiTipoFiador", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTfiTipoFiador instance) {
		log.debug("finding CtaTfiTipoFiador instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTfiTipoFiador").add(
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
		log.debug("finding CtaTfiTipoFiador instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTfiTipoFiador as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTfiNombre(Object tfiNombre) {
		return findByProperty(TFI_NOMBRE, tfiNombre);
	}

	public List findByTfiDescripcion(Object tfiDescripcion) {
		return findByProperty(TFI_DESCRIPCION, tfiDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTfiTipoFiador instances");
		try {
			String queryString = "from CtaTfiTipoFiador";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTfiTipoFiador merge(CtaTfiTipoFiador detachedInstance) {
		log.debug("merging CtaTfiTipoFiador instance");
		try {
			CtaTfiTipoFiador result = (CtaTfiTipoFiador) getSession().merge(
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

	public void attachDirty(CtaTfiTipoFiador instance) {
		log.debug("attaching dirty CtaTfiTipoFiador instance");
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

	public void attachClean(CtaTfiTipoFiador instance) {
		log.debug("attaching clean CtaTfiTipoFiador instance");
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
			String sql = "from CtaTfiTipoFiador tfi where tfi.tfiId != ? and tfi.tfiNombre = ?";
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