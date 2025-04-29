package me.juanescobar.appejercicios

import android.util.Patterns


fun validateEmail(email: String): Pair<Boolean, String> {
    return when {
        email.isEmpty() -> Pair(false, "El correo es requerido")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "Solo correos válidos")
        else -> Pair(true, "")
    }
}


fun validatePassword(password: String): Pair<Boolean, String> {
    return when {
        password.isEmpty() -> Pair(false, "La contraseña es requerida")
        password.length < 5 -> Pair(false, "La contraseña debe tener mininimo 6 caracteres")
        else -> Pair(true, "")
    }
}

fun validateName(name : String) : Pair<Boolean, String> {
    return when {
        name.isEmpty() -> Pair(false, "Es requerido el nombre completo")
        !name.trim().contains(" ") -> Pair(false, "Ingresa al menos un nombre y un apellido")
        else -> Pair(true, "")
    }
}

fun validateRepeatedPassword(password: String, repeatPassword: String): Pair<Boolean, String> {
    return when {
        repeatPassword.isEmpty() -> Pair(false, "Repite la contraseña")
        password != repeatPassword -> Pair(false, "Las contraseñas no coinciden")
        else -> Pair(true, "")
    }
}