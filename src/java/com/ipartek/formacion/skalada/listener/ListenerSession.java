package com.ipartek.formacion.skalada.listener;

import java.util.ArrayList;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Usuario;

/**
 * Application Lifecycle Listener implementation class ListenerSession
 *
 */
public class ListenerSession implements HttpSessionAttributeListener {

	public static ArrayList<Usuario> session_users = new ArrayList<Usuario>();
	   
    /**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	if(Constantes.KEY_SESSION_USER.equals(se.getName())){
			if (!ListenerSession.session_users.contains(se.getValue())) {
				synchronized (this) {
					ListenerSession.session_users.add((Usuario) se.getValue());
				}
			}
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	if(Constantes.KEY_SESSION_USER.equals(se.getName())){
       	 	if (ListenerSession.session_users.contains(se.getValue())) {
				synchronized (this) {
					ListenerSession.session_users.remove((Usuario) se.getValue());
				}
			}
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
