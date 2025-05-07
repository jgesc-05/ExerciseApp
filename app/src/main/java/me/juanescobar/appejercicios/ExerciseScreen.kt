package me.juanescobar.appejercicios


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

data class ExerciseCategory(
    val name: String,
    val iconResId: Int,
    val exercises: List<String>
)

@Composable
fun ExerciseScreen() {
    val categories = listOf(
        ExerciseCategory("Piernas y glúteos", R.drawable.sharp_tibia_alt_24, listOf("Sentadillas", "Zancadas", "Peso muerto rumano")),
        ExerciseCategory("Pecho", R.drawable.rounded_rib_cage_24, listOf("Flexiones", "Press de pecho", "Aperturas de pecho")),
        ExerciseCategory("Espalda", R.drawable.baseline_scuba_diving_24, listOf("Remo con mancuernas", "Jalón al pecho", "Superman")),
        ExerciseCategory("Abdomen/Core", R.drawable.baseline_heart_broken_24, listOf("Crunches", "Plancha", "Mountain climbers")),
        ExerciseCategory("Hombros", R.drawable.baseline_person_24, listOf("Elevaciones laterales", "Press militar", "Pájaros")),
        ExerciseCategory("Brazos", R.drawable.sharp_wrist_24, listOf("Curl de bíceps", "Fondos de tríceps", "Curl martillo")),
        ExerciseCategory("Cardio y funcionales", R.drawable.baseline_diversity_1_24, listOf("Jumping jacks", "Burpees", "High knees"))
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
        ) {
            // Encabezado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Inicio", color = Color(0xFFF85F6A), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Ejercicios", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                IconButton(onClick = { selectedCategory = null }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings_backup_restore_24),
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

@Preview
@Composable
fun PreviewExerciseScreen() {
    ExerciseScreen()
}
