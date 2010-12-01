package com.cetia.sicaco.hibernate;

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
 * CtaMxaMovimientoAhorro entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorro
 * @author MyEclipse Persistence Tools
 */

public class CtaMxaMovimientoAhorroDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaMxaMovimientoAhorroDAO.class);
	// property constants
	public static final String MXA_MONTO = "mxaMonto";
	public static final String MXA_INTERES_TRAN = "mxaInteresTran";
	public static final String MXA_SALDO = "mxaSaldo";

	public CtaMxaMovimientoAhorroDAO(Session session) {
		super(session);
	}

	public void save(CtaMxaMovimientoAhorro transientInstance) {
		log.debug("saving CtaMxaMovimientoAhorro instance");
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

	public void delete(CtaMxaMovimientoAhorro persistentInstance) {
		log.debug("deleting CtaMxaMovimientoAhorro instance");
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

	public CtaMxaMovimientoAhorro findById(java.lang.Integer id) {
		log.debug("getting CtaMxaMovimientoAhorro instance with id: " + id);
		try {
			CtaMxaMovimientoAhorro instance = (CtaMxaMovimientoAhorro) getSession()
					.get("com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorro",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaMxaMovimientoAhorro instance) {
		log.debug("finding CtaMxaMovimientoAhorro instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaMxaMovimientoAhorro").add(
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
		log.debug("finding CtaMxaMovimientoAhorro instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaMxaMovimientoAhorro as model where model."
					+ propertyName + "= ? " +
							"ORDER BY model.mxaFecha desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMxaMonto(Object mxaMonto) {
		return findByProperty(MXA_MONTO, mxaMonto);
	}

	public List findByMxaInteresTran(Object mxaInteresTran) {
		return findByProperty(MXA_INTERES_TRAN, mxaInteresTran);
	}
	
	public List findByMxaSaldo(Object mxaSaldo) {
		return findByProperty(MXA_SALDO, mxaSaldo);
	}
	 
	public List findAll() {
		log.debug("finding all CtaMxaMovimientoAhorro instances");
		try {
			String queryString = "from CtaMxaMovimientoAhorro";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaMxaMovimientoAhorro merge(CtaMxaMovimientoAhorro detachedInstance) {
		log.debug("merging CtaMxaMovimientoAhorro instance");
		try {
			CtaMxaMovimientoAhorro result = (CtaMxaMovimientoAhorro) getSession()
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

	public void attachDirty(CtaMxaMovimientoAhorro instance) {
		log.debug("attaching dirty CtaMxaMovimientoAhorro instance");
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

	public void attachClean(CtaMxaMovimientoAhorro instance) {
		log.debug("attaching clean CtaMxaMovimientoAhorro instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	
	public List<CtaMxaMovimientoAhorro> findByPeriod(String fechaIni,String fechaFin, String cahId) {
		try{
			List<CtaMxaMovimientoAhorro> movList = null;
			log.debug("Buscamos movimientos de ahorro por campos de busqueda en formulario ");
			DetachedCriteria criteria = DetachedCriteria.forClass(CtaMxaMovimientoAhorro.class);
			if(fechaIni!=null && fechaFin != null){
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.mxa_fecha,'%d-%m-%Y')  between ? and ?",
						new Object[]{fechaIni,fechaFin}
						,new Type[]{Hibernate.STRING,Hibernate.STRING}
				));
			}
			criteria.add(Restrictions.eq("ctaCahCuentaAhorro.cahId", cahId));
			criteria.addOrder(Order.desc("mxaFecha"));
			movList = criteria.getExecutableCriteria(getSession()).list();
			return movList;
			
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public CtaMxaMovimientoAhorro findUltimoMovimientoAhorroO(String idCuenta){
		try {
			String queryString = "from CtaMxaMovimientoAhorro as mxa"
								+"  where mxa.ctaCahCuentaAhorro.cahId = ? "+
								" and mxa.mxaFecha=( select max(m.mxaFecha)  from CtaMxaMovimientoAhorro as m"
								+"  where m.ctaCahCuentaAhorro.cahId = ?)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			queryObject.setParameter(1, idCuenta);
			List lista = queryObject.list();
			return (!lista.isEmpty()?(CtaMxaMovimientoAhorro)lista.get(0):null);
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	
	}
	
	
	public CtaMxaMovimientoAhorro findUltimoMovimientoAhorro(String idCuenta){
		try {
			String queryString = "from CtaMxaMovimientoAhorro ma " +
								"where ma.mxaId = " +
								"	(select max(mxa.mxaId) from CtaMxaMovimientoAhorro as mxa " +
								"	where mxa.ctaCahCuentaAhorro.cahId = ?) ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			Object obj = queryObject.uniqueResult();
			
			return (CtaMxaMovimientoAhorro) obj;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	
	}

	
	public int movimientosPost(String idCuenta, Date movimiento){
		try {
			String queryString = "from CtaMxaMovimientoAhorro mxa " +
			"where mxa.ctaCahCuentaAhorro.cahId = ? " +
			"and mxa.mxaFecha > ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			queryObject.setParameter(1, movimiento);
			return queryObject.list().size();
		} catch (Exception e) {
			return -1;
		}
		
	}
	
	
	public CtaMxaMovimientoAhorro findMovimientoAnterior(String idCuenta, String fecha){
		try {
			String queryString = "from CtaMxaMovimientoAhorro as mxa"
								+"  where mxa.ctaCahCuentaAhorro.cahId = ? "+
								" and mxa.mxaFecha <= ?  order by mxa.mxaFecha desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			queryObject.setParameter(1, fecha);
			
			List lista = queryObject.list();
			return (!lista.isEmpty()?(CtaMxaMovimientoAhorro)lista.get(0):null);
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	
	}
	
	public Object getFechaPrimerMov(String cahId) {
		log.debug("Buscando el primer movimiento de ahorro");
		try {
			String queryString ="select mxa1.mxaFecha from CtaMxaMovimientoAhorro mxa1 " +
					"where mxa1.mxaId = " +
					"(select min(mxa.mxaId) from CtaMxaMovimientoAhorro mxa " +
								"where mxa.ctaCahCuentaAhorro.cahId ='" + cahId + "' and mxa.ctaCahCuentaAhorro.cahId like 'B%')";
			Query queryObject = getSession().createQuery(queryString);
			return (Date) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("no se pudo localizar el primer movimiento para "+cahId, re);
			throw re;
		}
	}
	
	
	public Object getPrimerSaldo(String cahId) {
		log.debug("Buscando el primer movimiento de ahorro");
		try {
			String queryString ="select mxa1.mxaSaldo from CtaMxaMovimientoAhorro mxa1 " +
					"where mxa1.mxaId = " +
					"(select min(mxa.mxaId) from CtaMxaMovimientoAhorro mxa " +
								"where mxa.ctaCahCuentaAhorro.cahId ='" + cahId + "' and mxa.ctaCahCuentaAhorro.cahId like 'B%')";
			Query queryObject = getSession().createQuery(queryString);
			return (Double) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("no se pudo localizar el saldo movimiento para "+cahId, re);
			throw re;
		}
	}
	

	public CtaMxaMovimientoAhorro findPrimerMovimiento(String cahId){
		try {
			String queryString ="from CtaMxaMovimientoAhorro mxa1 " +
			"where mxa1.mxaId = " +
			"(select min(mxa.mxaId) from CtaMxaMovimientoAhorro mxa " +
						"where mxa.ctaCahCuentaAhorro.cahId ='" + cahId + "' and mxa.ctaCahCuentaAhorro.cahId like 'B%')";
			
			Query queryObject = getSession().createQuery(queryString);
			
			List l = queryObject.list();
			if(l.size() > 0){
				CtaMxaMovimientoAhorro mxa =(CtaMxaMovimientoAhorro)l.get(0) ;
				return mxa;
			}else{
				return null;
			}
			
		} catch (RuntimeException re) {
			log.error("Busqueda del primer movimiento fallida ", re);
			throw re;
		}
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CtaMxaMovimientoAhorro> findMovimientosSiguientes(String idCuenta, String fecha){
		try {
			String queryString = "from CtaMxaMovimientoAhorro as mxa"
								+"  where mxa.ctaCahCuentaAhorro.cahId = ? "+
								" and mxa.mxaFecha > ?  order by mxa.mxaFecha";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			queryObject.setParameter(1, fecha);
			List<CtaMxaMovimientoAhorro> lista = queryObject.list();
			return lista;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	
	}
	
	public List<CtaMxaMovimientoAhorro> findMovimientosSiguientes2(String idCuenta, String fecha){
		try {
			String queryString = "from CtaMxaMovimientoAhorro as mxa"
								+"  where mxa.ctaCahCuentaAhorro.cahId = ? "+
								" and mxa.mxaFecha >= ?  order by mxa.mxaFecha";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, idCuenta);
			queryObject.setParameter(1, fecha);
			List<CtaMxaMovimientoAhorro> lista = queryObject.list();
			return lista;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	
	}
	
	public Date findFechaUltimoMovimientoAhorro(String cahId) {
		try {
			String queryString = "select max(mxa.mxaFecha) from CtaMxaMovimientoAhorro as mxa " +
								 "where mxa.ctaCahCuentaAhorro.cahId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, cahId);
			Object obj = queryObject.uniqueResult();
			
			return (Date) obj;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}