package com.ipartek.formacion.skalada.listener;

import java.io.IOException;
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
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Fichero configuracion de Log4j
		try {
			props = new Properties();
			props.load(this.getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(props);
			LOG.info("Log4j cargado con exito");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG.info("Destruyendo contexto aplicacion");
		props = null;
	}

}
