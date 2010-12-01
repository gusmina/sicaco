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
 * SecDppDepartamentoPais entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecDppDepartamentoPais
 * @author MyEclipse Persistence Tools
 */

public class SecDppDepartamentoPaisDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecDppDepartamentoPaisDAO.class);
	// property constants
	public static final String DPP_NOMBRE = "dppNombre";
	public static final String DPP_DESCRIPCION = "dppDescripcion";

	public SecDppDepartamentoPaisDAO(Session session) {
		super(session);
	}

	public void save(SecDppDepartamentoPais transientInstance) {
		log.debug("saving SecDppDepartamentoPais instance");
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

	public void delete(SecDppDepartamentoPais persistentInstance) {
		log.debug("deleting SecDppDepartamentoPais instance");
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

	public SecDppDepartamentoPais findById(java.lang.Integer id) {
		log.debug("getting SecDppDepartamentoPais instance with id: " + id);
		try {
			SecDppDepartamentoPais instance = (SecDppDepartamentoPais) getSession()
					.get("com.cetia.sicaco.hibernate.SecDppDepartamentoPais",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecDppDepartamentoPais instance) {
		log.debug("finding SecDppDepartamentoPais instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecDppDepartamentoPais").add(
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
		log.debug("finding SecDppDepartamentoPais instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecDppDepartamentoPais as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDppNombre(Object dppNombre) {
		return findByProperty(DPP_NOMBRE, dppNombre);
	}

	public List findByDppDescripcion(Object dppDescripcion) {
		return findByProperty(DPP_DESCRIPCION, dppDescripcion);
	}

	public List findAll() {
		log.debug("finding all SecDppDepartamentoPais instances");
		try {
			String queryString = "from SecDppDepartamentoPais";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecDppDepartamentoPais merge(SecDppDepartamentoPais detachedInstance) {
		log.debug("merging SecDppDepartamentoPais instance");
		try {
			SecDppDepartamentoPais result = (SecDppDepartamentoPais) getSession()
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

	public void attachDirty(SecDppDepartamentoPais instance) {
		log.debug("attaching dirty SecDppDepartamentoPais instance");
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

	public void attachClean(SecDppDepartamentoPais instance) {
		log.debug("attaching clean SecDppDepartamentoPais instance");
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