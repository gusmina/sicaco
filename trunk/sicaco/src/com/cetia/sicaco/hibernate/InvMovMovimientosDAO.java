package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvMovMovimientos entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvMovMovimientos
 * @author MyEclipse Persistence Tools
 */

public class InvMovMovimientosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(InvMovMovimientosDAO.class);
	// property constants
	public static final String MOV_UNIDADES = "movUnidades";
	public static final String MOV_VALOR = "movValor";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";

	public InvMovMovimientosDAO(Session session) {
		super(session);
	}

	public void save(InvMovMovimientos transientInstance) {
		log.debug("saving InvMovMovimientos instance");
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

	public void delete(InvMovMovimientos persistentInstance) {
		log.debug("deleting InvMovMovimientos instance");
		try {
			getSession().delete(persistentInstance);
			getSession().flush();
			getSession().clear();
			log.debug("delete succ" +
					"essful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvMovMovimientos findById(java.lang.Integer id) {
		log.debug("getting InvMovMovimientos instance with id: " + id);
		try {
			InvMovMovimientos instance = (InvMovMovimientos) getSession().get(
					"com.cetia.sicaco.hibernate.InvMovMovimientos", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvMovMovimientos instance) {
		log.debug("finding InvMovMovimientos instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvMovMovimientos").add(
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
		log.debug("finding InvMovMovimientos instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvMovMovimientos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMovUnidades(Object movUnidades) {
		return findByProperty(MOV_UNIDADES, movUnidades);
	}

	public List findByMovValor(Object movValor) {
		return findByProperty(MOV_VALOR, movValor);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findAll() {
		log.debug("finding all InvMovMovimientos instances");
		try {
			String queryString = "from InvMovMovimientos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvMovMovimientos merge(InvMovMovimientos detachedInstance) {
		log.debug("merging InvMovMovimientos instance");
		try {
			InvMovMovimientos result = (InvMovMovimientos) getSession().merge(
					detachedInstance);
			getSession().flush();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvMovMovimientos instance) {
		log.debug("attaching dirty InvMovMovimientos instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			getSession().clear();
			log.debug("attach succ" +
					"essful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvMovMovimientos instance) {
		log.debug("attaching clean InvMovMovimientos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			getSession().clear();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByArt(String art) {
		log.debug("finding all InvMovMovimientos instances by artCodigo");
		try {
			String queryString = "from InvMovMovimientos mov where mov.invArtArticulo.artCodigo = '" + art + "'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByArtBod(String art, Integer bodId) {
		log.debug("finding all InvMovMovimientos instances by artCodigo and bodId");
		try {
			String queryString = "from InvMovMovimientos mov where mov.invArtArticulo.artCodigo = '" + art + "'" +
				" and mov.invBodBodegas.bodId = " + bodId;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByArtBodDate(String art, Integer bodId, String ini, String fin) {
		log.debug("finding all InvMovMovimientos instances by artCodigo and bodId and dates");
		try {
			String queryString = null;
			if( ini != null && !ini.equals("") && (fin == null || fin.equals(""))){
				queryString = "from InvMovMovimientos mov where mov.invArtArticulo.artCodigo = '" + art + "'" +
				" and mov.invBodBodegas.bodId = " + bodId + " and DATE_FORMAT(mov.audFechaCreacion,'%d-%m-%Y') >= '" + ini + "' " ;
			}
			if( (ini == null || ini.equals("")) && fin != null && !fin.equals("")){
				queryString = "from InvMovMovimientos mov where mov.invArtArticulo.artCodigo = '" + art + "'" +
				" and mov.invBodBodegas.bodId = " + bodId + " and DATE_FORMAT(mov.audFechaCreacion,'%d-%m-%Y') <= '"+ fin + "'" +
				"order by mov.audFechaCreacion desc";
			}
			if( ini != null && !ini.equals("") && fin != null && !fin.equals("")){
				queryString = "from InvMovMovimientos mov where mov.invArtArticulo.artCodigo = '" + art + "'" +
				" and mov.invBodBodegas.bodId = " + bodId + " and DATE_FORMAT(mov.audFechaCreacion,'%d-%m-%Y') between '"+ ini + "' and '" + fin + "'";
			}
			if(queryString == null || queryString.equals("")){
				ArrayList lst = new ArrayList();
				return lst;
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(mov.movId) + 1  as mId from InvMovMovimientos mov";
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
	/* MODIFICACIONES PARA PAGINACION */
	public Integer getTotalRowCountByArtBod(String art, Integer bodId) {		
		try {
			String queryString = "select count(mov.movId)from InvMovMovimientos mov where mov.invArtArticulo.artCodigo = '" + art + "'" +
				" and mov.invBodBodegas.bodId = " + bodId;
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByArtBod(String art, Integer bodId, int rowStart,int rowEnd) {
		log.debug("finding all InvMovMovimientos instances by artCodigo and bodId");
		try {
			String queryString = "from InvMovMovimientos mov where mov.invArtArticulo.artCodigo = '" + art + "'" +
				" and mov.invBodBodegas.bodId = " + bodId;
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/* FIN DE MODIFICACIONES PARA PAGINACION */
}