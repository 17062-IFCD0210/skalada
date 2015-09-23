package com.ipartek.formacion.utilidades;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

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

}
