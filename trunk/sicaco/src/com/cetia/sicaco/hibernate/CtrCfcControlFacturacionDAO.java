package com.cetia.sicaco.hibernate;

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
 * CtrCfcControlFacturacion entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrCfcControlFacturacion
 * @author MyEclipse Persistence Tools
 */

public class CtrCfcControlFacturacionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtrCfcControlFacturacionDAO.class);
	// property constants
	public static final String CFC_CORR_INI = "cfcCorrIni";
	public static final String CFC_CORR_FIN = "cfcCorrFin";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public CtrCfcControlFacturacionDAO(Session session) {
		super(session);
	}

	public void save(CtrCfcControlFacturacion transientInstance) {
		log.debug("saving CtrCfcControlFacturacion instance");
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

	public void delete(CtrCfcControlFacturacion persistentInstance) {
		log.debug("deleting CtrCfcControlFacturacion instance");
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

	public CtrCfcControlFacturacion findById(java.lang.String id) {
		log.debug("getting CtrCfcControlFacturacion instance with id: " + id);
		try {
			CtrCfcControlFacturacion instance = (CtrCfcControlFacturacion) getSession()
					.get("com.cetia.sicaco.hibernate.CtrCfcControlFacturacion",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrCfcControlFacturacion instance) {
		log.debug("finding CtrCfcControlFacturacion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrCfcControlFacturacion").add(
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
		log.debug("finding CtrCfcControlFacturacion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrCfcControlFacturacion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCfcCorrIni(Object cfcCorrIni) {
		return findByProperty(CFC_CORR_INI, cfcCorrIni);
	}

	public List findByCfcCorrFin(Object cfcCorrFin) {
		return findByProperty(CFC_CORR_FIN, cfcCorrFin);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all CtrCfcControlFacturacion instances");
		try {
			String queryString = "from CtrCfcControlFacturacion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrCfcControlFacturacion merge(
			CtrCfcControlFacturacion detachedInstance) {
		log.debug("merging CtrCfcControlFacturacion instance");
		try {
			CtrCfcControlFacturacion result = (CtrCfcControlFacturacion) getSession()
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

	public void attachDirty(CtrCfcControlFacturacion instance) {
		log.debug("attaching dirty CtrCfcControlFacturacion instance");
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

	public void attachClean(CtrCfcControlFacturacion instance) {
		log.debug("attaching clean CtrCfcControlFacturacion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CtrCfcControlFacturacion> findByCriteria(CtrCfcControlFacturacion controlFacturacion){
		log.debug("finding InvProProveedor instance by criteria");
		DetachedCriteria criteria = DetachedCriteria.forClass( CtrCfcControlFacturacion.class);
		if (controlFacturacion.getCfcSerie()!= null && !controlFacturacion.getCfcSerie().trim().equals("")) {
 			criteria.add(Restrictions.like("cfcSerie","%" +controlFacturacion.getCfcSerie() + "%"));
		}
		return (List<CtrCfcControlFacturacion>)criteria.getExecutableCriteria(getSession()).list();
	}
	
	public List findSame(String serie) {
		log.debug("finding all CtrCfcControlFacturacion instances");
		try {
			String queryString = "from CtrCfcControlFacturacion cfc where cfc.cfcSerie = '" + serie + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}