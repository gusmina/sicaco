package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvTarTipoArticulo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvTarTipoArticulo
 * @author MyEclipse Persistence Tools
 */

public class InvTarTipoArticuloDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvTarTipoArticuloDAO.class);
	// property constants
	public static final String TAR_ID = "tarId";
	public static final String TAR_NOMBRE = "tarNombre";
	public static final String TAR_DESCRIPCION = "tarDescripcion";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvTarTipoArticuloDAO(Session session) {
		super(session);
	}

	public void save(InvTarTipoArticulo transientInstance) {
		log.debug("saving InvTarTipoArticulo instance");
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

	public void delete(InvTarTipoArticulo persistentInstance) {
		log.debug("deleting InvTarTipoArticulo instance");
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

	public InvTarTipoArticulo findById(java.lang.String id) {
		log.debug("getting InvTarTipoArticulo instance with id: " + id);
		try {
			InvTarTipoArticulo instance = (InvTarTipoArticulo) getSession()
					.get("com.cetia.sicaco.hibernate.InvTarTipoArticulo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvTarTipoArticulo instance) {
		log.debug("finding InvTarTipoArticulo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvTarTipoArticulo").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<InvTarTipoArticulo> findByCriteria(InvTarTipoArticulo instance){
		List<InvTarTipoArticulo> lst = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(InvTarTipoArticulo.class);
			
			if (instance.getTarId()!=null && !instance.getTarId().trim().equals("")){
				criteria.add(Restrictions.like(TAR_ID,"%" + instance.getTarId() + "%"));
			}
			if (instance.getTarNombre() !=null && !instance.getTarNombre().trim().equals("")) {
				criteria.add(Restrictions.like(TAR_NOMBRE,"%" + instance.getTarNombre() + "%"));
			}
			lst =  (List<InvTarTipoArticulo>)criteria.getExecutableCriteria(getSession()).list();
		return lst;
	}
	
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding InvTarTipoArticulo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvTarTipoArticulo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTarNombre(Object tarNombre) {
		return findByProperty(TAR_NOMBRE, tarNombre);
	}

	public List findByTarDescripcion(Object tarDescripcion) {
		return findByProperty(TAR_DESCRIPCION, tarDescripcion);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvTarTipoArticulo instances");
		try {
			String queryString = "from InvTarTipoArticulo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvTarTipoArticulo merge(InvTarTipoArticulo detachedInstance) {
		log.debug("merging InvTarTipoArticulo instance");
		try {
			InvTarTipoArticulo result = (InvTarTipoArticulo) getSession()
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

	public void attachDirty(InvTarTipoArticulo instance) {
		log.debug("attaching dirty InvTarTipoArticulo instance");
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

	public void attachClean(InvTarTipoArticulo instance) {
		log.debug("attaching clean InvTarTipoArticulo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public String nextId() {
		String id = "";
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(tar.tarId)  as tarId from InvTarTipoArticulo tar";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof String) {
				String val = obj.toString();
				Integer value = Integer.valueOf(val);
				value = value + 1;
				id = value.toString();
			}else {
				id = new Integer(1).toString();
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	}
}