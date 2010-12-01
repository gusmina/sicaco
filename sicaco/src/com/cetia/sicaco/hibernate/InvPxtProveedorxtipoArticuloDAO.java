package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.List;
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
 * InvClaClasificado entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticulo
 * @author MyEclipse Persistence Tools
 */

public class InvPxtProveedorxtipoArticuloDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvPxtProveedorxtipoArticuloDAO.class);
	// property constants

	public InvPxtProveedorxtipoArticuloDAO(Session session) {
		super(session);
	}

	public void save(InvPxtProveedorxtipoArticulo transientInstance) {
		log.debug("saving InvPxtProveedorxtipoArticulo instance");
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

	public void delete(InvPxtProveedorxtipoArticulo persistentInstance) {
		log.debug("deleting InvPxtProveedorxtipoArticulo instance");
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

	public InvClaClasificado findById(
			com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticulo id) {
		log.debug("getting InvPxtProveedorxtipoArticulo instance with id: " + id);
		try {
			InvClaClasificado instance = (InvClaClasificado) getSession().get(
					"com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticulo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvPxtProveedorxtipoArticulo instance) {
		log.debug("finding InvPxtProveedorxtipoArticulo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvPxtProveedorxtipoArticulo").add(
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
		log.debug("finding InvPxtProveedorxtipoArticulo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvPxtProveedorxtipoArticulo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all InvPxtProveedorxtipoArticulo instances");
		try {
			String queryString = "from InvPxtProveedorxtipoArticulo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvPxtProveedorxtipoArticulo merge(InvPxtProveedorxtipoArticulo detachedInstance) {
		log.debug("merging InvPxtProveedorxtipoArticulo instance");
		try {
			InvPxtProveedorxtipoArticulo result = (InvPxtProveedorxtipoArticulo) getSession().merge(
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

	public void attachDirty(InvPxtProveedorxtipoArticulo instance) {
		log.debug("attaching dirty InvPxtProveedorxtipoArticulo instance");
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

	public void attachClean(InvPxtProveedorxtipoArticulo instance) {
		log.debug("attaching clean InvPxtProveedorxtipoArticulo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
/*	
	public List<InvClaClasificado> findByCriteria(InvClaClasificado clasificado){
		//log.debug("finding allRolNombre SecRopRolMenu instances");
		log.debug("finding InvClaClasificado instance by criteria");
			
		List<InvClaClasificado> lst = null;
		//DetachedCriteria criteria = DetachedCriteria.forClass(InvClaClasificado.class);
		
		if (clasificado.getId().getInvProProveedor().getProNombre()!= null ) {
			try {
				
				String queryString = "from InvClaClasificado cl, InvProProveedor pr where pr.proId = cl.id.invProProveedor.proId";
				Query queryObject = getSession().createQuery(queryString);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find allRolNombre failed", re);
				throw re;
			}
 			//criteria.add(Restrictions.like("id.invProProveedor.proId",clasificado.getId().getInvProProveedor().getProNombre()));
		}
		/*if (clasificado.getId().getInvTclTipoClasificacion().getTclClasificacion()!= null ) {
 			//criteria.add(Restrictions.like("id.invTclTipoClasificacion.tclClasificacion",clasificado.getId().getInvTclTipoClasificacion().getTclClasificacion()));
			try {
				
				String queryString = "from InvClaClasificado cl, TclClasificacion tc where tc.tclClasificacion = cl.id.invTclTipoClasificacion.tclClasificacion";
				Query queryObject = getSession().createQuery(queryString);
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find allRolNombre failed", re);
				throw re;
			}
		}*/
/*		
		return lst;//(List<InvClaClasificado>)criteria.getExecutableCriteria(getSession()).list();
	}
	*/
	public List findByProId(Integer proId) {
		log.debug("finding all InvPxtProveedorxtipoArticulo instances by proId");
		try {
			String queryString = "from InvPxtProveedorxtipoArticulo pxt where pxt.invProProveedor.proId = " + proId;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByProAndTar(InvPxtProveedorxtipoArticulo pxt) {
		log.debug("finding all InvPxtProveedorxtipoArticulo instances by proId");
		try {
			String queryString = "from InvPxtProveedorxtipoArticulo pxt where pxt.invProProveedor.proId = " + pxt.getInvProProveedor().getProId() +
				" AND pxt.invTarTipoArticulo.tarId='" + pxt.getInvTarTipoArticulo().getTarId() + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}