package me.juanescobar.appejercicios


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

data class ExerciseCategory(
    val name: String,
    val iconResId: Int,
    val exercises: List<String>
)

@Composable
fun ExerciseScreen(myNavController: NavController) {
    val categories = listOf(
        ExerciseCategory("Piernas y glúteos", R.drawable.sharp_tibia_alt_24, listOf("Sentadillas", "Zancadas")),
        ExerciseCategory("Pecho", R.drawable.rounded_rib_cage_24, listOf("Flexiones", "Press de pecho")),
    )

    var selectedCategory by remember { mutableStateOf<ExerciseCategory?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    if (selectedCategory == null) {
        // Pantalla de categorías
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .padding(vertical = 90.dp)
        ) {
            // Encabezado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Abraham, por favor ver como implementar el Scaffold acá para la navegación de cajón, ya que esta pantalla quedaría desconectada

            // Search bar con ícono
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar ejercicios") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = "Buscar"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF2F2F2),
                    focusedContainerColor = Color(0xFFF2F2F2),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFFF85F6A)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de categorías
            LazyColumn {
                items(categories.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }) { category ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .clickable { selectedCategory = category }
                    ) {
                        Image(
                            painter = painterResource(id = category.iconResId),
                            contentDescription = category.name,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = category.name, fontSize = 18.sp)
                    }
                    Divider()
                }
            }
        }
    } else {
        // Pantalla de subcategorías
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Encabezado con botón volver
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { myNavController.navigate("exercisesList") }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color(0xFFF85F6A)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = selectedCategory!!.name,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de ejercicios
            LazyColumn {
                items(selectedCategory!!.exercises) { exercise ->
                    Text(
                        text = exercise,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    )
                    Divider()
                }
            }
        }
    }
}


