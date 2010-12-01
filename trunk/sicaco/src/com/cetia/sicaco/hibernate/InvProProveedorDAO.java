package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
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
 * InvProProveedor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.InvProProveedor
 * @author MyEclipse Persistence Tools
 */

public class InvProProveedorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(InvProProveedorDAO.class);
	// property constants
	public static final String PRO_NOMBRE = "proNombre";
	public static final String PRO_DIRECCION = "proDireccion";
	public static final String PRO_NUMERO_TELEFONO = "proNumeroTelefono";
	public static final String PRO_HORARIO_OFICINA = "proHorarioOficina";
	public static final String PRO_NIT = "proNit";
	public static final String PRO_LIMITE_CREDITO = "proLimiteCredito";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String PRO_CODIGO = "proCodigo";
	public static final String PRO_REGISTRO = "proRegistro";
	public static final String PRO_ESTADO = "proEstado";
	public static final String PRO_GIRO = "proGiro";

	public InvProProveedorDAO(Session session) {
		super(session);
	}

	public void save(InvProProveedor transientInstance) {
		log.debug("saving InvProProveedor instance");
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

	public void delete(InvProProveedor persistentInstance) {
		log.debug("deleting InvProProveedor instance");
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

	public InvProProveedor findById(java.lang.Integer id) {
		log.debug("getting InvProProveedor instance with id: " + id);
		try {
			InvProProveedor instance = (InvProProveedor) getSession().get(
					"com.cetia.sicaco.hibernate.InvProProveedor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvProProveedor instance) {
		log.debug("finding InvProProveedor instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.InvProProveedor").add(
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
		log.debug("finding InvProProveedor instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvProProveedor as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProNombre(Object proNombre) {
		return findByProperty(PRO_NOMBRE, proNombre);
	}

	public List findByProDireccion(Object proDireccion) {
		return findByProperty(PRO_DIRECCION, proDireccion);
	}

	public List findByProNumeroTelefono(Object proNumeroTelefono) {
		return findByProperty(PRO_NUMERO_TELEFONO, proNumeroTelefono);
	}

	public List findByProHorarioOficina(Object proHorarioOficina) {
		return findByProperty(PRO_HORARIO_OFICINA, proHorarioOficina);
	}

	public List findByProNit(Object proNit) {
		return findByProperty(PRO_NIT, proNit);
	}

	public List findByProLimiteCredito(Object proLimiteCredito) {
		return findByProperty(PRO_LIMITE_CREDITO, proLimiteCredito);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findByProCodigo(Object proCodigo) {
		return findByProperty(PRO_CODIGO, proCodigo);
	}
	
	public List findByProRegistro(Object proRegistro) {
		return findByProperty(PRO_REGISTRO, proRegistro);
	}
	
	public List findByProEstado(Object proEstado) {
		return findByProperty(PRO_ESTADO, proEstado);
	}
	
	public List findByProGiro(Object proGiro) {
		return findByProperty(PRO_GIRO, proGiro);
	}
	
	public List findAll() {
		log.debug("finding all InvProProveedor instances");
		try {
			String queryString = "from InvProProveedor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvProProveedor merge(InvProProveedor detachedInstance) {
		log.debug("merging InvProProveedor instance");
		try {
			InvProProveedor result = (InvProProveedor) getSession().merge(
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

	public void attachDirty(InvProProveedor instance) {
		log.debug("attaching dirty InvProProveedor instance");
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

	public void attachClean(InvProProveedor instance) {
		log.debug("attaching clean InvProProveedor instance");
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
			String sql = "select max(pr.proId) + 1  as proId from InvProProveedor pr";
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
	
	public List<InvProProveedor> findByCriteria(InvProProveedor proveedor){
		log.debug("finding InvProProveedor instance by criteria");
		List<InvProProveedor> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(InvProProveedor.class);
		if (proveedor.getProCodigo()!= null && !proveedor.getProCodigo().trim().equals("")) {
 			criteria.add(Restrictions.like("proCodigo",proveedor.getProCodigo()));
		}
		if (proveedor.getProNombre()!= null && !proveedor.getProNombre().trim().equals("")) {
 			criteria.add(Restrictions.like("proNombre", "%" + proveedor.getProNombre() + "%"));
		}
		if (proveedor.getProNit()!= null && !proveedor.getProNit().trim().equals("")) {
			criteria.add(Restrictions.like("proNit", proveedor.getProNit()));
		}
		if (proveedor.getInvTprTipoProveedor().getTprId()!= null && !proveedor.getInvTprTipoProveedor().getTprId().equals("")) {
			criteria.add(Restrictions.like("invTprTipoProveedor.tprId", proveedor.getInvTprTipoProveedor().getTprId()));
		}
		if (proveedor.getCtrPaiPais().getPaiId()!= null && !proveedor.getCtrPaiPais().getPaiId().equals("") 
				&& proveedor.getCtrPaiPais().getPaiId() != 0) {
			criteria.add(Restrictions.like("ctrPaiPais.paiId", proveedor.getCtrPaiPais().getPaiId()));
		}
		
		return (List<InvProProveedor>)criteria.getExecutableCriteria(getSession()).list();
	}
	
	public String nextCod() {
		String id = "";
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(pro.proId)  as proCodigo from InvProProveedor pro";
			Query query = getSession().createQuery(sql);
			
			Object obj = query.uniqueResult();
			if (obj instanceof Integer) {
				//String val = obj.toString();
				Integer value;
				value = (Integer)obj + 1;
				id = value.toString();
			}else {
				id = new Integer(1).toString();
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
	public Integer getTotalRowCount() {
		log.debug("counting all rows of SecPerPersona");
		try {
			String queryString = "select count(pro.proId)from InvProProveedor pro";
			Query queryObject = getSession().createQuery(queryString);
			return (Integer) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll(int rowStart,int rowEnd) {
		log.debug("finding all InvProProveedor instances");
		try {
			String queryString = "from InvProProveedor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/* FIN DE MODIFICACIONES PARA PAGINACION */
}