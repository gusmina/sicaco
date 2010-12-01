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
 * InvCnvConversion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvCnvConversion
 * @author MyEclipse Persistence Tools
 */

public class InvCnvConversionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvCnvConversionDAO.class);
	// property constants
	public static final String CNV_NUEVA_MEDIDA = "cnvNuevaMedida";
	public static final String CNV_FACTOR = "cnvFactor";
	public static final String CNV_NOMBRE_MEDIDA = "cnvNombreMedida";
	public static final String CNV_COMENTARIO = "cnvComentario";

	public InvCnvConversionDAO(Session session) {
		super(session);
	}

	public void save(InvCnvConversion transientInstance) {
		log.debug("saving InvCnvConversion instance");
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

	public void delete(InvCnvConversion persistentInstance) {
		log.debug("deleting InvCnvConversion instance");
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

	public InvCnvConversion findById(java.lang.Integer id) {
		log.debug("getting InvCnvConversion instance with id: " + id);
		try {
			InvCnvConversion instance = (InvCnvConversion) getSession().get(
					"com.cetia.sicaco.hibernate.InvCnvConversion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvCnvConversion instance) {
		log.debug("finding InvCnvConversion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvCnvConversion").add(
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
		log.debug("finding InvCnvConversion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvCnvConversion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCnvNuevaMedida(Object cnvNuevaMedida) {
		return findByProperty(CNV_NUEVA_MEDIDA, cnvNuevaMedida);
	}

	public List findByCnvFactor(Object cnvFactor) {
		return findByProperty(CNV_FACTOR, cnvFactor);
	}

	public List findByCnvNombreMedida(Object cnvNombreMedida) {
		return findByProperty(CNV_NOMBRE_MEDIDA, cnvNombreMedida);
	}

	public List findByCnvComentario(Object cnvComentario) {
		return findByProperty(CNV_COMENTARIO, cnvComentario);
	}

	public List findAll() {
		log.debug("finding all InvCnvConversion instances");
		try {
			String queryString = "from InvCnvConversion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvCnvConversion merge(InvCnvConversion detachedInstance) {
		log.debug("merging InvCnvConversion instance");
		try {
			InvCnvConversion result = (InvCnvConversion) getSession().merge(
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

	public void attachDirty(InvCnvConversion instance) {
		log.debug("attaching dirty InvCnvConversion instance");
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

	public void attachClean(InvCnvConversion instance) {
		log.debug("attaching clean InvCnvConversion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByMedId(String medId) {
		log.debug("finding all InvCnvConversion instances by medId");
		try {
			String queryString = "from InvCnvConversion cnv where cnv.invMedMedida.medId = '" + medId + "' " +
					"AND cnv.cnvId <> -2";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}