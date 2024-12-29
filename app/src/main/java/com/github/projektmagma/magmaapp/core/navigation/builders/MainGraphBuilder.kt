package com.github.projektmagma.magmaapp.core.navigation.builders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.projektmagma.magmaapp.core.navigation.Screen
import com.github.projektmagma.magmaapp.home.presentation.HomeScreen

fun NavGraphBuilder.mainGraph() {
    navigation<Screen.MainGraph>(
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen> {
            HomeScreen()
        }
    }
}