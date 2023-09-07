package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

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


@Composable
fun ProfileScreen(){
    val imageUrl = "https://oliva.ulima.edu.pe/imagenes/fotos/49502.jpg" // Replace with your image URL
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ImageView(url = imageUrl, width = 100, height = 100)
        Column() {
            Text("Nombre y Apellido")
            Text("Código")
        }
    }
}