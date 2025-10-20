package com.example.parcial1.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial1.data.ParadaPits
import com.example.parcial1.data.ParadaPitsRepositorio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListadoViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio: ParadaPitsRepositorio = ParadaPitsRepositorio(application.applicationContext)

    private val _pitStops = MutableStateFlow<List<ParadaPits>>(emptyList())
    val pitStops: StateFlow<List<ParadaPits>> = _pitStops

    init {
        cargarPitStops()
    }

    fun cargarPitStops() {
        viewModelScope.launch {
            _pitStops.value = repositorio.listar()
        }
    }

    fun eliminarPitStop(pitStop: ParadaPits) {
        viewModelScope.launch {
            repositorio.eliminar(pitStop.id)
            cargarPitStops()
        }
    }
}
