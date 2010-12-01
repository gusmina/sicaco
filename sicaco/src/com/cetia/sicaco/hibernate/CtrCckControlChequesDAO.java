package com.cetia.sicaco.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMessages;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtrCckControlCheques entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrCckControlCheques
 * @author MyEclipse Persistence Tools
 */

public class CtrCckControlChequesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtrCckControlChequesDAO.class);
	// property constants
	public static final String CCK_SERIE = "cckSerie";
	public static final String CCK_CORR_INI = "cckCorrIni";
	public static final String CCK_CORR_FIN = "cckCorrFin";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public CtrCckControlChequesDAO(Session session) {
		super(session);
	}

	public void save(CtrCckControlCheques transientInstance) {
		log.debug("saving CtrCckControlCheques instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtrCckControlCheques persistentInstance) {
		log.debug("deleting CtrCckControlCheques instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtrCckControlCheques findById(java.lang.Integer id) {
		log.debug("getting CtrCckControlCheques instance with id: " + id);
		try {
			CtrCckControlCheques instance = (CtrCckControlCheques) getSession()
					.get("com.cetia.sicaco.hibernate.CtrCckControlCheques", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrCckControlCheques instance) {
		log.debug("finding CtrCckControlCheques instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrCckControlCheques").add(
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
		log.debug("finding CtrCckControlCheques instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrCckControlCheques as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCckSerie(Object cckSerie) {
		return findByProperty(CCK_SERIE, cckSerie);
	}

	public List findByCckCorrIni(Object cckCorrIni) {
		return findByProperty(CCK_CORR_INI, cckCorrIni);
	}

	public List findByCckCorrFin(Object cckCorrFin) {
		return findByProperty(CCK_CORR_FIN, cckCorrFin);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all CtrCckControlCheques instances");
		try {
			String queryString = "from CtrCckControlCheques";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrCckControlCheques merge(CtrCckControlCheques detachedInstance) {
		log.debug("merging CtrCckControlCheques instance");
		try {
			CtrCckControlCheques result = (CtrCckControlCheques) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtrCckControlCheques instance) {
		log.debug("attaching dirty CtrCckControlCheques instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtrCckControlCheques instance) {
		log.debug("attaching clean CtrCckControlCheques instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findSame(String serie) {
		log.debug("finding all CtrCckControlCheques instances");
		try {
			String queryString = "from CtrCckControlCheques cck where cck.cckSerie = '" + serie + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String findCorrelativoActualCheque(int idBanco, int idSucursal) {
		log.debug("finding all CtrCckControlCheques instances");
		try {
			String queryString = "SELECT c.rckCorrActual "+
								 "FROM CtrRckRepositorioCheques c, CtrCckControlCheques cc, CtrBanBanco b  "+
								 "where 	b.banId=cc.ctrBanBanco.banId and c.ctrCckControlCheques.cckId=cc.cckId and "+
								 "(date(CURDATE()) between date(c.rckFechaIni) and date(c.rckFechaFin))"+
								 "and b.banId="+idBanco + " and c.secSucSucursal.sucId=" + idSucursal;
			Query queryObject = getSession().createQuery(queryString);
			Object objeto = queryObject.uniqueResult();
			String obj;
			if(objeto==null){
				obj = "0";
			}else{
				Integer correlativoCheque = new Integer(objeto.toString());
				correlativoCheque += 1;
				obj = correlativoCheque.toString();
			}
			return obj;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int findIdCorrelativoActualCheque(int idBanco, int idSucursal) {
		log.debug("finding all CtrCckControlCheques instances");
		try {
			String queryString = " SELECT c.rckId "+
								 " FROM CtrRckRepositorioCheques c, CtrCckControlCheques cc, CtrBanBanco b  "+
								 " where 	b.banId=cc.ctrBanBanco.banId and c.ctrCckControlCheques.cckId=cc.cckId and  "+
								 " (date(CURDATE()) between date(c.rckFechaIni) and date(c.rckFechaFin)) "+
								 " and b.banId="+idBanco + " and c.secSucSucursal.sucId=" + idSucursal;
			Query queryObject = getSession().createQuery(queryString);
			Object objeto = queryObject.uniqueResult();
			int obj;
			if(objeto==null){
				obj = 0;
			}else{
				Integer correlativoCheque = new Integer(objeto.toString());
				obj = correlativoCheque;
			}
			return obj;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByCriteria(CtrCckControlCheques controlCheques) {
		log.debug("finding CtrCckControlCheques instance by criteria");
		DetachedCriteria criteria = DetachedCriteria.forClass( CtrCckControlCheques.class);
		if (controlCheques.getCckSerie()!= null && !controlCheques.getCckSerie().trim().equals("")) {
 			criteria.add(Restrictions.like("cckSerie","%" +controlCheques.getCckSerie() + "%"));
		}
		return (List<CtrCckControlCheques>)criteria.getExecutableCriteria(getSession()).list();
	}
	
	public List<CtrCckControlCheques> findAllWithBanco() {
		log.debug("finding all CtrCckControlCheques instances");
		try {
			String queryString = "from CtrCckControlCheques";
			Query queryObject = getSession().createQuery(queryString);
			if(queryObject.list().size()>0){
				ArrayList<CtrCckControlCheques> lista = new ArrayList<CtrCckControlCheques>();
				for (Iterator iterator = queryObject.list().iterator(); iterator
						.hasNext();) {
					CtrCckControlCheques cheques = (CtrCckControlCheques) iterator.next();
					CtrCckControlCheques cheques2 = new CtrCckControlCheques();
					cheques2.setCckId(cheques.getCckId());
					cheques2.setCckSerie(cheques.getCtrBanBanco().getBanNombre() + " - " + cheques.getCckSerie());
					lista.add(cheques2);
				}
				return lista;
			}else{
				return new ArrayList<CtrCckControlCheques>();
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}