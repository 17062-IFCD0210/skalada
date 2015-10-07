package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class LugaresController
 * @author Curso
 */
public class LugaresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher = null;
	private ModeloZona modeloZona = null;
	private Zona zona = null;
	private ModeloSector modeloSector = null;
	
	//parametros
	private int pAccion = Constantes.ACCION_LISTAR;	
       
    /**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se usa para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloZona = new ModeloZona();  
    	modeloSector = new ModeloSector();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		getParameters(request,response);
		
		//realizar accion solicitada
		switch (pAccion) {
		case Constantes.ACCION_DETALLE:
			this.detalle(request, response);
			break;
		default:
			listar(request,response);
			break;
		}
			
		dispatcher.forward(request, response);
	}
		
	private void getParameters(HttpServletRequest request, HttpServletResponse response) {		
		try {
			request.setCharacterEncoding("UTF-8");
			pAccion = Integer.parseInt(request.getParameter("accion"));		
			
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Zona> zonas = modeloZona.getAll(); 
		ArrayList<Integer> cant_sector = new ArrayList<Integer>();
		for(int i = 0 ; i < cant_sector.size() ; i++ ){
			zona = zonas.get(i);
			modeloSector.getAllByZona(zona.getId());
		}		
		request.setAttribute("zonas", zonas);
		request.setAttribute("cant_sector", cant_sector);
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LUGARES_INDEX);		
	}
	
	private void detalle(HttpServletRequest request, HttpServletResponse response) {
//		zona = (Zona)modeloZona.getById(pID);
//		request.setAttribute("zona", zona);
//		request.setAttribute("titulo", zona.getNombre().toUpperCase());
//		request.setAttribute("metodo", "Modificar");
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LUGARES_DETALLE);		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
