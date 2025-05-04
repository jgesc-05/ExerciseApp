package me.juanescobar.appejercicios

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color

data class ExerciseCategory(
    val name: String,
    val iconResId: Int,
    val exercises: List<String>
)

@Composable
fun ExerciseCategoryList(categories: List<ExerciseCategory>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(categories) { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = category.iconResId),
                    contentDescription = category.name,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = category.name, fontSize = 20.sp, color = Color(0xFFF85F6A))
            }

            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier.padding(start = 44.dp)) {
                category.exercises.forEach { exercise ->
                    Text(text = "• $exercise", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Composable
fun ExerciseScreen() {
    val categories = listOf(
        ExerciseCategory(
            "Piernas y glúteos",
            R.drawable.tibia_alt_24,
            listOf("Sentadillas", "Zancadas", "Peso muerto rumano", "Puente de glúteos",
                "Elevaciones de talones", "Step-ups", "Sentadillas búlgaras")
        ),
        ExerciseCategory(
            "Pecho",
            R.drawable.rib_cage_24,
            listOf("Flexiones", "Press de pecho", "Aperturas de pecho", "Fondos en paralelas")
        ),
        ExerciseCategory(
            "Espalda",
            R.drawable.scuba_diving_24,
            listOf("Remo con mancuernas", "Jalón al pecho", "Superman", "Peso muerto convencional")
        ),
        ExerciseCategory(
            "Abdomen/Core",
            R.drawable.heart_broken_24,
            listOf("Crunches", "Plancha", "Plancha lateral", "Elevaciones de piernas",
                "Mountain climbers", "Russian twists")
        ),
        ExerciseCategory(
            "Hombros",
            R.drawable.person_24,
            listOf("Elevaciones laterales", "Press militar", "Elevaciones frontales", "Pájaros")
        ),
        ExerciseCategory(
            "Brazos",
            R.drawable.wrist_24,
            listOf("Curl de bíceps", "Fondos de tríceps", "Extensiones de tríceps", "Curl martillo")
        ),
        ExerciseCategory(
            "Cardio y funcionales",
            R.drawable.diversity_1_24,
            listOf("Jumping jacks", "Burpees", "High knees", "Saltos en tijera", "Sprints estáticos")
        )
    )

    ExerciseCategoryList(categories)
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseScreen() {
    ExerciseScreen()
}
