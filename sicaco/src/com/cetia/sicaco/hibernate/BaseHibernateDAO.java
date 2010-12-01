package com.cetia.sicaco.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

/**
 * Data access object (DAO) for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {

	public static Log log = LogFactory.getLog(BaseHibernateDAO.class);
	
	//FIXME Mauricio Jovel: Eliminar el static cuando ya se implemente la nueva forma de obtener la sesion.
	private /*static*/ Session session;
	
	//FIXME Mauricio Jovel: eliminar este constructor cuando se inicie los cambios para el filtro
//	public BaseHibernateDAO(){/*session = null;*/}
	public BaseHibernateDAO(Session session) {
		//FIXME Mauricio Jovel: Descomentarear estas lineas de codigo
		this.session = session;
	}

	public Session getSession() {
		//FIXME Mauricio Jovel: eliminar las siguientes lineas de codigo
		//-- Inicia la eliminacion----
//		Context ctx;
//		try {
//			
//			
//			if(session==null||!session.isOpen()) {
//				ctx = new InitialContext();
//				
//				SessionFactory sf = (SessionFactory) ctx.lookup("foo:SessionFactory");
//				session = sf.openSession();
//				
//			}
//			
//			return session;
//		} catch (NamingException e) {
//			log.fatal("No se pudo inicializar el contexto");
//			throw new RuntimeException(e);
//		}
		//-- Hasta aqui llega la eliminacion.
		
		//FIXME Mauricio Jovel: descomentarear esta linea para poder realizar la nueva forma de sesion
		return session;

	}

	public void closeSession() {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

}