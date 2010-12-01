package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 	* A data access object (DAO) providing persistence and search support for OrdOpaOrdenDePago entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.cetia.sicaco.hibernate.OrdOpaOrdenDePago
  * @author MyEclipse Persistence Tools 
 */

public class OrdOpaOrdenDePagoDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(OrdOpaOrdenDePagoDAO.class);
	//property constants
	public static final String OPA_CODIGO = "opaCodigo";
	public static final String OPA_TOTAL = "opaTotal";
	public static final String OPA_DESCUENTO = "opaDescuento";
	public static final String OPA_TIPO_PAGO = "opaTipoPago";
	public static final String OPA_ESTADO = "opaEstado";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

    public OrdOpaOrdenDePagoDAO(Session session) {
		super(session);
	}

	public void save(OrdOpaOrdenDePago transientInstance) {
        log.debug("saving OrdOpaOrdenDePago instance");
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
    
	public void delete(OrdOpaOrdenDePago persistentInstance) {
        log.debug("deleting OrdOpaOrdenDePago instance");
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
    
	public OrdOpaOrdenDePago findById(java.lang.Integer id) {
		log.debug("getting OrdOpaOrdenDePago instance with id: " + id);
		try {
			OrdOpaOrdenDePago instance = (OrdOpaOrdenDePago) getSession().get(
					"com.cetia.sicaco.hibernate.OrdOpaOrdenDePago", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
    
    public List findByExample(OrdOpaOrdenDePago instance) {
        log.debug("finding OrdOpaOrdenDePago instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.cetia.sicaco.hibernate.OrdOpaOrdenDePago")
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
      log.debug("finding OrdOpaOrdenDePago instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from OrdOpaOrdenDePago as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByOpaCodigo(Object opaCodigo
	) {
		return findByProperty(OPA_CODIGO, opaCodigo
		);
	}
	
	public List findByOpaTotal(Object opaTotal
	) {
		return findByProperty(OPA_TOTAL, opaTotal
		);
	}
	
	public List findByOpaDescuento(Object opaDescuento
	) {
		return findByProperty(OPA_DESCUENTO, opaDescuento
		);
	}
	
	public List findByOpaTipoPago(Object opaTipoPago
	) {
		return findByProperty(OPA_TIPO_PAGO, opaTipoPago
		);
	}
	
	public List findByOpaEstado(Object opaEstado
	) {
		return findByProperty(OPA_ESTADO, opaEstado
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
	

	public List findAll() {
		log.debug("finding all OrdOpaOrdenDePago instances");
		try {
			String queryString = "from OrdOpaOrdenDePago order by opaFechaPago desc";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public OrdOpaOrdenDePago merge(OrdOpaOrdenDePago detachedInstance) {
        log.debug("merging OrdOpaOrdenDePago instance");
        try {
            OrdOpaOrdenDePago result = (OrdOpaOrdenDePago) getSession()
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

    public void attachDirty(OrdOpaOrdenDePago instance) {
        log.debug("attaching dirty OrdOpaOrdenDePago instance");
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
    
    public void attachClean(OrdOpaOrdenDePago instance) {
        log.debug("attaching clean OrdOpaOrdenDePago instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            getSession().flush();
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public Integer nextCod(int restart) {
		Integer cod;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select op.opaCodigo + 1  as opaCodigo from OrdOpaOrdenDePago op" +
					" where op.opaId = (select max(opaId) from OrdOpaOrdenDePago" +
					"					where opaEstado <> 'N')";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				if(restart != 1){
					cod = value;
				}else{
					cod = new Integer(1);
				}
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				cod = new Integer(value.intValue());
			}else {
				cod = new Integer(1);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return cod;
	} 
    
    public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(op.opaId) + 1  as opaId from OrdOpaOrdenDePago op";
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
    
    public List findByProCodigo(String proCodigo) {
		log.debug("finding all OrdOpaOrdenDePago instances by proCodigo");
		try {
			String queryString = "from OrdOpaOrdenDePago op where op.invProProveedor.proCodigo = '" + proCodigo + "' AND op.opaEstado ='C'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    @SuppressWarnings("unchecked")
	public List<OrdOpaOrdenDePago> findByCriteria(OrdOpaOrdenDePago pago){
		log.debug("finding OrdOpaOrdenDePago instance by criteria");
		List<OrdOpaOrdenDePago> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OrdOpaOrdenDePago.class);
		if(pago.getOpaCodigo() != null && !pago.getOpaCodigo().equals("") && pago.getOpaCodigo() != 0){
			criteria.add(Restrictions.like("opaCodigo",pago.getOpaCodigo()));
		}
		if(pago.getInvProProveedor().getProId() != null && !pago.getInvProProveedor().getProId().equals("")){
			criteria.add(Restrictions.like("invProProveedor.proId",pago.getInvProProveedor().getProId()));
		}
		if(pago.getOpaFechaPago() != null && !pago.getOpaFechaPago().equals("")){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.opa_fecha_pago,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(pago.getOpaFechaPago())
					,Hibernate.STRING
			));
		}
		lst = (List<OrdOpaOrdenDePago>)criteria.getExecutableCriteria(getSession()).list();
		return lst;
	}

	public List findAllBut(String estado) {
		log.debug("finding all OrdOpaOrdenDePago instances");
		try {
			String queryString = "from OrdOpaOrdenDePago " +
					"where opaEstado <> '"+ estado +"' order by opaFechaPago desc ";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}