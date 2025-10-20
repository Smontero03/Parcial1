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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    var currentScreen by remember { mutableStateOf("resumen") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF2F2F7),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    when (currentScreen) {
                        "resumen" -> navController.navigate("listado")
                        "registro" -> navController.navigate("resumen")
                        "listado" -> navController.navigate("registro")
                    }
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Anterior")
                }
                IconButton(onClick = {
                    when (currentScreen) {
                        "resumen" -> navController.navigate("registro")
                        "registro" -> navController.navigate("listado")
                        "listado" -> navController.navigate("resumen")
                    }
                }) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "resumen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("resumen") {
                currentScreen = "resumen"
                ResumenScreen(
                    onNavigateToRegistro = { navController.navigate("registro") },
                    onNavigateToListado = { navController.navigate("listado") }
                )
            }
            composable("registro") {
                currentScreen = "registro"
                RegistroScreen(onNavigateToResumen = { navController.navigate("resumen") })
            }
            composable("listado") {
                currentScreen = "listado"
                ListadoScreen(onNavigateToResumen = { navController.navigate("resumen") })
            }
        }
    }
}
