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
 * SecIseInicioSesion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecIseInicioSesion
 * @author MyEclipse Persistence Tools
 */

public class SecIseInicioSesionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecIseInicioSesionDAO.class);
	// property constants
	public static final String ISE_CONTRASENIA = "iseContrasenia";
	public static final String ISE_ULTIMA_IP = "iseUltimaIp";
	public static final String ISE_VECES_UTILIZADO = "iseVecesUtilizado";
	public static final String ISE_PORQUE_INACTIVACION = "isePorqueInactivacion";
	public static final String ISE_TIPO_SESION = "iseTipoSesion";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public SecIseInicioSesionDAO(Session session) {
		super(session);
	}

	public void save(SecIseInicioSesion transientInstance) {
		log.debug("saving SecIseInicioSesion instance");
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

	public void delete(SecIseInicioSesion persistentInstance) {
		log.debug("deleting SecIseInicioSesion instance");
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

	public SecIseInicioSesion findById(java.lang.String id) {
		log.debug("getting SecIseInicioSesion instance with id: " + id);
		try {
			SecIseInicioSesion instance = (SecIseInicioSesion) getSession()
					.get("com.cetia.sicaco.hibernate.SecIseInicioSesion",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecIseInicioSesion instance) {
		log.debug("finding SecIseInicioSesion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecIseInicioSesion").add(
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
		log.debug("finding SecIseInicioSesion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecIseInicioSesion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public SecIseInicioSesion findActiveSesionByPersona(SecPerPersona secPerPersona){
		SecIseInicioSesion secIseInicioSesion = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecIseInicioSesion.class);
			if(secPerPersona.getPerId() != null && !secPerPersona.getPerId().trim().equals("")){
				criteria.add(Restrictions.eq("secPerPersona.perId", secPerPersona.getPerId()));
				criteria.add(Restrictions.isNull("iseFechaInactivacion"));
			}
		secIseInicioSesion = (SecIseInicioSesion)criteria.getExecutableCriteria(getSession()).uniqueResult();
		return secIseInicioSesion;
	}
	
	@SuppressWarnings("unchecked")
	public List<SecIseInicioSesion> findActiveSesionByPersonaList(SecPerPersona secPerPersona){
		List<SecIseInicioSesion> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecIseInicioSesion.class);
			if(secPerPersona.getPerId() != null && !secPerPersona.getPerId().trim().equals("")){
				criteria.add(Restrictions.eq("secPerPersona.perId", secPerPersona.getPerId()));
				criteria.add(Restrictions.isNull("iseFechaInactivacion"));
			}
		lst = (List<SecIseInicioSesion>)criteria.getExecutableCriteria(getSession()).list();
		return lst;
	}
	
	public List findByIseContrasenia(Object iseContrasenia) {
		return findByProperty(ISE_CONTRASENIA, iseContrasenia);
	}

	public List findByIseUltimaIp(Object iseUltimaIp) {
		return findByProperty(ISE_ULTIMA_IP, iseUltimaIp);
	}

	public List findByIseVecesUtilizado(Object iseVecesUtilizado) {
		return findByProperty(ISE_VECES_UTILIZADO, iseVecesUtilizado);
	}

	public List findByIsePorqueInactivacion(Object isePorqueInactivacion) {
		return findByProperty(ISE_PORQUE_INACTIVACION, isePorqueInactivacion);
	}

	public List findByIseTipoSesion(Object iseTipoSesion) {
		return findByProperty(ISE_TIPO_SESION, iseTipoSesion);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all SecIseInicioSesion instances");
		try {
			String queryString = "from SecIseInicioSesion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecIseInicioSesion merge(SecIseInicioSesion detachedInstance) {
		log.debug("merging SecIseInicioSesion instance");
		try {
			SecIseInicioSesion result = (SecIseInicioSesion) getSession()
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

	public void attachDirty(SecIseInicioSesion instance) {
		log.debug("attaching dirty SecIseInicioSesion instance");
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

	public void attachClean(SecIseInicioSesion instance) {
		log.debug("attaching clean SecIseInicioSesion instance");
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