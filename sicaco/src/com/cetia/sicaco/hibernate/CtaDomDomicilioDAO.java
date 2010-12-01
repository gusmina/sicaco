package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaDomDomicilio entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaDomDomicilio
 * @author MyEclipse Persistence Tools
 */

public class CtaDomDomicilioDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaDomDomicilioDAO.class);
	// property constants
	public static final String DOM_NOMBRE = "domNombre";
	public static final String DOM_DESCRIPCION = "domDescripcion";

	public CtaDomDomicilioDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void save(CtaDomDomicilio transientInstance) {
		log.debug("saving CtaDomDomicilio instance");
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

	public void delete(CtaDomDomicilio persistentInstance) {
		log.debug("deleting CtaDomDomicilio instance");
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

	public CtaDomDomicilio findById(java.lang.Integer id) {
		log.debug("getting CtaDomDomicilio instance with id: " + id);
		try {
			CtaDomDomicilio instance = (CtaDomDomicilio) getSession().get(
					"com.cetia.sicaco.hibernate.CtaDomDomicilio", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaDomDomicilio instance) {
		log.debug("finding CtaDomDomicilio instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaDomDomicilio").add(
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
		log.debug("finding CtaDomDomicilio instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaDomDomicilio as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDomNombre(Object domNombre) {
		return findByProperty(DOM_NOMBRE, domNombre);
	}

	public List findByDomDescripcion(Object domDescripcion) {
		return findByProperty(DOM_DESCRIPCION, domDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaDomDomicilio instances");
		try {
			String queryString = "from CtaDomDomicilio";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaDomDomicilio merge(CtaDomDomicilio detachedInstance) {
		log.debug("merging CtaDomDomicilio instance");
		try {
			CtaDomDomicilio result = (CtaDomDomicilio) getSession().merge(
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

	public void attachDirty(CtaDomDomicilio instance) {
		log.debug("attaching dirty CtaDomDomicilio instance");
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

	public void attachClean(CtaDomDomicilio instance) {
		log.debug("attaching clean CtaDomDomicilio instance");
		try {
			getSession().lock(instance, LockMode.NONE);
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
			String sql = "select max(dom.domId) + 1  as id from CtaDomDomicilio dom";
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
		
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaDomDomicilio dom where dom.domId != ? and dom.domNombre = ?";
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
	public Integer getTotalRowCount() {
		log.debug("counting all rows of ctaDomDomicilio");
		try {
			String queryString = "select count(h.domId)from CtaDomDomicilio h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaDomDomicilio> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaDomDomicilio hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaDomDomicilio hse order by hse.domId desc";
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