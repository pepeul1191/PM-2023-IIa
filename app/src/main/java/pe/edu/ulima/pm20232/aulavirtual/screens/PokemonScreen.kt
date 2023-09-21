package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PokemonScreen(navController: NavController, pokemonId: Int) {
    Text("pokemon id : ${pokemonId}")
}