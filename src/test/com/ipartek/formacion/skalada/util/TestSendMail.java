package com.ipartek.formacion.skalada.util;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSendMail {
	
	private static final String DESTINO = "ieltxuorue@gmail.com";
	private static final String ASUNTO  = "prueba";
	private static final String MENSAJE = "<h1>hola</h1>"
										+ "<p>ñsdfghañusoihfgasodhf</p>";
	
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
		assertTrue("Email no enviado", mail.enviar(DESTINO, ASUNTO, MENSAJE));
	}

}
