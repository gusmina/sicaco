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
 * CtaRpeReferenciasPersonales entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaRpeReferenciasPersonales
 * @author MyEclipse Persistence Tools
 */

public class CtaRpeReferenciasPersonalesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaRpeReferenciasPersonalesDAO.class);
	// property constants
	public static final String PAR_ID = "parId";
	public static final String RPE_APELLIDOS = "rpeApellidos";
	public static final String RPE_NOMBRES = "rpeNombres";
	public static final String RPE_TELEFONO = "rpeTelefono";
	public static final String RPE_DIRECCION = "rpeDireccion";
	public static final String RPE_ESTADO_VALIDEZ = "rpeEstadoValidez";

	public CtaRpeReferenciasPersonalesDAO(Session session) {
		super(session);
	}

	public void save(CtaRpeReferenciasPersonales transientInstance) {
		log.debug("saving CtaRpeReferenciasPersonales instance");
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

	public void delete(CtaRpeReferenciasPersonales persistentInstance) {
		log.debug("deleting CtaRpeReferenciasPersonales instance");
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

	public CtaRpeReferenciasPersonales findById(java.lang.Integer id) {
		log
				.debug("getting CtaRpeReferenciasPersonales instance with id: "
						+ id);
		try {
			CtaRpeReferenciasPersonales instance = (CtaRpeReferenciasPersonales) getSession()
					.get(
							"com.cetia.sicaco.hibernate.CtaRpeReferenciasPersonales",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaRpeReferenciasPersonales instance) {
		log.debug("finding CtaRpeReferenciasPersonales instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaRpeReferenciasPersonales")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log
				.debug("finding CtaRpeReferenciasPersonales instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaRpeReferenciasPersonales as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByParId(Object parId) {
		return findByProperty(PAR_ID, parId);
	}

	public List findByRpeApellidos(Object rpeApellidos) {
		return findByProperty(RPE_APELLIDOS, rpeApellidos);
	}

	public List findByRpeNombres(Object rpeNombres) {
		return findByProperty(RPE_NOMBRES, rpeNombres);
	}

	public List findByRpeTelefono(Object rpeTelefono) {
		return findByProperty(RPE_TELEFONO, rpeTelefono);
	}

	public List findByRpeDireccion(Object rpeDireccion) {
		return findByProperty(RPE_DIRECCION, rpeDireccion);
	}

	public List findByRpeEstadoValidez(Object rpeEstadoValidez) {
		return findByProperty(RPE_ESTADO_VALIDEZ, rpeEstadoValidez);
	}

	public List findAll() {
		log.debug("finding all CtaRpeReferenciasPersonales instances");
		try {
			String queryString = "from CtaRpeReferenciasPersonales";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaRpeReferenciasPersonales merge(
			CtaRpeReferenciasPersonales detachedInstance) {
		log.debug("merging CtaRpeReferenciasPersonales instance");
		try {
			CtaRpeReferenciasPersonales result = (CtaRpeReferenciasPersonales) getSession()
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

	public void attachDirty(CtaRpeReferenciasPersonales instance) {
		log.debug("attaching dirty CtaRpeReferenciasPersonales instance");
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

	public void attachClean(CtaRpeReferenciasPersonales instance) {
		log.debug("attaching clean CtaRpeReferenciasPersonales instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}