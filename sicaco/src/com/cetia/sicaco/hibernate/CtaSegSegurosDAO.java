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
 * CtaSegSeguros entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaSegSeguros
 * @author MyEclipse Persistence Tools
 */

public class CtaSegSegurosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaSegSegurosDAO.class);
	// property constants
	public static final String SEG_CERTIFICADO = "segCertificado";
	public static final String SEG_CARNET = "segCarnet";
	public static final String SEG_MONTO = "segMonto";
	public static final String SEG_SALDO_ACTUAL = "segSaldoActual";
	public static final String SEG_REFERENCIA = "segReferencia";
	public static final String SEG_CUOTA = "segCuota";
	public static final String SEG_SALDO_INI = "segSaldoIni";
	public static final String SEG_NUM_CUOTA_CANCEL = "segNumCuotaCancel";

	public CtaSegSegurosDAO(Session session) {
		super(session);
	}

	public void save(CtaSegSeguros transientInstance) {
		log.debug("saving CtaSegSeguros instance");
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

	public void delete(CtaSegSeguros persistentInstance) {
		log.debug("deleting CtaSegSeguros instance");
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

	public CtaSegSeguros findById(java.lang.String id) {
		log.debug("getting CtaSegSeguros instance with id: " + id);
		try {
			CtaSegSeguros instance = (CtaSegSeguros) getSession().get(
					"com.cetia.sicaco.hibernate.CtaSegSeguros", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaSegSeguros instance) {
		log.debug("finding CtaSegSeguros instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaSegSeguros").add(
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
		log.debug("finding CtaSegSeguros instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaSegSeguros as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySegCertificado(Object segCertificado) {
		return findByProperty(SEG_CERTIFICADO, segCertificado);
	}

	public List findBySegCarnet(Object segCarnet) {
		return findByProperty(SEG_CARNET, segCarnet);
	}

	public List findBySegMonto(Object segMonto) {
		return findByProperty(SEG_MONTO, segMonto);
	}

	public List findBySegSaldoActual(Object segSaldoActual) {
		return findByProperty(SEG_SALDO_ACTUAL, segSaldoActual);
	}

	public List findBySegReferencia(Object segReferencia) {
		return findByProperty(SEG_REFERENCIA, segReferencia);
	}

	public List findBySegCuota(Object segCuota) {
		return findByProperty(SEG_CUOTA, segCuota);
	}

	public List findBySegSaldoIni(Object segSaldoIni) {
		return findByProperty(SEG_SALDO_INI, segSaldoIni);
	}

	public List findBySegNumCuotaCancel(Object segNumCuotaCancel) {
		return findByProperty(SEG_NUM_CUOTA_CANCEL, segNumCuotaCancel);
	}

	public List findAll() {
		log.debug("finding all CtaSegSeguros instances");
		try {
			String queryString = "from CtaSegSeguros";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaSegSeguros merge(CtaSegSeguros detachedInstance) {
		log.debug("merging CtaSegSeguros instance");
		try {
			CtaSegSeguros result = (CtaSegSeguros) getSession().merge(
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

	public void attachDirty(CtaSegSeguros instance) {
		log.debug("attaching dirty CtaSegSeguros instance");
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

	public void attachClean(CtaSegSeguros instance) {
		log.debug("attaching clean CtaSegSeguros instance");
		try {
			getSession().lock(instance, LockMode.NONE);
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
			String queryString = "select max(seguros.segId) from CtaSegSeguros as seguros";
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

	public CtaSegSeguros findByCasCuenta(Long casCuenta) {
		log.debug("finding all CtaSegSeguros instances");
		try {
			CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSession());
			CtaCasCuentaAsociado cuentaAsociado = cuentaAsociadoDAO.findById(casCuenta);
			String queryString = "from CtaSegSeguros seg where seg.segId = '" + cuentaAsociado.getCtaSegSeguros().getSegId() + "'";
			Query queryObject = getSession().createQuery(queryString);
			return (CtaSegSeguros) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}