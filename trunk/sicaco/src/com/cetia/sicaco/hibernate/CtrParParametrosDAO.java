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
 * CtrParParametros entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrParParametros
 * @author MyEclipse Persistence Tools
 */

public class CtrParParametrosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtrParParametrosDAO.class);
	// property constants
	public static final String PAR_DESCRIPCION = "parDescripcion";
	public static final String PAR_VALOR_STRING = "parValorString";
	public static final String PAR_VALOR_NUMBER = "parValorNumber";
	public static final String PAR_VALOR_BOOLEAN = "parValorBoolean";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public CtrParParametrosDAO(Session session) {
		super(session);
	}

	public void save(CtrParParametros transientInstance) {
		log.debug("saving CtrParParametros instance");
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

	public void delete(CtrParParametros persistentInstance) {
		log.debug("deleting CtrParParametros instance");
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

	public CtrParParametros findById(java.lang.String id) {
		log.debug("getting CtrParParametros instance with id: " + id);
		try {
			CtrParParametros instance = (CtrParParametros) getSession().get(
					"com.cetia.sicaco.hibernate.CtrParParametros", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrParParametros instance) {
		log.debug("finding CtrParParametros instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrParParametros").add(
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
		log.debug("finding CtrParParametros instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrParParametros as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByParDescripcion(Object parDescripcion) {
		return findByProperty(PAR_DESCRIPCION, parDescripcion);
	}

	public List findByParValorString(Object parValorString) {
		return findByProperty(PAR_VALOR_STRING, parValorString);
	}

	public List findByParValorNumber(Object parValorNumber) {
		return findByProperty(PAR_VALOR_NUMBER, parValorNumber);
	}

	public List findByParValorBoolean(Object parValorBoolean) {
		return findByProperty(PAR_VALOR_BOOLEAN, parValorBoolean);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all CtrParParametros instances");
		try {
			String queryString = "from CtrParParametros";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrParParametros merge(CtrParParametros detachedInstance) {
		log.debug("merging CtrParParametros instance");
		try {
			CtrParParametros result = (CtrParParametros) getSession().merge(
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

	public void attachDirty(CtrParParametros instance) {
		log.debug("attaching dirty CtrParParametros instance");
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

	public void attachClean(CtrParParametros instance) {
		log.debug("attaching clean CtrParParametros instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCount() {
		log.debug("finding all CtrParParametros instances");
		try {
			String queryString = "select count(*) from CtrParParametros";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtrParParametros> findAll(int rowStart, int rowEnd) {
		log.debug("finding all CtrParParametros instances");
		try {
			String queryString = "from CtrParParametros";
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