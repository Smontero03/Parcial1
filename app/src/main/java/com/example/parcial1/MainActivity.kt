package com.example.parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parcial1.ui.screens.ListadoScreen
import com.example.parcial1.ui.screens.RegistroScreen
import com.example.parcial1.ui.screens.ResumenScreen
import com.example.parcial1.ui.theme.Parcial1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Parcial1Theme {
                val view = LocalView.current
                LaunchedEffect(Unit) {
                    val window = (view.context as android.app.Activity).window
                    val insetsController = WindowCompat.getInsetsController(window, view)
                    insetsController.hide(WindowInsetsCompat.Type.statusBars())
                }
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    // La única fuente de verdad para saber la pantalla actual
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF2F2F7),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón "Atrás" con tu lógica de CICLO INVERSO
                IconButton(onClick = {
                    val prevScreen = when (currentRoute) {
                        "resumen" -> "listado"
                        "registro" -> "resumen"
                        "listado" -> "registro"
                        else -> "resumen" // Ruta por defecto
                    }
                    navController.navigate(prevScreen) { launchSingleTop = true }
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Anterior")
                }
                // Botón "Adelante" con tu lógica de CICLO HACIA ADELANTE
                IconButton(onClick = {
                    val nextScreen = when (currentRoute) {
                        "resumen" -> "registro"
                        "registro" -> "listado"
                        "listado" -> "resumen"
                        else -> "resumen" // Ruta por defecto
                    }
                    navController.navigate(nextScreen) { launchSingleTop = true }
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Siguiente")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "resumen",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Limpio: ya no se gestiona el estado aquí
            composable("resumen") {
                ResumenScreen(
                    onNavigateToRegistro = { navController.navigate("registro") },
                    onNavigateToListado = { navController.navigate("listado") }
                )
            }
            composable("registro") {
                RegistroScreen(onNavigateToResumen = { navController.navigate("resumen") })
            }
            composable("listado") {
                ListadoScreen(onNavigateToResumen = { navController.navigate("resumen") })
            }
        }
    }
}
