package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaTxaTransaccionxcuentaAsociado entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado
 * @author MyEclipse Persistence Tools
 */

public class CtaTxaTransaccionxcuentaAsociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaTxaTransaccionxcuentaAsociadoDAO.class);
	// property constants
	public static final String TCM_ID = "tcmId";
	public static final String TXA_MONTO = "txaMonto";
	public static final String TXA_COMPROBANTE = "txaComprobante";
	public static final String TXA_ESTADO = "txaEstado";
	
	public String band;

	public CtaTxaTransaccionxcuentaAsociadoDAO(Session session) {
		super(session);
	}

	public void save(CtaTxaTransaccionxcuentaAsociado transientInstance) {
		log.debug("saving CtaTxaTransaccionxcuentaAsociado instance");
		try {
			Transaction tx = getSession().beginTransaction();
			if(transientInstance.getCtaNotNotas() == null ||
					transientInstance.getCtaNotNotas().getNotId() == null ||
					transientInstance.getCtaNotNotas().getNotId() < 0){
				transientInstance.setCtaNotNotas(null);
			}
			getSession().save(transientInstance);
			tx.commit();
			getSession().flush();
			getSession().clear();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void save2(CtaTxaTransaccionxcuentaAsociado transientInstance) {
		log.debug("saving CtaTxaTransaccionxcuentaAsociado instance");
		try {
			Transaction tx = getSession().beginTransaction();
			if(transientInstance.getCtaNotNotas() == null ||
					transientInstance.getCtaNotNotas().getNotId() == null ||
					transientInstance.getCtaNotNotas().getNotId() < 0){
				transientInstance.setCtaNotNotas(null);
			}
			getSession().save(transientInstance);
			tx.commit();
			getSession().flush();
			getSession().clear();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaTxaTransaccionxcuentaAsociado persistentInstance) {
		log.debug("deleting CtaTxaTransaccionxcuentaAsociado instance");
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

	public CtaTxaTransaccionxcuentaAsociado findById(java.lang.Long id) {
		log.debug("getting CtaTxaTransaccionxcuentaAsociado instance with id: "
				+ id);
		try {
			CtaTxaTransaccionxcuentaAsociado instance = (CtaTxaTransaccionxcuentaAsociado) getSession()
					.get(
							"com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTxaTransaccionxcuentaAsociado instance) {
		log
				.debug("finding CtaTxaTransaccionxcuentaAsociado instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.cetia.sicaco.hibernate.CtaTxaTransaccionxcuentaAsociado")
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
				.debug("finding CtaTxaTransaccionxcuentaAsociado instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTxaTransaccionxcuentaAsociado as model where model."
					+ propertyName + "= ?" + " ORDER BY model.txaFecha DESC";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTcmId(Object tcmId) {
		return findByProperty(TCM_ID, tcmId);
	}

	public List findByTxaMonto(Object txaMonto) {
		return findByProperty(TXA_MONTO, txaMonto);
	}

	public List findByTxaComprobante(Object txaComprobante) {
		return findByProperty(TXA_COMPROBANTE, txaComprobante);
	}
	
	public List findByTxaEstado(Object txaEstado) {
		return findByProperty(TXA_ESTADO, txaEstado);
	}

	public List findAll() {
		log.debug("finding all CtaTxaTransaccionxcuentaAsociado instances");
		try {
			String queryString = "from CtaTxaTransaccionxcuentaAsociado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTxaTransaccionxcuentaAsociado merge(
			CtaTxaTransaccionxcuentaAsociado detachedInstance) {
		log.debug("merging CtaTxaTransaccionxcuentaAsociado instance");
		
		try {
			if(detachedInstance.getCtaNotNotas() == null ||
					detachedInstance.getCtaNotNotas().getNotId() == null ||
					detachedInstance.getCtaNotNotas().getNotId() < 0){
				detachedInstance.setCtaNotNotas(null);
			}
			CtaTxaTransaccionxcuentaAsociado result = (CtaTxaTransaccionxcuentaAsociado) getSession()
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

	public void attachDirty(CtaTxaTransaccionxcuentaAsociado instance) {
		log.debug("attaching dirty CtaTxaTransaccionxcuentaAsociado instance");
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

	public void attachClean(CtaTxaTransaccionxcuentaAsociado instance) {
		log.debug("attaching clean CtaTxaTransaccionxcuentaAsociado instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findEntreFechasParaCuenta(Long casCuenta,Date fecha1,Date fecha2) {
		try {
			
			List<CtaTxaTransaccionxcuentaAsociado> secList = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(CtaTxaTransaccionxcuentaAsociado.class);
			criteria.add(Restrictions.like("ctaCasCuentaAsociado.casCuenta",casCuenta));
			
			if (fecha1 != null && fecha2 != null) {
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.txa_fecha,'%d-%m-%Y')  between ? and ?",
						new Object[]{new SimpleDateFormat("dd-MM-yyyy").format(fecha1)
							,new SimpleDateFormat("dd-MM-yyyy").format(fecha2)}
						,new Type[]{Hibernate.STRING,Hibernate.STRING}
				));
			}
			
			if (fecha1 != null && fecha2 == null) {
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.txa_fecha,'%d-%m-%Y') = ?",
						new SimpleDateFormat("dd-MM-yyyy").format(fecha1)
						,Hibernate.STRING
				));
			}
			
			if (fecha1 == null && fecha2 != null) {
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.txa_fecha,'%d-%m-%Y') = ?",
						new SimpleDateFormat("dd-MM-yyyy").format(fecha2)
						,Hibernate.STRING
				));
			}
			
			criteria.addOrder(Order.desc("txaId"));
			secList = criteria.getExecutableCriteria(getSession()).list();
			return secList;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public long lastId() {
		long id;
		log.debug("Consiguiendo el siguiente id - Transaccion del asociado");
		try {
			String sql = "select max(txa.txaId)+1 as id from CtaTxaTransaccionxcuentaAsociado txa";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				long value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida - Transaccion cuenta asociado", re);
			throw re;
		}
		return id;
	} 
	
	public Long nextComprobante() {
		Long id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			Transaction tx = getSession().beginTransaction();
			String sql = "select max(txa.txaComprobante) + 1  as comprobante from CtaTxaTransaccionxcuentaAsociado txa";
			Query query = getSession().createQuery(sql);
			Object obj = query.uniqueResult();
			if (obj instanceof Long) {
				Long value = (Long) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Long(value.longValue());
			}else {
				id = new Long(1);
			}
			tx.commit();
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	}
	public Integer getTotalRowCountTransaccionxAsociado(String ascid) {
		log.debug("counting all rows of CtaTxaTransaccionxcuentaAsociado");
		try {
			String queryString = "select count(h.txaId) from CtaTxaTransaccionxcuentaAsociado h " + 
                                 "where h.ctaCasCuentaAsociado.ctaAscAsociado.ascId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascid);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaTxaTransaccionxcuentaAsociado> findAllTransaccionXAsociado(int rowStart,int rowEnd, String ascid) {
		log.debug("finding all CtaTxaTransaccionxcuentaAsociado hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaTxaTransaccionxcuentaAsociado hse " +
                                 "where hse.ctaCasCuentaAsociado.ctaAscAsociado.ascId = ? " +
                                 "order by hse.txaId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			queryObject.setParameter(0, ascid);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}	
