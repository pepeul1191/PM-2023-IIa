package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToMain: () -> Unit
) {
    // Add your splash screen content here
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        // Display your splash screen content
        Text(
            text = "My App Splash Screen",
            style = MaterialTheme.typography.h4
        )
    }

    // Simulate a delay and then navigate to the main screen
    LaunchedEffect(Unit) {
        delay(2000) // Adjust the delay duration as needed
        navigateToMain()
    }
}