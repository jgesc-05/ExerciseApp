package me.juanescobar.appejercicios

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

fun updateUserData(newName : String, newWeight : Double, newAge : Int, newHeight : Int, description : String){
    val db = Firebase.firestore
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val userDocument = db.collection("usuarios").document(userId!!)

    userDocument.update(
        mapOf(
            "name" to newName,
            "weight" to newWeight,
            "age" to newAge,
            "height" to newHeight,
            "description" to description
        )
    ).addOnSuccessListener {
        println("Datos actualizados correctamente")
    }.addOnFailureListener { e ->
        println("Error al actualizar los datos: $e")
    }
}

