package com.ipartek.formacion.skalada;

import java.util.ArrayList;
import java.util.Arrays;

public class Constantes {
	/**
	 * 
	 */
	
	public static final String SERVER   = "http://localhost:8080";
	public static final String ROOT_APP = "/skalada/";
	public static final String APP_NAME = "skalada";
	
	//vistas publicas
	public static final String VIEW_PUBLIC_INDEX = "index.jsp";
	public static final String VIEW_PUBLIC_VIA = "/pages/via_detalle.jsp";
	
	//vistas backoffice
	public static final String ROOT_BACK = ROOT_APP + "backoffice/";
	
	public static final String VIEW_BACK_CONTENT_LOGS = "pages/log-content.jsp";
	public static final String VIEW_BACK_LOGS = "/logs/trazas.jsp";
	
	public static final String VIEW_BACK_LOGIN   = "backoffice/pages/login.jsp";
	public static final String VIEW_BACK_INDEX   = "backoffice/pages/index_back.jsp";
	public static final String VIEW_BACK_SIGNUP  = "pages/signup.jsp";
	public static final String VIEW_BACK_NEWPASS = "pages/newpass.jsp";
	
	
	public static final String VIEW_BACK_VIAS_INDEX = "backoffice/pages/vias/index.jsp";
	public static final String VIEW_BACK_VIAS_FORM = "backoffice/pages/vias/form.jsp";
	
	public static final String VIEW_BACK_GRADOS_INDEX = "backoffice/pages/grados/index.jsp";
	public static final String VIEW_BACK_GRADOS_FORM = "backoffice/pages/grados/form.jsp";
	
	public static final String VIEW_BACK_TIPO_ESCALADA_INDEX = "backoffice/pages/tipo-escalada/index.jsp";
	public static final String VIEW_BACK_TIPO_ESCALADA_FORM = "backoffice/pages/tipo-escalada/form.jsp";
	
	public static final String VIEW_BACK_ZONAS_INDEX = "backoffice/pages/zonas/index.jsp";
	public static final String VIEW_BACK_ZONAS_FORM = "backoffice/pages/zonas/form.jsp";
	
	public static final String VIEW_BACK_SECTORES_INDEX = "backoffice/pages/sectores/index.jsp";
	public static final String VIEW_BACK_SECTORES_FORM = "backoffice/pages/sectores/form.jsp";
	
	public static final String VIEW_BACK_ROLES_INDEX = "backoffice/pages/roles/index.jsp";
	public static final String VIEW_BACK_ROLES_FORM = "backoffice/pages/roles/form.jsp";
	
	public static final String VIEW_BACK_USUARIOS_INDEX = "backoffice/pages/usuarios/index.jsp";
	public static final String VIEW_BACK_USUARIOS_FORM = "backoffice/pages/usuarios/form.jsp";
	
	//controladores
	public static final String CONTROLLER_LOGIN           = ROOT_APP + "login";
	public static final String CONTROLLER_LOGOUT          = ROOT_APP + "logout";	
	public static final String CONTROLLER_VIAS            = ROOT_APP + "vias";
	public static final String CONTROLLER_HOME            = ROOT_APP + "home";
	public static final String CONTROLLER_GRADOS          = ROOT_APP + "grados";
	public static final String CONTROLLER_TIPO_ESCALADA   = ROOT_APP + "tipo-escalada";
	public static final String CONTROLLER_ZONAS           = ROOT_APP + "zonas";
	public static final String CONTROLLER_ZONAS_JSON      = ROOT_APP + "zonas-json";
	public static final String CONTROLLER_SECTORES        = ROOT_APP + "sectores";
	public static final String CONTROLLER_ROLES           = ROOT_APP + "roles";
	public static final String CONTROLLER_USUARIOS        = ROOT_APP + "usuarios";
	public static final String CONTROLLER_SIGNUP          = ROOT_APP + "signup";
	public static final String CONTROLLER_FORGOT_PASS     = ROOT_APP + "forgot-pass";
	
	//acciones
	public static final int ACCION_NUEVO	= 0;
	public static final int ACCION_DETALLE	= 1;
	public static final int ACCION_LISTAR	= 2;
	public static final int ACCION_ELIMINAR = 3;
	public static final int ACCION_VALIDAR  = 4;
	public static final int ACCION_RECUPERAR = 5;
	
	//Imagenes y Ficheros
	public static final String IMG_UPLOAD_FOLDER        = 
			"C:\\desarrollo\\apache-tomcat-6.0.44\\webapps\\uploads";
	public static final String IMG_UPLOAD_TEMP_FOLDER   = 
			"C:\\desarrollo\\apache-tomcat-6.0.44\\temp";
	public static final String IMG_WEP_PATH             = 
			"http://localhost:8080/uploads/"; 
	public static final int    MAX_FILE_SIZE            = 1000 * 1024;
	public static final int    MAX_MEM_SIZE             = 40 * 1024;
	public static final String IMG_DEFAULT_SECTOR       = "default_sector.jpg";
	public static final ArrayList<String> CONTENT_TYPES =  
			new ArrayList<String>(Arrays.asList("image/jpeg", "image/png"));

	//ROLES
	public static final String ROLE_USER = "usuario";
	public static final String ROLE_ADMIN = "administrador";
	
	//Usuarios
	public static final int USER_VALIDATE    = 1;
	public static final int USER_NO_VALIDATE = 0;
	
	//Plantillas emails
	public static final String EMAIL_TEMPLATE_REGISTRO = "emails/registro.html";
}