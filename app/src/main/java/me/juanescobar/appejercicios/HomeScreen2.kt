package me.juanescobar.appejercicios

import android.R.attr.strokeWidth
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FormatListNumberedRtl
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

//Pagina principal del trabajo
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen2(myNavController: NavController) {

    getExerciseCounter()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val db = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = Firebase.auth
    val currentUser = auth.currentUser
    var nombre by remember { mutableStateOf("") }
    val exercises = exerciseCounter.exerciseCounter
    var visitas by remember { mutableStateOf<Map<String, Long>>(emptyMap()) }

    // Estado para almacenar los ejercicios más realizados
    var topExercises by remember { mutableStateOf<List<Pair<String, Long>>>(emptyList()) }

    // Cargar los ejercicios más realizados del usuario actual
    LaunchedEffect(key1 = currentUser?.uid) {
        if (currentUser != null) {
            val userDocRef = db.collection("usuarios").document(currentUser.uid)

            userDocRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val exerciseFields = listOf("Flexión", "Press", "Sentadilla", "Zancada")

                        val exerciseCounts = document.data?.filter { entry ->
                            entry.key in exerciseFields && entry.value is Number
                        } ?: emptyMap()

                        val sortedExercises = exerciseCounts.entries
                            .map { Pair(it.key, it.value.toString().toLongOrNull() ?: 0L) }
                            .sortedByDescending { it.second }

                        topExercises = sortedExercises
                    }
                }
                .addOnFailureListener { e ->
                    println("Error al cargar los ejercicios: ${e.message}")
                }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
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
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Aquí puedes poner la foto de perfil si es necesario
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )

                    LaunchedEffect(key1 = currentUser?.uid) {
                        if (currentUser != null) {
                            val nombreDocumentoRef = db.collection("usuarios").document(currentUser.uid)

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

                // Primer NavigationDrawerItem
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
                            contentDescription = "Inicio"
                        )
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                // Segundo NavigationDrawerItem
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
                            contentDescription = "Ejercicios"
                        )
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                // Tercer NavigationDrawerItem
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
            }
        }
    ) {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
                    .fillMaxWidth().fillMaxSize().padding(horizontal = 10.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center


            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    //boton para desplegar el menu
                    IconButton(onClick = {scope.launch { drawerState.open() }}) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        "Rendimiento por días",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.LightGray)
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                            .background(Color.Black)
                            .size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Esta semana",
                            fontSize = 15.sp
                        )
                        //Implementación de círculos de progreso
                        Box(
                            modifier = Modifier
                                .size(82.dp)
                                .padding(top = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .matchParentSize(),
                                onDraw = {
                                    val sweepAngle = 360f * (exercises) / 7
                                    drawArc(
                                        Color.Green,
                                        -90f,
                                        sweepAngle,
                                        false,
                                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10f)
                                    )
                                },
                            )
                            Text(
                                text = exercises.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Dos semanas",
                            fontSize = 15.sp
                        )

                        Box(
                            modifier = Modifier
                                .size(82.dp)
                                .padding(top = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .matchParentSize(),
                                onDraw = {
                                    val sweepAngle = 360f * (exercises) / 14
                                    drawArc(
                                        Color.Red,
                                        -90f,
                                        sweepAngle,
                                        false,
                                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10f)
                                    )
                                },
                            )
                            Text(
                                text = exercises.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,

                                )
                        }

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Mes pasado\n (días)",
                            fontSize = 15.sp, textAlign = TextAlign.Center
                        )


                        Box(
                            modifier = Modifier
                                .size(82.dp)
                                .padding(top = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .matchParentSize(),
                                onDraw = {
                                    val sweepAngle = 360f * (exercises) / 30
                                    drawArc(
                                        Color.Cyan,
                                        -90f,
                                        sweepAngle,
                                        false,
                                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10f)
                                    )
                                },
                            )
                            Text(
                                text = exercises.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,

                                )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        "Ejercicios más realizados",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.LightGray)
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                            .background(Color.Black)
                            .size(20.dp)
                    )
                }

                // Lista dinámica de ejercicios más realizados
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (topExercises.isEmpty()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "No hay ejercicios registrados",
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 25.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(Color.LightGray)
                        )
                    } else {
                        // Mostrar hasta 3 ejercicios más realizados
                        topExercises.take(3).forEachIndexed { index, (exerciseName, count) ->
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = exerciseName,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(start = 25.dp)
                                )
                                Text(
                                    text = "$count veces",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(end = 25.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        "Otros usuarios",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.LightGray)
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                            .background(Color.Black)
                            .size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column() {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        //Circulo representa a una pantalla de usuario
                        Icon(
                            Icons.Default.Circle,
                            contentDescription = null,
                            modifier = Modifier.padding(
                                end = 10.dp
                            )
                                .size(60.dp)
                        )
                        Text(
                            "A. Lovelace",
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 25.sp
                        )
                        Icon(
                            Icons.Default.FitnessCenter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 15.dp)
                        )

                    }
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    //Modificación de los elementos

                    Row(modifier = Modifier.fillMaxWidth()) {
                        //Circulo representa a una pantalla de usuario
                        Icon(
                            Icons.Default.Circle,
                            contentDescription = null,
                            modifier = Modifier.padding(
                                end = 10.dp
                            )
                                .size(60.dp)
                        )
                        Text(
                            "F. Afanador",
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 25.sp
                        )
                        Icon(
                            Icons.Default.FitnessCenter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 15.dp)
                        )

                    }
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        //Circulo representa a una pantalla de usuario
                        Icon(
                            Icons.Default.Circle,
                            contentDescription = null,
                            modifier = Modifier.padding(
                                end = 10.dp
                            )
                                .size(60.dp)
                        )
                        Text(
                            "S. Afanador",
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 25.sp
                        )
                        Icon(
                            Icons.Default.FitnessCenter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 15.dp)
                        )

                    }
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                }
            }
        }
    }
}