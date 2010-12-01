package com.cetia.sicaco.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaMxpMovimientoPrestamo entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaMxpMovimientoPrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaMxpMovimientoPrestamoDAO.class);
	// property constants
	public static final String MXP_SALDO_ACTUAL = "mxpSaldoActual";
	public static final String MXP_INTERES_PENDIENTE = "mxpInteresPendiente";
	public static final String MXP_MORA = "mxpMora";

	public CtaMxpMovimientoPrestamoDAO(Session session) {
		super(session);
	}

	public void save(CtaMxpMovimientoPrestamo transientInstance) {
		log.debug("saving CtaMxpMovimientoPrestamo instance");
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

	public void delete(CtaMxpMovimientoPrestamo persistentInstance) {
		log.debug("deleting CtaMxpMovimientoPrestamo instance");
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

	public CtaMxpMovimientoPrestamo findById(java.lang.Integer id) {
		log.debug("getting CtaMxpMovimientoPrestamo instance with id: " + id);
		try {
			CtaMxpMovimientoPrestamo instance = (CtaMxpMovimientoPrestamo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaMxpMovimientoPrestamo instance) {
		log.debug("finding CtaMxpMovimientoPrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaMxpMovimientoPrestamo").add(
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
		log.debug("finding CtaMxpMovimientoPrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaMxpMovimientoPrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMxpSaldoActual(Object mxpSaldoActual) {
		return findByProperty(MXP_SALDO_ACTUAL, mxpSaldoActual);
	}

	public List findByMxpInteresPendiente(Object mxpInteresPendiente) {
		return findByProperty(MXP_INTERES_PENDIENTE, mxpInteresPendiente);
	}

	public List findByMxpMora(Object mxpMora) {
		return findByProperty(MXP_MORA, mxpMora);
	}

	public List findAll() {
		log.debug("finding all CtaMxpMovimientoPrestamo instances");
		try {
			String queryString = "from CtaMxpMovimientoPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaMxpMovimientoPrestamo merge(
			CtaMxpMovimientoPrestamo detachedInstance) {
		log.debug("merging CtaMxpMovimientoPrestamo instance");
		try {
			CtaMxpMovimientoPrestamo result = (CtaMxpMovimientoPrestamo) getSession()
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

	public void attachDirty(CtaMxpMovimientoPrestamo instance) {
		log.debug("attaching dirty CtaMxpMovimientoPrestamo instance");
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

	public void attachClean(CtaMxpMovimientoPrestamo instance) {
		log.debug("attaching clean CtaMxpMovimientoPrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findAllByPrestamo(String prestamoId) {
		log.debug("finding all CtaMxpMovimientoPrestamo instances");
		try {
			String queryString = "from CtaMxpMovimientoPrestamo mxp where mxp.ctaPrePrestamo.preId='" + prestamoId + "' order by mxp.mxpId desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List<CtaMxpMovimientoPrestamo> findByCriteria(CtaMxpMovimientoPrestamo ctaMxpMovimientoPrestamo){
		List<CtaMxpMovimientoPrestamo> secList = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaMxpMovimientoPrestamo.class);

		if(ctaMxpMovimientoPrestamo.getCtaPrePrestamo().getPreId() !=null && !ctaMxpMovimientoPrestamo.getCtaPrePrestamo().getPreId().equals("")){
			criteria.add(Restrictions.eq("ctaPrePrestamo.preId", ctaMxpMovimientoPrestamo.getCtaPrePrestamo().getPreId()));
		}
		
		if (ctaMxpMovimientoPrestamo.getMxpFechaMovIni() != null && ctaMxpMovimientoPrestamo.getMxpFechaMovFin() != null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.mxp_fecha,'%d-%m-%Y')  between ? and ?",
					new Object[]{new SimpleDateFormat("dd-MM-yyyy").format(ctaMxpMovimientoPrestamo.getMxpFechaMovIni())
						,new SimpleDateFormat("dd-MM-yyyy").format(ctaMxpMovimientoPrestamo.getMxpFechaMovFin())}
					,new Type[]{Hibernate.STRING,Hibernate.STRING}
			));
		}
		if (ctaMxpMovimientoPrestamo.getMxpFechaMovIni() != null && ctaMxpMovimientoPrestamo.getMxpFechaMovFin() == null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.mxp_fecha,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(ctaMxpMovimientoPrestamo.getMxpFechaMovIni())
					,Hibernate.STRING
			));
			
		}
		if(ctaMxpMovimientoPrestamo.getMxpFechaMovIni() == null && ctaMxpMovimientoPrestamo.getMxpFechaMovFin() != null){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.mxp_fecha,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(ctaMxpMovimientoPrestamo.getMxpFechaMovFin())
					,Hibernate.STRING
			));
		}
		criteria.addOrder(Order.desc("mxpId"));
		secList = criteria.getExecutableCriteria(getSession()).list();
		
		return secList;
	}
	
	public CtaMxpMovimientoPrestamo findUltimoMovimiento(String prestamoId) {
		log.debug("finding all CtaMxpMovimientoPrestamo instances");
		try {
			String queryString = "from CtaMxpMovimientoPrestamo mxp " +
					" where mxp.ctaPrePrestamo.preId='" + prestamoId + "' " +
							"order by mxp.mxpFecha desc";
			Query queryObject = getSession().createQuery(queryString);
			if(queryObject.list().size()<1){
				return null;
			}
			return (CtaMxpMovimientoPrestamo) queryObject.list().get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public CtaMxpMovimientoPrestamo findMovimientoFecha(String prestamoId, Date fecha) {
		log.debug("finding all CtaMxpMovimientoPrestamo instances");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String queryString = "from CtaMxpMovimientoPrestamo mxp " +
					" where mxp.ctaPrePrestamo.preId='" + prestamoId + "'  and mxp.mxpFecha < '" + sdf.format(fecha)+
							"' order by mxp.mxpFecha desc";
			Query queryObject = getSession().createQuery(queryString);
//			System.out.println("Busqueda del movimiento anterior a la fecha"+ sdf.format(fecha));
//			System.out.println(queryString);
			if(queryObject.list().size()<1){
				return null;
			}
			
			return (CtaMxpMovimientoPrestamo) queryObject.list().get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	public List findMovimientosPosteriores(String prestamoId, Date fecha) {
		log.debug("finding all CtaMxpMovimientoPrestamo instances");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String queryString = "from CtaMxpMovimientoPrestamo mxp " +
					" where mxp.ctaPrePrestamo.preId='" + prestamoId + "'  and mxp.mxpFecha > '" + sdf.format(fecha)+
							"' order by mxp.mxpFecha desc";
			Query queryObject = getSession().createQuery(queryString);
			if(queryObject.list().size()<1){
				return null;
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("Encontrar movimientos posteriores falló para el préstamo "+prestamoId, re);
			throw re;
		}
	}
	
	
	
	public Object getFechaPrimerMov(String preId) {
		log.debug("finding all CtaMxpMovimientoPrestamo instances");
		try {
			String queryString ="select mxp1.mxpFecha from CtaMxpMovimientoPrestamo mxp1 " +
					"where mxp1.mxpId = " +
					"(select min(mxp.mxpId) from CtaMxpMovimientoPrestamo mxp " +
								"where mxp.ctaPrePrestamo.preId='" + preId + "')";
			Query queryObject = getSession().createQuery(queryString);
			return (Date) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Integer getIdPrimerMov(String preId) {
		log.debug("finding all CtaMxpMovimientoPrestamo instances");
		try {
			String queryString ="select min(mxp.mxpId) from CtaMxpMovimientoPrestamo mxp " +
								"where mxp.ctaPrePrestamo.preId='" + preId + "'";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}