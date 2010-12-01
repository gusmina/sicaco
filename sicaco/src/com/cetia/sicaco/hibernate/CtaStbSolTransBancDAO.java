package com.cetia.sicaco.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * CtaStbSolTransBanc entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.CtaStbSolTransBanc
 * @author MyEclipse Persistence Tools
 */

public class CtaStbSolTransBancDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(CtaStbSolTransBancDAO.class);
	// property constants
	public static final String STB_RAZON = "stbRazon";
	public static final String STB_MONTO = "stbMonto";
	public static final String STB_ESTADO = "stbEstado";
	public static final String STB_NOMBRE_ASOCIADO = "stbNombreAsociado";
	public static final String STB_TIPO_AHORRO = "stbTipoAhorro";

	public CtaStbSolTransBancDAO(Session session) {
		super(session);
	}

	public void save(CtaStbSolTransBanc transientInstance) {
		log.debug("saving CtaStbSolTransBanc instance");
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

	public void delete(CtaStbSolTransBanc persistentInstance) {
		log.debug("deleting CtaStbSolTransBanc instance");
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

	public CtaStbSolTransBanc findById(java.lang.Integer id) {
		log.debug("getting CtaStbSolTransBanc instance with id: " + id);
		try {
			CtaStbSolTransBanc instance = (CtaStbSolTransBanc) getSession()
					.get("com.cetia.sicaco.hibernate.CtaStbSolTransBanc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CtaStbSolTransBanc instance) {
		log.debug("finding CtaStbSolTransBanc instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.CtaStbSolTransBanc").add(
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
		log.debug("finding CtaStbSolTransBanc instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CtaStbSolTransBanc as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStbRazon(Object stbRazon) {
		return findByProperty(STB_RAZON, stbRazon);
	}

	public List findByStbMonto(Object stbMonto) {
		return findByProperty(STB_MONTO, stbMonto);
	}
	
	public List findByStbNombreAsociado(Object stbNombreAsociado) {
		return findByProperty(STB_NOMBRE_ASOCIADO, stbNombreAsociado);
	}
	
	public List findByStbEstado(Object stbEstado) {
		return findByProperty(STB_ESTADO, stbEstado);
	}
	
	public List findByStbTipoAhorro(Object stbTipoAhorro) {
		return findByProperty(STB_TIPO_AHORRO, stbTipoAhorro);
	}

	public List findAll() {
		log.debug("finding all CtaStbSolTransBanc instances");
		try {
			String queryString = "from CtaStbSolTransBanc stb order by stb.stbEstado asc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CtaStbSolTransBanc merge(CtaStbSolTransBanc detachedInstance) {
		log.debug("merging CtaStbSolTransBanc instance");
		try {
			CtaStbSolTransBanc result = (CtaStbSolTransBanc) getSession()
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

	public void attachDirty(CtaStbSolTransBanc instance) {
		log.debug("attaching dirty CtaStbSolTransBanc instance");
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

	public void attachClean(CtaStbSolTransBanc instance) {
		log.debug("attaching clean CtaStbSolTransBanc instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByBanco(Integer banId) {
		log.debug("finding all CtaStbSolTransBanc instances");
		ArrayList<CtaStbSolTransBanc> stbList = new ArrayList<CtaStbSolTransBanc>();
		try {
			String queryString = "from CtaStbSolTransBanc stb " +
					"where stb.ctaCbaCuentaBancaria.ctrBanBanco.banId = " + banId + " " +
					"order by stb.stbEstado asc";
			Query queryObject = getSession().createQuery(queryString);
			stbList.addAll(queryObject.list());
			
			String queryString2 = "from CtaStbSolTransBanc stb " +
					"where stb.invPcbProveedorCuentaBancaria.id.ctrBanBanco.banId = " + banId + " " +
					"order by stb.stbEstado asc";
			Query queryObject2 = getSession().createQuery(queryString2);
			stbList.addAll(queryObject2.list());
			return stbList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByBancoAndRubro(Integer banId, String rubro) {
		log.debug("finding all CtaStbSolTransBanc instances");
		ArrayList<CtaStbSolTransBanc> stbList = new ArrayList<CtaStbSolTransBanc>();
		try {
			String queryString = "from CtaStbSolTransBanc stb " +
					"where stb.ctaCbaCuentaBancaria.ctrBanBanco.banId = " + banId + " " + "and stb.stbRazon like ('%"+rubro+"%')"+
					"order by stb.stbEstado asc";
			Query queryObject = getSession().createQuery(queryString);
			stbList.addAll(queryObject.list());
			
			String queryString2 = "from CtaStbSolTransBanc stb " +
					"where stb.invPcbProveedorCuentaBancaria.id.ctrBanBanco.banId = " + banId + " " + "and stb.stbRazon like ('%"+rubro+"%')"+
					"order by stb.stbEstado asc";
			Query queryObject2 = getSession().createQuery(queryString2);
			stbList.addAll(queryObject2.list());
			return stbList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findByBancoAndEstado(Integer banId, String estado) {
		log.debug("finding all CtaStbSolTransBanc instances");
		ArrayList<CtaStbSolTransBanc> stbList = new ArrayList<CtaStbSolTransBanc>();
		try {
			String queryString = "from CtaStbSolTransBanc stb " +
				"where stb.ctaCbaCuentaBancaria.ctrBanBanco.banId = " + banId + " " +
				"and stb.stbEstado = '"+estado+"'";
			Query queryObject = getSession().createQuery(queryString);
			stbList.addAll(queryObject.list());
			
			String queryString2 = "from CtaStbSolTransBanc stb " +
					"where stb.invPcbProveedorCuentaBancaria.id.ctrBanBanco.banId = " + banId + " " +
					"and stb.stbEstado = '"+estado+"'";
			Query queryObject2 = getSession().createQuery(queryString2);
			stbList.addAll(queryObject2.list());
			return stbList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}