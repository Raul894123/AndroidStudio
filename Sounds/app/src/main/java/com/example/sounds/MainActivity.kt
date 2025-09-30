package com.example.sounds

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SoundImagesApp() }
    }
}

@Composable
fun SoundImagesApp() {
    val context = LocalContext.current

    // Готовим плееры под каждый звук и чистим их при уходе с экрана
    val cowPlayer = remember {
        MediaPlayer.create(context, R.raw.cow)   // res/raw/cow.mp3
    }
    val jetPlayer = remember {
        MediaPlayer.create(context, R.raw.jetfly) // res/raw/jetfly.mp3
    }

    // Чтобы не играли одновременно
    fun playExclusive(playerToPlay: MediaPlayer, others: List<MediaPlayer>) {
        others.forEach { p ->
            if (p.isPlaying) p.pause()
            p.seekTo(0)
        }
        if (playerToPlay.isPlaying) playerToPlay.seekTo(0)
        playerToPlay.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            runCatching { cowPlayer.stop() }
            runCatching { cowPlayer.release() }
            runCatching { jetPlayer.stop() }
            runCatching { jetPlayer.release() }
        }
    }

    // UI: две картинки одна под другой
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 🐄 КОРОВА  звук cow.mp3
        Image(
            painter = painterResource(id = R.drawable.cow),
            contentDescription = "Cow",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable {
                    playExclusive(cowPlayer, listOf(jetPlayer))
                },
            contentScale = ContentScale.Fit
        )

        // ✈️ САМОЛЁТ звук jetfly.mp3
        Image(
            painter = painterResource(id = R.drawable.jet),
            contentDescription = "Jet",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable {
                    playExclusive(jetPlayer, listOf(cowPlayer))
                },
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSoundImagesApp() {
    SoundImagesApp()
}
