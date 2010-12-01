package com.cetia.sicaco.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
/**
 * A data access object (DAO) providing persistence and search support for
 * CtaGarGarantia entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaGarGarantia
 * @author MyEclipse Persistence Tools
 */

public class CtaGarGarantiaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaGarGarantiaDAO.class);
	// property constants
	public static final String GAR_INSPECCION = "garInspeccion";
	public static final String GAR_UBICACION = "garUbicacion";
	public static final String GAR_DESCRIPCION_INMUEBLE = "garDescripcionInmueble";
	public static final String GAR_NOMBRE_INMUEBLE = "garNombreInmueble";
	public static final String GAR_VALOR = "garValor";

	public CtaGarGarantiaDAO(Session session) {
		super(session);
	}

	public void save(CtaGarGarantia transientInstance) {
		log.debug("saving CtaGarGarantia instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaGarGarantia persistentInstance) {
		log.debug("deleting CtaGarGarantia instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaGarGarantia findById(java.lang.Integer id) {
		log.debug("getting CtaGarGarantia instance with id: " + id);
		try {
			CtaGarGarantia instance = (CtaGarGarantia) getSession().get(
					"com.cetia.sicaco.hibernate.CtaGarGarantia", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaGarGarantia instance) {
		log.debug("finding CtaGarGarantia instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaGarGarantia").add(
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
		log.debug("finding CtaGarGarantia instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaGarGarantia as model where model."
					+ propertyName + "= ?";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGarInspeccion(Object garInspeccion) {
		return findByProperty(GAR_INSPECCION, garInspeccion);
	}

	public List findByGarUbicacion(Object garUbicacion) {
		return findByProperty(GAR_UBICACION, garUbicacion);
	}

	public List findByGarDescripcionInmueble(Object garDescripcionInmueble) {
		return findByProperty(GAR_DESCRIPCION_INMUEBLE, garDescripcionInmueble);
	}

	public List findByGarNombreInmueble(Object garNombreInmueble) {
		return findByProperty(GAR_NOMBRE_INMUEBLE, garNombreInmueble);
	}

	public List findByGarValor(Object garValor) {
		return findByProperty(GAR_VALOR, garValor);
	}

	public List findAll() {
		log.debug("finding all CtaGarGarantia instances");
		try {
			String queryString = "from CtaGarGarantia";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaGarGarantia merge(CtaGarGarantia detachedInstance) {
		log.debug("merging CtaGarGarantia instance");
		try {
			CtaGarGarantia result = (CtaGarGarantia) getSession().merge(
					detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaGarGarantia instance) {
		log.debug("attaching dirty CtaGarGarantia instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaGarGarantia instance) {
		log.debug("attaching clean CtaGarGarantia instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}