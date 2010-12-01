package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
 * CtaTprTipoPrestamo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTprTipoPrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaTprTipoPrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaTprTipoPrestamoDAO.class);
	// property constants
	public static final String TPR_NOMBRE = "tprNombre";
	public static final String TPR_DESCRIPCION = "tprDescripcion";

	public CtaTprTipoPrestamoDAO(Session session) {
		super(session);
	}

	public void save(CtaTprTipoPrestamo transientInstance) {
		log.debug("saving CtaTprTipoPrestamo instance");
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

	public void delete(CtaTprTipoPrestamo persistentInstance) {
		log.debug("deleting CtaTprTipoPrestamo instance");
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

	public CtaTprTipoPrestamo findById(java.lang.Integer id) {
		log.debug("getting CtaTprTipoPrestamo instance with id: " + id);
		try {
			CtaTprTipoPrestamo instance = (CtaTprTipoPrestamo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaTprTipoPrestamo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTprTipoPrestamo instance) {
		log.debug("finding CtaTprTipoPrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTprTipoPrestamo").add(
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
		log.debug("finding CtaTprTipoPrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTprTipoPrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTprNombre(Object tprNombre) {
		return findByProperty(TPR_NOMBRE, tprNombre);
	}

	public List findByTprDescripcion(Object tprDescripcion) {
		return findByProperty(TPR_DESCRIPCION, tprDescripcion);
	}

	public List findAll() {
		log.debug("finding all CtaTprTipoPrestamo instances");
		try {
			String queryString = "from CtaTprTipoPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTprTipoPrestamo merge(CtaTprTipoPrestamo detachedInstance) {
		log.debug("merging CtaTprTipoPrestamo instance");
		try {
			CtaTprTipoPrestamo result = (CtaTprTipoPrestamo) getSession()
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

	public void attachDirty(CtaTprTipoPrestamo instance) {
		log.debug("attaching dirty CtaTprTipoPrestamo instance");
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

	public void attachClean(CtaTprTipoPrestamo instance) {
		log.debug("attaching clean CtaTprTipoPrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUpdatedName(Integer id,String nombre,Integer lprId) {
		try{
			String sql = "from CtaTprTipoPrestamo tpr where tpr.tprId != ? and tpr.tprNombre = ? and tpr.ctaLprLineaPrestamo.lprId = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, id);
			queryObject.setParameter(1, nombre);
			queryObject.setParameter(2, lprId);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findTiposVigentes(Date fecha) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "from CtaTprTipoPrestamo as tpr  where ( ? " +
					"between tpr.ctaLprLineaPrestamo.lprDesde and tpr.ctaLprLineaPrestamo.lprHasta) or  tpr.ctaLprLineaPrestamo.lprHasta is null";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, sdf.format(fecha));
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			throw re;
		}
	}
	public List findTiposVigentes2(Date fecha) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "from CtaTprTipoPrestamo as tpr  where ( ? " +
					"between tpr.ctaLprLineaPrestamo.lprDesde and tpr.ctaLprLineaPrestamo.lprHasta) or  tpr.ctaLprLineaPrestamo.lprHasta is null" +
					" and tpr.tprNombre like ('%fiadores%')";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, sdf.format(fecha));
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	public List findByNameLinea(String nombre,Integer lprId) {
		try{
			String sql = "from CtaTprTipoPrestamo tpr where tpr.tprNombre = ? and tpr.ctaLprLineaPrestamo.lprId = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, nombre);
			queryObject.setParameter(1, lprId);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByLinea(Integer linea) {
		log.debug("finding all CtaTprTipoPrestamo instances");
		try {
			String queryString = "from CtaTprTipoPrestamo tpr where tpr.ctaLprLineaPrestamo.lprId = " + linea + " order by tpr.tprNombre";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * Se utiliza para buscar los tipos de prestamo sin lineas que tengan 0 en su
	 * plan de meses
	 * 
	 * @param linea
	 * @return
	 */
	public List findByLineaSinCeros(Integer linea) {
		log.debug("finding all CtaTprTipoPrestamo instances");
		try {
			String queryString = "from CtaTprTipoPrestamo tpr where tpr.ctaLprLineaPrestamo.lprId = " + linea + "and tpr.ctaPlmPlanMeses.plmDuracion > 0 order by tpr.tprNombre";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(tpr.tprId) + 1  as id from CtaTprTipoPrestamo tpr";
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
			//getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	}

	public CtaTprTipoPrestamo findByProveedor(Integer proId) {
		try{
			String sql = "select put.ctaTprTipoPrestamo " +
						"from IucPutProveedorTipoPrestamo put " +
						"where put.invProProveedor.proId = ? ";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, proId);
			return (CtaTprTipoPrestamo) queryObject.uniqueResult();
		}		
		catch (RuntimeException re) {
			throw re;
		}
	}

	public CtaTprTipoPrestamo findByTar(String tarId) {
		try{
			String sql ="select tut.ctaTprTipoPrestamo " +
						"from IucTutTarTpr tut " +
						"where tut.invTarTipoArticulo.tarId = ? ";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, tarId);
			return (CtaTprTipoPrestamo) queryObject.uniqueResult();
		}		
		catch (RuntimeException re) {
			throw re;
		}
	}

	//Ya no se usa
	public List findPlazosByLinea(Integer linea) {
		CtaPlmPlanMesesDAO CtaPlmPlanMesesDAO = new CtaPlmPlanMesesDAO(getSession());
		ArrayList<CtaPlmPlanMeses> lista = new ArrayList<CtaPlmPlanMeses>();
		try {
			String queryString = "select distinct tpr.ctaPlmPlanMeses.plmId from CtaTprTipoPrestamo tpr where tpr.ctaLprLineaPrestamo.lprId = " + linea + "and tpr.ctaPlmPlanMeses.plmDuracion > 0 order by tpr.ctaPlmPlanMeses.plmId";
			Query queryObject = getSession().createQuery(queryString);
			
			List l=queryObject.list();
			Iterator i = l.iterator();
			while(i.hasNext()){
				Integer id = (Integer) i.next();
				CtaPlmPlanMeses CtaPlmPlanMeses = CtaPlmPlanMesesDAO.findById(id);
				lista.add(CtaPlmPlanMeses);
			}			
			return lista;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//Ya no se usa
	public List findTasaByLineaAndPlazo(Integer linea,Integer plazo) {
		CtaTinTasaInteresDAO CtaTinTasaInteresDAO = new CtaTinTasaInteresDAO(getSession());
		ArrayList<CtaTinTasaInteres> lista = new ArrayList<CtaTinTasaInteres>();
		try {
			String queryString = "SELECT distinct tpr.ctaTinTasaInteres.tinId FROM CtaTprTipoPrestamo tpr WHERE tpr.ctaLprLineaPrestamo.lprId = " + linea + "and tpr.ctaPlmPlanMeses.plmDuracion > 0 and tpr.ctaPlmPlanMeses.plmId="+plazo+" order by tpr.ctaTinTasaInteres.tinTasa";
			Query queryObject = getSession().createQuery(queryString);
			
			List l=queryObject.list();
			Iterator i = l.iterator();
			while(i.hasNext()){
				Integer id = (Integer) i.next();
				CtaTinTasaInteres CtaTinTasaInteres = CtaTinTasaInteresDAO.findById(id);
				lista.add(CtaTinTasaInteres);
			}			
			return lista;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findTasaByLineaAndPlazoAndTasa(Integer linea,Integer plazo,Integer tasa) {
		try {
			String queryString = "from CtaTprTipoPrestamo as model where model.ctaLprLineaPrestamo.lprId= ? and "+
					"model.ctaPlmPlanMeses.plmId = ? and model.ctaTinTasaInteres.tinId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, linea);
			queryObject.setParameter(1, plazo);
			queryObject.setParameter(2, tasa);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by linea and plazo and tasa failed", re);
			throw re;
		}
	}	
}