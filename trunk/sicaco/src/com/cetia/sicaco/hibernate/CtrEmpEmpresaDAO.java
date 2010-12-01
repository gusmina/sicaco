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
 * CtrEmpEmpresa entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrEmpEmpresa
 * @author MyEclipse Persistence Tools
 */

public class CtrEmpEmpresaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtrEmpEmpresaDAO.class);
	// property constants
	public static final String EMP_NOMBRE = "empNombre";
	public static final String EMP_NIT = "empNit";
	public static final String EMP_DIRECCION = "empDireccion";
	public static final String EMP_TEL = "empTel";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String EMP_REGISTRO = "empRegistro";
	public static final String EMP_GIRO	= "empGiro";

	public CtrEmpEmpresaDAO(Session session) {
		super(session);
	}

	public void save(CtrEmpEmpresa transientInstance) {
		log.debug("saving CtrEmpEmpresa instance");
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

	public void delete(CtrEmpEmpresa persistentInstance) {
		log.debug("deleting CtrEmpEmpresa instance");
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

	public CtrEmpEmpresa findById(java.lang.Integer id) {
		log.debug("getting CtrEmpEmpresa instance with id: " + id);
		try {
			CtrEmpEmpresa instance = (CtrEmpEmpresa) getSession().get(
					"com.cetia.sicaco.hibernate.CtrEmpEmpresa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrEmpEmpresa instance) {
		log.debug("finding CtrEmpEmpresa instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrEmpEmpresa").add(
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
		log.debug("finding CtrEmpEmpresa instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrEmpEmpresa as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEmpNombre(Object empNombre) {
		return findByProperty(EMP_NOMBRE, empNombre);
	}

	public List findByEmpNit(Object empNit) {
		return findByProperty(EMP_NIT, empNit);
	}

	public List findByEmpDireccion(Object empDireccion) {
		return findByProperty(EMP_DIRECCION, empDireccion);
	}

	public List findByEmpTel(Object empTel) {
		return findByProperty(EMP_TEL, empTel);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}
	
	public List findByEmpRegistro(Object empRegistro) {
		return findByProperty(EMP_REGISTRO, empRegistro);
	}
	
	public List findByEmpGiro(Object empGiro) {
		return findByProperty(EMP_GIRO, empGiro);
	}

	public List findAll() {
		log.debug("finding all CtrEmpEmpresa instances");
		try {
			String queryString = "from CtrEmpEmpresa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrEmpEmpresa merge(CtrEmpEmpresa detachedInstance) {
		log.debug("merging CtrEmpEmpresa instance");
		try {
			CtrEmpEmpresa result = (CtrEmpEmpresa) getSession().merge(
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

	public void attachDirty(CtrEmpEmpresa instance) {
		log.debug("attaching dirty CtrEmpEmpresa instance");
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

	public void attachClean(CtrEmpEmpresa instance) {
		log.debug("attaching clean CtrEmpEmpresa instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}