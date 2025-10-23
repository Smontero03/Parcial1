package com.example.parcial1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    var isEditMode by remember { mutableStateOf(false) }

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
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(pitStops.filter { it.piloto.contains(searchQuery, ignoreCase = true) }) { pitStop ->
                PitStopCard(
                    pitStop = pitStop,
                    isEditMode = isEditMode,
                    // AHORA SÍ: Se llama a la función que acabamos de crear en el ViewModel
                    onPitStopChange = { updatedStop -> viewModel.actualizarPitStop(updatedStop) },
                    onDelete = { viewModel.eliminarPitStop(pitStop) }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { isEditMode = !isEditMode },
                colors = ButtonDefaults.buttonColors(containerColor = if (isEditMode) Color(0xFF4CAF50) else Color.Blue)
            ) {
                // El botón ahora solo se encarga de cambiar el modo
                Text(if (isEditMode) "Finalizar Edición" else "Editar", color = Color.White)
            }
            Button(
                onClick = onNavigateToResumen,
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text("Volver al Resumen", color = Color.White)
            }
        }
    }
}

@Composable
fun PitStopCard(
    pitStop: ParadaPits,
    isEditMode: Boolean,
    onPitStopChange: (ParadaPits) -> Unit,
    onDelete: () -> Unit
) {
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
                if (!isEditMode) { 
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            if (isEditMode) {
                // --- VISTA DE EDICIÓN ---
                OutlinedTextField(
                    value = pitStop.piloto,
                    onValueChange = { onPitStopChange(pitStop.copy(piloto = it)) },
                    label = { Text("Piloto") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
                )
                OutlinedTextField(
                    value = pitStop.escuderia,
                    onValueChange = { onPitStopChange(pitStop.copy(escuderia = it)) },
                    label = { Text("Escudería") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
                )
                 OutlinedTextField(
                    value = pitStop.tiempo.toString(),
                    onValueChange = { 
                        onPitStopChange(pitStop.copy(tiempo = it.toDoubleOrNull() ?: 0.0))
                    },
                    label = { Text("Tiempo") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                // TODO: Añadir aquí OutlinedTextFields para los demás campos si se desea

            } else {
                // --- VISTA DE SOLO LECTURA ---
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
}

@Composable
fun InfoDetailRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, modifier = Modifier.width(120.dp))
        Text(text = value)
    }
}
