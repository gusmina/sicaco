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
 * CtaTcoTipoContrato entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTcoTipoContrato
 * @author MyEclipse Persistence Tools
 */

public class CtaTcoTipoContratoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaTcoTipoContratoDAO.class);
	// property constants
	public static final String TCO_NOMBRE = "tcoNombre";
	public static final String TCO_DESCRIPCION = "tcoDescripcion";

	public CtaTcoTipoContratoDAO(Session session) {
		super(session);
	}

	public void save(CtaTcoTipoContrato transientInstance) {
		log.debug("saving CtaTcoTipoContrato instance");
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

	public void delete(CtaTcoTipoContrato persistentInstance) {
		log.debug("deleting CtaTcoTipoContrato instance");
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

	public CtaTcoTipoContrato findById(java.lang.Integer id) {
		log.debug("getting CtaTcoTipoContrato instance with id: " + id);
		try {
			CtaTcoTipoContrato instance = (CtaTcoTipoContrato) getSession()
					.get("com.cetia.sicaco.hibernate.CtaTcoTipoContrato", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTcoTipoContrato instance) {
		log.debug("finding CtaTcoTipoContrato instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTcoTipoContrato").add(
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
		log.debug("finding CtaTcoTipoContrato instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTcoTipoContrato as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTcoNombre(Object tcoNombre) {
		return findByProperty(TCO_NOMBRE, tcoNombre);
	}

	public List findByTcoDescripcion(Object tcoDescripcion) {
		return findByProperty(TCO_DESCRIPCION, tcoDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTcoTipoContrato instances");
		try {
			String queryString = "from CtaTcoTipoContrato";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTcoTipoContrato merge(CtaTcoTipoContrato detachedInstance) {
		log.debug("merging CtaTcoTipoContrato instance");
		try {
			CtaTcoTipoContrato result = (CtaTcoTipoContrato) getSession()
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

	public void attachDirty(CtaTcoTipoContrato instance) {
		log.debug("attaching dirty CtaTcoTipoContrato instance");
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

	public void attachClean(CtaTcoTipoContrato instance) {
		log.debug("attaching clean CtaTcoTipoContrato instance");
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
			String sql = "select max(tc.tcoId) + 1  as id from CtaTcoTipoContrato tc";
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
			String sql = "from CtaTcoTipoContrato tc where tc.tcoId != ? and tc.tcoNombre = ?";
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
		log.debug("counting all rows of CtaTcoTipoContrato");
		try {
			String queryString = "select count(h.tcoId)from CtaTcoTipoContrato h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaTcoTipoContrato> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaTcoTipoContrato hse instances order by hse.tcoId desc");
		try {
			String queryString = "from CtaTcoTipoContrato hse order by hse.tcoId desc";
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