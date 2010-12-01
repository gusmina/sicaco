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
 * InvPexProductosExistencia entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.InvPexProductosExistencia
 * @author MyEclipse Persistence Tools
 */

public class InvPexProductosExistenciaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvPexProductosExistenciaDAO.class);
	// property constants
	public static final String PEX_CANTIDAD_PRODUCTO = "pexCantidadProducto";
	public static final String PEX_SALDO = "pexSaldo";
	public static final String PEX_COSTO_PRODUCTO = "pexCostoProducto";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvPexProductosExistenciaDAO(Session session) {
		super(session);
	}

	public void save(InvPexProductosExistencia transientInstance) {
		log.debug("saving InvPexProductosExistencia instance");
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

	public void delete(InvPexProductosExistencia persistentInstance) {
		log.debug("deleting InvPexProductosExistencia instance");
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

	public InvPexProductosExistencia findById(java.lang.String id) {
		log.debug("getting InvPexProductosExistencia instance with id: " + id);
		try {
			InvPexProductosExistencia instance = (InvPexProductosExistencia) getSession()
					.get(
							"com.cetia.sicaco.hibernate.InvPexProductosExistencia",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvPexProductosExistencia instance) {
		log.debug("finding InvPexProductosExistencia instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvPexProductosExistencia")
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
		log.debug("finding InvPexProductosExistencia instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvPexProductosExistencia as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPexCantidadProducto(Object pexCantidadProducto) {
		return findByProperty(PEX_CANTIDAD_PRODUCTO, pexCantidadProducto);
	}

	public List findByPexSaldo(Object pexSaldo) {
		return findByProperty(PEX_SALDO, pexSaldo);
	}

	public List findByPexCostoProducto(Object pexCostoProducto) {
		return findByProperty(PEX_COSTO_PRODUCTO, pexCostoProducto);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvPexProductosExistencia instances");
		try {
			String queryString = "from InvPexProductosExistencia";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvPexProductosExistencia merge(
			InvPexProductosExistencia detachedInstance) {
		log.debug("merging InvPexProductosExistencia instance");
		try {
			InvPexProductosExistencia result = (InvPexProductosExistencia) getSession()
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

	public void attachDirty(InvPexProductosExistencia instance) {
		log.debug("attaching dirty InvPexProductosExistencia instance");
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

	public void attachClean(InvPexProductosExistencia instance) {
		log.debug("attaching clean InvPexProductosExistencia instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//No esta siendo usada, la cree por error
	public List findByArticulo(String artCod) {
		log.debug("finding all InvPexProductosExistencia instances by artCod");
		try {
			String queryString = "from InvPexProductosExistencia pex where pex.artCodigo='" + artCod + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}