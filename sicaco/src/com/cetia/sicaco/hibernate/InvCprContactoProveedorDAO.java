package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
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
 * InvCprContactoProveedor entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvCprContactoProveedor
 * @author MyEclipse Persistence Tools
 */

public class InvCprContactoProveedorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvCprContactoProveedorDAO.class);
	// property constants
	public static final String CPR_NOMBRE_COMPLETO = "cprNombreCompleto";
	public static final String CPR_NUMERO_TEL_OFICINA = "cprNumeroTelOficina";
	public static final String CPR_NUMERO_TEL_CELULAR = "cprNumeroTelCelular";
	public static final String CPR_NUMERO_FAX = "cprNumeroFax";
	public static final String CPR_EMAIL = "cprEmail";
	public static final String CPR_EMPRESA_CELULAR = "cprEmpresaCelular";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvCprContactoProveedorDAO(Session session) {
		super(session);
	}

	public void save(InvCprContactoProveedor transientInstance) {
		log.debug("saving InvCprContactoProveedor instance");
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

	public void delete(InvCprContactoProveedor persistentInstance) {
		log.debug("deleting InvCprContactoProveedor instance");
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

	public InvCprContactoProveedor findById(java.lang.Integer id) {
		log.debug("getting InvCprContactoProveedor instance with id: " + id);
		try {
			InvCprContactoProveedor instance = (InvCprContactoProveedor) getSession()
					.get("com.cetia.sicaco.hibernate.InvCprContactoProveedor",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvCprContactoProveedor instance) {
		log.debug("finding InvCprContactoProveedor instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvCprContactoProveedor").add(
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
		log.debug("finding InvCprContactoProveedor instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvCprContactoProveedor as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCprNombreCompleto(Object cprNombreCompleto) {
		return findByProperty(CPR_NOMBRE_COMPLETO, cprNombreCompleto);
	}

	public List findByCprNumeroTelOficina(Object cprNumeroTelOficina) {
		return findByProperty(CPR_NUMERO_TEL_OFICINA, cprNumeroTelOficina);
	}

	public List findByCprNumeroTelCelular(Object cprNumeroTelCelular) {
		return findByProperty(CPR_NUMERO_TEL_CELULAR, cprNumeroTelCelular);
	}
	
	public List findByCprNumeroFax(Object cprNumeroFax) {
		return findByProperty(CPR_NUMERO_FAX, cprNumeroFax);
	}

	public List findByCprEmpresaCelular(Object cprEmpresaCelular) {
		return findByProperty(CPR_EMPRESA_CELULAR, cprEmpresaCelular);
	}
	
	public List findByCprEmail(Object cprEmail) {
		return findByProperty(CPR_EMAIL, cprEmail);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvCprContactoProveedor instances");
		try {
			String queryString = "from InvCprContactoProveedor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvCprContactoProveedor merge(
			InvCprContactoProveedor detachedInstance) {
		log.debug("merging InvCprContactoProveedor instance");
		try {
			InvCprContactoProveedor result = (InvCprContactoProveedor) getSession()
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

	public void attachDirty(InvCprContactoProveedor instance) {
		log.debug("attaching dirty InvCprContactoProveedor instance");
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

	public void attachClean(InvCprContactoProveedor instance) {
		log.debug("attaching clean InvCprContactoProveedor instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
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
			String sql = "select max(cp.cprId) + 1  as cprId from InvCprContactoProveedor cp";
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
}