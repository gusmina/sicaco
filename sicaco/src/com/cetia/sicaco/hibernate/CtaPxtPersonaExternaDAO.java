package com.cetia.sicaco.hibernate;

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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.mad.utilidades.Format;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaPxtPersonaExterna entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaPxtPersonaExterna
 * @author MyEclipse Persistence Tools
 */

public class CtaPxtPersonaExternaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaPxtPersonaExternaDAO.class);
	// property constants
	public static final String PXT_PRIMER_APELLIDO = "pxtPrimerApellido";
	public static final String PXT_SEGUNDO_APELLIDO = "pxtSegundoApellido";
	public static final String PXT_NOMBRES = "pxtNombres";
	public static final String PXT_DIRECCION = "pxtDireccion";
	public static final String PXT_SALARIO = "pxtSalario";
	public static final String PXT_DUI = "pxtDui";
	public static final String PXT_TRABAJO = "pxtTrabajo";
	public static final String PXT_JEFE_INMEDIATO = "pxtJefeInmediato";
	public static final String PXT_TELEFONO_CASA = "pxtTelefonoCasa";
	public static final String PXT_TELEFONO_OFICINA = "pxtTelefonoOficina";
	public static final String PXT_CODIGO_EMPLEADO = "pxtCodigoEmpleado";
	public static final String PXT_EMPRESA = "pxtEmpresa";
	public static final String PXT_EMAIL = "pxtEmail";

	public CtaPxtPersonaExternaDAO(Session session) {
		super(session);
	}

	public void save(CtaPxtPersonaExterna transientInstance) {
		log.debug("saving CtaPxtPersonaExterna instance");
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

	public void delete(CtaPxtPersonaExterna persistentInstance) {
		log.debug("deleting CtaPxtPersonaExterna instance");
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

	public CtaPxtPersonaExterna findById(java.lang.String id) {
		log.debug("getting CtaPxtPersonaExterna instance with id: " + id);
		try {
			CtaPxtPersonaExterna instance = (CtaPxtPersonaExterna) getSession()
					.get("com.cetia.sicaco.hibernate.CtaPxtPersonaExterna", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaPxtPersonaExterna instance) {
		log.debug("finding CtaPxtPersonaExterna instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaPxtPersonaExterna").add(
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
		log.debug("finding CtaPxtPersonaExterna instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaPxtPersonaExterna as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPxtPrimerApellido(Object pxtPrimerApellido) {
		return findByProperty(PXT_PRIMER_APELLIDO, pxtPrimerApellido);
	}

	public List findByPxtSegundoApellido(Object pxtSegundoApellido) {
		return findByProperty(PXT_SEGUNDO_APELLIDO, pxtSegundoApellido);
	}

	public List findByPxtNombres(Object pxtNombres) {
		return findByProperty(PXT_NOMBRES, pxtNombres);
	}

	public List findByPxtDireccion(Object pxtDireccion) {
		return findByProperty(PXT_DIRECCION, pxtDireccion);
	}

	public List findByPxtSalario(Object pxtSalario) {
		return findByProperty(PXT_SALARIO, pxtSalario);
	}

	public List findByPxtDui(Object pxtDui) {
		return findByProperty(PXT_DUI, pxtDui);
	}

	public List findByPxtTrabajo(Object pxtTrabajo) {
		return findByProperty(PXT_TRABAJO, pxtTrabajo);
	}

	public List findByPxtJefeInmediato(Object pxtJefeInmediato) {
		return findByProperty(PXT_JEFE_INMEDIATO, pxtJefeInmediato);
	}

	public List findByPxtTelefonoCasa(Object pxtTelefonoCasa) {
		return findByProperty(PXT_TELEFONO_CASA, pxtTelefonoCasa);
	}

	public List findByPxtTelefonoOficina(Object pxtTelefonoOficina) {
		return findByProperty(PXT_TELEFONO_OFICINA, pxtTelefonoOficina);
	}
	
	public List findByPxtCodigoEmpleado(Object pxtCodigoEmpleado) {
		return findByProperty(PXT_CODIGO_EMPLEADO, pxtCodigoEmpleado);
	}
	
	public List findByPxtEmpresa(Object pxtEmpresa) {
		return findByProperty(PXT_EMPRESA, pxtEmpresa);
	}
	
	public List findByPxtEmail(Object pxtEmail) {
		return findByProperty(PXT_EMAIL, pxtEmail);
	}

	public List findAll() {
		log.debug("finding all CtaPxtPersonaExterna instances");
		try {
			String queryString = "from CtaPxtPersonaExterna";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaPxtPersonaExterna merge(CtaPxtPersonaExterna detachedInstance) {
		log.debug("merging CtaPxtPersonaExterna instance");
		try {
			CtaPxtPersonaExterna result = (CtaPxtPersonaExterna) getSession()
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

	public void attachDirty(CtaPxtPersonaExterna instance) {
		log.debug("attaching dirty CtaPxtPersonaExterna instance");
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

	public void attachClean(CtaPxtPersonaExterna instance) {
		log.debug("attaching clean CtaPxtPersonaExterna instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public String generarId() {
		log.debug("Se generara un nuevo id");
		String id = null;
		try {
			String queryString = "select max(pxt.pxtId) as pxtId from CtaPxtPersonaExterna pxt";
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

	public Double totalDescuentos(String codEmpId) {
		log.debug("finding all CtaPxtPersonaExterna instances");
		try {
			String queryString = "" +
					"SELECT SUM(pre.preCuota) " +
					"FROM " +
					"CtaCasCuentaAsociado cas INNER JOIN cas.ctaPxtPersonaExterna as p " +
					"INNER JOIN cas.ctaPrePrestamo as pre " +
					    "WHERE "+
					        "cas.ctrEstEstado.estId=13 AND "+  
						"p.pxtCodigoEmpleado='"+codEmpId+"'";
			Query queryObject = getSession().createQuery(queryString);
			return (Double) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByAscInfo(CtaAscAsociado asociado) {
		ArrayList<CtaPxtPersonaExterna> pxtList = new ArrayList<CtaPxtPersonaExterna>();
		CtaEtrEmpresaTrabajoDAO empresaTrabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSession());
		CtaEtrEmpresaTrabajo trabajo = empresaTrabajoDAO.findById(asociado.getCtaDptDepartamentoTrabajo().getCtaEtrEmpresaTrabajo().getEtrId());
		CtaFxpFiadorPrestamoDAO fxpDao = new CtaFxpFiadorPrestamoDAO(getSession());
		List mismoCodigoEmp = fxpDao.findByCodigoYEmpresa(asociado.getAscCodigo(), trabajo.getEtrNombre().trim().toUpperCase());
		for (Iterator iterator = mismoCodigoEmp.iterator(); iterator.hasNext();) {
			CtaFxpFiadorPrestamo fxp = (CtaFxpFiadorPrestamo) iterator.next();
			CtaPxtPersonaExterna pxt = fxp.getCtaPxtPersonaExterna();
			if(pxt.getPxtTrabajo().trim().toUpperCase().equals(trabajo.getEtrNombre().trim().toUpperCase())){
				pxtList.add(pxt);
			}
		}
		return pxtList;
	}

	public List<CtaPxtPersonaExterna> findbyCriteria(CtaPxtPersonaExterna externa) {
		List<CtaPxtPersonaExterna> resp = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaPxtPersonaExterna.class);
		try{
			//criteria.createAlias("ctaAscAsociado", "asoc").createAlias("ctaPrePrestamo", "pre").createAlias("asoc.secPerPersona", "persona");
			if(externa.getPxtNombres()!=null && !externa.getPxtNombres().trim().equals("")){
				criteria.add(Restrictions.like("pxtNombres", "%" + externa.getPxtNombres() + "%"));
			}
			if(externa.getPxtPrimerApellido()!=null && !externa.getPxtPrimerApellido().trim().equals("")){
				criteria.add(Restrictions.like("pxtPrimerApellido", "%" + externa.getPxtPrimerApellido() + "%"));
			}
			resp  = criteria.getExecutableCriteria(getSession()).list();
		}catch (Exception e) {
			log.error("find by criteria failed", e);
			e.printStackTrace();
		}
		return resp;
	}
}