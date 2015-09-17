package com.ipartek.formacion.skalada.controladores;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class SectoresController
 */
public class SectoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Variables subida imagenes
	private File file;
	
	private RequestDispatcher dispatcher = null;
	private ModeloSector modeloSector = null;
	private ModeloZona modeloZona = null;
	private Sector sector = null;
	private Zona zona = null;
	
	//Parametros Get
	private int pAccion = Constantes.ACCION_LISTAR; //Accion por defecto
	private int pID     = -1; //ID no valido
	
	//Parametros Post
	private String pNombre = "Nuevo"; // Nombre por defecto
	private int pZona;

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
		response.setContentType("text/html");
		getParametersPost(request, response);
		
		//Subida Imagen
		uploadFile(request);
		
		crearObjetoSector();
		
		if(sector.getId() != -1) { //sector modificable
			if(modeloSector.update(sector)) {
				request.setAttribute("msg_mod", "Registro Modificado");
			} else {
				request.setAttribute("msg_mod", "Registro no modificado");
			}	
		} else { //sector nuevo
			if(modeloSector.save(sector) != -1) {
				request.setAttribute("msg_new", "Registro Creado");
			} else {
				request.setAttribute("msg_new", "Registro NO Creado");
			}			
		}
		
		listar(request, response);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Se encarga de guardar la imagen del formulario en la carpeta de uploads
	 * @param request
	 */
	private void uploadFile(HttpServletRequest request) {
		//TODO realizar comprobaciones y guardar imagen en PC
	}

	/**
	 * Crea un objeto {@code sector} con los parametros recibidos
	 */
	private void crearObjetoSector() {
		zona = (Zona)modeloZona.getById(pZona);
		
		//existe sector
		if ( pID != -1 ){		
			sector = (Sector)modeloSector.getById(pID);
			sector.setNombre(pNombre);
			sector.setZona(zona);
			sector.setImagen(file.getName());
			
		//nuevo sector	
		}else{
			sector = new Sector(pNombre, zona);
			sector.setId(pID);
			sector.setImagen(file.getName());
		}
	}

	/**
	 * Recoger los parametros enviados desde el formulario:
	 * @see backoffice\pages\sectores\form.jsp
	 * @param request
	 * @param response
	 */
	private void getParametersPost(HttpServletRequest request, HttpServletResponse response) {
		String fileName = "";
		try {
			  DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
			  //TODO cambiar este valor para que falle
		      factory.setSizeThreshold(Constantes.MAX_MEM_SIZE);
		      // Location to save data that is larger than maxMemSize.
		      //TODO comprobar si no existe carpeta
		      factory.setRepository(new File(Constantes.IMG_UPLOAD_TEMP_FOLDER));
	
		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      //TODO cambiar valor no dejar subir mas de 1MB
		      upload.setSizeMax(Constantes.MAX_FILE_SIZE);
		      
		      //Parametros de la request del parametro, NO la imagen
		      HashMap<String, String> dataParameters = new HashMap<String, String>();
		      
		      // Parse the request to get file items.
		      List<FileItem> items = upload.parseRequest(request);
		      for(FileItem item : items) { 
		    	  if(item.isFormField()) {
		    		  dataParameters.put(item.getFieldName(), item.getString());
		    	  } else { //Imagen
		    		  fileName = item.getName();
			          String contentType = item.getContentType();
			          boolean isInMemory = item.isInMemory();
			          long sizeInBytes = item.getSize();
			          
			          //TODO comprobar size y contenttype
			          //TODO no repetir nombres de imagenes
			          //TODO comprobar subir mas de una imagen
			          file = new File(Constantes.IMG_UPLOAD_FOLDER + fileName);
			          item.write(file);
		    	  }
		      } //End for
		      pID = Integer.parseInt(dataParameters.get("id"));
		      pNombre = dataParameters.get("nombre");
		      pZona = Integer.parseInt(dataParameters.get("zona"));    
		} catch(Exception e) {
			e.printStackTrace();
		}	
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
		 	request.setAttribute("msg_elim", "sector eliminado.");
		} else {
			request.setAttribute("msg_elim", "sector NO eliminado. " + pID);
		}
		
		//listamos las sectors actualizadas
		listar(request,response);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		zona = new Zona("",null);
		sector = new Sector("Nuevo", zona);
		request.setAttribute("sector", sector);
		request.setAttribute("titulo", "Crear Nuevo Sector");
		request.setAttribute("lista_zonas", modeloZona.getAll());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);
		
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		sector = (Sector) modeloSector.getById(pID);
		request.setAttribute("sector", sector);
		request.setAttribute("titulo", sector.getNombre());
		request.setAttribute("lista_zonas", modeloZona.getAll());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);
		
	}

}