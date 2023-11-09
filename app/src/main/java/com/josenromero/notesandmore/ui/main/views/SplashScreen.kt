package com.josenromero.notesandmore.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.notesandmore.R
import com.josenromero.notesandmore.ui.theme.NotesAndMoreTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToHomeScreen: () -> Unit
) {

    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(3000),
        label = "AnimatedSplashScreen"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        onNavigateToHomeScreen()
    }

    SplashScreenContent(alphaAnim.value)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreenContent(alpha: Float) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.scrim
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo_icon),
                    modifier = Modifier
                        .alpha(alpha)
                        .size(120.dp)
                )
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    NotesAndMoreTheme {
        SplashScreenContent(alpha = 1f)
    }
}
