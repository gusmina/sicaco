package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtrUreUsuarioRepositorio entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.cetia.sicaco.hibernate.CtrUreUsuarioRepositorio
 * @author MyEclipse Persistence Tools
 */

public class CtrUreUsuarioRepositorioDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtrUreUsuarioRepositorioDAO.class);
	// property constants
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public CtrUreUsuarioRepositorioDAO(Session session) {
		super(session);
	}

	public void save(CtrUreUsuarioRepositorio transientInstance) {
		log.debug("saving CtrUreUsuarioRepositorio instance");
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

	public void delete(CtrUreUsuarioRepositorio persistentInstance) {
		log.debug("deleting CtrUreUsuarioRepositorio instance");
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

	public CtrUreUsuarioRepositorio findById(java.lang.Integer id) {
		log.debug("getting CtrUreUsuarioRepositorio instance with id: " + id);
		try {
			CtrUreUsuarioRepositorio instance = (CtrUreUsuarioRepositorio) getSession()
					.get("com.cetia.sicaco.hibernate.CtrUreUsuarioRepositorio",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtrUreUsuarioRepositorio instance) {
		log.debug("finding CtrUreUsuarioRepositorio instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtrUreUsuarioRepositorio").add(
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
		log.debug("finding CtrUreUsuarioRepositorio instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtrUreUsuarioRepositorio as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findAllNombreRepositorio() {
		try {
			String queryString = "select distinct rf.id.ctrRfcRepositorioFacturas.rfcNombre from CtrUreUsuarioRepositorio rf ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find allRolNombre failed", re);
			throw re;
		}
	}
	
	public List findAllUsuariosRepositorio() {
		try {
			String queryString = "select distinct concat(concat(rf.id.secPerPersona.perPrimerNombre,\' \'),rf.id.secPerPersona.perSegundoNombre) as perNombrePersona from CtrUreUsuarioRepositorio rf";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all CtrUreUsuarioRepositorio instances");
		try {
			String queryString = "from CtrUreUsuarioRepositorio";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtrUreUsuarioRepositorio merge(
			CtrUreUsuarioRepositorio detachedInstance) {
		log.debug("merging CtrUreUsuarioRepositorio instance");
		try {
			CtrUreUsuarioRepositorio result = (CtrUreUsuarioRepositorio) getSession()
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

	public void attachDirty(CtrUreUsuarioRepositorio instance) {
		log.debug("attaching dirty CtrUreUsuarioRepositorio instance");
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

	public void attachClean(CtrUreUsuarioRepositorio instance) {
		log.debug("attaching clean CtrUreUsuarioRepositorio instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CtrUreUsuarioRepositorio> findByCriteria(CtrUreUsuarioRepositorio usurep){
		log.debug("finding CtrUreUsuarioRepositorio instance by criteria");
		List<CtrUreUsuarioRepositorio> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(CtrUreUsuarioRepositorio.class);
		if(usurep.getSecPerPersona().getPerPrimerNombre() != null && !usurep.getSecPerPersona().getPerPrimerNombre().equals("") ){
			criteria.add(Restrictions.like("secPerPersona.perPrimerNombre",usurep.getSecPerPersona().getPerPrimerNombre()));
		}
	/*	if(pago.getInvProProveedor().getProId() != null && !pago.getInvProProveedor().getProId().equals("")){
			criteria.add(Restrictions.like("invProProveedor.proId",pago.getInvProProveedor().getProId()));
		}
		if(pago.getOpaFechaPago() != null && !pago.getOpaFechaPago().equals("")){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.opa_fecha_pago,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(pago.getOpaFechaPago())
					,Hibernate.STRING
			));
		}*/
		lst = (List<CtrUreUsuarioRepositorio>)criteria.getExecutableCriteria(getSession()).list();
		return lst;
	}
	
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(ctr.ureId) + 1  as ureId from CtrUreUsuarioRepositorio ctr";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				Integer value = (Integer) obj;
				id = value;
			}else if (obj instanceof BigDecimal) {
				BigDecimal value = (BigDecimal) obj;
				id = new Integer(value.intValue());
			}else {
				id = new Integer(0);
			}
			getSession().flush();
			getSession().clear();
		}catch(RuntimeException re) {
			log.error("Secuencia fallida", re);
			throw re;
		}
		return id;
	} 
}