package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.modelo.ModeloSector;

/**
 * Servlet implementation class HomeController
 * @author Curso
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloSector modeloSector = null;
	
	private static final int SECTORES_CANTIDAD = 6;
	
       
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modeloSector = new ModeloSector();
	}

	/**
	 * Se puentea al doPost()
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recuperar los ultimos 6 sectores del modelo
		ArrayList<Sector> sectores = modeloSector.getAll();
		
		//TODO usar LIMIT en la select y ORDER BY id desc
		if ( sectores.size() > SECTORES_CANTIDAD ){
			sectores = new ArrayList<Sector>(sectores.subList(0, SECTORES_CANTIDAD));
		}
		
		//enviarlas como atributo en la request
		request.setAttribute("ultimos_sectores", sectores);
		
		//ir a index
		request.getRequestDispatcher(Constantes.VIEW_PUBLIC_INDEX).forward(request, response);
		
		
		
		
		

		
		
		
		
	}

}
