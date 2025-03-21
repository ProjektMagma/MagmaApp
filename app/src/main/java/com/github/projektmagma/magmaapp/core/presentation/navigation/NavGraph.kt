package com.github.projektmagma.magmaapp.core.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.authGraph
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.mainGraph
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavGraph(
    screen: Screen,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    isAppInDarkMode: MutableState<Boolean>
) {

    NavHost(
        navController = navHostController,
        startDestination = screen,
    ) {
        authGraph(navHostController, snackbarHostState, snackbarCoroutine)
        mainGraph(navHostController, snackbarHostState, snackbarCoroutine, isAppInDarkMode)
    }
}