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
 * SecSucSucursal entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecSucSucursal
 * @author MyEclipse Persistence Tools
 */

public class SecSucSucursalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecSucSucursalDAO.class);
	// property constants
	public static final String SUC_NOMBRE = "sucNombre";
	public static final String SUC_DIRECCION = "sucDireccion";

	public SecSucSucursalDAO(Session session) {
		super(session);
	}

	public void save(SecSucSucursal transientInstance) {
		log.debug("saving SecSucSucursal instance");
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

	public void delete(SecSucSucursal persistentInstance) {
		log.debug("deleting SecSucSucursal instance");
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

	public SecSucSucursal findById(java.lang.Integer id) {
		log.debug("getting SecSucSucursal instance with id: " + id);
		try {
			SecSucSucursal instance = (SecSucSucursal) getSession().get(
					"com.cetia.sicaco.hibernate.SecSucSucursal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecSucSucursal instance) {
		log.debug("finding SecSucSucursal instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecSucSucursal").add(
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
		log.debug("finding SecSucSucursal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecSucSucursal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySucNombre(Object sucNombre) {
		return findByProperty(SUC_NOMBRE, sucNombre);
	}

	public List findBySucDireccion(Object sucDireccion) {
		return findByProperty(SUC_DIRECCION, sucDireccion);
	}

	public List findAll() {
		log.debug("finding all SecSucSucursal instances");
		try {
			String queryString = "from SecSucSucursal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecSucSucursal merge(SecSucSucursal detachedInstance) {
		log.debug("merging SecSucSucursal instance");
		try {
			SecSucSucursal result = (SecSucSucursal) getSession().merge(
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

	public void attachDirty(SecSucSucursal instance) {
		log.debug("attaching dirty SecSucSucursal instance");
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

	public void attachClean(SecSucSucursal instance) {
		log.debug("attaching clean SecSucSucursal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAllActive() {
		log.debug("finding all SecSucSucursal instances");
		try {
			String queryString = "from SecSucSucursal su where su.sucEstado = 'A'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Integer getTotalRowCount() {
		log.debug("counting all rows of SecSucSucursal");
		try {
			String queryString = "select count(h.sucId) from SecSucSucursal h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SecSucSucursal> findAll(int rowStart,int rowEnd) {
		log.debug("finding all SecSucSucursal hse instances order by hse.hseId desc");
		try {
			String queryString = "from SecSucSucursal hse order by hse.sucId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
}