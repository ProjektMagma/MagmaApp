package com.github.projektmagma.magmaapp.auth.presentation.register_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.auth.presentation.common.EmailField
import com.github.projektmagma.magmaapp.auth.presentation.common.ErrorText
import com.github.projektmagma.magmaapp.auth.presentation.common.PasswordField
import com.github.projektmagma.magmaapp.auth.presentation.common.RegistrationType
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormEvent
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    authViewModel: AuthViewModel = koinViewModel()
) {
    val passwordVisible = authViewModel.passwordVisible
    val state = authViewModel.state
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
            snackbarCoroutine.launch {
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
            Spacer(Modifier.padding(32.dp))
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = R.string.register_banner),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            EmailField(
                email = state.email,
                onValueChange = { authViewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
                isError = state.emailError != null
            )
            PasswordField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                passwordVisible = passwordVisible,
                isError = state.passwordError != null,
                showPasswordVisibilityIcon = true,
                labelString = stringResource(id = R.string.password),
                passwordStateString = state.password,
                onValueChange = { authViewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) }
            )
            PasswordField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                passwordVisible = passwordVisible,
                isError = state.repeatedPasswordError != null,
                showPasswordVisibilityIcon = false,
                labelString = stringResource(id = R.string.repeat_password),
                passwordStateString = state.repeatedPassword,
                onValueChange = {
                    authViewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
                }
            )
            ErrorText(state)
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp),
                onClick = {
                    authViewModel.onEvent(RegistrationFormEvent.Submit(RegistrationType.REGISTER))
                    keyboardController?.hide()
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(id = R.string.register),
                )
            }
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp),
                onClick = {
                    keyboardController?.hide()
                    navHostController.navigate(Screen.LoginScreen)
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(id = R.string.login_redirect_button),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}