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
 * CtaMxsMovimientoSeguros entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaMxsMovimientoSeguros
 * @author MyEclipse Persistence Tools
 */

public class CtaMxsMovimientoSegurosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaMxsMovimientoSegurosDAO.class);
	// property constants
	public static final String MXS_MONTO = "mxsMonto";
	public static final String MXS_SALDO = "mxsSaldo";
	public static final String MXS_FECHA = "mxsFecha";

	public CtaMxsMovimientoSegurosDAO(Session session) {
		super(session);
	}

	public void save(CtaMxsMovimientoSeguros transientInstance) {
		log.debug("saving CtaMxsMovimientoSeguros instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaMxsMovimientoSeguros persistentInstance) {
		log.debug("deleting CtaMxsMovimientoSeguros instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaMxsMovimientoSeguros findById(java.lang.Integer id) {
		log.debug("getting CtaMxsMovimientoSeguros instance with id: " + id);
		try {
			CtaMxsMovimientoSeguros instance = (CtaMxsMovimientoSeguros) getSession()
					.get("com.cetia.sicaco.hibernate.CtaMxsMovimientoSeguros",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaMxsMovimientoSeguros instance) {
		log.debug("finding CtaMxsMovimientoSeguros instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaMxsMovimientoSeguros").add(
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
		log.debug("finding CtaMxsMovimientoSeguros instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaMxsMovimientoSeguros as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMxsMonto(Object mxsMonto) {
		return findByProperty(MXS_MONTO, mxsMonto);
	}

	public List findByMxsSaldo(Object mxsSaldo) {
		return findByProperty(MXS_SALDO, mxsSaldo);
	}

	public List findByMxsFecha(Object mxsFecha) {
		return findByProperty(MXS_FECHA, mxsFecha);
	}

	public List findAll() {
		log.debug("finding all CtaMxsMovimientoSeguros instances");
		try {
			String queryString = "from CtaMxsMovimientoSeguros";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaMxsMovimientoSeguros merge(
			CtaMxsMovimientoSeguros detachedInstance) {
		log.debug("merging CtaMxsMovimientoSeguros instance");
		try {
			CtaMxsMovimientoSeguros result = (CtaMxsMovimientoSeguros) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaMxsMovimientoSeguros instance) {
		log.debug("attaching dirty CtaMxsMovimientoSeguros instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaMxsMovimientoSeguros instance) {
		log.debug("attaching clean CtaMxsMovimientoSeguros instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public CtaMxsMovimientoSeguros findUltimoMovimiento(String segId) {
		log.debug("finding all CtaMxsMovimientoSeguros instances");
		try {
			String queryString = "from CtaMxsMovimientoSeguros mxs where mxs.ctaSegSeguros.segId='" + segId + "' order by mxs.mxsId desc";
			Query queryObject = getSession().createQuery(queryString);
			if(queryObject.list().size()<1){
				return null;
			}
			return (CtaMxsMovimientoSeguros) queryObject.list().get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	
	public CtaMxsMovimientoSeguros findMovimientoAnterior(String idCuenta, String fecha){
		try {
			String queryString = "from CtaMxsMovimientoSeguros as mxs"
								+"  where mxs.ctaSegSeguros.segId= ? "+
								" and mxs.mxsFecha <= ?  order by mxs.mxsFecha desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			queryObject.setParameter(1, fecha);
			
			List lista = queryObject.list();
			return (!lista.isEmpty()?(CtaMxsMovimientoSeguros)lista.get(0):null);
			
		} catch (RuntimeException re) {
			log.error("find movimiento anterior failed", re);
			throw re;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public List<CtaMxsMovimientoSeguros> findMovimientosSiguientes(String idCuenta, String fecha){
		try {
			String queryString = "from CtaMxsMovimientoSeguros as mxa"
								+"  where mxa.ctaSegSeguros.segId = ? "+
								" and mxa.mxsFecha > ?  order by mxa.mxsFecha";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			queryObject.setParameter(1, fecha);
			List<CtaMxsMovimientoSeguros> lista = queryObject.list();
			return lista;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}	
	}
	
	
	
}