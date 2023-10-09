package pe.edu.ulima.pm20232.aulavirtual

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.ulima.pm20232.aulavirtual.components.BottomNavigationBar
import pe.edu.ulima.pm20232.aulavirtual.components.TopNavigationBar
import pe.edu.ulima.pm20232.aulavirtual.configs.BottomBarScreen
import pe.edu.ulima.pm20232.aulavirtual.configs.TopBarScreen
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.LoginScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.ProfileScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.HomeScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screens.*
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.AulaVirtualTheme

class MainActivity : ComponentActivity() {
    private val loginScrennViewModel by viewModels<LoginScreenViewModel>()
    private val profileScrennViewModel by viewModels<ProfileScreenViewModel>()
    private val homeScrennViewModel by viewModels<HomeScreenViewModel>()

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
                    Scaffold(
                        topBar = {
                            val screens: List<TopBarScreen> = listOf(
                                TopBarScreen(
                                    route = "home",
                                    title = "Home",
                                ),
                                TopBarScreen(
                                    route = "profile",
                                    title = "Ver Perfíl",
                                ),
                                TopBarScreen(
                                    route = "sign_out",
                                    title = "Cerrar Sesión",
                                ),
                            )
                            TopNavigationBar(navController, screens)
                        },
                        bottomBar = {
                            val screens: List<BottomBarScreen> = listOf(
                                BottomBarScreen(
                                    route = "home",
                                    title = "Home",
                                    icon = Icons.Default.Home
                                ),
                                BottomBarScreen(
                                    route = "profile",
                                    title = "Profile",
                                    icon = Icons.Default.Person
                                ),
                                BottomBarScreen(
                                    route = "settings",
                                    title = "Settings",
                                    icon = Icons.Default.Settings
                                ),
                            )
                            BottomNavigationBar(navController = navController, screens)
                        },
                        content = {
                            NavHost(navController, startDestination = "home") {
                                composable(route = "splash") {
                                    SplashScreen {
                                        navController.navigate("login")
                                    }
                                }
                                composable(route = "home") {
                                    Log.d("HOME", "home screen")
                                    HomeScreen(navController, homeScrennViewModel)
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
                    )

                }
            }
        }
    }
}
