package com.cetia.sicaco.hibernate;

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
 * CtrTusTipoUso entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrTusTipoUso
 * @author MyEclipse Persistence Tools
 */

public class CtrTusTipoUsoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtrTusTipoUsoDAO.class);
	// property constants
	public static final String TUS_NOMBRE = "tusNombre";
	public static final String TUS_COMENTARIO = "tusComentario";

	public CtrTusTipoUsoDAO(Session session) {
		super(session);
	}

	public void save(CtrTusTipoUso transientInstance) {
		log.debug("saving CtrTusTipoUso instance");
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

	public void delete(CtrTusTipoUso persistentInstance) {
		log.debug("deleting CtrTusTipoUso instance");
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

	public CtrTusTipoUso findById(java.lang.String id) {
		log.debug("getting CtrTusTipoUso instance with id: " + id);
		try {
			CtrTusTipoUso instance = (CtrTusTipoUso) getSession().get(
					"com.cetia.sicaco.hibernate.CtrTusTipoUso", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrTusTipoUso instance) {
		log.debug("finding CtrTusTipoUso instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrTusTipoUso").add(
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
		log.debug("finding CtrTusTipoUso instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrTusTipoUso as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTusNombre(Object tusNombre) {
		return findByProperty(TUS_NOMBRE, tusNombre);
	}

	public List findByTusComentario(Object tusComentario) {
		return findByProperty(TUS_COMENTARIO, tusComentario);
	}

	public List findAll() {
		log.debug("finding all CtrTusTipoUso instances");
		try {
			String queryString = "from CtrTusTipoUso";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrTusTipoUso merge(CtrTusTipoUso detachedInstance) {
		log.debug("merging CtrTusTipoUso instance");
		try {
			CtrTusTipoUso result = (CtrTusTipoUso) getSession().merge(
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

	public void attachDirty(CtrTusTipoUso instance) {
		log.debug("attaching dirty CtrTusTipoUso instance");
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

	public void attachClean(CtrTusTipoUso instance) {
		log.debug("attaching clean CtrTusTipoUso instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}