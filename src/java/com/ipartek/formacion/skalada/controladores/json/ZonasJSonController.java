package com.ipartek.formacion.skalada.controladores.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.modelo.ModeloSector;

/**
 * Servlet implementation class ZonasJSonController
 */
public class ZonasJSonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ModeloSector modeloSectores;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modeloSectores = new ModeloSector();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoger Parametros
		int id_zona = -1;
		ArrayList<Sector> sectores = null;
		
		//Llamar al modelo
		id_zona = Integer.parseInt(request.getParameter("id_zona"));
		sectores = modeloSectores.getByZona(id_zona);
		
		//Responder
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String jsonResponse = new Gson().toJson(sectores);
		out.print(jsonResponse);
		out.flush();
	}

}
