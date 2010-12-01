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
 * ConTicTipoCuenta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConTicTipoCuenta
 * @author MyEclipse Persistence Tools
 */

public class ConTicTipoCuentaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ConTicTipoCuentaDAO.class);
	// property constants
	public static final String TIC_NOMBRE = "ticNombre";
	public static final String TIC_ACREE_DEUDO = "ticAcreeDeudo";

	public ConTicTipoCuentaDAO(Session session) {
		super(session);
	}

	public void save(ConTicTipoCuenta transientInstance) {
		log.debug("saving ConTicTipoCuenta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConTicTipoCuenta persistentInstance) {
		log.debug("deleting ConTicTipoCuenta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConTicTipoCuenta findById(java.lang.Integer id) {
		log.debug("getting ConTicTipoCuenta instance with id: " + id);
		try {
			ConTicTipoCuenta instance = (ConTicTipoCuenta) getSession().get(
					"com.cetia.sicaco.hibernate.ConTicTipoCuenta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConTicTipoCuenta instance) {
		log.debug("finding ConTicTipoCuenta instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConTicTipoCuenta").add(
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
		log.debug("finding ConTicTipoCuenta instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConTicTipoCuenta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTicNombre(Object ticNombre) {
		return findByProperty(TIC_NOMBRE, ticNombre);
	}

	public List findByTicAcreeDeudo(Object ticAcreeDeudo) {
		return findByProperty(TIC_ACREE_DEUDO, ticAcreeDeudo);
	}

	public List findAll() {
		log.debug("finding all ConTicTipoCuenta instances");
		try {
			String queryString = "from ConTicTipoCuenta order by ticId";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConTicTipoCuenta merge(ConTicTipoCuenta detachedInstance) {
		log.debug("merging ConTicTipoCuenta instance");
		try {
			ConTicTipoCuenta result = (ConTicTipoCuenta) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConTicTipoCuenta instance) {
		log.debug("attaching dirty ConTicTipoCuenta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConTicTipoCuenta instance) {
		log.debug("attaching clean ConTicTipoCuenta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from ConTicTipoCuenta ta where ta.ticId != ? and ta.ticNombre = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, id);
			queryObject.setParameter(1, nombre);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}