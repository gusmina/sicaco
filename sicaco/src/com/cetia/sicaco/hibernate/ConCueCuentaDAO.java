package com.cetia.sicaco.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.mad.utilidades.Banco;
import com.mad.utilidades.Cuenta;
import com.mad.utilidades.Format;


/**
 * A data access object (DAO) providing persistence and search support for
 * ConCueCuenta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.ConCueCuenta
 * @author MyEclipse Persistence Tools
 */

public class ConCueCuentaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ConCueCuentaDAO.class);
	// property constants
	public static final String CUE_CODIGO_CUENTA = "cueCodigoCuenta";
	public static final String CUE_TIPO_CUENTA = "cueTipoCuenta";
	public static final String CUE_NOMBRE = "cueNombre";
	public static final String CUE_SALDO_ACTUAL = "cueSaldoActual";
	public static final String CUE_POSTEABLE = "cuePosteable";
	public static final String CUE_DESCRIPCION = "cueDescripcion";
	public static final String CUE_ESTADO = "cueEstado";
	public static final String CUE_PORCENTAJE = "cuePorcentaje";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	
	public ConCueCuentaDAO(Session session) {
		super(session);
	}

	public void save(ConCueCuenta transientInstance) {
		log.debug("saving ConCueCuenta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConCueCuenta persistentInstance) {
		log.debug("deleting ConCueCuenta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConCueCuenta findById(java.lang.Integer id) {
		log.debug("getting ConCueCuenta instance with id: " + id);
		try {
			ConCueCuenta instance = (ConCueCuenta) getSession().get(
					"com.cetia.sicaco.hibernate.ConCueCuenta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConCueCuenta instance) {
		log.debug("finding ConCueCuenta instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.ConCueCuenta").add(
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
		log.debug("finding ConCueCuenta instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConCueCuenta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCueCodigoCuenta(Object cueCodigoCuenta) {
		return findByProperty(CUE_CODIGO_CUENTA, cueCodigoCuenta);
	}

	public List findByCueTipoCuenta(Object cueTipoCuenta) {
		return findByProperty(CUE_TIPO_CUENTA, cueTipoCuenta);
	}

	public List findByCueNombre(Object cueNombre) {
		return findByProperty(CUE_NOMBRE, cueNombre);
	}

	public List findByCueSaldoActual(Object cueSaldoActual) {
		return findByProperty(CUE_SALDO_ACTUAL, cueSaldoActual);
	}

	public List findByCuePosteable(Object estado) {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "select cue.cueId,concat(cue.cueCodigoCuenta,' - ',cue.cueNombre)  from ConCueCuenta as cue "+
			"where cue.cueEstado = 1 and cue.cuePosteable=? order by cue.cueCodigoCuenta";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, estado);
			List lista =  queryObject.list();
			List resp = new ArrayList<ConCueCuenta>();
			Iterator it = lista.iterator();
			while(it.hasNext()){
				ConCueCuenta cuenta = new ConCueCuenta();
				Object[] item = (Object[])it.next();
				cuenta.setCueId(Integer.parseInt(item[0].toString()));
				cuenta.setCueNombre(item[1].toString());
				resp.add(cuenta);
			}
			return resp;
		}catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
		}
		
		
	}

	public List findByCueDescripcion(Object cueDescripcion) {
		return findByProperty(CUE_DESCRIPCION, cueDescripcion);
	}

	public List findByCueEstado(Object cueEstado) {
		return findByProperty(CUE_ESTADO, cueEstado);
	}

	public List findByCuePorcentaje(Object cuePorcentaje) {
		return findByProperty(CUE_PORCENTAJE, cuePorcentaje);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}
	
	public List findAll() {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "from ConCueCuenta cue ORDER BY cue.cueCodigoCuenta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConCueCuenta merge(ConCueCuenta detachedInstance) {
		log.debug("merging ConCueCuenta instance");
		try {
			ConCueCuenta result = (ConCueCuenta) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConCueCuenta instance) {
		log.debug("attaching dirty ConCueCuenta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConCueCuenta instance) {
		log.debug("attaching clean ConCueCuenta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAllParent() {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "from ConCueCuenta cue where cue.conCueCuenta.cueId = NULL";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public String getNextByNivel(int i, String cueId) {
		// TODO Auto-generated method stub
		String queryString = "";
		String siguiente = "";
		if(i == 1){
			queryString = "select max(cue.cueCodigoCuenta) as cueCod from ConCueCuenta cue where cue.conCueCuenta.cueId = NULL";
		}else{
			queryString = "select max(cue.cueCodigoCuenta) as cueCod from ConCueCuenta cue where cue.conCueCuenta.cueId ='" + cueId + "'";
		}
		try {
			Query query = getSession().createQuery(queryString);
            String ultimoIdentificador = (String) query.uniqueResult();
            ConEscEstructuraCuentasDAO escDao = new ConEscEstructuraCuentasDAO(getSession());
			ConEscEstructuraCuentas esc = escDao.findById(i);
			if(ultimoIdentificador == null){
				siguiente = Format.rellenarIzquierda("1", "0", esc.getEscTamanho());
			}else{
				while(ultimoIdentificador.length() > esc.getEscTamanho()){
					ultimoIdentificador = ultimoIdentificador.substring(1);
				}
				Integer next = Integer.valueOf(ultimoIdentificador) + 1;
				siguiente = Format.rellenarIzquierda(next.toString(), "0", esc.getEscTamanho()); 
			}
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return siguiente;
	}
	
	public List findByCueCodCue(Object posteable, int estado) {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "from ConCueCuenta cue where cue.cuePosteable = " + posteable +
								" AND cue.cueEstado = ? order by cue.cueCodigoCuenta";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0 , estado);
			List<ConCueCuenta> lista =  queryObject.list();
			Iterator<ConCueCuenta> iterador = lista.iterator();
			ArrayList<ConCueCuenta> lista2 = new ArrayList<ConCueCuenta>();
			while(iterador.hasNext()){
				ConCueCuenta cuenta = iterador.next();
				String nombre = cuenta.getCueCodigoCuenta() + " -- "+cuenta.getCueNombre();
				cuenta.setCueNombre(nombre);
				lista2.add(cuenta);
			}
			return lista2;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByCuentaConCodigo(Object cueCodigoCuenta) {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "from ConCueCuenta cue where cue.cueCodigoCuenta = '" + cueCodigoCuenta +
								"' AND cue.cueEstado = 1";
			Query queryObject = getSession().createQuery(queryString);
			Iterator<ConCueCuenta> iterador = queryObject.list().iterator();
			List<ConCueCuenta> lista = new ArrayList<ConCueCuenta>();
			lista.clear();
			while(iterador.hasNext()){
				ConCueCuenta cuenta = new ConCueCuenta();
				cuenta = iterador.next();
				String nombre = cuenta.getCueCodigoCuenta()+ " - " + cuenta.getCueNombre();
				cuenta.setCueNombre(nombre);
				lista.add(cuenta);
			}
			return lista;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Float findPorcentajeActual() {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "select sum(cue.cuePorcentaje) from ConCueCuenta cue " +
					"where cue.cueTipoCuenta = 2 " +
					"AND cue.cueEstado = 1 " +
					"and cue.cuePosteable = 0";
			Query queryObject = getSession().createQuery(queryString);
			Object obj = queryObject.uniqueResult();
			return (obj==null)?0:(Float) obj;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllCodNameByEstadoForEstConf(int estado) {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "select cue.cueId,concat(cue.cueCodigoCuenta,' - ',cue.cueNombre),cue.cueCodigoCuenta from ConCteConfiguracionEstadoFinanciero c right outer join c.conCueCuenta as cue "+
			"where cue.cueEstado = ? order by cue.cueCodigoCuenta ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, estado);
			List lista =  queryObject.list();
			List resp = new ArrayList<ConCueCuenta>();
			Iterator it = lista.iterator();
			while(it.hasNext()){
				ConCueCuenta cuenta = new ConCueCuenta();
				Object[] item = (Object[])it.next();
				cuenta.setCueId(Integer.parseInt(item[0].toString()));
				cuenta.setCueNombre(item[1].toString());
				cuenta.setCueCodigoCuenta(item[2].toString());
				resp.add(cuenta);
			}
			return resp;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ConCueCuenta>findCuentasReservaPosteables() {
		log.debug("finding all ConCueCuenta instances");
		try {
			String queryString = "from ConCueCuenta c where c.cuePorcentaje > 0 and c.cuePosteable = 0 and c.cueEstado = 1";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ConCueCuenta>findCuentas(int longitud,int tipo,int retroActiva) {
		try {
			String queryString = "	from ConCueCuenta c where length(c.cueCodigoCuenta) = ? and c.conTicTipoCuenta.ticId = ? "+
	"and c.cueTipoCuenta = ? and c.cueEstado = 1 ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, longitud);
			queryObject.setParameter(1, tipo);
			queryObject.setParameter(2, retroActiva);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Banco>  getBancos(){
		String sql = 
			"SELECT distinct cue_nombre as nombre, substring(cue_codigo_cuenta,6,2) as cod,'Bancos' as tipo "+
			"FROM con_cue_cuenta c "+
			"where(cue_codigo_cuenta like '11301%' or cue_codigo_cuenta like '11302%' or cue_codigo_cuenta like '11303%') and length(cue_codigo_cuenta) = 7 "+
			"UNION "+
			"SELECT distinct cue_nombre as nombre, substring(cue_codigo_cuenta,6,2) as cod,'Otras Instituciones' as tipo "+
			"FROM con_cue_cuenta c "+
			"where cue_codigo_cuenta like '11304%' and length(cue_codigo_cuenta) = 7 ";
		
		String jdbcDriver = "com.mysql.jdbc.Driver";
		List<Banco> l = new ArrayList<Banco>(); ;
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Banco banco = new Banco();
				String  nombre = rs.getString("nombre");
				String codigo = rs.getString("cod");
				String  tipo = rs.getString("tipo");
				banco.setNombre(nombre);
				banco.setTipo(tipo);
				banco.setCodigo(codigo);
				if(tipo.equals("Bancos")){
					banco.setIdBanco("0"+codigo);
				}else{
					banco.setIdBanco("1"+codigo);
				}
				
				l.add(banco);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return l;
	}
	
	public List<Cuenta> getCuentasDeBanco(String bancoId, String tipo){
		String sql="";
		List<Cuenta> l = new ArrayList<Cuenta>(); ;
		if (tipo.equalsIgnoreCase("0")){
			sql= "SELECT cue_codigo_cuenta as cueId, cue_nombre as nombre, cue_saldo_actual as saldo FROM con_cue_cuenta c " 
				+" where(cue_codigo_cuenta like '11301"+bancoId+"%' or cue_codigo_cuenta like '11302"+bancoId+"%' or cue_codigo_cuenta like '11303"+bancoId+"%') and length(cue_codigo_cuenta) = 9";
		}
		if(tipo.equalsIgnoreCase("1")){
			sql="Select cue_codigo_cuenta as cueId, cue_nombre as nombre,cue_saldo_actual as saldo FROM con_cue_cuenta c "
				+" where (cue_codigo_cuenta like '11304"+bancoId+"%') and length(cue_codigo_cuenta) = 9; ";
			
		}
		if(sql.equals(""))return l;
		String jdbcDriver = "com.mysql.jdbc.Driver";
		try {
			
			Class.forName(jdbcDriver);
			String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
			String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
			String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Cuenta cuenta = new Cuenta();
				String cueId = rs.getString("cueId");
				String  nombre = rs.getString("nombre");
				Double saldo = rs.getDouble("saldo");
				
				cuenta.setCodigo(cueId);
				cuenta.setNombre(nombre);
				cuenta.setSaldo(saldo);
				l.add(cuenta);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return l;
		
	}

	
}