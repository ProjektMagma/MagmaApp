package com.github.projektmagma.magmaapp.auth.presentation.register_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.auth.presentation.common.AuthModifiers
import com.github.projektmagma.magmaapp.auth.presentation.common.EmailField
import com.github.projektmagma.magmaapp.auth.presentation.common.ErrorText
import com.github.projektmagma.magmaapp.auth.presentation.common.PasswordField
import com.github.projektmagma.magmaapp.auth.presentation.common.RegistrationType
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormEvent
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    authViewModel: AuthViewModel = koinViewModel()
) {
    val passwordVisible = authViewModel.passwordVisible
    val state = authViewModel.state
    val snackbarScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(authViewModel.validationEvent) {
        var message by mutableStateOf(context.getString(R.string.error_unknown))
        authViewModel.validationEvent.collect { event ->
            when (event) {
                is AuthViewModel.AuthEvent.Success -> {
                    message = context.getString(R.string.register_success)
                    authViewModel.logout()
                    navHostController.navigate(Screen.OnBoardingScreen)
                }

                is AuthViewModel.AuthEvent.Failure -> {
                    message = context.getString(event.messageId)
                }
            }
            snackbarScope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }
    }

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(AuthModifiers.topSpacerModifier)
                Text(
                    modifier = AuthModifiers.textPaddingModifier,
                    text = stringResource(id = R.string.register_banner),
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp
                )
                EmailField(
                    email = state.email,
                    onValueChange = { authViewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
                    isError = state.emailError != null
                )
                ErrorText(state = state)
                PasswordField(
                    modifier = AuthModifiers.textFieldsModifier,
                    passwordVisible = passwordVisible,
                    isError = state.passwordError != null,
                    showPasswordVisibilityIcon = true,
                    labelString = stringResource(id = R.string.password),
                    passwordStateString = state.password,
                    onValueChange = { authViewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) }
                )
                ErrorText(state = state)
                PasswordField(
                    modifier = AuthModifiers.textFieldsModifier,
                    passwordVisible = passwordVisible,
                    isError = state.repeatedPasswordError != null,
                    showPasswordVisibilityIcon = false,
                    labelString = stringResource(id = R.string.repeat_password),
                    passwordStateString = state.repeatedPassword,
                    onValueChange = {
                        authViewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it)) }
                )
                ErrorText(state = state)
                Button(
                    modifier = AuthModifiers.buttonsModifier,
                    onClick = {
                        authViewModel.onEvent(RegistrationFormEvent.Submit(RegistrationType.REGISTER))
                        keyboardController?.hide()
                    }
                ) {
                    Text(
                        modifier = AuthModifiers.textPaddingModifier,
                        text = stringResource(id = R.string.register),
                    )
                }
                Button(
                    modifier = AuthModifiers.buttonsModifier,
                    onClick = {
                        keyboardController?.hide()
                        navHostController.navigate(Screen.LoginScreen)
                    }
                ) {
                    Text(
                        modifier = AuthModifiers.textPaddingModifier,
                        text = stringResource(id = R.string.login_redirect_button),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }