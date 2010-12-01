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
 * SecParParentesco entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecParParentesco
 * @author MyEclipse Persistence Tools
 */

public class SecParParentescoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecParParentescoDAO.class);
	// property constants
	public static final String PAR_DESCRIPCION = "parDescripcion";

	public SecParParentescoDAO(Session session) {
		super(session);
	}

	public void save(SecParParentesco transientInstance) {
		log.debug("saving SecParParentesco instance");
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

	public void delete(SecParParentesco persistentInstance) {
		log.debug("deleting SecParParentesco instance");
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

	public SecParParentesco findById(java.lang.Integer id) {
		log.debug("getting SecParParentesco instance with id: " + id);
		try {
			SecParParentesco instance = (SecParParentesco) getSession().get(
					"com.cetia.sicaco.hibernate.SecParParentesco", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecParParentesco instance) {
		log.debug("finding SecParParentesco instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecParParentesco").add(
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
		log.debug("finding SecParParentesco instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecParParentesco as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByParDescripcion(Object parDescripcion) {
		return findByProperty(PAR_DESCRIPCION, parDescripcion);
	}

	public List findAll() {
		log.debug("finding all SecParParentesco instances");
		try {
			String queryString = "from SecParParentesco";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecParParentesco merge(SecParParentesco detachedInstance) {
		log.debug("merging SecParParentesco instance");
		try {
			SecParParentesco result = (SecParParentesco) getSession().merge(
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

	public void attachDirty(SecParParentesco instance) {
		log.debug("attaching dirty SecParParentesco instance");
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

	public void attachClean(SecParParentesco instance) {
		log.debug("attaching clean SecParParentesco instance");
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
		log.debug("Consiguiendo la siguiente secuencia - Parentesco");
		try {
			String sql = "select max(par.parId) + 1  as id from SecParParentesco par";
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
			log.error("Secuencia fallida - Parentesco", re);
			throw re;
		}
		return id;
	} 
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from SecParParentesco par where par.parId != ? and par.parDescripcion = ?";
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