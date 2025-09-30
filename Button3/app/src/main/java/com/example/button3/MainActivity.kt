package com.example.button3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ThreeButtonsApp() }
    }
}

@Composable
fun ThreeButtonsApp() {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    // Текущий цвет кнопки 2
    var currentColor by remember { mutableStateOf(Color.Blue) }

    // Для диалога подтверждения выхода
    var showDialog by remember { mutableStateOf(false) }

    // Список доступных цветов с названиями
    val colorsWithNames = listOf(
        "Красный" to Color.Red,
        "Зелёный" to Color.Green,
        "Синий" to Color.Blue,
        "Жёлтый" to Color.Yellow,
        "Магента" to Color.Magenta,
        "Циан" to Color.Cyan,
        "Чёрный" to Color.Black,
        "Серый" to Color.Gray
    )

    // Диалог подтверждения
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    backDispatcher?.onBackPressed()
                }) {
                    Text("Да")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Нет")
                }
            },
            title = { Text("Выход") },
            text = { Text("Вы уверены, что хотите закрыть приложение?") }
        )
    }

    // Основной UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔹 Кнопка 1 — Закрыть приложение
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Закрыть приложение")
            }

            // 🔹 Кнопка 2 — смена цвета и уведомление
            Button(
                onClick = {
                    val (name, color) = colorsWithNames.random()
                    currentColor = color
                    Toast.makeText(context, "Новый цвет: $name", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = currentColor)
            ) {
                Text("Сменить цвет")
            }

            // 🔹 Кнопка 3 — приветствие
            Button(
                onClick = {
                    Toast.makeText(context, "День добрый, Руслан Кайратович!", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Приветствие")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThreeButtons() {
    ThreeButtonsApp()
}
