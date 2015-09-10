package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Grado;
import com.ipartek.formacion.skalada.modelo.ModeloGrado;

/**
 * Servlet implementation class GradosController
 */
public class GradosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloGrado modelo = null;
	private Grado grado = null;
	
	//Parametros Get
	private int pAccion = Constantes.ACCION_LISTAR; //Accion por defecto
	private int pID     = -1; //ID no valido
	
	//Parametros Post
	private String pNombre = "Nuevo"; // Nombre por defecto
	private String pDesc = ""; //Descripcion por defecto

    /**
     * Este metodo se ejecuta solo la primera vez que se llama al Servlet
     * Se suele usar para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {    	
    	super.init(config);
    	modelo = new ModeloGrado();  	
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
		
		if(grado.getId() != -1) { //Grado modificable
			if(modelo.update(grado)) {
				request.setAttribute("msg_mod", "Registro Modificado");
			} else {
				request.setAttribute("msg_mod", "Registro no modificado");
			}	
		} else { //Via nueva
			modelo.save(grado);
			request.setAttribute("msg_new", "Registro Creado");
		}
		listar(request, response);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un objeto {@code Grado} con los parametros recibidos
	 */
	private void crearObjetoGrado() {
		grado = new Grado(pNombre);
		grado.setId(pID);
		grado.setDescripcion(pDesc);
	}

	/**
	 * Recoger los parametros enviados desde el formulario:
	 * @see backoffice\pages\grados\form.jsp
	 * @param request
	 * @param response
	 */
	private void getParametersPost(HttpServletRequest request, HttpServletResponse response) {		
		pID = Integer.parseInt(request.getParameter("id"));
		pNombre = request.getParameter("nombre");
		pDesc = request.getParameter("desc");
		
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Recoger accion a realizar
			String sAccion = request.getParameter("accion");
			pAccion = Integer.parseInt(sAccion);
			
			//Recoger identificador del grado
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
		request.setAttribute("grados", modelo.getAll());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_GRADOS_INDEX);
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		
		//Comprobamos si ha podido eliminar el grado, y le damos un mensaje de informacion al index.jsp
		if(modelo.delete(pID)) {
		 	request.setAttribute("msg_elim", "Grado eliminado.");
		} else {
			request.setAttribute("msg_elim", "Grado NO eliminado. " + pID);
		}
		
		//listamos los grados actualizados
		listar(request,response);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		grado = new Grado("Nuevo");
		request.setAttribute("grado", grado);
		request.setAttribute("titulo", "Crear Nuevo Grado");
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_GRADOS_FORM);
		
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		grado = (Grado) modelo.getById(pID);
		request.setAttribute("grado", grado);
		request.setAttribute("titulo", grado.getNombre());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_GRADOS_FORM);
		
	}

}