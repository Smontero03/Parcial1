package com.example.parcial1.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial1.data.ParadaPitsRepositorio
import com.example.parcial1.ui.screens.ResumenViewModel

class ResumenViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResumenViewModel::class.java)) {
            val repositorio = ParadaPitsRepositorio(context.applicationContext)
            @Suppress("UNCHECKED_CAST")
            return ResumenViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
