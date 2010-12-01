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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.mad.utilidades.Format;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaPrePrestamo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaPrePrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaPrePrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaPrePrestamoDAO.class);
	// property constants
	public static final String PRE_REFERENCIA = "preReferencia";
	public static final String PRE_CUOTA = "preCuota";
	public static final String PRE_MONTO_SOLICITADO = "preMontoSolicitado";
	public static final String PRE_SALDO_ACTUAL_T = "preSaldoActualT";
	public static final String PRE_INTERES_ACUMULADO = "preInteresAcumulado";
	public static final String PRE_GASTO_ABOGADO = "preGastoAbogado";
	public static final String PRE_MORA_EMBARGO = "preMoraEmbargo";
	public static final String PRE_LIQUIDO_ARECIBIR = "preLiquidoARecibir";
	public static final String PRE_NUM_CUOTA_CANCEL = "preNumCuotaCancel";
	public static final String PRE_CREDITO = "preCredito";

	public CtaPrePrestamoDAO(Session session) {
		super(session);
	}

	public void save(CtaPrePrestamo transientInstance) {
		log.debug("saving CtaPrePrestamo instance");
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

	public void delete(CtaPrePrestamo persistentInstance) {
		log.debug("deleting CtaPrePrestamo instance");
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

	public CtaPrePrestamo findById(java.lang.String id) {
		log.debug("getting CtaPrePrestamo instance with id: " + id);
		try {
			CtaPrePrestamo instance = (CtaPrePrestamo) getSession().get(
					"com.cetia.sicaco.hibernate.CtaPrePrestamo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaPrePrestamo instance) {
		log.debug("finding CtaPrePrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaPrePrestamo").add(
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
		log.debug("finding CtaPrePrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaPrePrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPreReferencia(Object preReferencia) {
		return findByProperty(PRE_REFERENCIA, preReferencia);
	}

	public List findByPreCuota(Object preCuota) {
		return findByProperty(PRE_CUOTA, preCuota);
	}

	public List findByPreMontoSolicitado(Object preMontoSolicitado) {
		return findByProperty(PRE_MONTO_SOLICITADO, preMontoSolicitado);
	}

	public List findByPreSaldoActualT(Object preSaldoActualT) {
		return findByProperty(PRE_SALDO_ACTUAL_T, preSaldoActualT);
	}

	public List findByPreInteresAcumulado(Object preInteresAcumulado) {
		return findByProperty(PRE_INTERES_ACUMULADO, preInteresAcumulado);
	}

	public List findByPreGastoAbogado(Object preGastoAbogado) {
		return findByProperty(PRE_GASTO_ABOGADO, preGastoAbogado);
	}

	public List findByPreMoraEmbargo(Object preMoraEmbargo) {
		return findByProperty(PRE_MORA_EMBARGO, preMoraEmbargo);
	}

	public List findByPreLiquidoARecibir(Object preLiquidoARecibir) {
		return findByProperty(PRE_LIQUIDO_ARECIBIR, preLiquidoARecibir);
	}

	public List findByPreNumCuotaCancel(Object preNumCuotaCancel) {
		return findByProperty(PRE_NUM_CUOTA_CANCEL, preNumCuotaCancel);
	}
	
	public List findByPreCredito(Object preCredito) {
		return findByProperty(PRE_CREDITO, preCredito);
	}

	public List findAll() {
		log.debug("finding all CtaPrePrestamo instances");
		try {
			String queryString = "from CtaPrePrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaPrePrestamo merge(CtaPrePrestamo detachedInstance) {
		log.debug("merging CtaPrePrestamo instance");
		try {
			CtaPrePrestamo result = (CtaPrePrestamo) getSession().merge(
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

	public void attachDirty(CtaPrePrestamo instance) {
		log.debug("attaching dirty CtaPrePrestamo instance");
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

	public void attachClean(CtaPrePrestamo instance) {
		log.debug("attaching clean CtaPrePrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Object findMxpIdDeInteresPendiente(Object id) {
		log.debug("finding id de interes pendiente CtaPrePrestamo instances");
		try {
			String queryString = "select max(mxp.mxpId) from CtaMxpMovimientoPrestamo mxp where mxp.ctaPrePrestamo.preId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, id);
			Object obj = queryObject.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				return value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				return new Integer(value.intValue());
			}else {
				return new Integer(0);
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Object findInteresPendiente(Object value) {
		log.debug("finding interes pendiente CtaPrePrestamo instances");
		try {
			String queryString = "from CtaMxpMovimientoPrestamo mxp where mxp.mxpId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String generarId(String caracter) {
		log.debug("Se generara un nuevo id");
		String id = null;
		try {
			Transaction tx = getSession().beginTransaction();
			String queryString = "select max(prestamo.preId) from CtaPrePrestamo as prestamo";
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
			tx.commit();
			getSession().flush();
			getSession().clear();
			return id;
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
	}
	
	public String generarId2(String caracter) {
		log.debug("Se generara un nuevo id");
		String id = null;
		try {
			Transaction tx = getSession().beginTransaction();
			String queryString = "select max(prestamo.preId) from CtaPrePrestamo as prestamo";
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
					id= hoy + System.currentTimeMillis();
				}
			}else{
				id = sdf.format(new Date())+System.currentTimeMillis();//si el ultimo id viene nulo...
			}
			id = caracter+id;
			tx.commit();
			getSession().flush();
			getSession().clear();
			return id;
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
	}
	
	
	public List findTotalInteresesPorAsoc(Integer anio) {
		try{/*se modifico con el fin de sumar todo lo de todos los prestamos*/
			String sql ="SELECT cas.asc_Id as ASO_ID, SUM(mxp.mxp_Interes_Acumulado + mxp.mxp_Interes_Pendiente) AS INTERESES"+
					" FROM Cta_Cas_Cuenta_Asociado cas, Cta_Pre_Prestamo pre, Cta_Mxp_Movimiento_Prestamo mxp "+  
					" WHERE 	cas.pre_Id = pre.pre_Id AND pre.pre_Id=mxp.pre_Id "+ 
						" AND YEAR(mxp.mxp_Fecha)=? AND (mxp.mxp_Interes_Acumulado >0 OR mxp.mxp_Interes_Pendiente>0) "+
						" AND cas.asc_Id in "+
							" (SELECT distinct cas1.asc_Id AS ASC_ID"+
							"  FROM"+
								" Cta_Ttr_Tipo_Transaccion ttr INNER JOIN Cta_Txa_Transaccionxcuenta_Asociado txa ON ttr.ttr_Id = txa.ttr_Id"+
								" INNER JOIN Cta_Cas_Cuenta_Asociado cas1 ON txa.cas_Cuenta = cas1.cas_Cuenta"+
							"  where ttr.ttr_Uso='A' and cas1.cah_Id is not null and cas1.cah_Id like 'A%' and year(txa_Fecha)=?"+
							"  GROUP BY cas1.asc_Id 	order by cas1.asc_Id) "+
						" GROUP BY cas.asc_Id";
			sql = sql.toLowerCase();
//			System.out.println("el año es: "+anio);
			SQLQuery queryObject = getSession().createSQLQuery(sql);
			queryObject.setParameter(0, anio);
			queryObject.setParameter(1, anio);
			queryObject.addScalar("ASO_ID", Hibernate.STRING);
			queryObject.addScalar("INTERESES", Hibernate.DOUBLE);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Double sumCreditoUtilizado(String ascId, String tipo) {
		log.debug("finding interes pendiente CtaPrePrestamo instances");
		try {
			String queryString ="select sum(pre.preSaldoActualT) as saldo " +
								"from CtaCasCuentaAsociado as cas " +
								"inner join cas.ctaPrePrestamo as pre " +
								"where cas.ctaAscAsociado.ascId = ? " +
								"and pre.preCredito = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			queryObject.setParameter(1, tipo);
			Object obj = queryObject.uniqueResult();
			if (obj instanceof Double) {
				Double value = (Double) obj;
				return value;
			}else {
				return new Double(0.0);
			}
			//return (Double) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public boolean notaEspecificaPorPrestamo(Long casCuenta, String campo) {
		log.debug("finding nota especifica on CtaPrePrestamo instances");
		try {
			String queryString = "select n.notId " +
					" from CtaNotNotas n " +
					" where n.casCuenta = ? " +
					" and n.notCampo = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, casCuenta);
			queryObject.setParameter(1, campo);
			Object notId = queryObject.uniqueResult();
			if(notId != null){
				return true;
			}else{
				return false;
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}