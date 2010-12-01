package com.cetia.sicaco.hibernate;

import com.cetia.sicaco.hibernate.BaseHibernateDAO;
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
 * InvStnEstante entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.inventario.hibernate.rev.eng.InvStnEstante
 * @author MyEclipse Persistence Tools
 */

public class InvStnEstanteDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvStnEstanteDAO.class);
	// property constants
	public static final String STN_CANT_FILAS = "stnCantFilas";
	public static final String STN_CANT_COLUMNAS = "stnCantColumnas";
	public static final String STN_ESTADO = "stnEstado";
	public static final String STN_POSICION = "stnPosicion";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String STN_CODIGO = "stnCodigo";

	public InvStnEstanteDAO(Session session) {
		super(session);
	}

	public void save(InvStnEstante transientInstance) {
		log.debug("saving InvStnEstante instance");
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

	public void delete(InvStnEstante persistentInstance) {
		log.debug("deleting InvStnEstante instance");
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

	public InvStnEstante findById(java.lang.Integer id) {
		log.debug("getting InvStnEstante instance with id: " + id);
		try {
			InvStnEstante instance = (InvStnEstante) getSession().get(
					"com.cetia.sicaco.hibernate.InvStnEstante", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvStnEstante instance) {
		log.debug("finding InvStnEstante instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvStnEstante").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	public List<InvStnEstante> findByCriteria(InvStnEstante invStnEstante){
		List<InvStnEstante> invList = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(InvStnEstante.class);
		if(invStnEstante.getStnCantColumnas() != null && invStnEstante.getStnCantColumnas().toString().trim().equals("")){
			criteria.add(Restrictions.eq("stnCantColumnas", invStnEstante.getStnCantColumnas()));
		}
		if(invStnEstante.getStnCantFilas() != null && invStnEstante.getStnCantFilas().toString().trim().equals("")){
			criteria.add(Restrictions.eq("stnCantFilas", invStnEstante.getStnCantFilas()));
		}
		if (invStnEstante.getInvBodBodegas().getBodId() != null){
			criteria.add(Restrictions.eq("invBodBodegas.bodId", invStnEstante.getInvBodBodegas().getBodId()));
		}
		if (invStnEstante.getStnEstado() != null){
			criteria.add(Restrictions.eq("stnEstado", invStnEstante.getStnEstado()));
		}
		if (invStnEstante.getStnPosicion() != null && invStnEstante.getStnPosicion().trim().equals("")){
			criteria.add(Restrictions.eq("stnPosicion", invStnEstante.getStnPosicion()));
		}
		
		invList = criteria.getExecutableCriteria(getSession()).list();
		return invList;
	}
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding InvStnEstante instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvStnEstante as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStnCantFilas(Object stnCantFilas) {
		return findByProperty(STN_CANT_FILAS, stnCantFilas);
	}

	public List findByStnCantColumnas(Object stnCantColumnas) {
		return findByProperty(STN_CANT_COLUMNAS, stnCantColumnas);
	}

	public List findByStnEstado(Object stnEstado) {
		return findByProperty(STN_ESTADO, stnEstado);
	}

	public List findByStnPosicion(Object stnPosicion) {
		return findByProperty(STN_POSICION, stnPosicion);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}
	
	public List findByStnCodigo(Object stnCodigo){
		return findByProperty(STN_CODIGO,stnCodigo);
	}

	public List findAll() {
		log.debug("finding all InvStnEstante instances");
		try {
			String queryString = "from InvStnEstante";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvStnEstante merge(InvStnEstante detachedInstance) {
		log.debug("merging InvStnEstante instance");
		try {
			InvStnEstante result = (InvStnEstante) getSession().merge(
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

	public void attachDirty(InvStnEstante instance) {
		log.debug("attaching dirty InvStnEstante instance");
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

	public void attachClean(InvStnEstante instance) {
		log.debug("attaching clean InvStnEstante instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByBodega(Integer bodId) {
		log.debug("finding all InvStnEstante instances by bodega");
		try {
			String queryString = "from InvStnEstante stn where stn.invBodBodegas.bodId =" + bodId;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCount() {
		log.debug("counting all rows of InvStnEstante");
		try {
			String queryString = "select count(i.stnId) from InvStnEstante i";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("count all InvStnEstante failed", re);
			throw re;
		}
	}
	
	public List<InvStnEstante> findAll(int rowStart,int rowEnd) {
		log.debug("finding all InvStnEstante instances");
		try {
			String queryString = "from InvStnEstante";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}