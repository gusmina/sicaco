package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
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
 * InvLinLinea entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvLinLinea
 * @author MyEclipse Persistence Tools
 */

public class InvLinLineaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvLinLineaDAO.class);
	// property constants
	public static final String LIN_CODIGO = "linCodigo";
	public static final String LIN_NOMBRE = "linNombre";
	public static final String LIN_DESCRIPCION = "linDescripcion";
	public static final String LIN_UTILIDAD = "linUtilidad";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvLinLineaDAO(Session session) {
		super(session);
	}

	public void save(InvLinLinea transientInstance) {
		log.debug("saving InvLinLinea instance");
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

	public void delete(InvLinLinea persistentInstance) {
		log.debug("deleting InvLinLinea instance");
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

	public InvLinLinea findById(java.lang.Integer id) {
		log.debug("getting InvLinLinea instance with id: " + id);
		try {
			InvLinLinea instance = (InvLinLinea) getSession().get(
					"com.cetia.sicaco.hibernate.InvLinLinea", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvLinLinea instance) {
		log.debug("finding InvLinLinea instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvLinLinea").add(
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
		log.debug("finding InvLinLinea instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from InvLinLinea as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLinCodigo(Object linCodigo) {
		return findByProperty(LIN_CODIGO, linCodigo);
	}
	
	public List findByLinNombre(Object linNombre) {
		return findByProperty(LIN_NOMBRE, linNombre);
	}

	public List findByLinDescripcion(Object linDescripcion) {
		return findByProperty(LIN_DESCRIPCION, linDescripcion);
	}
	
	public List findByLinUtilidad(Object linUtilidad) {
		return findByProperty(LIN_UTILIDAD, linUtilidad);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvLinLinea instances");
		try {
			String queryString = "from InvLinLinea";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvLinLinea merge(InvLinLinea detachedInstance) {
		log.debug("merging InvLinLinea instance");
		try {
			InvLinLinea result = (InvLinLinea) getSession().merge(
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

	public void attachDirty(InvLinLinea instance) {
		log.debug("attaching dirty InvLinLinea instance");
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

	public void attachClean(InvLinLinea instance) {
		log.debug("attaching clean InvLinLinea instance");
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
			String sql = "select max(oc.linCodigo) + 1  as linCodigo from InvLinLinea oc";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(1010);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	} 
	
	public Integer getTotalRowCount() {
		log.debug("counting all rows of InvLinLinea");
		try {
			String queryString = "select count(i.linId) from InvLinLinea i";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("count all InvLinLinea failed", re);
			throw re;
		}
	}
	
	public List<InvLinLinea> findAll(int rowStart,int rowEnd) {
		log.debug("finding all InvLinLinea instances");
		try {
			String queryString = "from InvLinLinea";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}