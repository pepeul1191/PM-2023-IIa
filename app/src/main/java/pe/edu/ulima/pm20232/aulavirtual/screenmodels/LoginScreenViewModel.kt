package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel: ViewModel() {
    var user: String by mutableStateOf("")
    var password: String by mutableStateOf("")

    fun access(): Unit{
        println("BTN PRESSED")
        println(user)
        println(password)
    }
}