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
 * CtaCntCodigosAnteriores entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaCntCodigosAnteriores
 * @author MyEclipse Persistence Tools
 */

public class CtaCntCodigosAnterioresDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaCntCodigosAnterioresDAO.class);
	// property constants
	public static final String CNT_COD = "cntCod";

	public CtaCntCodigosAnterioresDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void save(CtaCntCodigosAnteriores transientInstance) {
		log.debug("saving CtaCntCodigosAnteriores instance");
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

	public void delete(CtaCntCodigosAnteriores persistentInstance) {
		log.debug("deleting CtaCntCodigosAnteriores instance");
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

	public CtaCntCodigosAnteriores findById(java.lang.Integer id) {
		log.debug("getting CtaCntCodigosAnteriores instance with id: " + id);
		try {
			CtaCntCodigosAnteriores instance = (CtaCntCodigosAnteriores) getSession()
					.get("com.cetia.sicaco.hibernate.CtaCntCodigosAnteriores",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaCntCodigosAnteriores instance) {
		log.debug("finding CtaCntCodigosAnteriores instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaCntCodigosAnteriores").add(
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
		log.debug("finding CtaCntCodigosAnteriores instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaCntCodigosAnteriores as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findCodigosVencidos(String propertyName, Object value) {
		log.debug("finding CtaCntCodigosAnteriores instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaCntCodigosAnteriores as model where model."
					+ propertyName + "= ? and model.cntFechaFin is not null";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCntCod(Object cntCod) {
		return findByProperty(CNT_COD, cntCod);
	}

	public List findAll() {
		log.debug("finding all CtaCntCodigosAnteriores instances");
		try {
			String queryString = "from CtaCntCodigosAnteriores";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaCntCodigosAnteriores merge(
			CtaCntCodigosAnteriores detachedInstance) {
		log.debug("merging CtaCntCodigosAnteriores instance");
		try {
			CtaCntCodigosAnteriores result = (CtaCntCodigosAnteriores) getSession()
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

	public void attachDirty(CtaCntCodigosAnteriores instance) {
		log.debug("attaching dirty CtaCntCodigosAnteriores instance");
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

	public void attachClean(CtaCntCodigosAnteriores instance) {
		log.debug("attaching clean CtaCntCodigosAnteriores instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public CtaCntCodigosAnteriores findLastCode(String codigo) {
		try {
			String queryString = "from CtaCntCodigosAnteriores as cnt where cnt.cntFechaFin is null and cnt.ctaAscAsociado.ascId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codigo);
			CtaCntCodigosAnteriores ret = (CtaCntCodigosAnteriores)queryObject.list().get(0);
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Integer getTotalRowCountCodigosAnterioresxAsociado(String ascid) {
		log.debug("counting all rows of CtaCntCodigosAnteriores");
		try {
			String queryString = "select count(h.cntId)" + 
			                     "from CtaCntCodigosAnteriores h "+
			                     "where h.ctaAscAsociado.ascId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascid);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaCntCodigosAnteriores> findAllTransaccionXAsociado(int rowStart,int rowEnd, String ascid) {
		log.debug("finding all CtaCntCodigosAnteriores hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaCntCodigosAnteriores hse" +
                                 "where hse.ctaAscAsociado.ascId = ? " +
                                 "order by hse.cntId desc";
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