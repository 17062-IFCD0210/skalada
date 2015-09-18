package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.modelo.ModeloSector;

/**
 * Servlet implementation class ViasPublicController
 */
public class SectoresPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloSector modelo = null;
	private Sector s = null;
	
	private int pID = 0;


	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modelo = new ModeloSector();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("id");
		pID = Integer.parseInt(sId);
		
		s = (Sector)modelo.getById(pID);
		
		request.setAttribute("sector", s);
		request.setAttribute("titulo", s.getNombre());
		request.setAttribute("imagen", s.getImagen());
		request.setAttribute("id", s.getId());
		
		
		ArrayList<Object> sectores = modelo.getAll();
		
		//Recuperar las ultimas 6 vias del modelo
		if(sectores.size() > 4) {
			sectores = new ArrayList<Object>(sectores.subList(0, 4));
		}
		
		//Enviarlas como atributo en la request
		request.setAttribute("cuatro_vias", sectores);
		
		request.getRequestDispatcher("infoSector.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
