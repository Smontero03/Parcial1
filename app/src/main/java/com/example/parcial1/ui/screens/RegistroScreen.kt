package com.example.parcial1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial1.data.ParadaPits
import com.example.parcial1.data.ParadaPitsRepositorio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(onNavigateToResumen: () -> Unit) {
    val context = LocalContext.current
    var piloto by remember { mutableStateOf("") }
    var escuderia by remember { mutableStateOf("") }
    var tiempo by remember { mutableStateOf("") }

    val neumaticosOptions = listOf("Soft", "Medium", "Hard")
    var neumaticoSeleccionado by remember { mutableStateOf(neumaticosOptions[0]) }
    var neumaticosExpanded by remember { mutableStateOf(false) }

    var numeroNeumaticos by remember { mutableStateOf("") }

    val estadoOptions = listOf("OK", "Fallido")
    var estadoSeleccionado by remember { mutableStateOf(estadoOptions[0]) }
    var estadoExpanded by remember { mutableStateOf(false) }

    var motivoFallo by remember { mutableStateOf("") }
    var mecanico by remember { mutableStateOf("") }
    var fechaHora by remember { mutableStateOf("") }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Gray,
        focusedPlaceholderColor = Color.Gray,
        unfocusedPlaceholderColor = Color.Gray
    )

    val dropdownColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Gray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registrar Pit Stop",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = piloto,
                    onValueChange = { piloto = it },
                    label = { Text("PILOTO") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = escuderia,
                    onValueChange = { escuderia = it },
                    label = { Text("ESCUDERÍA") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tiempo,
                    onValueChange = { tiempo = it },
                    label = { Text("TIEMPO TOTAL (S)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExposedDropdownMenuBox(
                    expanded = neumaticosExpanded,
                    onExpandedChange = { neumaticosExpanded = !neumaticosExpanded }
                ) {
                    OutlinedTextField(
                        value = neumaticoSeleccionado,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("CAMBIO DE NEUMÁTICOS") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = neumaticosExpanded)
                        },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        colors = dropdownColors
                    )
                    ExposedDropdownMenu(
                        expanded = neumaticosExpanded,
                        onDismissRequest = { neumaticosExpanded = false }
                    ) {
                        neumaticosOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    neumaticoSeleccionado = option
                                    neumaticosExpanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = numeroNeumaticos,
                    onValueChange = { numeroNeumaticos = it },
                    label = { Text("NUMERO DE NEUMÁTICOS CAMBIADOS") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExposedDropdownMenuBox(
                    expanded = estadoExpanded,
                    onExpandedChange = { estadoExpanded = !estadoExpanded }
                ) {
                    OutlinedTextField(
                        value = estadoSeleccionado,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("ESTADO") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = estadoExpanded)
                        },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        colors = dropdownColors
                    )
                    ExposedDropdownMenu(
                        expanded = estadoExpanded,
                        onDismissRequest = { estadoExpanded = false }
                    ) {
                        estadoOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    estadoSeleccionado = option
                                    estadoExpanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = motivoFallo,
                    onValueChange = { motivoFallo = it },
                    label = { Text("MOTIVO DEL FALLO") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = mecanico,
                        onValueChange = { mecanico = it },
                        label = { Text("MECÁNICO PRINCIPAL") },
                        modifier = Modifier.weight(1f),
                        colors = textFieldColors
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = fechaHora,
                        onValueChange = { fechaHora = it },
                        label = { Text("FECHA Y HORA DEL PIT STOP") },
                        modifier = Modifier.weight(1f),
                        colors = textFieldColors
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    Button(
                        onClick = {

                            val repo = ParadaPitsRepositorio(context)
                            val parada = ParadaPits(
                                piloto = piloto,
                                escuderia = escuderia,
                                tiempo = tiempo.toDoubleOrNull() ?: 0.0,
                                neumatico = neumaticoSeleccionado,
                                numNeumaticos = numeroNeumaticos.toIntOrNull() ?: 0,
                                estado = estadoSeleccionado,
                                motivo = motivoFallo,
                                mecanico = mecanico,
                                fechaHora = fechaHora)

                            repo.insertar(parada)
                            onNavigateToResumen()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("GUARDAR", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onNavigateToResumen,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("CANCELAR", color = Color.White)
                    }
                }
            }
        }
    }
}
