package com.github.projektmagma.magmaapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.projektmagma.magmaapp.core.presentation.navigation.NavGraph
import com.github.projektmagma.magmaapp.core.presentation.ui.theme.MagmaAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MagmaAppTheme {
                val navController = rememberNavController()
                val snackbarState = remember { SnackbarHostState() }

                val mainViewModel = koinViewModel<MainViewModel>()
                LaunchedEffect(Unit) {
                    mainViewModel.initializeApp()
                }

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarState,
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavGraph(
                            mainViewModel,
                            navController,
                            snackbarState
                        )
                    }
                }
            }
        }
    }
}
