package com.ipartek.formacion.utilidades;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.Constantes;

public class TestEnviarEmails {
	
	private static final String PATH_TEMPLATE_REGISTRO = "C:\\desarrollo\\java\\workspace\\skalada\\WebContent\\emails\\registro.html";

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testEnviar() {
		
		EnviarEmails correo = new EnviarEmails();
		
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino("ander.ipartek@gmail.com");
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
		String url     = Constantes.SERVER + Constantes.CONTROLLER_SIGNUP+"?accion="+Constantes.ACCION_VALIDAR+"&email="+email;
		String usuario = "Antton Gorriti";
		
		/*
		 * Variables a reemplazar en la plantilla:
		 * {usuario}  =>  Usuario Refistrado
		 * {url}      =>  Enlace para validar la cuenta del usuario  
		 * 
		 * */
		
		//Constantes.EMAIL_TEMPLATE_REGISTRO);
		
		//@see: http://memorynotfound.com/load-file-resources-folder-java/
		
		File file = new File ( PATH_TEMPLATE_REGISTRO );  		                     
		String cuerpo = "";
		try{
			 cuerpo = FileUtils.readFileToString(file, "UTF-8"); 
		}catch(IOException e){
			e.printStackTrace();
			fail("No existe la plantilla: " + Constantes.EMAIL_TEMPLATE_REGISTRO);
		}	
		cuerpo = cuerpo.replace("{usuario}", usuario);
		cuerpo = cuerpo.replace("{url}", url);
		
		EnviarEmails correo = new EnviarEmails();
		
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino("ander.ipartek@gmail.com");
		correo.setMessageSubject("Confirmar registro usuario");
		correo.setMessageContent( cuerpo );
		
		assertTrue(
				 "Email no enviado " + correo.toString() ,
				  correo.enviar()
				);
		
		
	}

}
