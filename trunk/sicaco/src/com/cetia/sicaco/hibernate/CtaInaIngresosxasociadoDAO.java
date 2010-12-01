package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaInaIngresosxasociado entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaInaIngresosxasociado
 * @author MyEclipse Persistence Tools
 */

public class CtaInaIngresosxasociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaInaIngresosxasociadoDAO.class);

	// property constants

	public CtaInaIngresosxasociadoDAO(Session session) {
		super(session);
	}

	public void save(CtaInaIngresosxasociado transientInstance) {
		log.debug("saving CtaInaIngresosxasociado instance");
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

	public void delete(CtaInaIngresosxasociado persistentInstance) {
		log.debug("deleting CtaInaIngresosxasociado instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaInaIngresosxasociado findById(java.lang.Integer id) {
		log.debug("getting CtaInaIngresosxasociado instance with id: " + id);
		try {
			CtaInaIngresosxasociado instance = (CtaInaIngresosxasociado) getSession()
					.get("com.cetia.sicaco.hibernate.CtaInaIngresosxasociado",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaInaIngresosxasociado instance) {
		log.debug("finding CtaInaIngresosxasociado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaInaIngresosxasociado").add(
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
		log.debug("finding CtaInaIngresosxasociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaInaIngresosxasociado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all CtaInaIngresosxasociado instances");
		try {
			String queryString = "from CtaInaIngresosxasociado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaInaIngresosxasociado findLastIngreso(String ascId) {
		log.debug("finding all CtaInaIngresosxasociado instances");
		try {
			String queryString = "from CtaInaIngresosxasociado ina where ina.inaFechaSalida is null and ina.ctaAscAsociado.ascId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			return (CtaInaIngresosxasociado) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public CtaInaIngresosxasociado merge(
			CtaInaIngresosxasociado detachedInstance) {
		log.debug("merging CtaInaIngresosxasociado instance");
		try {
			CtaInaIngresosxasociado result = (CtaInaIngresosxasociado) getSession()
					.merge(detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaInaIngresosxasociado instance) {
		log.debug("attaching dirty CtaInaIngresosxasociado instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaInaIngresosxasociado instance) {
		log.debug("attaching clean CtaInaIngresosxasociado instance");
		try {
			getSession().lock(instance, LockMode.NONE);
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
			String sql = "select max(ina.inaId) + 1  as id from CtaInaIngresosxasociado ina";
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
	public Integer getTotalRowCount() {
		log.debug("counting all rows of CtaInaIngresosxasociado");
		try {
			String queryString = "select count(h.inaId)from CtaInaIngresosxasociado h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SecHseHistorialSesion> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaInaIngresosxasociado hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaInaIngresosxasociado hse order by hse.inaId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCountIngresoxAsociado(String ascid) {
		log.debug("counting all rows of CtaInaIngresosxasociado");
		try {
			String queryString = "select count(h.inaId) from CtaInaIngresosxasociado h " + 
                                 "where h.ctaAscAsociado.ascId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascid);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaInaIngresosxasociado> findAllIngresoXAsociado(int rowStart,int rowEnd, String ascid) {
		log.debug("finding all CtaInaIngresoxasociado hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaInaIngresosxasociado hse " +
                                 "where hse.ctaAscAsociado.ascId = ? " +
                                 "order by hse.inaId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			queryObject.setParameter(0, ascid);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}