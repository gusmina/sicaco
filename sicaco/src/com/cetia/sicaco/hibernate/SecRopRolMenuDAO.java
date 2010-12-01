package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecRopRolMenu entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecRopRolMenu
 * @author MyEclipse Persistence Tools
 */

public class SecRopRolMenuDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecRopRolMenuDAO.class);
	// property constants
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public SecRopRolMenuDAO(Session session) {
		super(session);
	}

	public void save(SecRopRolMenu transientInstance) {
		log.debug("saving SecRopRolMenu instance");
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

	public void delete(SecRopRolMenu persistentInstance) {
		log.debug("deleting SecRopRolMenu instance");
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

	public SecRopRolMenu findById(
			com.cetia.sicaco.hibernate.SecRopRolMenuId id) {
		log.debug("getting SecRopRolMenu instance with id: " + id);
		try {
			SecRopRolMenu instance = (SecRopRolMenu) getSession().get(
					"com.cetia.sicaco.hibernate.SecRopRolMenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(SecRopRolMenu instance) {
		log.debug("finding SecRopRolMenu instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecRopRolMenu").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
/*	
	public List findByRol(String rolName){
		log.debug("encontrando las opciones por el rol "+rolName);
		try {
			String queryString = "from SecRopRolMenu as model where model.id.secRolRoles.rolNombre = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, rolName);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
*/
	
	@SuppressWarnings("unchecked")
	public List<SecRopRolMenu> findByRol(String rolName){
        log.debug("encontrando las opciones por el rol "+rolName);
        
        try {
              String queryString = "  FROM SecRopRolMenu as model " 
                                 + " WHERE model.id.secRolRoles.rolNombre = ? " 
                                 + "   AND model.id.secMopMenuOpcion.secMopMenuOpcion is null " 
                                 + " ORDER by model.id.secMopMenuOpcion.mopOrden";
              Query queryObject = getSession().createQuery(queryString);
              queryObject.setParameter(0, rolName);
              List result =queryObject.list(); 
              return result;
        
        } catch (RuntimeException re) {
              log.error("find findByRol", re);
              re.printStackTrace();
              throw re;
        }
	}


	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SecRopRolMenu instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecRopRolMenu as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all SecRopRolMenu instances");
		try {
			String queryString = "from SecRopRolMenu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecRopRolMenu merge(SecRopRolMenu detachedInstance) {
		log.debug("merging SecRopRolMenu instance");
		try {
			SecRopRolMenu result = (SecRopRolMenu) getSession().merge(
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

	public void attachDirty(SecRopRolMenu instance) {
		log.debug("attaching dirty SecRopRolMenu instance");
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

	public void attachClean(SecRopRolMenu instance) {
		log.debug("attaching clean SecRopRolMenu instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<SecRopRolMenu> findByCriteria(SecRopRolMenu rolMenu){
		log.debug("finding SecRopRolMenu instance by criteria");
		List<SecRopRolMenu> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecRopRolMenu.class);
		
		if (rolMenu.getId().getSecRolRoles().getRolNombre() != null && !rolMenu.getId().getSecRolRoles().getRolNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("id.secRolRoles.rolNombre", rolMenu.getId().getSecRolRoles().getRolNombre()));
		}
		if (rolMenu.getId().getSecMopMenuOpcion().getMopName()!= null && !rolMenu.getId().getSecMopMenuOpcion().getMopName().trim().equals("")) {
			criteria.add(Restrictions.like("id.secMopMenuOpcion.mopName", rolMenu.getId().getSecMopMenuOpcion().getMopName()));
		}
		
		return (List<SecRopRolMenu>)criteria.getExecutableCriteria(getSession()).list();
	}
	
	public List findAllRolNombre() {
		log.debug("finding allRolNombre SecRopRolMenu instances");
		try {
			
			String queryString = "select distinct rm.id.secRolRoles.rolNombre from SecRopRolMenu rm";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find allRolNombre failed", re);
			throw re;
		}
	}
	
	public List findAllMopName() {
		log.debug("finding allMopName SecRopRolMenu instances");
		try {
			
			String queryString = "select distinct rm.id.secMopMenuOpcion.mopName from SecRopRolMenu rm";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find allMopName failed", re);
			throw re;
		}
	}
	
	public List findByMopNameByRol(String rolId) {
		log.debug("finding allMopName SecRopRolMenu instances");
		try {
			
			String queryString = "from SecRopRolMenu rm where rm.id.secRolRoles.rolNombre = '" + rolId + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find findByMopNameByRol failed", re);
			throw re;
		}
	}

	public boolean findByRolAndOpcion(SecMopMenuOpcion menuOpcion,
			String rolName) {
		log.debug("finding allMopName SecRopRolMenu instances");
		try {
			
			String queryString = "from SecRopRolMenu rm where rm.id.secRolRoles.rolNombre = '" + rolName + "' " +
					"and rm.id.secMopMenuOpcion.mopName ='" + menuOpcion.getMopName() + "'";
			Query queryObject = getSession().createQuery(queryString);
			return !queryObject.list().isEmpty();
		} catch (RuntimeException re) {
			log.error("find findByMopNameByRol failed", re);
			throw re;
		}
	}
	
	public List<SecRopRolMenu> findAllByRol(String rolName){
        log.debug("encontrando las opciones por el rol "+rolName);
        try {
              String queryString = "  FROM SecRopRolMenu as model " 
                                 + " WHERE model.id.secRolRoles.rolNombre = ? "; 
              Query queryObject = getSession().createQuery(queryString);
              queryObject.setParameter(0, rolName);
              return queryObject.list();
              
        } catch (RuntimeException re) {
              log.error("find by property name failed", re);
              throw re;
        }
	}
}