package com.ipartek.formacion.skalada;

public class Constantes {

	public static final String ROOT_APP = "/skalada/";

	// vistas publicas
	public static final String VIEW_PUBLIC_INDEX = ROOT_APP + "index.jsp";
	public static final String VIEW_PUBLIC_INFOVIA = ROOT_APP + "infoVia.jsp";

	// vistas backoffice
	public static final String ROOT_BACK       = ROOT_APP + "backoffice/";
	public static final String VIEW_BACK_LOGIN = "backoffice/pages/login.jsp";
	public static final String VIEW_BACK_INDEX = "backoffice/pages/index_back.jsp";

	// vistas vias
	public static final String VIEW_BACK_VIAS_INDEX = "backoffice/pages/vias/index.jsp";
	public static final String VIEW_BACK_VIAS_FORM  = "backoffice/pages/vias/form.jsp";
	
	// vistas grados
	public static final String VIEW_BACK_GRADOS_INDEX = "backoffice/pages/grados/index.jsp";
	public static final String VIEW_BACK_GRADOS_FORM  = "backoffice/pages/grados/form.jsp";
	
	// vistas tipos de escalada
	public static final String VIEW_BACK_TIPOS_INDEX = "backoffice/pages/tipos_escalada/index.jsp";
	public static final String VIEW_BACK_TIPOS_FORM  = "backoffice/pages/tipos_escalada/form.jsp";
	
	// vistas tipos de escalada
	public static final String VIEW_BACK_ZONAS_INDEX = "backoffice/pages/zonas/index.jsp";
	public static final String VIEW_BACK_ZONAS_FORM  = "backoffice/pages/zonas/form.jsp";
	

	// controladores
	public static final String CONTROLLER_LOGIN  = ROOT_APP + "login";
	public static final String CONTROLLER_LOGOUT = ROOT_APP + "logout";
	public static final String CONTROLLER_VIAS   = ROOT_APP + "vias";
	public static final String CONTROLLER_HOME   = ROOT_APP + "home";
	public static final String CONTROLLER_INFO_VIA   = "infoVia";
	public static final String CONTROLLER_GRADOS   = ROOT_APP + "grados";
	public static final String CONTROLLER_TIPOS   = ROOT_APP + "tipo-escalada";
	public static final String CONTROLLER_ZONAS   = ROOT_APP + "zona";
	
	//Acciones
	public static final int ACCION_NUEVO    = 1;
	public static final int ACCION_DETALLE  = 2;
	public static final int ACCION_LISTAR   = 3;
	public static final int ACCION_ELIMINAR = 4;

}
