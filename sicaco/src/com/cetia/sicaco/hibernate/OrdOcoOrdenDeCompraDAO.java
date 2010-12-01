package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 	* A data access object (DAO) providing persistence and search support for OrdOcoOrdenDeCompra entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.cetia.sicaco.hibernate.OrdOcoOrdenDeCompra
  * @author MyEclipse Persistence Tools 
 */

public class OrdOcoOrdenDeCompraDAO extends BaseHibernateDAO  {
    private static final Log log = LogFactory.getLog(OrdOcoOrdenDeCompraDAO.class);
	//property constants
	public static final String OCO_CODIGO = "ocoCodigo";
	public static final String OCO_MONTO = "ocoMonto";
	public static final String OCO_PAGADO = "ocoPagado";
	public static final String OCO_SALDO = "ocoSaldo";
	public static final String OCO_ELABORADO = "ocoElaborado";
	public static final String OCO_ESTADO = "ocoEstado";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String ASC_CODIGO = "ascCodigo";
	public static final String CLI_CODIGO = "cliCodigo";

    public OrdOcoOrdenDeCompraDAO(Session session) {
		super(session);
	}

	public void save(OrdOcoOrdenDeCompra transientInstance) {
        log.debug("saving OrdOcoOrdenDeCompra instance");
        try {
        	getSession().flush();
            getSession().clear();
            transientInstance.setOcoCodigo(nextId());
            transientInstance.setAudFechaModificacion(new Date());
            getSession().save(transientInstance);
            getSession().flush();
            getSession().clear();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(OrdOcoOrdenDeCompra persistentInstance) {
        log.debug("deleting OrdOcoOrdenDeCompra instance");
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
    
	public OrdOcoOrdenDeCompra findById(java.lang.Integer id) {
		log.debug("getting OrdOcoOrdenDeCompra instance with id: " + id);
		try {
			OrdOcoOrdenDeCompra instance = (OrdOcoOrdenDeCompra) getSession()
					.get("com.cetia.sicaco.hibernate.OrdOcoOrdenDeCompra", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
    
    public List findByExample(OrdOcoOrdenDeCompra instance) {
        log.debug("finding OrdOcoOrdenDeCompra instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.cetia.sicaco.hibernate.OrdOcoOrdenDeCompra")
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
      log.debug("finding OrdOcoOrdenDeCompra instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from OrdOcoOrdenDeCompra as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByOcoCodigo(Object ocoCodigo
	) {
		return findByProperty(OCO_CODIGO, ocoCodigo
		);
	}
	
	public List findByOcoMonto(Object ocoMonto
	) {
		return findByProperty(OCO_MONTO, ocoMonto
		);
	}
	
	public List findByOcoPagado(Object ocoPagado
	) {
		return findByProperty(OCO_PAGADO, ocoPagado
		);
	}
	
	public List findByOcoSaldo(Object ocoSaldo
	) {
		return findByProperty(OCO_SALDO, ocoSaldo
		);
	}
	
	public List findByOcoElaborado(Object ocoElaborado
	) {
		return findByProperty(OCO_ELABORADO, ocoElaborado
		);
	}
	
	public List findByOcoEstado(Object ocoEstado
	) {
		return findByProperty(OCO_ESTADO, ocoEstado
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
	
	public List findByAscCodigo(Object ascCodigo) {
		return findByProperty(ASC_CODIGO, ascCodigo);
	}
	
	public List findByCliCodigo(Object cliCodigo) {
		return findByProperty(CLI_CODIGO, cliCodigo);
	}
	

	public List findAll() {
		log.debug("finding all OrdOcoOrdenDeCompra instances");
		try {
			String queryString = "from OrdOcoOrdenDeCompra";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public OrdOcoOrdenDeCompra merge(OrdOcoOrdenDeCompra detachedInstance) {
        log.debug("merging OrdOcoOrdenDeCompra instance");
        try {
            OrdOcoOrdenDeCompra result = (OrdOcoOrdenDeCompra) getSession()
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

    public void attachDirty(OrdOcoOrdenDeCompra instance) {
        log.debug("attaching dirty OrdOcoOrdenDeCompra instance");
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
    
    public void attachClean(OrdOcoOrdenDeCompra instance) {
        log.debug("attaching clean OrdOcoOrdenDeCompra instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            getSession().flush();
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public Integer nextCod(Integer don, int restart) {
		Integer cod;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(oc.ocoCodigo) + 1  as ocoCodigo from OrdOcoOrdenDeCompra oc where oc.ocoDonacion = " + don 
							+ " and oc.ocoId = (select max(oc2.ocoId) from OrdOcoOrdenDeCompra oc2 where oc2.ocoDonacion = " + don +")";
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
			String sql = "select max(oc.ocoId) + 1  as ocoId from OrdOcoOrdenDeCompra oc";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(1);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	} 
    
    public List findAllD(int don, int tam) {
		log.debug("finding all OrdOcoOrdenDeCompra instances");
		try {
			String queryString = "from OrdOcoOrdenDeCompra oc where oc.ocoDonacion = " + don + " order by oc.ocoId desc";
			Query queryObject = null;
			if(tam < 1){
				queryObject = getSession().createQuery(queryString);
			}else{
				queryObject = getSession().createQuery(queryString).setMaxResults(tam);
			}
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public List findByProCodigo(int don, String proCodigo) {
		log.debug("finding all OrdOcoOrdenDeCompra instances by proCodigo");
		try {
			String queryString ="from OrdOcoOrdenDeCompra oc where oc.ocoDonacion = " + don +
								" AND oc.invProProveedor.proCodigo = '" + proCodigo + "' AND ( oc.ocoEstado ='C' " +
								"OR oc.ocoEstado ='P' OR oc.ocoEstado ='R')";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public List findByProCodigo2(String proCodigo) {
		log.debug("finding all OrdOcoOrdenDeCompra instances by proCodigo");
		try {
			String queryString ="from OrdOcoOrdenDeCompra oc " +
								"where oc.invProProveedor.proCodigo = '" + proCodigo + "' " +
								"AND (oc.ocoEstado ='C' "+
								"OR oc.ocoEstado ='P' OR oc.ocoEstado ='R')";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public List<OrdOcoOrdenDeCompra> findByCriteria(OrdOcoOrdenDeCompra compra){
		log.debug("finding OrdOcoOrdenDeCompra instance by criteria");
		List<InvProProveedor> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(InvProProveedor.class);
		if (compra.getInvProProveedor().getProCodigo()!= null && !compra.getInvProProveedor().getProCodigo().trim().equals("")) {
 			criteria.add(Restrictions.like("invProProveedor.proCodigo",compra.getInvProProveedor().getProCodigo()));
		}
	
		return (List<OrdOcoOrdenDeCompra>)criteria.getExecutableCriteria(getSession()).list();
	}

	public Double creditoUsadoPorAsociado(String ascId) {
		log.debug("finding all OrdOcoOrdenDeCompra instances by proCodigo");
		try {
			String queryString = "select sum(oc.ocoMonto) from OrdOcoOrdenDeCompra oc " +
					"where oc.ascCodigo = '" + ascId + "' " +
					"AND (oc.ocoEstado ='C' OR oc.ocoEstado ='D' OR oc.ocoEstado ='F' " +
					"OR oc.ocoEstado = 'R') " +
					"AND oc.ocoDonacion = 0";
	         Query queryObject = getSession().createQuery(queryString);
			 return (Double) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findTodasLasQueDebe(String ascId) {
		log.debug("finding all OrdOcoOrdenDeCompra instances by ascId");
		try {
			String queryString = "from OrdOcoOrdenDeCompra oc " +
					"where oc.ascCodigo = '" + ascId + "' " +
					"AND (oc.ocoEstado ='C' OR oc.ocoEstado ='D' " +
					"OR oc.ocoEstado ='F' OR oc.ocoEstado = 'R') " +
					"AND oc.ocoDonacion = 0 " +
					"ORDER BY oc.ocoId asc";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCountOrdenesByCriteria(OrdOcoOrdenDeCompra orden) {
	try {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrdOcoOrdenDeCompra.class);
		if(orden.getOcoCodigo()!= null && orden.getOcoCodigo() > 0) {
			criteria.add(Restrictions.eq("ocoCodigo",+ orden.getOcoCodigo()));
		}
		if(orden.getAscCodigo() != null && orden.getAscCodigo().length() > 0) {
			criteria.add(Restrictions.like("ascCodigo", orden.getAscCodigo()));
		}
		if(orden.getOcoDonacion()!= null && orden.getOcoDonacion() > -1) {
			criteria.add(Restrictions.like("ocoDonacion",orden.getOcoDonacion()));
		}
		if(orden.getInvProProveedor().getProId() != null && orden.getInvProProveedor().getProId() > -1) {
			criteria.add(Restrictions.like("invProProveedor.proId",orden.getInvProProveedor().getProId()));
		}
		if(orden.getOcoEmision()!=null){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.oco_emision,'%d-%m-%Y') = ?",
					 new SimpleDateFormat("dd-MM-yyyy").format(orden.getOcoEmision())
					,Hibernate.STRING
			));
		}
		criteria.setProjection(Projections.countDistinct("ocoId"));
		Integer var =(Integer) criteria.getExecutableCriteria(getSession()).uniqueResult();
		return var;
	} catch (RuntimeException re) {
		log.error("find all failed", re);
		throw re;
	}
}
	
	public List findOrdenesByCriteria(OrdOcoOrdenDeCompra orden,int rowStart,int rowEnd) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(OrdOcoOrdenDeCompra.class);
			if(orden.getOcoCodigo()!= null && orden.getOcoCodigo() > 0) {
				criteria.add(Restrictions.eq("ocoCodigo",orden.getOcoCodigo()));
			}
			if(orden.getAscCodigo() != null && orden.getAscCodigo().length() > 0) {
				criteria.add(Restrictions.like("ascCodigo", orden.getAscCodigo()));
			}
			if(orden.getOcoDonacion()!= null && orden.getOcoDonacion() > -1) {
				criteria.add(Restrictions.eq("ocoDonacion",orden.getOcoDonacion()));
			}
			if(orden.getInvProProveedor().getProId() != null && orden.getInvProProveedor().getProId() > -1) {
				criteria.add(Restrictions.eq("invProProveedor.proId",orden.getInvProProveedor().getProId()));
			}
			if(orden.getOcoEmision()!=null){
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.oco_emision,'%d-%m-%Y') = ?",
						new SimpleDateFormat("dd-MM-yyyy").format(orden.getOcoEmision())
						,Hibernate.STRING
				));
			}
			return criteria.getExecutableCriteria(getSession()).setFirstResult(rowStart).setMaxResults(rowEnd-rowStart).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Double usadoEmitidasAsociado(String ascId) {
		log.debug("total de saldo a restar por las emitidas");
		try {
			String queryString ="select sum(oc.ocoMonto) as monto " +
								"from OrdOcoOrdenDeCompra as oc " +
								"where oc.ascCodigo = ? " +
								"and oc.ocoEstado = 'E'";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			Object obj = queryObject.uniqueResult();
			if (obj instanceof Double) {
				Double value = (Double) obj;
				return value;
			}else {
				return new Double(0.0);
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}