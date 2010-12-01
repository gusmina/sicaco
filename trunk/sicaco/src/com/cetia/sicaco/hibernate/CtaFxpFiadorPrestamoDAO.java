package com.cetia.sicaco.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaFxpFiadorPrestamo entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaFxpFiadorPrestamo
 * @author MyEclipse Persistence Tools
 */

public class CtaFxpFiadorPrestamoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaFxpFiadorPrestamoDAO.class);
	// property constants
	public static final String FXP_ESTADO = "fxpEstado";

	public CtaFxpFiadorPrestamoDAO(Session session) {
		super(session);
	}

	public void save(CtaFxpFiadorPrestamo transientInstance) {
		log.debug("saving CtaFxpFiadorPrestamo instance");
		try {
			getSession().save(transientInstance);
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CtaFxpFiadorPrestamo persistentInstance) {
		log.debug("deleting CtaFxpFiadorPrestamo instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CtaFxpFiadorPrestamo findById(java.lang.Integer id) {
		log.debug("getting CtaFxpFiadorPrestamo instance with id: " + id);
		try {
			CtaFxpFiadorPrestamo instance = (CtaFxpFiadorPrestamo) getSession()
					.get("com.cetia.sicaco.hibernate.CtaFxpFiadorPrestamo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaFxpFiadorPrestamo instance) {
		log.debug("finding CtaFxpFiadorPrestamo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaFxpFiadorPrestamo").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findFiadoresByPrestamo(String prestamoId,String ascId) {
		try {
			String queryString = "from CtaFxpFiadorPrestamo as fxp where fxp.ctaPrePrestamo.preId= ? and fxp.ctaAscAsociado.ascId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, prestamoId);
			queryObject.setParameter(1, ascId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<CtaFxpFiadorPrestamo> findFiadoresByPrestamoAprobados(String prestamoId, String estado) {
		try {
			String queryString = "from CtaFxpFiadorPrestamo as fxp where fxp.ctaPrePrestamo.preId= ? "
			+ "and fxp.fxpEstado = '"+estado+"'";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, prestamoId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CtaFxpFiadorPrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaFxpFiadorPrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFxpEstado(Object fxpEstado) {
		return findByProperty(FXP_ESTADO, fxpEstado);
	}

	public List findAll() {
		log.debug("finding all CtaFxpFiadorPrestamo instances");
		try {
			String queryString = "from CtaFxpFiadorPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaFxpFiadorPrestamo merge(CtaFxpFiadorPrestamo detachedInstance) {
		log.debug("merging CtaFxpFiadorPrestamo instance");
		try {
			CtaFxpFiadorPrestamo result = (CtaFxpFiadorPrestamo) getSession()
					.merge(detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CtaFxpFiadorPrestamo instance) {
		log.debug("attaching dirty CtaFxpFiadorPrestamo instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CtaFxpFiadorPrestamo instance) {
		log.debug("attaching clean CtaFxpFiadorPrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<CtaFxpFiadorPrestamo> findAllEmployees() {
		log.debug("finding all CtaFxpFiadorPrestamo instances");
		try {
			String queryString = "from CtaFxpFiadorPrestamo fxp where fxp.fxpEstado = 'C' " +
					"AND fxp.ctaPxtPersonaExterna is not NULL";
			Query queryObject = getSession().createQuery(queryString);
			if(queryObject.list().size() > 0){
				ArrayList<CtaFxpFiadorPrestamo> empleados = new ArrayList<CtaFxpFiadorPrestamo>();
				List fiadores = queryObject.list();
				ArrayList<CtaFxpFiadorPrestamo> fiadoresEmpleados = new ArrayList<CtaFxpFiadorPrestamo>();
				ArrayList<String> codigos = new ArrayList<String>();
				for (Iterator iterator = fiadores.iterator(); iterator
						.hasNext();) {
					CtaFxpFiadorPrestamo fiador = (CtaFxpFiadorPrestamo) iterator.next();
					if(fiador.getCtaPxtPersonaExterna().getPxtCodigoEmpleado() != null 
							&& !fiador.getCtaPxtPersonaExterna().getPxtCodigoEmpleado().trim().equals("")
							&& fiador.getCtaPxtPersonaExterna().getPxtEmpresa() != null 
							&& !fiador.getCtaPxtPersonaExterna().getPxtEmpresa().trim().equals("")){
						if(!codigos.contains(fiador.getCtaPxtPersonaExterna().getPxtCodigoEmpleado())){
							fiadoresEmpleados.add(fiador);
							codigos.add(fiador.getCtaPxtPersonaExterna().getPxtCodigoEmpleado());
						}
					}
				}
				if(fiadoresEmpleados.size()>0){
					CtaEtrEmpresaTrabajoDAO trabajoDAO = new CtaEtrEmpresaTrabajoDAO(getSession());
					List empresas = trabajoDAO.findAll();
					for (Iterator iterator = fiadoresEmpleados.iterator(); iterator
							.hasNext();) {
						CtaFxpFiadorPrestamo fiador = (CtaFxpFiadorPrestamo) iterator.next();
						for (Iterator iterator2 = empresas.iterator(); iterator2
								.hasNext();) {
							CtaEtrEmpresaTrabajo empresa = (CtaEtrEmpresaTrabajo) iterator2.next();
							if(fiador.getCtaPxtPersonaExterna().getPxtEmpresa().trim().toUpperCase()
									.equals(empresa.getEtrNombre().trim().toUpperCase())){
								empleados.add(fiador);
							}
						}
					}
					return empleados;
				}else{
					return null;
				}
			}else{
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaFxpFiadorPrestamo> findFiadorByCriteria(CtaFxpFiadorPrestamo fiador,int rowStart,int rowEnd){
		List<CtaFxpFiadorPrestamo> resp = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaFxpFiadorPrestamo.class);
		try{
			criteria.createAlias("ctaAscAsociado", "asoc").createAlias("ctaPrePrestamo", "pre").createAlias("asoc.secPerPersona", "persona");
			criteria.add(Restrictions.isNull("ctaPxtPersonaExterna"));
			SecPerPersona persona = fiador.getCtaAscAsociado().getSecPerPersona();
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				criteria.add(Restrictions.like("persona.perSegundoApellido","%" + persona.getPerSegundoApellido() + "%"));
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				criteria.add(Restrictions.like("persona.perPrimerNombre","%" + persona.getPerPrimerNombre() + "%"));
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				criteria.add(Restrictions.like("persona.perSegundoNombre","%" + persona.getPerSegundoNombre() + "%"));
			}
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				criteria.add(Restrictions.like("persona.perPrimerApellido","%" + persona.getPerPrimerApellido() + "%"));
			}
			if(fiador.getCtaPrePrestamo().getPreId()!=null && fiador.getCtaPrePrestamo().getPreId().length()>0) {//para buscar por codigo de prestamo
				criteria.add(Restrictions.like("pre.preId","%" +fiador.getCtaPrePrestamo().getPreId()+ "%"));
			}
			if(fiador.getCtaAscAsociado().getAscCodigoAsociado()!=null && fiador.getCtaAscAsociado().getAscCodigoAsociado().length()>0) {
				criteria.add(Restrictions.like("asoc.ascCodigoAsociado","%" + fiador.getCtaAscAsociado()+ "%"));
			}
			if(fiador.getCtaAscAsociado().getAscCodigo()!=null && fiador.getCtaAscAsociado().getAscCodigo().length()>0) {//para buscar por codigo de empleado
				criteria.add(Restrictions.like("asoc.ascCodigo","%" + fiador.getCtaAscAsociado().getAscCodigo()+ "%"));
			}
			criteria.setProjection(Projections.distinct( 
					Projections.projectionList()
					//.add(Projections.property("fxpId"),"fxpId")
					.add(Projections.property("ctaAscAsociado"),"ctaAscAsociado")
					//.add(Projections.property("fxpId"),"fxpId")
					)).setResultTransformer(new AliasToBeanResultTransformer(CtaFxpFiadorPrestamo.class));
			resp  = (List<CtaFxpFiadorPrestamo>) criteria.getExecutableCriteria(getSession()).setFirstResult(rowStart).setMaxResults(rowEnd-rowStart).list();
		}catch (Exception e) {
			log.error("find by criteria failed", e);
			e.printStackTrace();
		}
		return resp;
	}
	
	
	public List<CtaCasCuentaAsociado> findDeudores(String ascId ,int estId) {
		//Extrae los casCuenta correspondiente a las personas de las cuales un asociado es fiador,
		//permite buscar por estado del prestamo
		try {
			String queryString = "select c	from CtaCasCuentaAsociado c join c.ctaPrePrestamo p	join p.ctaFxpFiadorPrestamos f where f.ctaAscAsociado.ascId = ? and c.ctrEstEstado.estId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			queryObject.setParameter(1, estId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<CtaCasCuentaAsociado> findDeudores(String ascId) {
		//Extrae los casCuenta correspondiente a las personas de las cuales un asociado es fiador,
		try {
			String queryString = 
				"select distinct c " +
				"from CtaCasCuentaAsociado c join c.ctaPrePrestamo p	" +
				"join p.ctaFxpFiadorPrestamos f where f.ctaAscAsociado.ascId = ? " +
				"and c.ctrEstEstado.estId = 13"
				;
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCountFiadorByCriteria(CtaFxpFiadorPrestamo fiador){
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaFxpFiadorPrestamo.class);
		Integer resp = 0;
		try{
			criteria.createAlias("ctaAscAsociado", "asoc").createAlias("ctaPrePrestamo", "pre").createAlias("asoc.secPerPersona", "persona");
			criteria.add(Restrictions.isNull("ctaPxtPersonaExterna"));
			SecPerPersona persona = fiador.getCtaAscAsociado().getSecPerPersona();
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				criteria.add(Restrictions.like("persona.perSegundoApellido","%" + persona.getPerSegundoApellido() + "%"));
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				criteria.add(Restrictions.like("persona.perPrimerNombre","%" + persona.getPerPrimerNombre() + "%"));
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				criteria.add(Restrictions.like("persona.perSegundoNombre","%" + persona.getPerSegundoNombre() + "%"));
			}
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				criteria.add(Restrictions.like("persona.perPrimerApellido","%" + persona.getPerPrimerApellido() + "%"));
			}
			if(fiador.getCtaPrePrestamo().getPreId()!=null && fiador.getCtaPrePrestamo().getPreId().length()>0) {//para buscar por codigo de prestamo
				criteria.add(Restrictions.like("pre.preId","%" +fiador.getCtaPrePrestamo().getPreId()+ "%"));
			}
			if(fiador.getCtaAscAsociado().getAscCodigoAsociado()!=null && fiador.getCtaAscAsociado().getAscCodigoAsociado().length()>0) {
				criteria.add(Restrictions.like("asoc.ascCodigoAsociado","%" + fiador.getCtaAscAsociado()+ "%"));
			}
			if(fiador.getCtaAscAsociado().getAscCodigo()!=null && fiador.getCtaAscAsociado().getAscCodigo().length()>0) {//para buscar por codigo de empleado
				criteria.add(Restrictions.like("asoc.ascCodigo","%" + fiador.getCtaAscAsociado().getAscCodigo()+ "%"));
			}
			criteria.setProjection(Projections.countDistinct("ctaAscAsociado"));
			resp = (Integer) criteria.getExecutableCriteria(getSession()).uniqueResult();
		}catch (Exception e) {
			log.error("find by criteria failed", e);
			e.printStackTrace();
		}
		return resp;
	}

	public List findByCodigoYEmpresa(String ascCodigo, String empresa) {
		//Extrae los casCuenta correspondiente a las personas de las cuales un asociado es fiador,
		try {
			String queryString = "from CtaFxpFiadorPrestamo fxp " +
					" where fxp.ctaPxtPersonaExterna.pxtCodigoEmpleado = ? " +
					" and ucase(fxp.ctaPxtPersonaExterna.pxtTrabajo) = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascCodigo);
			queryObject.setParameter(1, empresa);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}