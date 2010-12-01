package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
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
 * SecHseHistorialSesion entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.SecHseHistorialSesion
 * @author MyEclipse Persistence Tools
 */

public class SecHseHistorialSesionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecHseHistorialSesionDAO.class);
	// property constants
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	//private DetachedCriteria ;

	public SecHseHistorialSesionDAO(Session session) {
		super(session);
	}

	public void save(SecHseHistorialSesion transientInstance) {
		log.debug("saving SecHseHistorialSesion instance");
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

	public void delete(SecHseHistorialSesion persistentInstance) {
		log.debug("deleting SecHseHistorialSesion instance");
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

	public SecHseHistorialSesion findById(java.lang.Integer id) {
		log.debug("getting SecHseHistorialSesion instance with id: " + id);
		try {
			SecHseHistorialSesion instance = (SecHseHistorialSesion) getSession()
					.get(
							"com.cetia.sicaco.hibernate.SecHseHistorialSesion",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecHseHistorialSesion instance) {
		log.debug("finding SecHseHistorialSesion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.SecHseHistorialSesion")
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
		log.debug("finding SecHseHistorialSesion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecHseHistorialSesion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
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
		log.debug("finding all SecHseHistorialSesion hse instances order by hse.hseId desc");
		try {
			String queryString = "from SecHseHistorialSesion hse order by hse.hseId desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SecHseHistorialSesion> findByCriteria(SecHseHistorialSesion secHseHistorialSesion){
		List<SecHseHistorialSesion> secList = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SecHseHistorialSesion.class);
		if (secHseHistorialSesion.getSecIseInicioSesion().getIseNombreUsuario() != null && !secHseHistorialSesion.getSecIseInicioSesion().getIseNombreUsuario().trim().equals("")) {
			criteria.add(Restrictions.like("secIseInicioSesion.iseNombreUsuario", "%" + secHseHistorialSesion.getSecIseInicioSesion().getIseNombreUsuario() + "%"));
		}
		if (secHseHistorialSesion.getHseIp() != null && !secHseHistorialSesion.getHseIp().equals("")) {
			criteria.add(Restrictions.like("hseIp","%" + secHseHistorialSesion.getHseIp() + "%" ));
		}
		if (secHseHistorialSesion.getHseFechaAccesoIni() != null && secHseHistorialSesion.getHseFechaAccesoFin() != null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.hse_fecha_acceso,'%d-%m-%Y')  between ? and ?",
					new Object[]{new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoIni())
						,new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoFin())}
					,new Type[]{Hibernate.STRING,Hibernate.STRING}
			));
		}
		if (secHseHistorialSesion.getHseFechaSalidaIni()!= null && secHseHistorialSesion.getHseFechaSalidaFin() !=null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.hse_fecha_salida,'%d-%m-%Y')  between ? and ?",
					new Object[]{new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaIni())
						,new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaFin())}
					,new Type[]{Hibernate.STRING,Hibernate.STRING}
			));
		}
		if (secHseHistorialSesion.getHseFechaAccesoIni() != null && secHseHistorialSesion.getHseFechaAccesoFin() == null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.hse_fecha_acceso,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoIni())
					,Hibernate.STRING
			));
			
		}
		if(secHseHistorialSesion.getHseFechaAccesoIni() == null && secHseHistorialSesion.getHseFechaAccesoFin() != null){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.hse_fecha_acceso,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoFin())
					,Hibernate.STRING
			));
		}
		if (secHseHistorialSesion.getHseFechaSalidaIni()!= null && secHseHistorialSesion.getHseFechaSalidaFin() ==null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.hse_fecha_salida,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaIni())
					,Hibernate.STRING
			));
		}
		if(secHseHistorialSesion.getHseFechaSalidaIni() == null && secHseHistorialSesion.getHseFechaSalidaFin() !=null){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.hse_fecha_salida,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaFin())
					,Hibernate.STRING
			));
		}
		criteria.addOrder(Order.desc("hseId"));
		secList = criteria.getExecutableCriteria(getSession()).list();
		
		return secList;
	}

	public SecHseHistorialSesion merge(SecHseHistorialSesion detachedInstance) {
		log.debug("merging SecHseHistorialSesion instance");
		try {
			SecHseHistorialSesion result = (SecHseHistorialSesion) getSession()
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

	public void attachDirty(SecHseHistorialSesion instance) {
		log.debug("attaching dirty SecHseHistorialSesion instance");
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

	public void attachClean(SecHseHistorialSesion instance) {
		log.debug("attaching clean SecHseHistorialSesion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public Integer nextId() {
		Integer id;
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(hs.hseId) + 1  as hId from SecHseHistorialSesion hs";
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
	
	public void deleteBy(SecHseHistorialSesion secHseHistorialSesion){
		String hql = "delete SecHseHistorialSesion where hseId = hseId";
		Properties properties = new Properties();
		if (secHseHistorialSesion.getSecIseInicioSesion().getIseNombreUsuario() != null && !secHseHistorialSesion.getSecIseInicioSesion().getIseNombreUsuario().trim().equals("")) {
			hql+=" and ";
			hql+="secIseInicioSesion = (from SecIseInicioSesion where iseNombreUsuario = :iseNombreUsuario)";
			properties.put("iseNombreUsuario",secHseHistorialSesion.getSecIseInicioSesion().getIseNombreUsuario());	
		}
		if (secHseHistorialSesion.getHseFechaAccesoIni() != null && secHseHistorialSesion.getHseFechaAccesoFin() != null) {
			hql+=" and ";
			hql+=" Date_format(hseFechaAcceso,'%d-%m-%Y') between  :hseFechaAccesoIni and :hseFechaAccesoFin";
			properties.put("hseFechaAccesoIni",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoIni()
			));
			properties.put("hseFechaAccesoFin", new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoFin()
			));
		}
		if(secHseHistorialSesion.getHseFechaAccesoIni() != null && secHseHistorialSesion.getHseFechaAccesoFin() == null){
			hql+=" and ";
			hql+=" Date_format(hseFechaAcceso,'%d-%m-%Y') =  :hseFechaAccesoIni";
			properties.put("hseFechaAccesoIni",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoIni()
			));
		}
		if(secHseHistorialSesion.getHseFechaAccesoIni() == null && secHseHistorialSesion.getHseFechaAccesoFin() != null){
			hql+=" and ";
			hql+=" Date_format(hseFechaAcceso,'%d-%m-%Y') =  :hseFechaAccesoFin";
			properties.put("hseFechaAccesoFin",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaAccesoFin()
			));
		}
		if (secHseHistorialSesion.getHseFechaSalidaIni() != null && secHseHistorialSesion.getHseFechaSalidaFin() != null) {
			hql+=" and ";
			hql+=" Date_format(hseFechaSalida,'%d-%m-%Y') between  :hseFechaSalidaIni and :hseFechaSalidaFin";
			properties.put("hseFechaSalidaIni",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaIni()
			));
			properties.put("hseFechaSalidaFin", new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaFin()
			));
		}
		if(secHseHistorialSesion.getHseFechaSalidaIni() != null && secHseHistorialSesion.getHseFechaSalidaFin() == null){
			hql+=" and ";
			hql+=" Date_format(hseFechaSalida,'%d-%m-%Y') =  :hseFechaSalidaIni";
			properties.put("hseFechaSalidaIni",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaIni()
			));
		}
		if(secHseHistorialSesion.getHseFechaSalidaIni() == null && secHseHistorialSesion.getHseFechaSalidaFin() != null){
			hql+=" and ";
			hql+=" Date_format(hseFechaSalida,'%d-%m-%Y') =  :hseFechaSalidaFin";
			properties.put("hseFechaSalidaFin",
					new SimpleDateFormat("dd-MM-yyyy").format(secHseHistorialSesion.getHseFechaSalidaFin()
			));
		}
		Query q = getSession().createQuery(hql);
		for(Object o : properties.keySet()){
			String keyO = (String)o;
			q.setParameter(keyO, 
					properties.get(keyO)
			);
		}
		q.executeUpdate();

	}
	
	public Integer getTotalRowCount() {
		log.debug("counting all rows of SecHseHistorialSesion");
		try {
			String queryString = "select count(h.hseId)from SecHseHistorialSesion h";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SecHseHistorialSesion> findAll(int rowStart,int rowEnd) {
		log.debug("finding all SecHseHistorialSesion hse instances order by hse.hseId desc");
		try {
			String queryString = "from SecHseHistorialSesion hse order by hse.hseId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(rowStart);
			queryObject.setMaxResults(rowEnd-rowStart);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}