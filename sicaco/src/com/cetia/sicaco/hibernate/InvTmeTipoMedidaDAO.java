package com.cetia.sicaco.hibernate;

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
 * InvTmeTipoMedida entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvTmeTipoMedida
 * @author MyEclipse Persistence Tools
 */

public class InvTmeTipoMedidaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvTmeTipoMedidaDAO.class);
	// property constants
	public static final String TME_NOMBRE = "tmeNombre";
	public static final String TME_DESCRIPCION = "tmeDescripcion";

	public InvTmeTipoMedidaDAO(Session session) {
		super(session);
	}

	public void save(InvTmeTipoMedida transientInstance) {
		log.debug("saving InvTmeTipoMedida instance");
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

	public void delete(InvTmeTipoMedida persistentInstance) {
		log.debug("deleting InvTmeTipoMedida instance");
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

	public InvTmeTipoMedida findById(java.lang.Integer id) {
		log.debug("getting InvTmeTipoMedida instance with id: " + id);
		try {
			InvTmeTipoMedida instance = (InvTmeTipoMedida) getSession().get(
					"com.cetia.sicaco.hibernate.InvTmeTipoMedida", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvTmeTipoMedida instance) {
		log.debug("finding InvTmeTipoMedida instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvTmeTipoMedida").add(
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
		log.debug("finding InvTmeTipoMedida instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvTmeTipoMedida as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTmeNombre(Object tmeNombre) {
		return findByProperty(TME_NOMBRE, tmeNombre);
	}

	public List findByTmeDescripcion(Object tmeDescripcion) {
		return findByProperty(TME_DESCRIPCION, tmeDescripcion);
	}

	public List findAll() {
		log.debug("finding all InvTmeTipoMedida instances");
		try {
			String queryString = "from InvTmeTipoMedida";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvTmeTipoMedida merge(InvTmeTipoMedida detachedInstance) {
		log.debug("merging InvTmeTipoMedida instance");
		try {
			InvTmeTipoMedida result = (InvTmeTipoMedida) getSession().merge(
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

	public void attachDirty(InvTmeTipoMedida instance) {
		log.debug("attaching dirty InvTmeTipoMedida instance");
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

	public void attachClean(InvTmeTipoMedida instance) {
		log.debug("attaching clean InvTmeTipoMedida instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InvTmeTipoMedida> findByCriteria(InvTmeTipoMedida instance) {
		// TODO Auto-generated method stub
		List<InvTmeTipoMedida> lst = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(InvTmeTipoMedida.class);
		
			if (instance.getTmeNombre()!=null && !instance.getTmeNombre().trim().equals("")) {
				criteria.add(Restrictions.eq("tmeNombre", instance.getTmeNombre()));
			}
			lst =  (List<InvTmeTipoMedida>)criteria.getExecutableCriteria(getSession()).list();
		return lst;
	}
}