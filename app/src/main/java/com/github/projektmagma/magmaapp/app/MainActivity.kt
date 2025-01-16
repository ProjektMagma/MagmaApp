package com.github.projektmagma.magmaapp.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.github.projektmagma.magmaapp.core.presentation.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.core.presentation.ui.theme.MagmaAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver {_, event ->
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        lifecycleOwner.lifecycleScope.launch {
                            FirebaseAuth.getInstance().signOut()
                            Log.d("TEST2", "onCreate: ")
                        }
                    }
                    Log.d("TEST", "onCreate: ")
                    Log.d("EVENT", event.toString())
                }

                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }

            MagmaAppTheme {
                val mainViewModel = koinViewModel<MainViewModel>()
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
}
