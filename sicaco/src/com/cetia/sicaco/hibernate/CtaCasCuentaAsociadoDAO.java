package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.cetia.sicaco.cuentaCorriente.struts.form.CuentaAsociadoForm;
import com.cetia.sicaco.cuentaCorriente.struts.form.DesembolsoPrestamoForm;
import com.cetia.sicaco.cuentaCorriente.struts.form.PrestamoForm;
import com.cetia.sicaco.struts.CuentaAsociadoVista;
import com.mad.utilidades.ElapsedTime;
import com.mad.utilidades.IntereseYMora;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaCasCuentaAsociado entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaCasCuentaAsociado
 * @author MyEclipse Persistence Tools
 */

public class CtaCasCuentaAsociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaCasCuentaAsociadoDAO.class);
	// property constants
	public static final String CAS_VALOR_APERTURA = "casValorApertura";
	public static final String CAS_PRINCIPAL = "casPrincipal";
	public static final String CAS_REFINANCIADO = "casRefinanciado";
	public static final String CAS_PRESTAMO_PAGA = "casPrestamoPaga";
	public static final String CAS_PERSONA_EXTERNA = "ctaPxtPersonaExterna.pxtId";
	
	public CtaCasCuentaAsociadoDAO(Session session) {
		super(session);
	}

	public void save(CtaCasCuentaAsociado transientInstance) {
		log.debug("saving CtaCasCuentaAsociado instance");
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
	
	public void delete(CtaCasCuentaAsociado persistentInstance) {
		log.debug("deleting CtaCasCuentaAsociado instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaCasCuentaAsociado findById(java.lang.Long id) {
		log.debug("getting CtaCasCuentaAsociado instance with id: " + id);
		try {
			CtaCasCuentaAsociado instance = (CtaCasCuentaAsociado) getSession()
					.get("com.cetia.sicaco.hibernate.CtaCasCuentaAsociado", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaCasCuentaAsociado instance) {
		log.debug("finding CtaCasCuentaAsociado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaCasCuentaAsociado").add(
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
		log.debug("finding CtaCasCuentaAsociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaCasCuentaAsociado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertyEstado(String propertyName, Object value,Integer estado) {
		log.debug("finding CtaCasCuentaAsociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaCasCuentaAsociado as model where model."
					+ propertyName + "= ? and model.ctrEstEstado.estId="+estado.toString();
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByCasValorApertura(Object casValorApertura) {
		return findByProperty(CAS_VALOR_APERTURA, casValorApertura);
	}

	public List findByCasPrincipal(Object casPrincipal) {
		return findByProperty(CAS_PRINCIPAL, casPrincipal);
	}
	
	public List findByCasRefinanciado(Object casRefinanciado) {
		return findByProperty(CAS_REFINANCIADO, casRefinanciado);
	}
	
	public List findByCasPrestamoPaga(Object casPrestamoPaga) {
		return findByProperty(CAS_PRESTAMO_PAGA, casPrestamoPaga);
	}
	
	public List findByCtaPxtPersonaExterna(Object casRefinanciado) {
		return findByProperty(CAS_PERSONA_EXTERNA, casRefinanciado);
	}
	
	public List findAll() {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaCasCuentaAsociado merge(CtaCasCuentaAsociado detachedInstance) {
		log.debug("merging CtaCasCuentaAsociado instance");
		try {
			CtaCasCuentaAsociado result = (CtaCasCuentaAsociado) getSession()
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

	public void attachDirty(CtaCasCuentaAsociado instance) {
		log.debug("attaching dirty CtaCasCuentaAsociado instance");
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

	public void attachClean(CtaCasCuentaAsociado instance) {
		log.debug("attaching clean CtaCasCuentaAsociado instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAscId(Object ascId) {
		try {
			String queryString = "from CtaCahCuentaAhorro as cuenta,CtaCasCuentaAsociado as cuentaAsoc where " 
				+"cuentaAsoc.casCuenta = cuenta.cahId and cuentaAsoc.casCuenta =";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Date findFechaTransaction(Object casCuenta) {
		try {
			String queryString = "select max(txa.txaFecha)  as id from CtaTxaTransaccionxcuentaAsociado as txa"
								+"  where txa.ctaCasCuentaAsociado.casCuenta = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, casCuenta);
			Object obj = queryObject.uniqueResult();
			
			return (Date)obj;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Object findTipoTransaction(Object fechaTransac,Object casCuenta) {
		try {
			String queryString = "select max(txa.txaId) from CtaTxaTransaccionxcuentaAsociado as txa"
								+"  where txa.txaFecha = ? and txa.ctaCasCuentaAsociado.casCuenta = ?";//txa.ctaTtrTipoTransaccion.ttrNombre   max(txa.txaFecha)
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fechaTransac);
			queryObject.setParameter(1, casCuenta);
			Long txaId = (Long)queryObject.uniqueResult();
			if(txaId == null){
				return "";
			}		
			CtaTxaTransaccionxcuentaAsociadoDAO txaDAO = new CtaTxaTransaccionxcuentaAsociadoDAO(getSession());
			
			return txaDAO.findById(txaId).getCtaTtrTipoTransaccion().getTtrNombre();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaCasCuentaAsociado> findByCriteria(CuentaAsociadoForm form){
		List<CtaCasCuentaAsociado> cueList = null;
		String tipo = new String("");
		tipo = form.getTipoCuentaMadre();
		CtaCasCuentaAsociado ctaCasCuentaAsociado = form.getCtaCasCuentaAsociadoH();
		log.debug("Buscamos cuentas del asociado por campos de busqueda en formulario ");
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaCasCuentaAsociado.class);		
		CtrEstEstadoDAO ctrEstadoDAO = new CtrEstEstadoDAO(getSession());
		CtrEstEstado estado;
		if(tipo.equals("Ap")){
			
			if (form.getAscId()!= null) {
				criteria.add(Restrictions.eq("ctaAscAsociado.ascId", form.getAscId()));
			}
			if (ctaCasCuentaAsociado.getCasFechaApertura() != null) {
				criteria.add(Restrictions.eq(
						"casFechaApertura",
						ctaCasCuentaAsociado.getCasFechaApertura())
						
				);
			}
			
			estado = ctrEstadoDAO.findById(9);
			criteria.add(Restrictions.eq("ctrEstEstado.estId", estado.getEstId()));
			
			criteria.add(Restrictions.sqlRestriction("{alias}.cah_Id is not null"));
			criteria.add(Restrictions.like("ctaCahCuentaAhorro.cahId","A"+"%"));
			if(form.getReferenciaCuenta()!=null && !form.getReferenciaCuenta().trim().equals("")){
				criteria.add(Restrictions.like("ctaCahCuentaAhorro.cahId", "%"+form.getReferenciaCuenta()+"%"));
				
			}
			criteria.addOrder(Order.asc("ctaCahCuentaAhorro.cahId"));
			
		}else if(tipo.equals("Ah")){
			
			if (form.getAscId()!= null) {
				criteria.add(Restrictions.eq("ctaAscAsociado.ascId", form.getAscId()));
			}
			if (ctaCasCuentaAsociado.getCasFechaApertura() != null) {
				criteria.add(Restrictions.eq(
						"casFechaApertura",
						ctaCasCuentaAsociado.getCasFechaApertura())
						
				);
			}
			estado = ctrEstadoDAO.findById(9);
			criteria.add(Restrictions.eq("ctrEstEstado.estId", estado.getEstId()));
			criteria.add(Restrictions.sqlRestriction("{alias}.cah_Id is not null"));
			criteria.add(Restrictions.like("ctaCahCuentaAhorro.cahId","B"+"%"));
			if(form.getReferenciaCuenta()!=null && !form.getReferenciaCuenta().trim().equals("")){
				
				
				criteria.add(Restrictions.like("ctaCahCuentaAhorro.cahId", "%"+form.getReferenciaCuenta()+"%"));
				
			}
			criteria.addOrder(Order.asc("ctaCahCuentaAhorro.cahId"));
			
		}else if(tipo.equals("Pr")){

			if (form.getAscId()!= null) {
				criteria.add(Restrictions.eq("ctaAscAsociado.ascId", form.getAscId()));
			}
			if (ctaCasCuentaAsociado.getCasFechaApertura() != null) {
				criteria.add(Restrictions.eq(
						"casFechaApertura",
						ctaCasCuentaAsociado.getCasFechaApertura())
						
				);
			}
			estado = ctrEstadoDAO.findById(17);
			criteria.add(Restrictions.ne("ctrEstEstado.estId", estado.getEstId()));
			estado = ctrEstadoDAO.findById(23);//tambien significa que ya fue pagado aunque se le cobra a los fiadores
			criteria.add(Restrictions.ne("ctrEstEstado.estId", estado.getEstId()));
			estado = ctrEstadoDAO.findById(16);
			criteria.add(Restrictions.ne("ctrEstEstado.estId", estado.getEstId()));
			criteria.add(Restrictions.sqlRestriction(" {alias}.pre_Id is not null"));
			if(form.getReferenciaCuenta()!=null && !form.getReferenciaCuenta().trim().equals("")){
				
				criteria.add(Restrictions.like("ctaPrePrestamo.preId", "%"+form.getReferenciaCuenta()+"%"));
				
			}
			criteria.addOrder(Order.asc("ctaPrePrestamo.preId"));
			
		}else if(tipo.equals("Se")){
			if (form.getAscId()!= null) {
				criteria.add(Restrictions.eq("ctaAscAsociado.ascId", form.getAscId()));
			}
			if (ctaCasCuentaAsociado.getCasFechaApertura() != null) {
				criteria.add(Restrictions.eq(
						"casFechaApertura",
						ctaCasCuentaAsociado.getCasFechaApertura())
						
				);
			}
			estado = ctrEstadoDAO.findById(12);
			criteria.add(Restrictions.ne("ctrEstEstado.estId", estado.getEstId()));
			criteria.add(Restrictions.sqlRestriction("{alias}.seg_Id is not null"));
			if(form.getReferenciaCuenta()!=null && !form.getReferenciaCuenta().trim().equals("")){
				
				criteria.add(Restrictions.like("ctaSegSeguros.segId", "%"+form.getReferenciaCuenta()+"%"));
				
			}
			criteria.addOrder(Order.asc("ctaSegSeguros.segId"));
			
		}
		cueList = criteria.getExecutableCriteria(getSession()).list();
		return cueList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CtaCasCuentaAsociado> findByCamposBusqueda(CuentaAsociadoForm form) {
		List<CtaCasCuentaAsociado> resultado = null;
		String tipo = new String("");
		tipo = form.getTipoCuentaMadre();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CtaCasCuentaAsociado ctaCasCuentaAsociado = form.getCtaCasCuentaAsociadoH();
		log.debug("Buscamos cuentas del asociado por campos de busqueda en formulario ");
		String tipoH = "";
		String queryString = "";
		try {
			if(tipo.equals("Ap")){
				queryString="from CtaCasCuentaAsociado as model where 1 = 1 and model.ctaAscAsociado.ascId='"
					+form.getAscId()+"' ";
				if(ctaCasCuentaAsociado.getCasFechaApertura()!=null){
						queryString += " and model.casFechaApertura = "	+ ctaCasCuentaAsociado.getCasFechaApertura();
				}
				queryString += " and model.ctaCahCuentaAhorro.cahId is not null and substring(model.ctaCahCuentaAhorro.cahId,1,1)='A' ";
				if(form.getReferenciaCuenta()!=null) {
					queryString += " and model.ctaCahCuentaAhorro.cahId like '%"+form.getReferenciaCuenta()+"%'";
					queryString += " order by model.ctaCahCuentaAhorro.cahId";
				}
				tipoH = "Ap";
			}else if(tipo.equals("Ah")){
					queryString += " and model.ctaCahCuentaAhorro.cahId is not null and substring(model.ctaCahCuentaAhorro.cahId,1,1)='B' ";
					if(form.getReferenciaCuenta()!=null) {
						queryString += " and model.ctaCahCuentaAhorro.cahId like '%"+form.getReferenciaCuenta()+"%'";
					}
			}else if(tipo.equals("Pr")){
				queryString="from CtaCasCuentaAsociado as model where 1 = 1 and model.ctaAscAsociado.ascId='"
					+form.getAscId()+"' ";
				if(ctaCasCuentaAsociado.getCasFechaApertura()!=null){
						queryString += " and model.casFechaApertura = "	+ ctaCasCuentaAsociado.getCasFechaApertura();
				}
				queryString += " and model.ctaPrePrestamo.preId is not null";
				if(form.getReferenciaCuenta()!=null) {
					queryString += " and model.ctaPrePrestamo.preId like '%"+form.getReferenciaCuenta()+"%'";
					queryString += " order by model.ctaPrePrestamo.preId";
				}
			}else if(tipo.equals("Se")){
				queryString += " and model.ctaSegSeguros.segId is not null";
				if(form.getReferenciaCuenta()!=null) {
					queryString += " and model.ctaSegSeguros.segId like '%"+form.getReferenciaCuenta()+"%'";
					queryString += " order by model.ctaSegSeguros.segId";
				}
			}
			
			Query queryObject = getSession().createQuery(queryString);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando la cuenta del asociado, revisar campos de busqueda", e);
			throw e;
		}
		
	}
	
	public List findAllCuentasAsociado(String ascId,String tipoCuenta) {//retorna una lista de cuentas poseidas 
		// por el asociado con un tipo especifico de cuenta
		String queryString = null;
		if(!tipoCuenta.equals("Z")){
		try {
			if(tipoCuenta.equals("A")){
				queryString = "from CtaCasCuentaAsociado cuenta "+
				" where cuenta.ctaAscAsociado.ascId='" +ascId+ "' and cuenta.ctaCahCuentaAhorro.cahId is not null "+
				" and substring(cuenta.ctaCahCuentaAhorro.cahId,1,1)='A'";
			}
			if(tipoCuenta.equals("B")){
				queryString = "from CtaCasCuentaAsociado cuenta "+
				" where cuenta.ctaAscAsociado.ascId='" +ascId+ "' and cuenta.ctaCahCuentaAhorro.cahId is not null "+
				" and substring(cuenta.ctaCahCuentaAhorro.cahId,1,1)='B'";
			}
			if(tipoCuenta.equals("C")){
				queryString = "from CtaCasCuentaAsociado as cuenta "+
				" where cuenta.ctaAscAsociado.ascId='" +ascId+ "' and cuenta.ctaPrePrestamo is not null";
			}
			if(tipoCuenta.equals("D")){
				queryString = "from CtaCasCuentaAsociado as cuenta "+
				" where cuenta.ctaAscAsociado.ascId='" +ascId+ "' and cuenta.ctaSegSeguros is not null";
			}
			Query queryObject = getSession().createQuery(queryString);
			//queryObject.setParameter(0, ascId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}}else{
			return null;
		}
			
	}

	public CtaPrePrestamo findByAscIdAndValorApertura(String ascId, double i) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where cas.ctaAscAsociado.ascId ='" + ascId
				+ "' and cas.casValorApertura =" + i;
			Query queryObject = getSession().createQuery(queryString);
			if(queryObject.list().size()<1){
				return new CtaPrePrestamo();
			}else{
				for (Iterator iterator = queryObject.list().iterator(); iterator
						.hasNext();) {
					CtaCasCuentaAsociado cuentaAsociado = (CtaCasCuentaAsociado) iterator.next();
					CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSession());
					CtaPrePrestamo prestamo = (CtaPrePrestamo) prestamoDAO.findById(cuentaAsociado.getCtaPrePrestamo().getPreId());
					if(prestamo.getPreLiquidoARecibir()==0){
						return prestamo;
					}
				}
			}
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return null;
	}

	public CtaCasCuentaAsociado findByPreId(String preId) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {			
			String queryString = "from CtaCasCuentaAsociado cas where cas.ctaPrePrestamo.preId =:prestamoId";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("prestamoId", preId);//otra forma de agregar parametros nombrados, en lugar de utilizar el "?".
			return (CtaCasCuentaAsociado)queryObject.uniqueResult();
	  } catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaPrePrestamo findCredito(String ascId) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaPrePrestamo where preId = " +
								 "(select cas.ctaPrePrestamo.preId " +
								 "from CtaCasCuentaAsociado cas where cas.ctaAscAsociado.ascId = '" + ascId + "' " +
								 "and cas.ctaPrePrestamo is not null " +
								 "and cas.ctaPrePrestamo.ctaTprTipoPrestamo is null)";
			Query queryObject = getSession().createQuery(queryString);
			return (CtaPrePrestamo) queryObject.uniqueResult();
			/*if(queryObject.list().size()<1){
				return new CtaPrePrestamo();
			}else{
				for (Iterator iterator = queryObject.list().iterator(); iterator
						.hasNext();) {
					CtaCasCuentaAsociado cuentaAsociado = (CtaCasCuentaAsociado) iterator.next();
					if(!cuentaAsociado.getCasPrincipal().equals("S") && cuentaAsociado.getCtaPrePrestamo()!=null
							&& cuentaAsociado.getCtrEstEstado().getEstId() == 13){
						CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO();
						CtaPrePrestamo prestamo = (CtaPrePrestamo) prestamoDAO.findById(cuentaAsociado.getCtaPrePrestamo().getPreId());
						if(prestamo.getPreLiquidoARecibir()==0){
							return prestamo;
						}
					}
				}
			}*/
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		//return null;
	}

	public CtaCasCuentaAsociado findbyCbaId(String cbaId) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where cas.ctaCbaCuentaBancaria.cbaId ='" + cbaId + "'";
			Query queryObject = getSession().createQuery(queryString);
			return (CtaCasCuentaAsociado)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public CtaCasCuentaAsociado findbyCahId(String cahId) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where cas.ctaCahCuentaAhorro.cahId ='" + cahId + "'";
			Query queryObject = getSession().createQuery(queryString);
			return (CtaCasCuentaAsociado)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CuentaAsociadoVista> findCuentasSinPrestamos(String ascId,int estId){
		//retorna una lista de cuentas del asociado (solo nombre y id)
		String queryString = null;
		List lista1 = null;
		List lista2 = new ArrayList();
		Query queryObject = null;
		Iterator iterator = null;
		String pre = null;
		String nombreTipo = "";
		try {
			queryString ="from CtaCasCuentaAsociado as c where c.ctaAscAsociado.ascId=? " +
						"and (c.ctaCahCuentaAhorro is not null or c.ctaCbaCuentaBancaria is not null) "+
						"  and c.ctrEstEstado.estId=?  "+
						" order by c.ctaCahCuentaAhorro.cahId, c.ctaCbaCuentaBancaria.cbaId";
			queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			queryObject.setParameter(1, estId);
			lista1= queryObject.list();
			iterator = lista1.iterator();
			while(iterator.hasNext()){
				CtaCasCuentaAsociado cuenta = (CtaCasCuentaAsociado)iterator.next();
				if(cuenta.getCtaCahCuentaAhorro() != null){
					String letra = cuenta.getCtaCahCuentaAhorro().getCahId().substring(0,1);
					if(letra.equals("B")){
						letra = cuenta.getCtaCahCuentaAhorro().getCtaTahTipoAhorro().getTahNombre();
						if(letra != null)
							nombreTipo = " - " + letra;
					}else{
						nombreTipo =" - APORTACION";
					}
					lista2.add(new CuentaAsociadoVista(cuenta.getCasCuenta(),cuenta.getCtaCahCuentaAhorro().getCahId() + nombreTipo));
				}else{
					CuentaAsociadoVista vista = new CuentaAsociadoVista(cuenta.getCasCuenta(),
							cuenta.getCtaCbaCuentaBancaria().getCbaCuenta()+" - "+cuenta.getCtaCbaCuentaBancaria().getCtrBanBanco().getBanNombre()+ " - "
											+cuenta.getCtaCbaCuentaBancaria().getCtaTcuTipoCuenta().getTcuNombre());
					lista2.add(vista);
				}
			}
			return lista2;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findCuentasSinPrestamos(String ascId,  int estId, String cahId){
		//retorna una lista de cuentas del asociado, menos la que se esta cerrando (solo nombre y id)
		String queryString = null;
		List lista1 = null;
		List lista2 = new ArrayList();
		Query queryObject = null;
		Iterator iterator = null;
		String pre = null;
		String nombreTipo = null;
		try {
			queryString ="from CtaCasCuentaAsociado as c where c.ctaAscAsociado.ascId=? " +
						" and c.ctrEstEstado.estId=?   "+
						" order by c.ctaCahCuentaAhorro.cahId,c.ctaCbaCuentaBancaria.cbaId ";
			queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			/*queryObject.setParameter(1, cahId);*/
			queryObject.setParameter(1, estId);
			lista1= queryObject.list();
			iterator = lista1.iterator();
			while(iterator.hasNext()){
				CtaCasCuentaAsociado cuenta = (CtaCasCuentaAsociado)iterator.next();
				if(cuenta.getCtaCahCuentaAhorro() != null){
					if(cuenta.getCtaCahCuentaAhorro().getCahId()!=cahId){
						String letra = cuenta.getCtaCahCuentaAhorro().getCahId().substring(0,1);
						if(letra.equals("B")){
							pre="AHORRO - ";
							nombreTipo = " - " + cuenta.getCtaCahCuentaAhorro().getCtaTahTipoAhorro().getTahNombre();
						}else{
							pre="APORTACION - ";
						}
						lista2.add(new CuentaAsociadoVista(cuenta.getCasCuenta(),pre+cuenta.getCtaCahCuentaAhorro().getCahId() +" " +(letra.equals("B")?nombreTipo:"")));
					}
				}else{
					CuentaAsociadoVista vista = new CuentaAsociadoVista(cuenta.getCasCuenta(),
											"BANCARIA - "+cuenta.getCtaCbaCuentaBancaria().getCbaCuenta()+" - "
											+cuenta.getCtaCbaCuentaBancaria().getCtaTcuTipoCuenta().getTcuNombre());
					lista2.add(vista);
				}
			}
			return lista2;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public CtaCasCuentaAsociado findbySegId(String segId) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where cas.ctaSegSeguros.segId ='" + segId + "'";
			Query queryObject = getSession().createQuery(queryString);
			return (CtaCasCuentaAsociado)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByEstado(CtrEstEstado estado) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where cas.ctrEstEstado.estId =" + estado.getEstId() ;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByEstadoForDesembolso(CtrEstEstado estado) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = 
			"FROM CtaCasCuentaAsociado cas "+
			"WHERE cas.ctrEstEstado.estId = " + estado.getEstId() + 
			" AND ( (cas.ctaPrePrestamo.preLiquidoARecibir > 0 and cas.ctaPrePrestamo.ctaCbaCuentaBancaria.cbaId is not null) "+
			"OR (cas.ctaPrePrestamo.preLiquidoARecibir = 0) )";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	

	public List busqueda(DesembolsoPrestamoForm desembolsoForm,HttpServletRequest request) {
		CtaCasCuentaAsociadoDAO cuentaAsociadoDAO = new CtaCasCuentaAsociadoDAO(getSession());
		CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSession());
		CtrEstEstado estado = estadoDAO.findById(15);
		ArrayList lstPre = new ArrayList();
		List lst = cuentaAsociadoDAO.findByEstado(estado);
		List<CtaCasCuentaAsociado> cueList = lst;
		log.debug("cuentas del asociado a partir de los campos de desembolso");
		//DetachedCriteria criteria = DetachedCriteria.forClass(CtaCasCuentaAsociado.class);
		
		if(desembolsoForm.getSelected() != null && desembolsoForm.getSelected() !=0){
			if(desembolsoForm.getSelected() == 1){
				if(desembolsoForm.getPrestamoId() == null || desembolsoForm.getPrestamoId().trim().equals("")){
					return lst;
				}
				cueList = cuentaAsociadoDAO.findByPropertyEstado("ctaPrePrestamo.preId", desembolsoForm.getPrestamoId(),new Integer(15));
			}
			if(desembolsoForm.getSelected() == 2){
				CtaPrePrestamoDAO prestamoDAO = new CtaPrePrestamoDAO(getSession());
				
				if(desembolsoForm.getLineaPrestamoId()!=-1){
					if(desembolsoForm.getTipoPrestamoId()!=-1){
						for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
							CtaCasCuentaAsociado cas = (CtaCasCuentaAsociado) iterator.next();
							CtaPrePrestamo prestamo = prestamoDAO.findById(cas.getCtaPrePrestamo().getPreId());
							String tprId = "" + prestamo.getCtaTprTipoPrestamo().getTprId();
							String tipoPreId = "" + desembolsoForm.getTipoPrestamoId();
							if(tprId.trim().equals(tipoPreId)){
								lstPre.add(cas);
							}
							/*if(prestamo.getCtaTprTipoPrestamo().getTprId() == desembolsoForm.getTipoPrestamoId()){
								lstPre.add(cas);
							}*/
						}
						cueList = lstPre;
					}else{
						for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
							CtaCasCuentaAsociado cas = (CtaCasCuentaAsociado) iterator.next();
							CtaPrePrestamo prestamo = prestamoDAO.findById(cas.getCtaPrePrestamo().getPreId());
							CtaTprTipoPrestamoDAO tipoPrestamoDAO = new CtaTprTipoPrestamoDAO(getSession());
							CtaTprTipoPrestamo tipoPrestamo = tipoPrestamoDAO.findById(prestamo.getCtaTprTipoPrestamo().getTprId());
							String lprId = "" + tipoPrestamo.getCtaLprLineaPrestamo().getLprId();
							//System.out.println(tipoPrestamo.getCtaLprLineaPrestamo().getLprId()+ ", " +desembolsoForm.getLineaPrestamoId());
							String lineaPreId = "" + desembolsoForm.getLineaPrestamoId();
							if(lprId.trim().equals(lineaPreId)){
								lstPre.add(cas);
							}else{
//								System.out.println(tipoPrestamo.getCtaLprLineaPrestamo().getLprId()+ ", " +desembolsoForm.getLineaPrestamoId());
							}
						}
						cueList = lstPre;
					}
				}else{
					return lst;
				}
			}
			if(desembolsoForm.getSelected() == 3){
				if(desembolsoForm.getAsociadoId() == null || desembolsoForm.getAsociadoId().trim().equals("")){
					return lst;
				}
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSession());
				List lstAsociados = asociadoDAO.findByAscCodigo(desembolsoForm.getAsociadoId());
				for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
					CtaCasCuentaAsociado cas = (CtaCasCuentaAsociado) iterator.next();
					if(encuentraAsociado(cas.getCtaAscAsociado().getAscId(),lstAsociados)){
						lstPre.add(cas);
					}
				}
				cueList = lstPre;
			}
			if(desembolsoForm.getSelected() == 4){
				if((desembolsoForm.getPerApellido() == null || desembolsoForm.getPerApellido().trim().equals("")) 
						&& (desembolsoForm.getPerNombre() == null ||desembolsoForm.getPerNombre().trim().equals(""))){
					return lst;
				}
				CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSession());
				CtaAscAsociado asociado = new CtaAscAsociado();
				SecPerPersona persona = new SecPerPersona();
				persona.setPerPrimerApellido(desembolsoForm.getPerApellido());
				persona.setPerPrimerNombre(desembolsoForm.getPerNombre());
				asociado.setSecPerPersona(persona);
				List lstAsociados = asociadoDAO.findByNameUser(asociado,10000);
				for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
					CtaCasCuentaAsociado cas = (CtaCasCuentaAsociado) iterator.next();
					if(encuentraAsociado(cas.getCtaAscAsociado().getAscId(),lstAsociados)){
						lstPre.add(cas);
					}
				}
				cueList = lstPre;
			}
		}else{
			return lst;
		}
		return cueList;
	}

	private boolean encuentraAsociado(String ascId, List lstAsociados) {
		for (Iterator iterator = lstAsociados.iterator(); iterator.hasNext();) {
			CtaAscAsociado asociado = (CtaAscAsociado) iterator.next();
			if(asociado.getAscId() == ascId){
				return true;
			}
		}
		return false;
	}
	
	public List<CtaCasCuentaAsociado> findAllPrestamos() {
		try {
			String queryString = "from CtaCasCuentaAsociado c where c.ctaPrePrestamo is not NULL and c.ctaPrePrestamo.ctaTprTipoPrestamo is not NULL"; // and c.ctrEstEstado.estId=13" +
				//	" and c.ctrEstEstado.estId != 20";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaCasCuentaAsociado> findSolicitudesByRol(String Rol) {
		try {
			String queryString = "from CtaCasCuentaAsociado cas where  cas.ctaPrePrestamo.ctaTprTipoPrestamo.ctaLprLineaPrestamo.lprOrdenAprov like '%"+Rol+"%'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByAscAndTipoCuenta3(String ascId, int tipoAhorro) {
		//encuentra las cuentas de un asociado dependiendo del tipo de ahorro seleccionado
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas, CtaCahCuentaAhorro cah, CtaTahTipoAhorro tah where " +
								 " cas.ctaCahCuentaAhorro.cahId=cah.cahId AND "+
					"cas.ctaAscAsociado.ascId = ? AND ";
			if(tipoAhorro==-1){
				queryString += " cah.cahId like 'A%'";
			}else{
				queryString += " cah.ctaTahTipoAhorro.tahId=tah.tahId AND tah.tahId= " + tipoAhorro;
			}
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByAscAndTipoCuenta(String ascId, String tipoCuenta) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where " +
					"cas.ctaAscAsociado.ascId = '" + ascId + "' AND ";
			if(tipoCuenta.equals("B")){
				queryString += "cas.ctaCahCuentaAhorro.cahId IS NOT NULL " +
						"ORDER BY cas.ctaCahCuentaAhorro.cahCuota desc";
			}
			if(tipoCuenta.equals("C")){
				queryString += "cas.ctaPrePrestamo.preId IS NOT NULL " +
						"ORDER BY cas.ctaPrePrestamo.preSaldoActualT asc";
			}
			if(tipoCuenta.equals("D")){
				queryString += "cas.ctaSegSeguros.segId IS NOT NULL " +
						"ORDER BY cas.ctaSegSeguros.segSaldoActual desc";
			}
			if(tipoCuenta.equals("E")){
				queryString += "cas.ctaCbaCuentaBancaria.cbaId IS NOT NULL";
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByAscAndTipoCuenta2(String ascId, String tipoCuenta) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where " +
					"cas.ctaAscAsociado.ascId = '" + ascId + "' AND ";
			if(tipoCuenta.equals("B")){
				queryString += "cas.ctaCahCuentaAhorro.cahId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 9 " +
						"ORDER BY cas.ctaCahCuentaAhorro.cahCuota desc";
			}
			if(tipoCuenta.equals("C")){
				queryString += "cas.ctaPrePrestamo.preId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 13 " +
						"ORDER BY cas.ctaPrePrestamo.preSaldoActualT asc";
			}
			if(tipoCuenta.equals("D")){
				queryString += "cas.ctaSegSeguros.segId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 11 " +
						"ORDER BY cas.ctaSegSeguros.segSaldoActual desc";
			}
			if(tipoCuenta.equals("E")){
				queryString += "cas.ctaCbaCuentaBancaria.cbaId IS NOT NULL";
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByAscAndTipoCuenta3(String ascId, String tipoCuenta) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where " +
					"cas.ctaAscAsociado.ascId = '" + ascId + "' AND ";
			if(tipoCuenta.equals("B")){
				queryString += "cas.ctaCahCuentaAhorro.cahId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 9 " +
						"ORDER BY cas.ctaCahCuentaAhorro.cahCuota desc";
			}
			if(tipoCuenta.equals("C")){
				queryString += "cas.ctaPrePrestamo.preId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 13 " +
						"AND (cas.ctaPrePrestamo.preCredito IS NULL OR cas.ctaPrePrestamo.preCredito = 'A') " +
						"and cas.ctaPrePrestamo.preSaldoActualT >0 "+
						"ORDER BY cas.ctaPrePrestamo.preSaldoActualT asc";
			}
			if(tipoCuenta.equals("D")){
				queryString += "cas.ctaSegSeguros.segId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 11 " +
						"ORDER BY cas.ctaSegSeguros.segSaldoActual desc";
			}
			if(tipoCuenta.equals("E")){
				queryString += "cas.ctaCbaCuentaBancaria.cbaId IS NOT NULL";
			}
			if(tipoCuenta.equals("CO")){
//				System.out.println("Tipo de cuenta CO ");
				
				queryString += "cas.ctaPrePrestamo.preId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 13 " +
						"AND cas.ctaPrePrestamo.preCredito IS NOT NULL " +
						"AND cas.ctaPrePrestamo.preCredito <>  'A' " +						
						"ORDER BY cas.ctaPrePrestamo.preSaldoActualT asc";
//				System.out.println(queryString);
			}
//			System.out.println(queryString);
			
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	
	public List findByAscAndTipoCuentaSinRef(String ascId, String tipoCuenta,Long casRef) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where " +
					"cas.ctaAscAsociado.ascId = '" + ascId + "' AND ";
			if(tipoCuenta.equals("B")){
				queryString += "cas.ctaCahCuentaAhorro.cahId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 9 " +
						"ORDER BY cas.ctaCahCuentaAhorro.cahCuota desc";
			}
			if(tipoCuenta.equals("C")){
				queryString += "cas.ctaPrePrestamo.preId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 13 and not (cas.casCuenta="+ casRef +
						") ORDER BY cas.ctaPrePrestamo.preSaldoActualT asc";
			}
			if(tipoCuenta.equals("D")){
				queryString += "cas.ctaSegSeguros.segId IS NOT NULL " +
						"AND cas.ctrEstEstado.estId = 11 " +
						"ORDER BY cas.ctaSegSeguros.segSaldoActual desc";
			}
			if(tipoCuenta.equals("E")){
				queryString += "cas.ctaCbaCuentaBancaria.cbaId IS NOT NULL";
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
	public CtaCasCuentaAsociado findPrincipal(String ascId) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where " +
					"cas.ctaAscAsociado.ascId = '" + ascId + "' AND cas.casPrincipal='S'";
			Query queryObject = getSession().createQuery(queryString);
			return (CtaCasCuentaAsociado) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public CtaCasCuentaAsociado findMostRecentPrestamo(String ascId) {
		try {
			String queryString = "from CtaCasCuentaAsociado c where c.ctaAscAsociado.ascId=? "+
			"and c.ctaPrePrestamo is not NULL and c.casFechaApertura=(select max(cas.casFechaApertura)"
			+" from CtaCasCuentaAsociado cas where cas.ctaAscAsociado.ascId = ?"
			+" and cas.ctaPrePrestamo is not null)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0,ascId);
			queryObject.setParameter(1,ascId);
			return (CtaCasCuentaAsociado)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaCasCuentaAsociado> findPrestamosByCriteria(PrestamoForm form,int rowStart,int rowEnd) {
			CtaAscAsociado asociado = form.getAsociadoH();
			SecPerPersona persona = asociado.getSecPerPersona();
		try {
			List<CtaCasCuentaAsociado> lst = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(CtaCasCuentaAsociado.class);
			//definimos los alias que se utilizaran para el join implicito de tablas.
			criteria.createAlias("ctaPrePrestamo", "pre").createAlias("ctaAscAsociado", "asoc").createAlias("asoc.secPerPersona", "persona");
			criteria.add(Restrictions.isNotNull("pre.ctaTprTipoPrestamo"));
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				criteria.add(Restrictions.like("persona.perPrimerApellido","%" + persona.getPerPrimerApellido() + "%"));
			}
			if(persona.getPerDui()!=null&&persona.getPerDui().length()>0) {
				criteria.add(Restrictions.like("persona.perDui", persona.getPerDui()));
			}
			if(persona.getPerNit()!=null&&persona.getPerNit().length()>0) {
				criteria.add(Restrictions.like("persona.perNit",persona.getPerNit()));
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				criteria.add(Restrictions.like("persona.perSegundoApellido","%" + persona.getPerSegundoApellido() + "%"));
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				criteria.add(Restrictions.like("persona.perPrimerNombre","%" + persona.getPerPrimerNombre() + "%"));
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				criteria.add(Restrictions.like("persona.perSegundoNombre","%" + persona.getPerSegundoNombre() + "%"));
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				criteria.add(Restrictions.like("asoc.ascCodigoAsociado","%" + asociado.getAscCodigoAsociado()+ "%"));
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				criteria.add(Restrictions.like("asoc.ascCodigo","%" + asociado.getAscCodigo()+ "%"));
			}
			if(form.getPreId()!=null&&form.getPreId().length()>0) {//para buscar por codigo de prestamo
				criteria.add(Restrictions.like("pre.preId","%" + form.getPreId()+ "%"));
			}
			if(form.getEstado()>0) {//para buscar por estado de prestamo
				criteria.add(Restrictions.like("ctrEstEstado.estId",form.getEstado()));
			}else{
				if(form.getEstado()!=-1) criteria.add(Restrictions.like("ctrEstEstado.estId",13));
			}
			if(form.getCasFechaApertura()!=null&&form.getCasFechaApertura().length()>0){
				criteria.add(Restrictions.sqlRestriction(
						"DATE_FORMAT({alias}.cas_fecha_apertura,'%d-%m-%Y') = ?",
						form.getCasFechaApertura()
						,Hibernate.STRING
				));
			}
		   //Implementamos "distinct" en hibernate
			criteria.setProjection(Projections.distinct( // Se define la utilizacion de "distinct"
					Projections.projectionList()//añadimos la lista de campos que deseamos que nos devuelva la consulta
					.add(Projections.property("casCuenta"),"casCuenta")
					.add(Projections.property("ctaPrePrestamo"),"ctaPrePrestamo")
					.add(Projections.property("ctaCahCuentaAhorro"),"ctaCahCuentaAhorro")
					.add(Projections.property("ctaCbaCuentaBancaria"),"ctaCbaCuentaBancaria")
					.add(Projections.property("ctaAscAsociado"),"ctaAscAsociado")
					.add(Projections.property("ctaSegSeguros"),"ctaSegSeguros")
					.add(Projections.property("ctrEstEstado"),"ctrEstEstado")
					.add(Projections.property("casValorApertura"),"casValorApertura")
					.add(Projections.property("casFechaApertura"),"casFechaApertura")
					.add(Projections.property("casPrincipal"),"casPrincipal")
					.add(Projections.property("casFechaCierre"),"casFechaCierre")
					.add(Projections.property("casRefinanciado"),"casRefinanciado")
					)).setResultTransformer(new AliasToBeanResultTransformer(CtaCasCuentaAsociado.class));//se define el transformador de resultados,
					//en este casi se utiliza AliasToBeanResultTransdormer para que inyecte la consulta escalar arrojada por las proyecciones en objetos del
					//tipo establecido.
			
			criteria.addOrder(Order.asc("ctrEstEstado.estId")).addOrder(Order.desc("casFechaApertura"));
			lst  = (List<CtaCasCuentaAsociado>) criteria.getExecutableCriteria(getSession()).setFirstResult(rowStart).setMaxResults(rowEnd-rowStart).list();
			return lst;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Projection crearProjections(String[] properties) {
        ProjectionList list = Projections.projectionList();
        for (int i = 0; i < properties.length; ++i)
            list.add(Projections.property(properties[i]), properties[i]);
        return list;
  }
	
	public List<CtaCasCuentaAsociado> findPrestamoByEstadoAsocGarantiaYesNo(String ascId,int est, int opcionGarantia) {
		List resp = null;
		try {
			/* opcionGarantia puede tener 3 valores:
			 * 0 : indica que debe traer todos los prestamos con y sin garantia
			 * 1 : indica que debe traer prestamos sin garantia
			 * 2 : indica que debe traer prestamos con garantia
			 * */
			String queryString = "from CtaCasCuentaAsociado acc where acc.ctrEstEstado.estId= ? and acc.ctaPrePrestamo is not null"+
				" and acc.ctaAscAsociado.ascId = ? order by acc.ctaPrePrestamo.preSaldoActualT";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0,est);
			queryObject.setParameter(1,ascId);
			resp = queryObject.list();
			
			Iterator<CtaCasCuentaAsociado> it = resp.iterator();
			List pres = new ArrayList<CtaCasCuentaAsociado>();
			List temp = new ArrayList<CtaCasCuentaAsociado>();
			while(it.hasNext()){
				CtaCasCuentaAsociado cuenta = it.next();
				CtaPrePrestamo prestamo = cuenta.getCtaPrePrestamo();
				if(prestamo.getCtaFxpFiadorPrestamos().size()==0 && prestamo.getCtaGarGarantias().size()==0){
					if(opcionGarantia==0 || opcionGarantia == 1)	pres.add(cuenta);
				}else{
					if(opcionGarantia==0 || opcionGarantia==2) temp.add(cuenta);
				}
			}
			if(!temp.isEmpty()){
				pres.addAll(temp);
			}
			return pres;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	Al manipular la lista arrojada por el siguiente metodo se debe preguntar 
	si el prestamo tiene movimientos, si el prestamo no tiene
	movimientos, entonces el saldo es igual al liquido a recibir,
	caso contrario, si hay movimientos el saldo es igual al valor almacenado en
	el campo saldoActualT.**/
	public List<CtaPrePrestamo> findPrestamosALiquidar(String ascId,HttpServletRequest request) {
		CtaMxpMovimientoPrestamoDAO movPresDAO = new CtaMxpMovimientoPrestamoDAO(getSession());
		List resp = new ArrayList<CtaPrePrestamo>();
		List temp = new ArrayList<CtaPrePrestamo>();
		Double mora, pendiente, inteAcumulado;
		try {
			List<CtaCasCuentaAsociado> lista = findPrestamoByEstadoAsocGarantiaYesNo(ascId,13,0);//extraemos todos los prestamos activos del asociado
			Iterator<CtaCasCuentaAsociado> it = lista.iterator();
			IntereseYMora interesYMora = new IntereseYMora();
			while(it.hasNext()){
				CtaCasCuentaAsociado cuenta = it.next();
				CtaPrePrestamo prestamo = cuenta.getCtaPrePrestamo();
				CtaMxpMovimientoPrestamo movimiento = movPresDAO.findUltimoMovimiento(prestamo.getPreId());
				if(movimiento == null){
					movimiento = new CtaMxpMovimientoPrestamo();
				}
				interesYMora = interesYMora.actualizaInteres(movimiento, prestamo, cuenta,new Date(),request);
		        mora =interesYMora.getMora();
		        pendiente=interesYMora.getPendiente();
				prestamo.setPreMoraMov(mora);
				prestamo.setPrePendMov(pendiente);
				//con el fin de realizar ordenamiento se pregunta si el prestamo sobre el cual se itera no tiene fiadores
				//ni garantias, si es asi se insertara en las primeras posiciones de la lista, de lo contrario se colocara en la 
				//lista temporal para que posteriormente se anexe a la lista final.
				if(prestamo.getCtaFxpFiadorPrestamos().size()==0 && prestamo.getCtaGarGarantias().size()==0){
					resp.add(prestamo);
				}else{
					temp.add(prestamo);
				}
			}
			if(!temp.isEmpty()){
				resp.addAll(temp);
			}
			return resp;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public CtaCasCuentaAsociado findAportacion(String ascId,int estado) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where " +
					"cas.ctaAscAsociado.ascId = '" + ascId + "' AND cas.ctaCahCuentaAhorro.cahId like 'A%' " +
							"and cas.ctrEstEstado = "+estado;
			Query queryObject = getSession().createQuery(queryString);
			return (CtaCasCuentaAsociado) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public CtaCasCuentaAsociado findCreditoSinTipo(String ascId) {
		log.debug("finding all CtaCasCuentaAsociado instances");
		try {
			String queryString = "from CtaCasCuentaAsociado cas where "+
	"cas.ctaAscAsociado.ascId = ? and cas.ctaPrePrestamo is not null and cas.ctaPrePrestamo.ctaTprTipoPrestamo is null";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			return (CtaCasCuentaAsociado) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findCuentasAhorroConCierreHoyC(int tipoAh) {
		log.debug("finding all CtaCasCuentaAsociado instances con cierre de cuenta fecha hoy");
		Date fecha = new Date();
		ElapsedTime elap = new ElapsedTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		CtrParParametrosDAO parametroDAO = new CtrParParametrosDAO(getSession());
		CtrParParametros parametro = parametroDAO.findById("DIAS_ANTERIORIDAD_AVISO_AHORRO");
		try {
			String queryString = " SELECT distinct cas.ctaAscAsociado.ascId AS ASC_ID "+
								 " FROM "+
								 " CtaCahCuentaAhorro as cah,  CtaCasCuentaAsociado as cas, CtaTahTipoAhorro as tah "+
								 " where cah.cahId = cas.ctaCahCuentaAhorro.cahId  and cah.ctaTahTipoAhorro.tahId = tah.tahId and " +
								 " tah.tahId= " + tipoAh + "  " +
								" and DATE(cas.casFechaCierre) between  DATE('"+sdf.format(fecha)+"') and DATE('"+sdf.format(elap.obtenerFecha(fecha, parametro.getParValorNumber().intValue()))+"') ";
			Query queryObject = getSession().createQuery(queryString);
			
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findCuentasActualizacionDeFechaCierre(int tipoAh) {
		log.debug("finding all CtaCasCuentaAsociado instances con cierre de cuenta fecha hoy");
		try {
			String queryString = " SELECT distinct cas.casCuenta AS cuenta "+
								 " FROM "+
								 " CtaCahCuentaAhorro as cah,  CtaCasCuentaAsociado as cas, CtaTahTipoAhorro as tah "+
								 " where cah.cahId = cas.ctaCahCuentaAhorro.cahId  and cah.ctaTahTipoAhorro.tahId = tah.tahId and " +
								 " tah.tahId= " + tipoAh;
			Query queryObject = getSession().createQuery(queryString);
			
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getTotalPrestamosRowCount() {
		try {
			String queryString = "select count(c.casCuenta) from CtaCasCuentaAsociado c where c.ctaPrePrestamo is not NULL and c.ctaPrePrestamo.ctaTprTipoPrestamo is not NULL"; // and c.ctrEstEstado.estId=13" +
				//	" and c.ctrEstEstado.estId != 20";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<CtaCasCuentaAsociado> findAllPrestamos(int rowStart,int rowEnd) {
		try {
			String queryString = "from CtaCasCuentaAsociado c where c.ctaPrePrestamo is not NULL and c.ctaPrePrestamo.ctaTprTipoPrestamo is not NULL"; // and c.ctrEstEstado.estId=13" +
				//	" and c.ctrEstEstado.estId != 20";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByTprActivos(Integer tipoPre) {
		try {
			String queryString ="from CtaCasCuentaAsociado c " +
								"where c.ctaPrePrestamo is not NULL " +
								"and c.ctaPrePrestamo.ctaTprTipoPrestamo = ? " +
								"and (c.ctrEstEstado <> 16 or c.ctrEstEstado <> 17 " +
								"or c.ctrEstEstado <> 23)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tipoPre);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaCasCuentaAsociado> findAllCasCuentaByTipoCuenta(int tahId) {
		try {
			String queryString = "SELECT cta_cas_cuenta_asociado.`cas_cuenta` as cas,cta_cah_cuenta_ahorro.`cah_id` as cah "+
						"FROM "+
						" `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "+
						" ON cta_cah_cuenta_ahorro.`cah_id` = cta_cas_cuenta_asociado.`cah_id`"+
						" INNER JOIN `cta_tah_tipo_ahorro` cta_tah_tipo_ahorro ON cta_cah_cuenta_ahorro.`tah_id` = cta_tah_tipo_ahorro.`TAH_ID`"+
						" where cta_tah_tipo_ahorro.`TAH_ID`=" + tahId;
			SQLQuery queryObject = getSession().createSQLQuery(queryString);
			queryObject.addScalar("cas",Hibernate.LONG);
			queryObject.addScalar("cah",Hibernate.STRING);
			List lista = queryObject.list();
			CtaCasCuentaAsociadoDAO cuentaDAO = new CtaCasCuentaAsociadoDAO(getSession());
			CtaCahCuentaAhorroDAO ahorroDAO  = new CtaCahCuentaAhorroDAO(getSession());
			ArrayList<CtaCasCuentaAsociado> listaC = new ArrayList<CtaCasCuentaAsociado>();
			Iterator it = lista.iterator();
			while(it.hasNext()){
				CtaCasCuentaAsociado cuenta = new CtaCasCuentaAsociado();
				CtaCahCuentaAhorro ahorro = new CtaCahCuentaAhorro();
				
				Object[] item = (Object[])it.next();
				cuenta = cuentaDAO.findById(new Long(item[0].toString()));
				ahorro = ahorroDAO.findById(item[1].toString());
				cuenta.setCtaCahCuentaAhorro(ahorro);
				listaC.add(cuenta);
			}
			
			return listaC;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Integer getTotalRowCountPrestamosByCriteria(PrestamoForm form) {
		CtaAscAsociado asociado = form.getAsociadoH();
		SecPerPersona persona = asociado.getSecPerPersona();
	try {
		List<CtaCasCuentaAsociado> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaCasCuentaAsociado.class);
		//definimos los alias que se utilizaran para el join implicito de tablas.
		criteria.createAlias("ctaPrePrestamo", "pre").createAlias("ctaAscAsociado", "asoc").createAlias("asoc.secPerPersona", "persona");
		criteria.add(Restrictions.isNotNull("pre.ctaTprTipoPrestamo"));
		if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
			criteria.add(Restrictions.like("persona.perPrimerApellido","%" + persona.getPerPrimerApellido() + "%"));
		}
		if(persona.getPerDui()!=null&&persona.getPerDui().length()>0) {
			criteria.add(Restrictions.like("persona.perDui", persona.getPerDui()));
		}
		if(persona.getPerNit()!=null&&persona.getPerNit().length()>0) {
			criteria.add(Restrictions.like("persona.perNit",persona.getPerNit()));
		}
		if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
			criteria.add(Restrictions.like("persona.perSegundoApellido","%" + persona.getPerSegundoApellido() + "%"));
		}
		if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
			criteria.add(Restrictions.like("persona.perPrimerNombre","%" + persona.getPerPrimerNombre() + "%"));
		}
		if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
			criteria.add(Restrictions.like("persona.perSegundoNombre","%" + persona.getPerSegundoNombre() + "%"));
		}
		if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
			criteria.add(Restrictions.like("asoc.ascCodigoAsociado","%" + asociado.getAscCodigoAsociado()+ "%"));
		}
		if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
			criteria.add(Restrictions.like("asoc.ascCodigo","%" + asociado.getAscCodigo()+ "%"));
		}
		if(form.getPreId()!=null&&form.getPreId().length()>0) {//para buscar por codigo de prestamo
			criteria.add(Restrictions.like("pre.preId","%" + form.getPreId()+ "%"));
		}
		if(form.getEstado()>0) {//para buscar por estado de prestamo
			criteria.add(Restrictions.like("ctrEstEstado.estId",form.getEstado()));
		}else{
			if(form.getEstado()!=-1) criteria.add(Restrictions.like("ctrEstEstado.estId",13));
		}
		if(form.getCasFechaApertura()!=null&&form.getCasFechaApertura().length()>0){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.cas_fecha_apertura,'%d-%m-%Y') = ?",
					form.getCasFechaApertura()
					,Hibernate.STRING
			));
		}
		criteria.setProjection(Projections.countDistinct("casCuenta"));
		Integer var =(Integer) criteria.getExecutableCriteria(getSession()).uniqueResult();
		return var;
	} catch (RuntimeException re) {
		log.error("find all failed", re);
		throw re;
	}
}

	public List findByTprAndAsc(Integer tprId, String ascId, String OF) {
		try {
			String queryString ="from CtaCasCuentaAsociado c " +
								"where c.ctaPrePrestamo is not NULL " +
								"and c.ctaPrePrestamo.ctaTprTipoPrestamo.tprId = ? " +
								"and c.ctaAscAsociado.ascId = ? " +
								"and c.ctaPrePrestamo.preCredito = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tprId);
			queryObject.setParameter(1, ascId);
			queryObject.setParameter(2, OF);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByAscAndTipoCreditoMenosTprId(String ascId,
			String tipoCredito, Integer tprId) {
		try {
			String queryString ="select c.ctaPrePrestamo " +
								"from CtaCasCuentaAsociado c " +
								"where c.ctaPrePrestamo is not NULL " +
								"and c.ctaPrePrestamo.ctaTprTipoPrestamo.tprId <> ? " +
								"and c.ctaAscAsociado.ascId = ? " +
								"and c.ctaPrePrestamo.preCredito = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, tprId);
			queryObject.setParameter(1, ascId);
			queryObject.setParameter(2, tipoCredito);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Double getTotalDescuentosAsociado(String ascId, Double divisor) {
		Double valor = 0.00;
		try {
			valor = this.getTotalDescuentosAportaciones(ascId)/divisor
			+this.getTotalDescuentosAhorros(ascId)/divisor
			+this.getTotalDescuentosPrestamos(ascId)/divisor
			+this.getTotalDescuentosSeguros(ascId)/divisor
			;
			return valor;
			
			/*
			String queryString = "" +
					"SELECT " +
    "((IF(asc1.asc_pago_ingreso = 'S', 0.0, " + 
    "	(SELECT par.par_valor_number " +
    "	FROM ctr_par_parametros par " +
    "	where par.par_nombre = 'VALOR_INSCRIPCION') " +
    ")) + " +
    
    "(SELECT  SUM(IF(cah.`cah_cuota` IS NULL,0.0,cah.`cah_cuota`)) AS cuota " +
     "FROM " +
      "   `cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas " +
       "  ON asoc.`ASC_ID` = cas.`ASC_ID` " +
        " INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id` " +
    "WHERE " +
     "   cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' AND " +
      "  IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, " +
		"(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` " +
		"IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a " +
		 "   WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))/"+ divisor +" + " +
		    
    "(SELECT SUM(IF(cah.`cah_cuota` IS NULL,0.0,cah.`cah_cuota`)) AS cuota " +
     "FROM " +
      " `cta_asc_asociado` asoc    INNER JOIN `cta_cas_cuenta_asociado` cas " +
       "ON asoc.`ASC_ID` = cas.`ASC_ID` " +
       "INNER JOIN `cta_cah_cuenta_ahorro` cah  ON cas.`cah_id` = cah.`cah_id` " +
    "WHERE " +
     "   cas.`EST_ID`=9 AND   cah.`cah_id` LIKE 'B%' AND " +
	"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, " +
	"	(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` " +
	"	IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a " +
	"	    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))/"+ divisor +" + " + 
		    
    "IF(   " +
	"	(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0.0,pre.`pre_cuota`)) AS cuota " +
	 "    FROM " +
	  "     `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas " +
	   "    ON asoc.`ASC_ID` = cas.`ASC_ID` " +
	    "   INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id` " +
	    "WHERE " +
	     "   cas.`EST_ID`=13 AND " +
		"pre.`pre_saldo_actual_t` > 0 AND " +  
		"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, " +
		"	(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` " +
		"	IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a " +
		"	    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null,0.0, " + 
		"(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0.0,pre.`pre_cuota`)) AS cuota " +
	     "FROM " +
	      " `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas " +
	       "ON asoc.`ASC_ID` = cas.`ASC_ID` " +
	       "INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id` " +
	    "WHERE " +
	     "   cas.`EST_ID`=13 AND " +
		"pre.`pre_saldo_actual_t` > 0.0 AND " +  
		"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, " +
		"	(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` " +
		"	IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a " +
		"	    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) " +
    ")/"+ divisor +" + " +
    
    " if((select seg.`seg_saldo_actual`	"+ 
    " 	     FROM	"+
    "        `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas	"+
    "        ON asoc.`ASC_ID` = cas.`ASC_ID`	"+
    "        INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`	"+
    "     WHERE	"+
    "         cas.`EST_ID`=11 AND	"+   
    " 	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,	"+
    " 		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`	"+
    " 		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a	"+
    " 		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))> 0,(	"+
    
    "if((SELECT SUM(IF(seg.`seg_cuota` IS NULL,0.0,seg.`seg_cuota`)) AS cuota " +
     "FROM " +
      " `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas " +
       "ON asoc.`ASC_ID` = cas.`ASC_ID` " +
       "INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id` " +
    "WHERE " +
     "   cas.`EST_ID`=11 AND " +   
	"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, " +
	"	(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` " +
	"	IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a " +
	"	    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null, " +
"0.0, (SELECT SUM(IF(seg.`seg_cuota` IS NULL,0.0,seg.`seg_cuota`)) AS cuota " +
 "    FROM " +
  "     `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas " +
   "    ON asoc.`ASC_ID` = cas.`ASC_ID` " +
    "   INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id` " +
    "WHERE " +
     "   cas.`EST_ID`=11 AND " +   
	"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, " +
	"	(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` " +
	"	IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a " +
	"	    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))))/"+ divisor +"),0.0)) as TOTAL " +     

"FROM "+
     "`sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1 "+
     "ON per.`PER_ID` = asc1.`PER_ID` "+
"WHERE "+
     " asc1.`ASC_ID`= '" + ascId +"'" ; 
			SQLQuery sqlQueryObject = getSession().createSQLQuery(queryString);
			sqlQueryObject.addScalar("TOTAL", Hibernate.BIG_DECIMAL);
			Object obj = sqlQueryObject.uniqueResult();
			if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				return value.doubleValue();
			}else {
				return new Double(0.0);
			}
		*/} catch (Exception re) {
			log.error("fallo obtener total", re);
			//throw re;
			re.printStackTrace();
			return 0.00;
		}
	}
	
	public Double getTotalDescuentosAportaciones(String ascId) {
		try {
			String queryString = "" +
					"SELECT "+
    "(SELECT  SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota "+
     "FROM "+
         "`cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas "+
         "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
         "INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id` "+
    "WHERE "+
        "cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' AND "+
        "IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, "+
		"(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` "+
		"IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a "+
		    "WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) AS TOTAL "+

"FROM "+
     "`sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1 "+
     "ON per.`PER_ID` = asc1.`PER_ID` "+
     "INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo "+  
     "ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID` "+
     "INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo "+ 
	"ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID` "+
"WHERE "+
     " asc1.`ASC_ID`= '" + ascId +"'" ; 
			SQLQuery sqlQueryObject = getSession().createSQLQuery(queryString);
			sqlQueryObject.addScalar("TOTAL", Hibernate.BIG_DECIMAL);
			Object obj = sqlQueryObject.uniqueResult();
			if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				return value.doubleValue();
			}else {
				return new Double(0.0);
			}
		} catch (RuntimeException re) {
			log.error("fallo obtener total aportaciones", re);
			throw re;
		}
	}
	
	public Double getTotalDescuentosAhorros(String ascId) {
		try {
			String queryString = "" +
					"SELECT "+
    "(SELECT SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota "+
     "FROM "+
       "`cta_asc_asociado` asoc    INNER JOIN `cta_cas_cuenta_asociado` cas "+
       "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
       "INNER JOIN `cta_cah_cuenta_ahorro` cah  ON cas.`cah_id` = cah.`cah_id` "+
    "WHERE "+
        "cas.`EST_ID`=9 AND   cah.`cah_id` LIKE 'B%' AND "+
	"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, "+
		"(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` "+
		"IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a "+
		    "WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) AS TOTAL "+

"FROM "+
     "`sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1 "+
     "ON per.`PER_ID` = asc1.`PER_ID` "+
     "INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo "+  
     "ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID` "+
     "INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo "+ 
	"ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID` "+
"WHERE "+
     " asc1.`ASC_ID`= '" + ascId +"'" ; 
			SQLQuery sqlQueryObject = getSession().createSQLQuery(queryString);
			sqlQueryObject.addScalar("TOTAL", Hibernate.BIG_DECIMAL);
			Object obj = sqlQueryObject.uniqueResult();
			if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				return value.doubleValue();
			}else {
				return new Double(0.0);
			}
		} catch (RuntimeException re) {
			log.error("fallo obtener total ahorros", re);
			throw re;
		}
	}
	
	public Double getTotalDescuentosPrestamos(String ascId) {
		try {
			String queryString = "" +
					"SELECT "+
"(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota "+
     "FROM "+
       "`cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas "+
       "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
       "INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id` "+
    "WHERE "+
        "cas.`EST_ID`=13 AND "+
	"pre.`pre_saldo_actual_t` > 0 AND "+  
	"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, "+
		"(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` "+
		"IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a "+
		    "WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) AS TOTAL "+		    

"FROM "+
     "`sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1 "+
     "ON per.`PER_ID` = asc1.`PER_ID` "+
     "INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo "+  
     "ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID` "+
     "INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo "+ 
	"ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID` "+
"WHERE "+
     " asc1.`ASC_ID`= '" + ascId +"'" ; 
			SQLQuery sqlQueryObject = getSession().createSQLQuery(queryString);
			sqlQueryObject.addScalar("TOTAL", Hibernate.BIG_DECIMAL);
			Object obj = sqlQueryObject.uniqueResult();
			if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				return value.doubleValue();
			}else {
				return new Double(0.0);
			}
		} catch (RuntimeException re) {
			log.error("fallo obtener total prestamos", re);
			throw re;
		}
	}
	
	public Double getTotalDescuentosSeguros(String ascId) {
		try {
			String queryString = "" +
			"Select IFNULL(SUM(IFNULL(seg.`seg_cuota`,0)), 0.00) as TOTAL "+
			"     				FROM"+
			"       					`cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas"+
			"       					ON asoc.`ASC_ID` = cas.`ASC_ID` INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`"+
			"    				WHERE"+
			"        				cas.`EST_ID`=11 AND seg.`seg_saldo_actual`>0 AND asoc.`ASC_ID` IN"+
			"					('"+ascId+"',(SELECT a.`ASC_ID` FROM cta_asc_asociado a"+
			"		    				WHERE a.`ASC_ASOCIADO_PADRE`='"+ascId+"' AND a.`ASC_PAGARA_PADRE`='S'))";
					/*"SELECT "+
					
					" if((select seg.`seg_saldo_actual`	"+ 
				    " 	     FROM	"+
				    "        `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas	"+
				    "        ON asoc.`ASC_ID` = cas.`ASC_ID`	"+
				    "        INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`	"+
				    "     WHERE	"+
				    "         cas.`EST_ID`=11 AND	"+   
				    " 	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,	"+
				    " 		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`	"+
				    " 		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a	"+
				    " 		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))> 0,(	"+
					
"if((SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota "+
     "FROM "+
       "`cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas "+
       "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
       "INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id` "+
    "WHERE "+
        "cas.`EST_ID`=11 AND "+   
	"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, "+
		"(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` "+
		"IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a "+
		    "WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null, "+
"0.0, (SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`))),0.0) AS cuota "+
     "FROM "+
       "`cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas "+
       "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
       "INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id` "+
    "WHERE "+
        "cas.`EST_ID`=11 AND "+   
	"IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`, "+
		"(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID` "+
		"IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a "+
		    "WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))),0.0) as TOTAL "+    

"FROM "+
     "`sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1 "+
     "ON per.`PER_ID` = asc1.`PER_ID` "+
     "INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo "+  
     "ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID` "+
     "INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo "+ 
	"ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID` "+
"WHERE "+
     " asc1.`ASC_ID`= '" + ascId +"'" ; */
			
			
			SQLQuery sqlQueryObject = getSession().createSQLQuery(queryString);
			sqlQueryObject.addScalar("TOTAL", Hibernate.BIG_DECIMAL);
			Object obj = sqlQueryObject.uniqueResult();
			if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				return value.doubleValue();
			}else {
				return new Double(0.0);
			}
		} catch (RuntimeException re) {
			log.error("fallo obtener total seguros", re);
			throw re;
		}
	}
	
	public Double getCuotaAfiliacion(String ascId) {
		try {
			String queryString = "" +
					"SELECT " +
					" (IF(asc1.asc_pago_ingreso = 'S', 0.0, " + 
    	" (SELECT par.par_valor_number " +
    	" FROM ctr_par_parametros par " +
    	" where par.par_nombre = 'VALOR_INSCRIPCION') " +
    " )) AS TOTAL "+

"FROM "+
     "`sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1 "+
     "ON per.`PER_ID` = asc1.`PER_ID` "+
     "INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo "+  
     "ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID` "+
     "INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo "+ 
	"ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID` "+
"WHERE "+
     " asc1.`ASC_ID`= '" + ascId +"'" ; 
			SQLQuery sqlQueryObject = getSession().createSQLQuery(queryString);
			sqlQueryObject.addScalar("TOTAL", Hibernate.BIG_DECIMAL);
			Object obj = sqlQueryObject.uniqueResult();
			if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				return value.doubleValue();
			}else {
				return new Double(0.0);
			}
		} catch (RuntimeException re) {
			log.error("fallo obtener cuota de afiliacion", re);
			throw re;
		}
	}
}