package com.mad.utilidades.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mad.utilidades.HibernatePlugIn;

/**
 * Filtro encargado de centralizar la apertura y cierre de la conexion de hibernate para
 * un request defindo.
 * 
 * @author Mauricio Jovel
 * @since 07-ABR-2010
 */
public class FiltroOpenSession implements Filter{
	private static Log log = LogFactory.getLog(FiltroOpenSession.class);
	private FilterConfig filterConfig;
	public static final String KEY_SESSION = "sessionActiva";
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest myRequest = (HttpServletRequest) request;
			// Obtenemos de sesion el session factory
			SessionFactory sf = (SessionFactory) filterConfig.getServletContext().getAttribute(HibernatePlugIn.SESSION_FACTORY_KEY);//myRequest.getSession().getAttribute(HibernatePlugIn.SESSION_FACTORY_KEY);
			Session session = null;
			if(sf!=null) {
				session = sf.openSession();
			}
			myRequest.setAttribute(KEY_SESSION, session);
			filterChain.doFilter(request, response);
			if(session!=null){
				// Solo por verificacion nos aseguramos que algun burro
				// no la haya cerrado antes
				if(session.isOpen()) {
					try {
						session.flush();
						session.clear();
						session.close();
					}catch(RuntimeException e) {
						log.error("No se pudo cerrar la session de hibernate.",e);
					}
				}else {
					//TODO Mauricio Jovel: Ingresar la ruta que esta cerrando anticipadamente la conexion.
					log.warn("La session ya se encontraba cerrada para el action");
				}
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//Por el momento no se reciben parametros.
		this.filterConfig = filterConfig;
	}
	
}
