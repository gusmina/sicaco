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
 * CtaTcuTipoCuenta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTcuTipoCuenta
 * @author MyEclipse Persistence Tools
 */

public class CtaTcuTipoCuentaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaTcuTipoCuentaDAO.class);
	// property constants
	public static final String TCU_NOMBRE = "tcuNombre";
	public static final String TCU_DESCRIPCION = "tcuDescripcion";

	public CtaTcuTipoCuentaDAO(Session session) {
		super(session);
	}

	public void save(CtaTcuTipoCuenta transientInstance) {
		log.debug("saving CtaTcuTipoCuenta instance");
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

	public void delete(CtaTcuTipoCuenta persistentInstance) {
		log.debug("deleting CtaTcuTipoCuenta instance");
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

	public CtaTcuTipoCuenta findById(java.lang.Integer id) {
		log.debug("getting CtaTcuTipoCuenta instance with id: " + id);
		try {
			CtaTcuTipoCuenta instance = (CtaTcuTipoCuenta) getSession().get(
					"com.cetia.sicaco.hibernate.CtaTcuTipoCuenta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTcuTipoCuenta instance) {
		log.debug("finding CtaTcuTipoCuenta instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTcuTipoCuenta").add(
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
		log.debug("finding CtaTcuTipoCuenta instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTcuTipoCuenta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTcuNombre(Object tcuNombre) {
		return findByProperty(TCU_NOMBRE, tcuNombre);
	}

	public List findByTcuDescripcion(Object tcuDescripcion) {
		return findByProperty(TCU_DESCRIPCION, tcuDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTcuTipoCuenta instances");
		try {
			String queryString = "from CtaTcuTipoCuenta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTcuTipoCuenta merge(CtaTcuTipoCuenta detachedInstance) {
		log.debug("merging CtaTcuTipoCuenta instance");
		try {
			CtaTcuTipoCuenta result = (CtaTcuTipoCuenta) getSession().merge(
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

	public void attachDirty(CtaTcuTipoCuenta instance) {
		log.debug("attaching dirty CtaTcuTipoCuenta instance");
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

	public void attachClean(CtaTcuTipoCuenta instance) {
		log.debug("attaching clean CtaTcuTipoCuenta instance");
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
			String sql = "select max(tcu.tcuId) + 1  as id from CtaTcuTipoCuenta tcu";
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
			String sql = "from CtaTcuTipoCuenta tcu where tcu.tcuId != ? and tcu.tcuNombre = ?";
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