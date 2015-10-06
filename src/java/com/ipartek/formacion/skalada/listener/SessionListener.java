package com.ipartek.formacion.skalada.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.skalada.Constantes;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	public static int sessionCount = 0;
	private final static Logger LOG = Logger.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		LOG.trace("usuario conectado: "+ se.getSession().getAttribute(Constantes.KEY_SESSION_USER));
		if (Constantes.KEY_SESSION_USER.equals(se.getName())) {
			synchronized (this) {
				this.sessionCount++;
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		LOG.trace("usuario desconectado: "+ se.getSession().getAttribute(Constantes.KEY_SESSION_USER));
		if (Constantes.KEY_SESSION_USER.equals(se.getName())) {
			synchronized (this) {
				this.sessionCount--;
			}
		}

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

}
