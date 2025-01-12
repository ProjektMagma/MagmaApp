package com.github.projektmagma.magmaapp.auth.presentation.register_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.projektmagma.magmaapp.R
import com.github.projektmagma.magmaapp.auth.presentation.AuthViewModel
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormEvent
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    viewModel: AuthViewModel = koinViewModel()
) {
    var passwordVisible by viewModel.passwordVisible
    var repeatedPasswordVisible by viewModel.repeatedPasswordVisible
    val state = viewModel.state
    val snackbarState = remember { SnackbarHostState() }
    val snackbarScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is AuthViewModel.ValidationEvent.Success -> {
                    snackbarScope.launch {
                        snackbarState.showSnackbar(context.getString(R.string.register_success))
                        navHostController.navigate(Screen.MainGraph)
                    }
                }
            }
        }
    }

    Scaffold(bottomBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(0.dp, 48.dp)
        ) {
            SnackbarHost(hostState = snackbarState)
        }
    }) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(id = R.string.register_banner),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
                },
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.email)
                    )
                },
                isError = state.emailError != null,
            )
            if (state.emailError != null) {
                Text(
                    text = state.emailError.name,
                    color = MaterialTheme.colorScheme.error
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = state.password,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.password)
                    )
                },
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description =
                        if (passwordVisible) stringResource(R.string.hide_password) else stringResource(
                            R.string.show_password
                        )

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                })
            if (state.passwordError != null) {
                Text(
                    text = state.passwordError.name,
                    color = MaterialTheme.colorScheme.error
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = state.repeatedPassword,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (repeatedPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.password)
                    )
                },
                trailingIcon = {
                    val image = if (repeatedPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description =
                        if (passwordVisible) stringResource(R.string.hide_password) else stringResource(
                            R.string.show_password
                        )

                    IconButton(onClick = { repeatedPasswordVisible = !repeatedPasswordVisible }) {
                        Icon(imageVector = image, description)
                    }
                })
            if (state.repeatedPasswordError != null) {
                Text(
                    text = state.repeatedPasswordError.name,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(4.dp),
                onClick = {
                    viewModel.onEvent(RegistrationFormEvent.Submit)
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
            Text(
                text = state.toString(),
            )
        }
    }
}