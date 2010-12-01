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
 * InvPcbProveedorCuentaBancaria entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.InvPcbProveedorCuentaBancaria
 * @author MyEclipse Persistence Tools
 */

public class InvPcbProveedorCuentaBancariaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvPcbProveedorCuentaBancariaDAO.class);
	// property constants
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvPcbProveedorCuentaBancariaDAO(Session session) {
		super(session);
	}

	public void save(InvPcbProveedorCuentaBancaria transientInstance) {
		log.debug("saving InvPcbProveedorCuentaBancaria instance");
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

	public void delete(InvPcbProveedorCuentaBancaria persistentInstance) {
		log.debug("deleting InvPcbProveedorCuentaBancaria instance");
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

	public InvPcbProveedorCuentaBancaria findById(
			com.cetia.sicaco.hibernate.InvPcbProveedorCuentaBancariaId id) {
		log.debug("getting InvPcbProveedorCuentaBancaria instance with id: "
				+ id);
		try {
			InvPcbProveedorCuentaBancaria instance = (InvPcbProveedorCuentaBancaria) getSession()
					.get(
							"com.cetia.sicaco.hibernate.InvPcbProveedorCuentaBancaria",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvPcbProveedorCuentaBancaria instance) {
		log.debug("finding InvPcbProveedorCuentaBancaria instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvPcbProveedorCuentaBancaria")
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
				.debug("finding InvPcbProveedorCuentaBancaria instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvPcbProveedorCuentaBancaria as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvPcbProveedorCuentaBancaria instances");
		try {
			String queryString = "from InvPcbProveedorCuentaBancaria";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvPcbProveedorCuentaBancaria merge(
			InvPcbProveedorCuentaBancaria detachedInstance) {
		log.debug("merging InvPcbProveedorCuentaBancaria instance");
		try {
			InvPcbProveedorCuentaBancaria result = (InvPcbProveedorCuentaBancaria) getSession()
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

	public void attachDirty(InvPcbProveedorCuentaBancaria instance) {
		log.debug("attaching dirty InvPcbProveedorCuentaBancaria instance");
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

	public void attachClean(InvPcbProveedorCuentaBancaria instance) {
		log.debug("attaching clean InvPcbProveedorCuentaBancaria instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
}