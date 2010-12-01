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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.cetia.sicaco.procesosEspeciales.struts.action.ObjetoQuery;
import com.mad.utilidades.Format;
import com.mad.utilidades.Resumen;


/**
 * A data access object (DAO) providing persistence and search support for
 * CtaAscAsociado entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaAscAsociado
 * @author MyEclipse Persistence Tools
 */

public class CtaAscAsociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaAscAsociadoDAO.class);
	// property constants
	public static final String ASC_CODIGO = "ascCodigo";
	public static final String EST_ID = "estId";
	public static final String ASC_PROFESION = "ascProfesion";
	public static final String ASC_SALARIO = "ascSalario";
	public static final String ASC_ASOCIADO_PADRE = "ascAsociadoPadre";
	public static final String ASC_NACIONALIDAD = "ascNacionalidad";
	public static final String ASC_JEFE_INMEDIATO = "ascJefeInmediato";
	public static final String ASC_RENTA_DOMICILIO = "ascRentaDomicilio";
	public static final String ASC_ISSS = "ascIsss";
	public static final String ASC_DUI_LUGAR_EXP = "ascDuiLugarExp";
	public static final String ASC_DIVIDENDO_APORTACIONES= "ascDividendoAportaciones";
	public static final String ASC_NOMBRE_NIT = "ascNombreNit";
	public static final String ASC_DIR_TRABAJO = "ascDirTrabajo";
	public static final String PER_ID = "secPerPersona.perId";
	public static final String ASC_CODIGO_ASOCIADO = "ascCodigoAsociado";
	public static final String ASC_PAGO_INGRESO = "ascPagoIngreso";
	public static final String ASC_ESTADO_DISTRIBUCION = "ascEstadoDistribucion";
	
	public CtaAscAsociadoDAO(Session session) {
		super(session);
	}

	public void save(CtaAscAsociado transientInstance) {
		log.debug("saving CtaAscAsociado instance");
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

	public void delete(CtaAscAsociado persistentInstance) {
		log.debug("deleting CtaAscAsociado instance");
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

	public CtaAscAsociado findById(java.lang.String id) {
		log.debug("getting CtaAscAsociado instance with id: " + id);
		try {
			CtaAscAsociado instance = (CtaAscAsociado) getSession().get(
					"com.cetia.sicaco.hibernate.CtaAscAsociado", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaAscAsociado instance) {
		log.debug("finding CtaAscAsociado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaAscAsociado").add(
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
		log.debug("finding CtaAscAsociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaAscAsociado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPerId(Object perId) {
		return findByProperty(PER_ID, perId);
	}

	public List findByAscCodigo(Object ascCodigo) {
		return findByProperty(ASC_CODIGO, ascCodigo);
	}

	public List findByEstId(Object estId) {
		return findByProperty(EST_ID, estId);
	}

	public List findByAscProfesion(Object ascProfesion) {
		return findByProperty(ASC_PROFESION, ascProfesion);
	}

	public List findByAscSalario(Object ascSalario) {
		return findByProperty(ASC_SALARIO, ascSalario);
	}
	
	public List findByAscEstadoDistribucion(Object AscEstadoDistribucion) {
		return findByProperty(ASC_ESTADO_DISTRIBUCION, AscEstadoDistribucion);
	}
		
	public List findByAscAsociadoPadre(Object ascAsociadoPadre) {
		return findByProperty(ASC_ASOCIADO_PADRE, ascAsociadoPadre);
	}

	public List findByAscNacionalidad(Object ascNacionalidad) {
		return findByProperty(ASC_NACIONALIDAD, ascNacionalidad);
	}

	public List findByAscJefeInmediato(Object ascJefeInmediato) {
		return findByProperty(ASC_JEFE_INMEDIATO, ascJefeInmediato);
	}

	public List findByAscRentaDomicilio(Object ascRentaDomicilio) {
		return findByProperty(ASC_RENTA_DOMICILIO, ascRentaDomicilio);
	}

	public List findByAscIsss(Object ascIsss) {
		return findByProperty(ASC_ISSS, ascIsss);
	}

	public List findByAscDuiLugarExp(Object ascDuiLugarExp) {
		return findByProperty(ASC_DUI_LUGAR_EXP, ascDuiLugarExp);
	}

	public List findByAscDividendoAportaciones(Object ascDividendoAportaciones) {
		return findByProperty(ASC_DIVIDENDO_APORTACIONES, ascDividendoAportaciones);
	}

	public List findByAscNombreNit(Object ascNombreNit) {
		return findByProperty(ASC_NOMBRE_NIT, ascNombreNit);
	}

	public List findByAscDirTrabajo(Object ascDirTrabajo) {
		return findByProperty(ASC_DIR_TRABAJO, ascDirTrabajo);
	}
	
	public List findByAscCodigoAsociado(Object ascCodigoAsociado) {
		return findByProperty(ASC_CODIGO_ASOCIADO, ascCodigoAsociado);
	}
	
	public List findByAscPagoIngreso(Object ascPagoIngreso) {
		return findByProperty(ASC_PAGO_INGRESO, ascPagoIngreso);
	}

	public List findAll(int max) {
		log.debug("finding all CtaAscAsociado instances");
		try {
			String queryString = "from CtaAscAsociado order by ascId desc";
			Query queryObject = null;
			if(max < 1){
				queryObject = getSession().createQuery(queryString);
			}else{
				queryObject = getSession().createQuery(queryString).setMaxResults(max);
			}
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaAscAsociado merge(CtaAscAsociado detachedInstance) {
		log.debug("merging CtaAscAsociado instance");
		try {
			CtaAscAsociado result = (CtaAscAsociado) getSession().merge(
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

	public void attachDirty(CtaAscAsociado instance) {
		log.debug("attaching dirty CtaAscAsociado instance");
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

	public void attachClean(CtaAscAsociado instance) {
		log.debug("attaching clean CtaAscAsociado instance");
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
			String queryString = "select max(asociado.ascId) as ascId from CtaAscAsociado asociado";
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

	
	@SuppressWarnings("unchecked")
	public List<CtaAscAsociado> findByNameUser(CtaAscAsociado asociado, int lim) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where model.ctrEstEstado.estId = 0";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido ";
			
			Query queryObject = getSession().createQuery(queryString).setMaxResults(lim);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	
	
	public List<CtaAscAsociado> findByNameUser2(CtaAscAsociado asociado, int lim) {
		// busca asociados activos e inactivos 
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		
		try {
			
			String queryString="from CtaAscAsociado as model where 1 = 1  ";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido ";
			
			Query queryObject = getSession().createQuery(queryString).setMaxResults(lim);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<CtaAscAsociado> findByNameUserInactivo(CtaAscAsociado asociado, int lim) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where model.ctrEstEstado.estId = 6";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido ";
			
			Query queryObject = getSession().createQuery(queryString).setMaxResults(lim);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CtaAscAsociado> findByNameUserForCargoFiador(CtaAscAsociado asociado, int lim) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where 1=1 ";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			queryString += " order by model.secPerPersona.perPrimerApellido ";
			
			Query queryObject = getSession().createQuery(queryString).setMaxResults(lim);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	
	public List<CtaAscAsociado> findAsociadosDisponiblesPrestamo(CtaAscAsociado asociado, int lim) {
		List<CtaAscAsociado> resultado = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String [] args = sdf.format(new Date()).split("-");
		CtrParParametrosDAO parametrosDAO = new CtrParParametrosDAO(getSession());
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		Integer mes = new Integer(args[1]);
		try {
			
			String queryString="from CtaAscAsociado as model" +
					" where (model.estId=0 or model.estId=8) and (year(model.ascIngresoCoope) < "+ args[2] +" OR ("+ mes 
					 +"-MONTH(model.ascIngresoCoope)) >= "+parametrosDAO.findById("ESTADIA_MIN_PREST").getParValorNumber().intValue()+")";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like '%" + persona.getPerPrimerNombre().toUpperCase() + "%'";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.id.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido ";
			Query queryObject = getSession().createQuery(queryString).setMaxResults(lim);
			
			resultado =queryObject.list();
			
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	
	public String generarCodigo() {
		String identificador = null;

		try {
			
            String queryString = "select max(a.ascCodigoAsociado) as ascCodigoAsociado from CtaAscAsociado a where a.ascCodigoAsociado not like 'F%'";
            Query query = getSession().createQuery(queryString);
            String ultimoIdentificador = (String) query.uniqueResult();
      
            SimpleDateFormat sdf = new SimpleDateFormat("yy");
            String anioActualFormateado = sdf.format(new Date());
           
            if (ultimoIdentificador == null){
                identificador = "ASC"+anioActualFormateado+"000000";               
            }

            if (ultimoIdentificador != null){
                String anioUltimoIdentificador = ultimoIdentificador.substring(3,5);
                if (anioUltimoIdentificador.equals(anioActualFormateado)){
                    Integer nuevoCorrelativo = (Integer.parseInt(ultimoIdentificador.substring(5))+1);
                    identificador = "ASC"+anioActualFormateado+Format.rellenarIzquierda(nuevoCorrelativo.toString(), "0", 6);                   
                }
                else {
                    identificador = "ASC"+anioActualFormateado+"000000";                   
                }
            }
           
        }catch(RuntimeException e) {
		log.error("Ocurrio un error buscando el codigo " + identificador, e);
		throw e;
	}
        return identificador;
	}
	
	@SuppressWarnings("unchecked")
	public List<CtaAscAsociado> findByNameUserWithoutMe(CtaAscAsociado asociado,String ascId) {//busca asociados por criteria y que sean distintos a ascId
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where model.estId=0 and model.ascId!='"+ascId+"'";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.id.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido";
			
			Query queryObject = getSession().createQuery(queryString);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}

	
	//Funciones para Planilla
	public List findAllInternos() {
		log.debug("finding all CtaAscAsociado que trabajan para Taca o su familia de empresas instances");
		try {
			String queryString = "from CtaAscAsociado a where a.ctaDptDepartamentoTrabajo.dptId IS NOT NULL " +
					"AND a.estId = 0 AND a.ctrEstEstado.estId=0 ORDER BY a.secPerPersona.perPrimerApellido";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public boolean verificarRepetidos(String codAsc, int empresa) {
		log.debug("Verificando codigo de Asociado si esta repetido...");
		boolean repetido = false;
		try {
			String queryString = "from CtaAscAsociado  a where a.ascCodigo = ? ";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, codAsc);
			if(queryObject.list()!=null || !queryObject.list().isEmpty()){
				List lista = queryObject.list();
				for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
					CtaAscAsociado nameA = (CtaAscAsociado) iterator.next();
					if(nameA.getCtaDptDepartamentoTrabajo()!=null){
						if(nameA.getCtaDptDepartamentoTrabajo().getCtaEtrEmpresaTrabajo().getEtrId()==empresa){
							repetido =true;
							break;
						}
							
					}else{
						repetido=true;
						break;
					}
					
				}
				
				repetido = true;
			}
			return repetido;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ObjetoQuery> findAllInternos2() {
		log.debug("finding all CtaAscAsociado que trabajan para Taca o su familia de empresas instances");
		try {
			String queryString = 
				"SELECT "+
					"asc1.ASC_ID AS ASC_ID, "+
				     "asc1.`ASC_CODIGO` AS ASC_CODIGO, "+
				     "CONCAT( per.`PER_PRIMER_APELLIDO`,', ', "+
				    		 "per.`PER_PRIMER_NOMBRE`) AS NOMBRE, "+
				    "(SELECT  SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota "+
				     "FROM "+
				         "`cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas "+
				         "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
				         "INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id` "+
				    "WHERE "+
				        "cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' AND "+
				        "asoc.`ASC_ID`=asc1.`ASC_ID`) AS TOTAL_APORTACION, "+
				    "(SELECT SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota "+
				     "FROM "+
				       "`cta_asc_asociado` asoc    INNER JOIN `cta_cas_cuenta_asociado` cas "+
				       "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
				       "INNER JOIN `cta_cah_cuenta_ahorro` cah  ON cas.`cah_id` = cah.`cah_id` "+
				    "WHERE "+
				        "cas.`EST_ID`=9 AND   asoc.`ASC_ID`=asc1.`ASC_ID` AND "+
				        "cah.`cah_id` LIKE 'B%')  AS TOTAL_AHORRO, "+
				
				    "(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota "+
				     "FROM "+
				       "`cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas "+
				       "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
				       "INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id` "+
				    "WHERE "+
				        "cas.`EST_ID`=13 AND  asoc.`ASC_ID`=asc1.`ASC_ID`)  AS TOTAL_PRESTAMO, "+
				
				    "(SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota "+
				     "FROM "+
				       "`cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas "+
				       "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
				       "INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id` "+
				    "WHERE "+
				        "cas.`EST_ID`=11 AND   asoc.`ASC_ID`=asc1.`ASC_ID`)  AS TOTAL_SEGURO "+
				
				
				"FROM "+
				     "`sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1 "+
				     "ON per.`PER_ID` = asc1.`PER_ID` "+
				"WHERE "+
				         "asc1.`EST_ID_2`=0 AND asc1.`EST_ID`=0"; 
			//Query queryObject = getSession().createQuery(queryString);
			SQLQuery q = getSession().createSQLQuery(queryString);
			q.addScalar("ASC_ID", Hibernate.STRING);
			q.addScalar("ASC_CODIGO", Hibernate.STRING);
			q.addScalar("NOMBRE", Hibernate.STRING);
			q.addScalar("TOTAL_APORTACION", Hibernate.DOUBLE);
			q.addScalar("TOTAL_AHORRO", Hibernate.DOUBLE);
			q.addScalar("TOTAL_PRESTAMO", Hibernate.DOUBLE);
			q.addScalar("TOTAL_SEGURO", Hibernate.DOUBLE);
			//q.addEntity(ObjetoQuery.class);
			return q.list();
			//return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaAscAsociado> findByNameUserPlanilla(CtaAscAsociado asociado) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where 1 = 1";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.id.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			queryString +=" AND model.id.ctaDptDepartamentoTrabajo.dptId IS NOT NULL " +
			"AND model.id.estId = 0 AND model.id.ctrEstEstado.estId=0";
			queryString += " order by model.id.secPerPersona.perPrimerApellido";
			
			Query queryObject = getSession().createQuery(queryString);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}

	public List findByNameUserPlanillaAndEtr(CtaAscAsociado asociado, int empresa) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where 1 = 1";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.id.ascCodigoAsociado) like ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			if(empresa > 0){
				queryString += " and model.id.ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId = " + empresa;
			}
			queryString +=" AND model.id.ctaDptDepartamentoTrabajo.dptId IS NOT NULL " +
			"AND (model.id.estId = 0 or model.id.estId = 21) AND model.id.ctrEstEstado.estId=0";
			queryString += " order by model.id.ascCodigo";
			
			Query queryObject = getSession().createQuery(queryString);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	
	public List<CtaAscAsociado> findByCodigos(CtaAscAsociado asociado, int lim, String like) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {			
			String queryString="from CtaAscAsociado as model where 1 = 1";
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.id.ascCodigoAsociado) like ucase('" + asociado.getAscCodigoAsociado()+ "')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('" +asociado.getAscCodigo()+ "')";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido ";
			
			Query queryObject = getSession().createQuery(queryString).setMaxResults(lim);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}

	public List findByPadreYPadrePaga(String ascId) {
		log.debug("finding all CtaAscAsociado instances");
		try {
			String queryString = "from CtaAscAsociado asoc where asoc.ascAsociadoPadre = '" + ascId + "' " +
					"AND asoc.ascPagaraPadre='S' AND asoc.ctrEstEstado.estId = 0";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaAscAsociado> findDependientesPorAsociado(String ascId,int estId) {
		log.debug("finding all CtaAscAsociado instances");
		try {
			String queryString = "from CtaAscAsociado asoc where asoc.ascAsociadoPadre = ? and asoc.ctrEstEstado.estId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0,ascId);
			queryObject.setParameter(1,estId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int countAllByEmpresa(int etrId) {
		log.debug("finding all CtaAscAsociado que trabajan para Taca o su familia de empresas instances");
		try {
			String queryString = "select count(a.ascId) as todos " +
					"from CtaAscAsociado a " +
					"inner join a.ctaDptDepartamentoTrabajo as dpt " +
					"inner join dpt.ctaEtrEmpresaTrabajo as etr " +
					"WHERE a.ctaDptDepartamentoTrabajo.dptId IS NOT NULL " +
					"AND a.estId = 0 " +
					"AND a.ctrEstEstado.estId=0 ";
					if(etrId != -1){
						queryString += "AND etr.etrId = " + etrId;
					}
					
			Query queryObject = getSession().createQuery(queryString);
			return (Integer)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllByEstado(int max,int estId) {
		log.debug("finding all CtaAscAsociado instances");
		try {
			String queryString = "from CtaAscAsociado asoc where asoc.ctrEstEstado.estId= ? order by asoc.ascId desc";
			Query queryObject = null;
			if(max > 0){
				queryObject = getSession().createQuery(queryString).setMaxResults(max);
			}else{
				queryObject = getSession().createQuery(queryString);
			}
			queryObject.setParameter(0, estId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CtaAscAsociado> findByNameUserYEstado(CtaAscAsociado asociado, int lim,int estado) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where 1 = 1";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.id.ascCodigoAsociado) LIKE ucase('"+asociado.getAscCodigoAsociado()+"')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('"+asociado.getAscCodigo()+"')";
			}
			queryString += "and model.ctrEstEstado.estId="+estado+" order by model.id.secPerPersona.perPrimerApellido ";
			
			Query queryObject = getSession().createQuery(queryString).setMaxResults(lim);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}

	public List findActivosConAportacion() {
		log.debug("finding all CtaAscAsociado instances");
		ArrayList<CtaAscAsociado> asociados = new ArrayList<CtaAscAsociado>();
		try {
			String queryString = "from CtaAscAsociado asoc where asoc.ctrEstEstado.estId= ? order by asoc.ascId desc";
			Query queryObject = null;
			queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, 0);
			if(queryObject.list() != null && queryObject.list().size()>0){
				System.out.println("se creo la lista, empieza a iterar");
				for (Iterator iterator = queryObject.list().iterator(); iterator
						.hasNext();) {
					CtaAscAsociado asociado = (CtaAscAsociado) iterator.next();
					if(this.findSaldoAportacion(asociado.getAscId())!=null &&
							this.findSaldoAportacion(asociado.getAscId())>0){
						asociados.add(asociado);
					}
				}
			}
			System.out.println("Termino iteracion, devuelve lista asociados");
			return asociados;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/*
	 * update Cta_Asc_Asociado c set c.asc_Estado_Distribucion = 'P', c.asc_Dividendo_Prestamo=0.0, c.asc_Dividendo_Aportaciones=0.0
	 * 
	 * */
	
	public void inicializarEstadoParaDistribuir() {
		try {
			String queryString = "update CtaAscAsociado " +
					"set ascEstadoDistribucion = 'P', ascDividendoPrestamo=0.0, ascDividendoAportaciones=0.0";
					
			Query queryObject = getSession().createQuery(queryString);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Double findSaldoAportacion(String ascId) {
		log.debug("finding all CtaAscAsociado instances");
		System.out.print(ascId);
		try {
			String queryString = "SELECT "+
		    "(SELECT  IF(cah.`cah_saldo_actual` IS NULL,0,cah.`cah_saldo_actual`) AS saldo "+
		     "FROM "+
		         "`cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas "+
		         "ON asoc.`ASC_ID` = cas.`ASC_ID` "+
		         "INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id` "+
		    "WHERE "+
		        "cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' "+
			"and asoc.`ASC_ID`=asc1.`ASC_ID` "+
			") AS TOTAL_APORTACION "+
		"FROM "+
		     "`cta_asc_asociado` asc1 "+
		"WHERE "+
     "asc1.`EST_ID_2`=0 and asc1.`ASC_ID` = '"+ ascId +"'";
			SQLQuery queryObject = null;
			queryObject = getSession().createSQLQuery(queryString);
			queryObject.addScalar("TOTAL_APORTACION", Hibernate.DOUBLE);
			System.out.println(queryObject.uniqueResult());
			return (Double) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findActivosConAportacion2() {
		log.debug("finding all CtaAscAsociado instances");
		try {
			String queryString = 
//					"select asc2.asc_id as ASC_ID " +
//					"from cta_asc_asociado asc2 " +
//					"where " +
//					"( " +
//						"SELECT " +
//						"(SELECT  IF(cah.`cah_saldo_actual` IS NULL,0,cah.`cah_saldo_actual`) AS saldo " +
//							"FROM " +
//							"`cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas " +
//							"ON asoc.`ASC_ID` = cas.`ASC_ID` " +
//							"INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id` " +
//							"WHERE " +
//							"cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' " +
//							"and asoc.`ASC_ID`=asc1.`ASC_ID` " +
//						") AS TOTAL_APORTACION " +
//					"FROM " +
//					"`cta_asc_asociado` asc1 " +
//					"WHERE " +
//					"asc1.`EST_ID_2`=0 and asc1.`ASC_ID` = asc2.`ASC_ID`) > 0";
				"SELECT " +
				"  aso.asc_id " +
				" FROM cta_cas_cuenta_asociado cas,  cta_asc_asociado aso,cta_cah_cuenta_ahorro cah " +
				"where  cas.asc_id = aso.asc_id and  cas.cah_id like 'A%' and  cas.est_id = 9 and cah.cah_id = cas.cah_id and cah.cah_saldo_actual > 0";
			SQLQuery queryObject = null;
			queryObject = getSession().createSQLQuery(queryString);
			queryObject.addScalar("ASC_ID", Hibernate.STRING);
			//System.out.println("Termino iteracion, devuelve lista asociados");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getAsociadosSinDistribuirRows() {
		try {
			String queryString = "select count(a.ascId) from CtaAscAsociado a where a.ascEstadoDistribucion = 'N' ";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<CtaAscAsociado> findAllAsociadosSinDistribuir(int rowStart,int rowEnd) {
		try {
			String queryString = "from CtaAscAsociado a where a.ascEstadoDistribucion = 'N' ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CtaAscAsociado> findAll(int rowStart,int rowEnd) {
		log.debug("finding all CtaAscAsociado hse instances order by hse.hseId desc");
		try {
			String queryString = "from CtaAscAsociado hse order by hse.ascId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int updateEstadoDistribucion(String cadena) {
		log.debug("finding all CtaAscAsociado hse instances order by hse.hseId desc");
		try {
			String queryString = "update CtaAscAsociado set ascEstadoDistribucion = 'S' where ascId in ("+cadena+")";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer getTotalRowCount() {
		log.debug("counting all rows of CtaAscAsociado");
		try {
			String queryString = "select count(a.ascId)from CtaAscAsociado a";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer findByCodigoEmpleadoAndEmpresa(String ascCodigo,int dptId) {
		try {
			String queryString = "select count(*) from CtaAscAsociado asoc where UCASE(asoc.ascCodigo) = ?"
				+"and asoc.ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId ="
				+"(select dep.ctaEtrEmpresaTrabajo.etrId from CtaDptDepartamentoTrabajo dep where dep.dptId = ?)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascCodigo.toUpperCase());
			queryObject.setParameter(1, dptId);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Integer getTotalRowCountByNameUser(CtaAscAsociado asociado) {
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Contando en numero de asociados por criteria " + persona.toString());
		try {
			String queryString="select count(model.ascId) from CtaAscAsociado as model where model.ctrEstEstado.estId = 0";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('%"+asociado.getAscCodigoAsociado()+"%')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('%"+asociado.getAscCodigo()+"%')";
			}
			if(asociado.getSecPerPersona().getPerNit()!=null && asociado.getSecPerPersona().getPerNit().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perNit) like '%"+asociado.getSecPerPersona().getPerNit().replace("-", "")+"%'";
			}
			if(asociado.getSecPerPersona().getPerDui()!=null && asociado.getSecPerPersona().getPerDui().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perDui) like '%"+asociado.getSecPerPersona().getPerDui()+"%'";
			}
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		}catch(RuntimeException e) {
			log.error("Ocurrio un error contando el numero de asociados por criteria " + persona.toString(), e);
			throw e;
		}
	}
	

	public List<CtaAscAsociado> findByNameUser(CtaAscAsociado asociado,int rowStart,int rowEnd) {
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Contando en numero de asociados por criteria " + persona.toString());
		try {
			String queryString="from CtaAscAsociado as model where model.ctrEstEstado.estId = 0";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('%"+asociado.getAscCodigoAsociado()+"%')";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('%"+asociado.getAscCodigo()+"%')";
			}
			if(asociado.getSecPerPersona().getPerNit()!=null && asociado.getSecPerPersona().getPerNit().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perNit) like '%"+asociado.getSecPerPersona().getPerNit().replace("-","")+"%'";
			}
			if(asociado.getSecPerPersona().getPerDui()!=null && asociado.getSecPerPersona().getPerDui().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perDui) like '%"+asociado.getSecPerPersona().getPerDui()+"%'";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		}catch(RuntimeException e) {
			log.error("Ocurrio un error contando el numero de asociados por criteria " + persona.toString(), e);
			throw e;
		}
	}
	

	public List<CtaAscAsociado> findByNameUserInactivo(CtaAscAsociado asociado, int rowStart,int rowEnd) {
		List<CtaAscAsociado> resultado = null;
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="from CtaAscAsociado as model where model.ctrEstEstado.estId = 6";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('%"+asociado.getAscCodigoAsociado()+"%')";
			}
			if(asociado.getSecPerPersona().getPerNit()!=null && asociado.getSecPerPersona().getPerNit().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perNit) like '%"+asociado.getSecPerPersona().getPerNit().replace("-", "")+"%'";
			}
			if(asociado.getSecPerPersona().getPerDui()!=null && asociado.getSecPerPersona().getPerDui().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perDui) like '%"+asociado.getSecPerPersona().getPerDui()+"%'";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('%"+asociado.getAscCodigo()+"%')";
			}
			queryString += " order by model.id.secPerPersona.perPrimerApellido ";
			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			resultado =queryObject.list();
			log.debug("Se encontraron " + resultado.size() + " registros");
			return resultado;
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	

	public Integer getTotalRowCountByNameUserInactivo(CtaAscAsociado asociado) {
		SecPerPersona persona = asociado.getSecPerPersona();
		log.debug("Buscamos las ocurrencias del nombre " + persona.toString());
		try {
			
			String queryString="select count(model.ascId) from CtaAscAsociado as model where model.ctrEstEstado.estId = 6";
			
			if(persona.getPerPrimerApellido()!=null&&persona.getPerPrimerApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerApellido) like ucase('%" + persona.getPerPrimerApellido() + "%')";
			}
			if(persona.getPerSegundoApellido()!=null&&persona.getPerSegundoApellido().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoApellido) like ucase('%" + persona.getPerSegundoApellido() + "%')";
			}
			if(persona.getPerPrimerNombre()!=null&&persona.getPerPrimerNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perPrimerNombre) like ucase('%" + persona.getPerPrimerNombre() + "%')";
			}
			if(persona.getPerSegundoNombre()!=null&&persona.getPerSegundoNombre().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perSegundoNombre) like ucase('%" + persona.getPerSegundoNombre() + "%')";
			}
			if(asociado.getAscCodigoAsociado()!=null&&asociado.getAscCodigoAsociado().length()>0) {
				queryString += " and ucase(model.ascCodigoAsociado) like ucase('%"+asociado.getAscCodigoAsociado()+"%')";
			}
			if(asociado.getSecPerPersona().getPerNit()!=null && asociado.getSecPerPersona().getPerNit().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perNit) like '%"+asociado.getSecPerPersona().getPerNit().replace("-", "")+"%'";
			}
			if(asociado.getSecPerPersona().getPerDui()!=null && asociado.getSecPerPersona().getPerDui().length()>0) {
				queryString += " and ucase(model.id.secPerPersona.perDui) like '%"+asociado.getSecPerPersona().getPerDui()+"%'";
			}
			if(asociado.getAscCodigo()!=null&&asociado.getAscCodigo().length()>0) {//para buscar por codigo de empleado
				queryString += " and ucase(model.id.ascCodigo) like ucase('%"+asociado.getAscCodigo()+"%')";
			}
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		}catch(RuntimeException e) {
			log.error("Ocurrio un error buscando el nombre " + persona.toString(), e);
			throw e;
		}
	}
	public String next(){		
	
			String sql =
			"SELECT "+
			"  max(substring(aso.asc_codigo,2))+1 as next "+
			"FROM "+
			"  cta_asc_asociado aso "+
			"WHERE "+
			"  aso.asc_codigo like 'F%' ";;
		 
			String jdbcDriver = "com.mysql.jdbc.Driver";
			String res = "";
			try {
				
				Class.forName(jdbcDriver);
				String url = HibernateSessionFactory.getConfiguration().getProperty("connection.url");
				String user = HibernateSessionFactory.getConfiguration().getProperty("connection.username");
				String pass = HibernateSessionFactory.getConfiguration().getProperty("connection.password");
				
				Connection con = DriverManager.getConnection(url, user, pass);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()){
					res = rs.getString("next");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return res;
	}

	public List findAllInternosByEmpresa(int empresa) {
		log.debug("finding all CtaAscAsociado que trabajan para empresa x");
		try {
			String queryString = "from CtaAscAsociado a " +
					"where a.ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrId = "+empresa+" " +
					"AND a.estId = 0 AND a.ctrEstEstado.estId=0 ORDER BY a.secPerPersona.perPrimerApellido";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
//	public List findAllInternosByEmpresa2(int empresa) {
//		log.debug("finding all CtaAscAsociado que trabajan para empresa x");
//		try {
//			String queryString = "from CtaAscAsociado a " +
//					"where " +
//					"a.estId = 0 AND a.ctrEstEstado.estId=0 ORDER BY a.secPerPersona.perPrimerApellido";
//			Query queryObject = getSession().createQuery(queryString);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}
	

	public CtaAscAsociado findAsociadoActivoFromFiador(String codigoEmpleado, String empresa) {
		log.debug("finding all CtaAscAsociado que trabajan para empresa x");
		try {
			String queryString = "from CtaAscAsociado a " +
					"where UCASE(a.ctaDptDepartamentoTrabajo.ctaEtrEmpresaTrabajo.etrNombre) = '"+empresa+"' " +
					"AND UCASE(a.ascCodigo) = '" +codigoEmpleado+"' " +
					"AND a.ctrEstEstado.estId=0";
			Query queryObject = getSession().createQuery(queryString);
			List<CtaAscAsociado> asociados = queryObject.list();
			if(asociados.size() > 0){
				return (CtaAscAsociado) asociados.get(0);
			}else{
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}