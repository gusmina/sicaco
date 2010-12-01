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
 * PartidaContable entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.PartidaContable
 * @author MyEclipse Persistence Tools
 */

public class PartidaContableDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PartidaContableDAO.class);
	// property constants
	public static final String CODIGO_CUENTA = "codigoCuenta";
	public static final String NOMBRE_CUENTA = "nombreCuenta";
	public static final String PARCIAL = "parcial";
	public static final String DEBE = "debe";
	public static final String HABER = "haber";
	public static final String PCO_ID = "pcoId";

	public PartidaContableDAO(Session session) {
		super(session);
	}

	public void save(PartidaContable transientInstance) {
		log.debug("saving PartidaContable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PartidaContable persistentInstance) {
		log.debug("deleting PartidaContable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PartidaContable findById(java.lang.Integer id) {
		log.debug("getting PartidaContable instance with id: " + id);
		try {
			PartidaContable instance = (PartidaContable) getSession().get(
					"com.cetia.sicaco.hibernate.PartidaContable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PartidaContable instance) {
		log.debug("finding PartidaContable instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.PartidaContable").add(
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
		log.debug("finding PartidaContable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PartidaContable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCodigoCuenta(String property,Object codigoCuenta/*,int debeHaber*/) {
		try {
			String queryString = "from PartidaContable as model where model."+property+"= ? ";/*and model.*/
			
			/*switch (debeHaber) {
			case 1://1 = DEBE mayor;
				queryString += "debe>0";
				break;
			case 0://0 = HABER mayor
				queryString += "haber>0";
				break;
			case 2://debe parcial
				queryString += "haber=0";
				break;
			case 3://haber parcial
				queryString += "haber=0";
				break;
			}*/
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codigoCuenta);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombreCuenta(Object nombreCuenta) {
		return findByProperty(NOMBRE_CUENTA, nombreCuenta);
	}

	public List findByParcial(Object parcial) {
		return findByProperty(PARCIAL, parcial);
	}

	public List findByDebe(Object debe) {
		return findByProperty(DEBE, debe);
	}

	public List findByHaber(Object haber) {
		return findByProperty(HABER, haber);
	}

	public List findByPcoId(Object pcoId) {
		return findByProperty(PCO_ID, pcoId);
	}

	public List findAll() {
		log.debug("finding all PartidaContable instances");
		try {
			String queryString = "from PartidaContable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PartidaContable merge(PartidaContable detachedInstance) {
		log.debug("merging PartidaContable instance");
		try {
			PartidaContable result = (PartidaContable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PartidaContable instance) {
		log.debug("attaching dirty PartidaContable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PartidaContable instance) {
		log.debug("attaching clean PartidaContable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void deleteAll() {
		log.debug("deleting all instances of PartidaContable ");
		try {
			String queryString = "delete from PartidaContable";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("deleting all instances of PartidaContable failed", re);
			throw re;
		}
	}
}