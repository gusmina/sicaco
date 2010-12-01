package com.cetia.sicaco.hibernate;

import java.util.Date;
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
 * InvBodBodegas entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvBodBodegas
 * @author MyEclipse Persistence Tools
 */

public class InvBodBodegasDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvBodBodegasDAO.class);
	// property constants
	public static final String BOD_NOMBRE = "bodNombre";
	public static final String BOD_DIRECCION = "bodDireccion";
	public static final String BOD_COMENTARIO = "bodComentario";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String BOD_ESTADO = "bodEstado";

	public InvBodBodegasDAO(Session session) {
		super(session);
	}

	public void save(InvBodBodegas transientInstance) {
		log.debug("saving InvBodBodegas instance");
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

	public void delete(InvBodBodegas persistentInstance) {
		log.debug("deleting InvBodBodegas instance");
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

	public InvBodBodegas findById(java.lang.Integer id) {
		log.debug("getting InvBodBodegas instance with id: " + id);
		try {
			InvBodBodegas instance = (InvBodBodegas) getSession().get(
					"com.cetia.sicaco.hibernate.InvBodBodegas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvBodBodegas instance) {
		log.debug("finding InvBodBodegas instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvBodBodegas").add(
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
		log.debug("finding InvBodBodegas instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvBodBodegas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBodNombre(Object bodNombre) {
		return findByProperty(BOD_NOMBRE, bodNombre);
	}

	public List findByBodDireccion(Object bodDireccion) {
		return findByProperty(BOD_DIRECCION, bodDireccion);
	}

	public List findByBodComentario(Object bodComentario) {
		return findByProperty(BOD_COMENTARIO, bodComentario);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findByBodEstado(Object bodEstado) {
		return findByProperty(BOD_ESTADO, bodEstado);
	}

	public List findAll() {
		log.debug("finding all InvBodBodegas instances");
		try {
			String queryString = "from InvBodBodegas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findActivas() {
		log.debug("finding all InvBodBodegas instances");
		try {
			String queryString = "from InvBodBodegas bod where bod.bodEstado='A'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public InvBodBodegas merge(InvBodBodegas detachedInstance) {
		log.debug("merging InvBodBodegas instance");
		try {
			InvBodBodegas result = (InvBodBodegas) getSession().merge(
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

	public void attachDirty(InvBodBodegas instance) {
		log.debug("attaching dirty InvBodBodegas instance");
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

	public void attachClean(InvBodBodegas instance) {
		log.debug("attaching clean InvBodBodegas instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAllButThis(int bodega1) {
		log.debug("finding all InvBodBodegas instances");
		try {
			String queryString = "from InvBodBodegas bod where bod.bodId not like " + bodega1;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/* MODIFICACIONES PARA PAGINACION */
	public Integer getTotalRowCount() {
		log.debug("counting all rows of InvBodBodegas");
		try {
			String queryString = "select count(bod.bodId)from InvBodBodegas bod";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll(int rowStart,int rowEnd) {
		log.debug("finding all InvBodBodegas instances");
		try {
			String queryString = "from InvBodBodegas";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/* FIN DE MODIFICACIONES PARA PAGINACION */
	
	public List findByNombreSucursalId(int bodId,String bodNombre,int sucId) {
		log.debug("finding all InvBodBodegas instances by nombre y sucursal");
		try {
			String queryString = "select bod.bodNombre from InvBodBodegas bod " +
				"where bod.bodId != ? and upper(bod.bodNombre) = upper(?) and bod.secSucSucursal.sucId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, bodId);
			queryObject.setParameter(1, bodNombre);
			queryObject.setParameter(2, sucId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByNombreSucursal(String bodNombre,int sucId) {
		log.debug("finding all InvBodBodegas instances by nombre y sucursal");
		try {
			String queryString = "select bod.bodNombre from InvBodBodegas bod " +
				"where upper(bod.bodNombre) = upper(?) and bod.secSucSucursal.sucId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, bodNombre);
			queryObject.setParameter(1, sucId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}