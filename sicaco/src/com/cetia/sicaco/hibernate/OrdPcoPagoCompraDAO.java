package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.mad.utilidades.ElapsedTime;

/**
 * A data access object (DAO) providing persistence and search support for
 * OrdPcoPagoCompra entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.OrdPcoPagoCompra
 * @author MyEclipse Persistence Tools
 */

public class OrdPcoPagoCompraDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OrdPcoPagoCompraDAO.class);

	// property constants

	public OrdPcoPagoCompraDAO(Session session) {
		super(session);
	}

	public void save(OrdPcoPagoCompra transientInstance) {
		log.debug("saving OrdPcoPagoCompra instance");
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

	public void delete(OrdPcoPagoCompra persistentInstance) {
		log.debug("deleting OrdPcoPagoCompra instance");
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

	public OrdPcoPagoCompra findById(
			com.cetia.sicaco.hibernate.OrdPcoPagoCompraId id) {
		log.debug("getting OrdPcoPagoCompra instance with id: " + id);
		try {
			OrdPcoPagoCompra instance = (OrdPcoPagoCompra) getSession().get(
					"com.cetia.sicaco.hibernate.OrdPcoPagoCompra", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OrdPcoPagoCompra instance) {
		log.debug("finding OrdPcoPagoCompra instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.OrdPcoPagoCompra").add(
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
		log.debug("finding OrdPcoPagoCompra instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OrdPcoPagoCompra as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all OrdPcoPagoCompra instances");
		try {
			String queryString = "from OrdPcoPagoCompra";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OrdPcoPagoCompra merge(OrdPcoPagoCompra detachedInstance) {
		log.debug("merging OrdPcoPagoCompra instance");
		try {
			OrdPcoPagoCompra result = (OrdPcoPagoCompra) getSession().merge(
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

	public void attachDirty(OrdPcoPagoCompra instance) {
		log.debug("attaching dirty OrdPcoPagoCompra instance");
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

	public void attachClean(OrdPcoPagoCompra instance) {
		log.debug("attaching clean OrdPcoPagoCompra instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByPagoId(OrdOpaOrdenDePago pagoId) {
		log.debug("finding all OrdPcoPagoCompra instances by pagoId");
		try {
			String queryString = "from OrdPcoPagoCompra opc where opc.id.ordOpaOrdenDePago.opaId = " + pagoId.getOpaId();  
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Double totalPago(Integer pagoId) {
		log.debug("finding all OrdPcoPagoCompra instances by pagoId");
		Double total = 0.0;
		try {
			String queryString = "select sum(pco.id.ordOcoOrdenDeCompra.ocoPagado) " +
					" from OrdPcoPagoCompra pco " +
					" where pco.id.ordOpaOrdenDePago.opaId = " + pagoId;  
	        Query queryObject = getSession().createQuery(queryString);
	        Object obj = queryObject.uniqueResult();
			if (obj instanceof Double) {
				Double value = (Double) obj;
				total = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				total = new Double(value.doubleValue());
			}else {
				total = new Double(0.0);
			}
			return total;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Double totalPagoMenosOcoId(Integer opaId, Integer ocoId) {
		log.debug("finding all OrdPcoPagoCompra instances by pagoId");
		Double total = 0.0;
		try {
			String queryString = "select sum(pco.id.ordOcoOrdenDeCompra.ocoPagado) " +
					" from OrdPcoPagoCompra pco " +
					" where pco.id.ordOpaOrdenDePago.opaId = " + opaId + " " +
					" and pco.id.ordOcoOrdenDeCompra.ocoId <> " + ocoId;  
	        Query queryObject = getSession().createQuery(queryString);
	        Object obj = queryObject.uniqueResult();
			if (obj instanceof Double) {
				Double value = (Double) obj;
				total = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				total = new Double(value.doubleValue());
			}else {
				total = new Double(0.0);
			}
			return total;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void eliminaOrdenesPorEstado(String estado) {
		log.debug("finding all OrdPcoPagoCompra instances by pagoId");
		//ArrayList<OrdPcoPagoCompra> pcos = new ArrayList<OrdPcoPagoCompra>();
		try {
			String queryString = "from OrdPcoPagoCompra opc where opc.id.ordOpaOrdenDePago.opaEstado = '" + estado + "'";  
	         Query queryObject = getSession().createQuery(queryString);
			 List pcos = queryObject.list();
			 for (Iterator iterator = pcos.iterator(); iterator.hasNext();) {
				OrdPcoPagoCompra pco = (OrdPcoPagoCompra) iterator.next();
				if(ElapsedTime.fechaMenor(pco.getId().getOrdOpaOrdenDePago().getAudFechaCreacion(), ElapsedTime.obtenerFecha(new Date(), -1))){
					//System.out.println(pco.getId().getOrdOpaOrdenDePago().getOpaId());
					
					OrdOpaOrdenDePagoDAO pagoDAO = new OrdOpaOrdenDePagoDAO(getSession());
					OrdOpaOrdenDePago pago = pagoDAO.findById(pco.getId().getOrdOpaOrdenDePago().getOpaId());
					
					delete(pco);
					Transaction tx = getSession().beginTransaction();
					tx.commit();
					 
					pagoDAO.delete(pago);
					tx.commit();
					getSession().flush();
					getSession().clear();
					
				}
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}