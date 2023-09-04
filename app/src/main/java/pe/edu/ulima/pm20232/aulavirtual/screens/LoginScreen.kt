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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.ulima.pm20232.aulavirtual.R
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Orange400
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.White400
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
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue, // Button background color
            contentColor = Color.White // Text and icon color
        ),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
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
            .padding(20.dp),
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        placeholder = {
            Text1(text = placeholder, fontSize = 16.sp)
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            // backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
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
fun LoginScreen() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

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
                (paddingPercentage * 0.07f * 16.dp.toPx()).dp
            }
            Column(
                modifier = Modifier.padding(top = paddingValue)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_ulima), // Replace with your SVG resource ID
                    contentDescription = "Universidad de Lima",
                    modifier = Modifier.size(120.dp),
                    colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) White400 else Orange400),
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ){
            Column {

                Text1(text = "Example", textAlign = TextAlign.End, color = Color.Blue, fontSize = 12.sp)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ){
            Column {

                Text1(text = "Example", textAlign = TextAlign.End, color = Color.Blue, fontSize = 12.sp)
            }
        }
    }
    Column(modifier =Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (screenHeightDp * 0.05).dp)
        ) {
            Box(
                modifier = Modifier
                    .size((screenWidthDp * 0.75).dp) // Adjust the size as needed
                    .align(Alignment.Center)
                    .border(1.dp, Color.White)
                    .padding(10.dp)
            ) {
                Column(){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text1("Bienvenido al Sistema")
                    }
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
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalArrangement = Arrangement.Center,
                    ){
                        ButtonWithIcon("INGRESAR", Icons.Default.Person, {})
                    }
                }
            }
        }
    }
}
