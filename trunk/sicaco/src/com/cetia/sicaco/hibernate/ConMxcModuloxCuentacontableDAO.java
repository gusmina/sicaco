package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;

import com.cetia.sicaco.contabilidad.struts.form.RelacionModuloContaForm;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConMxcModuloxCuentacontable entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontable
 * @author MyEclipse Persistence Tools
 */

public class ConMxcModuloxCuentacontableDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConMxcModuloxCuentacontableDAO.class);
	// property constants
	public static final String CXC_PARAMETROS_UNION = "cxcParametrosUnion";
	public static final String CXC_CARGO_ABONO = "cxcCargoAbono";
	public static final String CXA_CONCEPTO_EXTRA = "cxaConceptoExtra";

	public ConMxcModuloxCuentacontableDAO(Session session) {
		super(session);
	}

	public void save(ConMxcModuloxCuentacontable transientInstance) {
		log.debug("saving ConMxcModuloxCuentacontable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConMxcModuloxCuentacontable persistentInstance) {
		log.debug("deleting ConMxcModuloxCuentacontable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConMxcModuloxCuentacontable findById(Long id) {
		log
				.debug("getting ConMxcModuloxCuentacontable instance with id: "
						+ id);
		try {
			ConMxcModuloxCuentacontable instance = (ConMxcModuloxCuentacontable) getSession()
					.get(
							"com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontable",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConMxcModuloxCuentacontable instance) {
		log.debug("finding ConMxcModuloxCuentacontable instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConMxcModuloxCuentacontable")
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
				.debug("finding ConMxcModuloxCuentacontable instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConMxcModuloxCuentacontable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCxcParametrosUnion(Object cxcParametrosUnion) {
		return findByProperty(CXC_PARAMETROS_UNION, cxcParametrosUnion);
	}

	public List findByCxcCargoAbono(Object cxcCargoAbono) {
		return findByProperty(CXC_CARGO_ABONO, cxcCargoAbono);
	}

	public List findByCxaConceptoExtra(Object cxaConceptoExtra) {
		return findByProperty(CXA_CONCEPTO_EXTRA, cxaConceptoExtra);
	}

	public List findAll() {
		log.debug("finding all ConMxcModuloxCuentacontable instances");
		try {
			String queryString = "from ConMxcModuloxCuentacontable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConMxcModuloxCuentacontable merge(
			ConMxcModuloxCuentacontable detachedInstance) {
		log.debug("merging ConMxcModuloxCuentacontable instance");
		try {
			ConMxcModuloxCuentacontable result = (ConMxcModuloxCuentacontable) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConMxcModuloxCuentacontable instance) {
		log.debug("attaching dirty ConMxcModuloxCuentacontable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConMxcModuloxCuentacontable instance) {
		log.debug("attaching clean ConMxcModuloxCuentacontable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByModulo(int idModulo) {//recibe el modulo del cual se desean conocer sus relaciones con la conta.
		try {
			String queryString = "from ConMxcModuloxCuentacontable mxc where substring(mxc.cxcParametrosUnion,1,2) = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idModulo);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	
	public List findByCuentaParametros(int id,String parametros) {
		try {
			String queryString = "	from ConMxcModuloxCuentacontable mxc where mxc.conCueCuenta.cueId = ? and mxc.cxcParametrosUnion= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, id);
			queryObject.setParameter(1, parametros);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find  failed", re);
			throw re;
		}
	}
	

}