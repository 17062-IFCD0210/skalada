package com.ipartek.formacion.utilidades;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class TestEnviarEmails {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testEnviarEmails() {
		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("Skalada_App");
		correo.setDireccionDestino("javi70@gmail.com");
		correo.setMessageSubject("Email enviado desde Java");
		correo.setMessageText("Estamos todos aprobados");
		assertTrue("Email no enviado "+correo.toString(),correo.enviar());
	}

	@Test
	public void testEnviarEmailHTML() {
		String plantillaHTML="";
		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("Skalada_App");
		correo.setDireccionDestino("javi70@gmail.com");
		correo.setMessageSubject("Email enviado desde Java");
		correo.setMessageText(plantillaHTML);
		assertTrue("Email no enviado "+correo.toString(),correo.enviar());
	}

}
