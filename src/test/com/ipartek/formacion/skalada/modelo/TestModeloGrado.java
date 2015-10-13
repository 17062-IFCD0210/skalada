package com.ipartek.formacion.skalada.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.skalada.bean.Grado;

public class TestModeloGrado {

	private static ModeloGrado modeloGrado = null;

	static final String NOMBREGRADO = "gradoMock";
	static final String DESCRIPCIONGRADO = "Lorem impsun Ñóü";
	static final String DESCRIPCIONGRADO_UPDATED = "Updated Lorem impsun Ñóü";
	static final String NOMBREGRADO_UPDATED = "updated";
	private int id;

	@BeforeClass()
	public static void setUpBeforeClass() throws Exception {
		modeloGrado = new ModeloGrado();
	}

	@AfterClass()
	public static void tearDownAfterClass() throws Exception {
		modeloGrado = null;
	}

	@Before()
	public void setUp() throws Exception {		
	}

	@After()
	public void tearDown() throws Exception {
	}

	@Test()
	// @Ignore
	public void testCRUD() {
		int total; // Cantidad de registros iniciales en la tabla `grado`
		int total_despues; // Cantidad de registros en la tabla `grado` tras insertar uno nuevo

		// Test para comprobar que al insertar un nuevo registro aumenta el ID
		Grado g_insert = new Grado(NOMBREGRADO);
		g_insert.setDescripcion(DESCRIPCIONGRADO);

		total = modeloGrado.getAll(null).size();
		this.id = modeloGrado.save(g_insert);
		total_despues = modeloGrado.getAll(null).size(); // total = total_despues + 1

		assertTrue(total == (total_despues - 1));
		assertTrue((this.id != -1) && (this.id > 0));

		// Test para comprobar que al obtener un registro por su ID lo devuelve correctamente
		Grado g_get;
		g_get = (Grado) modeloGrado.getById(this.id);
		assertTrue(this.id == g_get.getId());
		assertEquals(NOMBREGRADO, g_get.getNombre());
		assertEquals(DESCRIPCIONGRADO, g_get.getDescripcion());

		// Test para comprobar que actualiza el registro indentificado por su ID
		Grado g_update = new Grado(NOMBREGRADO_UPDATED);
		g_update.setDescripcion(DESCRIPCIONGRADO_UPDATED);
		g_update.setId(this.id);

		assertTrue(modeloGrado.update(g_update));

		g_get = (Grado) modeloGrado.getById(g_update.getId());
		assertTrue(this.id == g_get.getId());
		assertEquals(NOMBREGRADO_UPDATED, g_get.getNombre());
		assertEquals(DESCRIPCIONGRADO_UPDATED, g_get.getDescripcion());

		// Test para comprobar que elimina el registro
		assertTrue(modeloGrado.delete(this.id));
		assertTrue(total == modeloGrado.getAll(null).size());
	}

	@Test()
	public void testCasosError() {
		// Intentar obtener un grado cuyo identificador no exista en la base de
		// datos
		this.id = -1;
		assertNull(modeloGrado.getById(this.id));
		assertNull(modeloGrado.getById(0));

		// Intentar actualizar un grado inexistente en la base de datos
		Grado g = new Grado(NOMBREGRADO);
		g.setId(this.id);
		assertTrue(!modeloGrado.update(g));

		// Intentar actualizar un null
		assertTrue(!modeloGrado.update(null));

		// Intentar insertar un null
		assertTrue(-1 == modeloGrado.save(null));

		// Intentar eliminar un grado con un id inexistente
		assertTrue(!modeloGrado.delete(this.id));

		// Intentar insertar un grado con un id existente
		Grado g_1 = new Grado(NOMBREGRADO);
		this.id = modeloGrado.save(g_1);
		Grado g_2 = new Grado(NOMBREGRADO);
		g_2.setId(this.id);

		assertTrue((this.id + 1) == modeloGrado.save(g_2));

		assertTrue(modeloGrado.delete(this.id));
		assertTrue(modeloGrado.delete(this.id + 1));

	}

}
