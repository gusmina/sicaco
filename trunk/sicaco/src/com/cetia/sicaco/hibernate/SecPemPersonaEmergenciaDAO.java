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
 * SecPemPersonaEmergencia entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecPemPersonaEmergencia
 * @author MyEclipse Persistence Tools
 */

public class SecPemPersonaEmergenciaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecPemPersonaEmergenciaDAO.class);
	// property constants
	public static final String PEM_PRIMER_NOMBRE = "pemPrimerNombre";
	public static final String PEM_SEGUNDO_NOMBRE = "pemSegundoNombre";
	public static final String PEM_TERCER_NOMBRE = "pemTercerNombre";
	public static final String PEM_PRIMER_APELLIDO = "pemPrimerApellido";
	public static final String PEM_SEGUNDO_APELLIDO = "pemSegundoApellido";
	public static final String PEM_APELLIDO_CASADA = "pemApellidoCasada";
	public static final String PEM_TELEFONO = "pemTelefono";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public SecPemPersonaEmergenciaDAO(Session session) {
		super(session);
	}

	public void save(SecPemPersonaEmergencia transientInstance) {
		log.debug("saving SecPemPersonaEmergencia instance");
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

	public void delete(SecPemPersonaEmergencia persistentInstance) {
		log.debug("deleting SecPemPersonaEmergencia instance");
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

	public SecPemPersonaEmergencia findById(java.lang.Integer id) {
		log.debug("getting SecPemPersonaEmergencia instance with id: " + id);
		try {
			SecPemPersonaEmergencia instance = (SecPemPersonaEmergencia) getSession()
					.get(
							"com.cetia.sicaco.hibernate.SecPemPersonaEmergencia",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecPemPersonaEmergencia instance) {
		log.debug("finding SecPemPersonaEmergencia instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecPemPersonaEmergencia")
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
		log.debug("finding SecPemPersonaEmergencia instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecPemPersonaEmergencia as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPemPrimerNombre(Object pemPrimerNombre) {
		return findByProperty(PEM_PRIMER_NOMBRE, pemPrimerNombre);
	}

	public List findByPemSegundoNombre(Object pemSegundoNombre) {
		return findByProperty(PEM_SEGUNDO_NOMBRE, pemSegundoNombre);
	}

	public List findByPemTercerNombre(Object pemTercerNombre) {
		return findByProperty(PEM_TERCER_NOMBRE, pemTercerNombre);
	}

	public List findByPemPrimerApellido(Object pemPrimerApellido) {
		return findByProperty(PEM_PRIMER_APELLIDO, pemPrimerApellido);
	}

	public List findByPemSegundoApellido(Object pemSegundoApellido) {
		return findByProperty(PEM_SEGUNDO_APELLIDO, pemSegundoApellido);
	}

	public List findByPemApellidoCasada(Object pemApellidoCasada) {
		return findByProperty(PEM_APELLIDO_CASADA, pemApellidoCasada);
	}

	public List findByPemTelefono(Object pemTelefono) {
		return findByProperty(PEM_TELEFONO, pemTelefono);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all SecPemPersonaEmergencia instances");
		try {
			String queryString = "from SecPemPersonaEmergencia";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecPemPersonaEmergencia merge(
			SecPemPersonaEmergencia detachedInstance) {
		log.debug("merging SecPemPersonaEmergencia instance");
		try {
			SecPemPersonaEmergencia result = (SecPemPersonaEmergencia) getSession()
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

	public void attachDirty(SecPemPersonaEmergencia instance) {
		log.debug("attaching dirty SecPemPersonaEmergencia instance");
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

	public void attachClean(SecPemPersonaEmergencia instance) {
		log.debug("attaching clean SecPemPersonaEmergencia instance");
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
			String sql = "select max(pe.pemId) + 1  as pemId from SecPemPersonaEmergencia pe";
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