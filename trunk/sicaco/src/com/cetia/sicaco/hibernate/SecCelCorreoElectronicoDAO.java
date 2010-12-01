package com.cetia.sicaco.hibernate;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.parser.node.GetExecutor;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecCelCorreoElectronico entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecCelCorreoElectronico
 * @author MyEclipse Persistence Tools
 */

public class SecCelCorreoElectronicoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecCelCorreoElectronicoDAO.class);
	// property constants
	public static final String CEL_PRINCIPAL = "celPrincipal";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public SecCelCorreoElectronicoDAO(Session session) {
		super(session);
	}

	public void save(SecCelCorreoElectronico transientInstance) {
		log.debug("saving SecCelCorreoElectronico instance");
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

	public void delete(SecCelCorreoElectronico persistentInstance) {
		log.debug("deleting SecCelCorreoElectronico instance");
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

	public SecCelCorreoElectronico findById(
			com.cetia.sicaco.hibernate.SecCelCorreoElectronicoId id) {
		log.debug("getting SecCelCorreoElectronico instance with id: " + id);
		try {
			SecCelCorreoElectronico instance = (SecCelCorreoElectronico) getSession()
					.get(
							"com.cetia.sicaco.hibernate.SecCelCorreoElectronico",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecCelCorreoElectronico instance) {
		log.debug("finding SecCelCorreoElectronico instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecCelCorreoElectronico")
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
		log.debug("finding SecCelCorreoElectronico instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecCelCorreoElectronico as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCelPrincipal(Object celPrincipal) {
		return findByProperty(CEL_PRINCIPAL, celPrincipal);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}
	
	
	public	String findCorreoForConectedUser(String iseNombreUsuario){
		String  correo  = null;
		
		if(iseNombreUsuario != null && !iseNombreUsuario.trim().equals("")){
			DetachedCriteria dcSubQuery = DetachedCriteria.forClass(SecIseInicioSesion.class,"si");
			dcSubQuery.add(Restrictions.eq("si.iseNombreUsuario",iseNombreUsuario))
				.setProjection(Property.forName("si.secPerPersona.perId"))
			;
			DetachedCriteria dc =  DetachedCriteria.forClass(SecCelCorreoElectronico.class,"c").
						add(Restrictions.eq("c.celPrincipal","S"))
						.add(Subqueries.propertyEq("c.id.secPerPersona.perId", dcSubQuery))
						.setProjection(Property.forName("c.id.celCorreoElectronico"));
			correo = (String)dc.getExecutableCriteria(getSession()).uniqueResult();
		}
		return correo;
	}
	
	public String findCorreoByPerId(String perId){
		String  correo  = null;
		if (perId != null && !perId.trim().equals("")) {
			DetachedCriteria dc = DetachedCriteria.forClass(SecCelCorreoElectronico.class,"c").
			add(Restrictions.eq("c.celPrincipal","S")).
			add(Restrictions.eq("c.id.secPerPersona.perId",perId))
			.setProjection(Property.forName("id.celCorreoElectronico"));
			correo = (String)dc.getExecutableCriteria(getSession()).uniqueResult();
		}
		return correo;
	}
	

	public List findAll() {
		log.debug("finding all SecCelCorreoElectronico instances");
		try {
			String queryString = "from SecCelCorreoElectronico";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecCelCorreoElectronico merge(
			SecCelCorreoElectronico detachedInstance) {
		log.debug("merging SecCelCorreoElectronico instance");
		try {
			SecCelCorreoElectronico result = (SecCelCorreoElectronico) getSession()
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

	public void attachDirty(SecCelCorreoElectronico instance) {
		log.debug("attaching dirty SecCelCorreoElectronico instance");
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

	public void attachClean(SecCelCorreoElectronico instance) {
		log.debug("attaching clean SecCelCorreoElectronico instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<SecCelCorreoElectronico> findByCorreo(String email) {
		log.debug("finding all SecCelCorreoElectronico instances");
		try {
			String queryString = "from SecCelCorreoElectronico cel where cel.id.celCorreoElectronico ='" + email + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}