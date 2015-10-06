package com.ipartek.formacion.skalada.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.bean.TipoEscalada;
/**
 * 
 * @author Curso
 *
 */

public class TestModeloTipoEscalada {
	/**
	 * 
	 */
	private static ModeloTipoEscalada modelo = null;
	/**
	 * 
	 */
	private static String nombreTipoEscalada = "TipoEscaladaMock";
	/**
	 * 
	 */
	private static String descripcionTipoEscalada = "Lorem impsum";
	/**
	 * 
	 */
	private static String nombreTipoEscalada_updated = "updated";
	/**
	 * 
	 */
	private static String descripcionTipoEscalada_updated =
					"Updated Lorem impsun";
	/**
	 * 
	 */
	private static int id;
	/**
	 * 
	 */
	private static TipoEscalada te_get;
	/**
	 * 
	 */
	private static TipoEscalada te_insert;
	/**
	 * 
	 */
	private static TipoEscalada te_update;
	/**
	 * 
	 */
	
	private static int total;		//Cantidad de registros 
									//iniciales en la tabla `tipo_escalada`
	/**
	 * 
	 */
	private static int total_despues; //Cantidad de registros en la tabla 
							//tipo_escalada` tras insertar uno nuevo	
	
	/**
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		modelo = new ModeloTipoEscalada();
		total = modelo.getAll().size();	
	}
	 /**
	  * 
	  * @throws Exception
	  */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		assertTrue(total == modelo.getAll().size());
		modelo = null;
	}
	/**
	 * 
	 * @throws Exception
	 */

	@Before
	public final void setUp() throws Exception {
		te_insert = new TipoEscalada(nombreTipoEscalada);
		te_insert.setDescripcion(descripcionTipoEscalada);
		id = modelo.save(te_insert);
	}
	/**
	 * 
	 * @throws Exception
	 */
	@After
	public final void tearDown() throws Exception {
		te_insert = null;
		modelo.delete(id);
	}

	/**
	 * 
	 */
	@Test
	public final void testGetAll() {		
		assertTrue("getAll() devuelve siempre un numero positivo", total > 0);
	}
	/**
	 * 
	 */
	@Test
	public final void testInsertar() {
		//Test para comprobar que al insertar un nuevo registro aumenta el ID
		total_despues = modelo.getAll().size();	// total = total_despues + 1	
		
		assertTrue("al insertar un nuevo registro aumenta el ID"
				+ total + total_despues, total == total_despues - 1);
		assertTrue("save(o) devuelve siempre un id positivo",
				(id != -1) && (id > 0));
		
		//Intentar insertar un null 
		assertTrue(-1 == modelo.save(null));
		
		//Intentar insertar un TipoEscalada con un id existente
		TipoEscalada te_2 = new TipoEscalada(nombreTipoEscalada);
		te_2.setId(id);
		
		assertTrue((id + 1) == modelo.save(te_2));
		
		modelo.delete(id + 1);
	}
	/**	
	 * 
	 */
	@Test
	public final void testGetById() {	
		//Test para comprobar que al obtener
		//un registro por su ID lo devuelve correctamente				
		te_get = (TipoEscalada) modelo.getById(id);	
		assertTrue(id == te_get.getId());
		assertEquals(nombreTipoEscalada, te_get.getNombre());
		assertEquals(descripcionTipoEscalada, te_get.getDescripcion());
		
		//Intentar obtener un TipoEscalada
		//cuyo identificador no exista en la base de datos 
		assertNull(modelo.getById(-1));
		assertNull(modelo.getById(0));
	}
	/**
	 * 
	 */
	@Test
	public final void testUpdate() {		
		//Test para comprobar que actualiza el registro indentificado por su ID
		te_update = new TipoEscalada(nombreTipoEscalada_updated);
		te_update.setDescripcion(descripcionTipoEscalada_updated);
		te_update.setId(id);
		
		assertTrue(modelo.update(te_update));
		
		te_get = (TipoEscalada) modelo.getById(te_update.getId());	
		assertTrue(id == te_get.getId());
		assertEquals(nombreTipoEscalada_updated, te_get.getNombre());
		assertEquals(descripcionTipoEscalada_updated, te_get.getDescripcion());
		
		//Intentar actualizar un TipoEscalada inexistente en la base de datos
		TipoEscalada te = new TipoEscalada(nombreTipoEscalada);
		te.setId(-1);
		assertTrue(!modelo.update(te));
		//Intentar actualizar un null
		assertTrue(!modelo.update(null));
	}
	/**
	 * 
	 */
	@Test
	public final void testdelete() {
		//Test para comprobar que elimina el registro
		TipoEscalada te_2 = new TipoEscalada(nombreTipoEscalada);
		assertTrue(modelo.delete(modelo.save(te_2)));
		
		// (total + 1) Porque en el set app se inserta un registro
		assertTrue(total + 1 == modelo.getAll().size()); 
		
		//Intentar eliminar un TipoEscalada con un id inexistente
		assertTrue(!modelo.delete(0));
	}
	
}
