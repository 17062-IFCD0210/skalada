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
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.TipoEscalada;
import com.ipartek.formacion.skalada.bean.Via;
import com.ipartek.formacion.skalada.bean.Zona;
import com.ipartek.formacion.skalada.modelo.ModeloGrado;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloTipoEscalada;
import com.ipartek.formacion.skalada.modelo.ModeloVia;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class ViasController
 */
public class ViasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloVia mv = null;
	private ModeloGrado mg = null;
	private ModeloTipoEscalada mte = null;
	private ModeloSector ms = null;
	private ModeloZona mz = null;
	private Via via = null;
	private Grado grado = null;
	private TipoEscalada tipoEsc = null;
	private Sector sector = null;
	private Zona zona = null;
	
	//Parametros Get
	private int pAccion = Constantes.ACCION_LISTAR; //Accion por defecto
	private int pID     = -1; //ID no valido
	
	//Parametros Post
	private String pNombre = "Nueva"; // Nombre por defecto
	private int pIDGrado;
	private int pLong = 0; // Longitud por defecto
	private String pDesc = ""; //Descripcion por defecto
	private int pIDTipoEsc;
	private int pIDSector;
	private int pIDZona;

    /**
     * Este metodo se ejecuta solo la primera vez que se llama al Servlet
     * Se suele usar para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {    	
    	super.init(config);
    	mv = new ModeloVia();
    	mg = new ModeloGrado();
    	mte = new ModeloTipoEscalada();
    	ms = new ModeloSector();
    	mz = new ModeloZona();
    }
    
    /**
     * Crea un juego de datos ficticio para las Vias
     * @param modelo2
     */

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
		
		crearObjetoVia();
		
		if(via.getId() != -1) { //Via modificable
			if(mv.update(via)) {
				request.setAttribute("msg_mod", "Registro Modificado");
			} else {
				request.setAttribute("msg_mod", "Registro no modificado");
			}	
		} else { //Via nueva
			mv.save(via);
			request.setAttribute("msg_new", "Registro Creado");
		}
		listar(request, response);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un objeto {@code Via} con los parametros recibidos
	 */
	private void crearObjetoVia() {
		grado = (Grado)mg.getById(pIDGrado);
		tipoEsc = (TipoEscalada)mte.getById(pIDTipoEsc);
		sector = (Sector)ms.getById(pIDSector);
		zona = (Zona)mz.getById(pIDZona);
		
		//Existe via
		if(pID != -1) {
			sector.setZona(zona);
			via = (Via)mv.getById(pID);
			via.setGrado(grado);
			via.setTipoEscalada(tipoEsc);
			via.setSector(sector);
			via.setId(pID);
			via.setDescripcion(pDesc);
		} else { //Nueva via
			sector.setZona(zona);
			via = new Via(pNombre,grado,pLong,tipoEsc,sector);
			via.setId(pID);
			via.setDescripcion(pDesc);
		}
	}

	/**
	 * Recoger los parametros enviados desde el formulario:
	 * @see backoffice\pages\vias\form.jsp
	 * @param request
	 * @param response
	 */
	private void getParametersPost(HttpServletRequest request, HttpServletResponse response) {
		pID = Integer.parseInt(request.getParameter("id"));
		pNombre = request.getParameter("nombre");
		pIDGrado = Integer.parseInt(request.getParameter("grado"));
		pLong = Integer.parseInt(request.getParameter("long"));
		pDesc = request.getParameter("desc");
		pIDTipoEsc = Integer.parseInt(request.getParameter("tipo_esc"));
		pIDSector = Integer.parseInt(request.getParameter("sector"));
		pIDZona = Integer.parseInt(request.getParameter("zona"));
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Recoger accion a realizar
			String sAccion = request.getParameter("accion");
			pAccion = Integer.parseInt(sAccion);
			
			//Recoger identificador de la via
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
		request.setAttribute("vias", mv.getAll());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_VIAS_INDEX);
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		
		//Comprobamos si ha podido eliminar la via, y le damos un mensaje de informacion al index.jsp
		if(mv.delete(pID)) {
		 	request.setAttribute("msg_elim", "Registro Eliminado.");
		} else {
			request.setAttribute("msg_elim", "Registro NO Eliminado. " + pID);
		}
		
		//listamos las vias actualizadas
		listar(request,response);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		grado = new Grado("");
		tipoEsc = new TipoEscalada("");
		zona = new Zona("",null);
		sector = new Sector("",zona);
		via = new Via("Nueva",grado,0,tipoEsc,sector);
		request.setAttribute("via", via);
		request.setAttribute("titulo", "Crear Nueva Via");
		request.setAttribute("lista_grados", mg.getAll());
		request.setAttribute("lista_tipos", mte.getAll());
		request.setAttribute("lista_sectores", ms.getAll());
		request.setAttribute("lista_zonas", mz.getZonas());
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_VIAS_FORM);
		
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		via = (Via) mv.getById(pID);
		request.setAttribute("via", via);
		request.setAttribute("titulo", via.getNombre());
		request.setAttribute("lista_grados", mg.getAll());
		request.setAttribute("lista_tipos", mte.getAll());
		request.setAttribute("lista_sectores", ms.getByZona(via.getSector().getZona().getId()));
		request.setAttribute("lista_zonas", mz.getZonas());
		
		
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_VIAS_FORM);
		
	}

}