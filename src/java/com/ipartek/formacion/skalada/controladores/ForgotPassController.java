package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.utilidades.EnviarEmails;


/**
 * Servlet implementation class PassOlvidadoController
 */
public class ForgotPassController extends HttpServlet {
	private String pEmail;
	private String pPass;
	private int pAccion;
	
	private RequestDispatcher dispatcher = null;
	
	private Usuario usuario = null;
	private ModeloUsuario modeloUsuario = null;
	private Mensaje msg = null;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
  	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloUsuario = new ModeloUsuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pEmail = request.getParameter("email");
		request.setAttribute("email", pEmail);
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_NEW_PASS);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pPass = request.getParameter("password");
		pAccion = Integer.parseInt(request.getParameter("accion"));
		pEmail = request.getParameter("email");
		
		usuario = (Usuario)modeloUsuario.getByEmail(pEmail);
		switch(pAccion) {
		case 4: //ACCION_FORGOT_PASS
			forgotPass(request,response);
			break;
		case 5: //ACCION_NEW_PASS
			newPass(request,response);
			break;
		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
	}

	private boolean enviarEmail() {
		boolean resul = false;
		try {
			EnviarEmails correo = new EnviarEmails();
			correo.setDireccionFrom("skalada.ipartek@gmail.com");
			correo.setDireccionDestino(usuario.getEmail());
			correo.setMessageSubject("Cambio de contraseña");
			HashMap<String,String> parametros = new HashMap<String,String>();
			parametros.put("{usuario}", usuario.getNombre());
			parametros.put("{url}", Constantes.SERVER + Constantes.CONTROLLER_FORGOT_PASS+"?email=" + usuario.getEmail());
			parametros.put("{contenido}", "Acabas de pedir una nueva contraseña para tu usuario. Por favor, clica en el enlace de debajo para cambiar la contraseña");
			parametros.put("{texto_boton}", "Recupera tu contraseña");
			correo.setMessageContent(correo.generarPlantilla(Constantes.EMAIL_TEMPLATE_REGISTRO, parametros));
			resul = correo.enviar();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resul;
	}
	
	/**
	 * Metodo que se encarga de actualizar la contraseña por una nueva
	 * @param request
	 * @param response
	 */
	private void newPass(HttpServletRequest request, HttpServletResponse response) {
		usuario.setPassword(pPass);
		modeloUsuario.update(usuario);
		msg = new Mensaje(Mensaje.MSG_SUCCESS, "Contrase�a cambiada");
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
	}
	
	/**
	 * Metodo que se encarga de enviar un mail al usuario para poder cambiar la contraseña
	 * @param request
	 * @param response
	 */
	private void forgotPass(HttpServletRequest request, HttpServletResponse response) {
		if(!modeloUsuario.checkEmail(pEmail)){
			//NO existe el email
			msg = new Mensaje(Mensaje.MSG_DANGER, "No existe el email");
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
		}else { //SI existe el email
			//ver si esta validado
			if(!modeloUsuario.isValidado(pEmail)){	//NO esta validado
				msg = new Mensaje(Mensaje.MSG_DANGER, "El usuario no est� validado");
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}else{
				if ( enviarEmail() ){
					msg = new Mensaje( Mensaje.MSG_SUCCESS , "Por favor revisa tu Email para cambiar tu contrase�a");
					dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
				}else{
					msg = new Mensaje( Mensaje.MSG_DANGER , "Error al enviar email, por favor ponte en contacto con nosotros " + EnviarEmails.direccionOrigen);						
				}	
			}
		}
	}

}