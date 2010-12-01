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
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * FacDfaDetalleFactura entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.FacDfaDetalleFactura
 * @author MyEclipse Persistence Tools
 */

public class FacDfaDetalleFacturaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(FacDfaDetalleFacturaDAO.class);
	// property constants
	public static final String DFA_CANTIDAD = "dfaCantidad";
	public static final String DFA_DESCRIPCION = "dfaDescripcion";
	public static final String DFA_PRECIO_UNITARIO = "dfaPrecioUnitario";
	public static final String DFA_EXENTO = "dfaExento";
	public static final String DFA_PRECIO_TOTAL = "dfaPrecioTotal";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public FacDfaDetalleFacturaDAO(Session session) {
		super(session);
	}

	public void save(FacDfaDetalleFactura transientInstance) {
		log.debug("saving FacDfaDetalleFactura instance");
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

	public void delete(FacDfaDetalleFactura persistentInstance) {
		log.debug("deleting FacDfaDetalleFactura instance");
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

	public FacDfaDetalleFactura findById(
			com.cetia.sicaco.hibernate.FacDfaDetalleFacturaId id) {
		log.debug("getting FacDfaDetalleFactura instance with id: " + id);
		try {
			FacDfaDetalleFactura instance = (FacDfaDetalleFactura) getSession()
					.get("com.cetia.sicaco.hibernate.FacDfaDetalleFactura", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FacDfaDetalleFactura instance) {
		log.debug("finding FacDfaDetalleFactura instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.FacDfaDetalleFactura").add(
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
		log.debug("finding FacDfaDetalleFactura instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FacDfaDetalleFactura as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByPropertyOrderByTarId(String propertyName, Object value) {
		log.debug("finding FacDfaDetalleFactura instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FacDfaDetalleFactura as model where model."
					+ propertyName + "= ? order by model.id.invArtArticulo.invTarTipoArticulo.tarId";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	

	public List findByDfaCantidad(Object dfaCantidad) {
		return findByProperty(DFA_CANTIDAD, dfaCantidad);
	}

	public List findByDfaDescripcion(Object dfaDescripcion) {
		return findByProperty(DFA_DESCRIPCION, dfaDescripcion);
	}

	public List findByDfaPrecioUnitario(Object dfaPrecioUnitario) {
		return findByProperty(DFA_PRECIO_UNITARIO, dfaPrecioUnitario);
	}

	public List findByDfaExento(Object dfaExento) {
		return findByProperty(DFA_EXENTO, dfaExento);
	}

	public List findByDfaPrecioTotal(Object dfaPrecioTotal) {
		return findByProperty(DFA_PRECIO_TOTAL, dfaPrecioTotal);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all FacDfaDetalleFactura instances");
		try {
			String queryString = "from FacDfaDetalleFactura";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FacDfaDetalleFactura merge(FacDfaDetalleFactura detachedInstance) {
		log.debug("merging FacDfaDetalleFactura instance");
		try {
			FacDfaDetalleFactura result = (FacDfaDetalleFactura) getSession()
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

	public void attachDirty(FacDfaDetalleFactura instance) {
		log.debug("attaching dirty FacDfaDetalleFactura instance");
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

	public void attachClean(FacDfaDetalleFactura instance) {
		log.debug("attaching clean FacDfaDetalleFactura instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(id.dfaId) + 1  as dfaId from FacDfaDetalleFactura";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(0);
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