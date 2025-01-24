package com.github.projektmagma.magmaapp.core.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.authGraph
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.mainGraph

@Composable
fun NavGraph(
    screen: Screen,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    NavHost(
        navController = navHostController,
        startDestination = screen,
    ) {
        authGraph(navHostController, snackbarHostState)
        mainGraph(navHostController, snackbarHostState)
    }
}