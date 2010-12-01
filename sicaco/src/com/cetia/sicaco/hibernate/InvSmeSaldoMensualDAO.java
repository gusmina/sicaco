package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvSmeSaldoMensual entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvSmeSaldoMensual
 * @author MyEclipse Persistence Tools
 */

public class InvSmeSaldoMensualDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvSmeSaldoMensualDAO.class);

	public InvSmeSaldoMensualDAO(Session session) {
		super(session);
	}

	public void save(InvSmeSaldoMensual transientInstance) {
		log.debug("saving InvSmeSaldoMensual instance");
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

	public void delete(InvSmeSaldoMensual persistentInstance) {
		log.debug("deleting InvSmeSaldoMensual instance");
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
	
	
	public InvSmeSaldoMensual findByCodArtAndDate(String codigo, Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		log.debug("getting InvSmeSaldoMensual instance with art_cod: " + codigo+" and Date :" + sdf.format(fecha));
		try {
			String queryString = "from InvSmeSaldoMensual as model where model.invArtArticulo.artCodigo = "+codigo
			+" and MONTH(model.smeFecha) = MONTH('"+sdf.format(fecha)+"')" 
			+" and YEAR(model.smeFecha) = YEAR('"+sdf.format(fecha)+"')" ;
		Query queryObject = getSession().createQuery(queryString);
		List<InvSmeSaldoMensual> l = queryObject.list();
		if (l.size() > 0){
			return l.get(0);	
		}else{
			return null;
		}
		
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public InvSmeSaldoMensual findById(java.lang.Integer id) {
		log.debug("getting InvSmeSaldoMensual instance with id: " + id);
		try {
			InvSmeSaldoMensual instance = (InvSmeSaldoMensual) getSession().get(
					"com.cetia.sicaco.hibernate.InvSmeSaldoMensual", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvSmeSaldoMensual instance) {
		log.debug("finding InvSmeSaldoMensual instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvSmeSaldoMensual").add(
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
		log.debug("finding InvSmeSaldoMensual instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvSmeSaldoMensual as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List findAll() {
		log.debug("finding all InvSmeSaldoMensual instances");
		try {
			String queryString = "from InvSmeSaldoMensual";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	public InvSmeSaldoMensual merge(InvSmeSaldoMensual detachedInstance) {
		log.debug("merging InvSmeSaldoMensual instance");
		try {
			InvSmeSaldoMensual result = (InvSmeSaldoMensual) getSession().merge(
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

	public void attachDirty(InvSmeSaldoMensual instance) {
		log.debug("attaching dirty InvSmeSaldoMensual instance");
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

	public void attachClean(InvSmeSaldoMensual instance) {
		log.debug("attaching clean InvSmeSaldoMensual instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByArtCodigo(String artCodigo) {
		log.debug("finding all InvSmeSaldoMensual instances");
		try {
			String queryString = "from InvSmeSaldoMensual sme where sme.invArtArticulo.artCodigo ='" + artCodigo + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer latest() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(sme.smeId) as lates from InvSmeSaldoMensual sme";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(-1);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	}
	
}