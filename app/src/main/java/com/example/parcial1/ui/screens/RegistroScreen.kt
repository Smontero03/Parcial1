package com.example.parcial1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistroScreen(onNavigateToResumen: () -> Unit) {
    var piloto by remember { mutableStateOf("Lewis Hamilton") }
    var escuderia by remember { mutableStateOf("Mercedes") }
    var tiempo by remember { mutableStateOf("12.5") }
    var cambioNeumaticos by remember { mutableStateOf("Soft") }
    var numeroNeumaticos by remember { mutableStateOf("4") }
    var estado by remember { mutableStateOf("Ok") }
    var motivoFallo by remember { mutableStateOf("") }
    var mecanico by remember { mutableStateOf("John Doe") }
    var fechaHora by remember { mutableStateOf("12/05/2024 14:30") }

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
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = escuderia,
                    onValueChange = { escuderia = it },
                    label = { Text("ESCUDERÍA") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = tiempo,
                    onValueChange = { tiempo = it },
                    label = { Text("TIEMPO TOTAL (S)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = cambioNeumaticos,
                    onValueChange = { cambioNeumaticos = it },
                    label = { Text("CAMBIO DE NEUMÁTICOS") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = numeroNeumaticos,
                    onValueChange = { numeroNeumaticos = it },
                    label = { Text("NUMERO DE NEUMÁTICOS CAMBIADOS") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = estado,
                    onValueChange = { estado = it },
                    label = { Text("ESTADO") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = motivoFallo,
                    onValueChange = { motivoFallo = it },
                    label = { Text("MOTIVO DEL FALLO") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = mecanico,
                        onValueChange = { mecanico = it },
                        label = { Text("MECÁNICO PRINCIPAL") },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = fechaHora,
                        onValueChange = { fechaHora = it },
                        label = { Text("FECHA Y HORA DEL PIT STOP") },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, unfocusedBorderColor = Color.Gray)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    Button(
                        onClick = { /* TODO */ },
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