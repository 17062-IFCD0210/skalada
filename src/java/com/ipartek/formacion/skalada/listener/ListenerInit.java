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

	//Se pone el nombre de la clase (LOG)
	private final static Logger LOG = Logger.getLogger(ListenerInit.class);

	Properties props = null;

	/**
	 * Default constructor.
	 */
	public ListenerInit() {
		// Cuando arranca la aplicaci�n
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Cuando se despliega la aplicaci�n
		// Inicializar log4j

		try {
			// Fichero configuracion de Log4j
			this.props = new Properties();
			this.props.load(this.getClass().getResourceAsStream(
					"/log4j.properties"));
			PropertyConfigurator.configure(this.props);

			LOG.info("Cargado con �xito");

		} catch (Exception e) {
			e.printStackTrace();
			// No se puede sobreescribir este m�todo
			// throw new Exception("No se puede cargar log4j");
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Cuando se elimina se destruye

		LOG.info("Destruyendo conexto aplicaci�n");
		this.props = null;
	}

}