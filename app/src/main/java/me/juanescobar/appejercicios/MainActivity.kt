package me.juanescobar.appejercicios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                ) {
                    composable("login") {
                        LoginScreen(myNavController)
                    }

                    composable("register") {
                        RegisterScreen(myNavController)
                    }

                    composable("survey") {
                        SurveyScreen(myNavController)
                    }

                    composable("home") {
                        HomeScreen2(myNavController)
                    }

                    composable("exercisesList") {
                        ExerciseScreen(myNavController)
                    }

                    composable("sentadillaExplain") {
                        SentadillaExplainScreen(myNavController)
                    }

                    composable("sentadillaAi") {
                        SentadillaAiScreen(myNavController)
                    }

                    composable("flexionExplain"){
                        FlexionExplainScreen(myNavController)
                    }

                    composable("flexionAi") {
                        FlexionAiScreen(myNavController)
                    }

                    composable("pressExplain") {
                        PressExplainScreen(myNavController)
                    }

                    composable("pressAi") {
                        PressAiScreen(myNavController)
                     }

                     composable("zancadaExplain") {
                        ZancadaExplainScreen(myNavController)

                      }

                      composable("zancadaAi") {
                        ZancadaAiScreen(myNavController)
                      }



                }
            }

        }
    }
}


