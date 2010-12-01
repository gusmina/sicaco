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
 * SecZonZona entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecZonZona
 * @author MyEclipse Persistence Tools
 */

public class SecZonZonaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecZonZonaDAO.class);
	// property constants
	public static final String ZON_NOMBRE = "zonNombre";
	public static final String ZON_DESCRIPCION = "zonDescripcion";

	public SecZonZonaDAO(Session session) {
		super(session);
	}

	public void save(SecZonZona transientInstance) {
		log.debug("saving SecZonZona instance");
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

	public void delete(SecZonZona persistentInstance) {
		log.debug("deleting SecZonZona instance");
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

	public SecZonZona findById(java.lang.Integer id) {
		log.debug("getting SecZonZona instance with id: " + id);
		try {
			SecZonZona instance = (SecZonZona) getSession().get(
					"com.cetia.sicaco.hibernate.SecZonZona", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecZonZona instance) {
		log.debug("finding SecZonZona instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecZonZona").add(
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
		log.debug("finding SecZonZona instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SecZonZona as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByZonNombre(Object zonNombre) {
		return findByProperty(ZON_NOMBRE, zonNombre);
	}

	public List findByZonDescripcion(Object zonDescripcion) {
		return findByProperty(ZON_DESCRIPCION, zonDescripcion);
	}

	public List findAll() {
		log.debug("finding all SecZonZona instances");
		try {
			String queryString = "from SecZonZona";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecZonZona merge(SecZonZona detachedInstance) {
		log.debug("merging SecZonZona instance");
		try {
			SecZonZona result = (SecZonZona) getSession().merge(
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

	public void attachDirty(SecZonZona instance) {
		log.debug("attaching dirty SecZonZona instance");
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

	public void attachClean(SecZonZona instance) {
		log.debug("attaching clean SecZonZona instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByZonCodigo(String zonCodigo) {
		log.debug("finding all SecZonZona instances");
		try {
			String queryString = "from SecZonZona zon where zon.zonCodigo ='" + zonCodigo + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}