package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class sectorsController
 */
public class SectoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloSector modeloSector = null;
	private ModeloZona modeloZona = null;
	private Sector sector = null;
	
	//Parametros Get
	private int pAccion = Constantes.ACCION_LISTAR; //Accion por defecto
	private int pID     = -1; //ID no valido
	
	//Parametros Post
	private String pNombre = "Nuevo"; // Nombre por defecto
	private String pZona = "";

    /**
     * Este metodo se ejecuta solo la primera vez que se llama al Servlet
     * Se suele usar para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {    	
    	super.init(config);
    	modeloSector = new ModeloSector();
    	modeloZona = new ModeloZona();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recoger parametros ACCION e ID
		getParameters(request,response);
		
		//Realizar Accion solicitada
		switch (pAccion) {
			case Constantes.ACCION_DETALLE:
				detalle(request, response);
				break;
			case Constantes.ACCION_NUEVO:
				nuevo(request, response);
				break;
			case Constantes.ACCION_ELIMINAR:
				eliminar(request, response);
			default:
				listar(request, response);
				break;
		}
		
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getParametersPost(request, response);
		
		crearObjetoSector();
		
		if(sector.getId() != -1) { //sector modificable
			if(modeloSector.update(sector)) {
				request.setAttribute("msg_mod", "Registro Modificado");
			} else {
				request.setAttribute("msg_mod", "Registro no modificado");
			}	
		} else { //Via nueva
			modeloSector.save(sector);
			request.setAttribute("msg_new", "Registro Creado");
		}
		listar(request, response);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un objeto {@code sector} con los parametros recibidos
	 */
	private void crearObjetoSector() {
		sector = new Sector(pNombre, null);
		sector.setId(pID);
	}

	/**
	 * Recoger los parametros enviados desde el formulario:
	 * @see backoffice\pages\sectores\form.jsp
	 * @param request
	 * @param response
	 */
	private void getParametersPost(HttpServletRequest request, HttpServletResponse response) {		
		pID = Integer.parseInt(request.getParameter("id"));
		pNombre = request.getParameter("nombre");
		pZona = request.getParameter("zona");
		
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Recoger accion a realizar
			String sAccion = request.getParameter("accion");
			pAccion = Integer.parseInt(sAccion);
			
			//Recoger identificador de la sector
			String sID = request.getParameter("id");
			if(sID != null && !"".equals(sID)) {
				pID = Integer.parseInt(sID);
			} else {
				pID = -1;
			}
		} catch(Exception e) {
			pAccion = Constantes.ACCION_LISTAR;
			pID = -1;
			e.printStackTrace();
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sectores", modeloSector.getAll());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_INDEX);
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		
		//Comprobamos si ha podido eliminar la sector, y le damos un mensaje de informacion al index.jsp
		if(modeloSector.delete(pID)) {
		 	request.setAttribute("msg_elim", "sector eliminada.");
		} else {
			request.setAttribute("msg_elim", "sector NO eliminada. " + pID);
		}
		
		//listamos las sectors actualizadas
		listar(request,response);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		Zona z = new Zona("",null);
		sector = new Sector("Nuevo", z);
		request.setAttribute("sector", sector);
		request.setAttribute("titulo", "Crear Nuevo Sector");
		listarZonas(request,response);
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);
		
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		sector = (Sector) modeloSector.getById(pID);
		request.setAttribute("sector", sector);
		request.setAttribute("titulo", sector.getNombre());
		listarZonas(request,response);
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);
		
	}
	
	private void listarZonas(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("lista_zonas", modeloZona.getAll());
	}

}