package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvCprCapacidadProducto entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvCprCapacidadProducto
 * @author MyEclipse Persistence Tools
 */

public class InvCprCapacidadProductoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvCprCapacidadProductoDAO.class);
	// property constants
	public static final String CPR_CANTIDAD_MINIMA = "cprCantidadMinima";
	public static final String CPR_CANTIDAD_MAXIMA = "cprCantidadMaxima";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvCprCapacidadProductoDAO(Session session) {
		super(session);
	}

	public void save(InvCprCapacidadProducto transientInstance) {
		log.debug("saving InvCprCapacidadProducto instance");
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

	public void delete(InvCprCapacidadProducto persistentInstance) {
		log.debug("deleting InvCprCapacidadProducto instance");
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

	public InvCprCapacidadProducto findById(
			com.cetia.sicaco.hibernate.InvCprCapacidadProductoId id) {
		log.debug("getting InvCprCapacidadProducto instance with id: " + id);
		try {
			InvCprCapacidadProducto instance = (InvCprCapacidadProducto) getSession()
					.get("com.cetia.sicaco.hibernate.InvCprCapacidadProducto",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvCprCapacidadProducto instance) {
		log.debug("finding InvCprCapacidadProducto instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvCprCapacidadProducto").add(
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
		log.debug("finding InvCprCapacidadProducto instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvCprCapacidadProducto as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCprCantidadMinima(Object cprCantidadMinima) {
		return findByProperty(CPR_CANTIDAD_MINIMA, cprCantidadMinima);
	}

	public List findByCprCantidadMaxima(Object cprCantidadMaxima) {
		return findByProperty(CPR_CANTIDAD_MAXIMA, cprCantidadMaxima);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvCprCapacidadProducto instances");
		try {
			String queryString = "from InvCprCapacidadProducto";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvCprCapacidadProducto merge(
			InvCprCapacidadProducto detachedInstance) {
		log.debug("merging InvCprCapacidadProducto instance");
		try {
			InvCprCapacidadProducto result = (InvCprCapacidadProducto) getSession()
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

	public void attachDirty(InvCprCapacidadProducto instance) {
		log.debug("attaching dirty InvCprCapacidadProducto instance");
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

	public void attachClean(InvCprCapacidadProducto instance) {
		log.debug("attaching clean InvCprCapacidadProducto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAllArtCodigo() {
		log.debug("finding allArtCodigo InvCprCapacidadProducto instances");
		try {
			
			String queryString = "select distinct cpr.id.invArtArticulo.artCodigo from InvCprCapacidadProducto cpr";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find allArtCodigo failed", re);
			throw re;
		}
	}
	
	public List findAllBodId() {
		log.debug("finding allBodId InvCprCapacidadProducto instances");
		try {
			
			String queryString = "select distinct cpr.id.invBodBodegas.bodId from InvCprCapacidadProducto cpr";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find allBodId failed", re);
			throw re;
		}
	}
}