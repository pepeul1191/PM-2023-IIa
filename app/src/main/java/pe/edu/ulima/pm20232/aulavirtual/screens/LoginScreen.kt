package pe.edu.ulima.pm20232.aulavirtual.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.ulima.pm20232.aulavirtual.R
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.*
import androidx.compose.material.Text as Text1

@Composable
fun ButtonWithIcon(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange400, // Button background color
            contentColor = Color.White // Text and icon color
        ),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text1(text = text)
        }
    }
}

@Composable
fun TextFieldWithLeadingIcon(
    leadingIcon: ImageVector,
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (isFocused) Color.Blue else Color.Gray

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            //.border(1.dp, borderColor)
            .padding(5.dp)
            .background(color = Color.Transparent)
            ,
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text1(text = placeholder, fontSize = 16.sp)
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
             backgroundColor = Color.White,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Orange800
        ),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = (if (isSystemInDarkTheme()) White400 else Orange400),
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .clickable { /* Handle icon click if needed */ }
            )
        },
    )
}

@Composable
fun topScreen(){
    Column(modifier =Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange400)
                .weight(3f)
                .padding(8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val paddingPercentage = 60;
            val paddingValue = with(LocalDensity.current) {
                (paddingPercentage * 0.02f * 16.dp.toPx()).dp
            }
            Column(
                modifier = Modifier.padding(top = paddingValue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_ulima), // Replace with your SVG resource ID
                    contentDescription = "Universidad de Lima",
                    modifier = Modifier.size(120.dp),
                    colorFilter = ColorFilter.tint(White400),
                )
                Text1(
                    text = "Gimnasio UL",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    //fontSize = 40.sp,
                    modifier =  Modifier.padding(top = 20.dp, bottom = 20.dp),
                        style = MaterialTheme.typography.h4.copy(
                        fontSize = 40.sp,
                        fontFamily = FontFamily(Font(R.font.caslon_classico_sc_regular)),
                        color = if (isSystemInDarkTheme()) White400 else Orange400 // Apply the custom text color here
                    )
                )
            }
        }
    }
}

@Composable
fun loginForm(screenWidthDp: Int, screenHeightDp: Int){
    Box( // caja gris (light)
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (screenHeightDp * 0.30).dp,)
            .background(Gray1200),
    ) {
        Box(modifier = Modifier.padding(
            start = (screenWidthDp * 0.125).dp,
            top = (40.dp)
        ),){
            Box(
                modifier = Modifier
                    .size(
                        (screenWidthDp * 0.75).dp,
                        (screenHeightDp * 0.35).dp
                    ) // Adjust the size as needed
                    //.border(1.dp, Gray800)
                    .background(White400)
                    .shadow(
                        elevation = 5.dp,
                        shape = MaterialTheme.shapes.medium,
                        //color = Color.Gray
                    )
                    .padding(start = 20.dp, top = 30.dp, bottom = 20.dp, end = 20.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text1(text ="Bienvenido al Sistema", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    TextFieldWithLeadingIcon(
                        leadingIcon = Icons.Default.Person, // Replace with your desired icon
                        placeholder = "Usuario",
                        text = "",
                        onTextChanged = {
                            println(it)
                        }
                    )
                    TextFieldWithLeadingIcon(
                        leadingIcon = Icons.Default.Lock, // Replace with your desired icon
                        placeholder = "Contraseña",
                        text = "",
                        onTextChanged = {
                            println(it)
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                    ){
                        ButtonWithIcon("INGRESAR", Icons.Default.Person, {})
                    }
                }
            }
        }
    }
}

@Composable
fun goToReset(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Row() {
            Text1(text = "Olvidó su contraseña? ", textAlign = TextAlign.End, color = Gray800, fontSize = 16.sp)
            Text1(text = "Cambiala Aquí", textAlign = TextAlign.End, color = Orange400, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LoginScreen() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    topScreen()
    loginForm(screenWidthDp, screenHeightDp)
    goToReset()
}
