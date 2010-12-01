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
 * SecEmpEmpresa entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecEmpEmpresa
 * @author MyEclipse Persistence Tools
 */

public class SecEmpEmpresaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecEmpEmpresaDAO.class);
	// property constants
	public static final String EMP_NOMBRE = "empNombre";
	public static final String EMP_DIRECCION = "empDireccion";
	public static final String EMP_TELEFONO = "empTelefono";

	public SecEmpEmpresaDAO(Session session) {
		super(session);
	}

	public void save(SecEmpEmpresa transientInstance) {
		log.debug("saving SecEmpEmpresa instance");
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

	public void delete(SecEmpEmpresa persistentInstance) {
		log.debug("deleting SecEmpEmpresa instance");
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

	public SecEmpEmpresa findById(java.lang.Integer id) {
		log.debug("getting SecEmpEmpresa instance with id: " + id);
		try {
			SecEmpEmpresa instance = (SecEmpEmpresa) getSession().get(
					"com.cetia.sicaco.hibernate.SecEmpEmpresa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecEmpEmpresa instance) {
		log.debug("finding SecEmpEmpresa instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecEmpEmpresa").add(
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
		log.debug("finding SecEmpEmpresa instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecEmpEmpresa as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEmpNombre(Object empNombre) {
		return findByProperty(EMP_NOMBRE, empNombre);
	}

	public List findByEmpDireccion(Object empDireccion) {
		return findByProperty(EMP_DIRECCION, empDireccion);
	}

	public List findByEmpTelefono(Object empTelefono) {
		return findByProperty(EMP_TELEFONO, empTelefono);
	}

	public List findAll() {
		log.debug("finding all SecEmpEmpresa instances");
		try {
			String queryString = "from SecEmpEmpresa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecEmpEmpresa merge(SecEmpEmpresa detachedInstance) {
		log.debug("merging SecEmpEmpresa instance");
		try {
			SecEmpEmpresa result = (SecEmpEmpresa) getSession().merge(
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

	public void attachDirty(SecEmpEmpresa instance) {
		log.debug("attaching dirty SecEmpEmpresa instance");
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

	public void attachClean(SecEmpEmpresa instance) {
		log.debug("attaching clean SecEmpEmpresa instance");
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