package com.example.parcial1.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial1.data.ParadaPitsRepositorio
import com.example.parcial1.ui.screens.ListadoViewModel

class ListadoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListadoViewModel::class.java)) {
            val repositorio = ParadaPitsRepositorio(context.applicationContext)
            @Suppress("UNCHECKED_CAST")
            return ListadoViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
