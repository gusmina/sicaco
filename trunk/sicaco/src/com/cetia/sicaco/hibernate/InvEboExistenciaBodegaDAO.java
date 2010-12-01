package com.cetia.sicaco.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvEboExistenciaBodega entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvEboExistenciaBodega
 * @author MyEclipse Persistence Tools
 */

public class InvEboExistenciaBodegaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvEboExistenciaBodegaDAO.class);
	// property constants
	public static final String EBO_CANTIDAD_PRODUCTO = "eboCantidadProducto";
	public static final String EBO_SALDO = "eboSaldo";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvEboExistenciaBodegaDAO(Session session) {
		super(session);
	}

	public void save(InvEboExistenciaBodega transientInstance) {
		log.debug("saving InvEboExistenciaBodega instance");
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

	public void delete(InvEboExistenciaBodega persistentInstance) {
		log.debug("deleting InvEboExistenciaBodega instance");
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

	public InvEboExistenciaBodega findById(
			com.cetia.sicaco.hibernate.InvEboExistenciaBodegaId id) {
		log.debug("getting InvEboExistenciaBodega instance with id: " + id);
		try {
			InvEboExistenciaBodega instance = (InvEboExistenciaBodega) getSession()
					.get("com.cetia.sicaco.hibernate.InvEboExistenciaBodega",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvEboExistenciaBodega instance) {
		log.debug("finding InvEboExistenciaBodega instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvEboExistenciaBodega").add(
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
		log.debug("finding InvEboExistenciaBodega instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvEboExistenciaBodega as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEboCantidadProducto(Object eboCantidadProducto) {
		return findByProperty(EBO_CANTIDAD_PRODUCTO, eboCantidadProducto);
	}

	public List findByEboSaldo(Object eboSaldo) {
		return findByProperty(EBO_SALDO, eboSaldo);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvEboExistenciaBodega instances");
		try {
			String queryString = "from InvEboExistenciaBodega";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvEboExistenciaBodega merge(InvEboExistenciaBodega detachedInstance) {
		log.debug("merging InvEboExistenciaBodega instance");
		try {
			InvEboExistenciaBodega result = (InvEboExistenciaBodega) getSession()
					.merge(detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvEboExistenciaBodega instance) {
		log.debug("attaching dirty InvEboExistenciaBodega instance");
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

	public void attachClean(InvEboExistenciaBodega instance) {
		log.debug("attaching clean InvEboExistenciaBodega instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			getSession().clear();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<InvEboExistenciaBodega> findByArticulo(int bodega, InvArtArticulo articulo) {
		log.debug("finding all InvEboExistenciaBodega instances");
		String queryString=null;
		try {
			if(articulo.getArtCodigo() != null && !articulo.getArtCodigo().equals("") && (articulo.getArtNombre() == null || articulo.getArtNombre().equals(""))){
				queryString = "from InvEboExistenciaBodega where id.invBodBodegas.bodId = " + bodega +
					" and id.invPexProductosExistencia.invArtArticulo.artCodigo like '%" + articulo.getArtCodigo() + "%'";
			}
			if((articulo.getArtCodigo() == null || articulo.getArtCodigo().equals("")) && articulo.getArtNombre() != null && !articulo.getArtNombre().equals("")){
				queryString = "from InvEboExistenciaBodega where id.invBodBodegas.bodId = " + bodega +
					" and id.invPexProductosExistencia.invArtArticulo.artNombre like '%" + articulo.getArtNombre() + "%'";
			}
			if(articulo.getArtCodigo() != null && !articulo.getArtCodigo().equals("") && articulo.getArtNombre() != null && !articulo.getArtNombre().equals("")){
				queryString = "from InvArtArticulo where artNombre like '%" + articulo.getArtNombre() + "%'" +
					" and artCodigo like '%" + articulo.getArtCodigo() + "%'";
				Query queryObject2 = getSession().createQuery(queryString);
				List x = queryObject2.list();
				List bods = findByBodega(bodega);
				ArrayList tmp = new ArrayList();
				for (Iterator iterator = bods.iterator(); iterator.hasNext();) {
					InvEboExistenciaBodega ebo = (InvEboExistenciaBodega) iterator.next();
					for (Iterator iterator2 = x.iterator(); iterator2.hasNext();) {
						InvArtArticulo articulo2 = (InvArtArticulo) iterator2.next();
						if(ebo.getId().getInvPexProductosExistencia().getArtCodigo().equals(articulo2.getArtCodigo())){
							tmp.add(ebo);
						}
					}
				}
				return tmp;
			}
			if((articulo.getArtCodigo() == null || articulo.getArtCodigo().equals("")) && (articulo.getArtNombre() == null || articulo.getArtNombre().equals(""))){
				InvEboExistenciaBodega existenciaBodega = new InvEboExistenciaBodega();
				ArrayList<InvEboExistenciaBodega> tmp = null;
				tmp.add(existenciaBodega);
				return tmp;
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByBodega(Integer bodega) {
		log.debug("finding all InvEboExistenciaBodega instances");
		try {
			String queryString = "from InvEboExistenciaBodega where id.invBodBodegas.bodId = " + bodega;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer existenciaPorBodegaYArticulo(Integer bodega, String articulo) {
		log.debug("finding all InvEboExistenciaBodega instances");
		try {
			String queryString = "select ebo.eboCantidadProducto from InvEboExistenciaBodega ebo " +
								 "where ebo.id.invBodBodegas.bodId = " + bodega + " " +
								 "and ebo.id.invPexProductosExistencia.artCodigo = " + articulo;
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/* MODIFICACIONES PARA PAGINACION */
	public Integer getTotalRowCount(Integer bodega) {
		log.debug("counting all rows of InvEboExistenciaBodega");
		try {
			//String queryString = "select count(p.perId)from SecPerPersona p";
			String queryString = "select count(id.invBodBodegas.bodId)from InvEboExistenciaBodega where id.invBodBodegas.bodId = " + bodega;
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByBodega(Integer bodega, int rowStart,int rowEnd) {
		log.debug("finding all InvEboExistenciaBodega instances");
		try {
			String queryString = "from InvEboExistenciaBodega where id.invBodBodegas.bodId = " + bodega;
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