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
import com.ipartek.formacion.skalada.bean.Sector;

/**
 * Servlet implementation class GradosController
 */
public class ZonasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloZona modelo = null;
	private Zona zona = null;
	
	//parametros
	private int pAccion = Constantes.ACCION_LISTAR; //accion por defecto
	private int pID = -1; //ID no valido.
	private String pNombre;
	
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		//recoger parametro ACCION y ID
		getParameters(request, response);
		
		//realizar Accion solicitada
		switch (pAccion) {
			case Constantes.ACCION_DETALLE:
				detalle(request, response);
				break;
			case Constantes.ACCION_NUEVO:
				nuevo(request, response);
				break;
			case Constantes.ACCION_ELIMINAR:
				eliminar(request, response);
				break;		
			default:
				listar(request, response);
				break;
		}
		
		dispatcher.forward(request, response); // dispatcher ya viene dado según donde haya entrado en el switch
	}
	

	/**
	 * Obtiene todas los grados del modelo y carga con dispatch con backoffice/pages/grados/form.jsp
	 * @param request
	 * @param response
	 */
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("zona", modelo.getAll() );
		
		dispatcher = request.getRequestDispatcher( Constantes.VIEW_BACK_ZONAS_INDEX );
		
	}



	private void eliminar(HttpServletRequest request,
			HttpServletResponse response) {

		
		if ( modelo.delete(pID)){
			request.setAttribute("msg", "Registro Eliminado");
		}else{
			request.setAttribute("msg", "ERROR: Registro NO Eliminado " + pID );
		}
		
		listar(request, response);
		
	}



	private void nuevo(HttpServletRequest request, HttpServletResponse response) {

		zona = new Zona(""); 
		request.setAttribute("zona", zona );
		request.setAttribute("titulo", "Crear nuevo registro" );
		request.setAttribute("metodo", "Guardar");
		
		dispatcher = request.getRequestDispatcher( Constantes.VIEW_BACK_ZONAS_FORM);
	}



	private void detalle(HttpServletRequest request,
			HttpServletResponse response) {
		
		zona = (Zona)modelo.getById(pID);
		request.setAttribute("zona", zona );
		request.setAttribute("titulo", zona.getNombre() );
		request.setAttribute("metodo", "Modificar");
		dispatcher = request.getRequestDispatcher( Constantes.VIEW_BACK_ZONAS_FORM);
		
	}



	private void getParameters(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
			//recoger accion a realizar
			String sAccion = request.getParameter("accion");
			pAccion = Integer.parseInt(sAccion);			
		
			//recoger Identificador del Grado	
			String sID = request.getParameter("id");
			if( sID != null && !"".equalsIgnoreCase(sID)){
				pID = Integer.parseInt(sID);
			}else{
				pID = 1;
			}
			
		}catch(Exception e){
			pAccion = Constantes.ACCION_LISTAR;
			pID = -1;
			e.printStackTrace();
		}	
		
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			getParametersFormulario(request, response); //Recogemos los parámetros que nos han enviado desde el formulario
			
			crearObjeto(); //Le metemos los parámetros al nuevo objeto
			
			if (pID == -1){
				if( modelo.save(zona) != -1){	
					request.setAttribute("msg", "Registro creado con exito");
				} else {
					request.setAttribute("msg", "Error al guardar el nuevo registro");
				}
			} else {
				if(modelo.update(zona)){
					request.setAttribute("msg", "Modificado correctamente el registro [id(" + pID + ")]");
				} else {
					request.setAttribute("msg", "Error al modificar el registro [id(" + pID + ")]");
				}
			}
			
			listar(request, response);
			
			dispatcher.forward(request, response);
	}

	
	/**
	 * Crea un objeto {@code Object} con los parámetros recibidos
	 */
	private void crearObjeto() {
		zona = new Zona(pNombre);
		zona.setId(pID);
	}
	
	/**
	 * Recoger los parámetros enviados desde el formulario:
	 * @see backoffice\pages\vias\form.jsp
	 * @param request
	 * @param response
	 */
	private void getParametersFormulario(HttpServletRequest request,
			HttpServletResponse response) {
		
		pID = Integer.parseInt(request.getParameter("id"));
		pNombre = request.getParameter("nombre"); //Según el atributo name="nombre" del input
		
	}

}