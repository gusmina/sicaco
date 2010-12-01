package com.cetia.sicaco.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

import com.cetia.sicaco.facturacion.struts.form.FacturaManualForm;

/**
 * A data access object (DAO) providing persistence and search support for
 * FacFenFacturaEncabezado entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.cetia.sicaco.hibernate.FacFenFacturaEncabezado
 * @author MyEclipse Persistence Tools
 */

public class FacFenFacturaEncabezadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(FacFenFacturaEncabezadoDAO.class);
	// property constants
	public static final String FEN_SERIE_FACTURA = "fenSerieFactura";
	public static final String FEN_NUMERO_FACTURA = "fenNumeroFactura";
	public static final String FEN_REGISTRO = "fenRegistro";
	public static final String FEN_TOTAL_VENTA = "fenTotalVenta";
	public static final String FEN_TOTAL_VENTAS_EXENTAS = "fenTotalVentasExentas";
	public static final String FEN_IVA_RETENIDO = "fenIvaRetenido";
	public static final String FEN_TIPO_FACTURA = "fenTipoFactura";
	public static final String FEN_CANCELADA = "fenCancelada";
	public static final String FEN_TIPO_PAGO = "fenTipoPago";
	public static final String AUD_USUARIO_CREACION = "audUsuarioCreacion";
	public static final String AUD_USUARIO_MODIFICACION = "audUsuarioModificacion";
	public static final String FEN_ORIGINAL = "fenOriginal";

	public FacFenFacturaEncabezadoDAO(Session session) {
		super(session);
	}

	public void save(FacFenFacturaEncabezado transientInstance) {
		log.debug("saving FacFenFacturaEncabezado instance");
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

	public void delete(FacFenFacturaEncabezado persistentInstance) {
		log.debug("deleting FacFenFacturaEncabezado instance");
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

	public FacFenFacturaEncabezado findById(int id) {
		log.debug("getting FacFenFacturaEncabezado instance with id: " + id);
		try {
			FacFenFacturaEncabezado instance = (FacFenFacturaEncabezado) getSession()
					.get("com.cetia.sicaco.hibernate.FacFenFacturaEncabezado",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FacFenFacturaEncabezado instance) {
		log.debug("finding FacFenFacturaEncabezado instance by example");
		try {
			List results = getSession().createCriteria(
					"com.cetia.sicaco.hibernate.FacFenFacturaEncabezado").add(
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
		log.debug("finding FacFenFacturaEncabezado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FacFenFacturaEncabezado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFenSerieFactura(Object fenSerieFactura) {
		return findByProperty(FEN_SERIE_FACTURA, fenSerieFactura);
	}

	public List findByFenNumeroFactura(Object fenNumeroFactura) {
		return findByProperty(FEN_NUMERO_FACTURA, fenNumeroFactura);
	}

	public List findByFenRegistro(Object fenRegistro) {
		return findByProperty(FEN_REGISTRO, fenRegistro);
	}

	public List findByFenTotalVenta(Object fenTotalVenta) {
		return findByProperty(FEN_TOTAL_VENTA, fenTotalVenta);
	}

	public List findByFenTotalVentasExentas(Object fenTotalVentasExentas) {
		return findByProperty(FEN_TOTAL_VENTAS_EXENTAS, fenTotalVentasExentas);
	}

	public List findByFenIvaRetenido(Object fenIvaRetenido) {
		return findByProperty(FEN_IVA_RETENIDO, fenIvaRetenido);
	}

	public List findByFenTipoFactura(Object fenTipoFactura) {
		return findByProperty(FEN_TIPO_FACTURA, fenTipoFactura);
	}

	public List findByFenCancelada(Object fenCancelada) {
		return findByProperty(FEN_CANCELADA, fenCancelada);
	}
	
	public List findByFenTipoPago(Object fenTipoPago) {
		return findByProperty(FEN_TIPO_PAGO, fenTipoPago);
	}

	public List findByAudUsuarioCreacion(Object audUsuarioCreacion) {
		return findByProperty(AUD_USUARIO_CREACION, audUsuarioCreacion);
	}

	public List findByAudUsuarioModificacion(Object audUsuarioModificacion) {
		return findByProperty(AUD_USUARIO_MODIFICACION, audUsuarioModificacion);
	}

	public List findByFenOriginal(Object fenOriginal) {
		return findByProperty(FEN_ORIGINAL, fenOriginal);
	}
	
	public List findAll() {
		log.debug("finding all FacFenFacturaEncabezado instances");
		try {
			String queryString = "from FacFenFacturaEncabezado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FacFenFacturaEncabezado merge(
			FacFenFacturaEncabezado detachedInstance) {
		log.debug("merging FacFenFacturaEncabezado instance");
		try {
			FacFenFacturaEncabezado result = (FacFenFacturaEncabezado) getSession()
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

	public void attachDirty(FacFenFacturaEncabezado instance) {
		log.debug("attaching dirty FacFenFacturaEncabezado instance");
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

	public void attachClean(FacFenFacturaEncabezado instance) {
		log.debug("attaching clean FacFenFacturaEncabezado instance");
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
		log.debug("Consiguiendo la siguiente secuencia");
		try {
			String sql = "select max(fe.fenId) + 1  as fenId from FacFenFacturaEncabezado fe";
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
	
	public List findAllCompra(int compra) {
		log.debug("finding all FacFenFacturaEncabezado instances");
		try {
			String queryString = "";
			if( compra == 1){
				queryString = "from FacFenFacturaEncabezado fe where fe.facFusFacturaUso.fusId =" + 1 
				+" or fe.facFusFacturaUso.fusId =" + 3 + "order by"
				+ " fe.fenFechaFactura desc";
			}
			if( compra == 2){
				queryString = "from FacFenFacturaEncabezado fe where fe.facFusFacturaUso.fusId =" + 2 
				+ " or fe.facFusFacturaUso.fusId =" + 4 
				+ " order by"
				+ " fe.fenFechaFactura desc";
			}
			if( compra == 3){
				queryString = "from FacFenFacturaEncabezado fe where fe.facFusFacturaUso.fusId =" + 8 
				+" order by" + " fe.fenFechaFactura desc";
			}
			if( compra == 4){
				queryString = "from FacFenFacturaEncabezado fe where fe.facFusFacturaUso.fusId =" + 7 
				+ " order by" + " fe.fenFechaFactura desc";
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<FacFenFacturaEncabezado> findByCriteria(FacFenFacturaEncabezado encabezado,
			Integer voc, String proCod, String proNom){
		log.debug("finding FacFenFacturaEncabezado instance by criteria");
		List<FacFenFacturaEncabezado> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FacFenFacturaEncabezado.class);
		String c="";
		if(voc == 1) c = "c";
		if(voc == 2) c = "v";
		if(voc == 3) c = "8";
		if(voc == 4) c = "7";
		encabezado.getFacFusFacturaUso().setFusToperacion(c);
		
		InvProProveedor proveedor = new InvProProveedor();
		if(proCod != null && !proCod.equals("")) proveedor.setProCodigo(proCod);
		if(proNom != null && !proNom.equals("")) proveedor.setProNombre(proNom);
		
		if(encabezado.getInvBodBodegas().getBodId() !=-1){
			criteria.add(Restrictions.eq("invBodBodegas.bodId", encabezado.getInvBodBodegas().getBodId()));
		}
		if(encabezado.getFenSerieFactura() != null && !encabezado.getFenSerieFactura().equals("")){
			criteria.add(Restrictions.like("fenSerieFactura", "%" + encabezado.getFenSerieFactura() + "%"));
		}
		if(encabezado.getFenNumeroFactura() != null && !encabezado.getFenNumeroFactura().equals("")){
			criteria.add(Restrictions.like("fenNumeroFactura", "%" + encabezado.getFenNumeroFactura() + "%"));
		}
		if (encabezado.getHseFechaFactIni() != null && encabezado.getHseFechaFactFin() != null) {
			criteria.add(Restrictions.sqlRestriction(
					"(DATE_FORMAT({alias}.fen_fecha_factura,'%d-%m-%Y')  between ? and ?)",
					new Object[]{new SimpleDateFormat("dd-MM-yyyy").format(encabezado.getHseFechaFactIni())
						,new SimpleDateFormat("dd-MM-yyyy").format(encabezado.getHseFechaFactFin())}
					,new Type[]{Hibernate.STRING,Hibernate.STRING}
			));
		}
		if (encabezado.getHseFechaFactIni() != null && encabezado.getHseFechaFactFin() == null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.fen_fecha_factura,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(encabezado.getHseFechaFactIni())
					,Hibernate.STRING
			));
			
		}
		if(encabezado.getHseFechaFactIni() == null && encabezado.getHseFechaFactFin() != null){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.fen_fecha_factura,'%d-%m-%Y') = ?",
					new SimpleDateFormat("dd-MM-yyyy").format(encabezado.getHseFechaFactFin())
					,Hibernate.STRING
			));
		}
		
		if(proveedor != null && (proveedor.getProCodigo() != null && !proveedor.getProCodigo().equals("") || 
				proveedor.getProNombre() != null && !proveedor.getProNombre().equals(""))){
			InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSession());
			List lpc = proveedorDAO.findByCriteria(proveedor);
			if(lpc.size()>0 ){
				InvProProveedor pro = (InvProProveedor) lpc.get(0);
				criteria.add(Restrictions.sqlRestriction(
						"{alias}.pro_id = ?", pro.getProId(), Hibernate.INTEGER					
				));
			}
			
		}
		
		if(encabezado.getFacFusFacturaUso().getFusToperacion().equals("c")){
			criteria.add(Restrictions.sqlRestriction(
					"({alias}.fus_id = 1 or {alias}.fus_id = 3)"					
			));
		}
		if(encabezado.getFacFusFacturaUso().getFusToperacion().equals("8")){
			criteria.add(Restrictions.sqlRestriction(
					"({alias}.fus_id = 8)"					
			));
		}
		if(encabezado.getFacFusFacturaUso().getFusToperacion().equals("v")){
			criteria.add(Restrictions.sqlRestriction(
					"({alias}.fus_id = 2 or {alias}.fus_id = 4 )"					
			));
		}
		if(encabezado.getFacFusFacturaUso().getFusToperacion().equals("7")){
			criteria.add(Restrictions.sqlRestriction(
					"({alias}.fus_id = 7)"					
			));
		}
		
		return (List<FacFenFacturaEncabezado>)criteria.getExecutableCriteria(getSession()).list();
	}

	public List findAllCompraGuardadas(int voc) {
		log.debug("finding all FacFenFacturaEncabezado instances");
		try {
			String queryString = "";
			if( voc == 1){
				queryString = "from FacFenFacturaEncabezado fe where (fe.facFusFacturaUso.fusId =" + 1 
				+" or fe.facFusFacturaUso.fusId =" + 3 + ") " 
				+ "and fe.ctrEstEstado.estId = 3 ";
			}
			if( voc == 2){
				queryString = "from FacFenFacturaEncabezado fe where (fe.facFusFacturaUso.fusId =" + 2 
				+ " or fe.facFusFacturaUso.fusId =" + 4 + ") "
				+ " and fe.ctrEstEstado.estId = 3 ";
			}
			if( voc == 3){
				queryString = "from FacFenFacturaEncabezado fe where fe.facFusFacturaUso.fusId =" + 8 
				+ " and fe.ctrEstEstado.estId = 3 ";
			}
			if( voc == 4){
				queryString = "from FacFenFacturaEncabezado fe where fe.facFusFacturaUso.fusId =" + 7 
				+ " and fe.ctrEstEstado.estId = 3 ";
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByFusId(int i) {
		log.debug("finding all FacFenFacturaEncabezado instances");
		try {
			String queryString = "from FacFenFacturaEncabezado fen " +
					"where fen.facFusFacturaUso.fusId = " + i + " " +
					"order by fen.audFechaCreacion desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<FacFenFacturaEncabezado> findByForm(int fusId, FacturaManualForm manualForm) {
		log.debug("finding FacFenFacturaEncabezado instance by criteria");
		List<FacFenFacturaEncabezado> lst = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FacFenFacturaEncabezado.class);
		InvProProveedor proveedor = new InvProProveedor();
		FacCliCliente cliente = new FacCliCliente();
		if(fusId == 5){
			if(manualForm.getCodCli() != null && !manualForm.getCodCli().equals("")){
				proveedor.setProCodigo(manualForm.getCodCli());
			}
			if(manualForm.getNombreCli() != null && !manualForm.getNombreCli().equals("")){
				proveedor.setProNombre(manualForm.getNombreCli());
			}
		}else{
			if(manualForm.getCodCli() != null && !manualForm.getCodCli().equals("")){
				cliente.setCliCodigo(manualForm.getCodCli());
			}
			if(manualForm.getNombreCli() != null && !manualForm.getNombreCli().equals("")){
				cliente.setCliNombre(manualForm.getNombreCli());
			}
		}
		if(manualForm.getFenSerieFactura() != null && !manualForm.getFenSerieFactura().equals("")){
			criteria.add(Restrictions.like("fenSerieFactura", "%" + manualForm.getFenSerieFactura() + "%"));
		}
		if(manualForm.getFenNumeroFactura() != null && !manualForm.getFenNumeroFactura().equals("")){
			criteria.add(Restrictions.like("fenNumeroFactura", "%" + manualForm.getFenNumeroFactura() + "%"));
		}
		if (manualForm.getFechaIni() != null && manualForm.getFechaFin() != null) {
			criteria.add(Restrictions.sqlRestriction(
					"(DATE_FORMAT({alias}.fen_fecha_factura,'%d-%m-%Y')  between ? and ?) ",
					new Object[]{new SimpleDateFormat("dd-MM-yyyy").format(manualForm.getFechaIni())
						,new SimpleDateFormat("dd-MM-yyyy").format(manualForm.getFechaFin())}
					,new Type[]{Hibernate.STRING,Hibernate.STRING}
			));
		}
		if (manualForm.getFechaIni() != null && manualForm.getFechaFin() == null) {
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.fen_fecha_factura,'%d-%m-%Y') = ? ",
					new SimpleDateFormat("dd-MM-yyyy").format(manualForm.getFechaIni())
					,Hibernate.STRING
			));
			
		}
		if(manualForm.getFechaIni() == null && manualForm.getFechaFin() != null){
			criteria.add(Restrictions.sqlRestriction(
					"DATE_FORMAT({alias}.fen_fecha_factura,'%d-%m-%Y') = ? ",
					new SimpleDateFormat("dd-MM-yyyy").format(manualForm.getFechaFin())
					,Hibernate.STRING
			));
		}
		
		if(proveedor != null && (proveedor.getProCodigo() != null && !proveedor.getProCodigo().equals("") || 
				proveedor.getProNombre() != null && !proveedor.getProNombre().equals(""))){
			InvProProveedorDAO proveedorDAO = new InvProProveedorDAO(getSession());
			List lpc = proveedorDAO.findByCriteria(proveedor);
			if(lpc.size()>0 ){
				InvProProveedor pro = (InvProProveedor) lpc.get(0);
				criteria.add(Restrictions.sqlRestriction(
						"{alias}.pro_id = ? ", pro.getProId(), Hibernate.INTEGER					
				));
			}
			
		}
		
		if(cliente != null && (cliente.getCliCodigo() != null && !cliente.getCliCodigo().equals("") || 
				cliente.getCliNombre() != null && !cliente.getCliNombre().equals(""))){
			FacCliClienteDAO clienteDAO = new FacCliClienteDAO(getSession());
			List lpc = clienteDAO.findByCriteria2(cliente);
			if(lpc.size()>0 ){
				FacCliCliente cli = (FacCliCliente) lpc.get(0);
				criteria.add(Restrictions.sqlRestriction(
						"{alias}.cli_Codigo = ? ", cli.getCliCodigo(), Hibernate.STRING
				));
			}
			
		}
		
		criteria.add(Restrictions.sqlRestriction(
				"{alias}.fus_id = " + fusId + " "					
		));
		
		return (List<FacFenFacturaEncabezado>)criteria.getExecutableCriteria(getSession()).list();
	}
	
}