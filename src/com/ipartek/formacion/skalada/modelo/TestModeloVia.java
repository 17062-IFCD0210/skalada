package com.ipartek.formacion.skalada.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.bean.Grado;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.TipoEscalada;
import com.ipartek.formacion.skalada.bean.Via;
import com.ipartek.formacion.skalada.bean.Zona;

public class TestModeloVia {
	private static final String LOREM_IPSUM_1 = "Bacon ipsum dolor amet short loin pork belly bresaola filet mignon t-bone ham beef spare ribs pork chop biltong. Pork chop capicola prosciutto landjaeger rump tenderloin. Tri-tip tail turkey, leberkas frankfurter pastrami shankle doner sausage sirloin boudin. Brisket ham hock strip steak, ribeye pork belly meatball turkey. Strip steak short loin landjaeger hamburger kevin swine pork belly jerky t-bone brisket chicken leberkas short ribs.";
	static ModeloVia modelo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		modelo = new ModeloVia();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		modelo = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ArrayList<Object> v = modelo.getAll();
		
		String nombreVia = "agarra lo que puedas";
		Grado grado = new Grado("6a");
		int longitud = 10;
		//Zona
		String nombreZona = "Ogoño";
		Zona zona = new Zona(nombreZona, null);
		//Sectores
		String nombreSector = "Cara Sur";
		Sector sector = new Sector(nombreSector, zona);
		
		String nombreTipoEscalada ="Deportiva";
		TipoEscalada tipoEscalada = new TipoEscalada(nombreTipoEscalada);

		
		Via v1 = new Via(nombreVia, grado, longitud, tipoEscalada, sector);
		v1.setDescripcion(LOREM_IPSUM_1);

		//Guardar objeto
		int id = modelo.save(v1);
		assertTrue("No se ha podido guardar", -1 < id);

		Via v2 = (Via) modelo.getById(id);
		assertEquals(nombreVia, v2.getNombre());
		assertEquals(longitud, v2.getLongitud());
		assertEquals(grado.getNombre(), v2.getGrado().getNombre());
		assertEquals(LOREM_IPSUM_1, v2.getDescripcion());

		// Eliminar
		assertTrue("No se ha podido eliminar", modelo.delete(id));

	}
}
