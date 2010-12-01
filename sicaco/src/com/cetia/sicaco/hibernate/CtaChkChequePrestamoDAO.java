package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.Date;
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
 * CtaChkChequePrestamo entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaChkChequePrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaChkChequePrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaChkChequePrestamoDAO.class);
	// property constants
	public static final String CHK_EMITIDO_A = "chkEmitidoA";
	public static final String CHK_MONTO_EMITIDO = "chkMontoEmitido";
	public static final String CHK_RAZON = "chkRazon";
	public static final String CHK_CORRELATIVO_CHEQUE = "chkCorrelativoCheque";
	public static final String CHK_LUGAR = "chkLugar";

	public CtaChkChequePrestamoDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void save(CtaChkChequePrestamo transientInstance) {
		log.debug("saving CtaChkChequePrestamo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaChkChequePrestamo persistentInstance) {
		log.debug("deleting CtaChkChequePrestamo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaChkChequePrestamo findById(java.lang.Integer id) {
		log.debug("getting CtaChkChequePrestamo instance with id: " + id);
		try {
			CtaChkChequePrestamo instance = (CtaChkChequePrestamo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaChkChequePrestamo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaChkChequePrestamo instance) {
		log.debug("finding CtaChkChequePrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaChkChequePrestamo").add(
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
		log.debug("finding CtaChkChequePrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaChkChequePrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByChkEmitidoA(Object chkEmitidoA) {
		return findByProperty(CHK_EMITIDO_A, chkEmitidoA);
	}

	public List findByChkMontoEmitido(Object chkMontoEmitido) {
		return findByProperty(CHK_MONTO_EMITIDO, chkMontoEmitido);
	}

	public List findByChkRazon(Object chkRazon) {
		return findByProperty(CHK_RAZON, chkRazon);
	}

	public List findByChkCorrelativoCheque(Object chkCorrelativoCheque) {
		return findByProperty(CHK_CORRELATIVO_CHEQUE, chkCorrelativoCheque);
	}

	public List findByChkLugar(Object chkLugar) {
		return findByProperty(CHK_LUGAR, chkLugar);
	}

	public List findAll() {
		log.debug("finding all CtaChkChequePrestamo instances");
		try {
			String queryString = "from CtaChkChequePrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaChkChequePrestamo merge(CtaChkChequePrestamo detachedInstance) {
		log.debug("merging CtaChkChequePrestamo instance");
		try {
			CtaChkChequePrestamo result = (CtaChkChequePrestamo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaChkChequePrestamo instance) {
		log.debug("attaching dirty CtaChkChequePrestamo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaChkChequePrestamo instance) {
		log.debug("attaching clean CtaChkChequePrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Float sumMontosCheques(String prestamoId) {
		log.debug("finding all CtaChkChequePrestamo instances");
		float cod;
		try {
			String sql = "select sum(chk.chkMontoEmitido) as chkMontoEmitido " +
					"from CtaChkChequePrestamo chk where chk.ctaPrePrestamo.preId='" + prestamoId + "'";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Float) {
				Float value = (Float) obj;
				cod = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				cod = new Float(value.floatValue());
			}else {
				cod = new Float(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return cod;
	}

	public List findByPrestamo(String prestamoId) {
		log.debug("finding all CtaChkChequePrestamo instances");
		try {
			String queryString = "from CtaChkChequePrestamo chk " +
					"where chk.ctaPrePrestamo.preId = '"+ prestamoId + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}