package com.ipartek.formacion.skalada.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class FilterBackoffice
 */
public class FilterBackoffice implements Filter {

	private static final Logger LOG = Logger.getLogger(FilterBackoffice.class);
	private FilterConfig config;

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
		// TODO Auto-generated method stub
		// place your code here
		LOG.trace("Filtrando...");
		// pass the request along the filter chain
		chain.doFilter(request, response);
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
