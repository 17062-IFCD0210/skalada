package com.ipartek.formacion.skalada.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ListenerInit.
 *
 */
public class ListenerInit implements ServletContextListener {

	private Properties props;
	private static final  Logger LOG = Logger.getLogger(ListenerInit.class);
	
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
	public void contextInitialized(ServletContextEvent arg0)  { 
    	// Inicializar Log4j

    	try {
	    	this.props = new Properties();
	    	this.props.load(this.getClass().getResourceAsStream(
	    			"/log4j.properties"));
	    	PropertyConfigurator.configure(this.props);
	    	
	    	LOG.info("Log4j cargado con exito");
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
	public void contextDestroyed(ServletContextEvent arg0)  { 

    	LOG.info("Destruyendo contexto Aplicacion");

    }
	
}
