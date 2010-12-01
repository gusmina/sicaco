package com.cetia.sicaco.hibernate;

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
 * SecRolRoles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecRolRoles
 * @author MyEclipse Persistence Tools
 */

public class SecRolRolesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecRolRolesDAO.class);
	// property constants
	public static final String ROL_DESCRIPCION = "rolDescripcion";
	public static final String ROL_TIPO_SESION = "rolTipoSesion";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public SecRolRolesDAO(Session session) {
		super(session);
	}

	public void save(SecRolRoles transientInstance) {
		log.debug("saving SecRolRoles instance");
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

	public void delete(SecRolRoles persistentInstance) {
		log.debug("deleting SecRolRoles instance");
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

	public SecRolRoles findById(java.lang.String id) {
		log.debug("getting SecRolRoles instance with id: " + id);
		try {
			SecRolRoles instance = (SecRolRoles) getSession().get(
					"com.cetia.sicaco.hibernate.SecRolRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecRolRoles instance) {
		log.debug("finding SecRolRoles instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecRolRoles").add(
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
		log.debug("finding SecRolRoles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SecRolRoles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRolDescripcion(Object rolDescripcion) {
		return findByProperty(ROL_DESCRIPCION, rolDescripcion);
	}

	public List findByRolTipoSesion(Object rolTipoSesion) {
		return findByProperty(ROL_TIPO_SESION, rolTipoSesion);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all SecRolRoles instances");
		try {
			String queryString = "from SecRolRoles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecRolRoles merge(SecRolRoles detachedInstance) {
		log.debug("merging SecRolRoles instance");
		try {
			SecRolRoles result = (SecRolRoles) getSession().merge(
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

	public void attachDirty(SecRolRoles instance) {
		log.debug("attaching dirty SecRolRoles instance");
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

	public void attachClean(SecRolRoles instance) {
		log.debug("attaching clean SecRolRoles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<SecRolRoles> findByCriteria(SecRolRoles roles){
		log.debug("finding SecRolRoles instance by criteria");
		List<SecRolRoles> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecRolRoles.class);
		
		if (roles.getRolNombre() != null && !roles.getRolNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("rolNombre", "%" + roles.getRolNombre() + "%"));
		}
		if (roles.getRolTipoSesion()!= null && !roles.getRolTipoSesion().trim().equals("")) {
			criteria.add(Restrictions.like("rolTipoSesion", roles.getRolTipoSesion()));
		}
		
		return (List<SecRolRoles>)criteria.getExecutableCriteria(getSession()).list();
	}

	public Integer getTotalRowCount() {
		log.debug("counting all rows of SecRolRoles");
		try {
			String queryString = "select count(p.rolNombre)from SecRolRoles p";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("counting all rows of SecRolRoles failed", re);
			throw re;
		}
	}
	
	public List<SecRolRoles> findAll(int rowStart,int rowEnd) {
		log.debug("finding all SecRolRoles instances");
		try {
			String queryString = "from SecRolRoles order by rolNombre desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed from SecRolRoles", re);
			throw re;
		}
	}
}