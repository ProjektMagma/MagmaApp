package com.github.projektmagma.magmaapp.core.presentation.navigation.builders

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.projektmagma.magmaapp.auth.presentation.OnBoardingScreen
import com.github.projektmagma.magmaapp.auth.presentation.login_screen.LoginScreen
import com.github.projektmagma.magmaapp.auth.presentation.register_screen.RegisterScreen
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen.LoginScreen
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen.OnBoardingScreen
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen.RegisterScreen

fun NavGraphBuilder.authGraph(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    navigation<Screen.AuthGraph>(
        startDestination = OnBoardingScreen
    ) {
        composable<OnBoardingScreen> {
            OnBoardingScreen(navHostController)
        }
        composable<LoginScreen> {
            LoginScreen(
                navHostController,
                snackbarHostState
            )
        }
        composable<RegisterScreen> {
            RegisterScreen(
                navHostController,
                snackbarHostState
            )
        }
    }
}