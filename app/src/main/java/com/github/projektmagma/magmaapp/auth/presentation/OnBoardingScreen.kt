package com.github.projektmagma.magmaapp.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.projektmagma.magmaapp.core.navigation.Screen

@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "OnBoarding Screen")
            Button(
                onClick = {
                    navHostController.navigate(Screen.RegisterScreen)
                }
            ) {
                Text(text = "Go to Register")
            }
        }
    }
}