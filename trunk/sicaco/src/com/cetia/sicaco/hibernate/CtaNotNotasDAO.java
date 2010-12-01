package com.cetia.sicaco.hibernate;
// default package

import java.math.BigDecimal;
import java.util.ArrayList;
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
 * CtaNotNotas entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .CtaNotNotas
 * @author MyEclipse Persistence Tools
 */

public class CtaNotNotasDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaNotNotasDAO.class);
	// property constants
	public static final String NOT_NOTA = "notNota";
	public static final String NOT_CUENTA = "notCuenta";
	public static final String NOT_CAMPO = "notCampo";

	public CtaNotNotasDAO(Session session) {
		super(session);
	}

	public void save(CtaNotNotas transientInstance) {
		log.debug("saving CtaNotNotas instance");
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

	public void delete(CtaNotNotas persistentInstance) {
		log.debug("deleting CtaNotNotas instance");
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

	public CtaNotNotas findById(java.lang.Integer id) {
		log.debug("getting CtaNotNotas instance with id: " + id);
		try {
			CtaNotNotas instance = (CtaNotNotas) getSession().get(
					"com.cetia.sicaco.hibernate.CtaNotNotas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaNotNotas instance) {
		log.debug("finding CtaNotNotas instance by example");
		try {
			List results = getSession().createCriteria("com.cetia.sicaco.hibernate.CtaNotNotas").add(
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
		log.debug("finding CtaNotNotas instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CtaNotNotas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNotNota(Object notNota) {
		return findByProperty(NOT_NOTA, notNota);
	}

	public List findByNotCuenta(Object notCuenta) {
		return findByProperty(NOT_CUENTA, notCuenta);
	}
	
	public List findByNotCampo(Object notCampo) {
		return findByProperty(NOT_CAMPO, notCampo);
	}

	public List findAll() {
		log.debug("finding all CtaNotNotas instances");
		try {
			String queryString = "from CtaNotNotas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaNotNotas merge(CtaNotNotas detachedInstance) {
		log.debug("merging CtaNotNotas instance");
		try {
			CtaNotNotas result = (CtaNotNotas) getSession().merge(
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

	public void attachDirty(CtaNotNotas instance) {
		log.debug("attaching dirty CtaNotNotas instance");
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

	public void attachClean(CtaNotNotas instance) {
		log.debug("attaching clean CtaNotNotas instance");
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
			String sql = "select max(nt.notId) + 1  as id from CtaNotNotas nt";
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

	public List findbyCuenta(Long casCuenta) {
		log.debug("finding all CtaNotNotas instances");
		try {
			String queryString = "from CtaNotNotas notas where notas.casCuenta = " + casCuenta;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findbyCuentaAndId(Long casCuenta, int id) {
		log.debug("finding all CtaNotNotas instances");
		try {
			String queryString = "from CtaNotNotas notas where notas.casCuenta = " + casCuenta + " " +
								 "and notas.ctaTntTipoNota.tntId = " + id;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findbyCuentaAndCampo(Long casCuenta, String campo) {
		log.debug("finding all CtaNotNotas instances");
		try {
			String queryString = "from CtaNotNotas notas where notas.casCuenta = " + casCuenta + " " +
								 "and notas.notCampo = '" + campo + "' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}