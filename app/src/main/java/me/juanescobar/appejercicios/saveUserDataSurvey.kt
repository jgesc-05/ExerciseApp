package me.juanescobar.appejercicios


import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

fun saveUserDataSurvey(nombre : String, peso: Double, edad: Int, estatura: Int) {
    val db = Firebase.firestore
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    if (userId != null) {
        val userData = hashMapOf(
            "nombre" to nombre,
            "peso" to peso,
            "edad" to edad,
            "estatura" to estatura
        )

        db.collection("usuarios").document(userId)
            .set(userData)
            .addOnSuccessListener {
                println("Datos guardados correctamente")
            }
            .addOnFailureListener { e ->
                println("Error al guardar los datos: $e")
            }
    } else {
        println("No hay usuario autenticado")
    }
}