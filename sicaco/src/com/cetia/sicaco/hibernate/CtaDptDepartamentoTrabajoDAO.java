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
 * CtaDptDepartamentoTrabajo entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajo
 * @author MyEclipse Persistence Tools
 */

public class CtaDptDepartamentoTrabajoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaDptDepartamentoTrabajoDAO.class);
	// property constants
	public static final String DPT_NOMBRE = "dptNombre";
	public static final String DPT_UBICACION = "dptUbicacion";
	public static final String DPT_DESCRIPCION = "dptDescripcion";
	public static final String DPT_ESTADO = "dptEstado";
	public static final String DPT_CENTRO_COSTO = "dptCentroCosto";

	public CtaDptDepartamentoTrabajoDAO(Session session) {
		super(session);
	}

	public void save(CtaDptDepartamentoTrabajo transientInstance) {
		log.debug("saving CtaDptDepartamentoTrabajo instance");
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

	public void delete(CtaDptDepartamentoTrabajo persistentInstance) {
		log.debug("deleting CtaDptDepartamentoTrabajo instance");
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

	public CtaDptDepartamentoTrabajo findById(java.lang.Integer id) {
		log.debug("getting CtaDptDepartamentoTrabajo instance with id: " + id);
		try {
			CtaDptDepartamentoTrabajo instance = (CtaDptDepartamentoTrabajo) getSession()
					.get(
							"com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaDptDepartamentoTrabajo instance) {
		log.debug("finding CtaDptDepartamentoTrabajo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaDptDepartamentoTrabajo")
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
		log.debug("finding CtaDptDepartamentoTrabajo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaDptDepartamentoTrabajo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertyAndEstado(String propertyName, Object value, Object estado) {
		try {
			String queryString = "from CtaDptDepartamentoTrabajo as model where model."
					+ propertyName + "= ? and model.dptEstado= ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, estado);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDptNombre(Object dptNombre) {
		return findByProperty(DPT_NOMBRE, dptNombre);
	}

	public List findByDptUbicacion(Object dptUbicacion) {
		return findByProperty(DPT_UBICACION, dptUbicacion);
	}

	public List findByDptDescripcion(Object dptDescripcion) {
		return findByProperty(DPT_DESCRIPCION, dptDescripcion);
	}

	public List findByDptEstado(Object dptEstado) {
		return findByProperty(DPT_ESTADO, dptEstado);
	}
	
	public List findByDptCentroCosto(Object dptCentroCosto) {
		return findByProperty(DPT_CENTRO_COSTO, dptCentroCosto);
	}

	public List findAll() {
		log.debug("finding all CtaDptDepartamentoTrabajo instances");
		try {
			String queryString = "from CtaDptDepartamentoTrabajo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaDptDepartamentoTrabajo merge(
			CtaDptDepartamentoTrabajo detachedInstance) {
		log.debug("merging CtaDptDepartamentoTrabajo instance");
		try {
			CtaDptDepartamentoTrabajo result = (CtaDptDepartamentoTrabajo) getSession()
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

	public void attachDirty(CtaDptDepartamentoTrabajo instance) {
		log.debug("attaching dirty CtaDptDepartamentoTrabajo instance");
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

	public void attachClean(CtaDptDepartamentoTrabajo instance) {
		log.debug("attaching clean CtaDptDepartamentoTrabajo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findAllByEtrId(int etrId) {
		log.debug("finding all CtaDptDepartamentoTrabajo instances");
		try {
			String queryString = "from CtaDptDepartamentoTrabajo dpt where dpt.ctaEtrEmpresaTrabajo.etrId=" + etrId;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<CtaDptDepartamentoTrabajo> findByCentroCostoAndEtr(String centroCosto, Integer etr, int i) {
		log.debug("finding all CtaDptDepartamentoTrabajo instances");
		try {
			String queryString = "from CtaDptDepartamentoTrabajo dpt " +
						"where dpt.ctaEtrEmpresaTrabajo.etrId=" + etr + " ";
			if(centroCosto != null && !centroCosto.trim().equals("")){
				queryString += "and dpt.dptCentroCosto like '" + centroCosto + "'";
			}
			Query queryObject = getSession().createQuery(queryString).setMaxResults(i);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Integer getTotalRowCount() {
		log.debug("counting all rows of CtaDptoDepartamentoTrabajo");
		try {
			String queryString = "select count(h.dptId)from CtaDptDepartamentoTrabajo h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SecHseHistorialSesion> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaDptDepartamentoTrabajo hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaDptDepartamentoTrabajo hse order by hse.dptId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAllByEtrId(int etrId, int rowStart,int rowEnd ) {
		log.debug("finding all CtaDptDepartamentoTrabajo instances");
		try {
			String queryString = "from CtaDptDepartamentoTrabajo dpt where dpt.ctaEtrEmpresaTrabajo.etrId=" + etrId;
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Integer getTotalRowCountByEtrId(int etrId) {
		log.debug("counting all rows of CtaDptoDepartamentoTrabajo");
		try {
			String queryString = "select count(dpt.dptId)from CtaDptDepartamentoTrabajo dpt where dpt.ctaEtrEmpresaTrabajo.etrId=" + etrId;
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}