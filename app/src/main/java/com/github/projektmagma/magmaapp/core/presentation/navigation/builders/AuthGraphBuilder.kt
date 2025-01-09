package com.github.projektmagma.magmaapp.core.presentation.navigation.builders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.auth.presentation.LoginScreen
import com.github.projektmagma.magmaapp.auth.presentation.OnBoardingScreen
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen.LoginScreen
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen.OnBoardingScreen

fun NavGraphBuilder.authGraph(
    navHostController: NavHostController
) {
    navigation<Screen.AuthGraph>(
        startDestination = OnBoardingScreen
    ) {
        composable<OnBoardingScreen> {
            OnBoardingScreen(
                navHostController
            )
        }
        composable<LoginScreen> {
            LoginScreen()
        }
    }
}