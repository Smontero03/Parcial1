package com.example.parcial1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial1.data.model.PitStop

// Datos de ejemplo
private val samplePitStops = listOf(
    PitStop(1, "Bahrain Grand Prix", "Max Verstappen", "Red Bull Racing", 25, 2.2f),
    PitStop(2, "Saudi Arabian Grand Prix", "Charles Leclerc", "Ferrari", 28, 2.5f),
    PitStop(3, "Australian Grand Prix", "Lando Norris", "McLaren", 23, 2.1f),
    PitStop(4, "Japanese Grand Prix", "Sergio Pérez", "Red Bull Racing", 30, 2.8f)
)

@Composable
fun PitStopListScreen(onNavigateToSummary: () -> Unit) { // Parámetro añadido
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Fila para contener ambos botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly // Distribuye los botones
        ) {
            Button(onClick = { /* TODO: Lógica de edición */ }) {
                Text("Editar")
            }
            Button(onClick = onNavigateToSummary) { // Acción de navegación
                Text("Volver al Resumen")
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre botones y lista

        LazyColumn {
            items(samplePitStops) { pitStop ->
                PitStopListItem(pitStop = pitStop)
            }
        }
    }
}

@Composable
fun PitStopListItem(pitStop: PitStop) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = pitStop.raceName, fontWeight = FontWeight.Bold)
            Text(text = "Driver: ${pitStop.driverName}")
            Text(text = "Team: ${pitStop.teamName}")
            Row {
                Text(text = "Lap: ${pitStop.lap}")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Time: ${pitStop.stopTime}s")
            }
        }
    }
}
