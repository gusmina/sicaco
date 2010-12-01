package com.cetia.sicaco.hibernate;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mad.utilidades.Format;
import com.mad.utilidades.MysqlProcedureException;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecPerPersona entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecPerPersona
 * @author MyEclipse Persistence Tools
 */

public class SecPerPersonaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecPerPersonaDAO.class);
	// property constants
	public static final String PER_PRIMER_NOMBRE = "perPrimerNombre";
	public static final String PER_SEGUNDO_NOMBRE = "perSegundoNombre";
	public static final String PER_TERCER_NOMBRE = "perTercerNombre";
	public static final String PER_PRIMER_APELLIDO = "perPrimerApellido";
	public static final String PER_SEGUNDO_APELLIDO = "perSegundoApellido";
	public static final String PER_APELLIDO_CASADA = "perApellidoCasada";
	public static final String PER_GENERO = "perGenero";
	public static final String PER_LUGAR_NACIMIENTO = "perLugarNacimiento";
	public static final String PER_NIT = "perNit";
	public static final String PER_DUI = "perDui";
	public static final String PER_MUNICIPIO = "perMunicipio";
	//public static final String PER_DEPARTAMENTO = "perDepartamento";
	public static final String PER_CALLE = "perCalle";
	public static final String PER_COLONIA_BARRIO = "perColoniaBarrio";
	public static final String PER_CODIGO_POSTAL = "perCodigoPostal";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String PER_ESTADO = "perEstado";
	public static final String PER_DIRECCION_COMPLETA = "perDireccionCompleta";
	
	public SecPerPersonaDAO(Session session) {
		super(session);
	}

	public void save(SecPerPersona transientInstance) {
		log.debug("saving SecPerPersona instance");
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

	public void delete(SecPerPersona persistentInstance) {
		log.debug("deleting SecPerPersona instance");
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

	public SecPerPersona findById(java.lang.String id) {
		log.debug("getting SecPerPersona instance with id: " + id);
		try {
			SecPerPersona instance = (SecPerPersona) getSession().get(
					"com.cetia.sicaco.hibernate.SecPerPersona", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	public List<SecPerPersona> findByDuiOrNit(SecPerPersona persona){
		List<SecPerPersona> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecPerPersona.class);
		
		
		if (persona.getPerNit() != null && !persona.getPerNit().trim().equals("") && persona.getPerDui() != null && !persona.getPerDui().trim().equals("")) {
			criteria.add(Restrictions.or(Restrictions.eq("perNit", persona.getPerNit()),
					Restrictions.eq("perDui", persona.getPerDui())));
		}
		
		lst = (List<SecPerPersona>)criteria.getExecutableCriteria(getSession()).list();
		return lst;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SecPerPersona> findByCriteria(SecPerPersona persona,int rowStart,int rowEnd){
		log.debug("finding SecPerPersona instance by criteria");
		List<SecPerPersona> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecPerPersona.class);
		if(persona.getCodigoUsuario()!= null && !persona.getCodigoUsuario().trim().equals("")){
			criteria.add(Restrictions.like("",persona.getCodigoUsuario()));
		}
		if (persona.getPerNit() != null && !persona.getPerNit().trim().equals("")) {
 			criteria.add(Restrictions.like("perNit", persona.getPerNit()));
		}
		if (persona.getPerDui() != null && !persona.getPerDui().trim().equals("")) {
			criteria.add(Restrictions.like("perDui", persona.getPerDui()));
		}
		if (persona.getPerPrimerNombre() != null && !persona.getPerPrimerNombre().trim().equals("")) {
			criteria.add(Restrictions.like("perPrimerNombre","%" + persona.getPerPrimerNombre() + "%"));
		}
		if (persona.getPerSegundoNombre() != null && !persona.getPerSegundoNombre().trim().equals("")) {
			criteria.add(Restrictions.like("perSegundoNombre", "%" + persona.getPerSegundoNombre() + "%"));
		}
		if (persona.getPerPrimerApellido() != null && !persona.getPerPrimerApellido().trim().equals("")) {
			criteria.add(Restrictions.like("perPrimerApellido","%" + persona.getPerPrimerApellido() + "%"));
		}
		if (persona.getPerSegundoApellido() != null && !persona.getPerSegundoApellido().trim().equals("")) {
			criteria.add(Restrictions.like("perSegundoApellido","%" + persona.getPerSegundoApellido() + "%"));
		}
		lst = (List<SecPerPersona>)criteria.getExecutableCriteria(getSession()).setFirstResult(rowStart).setMaxResults(rowEnd-rowStart).list();
		return lst;
	}
	
	
	@SuppressWarnings("deprecation")
	public void callSP_DELETE_PERSONA(String perId)throws MysqlProcedureException{
			if(perId != null && !perId.trim().equals("")){
				try {
					@SuppressWarnings("unused")
					CallableStatement mysqlProc = getSession().connection().prepareCall("call SP_DELETE_PERSONA(?)");
					mysqlProc.setString(1, perId);
					mysqlProc.execute();
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					throw new MysqlProcedureException("errors.ai.persona.delete");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new MysqlProcedureException("errors.ai.persona.delete");
				}
			}
	}
	
	public String generarId() {
		log.debug("Se generara un nuevo id");
		String id = null;
		try {
			String queryString = "select max(persona.perId) as perId from SecPerPersona persona";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Query query = getSession().createQuery(queryString);
			String ultimoId = (String) query.uniqueResult();
			if(ultimoId != null){
				String soloFecha = ultimoId.substring(0,8);
				String hoy = sdf.format(new Date());
				if(soloFecha.equals(hoy)) {
					Integer corr = (Integer.parseInt(ultimoId.substring(8))+1);
				    id = hoy+Format.formatNumeroFactura(corr,4);
				}else {
					id= hoy + "0000";
				}
			}else{
				id = sdf.format(new Date())+"0000";//si el ultimo id viene nulo...
			}
			return id ;
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
	}
	
	public List findByExample(SecPerPersona instance) {
		log.debug("finding SecPerPersona instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecPerPersona").add(
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
		log.debug("finding SecPerPersona instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecPerPersona as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPerPrimerNombre(Object perPrimerNombre) {
		return findByProperty(PER_PRIMER_NOMBRE, perPrimerNombre);
	}

	public List findByPerSegundoNombre(Object perSegundoNombre) {
		return findByProperty(PER_SEGUNDO_NOMBRE, perSegundoNombre);
	}

	public List findByPerTercerNombre(Object perTercerNombre) {
		return findByProperty(PER_TERCER_NOMBRE, perTercerNombre);
	}

	public List findByPerPrimerApellido(Object perPrimerApellido) {
		return findByProperty(PER_PRIMER_APELLIDO, perPrimerApellido);
	}

	public List findByPerSegundoApellido(Object perSegundoApellido) {
		return findByProperty(PER_SEGUNDO_APELLIDO, perSegundoApellido);
	}

	public List findByPerApellidoCasada(Object perApellidoCasada) {
		return findByProperty(PER_APELLIDO_CASADA, perApellidoCasada);
	}

	public List findByPerGenero(Object perGenero) {
		return findByProperty(PER_GENERO, perGenero);
	}

	public List findByPerLugarNacimiento(Object perLugarNacimiento) {
		return findByProperty(PER_LUGAR_NACIMIENTO, perLugarNacimiento);
	}

	public List findByPerNit(Object perNit) {
		return findByProperty(PER_NIT, perNit);
	}

	public List findByPerDui(Object perDui) {
		return findByProperty(PER_DUI, perDui);
	}

	public List findByPerMunicipio(Object perMunicipio) {
		return findByProperty(PER_MUNICIPIO, perMunicipio);
	}
/*
	public List findByPerDepartamento(Object perDepartamento) {
		return findByProperty(PER_DEPARTAMENTO, perDepartamento);
	}
*/
	public List findByPerCalle(Object perCalle) {
		return findByProperty(PER_CALLE, perCalle);
	}

	public List findByPerColoniaBarrio(Object perColoniaBarrio) {
		return findByProperty(PER_COLONIA_BARRIO, perColoniaBarrio);
	}

	public List findByPerCodigoPostal(Object perCodigoPostal) {
		return findByProperty(PER_CODIGO_POSTAL, perCodigoPostal);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}
	
	public List findByPerEstado(Object perEstado) {
		return findByProperty(PER_ESTADO, perEstado);
	}
	
	public List findByPerDireccionCompleta(Object perDireccionCompleta) {
		return findByProperty(PER_DIRECCION_COMPLETA, perDireccionCompleta);
	}

	public List findAll(int max) {
		log.debug("finding all SecPerPersona instances");
		try {
			String queryString = "from SecPerPersona order by perId desc";
			Query queryObject = getSession().createQuery(queryString).setMaxResults(max);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecPerPersona merge(SecPerPersona detachedInstance) {
		log.debug("merging SecPerPersona instance");
		try {
			SecPerPersona result = (SecPerPersona) getSession().merge(
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

	public void attachDirty(SecPerPersona instance) {
		log.debug("attaching dirty SecPerPersona instance");
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

	public void attachClean(SecPerPersona instance) {
		log.debug("attaching clean SecPerPersona instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findAllBySucursal(int sucId) {
		log.debug("finding all SecPerPersona instances");
		try {
			String queryString = "from SecPerPersona p where p.secSucSucursal.sucId = " + sucId;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCount() {
		log.debug("counting all rows of SecPerPersona");
		try {
			String queryString = "select count(p.perId)from SecPerPersona p";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SecPerPersona> findAll(int rowStart,int rowEnd) {
		log.debug("finding all SecPerPersona instances");
		try {
			String queryString = "from SecPerPersona order by perId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List<CtaAscAsociado> findAllbyAscAsociadoPadre(String AscPadre, int rowStart,int rowEnd) {
		log.debug("finding all CtaAscAsociado hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaAscAsociado hse where hse.ascAsociadoPadre = ? order by hse.ascId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			queryObject.setParameter(0, AscPadre);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Integer getTotalRowCountbyAscAsociadoPadre(String ascPadre) {
		log.debug("counting all rows of CtaAscAsociado");
		try {
			String queryString = "select count(hse.ascId) from CtaAscAsociado hse where hse.ascAsociadoPadre = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascPadre);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCountPersonaByCriteria(SecPerPersona persona){
		log.debug("finding SecPerPersona instance by criteria");
		List<SecPerPersona> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecPerPersona.class);
		if(persona.getCodigoUsuario()!= null && !persona.getCodigoUsuario().trim().equals("")){
			criteria.add(Restrictions.like("",persona.getCodigoUsuario()));
		}
		if (persona.getPerNit() != null && !persona.getPerNit().trim().equals("")) {
 			criteria.add(Restrictions.like("perNit", persona.getPerNit()));
		}
		if (persona.getPerDui() != null && !persona.getPerDui().trim().equals("")) {
			criteria.add(Restrictions.like("perDui", persona.getPerDui()));
		}
		if (persona.getPerPrimerNombre() != null && !persona.getPerPrimerNombre().trim().equals("")) {
			criteria.add(Restrictions.like("perPrimerNombre","%" + persona.getPerPrimerNombre() + "%"));
		}
		if (persona.getPerSegundoNombre() != null && !persona.getPerSegundoNombre().trim().equals("")) {
			criteria.add(Restrictions.like("perSegundoNombre", "%" + persona.getPerSegundoNombre() + "%"));
		}
		if (persona.getPerPrimerApellido() != null && !persona.getPerPrimerApellido().trim().equals("")) {
			criteria.add(Restrictions.like("perPrimerApellido","%" + persona.getPerPrimerApellido() + "%"));
		}
		if (persona.getPerSegundoApellido() != null && !persona.getPerSegundoApellido().trim().equals("")) {
			criteria.add(Restrictions.like("perSegundoApellido","%" + persona.getPerSegundoApellido() + "%"));
		}
		criteria.setProjection(Projections.count("perId"));
		return (Integer) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}
}