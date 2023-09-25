package pe.edu.ulima.pm20232.aulavirtual.screens

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.ProfileScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService

@Composable
fun ImageView(url: String, height: Int, width: Int, viewModel: ProfileScreenViewModel) {
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
fun ProfileScreen( navController: NavController, viewModel: ProfileScreenViewModel, launcher: ActivityResultLauncher<Intent>
){
    val imageUrl = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/25.png"
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){uri: Uri? ->
        imageUri = uri
    }

    if (imageUri == null){
        val uri = Uri.parse(imageUrl)
        val painter = rememberImagePainter(
            data = uri.scheme + "://" + uri.host + uri.path + (if (uri.query != null) uri.query else ""),
            builder = {
                // You can apply transformations here if needed
                transformations(CircleCropTransformation())
            }
        )
        Image(
            painter = painter,
            contentDescription = null, // Set a proper content description if required
            modifier = Modifier.size(200.dp, 200.dp)
        )
    }else{
        imageUri?.let{
            if(Build.VERSION.SDK_INT < 28){
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }else{
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp).padding(20.dp)
                )
            }
        }
    }

    Row(){
        Button(
            onClick = {
                launcher.launch("image/*")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Seleccionar Imagen", fontSize = 16.sp)
        }
        Button(
            onClick = {
                imageUri= null
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Borrar Imagen", fontSize = 16.sp)
        }
    }



    /*
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ImageView(url = imageUrl, width = 50, height = 50, viewModel = viewModel, imageUri)
        Column() {
            Text("Nombre y Apellido")
            Text("Código")
        }
        Button(
            onClick = {

            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Seleccionar Imagen", fontSize = 16.sp)
        }
        Button(
            onClick = {

            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Compartir Imagen", fontSize = 16.sp)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 90.dp)
    ){
        PokemonsGrid(navController)
    }
    */
}