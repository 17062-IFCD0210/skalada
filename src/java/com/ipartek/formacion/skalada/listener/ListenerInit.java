package com.ipartek.formacion.skalada.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ListenerInit
 *
 */
public class ListenerInit implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ListenerInit.class);
	Properties props = null;
	
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		try {
			// Fichero de configuracion de Log4jv
			Properties props = new Properties();
			props.load(getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce)  { 
    	LOG.info("Destruyendo contexto aplicacion");
		this.props = null;
    }
}
