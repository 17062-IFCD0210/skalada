package com.ipartek.formacion.skalada.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.bean.Grado;

public class TestModeloGrado {

	static ModeloGrado modelo = null;

	static String nombreGrado = "gradoMock";
	static String descripcionGrado = "Lorem impsun ���";
	static String descripcionGradoUpdated = "Updated Lorem impsun ���";
	static String nombreGradoUpdated = "updated";
	int id;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		modelo = new ModeloGrado();
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
	// @Ignore
	public void testCRUD() {
		int total; // Cantidad de registros iniciales en la tabla `test`
		int total_despues; // Cantidad de registros en la tabla `test` tras
		// insertar uno nuevo

		// Test para comprobar que al insertar un nuevo registro aumenta el ID
		Grado g_insert = new Grado(nombreGrado);
		g_insert.setDescripcion(descripcionGrado);

		total = modelo.getAll().size();
		this.id = modelo.save(g_insert);
		total_despues = modelo.getAll().size(); // total = total_despues + 1

		assertTrue(total == total_despues - 1);
		assertTrue((this.id != -1) && (this.id > 0));

		// Test para comprobar que al obtener un registro por su ID lo devuelve
		// correctamente
		Grado g_get;
		g_get = modelo.getById(this.id);
		assertTrue(this.id == g_get.getId());
		assertEquals(nombreGrado, g_get.getNombre());
		assertEquals(descripcionGrado, g_get.getDescripcion());

		// Test para comprobar que actualiza el registro indentificado por su ID
		Grado g_update = new Grado(nombreGradoUpdated);
		g_update.setDescripcion(descripcionGradoUpdated);
		g_update.setId(this.id);

		assertTrue(modelo.update(g_update));

		g_get = modelo.getById(g_update.getId());
		assertTrue(this.id == g_get.getId());
		assertEquals(nombreGradoUpdated, g_get.getNombre());
		assertEquals(descripcionGradoUpdated, g_get.getDescripcion());

		// Test para comprobar que elimina el registro
		assertTrue(modelo.delete(this.id));
		assertTrue(total == modelo.getAll().size());
	}

	@Test
	public void testCasosError() {
		// Intentar obtener un grado cuyo identificador no exista en la base de
		// datos
		this.id = -1;
		assertNull(modelo.getById(this.id));
		assertNull(modelo.getById(0));

		// Intentar actualizar un grado inexistente en la base de datos
		Grado g = new Grado(nombreGrado);
		g.setId(this.id);
		assertTrue(!modelo.update(g));

		// Intentar actualizar un null
		assertTrue(!modelo.update(null));

		// Intentar insertar un null
		assertTrue(-1 == modelo.save(null));

		// Intentar eliminar un grado con un id inexistente
		assertTrue(!modelo.delete(this.id));

		// Intentar insertar un grado con un id existente
		Grado g_1 = new Grado(nombreGrado);
		this.id = modelo.save(g_1);
		Grado g_2 = new Grado(nombreGrado);
		g_2.setId(this.id);

		assertTrue((this.id + 1) == modelo.save(g_2));

		assertTrue(modelo.delete(this.id));
		assertTrue(modelo.delete(this.id + 1));

	}

}
