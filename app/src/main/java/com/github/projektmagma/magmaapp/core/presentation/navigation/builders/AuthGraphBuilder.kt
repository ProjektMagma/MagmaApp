package com.github.projektmagma.magmaapp.core.presentation.navigation.builders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.auth.presentation.LoginScreen
import com.github.projektmagma.magmaapp.auth.presentation.OnBoardingScreen

fun NavGraphBuilder.authGraph(
    navHostController: NavHostController
) {
    navigation<Screen.AuthGraph>(
        startDestination = Screen.OnBoardingScreen
    ) {
        composable<Screen.OnBoardingScreen> {
            OnBoardingScreen(
                navHostController
            )
        }
        composable<Screen.LoginScreen> {
            LoginScreen()
        }
    }
}