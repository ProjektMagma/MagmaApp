package com.github.projektmagma.magmaapp.core.navigation.builders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.projektmagma.magmaapp.auth.presentation.LoginScreen
import com.github.projektmagma.magmaapp.auth.presentation.OnBoardingScreen
import com.github.projektmagma.magmaapp.auth.presentation.RegisterScreen
import com.github.projektmagma.magmaapp.core.navigation.Screen

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
        composable<Screen.RegisterScreen> {
            RegisterScreen(
                navHostController
            )
        }
    }
}