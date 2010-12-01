package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.Date;
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
 * CtaLahLineaAhorro entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaLahLineaAhorro
 * @author MyEclipse Persistence Tools
 */

public class CtaLahLineaAhorroDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaLahLineaAhorroDAO.class);
	// property constants
	public static final String LAH_NOMBRE = "lahNombre";
	public static final String LAH_DESCRIPCION = "lahDescripcion";

	public CtaLahLineaAhorroDAO(Session session) {
		super(session);
	}

	public void save(CtaLahLineaAhorro transientInstance) {
		log.debug("saving CtaLahLineaAhorro instance");
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

	public void delete(CtaLahLineaAhorro persistentInstance) {
		log.debug("deleting CtaLahLineaAhorro instance");
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

	public CtaLahLineaAhorro findById(java.lang.Integer id) {
		log.debug("getting CtaLahLineaAhorro instance with id: " + id);
		try {
			CtaLahLineaAhorro instance = (CtaLahLineaAhorro) getSession().get(
					"com.cetia.sicaco.hibernate.CtaLahLineaAhorro", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaLahLineaAhorro instance) {
		log.debug("finding CtaLahLineaAhorro instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaLahLineaAhorro").add(
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
		log.debug("finding CtaLahLineaAhorro instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaLahLineaAhorro as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLahNombre(Object lahNombre) {
		return findByProperty(LAH_NOMBRE, lahNombre);
	}

	public List findByLahDescripcion(Object lahDescripcion) {
		return findByProperty(LAH_DESCRIPCION, lahDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaLahLineaAhorro instances");
		try {
			String queryString = "from CtaLahLineaAhorro";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaLahLineaAhorro merge(CtaLahLineaAhorro detachedInstance) {
		log.debug("merging CtaLahLineaAhorro instance");
		try {
			CtaLahLineaAhorro result = (CtaLahLineaAhorro) getSession().merge(
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

	public void attachDirty(CtaLahLineaAhorro instance) {
		log.debug("attaching dirty CtaLahLineaAhorro instance");
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

	public void attachClean(CtaLahLineaAhorro instance) {
		log.debug("attaching clean CtaLahLineaAhorro instance");
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
		log.debug("Consiguiendo la siguiente secuencia - Linea Ahorro");
		try {
			String sql = "select max(lah.lahId) + 1  as id from CtaLahLineaAhorro lah";
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
			log.error("Secuencia fallida - Linea Ahorro", re);
			throw re;
		}
		return id;
	} 
	
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaLahLineaAhorro lah where lah.lahId != ? and lah.lahNombre = ?";
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