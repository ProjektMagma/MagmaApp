package com.github.projektmagma.magmaapp.auth.presentation.login_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.github.projektmagma.magmaapp.auth.presentation.common.AuthModifiers
import com.github.projektmagma.magmaapp.auth.presentation.common.PasswordField
import com.github.projektmagma.magmaapp.auth.presentation.common.RegistrationType
import com.github.projektmagma.magmaapp.auth.presentation.common.SnackbarInfoEffect
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormEvent
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val passwordVisible = viewModel.passwordVisible
    val state = viewModel.state
    val snackbarState = remember { SnackbarHostState() }
    val snackbarScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val authModifiers = AuthModifiers()


    SnackbarInfoEffect(
        context = context,
        snackbarState = snackbarState,
        snackbarScope = snackbarScope,
        registrationType = RegistrationType.LOGIN,
        viewModel = viewModel,
        navHostController = navHostController,
        screen = Screen.HomeScreen,
    )

    Scaffold(bottomBar = {
        Box(
            modifier = authModifiers.snackbarModifier
                .background(MaterialTheme.colorScheme.background)
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
            Spacer(modifier = authModifiers.topSpacerModifier)
            Text(
                modifier = authModifiers.textPaddingModifier,
                text = stringResource(id = R.string.login_banner),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            TextField(
                modifier = authModifiers.textFieldsModifier,
                value = state.email,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.email)
                    )
                })
            PasswordField(
                modifier = authModifiers.textFieldsModifier,
                passwordVisible = passwordVisible,
                showPasswordVisibilityIcon = true,
                labelString = stringResource(id = R.string.password),
                passwordStateString = state.password,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) }
            )
            Button(
                modifier = authModifiers.buttonsModifier,
                onClick = {
                    viewModel.onEvent(RegistrationFormEvent.Submit)
                    keyboardController?.hide()
                }
            ) {
                Text(
                    modifier = Modifier.padding(4.dp), text = stringResource(id = R.string.login),
                )
            }
            Button(
                modifier = authModifiers.buttonsModifier,
                onClick = {
                    keyboardController?.hide()
                    navHostController.navigate(Screen.RegisterScreen)
                }
            ) {
                Text(
                    modifier = authModifiers.textPaddingModifier,
                    text = stringResource(id = R.string.register_redirect_button),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}