package com.github.projektmagma.magmaapp.auth.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.projektmagma.magmaapp.auth.presentation.model.RegistrationFormState

@Composable
fun ErrorText(
    state: RegistrationFormState
) {
    if (state.emailError != null) {
        Text(
            text = stringResource(state.emailError.messageId),
            color = MaterialTheme.colorScheme.error,
        )
    }
}