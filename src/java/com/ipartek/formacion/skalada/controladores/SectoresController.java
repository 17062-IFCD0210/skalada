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
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class SectoresController
 */
public class SectoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	   
		private RequestDispatcher dispatcher = null;
		private ModeloSector modeloSector = null;
		private Sector sector = null;
		
		private ModeloZona modeloZona = null;
		private Zona zona = null;
		
		//parametros
		private int pAccion = Constantes.ACCION_LISTAR;		//Accion por defecto
		private int pID	= -1;		//ID no valido	
		private String pNombre;
		private int pIDZona;

		private Mensaje msg;
	    
	    /**
	     * Este metodo se ejecuta solo la primera vez que se llama al servlet
	     * Se usa para crear el modeloSector
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
		 * Obtiene todas los sectores del modeloSector y carga dispatch con index.jsp
		 * @see backoffice/pages/sectores/index.jsp
		 * @param request
		 * @param response
		 */
		private void listar(HttpServletRequest request, HttpServletResponse response) {
			request.setAttribute("sectores", modeloSector.getAll());
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_INDEX);		
		}

		private void eliminar(HttpServletRequest request, HttpServletResponse response) {
			if(modeloSector.delete(pID)){			
				msg = new Mensaje(Mensaje.MSG_DANGER, "Registro eliminado correctamente");			
			} else {
				msg = new Mensaje(Mensaje.MSG_WARNING, "Error al eliminar el registro [id(" + pID + ")]");
			}
			request.setAttribute("msg", msg);
			listar(request, response);
		}

		private void nuevo(HttpServletRequest request, HttpServletResponse response) {
			zona = new Zona("");
			sector = new Sector("", zona);
			request.setAttribute("sector", sector);
			request.setAttribute("titulo", "Crear nuevo Sector");
			request.setAttribute("metodo", "Guardar");
			request.setAttribute("zonas", modeloZona.getAll());
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);
			
		}
		
		private void detalle(HttpServletRequest request, HttpServletResponse response) {
			sector = (Sector)modeloSector.getById(pID);
			request.setAttribute("sector", sector);
			request.setAttribute("titulo", sector.getNombre().toUpperCase());
			request.setAttribute("metodo", "Modificar");
			request.setAttribute("zonas", modeloZona.getAll());
						
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);		
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//recoger parametros del formulario
			getParametersForm(request);
			
			//Crear Objeto Sector
			crearObjeto();
			
			//Guardar/Modificar Objeto Via
			if (pID == -1){
				if( modeloSector.save(sector) != -1){	
					msg = new Mensaje(Mensaje.MSG_SUCCESS, "Registro creado con exito");
				} else {
					msg = new Mensaje(Mensaje.MSG_DANGER, "Error al guardar el nuevo registro");
				}
			} else {
				if(modeloSector.update(sector)){
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
		 * Crea un Objeto {@code Sector} Con los parametros recibidos
		 */
		private void crearObjeto() {
			zona = (Zona)modeloZona.getById(pIDZona);
			if (pID != -1) {
				sector = (Sector)modeloSector.getById(pID);
				sector.setZona(zona);
			}else{			
				sector = new Sector(pNombre, zona);
				sector.setId(pID);
			}
		}


		/**
		* Recoger los parametros enviados desde el formulario
		* @see backoffice\pages\sectores\form.jsp
		* @param request
		*/
		private void getParametersForm(HttpServletRequest request) {	
			pID = Integer.parseInt(request.getParameter("id"));
			pNombre = request.getParameter("nombre");	
			pIDZona = Integer.parseInt(request.getParameter("zona"));
		}


	}