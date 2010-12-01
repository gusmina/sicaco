package com.cetia.sicaco.hibernate;

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
 * ConCteConfiguracionEstadoFinanciero entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.ConCteConfiguracionEstadoFinanciero
 * @author MyEclipse Persistence Tools
 */

public class ConCteConfiguracionEstadoFinancieroDAO extends BaseHibernateDAO {

	private static final Log log = LogFactory
			.getLog(ConCteConfiguracionEstadoFinancieroDAO.class);
	// property constants
	public static final String CET_INF = "cetInf";

	public ConCteConfiguracionEstadoFinancieroDAO(Session session) {
		super(session);
	}
	
	public void save(ConCteConfiguracionEstadoFinanciero transientInstance) {
		log.debug("saving ConCteConfiguracionEstadoFinanciero instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConCteConfiguracionEstadoFinanciero persistentInstance) {
		log.debug("deleting ConCteConfiguracionEstadoFinanciero instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConCteConfiguracionEstadoFinanciero findById(java.lang.Integer id) {
		log
				.debug("getting ConCteConfiguracionEstadoFinanciero instance with id: "
						+ id);
		try {
			ConCteConfiguracionEstadoFinanciero instance = (ConCteConfiguracionEstadoFinanciero) getSession()
					.get(
							"com.cetia.sicaco.hibernate.ConCteConfiguracionEstadoFinanciero",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConCteConfiguracionEstadoFinanciero instance) {
		log
				.debug("finding ConCteConfiguracionEstadoFinanciero instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.cetia.sicaco.hibernate.ConCteConfiguracionEstadoFinanciero")
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
		log
				.debug("finding ConCteConfiguracionEstadoFinanciero instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConCteConfiguracionEstadoFinanciero as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCetInf(Object cetInf) {
		return findByProperty(CET_INF, cetInf);
	}

	public List findAll() {
		log.debug("finding all ConCteConfiguracionEstadoFinanciero instances");
		try {
			String queryString = "from ConCteConfiguracionEstadoFinanciero";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConCteConfiguracionEstadoFinanciero merge(
			ConCteConfiguracionEstadoFinanciero detachedInstance) {
		log.debug("merging ConCteConfiguracionEstadoFinanciero instance");
		try {
			ConCteConfiguracionEstadoFinanciero result = (ConCteConfiguracionEstadoFinanciero) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConCteConfiguracionEstadoFinanciero instance) {
		log
				.debug("attaching dirty ConCteConfiguracionEstadoFinanciero instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConCteConfiguracionEstadoFinanciero instance) {
		log
				.debug("attaching clean ConCteConfiguracionEstadoFinanciero instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public int[] findAllByModuleToArray(String var,int banda) {
		log.debug("finding all ConCteConfiguracionEstadoFinanciero instances");
		try {
			String queryString = "select c.conCueCuenta.cueId from ConCteConfiguracionEstadoFinanciero c where c.cetInf= ? and c.cetBanda = ?  order by c.cetPosc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, var);
			queryObject.setParameter(1, banda);
			Object[] array =queryObject.list().toArray();
			int resp[] = new int[array.length];
			for(int i = 0; i < array.length;i++){
				resp[i] = new Integer(array[i].toString()).intValue();
			}
			
			return resp;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteByModuleAndBand(String var,int band) {
		log.debug("finding all ConCteConfiguracionEstadoFinanciero instances");
		try {
			Transaction tx = this.getSession().beginTransaction();
			String queryString = "delete from ConCteConfiguracionEstadoFinanciero where cetInf= ? and cetBanda = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, var);
			queryObject.setParameter(1, band);
			queryObject.executeUpdate();
			tx.commit();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int[] findAllByModuleToArray(String var) {
		log.debug("finding all ConCteConfiguracionEstadoFinanciero instances");
		try {
			String queryString = "select c.conCueCuenta.cueId from ConCteConfiguracionEstadoFinanciero c where c.cetInf= ?  order by c.cetPosc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, var);
			Object[] array =queryObject.list().toArray();
			int resp[] = new int[array.length];
			for(int i = 0; i < array.length;i++){
				resp[i] = new Integer(array[i].toString()).intValue();
			}
			return resp;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}