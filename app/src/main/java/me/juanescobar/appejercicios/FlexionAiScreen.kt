package me.juanescobar.appejercicios

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.common.PointF3D
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import me.juanescobar.appejercicios.exerciseCounter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlexionAiScreen(navController: NavController) {
    val context = LocalContext.current

    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val feedback = remember { mutableStateOf("") }
    val resultadoAngulo = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        exerciseCounter.exerciseCounter ++
        storeExerciseCounter()
        mostEnteredExercises("Flexión")
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri.value = it

            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            val inputImage = InputImage.fromBitmap(bitmap, 0)

            val options = AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.SINGLE_IMAGE_MODE)
                .build()

            val detector = PoseDetection.getClient(options)

            detector.process(inputImage)
                .addOnSuccessListener { pose ->
                    val leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
                    val leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)
                    val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)

                    if (leftElbow != null && leftWrist != null && leftShoulder != null) {
                        feedback.value = "¡Articulaciones detectadas!"
                        val angle = calculateAngle(
                            leftShoulder.position3D,
                            leftElbow.position3D,
                            leftWrist.position3D
                        )

                        resultadoAngulo.value = when {
                            angle > 160 -> "Cuerpo extendido: ${angle.toInt()}°"
                            angle in 100.0..160.0 -> "No bajó lo suficiente. Ángulo: ${angle.toInt()}°"
                            angle in 70.0..100.0 -> "Muy bien: ${angle.toInt()}°"
                            angle < 70.0 -> "Bajó demasiado o no lo hizo bien. Ángulo: ${angle.toInt()}°"
                            else -> ""
                        }
                    } else {
                        feedback.value = "No se detectaron bien las articulaciones. Intenta otra imagen."
                        resultadoAngulo.value = ""
                    }
                }
                .addOnFailureListener { e ->
                    feedback.value = "Error: ${e.message}"
                    resultadoAngulo.value = ""
                }
        }
    }

    TopAppBar(
        title = { Text("Flexión de pecho clásica") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color(0xFFF85F6A)
                )
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF85F6A)),
        ) {
            Text("Seleccionar imagen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        imageUri.value?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Imagen seleccionada",
                modifier = Modifier
                    .size(500.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = feedback.value)
            if (resultadoAngulo.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = resultadoAngulo.value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF85F6A))
            }
        }
    }
}

