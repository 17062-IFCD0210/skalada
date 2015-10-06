package com.ipartek.formacion.skalada.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.bean.Zona;

/**
 * 
 * @author Curso
 *
 */

public class TestModeloZona {
	/**
	 * 
	 */
	private static ModeloZona modelo = null;
	/**
	 * 
	 */
	private static String nombreZona = "gradoMock";
	/**
	 * 
	 */
	private static String descripcionZona = "Lorem impsun";
	/**
	 * 
	 */
	private static String descripcionZona_updated = "Updated Lorem impsun";
	/**
	 * 
	 */
	private static String nombreZona_updated = "updated";
	/**
	 * 
	 */
	private static int id;	
/**
 * 
 * @throws Exception
 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		modelo = new ModeloZona();
	}
/**
 * 
 * @throws Exception
 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		modelo = null;
	}
/**
 * 
 * @throws Exception
 */
	@Before
	public void setUp() throws Exception {
	}
/**
 * 
 * @throws Exception
 */
	@After
	public void tearDown() throws Exception {
	}
/**
 * 
 */
	
	//@Ignore
	@Test
	public final void testCRUD() {
		int total;				//Cantidad de registros iniciales
								//en la tabla `test`
		int total_despues;		//Cantidad de registros en la 
								//tabla `test` tras insertar uno nuevo	
			
		
		//Test para comprobar que al insertar un nuevo registro aumenta el ID
		Zona z_insert = new Zona(nombreZona);
		
		total = modelo.getAll().size();
		id = modelo.save(z_insert);
		total_despues = modelo.getAll().size();	// total = total_despues + 1	
		
		assertTrue(total == total_despues - 1);
		assertTrue((id != -1) && (id > 0));
		
		
		//Test para comprobar que al obtener un registro
		//por su ID lo devuelve correctamente
		Zona z_get;
		z_get = (Zona) modelo.getById(id);	
		assertTrue(id == z_get.getId());
		assertEquals(nombreZona, z_get.getNombre());
		
		
		//Test para comprobar que actualiza el registro indentificado por su ID
		Zona z_update = new Zona(nombreZona_updated);
		z_update.setId(id);
		
		assertTrue(modelo.update(z_update));
		
		z_get = (Zona) modelo.getById(z_update.getId());	
		assertTrue(id == z_get.getId());
		assertEquals(nombreZona_updated, z_get.getNombre());
		
		
		//Test para comprobar que elimina el registro
		assertTrue(modelo.delete(id));
		assertTrue(total == modelo.getAll().size());	
	}
	/**
	 * 
	 */
	@Test
	public final void testCasosError() {
		//Intentar obtener un grado cuyo identificador 
		//no exista en la base de datos 
		id = -1;
		assertNull(modelo.getById(id));
		assertNull(modelo.getById(0));
		
		//Intentar actualizar un grado inexistente en la base de datos
		Zona g = new Zona(nombreZona);
		g.setId(id);
		assertTrue(!modelo.update(g));
		
		//Intentar actualizar un null
		assertTrue(!modelo.update(null));
		
		//Intentar insertar un null 
		assertTrue(-1 == modelo.save(null));
		
		//Intentar eliminar un grado con un id inexistente
		assertTrue(!modelo.delete(id));
		
		//Intentar insertar un grado con un id existente
		Zona z_1 = new Zona(nombreZona);
		id = modelo.save(z_1);
		Zona z_2 = new Zona(nombreZona);
		z_2.setId(id);
		
		assertTrue((id + 1) == modelo.save(z_2));
		
		assertTrue(modelo.delete(id));
		assertTrue(modelo.delete(id + 1));
		
		
		
	}

}
