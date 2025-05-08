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
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FormatListNumberedRtl
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

//Pagina principal del trabajo
// Preview eliminado para navigation
@Composable
fun HomeScreen2(myNavController: NavController){
    Scaffold { innerPadding->
        Column (
            modifier = Modifier.padding(innerPadding)
                .fillMaxWidth().fillMaxSize().padding(horizontal = 10.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center


        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ){
                //boton para desplegar el menu
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = null,
                        modifier = Modifier.size(400.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Rendimiento por días",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp)
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
                    modifier   = Modifier
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
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Text("Esta semana",
                        fontSize = 15.sp)
                    //Implementación de círculos de progreso
                    Box ( modifier = Modifier
                        .size(82.dp)
                        .padding(top = 10.dp),
                        contentAlignment = Alignment.Center) {
                        Canvas(
                            modifier = Modifier
                                .matchParentSize(),
                            onDraw = {drawArc(Color.Green, -90f, 360f * 0.75f, false, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10f))},
                        )
                        Text(
                            text = "4/7",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text("Dos semanas",
                        fontSize = 15.sp)

                    Box ( modifier = Modifier
                        .size(82.dp)
                        .padding(top = 10.dp),
                        contentAlignment = Alignment.Center) {
                        Canvas(
                            modifier = Modifier
                                .matchParentSize(),
                            onDraw = {drawArc(Color.Red, -90f, 360f * 0.75f, false, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10f))},
                    )
                            Text(
                                text = "8/14",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,

                        )
                    }

                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Text("Mes pasado\n (días)",
                        fontSize = 15.sp, textAlign = TextAlign.Center)


                    Box ( modifier = Modifier
                        .size(82.dp)
                        .padding(top = 10.dp),
                        contentAlignment = Alignment.Center) {
                        Canvas(
                            modifier = Modifier
                                .matchParentSize(),
                            onDraw = {drawArc(Color.Cyan, -90f, 360f * 0.75f, false, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 10f))},
                        )
                        Text(
                            text = "25/30",
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
                Text("Ejercicios más realizados",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp)
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

            //Modificación de los tamaños de elementos

            Column(
            ){
                Spacer(modifier = Modifier.height(20.dp))
                //Texto de ejemplo por el momento
                Text("Sentadilla-1",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 25.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.height(20.dp))
                //Texto de ejemplo por el momento
                Text("Sentadilla-1",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 25.dp))
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.height(20.dp))
                //Texto de ejemplo por el momento
                Text("Sentadilla-1",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 25.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )


            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Otros usuarios",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp)
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

            Column () {
                Row (modifier = Modifier.fillMaxWidth()) {
                    //Circulo representa a una pantalla de usuario
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.padding(
                            end = 10.dp)
                            .size(60.dp)
                    )
                    Text("A. Lovelace",
                        modifier = Modifier.padding(top=10.dp),
                        fontSize = 25.sp)
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

                Row (modifier = Modifier.fillMaxWidth()) {
                    //Circulo representa a una pantalla de usuario
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.padding(
                            end = 10.dp)
                            .size(60.dp)
                    )
                    Text("F. Afanador",
                        modifier = Modifier.padding(top=10.dp),
                        fontSize = 25.sp)
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

                Row (modifier = Modifier.fillMaxWidth()) {
                    //Circulo representa a una pantalla de usuario
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.padding(
                            end = 10.dp)
                            .size(60.dp)
                    )
                    Text("S. Afanador",
                        modifier = Modifier.padding(top=10.dp),
                        fontSize = 25.sp)
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