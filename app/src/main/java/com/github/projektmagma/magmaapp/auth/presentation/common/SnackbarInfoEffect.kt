package com.github.projektmagma.magmaapp.auth.presentation.common

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SnackbarInfoEffect(
    context: Context,
    registrationType: RegistrationType,
    viewModel: AuthViewModel,
    snackbarState: SnackbarHostState,
    snackbarScope: CoroutineScope,
    navHostController: NavController,
    screen: Screen
) {
    val snackbarMessageSuccess = when (registrationType) {
        RegistrationType.LOGIN -> context.getString(R.string.login_success)
        RegistrationType.REGISTER -> context.getString(R.string.register_success)
    }
    LaunchedEffect(context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is AuthViewModel.ValidationEvent.Success -> {
                    when (registrationType) {
                        RegistrationType.LOGIN -> viewModel.login()
                        RegistrationType.REGISTER -> viewModel.register()
                    }
                    snackbarScope.launch {
                        snackbarState.showSnackbar(snackbarMessageSuccess)
                    }
                    navHostController.navigate(screen)
                }
            }
        }
    }
}

enum class RegistrationType {
    LOGIN,
    REGISTER
}