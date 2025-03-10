package com.github.projektmagma.magmaapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.github.projektmagma.magmaapp.core.presentation.navigation.NavGraph
import com.github.projektmagma.magmaapp.core.presentation.ui.theme.MagmaAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                val mainViewModel: MainViewModel by viewModels()
                mainViewModel.startDestination.value == null
            }
        }

        setContent {
            val navHostController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }
            val snackbarCoroutine = rememberCoroutineScope()
            val mainViewModel = koinViewModel<MainViewModel>()
            val startDestination by mainViewModel.startDestination.collectAsStateWithLifecycle()
            val isAppInDarkMode by mainViewModel.isAppInDarkMode.collectAsStateWithLifecycle()

            MagmaAppTheme(isAppInDarkMode) {

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        startDestination?.let {
                            NavGraph(
                                startDestination!!,
                                navHostController,
                                snackbarHostState,
                                snackbarCoroutine
                            )
                        }
                    }
                }
            }
        }
    }
}
