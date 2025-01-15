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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.projektmagma.magmaapp.core.presentation.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.core.presentation.ui.theme.MagmaAppTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MagmaAppTheme {
                mainViewModel = koinViewModel<MainViewModel>()
                val navController = rememberNavController()
                val snackbarState = remember { SnackbarHostState() }

                Scaffold (
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
                            if (Firebase.auth.currentUser != null) Screen.MainGraph else Screen.AuthGraph,
                            navController,
                            snackbarState
                        )
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mainViewModel.userId){
            Firebase.auth.signOut()
        }
    }
}
