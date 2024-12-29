package com.github.projektmagma.magmaapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.github.projektmagma.magmaapp.core.navigation.NavGraph
import com.github.projektmagma.magmaapp.core.ui.theme.MagmaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MagmaAppTheme {
                val navController = rememberNavController()
                Surface {
                    NavGraph(navController)
                }
            }
        }
    }
}
