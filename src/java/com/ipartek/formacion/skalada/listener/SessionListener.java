package com.ipartek.formacion.skalada.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.controladores.LoginController;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	private int sessionCount = 0;
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override()
	public void sessionCreated(HttpSessionEvent event) {

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override()
	public void sessionDestroyed(HttpSessionEvent event) {

	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		LOG.trace("added");
		if (Constantes.KEY_SESSION_USER.equals(event.getName())) {
			synchronized (this) {
				this.sessionCount++;
			}
		}
		LOG.info("numero session added");

	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		LOG.trace("remove");
		if (Constantes.KEY_SESSION_USER.equals(event.getName())) {
			synchronized (this) {
				this.sessionCount--;
			}
		}
		LOG.info("numero session remove");
	}

	@Override()
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

}
