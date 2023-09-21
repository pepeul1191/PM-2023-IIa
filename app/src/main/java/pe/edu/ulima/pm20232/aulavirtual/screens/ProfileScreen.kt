package pe.edu.ulima.pm20232.aulavirtual.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService

@Composable
fun ImageView(url: String, height: Int, width: Int) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            // You can apply transformations here if needed
            transformations(CircleCropTransformation())
        }
    )
    Image(
        painter = painter,
        contentDescription = null, // Set a proper content description if required
        modifier = Modifier.size(width.dp, height.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonsGrid(navController: NavController){
    val pokemonService: PokemonService = PokemonService()
    val pokemons: List<Pokemon> = pokemonService.pokemonList
    var intValue by remember { mutableStateOf(0) }
    LazyVerticalGrid(
        cells = GridCells.Fixed(4) // Specify the number of columns
    ) {
        items(pokemons.size) { i ->
            Image(
                painter = rememberImagePainter(data = pokemons[i].imageUrl),
                contentDescription = pokemons[i].name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 10.dp)
                    .clickable {
                        Log.d("POKEMONS", pokemons[i].id.toString())
                        intValue = pokemons[i].id.toInt()
                        navController.navigate("pokemon/edit?pokemon_id=${intValue}")
                    },
            )
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController){
    val imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/25.png" // Replace with your image URL
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ImageView(url = imageUrl, width = 50, height = 50)
        Column() {
            Text("Nombre y Apellido")
            Text("Código")
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 90.dp)
    ){
        PokemonsGrid(navController)
    }
}