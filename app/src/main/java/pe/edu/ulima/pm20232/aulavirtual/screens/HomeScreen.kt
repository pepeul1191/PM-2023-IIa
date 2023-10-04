package pe.edu.ulima.pm20232.aulavirtual.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.HomeScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray1200

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExercisesGrid(navController: NavController, model: HomeScreenViewModel){
    var intValue by remember { mutableStateOf(0) }
    LazyVerticalGrid(
        cells = GridCells.Fixed(4) // Specify the number of columns
    ) {
        items(model.pokemons.size) { i ->
            Column(){
                Image(
                    painter = rememberImagePainter(data = model.pokemons[i].imageUrl),
                    contentDescription = model.pokemons[i].name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            //Log.d("POKEMONS", model.pokemons[i].id.toString())
                            intValue = model.pokemons[i].id.toInt()
                            navController.navigate("pokemon/edit?pokemon_id=${intValue}")
                        },
                )
                Text(model.pokemons[i].name)
            }
        }
    }
}

@Composable
fun SelectOpitions(model: HomeScreenViewModel) {
    var expanded by remember { mutableStateOf(false) }
    // val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Lista de Generaciones")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledLabelColor = Gray1200, // Change the label color when disabled
                disabledBorderColor = Gray1200, // Change the border color when disabled
                disabledTextColor = Gray1200
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            for ((key, value) in model.generationsMap) {
                DropdownMenuItem(onClick = {
                    model.filterByGenerations(key)
                    selectedText = value
                    expanded = false
                }) {
                    Text(text = value)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, model: HomeScreenViewModel){
    model.listAll()
    model.getGenerations()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){
        SelectOpitions(model)
        ExercisesGrid(navController, model)
    }
}