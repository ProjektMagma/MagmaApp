package com.github.projektmagma.magmaapp.auth.presentation.login_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.github.projektmagma.magmaapp.auth.presentation.common.AuthModifiers
import com.github.projektmagma.magmaapp.auth.presentation.common.ErrorText
import com.github.projektmagma.magmaapp.auth.presentation.common.PasswordField
import com.github.projektmagma.magmaapp.auth.presentation.common.RegistrationType
import com.github.projektmagma.magmaapp.auth.presentation.common.SnackbarInfoEffect
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormEvent
import com.github.projektmagma.magmaapp.core.presentation.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    snackbarState: SnackbarHostState,
    viewModel: AuthViewModel = koinViewModel()
) {
    val passwordVisible = viewModel.passwordVisible
    val state = viewModel.state
    val snackbarScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var clicked by remember { mutableStateOf(false) }

    SnackbarInfoEffect(
        context = context,
        snackbarState = snackbarState,
        snackbarScope = snackbarScope,
        registrationType = RegistrationType.LOGIN,
        viewModel = viewModel,
        navHostController = navHostController,
        screen = Screen.HomeScreen,
    )

    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = AuthModifiers.topSpacerModifier)
            Text(
                modifier = AuthModifiers.textPaddingModifier,
                text = stringResource(id = R.string.login_banner),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            TextField(
                modifier = AuthModifiers.textFieldsModifier,
                value = state.email,
                isError = state.emailError != null,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
                label = {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = stringResource(id = R.string.email)
                    )
                })
            ErrorText(state = state)
            PasswordField(
                modifier = AuthModifiers.textFieldsModifier,
                passwordVisible = passwordVisible,
                showPasswordVisibilityIcon = true,
                labelString = stringResource(id = R.string.password),
                passwordStateString = state.password,
                onValueChange = { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    modifier = AuthModifiers.textPaddingModifier,
                    checked = clicked,
                    onCheckedChange = { clicked = !clicked }
                )
                Text(
                    text = stringResource(id = R.string.remember_me),
                )
            }
            Button(
                modifier = AuthModifiers.buttonsModifier,
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
                modifier = AuthModifiers.buttonsModifier,
                onClick = {
                    keyboardController?.hide()
                    navHostController.navigate(Screen.RegisterScreen)
                }
            ) {
                Text(
                    modifier = AuthModifiers.textPaddingModifier,
                    text = stringResource(id = R.string.register_redirect_button),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}