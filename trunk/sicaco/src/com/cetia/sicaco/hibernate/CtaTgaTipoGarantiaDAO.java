package com.cetia.sicaco.hibernate;

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
 * CtaTgaTipoGarantia entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTgaTipoGarantia
 * @author MyEclipse Persistence Tools
 */

public class CtaTgaTipoGarantiaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaTgaTipoGarantiaDAO.class);
	// property constants
	public static final String TGA_NOMBRE = "tgaNombre";
	public static final String TGA_DESCRIPCION = "tgaDescripcion";

	public CtaTgaTipoGarantiaDAO(Session session) {
		super(session);
	}

	public void save(CtaTgaTipoGarantia transientInstance) {
		log.debug("saving CtaTgaTipoGarantia instance");
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

	public void delete(CtaTgaTipoGarantia persistentInstance) {
		log.debug("deleting CtaTgaTipoGarantia instance");
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

	public CtaTgaTipoGarantia findById(java.lang.Integer id) {
		log.debug("getting CtaTgaTipoGarantia instance with id: " + id);
		try {
			CtaTgaTipoGarantia instance = (CtaTgaTipoGarantia) getSession()
					.get("com.cetia.sicaco.hibernate.CtaTgaTipoGarantia", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTgaTipoGarantia instance) {
		log.debug("finding CtaTgaTipoGarantia instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTgaTipoGarantia").add(
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
		log.debug("finding CtaTgaTipoGarantia instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTgaTipoGarantia as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTgaNombre(Object tgaNombre) {
		return findByProperty(TGA_NOMBRE, tgaNombre);
	}

	public List findByTgaDescripcion(Object tgaDescripcion) {
		return findByProperty(TGA_DESCRIPCION, tgaDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTgaTipoGarantia instances");
		try {
			String queryString = "from CtaTgaTipoGarantia";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTgaTipoGarantia merge(CtaTgaTipoGarantia detachedInstance) {
		log.debug("merging CtaTgaTipoGarantia instance");
		try {
			CtaTgaTipoGarantia result = (CtaTgaTipoGarantia) getSession()
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

	public void attachDirty(CtaTgaTipoGarantia instance) {
		log.debug("attaching dirty CtaTgaTipoGarantia instance");
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

	public void attachClean(CtaTgaTipoGarantia instance) {
		log.debug("attaching clean CtaTgaTipoGarantia instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaTgaTipoGarantia tga where tga.tgaId != ? and tga.tgaNombre = ?";
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