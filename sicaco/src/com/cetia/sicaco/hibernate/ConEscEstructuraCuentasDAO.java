package com.cetia.sicaco.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConEscEstructuraCuentas entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConEscEstructuraCuentas
 * @author MyEclipse Persistence Tools
 */

public class ConEscEstructuraCuentasDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConEscEstructuraCuentasDAO.class);
	// property constants
	public static final String ESC_TAMANHO = "escTamanho";

	public ConEscEstructuraCuentasDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void save(ConEscEstructuraCuentas transientInstance) {
		log.debug("saving ConEscEstructuraCuentas instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConEscEstructuraCuentas persistentInstance) {
		log.debug("deleting ConEscEstructuraCuentas instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConEscEstructuraCuentas findById(java.lang.Integer id) {
		log.debug("getting ConEscEstructuraCuentas instance with id: " + id);
		try {
			ConEscEstructuraCuentas instance = (ConEscEstructuraCuentas) getSession()
					.get("com.cetia.sicaco.hibernate.ConEscEstructuraCuentas",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConEscEstructuraCuentas instance) {
		log.debug("finding ConEscEstructuraCuentas instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConEscEstructuraCuentas").add(
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
		log.debug("finding ConEscEstructuraCuentas instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConEscEstructuraCuentas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEscTamanho(Object escTamanho) {
		return findByProperty(ESC_TAMANHO, escTamanho);
	}

	public List findAll() {
		log.debug("finding all ConEscEstructuraCuentas instances");
		try {
			String queryString = "from ConEscEstructuraCuentas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConEscEstructuraCuentas merge(
			ConEscEstructuraCuentas detachedInstance) {
		log.debug("merging ConEscEstructuraCuentas instance");
		try {
			ConEscEstructuraCuentas result = (ConEscEstructuraCuentas) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConEscEstructuraCuentas instance) {
		log.debug("attaching dirty ConEscEstructuraCuentas instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConEscEstructuraCuentas instance) {
		log.debug("attaching clean ConEscEstructuraCuentas instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}