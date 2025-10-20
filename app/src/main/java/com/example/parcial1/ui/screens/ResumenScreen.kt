package com.example.parcial1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumenScreen(
    onNavigateToRegistro: () -> Unit,
    onNavigateToListado: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resumen de Pit Stops",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow(icon = { Icon(Icons.Default.DirectionsCar, contentDescription = "Pit stop más rápido", tint = Color.Red) }, text = "Pit stop más rápido: 2.3 s")
                InfoRow(icon = { Icon(Icons.Default.AccessTime, contentDescription = "Promedio de tiempos") }, text = "Promedio de tiempos: 2.82 s")
                InfoRow(icon = { Text("S", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface) }, text = "Total de paradas: 5")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Últimos pit stops",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                BarChart()
            }
        }

        Button(
            onClick = onNavigateToRegistro,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Registrar Pit Stop", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToListado,
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Ver Listado", color = Color.White)
        }
    }
}

@Composable
fun InfoRow(icon: @Composable () -> Unit, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}

@Composable
fun BarChart() {
    val barValues = listOf(5f, 4f, 3f, 2f, 1f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        barValues.forEach { value ->
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .fillMaxHeight(value / barValues.maxOrNull()!!)
                    .background(Color.Red)
            )
        }
    }
    Text(
        text = "Tiempos (s)",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        fontSize = 12.sp
    )
}
