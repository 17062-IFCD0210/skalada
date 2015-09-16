package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.bean.Via;
import com.ipartek.formacion.skalada.modelo.ModeloVia;

/**
 * Servlet implementation class ViasPublicController
 */
public class ViasPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloVia modelo = null;
	private Via v = null;
	
	private int pID = 0;


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
		String sId = request.getParameter("id");
		pID = Integer.parseInt(sId);
		
		v = (Via)modelo.getById(pID);
		
		request.setAttribute("via", v);
		request.setAttribute("titulo", v.getNombre());
		request.setAttribute("nivel", v.getGrado().getNombre());
		request.setAttribute("long", v.getLongitud());
		request.setAttribute("desc", v.getDescripcion());
		//request.setAttribute("url", v.getUrl());
		request.setAttribute("id", v.getId());
		
		
		ArrayList<Object> vias = modelo.getAll();
		
		//Recuperar las ultimas 6 vias del modelo
		if(vias.size() > 4) {
			vias = new ArrayList<Object>(vias.subList(0, 4));
		}
		
		//Enviarlas como atributo en la request
		request.setAttribute("cuatro_vias", vias);
		
		request.getRequestDispatcher("infoVia.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
