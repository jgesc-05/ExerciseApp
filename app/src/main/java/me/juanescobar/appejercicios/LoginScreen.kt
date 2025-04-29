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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun LoginScreen(myNavController : NavHostController) {
    //Estado email
    var inputEmail by remember { mutableStateOf("") }

    //Estado password
    var inputPassword by remember { mutableStateOf("") }

    val activity = LocalView.current.context as Activity

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize().fillMaxSize().padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Filled.FitnessCenter,
                contentDescription = null,
                modifier = Modifier.size(120.dp).padding(bottom = 18.dp),
                colorFilter = ColorFilter.tint(Color(0xFFF85F6A))
            )

            Text(
                text = "GymUNAB",
                modifier = Modifier.padding(bottom = 30.dp),
                color = Color.Gray
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Inicia Sesión",
                    color = Color(0xFFF85F6A),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = "¡Es un gusto verte nuevamente!",
                    modifier = Modifier.padding(top = 16.dp),
                    color = Color.Gray
                )
            }


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

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = Onclick@{
                    val (isEmailValid, emailError) = validateEmail(inputEmail)
                    val (isPasswordValid, passwordError) = validatePassword(inputPassword)

                    if (!isEmailValid || !isPasswordValid) {
                        val errorMessage = when {
                            !isEmailValid -> emailError
                            else -> passwordError
                        }

                        Toast.makeText(
                            activity.applicationContext,
                            errorMessage,
                            Toast.LENGTH_LONG
                        ).show()
                        return@Onclick
                    }

                    // Si las validaciones pasan, continúa con Firebase
                    val auth = Firebase.auth
                    auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                        .addOnCompleteListener(activity) { task ->
                            if (task.isSuccessful) {
                                myNavController.navigate("home")
                            } else {
                                Toast.makeText(
                                    activity.applicationContext,
                                    "Error en las credenciales",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF85F6A))
            ) {
                Text(text = "Iniciar Sesión", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }


            Spacer(modifier = Modifier.height(32.dp))

            TextButton(onClick = {myNavController.navigate("register")}) {
                Text(text = "¿No tienes cuenta? Regístrate", color = Color(0xFFF85F6A))
            }

        }
    }
}