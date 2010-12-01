package com.cetia.sicaco.hibernate;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConDdfDesembolsoDeFondos entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.ConDdfDesembolsoDeFondos
 * @author MyEclipse Persistence Tools
 */

public class ConDdfDesembolsoDeFondosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConDdfDesembolsoDeFondosDAO.class);
	// property constants
	public static final String BAN_ID = "banId";
	public static final String PRESTAMOS = "prestamos";
	public static final String AHORROS = "ahorros";
	public static final String PROVEEDORES = "proveedores";
	public static final String CODIGO_CUENTA = "codigoCuenta";

	public ConDdfDesembolsoDeFondosDAO(Session session) {
		super(session);
	}

	public void save(ConDdfDesembolsoDeFondos transientInstance) {
		log.debug("saving ConDdfDesembolsoDeFondos instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConDdfDesembolsoDeFondos persistentInstance) {
		log.debug("deleting ConDdfDesembolsoDeFondos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConDdfDesembolsoDeFondos findById(java.lang.Integer id) {
		log.debug("getting ConDdfDesembolsoDeFondos instance with id: " + id);
		try {
			ConDdfDesembolsoDeFondos instance = (ConDdfDesembolsoDeFondos) getSession()
					.get("com.cetia.sicaco.hibernate.ConDdfDesembolsoDeFondos",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConDdfDesembolsoDeFondos instance) {
		log.debug("finding ConDdfDesembolsoDeFondos instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConDdfDesembolsoDeFondos").add(
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
		log.debug("finding ConDdfDesembolsoDeFondos instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConDdfDesembolsoDeFondos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBanId(Object banId) {
		return findByProperty(BAN_ID, banId);
	}

	public List findByPrestamos(Object prestamos) {
		return findByProperty(PRESTAMOS, prestamos);
	}

	public List findByAhorros(Object ahorros) {
		return findByProperty(AHORROS, ahorros);
	}

	public List findByProveedores(Object proveedores) {
		return findByProperty(PROVEEDORES, proveedores);
	}

	public List findByCodigoCuenta(Object codigoCuenta) {
		return findByProperty(CODIGO_CUENTA, codigoCuenta);
	}

	public List findAll() {
		log.debug("finding all ConDdfDesembolsoDeFondos instances");
		try {
			String queryString = "from ConDdfDesembolsoDeFondos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConDdfDesembolsoDeFondos merge(
			ConDdfDesembolsoDeFondos detachedInstance) {
		log.debug("merging ConDdfDesembolsoDeFondos instance");
		try {
			ConDdfDesembolsoDeFondos result = (ConDdfDesembolsoDeFondos) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConDdfDesembolsoDeFondos instance) {
		log.debug("attaching dirty ConDdfDesembolsoDeFondos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConDdfDesembolsoDeFondos instance) {
		log.debug("attaching clean ConDdfDesembolsoDeFondos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void deleteAll(){
		ConDdfDesembolsoDeFondosDAO dao = new ConDdfDesembolsoDeFondosDAO(getSession());
		Transaction tx = dao.getSession().beginTransaction();
		List l = dao.findAll();
		Iterator i = l.iterator();
		while (i.hasNext()){
			dao.delete((ConDdfDesembolsoDeFondos) i.next());
		}
		tx.commit();
		dao.getSession().flush();
		dao.getSession().clear();
	}
}