package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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
 * CtaPlmPlanMeses entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaPlmPlanMeses
 * @author MyEclipse Persistence Tools
 */

public class CtaPlmPlanMesesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaPlmPlanMesesDAO.class);
	// property constants
	public static final String PLM_NOMBRE = "plmNombre";
	public static final String PLM_DESCRIPCION = "plmDescripcion";
	public static final String PLM_DURACION = "plmDuracion";

	public CtaPlmPlanMesesDAO(Session session) {
		super(session);
	}

	public void save(CtaPlmPlanMeses transientInstance) {
		log.debug("saving CtaPlmPlanMeses instance");
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

	public void delete(CtaPlmPlanMeses persistentInstance) {
		log.debug("deleting CtaPlmPlanMeses instance");
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

	public CtaPlmPlanMeses findById(java.lang.Integer id) {
		log.debug("getting CtaPlmPlanMeses instance with id: " + id);
		try {
			CtaPlmPlanMeses instance = (CtaPlmPlanMeses) getSession().get(
					"com.cetia.sicaco.hibernate.CtaPlmPlanMeses", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaPlmPlanMeses instance) {
		log.debug("finding CtaPlmPlanMeses instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaPlmPlanMeses").add(
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
		log.debug("finding CtaPlmPlanMeses instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaPlmPlanMeses as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPlmNombre(Object plmNombre) {
		return findByProperty(PLM_NOMBRE, plmNombre);
	}

	public List findByPlmDescripcion(Object plmDescripcion) {
		return findByProperty(PLM_DESCRIPCION, plmDescripcion);
	}

	public List findByPlmDuracion(Object plmDuracion) {
		return findByProperty(PLM_DURACION, plmDuracion);
	}

	public List findAll() {
		log.debug("finding all CtaPlmPlanMeses instances");
		try {
			String queryString = "from CtaPlmPlanMeses";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaPlmPlanMeses merge(CtaPlmPlanMeses detachedInstance) {
		log.debug("merging CtaPlmPlanMeses instance");
		try {
			CtaPlmPlanMeses result = (CtaPlmPlanMeses) getSession().merge(
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

	public void attachDirty(CtaPlmPlanMeses instance) {
		log.debug("attaching dirty CtaPlmPlanMeses instance");
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

	public void attachClean(CtaPlmPlanMeses instance) {
		log.debug("attaching clean CtaPlmPlanMeses instance");
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
		log.debug("Consiguiendo la siguiente secuencia - Plan Mes");
		try {
			String sql = "select max(plm.plmId) + 1  as id from CtaPlmPlanMeses plm";
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
			log.error("Secuencia fallida - Plan Mes", re);
			throw re;
		}
		return id;
	} 
	
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaPlmPlanMeses plm where plm.plmId != ? and plm.plmNombre = ?";
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
	
	public List<CtaPlmPlanMeses> findAllWithName() {
		try{
			String sql = "from CtaPlmPlanMeses";
			Query queryObject = getSession().createQuery(sql);
			Iterator<CtaPlmPlanMeses> it = queryObject.list().iterator();
			List<CtaPlmPlanMeses> lista = new ArrayList<CtaPlmPlanMeses>();
			while(it.hasNext()){
				CtaPlmPlanMeses plm = new CtaPlmPlanMeses();
				CtaPlmPlanMeses plan = it.next();
				plm.setPlmNombre(plan.getPlmNombre()+"("+plan.getPlmDuracion()+")");
				plm.setPlmId(plan.getPlmId());
				plm.setPlmDuracion(plan.getPlmDuracion());
				lista.add(plm);
			}
			getSession().clear();
			return lista;
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}