package com.cetia.sicaco.hibernate;

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
 * CtaEtrEmpresaTrabajo entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajo
 * @author MyEclipse Persistence Tools
 */

public class CtaEtrEmpresaTrabajoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaEtrEmpresaTrabajoDAO.class);
	// property constants
	public static final String ETR_NOMBRE = "etrNombre";
	public static final String ETR_DESCRIPCION = "etrDescripcion";
	public static final String ETR_ESTADO = "etrEstado";

	public CtaEtrEmpresaTrabajoDAO(Session session) {
		super(session);
	}

	public void save(CtaEtrEmpresaTrabajo transientInstance) {
		log.debug("saving CtaEtrEmpresaTrabajo instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaEtrEmpresaTrabajo persistentInstance) {
		log.debug("deleting CtaEtrEmpresaTrabajo instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaEtrEmpresaTrabajo findById(java.lang.Integer id) {
		log.debug("getting CtaEtrEmpresaTrabajo instance with id: " + id);
		try {
			CtaEtrEmpresaTrabajo instance = (CtaEtrEmpresaTrabajo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaEtrEmpresaTrabajo instance) {
		log.debug("finding CtaEtrEmpresaTrabajo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaEtrEmpresaTrabajo").add(
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
		log.debug("finding CtaEtrEmpresaTrabajo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaEtrEmpresaTrabajo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEtrNombre(Object etrNombre) {
		return findByProperty(ETR_NOMBRE, etrNombre);
	}

	public List findByEtrDescripcion(Object etrDescripcion) {
		return findByProperty(ETR_DESCRIPCION, etrDescripcion);
	}

	public List findByEtrEstado(Object etrEstado) {
		return findByProperty(ETR_ESTADO, etrEstado);
	}

	public List findAll() {
		log.debug("finding all CtaEtrEmpresaTrabajo instances");
		try {
			String queryString = "from CtaEtrEmpresaTrabajo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllActivasConDepartamentos() {
		log.debug("finding all CtaEtrEmpresaTrabajo instances");
		try {
			List list = new ArrayList<CtaEtrEmpresaTrabajo>();
			String queryString = "from CtaEtrEmpresaTrabajo as etr where (exists elements(etr.ctaDptDepartamentoTrabajos)) and etr.etrEstado = 'A' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
		
	public CtaEtrEmpresaTrabajo merge(CtaEtrEmpresaTrabajo detachedInstance) {
		log.debug("merging CtaEtrEmpresaTrabajo instance");
		try {
			CtaEtrEmpresaTrabajo result = (CtaEtrEmpresaTrabajo) getSession()
					.merge(detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaEtrEmpresaTrabajo instance) {
		log.debug("attaching dirty CtaEtrEmpresaTrabajo instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaEtrEmpresaTrabajo instance) {
		log.debug("attaching clean CtaEtrEmpresaTrabajo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public Integer getTotalRowCount() {
		log.debug("counting all rows of CtaEtrEmpresaTrabajo");
		try {
			String queryString = "select count(h.etrId) from CtaEtrEmpresaTrabajo h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaEtrEmpresaTrabajo> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaEtrEmpresaTrabajo hse instances order by hse.etrId desc");
		try {
			String queryString = "from CtaEtrEmpresaTrabajo hse order by hse.etrId desc";
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