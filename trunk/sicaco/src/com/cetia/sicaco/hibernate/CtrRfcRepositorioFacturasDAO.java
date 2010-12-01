package com.cetia.sicaco.hibernate;

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
 * CtrRfcRepositorioFacturas entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrRfcRepositorioFacturas
 * @author MyEclipse Persistence Tools
 */

public class CtrRfcRepositorioFacturasDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtrRfcRepositorioFacturasDAO.class);
	// property constants
	public static final String RFC_NOMBRE = "rfcNombre";
	public static final String RFC_CORR_INI = "rfcCorrIni";
	public static final String RFC_CORR_FIN = "rfcCorrFin";
	public static final String RFC_CORR_ACTUAL = "rfcCorrActual";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String TIPO_FACT_CONT = "tipoFactCont";
	public static final String RFC_ESTADO = "rfcEstado";

	public CtrRfcRepositorioFacturasDAO(Session session) {
		super(session);
	}

	public void save(CtrRfcRepositorioFacturas transientInstance) {
		log.debug("saving CtrRfcRepositorioFacturas instance");
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

	public void delete(CtrRfcRepositorioFacturas persistentInstance) {
		log.debug("deleting CtrRfcRepositorioFacturas instance");
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

	public CtrRfcRepositorioFacturas findById(java.lang.Integer id) {
		log.debug("getting CtrRfcRepositorioFacturas instance with id: " + id);
		try {
			CtrRfcRepositorioFacturas instance = (CtrRfcRepositorioFacturas) getSession()
					.get(
							"com.cetia.sicaco.hibernate.CtrRfcRepositorioFacturas",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrRfcRepositorioFacturas instance) {
		log.debug("finding CtrRfcRepositorioFacturas instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrRfcRepositorioFacturas")
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
		log.debug("finding CtrRfcRepositorioFacturas instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrRfcRepositorioFacturas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRfcNombre(Object rfcNombre) {
		return findByProperty(RFC_NOMBRE, rfcNombre);
	}

	public List findByRfcCorrIni(Object rfcCorrIni) {
		return findByProperty(RFC_CORR_INI, rfcCorrIni);
	}

	public List findByRfcCorrFin(Object rfcCorrFin) {
		return findByProperty(RFC_CORR_FIN, rfcCorrFin);
	}

	public List findByRfcCorrActual(Object rfcCorrActual) {
		return findByProperty(RFC_CORR_ACTUAL, rfcCorrActual);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}
	
	public List findByRfcEstado(Object rfcEstado) {
		return findByProperty(RFC_ESTADO, rfcEstado);
	}

	public List findAll() {
		log.debug("finding all CtrRfcRepositorioFacturas instances");
		try {
			String queryString = "from CtrRfcRepositorioFacturas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrRfcRepositorioFacturas merge(
			CtrRfcRepositorioFacturas detachedInstance) {
		log.debug("merging CtrRfcRepositorioFacturas instance");
		try {
			CtrRfcRepositorioFacturas result = (CtrRfcRepositorioFacturas) getSession()
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

	public void attachDirty(CtrRfcRepositorioFacturas instance) {
		log.debug("attaching dirty CtrRfcRepositorioFacturas instance");
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

	public void attachClean(CtrRfcRepositorioFacturas instance) {
		log.debug("attaching clean CtrRfcRepositorioFacturas instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List findSerie(){
		try {
			String queryString = "from CtrRfcRepositorioFacturas where " +
					"rfcFechaIni <= curdate() and rfcFechaFin >= curdate()" +
					"and rfcCorrActual not like rfcCorrFin";
			Query queryObject = getSession().createQuery(queryString);
			List list = (List<CtrRfcRepositorioFacturas>)queryObject.list();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findSerie(String tipoFactura,int sucID){
		try {
			String queryString = "from CtrRfcRepositorioFacturas where " +
					"rfcCorrActual <= rfcCorrFin " +
					"and rfcEstado = 'A' "+
					"and tipoFactCont='"+tipoFactura+"' "+
					"and sucID = "+sucID;
			Query queryObject = getSession().createQuery(queryString);
			List list = (List<CtrRfcRepositorioFacturas>)queryObject.list();
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findByCfcSerie(String cfcSerie){
		try {
			String queryString = "from CtrRfcRepositorioFacturas rfc where " +
					"rfc.ctrCfcControlFacturacion.cfcSerie = '" + cfcSerie + "'";
			Query queryObject = getSession().createQuery(queryString);
			List list = (List<CtrRfcRepositorioFacturas>)queryObject.list();
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}