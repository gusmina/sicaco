package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * OrdPecPeticionCompra entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.OrdPecPeticionCompra
 * @author MyEclipse Persistence Tools
 */

public class OrdPecPeticionCompraDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(OrdPecPeticionCompraDAO.class);

	public OrdPecPeticionCompraDAO(Session session) {
		super(session);
	}

	public void save(OrdPecPeticionCompra transientInstance) {
		log.debug("saving OrdPecPeticionCompra instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OrdPecPeticionCompra persistentInstance) {
		log.debug("deleting OrdPecPeticionCompra instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrdPecPeticionCompra findById(java.lang.Integer id) {
		log.debug("getting OrdPecPeticionCompra instance with id: " + id);
		try {
			OrdPecPeticionCompra instance = (OrdPecPeticionCompra) getSession()
					.get("com.cetia.sicaco.hibernate.OrdPecPeticionCompra", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OrdPecPeticionCompra instance) {
		log.debug("finding OrdPecPeticionCompra instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.OrdPecPeticionCompra").add(
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
		log.debug("finding OrdPecPeticionCompra instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OrdPecPeticionCompra as model where model."
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
		log.debug("finding all OrdPecPeticionCompra instances");
		try {
			String queryString = "from OrdPecPeticionCompra";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OrdPecPeticionCompra merge(OrdPecPeticionCompra detachedInstance) {
		log.debug("merging OrdPecPeticionCompra instance");
		try {
			OrdPecPeticionCompra result = (OrdPecPeticionCompra) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OrdPecPeticionCompra instance) {
		log.debug("attaching dirty OrdPecPeticionCompra instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OrdPecPeticionCompra instance) {
		log.debug("attaching clean OrdPecPeticionCompra instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Double creditoUtilizado(String ascId) {
		log.debug("finding interes pendiente CtaPrePrestamo instances");
		try {
			String queryString ="select sum(pec.pecMonto) as credito " +
								"from OrdPecPeticionCompra as pec " +
								"where pec.ctaAscAsociado.ascId = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			Object obj = queryObject.uniqueResult();
			if (obj instanceof Double) {
				Double value = (Double) obj;
				return value;
			}else {
				return new Double(0.0);
			}
			//return (Double) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(pec.pecId) + 1  as id from OrdPecPeticionCompra pec";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
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
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	}	

	public List findPeticionesByCriteria(OrdPecPeticionCompra pec,int rowStart,int rowEnd) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(OrdPecPeticionCompra.class);
			if(pec.getCtaAscAsociado().getAscId() != null && pec.getCtaAscAsociado().getAscId().length() > 0) {
				criteria.add(Restrictions.like("ctaAscAsociado.ascId", pec.getCtaAscAsociado().getAscId()));
			}
			if(pec.getSecSucSucursal().getSucId()!= null && pec.getSecSucSucursal().getSucId() > -1) {
				criteria.add(Restrictions.eq("secSucSucursal.sucId",pec.getSecSucSucursal().getSucId()));
			}
			if(pec.getInvProProveedor().getProId() != null && pec.getInvProProveedor().getProId() > -1) {
				criteria.add(Restrictions.eq("invProProveedor.proId",pec.getInvProProveedor().getProId()));
			}
			if(pec.getPecFecha()!=null){
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.pec_fecha,'%d-%m-%Y') = ?",
						new SimpleDateFormat("dd-MM-yyyy").format(pec.getPecFecha())
						,Hibernate.STRING
				));
			}
			return criteria.getExecutableCriteria(getSession()).setFirstResult(rowStart).setMaxResults(rowEnd-rowStart).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Integer getTotalRowCountPeticionesByCriteria(OrdPecPeticionCompra pec) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(OrdPecPeticionCompra.class);
			if(pec.getCtaAscAsociado().getAscId() != null && pec.getCtaAscAsociado().getAscId().length() > 0) {
				criteria.add(Restrictions.like("ctaAscAsociado.ascId", pec.getCtaAscAsociado().getAscId()));
			}
			if(pec.getSecSucSucursal().getSucId()!= null && pec.getSecSucSucursal().getSucId() > -1) {
				criteria.add(Restrictions.eq("secSucSucursal.sucId",pec.getSecSucSucursal().getSucId()));
			}
			if(pec.getInvProProveedor().getProId() != null && pec.getInvProProveedor().getProId() > -1) {
				criteria.add(Restrictions.eq("invProProveedor.proId",pec.getInvProProveedor().getProId()));
			}
			if(pec.getPecFecha()!=null){
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.pec_fecha,'%d-%m-%Y') = ?",
						new SimpleDateFormat("dd-MM-yyyy").format(pec.getPecFecha())
						,Hibernate.STRING
				));
			}
			criteria.setProjection(Projections.countDistinct("pecId"));
			Integer var =(Integer) criteria.getExecutableCriteria(getSession()).uniqueResult();
			return var;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}