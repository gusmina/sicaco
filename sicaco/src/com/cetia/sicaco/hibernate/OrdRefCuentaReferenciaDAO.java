package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for OrdRefCuentaReferencia entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.cetia.sicaco.hibernate.OrdRefCuentaReferencia
  * @author MyEclipse Persistence Tools 
 */

public class OrdRefCuentaReferenciaDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(OrdRefCuentaReferenciaDAO.class);
	//property constants
	public static final String REF_CUENTA = "refCuenta";
	public static final String REF_DESCRIPCION = "refDescripcion";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String REF_ESTADO = "refEstado";

    public OrdRefCuentaReferenciaDAO(Session session) {
		super(session);
	}

	public void save(OrdRefCuentaReferencia transientInstance) {
        log.debug("saving OrdRefCuentaReferencia instance");
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
    
	public void delete(OrdRefCuentaReferencia persistentInstance) {
        log.debug("deleting OrdRefCuentaReferencia instance");
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
    
    public OrdRefCuentaReferencia findById( java.lang.Integer id) {
        log.debug("getting OrdRefCuentaReferencia instance with id: " + id);
        try {
            OrdRefCuentaReferencia instance = (OrdRefCuentaReferencia) getSession()
                    .get("com.cetia.sicaco.hibernate.OrdRefCuentaReferencia", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(OrdRefCuentaReferencia instance) {
        log.debug("finding OrdRefCuentaReferencia instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.cetia.sicaco.hibernate.OrdRefCuentaReferencia")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding OrdRefCuentaReferencia instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from OrdRefCuentaReferencia as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByRefCuenta(Object refCuenta
	) {
		return findByProperty(REF_CUENTA, refCuenta
		);
	}
	
	public List findByRefDescripcion(Object refDescripcion
	) {
		return findByProperty(REF_DESCRIPCION, refDescripcion
		);
	}
	
	public List findByAudUsuarioCreacion(Object audUsuarioCreacion
	) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion
		);
	}
	
	public List findByAudUsuarioModificacion(Object audUsuarioModificacion
	) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion
		);
	}
	
	public List findByRefEstado(Object refEstado
	) {
		return findByProperty(REF_ESTADO, refEstado
		);
	}
	

	public List findAll() {
		log.debug("finding all OrdRefCuentaReferencia instances");
		try {
			String queryString = "from OrdRefCuentaReferencia";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public OrdRefCuentaReferencia merge(OrdRefCuentaReferencia detachedInstance) {
        log.debug("merging OrdRefCuentaReferencia instance");
        try {
            OrdRefCuentaReferencia result = (OrdRefCuentaReferencia) getSession()
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

    public void attachDirty(OrdRefCuentaReferencia instance) {
        log.debug("attaching dirty OrdRefCuentaReferencia instance");
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
    
    public void attachClean(OrdRefCuentaReferencia instance) {
        log.debug("attaching clean OrdRefCuentaReferencia instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            getSession().flush();
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public List findByProId(Integer proId) {
		log.debug("finding all OrdRefCuentaReferencia instances by proId");
		try {
			String queryString = "from OrdRefCuentaReferencia ref where ref.invProProveedor.proId = " + proId;
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public List findByProIdEstado(Integer proId,String estado) {
		log.debug("finding all OrdRefCuentaReferencia instances by proId");
		try {
			String queryString = "from OrdRefCuentaReferencia ref where ref.invProProveedor.proId = " + proId 
			+ " and ref.refEstado = '" + estado +"'";
			Query queryObject = getSession().createQuery(queryString);
	        List result = queryObject.list();
			 return result;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public OrdRefCuentaReferencia findByProIdEstado2(Integer proId,String estado) {
		log.debug("finding all OrdRefCuentaReferencia instances by proId");
		try {
			String queryString = "from OrdRefCuentaReferencia ref where ref.invProProveedor.proId = " + proId 
			+ " and ref.refEstado = '" + estado +"'";
	         Query queryObject = getSession().createQuery(queryString);
			 return (OrdRefCuentaReferencia) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public Integer firstId() {
		Integer id;
		log.debug("Consiguiendo el primer id");
		try {
			String sql = "select min(ref.refId) as refId from OrdRefCuentaReferencia ref";
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