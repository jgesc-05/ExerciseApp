package me.juanescobar.appejercicios

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SurveyScreen(myNavController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var estatura by remember { mutableStateOf("") }

    val context = LocalContext.current


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState(    ))
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cuéntanos un poco más de ti",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = {nombre = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.MonitorWeight, contentDescription = null) },
                label = { Text(text = "Nombre completo (1 nombre y 1 apellido)") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = peso,
                onValueChange = {peso = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.MonitorWeight, contentDescription = null) },
                label = { Text(text = "Peso (kg)") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = edad,
                onValueChange = {edad = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.Numbers, contentDescription = null) },
                label = { Text(text = "Edad (años)") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = estatura,
                onValueChange = {estatura = it},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.Height, contentDescription = null) },
                label = { Text(text = "Estatura (cm)") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(12.dp))



            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val (nameValid, nameError) = validateName(nombre)
                    if (!nameValid) {
                        Toast.makeText(context, nameError, Toast.LENGTH_LONG).show()
                        return@Button
                    }
                    saveUserDataSurvey(nombre, peso.toDoubleOrNull() ?: 0.0, edad.toIntOrNull() ?: 0, estatura.toIntOrNull() ?: 0)
                    myNavController.navigate("home")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF85F6A)
                )
            ) {
                Text(text = "Enviar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}