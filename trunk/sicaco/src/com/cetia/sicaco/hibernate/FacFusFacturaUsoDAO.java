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
 * FacFusFacturaUso entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.FacFusFacturaUso
 * @author MyEclipse Persistence Tools
 */

public class FacFusFacturaUsoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(FacFusFacturaUsoDAO.class);
	// property constants
	public static final String FUS_NOMBRE = "fusNombre";
	public static final String FUS_DESCRIPCION = "fusDescripcion";

	public FacFusFacturaUsoDAO(Session session) {
		super(session);
	}

	public void save(FacFusFacturaUso transientInstance) {
		log.debug("saving FacFusFacturaUso instance");
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

	public void delete(FacFusFacturaUso persistentInstance) {
		log.debug("deleting FacFusFacturaUso instance");
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

	public FacFusFacturaUso findById(java.lang.Integer id) {
		log.debug("getting FacFusFacturaUso instance with id: " + id);
		try {
			FacFusFacturaUso instance = (FacFusFacturaUso) getSession().get(
					"com.cetia.sicaco.hibernate.FacFusFacturaUso", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FacFusFacturaUso instance) {
		log.debug("finding FacFusFacturaUso instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.FacFusFacturaUso").add(
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
		log.debug("finding FacFusFacturaUso instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FacFusFacturaUso as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFusNombre(Object fusNombre) {
		return findByProperty(FUS_NOMBRE, fusNombre);
	}

	public List findByFusDescripcion(Object fusDescripcion) {
		return findByProperty(FUS_DESCRIPCION, fusDescripcion);
	}

	public List findAll() {
		log.debug("finding all FacFusFacturaUso instances");
		try {
			String queryString = "from FacFusFacturaUso";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FacFusFacturaUso merge(FacFusFacturaUso detachedInstance) {
		log.debug("merging FacFusFacturaUso instance");
		try {
			FacFusFacturaUso result = (FacFusFacturaUso) getSession().merge(
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

	public void attachDirty(FacFusFacturaUso instance) {
		log.debug("attaching dirty FacFusFacturaUso instance");
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

	public void attachClean(FacFusFacturaUso instance) {
		log.debug("attaching clean FacFusFacturaUso instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAllV(String cov,int fus) {
		log.debug("finding all FacFusFacturaUso instances");
		String queryString = "";
		try {
			if(cov.equals("v")){
				queryString = "from FacFusFacturaUso fus where fus.fusToperacion = 'v' and fus.fusId not in(" + fus+",6,8) ";
			}else{
				queryString = "from FacFusFacturaUso fus where fus.fusToperacion = 'c' and fus.fusId not in(" + fus+",5) ";
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByDosId(int id1,int id2) {
		try{
			String query = "from FacFusFacturaUso fu where fu.fusId = ? or fu.fusId = ?";
			Query queryObject = getSession().createQuery(query);
			queryObject.setParameter(0, id1);
			queryObject.setParameter(1, id2);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}