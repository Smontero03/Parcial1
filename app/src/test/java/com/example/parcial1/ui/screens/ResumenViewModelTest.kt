package com.example.parcial1.ui.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.parcial1.data.ParadaPits
import com.example.parcial1.data.ParadaPitsRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ResumenViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: ResumenViewModel
    private val repositorio: ParadaPitsRepositorio = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ResumenViewModel(repositorio)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cargarEstadisticas actualiza los flows con valores del repositorio`() = runTest {
        // Arrange
        val paradas = listOf(
            ParadaPits(
                id = 1,
                piloto = "P1",
                escuderia = "E1",
                tiempo = 10.0,
                neumatico = "Blandos",
                numNeumaticos = 4,
                estado = "OK",
                motivo = "Rutina",
                mecanico = "M1",
                fechaHora = "2024-01-01T12:00:00"
            ),
            ParadaPits(
                id = 2,
                piloto = "P2",
                escuderia = "E2",
                tiempo = 20.0,
                neumatico = "Medios",
                numNeumaticos = 4,
                estado = "OK",
                motivo = "Rutina",
                mecanico = "M2",
                fechaHora = "2024-01-01T12:05:00"
            )
        )
        val promedioEsperado = 15.0
        val totalEsperado = 2
        val menorTiempoEsperado = 10.0

        whenever(repositorio.calcularPromedioTiempo()).thenReturn(promedioEsperado)
        whenever(repositorio.contarRegistros()).thenReturn(totalEsperado)
        whenever(repositorio.obtenerMenorTiempo()).thenReturn(menorTiempoEsperado)
        whenever(repositorio.obtenerUltimasCincoParadas()).thenReturn(paradas)

        // Act
        viewModel.cargarEstadisticas()
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutines complete

        // Assert
        assertEquals(promedioEsperado, viewModel.promedioTiempo.value, 0.0)
        assertEquals(totalEsperado, viewModel.totalRegistros.value)
        assertEquals(menorTiempoEsperado, viewModel.menorTiempo.value, 0.0)
        assertEquals(paradas, viewModel.ultimasParadas.value)
    }
}
