package com.ipartek.formacion.skalada.controladores;

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
 * Servlet implementation class ZonasJsonController
 * @author Curso
 */
public class ZonasJsonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloSector modeloSector = null;
	
	private int pIdZona;
	private ArrayList<Sector> sectores = null;
       
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloSector = new ModeloSector();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recoger parametros
		getParameters(request,response);
		
		//llamar al modelo
		sectores = modeloSector.getAllByZona(pIdZona);
		
		//responder
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		//variable tipo PrintWriter para escribir response
		PrintWriter out = response.getWriter();
		
		//formatear response a json (Objeto serializable)
		String jsonResponse = new Gson().toJson(sectores);
		
		out.print(jsonResponse);
		
		//libera el buffer del PrintWriter
		out.flush();
		
		
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("UTF-8");
			if(request.getParameter("id_zona") != null){
				pIdZona = Integer.parseInt(request.getParameter("id_zona"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
