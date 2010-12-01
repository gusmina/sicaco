package com.cetia.inventario.hibernate;

import com.cetia.sicaco.hibernate.BaseHibernateDAO;
import com.cetia.sicaco.hibernate.HibernateSessionFactory;
import com.mad.utilidades.CuentaEstadoRes;
import com.mad.utilidades.CuentasReservas;
import com.mad.utilidades.Resumen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConEreEstadoResultados entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.inventario.hibernate.ConEreEstadoResultados
 * @author MyEclipse Persistence Tools
 */

public class ConEreEstadoResultadosDAO extends BaseHibernateDAO {
	public ConEreEstadoResultadosDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	private static final Log log = LogFactory
			.getLog(ConEreEstadoResultadosDAO.class);
	// property constants
	public static final String CUE_NOMBRE = "cueNombre";
	public static final String BANDA1 = "banda1";
	public static final String BANDA2 = "banda2";

	public void save(ConEreEstadoResultados transientInstance) {
		log.debug("saving ConEreEstadoResultados instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConEreEstadoResultados persistentInstance) {
		log.debug("deleting ConEreEstadoResultados instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConEreEstadoResultados findById(java.lang.Integer id) {
		log.debug("getting ConEreEstadoResultados instance with id: " + id);
		try {
			ConEreEstadoResultados instance = (ConEreEstadoResultados) getSession()
					.get(
							"com.cetia.inventario.hibernate.ConEreEstadoResultados",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConEreEstadoResultados instance) {
		log.debug("finding ConEreEstadoResultados instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.inventario.hibernate.ConEreEstadoResultados")
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
		log.debug("finding ConEreEstadoResultados instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConEreEstadoResultados as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	

	public List findByCueNombre(Object cueNombre) {
		return findByProperty(CUE_NOMBRE, cueNombre);
	}

	public List findByBanda1(Object banda1) {
		return findByProperty(BANDA1, banda1);
	}

	public List findByBanda2(Object banda2) {
		return findByProperty(BANDA2, banda2);
	}

	public List<ConEreEstadoResultados> findAll() {
		log.debug("finding all ConEreEstadoResultados instances");
		try {
			String queryString = "from ConEreEstadoResultados";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConEreEstadoResultados merge(ConEreEstadoResultados detachedInstance) {
		log.debug("merging ConEreEstadoResultados instance");
		try {
			ConEreEstadoResultados result = (ConEreEstadoResultados) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConEreEstadoResultados instance) {
		log.debug("attaching dirty ConEreEstadoResultados instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConEreEstadoResultados instance) {
		log.debug("attaching clean ConEreEstadoResultados instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	public List getConf(String mes, String anio){		

		String sql =
		"SELECT "+
			 " cte.cet_inf cetInf, "+
			 " cte.cet_posc cetPos, "+
			 " cte.cet_banda cetBan, "+
			 " cue.cue_nombre cueNombre, "+
			 " sac.sac_saldo_a_la_fecha saldo, "+
			 " sac.sac_fecha fecha "+
			" "+
		"FROM "+
			"  con_cte_configuracion_estado_financiero cte, "+
			"  con_cue_cuenta cue, "+
			"  con_sac_saldos_anteriores_cuenta sac "+
		"WHERE "+
			" cte.cue_id = sac.cue_id and "+
			"  cue.cue_id = cte.cue_id and "+
			"  month(sac.sac_fecha) = "+mes+" and "+
			"  year(sac.sac_fecha) = "+anio+" and " +
			"  cte.cet_banda <> 7 "+
			
		"ORDER BY " +
			" cetBan, cet_posc";
	 
		String jdbcDriver = "com.mysql.jdbc.Driver";
		List<CuentaEstadoRes> l = new ArrayList<CuentaEstadoRes>(); ;
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				CuentaEstadoRes cuentaEs = new CuentaEstadoRes();
				String cetInf = rs.getString("cetInf");
				Integer ctePos = rs.getInt("cetPos");
				Integer cetBan = rs.getInt("cetBan");
				String cueNombre = rs.getString("cueNombre");
				Double saldo = rs.getDouble("saldo");
				Date fecha = rs.getDate("fecha");
				cuentaEs.setCetBan(cetBan);
				cuentaEs.setCetInf(cetInf);
				cuentaEs.setCtePos(ctePos);
				cuentaEs.setCueNombre(cueNombre);
				cuentaEs.setFecha(fecha);
				cuentaEs.setSaldo(saldo);
				l.add(cuentaEs);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	return l;
	}
	
	public List getReservas(){		

		String sql =
			" SELECT "+
			"  cue.cue_nombre nombre, cue_porcentaje porc "+
			"FROM "+
			"  con_cte_configuracion_estado_financiero cet,  "+
			"  con_cue_cuenta cue  "+
			"where  "+
			"  cue.cue_id = cet.cue_id and "+
			"  cet_banda = 7 "+
			"ORDER BY "+
			"  cet.cet_posc ;";

	 
		String jdbcDriver = "com.mysql.jdbc.Driver";
		List<CuentasReservas> l = new ArrayList<CuentasReservas>(); ;
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				CuentasReservas cuentaEs = new CuentasReservas();
				String nombre = rs.getString("nombre");
				Double porc = rs.getDouble("porc");
				cuentaEs.setNombre(nombre);
				cuentaEs.setPorc(porc);
				l.add(cuentaEs);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	return l;
	}
	

	public void deleteAll(){
		List<ConEreEstadoResultados> l = this.findAll();
		Iterator<ConEreEstadoResultados> i = l.iterator();
		Transaction tx = this.getSession().beginTransaction() ;
		while(i.hasNext()){
			this.delete((ConEreEstadoResultados) i.next());
			tx.commit();
		}
		this.getSession().flush();
		this.getSession().clear();
	}
	
}