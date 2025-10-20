package com.example.parcial1

import androidx.test.core.app.ApplicationProvider
import com.example.parcial1.data.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class ParadaPitsRepositorioTest {
    private lateinit var context: android.content.Context
    private lateinit var dbHelper: AuxiliarParadaPits
    private lateinit var repo: ParadaPitsRepositorio

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = AuxiliarParadaPits(context)
        dbHelper.writableDatabase.execSQL("DROP TABLE IF EXISTS ParadaPits")
        dbHelper.onCreate(dbHelper.writableDatabase)
        repo = ParadaPitsRepositorio(context)
    }

    @After
    fun tearDown() {
        dbHelper.close()
    }

    @Test
    fun insertarPitStop_deberiaGuardarUnRegistro() {
        val ParadaPits = ParadaPits(
            piloto = "Lewis Hamilton",
            escuderia = "Mercedes",
            tiempo = 2.5,
            neumatico = "Soft",
            numNeumaticos = 4,
            estado = "OK",
            motivo = "",
            mecanico = "John Doe",
            fechaHora = "19/10/2025 14:30"

        )

        repo.insertar(ParadaPits)
        val lista = repo.listar()

        assertEquals(1, lista.size)
        assertEquals("Lewis Hamilton", lista[0].piloto)
    }

    @Test
    fun listarPitStops_vaciaDevuelveListaVacia() {
        val lista = repo.listar()
        assertTrue(lista.isEmpty())
    }

    @Test
    fun insertarVariosPitStops_yVerificarPromedio() {
        repo.insertar(ParadaPits(0, "Mark", "Red Bull", 2.4, "Soft", 4, "OK", "", "John", "19/10/2025"))
        repo.insertar(ParadaPits(0, "James", "Ferrari", 2.8, "Soft", 4, "Fallo", "Rueda suelta", "Mike", "19/10/2025"))

        val lista = repo.listar()
        val promedio = lista.map { it.tiempo }.average()

        assertEquals(2, lista.size)
        assertEquals(2.6, promedio, 0.01)
    }

    @Test
    fun eliminarPitStop_deberiaQuitarRegistro() {
        val p = ParadaPits(0, "Sebastian", "Aston Martin", 3.0, "Soft", 4, "OK", "", "John", "19/10/2025")
        repo.insertar(p)
        val lista1 = repo.listar()
        assertEquals(1, lista1.size)

        val id = lista1[0].id
        repo.eliminar(id)

        val lista2 = repo.listar()
        assertTrue(lista2.isEmpty())
    }

    @Test
    fun actualizarPitStop_deberiaModificarDatos() {
        val p = ParadaPits(0, "Max", "Red Bull", 2.3, "Soft", 4, "OK", "", "John", "19/10/2025")
        repo.insertar(p)
        val original = repo.listar()[0]

        val actualizado = original.copy(tiempo = 2.1, estado = "Fallo")
        repo.actualizar(actualizado)

        val nuevo = repo.listar()[0]
        assertEquals(2.1, nuevo.tiempo, 0.01)
        assertEquals("Fallo", nuevo.estado)
    }
}