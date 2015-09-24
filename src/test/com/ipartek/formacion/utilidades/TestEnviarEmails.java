package com.ipartek.formacion.utilidades;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.Assert;

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
		
		String email   = "laragonzalez.bm@gmail.com";
		String url     = Constantes.SERVER + Constantes.CONTROLLER_SIGNUP+"?accion="+Constantes.ACCION_VALIDAR+"&email="+email;
		String usuario = "Antton Gorriti";
		
		/*
		 * Variables a reemplazar en la plantilla:
		 * ${usuario}  =>  Usuario Refistrado
		 * ${url}      =>  Enlace para validar la cuenta del usuario  
		 * 
		 * */
		
		
		File file = new File (Constantes.EMAIL_TEMPLATE_REGISTRO);
		String cuerpo = file.toString();
		cuerpo.replace("{usuario}", usuario);
		cuerpo.replace("{url}", url);
		
		EnviarEmails correo = new EnviarEmails();
		
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