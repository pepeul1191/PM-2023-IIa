package pe.edu.ulima.pm20232.aulavirtual

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.LoginScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.ProfileScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screens.*
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.AulaVirtualTheme

class MainActivity : ComponentActivity() {
    private val loginScrennViewModel by viewModels<LoginScreenViewModel>()
    private val profileScrennViewModel by viewModels<ProfileScreenViewModel>()

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

                    NavHost(navController, startDestination = "profile") {
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
                            ProfileScreen(navController, profileScrennViewModel)
                        }
                        composable(route = "pokemon/edit?pokemon_id={pokemon_id}", arguments = listOf(
                            navArgument("pokemon_id") {
                                type = NavType.IntType
                                defaultValue = 0
                            }
                        ), content = { entry ->
                            val pokemonId = entry.arguments?.getInt("pokemon_id")!!
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
