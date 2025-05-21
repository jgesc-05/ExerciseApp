package me.juanescobar.appejercicios

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlin.js.ExperimentalJsFileName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen( myNavController: NavController) {

    val drawerState =
        rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var info by remember { mutableStateOf("") }

    val auth: FirebaseAuth = Firebase.auth
    val activity = LocalView.current.context as Activity
    val currentUser = auth.currentUser
    val db = FirebaseFirestore.getInstance()
    var nombre by remember { mutableStateOf("") }



    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {

                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier
                            .size(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar"
                        )

                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {


                    LaunchedEffect(key1 = currentUser?.uid) {
                        if (currentUser != null) {
                            val nombreDocumentoRef =
                                db.collection("usuarios").document(currentUser.uid)

                            nombreDocumentoRef.get()
                                .addOnSuccessListener { documentSnapshot ->
                                    if (documentSnapshot.exists()) {
                                        val nombreCompleto = documentSnapshot.getString("nombre")
                                        if (nombreCompleto != null) {
                                            nombre = nombreCompleto
                                        } else {
                                            nombre = "Nombre no encontrado"
                                        }
                                    } else {
                                        nombre = "Documento no encontrado"
                                    }
                                }
                                .addOnFailureListener { e ->
                                    nombre = "Error al cargar el nombre: ${e.message}"
                                }
                        } else {
                            nombre = "Usuario no autenticado"
                        }
                    }

                    Text(
                        text = nombre,
                        fontWeight = FontWeight.Bold
                    )

                }

                Spacer(modifier = Modifier.padding(top = 23.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Inicio") },
                    selected = false,
                    onClick = {
                        myNavController.navigate("home")
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Menu"
                        )
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)

                )
                NavigationDrawerItem(
                    label = { Text(text = "Ejercicios") },
                    selected = false,
                    onClick = {
                        myNavController.navigate("exercisesList")
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = "Menu"
                        )
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text(text = "Perfil") },
                    selected = false,
                    onClick = {
                        myNavController.navigate("profile")
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil"
                        )
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text(text = "Cerrar sesión") },
                    selected = false,
                    onClick = {
                        auth.signOut()
                        myNavController.navigate("login"){
                            popUpTo(0)
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Exit"
                        )
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )

            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Modificar perfil",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Nombre",
                    fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color(0xFFF85F6A),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name= it},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Peso",
                    fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color(0xFFF85F6A),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Edad",
                    fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color(0xFFF85F6A),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Descripción",
                    fontSize = 15.sp,
                    modifier = Modifier.align(Alignment.Start),
                    color = Color(0xFFF85F6A),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = info,
                    onValueChange = { info= it},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        updateUserData(name, weight.toDoubleOrNull() ?: 0.0 , age.toIntOrNull() ?: 0, info)

                        Toast.makeText(activity.applicationContext, "Información de perfil actualizada", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier
                        .size(300.dp,50.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF85F6A)
                    )
                ) {
                    Text("Guardar informacion")
                }

            }
        }
    }
}