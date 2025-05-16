package me.juanescobar.appejercicios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

object exerciseCounter {
    var exerciseCounter by mutableIntStateOf(0)
}

fun storeExerciseCounter(){
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val db = FirebaseFirestore.getInstance()

    if (currentUser != null) {
        val userId = currentUser.uid
        val contador = exerciseCounter.exerciseCounter

        val userRef = db.collection("usuarios").document(userId)

        userRef.update("ejerciciosCounter", contador)
    }
}

fun getExerciseCounter() {
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    val db = FirebaseFirestore.getInstance()

    if (currentUser != null) {
        val userId = currentUser.uid
        val userRef = db.collection("usuarios").document(userId)
        userRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val contador = documentSnapshot.getLong("ejerciciosCounter")
                    if (contador != null) {
                        exerciseCounter.exerciseCounter = contador.toInt()
                    }
                }
            }
    }
}

fun mostEnteredExercises(exerciseName : String) {
    val currentUser = FirebaseAuth.getInstance().currentUser

    if (currentUser != null) {
        val userId = currentUser.uid
        val documentRef = FirebaseFirestore.getInstance().collection("usuarios").document(userId)

        documentRef.update(exerciseName, FieldValue.increment(1))
            .addOnFailureListener {
                val start = mapOf(exerciseName to 1)
                documentRef.set(start, SetOptions.merge())
            }
    }

}