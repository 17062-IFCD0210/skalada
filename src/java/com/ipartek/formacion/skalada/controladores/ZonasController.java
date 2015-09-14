package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class ZonasController
 */
public class ZonasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloZona modelo = null;
	private Zona zona = null;
	
	//Parametros Get
	private int pAccion = Constantes.ACCION_LISTAR; //Accion por defecto
	private int pID     = -1; //ID no valido
	
	//Parametros Post
	private String pNombre = "Nuevo"; // Nombre por defecto

    /**
     * Este metodo se ejecuta solo la primera vez que se llama al Servlet
     * Se suele usar para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {    	
    	super.init(config);
    	modelo = new ModeloZona();  	
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
		
		crearObjetoGrado();
		
		if(zona.getId() != -1) { //Zona modificable
			if(modelo.update(zona)) {
				request.setAttribute("msg_mod", "Registro Modificado");
			} else {
				request.setAttribute("msg_mod", "Registro no modificado");
			}	
		} else { //Via nueva
			modelo.save(zona);
			request.setAttribute("msg_new", "Registro Creado");
		}
		listar(request, response);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un objeto {@code Zona} con los parametros recibidos
	 */
	private void crearObjetoGrado() {
		zona = new Zona(pNombre, null);
		zona.setId(pID);
	}

	/**
	 * Recoger los parametros enviados desde el formulario:
	 * @see backoffice\pages\zonas\form.jsp
	 * @param request
	 * @param response
	 */
	private void getParametersPost(HttpServletRequest request, HttpServletResponse response) {		
		pID = Integer.parseInt(request.getParameter("id"));
		pNombre = request.getParameter("nombre");
		
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Recoger accion a realizar
			String sAccion = request.getParameter("accion");
			pAccion = Integer.parseInt(sAccion);
			
			//Recoger identificador de la zona
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
		request.setAttribute("zonas", modelo.getAll());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ZONAS_INDEX);
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		
		//Comprobamos si ha podido eliminar la zona, y le damos un mensaje de informacion al index.jsp
		if(modelo.delete(pID)) {
		 	request.setAttribute("msg_elim", "Zona eliminada.");
		} else {
			request.setAttribute("msg_elim", "Zona NO eliminada. " + pID);
		}
		
		//listamos las zonas actualizadas
		listar(request,response);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		zona = new Zona("Nuevo", null);
		request.setAttribute("zona", zona);
		request.setAttribute("titulo", "Crear Nueva Zona");
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ZONAS_FORM);
		
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		zona = (Zona) modelo.getById(pID);
		request.setAttribute("zona", zona);
		request.setAttribute("titulo", zona.getNombre());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ZONAS_FORM);
		
	}

}