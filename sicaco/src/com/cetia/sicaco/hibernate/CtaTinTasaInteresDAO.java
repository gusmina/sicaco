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
 * CtaTinTasaInteres entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTinTasaInteres
 * @author MyEclipse Persistence Tools
 */

public class CtaTinTasaInteresDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaTinTasaInteresDAO.class);
	// property constants
	public static final String TIN_NOMBRE = "tinNombre";
	public static final String TIN_TASA = "tinTasa";
	public static final String TIN_DESCRIPCION = "tinDescripcion";

	public CtaTinTasaInteresDAO(Session session) {
		super(session);
	}

	public void save(CtaTinTasaInteres transientInstance) {
		log.debug("saving CtaTinTasaInteres instance");
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

	public void delete(CtaTinTasaInteres persistentInstance) {
		log.debug("deleting CtaTinTasaInteres instance");
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

	public CtaTinTasaInteres findById(java.lang.Integer id) {
		log.debug("getting CtaTinTasaInteres instance with id: " + id);
		try {
			CtaTinTasaInteres instance = (CtaTinTasaInteres) getSession().get(
					"com.cetia.sicaco.hibernate.CtaTinTasaInteres", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTinTasaInteres instance) {
		log.debug("finding CtaTinTasaInteres instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTinTasaInteres").add(
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
		log.debug("finding CtaTinTasaInteres instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTinTasaInteres as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTinNombre(Object tinNombre) {
		return findByProperty(TIN_NOMBRE, tinNombre);
	}

	public List findByTinTasa(Object tinTasa) {
		return findByProperty(TIN_TASA, tinTasa);
	}

	public List findByTinDescripcion(Object tinDescripcion) {
		return findByProperty(TIN_DESCRIPCION, tinDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTinTasaInteres instances");
		try {
			String queryString = "from CtaTinTasaInteres";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTinTasaInteres merge(CtaTinTasaInteres detachedInstance) {
		log.debug("merging CtaTinTasaInteres instance");
		try {
			CtaTinTasaInteres result = (CtaTinTasaInteres) getSession().merge(
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

	public void attachDirty(CtaTinTasaInteres instance) {
		log.debug("attaching dirty CtaTinTasaInteres instance");
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

	public void attachClean(CtaTinTasaInteres instance) {
		log.debug("attaching clean CtaTinTasaInteres instance");
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
		log.debug("Consiguiendo la siguiente secuencia - Tasa Interes");
		try {
			String sql = "select max(tin.tinId) + 1  as id from CtaTinTasaInteres tin";
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
			log.error("Secuencia fallida - Tasa Interes", re);
			throw re;
		}
		return id;
	} 
	
	public float maximaTasadeInteres() {
		Float tasa = new Float(0);
		log.debug("Consiguiendo la maxima Tasa Interes");
		try {
			String sql = "select max(tin.tinTasa) as tasa from CtaTinTasaInteres tin";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Double) {
				Double value = (Double) obj;
				tasa = value.floatValue();
			}else {
				tasa = new Float(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia falla max tasa - Tasa Interes", re);
			throw re;
		}
		return tasa;
	} 
	
	public float maximaTasadeInteresPrestamosActivos() {
		Float tasa = new Float(0);
		log.debug("Consiguiendo la maxima Tasa Interes en Prestamos Activos");
		try {
			String sql = "select max(cas.ctaPrePrestamo.ctaTprTipoPrestamo.ctaTinTasaInteres.tinTasa) as tasa from " +
					"CtaCasCuentaAsociado cas where cas.ctrEstEstado.estId=13";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Double) {
				Double value = (Double) obj;
				tasa = value.floatValue();
			}else {
				tasa = new Float(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia falla max tasa en prestamos activos- Tasa Interes", re);
			throw re;
		}
		return tasa;
	}
	
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaTinTasaInteres tin where tin.tinId != ? and tin.tinNombre = ?";
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
	
	public List findAllWithName() {
		try{
			String sql = "from CtaTinTasaInteres";
			Query queryObject = getSession().createQuery(sql);
			Iterator<CtaTinTasaInteres> it = queryObject.list().iterator();
			List<CtaTinTasaInteres> lista = new ArrayList<CtaTinTasaInteres>();
			while(it.hasNext()){
				CtaTinTasaInteres tasaInteres = new CtaTinTasaInteres();
				CtaTinTasaInteres tin = it.next();
				tasaInteres.setTinTasa(tin.getTinTasa());
				tasaInteres.setTinId(tin.getTinId());
				tasaInteres.setTinNombre( tin.getTinNombre()+" - "+tin.getTinTasa()+"%");
				lista.add(tasaInteres);
			}
			return lista;
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public float minimaTasadeInteres() {
		Float tasa = new Float(0);
		log.debug("Consiguiendo la minima Tasa Interes");
		try {
			String sql = "select min(tin.tinTasa) as tasa from CtaTinTasaInteres tin";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Float) {
				Float value = (Float) obj;
				tasa = value;
			}else {
				tasa = new Float(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia falla min tasa - Tasa Interes", re);
			throw re;
		}
		return tasa;
	}
	
}