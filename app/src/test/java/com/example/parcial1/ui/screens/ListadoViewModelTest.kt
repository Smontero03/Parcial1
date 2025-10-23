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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ListadoViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: ListadoViewModel
    private val repositorio: ParadaPitsRepositorio = mock()

    private val parada1 = ParadaPits(1, "P1", "E1", 10.0, "Blandos", 4, "OK", "Rutina", "M1", "2024-01-01T12:00:00")
    private val parada2 = ParadaPits(2, "P2", "E2", 20.0, "Medios", 4, "OK", "Rutina", "M2", "2024-01-01T12:05:00")
    private val paradasDePrueba = listOf(parada1, parada2)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ListadoViewModel(repositorio)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cargarPitStops actualiza el flow con la lista del repositorio`() = runTest {
        // Arrange
        whenever(repositorio.listar()).thenReturn(paradasDePrueba)

        // Act
        viewModel.cargarPitStops()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(paradasDePrueba, viewModel.pitStops.value)
    }

    @Test
    fun `eliminarPitStop llama a eliminar del repositorio y recarga la lista`() = runTest {
        // Arrange
        // Simulate that the list is already loaded
        whenever(repositorio.listar()).thenReturn(paradasDePrueba).thenReturn(listOf(parada2))
        viewModel.cargarPitStops()
        testDispatcher.scheduler.advanceUntilIdle()

        // Act
        viewModel.eliminarPitStop(parada1)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        // Verify that the delete method was called on the repository
        verify(repositorio).eliminar(parada1.id)
        // Verify that the list was refreshed and now only contains the second stop
        assertEquals(listOf(parada2), viewModel.pitStops.value)
    }
}
