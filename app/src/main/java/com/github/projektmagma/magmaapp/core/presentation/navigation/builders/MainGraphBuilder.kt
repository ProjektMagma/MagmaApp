package com.github.projektmagma.magmaapp.core.presentation.navigation.builders

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.HomeScreen
import com.github.projektmagma.magmaapp.home.presentation.NotebookEditScreen

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
        
        composable<Screen.NotebookEditScreen> { backStackEntry ->
            val notebookIndex = backStackEntry.toRoute<Screen.NotebookEditScreen>().index
            NotebookEditScreen(navHostController, snackbarHostState, notebookIndex)
        }
    }
}