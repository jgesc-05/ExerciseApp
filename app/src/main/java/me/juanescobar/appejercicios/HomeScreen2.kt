package me.juanescobar.appejercicios

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.FormatListNumberedRtl
import androidx.compose.material.icons.filled.List
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


//Pagina principal del trabajo
@Preview
@Composable
fun HomeScreen2(){
    Scaffold { innerPading->
        Column (
            modifier = Modifier.padding(innerPading)
                .fillMaxWidth(),
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
                        Icons.Default.List,
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
                Text("Rendimiento por dias",
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
                    //por el momento se pone iconos hasta que se pueda hacer la barra de progreso
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)

                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text("Dos semanas",
                        fontSize = 15.sp)
                    //por el momento se pone iconos hasta que se pueda hacer la barra de progreso
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)

                    )
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Text("Mes pasado",
                        fontSize = 15.sp)
                    Text("(días)",
                        fontSize = 15.sp)

                    //por el momento se pone iconos hasta que se pueda hacer la barra de progreso
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)

                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Ejercicios mas realizados",
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

            Column(
            ){
                Spacer(modifier = Modifier.height(20.dp))
                //Texto de ejemplo por el momento
                Text("Sentadilla-1",
                    fontSize = 20.sp,
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
                    fontSize = 20.sp,
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
                    fontSize = 20.sp,
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
                        modifier = Modifier.padding(start = 25.dp,
                            end = 10.dp)
                            .size(60.dp)
                    )
                    Text("Ada Lovela",
                        modifier = Modifier.padding(top=10.dp),
                        fontSize = 30.sp)
                    Icon(
                        Icons.Default.FitnessCenter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
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
                        modifier = Modifier.padding(start = 25.dp,
                            end = 10.dp)
                            .size(60.dp)
                    )
                    Text("Felipe Afanador",
                        modifier = Modifier.padding(top=10.dp),
                        fontSize = 30.sp)
                    Icon(
                        Icons.Default.FitnessCenter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
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
                        modifier = Modifier.padding(start = 25.dp,
                            end = 10.dp)
                            .size(60.dp)
                    )
                    Text("Sofia Afanador",
                        modifier = Modifier.padding(top=10.dp),
                        fontSize = 30.sp)
                    Icon(
                        Icons.Default.FitnessCenter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
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