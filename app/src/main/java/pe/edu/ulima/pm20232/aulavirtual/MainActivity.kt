package pe.edu.ulima.pm20232.aulavirtual

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.LoginScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screens.*
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.AulaVirtualTheme

class MainActivity : ComponentActivity() {
    private val loginScrennViewModel by viewModels<LoginScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AulaVirtualTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "splash") {
                        composable(route = "splash") {
                            SplashScreen {
                                navController.navigate("login")
                            }
                        }
                        composable(route = "reset_password") {
                            Log.d("ROUTER", "reset password")
                            ResetPasswordScreen(navController)
                        }
                        composable(route = "profile") {
                            Log.d("ROUTER", "profile")
                            ProfileScreen(navController)
                        }
                        composable(route = "pokemon/edit?pokemon_id={pokemon_id}", arguments = listOf(
                            navArgument("pokemon_id") {
                                type = NavType.IntType
                                defaultValue = 0
                            }
                        ), content = { entry ->
                            println("++++++++++++++++++++++++++++++++++++")
                            val pokemonId = entry.arguments?.getInt("pokemon_id")!!
                            println(pokemonId)
                            PokemonScreen(navController, pokemonId!!)
                        })
                        composable(route = "login") {
                            Log.d("ROUTER", "login")
                            LoginScreen(loginScrennViewModel, navController)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AulaVirtualTheme {
        Greeting("Android")
    }
}