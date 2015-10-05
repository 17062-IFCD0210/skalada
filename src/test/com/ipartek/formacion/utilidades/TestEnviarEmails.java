package com.ipartek.formacion.utilidades;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
		correo.setDireccionDestino("javi70@gmail.com");
		correo.setMessageSubject("Email de prueba enviado desde Java");
		correo.setMessageText("Cuerpo del mensaje de texto");
		
		assertTrue(
				 "Email no enviado " + correo.toString() ,
				  correo.enviar()
				);
		
		
	}
	
	@Test
	public void testEnviarRegistro() {
		
		
		String email   = "ander.ipartek@gmail.com";
		String url     = Constantes.SERVER + Constantes.CONTROLLER_REGISTRO+"?accion="+Constantes.ACCION_VALIDAR+"&email="+email;
		String usuario = "Antton Gorriti";
		
		/*
		 * Variables a reemplazar en la plantilla:
		 * {usuario}  =>  Usuario Refistrado
		 * {url}      =>  Enlace para validar la cuenta del usuario  
		 * 
		 * */
		
		//Constantes.EMAIL_TEMPLATE_REGISTRO);
		
		//@see: http://memorynotfound.com/load-file-resources-folder-java/
		
		EnviarEmails correo = new EnviarEmails();
		String cuerpo = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		parametros.put("{usuario}", usuario);
		parametros.put("{url}", url);
		try{
			 cuerpo = correo.generarPlantilla(Constantes.EMAIL_TEMPLATE_REGISTRO, parametros);
		}catch(IOException e){
			e.printStackTrace();
			fail("No existe la plantilla: " + Constantes.EMAIL_TEMPLATE_REGISTRO);
		}	
		
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino("javi70@gmail.com");
		correo.setMessageSubject("Confirmar registro usuario");
		correo.setMessageContent( cuerpo );
		
		assertTrue(
				 "Email no enviado " + correo.toString() ,
				  correo.enviar()
				);
		
		
	}
	

}