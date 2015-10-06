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
 * Servlet implementation class HomeController.
 */
public class HomeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ModeloSector modeloSector = null;
	
       
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.modeloSector = new ModeloSector();
	}

	/**
	 * Se puentea al doPost().
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		//recuperar las ultimas 6 sectores del modelo
		//TODO usar LIMIT en la select y order bu id desc
		ArrayList<Sector> sectores = this.modeloSector.getAll();
		if (sectores.size() > 6) {
			sectores = new ArrayList<Sector>(sectores.subList(0, 6));
		}
		
		//enviarlas como atributo en la request
		request.setAttribute("ultimos_sectores", sectores);
		
		//ir a index
		request.getRequestDispatcher(
				Constantes.VIEW_PUBLIC_INDEX).forward(request, response);
		
		
		
		
		

		
		
		
		
	}

}