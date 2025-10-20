package com.example.parcial1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial1.data.ParadaPits

@Composable
fun ListadoScreen(
    onNavigateToResumen: () -> Unit,
    viewModel: ListadoViewModel = viewModel()
) {
    val pitStops by viewModel.pitStops.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF2F2F7))
        .padding(16.dp)) {
        Text(
            text = "Listado de Pit Stops",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(pitStops.filter { it.piloto.contains(searchQuery, ignoreCase = true) }) { pitStop ->
                PitStopCard(pitStop = pitStop, onDelete = { viewModel.eliminarPitStop(pitStop) })
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateToResumen,
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver al Resumen", color = Color.White)
        }
    }
}

@Composable
fun PitStopCard(pitStop: ParadaPits, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pit Stop #${pitStop.id}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoDetailRow("Piloto:", pitStop.piloto)
            InfoDetailRow("Escudería:", pitStop.escuderia)
            InfoDetailRow("Tiempo:", "${pitStop.tiempo} s")
            InfoDetailRow("Neumático:", pitStop.neumatico)
            InfoDetailRow("Num. Neumáticos:", pitStop.numNeumaticos.toString())
            InfoDetailRow("Estado:", pitStop.estado)
            InfoDetailRow("Motivo:", pitStop.motivo)
            InfoDetailRow("Mecánico:", pitStop.mecanico)
            InfoDetailRow("Fecha y Hora:", pitStop.fechaHora)
        }
    }
}

@Composable
fun InfoDetailRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, modifier = Modifier.width(120.dp))
        Text(text = value)
    }
}
