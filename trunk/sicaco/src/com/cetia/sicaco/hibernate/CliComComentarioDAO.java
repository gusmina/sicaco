package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * CliComComentario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CliComComentario
 * @author MyEclipse Persistence Tools
 */

public class CliComComentarioDAO extends BaseHibernateDAO {
	
	public CliComComentarioDAO(Session session) {
		super(session);
	}

	private static final Log log = LogFactory.getLog(CliComComentarioDAO.class);
	// property constants
	public static final String COM_COMENTARIO = "comComentario";

	public void save(CliComComentario transientInstance) {
		log.debug("saving CliComComentario instance");
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

	public void delete(CliComComentario persistentInstance) {
		log.debug("deleting CliComComentario instance");
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

	public CliComComentario findById(java.lang.Integer id) {
		log.debug("getting CliComComentario instance with id: " + id);
		try {
			CliComComentario instance = (CliComComentario) getSession().get(
					"com.cetia.sicaco.hibernate.CliComComentario", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CliComComentario instance) {
		log.debug("finding CliComComentario instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CliComComentario").add(
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
		log.debug("finding CliComComentario instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CliComComentario as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByComComentario(Object comComentario) {
		return findByProperty(COM_COMENTARIO, comComentario);
	}

	public List findAll() {
		log.debug("finding all CliComComentario instances");
		try {
			String queryString = "from CliComComentario";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CliComComentario merge(CliComComentario detachedInstance) {
		log.debug("merging CliComComentario instance");
		try {
			CliComComentario result = (CliComComentario) getSession().merge(
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

	public void attachDirty(CliComComentario instance) {
		log.debug("attaching dirty CliComComentario instance");
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

	public void attachClean(CliComComentario instance) {
		log.debug("attaching clean CliComComentario instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CliComComentario> buscarPorFechas(Date fechaInicio, Date fechaFin) {
		log.debug("Busqueda por fechas : "+ fechaInicio + " , " + fechaFin);
		
		try {
			List<CliComComentario> comentarios = null;
			Criteria criteria = getSession().createCriteria(CliComComentario.class);
			if(fechaInicio!=null)
				criteria.add(Restrictions.ge("comFechaIngreso", fechaInicio));
			if(fechaFin!=null)
				criteria.add(Restrictions.le("comFechaIngreso", fechaFin));
			comentarios=criteria.list();
			return comentarios;	
		}catch(RuntimeException re) {
			log.error("Fallo la busqueda por fechas", re);
			throw re;
		}
	}
}