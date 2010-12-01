package com.cetia.sicaco.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvMedMedida entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvMedMedida
 * @author MyEclipse Persistence Tools
 */

public class InvMedMedidaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvMedMedidaDAO.class);
	// property constants
	public static final String MED_NOMBRE_MEDIDA = "medNombreMedida";
	public static final String MED_COMENTARIO = "medComentario";
	public static final String MED_BOGUS = "medBogus";

	public InvMedMedidaDAO(Session session) {
		super(session);
	}

	public void save(InvMedMedida transientInstance) {
		log.debug("saving InvMedMedida instance");
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

	public void delete(InvMedMedida persistentInstance) {
		log.debug("deleting InvMedMedida instance");
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

	public InvMedMedida findById(java.lang.String id) {
		log.debug("getting InvMedMedida instance with id: " + id);
		try {
			InvMedMedida instance = (InvMedMedida) getSession().get(
					"com.cetia.sicaco.hibernate.InvMedMedida", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvMedMedida instance) {
		log.debug("finding InvMedMedida instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvMedMedida").add(
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
		log.debug("finding InvMedMedida instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvMedMedida as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List<InvMedMedida> findByCriteria(InvMedMedida medida){
		log.debug("finding InvMedMedida instance by criteria");
		List<InvMedMedida> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(InvMedMedida.class);
		
		if (medida.getMedId() != null && !medida.getMedId().trim().equals("")) {
 			criteria.add(Restrictions.like("medId", "%" + medida.getMedId() + "%"));
		}
		if (medida.getMedNombreMedida()!= null && !medida.getMedNombreMedida().trim().equals("")) {
			criteria.add(Restrictions.like("medNombreMedida","%"+ medida.getMedNombreMedida()+ "%"));
		}
		
		return (List<InvMedMedida>)criteria.getExecutableCriteria(getSession()).list();
	}
	
	public List findByMedNombreMedida(Object medNombreMedida) {
		return findByProperty(MED_NOMBRE_MEDIDA, medNombreMedida);
	}

	public List findByMedComentario(Object medComentario) {
		return findByProperty(MED_COMENTARIO, medComentario);
	}
	
	public List findByMedBogus(Object medBogus) {
		return findByProperty(MED_BOGUS, medBogus);
	}

	public List findAll() {
		log.debug("finding all InvMedMedida instances");
		try {
			String queryString = "from InvMedMedida";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvMedMedida merge(InvMedMedida detachedInstance) {
		log.debug("merging InvMedMedida instance");
		try {
			InvMedMedida result = (InvMedMedida) getSession().merge(
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

	public void attachDirty(InvMedMedida instance) {
		log.debug("attaching dirty InvMedMedida instance");
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

	public void attachClean(InvMedMedida instance) {
		log.debug("attaching clean InvMedMedida instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}