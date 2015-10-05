package com.ipartek.formacion.skalada.controladores;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
	private String pImagen;

	private Mensaje msg = null;
	
	// Imagen File
	private File file = null;
    
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
			request.setCharacterEncoding("UTF-8");
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
		msg = null;
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
		request.setCharacterEncoding("UTF-8");
		//recoger parametros del formulario
		getParametersForm(request);
		
		//Subida imagen
		uploadFile(request);			
		
		if(msg == null){
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
		}
		listar(request,response);
		
		request.setAttribute("msg", msg);
		msg = null;
		dispatcher.forward(request, response);		
	}
	
	
	/**
	* Recoger los parametros enviados desde el formulario
	* @see backoffice\pages\sectores\form.jsp
	* @param request
	 * @throws UnsupportedEncodingException 
	 * @throws FileUploadException 
	*/
	private void getParametersForm(HttpServletRequest request) {	
		try{
			request.setCharacterEncoding("UTF-8");		
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(Constantes.IMG_MAX_MEM_SIZE);
			// Location to save data that is larger than maxMemSize.
			factory.setRepository(new File(Constantes.IMG_UPLOAD_TEMP_FOLDER));
			
			// Create a new file upload handler
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    // maximum file size to be uploaded.
		    upload.setSizeMax( Constantes.IMG_MAX_FILE_SIZE );
		    
		    //Parametros de la request del formulario, NO la imagen
		    HashMap<String, String> dataParameters = new HashMap<String, String>();
			// Parse the request to get file items.		   
		    List<FileItem> items = upload.parseRequest(request);
		    boolean new_img = false;
		    for (FileItem item : items) {
		    	
		    	//parametro formulario
		    	if ( item.isFormField() ){		    		
		    		dataParameters.put( item.getFieldName(), item.getString("UTF-8") );
		    	//Imagen	
		    	}else{
		    		String fileName     	= item.getName();
		    		if (!fileName.equals("")){
		    			new_img = true;
			            String fileContentType  = item.getContentType();
			            if(!Constantes.IMG_CONTENT_TYPES.contains(fileContentType)){
			            	msg = new Mensaje(Mensaje.MSG_DANGER, "Error al guardar el nuevo registro. Extension de imagen no valida (.jpg, .png)");
			            }
//			            boolean isInMemory  = item.isInMemory();
//			            long sizeInBytes    = item.getSize();
			            
			            
			            file = new File( Constantes.IMG_UPLOAD_FOLDER + "\\" + fileName );
			            if(file.exists()){
			            	msg = new Mensaje(Mensaje.MSG_DANGER, "Error al guardar el nuevo registro. Nombre de imagen ya empleada");
			            }			            
			            item.write( file ) ;
			            pImagen = file.getName();
		    		}
		    	}	            
		    	
		    }
		    
		   	pID = Integer.parseInt( dataParameters.get("id"));
			pNombre = dataParameters.get("nombre");
			pIDZona = Integer.parseInt(dataParameters.get("zona"));
			if(!new_img){
				pImagen = dataParameters.get("img");
			}
		}catch(SizeLimitExceededException e){
			msg = new Mensaje(Mensaje.MSG_DANGER, "Error al guardar el nuevo registro. Tamaño imagen excedido (MAX. 1Mb)");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Crea un Objeto {@code Sector} Con los parametros recibidos
	 */
	private void crearObjeto() {
		zona = (Zona)modeloZona.getById(pIDZona);
		
		if (pID != -1) {
			sector = (Sector)modeloSector.getById(pID);
			sector.setNombre(pNombre);
			sector.setZona(zona);			
		}else{			
			sector = new Sector(pNombre, zona);
			sector.setId(pID);			
		}
		
		if (file != null){ 
			sector.setImagen(pImagen);
		} 
	}
	

	/**
	 * Se encarga de guardar la imagen del formulario en la carpeta upload
	 * @param request
	 */
	private void uploadFile(HttpServletRequest request) {		
		//TODO realizar comprobaciones y guardar imagen en PC
	}

}