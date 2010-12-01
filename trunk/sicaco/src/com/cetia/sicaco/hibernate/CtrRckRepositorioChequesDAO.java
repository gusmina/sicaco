package com.cetia.sicaco.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.mad.utilidades.ElapsedTime;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtrRckRepositorioCheques entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrRckRepositorioCheques
 * @author MyEclipse Persistence Tools
 */

public class CtrRckRepositorioChequesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtrRckRepositorioChequesDAO.class);
	// property constants
	public static final String RCK_NOMBRE = "rckNombre";
	public static final String RCK_CORR_INI = "rckCorrIni";
	public static final String RCK_CORR_FIN = "rckCorrFin";
	public static final String RCK_CORR_ACTUAL = "rckCorrActual";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String RCK_ESTADO = "rckEstado";

	public CtrRckRepositorioChequesDAO(Session session) {
		super(session);
	}

	public void save(CtrRckRepositorioCheques transientInstance) {
		log.debug("saving CtrRckRepositorioCheques instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtrRckRepositorioCheques persistentInstance) {
		log.debug("deleting CtrRckRepositorioCheques instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtrRckRepositorioCheques findById(java.lang.Integer id) {
		log.debug("getting CtrRckRepositorioCheques instance with id: " + id);
		try {
			CtrRckRepositorioCheques instance = (CtrRckRepositorioCheques) getSession()
					.get("com.cetia.sicaco.hibernate.CtrRckRepositorioCheques",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrRckRepositorioCheques instance) {
		log.debug("finding CtrRckRepositorioCheques instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrRckRepositorioCheques").add(
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
		log.debug("finding CtrRckRepositorioCheques instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrRckRepositorioCheques as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRckNombre(Object rckNombre) {
		return findByProperty(RCK_NOMBRE, rckNombre);
	}

	public List findByRckCorrIni(Object rckCorrIni) {
		return findByProperty(RCK_CORR_INI, rckCorrIni);
	}

	public List findByRckCorrFin(Object rckCorrFin) {
		return findByProperty(RCK_CORR_FIN, rckCorrFin);
	}

	public List findByRckCorrActual(Object rckCorrActual) {
		return findByProperty(RCK_CORR_ACTUAL, rckCorrActual);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}
	
	public List findByRckEstado(Object rckEstado) {
		return findByProperty(RCK_ESTADO, rckEstado);
	}

	public List findAll() {
		log.debug("finding all CtrRckRepositorioCheques instances");
		try {
			String queryString = "from CtrRckRepositorioCheques";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrRckRepositorioCheques merge(
			CtrRckRepositorioCheques detachedInstance) {
		log.debug("merging CtrRckRepositorioCheques instance");
		try {
			CtrRckRepositorioCheques result = (CtrRckRepositorioCheques) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtrRckRepositorioCheques instance) {
		log.debug("attaching dirty CtrRckRepositorioCheques instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtrRckRepositorioCheques instance) {
		log.debug("attaching clean CtrRckRepositorioCheques instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByCckSerie(String cckSerie) {
		try {
			String queryString = "from CtrRckRepositorioCheques rck where " +
					"rck.ctrCckControlCheques.cckSerie = '" + cckSerie + "'";
			Query queryObject = getSession().createQuery(queryString);
			List list = (List<CtrRckRepositorioCheques>)queryObject.list();
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<CtrRckRepositorioCheques> findBySyB(Integer banId, Integer sucId) {
		try {
			String queryString = "from CtrRckRepositorioCheques rck where " +
					"rck.ctrCckControlCheques.ctrBanBanco.banId = " + banId +
					" AND rck.secSucSucursal.sucId = " + sucId + 
					" AND rck.rckEstado = 'A'";
			Query queryObject = getSession().createQuery(queryString);
			List<CtrRckRepositorioCheques> rck = queryObject.list();
			return rck;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<CtrRckRepositorioCheques> findRepositorioWithBanco(Integer sucId) {
		try {
			String queryString = "from CtrRckRepositorioCheques rck where rck.rckEstado='A' and rck.secSucSucursal.sucId="+sucId;
			Query queryObject = getSession().createQuery(queryString);
			List<CtrRckRepositorioCheques> lista = queryObject.list();
			List<CtrRckRepositorioCheques> listaN = new ArrayList<CtrRckRepositorioCheques>();
			
			/**Construimos nombre**/
			String nombre;
			String tempNombre;
			Iterator<CtrRckRepositorioCheques> iterador = lista.listIterator();
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				CtrRckRepositorioCheques name = (CtrRckRepositorioCheques) iterator.next();
				nombre = name.getRckNombre();
				
				if(!nombre.contains("-")){
					tempNombre = name.getCtrCckControlCheques().getCtrBanBanco().getBanNombre();
					tempNombre += "-"+nombre;
				}else tempNombre = nombre;
				
				name.setRckNombre(tempNombre);
				listaN.add(name);
			}
			return listaN;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}