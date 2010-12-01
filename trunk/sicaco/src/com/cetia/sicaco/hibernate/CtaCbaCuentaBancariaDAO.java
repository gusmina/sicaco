package com.cetia.sicaco.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.mad.utilidades.Format;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaCbaCuentaBancaria entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria
 * @author MyEclipse Persistence Tools
 */

public class CtaCbaCuentaBancariaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaCbaCuentaBancariaDAO.class);
	// property constants
	public static final String CBA_CUENTA = "cbaCuenta";

	public CtaCbaCuentaBancariaDAO(Session session) {
		super(session);
	}

	public void save(CtaCbaCuentaBancaria transientInstance) {
		log.debug("saving CtaCbaCuentaBancaria instance");
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

	public void delete(CtaCbaCuentaBancaria persistentInstance) {
		log.debug("deleting CtaCbaCuentaBancaria instance");
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

	public CtaCbaCuentaBancaria findById(java.lang.String id) {
		log.debug("getting CtaCbaCuentaBancaria instance with id: " + id);
		try {
			CtaCbaCuentaBancaria instance = (CtaCbaCuentaBancaria) getSession()
					.get("com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaCbaCuentaBancaria instance) {
		log.debug("finding CtaCbaCuentaBancaria instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaCbaCuentaBancaria").add(
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
		log.debug("finding CtaCbaCuentaBancaria instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaCbaCuentaBancaria as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List findByCbaCuenta(Object cbaCuenta) {
		return findByProperty(CBA_CUENTA, cbaCuenta);
	}

	public List findAll() {
		log.debug("finding all CtaCbaCuentaBancaria instances");
		try {
			String queryString = "from CtaCbaCuentaBancaria";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaCbaCuentaBancaria merge(CtaCbaCuentaBancaria detachedInstance) {
		log.debug("merging CtaCbaCuentaBancaria instance");
		try {
			CtaCbaCuentaBancaria result = (CtaCbaCuentaBancaria) getSession()
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

	public void attachDirty(CtaCbaCuentaBancaria instance) {
		log.debug("attaching dirty CtaCbaCuentaBancaria instance");
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
	
	
	public String generarId(String caracter) {
		log.debug("Se generara un nuevo id");
		String id = null;
		try {
			String queryString = "select max(cba.cbaId) from CtaCbaCuentaBancaria as cba";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Query query = getSession().createQuery(queryString);
			String ultimoId = (String) query.uniqueResult();
			if(ultimoId != null){
				String soloFecha = ultimoId.substring(1,9);
				String hoy = sdf.format(new Date());
				if(soloFecha.equals(hoy)) {
					Integer corr = (Integer.parseInt(ultimoId.substring(9, 15))+1);
				    id = hoy+Format.formatNumeroFactura(corr,6);
				}else {
					id= hoy + "000000";
				}
			}else{
				id = sdf.format(new Date())+"000000";//si el ultimo id viene nulo...
			}
			id = caracter+id;
			return id;
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
	}

	public void attachClean(CtaCbaCuentaBancaria instance) {
		log.debug("attaching clean CtaCbaCuentaBancaria instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findRepetidaByBanco(Integer banco,String cuenta) {
		try {
			String queryString = "from CtaCbaCuentaBancaria as c where c.ctrBanBanco.banId= ? " +
					"and c.cbaCuenta= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, banco);
			queryObject.setParameter(1, cuenta);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findByAsociado(String ascId) {
		try {
			String queryString = "	select cuenta.ctaCbaCuentaBancaria from CtaCasCuentaAsociado cuenta where cuenta.ctaAscAsociado.ascId = ? " +
					"and cuenta.ctaCbaCuentaBancaria is not null";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0,ascId);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Integer getTotalRowCountbyAsociado(String ascId) {
		log.debug("counting all rows of CtaCbaCuentaBancaria");
		try {
			String queryString = "	select count(cuenta.ctaCbaCuentaBancaria.cbaId) from CtaCasCuentaAsociado cuenta where cuenta.ctaAscAsociado.ascId = ? " +
			"and cuenta.ctaCbaCuentaBancaria is not null";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SecHseHistorialSesion> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaCbaCuentaBancaria h hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaCbaCuentaBancaria hse order by hse.cbaId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByAsociado(String ascId, int rowStart, int rowEnd) {
		try {
			String queryString = "select cuenta.ctaCbaCuentaBancaria from CtaCasCuentaAsociado cuenta where cuenta.ctaAscAsociado.ascId = ? " +
					"and cuenta.ctaCbaCuentaBancaria is not null";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0,ascId);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
		
	}

	public List findByAscAndBanco(String ascId2, Integer banId) {
		try {
			
			/*
			 * SELECT * FROM cta_cas_cuenta_asociado c 
inner join cta_asc_asociado a on a.asc_id = c.asc_id
inner join cta_cba_cuenta_bancaria cba on c.cba_Id=cba.cba_Id
where c.cba_id is not null
and cba.ban_id = 2
and a.asc_id = '200901142252'
			 */
			String queryString ="select cba " +
								"from CtaCasCuentaAsociado cuenta " +
								"inner join cuenta.ctaAscAsociado as a " +
								"inner join cuenta.ctaCbaCuentaBancaria as cba " +
								"where a.ascId = ? " +
								"and cba.cbaId is not null " +
								"and cba.ctrBanBanco.banId = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId2);
			queryObject.setParameter(1, banId);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}