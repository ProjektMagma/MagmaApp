package com.github.projektmagma.magmaapp.auth.presentation.login_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
fun LoginScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    snackbarCoroutine: CoroutineScope,
    viewModel: AuthViewModel = koinViewModel()
) {
    val passwordVisible = viewModel.passwordVisible
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var clicked by viewModel.clicked

    LaunchedEffect(viewModel.validationEvent) {
        var message by mutableStateOf(context.getString(R.string.error_unknown))
        viewModel.validationEvent.collect { event ->
            when (event) {
                is AuthViewModel.AuthEvent.Success -> {
                    message = context.getString(R.string.login_success)
                    navHostController.navigate(Screen.MainGraph)
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
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = R.string.login_banner),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            EmailField(
                email = state.email,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
                isError = state.emailError != null
            )
            PasswordField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                passwordVisible = passwordVisible,
                showPasswordVisibilityIcon = true,
                labelString = stringResource(id = R.string.password),
                passwordStateString = state.password,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) }
            )

            ErrorText(state)

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    modifier = Modifier.padding(4.dp),
                    checked = clicked,
                    onCheckedChange = { clicked = !clicked }
                )
                Text(
                    text = stringResource(id = R.string.remember_me),
                )
            }
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp),
                onClick = {
                    viewModel.onEvent(RegistrationFormEvent.Submit(RegistrationType.LOGIN))
                    keyboardController?.hide()
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp), text = stringResource(id = R.string.login),
                )
            }
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp),
                onClick = {
                    keyboardController?.hide()
                    navHostController.navigate(Screen.RegisterScreen)
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(id = R.string.register_redirect_button),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}