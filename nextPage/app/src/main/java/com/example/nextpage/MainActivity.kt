package com.example.nextpage  // ← поменяй под свой package

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TwoScreensApp() }
    }
}

@Composable
fun TwoScreensApp() {
    val nav = rememberNavController()
    MaterialTheme {
        NavHost(navController = nav, startDestination = "screen1") {
            composable("screen1") { Screen1(nav) }
            composable("screen2") { Screen2(nav) }
        }
    }
}

@Composable
fun Screen1(nav: NavHostController) {
    // Центрируем кнопку
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { nav.navigate("screen2") }
        ) {
            Text("ПЕРЕЙТИ НА НОВУЮ СТРАНИЦУ")
        }
    }
}

@Composable
fun Screen2(nav: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // заголовок как на первом скрине
            Text(
                text = "Новая страница",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Button(onClick = { nav.popBackStack() }) {
                Text("НАЗАД")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen1() {
    val fakeNav = rememberNavController()
    Screen1(fakeNav)
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen2() {
    val fakeNav = rememberNavController()
    Screen2(fakeNav)
}
