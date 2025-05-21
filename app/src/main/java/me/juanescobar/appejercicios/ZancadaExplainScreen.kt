package me.juanescobar.appejercicios

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ZancadaExplainScreen(myNavController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(20.dp)
    ) { innerPadding ->

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            IconButton(
                onClick = {myNavController.popBackStack()},
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFF85F6A)
                )

            }
            Text(
                text = "Zancada",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Image(
                painter = painterResource(id = R.drawable.zancada),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 30.dp)
            )

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Descripción del ejercicio",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                Text(
                    text = "Es un ejercicio para el tren inferior que consiste en dar un paso grande adelante o atrás con una pierna, doblando la rodilla de la pierna adelantada y manteniendo la rodilla de la pierna trasera cerca del suelo. Se trabaja principalmente la musculatura de las piernas y glúteos.",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 40.dp))

            Text(
                text = "¿No estás seguro de si lo estás haciendo bien?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            Button(
                onClick = {
                    myNavController.navigate("ZancadaAi")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF85F6A),
                )
            ) {
                Text(
                    text = "Verifica tu postura",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        }
    }
}