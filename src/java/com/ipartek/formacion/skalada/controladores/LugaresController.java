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
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.Via;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloVia;
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
	private Sector sector = null;
	private ModeloVia modeloVia = null;
	private Via via = null;
	
	
	public static final int MOSTRAR_ZONAS 		= 0;
	public static final int MOSTRAR_SECTORES 	= 1;
	public static final int MOSTRAR_VIAS		= 2;
	public static final int MOSTRAR_DETALLE 	= 3;
	
	
	//parametros
	private int pAccion = MOSTRAR_ZONAS;
	private int pId = 0;
       
    /**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se usa para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloZona = new ModeloZona();  
    	modeloSector = new ModeloSector();
    	modeloVia = new ModeloVia();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		getParameters(request,response);
		
		//realizar accion solicitada
		switch (pAccion) {
		case MOSTRAR_SECTORES:
			this.mostrarSectores(request, response);
			break;
		case MOSTRAR_VIAS:
			this.mostrarVias(request, response);
			break;
		case MOSTRAR_DETALLE:
			this.detalle(request, response);
			break;
		default:
			mostrarZonas(request,response);
			break;
		}
			
		dispatcher.forward(request, response);
	}
		
	private void getParameters(HttpServletRequest request, HttpServletResponse response) {		
		try {
			request.setCharacterEncoding("UTF-8");
			pAccion = Integer.parseInt(request.getParameter("accion"));	
			if(request.getParameter("id") != null){
				pId = Integer.parseInt(request.getParameter("id"));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	private void mostrarZonas(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Zona> zonas = modeloZona.getAll(); 	
		request.setAttribute("zonas", zonas);
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LUGARES_INDEX_ZONAS);		
	}
	
	private void mostrarSectores(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Sector> sectores = modeloSector.getAllByZona(pId); 	
		request.setAttribute("sectores", sectores);
		zona = modeloZona.getById(pId);
		request.setAttribute("zona", zona );
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LUGARES_INDEX_SECTORES);		
	}
	
	private void mostrarVias(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Via> vias = modeloVia.getAllBySector(pId); 	
		request.setAttribute("vias", vias);
		request.setAttribute("zona", zona );
		sector = modeloSector.getById(pId);
		request.setAttribute("sector", sector);
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LUGARES_INDEX_VIAS);
		
	}
	
	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
