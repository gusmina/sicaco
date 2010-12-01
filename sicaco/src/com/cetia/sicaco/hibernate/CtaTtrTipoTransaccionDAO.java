package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
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
 * CtaTtrTipoTransaccion entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion
 * @author MyEclipse Persistence Tools
 */

public class CtaTtrTipoTransaccionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaTtrTipoTransaccionDAO.class);
	// property constants
	public static final String TTR_NOMBRE = "ttrNombre";
	public static final String TTR_DESCRIPCION = "ttrDescripcion";
	public static final String TTR_USO = "ttrUso";

	public CtaTtrTipoTransaccionDAO(Session session) {
		super(session);
	}

	public void save(CtaTtrTipoTransaccion transientInstance) {
		log.debug("saving CtaTtrTipoTransaccion instance");
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

	public void delete(CtaTtrTipoTransaccion persistentInstance) {
		log.debug("deleting CtaTtrTipoTransaccion instance");
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

	public CtaTtrTipoTransaccion findById(java.lang.Integer id) {
		log.debug("getting CtaTtrTipoTransaccion instance with id: " + id);
		try {
			CtaTtrTipoTransaccion instance = (CtaTtrTipoTransaccion) getSession()
					.get("com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTtrTipoTransaccion instance) {
		log.debug("finding CtaTtrTipoTransaccion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTtrTipoTransaccion").add(
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
		log.debug("finding CtaTtrTipoTransaccion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTtrTipoTransaccion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTtrNombre(Object ttrNombre) {
		return findByProperty(TTR_NOMBRE, ttrNombre);
	}

	public List findByTtrDescripcion(Object ttrDescripcion) {
		return findByProperty(TTR_DESCRIPCION, ttrDescripcion);
	}

	public List findByTtrUso(Object ttrUso) {
		return findByProperty(TTR_USO, ttrUso);
	}

	public List findAll() {
		log.debug("finding all CtaTtrTipoTransaccion instances");
		try {
			String queryString = "from CtaTtrTipoTransaccion order by ttrNombre";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTtrTipoTransaccion merge(CtaTtrTipoTransaccion detachedInstance) {
		log.debug("merging CtaTtrTipoTransaccion instance");
		try {
			CtaTtrTipoTransaccion result = (CtaTtrTipoTransaccion) getSession()
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

	public void attachDirty(CtaTtrTipoTransaccion instance) {
		log.debug("attaching dirty CtaTtrTipoTransaccion instance");
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

	public void attachClean(CtaTtrTipoTransaccion instance) {
		log.debug("attaching clean CtaTtrTipoTransaccion instance");
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
			String sql = "select max(tt.ttrId) + 1  as id from CtaTtrTipoTransaccion tt";
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
		
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaTtrTipoTransaccion tt where tt.ttrId != ? and tt.ttrNombre = ?";
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
	
	public List findAllNotInCpa() {
		try {
			String queryString = "from CtaTtrTipoTransaccion ttr where ttr.ttrId not in (select distinct cpa.ctaTtrTipoTransaccion.ttrId from ConCpaConceptoPartida cpa where cpa.ctaTtrTipoTransaccion.ttrId is not null)";
			Query queryObject = getSession().createQuery(queryString);
			List lista =  queryObject.list();
			return lista;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public Integer getTotalRowCount() {
		log.debug("counting all rows of CtaTtrTipoTransaccion");
		try {
			String queryString = "select count(h.ttrId)from CtaTtrTipoTransaccion h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaTtrTipoTransaccion> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaTtrTipoTransaccion hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaTtrTipoTransaccion hse order by hse.ttrId desc";
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