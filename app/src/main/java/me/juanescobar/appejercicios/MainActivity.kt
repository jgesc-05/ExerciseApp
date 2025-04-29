package me.juanescobar.appejercicios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.juanescobar.appejercicios.ui.theme.AppEjerciciosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppEjerciciosTheme {
                val myNavController = rememberNavController()
                val myStartDestination = "login"

                NavHost(
                    navController = myNavController,
                    startDestination = myStartDestination
                ){
                    composable("login"){
                        LoginScreen(myNavController)
                    }

                    composable("register"){
                        RegisterScreen(myNavController)
                    }

                    composable("survey"){
                        SurveyScreen(myNavController)
                    }

                    composable("home") {
                        HomeScreen(myNavController)
                    }
            }
         }
       }
    }
}

