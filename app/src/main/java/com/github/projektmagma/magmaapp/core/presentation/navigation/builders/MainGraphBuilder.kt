package com.github.projektmagma.magmaapp.core.presentation.navigation.builders

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.edit_screen.NotebookEditScreen
import com.github.projektmagma.magmaapp.home.presentation.home_screen.HomeScreen
import com.github.projektmagma.magmaapp.home.presentation.settings_screen.SettingsScreen
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.mainGraph(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    isAppInDarkMode: MutableState<Boolean>
) {
    navigation<Screen.MainGraph>(
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen(navHostController, snackbarHostState, snackbarCoroutine)
        }

        composable<Screen.SettingsScreen> {
            SettingsScreen(navHostController, snackbarHostState, snackbarCoroutine, isAppInDarkMode)
        }

        composable<Screen.NotebookEditScreen> { backStackEntry ->
            val notebookId = backStackEntry.toRoute<Screen.NotebookEditScreen>().id
            NotebookEditScreen(navHostController, snackbarHostState, snackbarCoroutine, notebookId)
        }
    }
}