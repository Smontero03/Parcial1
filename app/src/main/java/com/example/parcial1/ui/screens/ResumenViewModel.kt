package com.example.parcial1.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial1.data.ParadaPits
import com.example.parcial1.data.ParadaPitsRepositorio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResumenViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio: ParadaPitsRepositorio = ParadaPitsRepositorio(application.applicationContext)

    private val _promedioTiempo = MutableStateFlow(0.0)
    val promedioTiempo: StateFlow<Double> = _promedioTiempo

    private val _totalRegistros = MutableStateFlow(0)
    val totalRegistros: StateFlow<Int> = _totalRegistros

    private val _menorTiempo = MutableStateFlow(0.0)
    val menorTiempo: StateFlow<Double> = _menorTiempo

    private val _ultimasParadas = MutableStateFlow<List<ParadaPits>>(emptyList())
    val ultimasParadas: StateFlow<List<ParadaPits>> = _ultimasParadas

    init {
        cargarEstadisticas()
    }

    fun cargarEstadisticas() {
        viewModelScope.launch {
            _promedioTiempo.value = repositorio.calcularPromedioTiempo()
            _totalRegistros.value = repositorio.contarRegistros()
            _menorTiempo.value = repositorio.obtenerMenorTiempo()
            _ultimasParadas.value = repositorio.obtenerUltimasCincoParadas()
        }
    }
}
