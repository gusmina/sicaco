package com.cetia.sicaco.hibernate;

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
 * CtaLprLineaPrestamo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaLprLineaPrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaLprLineaPrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaLprLineaPrestamoDAO.class);
	// property constants
	public static final String LPR_NOMBRE = "lprNombre";
	public static final String LPR_DESCRIPCION = "lprDescripcion";
	public static final String LPR_ORDEN_APROV = "lprOrdenAprov";

	public CtaLprLineaPrestamoDAO(Session session) {
		super(session);
	}

	public void save(CtaLprLineaPrestamo transientInstance) {
		log.debug("saving CtaLprLineaPrestamo instance");
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

	public void delete(CtaLprLineaPrestamo persistentInstance) {
		log.debug("deleting CtaLprLineaPrestamo instance");
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

	public CtaLprLineaPrestamo findById(java.lang.Integer id) {
		log.debug("getting CtaLprLineaPrestamo instance with id: " + id);
		try {
			CtaLprLineaPrestamo instance = (CtaLprLineaPrestamo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaLprLineaPrestamo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaLprLineaPrestamo instance) {
		log.debug("finding CtaLprLineaPrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaLprLineaPrestamo").add(
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
		log.debug("finding CtaLprLineaPrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaLprLineaPrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLprNombre(Object lprNombre) {
		return findByProperty(LPR_NOMBRE, lprNombre);
	}

	public List findByLprDescripcion(Object lprDescripcion) {
		return findByProperty(LPR_DESCRIPCION, lprDescripcion);
	}

	public List findByLprOrdenAprov(Object lprOrdenAprov) {
		return findByProperty(LPR_ORDEN_APROV, lprOrdenAprov);
	}

	public List findAll() {
		log.debug("finding all CtaLprLineaPrestamo instances");
		try {
			String queryString = "from CtaLprLineaPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaLprLineaPrestamo merge(CtaLprLineaPrestamo detachedInstance) {
		log.debug("merging CtaLprLineaPrestamo instance");
		try {
			CtaLprLineaPrestamo result = (CtaLprLineaPrestamo) getSession()
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

	public void attachDirty(CtaLprLineaPrestamo instance) {
		log.debug("attaching dirty CtaLprLineaPrestamo instance");
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

	public void attachClean(CtaLprLineaPrestamo instance) {
		log.debug("attaching clean CtaLprLineaPrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<CtaLprLineaPrestamo> findByUpdatedName(Integer id,String nombre) {
		List<CtaLprLineaPrestamo> lista = null;
		try{
			String sql = "from CtaLprLineaPrestamo lp where lp.lprId != ? and lp.lprNombre = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, id);
			queryObject.setParameter(1, nombre);
			lista = queryObject.list();
			return lista;
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}