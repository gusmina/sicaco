package com.cetia.sicaco.hibernate;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConDpaDetallePartida entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConDpaDetallePartida
 * @author MyEclipse Persistence Tools
 */

public class ConDpaDetallePartidaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConDpaDetallePartidaDAO.class);
	// property constants
	public static final String DPA_VALOR_DEBE = "dpaValorDebe";
	public static final String DPA_VALOR_HABER = "dpaValorHaber";
	public static final String DPA_OTRO_CONCEPTO = "dpaOtroConcepto";

	public ConDpaDetallePartidaDAO(Session session) {
		super(session);
	}

	public void save(ConDpaDetallePartida transientInstance) {
		log.debug("saving ConDpaDetallePartida instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConDpaDetallePartida persistentInstance) {
		log.debug("deleting ConDpaDetallePartida instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConDpaDetallePartida findById(Long id) {
		log.debug("getting ConDpaDetallePartida instance with id: " + id);
		try {
			ConDpaDetallePartida instance = (ConDpaDetallePartida) getSession()
					.get("com.cetia.sicaco.hibernate.ConDpaDetallePartida", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConDpaDetallePartida instance) {
		log.debug("finding ConDpaDetallePartida instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConDpaDetallePartida").add(
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
		log.debug("finding ConDpaDetallePartida instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConDpaDetallePartida as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDpaValorDebe(Object dpaValorDebe) {
		return findByProperty(DPA_VALOR_DEBE, dpaValorDebe);
	}

	public List findByDpaValorHaber(Object dpaValorHaber) {
		return findByProperty(DPA_VALOR_HABER, dpaValorHaber);
	}

	public List findByDpaOtroConcepto(Object dpaOtroConcepto) {
		return findByProperty(DPA_OTRO_CONCEPTO, dpaOtroConcepto);
	}

	public List findAll() {
		log.debug("finding all ConDpaDetallePartida instances");
		try {
			String queryString = "from ConDpaDetallePartida";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConDpaDetallePartida merge(ConDpaDetallePartida detachedInstance) {
		log.debug("merging ConDpaDetallePartida instance");
		try {
			ConDpaDetallePartida result = (ConDpaDetallePartida) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConDpaDetallePartida instance) {
		log.debug("attaching dirty ConDpaDetallePartida instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConDpaDetallePartida instance) {
		log.debug("attaching clean ConDpaDetallePartida instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByPartidaContable(Long pcoId) {
		try{
			String sql = "from ConDpaDetallePartida dpa where dpa.conPcoPartidaContable.pcoId ="+pcoId+ "and (dpa.dpaValorDebe <> 0 or dpa.dpaValorHaber <> 0)";
			Query queryObject = getSession().createQuery(sql);
			//queryObject.setParameter(0, pcoId);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByPartidaContable(Long pcoId, String estado) {
		String cola  = "";
		if (estado != null && estado.length()> 1 && !estado.equals("A")){
			 cola = " and (dpa.dpaValorDebe <> 0 or dpa.dpaValorHaber <> 0)";
		}
		try{
			String sql = "from ConDpaDetallePartida dpa where dpa.conPcoPartidaContable.pcoId = "+pcoId +cola;
			Query queryObject = getSession().createQuery(sql);
			//queryObject.setParameter(0, pcoId);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List<ConDpaDetallePartida> findByCriteriaDetPartida(Long pcoId){
		List<ConDpaDetallePartida> conList = null;
		Criteria criteria = getSession().createCriteria(ConDpaDetallePartida.class);		
		criteria.add(Restrictions.eq("conPcoPartidaContable.pcoId", pcoId));
			
		conList = criteria.list();
		return conList;
	}
	
	public int deleteByPartidaContable(Long pcoId) {
		try{
			String sql = "delete from ConDpaDetallePartida dpa where conPcoPartidaContable.pcoId = ? ";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, pcoId);
			return queryObject.executeUpdate();
		}		
		catch (RuntimeException re) {
			log.error("eliminando por id partida fallo ...   ", re);
			throw re;
		}
	}

	public void actualizaMovimientos(int idHija, int idPadre) {
		log.debug("updating all ConCueCuenta instances");
		try {
			String queryString = "UPDATE ConDpaDetallePartida c SET c.conCueCuenta.cueId=" + idHija + 
									" where c.conCueCuenta.cueId=" + idPadre;
			getSession().createQuery(queryString).executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public void actualizaMovimientos(Integer cueId, Integer cueId2,Session session) {
		ConDpaDetallePartidaDAO partidaDAO = new ConDpaDetallePartidaDAO(session);
		Transaction tx = partidaDAO.getSession().beginTransaction();
		ConCueCuentaDAO cuentaDAO = new ConCueCuentaDAO(session);
		ConCueCuenta cuenta = cuentaDAO.findById(cueId);
		List<ConDpaDetallePartida> partidas = partidaDAO.findByProperty("conCueCuenta.cueId", cueId2);
		for (Iterator iterator = partidas.iterator(); iterator.hasNext();) {
			ConDpaDetallePartida partida = (ConDpaDetallePartida) iterator.next();
			partida.setConCueCuenta(cuenta);
			partidaDAO.merge(partida);
		}
		tx.commit();
		partidaDAO.getSession().flush();
		partidaDAO.getSession().clear();
	}
	
	public List<ConDpaDetallePartida> findByPartidaModulo(Integer cpaId,Long pcoId,String otroConcepto,Integer cueId) {
		try{
			String sql = "from ConDpaDetallePartida det where (det.conCpaConceptoPartida.cpaId = ? or det.dpaOtroConcepto = ?) "+
						"and det.conPcoPartidaContable.pcoId = ? and det.conCueCuenta.cueId = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, cpaId);
			queryObject.setParameter(1, otroConcepto);
			queryObject.setParameter(2, pcoId);
			queryObject.setParameter(3, cueId);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}