package com.mad.utilidades;

import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 * Implements the <code>PlugIn</code> interface to configure the Hibernate
 * data persistence library.  A configured
 * <code>net.sf.hibernate.SessionFactory</code> is stored in the 
 * <code>ServletContext</code> of the web application unless the property
 * <code>storedInServletContext</code> is set to <code>false</code>.
 * 
 * <plugin class="net.sf.hibernate.plugins.struts.HibernatePlugIn">
 *   <set-property name="configFilePath"
 *                 value="path-to-config-file"/>
 *   <set-property name="storedInServletContext&quot"
 *                value="true-or-false"/>
 * </plugin>
 *
 * @author  <a href="mailto:bhandy@users.sf.net">Bradley M. Handy</a>
 * @version 1.0
 */
public class HibernatePlugIn implements PlugIn {
    
    /**
     * the key under which the <code>SessionFactory</code> instance is stored
     * in the <code>ServletContext</code>.
     */
    public static final String SESSION_FACTORY_KEY 
            = SessionFactory.class.getName();

    private static Log _log = LogFactory.getLog(HibernatePlugIn.class);
    
    /**
     * indicates whether the <code>SessionFactory</code> instance will be stored
     * in the <code>ServletContext</code>, or not.
     */
    private boolean _storedInServletContext = true;
    
    /**
     * the path to the xml configuration file.  the path should start with a
     * '/' character and be relative to the root of the class path.
     * (DEFAULT:  "/hibernate.cfg.xml")
     */
    private String _configFilePath;

    private ActionServlet _servlet = null;
    private ModuleConfig _config = null;
    private SessionFactory _factory = null;

    /**
     * Destroys the <code>SessionFactory</code> instance.
     */
    public void destroy() {
        _servlet = null;
        _config = null;
        try {
            _log.info("Destroying SessionFactory...");
            
            _factory.close();
            
            _log.info("SessionFactory destroyed...");
        } catch (Exception e) {
            _log.error("Unable to destroy SessionFactory...(exception ignored)",
                    e);
        }
    }
    
    /**
     * Initializes the <code>SessionFactory</code>.
     * @param servlet the <code>ActionServlet</code> instance under which the
     *        plugin will run.
     * @param config the <code>ModuleConfig</code> for the module under which
     *        the plugin will run.
     */
    public void init(ActionServlet servlet, ModuleConfig config)
    throws ServletException {
        _servlet = servlet;
        _config = config;
        
        initHibernate();
    }
    
    /**
     * Initializes Hibernate with the config file found at
     * <code>configFilePath</code>.
     */
    private void initHibernate() throws ServletException {
        Configuration configuration = null;
        URL configFileURL = null;
        ServletContext context = null;
        
        try {
            configFileURL = HibernatePlugIn.class.getResource(_configFilePath);

            context = _servlet.getServletContext();

            if (_log.isDebugEnabled()) {
                _log.debug("Initializing Hibernate from "
                        + _configFilePath + "...");
            }
            
            configuration = (new Configuration()).configure(configFileURL);
            _factory = configuration.buildSessionFactory();
            
            if (_storedInServletContext) {
                _log.debug("Storing SessionFactory in ServletContext...");
                
                context.setAttribute(SESSION_FACTORY_KEY, _factory);
            }
           
        } catch (Throwable t) {
            _log.error("Exception while initializing Hibernate.");
            _log.error("Rethrowing exception...", t);
            
            throw (new ServletException(t));
        }
    }
    
    /**
     * Setter for property configFilePath.
     * @param configFilePath New value of property configFilePath.
     */
    public void setConfigFilePath(String configFilePath) {
        if ((configFilePath == null) || (configFilePath.trim().length() == 0)) {
            throw new IllegalArgumentException(
                    "configFilePath cannot be blank or null.");
        }
        
        if (_log.isDebugEnabled()) {
            _log.debug("Setting 'configFilePath' to '"
                    + configFilePath + "'...");
        }
        
        _configFilePath = configFilePath;
    }
    
    /**
     * Setter for property storedInServletContext.
     * @param storedInServletContext New value of property storedInServletContext.
     */
    public void setStoredInServletContext(String storedInServletContext) {
        if ((storedInServletContext == null) 
                || (storedInServletContext.trim().length() == 0)) {
            storedInServletContext = "false";
        }
        
        if (_log.isDebugEnabled()) {
            _log.debug("Setting 'storedInServletContext' to '"
                    + storedInServletContext + "'...");
        }
        
        _storedInServletContext 
                = new Boolean(storedInServletContext).booleanValue();
    }
    
}

