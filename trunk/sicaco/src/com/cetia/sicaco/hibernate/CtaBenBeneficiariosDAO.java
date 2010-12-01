package com.cetia.sicaco.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaBenBeneficiarios entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaBenBeneficiarios
 * @author MyEclipse Persistence Tools
 */

public class CtaBenBeneficiariosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaBenBeneficiariosDAO.class);
	// property constants
	public static final String BEN_PRIMER_NOMBRE = "benPrimerNombre";
	public static final String BEN_PRIMER_APELLIDO = "benPrimerApellido";
	public static final String BEN_SEGUNDO_NOMBRE = "benSegundoNombre";
	public static final String BEN_SEGUNDO_APELLIDO = "benSegundoApellido";
	public static final String BEN_APELLIDO_CASADA = "benApellidoCasada";
	public static final String BEN_SEXO = "benSexo";
	public static final String BEN_TELEFONO = "benTelefono";
	public static final String BEN_ESTADO = "benEstado";
	public static final String BEN_HIJO = "benHijo";
	public static final String BEN_NOMBRE_COMPLETO = "benNombreCompleto";

	public CtaBenBeneficiariosDAO(Session session) {
		super(session);
	}

	public void save(CtaBenBeneficiarios transientInstance) {
		log.debug("saving CtaBenBeneficiarios instance");
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

	public void delete(CtaBenBeneficiarios persistentInstance) {
		log.debug("deleting CtaBenBeneficiarios instance");
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

	public CtaBenBeneficiarios findById(java.lang.Integer id) {
		log.debug("getting CtaBenBeneficiarios instance with id: " + id);
		try {
			CtaBenBeneficiarios instance = (CtaBenBeneficiarios) getSession()
					.get("com.cetia.sicaco.hibernate.CtaBenBeneficiarios", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaBenBeneficiarios instance) {
		log.debug("finding CtaBenBeneficiarios instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaBenBeneficiarios").add(
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
		log.debug("finding CtaBenBeneficiarios instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaBenBeneficiarios as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CtaBenBeneficiarios> findByAsociadoYEstado(String ascId, String estado) {
		log.debug("finding CtaBenBeneficiarios instance with property: ascId and estado"
				 +", value: "+ ascId + ", "+estado );
		try {
			String queryString = "from CtaBenBeneficiarios as model where model.ctaAscAsociado.ascId"
					+ "= ?  and model.benEstado= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			queryObject.setParameter(1, estado);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<CtaBenBeneficiarios> findByAsociadoYEstado(String ascId, String estado, int rowStart, int rowEnd) {
		log.debug("finding CtaBenBeneficiarios instance with property: ascId and estado"
				 +", value: "+ ascId + ", "+estado );
		try {
			String queryString = "from CtaBenBeneficiarios as model where model.ctaAscAsociado.ascId"
					+ "= ?  and model.benEstado= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			queryObject.setParameter(1, estado);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByBenPrimerNombre(Object benPrimerNombre) {
		return findByProperty(BEN_PRIMER_NOMBRE, benPrimerNombre);
	}
	
	public List findByBenPrimerApellido(Object benPrimerApellido) {
		return findByProperty(BEN_PRIMER_APELLIDO, benPrimerApellido);
	}

	public List findByBenSegundoNombre(Object benSegundoNombre) {
		return findByProperty(BEN_SEGUNDO_NOMBRE, benSegundoNombre);
	}
	
	public List findByBenNombre(String primerNombre, String segundoNombre, String ascId) {
		log.debug("finding CtaBenBeneficiarios instance with property: "
				);
		try {
			String queryString = "from CtaBenBeneficiarios as model where model.benPrimerNombre"
					+ "= ?  and model.benSegundoNombre=? and model.ctaAscAsociado.ascId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, primerNombre);
			queryObject.setParameter(1, segundoNombre);
			queryObject.setParameter(2, ascId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByBenSegundoApellido(Object benSegundoApellido) {
		return findByProperty(BEN_SEGUNDO_APELLIDO, benSegundoApellido);
	}

	public List findByBenSexo(Object benSexo) {
		return findByProperty(BEN_SEXO, benSexo);
	}

	public List findByBenTelefono(Object benTelefono) {
		return findByProperty(BEN_TELEFONO, benTelefono);
	}

	public List findByBenEstado(Object benEstado) {
		return findByProperty(BEN_ESTADO, benEstado);
	}

	public List findByBenHijo(Object benHijo) {
		return findByProperty(BEN_HIJO, benHijo);
	}

	public List findByBenNombreCompleto(Object benNombreCompleto) {
		return findByProperty(BEN_NOMBRE_COMPLETO, benNombreCompleto);
	}
	
	public List findByBenApellidoCasada(Object benApellidoCasada) {
		return findByProperty(BEN_APELLIDO_CASADA, benApellidoCasada);
	}
	
	public List findAll() {
		log.debug("finding all CtaBenBeneficiarios instances");
		try {
			String queryString = "from CtaBenBeneficiarios";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public CtaBenBeneficiarios merge(CtaBenBeneficiarios detachedInstance) {
		log.debug("merging CtaBenBeneficiarios instance");
		try {
			CtaBenBeneficiarios result = (CtaBenBeneficiarios) getSession()
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

	public void attachDirty(CtaBenBeneficiarios instance) {
		log.debug("attaching dirty CtaBenBeneficiarios instance");
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

	public void attachClean(CtaBenBeneficiarios instance) {
		log.debug("attaching clean CtaBenBeneficiarios instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUpdatedName(Integer id,String primerNombre, String segundoNombre) {
		try{
			String sql = "from CtaBenBeneficiarios ben where ben.benId != ? and ben.benPrimerNombre = ? and ben.benPrimeApellido = ? ";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, id);
			queryObject.setParameter(1, primerNombre);
			queryObject.setParameter(2, segundoNombre);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<CtaBenBeneficiarios> findByCriteria(CtaBenBeneficiarios beneficiarios){
		log.debug("finding CtaBenBeneficiarios instance by criteria");
		List<CtaBenBeneficiarios> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CtaBenBeneficiarios.class);
		if (beneficiarios.getBenPrimerNombre()!= null && !beneficiarios.getBenPrimerNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("benPrimerNombre", beneficiarios.getBenPrimerNombre() ));
		}
		if (beneficiarios.getBenPrimerApellido()!= null && !beneficiarios.getBenPrimerApellido().trim().equals("")) {
 			criteria.add(Restrictions.like("benPrimerApellido", beneficiarios.getBenPrimerApellido() ));
		}
		DetachedCriteria criteria2 = criteria;
		lst = (List<CtaBenBeneficiarios>)criteria.getExecutableCriteria(getSession()).list();
		if(lst.size()>0){
			if (beneficiarios.getBenSegundoNombre()!= null && !beneficiarios.getBenSegundoNombre().trim().equals("")) {
	 			criteria2.add(Restrictions.like("benSegundoNombre", beneficiarios.getBenSegundoNombre() ));
			}
			List lst2 = (List<CtaBenBeneficiarios>)criteria2.getExecutableCriteria(getSession()).list();
			if(lst2.size()>0){
				return lst2;
			}
		}
		return new ArrayList<CtaBenBeneficiarios>();
	}
	
	public Integer getTotalRowCountByAsociadoYEstado(String ascId, String estado) {
		log.debug("finding CtaBenBeneficiarios instance with property: ascId and estado"
				 +", value: "+ ascId + ", "+estado );
		try {
			String queryString = "select count(*) from CtaBenBeneficiarios as model where model.ctaAscAsociado.ascId"
					+ "= ?  and model.benEstado= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, ascId);
			queryObject.setParameter(1, estado);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
}