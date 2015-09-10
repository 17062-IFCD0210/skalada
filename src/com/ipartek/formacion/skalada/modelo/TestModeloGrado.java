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

import com.ipartek.formacion.skalada.bean.Grado;



public class TestModeloGrado {
	
	String nombreGrado1 = "Mock1";
	String descGrado1 = "blablabla";
	int idGrado1 = -1;
	Grado grado1 = new Grado(nombreGrado1);
	Grado grado2 = new Grado("");
	ModeloGrado modeloGrado = new ModeloGrado();
	int total;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		total = modeloGrado.getAll().size();
		
		//Insertamos un grado
		grado1.setDescripcion(descGrado1);
		idGrado1 = modeloGrado.save(grado1);
		assertNotEquals("No insertado " + grado1.toString(), -1, idGrado1);
	}

	@After
	public void tearDown() throws Exception {
		//Eliminamos el grado
		assertTrue("No borrado " + grado1.toString(), modeloGrado.delete(grado1.getId()));
		
		//Comprobamos si lo ha borrado
		assertTrue("No ha sido borrado 1 mas al total", total  == modeloGrado.getAll().size());
	}

	@Test
	public void testCRUD() {
		
		//Comprobamos si lo ha metido
		assertTrue("No ha sido añadido 1 mas al total", total + 1 == modeloGrado.getAll().size());
		
		//Actualizamos el grado insertado
		grado1.setNombre("4h");
		grado1.setDescripcion("Actualizado");
		assertTrue("No ha sido actualizado " + grado1.toString(), modeloGrado.update(grado1));
		
		//Comprobamos si lee el registro buscado
		grado2 = (Grado)modeloGrado.getById(idGrado1);
		assertEquals("No es el nombre elegido", "4h", grado2.getNombre());
		assertEquals("No es la descripcion elegida", "Actualizado", grado2.getDescripcion());		
		
		
		//////////////////// COMPROBACIONES QUE NO EXISTAN //////////////////////////
		
		//Obtener grado con ID no existente
		assertTrue("Existe el registro", null == modeloGrado.getById(9999));
		
		//Actualizar grado con ID no existente
		grado2.setId(66);
		assertFalse("Existe el ID", modeloGrado.update(grado2));
		
		//Insertar un null
		grado2 = null;
		assertEquals("Insertado ", -1, modeloGrado.save(grado2));
		
		//Insertar grado con ID existente
		grado2 = new Grado("Nuevo");
		grado2.setId(1);
		assertEquals("No ha sido insertado", modeloGrado.getLastID() + 1  , modeloGrado.save(grado2));
		
		//Eliminamos el grado
		assertTrue("No borrado " + grado1.toString(), modeloGrado.delete(grado2.getId()));
	}

}
