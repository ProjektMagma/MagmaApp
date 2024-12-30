package com.github.projektmagma.magmaapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.authGraph
import com.github.projektmagma.magmaapp.core.presentation.navigation.builders.mainGraph

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AuthGraph,
    ) {
        authGraph(navHostController)
        mainGraph()
    }
}