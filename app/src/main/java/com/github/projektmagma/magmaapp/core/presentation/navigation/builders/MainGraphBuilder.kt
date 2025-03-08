package com.github.projektmagma.magmaapp.core.presentation.navigation.builders

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.NotebookEditScreen
import com.github.projektmagma.magmaapp.home.presentation.home_screen.HomeScreen
import com.github.projektmagma.magmaapp.home.presentation.settings_screen.SettingsScreen

fun NavGraphBuilder.mainGraph(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    navigation<Screen.MainGraph>(
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen(navHostController, snackbarHostState)
        }

        composable<Screen.SettingsScreen> {
            SettingsScreen(navHostController, snackbarHostState)
        }

        composable<Screen.NotebookEditScreen> { backStackEntry ->
            val notebookId = backStackEntry.toRoute<Screen.NotebookEditScreen>().id
            NotebookEditScreen(navHostController, snackbarHostState, notebookId)
        }
    }
}