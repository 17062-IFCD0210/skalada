package com.ipartek.formacion.skalada.modelo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.bean.Zona;



public class TestModeloZona {
	
	String nombreZona1 = "Mock1";
	int idZona1 = -1;
	Zona zona1 = new Zona(nombreZona1, null);
	Zona zona2 = new Zona("",null);
	ModeloZona modeloZona = new ModeloZona();
	int total;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		total = modeloZona.getAll().size();
		
		//Insertamos una zona
		idZona1 = modeloZona.save(zona1);
		assertNotEquals("No insertado " + zona1.toString(), -1, idZona1);
	}

	@After
	public void tearDown() throws Exception {
		//Eliminamos la zona
		assertTrue("No borrado " + zona1.toString(), modeloZona.delete(zona1.getId()));
		
		//Comprobamos si lo ha borrado
		assertTrue("No ha sido borrado 1 mas al total", total  == modeloZona.getAll().size());
	}

	@Test
	public void testCRUD() {
		
		//Comprobamos si lo ha metido
		assertTrue("No ha sido añadido 1 mas al total", total + 1 == modeloZona.getAll().size());
		
		//Actualizamos la zona insertada
		zona1.setNombre("MockAct");
		assertTrue("No ha sido actualizado " + zona1.toString(), modeloZona.update(zona1));
		
		//Comprobamos si lee el registro buscado
		zona2 = (Zona)modeloZona.getById(idZona1);
		assertEquals("No es el nombre elegido", "MockAct", zona2.getNombre());		
		
		
		//////////////////// COMPROBACIONES QUE NO EXISTAN //////////////////////////
		
		//Obtener zona con ID no existente
		assertTrue("Existe el registro", null == modeloZona.getById(9999));
		
		//Actualizar zona con ID no existente
		zona2.setId(66);
		assertFalse("Existe el ID", modeloZona.update(zona2));
		
		//Insertar un null
		zona2 = null;
		assertEquals("Insertado ", -1, modeloZona.save(zona2));
		
		//Insertar zona con ID existente
		zona2 = new Zona("Nuevo", null);
		zona2.setId(1);
		assertEquals("No ha sido insertado", modeloZona.getLastID() + 1  , modeloZona.save(zona2));
		
		//Eliminamos la zona
		assertTrue("No borrado " + zona1.toString(), modeloZona.delete(zona2.getId()));
	}

}
