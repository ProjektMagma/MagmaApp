package com.github.projektmagma.magmaapp.core.presentation.navigation.builders

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.HomeScreen

fun NavGraphBuilder.mainGraph(
    navHostController: NavHostController,
    snackbarState: SnackbarHostState
) {
    navigation<Screen.MainGraph>(
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen(navHostController, snackbarState)
        }
    }
}