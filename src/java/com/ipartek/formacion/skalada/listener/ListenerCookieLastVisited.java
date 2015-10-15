package com.ipartek.formacion.skalada.listener;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Guarda en una coockie la utlima visita del usuario
 * 
 * Application Lifecycle Listener implementation class ListenerCookieLastVisited
 *
 */
public class ListenerCookieLastVisited implements HttpSessionListener {

	private Cookie cookieLastVisit = null;
	private final static String COOKIE_NAME = "last_visit";

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @Override
	public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    @Override
	public void sessionDestroyed(HttpSessionEvent se)  { 
    	
    	long milisegundos = System.currentTimeMillis();
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		Date resulDate = new Date(milisegundos);
		cookieLastVisit = new Cookie("last_visit", sdf.format(resulDate));
    }
	
}
