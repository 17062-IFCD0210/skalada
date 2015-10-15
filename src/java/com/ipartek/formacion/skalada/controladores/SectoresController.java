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
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class SectoresController
 * @author Curso
 */
public class SectoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher = null;
	private ModeloSector modeloSector = null;
	private Sector sector = null;
	private ModeloZona modeloZona = null;
	private Zona zona = null;
	
	private ModeloUsuario modeloUsuario = null;
	private Usuario uSector = null;
	private Usuario sessionUsuario = null;

	// parametros
	private int pAccion = Constantes.ACCION_LISTAR; // Accion por defecto
	private int pID = -1; // ID no valido
	private String pNombre;
	private int pIDZona;
	private int pIDUsuario;
	private boolean pValidado;
	
	private Mensaje msg = null;

	//Imagen File	
	private File file;

	/**
	 * Este metodo se ejecuta solo la primera vez que se llama al servlet Se usa
	 * para crear el modeloSector
	 */
	@Override()
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.modeloSector = new ModeloSector();
		this.modeloZona = new ModeloZona();
		this.modeloUsuario = new ModeloUsuario();
	}

	/**
	 * Este metodo se ejecuta cada vez que hay una peticion doGet o doPost. se usa para recoger el usuario
	 * de la sesion.
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.sessionUsuario = (Usuario)request.getSession().getAttribute(Constantes.KEY_SESSION_USER);
		super.service(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recoger parametros
		this.getParameters(request, response);

		// realizar accion solicitada
		switch (this.pAccion) {
		case Constantes.ACCION_NUEVO:
			this.nuevo(request, response);
			break;
		case Constantes.ACCION_DETALLE:
			this.detalle(request, response);
			break;
		case Constantes.ACCION_ELIMINAR:
			this.eliminar(request, response);
			break;
		default:
			this.listar(request, response);
			break;
		}

		this.dispatcher.forward(request, response);
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			this.pAccion = Integer.parseInt(request.getParameter("accion"));
			if (request.getParameter("id") != null
					&& !"".equalsIgnoreCase(request.getParameter("id"))) {
				this.pID = Integer.parseInt(request.getParameter("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene todas los sectores del modeloSector y carga dispatch con index.jsp
	 * 
	 * @see backoffice/pages/sectores/index.jsp
	 * @param request
	 * @param response
	 */
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sectores", this.modeloSector.getAll(sessionUsuario));
		request.setAttribute("msg", msg);
		this.dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_INDEX);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		this.sector = (Sector) this.modeloSector.getById(this.pID);
		//Check autorizacion
		if(sessionUsuario.isAdmin() || this.sector.getUsuario().getId() == sessionUsuario.getId()){
			if (this.modeloSector.delete(this.pID)) {
				msg = new Mensaje( Mensaje.MSG_DANGER , "Registro eliminado correctamente" );
			} else {
				msg = new Mensaje( Mensaje.MSG_WARNING , "Error al eliminar el registro [id(" + this.pID + ")]");
			}
		} else {
			msg = new Mensaje( Mensaje.MSG_DANGER , "No tienes permisos para eliminar el sector solicitado" );
		}
		this.listar(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		this.zona = new Zona("");
		this.sector = new Sector("", this.zona);
		this.sector.setUsuario( sessionUsuario );		
		
		request.setAttribute("sector", this.sector);
		request.setAttribute("titulo", "Crear nuevo Sector");
		request.setAttribute("zonas", this.modeloZona.getAll(null));
		request.setAttribute("usuarios", this.modeloUsuario.getAll(null));
		this.dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);
	}

	private void detalle(HttpServletRequest request,HttpServletResponse response) {
		this.sector = (Sector) this.modeloSector.getById(this.pID);
		//Check autorizacion
		if(sessionUsuario.isAdmin() || this.sector.getUsuario().getId() == sessionUsuario.getId()){
			request.setAttribute("sector", this.sector);
			request.setAttribute("titulo", this.sector.getNombre().toUpperCase());
			request.setAttribute("zonas", this.modeloZona.getAll(null));
			request.setAttribute("usuarios", this.modeloUsuario.getAll(null));
			this.dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SECTORES_FORM);
		} else {
			msg = new Mensaje( Mensaje.MSG_DANGER , "No tienes permisos para obtener el sector solicitado" );
			listar(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		msg = new Mensaje( Mensaje.MSG_DANGER , "Error sin identificar");		
		try{
			// recoger parametros del formulario
			this.getParametersForm(request);
	
			// Crear Objeto Sector
			this.crearObjeto();
	
			// Guardar/Modificar Objeto Via
			if (this.pID == -1) {
				if (this.modeloSector.save(this.sector) != -1) {
					msg = new Mensaje( Mensaje.MSG_SUCCESS , "Registro creado con exito");
					
				} else {
					msg = new Mensaje( Mensaje.MSG_DANGER , "Error al guardar el nuevo registro");														
				}
			} else {
				if (this.modeloSector.update(this.sector, sessionUsuario)) {
					msg = new Mensaje( Mensaje.MSG_SUCCESS , "Modificado correctamente el registro [id(" + this.pID + ")]");						
				} else {
					msg = new Mensaje( Mensaje.MSG_DANGER , "Error al modificar el registro [id(" + this.pID + ")]");			
				}
			}
		} catch( FileSizeLimitExceededException e){		
			e.printStackTrace();
			msg = new Mensaje( Mensaje.MSG_DANGER , "La imagen excede del tama�o maximo permitido " + Constantes.MAX_FILE_SIZE + " bytes" );
			request.setAttribute("msg", msg);	
		} catch(Exception e){
			e.printStackTrace();
			msg = new Mensaje( Mensaje.MSG_DANGER , e.getMessage() );
			request.setAttribute("msg", msg);
		}	
		
		this.listar(request, response);
		this.dispatcher.forward(request, response);

	}

	/**
	 * Crea un Objeto {@code Sector} Con los parametros recibidos
	 */
	private void crearObjeto() {
		this.zona = (Zona) this.modeloZona.getById(this.pIDZona);
		this.uSector = (Usuario) this.modeloUsuario.getById(this.pIDUsuario);

		//TODO controlar si cambiamos el sector pero no la imagen
		
		// existe sector
		if (this.pID != -1) {
			this.sector = (Sector) this.modeloSector.getById(this.pID);
			this.sector.setNombre(this.pNombre);
			this.sector.setZona(this.zona);			
		// nuevo sector
		} else {
			this.sector = new Sector(this.pNombre, this.zona);
			this.sector.setId(this.pID);
		}		
		//Siempre
		if ( this.file != null ){
			this.sector.setImagen(this.file.getName());
		}	
		this.sector.setValidado(this.pValidado);
		this.sector.setUsuario(this.uSector);			
	}

	/**
	 * Recoger los parametros enviados desde el formulario
	 * 
	 * @see backoffice\pages\sectores\form.jsp
	 * @param request
	 * @throws UnsupportedEncodingException
	 * @throws FileUploadException 
	 */
	private void getParametersForm(HttpServletRequest request) throws Exception {
	
			request.setCharacterEncoding("UTF-8");		
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			//TODO cambiar este valor para que falle
			factory.setSizeThreshold( Constantes.MAX_MEM_SIZE );
			// Location to save data that is larger than maxMemSize.
			//TODO comprobar si no existe carpeta
			factory.setRepository(new File(Constantes.IMG_UPLOAD_TEMP_FOLDER));
			
			// Create a new file upload handler
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    // maximum file size to be uploaded.
		    //TODO cambiar valor no dejar subir mas 1Mb
		    upload.setSizeMax( Constantes.MAX_FILE_SIZE );
		    
		    //Parametros de la request del formulario, NO la imagen
		    HashMap<String, String> dataParameters = new HashMap<String, String>();
			// Parse the request to get file items.		   
		    List<FileItem> items = upload.parseRequest(request);		   
		    for (FileItem item : items) {		    	
		    	//parametro formulario
		    	if ( item.isFormField() ){		    		
		    		dataParameters.put( item.getFieldName(), item.getString("UTF-8") );
		    	//Imagen	
		    	}else{		    		
		    		String fileName     = item.getName();		    		
		    		if ( !"".equals(fileName)){		    		
			            String fileContentType  = item.getContentType();
			            
			            if ( Constantes.CONTENT_TYPES.contains(fileContentType)){
			             		            
				            item.getSize();				            
				            
				            //TODO No repetir nombres imagenes
				            
				            this.file = new File( Constantes.IMG_UPLOAD_FOLDER + "\\" + fileName );
				            item.write( this.file ) ;
			            }else{
			            	throw new Exception( "[" + fileContentType + "] extensi�n de imagen no permitida");
			            }//end: content-type no permitido    
		    		}else{
		    			this.file = null;
		    		}   
		    	}	
		    }//End: for List<FileItem>
		    
		   	this.pID = Integer.parseInt( dataParameters.get("id"));
			this.pNombre = dataParameters.get("nombre");
			this.pIDZona = Integer.parseInt(dataParameters.get("zona"));
			
			//si es null, es un "usuario" (No es admin!)
			//puesto que el formulario no existe el parametro autor
			if (dataParameters.get("autor") != null) {
				this.pIDUsuario = Integer.parseInt(dataParameters.get("autor"));
			} else {
				this.pIDUsuario = this.sessionUsuario.getId();
			}			
			
			this.pValidado = (dataParameters.get("validado") != null) ? true : false ;	
		
	}

}