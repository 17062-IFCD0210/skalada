package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.Grado;
import com.ipartek.formacion.skalada.bean.Via;
import com.ipartek.formacion.skalada.modelo.ModeloVia;

/**
 * Servlet implementation class ViasController
 */
public class ViasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloVia modelo = null;
	private Via via = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViasController() {
        super();       
    }

    /**
     * Este metodo se ejecuta solo la primera vez que se llama al Servlet
     * Se suele usar para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {    	
    	super.init(config);
    	modelo = new ModeloVia();
    	
    	//TODO quitar estos datos de prueba
    	generateViaMocks(modelo);
    	
    	
    }
    
    /**
     * Crea un juego de datos ficticio para las Vias
     * @param modelo2
     */
	private void generateViaMocks(ModeloVia modelo2) {

		Via v = new Via("No se");
		v.setDescripcion("Tampoco lo se");
		v.setLongitud(12);
		v.setGrado(Grado.FACIL);
		modelo.save(v);
		
		v = new Via("Arrebalde");
		v.setDescripcion("Arrabalde es un pueblo situado al noreste de la provincia de Zamora a 25 kil�metros de Benavente. Es conocido culturalmente por su riqueza  arqueol�gica, ya que en lo alto de su sierra se encuentran los restos del antiguo castro celta de �Las Labradas� El castro de Las Labradas fue emplazado en la Sierra de Carpurias a 1.000 metros de altitud. Por su extensi�n, esta considerado como el m�s grande de todo el noroeste peninsular, con asentamientos en la Edad del Bronce (1400 a 900 a.C.) y finales de la Edad del Hierro (siglos I a.C. al Id.C.). Destaca la aparici�n de dos ocultaciones de joyas prerromanas conocidas gen�ricamente como �El Tesoro de Arrabalde�. En las antiguas escuelas del pueblo de Arrabalde encontramos el Aula Arqueol�gica, donde tendremos una amable visita guiada por la reproducci�n de una calle del Castro. As� mismo dispondremos de informaci�n sobre toda la Ruta Arqueol�gica de los Valles.En las inmediaciones del Castro existen unas franjas rocosas de cuarcitas conocidas como Pe�a la Pipa y Pe�a Sorda . En estas paredes el Club Monta�ero Benaventano ha equipado 40 v�as de Escalada Deportiva que van desde el tercero hasta el s�ptimo grado. Seis de estas rutas componen un sector de iniciaci�n llamado J�venes Guerreros.Para alojarnos, Benavente dispone de gran oferta, tambi�n hay un camping en M�zar de Valverde. Adem�s el pueblo de Arrabalde, dispone de dos panader�as con productos tradicionales, dos bares, una tienda y una farmacia.");
		v.setLongitud(32);
		v.setGrado(Grado.DIFICIL);
		modelo.save(v);
		
		v = new Via("La Cabeza");
		v.setDescripcion("No hay descripci�n disponible para esta zona");
		v.setLongitud(142);
		v.setGrado(Grado.EXTREMO);
		modelo.save(v);
		
		v = new Via("Uxola (Alcoy)");
		v.setDescripcion("Peque�a escuela situada a escasos cinco minutos del casco urbano de Alcoy. Cuenta con v�as dif�ciles y explosivas y en ella se han forjado los potentes escaladores locales.");
		v.setLongitud(142);
		v.setGrado(Grado.NORMAL);
		modelo.save(v);
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		//recoger parametro id de la Via
		String pID = request.getParameter("id");
		
		//detalle o nueva
		if ( pID != null && !"".equals(pID)){
			int id = Integer.parseInt(pID);
			// nueva via
			if ( id == -1 ){ 
				via = new Via("Nueva");
			//detalle via	
			}else{
				//TODO llamar al modelo para recuperarla por ID
				via = new Via("Almendralejo");
				via.setId(5);
				via.setGrado(Grado.FACIL);
				via.setLongitud(34);
				via.setDescripcion("Lorem ipsum bla bla bla");
			}
			//enviar atributo con la via
			request.setAttribute("via", via);
			//cargarmos el dispatcher
			dispatcher = request.getRequestDispatcher( Constantes.VIEW_BACK_VIAS_FORM);
		
		//Listar todas las vias	
		}else{
			request.setAttribute("vias", modelo.getAll() );
			dispatcher = request.getRequestDispatcher( Constantes.VIEW_BACK_VIAS_INDEX);	
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
