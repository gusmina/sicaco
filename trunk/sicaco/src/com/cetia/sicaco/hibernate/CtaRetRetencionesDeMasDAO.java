package com.cetia.sicaco.hibernate;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaRetRetencionesDeMas entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaRetRetencionesDeMas
 * @author MyEclipse Persistence Tools
 */

public class CtaRetRetencionesDeMasDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaRetRetencionesDeMasDAO.class);
	// property constants
	public static final String RET_CODIGO_ASC = "retCodigoAsc";
	public static final String RET_NOMBRE_ASC = "retNombreAsc";
	public static final String RET_EMP = "retEmp";
	public static final String RET_VALOR_DESC = "retValorDesc";

	public CtaRetRetencionesDeMasDAO(Session session) {
		super(session);
	}

	public void save(CtaRetRetencionesDeMas transientInstance) {
		log.debug("saving CtaRetRetencionesDeMas instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaRetRetencionesDeMas persistentInstance) {
		log.debug("deleting CtaRetRetencionesDeMas instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void deleteAll(){
		List l = this.findAll();
		Iterator i = l.iterator();
		Transaction tx = this.getSession().beginTransaction();
		while(i.hasNext()){
			CtaRetRetencionesDeMas retencion = (CtaRetRetencionesDeMas) i.next();
			this.delete(retencion);
		}
		tx.commit();
	}

	public CtaRetRetencionesDeMas findById(java.lang.Long id) {
		log.debug("getting CtaRetRetencionesDeMas instance with id: " + id);
		try {
			CtaRetRetencionesDeMas instance = (CtaRetRetencionesDeMas) getSession()
					.get("com.cetia.sicaco.hibernate.CtaRetRetencionesDeMas",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaRetRetencionesDeMas instance) {
		log.debug("finding CtaRetRetencionesDeMas instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaRetRetencionesDeMas").add(
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
		log.debug("finding CtaRetRetencionesDeMas instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaRetRetencionesDeMas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRetCodigoAsc(Object retCodigoAsc) {
		return findByProperty(RET_CODIGO_ASC, retCodigoAsc);
	}

	public List findByRetNombreAsc(Object retNombreAsc) {
		return findByProperty(RET_NOMBRE_ASC, retNombreAsc);
	}

	public List findByRetEmp(Object retEmp) {
		return findByProperty(RET_EMP, retEmp);
	}

	public List findByRetValorDesc(Object retValorDesc) {
		return findByProperty(RET_VALOR_DESC, retValorDesc);
	}

	public List findAll() {
		log.debug("finding all CtaRetRetencionesDeMas instances");
		try {
			String queryString = "from CtaRetRetencionesDeMas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaRetRetencionesDeMas merge(CtaRetRetencionesDeMas detachedInstance) {
		log.debug("merging CtaRetRetencionesDeMas instance");
		try {
			CtaRetRetencionesDeMas result = (CtaRetRetencionesDeMas) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaRetRetencionesDeMas instance) {
		log.debug("attaching dirty CtaRetRetencionesDeMas instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaRetRetencionesDeMas instance) {
		log.debug("attaching clean CtaRetRetencionesDeMas instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}