package me.juanescobar.appejercicios

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(myNavController : NavController) {

    //Estado email
    var inputEmail by remember { mutableStateOf("") }

    //Estado password
    var inputPassword by remember { mutableStateOf("") }

    //Estado repeatPassword
    var inputRepeatPassword by remember { mutableStateOf("") }

    //Estado name
    var inputName by remember { mutableStateOf("") }

    val activity = LocalView.current.context as Activity


    Scaffold(topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { myNavController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Registrarse en la app",
                color = Color(0xFFF85F6A),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = inputEmail,
                onValueChange = {inputEmail = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                label = { Text(text = "Correo Electrónico") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = inputName,
                onValueChange = {inputName = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text(text = "Nombre Completo") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )


            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = inputPassword,
                onValueChange = {inputPassword = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                label = { Text(text = "Contraseña") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = inputRepeatPassword,
                onValueChange = {inputRepeatPassword = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                label = { Text(text = "Confirmar contraseña") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Validaciones
                    val (emailValid, emailError) = validateEmail(inputEmail)
                    val (passValid, passError) = validatePassword(inputPassword)
                    val (nameValid, nameError) = validateName(inputName)



                    val repeatPassError = if (inputPassword != inputRepeatPassword) "Las contraseñas no coinciden" else ""

                    if (!emailValid || !passValid || nameError.isNotEmpty() || repeatPassError.isNotEmpty()) {
                        val firstError = listOf(nameError, emailError, passError, repeatPassError)
                            .firstOrNull { it.isNotEmpty() } ?: "Error desconocido"

                        Toast.makeText(activity.applicationContext, firstError, Toast.LENGTH_LONG).show()
                        return@Button
                    }

                    if (false) {
                        Toast.makeText(activity.applicationContext, "Error interno", Toast.LENGTH_LONG).show()
                        return@Button
                    }

                    // Firebase Auth
                    Firebase.auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                        .addOnCompleteListener(activity) { task ->
                            if (task.isSuccessful) {
                                // Mostrar mensaje y navegar a la pantalla "survey"
                                Toast.makeText(activity.applicationContext, "Usuario registrado correctamente", Toast.LENGTH_LONG).show()
                                myNavController.navigate("survey") // Navegación a la pantalla de encuesta
                            } else {
                                Toast.makeText(activity.applicationContext, "Error al registrar: ${task.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF85F6A)
                )
            ) {
                Text(text = "Registrarse", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}