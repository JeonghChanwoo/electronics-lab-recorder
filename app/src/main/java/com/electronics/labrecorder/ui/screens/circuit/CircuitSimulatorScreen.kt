package com.electronics.labrecorder.ui.screens.circuit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.electronics.labrecorder.R
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CircuitSimulatorScreen(navController: NavController) {
    var voltage by remember { mutableStateOf("") }
    var current by remember { mutableStateOf("") }
    var resistance by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }

    fun calculateOhmsLaw() {
        val v = voltage.toDoubleOrNull()
        val c = current.toDoubleOrNull()
        val r = resistance.toDoubleOrNull()

        when {
            v != null && r != null && r != 0.0 -> {
                current = (v / r).toString()
                power = (v * v / r).toString()
            }
            v != null && c != null && c != 0.0 -> {
                resistance = (v / c).toString()
                power = (v * c).toString()
            }
            c != null && r != null && r != 0.0 -> {
                voltage = (c * r).toString()
                power = (c * c * r).toString()
            }
        }
    }

    fun clearFields() {
        voltage = ""
        current = ""
        resistance = ""
        power = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text(stringResource(R.string.circuit_simulator)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Subtitle
        Text(
            text = "옴의 법칙 계산기 (V = I × R)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Input Fields
        OutlinedTextField(
            value = voltage,
            onValueChange = { voltage = it },
            label = { Text(stringResource(R.string.voltage_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = current,
            onValueChange = { current = it },
            label = { Text(stringResource(R.string.current_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = resistance,
            onValueChange = { resistance = it },
            label = { Text(stringResource(R.string.resistance_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // Power Display
        OutlinedTextField(
            value = power,
            onValueChange = {},
            label = { Text(stringResource(R.string.power_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            singleLine = true,
            enabled = false
        )

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { calculateOhmsLaw() },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text(stringResource(R.string.calculate))
            }

            Button(
                onClick = { clearFields() },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(stringResource(R.string.clear))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Results Box
        if (power.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "계산 결과",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "전압: ${voltage.ifEmpty { "계산됨" }} V",
                        fontSize = 12.sp
                    )
                    Text(
                        text = "전류: ${current.ifEmpty { "계산됨" }} A",
                        fontSize = 12.sp
                    )
                    Text(
                        text = "저항: ${resistance.ifEmpty { "계산됨" }} Ω",
                        fontSize = 12.sp
                    )
                    Text(
                        text = "전력: $power W",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}