package com.ipartek.formacion.skalada.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ipartek.formacion.skalada.Constantes;

public class TestSendMail {
	
	SendMail mail;
	
	@Before
	public void setUp() throws Exception {
		mail = new SendMail(Constantes.MAIL_USER, Constantes.MAIL_PASS);
	}

	@After
	public void tearDown() throws Exception {
		mail = null;
	}

	
	@Test
	public void testSendMail() throws IOException {
		
		
		String destino = "ieltxuorue@gmail.com";
		String asunto  = "prueba";
		
		String usuario = "Antton Gorriti";
//		String email   = "ander.ipartek@gmail.com";
//		String url     = Constantes.SERVER + Constantes.CONTROLLER_REGISTRO+"?accion="+Constantes.ACCION_VALIDAR+"&email="+email;
		
		String cuerpo;
		
		HashMap<String, String> hmParametros = new HashMap<String, String>();
		hmParametros.put("{usuario}", usuario);
		hmParametros.put("{pass}", "NEW PASS");		
		
		ClassLoader classLoader = getClass().getClassLoader();
		cuerpo = IOUtils.toString(classLoader.getResourceAsStream(Constantes.MAIL_TEMPLATE_RECUPERAR_PASS), "UTF-8");
		
		
		@SuppressWarnings("rawtypes")
		Iterator it = hmParametros.entrySet().iterator();
	    while (it.hasNext()) {
	    	@SuppressWarnings("rawtypes")
			Map.Entry e = (Map.Entry)it.next();
	    	cuerpo = cuerpo.replace(e.getKey().toString(), e.getValue().toString());
	    }
	         
	    assertTrue("Email no enviado", mail.enviar(destino, asunto, cuerpo));

	}

}
