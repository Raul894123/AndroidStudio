package com.example.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*          // Layout-компоненты (Column, Box, Spacer и т.д.)
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*                // Material 3 компоненты (Button, Text, Scaffold и т.д.)
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.runtime.*                  // Для состояния (remember, mutableStateOf)
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview // Для превью в Android Studio
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BlueCalculatorApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlueCalculatorApp() {
    val primaryBlue = Color(0xFF1976D2)   // синий
    val bgBlueLight = Color(0xFFE3F2FD)  // светлый фон

    MaterialTheme(colorScheme = lightColorScheme(
        primary = primaryBlue,
        onPrimary = Color.White,
        background = bgBlueLight,
        surface = bgBlueLight
    )) {
        val ctx = LocalContext.current
        var a by rememberSaveable { mutableStateOf("") }
        var b by rememberSaveable { mutableStateOf("") }
        var result by rememberSaveable { mutableStateOf<Double?>(null) }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Calculator", color = Color.White) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = primaryBlue
                    )
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Результат
                    Text(
                        text = result?.let { "%.2f".format(it) } ?: "--",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryBlue
                    )

                    // Поле 1
                    OutlinedTextField(
                        value = a,
                        onValueChange = { a = it },
                        singleLine = true,
                        label = { Text("First number") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth(),
                        colors = colors(
                            focusedBorderColor = primaryBlue,
                            focusedLabelColor = primaryBlue,
                            cursorColor = primaryBlue
                        )
                    )

                    // Поле 2
                    OutlinedTextField(
                        value = b,
                        onValueChange = { b = it },
                        singleLine = true,
                        label = { Text("Second number") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth(),
                        colors = colors(
                            focusedBorderColor = primaryBlue,
                            focusedLabelColor = primaryBlue,
                            cursorColor = primaryBlue
                        )
                    )

                    // Кнопка "Add"
                    Button(
                        onClick = {
                            val x = a.replace(',', '.').toDoubleOrNull()
                            val y = b.replace(',', '.').toDoubleOrNull()
                            if (x == null || y == null) {
                                Toast.makeText(ctx, "Enter valid numbers", Toast.LENGTH_SHORT).show()
                            } else {
                                result = x + y
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = buttonColors(containerColor = primaryBlue)
                    ) {
                        Text("ADD", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBlueCalculator() {
    BlueCalculatorApp()
}
