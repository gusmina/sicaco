package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
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
 * InvTprTipoProveedor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvTprTipoProveedor
 * @author MyEclipse Persistence Tools
 */

public class InvTprTipoProveedorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvTprTipoProveedorDAO.class);
	// property constants
	public static final String TPR_NOMBRE = "tprNombre";

	public InvTprTipoProveedorDAO(Session session) {
		super(session);
	}

	public void save(InvTprTipoProveedor transientInstance) {
		log.debug("saving InvTprTipoProveedor instance");
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

	public void delete(InvTprTipoProveedor persistentInstance) {
		log.debug("deleting InvTprTipoProveedor instance");
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

	public InvTprTipoProveedor findById(java.lang.Integer id) {
		log.debug("getting InvTprTipoProveedor instance with id: " + id);
		try {
			InvTprTipoProveedor instance = (InvTprTipoProveedor) getSession()
					.get("com.cetia.sicaco.hibernate.InvTprTipoProveedor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvTprTipoProveedor instance) {
		log.debug("finding InvTprTipoProveedor instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvTprTipoProveedor").add(
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
		log.debug("finding InvTprTipoProveedor instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvTprTipoProveedor as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTprNombre(Object tprNombre) {
		return findByProperty(TPR_NOMBRE, tprNombre);
	}

	public List findAll() {
		log.debug("finding all InvTprTipoProveedor instances");
		try {
			String queryString = "from InvTprTipoProveedor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvTprTipoProveedor merge(InvTprTipoProveedor detachedInstance) {
		log.debug("merging InvTprTipoProveedor instance");
		try {
			InvTprTipoProveedor result = (InvTprTipoProveedor) getSession()
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

	public void attachDirty(InvTprTipoProveedor instance) {
		log.debug("attaching dirty InvTprTipoProveedor instance");
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

	public void attachClean(InvTprTipoProveedor instance) {
		log.debug("attaching clean InvTprTipoProveedor instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(tp.tprId) + 1  as tprId from InvTprTipoProveedor tp";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	} 
}