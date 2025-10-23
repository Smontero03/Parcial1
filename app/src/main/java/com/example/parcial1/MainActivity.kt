package com.example.parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF2F2F7)
        // Top bar removed as requested
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "resumen",
            modifier = Modifier.padding(innerPadding)
        ) {
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
