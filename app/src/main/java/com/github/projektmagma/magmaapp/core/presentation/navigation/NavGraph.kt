package com.github.projektmagma.magmaapp.core.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.projektmagma.magmaapp.app.MainViewModel
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.authGraph
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.mainGraph

@Composable
fun NavGraph(
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    snackbarState: SnackbarHostState
) {

    val startDestination by mainViewModel.startDestination.collectAsStateWithLifecycle()

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {
        authGraph(navHostController, snackbarState)
        mainGraph(navHostController, snackbarState)
    }
}