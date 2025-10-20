package com.example.parcial1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PitStop(val num: Int, val piloto: String, val tiempo: Double, val estado: String)

@Composable
fun ListadoScreen(onNavigateToResumen: () -> Unit) {
    val pitStops = listOf(
        PitStop(1, "Oliveiro", 2.4, "OK"),
        PitStop(2, "James", 2.8, "Fallido"),
        PitStop(3, "Mark", 2.3, "OK"),
        PitStop(4, "Sebastian", 3.1, "Fallido"),
        PitStop(5, "Lucas", 3.0, "Fallido")
    )
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

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Núm.", fontWeight = FontWeight.Bold)
                    Text("Piloto", fontWeight = FontWeight.Bold)
                    Text("Tiempo (s)", fontWeight = FontWeight.Bold)
                    Text("Estado", fontWeight = FontWeight.Bold)
                    Text("", fontWeight = FontWeight.Bold) // For the edit icon
                }

                LazyColumn(modifier = Modifier.heightIn(max = 400.dp)) {
                    items(pitStops.filter { it.piloto.contains(searchQuery, ignoreCase = true) }) { pitStop ->
                        PitStopRow(pitStop)
                        Divider()
                    }
                }
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
fun PitStopRow(pitStop: PitStop) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(pitStop.num.toString())
        Text(pitStop.piloto)
        Text(pitStop.tiempo.toString())
        Text(
            text = pitStop.estado,
            color = Color.White,
            modifier = Modifier
                .background(
                    color = if (pitStop.estado == "OK") Color.Green else Color.Red,
                    shape = MaterialTheme.shapes.small
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
        IconButton(onClick = { /* TODO */ }) {
            Icon(Icons.Default.Edit, contentDescription = "Editar")
        }
    }
}
