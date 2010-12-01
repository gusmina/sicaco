package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaTahTipoAhorro entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaTahTipoAhorro
 * @author MyEclipse Persistence Tools
 */

public class CtaTahTipoAhorroDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CtaTahTipoAhorroDAO.class);
	// property constants
	public static final String TAH_NOMBRE = "tahNombre";
	public static final String TAH_DESCRIPCION = "tahDescripcion";
	
	public static final String TAH_FECHA_FIN = "tahFechaFin";
	public static final String TAH_PLAN = "ctaPlmPlanMeses.plmId";
	
	public CtaTahTipoAhorroDAO(Session session) {
		super(session);
	}

	public void save(CtaTahTipoAhorro transientInstance) {
		log.debug("saving CtaTahTipoAhorro instance");
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

	public void delete(CtaTahTipoAhorro persistentInstance) {
		log.debug("deleting CtaTahTipoAhorro instance");
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

	public CtaTahTipoAhorro findById(java.lang.Integer id) {
		log.debug("getting CtaTahTipoAhorro instance with id: " + id);
		try {
			CtaTahTipoAhorro instance = (CtaTahTipoAhorro) getSession().get(
					"com.cetia.sicaco.hibernate.CtaTahTipoAhorro", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaTahTipoAhorro instance) {
		log.debug("finding CtaTahTipoAhorro instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaTahTipoAhorro").add(
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
		log.debug("finding CtaTahTipoAhorro instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaTahTipoAhorro as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findAPlazos() {
		log.debug("finding CtaTahTipoAhorro instance with property: ");
		try {
			String queryString = "from CtaTahTipoAhorro as model where model.tahFechaFin is not null or model.ctaPlmPlanMeses is not null";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByTahNombre(Object tahNombre) {
		return findByProperty(TAH_NOMBRE, tahNombre);
	}

	public List findByTahDescripcion(Object tahDescripcion) {
		return findByProperty(TAH_DESCRIPCION, tahDescripcion);
	}
	
	public List findByTahFechaFin(Object tahFechaFin) {
		return findByProperty(TAH_FECHA_FIN, tahFechaFin);
	}
	
	public List findByTahPlan(Object tahPlan) {
		return findByProperty(TAH_PLAN, tahPlan);
	}
	
	public List findAll() {
		log.debug("finding all CtaTahTipoAhorro instances");
		try {
			String queryString = "from CtaTahTipoAhorro";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaTahTipoAhorro merge(CtaTahTipoAhorro detachedInstance) {
		log.debug("merging CtaTahTipoAhorro instance");
		try {
			CtaTahTipoAhorro result = (CtaTahTipoAhorro) getSession().merge(
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

	public void attachDirty(CtaTahTipoAhorro instance) {
		log.debug("attaching dirty CtaTahTipoAhorro instance");
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

	public void attachClean(CtaTahTipoAhorro instance) {
		log.debug("attaching clean CtaTahTipoAhorro instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia - Tipo Ahorro");
		try {
			String sql = "select max(tah.tahId) + 1  as id from CtaTahTipoAhorro tah";
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
			log.error("Secuencia fallida - Tipo Ahorro", re);
			throw re;
		}
		return id;
	} 
	
	public List findByUpdatedName(Integer id,String nombre) {
		try{
			String sql = "from CtaTahTipoAhorro tah where tah.tahId != ? and tah.tahNombre = ?";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, id);
			queryObject.setParameter(1, nombre);
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findTiposVigentes(Date fecha) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "from CtaTahTipoAhorro tah  where ( ? " +
					"between tah.ctaLahLineaAhorro.lahDesde and tah.ctaLahLineaAhorro.lahHasta) or tah.ctaLahLineaAhorro.lahHasta is null";
			Query queryObject = getSession().createQuery(sql);
			queryObject.setParameter(0, sdf.format(fecha));
			return queryObject.list();
		}		
		catch (RuntimeException re) {
			throw re;
		}
	}
	
	public CtaTahTipoAhorro findAhorroConMenorTasa() {
		CtaTahTipoAhorro resp = null;
		try{
			String sql = "	from CtaTahTipoAhorro tipo where tipo.ctaTinTasaInteres.tinTasa = (select min(tcp.ctaTinTasaInteres.tinTasa)  from CtaTahTipoAhorro tcp ) and ( tipo.tahFechaFin <> curdate() or tipo.tahFechaFin is null) ";
			Query queryObject = getSession().createQuery(sql);
			List <CtaTahTipoAhorro>lista = queryObject.list();
			if(!lista.isEmpty()){
				resp = lista.get(0);
			}
			return resp;
		}		
		catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CtaTahTipoAhorro> findByLinea(Integer id) {
		String sql = "from CtaTahTipoAhorro tha " +
				"where tha.ctaLahLineaAhorro.lahId = "+id;
		Query query = getSession().createQuery(sql);
		return query.list();
	}
	
}