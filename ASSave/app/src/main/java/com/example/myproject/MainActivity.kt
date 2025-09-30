package com.example.myproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myproject.ui.theme.MyProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProjectTheme { HelloWithButton() }
        }
    }
}

@Composable
fun HelloWithButton() {
    val context = LocalContext.current
    var name by rememberSaveable { mutableStateOf("Raul") } // это штука хранит имя

    // Центруем контент
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hello $name!", // выводит имя
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(24.dp))
            // делаем кнопочку, она показывает уведомление при нажатии
            Button(
                onClick = {
                    val who = name.trim()
                    val msg = if (who.isEmpty()) "Введите имя" else "Привет, $who!"
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            ) {
                Text("НАЖМИ НА МЕНЯ")
            }

            Spacer(Modifier.height(24.dp))

                //Это поле для ввода имени
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                label = { Text("Введите имя") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHelloWithButton() {
    MyProjectTheme { HelloWithButton() }
}
