package com.ipartek.formacion.utilidades;

import static org.junit.Assert.*;

import java.io.IOException;


import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.Constantes;

public class TestEnviarEmails {
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testEnviar() {
		
		EnviarEmails correo = new EnviarEmails();
		
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino("laragonzalez.bm@gmail.com");
		correo.setMessageSubject("Email de prueba enviado desde Java");
		correo.setMessageText("Cuerpo del mensaje de texto");
		
		assertTrue(
				 "Email no enviado " + correo.toString() ,
				  correo.enviar()
				);
		
		
	}
	
	@Test
	public void testEnviarRegistro() {
		
		String email   = "laragonzalez.bm@gmail.com";
		String url     = Constantes.SERVER + Constantes.CONTROLLER_SIGNUP+"?email=+email";
		String usuario = "Antton Gorriti";
		String contenido = "Gracias por registrarte. Para activar el usuario y verificar el email, clica en el enlace de debajo";
		String submit_button_text = "Activa tu cuenta";
		

		EnviarEmails correo = new EnviarEmails();
		String cuerpo = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		parametros.put("{usuario}", usuario);
		parametros.put("{url}", url);
		parametros.put("{contenido}", contenido);
		parametros.put("{btn_submit_text}", submit_button_text);

		try{
			 cuerpo = correo.generarPlantilla(
					 					Constantes.EMAIL_TEMPLATE_REGISTRO,
					 					parametros
					 					); 
		}catch(IOException e){
			e.printStackTrace();
			fail("No existe la plantilla: " + Constantes.EMAIL_TEMPLATE_REGISTRO);
		}
		
				
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino("laragonzalez.bm@gmail.com");
		correo.setMessageSubject("Confirmar registro usuario");
		correo.setMessageContent( cuerpo );
		
		assertTrue(
				 "Email no enviado " + correo.toString() ,
				  correo.enviar()
				);
		
		
	}

}