package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaBxcBeneficiariosCuenta entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaBxcBeneficiariosCuenta
 * @author MyEclipse Persistence Tools
 */

public class CtaBxcBeneficiariosCuentaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaBxcBeneficiariosCuentaDAO.class);
	// property constants
	public static final String BXC_PORCENTAJE = "bxcPorcentaje";

	public CtaBxcBeneficiariosCuentaDAO(Session session) {
		super(session);
	}

	public void save(CtaBxcBeneficiariosCuenta transientInstance) {
		log.debug("saving CtaBxcBeneficiariosCuenta instance");
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

	public void delete(CtaBxcBeneficiariosCuenta persistentInstance) {
		log.debug("deleting CtaBxcBeneficiariosCuenta instance");
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

	public CtaBxcBeneficiariosCuenta findById(java.lang.Integer id) {
		log.debug("getting CtaBxcBeneficiariosCuenta instance with id: " + id);
		try {
			CtaBxcBeneficiariosCuenta instance = (CtaBxcBeneficiariosCuenta) getSession()
					.get(
							"com.cetia.sicaco.hibernate.CtaBxcBeneficiariosCuenta",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaBxcBeneficiariosCuenta instance) {
		log.debug("finding CtaBxcBeneficiariosCuenta instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaBxcBeneficiariosCuenta")
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
		log.debug("finding CtaBxcBeneficiariosCuenta instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaBxcBeneficiariosCuenta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBxcPorcentaje(Object bxcPorcentaje) {
		return findByProperty(BXC_PORCENTAJE, bxcPorcentaje);
	}

	public List findAll() {
		log.debug("finding all CtaBxcBeneficiariosCuenta instances");
		try {
			String queryString = "from CtaBxcBeneficiariosCuenta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaBxcBeneficiariosCuenta merge(
			CtaBxcBeneficiariosCuenta detachedInstance) {
		log.debug("merging CtaBxcBeneficiariosCuenta instance");
		try {
			CtaBxcBeneficiariosCuenta result = (CtaBxcBeneficiariosCuenta) getSession()
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

	public void attachDirty(CtaBxcBeneficiariosCuenta instance) {
		log.debug("attaching dirty CtaBxcBeneficiariosCuenta instance");
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

	public void attachClean(CtaBxcBeneficiariosCuenta instance) {
		log.debug("attaching clean CtaBxcBeneficiariosCuenta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public float porcentajeAcum(long casCu) {
		float val;
		try{
			String sql = "select sum(bxc.bxcPorcentaje) from CtaBxcBeneficiariosCuenta bxc "+
					"where bxc.ctaCasCuentaAsociado.casCuenta = ? ";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, casCu);
			Object obj =queryObject.uniqueResult();
			
			if (obj instanceof Float) {
				Float value = (Float) obj;
				val = value;
			}else {
				val = new Float(0);
			}
			getSession().flush();
			getSession().clear();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		return val;
	}
	
	public List findBySeguro(String segId) {
		log.debug("finding all CtaBxcBeneficiariosCuenta instances");
		try {
			CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSession());
			CtaCasCuentaAsociado cuentaAsociado = cuentaAsociadoDAO.findbySegId(segId);
			if(cuentaAsociado == null){
				return new ArrayList();
			}
			String queryString = "from CtaBxcBeneficiariosCuenta bxc where bxc.ctaCasCuentaAsociado.casCuenta="+cuentaAsociado.getCasCuenta();
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String findPorcentajeByCuentaYBeneficiario(int benId, long cuenta) {
		log.debug("finding porcentaje CtaBxcBeneficiariosCuenta instances");
		try {
			String queryString = "select bxc.bxcPorcentaje from CtaBxcBeneficiariosCuenta bxc where bxc.ctaCasCuentaAsociado.casCuenta="+cuenta+
								"  and bxc.ctaBenBeneficiarios.benId="+benId;
			Query queryObject = getSession().createQuery(queryString);
			Object o = new Object();
			o = queryObject.uniqueResult();
			if(o!=null){
				return o.toString();
			}else{
				return "";
			}
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer findByCuentaYBeneficiario(CtaBxcBeneficiariosCuenta beneficiariosCuenta) {
		log.debug("finding porcentaje CtaBxcBeneficiariosCuenta instances");
		try {
			String queryString = "select bxc.bxcId from CtaBxcBeneficiariosCuenta bxc where bxc.ctaCasCuentaAsociado.casCuenta="+beneficiariosCuenta.getCtaCasCuentaAsociado().getCasCuenta()+
								"  and bxc.ctaBenBeneficiarios.benId="+beneficiariosCuenta.getCtaBenBeneficiarios().getBenId();
			Query queryObject = getSession().createQuery(queryString);
			Object o = new Object();
			o = queryObject.uniqueResult();
			if(o!=null){
				return (Integer) o;
			}else{
				return -1;
			}
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaBenBeneficiarios> findByCuenta(long casCuenta) {
		log.debug("finding porcentaje CtaBxcBeneficiariosCuenta instances");
		try {
			String queryString = "select bxc.ctaBenBeneficiarios " +
								"from CtaBxcBeneficiariosCuenta bxc " +
								"where bxc.ctaCasCuentaAsociado.casCuenta= ? ";		
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, casCuenta);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaBxcBeneficiariosCuenta> findByCuenta2(long casCuenta) {
		log.debug("finding porcentaje CtaBxcBeneficiariosCuenta instances");
		try {
			String queryString = "select bxc " +
								"from CtaBxcBeneficiariosCuenta bxc " +
								"where bxc.ctaCasCuentaAsociado.casCuenta= ? ";		
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, casCuenta);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}