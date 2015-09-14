package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.TipoEscalada;
import com.ipartek.formacion.skalada.modelo.ModeloTipoEscalada;

/**
 * Servlet implementation class TipoEscaladaController
 */
public class TipoEscaladaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RequestDispatcher dispatcher = null;
	private ModeloTipoEscalada modelo = null;
	private TipoEscalada tipoEscalada = null;
	
	//parametros
	private int pAccion = Constantes.ACCION_LISTAR;		//Accion por defecto
	private int pID	= -1;		//ID no valido	
	private String pNombre;
	private String pDescripcion;
	
	private Mensaje msg;
	
    
    /**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se usa para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modelo = new ModeloTipoEscalada();   	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		getParameters(request,response);
		
		//realizar accion solicitada
		switch (pAccion) {
		case Constantes.ACCION_NUEVO:
			nuevo(request,response);
			break;
		case Constantes.ACCION_DETALLE:
			detalle(request,response);
			break;
		case Constantes.ACCION_ELIMINAR:
			eliminar(request,response);
			break;
		default:
			listar(request,response);
			break;
		}
			
		dispatcher.forward(request, response);
	}
				
	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			pAccion = Integer.parseInt(request.getParameter("accion"));		
			if(request.getParameter("id") != null && !"".equalsIgnoreCase(request.getParameter("id"))){
				pID = Integer.parseInt(request.getParameter("id"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Obtiene todas los tipo_escalada del modelo y carga dispatch con index.jsp
	 * @see backoffice/pages/grados/index.jsp
	 * @param request
	 * @param response
	 */
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("tipo_escalada", modelo.getAll());
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_TIPO_ESCALADA_INDEX);		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		if(modelo.delete(pID)){
			msg = new Mensaje(Mensaje.MSG_DANGER, "Registro eliminado correctamente");			
		} else {
			msg = new Mensaje(Mensaje.MSG_WARNING, "Error al eliminar el registro [id(" + pID + ")]");
		}
		request.setAttribute("msg", msg);
		listar(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		tipoEscalada = new TipoEscalada("");
		request.setAttribute("tipo_escalada", tipoEscalada);
		request.setAttribute("titulo", "Crear nuevo Grado");
		request.setAttribute("metodo", "Guardar");
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_TIPO_ESCALADA_FORM);
		
	}
	
	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		tipoEscalada = (TipoEscalada)modelo.getById(pID);
		request.setAttribute("tipo_escalada", tipoEscalada);
		request.setAttribute("titulo", tipoEscalada.getNombre().toUpperCase());
		request.setAttribute("metodo", "Modificar");
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_TIPO_ESCALADA_FORM);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros del formulario
		getParametersForm(request);
		
		//Crear Objeto Grado
		crearObjeto();
		
		//Guardar/Modificar Objeto TipoEscalada
		if (pID == -1){
			if( modelo.save(tipoEscalada) != -1){	
				msg = new Mensaje(Mensaje.MSG_SUCCESS, "Registro creado con exito");
			} else {
				msg = new Mensaje(Mensaje.MSG_DANGER, "Error al guardar el nuevo registro");
			}
		} else {
			if(modelo.update(tipoEscalada)){
				msg = new Mensaje(Mensaje.MSG_SUCCESS, "Modificado correctamente el registro [id(" + pID + ")]");
			} else {
				msg = new Mensaje(Mensaje.MSG_DANGER, "Error al modificar el registro [id(" + pID + ")]");
			}
		}
		
		listar(request,response);
		
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un Objeto {@code Grado} Con los parametros recibidos
	 */
	private void crearObjeto() {
		tipoEscalada = new TipoEscalada(pNombre);
		tipoEscalada.setId(pID);
		tipoEscalada.setDescripcion(pDescripcion);
	}


	/**
	* Recoger los parametros enviados desde el formulario
	* @see backoffice\pages\grados\form.jsp
	* @param request
	*/
	private void getParametersForm(HttpServletRequest request) {	
		pID = Integer.parseInt(request.getParameter("id"));
		pNombre = request.getParameter("nombre");	
		pDescripcion = request.getParameter("descripcion");
	}


}