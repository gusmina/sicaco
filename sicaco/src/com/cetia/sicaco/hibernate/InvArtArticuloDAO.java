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
 * InvArtArticulo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvArtArticulo
 * @author MyEclipse Persistence Tools
 */

public class InvArtArticuloDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvArtArticuloDAO.class);
	// property constants
	public static final String ART_NOMBRE = "artNombre";
	public static final String ART_PORCENTAJE_UTILIDAD = "artPorcentajeUtilidad";
	public static final String ART_PRECIO_SUGERIDO = "artPrecioSugerido";
	public static final String ART_PRECIO_MINIMO = "artPrecioMinimo";
	public static final String ART_EXCENTO_IVA = "artExcentoIva";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvArtArticuloDAO(Session session) {
		super(session);
	}

	public void save(InvArtArticulo transientInstance) {
		log.debug("saving InvArtArticulo instance");
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

	public void delete(InvArtArticulo persistentInstance) {
		log.debug("deleting InvArtArticulo instance");
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

	public InvArtArticulo findById(java.lang.String id) {
		log.debug("getting InvArtArticulo instance with id: " + id);
		try {
			InvArtArticulo instance = (InvArtArticulo) getSession().get(
					"com.cetia.sicaco.hibernate.InvArtArticulo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvArtArticulo instance) {
		log.debug("finding InvArtArticulo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvArtArticulo").add(
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
		log.debug("finding InvArtArticulo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvArtArticulo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByArtNombre(Object artNombre) {
		return findByProperty(ART_NOMBRE, artNombre);
	}

	public List findByArtPorcentajeUtilidad(Object artPorcentajeUtilidad) {
		return findByProperty(ART_PORCENTAJE_UTILIDAD, artPorcentajeUtilidad);
	}

	public List findByArtPrecioSugerido(Object artPrecioSugerido) {
		return findByProperty(ART_PRECIO_SUGERIDO, artPrecioSugerido);
	}

	public List findByArtPrecioMinimo(Object artPrecioMinimo) {
		return findByProperty(ART_PRECIO_MINIMO, artPrecioMinimo);
	}

	public List findByArtExcentoIva(Object artExcentoIva) {
		return findByProperty(ART_EXCENTO_IVA, artExcentoIva);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvArtArticulo instances");
		try {
			String queryString = "from InvArtArticulo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<InvArtArticulo> findByCriteria(InvArtArticulo articulo){
		log.debug("finding InvArtArticulo instance by criteria");
		List<InvArtArticulo> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(InvArtArticulo.class);
		if (articulo.getArtCodigo()!= null && !articulo.getArtCodigo().trim().equals("")) {
 			criteria.add(Restrictions.like("artCodigo", "%" + articulo.getArtCodigo() + "%"));
		}
		if (articulo.getArtNombre()!= null && !articulo.getArtNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("artNombre", "%" + articulo.getArtNombre() + "%"));
		}
		if (articulo.getInvLinLinea().getLinId()!= null && articulo.getInvLinLinea().getLinId() != 0) {
 			criteria.add(Restrictions.eq("invLinLinea.linId", articulo.getInvLinLinea().getLinId()));
		}
		if (articulo.getInvMedMedida().getMedId()!= null && !articulo.getInvMedMedida().getMedId().trim().equals("")) {
 			criteria.add(Restrictions.eq("invMedMedida.medId", articulo.getInvMedMedida().getMedId()));
		}
		if (articulo.getInvTarTipoArticulo().getTarId()!= null && !articulo.getInvTarTipoArticulo().getTarId().trim().equals("")){
 			criteria.add(Restrictions.eq("invTarTipoArticulo.tarId", articulo.getInvTarTipoArticulo().getTarId()));
		}
		return (List<InvArtArticulo>)criteria.getExecutableCriteria(getSession()).list();
	}
	
	public List<InvArtArticulo> findByCriteria2(InvArtArticulo articulo, Integer max){
		log.debug("finding InvArtArticulo instance by criteria");
		List<InvArtArticulo> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(InvArtArticulo.class);
		if (articulo.getArtCodigo()!= null && !articulo.getArtCodigo().trim().equals("")) {
 			criteria.add(Restrictions.like("artCodigo", "%" + articulo.getArtCodigo() + "%"));
		}
		if (articulo.getArtNombre()!= null && !articulo.getArtNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("artNombre", "%" + articulo.getArtNombre() + "%"));
		}
		return (List<InvArtArticulo>)criteria.getExecutableCriteria(getSession()).setMaxResults(max).list();
	}

	public InvArtArticulo merge(InvArtArticulo detachedInstance) {
		log.debug("merging InvArtArticulo instance");
		try {
			InvArtArticulo result = (InvArtArticulo) getSession().merge(
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

	public void attachDirty(InvArtArticulo instance) {
		log.debug("attaching dirty InvArtArticulo instance");
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

	public void attachClean(InvArtArticulo instance) {
		log.debug("attaching clean InvArtArticulo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public String nextId(String linea) {
		String id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select substring(max(oc.artCodigo) + 1,5)  as artCodigo from InvArtArticulo oc where oc.artCodigo like '" + linea + "%'";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			/*if (obj instanceof Integer) {
				String value = obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(0);
			}*/
			if(obj == null){
				id = "001";
			}else{
				id = obj.toString();
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	} 
	
	public List findByArtCodigo(String artCodigo) {
		log.debug("finding all InvArtArticulo instances");
		try {
			String queryString = "from InvArtArticulo art where art.artCodigo ='" + artCodigo + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByLinea(Integer linId) {
		log.debug("finding all InvArtArticulo instances");
		try {
			String queryString = "from InvArtArticulo art where art.invLinLinea.linId=" + linId;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/*
	public List<InvArtArticulo> levantamientoInventario(){
		log.debug("finding InvArtArticulo instance by criteria");
		List<InvArtArticulo> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(InvArtArticulo.class);
		criteria.add(Restrictions.sqlRestriction(
			"SELECT "+
			     "art.ART_CODIGO, "+
			     "art.ART_NOMBRE, "+
			     "med.MED_NOMBRE_MEDIDA, "+
			     "pex.PEX_CANTIDAD_PRODUCTO, "+
			     "lin.LIN_NOMBRE "+
			"FROM "+
			     "inv_med_medida med INNER JOIN inv_art_articulo art ON med.MED_ID = art.MED_ID "+
			     "INNER JOIN inv_pex_productos_existencia pex ON art.ART_CODIGO = pex.ART_CODIGO "+
			    "INNER JOIN inv_lin_linea lin ON art.LIN_ID = lin.LIN_ID "+
			"ORDER BY lin.LIN_NOMBRE"
		));
		return (List<InvArtArticulo>)criteria.getExecutableCriteria(getSession()).list();
	}*/
	
	/* MODIFICACIONES PARA PAGINACION*/
	public Integer getTotalRowCount() {
		log.debug("counting all rows of InvArtArticulo");
		try {
			String queryString = "select count(art.invLinLinea.linId)from InvArtArticulo art";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAll(int rowStart,int rowEnd) {
		log.debug("finding all InvArtArticulo instances");
		try {
			String queryString = "from InvArtArticulo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/* FIN MODIFICACIONES PARA PAGINACION*/
}