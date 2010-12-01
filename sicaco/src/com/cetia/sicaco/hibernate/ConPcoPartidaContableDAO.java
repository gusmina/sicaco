package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.BigIntegerType;
import org.hibernate.type.Type;

import com.cetia.sicaco.cuentaCorriente.struts.form.PrestamoForm;
import com.mad.utilidades.PartidaDesc;
import com.mad.utilidades.Resumen;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConPcoPartidaContable entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConPcoPartidaContable
 * @author MyEclipse Persistence Tools
 */

public class ConPcoPartidaContableDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ConPcoPartidaContableDAO.class);
	// property constants
	public static final String PCO_COMPROBANTE_PARTIDA = "pcoComprobantePartida";
	public static final String PCO_ESTADO = "pcoEstado";
	public static final String PCO_OTRO_CONCEPTO = "pcoOtroConcepto";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String PCO_CHEQUE_PENDIENTE = "pcoChequePendiente";

	public ConPcoPartidaContableDAO(Session session) {
		super(session);
	}

	public void save(ConPcoPartidaContable transientInstance) {
		log.debug("saving ConPcoPartidaContable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConPcoPartidaContable persistentInstance) {
		log.debug("deleting ConPcoPartidaContable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConPcoPartidaContable findById(Long id) {
		log.debug("getting ConPcoPartidaContable instance with id: " + id);
		try {
			ConPcoPartidaContable instance = (ConPcoPartidaContable) getSession()
					.get("com.cetia.sicaco.hibernate.ConPcoPartidaContable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConPcoPartidaContable instance) {
		log.debug("finding ConPcoPartidaContable instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConPcoPartidaContable").add(
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
		log.debug("finding ConPcoPartidaContable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConPcoPartidaContable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List<ConPcoPartidaContable> findByTipoPartidaFecha(Date fecha,int tpaId) {
		try {
			String queryString = "from ConPcoPartidaContable par where DATE_FORMAT(par.pcoFechaIngresoPartida,'%d-%m-%Y') = ? and par.conTpaTipoPartida.tpaId=? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, new SimpleDateFormat("dd-MM-yyyy").format(fecha));
			queryObject.setParameter(1, tpaId);
			return  queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List<ConPcoPartidaContable> findByCompFecha(int anio, int mes, int comp) {
		try {
			String queryString = "from ConPcoPartidaContable par where " +
			"MONTH(par.pcoFechaIngresoPartida) = "+mes+" and YEAR(par.pcoFechaIngresoPartida)= "+anio+" " +
					"and par.pcoComprobantePartida = "+comp;
			Query queryObject = getSession().createQuery(queryString);
			return  queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public int getLastComprobante(int anio, int mes) {
		log.debug("finding comprobante partida instance");
		try {
			String queryString = "select max(model.pcoComprobantePartida) from ConPcoPartidaContable as model where  "
					+ "MONTH(model.pcoFechaIngresoPartida)" + "= ?  and YEAR(model.pcoFechaIngresoPartida)= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, mes);
			queryObject.setParameter(1, anio);
			Object obj = queryObject.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				return value;
			}else {
				return  new Integer(0);
			} 
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	
	
	public Date getLastFechaPartidaPorEstado(String estado) {
		log.debug("finding ultima partida que posea estado de "+ estado +", por haber sido cerrada\n");
		try {
			String queryString = "select distinct max(model.pcoFechaIngresoPartida) from ConPcoPartidaContable as model where  "
						+ " model.pcoEstado='" +estado +"'"; //	" group by model.pcoFechaIngresoPartida";
			Query queryObject = getSession().createQuery(queryString);
			//queryObject.setParameter(0, estado);
			Object obj = queryObject.uniqueResult();
			if(obj!=null){
				return (Date)obj;
			}else 	return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public ConPcoPartidaContable getLastPartidaPorEstado(String estado) {
		log.debug("finding ultima partida que posea estado de "+ estado +", por haber sido cerrada\n");
		try {
			String queryString = "from ConPcoPartidaContable as model where  "
						+ " model.pcoFechaIngresoPartida= " +
						"(select max(m.pcoFechaIngresoPartida)  "+ "from  ConPcoPartidaContable as m  " + 
						"where m.pcoEstado=" +" ?	) "; //group by m.pcoFechaIngresoPartida
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, estado);
			List lista = queryObject.list();
			return (!lista.isEmpty()?(ConPcoPartidaContable)lista.get(0):null);
			/*Object obj = queryObject.list();
			if(obj!=null){
				List<ConPcoPartidaContable> lista =  (List<ConPcoPartidaContable>)obj;
				int indice = lista.size();
				indice --;
				return lista.get(indice);
			}*/
			//return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPcoComprobantePartida(Object pcoComprobantePartida) {
		return findByProperty(PCO_COMPROBANTE_PARTIDA, pcoComprobantePartida);
	}

	public List findByPcoEstado(Object pcoEstado) {
		return findByProperty(PCO_ESTADO, pcoEstado);
	}

	public List findByPcoOtroConcepto(Object pcoOtroConcepto) {
		return findByProperty(PCO_OTRO_CONCEPTO, pcoOtroConcepto);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findByPcoChequePendiente(Object pcoChequePendiente) {
		return findByProperty(PCO_CHEQUE_PENDIENTE, pcoChequePendiente);
	}

	public List findAll() {
		log.debug("finding all ConPcoPartidaContable instances");
		try {
			String queryString = "from ConPcoPartidaContable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConPcoPartidaContable merge(ConPcoPartidaContable detachedInstance) {
		log.debug("merging ConPcoPartidaContable instance");
		try {
			ConPcoPartidaContable result = (ConPcoPartidaContable) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConPcoPartidaContable instance) {
		log.debug("attaching dirty ConPcoPartidaContable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConPcoPartidaContable instance) {
		log.debug("attaching clean ConPcoPartidaContable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConPcoPartidaContable> findPartidasChequesPendientes() {
		log.debug("Ingreso a busqueda de partidas por cheques pendientes");
		try {
			String query = " FROM ConPcoPartidaContable h " +
					       "WHERE h.conTpaTipoPartida.tpaId = 2 " +
					       "  AND h.pcoChequePendiente = 0";
			Query queryList = getSession().createQuery(query);
			return queryList.list();
		} catch (RuntimeException re) {
			log.error("Ingreso a busqueda de partidas por cheques pendientes failed", re);
			throw re;
		}
	}
	
	public List<ConPcoPartidaContable> findPartidasByCriteria(ConPcoPartidaContable partida,String fechaFin,int rowEnd,int rowStart, String menu) {
		try {
			List<ConPcoPartidaContable> lst = null;
			String queryString = "from ConPcoPartidaContable pco where pco.pcoId is not null ";
			
			if(menu!=null && menu.equals("1")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				queryString += " and month(pco.pcoFechaIngresoPartida) = month('"+sdf.format(new Date())+"')" +
							  " and year(pco_fecha_ingreso_partida) = year('"+sdf.format(new Date())+"')";
			}else{
				/*DetachedCriteria criteria = DetachedCriteria.forClass(ConPcoPartidaContable.class);*/
				//criteria.createAlias("conTpaTipoPartida", "tipo").createAlias("conCpaConceptoPartida", "concepto");
				if(partida.getPcoComprobantePartida()!=null && partida.getPcoComprobantePartida() > 0) {
					queryString += " and pco.pcoComprobantePartida= "+partida.getPcoComprobantePartida();
					/*criteria.add(Restrictions.eq("pcoComprobantePartida",partida.getPcoComprobantePartida()));*/
				}
				if(partida.getConCpaConceptoPartida().getCpaId()!=null) {
					/*criteria.add(Restrictions.like("concepto.cpaId", partida.getConCpaConceptoPartida().getCpaId()));*/
					/*criteria.add(Restrictions.sqlRestriction(
							
							"{alias}.cpa_id  = ?",
							new Object[]{partida.getConCpaConceptoPartida().getCpaId()}
							,new Type[]{Hibernate.INTEGER}
					));*/
					if(partida.getConCpaConceptoPartida().getCpaId() == -1) queryString += " and pco.conCpaConceptoPartida.cpaId = null";
					if(partida.getConCpaConceptoPartida().getCpaId() > 0) queryString += " and pco.conCpaConceptoPartida.cpaId= "+partida.getConCpaConceptoPartida().getCpaId();
				}
				if(partida.getConTpaTipoPartida().getTpaId()!=null && partida.getConTpaTipoPartida().getTpaId() > 0) {
					/*criteria.add(Restrictions.like("tipo.tpaId", partida.getConCpaConceptoPartida().getCpaId()));*/
					/*criteria.add(Restrictions.sqlRestriction(					
							"{alias}.tpa_id  = ?",
							new Object[]{partida.getConTpaTipoPartida().getTpaId()}
							,new Type[]{Hibernate.INTEGER}
					));*/
					queryString += " and pco.conTpaTipoPartida.tpaId="+partida.getConTpaTipoPartida().getTpaId();
				}
				if(fechaFin!=null && partida.getPcoFechaIngresoPartida() != null){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String fechaIni = sdf.format(partida.getPcoFechaIngresoPartida());
						
						String [] datos = fechaFin.split("-");
						String fechaF = datos[2] + "-" + datos[1] + "-" + datos [0]; 
							
						/*criteria.add(Restrictions.sqlRestriction(
								
								"DATE_FORMAT({alias}.pco_fecha_ingreso_partida,'%d-%m-%Y')  between ? and ?",
								new Object[]{fechaIni,fechaFin}
								,new Type[]{Hibernate.STRING,Hibernate.STRING}
						));*/
						queryString += " and date(pco.pcoFechaIngresoPartida) between date('"+fechaIni + "') and date('"+fechaF +"')  ";
				}
				if(fechaFin!= null && partida.getPcoFechaIngresoPartida()  == null){
					/*criteria.add(Restrictions.sqlRestriction(
							"DATE_FORMAT({alias}.pco_fecha_ingreso_partida,'%d-%m-%Y') = ?",fechaFin
							,Hibernate.STRING
					));*/
					String [] datos = fechaFin.split("-");
					String fechaF = datos[2] + "-" + datos[1] + "-" + datos [0]; 
					
					queryString += " and DATE_FORMAT(pco.pcoFechaIngresoPartida,'%Y-%m-%d')= '"+fechaF+"' ";
				}
				if(partida.getPcoFechaIngresoPartida() != null && (fechaFin == null || fechaFin.trim().equals(""))){
					/*criteria.add(Restrictions.sqlRestriction(
							"DATE_FORMAT({alias}.pco_fecha_ingreso_partida,'%d-%m-%Y') = ?",
							new SimpleDateFormat("dd-MM-yyyy").format(partida.getPcoFechaIngresoPartida())
							,Hibernate.STRING
					));*/
					queryString += " and pco.pcoFechaIngresoPartida >= '"+new SimpleDateFormat("yyyy-MM-dd").format(partida.getPcoFechaIngresoPartida())+"' ";
					
				}
			}
			queryString += " order by date(pco.pcoFechaIngresoPartida) desc,pco.pcoComprobantePartida desc";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			if(rowEnd > 0)	queryObject.setMaxResults(rowEnd-rowStart);
			lst = queryObject.list();
			return lst;
		
			/*lst  = (List<ConPcoPartidaContable>) criteria.getExecutableCriteria(getSession()).setFirstResult(rowStart).setMaxResults(rowEnd-rowStart).list();
			return lst;*/
	} catch (RuntimeException re) {
		log.error("find all failed", re);
		throw re;
	}
}
	
	public List<ConPcoPartidaContable> findByModuloFecha(Date fecha,int modulo) {
		try {
			String queryString = "from ConPcoPartidaContable par " +
					"where DATE_FORMAT(par.pcoFechaIngresoPartida,'%d-%m-%Y') = ? " +
					"and par.pcoModulo=? and par.conTpaTipoPartida.tpaId <> 3 " +
					"and par.ctaChkChequePrestamo.chkId is null " +
					"order by par.pcoId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, new SimpleDateFormat("dd-MM-yyyy").format(fecha));
			queryObject.setParameter(1, modulo);
			return  queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<ConPcoPartidaContable> findByModuloFechaTipo(Date fecha,int modulo,int tipoPar) {
		try {
			String queryString = "from ConPcoPartidaContable par where DATE_FORMAT(par.pcoFechaIngresoPartida,'%d-%m-%Y') = ? and par.pcoModulo=? and par.conTpaTipoPartida.tpaId = ? order by par.pcoId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, new SimpleDateFormat("dd-MM-yyyy").format(fecha));
			queryObject.setParameter(1, modulo);
			queryObject.setParameter(2, tipoPar);
			return  queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	
	
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia - Partida Contable");
		try {
			String sql = "select max(par.pcoId) + 1  as id from ConPcoPartidaContable par";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else if(obj instanceof Long){
				Long value = (Long) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida - Partida Contable", re);
			throw re;
		}
		return id;
	} 
	
	public int llamadasProcedimientosBalanza(int mes,int anio) {
		int resultado=0;
		int mesA = 0;
		if(mes==1){
			mesA=12;
		}else{
			mesA=mes-1;
		}
		try {
			//Conexion jdbc normal
			String jdbcDriver = "com.mysql.jdbc.Driver";
			Class.forName(jdbcDriver);
			
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");

			Connection con = DriverManager.getConnection(url, user, pass);
			//Connection con = HibernateSessionFactory.getSession().connection();
			CallableStatement callStatement = con.prepareCall("{CALL sicacodb.obtenerBalanceInicial(?,?,?)}");
			
			callStatement.setInt(1, mes);
			callStatement.setInt(2, mesA);
			callStatement.setInt(3, anio);
			
			
			CallableStatement callStatement0 = null;
			if(mes <= 3 && anio == 2010)
				 callStatement0 = con.prepareCall("{CALL sicacodb.llamarPadres2()}");		
			else{
				callStatement0 = con.prepareCall("{CALL sicacodb.llamarPadres(?,?)}");
				callStatement0.setInt(1, mesA);
				callStatement0.setInt(2, anio);
			}
				 
		
			CallableStatement callStatement1 = con.prepareCall("{CALL sicacodb.balanceComprobacion()}");
			

			
			
			resultado = callStatement.executeUpdate();
			Boolean resultado2;
			resultado2 = callStatement.execute();
			con = DriverManager.getConnection(url, user, pass);
			if(resultado > 0){
				//se ejecuto bien el procedure, ejecutamos el otro
				resultado = callStatement0.executeUpdate();
				con = DriverManager.getConnection(url, user, pass);
				if(resultado > 0)
				resultado = callStatement1.executeUpdate();
				
			}
		} catch (RuntimeException re) {
			log.error("procedimiento sicacodb.obtenerBalanceInicial fallo....   ", re);
			throw re;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public void updateParaCierre(String usuario,Date fecha) {
		try {
			String queryString = "	update ConPcoPartidaContable set pcoEstado = 'F' ,audUsuarioModificacion = ?,audFechaModificacion = curdate() "
				+"where MONTH(pcoFechaIngresoPartida) = MONTH(?) and YEAR(pcoFechaIngresoPartida) = YEAR(?)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, usuario);
			queryObject.setParameter(1, fecha);
			queryObject.setParameter(2, fecha);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("update para cierre failed", re);
			throw re;
		}
	}
	
	public void updateParaApertura(String usuario,Date fecha) {
		try {
			String queryString = "	update ConPcoPartidaContable set pcoEstado = 'P' ,audUsuarioModificacion = ?,audFechaModificacion = curdate() "
				+"where MONTH(pcoFechaIngresoPartida) = MONTH(?) and YEAR(pcoFechaIngresoPartida) = YEAR(?)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, usuario);
			queryObject.setParameter(1, fecha);
			queryObject.setParameter(2, fecha);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("update para apertura fallido", re);
			throw re;
		}
	}
	
	
	public String obtenerEstado(int mes, int anio) {
		log.debug("Estado del cierre del mes ");
		try {
			String query = " FROM ConPcoPartidaContable h " +
			" where month(h.pcoFechaIngresoPartida) = "+mes+
			" and year(h.pcoFechaIngresoPartida) = "+anio+
			" group by h.pcoEstado ";
					       
			Query queryList = getSession().createQuery(query);
			List <ConPcoPartidaContable>partidas = queryList.list();
			if(partidas.size()> 0){
				return partidas.get(0).getPcoEstado();
			}
			return "X";
		} catch (RuntimeException re) {
			log.error("El metodo obtenerEstado ha fallado ", re);
			throw re;
		}
	}
	
	public List<PartidaDesc> obtenerDescuadres(int mes, int anio){
		String sql = " "+
		"Select * from ( "+
		"SELECT "+
		" pco.pco_comprobante_partida as comprobante, sum(dpa_valor_debe) as debe, sum(dpa_valor_haber) as haber, " +
		" pco.pco_fecha_ingreso_partida as fecha"+
		" FROM "+
		"  con_pco_partida_contable pco, "+
		"  con_dpa_detalle_partida dpa " +
		" where " +
		"   month(pco_fecha_ingreso_partida) = "+mes+" and " +
		"   year(pco_fecha_ingreso_partida)= "+anio+" and "+
		"   pco.pco_id = dpa.pco_id "+
		"   group by pco.pco_id "+
		" )as q "+
		" where abs(debe-haber) >= 0.005";
		
		String jdbcDriver = "com.mysql.jdbc.Driver";
		List<PartidaDesc> l = new ArrayList<PartidaDesc>(); ;
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				PartidaDesc descuadre = new PartidaDesc();
				descuadre.setComprobante(rs.getInt("comprobante"));
				descuadre.setDebe(rs.getDouble("debe"));
				descuadre.setHaber(rs.getDouble("haber"));
				descuadre.setFechaIn(rs.getDate("fecha"));
				l.add(descuadre);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			return l;
	}
	
	
	public Integer getTotalRowCountPartidasByCriteria(ConPcoPartidaContable partida,String fechaFin, String menu) {
		try {
			String queryString ="select pco.pcoId from ConPcoPartidaContable pco ";
			if(menu != null && menu.equals("1")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				queryString += " where month(pco.pcoFechaIngresoPartida) = month('"+sdf.format(new Date())+"')" +
				  " and year(pco_fecha_ingreso_partida) = year('"+sdf.format(new Date())+"')";				
			}else{
				String filtro = "";
				if(partida.getConTpaTipoPartida()!=null && partida.getConTpaTipoPartida().getTpaId()!=null && partida.getConTpaTipoPartida().getTpaId()>0){
					queryString += ", ConTpaTipoPartida tpa ";
				}
				if(partida.getPcoComprobantePartida()!=null && partida.getPcoComprobantePartida() > 0) {
					filtro += (!filtro.trim().equals(""))?" and ":"";
					filtro += " pco.pcoComprobantePartida=" + partida.getPcoComprobantePartida();
				}
				if(partida.getConCpaConceptoPartida().getCpaId()!=null) {
					
					if(partida.getConCpaConceptoPartida().getCpaId() == -1){
						filtro += (!filtro.trim().equals(""))?" and ":"";
						filtro += " pco.conCpaConceptoPartida.cpaId = null";
					}
					if(partida.getConCpaConceptoPartida().getCpaId() > 0){
						filtro += (!filtro.trim().equals(""))?" and ":"";
						filtro += " pco.conCpaConceptoPartida.cpaId = "+ partida.getConCpaConceptoPartida().getCpaId();
					}
				}
				if(partida.getConTpaTipoPartida().getTpaId()!=null && partida.getConTpaTipoPartida().getTpaId() > 0) {
					filtro += (!filtro.trim().equals(""))?" and ":"";
					filtro += " tpa.tpaId=pco.conTpaTipoPartida.tpaId and tpa.tpaId="+partida.getConTpaTipoPartida().getTpaId();
				}
				if(fechaFin!=null && partida.getPcoFechaIngresoPartida() != null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String fechaIni = sdf.format(partida.getPcoFechaIngresoPartida());	
					//filtro += (!filtro.trim().equals(""))?" and ":"";
					//filtro += " DATE_FORMAT(pco.pcoFechaIngresoPartida,'%d-%m-%Y')  between '"+ fechaIni+"' and '"+fechaFin+"'";
					
					String [] datos = fechaFin.split("-");
					String fechaF = datos[2] + "-" + datos[1] + "-" + datos [0];
					
					filtro += (!filtro.trim().equals(""))?" and ":"";
					filtro += " date(pco.pcoFechaIngresoPartida) between date('"+ fechaIni+"') and date('"+fechaF+"')";
					
				}
			
				if (fechaFin!= null && partida.getPcoFechaIngresoPartida()  == null) {
					//filtro += (!filtro.trim().equals(""))?" and ":"";
					//filtro += " DATE_FORMAT(pco.pcoFechaIngresoPartida,'%d-%m-%Y') = '"+fechaFin+"'";				
					
					String [] datos = fechaFin.split("-");
					String fechaF = datos[2] + "-" + datos[1] + "-" + datos [0]; 
					filtro += (!filtro.trim().equals(""))?" and ":"";
					filtro += " DATE_FORMAT(pco.pcoFechaIngresoPartida,'%Y-%m-%d') = '"+fechaF+"'";	
					
				}
				if (partida.getPcoFechaIngresoPartida() != null && (fechaFin == null || fechaFin.trim().equals(""))) {
					filtro += (!filtro.trim().equals(""))?" and ":"";
					filtro += " pco.pcoFechaIngresoPartida >= '"+
					new SimpleDateFormat("yyyy-MM-dd").format(partida.getPcoFechaIngresoPartida())+"'";
					
				}
				if(!filtro.trim().equals("")){
					queryString += " where "+filtro;
				}
			}
			
			Query queryObject = getSession().createQuery(queryString);
			
			return (Integer) queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Integer getTotalCerradasMes(Date date) {
		log.debug("Encontrando la cantidad de partidas cerradas en un mes");
		try {
			String queryString = "FROM ConPcoPartidaContable "+
								 "WHERE pcoEstado = 'F' AND MONTH(pcoFechaIngresoPartida) = MONTH(?) "+
								 "AND YEAR(pcoFechaIngresoPartida) = YEAR(?)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, date);
			queryObject.setParameter(1, date);

			Integer totalCerradas = (Integer) queryObject.list().size();
			return totalCerradas;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
}