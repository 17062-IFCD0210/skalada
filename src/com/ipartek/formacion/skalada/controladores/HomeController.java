package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.modelo.ModeloVia;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	
	private ModeloVia modelo = null;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modelo = new ModeloVia();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Object> vias = modelo.getAll();
		
		//Recuperar las ultimas 6 vias del modelo
		if(vias.size() > 6) {
			vias = (ArrayList<Object>) vias.subList(0, 6);
		}
		
		//Enviarlas como atributo en la request
		request.setAttribute("ultimas_vias", vias);
		
		//Ir a index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
//		dispatcher = request.getRequestDispatcher(Constantes.VIEW_PUBLIC_INDEX);
//		dispatcher.forward(request, response);
	}

}
