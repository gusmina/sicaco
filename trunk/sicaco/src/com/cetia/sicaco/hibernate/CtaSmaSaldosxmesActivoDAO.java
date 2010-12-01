package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaSmaSaldosxmesActivo entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaSmaSaldosxmesActivo
 * @author MyEclipse Persistence Tools
 */

public class CtaSmaSaldosxmesActivoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaSmaSaldosxmesActivoDAO.class);
	// property constants
	public static final String SMA_INTER_ACUM = "smaInterAcum";
	public static final String SMA_SALDO_ACUM = "smaSaldoAcum";

	public CtaSmaSaldosxmesActivoDAO(Session session) {
		super(session);
	}

	public void save(CtaSmaSaldosxmesActivo transientInstance) {
		log.debug("saving CtaSmaSaldosxmesActivo instance");
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

	public void delete(CtaSmaSaldosxmesActivo persistentInstance) {
		log.debug("deleting CtaSmaSaldosxmesActivo instance");
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

	public CtaSmaSaldosxmesActivo findById(java.lang.Integer id) {
		log.debug("getting CtaSmaSaldosxmesActivo instance with id: " + id);
		try {
			CtaSmaSaldosxmesActivo instance = (CtaSmaSaldosxmesActivo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaSmaSaldosxmesActivo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaSmaSaldosxmesActivo instance) {
		log.debug("finding CtaSmaSaldosxmesActivo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaSmaSaldosxmesActivo").add(
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
		log.debug("finding CtaSmaSaldosxmesActivo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaSmaSaldosxmesActivo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySmaInterAcum(Object smaInterAcum) {
		return findByProperty(SMA_INTER_ACUM, smaInterAcum);
	}

	public List findBySmaSaldoAcum(Object smaSaldoAcum) {
		return findByProperty(SMA_SALDO_ACUM, smaSaldoAcum);
	}

	public List findAll() {
		log.debug("finding all CtaSmaSaldosxmesActivo instances");
		try {
			String queryString = "from CtaSmaSaldosxmesActivo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaSmaSaldosxmesActivo merge(CtaSmaSaldosxmesActivo detachedInstance) {
		log.debug("merging CtaSmaSaldosxmesActivo instance");
		try {
			CtaSmaSaldosxmesActivo result = (CtaSmaSaldosxmesActivo) getSession()
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

	public void attachDirty(CtaSmaSaldosxmesActivo instance) {
		log.debug("attaching dirty CtaSmaSaldosxmesActivo instance");
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

	public void attachClean(CtaSmaSaldosxmesActivo instance) {
		log.debug("attaching clean CtaSmaSaldosxmesActivo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void asignarSaldoMesAsocActivo() {
		Transaction tx = this.getSession().beginTransaction();
		try {
			String queryString = "select cta.ctaCahCuentaAhorro.cahId,cta.ctaCahCuentaAhorro.cahInteresAcumulado,cta.ctaCahCuentaAhorro.cahSaldoActual"+
			" from CtaCasCuentaAsociado cta where cta.ctaCahCuentaAhorro is not null and cta.ctaAscAsociado.ctrEstEstado = 0 and cta.ctaCahCuentaAhorro.cahSaldoActual != 0";
			Query queryObject = getSession().createQuery(queryString);
			List<?> cuentas =  queryObject.list();
			Iterator it = cuentas.iterator();
			while(it.hasNext()){
				Object[] obj = (Object[]) it.next();
				CtaSmaSaldosxmesActivo saldo = new CtaSmaSaldosxmesActivo();
				saldo.getCtaCahCuentaAhorro().setCahId(obj[0].toString());
				saldo.setSmaInterAcum(Double.parseDouble(obj[1].toString()));
				saldo.setSmaSaldoAcum(Double.parseDouble(obj[2].toString()));
				saldo.setSmaFecha(new Date());
				this.save(saldo);
			}
			tx.commit();
		} catch (RuntimeException re) {
			log.error("asignacion asignar asignar", re);
			tx.rollback();
			re.printStackTrace();
		}finally{
			this.getSession().flush();
			this.getSession().clear();
			//this.getSession().close();
		}
	}
	
	public boolean haySaldos() {
		boolean resp = true ;
		try {
			String queryString = "from CtaSmaSaldosxmesActivo sma where year(sma.smaFecha) = year(curdate()) and month(sma.smaFecha) = month(curdate())";
			Query queryObject = getSession().createQuery(queryString);
			if(queryObject.list().isEmpty()) resp = false;
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return resp;
	}
	
}