package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Token;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloToken;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.skalada.util.SendMail;

/**
 * Servlet implementation class ForgotPasswordController
 */
public class ForgotPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dispatcher = null;
	private ModeloUsuario modeloUsuario = null;
	private Usuario usuario = null;

	
	//parametros
	private int pID	= -1;		//ID no valido	
	private String pEmail;
	private String pPassword;
	private String pToken;
	
	private SendMail mail;

	private ModeloToken modeloToken = null;
	private Token token;
	
	private String cuerpo;
	private HashMap<String, String> hmParametros;

	private Mensaje msg;
       
	 /**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se usa para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloUsuario = new ModeloUsuario();   
    	mail = new SendMail(Constantes.MAIL_USER, Constantes.MAIL_PASS);
    	modeloToken = new ModeloToken();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		getParameters(request,response);
		
		//realizar accion solicitada
		recuperar(request,response);			
		
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
	}
	
	private void getParameters(HttpServletRequest request, HttpServletResponse response) {		
		try {
			request.setCharacterEncoding("UTF-8");
			if(request.getParameter("email") != null && !"".equalsIgnoreCase(request.getParameter("email"))){
				pEmail = request.getParameter("email");
			}
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	private void recuperar(HttpServletRequest request, HttpServletResponse response) {
		pID = modeloUsuario.getIdByEmail(pEmail);
		if(pID != 0){
			usuario = (Usuario)modeloUsuario.getById(pID);
			
			token = new Token(usuario.getEmail());
			
			if(!modeloToken.update(token)){
				msg = new Mensaje(Mensaje.MSG_DANGER, "Error al intentar recuperar la contraseña, por favor ponte en contacto con nosotros (admin@admin.com)");
			} else {			
				//enviar email	
				String url = Constantes.SERVER + Constantes.VIEW_BACK_RECUPERAR + "?email=" + usuario.getEmail() + "&token=" + token.getToken();
				hmParametros = new HashMap<String, String>();
				hmParametros.put("{usuario}", usuario.getNombre().toUpperCase());
				hmParametros.put("{url}", url);					
				cuerpo = mail.mailTemplateToString(Constantes.MAIL_TEMPLATE_RECUPERAR_PASS, hmParametros );
					
				if(mail.enviar(usuario.getEmail(), Constantes.MAIL_SUBJECT_RECUPERAR, cuerpo)){
					//Mensaje enviado correctamente
					msg = new Mensaje(Mensaje.MSG_INFO, "Solicitud de reestablecer contraseña aceptada, comprueba el email y sigue los pasos indicados.");
				} else {
					//Error en el envio del mensaje
					msg = new Mensaje(Mensaje.MSG_DANGER, "Error al intentar recuperar la contraseña, por favor ponte en contacto con nosotros (admin@admin.com)");
				}
			}
			
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			
		} else {
			msg = new Mensaje(Mensaje.MSG_DANGER, "Usuario no registrado");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_SIGNUP);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros del formulario
		getParametersForm(request);
		
		//Modificar contraseña
		pID = modeloUsuario.getIdByEmail(pEmail);
		usuario = (Usuario)modeloUsuario.getById(pID);
		
		token = modeloToken.getByEmail(pEmail);
		if("0".equals(token.getToken())){
			msg = new Mensaje(Mensaje.MSG_DANGER, "NO INTENTES CAMBIAR LA CONTRASEÑA DE OTRO!!!");
		}else if(pToken.equals(token.getToken())){		
			usuario.setPassword(pPassword);
			if(modeloUsuario.update(usuario)){
				msg = new Mensaje(Mensaje.MSG_SUCCESS, "Contraseña modificada correctamente. Prueba a iniciar sesion.");
				token.setToken("0");
				modeloToken.update(token);
			} else {
				msg = new Mensaje(Mensaje.MSG_DANGER, "Error al modificar la contraseña");
			}
		} else {
			msg = new Mensaje(Mensaje.MSG_DANGER, "Error al modificar la contraseña datos incorrectos");
		}
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
						
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
		
	}
			
	/**
	* Recoger los parametros enviados desde el formulario
	* @see backoffice\pages\\usuarios\form.jsp
	* @param request
	 * @throws UnsupportedEncodingException 
	*/
	private void getParametersForm(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		pEmail = request.getParameter("email");
		pPassword = request.getParameter("password");
		pToken = request.getParameter("token");

	}
}
