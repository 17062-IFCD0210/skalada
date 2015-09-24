package com.ipartek.formacion.skalada.util;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ipartek.formacion.skalada.Constantes;

public class TestSendMail {
	
	SendMail mail;
	
	@Before
	public void setUp() throws Exception {
		mail = new SendMail();
	}

	@After
	public void tearDown() throws Exception {
		mail = null;
	}

	@Test
	public void testSendMail() {
		
		
		String destino = "ieltxuorue@gmail.com";
		String asunto  = "prueba";
		
		String usuario = "Antton Gorriti";
		String email   = "ander.ipartek@gmail.com";
		String url     = Constantes.SERVER + Constantes.CONTROLLER_REGISTRO+"?accion="+Constantes.ACCION_VALIDAR+"&email="+email;
		
		String cuerpo;
		
		HashMap<String, String> hmParametros = new HashMap<String, String>();
		hmParametros.put("{usuario}", usuario);
		hmParametros.put("{url}", url);
		
		cuerpo = mail.mailTemplateToString(Constantes.MAIL_TEMPLATE_REGISTRO, hmParametros );
	         
	    assertTrue("Email no enviado", mail.enviar(destino, asunto, cuerpo));

	}

}
