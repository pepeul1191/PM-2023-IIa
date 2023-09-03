package pe.edu.ulima.pm20232.aulavirtual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.ulima.pm20232.aulavirtual.screens.SplashScreen
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.AulaVirtualTheme
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen {
                        navController.navigate("main")
                    }
                }
                composable("main") {
                    // Replace with your main screen Composable
                    Text("Home")
                }
            }
        }
    }
}

