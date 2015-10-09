package com.ipartek.formacion.skalada.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Usuario;

/**
 * Servlet Filter implementation class FilterBackoffice
 */
public class FilterBackoffice implements Filter {

	private final static Logger LOG = Logger.getLogger(FilterBackoffice.class);
	private FilterConfig config;	
	
	private Mensaje msg = null;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (request instanceof HttpServletRequest ){
			
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			
			String url = httpRequest.getRequestURL().toString();
			url = (url.split(Constantes.ROOT_APP))[1];
			LOG.trace("Filtrando... " + url);
			
			//Comprobar si esta logueado
			HttpSession session = httpRequest.getSession(true);
			Usuario user = (Usuario) session.getAttribute(Constantes.KEY_SESSION_USER);
			if (user == null){
				//Usuario no logueado
				this.msg = new Mensaje( Mensaje.MSG_DANGER, "No estas logueado, por favor inicia sesion.");
				session.setAttribute("msg", msg);
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				httpResponse.sendRedirect(Constantes.SERVER + Constantes.ROOT_APP + Constantes.VIEW_LOGIN);
				
			} else {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}			
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}
