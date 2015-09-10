package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.TipoEscalada;
import com.ipartek.formacion.skalada.modelo.ModeloTipoEscalada;

/**
 * Servlet implementation class TipoEscaladaController
 */
public class TipoEscaladaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloTipoEscalada modelo = null;
	private TipoEscalada tipo = null;
	
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
    	modelo = new ModeloTipoEscalada();  	
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
		
		crearObjetoTipoEscalada();
		
		if(tipo.getId() != -1) { //Tipo de escalada modificable
			if(modelo.update(tipo)) {
				request.setAttribute("msg_mod", "Registro Modificado");
			} else {
				request.setAttribute("msg_mod", "Registro no modificado");
			}	
		} else { //Via nueva
			modelo.save(tipo);
			request.setAttribute("msg_new", "Registro Creado");
		}
		listar(request, response);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un objeto {@code TipoEscalada} con los parametros recibidos
	 */
	private void crearObjetoTipoEscalada() {
		tipo = new TipoEscalada(pNombre);
		tipo.setId(pID);
		tipo.setDescripcion(pDesc);
	}

	/**
	 * Recoger los parametros enviados desde el formulario:
	 * @see backoffice\pages\tipos_escalada\form.jsp
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
			
			//Recoger identificador del tipo de Escalada
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
		request.setAttribute("tipos", modelo.getAll());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_TIPOS_INDEX);
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		
		//Comprobamos si ha podido eliminar el tipo, y le damos un mensaje de informacion al index.jsp
		if(modelo.delete(pID)) {
		 	request.setAttribute("msg_elim", "Tipo Escalada eliminado.");
		} else {
			request.setAttribute("msg_elim", "Tipo Escalada NO eliminado. " + pID);
		}
		
		//listamos los tipos de escalada actualizados
		listar(request,response);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		tipo = new TipoEscalada("Nuevo");
		request.setAttribute("tipos", tipo);
		request.setAttribute("titulo", "Crear Nuevo Tipo de Escalada");
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_TIPOS_FORM);
		
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		tipo = (TipoEscalada) modelo.getById(pID);
		request.setAttribute("tipo", tipo);
		request.setAttribute("titulo", tipo.getNombre());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_TIPOS_FORM);
		
	}

}