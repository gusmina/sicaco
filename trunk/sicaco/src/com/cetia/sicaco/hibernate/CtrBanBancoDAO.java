package com.cetia.sicaco.hibernate;

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
 * CtrBanBanco entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrBanBanco
 * @author MyEclipse Persistence Tools
 */

public class CtrBanBancoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtrBanBancoDAO.class);
	// property constants
	public static final String BAN_NOMBRE = "banNombre";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public CtrBanBancoDAO(Session session) {
		super(session);
	}

	public void save(CtrBanBanco transientInstance) {
		log.debug("saving CtrBanBanco instance");
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

	public void delete(CtrBanBanco persistentInstance) {
		log.debug("deleting CtrBanBanco instance");
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

	public CtrBanBanco findById(java.lang.Integer id) {
		log.debug("getting CtrBanBanco instance with id: " + id);
		try {
			CtrBanBanco instance = (CtrBanBanco) getSession().get(
					"com.cetia.sicaco.hibernate.CtrBanBanco", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrBanBanco instance) {
		log.debug("finding CtrBanBanco instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrBanBanco").add(
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
		log.debug("finding CtrBanBanco instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CtrBanBanco as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBanNombre(Object banNombre) {
		return findByProperty(BAN_NOMBRE, banNombre);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all CtrBanBanco instances");
		try {
			String queryString = "from CtrBanBanco";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrBanBanco merge(CtrBanBanco detachedInstance) {
		log.debug("merging CtrBanBanco instance");
		try {
			CtrBanBanco result = (CtrBanBanco) getSession().merge(
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

	public void attachDirty(CtrBanBanco instance) {
		log.debug("attaching dirty CtrBanBanco instance");
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

	public void attachClean(CtrBanBanco instance) {
		log.debug("attaching clean CtrBanBanco instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public List findBancoByRepositorioCheque() {
		log.debug("finding all BancoByRepositorioCheque instances");
		try {
			String queryString = "SELECT b.banId,b.banNombre   "+
								"FROM CtrBanBanco b, CtrRckRepositorioCheques rck,CtrCckControlCheques cck  "+
								"WHERE b.banId=cck.ctrBanBanco.banId and cck.cckId=rck.ctrCckControlCheques.cckId";
			Query queryObject = getSession().createQuery(queryString);
			List<Object> lista = queryObject.list();
			List<CtrBanBanco> listBanco = new ArrayList<CtrBanBanco>();
			Iterator<?> it = lista.iterator();
			while(it.hasNext()){
				CtrBanBanco banco = new CtrBanBanco();
				Object[] o = (Object[])it.next();
				banco.setBanId(Integer.parseInt(o[0].toString()));
				banco.setBanNombre(o[1].toString());
				listBanco.add(banco);
			}
			return listBanco;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}