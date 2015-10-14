package com.ipartek.formacion.skalada.filter;

import java.io.IOException;

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

	private static final Logger LOG = Logger.getLogger(FilterBackoffice.class);
	private FilterConfig config;
	/*
	 * private ArrayList<String> excludePages = new ArrayList<String>( Arrays.asList(Constantes.VIEW_BACK_LOGIN, Constantes.VIEW_BACK_SIGNUP,
	 * Constantes.VIEW_BACK_RECUPERAR_PASS) );
	 */
	
	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		config = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// Casteamos la request de tipo ServletRequest a tipo HttpRequest como antes para tratar la request como tal
		if (request instanceof HttpServletRequest) {

			HttpServletRequest httpRequest = (HttpServletRequest) request;

			String url = httpRequest.getRequestURL().toString();
			LOG.trace("Filtrando " + url);

			// Comprobar si hay que exluir páginas
			// if (!excludePages.contains(url)) {

			// Comprobar si está logeado
			HttpSession session = httpRequest.getSession(true); // Crea una sesión si es nula y puede cascar con el getAttribute
			Usuario user = (Usuario) session.getAttribute(Constantes.KEY_SESSION_USER);
			if (user == null) { // si es diferente de null el usuario está logeado

				Mensaje msg = new Mensaje(Mensaje.MSG_WARNING, "No estás logeado, por favor inicia sesion");
				session.setAttribute("msg", msg);
				// Redireccionamos
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				((HttpServletResponse) response).sendRedirect(Constantes.SERVER + Constantes.ROOT_APP + Constantes.VIEW_LOGIN); // URL absoluta
				// No hacer doChain si direccionamos
			} else {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Inicializamos con lo que nos pasan (fConfig)
		config = fConfig;
	}

}
