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
 * CtaTasTipoAsociado entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTasTipoAsociado
 * @author MyEclipse Persistence Tools
 */

public class CtaTasTipoAsociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaTasTipoAsociadoDAO.class);
	// property constants
	public static final String TAS_NOMBRE = "tasNombre";
	public static final String TAS_DESCRIPCION = "tasDescripcion";

	public CtaTasTipoAsociadoDAO(Session session) {
		super(session);
	}

	public void save(CtaTasTipoAsociado transientInstance) {
		log.debug("saving CtaTasTipoAsociado instance");
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

	public void delete(CtaTasTipoAsociado persistentInstance) {
		log.debug("deleting CtaTasTipoAsociado instance");
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

	public CtaTasTipoAsociado findById(java.lang.Integer id) {
		log.debug("getting CtaTasTipoAsociado instance with id: " + id);
		try {
			CtaTasTipoAsociado instance = (CtaTasTipoAsociado) getSession()
					.get("com.cetia.sicaco.hibernate.CtaTasTipoAsociado", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTasTipoAsociado instance) {
		log.debug("finding CtaTasTipoAsociado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTasTipoAsociado").add(
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
		log.debug("finding CtaTasTipoAsociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTasTipoAsociado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTasNombre(Object tasNombre) {
		return findByProperty(TAS_NOMBRE, tasNombre);
	}

	public List findByTasDescripcion(Object tasDescripcion) {
		return findByProperty(TAS_DESCRIPCION, tasDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTasTipoAsociado instances");
		try {
			String queryString = "from CtaTasTipoAsociado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTasTipoAsociado merge(CtaTasTipoAsociado detachedInstance) {
		log.debug("merging CtaTasTipoAsociado instance");
		try {
			CtaTasTipoAsociado result = (CtaTasTipoAsociado) getSession()
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

	public void attachDirty(CtaTasTipoAsociado instance) {
		log.debug("attaching dirty CtaTasTipoAsociado instance");
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

	public void attachClean(CtaTasTipoAsociado instance) {
		log.debug("attaching clean CtaTasTipoAsociado instance");
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
			String sql = "select max(ta.tasId) + 1  as id from CtaTasTipoAsociado ta";
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
			String sql = "from CtaTasTipoAsociado ta where ta.tasId != ? and ta.tasNombre = ?";
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
		log.debug("counting all rows of CtaTasTipoAsociado");
		try {
			String queryString = "select count(h.tasId) from CtaTasTipoAsociado h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaTasTipoAsociado> findAll(int rowStart,int rowEnd) {
		log.debug("finding all ctaTasTipoAsociado hse instances order by hse.tasId desc");
		try {
			String queryString = "from CtaTasTipoAsociado hse order by hse.tasId desc";
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