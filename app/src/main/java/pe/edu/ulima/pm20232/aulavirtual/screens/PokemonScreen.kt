package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.HomeScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.PokemonScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonsGrid(navController: NavController, model: PokemonScreenViewModel){
    var intValue by remember { mutableStateOf(0) }
    val pokemons by model.pokemons.collectAsState()
    LazyVerticalGrid(
        cells = GridCells.Fixed(4) // Specify the number of columns
    ) {
        items(pokemons.size) { i ->
            Column(){
                println(pokemons[i].imageUrl)
                Image(
                    painter = rememberImagePainter(data = pokemons[i].imageUrl),
                    contentDescription = pokemons[i].name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            //Log.d("POKEMONS", model.pokemons[i].id.toString())
                            intValue = pokemons[i].id.toInt()
                            navController.navigate("pokemon/edit?pokemon_id=${intValue}")
                        },
                )
                Text(pokemons[i].name)
            }
        }
    }
}


@Composable
fun PokemonScreen(navController: NavController) {
    val model: PokemonScreenViewModel = PokemonScreenViewModel()
    model.fetchAll()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){
        // SelectOpitions(model)
        PokemonsGrid(navController, model)
    }
}