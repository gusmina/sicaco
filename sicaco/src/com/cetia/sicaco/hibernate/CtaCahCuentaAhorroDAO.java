package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.cetia.sicaco.cuentaCorriente.struts.action.CuentaAhorroAction;
import com.cetia.sicaco.cuentaCorriente.struts.form.CuentaAhorroForm;
import com.mad.utilidades.ElapsedTime;
import com.mad.utilidades.Format;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaCahCuentaAhorro entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaCahCuentaAhorro
 * @author MyEclipse Persistence Tools
 */

public class CtaCahCuentaAhorroDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaCahCuentaAhorroDAO.class);
	// property constants
	public static final String CAH_SALDO_ACTUAL = "cahSaldoActual";
	public static final String CAH_CUOTA = "cahCuota";
	public static final String CAH_INTERES_ACUMULADO = "cahInteresAcumulado";
	
	public CtaCahCuentaAhorroDAO(Session session) {
		super(session);
	}

	public void save(CtaCahCuentaAhorro transientInstance) {
		log.debug("saving CtaCahCuentaAhorro instance");
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

	public void delete(CtaCahCuentaAhorro persistentInstance) {
		log.debug("deleting CtaCahCuentaAhorro instance");
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

	public CtaCahCuentaAhorro findById(java.lang.String id) {
		log.debug("getting CtaCahCuentaAhorro instance with id: " + id);
		try {
			CtaCahCuentaAhorro instance = (CtaCahCuentaAhorro) getSession()
					.get("com.cetia.sicaco.hibernate.CtaCahCuentaAhorro", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaCahCuentaAhorro instance) {
		log.debug("finding CtaCahCuentaAhorro instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaCahCuentaAhorro").add(
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
		log.debug("finding CtaCahCuentaAhorro instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaCahCuentaAhorro as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCahSaldoActual(Object cahSaldoActual) {
		return findByProperty(CAH_SALDO_ACTUAL, cahSaldoActual);
	}

	public List findByCahCuota(Object cahCuota) {
		return findByProperty(CAH_CUOTA, cahCuota);
	}

	public List findByCahInteresAcumulado(Object cahInteresAcumulado) {
		return findByProperty(CAH_INTERES_ACUMULADO, cahInteresAcumulado);
	}

	public List findAll() {
		log.debug("finding all CtaCahCuentaAhorro instances");
		try {
			String queryString = "from CtaCahCuentaAhorro";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaCahCuentaAhorro merge(CtaCahCuentaAhorro detachedInstance) {
		log.debug("merging CtaCahCuentaAhorro instance");
		try {
			CtaCahCuentaAhorro result = (CtaCahCuentaAhorro) getSession()
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

	public void attachDirty(CtaCahCuentaAhorro instance) {
		log.debug("attaching dirty CtaCahCuentaAhorro instance");
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

	public void attachClean(CtaCahCuentaAhorro instance) {
		log.debug("attaching clean CtaCahCuentaAhorro instance");
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
			String queryString = "select max(cah.cahId) from CtaCahCuentaAhorro as cah  " + 
								"where cah.cahId like '"+caracter+"%'";
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
			
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	}
	
	public List<CtaAscAsociado> findByCriteria(CtaCahCuentaAhorro cuenta, CtaAscAsociado asociado, SecPerPersona persona){
		log.debug("finding CtaCahCuentaAhorro instance by criteria");
		List<CtaAscAsociado> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaAscAsociado.class);
		
		if (persona.getPerPrimerNombre() != null && !persona.getPerPrimerNombre().trim().equals("")) {
			criteria.add(Restrictions.like("SecPerPersona.perPrimerNombre","%" + persona.getPerPrimerNombre() + "%"));
		}
		if (persona.getPerSegundoNombre() != null && !persona.getPerSegundoNombre().trim().equals("")) {
			criteria.add(Restrictions.like("SecPerPersona.perSegundoNombre", "%" + persona.getPerSegundoNombre() + "%"));
		}
		if (persona.getPerPrimerApellido() != null && !persona.getPerPrimerApellido().trim().equals("")) {
			criteria.add(Restrictions.like("SecPerPersona.perPrimerApellido","%" + persona.getPerPrimerApellido() + "%"));
		}
		if (persona.getPerSegundoApellido() != null && !persona.getPerSegundoApellido().trim().equals("")) {
			criteria.add(Restrictions.like("SecPerPersona.perSegundoApellido","%" + persona.getPerSegundoApellido() + "%"));
		}
		lst = (List<CtaAscAsociado>)criteria.getExecutableCriteria(getSession()).list();
		return lst;
	}
	
	public List findByReferencia(String referencia, String ascId) {
		try{
			String sql = "from CtaCasCuentaAsociado cas where cas.casCuenta = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, referencia);
			queryObject.setParameter(1, ascId);
			
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void updateByLiquidacion(String ascId,int op) {
		try{
			String sql = "UPDATE CtaCahCuentaAhorro  "+
			"SET cahSaldoActual=0.0 , cahInteresAcumulado=0.0  "+
			"WHERE cahId IN  "+
			"(SELECT cas.ctaCahCuentaAhorro.cahId FROM CtaCasCuentaAsociado cas  "+
			"where cas.ctaAscAsociado.ascId=? and cas.ctaCahCuentaAhorro.cahId is not null ";
			if(op==1){
				//pondremos todas las cuentas a 0.0 pero la principal no
				sql += "and cas.casPrincipal = 'N')";
			}else{
				//pondremos todas a cero
				sql += ")";
			}
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, ascId);
			queryObject.executeUpdate();
		}		
		catch (RuntimeException re) {
			log.error("Update by liquidacion fail   ", re);
			throw re;
		}
	}
	
	public List findByFechaAperturaYReferencia(String fecha, String referencia, String ascId) {
		List lista = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "from CtaCasCuentaAsociado cas where cas.casFechaApertura = ? " + 
						 " and cas.casCuenta=? and cas.ascId=?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, sdf.parse(fecha));
			queryObject.setParameter(1, referencia);
			queryObject.setParameter(2, ascId);
			
			lista =queryObject.list();
			
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}catch (ParseException e) {
			log.error("find by property name failed", e);
			
		}
		return lista;
	}
	public List findByFechaApertura(String fecha, String ascId) {
		List lista = null;
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "from CtaCasCuentaAsociado cas where " + 
						"cas.casFechaApertura= ?" +
						" and cas.ascId=?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, sdf.parse(fecha));
			queryObject.setParameter(1, ascId);
			lista = queryObject.list();
			
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List findTotalAportacionesPorAsoc(int anio) {
		try{
			String sql = /*"SELECT sum(sma.smaSaldoAcum), "+ 
						"sma.ctaCahCuentaAhorro.cahId, "+ 
						" cas.ctaAscAsociado.ascId  "+
						"FROM 	CtaCasCuentaAsociado cas, CtaCahCuentaAhorro  cah,	CtaSmaSaldosxmesActivo sma "+ 
						"where 	cas.ctaCahCuentaAhorro.cahId=cah.cahId and sma.ctaCahCuentaAhorro.cahId=cah.cahId and "+
						"sma.ctaCahCuentaAhorro.cahId like 'A%'  and YEAR(sma.smaFecha)=?  "+
						"group by cas.ctaAscAsociado.ascId ";*/
						"SELECT"+
							" cas.asc_Id AS ASC_ID,"+
							" sum(txa.txa_Monto) AS TXA_MONTO,"+
							" (SELECT "+
							" 	sum(period_diff(date_format(ifnull(i.ina_fecha_salida,curdate()),'%Y%m'), "+
							" 	if(year(i.ina_fecha_ingreso)<"+anio+", concat("+anio+",'01'),date_format(i.ina_fecha_ingreso,'%Y%m'))))	 as meses_diferencia"+
							" FROM cta_ina_ingresosxasociado i where i.asc_id=cas.asc_Id  "+

							"  group by asc_id) MESES_ACTIVO,  "+
							" cas.cah_id as cuenta"+//agregado como parte de la Solucion error de dividendos
						" FROM "+
							" Cta_Ttr_Tipo_Transaccion ttr INNER JOIN Cta_Txa_Transaccionxcuenta_Asociado txa ON ttr.ttr_Id = txa.ttr_Id"+
							" INNER JOIN Cta_Cas_Cuenta_Asociado cas ON txa.cas_Cuenta = cas.cas_Cuenta"+
						" where ttr.ttr_Uso='A' and cas.cah_Id is not null and cas.cah_Id like 'A%' and year(txa_Fecha)=?"+
						" group by cas.asc_Id" +
						" order by cas.asc_Id";
			sql = sql.toLowerCase();
			System.out.println(anio);
			SQLQuery queryObject = getSession().createSQLQuery(sql);
			queryObject.setParameter(0, anio);
			queryObject.addScalar("ASC_ID", Hibernate.STRING);
			queryObject.addScalar("TXA_MONTO", Hibernate.DOUBLE);
			queryObject.addScalar("MESES_ACTIVO", Hibernate.INTEGER);
			queryObject.addScalar("cuenta", Hibernate.STRING);
			
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Double findSaldosAportacionesMensuales(String cuenta, int anio, int mes) {
		try{
			String sql = "select mxa_saldo as saldo "+
						"from cta_mxa_movimiento_ahorro "+ 
						"where cah_id ='"+cuenta+"' and year(mxa_fecha)=? and month(mxa_fecha)=? "+
						"order by mxa_fecha desc "+
						"limit 1";
			
			String sql2 = "SELECT cah_saldo_actual as saldoC FROM cta_cah_cuenta_ahorro where cah_id = ?";
			sql = sql.toLowerCase();
			SQLQuery queryObject = getSession().createSQLQuery(sql);
			queryObject.setParameter(0, anio);
			queryObject.setParameter(1, mes);
			queryObject.addScalar("saldo", Hibernate.DOUBLE);
			if((Double) queryObject.uniqueResult()==null){
				queryObject = getSession().createSQLQuery(sql2);
				queryObject.setParameter(0, cuenta);
				queryObject.addScalar("saldoC", Hibernate.DOUBLE);
			}
			
			return (Double)queryObject.uniqueResult();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findAllAsociados() {
		try{
			String sql = "SELECT asc_id, cah_id "+
						 "FROM cta_cas_cuenta_asociado "+
						 "WHERE cah_id like 'A%'";
			
			sql = sql.toLowerCase();
			SQLQuery queryObject = getSession().createSQLQuery(sql);
			queryObject.addScalar("asc_id", Hibernate.STRING);
			queryObject.addScalar("cah_id", Hibernate.STRING);
			
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findListByLiquidacion(String idAsociado) {
		List lista = null;
		List<CtaCahCuentaAhorro> listaCah = new ArrayList<CtaCahCuentaAhorro>();
		try{
			CtaCahCuentaAhorroDAO cAhorroDAO = new CtaCahCuentaAhorroDAO(getSession());
			CtaCasCuentaAsociado cAsociado= new CtaCasCuentaAsociado();
			String sql = "from CtaCasCuentaAsociado cas where " + 
						" cas.ctaAscAsociado.ascId=? and cas.ctaCahCuentaAhorro.cahId is not null and cas.ctrEstEstado.estId = 9 ";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, idAsociado);
			//tengo toda la lista de cuentas de ahorro del 
			//asociado, ahora debo traerme los objetos de ahorro
			lista = queryObject.list();
			/*listaCah = (List<CtaCasCuentaAsociado>)lista;*/
			List<CtaCasCuentaAsociado> listaCas = (List<CtaCasCuentaAsociado>)lista;
			Iterator<CtaCasCuentaAsociado> iterador = (Iterator<CtaCasCuentaAsociado>)listaCas.listIterator();
			/*Double nuevoInteres = new Double(0);*/
			while(iterador.hasNext()){
				 cAsociado = iterador.next();
				 CtaCahCuentaAhorro cuentaAhorro = cAhorroDAO.findById(cAsociado.getCtaCahCuentaAhorro().getCahId());
				 /*if(cuentaAhorro.getCahId().substring(0, 1).equals("B")){
					 ahorroForm.setCtaCahCuentaAhorroH(cuentaAhorro);
					 nuevoInteres += cuentaAhorro.getCahInteresAcumulado();
					 nuevoInteres += ahorroAction.calculoInteresTransaccion(ahorroForm,new Date()); 
					//le sumaremos al interes actual, un interes de gracia que ofrece la 
					 //cooperativa para lograr saldar las deudas del asociado
					 CtrParParametrosDAO parametroDAO = new CtrParParametrosDAO();
					 CtrParParametros parametroAnio = parametroDAO.findById("ANHO_CALENDARIO");
					 Double interes = cuentaAhorro.getCtaTahTipoAhorro().getCtaTinTasaInteres().getTinTasa();
					 interes = interes/100;
					 Double interesGracia = new Double(0);
					 Double saldoActual = cuentaAhorro.getCahSaldoActual();
					 interesGracia = (saldoActual*interes)*parametroDAO.findById("DIAS_ADIC_INTERE_LIQUIDACION").getParValorNumber()/parametroAnio.getParValorNumber();
					 
					 nuevoInteres += interesGracia;
				 }
				 //nuevoInteres += cuentaAhorro.getCahInteresAcumulado();
				 cuentaAhorro.setCahInteresAcumulado(nuevoInteres);*/
				 listaCah.add(cuentaAhorro);
				 /*nuevoInteres = 0.0;*/
			}
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		return listaCah;
	}
	public List findAllCuAhorroCapitalizacion() {
		log.debug("finding all cuentas de ahorro existentes para capitalizar...\n");
		try {
			String queryString = "SELECT cah.cahId, cah.cahSaldoActual, "+
								"  cah.cahInteresAcumulado, cah.cahCuota, cah.ctaTahTipoAhorro.tahId, cas.casCuenta  "+
								"  FROM     CtaCasCuentaAsociado cas  INNER JOIN   cas.ctaAscAsociado as aso"+
								"  INNER JOIN cas. ctaCahCuentaAhorro as cah"+
								"  WHERE 	cah.cahId like 'B%'"+
								"  			and cas.ctrEstEstado.estId = 9  and cah.cahSaldoActual>0 "; 
//								+
//								" and cah.cahId ='B20100225001027' ";
			Query queryObject = getSession().createQuery(queryString);
			List lista =  queryObject.list();
			/*List resp = new ArrayList<CtaCahCuentaAhorro>();
			Iterator it = lista.iterator();
			while(it.hasNext()){
				CtaCahCuentaAhorro cAhorro = new CtaCahCuentaAhorro();
				Object[] item = (Object[])it.next();
				cAhorro.setCahId(item[0].toString());
				cAhorro.setCahSaldoActual(new Double(item[1].toString()));
				cAhorro.setCahInteresAcumulado(new Double(item[2].toString()));
				cAhorro.setCahCuota(new Double(item[3].toString()));
				cAhorro.getCtaTahTipoAhorro().setTahId(new Integer(item[4]+""));
				resp.add(cAhorro);
			}
			return resp;*/ return lista;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findAllCuAhorroCapitalizacion2() {
		log.debug("finding all cuentas de ahorro existentes para capitalizar...\n");
		try {
			String queryString ="from CtaCahCuentaAhorro cah where cah.cahId like 'B%' and cah.cahSaldoActual > 0 and cah.cahId ='B20100225001027' " ;
			// 
			/*String queryString ="  FROM     CtaCasCuentaAsociado cas  INNER JOIN   cas.ctaAscAsociado as aso"+
								"  INNER JOIN cas. ctaCahCuentaAhorro as cah"+
								"  WHERE 	cah.cahId like 'B%'"+
								"  			and cas.ctrEstEstado.estId = 9  and cah.cahSaldoActual>0";*/
			Query queryObject = getSession().createQuery(queryString);
			List lista =  queryObject.list();
			/*List resp = new ArrayList<CtaCahCuentaAhorro>();
			Iterator it = lista.iterator();
			while(it.hasNext()){
				CtaCahCuentaAhorro cAhorro = new CtaCahCuentaAhorro();
				Object[] item = (Object[])it.next();
				cAhorro.setCahId(item[0].toString());
				cAhorro.setCahSaldoActual(new Double(item[1].toString()));
				cAhorro.setCahInteresAcumulado(new Double(item[2].toString()));
				cAhorro.setCahCuota(new Double(item[3].toString()));
				cAhorro.getCtaTahTipoAhorro().setTahId(new Integer(item[4]+""));
				resp.add(cAhorro);
			}
			return resp;*/ return lista;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
//	
//	public CtaCahCuentaAhorro findAportacion(java.lang.String codigo) {
//	
//		
//		try {
//			String queryString ="" +
//					"from CtaCahCuentaAhorro cah " +
//					"where cah.ctaTahTipoAhorro is null" 
//			;
//			return instance;
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
	
	
}