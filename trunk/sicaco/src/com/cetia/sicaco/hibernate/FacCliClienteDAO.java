package com.cetia.sicaco.hibernate;

import java.util.List;
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
 * FacCliCliente entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.FacCliCliente
 * @author MyEclipse Persistence Tools
 */

public class FacCliClienteDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(FacCliClienteDAO.class);
	// property constants
	public static final String CLI_NOMBRE = "cliNombre";
	public static final String CLI_DIRECCION = "cliDireccion";
	public static final String CLI_NUM_REGISTRO = "cliNumRegistro";
	public static final String CLI_MUNICIPIO = "cliMunicipio";
	public static final String CLI_DEPARTAMENTO = "cliDepartamento";
	public static final String CLI_GIRO = "cliGiro";
	public static final String CLI_CONTRIBUYENTE = "cliContribuyente";

	public FacCliClienteDAO(Session session) {
		super(session);
	}

	public void save(FacCliCliente transientInstance) {
		log.debug("saving FacCliCliente instance");
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

	public void delete(FacCliCliente persistentInstance) {
		log.debug("deleting FacCliCliente instance");
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

	public FacCliCliente findById(java.lang.String id) {
		log.debug("getting FacCliCliente instance with id: " + id);
		try {
			FacCliCliente instance = (FacCliCliente) getSession().get(
					"com.cetia.sicaco.hibernate.FacCliCliente", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FacCliCliente instance) {
		log.debug("finding FacCliCliente instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.FacCliCliente").add(
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
		log.debug("finding FacCliCliente instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FacCliCliente as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCliNombre(Object cliNombre) {
		return findByProperty(CLI_NOMBRE, cliNombre);
	}

	public List findByCliDireccion(Object cliDireccion) {
		return findByProperty(CLI_DIRECCION, cliDireccion);
	}

	public List findByCliNumRegistro(Object cliNumRegistro) {
		return findByProperty(CLI_NUM_REGISTRO, cliNumRegistro);
	}

	public List findByCliMunicipio(Object cliMunicipio) {
		return findByProperty(CLI_MUNICIPIO, cliMunicipio);
	}

	public List findByCliDepartamento(Object cliDepartamento) {
		return findByProperty(CLI_DEPARTAMENTO, cliDepartamento);
	}

	public List findByCliGiro(Object cliGiro) {
		return findByProperty(CLI_GIRO, cliGiro);
	}
	
	public List findByCliContribuyente(Object cliContribuyente) {
		return findByProperty(CLI_CONTRIBUYENTE, cliContribuyente);
	}

	public List findAll() {
		log.debug("finding all FacCliCliente instances");
		try {
			String queryString = "from FacCliCliente";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FacCliCliente merge(FacCliCliente detachedInstance) {
		log.debug("merging FacCliCliente instance");
		try {
			FacCliCliente result = (FacCliCliente) getSession().merge(
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

	public void attachDirty(FacCliCliente instance) {
		log.debug("attaching dirty FacCliCliente instance");
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

	public void attachClean(FacCliCliente instance) {
		log.debug("attaching clean FacCliCliente instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<FacCliCliente> findByCriteria(FacCliCliente cliente,int max, int contrib){
		log.debug("finding FacCliCliente instance by criteria");
		List<FacCliCliente> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FacCliCliente.class);
		if (cliente.getCliCodigo()!= null && !cliente.getCliCodigo().trim().equals("")) {
			criteria.add(Restrictions.like("cliCodigo","%" +cliente.getCliCodigo() +"%"));
		}
		if (cliente.getCliNombre()!= null && !cliente.getCliNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("cliNombre", "%" + cliente.getCliNombre() + "%"));
		}
		if(contrib != 1){
			criteria.add(Restrictions.sqlRestriction(
				"{alias}.cli_Contribuyente is null"));
		}
		return (List<FacCliCliente>)criteria.getExecutableCriteria(getSession()).setMaxResults(max).list();
	}

	public List<FacCliCliente> findByCriteriaAndContribuyente(
			FacCliCliente cliente) {
		log.debug("finding FacCliCliente instance by criteria");
		List<FacCliCliente> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FacCliCliente.class);
		if (cliente.getCliCodigo()!= null && !cliente.getCliCodigo().trim().equals("")) {
			criteria.add(Restrictions.like("cliCodigo","%" +cliente.getCliCodigo() +"%"));
		}
		if (cliente.getCliNombre()!= null && !cliente.getCliNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("cliNombre", "%" + cliente.getCliNombre() + "%"));
		}
		criteria.add(Restrictions.sqlRestriction(
				"{alias}.cli_Contribuyente is not null"));
		return (List<FacCliCliente>)criteria.getExecutableCriteria(getSession()).list();
	}

	public List findByCriteria2(FacCliCliente cliente) {
		List<FacCliCliente> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FacCliCliente.class);
		if (cliente.getCliCodigo()!= null && !cliente.getCliCodigo().trim().equals("")) {
			criteria.add(Restrictions.like("cliCodigo","%" +cliente.getCliCodigo() +"%"));
		}
		if (cliente.getCliNombre()!= null && !cliente.getCliNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("cliNombre", "%" + cliente.getCliNombre() + "%"));
		}
		return (List<FacCliCliente>)criteria.getExecutableCriteria(getSession()).list();
	}
}