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

import com.ipartek.formacion.skalada.bean.TipoEscalada;



public class TestModeloTipoEscalada {
	
	String nombreTipo1 = "Mock1";
	String descTipo1 = "blablabla";
	int idTipo1 = -1;
	TipoEscalada tipo1 = new TipoEscalada(nombreTipo1);
	TipoEscalada tipo2 = new TipoEscalada("");
	ModeloTipoEscalada modeloTipo = new ModeloTipoEscalada();
	int total;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		total = modeloTipo.getAll().size();
		
		//Insertamos un tipo
		tipo1.setDescripcion(descTipo1);
		idTipo1 = modeloTipo.save(tipo1);
		assertNotEquals("No insertado " + tipo1.toString(), -1, idTipo1);
	}

	@After
	public void tearDown() throws Exception {
		//Eliminamos el tipo
		assertTrue("No borrado " + tipo1.toString(), modeloTipo.delete(tipo1.getId()));
		
		//Comprobamos si lo ha borrado
		assertTrue("No ha sido borrado 1 mas al total", total  == modeloTipo.getAll().size());
	}

	@Test
	public void testCRUD() {
		
		//Comprobamos si lo ha metido
		assertTrue("No ha sido añadido 1 mas al total", total + 1 == modeloTipo.getAll().size());
		
		//Actualizamos el tipo insertado
		tipo1.setNombre("4h");
		tipo1.setDescripcion("Actualizado");
		assertTrue("No ha sido actualizado " + tipo1.toString(), modeloTipo.update(tipo1));
		
		//Comprobamos si lee el registro buscado
		tipo2 = (TipoEscalada)modeloTipo.getById(idTipo1);
		assertEquals("No es el nombre elegido", "4h", tipo2.getNombre());
		assertEquals("No es la descripcion elegida", "Actualizado", tipo2.getDescripcion());		
		
		
		//////////////////// COMPROBACIONES QUE NO EXISTAN //////////////////////////
		
		//Obtener tipo con ID no existente
		assertTrue("Existe el registro", null == modeloTipo.getById(9999));
		
		//Actualizar tipo con ID no existente
		tipo2.setId(66);
		assertFalse("Existe el ID", modeloTipo.update(tipo2));
		
		//Insertar un null
		tipo2 = null;
		assertEquals("Insertado ", -1, modeloTipo.save(tipo2));
		
		//Insertar tipo con ID existente
		tipo2 = new TipoEscalada("Nuevo");
		tipo2.setId(1);
		assertEquals("No ha sido insertado", modeloTipo.getLastID() + 1  , modeloTipo.save(tipo2));
		
		//Eliminamos el tipo
		assertTrue("No borrado " + tipo2.toString(), modeloTipo.delete(tipo2.getId()));
		
	}

}
