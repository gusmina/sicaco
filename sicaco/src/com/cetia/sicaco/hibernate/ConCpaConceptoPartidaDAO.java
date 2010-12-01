package com.cetia.sicaco.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConCpaConceptoPartida entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConCpaConceptoPartida
 * @author MyEclipse Persistence Tools
 */

public class ConCpaConceptoPartidaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConCpaConceptoPartidaDAO.class);
	// property constants
	public static final String CPA_CONCEPTO = "cpaConcepto";
	public static final String CPA_DESCRIPCION_CONCEPTO = "cpaDescripcionConcepto";

	public ConCpaConceptoPartidaDAO(Session session) {
		super(session);
	}
	
	public void save(ConCpaConceptoPartida transientInstance) {
		log.debug("saving ConCpaConceptoPartida instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConCpaConceptoPartida persistentInstance) {
		log.debug("deleting ConCpaConceptoPartida instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConCpaConceptoPartida findById(java.lang.Integer id) {
		log.debug("getting ConCpaConceptoPartida instance with id: " + id);
		try {
			ConCpaConceptoPartida instance = (ConCpaConceptoPartida) getSession()
					.get("com.cetia.sicaco.hibernate.ConCpaConceptoPartida", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConCpaConceptoPartida instance) {
		log.debug("finding ConCpaConceptoPartida instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConCpaConceptoPartida").add(
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
		log.debug("finding ConCpaConceptoPartida instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConCpaConceptoPartida as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCpaConcepto(Object cpaConcepto,int descr_concep) {
		try {
			String queryString = "from ConCpaConceptoPartida as model where model.cpaConcepto=? and model.cpaDescripcionConcepto="+descr_concep;
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, cpaConcepto);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCpaDescripcionConcepto(Object cpaDescripcionConcepto) {
		try {
			String queryString ="SELECT  cpa.CPA_ID AS ID, IF(cpa.TTR_ID IS NULL,cpa.CPA_CONCEPTO, "+
									" ttr.TTR_NOMBRE) AS NOMBRE	"+
								" FROM 	cta_ttr_tipo_transaccion ttr RIGHT OUTER JOIN" +
								"       con_cpa_concepto_partida cpa	 ON ttr.TTR_ID = cpa.TTR_ID" +
								" WHERE cpa.CPA_DESCRIPCION_CONCEPTO=?";
			SQLQuery queryObject = getSession().createSQLQuery(queryString);
			queryObject.setParameter(0, cpaDescripcionConcepto);
			queryObject.addScalar("ID", Hibernate.INTEGER);
			queryObject.addScalar("NOMBRE", Hibernate.STRING);
			ArrayList<ConCpaConceptoPartida> listaConceptos = new ArrayList<ConCpaConceptoPartida>();
			List lista = null;
			lista = queryObject.list();
			Iterator it = lista.iterator();
			while(it.hasNext()){
				ConCpaConceptoPartida detalle = new ConCpaConceptoPartida();
				Object[] item = (Object[])it.next();
				detalle.setCpaId(new Integer(item[0].toString()));
				detalle.setCpaConcepto(item[1].toString());
				listaConceptos.add(detalle);
			}
			return listaConceptos;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ConCpaConceptoPartida instances");
		try {
			String queryString = "from ConCpaConceptoPartida";
			
			Query queryObject = getSession().createQuery(queryString);
			List<ConCpaConceptoPartida> listaCon = queryObject.list();
			List<ConCpaConceptoPartida> listaN = new ArrayList<ConCpaConceptoPartida>();
			for (Iterator iterator = listaCon.iterator(); iterator.hasNext();) {
				ConCpaConceptoPartida conCpaConceptoPartida = (ConCpaConceptoPartida) iterator
						.next();
				if(conCpaConceptoPartida.getCpaDescripcionConcepto()==0){
					if(conCpaConceptoPartida.getCtaTtrTipoTransaccion()!=null && conCpaConceptoPartida.getCtaTtrTipoTransaccion().getTtrId()!=null){
						if(conCpaConceptoPartida.getCpaConcepto()==null || conCpaConceptoPartida.getCpaConcepto().trim().equals("")){
							conCpaConceptoPartida.setCpaConcepto(conCpaConceptoPartida.getCtaTtrTipoTransaccion().getTtrNombre());
						}
					}
				}
				listaN.add(conCpaConceptoPartida);
			}
			return listaN;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConCpaConceptoPartida merge(ConCpaConceptoPartida detachedInstance) {
		log.debug("merging ConCpaConceptoPartida instance");
		try {
			ConCpaConceptoPartida result = (ConCpaConceptoPartida) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConCpaConceptoPartida instance) {
		log.debug("attaching dirty ConCpaConceptoPartida instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConCpaConceptoPartida instance) {
		log.debug("attaching clean ConCpaConceptoPartida instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUpdate(int cpaId, int op, int ttrId, String concepto) {
		log.debug("finding ConCpaConceptoPartida instances");
		try {
			String queryString="";
			if(op==1){
				queryString = "from ConCpaConceptoPartida c where c.cpaId!=? and c.ctaTtrTipoTransaccion.ttrId="+ttrId;
			}else{
				queryString = "from ConCpaConceptoPartida c where c.cpaId!=? and c.cpaConcepto='"+concepto+"'";
			}
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, cpaId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByTipoTransaccion(int ttrId, int descri_concep) {
		log.debug("finding ConCpaConceptoPartida instances");
		try {
			String queryString= "from ConCpaConceptoPartida c where c.ctaTtrTipoTransaccion.ttrId=? and c.cpaDescripcionConcepto="+descri_concep;
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ttrId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
}