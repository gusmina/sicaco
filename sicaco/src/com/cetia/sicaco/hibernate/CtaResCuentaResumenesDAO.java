package com.cetia.sicaco.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.mad.utilidades.Resumen;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaResCuentaResumenes entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaResCuentaResumenes
 * @author MyEclipse Persistence Tools
 */

public class CtaResCuentaResumenesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaResCuentaResumenesDAO.class);
	// property constants
	public static final String RES_CUENTA_NOM = "resCuentaNom";
	public static final String RES_SALDO_ANT = "resSaldoAnt";
	public static final String RES_SALDO_ACT = "resSaldoAct";
	public static final String RES_LINEA = "resLinea";

	public CtaResCuentaResumenesDAO(Session session) {
		super(session);
	}

	public void save(CtaResCuentaResumenes transientInstance) {
		log.debug("saving CtaResCuentaResumenes instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaResCuentaResumenes persistentInstance) {
		log.debug("deleting CtaResCuentaResumenes instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	

	public CtaResCuentaResumenes findById(java.lang.Long id) {
		log.debug("getting CtaResCuentaResumenes instance with id: " + id);
		try {
			CtaResCuentaResumenes instance = (CtaResCuentaResumenes) getSession()
					.get("com.cetia.sicaco.hibernate.CtaResCuentaResumenes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaResCuentaResumenes instance) {
		log.debug("finding CtaResCuentaResumenes instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaResCuentaResumenes").add(
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
		log.debug("finding CtaResCuentaResumenes instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaResCuentaResumenes as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByResCuentaNom(Object resCuentaNom) {
		return findByProperty(RES_CUENTA_NOM, resCuentaNom);
	}

	public List findByResSaldoAnt(Object resSaldoAnt) {
		return findByProperty(RES_SALDO_ANT, resSaldoAnt);
	}

	public List findByResSaldoAct(Object resSaldoAct) {
		return findByProperty(RES_SALDO_ACT, resSaldoAct);
	}

	public List findByResLinea(Object resLinea) {
		return findByProperty(RES_LINEA, resLinea);
	}

	public List findAll() {
		log.debug("finding all CtaResCuentaResumenes instances");
		try {
			String queryString = "from CtaResCuentaResumenes";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaResCuentaResumenes merge(CtaResCuentaResumenes detachedInstance) {
		log.debug("merging CtaResCuentaResumenes instance");
		try {
			CtaResCuentaResumenes result = (CtaResCuentaResumenes) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaResCuentaResumenes instance) {
		log.debug("attaching dirty CtaResCuentaResumenes instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findSaldosAnteriores(Date fechaD, String ascId){		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = sdf.format(fechaD);

		String sql =
		"SELECT "
		+"'Aportaciones y ahorros' as Linea, "
		+"cuenta_nombre as Cuenta, "+
		"cuenta_id as cuentaId, "
		+"saldo as Saldo "
		+" FROM "
		+"(SELECT "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_FECHA` AS FECHA, "
		     +"	cta_txa_transaccionxcuenta_asociado.`TXA_ID` id_trans, "
			+"if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,'APORTACIONES', "
				+"cta_tah_tipo_ahorro.`TAH_NOMBRE`) AS CUENTA_NOMBRE, " +
				"	 if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,'APORTACIONES',cta_tah_tipo_ahorro.`TAH_ID`)AS CUENTA_ID,"
		     	+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS DESCUENTO, "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS TOTAL_DESCUENTO, "
			+"if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,0.0, "
				+"IFNULL(cta_mxa_movimiento_ahorro.`mxa_interes_tran`,0.0)) as INTERES, "
			+"0.0 AS INTERES_PENDIENTE, "
			+"0.0 as INTERES_PAGADO, "
			+"cta_mxa_movimiento_ahorro.`mxa_saldo` AS SALDO, "
			+"CONCAT_WS(' ',cta_ttr_tipo_transaccion.`TTR_NOMBRE`, "
				+"IF(cta_txa_transaccionxcuenta_asociado.`TTR_ID` IN (26,33,15), "
				  +" IFNULL(cta_txa_transaccionxcuenta_asociado.`TXA_NOTA`,''),''))  AS DESCRIPCION, "
			+"cta_ttr_tipo_transaccion.`TTR_USO` AS TTR_USO, "
			+"if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,1,2) AS NUMERO "
		/*+"FROM "
		  +"   	`cta_asc_asociado` cta_asc_asociado "
			+"INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
			+"ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
			+"INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado "
			+"ON cta_cas_cuenta_asociado.`CAS_CUENTA` = cta_txa_transaccionxcuenta_asociado.`CAS_CUENTA` "
			+"INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion "
			+"ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
			+"INNER JOIN `cta_mxa_movimiento_ahorro` cta_mxa_movimiento_ahorro "
			+"ON cta_txa_transaccionxcuenta_asociado.`TXA_ID` = cta_mxa_movimiento_ahorro.`txa_id` "
		    +" 	INNER JOIN `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro "
			+"ON cta_cas_cuenta_asociado.`cah_id` = cta_cah_cuenta_ahorro.`cah_id` "
			+"left outer JOIN `cta_tah_tipo_ahorro` cta_tah_tipo_ahorro "
			+"ON cta_cah_cuenta_ahorro.`tah_id` = cta_tah_tipo_ahorro.`TAH_ID` "
		+" "
		*/
			+" FROM "
			   +"  `cta_asc_asociado` cta_asc_asociado "
				+" INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
				+" ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
			    +" INNER JOIN `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro ON cta_cas_cuenta_asociado.`cah_id` = cta_cah_cuenta_ahorro.`cah_id` "
			    +" LEFT OUTER JOIN `cta_tah_tipo_ahorro` cta_tah_tipo_ahorro ON cta_cah_cuenta_ahorro.`tah_id` = cta_tah_tipo_ahorro.`TAH_ID` "
			    +" INNER JOIN `cta_mxa_movimiento_ahorro` cta_mxa_movimiento_ahorro ON cta_cah_cuenta_ahorro.`cah_id` = cta_mxa_movimiento_ahorro.`cah_id` "
			    +" INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado ON cta_mxa_movimiento_ahorro.`txa_id` = cta_txa_transaccionxcuenta_asociado.`TXA_ID` "
			    +" INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
				+" "
		+"WHERE "
			+"cta_cas_cuenta_asociado.`ASC_ID` = '"+ascId
		+"' "
			+"and date(cta_txa_transaccionxcuenta_asociado.`TXA_FECHA`) < date('"+fecha+"') "
			+"and cta_mxa_movimiento_ahorro.`mxa_saldo` is not null "
		+" "
		 +"order by fecha desc )as ahorro "

		  +"group by Cuenta, cuentaId "
		+"UNION "
		+" "
		+"SELECT "
		+"'Seguros' as Linea, "
		+"cuenta_nombre as Cuenta, "
		+"cuenta_id as cuentaId, "
		+"saldo as Saldo "
		+" "
		+" FROM "
		+"( "
		+"SELECT "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_FECHA` AS FECHA, "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_ID` id_trans, "
		     	+"cta_tis_tipo_seguro.`TIS_NOMBRE` AS CUENTA_NOMBRE, " +
		     	"	cta_seg_seguros.`seg_id` AS CUENTA_ID,"
			+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS DESCUENTO, "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS TOTAL_DESCUENTO, "
			+"0.0 as INTERES, "
			+"0.0 AS INTERES_PENDIENTE, "
			+"0.0 as INTERES_PAGADO, "
			+"cta_mxs_movimiento_seguros.`mxs_saldo` AS SALDO, "
			+"CONCAT_WS(' ',cta_ttr_tipo_transaccion.`TTR_NOMBRE`, "
				+"IF(cta_txa_transaccionxcuenta_asociado.`TTR_ID` IN (26,33,15), "
				  +" IFNULL(cta_txa_transaccionxcuenta_asociado.`TXA_NOTA`,''),''))  AS DESCRIPCION, "
			+"cta_ttr_tipo_transaccion.`TTR_USO` AS TTR_USO, "
			+"3 AS NUMERO "
		+" "
		+"FROM "
		  +"  	`cta_asc_asociado` cta_asc_asociado "
			+"INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
		+"	ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
		     	+"INNER JOIN `cta_seg_seguros` cta_seg_seguros "
			+"ON cta_cas_cuenta_asociado.`seg_id` = cta_seg_seguros.`SEG_ID` "
		     +"	INNER JOIN `cta_tis_tipo_seguro` cta_tis_tipo_seguro "
			+"ON cta_seg_seguros.`TIS_ID` = cta_tis_tipo_seguro.`TIS_ID` "
		 	+"INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado "
			+"ON cta_cas_cuenta_asociado.`CAS_CUENTA` = cta_txa_transaccionxcuenta_asociado.`CAS_CUENTA` "
		 	+"INNER JOIN `cta_mxs_movimiento_seguros` cta_mxs_movimiento_seguros "
			+"ON cta_txa_transaccionxcuenta_asociado.`TXA_ID` = cta_mxs_movimiento_seguros.`TXA_ID` "
		 	+"INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion "
			+"ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
		+" "
		+"WHERE "
		+"	cta_cas_cuenta_asociado.`ASC_ID` = '" +ascId
		+"' "
		+"	and date(cta_txa_transaccionxcuenta_asociado.`TXA_FECHA`) < date('"+fecha+"') "
		+" "
			+"and cta_mxs_movimiento_seguros.`mxs_saldo` is not null "
		+" "
		 +"order by fecha desc "
		+")as seguros "
		+" "
		  +"group by Cuenta, CuentaId "
		+" "
		+"UNION "
		+" "
		+"SELECT "
		+"'Prestamos' as Linea, "
		+"cuenta_nombre as Cuenta, " +
		"cuenta_id as cuentaId, "
		+"saldo as Saldo "
		+" "
		+" FROM "
		+"( "
		+"SELECT "
		+"	cta_txa_transaccionxcuenta_asociado.`TXA_FECHA` AS FECHA, "
		+"	cta_txa_transaccionxcuenta_asociado.`TXA_ID` id_trans, "
		    	+"cta_tpr_tipo_prestamo.`TPR_NOMBRE` AS CUENTA_NOMBRE, " +
		 "	cta_pre_prestamo.`PRE_ID` AS CUENTA_ID,"
		+"     	IF((cta_txa_transaccionxcuenta_asociado.`TXA_MONTO`- IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0)  - "
		+"	IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0))<0.0,0.0, "
		+"	(cta_txa_transaccionxcuenta_asociado.`TXA_MONTO`- IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0)  - "
		+"	IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0))) AS DESCUENTO, "
		+"	cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS TOTAL_DESCUENTO, "
			+"IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0)  as INTERES, "
		+"	IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0) as INTERES_PENDIENTE, "
			+"IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0) + "
			+"IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0) as INTERES_PAGADO, "
			+"cta_mxp_movimiento_prestamo.`mxp_saldo` AS SALDO, "
			+"CONCAT_WS(' ',cta_ttr_tipo_transaccion.`TTR_NOMBRE`, "
		+"		IF(cta_txa_transaccionxcuenta_asociado.`TTR_ID` IN (26,33,15), "
			+"	   IFNULL(cta_txa_transaccionxcuenta_asociado.`TXA_NOTA`,''),''))  AS DESCRIPCION, "
			+"cta_ttr_tipo_transaccion.`TTR_USO` AS TTR_USO, "
			+"4 AS NUMERO "
		+"FROM "
		  +"  	`cta_asc_asociado` cta_asc_asociado "
			+"INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
			+"ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
		     +"	INNER JOIN `cta_pre_prestamo` cta_pre_prestamo "
			+"ON cta_cas_cuenta_asociado.`pre_id` = cta_pre_prestamo.`PRE_ID` "
		     +"	INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado "
			+"ON cta_cas_cuenta_asociado.`CAS_CUENTA` = cta_txa_transaccionxcuenta_asociado.`CAS_CUENTA` "
		+"     	INNER JOIN `cta_mxp_movimiento_prestamo` cta_mxp_movimiento_prestamo "
			+"ON cta_txa_transaccionxcuenta_asociado.`TXA_ID` = cta_mxp_movimiento_prestamo.`TXA_ID` "
		     +"	INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion "
			+"ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
		     +"	LEFT OUTER JOIN `cta_tpr_tipo_prestamo` cta_tpr_tipo_prestamo "
			+"ON cta_pre_prestamo.`TPR_ID` = cta_tpr_tipo_prestamo.`TPR_ID` "
		+"WHERE "
			+"cta_cas_cuenta_asociado.`ASC_ID` = '"+ascId
		+"' "
			+"and date(cta_txa_transaccionxcuenta_asociado.`TXA_FECHA`) < date('"+fecha+"') "
			+" "
			+"and (cta_pre_prestamo.`PRE_MONTO_SOLICITADO` - cta_mxp_movimiento_prestamo.`mxp_saldo_actual`) is not null "
		 +"order by fecha desc "
		+")as prestamos "
		+" "
		  +"group by  CUENTA_NOMBRE, cuentaId " ;
	 
		String jdbcDriver = "com.mysql.jdbc.Driver";
		List<Resumen> l = new ArrayList<Resumen>(); ;
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Resumen resumen = new Resumen();
				String linea = rs.getString("Linea");
				String cuenta = rs.getString("Cuenta");
				Double saldo = rs.getDouble("Saldo");
				resumen.setLinea(linea);
				resumen.setCuenta(cuenta);
				resumen.setSaldo(saldo);
				l.add(resumen);
			
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	return l;
	}

	public List findSaldosActuales(Date fechaD, String ascId){		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = sdf.format(fechaD);

		String sql =
		"SELECT "
		+"'Aportaciones y ahorros' as Linea, "
		+"cuenta_nombre as Cuenta, " +
		"cuenta_id as cuentaId, "
		+"saldo as Saldo "
		+" FROM "
		+"(SELECT "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_FECHA` AS FECHA, "
		     +"	cta_txa_transaccionxcuenta_asociado.`TXA_ID` id_trans, "
			+"if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,'APORTACIONES', "
				+"cta_tah_tipo_ahorro.`TAH_NOMBRE`) AS CUENTA_NOMBRE, "+
				"	 if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,'APORTACIONES',cta_tah_tipo_ahorro.`TAH_ID`)AS CUENTA_ID, "
		     	+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS DESCUENTO, "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS TOTAL_DESCUENTO, "
			+"if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,0.0, "
				+"IFNULL(cta_mxa_movimiento_ahorro.`mxa_interes_tran`,0.0)) as INTERES, "
			+"0.0 AS INTERES_PENDIENTE, "
			+"0.0 as INTERES_PAGADO, "
			+"cta_mxa_movimiento_ahorro.`mxa_saldo` AS SALDO, "
			+"CONCAT_WS(' ',cta_ttr_tipo_transaccion.`TTR_NOMBRE`, "
				+"IF(cta_txa_transaccionxcuenta_asociado.`TTR_ID` IN (26,33,15), "
				  +" IFNULL(cta_txa_transaccionxcuenta_asociado.`TXA_NOTA`,''),''))  AS DESCRIPCION, "
			+"cta_ttr_tipo_transaccion.`TTR_USO` AS TTR_USO, "
			+"if(cta_cah_cuenta_ahorro.`tah_id` IS NULL,1,2) AS NUMERO "
		+/*"FROM "
		  +"   	`cta_asc_asociado` cta_asc_asociado "
			+"INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
			+"ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
			+"INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado "
			+"ON cta_cas_cuenta_asociado.`CAS_CUENTA` = cta_txa_transaccionxcuenta_asociado.`CAS_CUENTA` "
			+"INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion "
			+"ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
			+"INNER JOIN `cta_mxa_movimiento_ahorro` cta_mxa_movimiento_ahorro "
			+"ON cta_txa_transaccionxcuenta_asociado.`TXA_ID` = cta_mxa_movimiento_ahorro.`txa_id` "
		    +" 	INNER JOIN `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro "
			+"ON cta_cas_cuenta_asociado.`cah_id` = cta_cah_cuenta_ahorro.`cah_id` "
			+"left outer JOIN `cta_tah_tipo_ahorro` cta_tah_tipo_ahorro "
			+"ON cta_cah_cuenta_ahorro.`tah_id` = cta_tah_tipo_ahorro.`TAH_ID` "
		+" "*/
		" FROM "
		   +"  `cta_asc_asociado` cta_asc_asociado "
			+" INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
			+" ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
		    +" INNER JOIN `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro ON cta_cas_cuenta_asociado.`cah_id` = cta_cah_cuenta_ahorro.`cah_id` "
		    +" LEFT OUTER JOIN `cta_tah_tipo_ahorro` cta_tah_tipo_ahorro ON cta_cah_cuenta_ahorro.`tah_id` = cta_tah_tipo_ahorro.`TAH_ID` "
		    +" INNER JOIN `cta_mxa_movimiento_ahorro` cta_mxa_movimiento_ahorro ON cta_cah_cuenta_ahorro.`cah_id` = cta_mxa_movimiento_ahorro.`cah_id` "
		    +" INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado ON cta_mxa_movimiento_ahorro.`txa_id` = cta_txa_transaccionxcuenta_asociado.`TXA_ID` "
		    +" INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
			+" "
		+"WHERE "
			+"cta_cas_cuenta_asociado.`ASC_ID` = '"+ascId
		+"' "
			+"and date(cta_txa_transaccionxcuenta_asociado.`TXA_FECHA`) <= date('"+fecha+"') "
			+"and cta_mxa_movimiento_ahorro.`mxa_saldo` is not null "
		+" "
		 +"order by fecha desc )as ahorro "

		  +"group by Cuenta , CuentaId "
		+"UNION "
		+" "
		+"SELECT "
		+"'Seguros' as Linea, "
		+"cuenta_nombre as Cuenta, "+
		"cuenta_id as cuentaId, "
		+"saldo as Saldo "
		+" "
		+" FROM "
		+"( "
		+"SELECT "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_FECHA` AS FECHA, "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_ID` id_trans, "
		     	+"cta_tis_tipo_seguro.`TIS_NOMBRE` AS CUENTA_NOMBRE, " +
		     "	cta_seg_seguros.`seg_id` AS CUENTA_ID, "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS DESCUENTO, "
			+"cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS TOTAL_DESCUENTO, "
			+"0.0 as INTERES, "
			+"0.0 AS INTERES_PENDIENTE, "
			+"0.0 as INTERES_PAGADO, "
			+"cta_mxs_movimiento_seguros.`mxs_saldo` AS SALDO, "
			+"CONCAT_WS(' ',cta_ttr_tipo_transaccion.`TTR_NOMBRE`, "
				+"IF(cta_txa_transaccionxcuenta_asociado.`TTR_ID` IN (26,33,15), "
				  +" IFNULL(cta_txa_transaccionxcuenta_asociado.`TXA_NOTA`,''),''))  AS DESCRIPCION, "
			+"cta_ttr_tipo_transaccion.`TTR_USO` AS TTR_USO, "
			+"3 AS NUMERO "
		+" "
		+"FROM "
		  +"  	`cta_asc_asociado` cta_asc_asociado "
			+"INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
		+"	ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
		     	+"INNER JOIN `cta_seg_seguros` cta_seg_seguros "
			+"ON cta_cas_cuenta_asociado.`seg_id` = cta_seg_seguros.`SEG_ID` "
		     +"	INNER JOIN `cta_tis_tipo_seguro` cta_tis_tipo_seguro "
			+"ON cta_seg_seguros.`TIS_ID` = cta_tis_tipo_seguro.`TIS_ID` "
		 	+"INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado "
			+"ON cta_cas_cuenta_asociado.`CAS_CUENTA` = cta_txa_transaccionxcuenta_asociado.`CAS_CUENTA` "
		 	+"INNER JOIN `cta_mxs_movimiento_seguros` cta_mxs_movimiento_seguros "
			+"ON cta_txa_transaccionxcuenta_asociado.`TXA_ID` = cta_mxs_movimiento_seguros.`TXA_ID` "
		 	+"INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion "
			+"ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
		+" "
		+"WHERE "
		+"	cta_cas_cuenta_asociado.`ASC_ID` = '" +ascId
		+"' "
		+"	and date(cta_txa_transaccionxcuenta_asociado.`TXA_FECHA`) <= date('"+fecha+"') "
		+" "
			+"and cta_mxs_movimiento_seguros.`mxs_saldo` is not null "
		+" "
		 +"order by fecha desc "
		+")as seguros "
		+" "
		  +"group by Cuenta, cuentaId "
		+" "
		+"UNION "
		+" "
		+"SELECT "
		+"'Prestamos' as Linea, "
		+"cuenta_nombre as Cuenta, "+
		"cuenta_id as cuentaId, "
		+"saldo as Saldo "
		+" "
		+" FROM "
		+"( "
		+"SELECT "
		+"	cta_txa_transaccionxcuenta_asociado.`TXA_FECHA` AS FECHA, "
		+"	cta_txa_transaccionxcuenta_asociado.`TXA_ID` id_trans, "
		    	+"cta_tpr_tipo_prestamo.`TPR_NOMBRE` AS CUENTA_NOMBRE, " +
		    	"	cta_pre_prestamo.`PRE_ID` AS CUENTA_ID, "
		+"     	IF((cta_txa_transaccionxcuenta_asociado.`TXA_MONTO`- IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0)  - "
		+"	IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0))<0.0,0.0, "
		+"	(cta_txa_transaccionxcuenta_asociado.`TXA_MONTO`- IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0)  - "
		+"	IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0))) AS DESCUENTO, "
		+"	cta_txa_transaccionxcuenta_asociado.`TXA_MONTO` AS TOTAL_DESCUENTO, "
			+"IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0)  as INTERES, "
		+"	IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0) as INTERES_PENDIENTE, "
			+"IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_acumulado`,0.0) + "
			+"IFNULL(cta_mxp_movimiento_prestamo.`mxp_interes_pendiente`,0.0) as INTERES_PAGADO, "
			+"cta_mxp_movimiento_prestamo.`mxp_saldo` AS SALDO, "
			+"CONCAT_WS(' ',cta_ttr_tipo_transaccion.`TTR_NOMBRE`, "
		+"		IF(cta_txa_transaccionxcuenta_asociado.`TTR_ID` IN (26,33,15), "
			+"	   IFNULL(cta_txa_transaccionxcuenta_asociado.`TXA_NOTA`,''),''))  AS DESCRIPCION, "
			+"cta_ttr_tipo_transaccion.`TTR_USO` AS TTR_USO, "
			+"4 AS NUMERO "
		+"FROM "
		  +"  	`cta_asc_asociado` cta_asc_asociado "
			+"INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado "
			+"ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID` "
		     +"	INNER JOIN `cta_pre_prestamo` cta_pre_prestamo "
			+"ON cta_cas_cuenta_asociado.`pre_id` = cta_pre_prestamo.`PRE_ID` "
		     +"	INNER JOIN `cta_txa_transaccionxcuenta_asociado` cta_txa_transaccionxcuenta_asociado "
			+"ON cta_cas_cuenta_asociado.`CAS_CUENTA` = cta_txa_transaccionxcuenta_asociado.`CAS_CUENTA` "
		+"     	INNER JOIN `cta_mxp_movimiento_prestamo` cta_mxp_movimiento_prestamo "
			+"ON cta_txa_transaccionxcuenta_asociado.`TXA_ID` = cta_mxp_movimiento_prestamo.`TXA_ID` "
		     +"	INNER JOIN `cta_ttr_tipo_transaccion` cta_ttr_tipo_transaccion "
			+"ON cta_txa_transaccionxcuenta_asociado.`TTR_ID` = cta_ttr_tipo_transaccion.`TTR_ID` "
		     +"	LEFT OUTER JOIN `cta_tpr_tipo_prestamo` cta_tpr_tipo_prestamo "
			+"ON cta_pre_prestamo.`TPR_ID` = cta_tpr_tipo_prestamo.`TPR_ID` "
		+"WHERE "
			+"cta_cas_cuenta_asociado.`ASC_ID` = '"+ascId
		+"' "
			+"and date(cta_txa_transaccionxcuenta_asociado.`TXA_FECHA`) <= date('"+fecha+"') "
			+" "
			+"and (cta_pre_prestamo.`PRE_MONTO_SOLICITADO` - cta_mxp_movimiento_prestamo.`mxp_saldo_actual`) is not null "
		 +"order by fecha desc "
		+")as prestamos "
		+" "
		  +"group by  CUENTA_NOMBRE, cuenta_Id " ;
	 
		String jdbcDriver = "com.mysql.jdbc.Driver";
		List<Resumen> l = new ArrayList<Resumen>(); ;
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Resumen resumen = new Resumen();
				String linea = rs.getString("Linea");
				String cuenta = rs.getString("Cuenta");
				Double saldo = rs.getDouble("Saldo");
				resumen.setLinea(linea);
				resumen.setCuenta(cuenta);
				resumen.setSaldo(saldo);
				l.add(resumen);
			
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	return l;
	}
	
	
	public void deleteAll(){
		
		CtaResCuentaResumenesDAO dao = new CtaResCuentaResumenesDAO(getSession());
		Transaction tx = dao.getSession().beginTransaction();
		List l = dao.findAll();
		Iterator i = l.iterator();
		while (i.hasNext()){
			dao.delete((CtaResCuentaResumenes) i.next());
			tx.commit();
		}
		dao.getSession().flush();
		dao.getSession().clear();
		
	}
	

	public void attachClean(CtaResCuentaResumenes instance) {
		log.debug("attaching clean CtaResCuentaResumenes instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}