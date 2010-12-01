package com.cetia.sicaco.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.mad.utilidades.SaldoAnterior;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConSacSaldosAnterioresCuenta entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.ConSacSaldosAnterioresCuenta
 * @author MyEclipse Persistence Tools
 */

public class ConSacSaldosAnterioresCuentaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConSacSaldosAnterioresCuentaDAO.class);
	// property constants
	public static final String SAC_SALDO_ALA_FECHA = "sacSaldoALaFecha";
	public static final String SAC_TOTAL_DEBE = "sacTotalDebe";
	public static final String SAC_TOTAL_HABER = "sacTotalHaber";


	public ConSacSaldosAnterioresCuentaDAO(Session session) {
		super(session);
	}
	
	public void save(ConSacSaldosAnterioresCuenta transientInstance) {
		log.debug("saving ConSacSaldosAnterioresCuenta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConSacSaldosAnterioresCuenta persistentInstance) {
		log.debug("deleting ConSacSaldosAnterioresCuenta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConSacSaldosAnterioresCuenta findById(java.lang.Long id) {
		log.debug("getting ConSacSaldosAnterioresCuenta instance with id: "
				+ id);
		try {
			ConSacSaldosAnterioresCuenta instance = (ConSacSaldosAnterioresCuenta) getSession()
					.get(
							"com.cetia.sicaco.hibernate.ConSacSaldosAnterioresCuenta",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConSacSaldosAnterioresCuenta instance) {
		log.debug("finding ConSacSaldosAnterioresCuenta instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConSacSaldosAnterioresCuenta")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log
				.debug("finding ConSacSaldosAnterioresCuenta instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConSacSaldosAnterioresCuenta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySacSaldoALaFecha(Object sacSaldoALaFecha) {
		return findByProperty(SAC_SALDO_ALA_FECHA, sacSaldoALaFecha);
	}

	public List findBySacTotalDebe(Object sacTotalDebe) {
		return findByProperty(SAC_TOTAL_DEBE, sacTotalDebe);
	}

	public List findBySacTotalHaber(Object sacTotalHaber) {
		return findByProperty(SAC_TOTAL_HABER, sacTotalHaber);
	}

	public List findAll() {
		log.debug("finding all ConSacSaldosAnterioresCuenta instances");
		try {
			String queryString = "from ConSacSaldosAnterioresCuenta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConSacSaldosAnterioresCuenta merge(
			ConSacSaldosAnterioresCuenta detachedInstance) {
		log.debug("merging ConSacSaldosAnterioresCuenta instance");
		try {
			ConSacSaldosAnterioresCuenta result = (ConSacSaldosAnterioresCuenta) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConSacSaldosAnterioresCuenta instance) {
		log.debug("attaching dirty ConSacSaldosAnterioresCuenta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConSacSaldosAnterioresCuenta instance) {
		log.debug("attaching clean ConSacSaldosAnterioresCuenta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public ConSacSaldosAnterioresCuenta findByCuentaFecha(int idCuenta) {
		
		try {
			String query = "from ConSacSaldosAnterioresCuenta c where year(c.sacFecha) = year(curdate()-1) and month(c.sacFecha) = month(curdate()) and c.conCueCuenta.cueId=?";
			Query queryObject = getSession().createQuery(query);
			queryObject.setParameter(0, idCuenta);
			return (ConSacSaldosAnterioresCuenta)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	public List<ConSacSaldosAnterioresCuenta> findCuentasparaCierre(Date fecha,int limiteInferior,int limiteSuperior,int longCod) {
		log.debug("buscando las cuentas utilizadas para el cierre");
		List<ConSacSaldosAnterioresCuenta> retorno = new ArrayList<ConSacSaldosAnterioresCuenta>();
		try {
			String query = "from ConSacSaldosAnterioresCuenta as csc inner join csc.conCueCuenta as cta"
				+" where (cta.conTicTipoCuenta.ticId between ? and ?) and year(csc.sacFecha) = year(?)"
				+"and month(csc.sacFecha) = month(?) and length(cta.cueCodigoCuenta) = ? order by cta.conTicTipoCuenta.ticId ";
			Query queryObject = getSession().createQuery(query);
			queryObject.setParameter(0, limiteInferior);
			queryObject.setParameter(1, limiteSuperior);
			queryObject.setParameter(2, fecha);
			queryObject.setParameter(3, fecha);
			queryObject.setParameter(4, longCod);
			List<Object> lista =  queryObject.list();
			Iterator<Object> it = lista.iterator();
			while(it.hasNext()){
				Object[] item = (Object[]) it.next();
				retorno.add((ConSacSaldosAnterioresCuenta)item[0]);
			}
			return retorno;
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("find all failed", re);
			throw re;
		}
	}
	/**@return el conjunto de registros de saldos anteriores para un mes, año y cuenta especifica.
	 * */  
	public List hayCuentas(int anio, int mes, int idCuenta) {
		try {
			String query = "from ConSacSaldosAnterioresCuenta c where year(c.sacFecha) = ? and month(c.sacFecha) = ?" +
					" and c.conCueCuenta.cueId=?";
			Query queryObject = getSession().createQuery(query);
			queryObject.setParameter(0, anio);
			queryObject.setParameter(1, mes);
			queryObject.setParameter(2, idCuenta);
			//if(queryObject.list().isEmpty()) respuesta = false;
			return /*respuesta*/queryObject.list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean hayCuentasDeAnioPasado() {
		boolean respuesta = true;
		try {
			String query = "from ConSacSaldosAnterioresCuenta c where year(c.sacFecha) = (year(curdate())-1) and month(c.sacFecha) = month(curdate())";
			Query queryObject = getSession().createQuery(query);
			if(queryObject.list().isEmpty()) respuesta = false;
			return respuesta;
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConSacSaldosAnterioresCuenta>findCuentasParaCierre(int codPar,int nivel,int mes, int anio) {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "from ConSacSaldosAnterioresCuenta c where c.conCueCuenta.cueTipoCuenta <> 1 and substring(c.conCueCuenta.cueCodigoCuenta,1,1) = ? "+
											"and length(c.conCueCuenta.cueCodigoCuenta) = ? and YEAR(c.sacFecha) = ? and MONTH(c.sacFecha) = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codPar);
			queryObject.setParameter(1, nivel);
			
			queryObject.setParameter(2,anio);
			queryObject.setParameter(3, mes);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ConSacSaldosAnterioresCuenta>findCuentasParaCierre2(int codPar,int mes, int anio) {
		log.debug("finding all ConCueCuenta instances");
		//Localiza las cuentas posteables para el cierre anual, son las cuentas que deben aparecer en los detalles
		//de las partidas de cierre.
		try {
			String queryString = 
			
				"from ConSacSaldosAnterioresCuenta c " +
				"where c.conCueCuenta.cueTipoCuenta <> 1 and substring(c.conCueCuenta.cueCodigoCuenta,1,1) = ? "+
				"and c.conCueCuenta.cuePosteable = 0 and YEAR(c.sacFecha) = ? and MONTH(c.sacFecha) = ? " +
				"and c.sacSaldoALaFecha <> 0";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codPar);
			queryObject.setParameter(1,anio);
			queryObject.setParameter(2, mes);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ConSacSaldosAnterioresCuenta>findCuentas(int acreeDeudo,int retroactiva,int posteable, int mes, int year) {
		try {/* Selecciona las cuentas acreedoras o deudorar  que sean o no posteables, y puede seleccionar las retroactivas
			*	acreedora = 0; deudora = 1; 
			*   retroactiva = 1 -> si; retroactiva = 0 -> no; retroactiva = 2 entonces son las de reserva
			*   posteable = 0 -> si; posteable =1 -> no
		 	*/
			String queryString = "	from ConSacSaldosAnterioresCuenta c where c.conCueCuenta.conTicTipoCuenta.ticAcreeDeudo = ? "+
				" and c.conCueCuenta.cueTipoCuenta = ? and c.conCueCuenta.cueEstado = 1  and c.conCueCuenta.cuePosteable=? " +
				"and month(c.sacFecha)=?" +
				"and c.sacSaldoALaFecha <> 0 " +
				" and year(c.sacFecha)=? order by c.conCueCuenta.cueCodigoCuenta";
			
			/*Select distinct cue.cue_codigo_cuenta,cue.cue_nombre  
			  from 	Con_Sac_Saldos_Anteriores_Cuenta sac
				inner join con_cue_cuenta cue on cue.cue_id=sac.cue_id 
				inner join con_tic_tipo_cuenta tic on cue.tic_id=tic.tic_id
	
				where 	--length(c.conCueCuenta.cueCodigoCuenta) = ? and
						tic.tic_acree_deudo= 1 and cue.cue_Tipo_Cuenta = 0 and cue.cue_Estado = 1 and cue.cue_posteable=0

				ORDER BY cue.cue_codigo_cuenta
			 * 
			 * */
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, acreeDeudo);
			queryObject.setParameter(1, retroactiva);
			queryObject.setParameter(2, posteable);
			queryObject.setParameter(3, mes);
			queryObject.setParameter(4, year);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * @return all sac rows for a given month and year
	 * 
	 **/
	public List getSacs(int anio, int mes) {
		try {
			String query = "from ConSacSaldosAnterioresCuenta c where year(c.sacFecha) = ? and month(c.sacFecha) = ?";
			Query queryObject = getSession().createQuery(query);
			queryObject.setParameter(0, anio);
			queryObject.setParameter(1, mes);
			return queryObject.list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("find all failed 2", re);
			throw re;
		}
	}
	
	
	
	
	/**@return el total de cargos y abonos para cada <b> POSTEABLE </b> cuenta en un mes dado*/
	 public List<SaldoAnterior>saldosEnElMes(int mes, int anio){
		
		 String sql = 
			" SELECT "+
			"   if((substring(cue.cue_codigo_cuenta,1,1)= 1 or substring(cue.cue_codigo_cuenta,1,1)= 4),'C','A') as mult, "+
			"   cue.cue_codigo_cuenta as cuenta, "+
			"   sum(dpa.dpa_valor_debe) as cargos, "+
			"   sum(dpa.dpa_valor_haber) as abonos "+
			" FROM "+
			"   con_dpa_detalle_partida dpa, "+
			"   con_pco_partida_contable pco, "+
			"   con_cue_cuenta cue "+
			" Where "+
			"   dpa.pco_id = pco.pco_id and "+
			"   year(pco.pco_fecha_ingreso_partida) = "+anio+" and "+
			"   month(pco.pco_fecha_ingreso_partida) ="+mes+" and "+
			"   cue.cue_id= dpa.cue_id "+
			" group by  "+
			" 	cue_codigo_cuenta ";
		
		 System.out.println(sql);
		String jdbcDriver = "com.mysql.jdbc.Driver";
		List<SaldoAnterior> l = new ArrayList<SaldoAnterior>(); ;
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				SaldoAnterior saldo = new SaldoAnterior();
				saldo.setMultiplicador(rs.getString("mult"));
				saldo.setCuenta(rs.getString("cuenta"));
				saldo.setCargos(rs.getDouble("cargos"));
				saldo.setAbonos(rs.getDouble("abonos"));
				l.add(saldo);
			}
			con.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			return l;
	}
	/**@return the last money amount stored in sac for that account*/  
	public double findUltimoSaldo(String cueId){
		String query = "from ConSacSaldosAnterioresCuenta sac  " +
						"where sac.conCueCuenta.cueId= "+cueId+" order by sac.sacFecha desc limit 1";
		Query queryObject = getSession().createQuery(query);
		List<ConSacSaldosAnterioresCuenta> saldos = queryObject.list();
		if(saldos.size() > 0){
			return saldos.get(0).getSacSaldoALaFecha();
		}else{
			return 0.00;
		}
	}
	
	
}